package geometry;

/**
 *
 * @author Amir Bagaon
 *
 * A class that represents point
 *
 */
public class Point {
private double x;
private double y;

/**.
 * Create Point
 * @param x - x value
 * @param y - y value
 */
    public Point(double x, double y) {
    this.x = x;
    this.y = y;
    }

/**.
 * return the distance of this point to the other point
 * @param other - another point
 * @return the distance between them
 */
    public double distance(Point other) {
    return (double) Math.sqrt(((this.x - other.getX()) * (this.x - other.getX()))
    + ((this.y - other.getY()) * (this.y - other.getY())));
    }

    /**.
    * return true is the points are equal, false otherwise
    * @param other - another point
    * @return true or false
    */
    public boolean equals(Point other) {
    if (this.x != other.getX()) {
      return false;
    }
    return (this.y == other.getY());
    }

    /**.
     * Return the x value of this point
     * @return x
     */
    public double getX() {
    return this.x;
    }
    /**.
     * Return the y value of this point
     * @return y
     */
    public double getY() {
    return this.y;
    }
    /**.
     * Set the x value of this point
     * @param x1 - x value
     */
    public void setX(double x1) {
    this.x = x1;
    }
    /**.
     * Set the y value of this point
     *  @param y1 - y value
     */
    public void setY(double y1) {
    this.y = y1;
    }
}