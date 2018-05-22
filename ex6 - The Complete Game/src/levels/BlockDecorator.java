package levels;

import others.Block;

/**.
 * The basic decorator of the block
 * @author PCP-RENT
 *
 */
public abstract class BlockDecorator implements BlockCreator {

    // The object that is being decorated
    private BlockCreator decorated;

    /**
     * The Constructor. It wraps any object of type BlockCreator.
     *
     * @param decorated the object to decorate
     */
    protected BlockDecorator(BlockCreator decorated) {
        this.decorated = decorated;
    }

    /**.
     * Create a block at the specified location
     * @param xpos block's x
     * @param ypos block's y
     * @return the block
     */
    public Block create(int xpos, int ypos) {
        Block b = this.decorated.create(xpos, ypos);
        return b;
    }

}
