/*
Entry point for the program
 */

package MazeSolver;

import java.util.ArrayList;

public class MazeSolver {
    public static void main(String[] args) {
        ImageConverter a = new ImageConverter("path\\to\\file");
        Node[][] nodes = a.to2Darray();
        Solver solve = new Solver(nodes);
        ArrayList<Node> solution = solve.aStar(a.findStartNode(nodes), a.findEndNode(nodes));
        a.toImage(nodes, solution);
    }
}
