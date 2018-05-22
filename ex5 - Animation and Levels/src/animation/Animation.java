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
         */
       void doOneFrame(DrawSurface d);
       /**.
        * Return if the anim should stop
        * @return true or false
        */
       boolean shouldStop();
    }