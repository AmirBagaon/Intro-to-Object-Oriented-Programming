package levels;

import java.awt.Image;

import others.Block;

/**.
 * Decorates block with Image
 *
 *
 */
public class ImageBlockDecorator extends BlockDecorator {

    private Image img;
    private int health;
    /**.
     * Contsructor
     * @param image the image
     * @param health the appropriate health for this image
     * @param decorated block
     * @throws Exception if image's load fail
     */
    public ImageBlockDecorator(Image image, int health, BlockCreator decorated) throws Exception {
        super(decorated);
        this.img = image;
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
        b.setImage(this.health, this.img);
        return b;
    }

}
