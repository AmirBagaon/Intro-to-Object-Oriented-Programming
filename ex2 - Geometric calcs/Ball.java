import biuoop.DrawSurface;

import java.awt.Color;

/**
 *
 * @author Amir Bagaon
 *
 * A class represents a ball
 */
public class Ball {

    private static final int DEFAULT = 15;

    private double radius;
    private Point point;
    private Color color;
    private Velocity velocity;
    private boolean bounds = false;
    private int rBound = -1; //the right bound of the ball
    private int dBound = -1; //the bottom bound of the ball
    private int lBound  = 0; //the left bound of the ball
    private int uBound  = 0; //the upper bound of the ball

    /**.
     * Create Ball
     *
     * @param point middle point of the circle
     * @param r radius
     * @param color color of the circle
     */
      public Ball(Point point, double r, Color color) {
        this.radius = r;
        this.point = point;
        this.color = color;
        this.velocity = new Velocity(DEFAULT, DEFAULT);
      }

      /**.
      * Create Ball
      *
      * @param x Center x's
      * @param y Center y's
      * @param r radius
      * @param color Circle's color
      */
      public Ball(double x, double y, double r, Color color) {
            Point p = new Point(x, y);
            this.point = p;
            this.color = color;
            this.radius = r;
            this.velocity = new Velocity(DEFAULT, DEFAULT);
      }
    /**.
    * Create Ball
    * @param x Center x's
    * @param y Center y's
    * @param r radius
    * @param color Circle's color
    * @param rBound right Bound
    * @param dBound down Bound
    */
      public Ball(double x, double y, double r, Color color, int rBound, int dBound) {
            Point p = new Point(x, y);
            this.point = p;
            this.color = color;
            this.radius = r;
            this.bounds = true;
            this.rBound = rBound;
            this.dBound = dBound;
            this.velocity = new Velocity(DEFAULT, DEFAULT);
      }

      /**.
      * Return x value
      * @return the X of the middle point of the circle
      */
      public int getX() {
      return (int) this.point.getX();
      }
      /**.
      * Return y value
      * @return the X of the middle point of the circle
      */

      public int getY() {
          return (int) this.point.getY();
      }
      /**.
      * REturn radius size
      * @return size
      */
      public double getSize() {
          return this.radius;
      }
      /**.
      * Return ball's color
      * @return color
      */
      public java.awt.Color getColor() {
          return this.color;
      }

    /**.
    * Draw the ball on the given DrawSurface
    * @param surface - the surface
    */
      public void drawOn(DrawSurface surface) {
          surface.setColor(color);
          surface.fillCircle((int) point.getX(), (int) point.getY(), (int) radius);
    }
      /**.
      * Set the velocity by type of Velocity
      * @param v - the velocity
      */
      public void setVelocity(Velocity v) {
          this.velocity = v;
      }
      /**.
      * Set the velocity by dx and dy
      * @param dx - x change
      * @param dy - y change
      */
      public void setVelocity(double dx, double dy) {
          Velocity v  = new Velocity(dx, dy);
          this.velocity = v;
      }
      /**.
      * return ball's velocity
      * @return velocity
      */
      public Velocity getVelocity() {
          return this.velocity;
      }
    /**.
    * Set the bounds of the ball
    * @param up - upper bound
    * @param down - bottom bound
    * @param left - left  bound
    * @param right - right bound
    */
      public void setBounds(int up, int down, int left, int right) {
          this.bounds = true;
          this.uBound = up;
          this.dBound = down;
          this.rBound = right;
          this.lBound = left;
      }
      /**.
      * Move the ball one step forward
      */
      public void moveOneStep() {

          if (this.bounds) {
            if (((this.point.getY() - (double) this.radius) < this.uBound)
                || ((this.point.getY() + (double) this.radius) > this.dBound)) {
                setVelocity(this.velocity.getDx(), -this.velocity.getDy());
            }
            if (((this.point.getX() - (double) this.radius) < this.lBound)
                    || ((this.point.getX() + (double) this.radius) > this.rBound)) {
                 setVelocity(-this.velocity.getDx(), this.velocity.getDy());
          }
          }
            this.point = this.getVelocity().applyToPoint(this.point);
      }
    /**.
    *  Returns right bound
    * @return rBound
    */
      public int rBound() {
          return this.rBound;
      }
      /**.
       * Returns down (bottom) bound
       * @return dBound
       */
      public int dBound() {
          return this.dBound;
      }
}