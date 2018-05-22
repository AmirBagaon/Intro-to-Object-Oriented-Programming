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
public class Level4 implements LevelInformation {

    private static final int BOUND_SIZE = 25;
    private static final int WIDTH = 800;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 28;

    private List<Block> bList;
    /**.
     * Constuctor
     */
    public Level4() {
        this.bList = new ArrayList<Block>();

      //Create Blocks and add them to game
        int x = WIDTH - BOUND_SIZE - BLOCK_WIDTH;
        int health = 2;
        for (int i = 0; i < 7; i++) {
            Color color = new Color((int) (Math.random() * 0x1000000));
            Block[] row = new Block[15];
            int y = 100 + i * BLOCK_HEIGHT;
            for (int j = 0; j < 15; j++) {
                row[j] = new Block(x - j * BLOCK_WIDTH, y,
                        BLOCK_WIDTH, BLOCK_HEIGHT, color, health);
                this.bList.add(row[j]);
            }
            health = 1;
        }
    }
    /**.
     * Returns The number of balls in the level
     * @return The number of balls in the level
     */
    public int numberOfBalls() {
        return 3;
    }

    /**.
     * The initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return the list
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l = new ArrayList<Velocity>();
            l.add(Velocity.fromAngleAndSpeed(30, 8));
            l.add(Velocity.fromAngleAndSpeed(-30, 8));
            l.add(Velocity.fromAngleAndSpeed(0, 8));
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
        return 150;
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
       Sprite s = new BG4();
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
