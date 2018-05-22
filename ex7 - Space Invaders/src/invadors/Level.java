package invadors;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import others.Block;
import others.LevelInformation;
import others.Sprite;

/**.
 * Level1 Class
 */
public class Level implements LevelInformation {

    private List<Block> bList;
    private int lvlNum;
    private int numberOfEnemy;
    private Image enemy = null;
    /**.
     * Constuctor
     * @param lvlNum the lvl num, which proivdes the lvl speed
     */
    public Level(int lvlNum) {
        this.bList = new ArrayList<Block>();
        this.lvlNum = lvlNum;
        this.numberOfEnemy = 50;

        try {
             enemy = ImageIO.read(ClassLoader.getSystemClassLoader().
             getResourceAsStream("enemy_images/enemy.png"));
         } catch (IOException e) {
             System.out.println("Can't load enemy image");
         }
     }
    /**.
     * Return enemy image
     * @return enemy image
     */
    public Image getEnemy() {
        return this.enemy;
    }

    /**.
     * The Paddle's speed
     * @return The Paddle's speed
     */
    public int paddleSpeed() {
        return 200;
    }
    /**.
     * The Paddle's width
     * @return The Paddle's width
     */
    public int paddleWidth() {
        return 50;
    }

    /**.
     * the level name will be displayed at the top of the screen.
     * @return the string
     */
    public String levelName() {
        String s = "Battle no. ";
        s += String.valueOf(this.lvlNum);
        return s;
    }

    /**.
     * Returns a sprite with the background of the level
     * @return sprite
     */
    public Sprite getBackground() {
        Image img = null;
        try {
             img = ImageIO.read(ClassLoader.getSystemClassLoader().
             getResourceAsStream("background_images/level1.png"));
             return new BackGround(img);
         } catch (IOException e) {
             System.out.println("Can't find background file");
             return null;
         }
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
     * Number of enemys that should be removed
     * before the level is considered to be "cleared".
     * @return r
     */
    public int numberOfEnemysRemove() {
        return this.numberOfEnemy;
    }
    /**.
     * decrease 1 enemy;
     */
    public void decreaseEnemy() {
        this.numberOfEnemy--;
    }
    /**.
     * Return the level (enemy's) speed
     * @return speed
     */
    public int speed() {
        return this.lvlNum;
    }

}
