package invadors;
import java.awt.Image;

import biuoop.DrawSurface;
import others.Sprite;

/**.
 * The BackGround of level 1
 */
public class BackGround implements Sprite {


    private Image img;
    /**.
     * Constructor from image
     * @param img image
     */
    public BackGround(Image img) {
        this.img = img;
    }

    /**.
     * Draw the sprite to the screen
     * @param d - the surface
     */
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, img);
    }
    /**.
     * Notify the sprite that time has passed
     * @param dt the dt
     */
      public void timePassed(double dt) {
        // TODO Auto-generated method stub

    }
 }