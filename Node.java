/*
Used for containing information needed for A*
 */
public class Node {
    private double f, g, h;
    private int r, c; // row and col
    private boolean isWall;

    public Node(int r, int c, boolean isWall) {
        this.isWall = isWall;
        this.r = r;
        this.c = c;
    }

    public void heuristic(Node end) {
        double a = end.getCol();
        double b = end.getRow();
        this.h = Math.sqrt(a * a + b * b); // smallest distance to end
    }

    public int getRow() {
        return r;
    }

    public int getCol() {
        return c;
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

    public boolean getState() {
        return isWall;
    }

    public void setState(boolean isWall) {
        this.isWall = isWall;
    }
}
