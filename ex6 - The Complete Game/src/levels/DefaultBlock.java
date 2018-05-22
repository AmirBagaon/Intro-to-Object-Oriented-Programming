package levels;

import others.Block;

/**.
 * Creates the block that the decorators will decorate it.
 */
public class DefaultBlock implements BlockCreator {

    /**.
     * Create default block
     * @param xpos block's x
     * @param ypos block's y
     * @return the block
     */
    public Block create(int xpos, int ypos) {
        Block b = new Block(xpos, ypos);
        return b;
    }

}
