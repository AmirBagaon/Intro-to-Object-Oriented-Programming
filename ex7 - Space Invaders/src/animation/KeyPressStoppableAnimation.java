package animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**.
 * A class for decorate all stop keys, and stop the animation
 * @author PCP-RENT
 *
 */
public class KeyPressStoppableAnimation implements Animation {
   private KeyboardSensor keyboard;
   private boolean stop;
   private String endKey;
   private boolean isAlreadyPressed;
   private Animation anim;

   /**.
    *Constuctor
    * @param sensor keyboard
    * @param animation animation
    * @param key the keyboard key
    */
   public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
       this.keyboard = sensor;
       this.anim = animation;
       this.endKey = key;
       this.isAlreadyPressed = true;
       this.stop = false;
   }

   /**.
    * Starts the pause screen
    * finished when space is pressed
    * @param d the surface
    * @param dt the dt
    */
   public void doOneFrame(DrawSurface d, double dt) {
       this.anim.doOneFrame(d, dt);
       if (this.keyboard.isPressed(endKey)) {
           if (!this.isAlreadyPressed) {
               this.stop = true;
           }
       }
       if (!this.keyboard.isPressed(this.endKey)) {
           this.isAlreadyPressed = false;
       }
   }

   /**.
    * Return if the method should stop
    * @return true or false
    */
   public boolean shouldStop() {
       if (this.stop) {
           this.stop = false;
           return true;
       }
       return this.stop;
       }
}