package others;
import java.awt.Image;
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
   int numberOfEnemysRemove();

   /**.
    * Return enemy Image
    * @return img
    */
   Image getEnemy();
}