/*
Entry point for the program
 */
public class Main {
    public static void main(String[] args) {
        ImageConverter a = new ImageConverter("C:\\Users\\rlioy\\documents\\tiny.bmp");
        Node[][] nodes = a.to2Darray();

        for (Node[] i : nodes) {
            for (Node k : i) {
                System.out.print(" " + k);
            }
            System.out.println();
        }
    }
}
