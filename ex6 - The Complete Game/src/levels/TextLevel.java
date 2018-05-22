package levels;

import java.util.ArrayList;
import java.util.List;

import others.Block;
import others.LevelInformation;
import others.Sprite;
import others.Velocity;

/**.
 * A class that contains level about information, that comes from an outer text
 * @author PCP-RENT
 *
 */
public class TextLevel implements LevelInformation {

    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**.
     * Constructor
     */
    public TextLevel() {
        this.initialBallVelocities = new ArrayList<Velocity>();
        this.blocks = new ArrayList<Block>();
    }
    /**.
     * Return number of balls
     * @return num
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    /**.
     * set number of balls
     * @param num the num
     */
    public void setNnumberOfBalls(int num) {
        this.numberOfBalls = num;
    }

    /**.
     * Return list with velocities of balls
     * @return list
     */
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }
    /**.
     * add velocity of a ball
     * @param v the velocity
     */
    public void addInitialBallVelocities(Velocity v) {
        this.initialBallVelocities.add(v);
    }

    /**.
     * Return paddle speed
     * @return speed
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }
    /**.
     * Set the paddle's speed
     * @param speed s
     */
    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }
    /**.
     * Return paddle width
     * @return width
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }
    /**.
     * Set the paddle's width
     * @param width w
     */
    public void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }

    /**.
     * Return level name
     * @return name name
     */
    public String levelName() {
        return this.levelName;
    }
    /**.
     * Set the level's name
     * @param name name
     */
    public void setLevelName(String name) {
        this.levelName = name;
    }
    /**.
     * Return level's background
     * @return background background
     */
    public Sprite getBackground() {
        return this.background;
    }
    /**.
     * Set the level's background
     * @param s sprite
     */
    public void setBackground(Sprite s) {
        this.background = s;
    }

    /**.
     * Return level's list of blocks
     * @return list blocks
     */

    public List<Block> blocks() {
        return this.blocks;
    }
    /**.
     * add block to the level's list block
     * @param l the block
     */
    public void addBlock(Block l) {
        this.blocks.add(l);
    }
    /**.
     * return the number of blocks to remove
     * @return the num
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
    /**.
     * Set the number of blocks to remove
     * @param num the num
     */
    public void setNumberOfBlocksToRemove(int num) {
        this.numberOfBlocksToRemove = num;
    }
}
