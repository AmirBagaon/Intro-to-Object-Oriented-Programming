package others;
import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**.
 * Shows an EndScreen in the end of the game
 * Shows up until 'space' is pressed
 */
public class EndScreen implements Animation {
   private KeyboardSensor keyboard;
   private boolean stop;
   private boolean isWin;
   private int score;

   /**.
    * Constructor
    * @param k the keyboard
    * @param win - If the player win or lose
    * @param score - The score
    */
   public EndScreen(KeyboardSensor k, boolean win, int score) {
      this.keyboard = k;
      this.stop = false;
      this.isWin = win;
      this.score = score;
   }
   /**.
    * Does the animation
    * @param d the surface
    */
   public void doOneFrame(DrawSurface d) {
      if (isWin) {
          d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + score, 30);
      } else {
          d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + score, 30);
      }
      if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
          this.stop = true;
      }
   }

   /**.
    * Returns if the anim should stop
    * @return true or false
    */
   public boolean shouldStop() {
       return this.stop;
   }
}
