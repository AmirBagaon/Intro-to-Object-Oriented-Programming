package invadors;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import others.Block;
import others.GameLevel;
import others.HitListener;


/**.
 * A class of shield, which is a group of blocks which has ball&block remover listener
 * @author PCP-RENT
 *
 */
public class Shield {

    private static final int ROW = 3;
    private static final int COL = 20;
    private static final int SIZE = 7;


    private int x;
    private int y;
    private List<Block> blocks;
    /**.
     * Constructor
     * @param x x
     * @param y y
     */
    public Shield(int x, int y) {
        this.x = x;
        this.y = y;
        this.blocks = new ArrayList<Block>();
    }

    /**.
     * Creates the Shield
     */
    public void create() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                Block b = new Block(this.x + j * SIZE, this.y + i * SIZE,
                        SIZE, SIZE, Color.blue.brighter(), Color.blue.brighter());
                this.blocks.add(b);
            }
        }
    }
    /**.
     * Add the shield (it's blocks) to the game.
     * Add a listener which remove the ball and the block if theres a hit
     * @param game the game
     * @param l the listener
     */
    public void addToGame(GameLevel game, HitListener l) {
        for (Block b : this.blocks) {
            b.addHitListener(l);
            b.addToGame(game);
        }
    }

}
