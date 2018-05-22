package others;
/**.
 * A BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 * @author PCP-RENT
 *
 */
public class BlockRemover implements HitListener {
   private GameLevel game;
   private Counter remainingBlocks;

   /**.
    * Constructor
    * @param game - The game
    * @param removedBlocks - The total blcoks to be removed
    */
   public BlockRemover(GameLevel game, Counter removedBlocks) {
       this.game = game;
       this.remainingBlocks = removedBlocks;
   }

   /**.
    * Blocks that are hit and reach 0 hit-points should be removed
    * from the game. Remember to remove this listener from the block
    * that is being removed from the game.
    *
    * @param beingHit - The block that got hit
    * @param hitter - The ball that hit
    */
   public void hitEvent(Block beingHit, Ball hitter) {
       if (beingHit.getHitPoints() <= 1) {
           beingHit.removeHitListener(this);
           beingHit.removeFromGame(this.game);
           this.remainingBlocks.decrease(1);
       }
   }
}