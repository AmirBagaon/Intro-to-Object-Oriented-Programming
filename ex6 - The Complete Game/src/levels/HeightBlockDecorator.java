package levels;

import others.Block;

/**.
 * Decorates block with height
 *
 *
 */
public class HeightBlockDecorator extends BlockDecorator {

    private int height;

    /**.
     * Constructor
     * @param height h
     * @param decorated block
     */
    public HeightBlockDecorator(int height, BlockCreator decorated) {
        super(decorated);
        // TODO Auto-generated constructor stub
        this.height = height;
    }

    /**.
     * Create a block at the specified location
     * @param xpos block's x
     * @param ypos block's y
     * @return the block
     */
    public Block create(int xpos, int ypos) {
        Block b = super.create(xpos, ypos);
        b.setHeight(this.height);
        return b;
    }

}
