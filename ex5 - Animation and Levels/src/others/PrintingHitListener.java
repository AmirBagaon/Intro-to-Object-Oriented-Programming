package others;
/**.
 * A test class the prints the health of the hitted block
 */
public class PrintingHitListener implements HitListener {

    /**.
     * Prints the health of the hitted block when hitted
     * @param beingHit the block
     * @param hitter the ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
      System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
   }
}