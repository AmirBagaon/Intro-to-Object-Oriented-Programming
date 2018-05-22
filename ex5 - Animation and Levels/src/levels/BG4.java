package levels;
import java.awt.Color;

import biuoop.DrawSurface;
import others.Sprite;

/**.
 * The BackGround of level 1
 */
public class BG4 implements Sprite {

    private static final int INDICATORS_SIZE = 15;

    /**.
     * Draw the sprite to the screen
     * @param d - the surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.blue.brighter());
        d.fillRectangle(0, INDICATORS_SIZE, d.getWidth(), d.getHeight());

        d.setColor(Color.GRAY);
        d.fillCircle(700, 510, 31);
        d.fillCircle(720, 500, 21);

        d.fillCircle(200, 400, 31);
        d.fillCircle(180, 380, 21);

        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(690, 530, 31);
        d.fillCircle(710, 520, 21);

        d.fillCircle(180, 420, 31);
        d.fillCircle(160, 400, 21);

        d.setColor(Color.LIGHT_GRAY.darker());
        d.fillCircle(680, 505, 31);
        d.fillCircle(700, 490, 21);

        d.fillCircle(170, 415, 25);
        d.fillCircle(160, 395, 21);

        d.setColor(Color.white);
        for (int i = 0; i < 10; i++) {
            d.drawLine(150 + 10 * i, 450, 110 + 10 * i, 600);
            d.drawLine(650 + 10 * i, 550, 600 + 10 * i, 600);
        }
}

    /**.
     * Notify the sprite that time has passed
     */
    public void timePassed() {

    }
 }