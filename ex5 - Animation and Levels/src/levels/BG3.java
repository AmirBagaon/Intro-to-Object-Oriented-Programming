package levels;
import java.awt.Color;

import biuoop.DrawSurface;
import others.Sprite;

/**.
 * The BackGround of level 1
 */
public class BG3 implements Sprite {

    private static final int INDICATORS_SIZE = 15;

    /**.
     * Draw the sprite to the screen
     * @param d - the surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.decode("#3e7c00"));
        d.fillRectangle(0, INDICATORS_SIZE, d.getWidth(), d.getHeight());

        d.setColor(Color.darkGray);
        d.fillRectangle(120, 220, 15, 200);
        d.fillRectangle(100, 420, 60, 50);
        d.fillRectangle(70, 470, 120, 300);
        d.setColor(Color.orange);
        d.fillCircle(125, 210, 10);
        d.setColor(Color.red);
        d.fillCircle(125, 210, 6);
        d.setColor(Color.WHITE);
        d.fillCircle(125, 210, 3);

        d.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(85 + j * 20, 480 + i * 30, 10, 20);
            }
        }
}

    /**.
     * Notify the sprite that time has passed
     */
    public void timePassed() {

    }
 }