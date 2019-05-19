/*
Used for containing information needed for A*
 */
package MazeSolver;

import java.util.ArrayList;

public class Node {

    private double f, g, h;
    private int row, col; // row and col
    private int state;
    private ArrayList<Node> neighbors = new ArrayList<>();

    public Node(int r, int c, int state) {
        this.state = state;
        row = r;
        col = c;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public double getF() {
        return f;
    }

    public double getG() {
        return g;
    }

    public double getH() {
        return h;
    }

    public void setF(double f) {
        this.f = f;
    }

    public void setG(double g) {
        this.g = g;
    }

    public void setH(double h) {
        this.h = h;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void addNeighbor(Node b){
        neighbors.add(b);
    }

    @Override
    public String toString(){
        return "["+row+"]["+col+"]";
    }

    public ArrayList<Node> getNeighbors(){
        return neighbors;
    }

    @Override
    public boolean equals(Object o){
        if (o ==this){
            return true;
        }
        if (!(o instanceof Node)){
            return false;
        }
        Node n = (Node) o;
        return row == n.getRow() && col==n.getRow();
    }
}
