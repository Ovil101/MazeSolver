/*
Class for converting between Node[][] and BufferedImage

Each BufferedImage is converted to a Node[][], where each Node object
is a pixel of the original input image
 */
package MazeSolver;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class ImageConverter {

    private BufferedImage image;
    private int x;
    private int y;
    private final String path;
    
    public ImageConverter(String path) {
        try {
            image = ImageIO.read(new FileInputStream(path));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        this.path = path;
        this.x = image.getWidth(); // done for readability of to2Darray()
        this.y = image.getHeight();
    }

    public Node[][] to2Darray() { // nested loop does [j][i] as [i][j] reflects along line from top left to bot right
        Node[][] nodes = new Node[x][y];

        for (int i = 0; i < nodes.length; i++){ // inital assignment/null pointer
            for (int j = 0; j < nodes.length; j++){
                nodes[i][j] = new Node(i,j,false); // the [j][i] thing doesn't matter here
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Color t = new Color(image.getRGB(i, j));
                if (t.equals(Color.BLACK)) {
                    nodes[j][i].setIsWall(true); //black pixels are walls
                } 
                else if (t.equals(Color.WHITE)) {
                    nodes[j][i].setIsWall(false); //white pixels are paths
                } 
                else { // is not black or white
                    try {
                        throw new Exception("Pixel at [" + i + "][" + j + "]" + " is not black or white");
                    } 
                    catch (Exception e) {
                        System.out.println("Java threw an exception while throwing an exception. God help you" +
                                " if you ever see this. But if you do, there might be a pixel in the maze that is not b/w");
                    }
                }
            }
        }

        for (int row = 0; row < x; row++){ // add neighbors, if neighbor does not exist (out of bounds) it makes it null
            for (int col = 0; col < y; col++){
                try{
                    nodes[row][col].addNeighbor(nodes[row-1][col]); // Node to the top
                }
                catch (IndexOutOfBoundsException e){
                    nodes[row][col].addNeighbor(null);
                }

                try{
                    nodes[row][col].addNeighbor(nodes[row][col+1]); // Node to the right
                }
                catch (IndexOutOfBoundsException e){
                    nodes[row][col].addNeighbor(null);
                }

                try{
                    nodes[row][col].addNeighbor(nodes[row+1][col]); // Node to the bottom
                    //System.out.println(row+" "+col+" try 3");
                }
                catch (IndexOutOfBoundsException e){
                    nodes[row][col].addNeighbor(null);
                }

                try{
                    nodes[row][col].addNeighbor(nodes[row][col-1]); // Node to the left
                    //System.out.println(row+" "+col+" try 4");
                }
                catch (IndexOutOfBoundsException e){
                    //System.out.println(row+" "+col+" catch 4");
                }
            }
        }

        return nodes;
    }

    public void toImage(Node[][] graph, ArrayList<Node> solution) { // converts to image and saves it at location from constructor
        BufferedImage imageCopy = this.image; // writing to copy image rather than rebuilding the image
        int index = path.lastIndexOf("\\"); // change this to \\ if on Windows
        File file = new File(path.substring(0, index) + "\\solved.png"); // remove the filename from filepath
        final int RED = new Color(255, 0, 0).getRGB(); // for readability

        for (int i = 0; i < x; i++) { // convert to BufferedImage
            for (int j = 0; j < y; j++) {
                System.out.println(solution);
                if (solution.contains(graph[j][i])){
                    imageCopy.setRGB(i,j,RED);
                }
            }
        }

        try {
            ImageIO.write(image, "png", file);
        } 
        catch (IOException e) { // something went wrong with writing image
            e.printStackTrace();
        }
    }

    public Node findEndNode(Node[][] graph){
        for (int i = 0; i < y; i++){
            if (!graph[y-1][i].isWall()){
                return graph[y-1][i];
            }
        }
        return null;
    }

    public Node findStartNode(Node[][] graph){
        for (int i = 0; i < y; i++){
            if (!graph[0][i].isWall()){
                return graph[0][i];
            }
        }
        return null;
    }
}
