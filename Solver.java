/*
Implementation of the A* pathfinding algorithm
 */

import java.util.ArrayList;

public class Solver {

    private Node[][] graph;

    public Solver(Node[][] graph) {
        this.graph = graph;
    }

    public void aStar(Node start, Node finish){ // solves maze using A*
    	ArrayList<Node> checked = new ArrayList<>();
    	ArrayList<Node> open = new ArrayList<>(start);
    	ArrayList<Node> backtrack = new ArrayList<>();
    	gScore = 

    }

    private void heuristic(Node end) {
        double a = end.getCol();
        double b = end.getRow();
        end.setH(Math.sqrt(a * a + b * b)); // smallest distance to end
    }
}
