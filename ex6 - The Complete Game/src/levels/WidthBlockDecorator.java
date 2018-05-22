package levels;

import others.Block;

/**.
 * Decorates block with width
 *
 */
public class WidthBlockDecorator extends BlockDecorator {

    private int width;
    /**.
     * Constructor
     * @param width w
     * @param decorated the block
     */
    public WidthBlockDecorator(int width, BlockCreator decorated) {
        super(decorated);
        this.width = width;
    }

    /**.
     * Create a block at the specified location
     * @param xpos block's x
     * @param ypos block's y
     * @return the block
     */
    public Block create(int xpos, int ypos) {
        Block b = super.create(xpos, ypos);
        b.setWidth(this.width);
        return b;
    }

}
