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
    private int width;
    private int height;
    private final String path;

    public ImageConverter(String path) {
        try {
            image = ImageIO.read(new FileInputStream(path));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.path = path;
        this.width = image.getWidth(); // done for readability of to2Darray()
        this.height = image.getHeight(); // please don't pass an empty image
    }

    /*
    Converts a BufferedImage into a Node[][]
     */
    public Node[][] to2Darray() { // nested loop does [j][i] as [i][j] reflects along line from top left to bot right
        Node[][] nodes = new Node[width][height];

        for (int i = 0; i < nodes.length; i++) { // initial assignment/null pointer
            for (int j = 0; j < nodes.length; j++) {
                nodes[i][j] = new Node(i, j, false); // the [j][i] thing doesn't matter here
            }
        }

        for (int i = 0; i < width; i++) { // build the array
            for (int j = 0; j < height; j++) {
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
                        e.printStackTrace(); // because throwing an exception while throwing an exception is a thing
                    }
                }
            }
        }

        for (int row = 0; row < width; row++) { // add neighbors, if neighbor does not exist (out of bounds) it makes it null
            for (int col = 0; col < height; col++) {
                try {
                    nodes[row][col].addNeighbor(nodes[row - 1][col]); // Node to the top
                }
                catch (IndexOutOfBoundsException e) {
                    nodes[row][col].addNeighbor(null);
                }

                try {
                    nodes[row][col].addNeighbor(nodes[row][col + 1]); // Node to the right
                }
                catch (IndexOutOfBoundsException e) {
                    nodes[row][col].addNeighbor(null);
                }

                try {
                    nodes[row][col].addNeighbor(nodes[row + 1][col]); // Node to the bottom
                }
                catch (IndexOutOfBoundsException e) {
                    nodes[row][col].addNeighbor(null);
                }

                try {
                    nodes[row][col].addNeighbor(nodes[row][col - 1]); // Node to the left
                }
                catch (IndexOutOfBoundsException e) {
                    nodes[row][col].addNeighbor(null);
                }
            }
        }
        return nodes;
    }

    /*
    This method was originally done by copying the global image into a new variable
    and writing the solution over the image. This however made the entire image
    black and white, and the solution wasn't obvious. So here we are, rebuilding
    the image.
     */
    public void toImage(Node[][] graph, ArrayList<Node> solution) { // converts to image and saves it at location from constructor
        BufferedImage ret = new BufferedImage(graph[0].length, graph.length, BufferedImage.TYPE_INT_RGB);
        int index = path.lastIndexOf("/"); // change this according to your OS path convention
        File file = new File(path.substring(0, index) + "/solved.png"); // remove the filename from filepath
        final int RED = new Color(255, 0, 0).getRGB(); // for readability
        final int BLACK = new Color(0, 0, 0).getRGB();
        final int WHITE = new Color(255, 255, 255).getRGB();

        for (int row = 0; row < width; row++) { // rebuild image
            for (int col = 0; col < height; col++) {
                if (graph[col][row].isWall()) {
                    ret.setRGB(row, col, BLACK);
                }
                if (!graph[col][row].isWall()) {
                    ret.setRGB(row, col, WHITE);
                }
            }
        }
        try { // make sure
            for (Node n : solution) { // put solution
                ret.setRGB(n.getCol(), n.getRow(), RED);
            }
        }
        catch (NullPointerException e) {
            // this is here so the output is just the input
        }

        try {
            ImageIO.write(ret, "png", file);
        }
        catch (IOException e) { // something went wrong with writing image
            e.printStackTrace();
        }
    }

    public Node findEndNode(Node[][] graph) { // these are here for simplicity
        for (int i = 1; i < height; i++) { // start at 1 because col 0 can never be valid start/end point
            if (!graph[height - 1][i].isWall()) {
                return graph[height - 1][i];
            }
        }
        return null;
    }

    public Node findStartNode(Node[][] graph) {
        for (int i = 1; i < height; i++) {
            if (!graph[0][i].isWall()) {
                return graph[0][i];
            }
        }
        return null;
    }
}
