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
public class Level1 implements LevelInformation {

    private List<Block> bList;
    /**.
     * Constuctor
     */
    public Level1() {
        this.bList = new ArrayList<Block>();
        Block b = new Block(390, 150, 33, 33, Color.red, 1);
        this.bList.add(b);
        }

    /**.
     * Returns The number of balls in the level
     * @return The number of balls in the level
     */
    public int numberOfBalls() {
        return 1;
    }

    /**.
     * The initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return the list
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<Velocity>();
        v.add(new Velocity(0.0, -5.0));
        return v;
    }

    /**.
     * The Paddle's speed
     * @return The Paddle's speed
     */
    public int paddleSpeed() {
        return 20;
    }
    /**.
     * The Paddle's width
     * @return The Paddle's width
     */
    public int paddleWidth() {
        return 100;
    }

    /**.
     * the level name will be displayed at the top of the screen.
     * @return the string
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**.
     * Returns a sprite with the background of the level
     * @return sprite
     */
    public Sprite getBackground() {
       Sprite s = new BG1();
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
