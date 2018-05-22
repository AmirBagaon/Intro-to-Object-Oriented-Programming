package others;
import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * Pause Screen
 * Player press P and the game stops until space is pressed.
 */
public class PauseScreen implements Animation {
   private KeyboardSensor keyboard;
   private boolean stop;

   /**.
    * Constructor
    * @param k keyboard
    */
   public PauseScreen(KeyboardSensor k) {
      this.keyboard = k;
      this.stop = false;
   }
   /**.
    * Starts the pause screen
    * finished when space is pressed
    * @param d the surface
    */
   public void doOneFrame(DrawSurface d) {
      d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
      if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
          this.stop = true;
          }
   }
   /**.
    * Return if the method should stop
    * @return true or false
    */
   public boolean shouldStop() {
       return this.stop;
       }
}
