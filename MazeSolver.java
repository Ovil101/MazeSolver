/*
Entry point for the program
 */

package MazeSolver;

import java.util.ArrayList;

public class MazeSolver {
    public static void main(String[] args) {
        ImageConverter a = new ImageConverter(args[0]);
        Node[][] nodes = a.to2Darray();
        Solver solve = new Solver(nodes);
        long start = System.nanoTime();
        ArrayList<Node> solution = solve.aStar(a.findStartNode(nodes), a.findEndNode(nodes));
        long finish = System.nanoTime();
        System.out.println("Solving using A* pathfinding");
        System.out.println("Start time: "+start+"ns");
        System.out.println("End time: "+finish+"ns");
        System.out.println("Time: "+(finish-start)+"ns ("+(finish-start)/1e9+"s)");
        a.toImage(nodes, solution);        
    }
}
