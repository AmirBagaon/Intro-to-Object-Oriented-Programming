package others;
import java.util.List;

import animation.AnimationRunner;
import biuoop.KeyboardSensor;
/**.
 * This class will be in charge of creating the differnet levels,
 * and moving from one level to the next.
 *
 */
public class GameFlow {

   private KeyboardSensor keyboardSensor;
   private AnimationRunner animationRunner;
   private Counter remaningLives;
   private Counter score;
   private int gWidth;
   private int gHeight;

   /**.
    * Constructor
    * @param ar - The Animation Runner
    * @param ks - The keboard
    * @param lives - Lives remaning
    * @param gWidth - game Width
    * @param gHeight - game Height
    */
   public GameFlow(AnimationRunner ar, KeyboardSensor ks, int lives, int gWidth, int gHeight) {
       this.remaningLives = new Counter(lives);
       this.keyboardSensor = ks;
       this.animationRunner = ar;
       this.score = new Counter();
       this.gWidth = gWidth;
       this.gHeight = gHeight;
   }

   /**.
    * Runs each level
    * @param levels the levels
    */
   public void runLevels(List<LevelInformation> levels) {

      for (LevelInformation levelInfo : levels) {
         GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                 this.animationRunner, this.score, this.remaningLives, this.gWidth, this.gHeight);

         level.initialize();

         while ((level.blocksRemaning() > 0) && (remaningLives.getValue() > 0)) {
             level.playOneTurn();
         }

         if (remaningLives.getValue() <= 0) {
             System.out.println("You lose");
            break;
         }

      }
      boolean isWin = (remaningLives.getValue() > 0);
      EndScreen es = new EndScreen(this.keyboardSensor, isWin, this.score.getValue());
      this.animationRunner.run(es);
   }
}

