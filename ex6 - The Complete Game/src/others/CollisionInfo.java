package others;
import geometry.Point;

/**.
 *
 * A class about Collision Info, that contains
 * the collision point and the collidable object
 *
 */
public class CollisionInfo {

    private Point point;
    private Collidable collidable;

    /**.
     * Create CollisionInfo
     *
     * @param point the point at which the collision occurs.
     * @param c the collidable object involved in the collision.
     */
      public CollisionInfo(Point point, Collidable c) {
        this.point = point;
        this.collidable = c;
        }

      /**.
       * Returns the point at which the collision occurs
       * @return the collision point
       */
      public Point collisionPoint() {
           return this.point;
       }

      /**
       * Returns the collidable object involved in the collision.
       * @return the collidable object
       */
      public Collidable collisionObject() {
           return this.collidable;
       }
    }