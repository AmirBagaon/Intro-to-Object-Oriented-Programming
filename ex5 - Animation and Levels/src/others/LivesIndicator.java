package others;
import java.awt.Color;

import biuoop.DrawSurface;

/**.
 * An Indicator of the Lives-Remaning
 *
 */
public class LivesIndicator implements Sprite {
    private Counter lives;
    private int x;
    private int y;

    /**.
     * Constructor
     * @param lives the current lives
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public LivesIndicator(Counter lives, int x, int y) {
        this.lives = lives;
        this.x = x;
        this.y = y;
        }    /**.
     * Draw the sprite to the screen
     * @param surface - the surface
     */
       public void drawOn(DrawSurface surface) {
          surface.setColor(Color.black);
          surface.drawText(this.x, this.y, "Lives:" + Integer.toString(this.lives.getValue()), 17);
 }
    /**.
     * Notify the sprite that time has passed
     */
    public void timePassed() {
    }

    /**.
     * Add the lives to the game
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

}
