
/**
 *
 * @author Amir Bagaon
 *
 * A class of Velocity
 */
public class Velocity {

    private double dx;
    private double dy;
/**.
 * Create velocity
 * @param dx - x change
 * @param dy - y change
 */
public Velocity(double dx, double dy) {
    this.dx = dx;
    this.dy = dy;
}

/**.
 * Take a point with position (x,y) and return a new point
 * with position (x+dx, y+dy)
 *
 * @param p the point
 * @return the new point
 */
public Point applyToPoint(Point p) {
    double x = p.getX();
    double y = p.getY();
    Point p2 =  new Point(x + dx, y + dy);
    return p2;
}
/**.
 * Return dx
 * @return dx
 */
public double getDx() {
    return this.dx;
}
/**.
 * Return dy
 * @return dy
 */
public double getDy() {
    return this.dy;
}

/**.
 * Set Velocity by angle and speed
 *
 * @param angle - change direction of the ball
 * @param speed - change speed of the ball
 * @return velocity
 */
public static Velocity fromAngleAndSpeed(double angle, double speed) {

    double newAngle  =  Math.toRadians(angle);

    double dx  =  (Math.sin(newAngle)) * speed;
    double dy  =  -(Math.cos(newAngle)) * speed;
    return new Velocity(dx, dy);
 }


/**.
 * Returns the current speed
 * @return the speed
 */
public double getSpeed() {
    return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
}
}