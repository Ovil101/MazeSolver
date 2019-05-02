/*
Class for converting maze image to a 2D Node object array
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageConverter {

    private BufferedImage image;
    private int x;
    private int y;

    public ImageConverter(String path) {
        try {
            image = ImageIO.read(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        x = image.getWidth(); // done for readability of to2Darray()
        y = image.getHeight();
    }

    public Node[][] to2Darray() { // nested loop does (j,i) as (i,j) reflects along line from top left to bot right
        Node[][] nodes = new Node[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Color t = new Color(image.getRGB(i, j));
                if (t.getRed() == 0 && t.getGreen() == 0 && t.getBlue() == 0) {
                    nodes[j][i].setState(true); //black pixels are walls
                } else if (t.getRed() == 1 && t.getGreen() == 1 && t.getBlue() == 1) {
                    nodes[j][i].setState(false); //white pixels are paths
                } else { // is not black or white
                    try {
                        throw new Exception("Pixel at (" + i + "," + j + ")" + " is not black or white");
                    } catch (Exception e) {
                        System.out.println("Java threw an exception while throwing an exception. God help you" +
                                " if you ever see this. But if you do, there might be a pixel in the maze that is not b/w");
                    }
                }
            }
        }
        return nodes;
    }

    public void toImage(Node[][] graph){

    }
}
