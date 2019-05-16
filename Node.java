/*
Used for containing information needed for A*
 */
public class Node {

    private double f, g, h;
    private int row, col; // row and col
    private int state;

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
}
