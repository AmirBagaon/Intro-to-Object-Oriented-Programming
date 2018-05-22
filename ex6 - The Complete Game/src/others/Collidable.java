package others;
import geometry.Point;
import geometry.Rectangle;

/**.
 *
 * An interface of collidable info
 *
 */
public interface Collidable {
        // Return the "collision shape" of the object.
        /**.
         * Returns the collision Rectangle
         * @return the collision Rectangle
         */
        Rectangle getCollisionRectangle();

        /**.
         * Notify the object that we collided with it at collisionPoint with
         * a given velocity.
         * The return is the new velocity expected after the hit (based on
         * the force the object inflicted on us).
         * @param collisionPoint - the collision Point
         * @param currentVelocity - the current velocity
         * @param hitter - The ball that hit
         * @return new velocity
         */
        Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
     }
