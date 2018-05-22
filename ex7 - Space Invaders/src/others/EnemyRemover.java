package others;

/**.
 * A BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 * @author PCP-RENT
 *
 */
public class EnemyRemover implements HitListener {
   private GameLevel game;
   private Counter remainingEnemys;

   /**.
    * Constructor
    * @param game the game
    * @param remainingEnemys the remaning Enemys
    */
   public EnemyRemover(GameLevel game, Counter remainingEnemys) {
       this.game = game;
       this.remainingEnemys = remainingEnemys;
   }

   /**.
    *   Blocks that are hit and reach 0 hit-points should be removed
    *   from the game. Remember to remove this listener from the block
    *   that is being removed from the game.
    *   @param beingHit - The block that was hit
    *   @param hitter - The ball that hit
    */
   public void hitEvent(Block beingHit, Ball hitter) {
          if (hitter.getDy() < 0) {
              beingHit.removeFromGame(game);
              this.remainingEnemys.decrease(1);
          }
          hitter.removeFromGame(this.game);
   }
}