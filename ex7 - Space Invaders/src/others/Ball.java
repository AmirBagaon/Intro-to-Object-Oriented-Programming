package others;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import java.awt.Color;

/**
 *
 * @author Amir Bagaon
 *
 * A class represents a ball
 */
public class Ball implements Sprite {

    private static final int DEFAULT = 15;

    private GameEnvironment gameEnv;
    private Velocity velocity;
    private double radius;
    private Point point;
    private Color color;

    /**.
     * Create Ball
     *
     * @param point middle point of the circle
     * @param r radius
     * @param color color of the circle
     * @param gameEnv - The game Environment
     */
      public Ball(Point point, double r, Color color, GameEnvironment gameEnv) {
        this.radius = r;
        this.point = point;
        this.color = color;
        this.velocity = new Velocity(DEFAULT, DEFAULT);
        this.gameEnv = gameEnv;
      }

      /**.
      * Create Ball from coordinate
      *
      * @param x Center x's
      * @param y Center y's
      * @param r radius
      * @param color Circle's color
      * @param gameEnv - The game Environment
      */
      public Ball(double x, double y, double r, Color color, GameEnvironment gameEnv) {
            Point p = new Point(x, y);
            this.point = p;
            this.color = color;
            this.radius = r;
            this.velocity = new Velocity(DEFAULT, DEFAULT);
            this.gameEnv = gameEnv;
      }

      /**.
      * Return x value
      * @return the x of the middle point of the circle
      */
      public int getX() {
      return (int) Math.round(this.point.getX());
      }
      /**.
      * Return y value
      * @return the y of the middle point of the circle
      */

      public int getY() {
          return (int) Math.round(this.point.getY());
      }
      /**.
      * Return radius size
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
       * Return the current postion of the ball (by returning it's center);
       * @return the center point
       */
      public Point getCenter() {
         return this.point;
      }

    /**.
    * Draw the ball on the given DrawSurface
    * @param surface - the surface
    */
      public void drawOn(DrawSurface surface) {
          surface.setColor(color);
          surface.fillCircle((int) point.getX(), (int) point.getY(), (int) radius);
          surface.setColor(Color.black);
          surface.drawCircle((int) point.getX(), (int) point.getY(), (int) radius);
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
      * Move the ball one step forward
      * Check if the ball will hit some object. If it does, the velocity will be changed,
      * and the ball will stick to the line
      *  @param dt the dt value
      */
      public void moveOneStep(double dt) {
          Velocity vByDt = this.velocity.setByDt(dt);
          Point p = vByDt.applyToPoint(this.point);
          Point start = new Point((int) this.point.getX(), (int) this.point.getY());
          Point end = new Point((int) p.getX(), (int) p.getY());
          Line trajectory = new Line(start, end);
          CollisionInfo info = this.gameEnv.getClosestCollision(trajectory);
          if (info != null) {
              Velocity newV = info.collisionObject().hit(this, info.collisionPoint(), this.velocity);
                  this.setVelocity(newV);
          }
          vByDt = this.velocity.setByDt(dt);
          this.point = vByDt.applyToPoint(this.point);
      }

/*
      /**
       * Calculate the ball's trajectory
       *
       * The trajectory is being calculated as a line from the ball's current point
       * to it's current point with it's velocity as addition.
       *
       *
       * @return the line that represent the ball's trajectory

      public Line ballTrajectory() {
          double x = this.point.getX();
          double y = this.point.getY();
          Point start = new Point((int) x, (int) y);
          Point end = new Point((int) (x + this.velocity.getDx()),
                  (int) (y + this.velocity.getDy()));
          Line l = new Line(start, end);
          return l;
      }
*/
      /**.
       * Update the movement of the ball
       *  @param dt the dt value
       */
      public void timePassed(double dt) {
          this.moveOneStep(dt);
          this.gameEnv.penetration(this);
      }
      /**.
       * Add the ball to the game
       * @param game the game
       */
      public void addToGame(GameLevel game) {
          game.addSprite(this);
      }
      /**.
       * Remove the ball from the game
       * @param g the game
       */
      public void removeFromGame(GameLevel g) {
          g.removeSprite(this);
      }
      /**.
       * Return the dy of the velocity
       * @return dy
       */
      public double getDy() {
          return this.velocity.getDy();
      }
}