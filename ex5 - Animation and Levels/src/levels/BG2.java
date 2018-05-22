package levels;
import java.awt.Color;

import biuoop.DrawSurface;
import others.Sprite;

/**.
 * The BackGround of level 1
 */
public class BG2 implements Sprite {

    private static final int INDICATORS_SIZE = 15;

    /**.
     * Draw the sprite to the screen
     * @param d - the surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, INDICATORS_SIZE, d.getWidth(), d.getHeight());

        d.setColor(Color.yellow);
        for (int i = 0; i < 125; i++) {
            d.drawLine(155, 155, 7 * i, 310);
        }
        d.fillCircle(155, 155, 50);
        d.setColor(Color.orange);
        d.fillCircle(155, 155, 40);


    }
    /**.
     * Notify the sprite that time has passed
     */
    public void timePassed() {

    }
 }