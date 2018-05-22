package animation;
import java.awt.Color;

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
    * Starts the pause screen
    * finished when space is pressed
    * @param d the surface
    * @param dt the dt
    */
   public void doOneFrame(DrawSurface d, double dt) {
      d.setColor(Color.BLACK);
      d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
   }
   /**.
    * Return if the method should stop
    * @return true or false
    */
   public boolean shouldStop() {
       return false;
       }
}
