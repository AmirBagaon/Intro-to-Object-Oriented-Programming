package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**.
 * Runs the animation
 *
 *
 */
public class AnimationRunner {
   private GUI gui;
   private int framesPerSecond;
   private Sleeper sleeper;


   /**.
    * Constructor, includes fps for frames per seconds
    * @param gui the gui
    * @param fps frames per second
    */
   public AnimationRunner(GUI gui, int fps) {
       this.gui = gui;
       this.sleeper = new Sleeper();
       this.framesPerSecond = fps;
   }

   /**.
    * Calculates time and run the animation
    * @param animation the animation
    */
   public void run(Animation animation) {
      int millisecondsPerFrame = 1000 / this.framesPerSecond;
      while (!animation.shouldStop()) {
         long startTime = System.currentTimeMillis(); // timing
         DrawSurface d = gui.getDrawSurface();

         //CHANGEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
         //The dt
         double dt = (double) 1 / this.framesPerSecond;
         animation.doOneFrame(d, dt);

         gui.show(d);
         long usedTime = System.currentTimeMillis() - startTime;
         long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
         if (milliSecondLeftToSleep > 0) {
             this.sleeper.sleepFor(milliSecondLeftToSleep);
         }
      }
   }
}
