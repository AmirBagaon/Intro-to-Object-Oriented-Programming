package others;
import java.io.File;
import java.io.IOException;

import animation.Animation;
import animation.AnimationRunner;
import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import invadors.Level;
/**.
 * This class will be in charge of creating the differnet levels,
 * and moving from one level to the next.
 *
 */
public class GameFlow {

   private static final int GAME_WIDTH = 800;
   private static final int GAME_HEIGHT = 600;

   private KeyboardSensor keyboardSensor;
   private AnimationRunner animationRunner;
   private Counter remaningLives;
   private Counter score;
   private HighScoresTable hST;
   private DialogManager hiScoreDialog;
   private File filename;

   /**.
    * Constructor
    * @param ar - The Animation Runner
    * @param ks - The keboard
    * @param lives - Lives remaning
    * @param hST - HighScoresTable
    * @param d - dialog manager
    * @param filename - filename
    */
   public GameFlow(AnimationRunner ar, KeyboardSensor ks, int lives, HighScoresTable hST,
           DialogManager d, File filename) {
       this.remaningLives = new Counter(lives);
       this.keyboardSensor = ks;
       this.animationRunner = ar;
       this.score = new Counter();
       this.hST = hST;
       this.hiScoreDialog = d;
       this.filename = filename;
   }

   /**.
    * Runs each level
    */
   public void runLevels() {

       int i = 1;
      while (this.remaningLives.getValue() > 0) {
          Level info = new Level(i++);
         GameLevel level = new GameLevel(info, this.keyboardSensor,
                 this.animationRunner, this.score, this.remaningLives, GAME_WIDTH, GAME_HEIGHT);

         level.initialize();

         while ((level.enemysRemaning() > 0) && (this.remaningLives.getValue() > 0)) {
             level.playOneTurn();
         }

         if (this.remaningLives.getValue() <= 0) {
             System.out.println("You lose");
            break;
         }

      }
      boolean updated = this.hST.updateTable(this.hiScoreDialog, this.score.getValue());
      if (updated) {
          //Save the new hst
              try {
                this.hST.save(this.filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
      }
      boolean isWin = (remaningLives.getValue() > 0);
      EndScreen es = new EndScreen(this.keyboardSensor, isWin, this.score.getValue());
      Animation end = new KeyPressStoppableAnimation(this.keyboardSensor, this.keyboardSensor.SPACE_KEY, es);
      this.animationRunner.run(end);
      Animation hiscores = new HighScoresAnimation(this.hST);
      this.animationRunner.run(new KeyPressStoppableAnimation(
              this.keyboardSensor, this.keyboardSensor.SPACE_KEY, hiscores));
   }
}

