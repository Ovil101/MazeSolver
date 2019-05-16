/*
Implementation of the A* pathfinding algorithm
 */
public class Solver {

    private Node[][] graph;

    public Solver(Node[][] graph) {
        this.graph = graph;
    }

    public void aStar(){ // solves maze using A*

    }

    private void heuristic(Node end) {
        double a = end.getCol();
        double b = end.getRow();
        end.setH(Math.sqrt(a * a + b * b)); // smallest distance to end
    }
}
