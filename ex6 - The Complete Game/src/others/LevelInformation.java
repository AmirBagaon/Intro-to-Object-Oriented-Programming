package others;
import java.util.List;

/**.
 * Contains Information about the level
 *
 */
public interface LevelInformation {
    /**.
     * Returns The number of balls in the level
     * @return The number of balls in the level
     */
   int numberOfBalls();
   /**.
    * The initial velocity of each ball
    * Note that initialBallVelocities().size() == numberOfBalls()
    * @return the list
    */
   List<Velocity> initialBallVelocities();
   /**.
    * The Paddle's speed
    * @return The Paddle's speed
    */
   int paddleSpeed();
   /**.
    * The Paddle's width
    * @return The Paddle's width
    */

   int paddleWidth();

   /**.
    * the level name will be displayed at the top of the screen.
    * @return the string
    */
   String levelName();
   /**.
    * Returns a sprite with the background of the level
    * @return sprite
    */
   Sprite getBackground();

   /**.
    * The Blocks that make up this level, each block contains
    * its size, color and location.
    * @return the list
    */
   List<Block> blocks();
   /**.
    * Number of blocks that should be removed
    * before the level is considered to be "cleared".
    * This number should be <= blocks.size();
    * @return the num
    */
   int numberOfBlocksToRemove();
}