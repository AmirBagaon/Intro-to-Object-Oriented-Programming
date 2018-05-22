package levels;

import java.util.Map;
import java.util.TreeMap;

import others.Block;

/**.
 * A class that contains information about the block types and the spacers
 * @author PCP-RENT
 *
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**.
     * Constructor
     */
    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new TreeMap<String, Integer>();
        this.blockCreators = new TreeMap<String, BlockCreator>();
    }

    /**.
     * returns true if 's' is a valid space symbol.
     * @param s string
     * @return true/false
     */
    public boolean isSpaceSymbol(String s) {
      if (this.spacerWidths.containsKey(s)) {
          return true;
      }
      return false;
    }
    /**.
     * returns true if 's' is a valid block symbol
     * @param s s
     * @return true if 's' is a valid block symbol
     */
    public boolean isBlockSymbol(String s) {
        if (this.blockCreators.containsKey(s)) {
            return true;
        }
        return false;
    }

    /**.
     * Return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     * @param s symbol
     * @param x x's
     * @param y y's
     * @return the new block
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
     }


    /**.
     *
     * @param s symbol
     * @return the width in pixels associated with the given spacer-symbol
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
     }

    /**.
     * add spacer to the list
     * @param s symbol
     * @param x width
     */
    public void addSpaceWidth(String s, Integer x) {
        this.spacerWidths.put(s, x);
    }
    /**.
     * add block
     * @param s symbol
     * @param b block
     */
    public void addBlockCreator(String s, BlockCreator b) {
        this.blockCreators.put(s, b);
    }
    /**.
     * return the map of the keys and the block that should be created
     * @return the map
     */
    public Map<String, BlockCreator> getBlockCreators() {
        return this.blockCreators;
    }
}

