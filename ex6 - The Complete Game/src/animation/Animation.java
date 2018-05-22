package animation;
import biuoop.DrawSurface;
/**.
 * Animation interface
 *
 */
    public interface Animation {
        /**.
         * Move the object one frame on the surface
         * @param d the surface
         * @param dt the dt of the game
         */
       void doOneFrame(DrawSurface d, double dt);
       /**.
        * Return if the anim should stop
        * @return true or false
        */
       boolean shouldStop();
    }