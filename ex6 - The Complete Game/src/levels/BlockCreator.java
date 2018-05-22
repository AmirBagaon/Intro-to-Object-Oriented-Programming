package levels;

import others.Block;

/**.
 * The interface of block creator, which is the type in the block factory
 * @author PCP-RENT
 *
 */
public interface BlockCreator {
    /**.
     * Create a block at the specified location
     * @param xpos block's x
     * @param ypos block's y
     * @return the block
     */
    Block create(int xpos, int ypos);
}
