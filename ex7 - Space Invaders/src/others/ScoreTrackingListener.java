package others;
/**.
 * A listener to change in the score
 * Changes when block hitted, block destroyed and level finished
 */
public class ScoreTrackingListener implements HitListener {
   private Counter currentScore;

   /**.
    * Constructor
    * @param scoreCounter the counter that counts the score
    */
   public ScoreTrackingListener(Counter scoreCounter) {
      this.currentScore = scoreCounter;
   }

   /**.
    * Increase the score
    * 5 for hit
    * 10 for destroy
    * (total of 15 for hit & destroy)
    * @param beingHit the block
    * @param hitter the ball
    */
   public void hitEvent(Block beingHit, Ball hitter) {
       if (hitter.getDy() < 0) {
           this.currentScore.increase(100);
       }
   }
}