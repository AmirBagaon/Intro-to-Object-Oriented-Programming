package levels;

import others.Block;

/**.
 * Decorates block with health (hit points)
 * @author PCP-RENT
 *
 */
public class HealthBlockDecorator extends BlockDecorator {

    private int health;

    /**.
     * Constructor
     * @param health h
     * @param decorated block
     */
    public HealthBlockDecorator(int health, BlockCreator decorated) {
        super(decorated);
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
        b.setHealth(this.health);
        return b;
    }

}
