package task2;

/**
 * This class was created for the test of Circle2D class
 * @author Okan Atas, created on March 21, 2021
 * @version 1.1.0
 */
public class Tester {
    /**
     * Main method of Tester
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Circle2D c1 = new Circle2D(2, 2, 5.5);

        System.out.println("The point (3, 3) contained by the Circle -> " + c1.contains(3, 3));

        System.out.println("Another circle centered at (4, 5, 10.5) contained by the Circle -> "
                        + c1.contains(new Circle2D(4, 5, 10.5)));

        System.out.println("Another circle centered at (3, 5, 2.3) overlapped by the Circle -> "
                        + c1.overlaps(new Circle2D(3, 5, 2.3)));

        System.out.println("Another circle centered at (4 + root of 129, 2, 5.5) overlapped by the Circle -> "
                + c1.overlaps(new Circle2D(4+Math.sqrt(129),2, 5.5)));


        System.out.println("Area (2, 2, 5.5) -> " + c1.getArea());
        System.out.println("Perimeter (2, 2, 5.5) -> " + c1.getPerimeter());
    }
}
