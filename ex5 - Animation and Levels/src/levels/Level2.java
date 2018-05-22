package levels;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import others.Block;
import others.LevelInformation;
import others.Sprite;
import others.Velocity;

/**.
 * Level1 Class
 */
public class Level2 implements LevelInformation {

    private static final int BOUND_SIZE = 25;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private List<Block> bList;
    /**.
     * Constuctor
     */
    public Level2() {
        this.bList = new ArrayList<Block>();

        int y = HEIGHT / 2;
        int blockWidth = (WIDTH - 2 * BOUND_SIZE) / 15;
        double blockHeight = 25;
        for (int i = 0; i < 15; i++) {
            int x = BOUND_SIZE + blockWidth * i;
            Color color = new Color((int) (Math.random() * 0x1000000));
            this.bList.add(new Block(x + 1, y, blockWidth, blockHeight, color, 1));
        }
    }
    /**.
     * Returns The number of balls in the level
     * @return The number of balls in the level
     */
    public int numberOfBalls() {
        return 10;
    }

    /**.
     * The initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return the list
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l = new ArrayList<Velocity>();
        for (int i = 1; i <= 5; i++) {
            l.add(Velocity.fromAngleAndSpeed(i * 10, 10));
            l.add(Velocity.fromAngleAndSpeed(i * -10, 10));
        }

        return l;
    }

    /**.
     * The Paddle's speed
     * @return The Paddle's speed
     */
    public int paddleSpeed() {
        return 5;
    }
    /**.
     * The Paddle's width
     * @return The Paddle's width
     */
    public int paddleWidth() {
        return 400;
    }

    /**.
     * the level name will be displayed at the top of the screen.
     * @return the string
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**.
     * Returns a sprite with the background of the level
     * @return sprite
     */
    public Sprite getBackground() {
       Sprite s = new BG2();
        return s;
    }

    /**.
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return the list
     */
    public List<Block> blocks() {
        return this.bList;
    }

    /**.
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return the num
     */
    public int numberOfBlocksToRemove() {
        return this.bList.size();
    }

}
