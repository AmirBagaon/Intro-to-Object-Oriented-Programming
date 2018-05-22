package levels;

import java.awt.Color;
import java.awt.Image;

import biuoop.DrawSurface;
import others.Sprite;
/**.
 * The background class
 * @author PCP-RENT
 *
 */
public class BackGround implements Sprite {

    private Color color = null;
    private Image img = null;
    private boolean isColor = false;
    /**.
     * Constructor from image
     * @param img img
     */
    public BackGround(Image img) {
        this.img = img;
    }
    /**.
     * Constructor from color
     * @param c color
     */
    public BackGround(Color c) {
        this.color = c;
        this.isColor = true;
    }
    @Override
    public void drawOn(DrawSurface d) {
        if (isColor) {
            d.setColor(this.color);
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        } else {
            d.drawImage(0, 0, this.img);
        }

    }

    @Override
    public void timePassed(double dt) {

    }

}
