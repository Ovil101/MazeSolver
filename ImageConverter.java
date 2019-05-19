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



public class ImageConverter {

    private BufferedImage image;
    private int x;
    private int y;
    private String path;
    
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
                nodes[i][j] = new Node(i,j,0); // the [j][i] thing doesn't matter here
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Color t = new Color(image.getRGB(i, j));
                if (t.equals(Color.BLACK)) {
                    nodes[j][i].setState(1); //black pixels are walls
                } 
                else if (t.equals(Color.WHITE)) {
                    nodes[j][i].setState(0); //white pixels are paths
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
                }
                catch (IndexOutOfBoundsException e){
                    nodes[row][col].addNeighbor(null);
                }

                try{
                    nodes[row][col].addNeighbor(nodes[row][col-1]); // Node to the left
                }
                catch (IndexOutOfBoundsException e){
                    nodes[row][col].addNeighbor(null);
                }
            }
        }

        return nodes;
    }

    public void toImage(Node[][] graph) { // converts to image and saves it at location from constructor
        BufferedImage image = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        int index = path.lastIndexOf("\\"); // change this to \\ if on Windows
        File file = new File(path.substring(0, index) + "\\solved.png"); // remove the filename from filepath
        
        final int RED = new Color(255, 0, 0).getRGB(); // for readability
        final int BLACK = new Color(0, 0, 0).getRGB();
        final int WHITE = new Color(255, 255, 255).getRGB();

        for (int i = 0; i < x; i++) { // convert to BufferedImage
            for (int j = 0; j < y; j++) {
                if (graph[i][j].getState() == 0) { // empty path
                    image.setRGB(j, i, WHITE);
                } 
                else if (graph[i][j].getState() == 1) { // wall
                    image.setRGB(j, i, BLACK);
                } 
                else if (graph[i][j].getState() == 2) { // solved path
                    image.setRGB(j, i, RED);
                }
            }
        }

        try {
            ImageIO.write(image, "png", file);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
