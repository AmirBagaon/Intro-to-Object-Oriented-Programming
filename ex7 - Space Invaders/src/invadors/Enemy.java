package invadors;

import java.awt.Image;
import others.Block;

/**.
 * A class of enemy, which is a image-block object
 * @author PCP-RENT
 *
 */
public class Enemy {

    private static final int WIDTH = 40;
    private static final int HEIGHT = 30;
    private static final int SHIELD_Y = 500;


    private Image img;
    private int x;
    private int y;
    private Block theEnemy;
    /**.
     * Constructor
     * @param img the image of the enemy
     * @param x position
     * @param y position
     */
    public Enemy(int x, int y, Image img) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.theEnemy = new Block(x, y, WIDTH, HEIGHT, img);
    }

    /**.
     * Return the enemy block
     * @return the block
     */
    public Block enemyBlock() {
        return this.theEnemy;
    }
    /**.
     * Move the enemy by x
     * @param range x
     */
    public void move(double range) {
        this.theEnemy.move(range, 0);
        double currentx = this.theEnemy.getCollisionRectangle().getUpperLeft().getX();
    }

    /**.
     * move the enemy by y
     * @param range y
     * @return if the enemy's y is locate on the shields y
     */
    public boolean lineDown(double range) {
        //this.y += y;
        this.theEnemy.move(0, range);
        if (this.theEnemy.getCollisionRectangle().getUpperLeft().getY() + HEIGHT >= SHIELD_Y) {
            return true;
        }
        return false;
    }
    /**.
     * Move the enemy to it's original position
     */
    public void resetPosition() {
        this.theEnemy.moveTo(this.x, this.y);
    }
    /**.
     * Return current x
     *
     * @return x
     */
    public double currentX() {
        return this.theEnemy.getCollisionRectangle().getUpperLeft().getX();
    }
}
