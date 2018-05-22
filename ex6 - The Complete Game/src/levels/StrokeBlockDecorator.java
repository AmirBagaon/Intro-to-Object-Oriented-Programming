package levels;

import java.awt.Color;

import others.Block;

/**.
 * Decorates block with a stroke (frame)
 * @author PCP-RENT
 *
 */
public class StrokeBlockDecorator extends BlockDecorator {

    private Color color;
    /**.
     * Constructor
     * @param color frames color
     * @param decorated block
     * @throws Exception if color parser fails
     */
    public StrokeBlockDecorator(String color, BlockCreator decorated) throws Exception {
        super(decorated);
        // TODO Auto-generated constructor stub
        Color c = ColorsParser.colorFromString(color);
        this.color = c;
    }

    /**.
     * Create a block at the specified location
     * @param xpos block's x
     * @param ypos block's y
     * @return the block
     */
    public Block create(int xpos, int ypos) {
        Block b = super.create(xpos, ypos);
        b.setFrame(this.color);
        return b;
    }

}
