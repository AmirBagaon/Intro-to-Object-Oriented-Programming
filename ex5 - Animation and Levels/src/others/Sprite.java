package others;


import biuoop.DrawSurface;
/**.
 * An Interface of sprite
 *
 */
public interface Sprite {
       /**.
        * Draw the sprite to the screen
        * @param d - the surface
        */
       void drawOn(DrawSurface d);
       /**.
        * Notify the sprite that time has passed
        */
       void timePassed();
    }