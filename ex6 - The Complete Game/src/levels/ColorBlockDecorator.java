package levels;

import java.awt.Color;

import others.Block;

/**.
 * Decorates block with color
 *
 *
 */
public class ColorBlockDecorator extends BlockDecorator {

    private Color color;
    private int health;

    /**.
     * Constructor
     * @param decorated the block
     * @param color c
     * @param health hit points
     */
    public ColorBlockDecorator(BlockCreator decorated, Color color, int health) {
        super(decorated);
        // TODO Auto-generated constructor stub
        this.color = color;
        this.health = health;
    }

    /**.
     * Create a block at the specified location
     * @param xpos block's x
     * @param ypos block's y
     * @return the block
     */
    public Block create(int xpos, int ypos) {
        Block b = super.create(xpos, ypos);
        b.setColor(this.health, this.color);
        return b;
    }

}
