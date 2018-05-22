package others;
/**.
 * A BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 * @author PCP-RENT
 *
 */
public class BlockRemover implements HitListener {
   private GameLevel game;

   /**.
    * Constructor
    * @param g the game
    */
   public BlockRemover(GameLevel g) {
       this.game = g;
   }

   /**.
    *   Blocks that are hit and reach 0 hit-points should be removed
    *   from the game. Remember to remove this listener from the block
    *   that is being removed from the game.
    *   @param beingHit - The block that was hit
    *   @param hitter - The ball that hit
    */
   public void hitEvent(Block beingHit, Ball hitter) {
          beingHit.removeFromGame(game);
   }
}