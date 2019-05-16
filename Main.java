/*
Entry point for the program
 */
public class Main {
    public static void main(String[] args) {
        ImageConverter a = new ImageConverter("C:\\Users\\rlioy\\documents\\java\\Maze\\src\\mazes\\small.png");
        long start = System.nanoTime();

        Node[][] nodes = a.to2Darray();
        a.toImage(nodes);

        long end = System.nanoTime();

        System.out.println((end-start)/1e9);

    }
}
