package others;
import java.awt.Color;

import biuoop.DrawSurface;

/**.
 * Indicate the score and shows it on the screen
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private int x;
    private int y;

    /**.
     * Constructor
     * @param score the current score
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public ScoreIndicator(Counter score, int x, int y) {
        this.score = score;
        this.x = x;
        this.y = y;
        }
    /**.
     * Draw the sprite to the screen
     * @param surface - the surface
     */
       public void drawOn(DrawSurface surface) {
           surface.setColor(Color.black);
           surface.drawText(x, y, "Score:" + Integer.toString(this.score.getValue()), 17);
     }
    /**.
     * Notify the sprite that time has passed
     */
    public void timePassed() {
    }

    /**.
     * Add the score to the game
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

}
