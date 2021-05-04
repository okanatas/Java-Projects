package task2;

/**
 * This class was created for 2D Circle Exercise
 * @author Okan Atas, created on March 21, 2021
 * @version 1.1.0
 */
public class Circle2D {
   // data fields
    private final double x;
    private final double y;
    private final double radius;

    /**
     * No argument constructor
     */
    Circle2D() {
        this(0, 0, 1);
    }

    /**
     * Circle constructor with arguments of coordinates and radius
     * @param x x coordinate
     * @param y y coordinate
     * @param radius radius
     */
    Circle2D(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    /**
     * Getter method for x coordinate
     * @return x coordinate
     */
    public double getXCoordinate() {
        return x;
    }

    /**
     * Getter method for y coordinate
     * @return y coordinate
     */
    public double getYCoordinate() {
        return y;
    }

    /**
     * Getter method for radius
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * This method for area calculation of this circle
     * @return this circle area
     */
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    /**
     * This method for perimeter calculation of this circle
     * @return this circle perimeter
     */
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    /**
     * This method for to check if specified point (with coordinates) within the circle
     * @param x x coordinate
     * @param y y coordinate
     * @return boolean depends on point within the circle
     */
    public boolean contains(double x, double y) {
        // the distance from point to circle : from formula

        System.out.println(Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2)));

        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2)) <= radius;
    }

    /**
     * This method for to check if another circle (with coordinates) within the circle,
     * if this circle contains that specified circle
     * @param circle another specified circle
     * @return boolean depends on another circle within the circle (contains)
     */
    public boolean contains(Circle2D circle) {
        // get circle's x,y coordinate
        double x2 = circle.getXCoordinate(), y2 = circle.getYCoordinate();

        // the distance from this x,y to c's x,y : from formula
        double distance = Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2));

        System.out.println(distance);
        System.out.println(radius - circle.getRadius());

        return distance <= radius - circle.getRadius();
    }

    /**
     * This method for to check if another circle (with coordinates) overlapped by this circle
     * @param circle another specified circle
     * @return boolean depends on another circle overlapped by this circle
     */
    public boolean overlaps(Circle2D circle) {
        // get circle's x,y coordinate
        double x2 = circle.getXCoordinate(), y2 = circle.getYCoordinate();

        // the distance from this x,y to c's x,y : from formula
        double distance = Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2));

        System.out.println(distance);
        System.out.println(radius + circle.getRadius());

        return distance <= radius + circle.getRadius();
    }
}