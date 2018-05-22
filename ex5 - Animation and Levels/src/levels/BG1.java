package levels;
import java.awt.Color;

import biuoop.DrawSurface;
import others.Sprite;

/**.
 * The BackGround of level 1
 */
public class BG1 implements Sprite {

    private static final int INDICATORS_SIZE = 15;

    /**.
     * Draw the sprite to the screen
     * @param d - the surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, INDICATORS_SIZE, d.getWidth(), d.getHeight());

        d.setColor(Color.blue);
        d.drawCircle(410, 150, 50);
        d.drawCircle(410, 150, 80);
        d.drawCircle(410, 150, 110);
        d.drawLine(410, 145, 410, 25);
        d.drawLine(410, 184, 410, 302);
        d.drawLine(380, 165, 262, 165);
        d.drawLine(420, 165, 542, 165);
    }
    /**.
     * Notify the sprite that time has passed
     */
    public void timePassed() {

    }
 }