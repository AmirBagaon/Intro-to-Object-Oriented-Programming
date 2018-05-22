package animation;
import java.awt.Color;

import biuoop.DrawSurface;
import others.SpriteCollection;

/**.
 *   The CountdownAnimation will display the given gameScreen,
 *   for numOfSeconds seconds, and on top of them it will show
 *   a countdown from countFrom back to 1, where each number will
 *   appear on the screen for (numOfSeconds / countFrom) secods, before
 *   it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private long createdMillis = System.currentTimeMillis();
    private double numOfSeconds;
    private double countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private long timePerNum;

    /**.
     * Constructor
     * Contains count from (kind of number), and Time of seconds to do the count
     * @param numOfSeconds - Time of seconds to do the count
     * @param countFrom - The numbers to count
     * @param gameScreen - The sprites
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.stop = false;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds * 1000;
        this.timePerNum = (long) this.numOfSeconds / countFrom;
    }
   /**.
    * Moves the animation one frame next
    * Does the most of the animation part
    *
    * @param d - The surface
    * @param dt - the dt
    */
   public void doOneFrame(DrawSurface d, double dt) {
       this.gameScreen.drawAllOn(d);
       d.setColor(Color.red);

          if (this.countFrom > 0) {
              d.drawText(400, 400, Double.toString(this.countFrom), 50);
           } else {
               this.stop = true;
           }
          long nowMillis = System.currentTimeMillis();
           long passedTime = nowMillis - this.createdMillis;
           if (passedTime > this.timePerNum) {
               passedTime = 0;
               this.createdMillis = nowMillis;
               this.countFrom--;
           }
   }

   /**.
    * Returns if the animation should stop
    * @return true or false
    */
   public boolean shouldStop() {
       return this.stop;
   }
}