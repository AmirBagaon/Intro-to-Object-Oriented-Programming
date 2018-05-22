package menu;

import java.io.File;

import animation.Animation;
import animation.AnimationRunner;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import others.GameFlow;
import others.HighScoresTable;

/**.
 * Starts a game by reading a set of levels
 * @author PCP-RENT
 *
 */
public class StartTask implements Task<Void> {

    private AnimationRunner runner;
    private Animation highScoresAnimation;
    private boolean stop;
    private KeyboardSensor ks;
    private HighScoresTable hST;
    private DialogManager hiScoreDialog;
    private File filename;

    /**.
     * Constructor
     * @param runner runner
     * @param ks keyboard
     * @param hST high score table
     * @param d dialog manager
     * @param filename file
     */
    public StartTask(AnimationRunner runner, KeyboardSensor ks, HighScoresTable hST,
            DialogManager d, File filename) {
       this.stop = false;
       this.runner = runner;
       this.ks = ks;
       this.hST = hST;
       this.hiScoreDialog = d;
       this.filename = filename;
    }
    /**.
     * Run the game, including reading the level information
     * @return null
     */
    public Void run() {
        int lives = 3;
        GameFlow game = new GameFlow(this.runner, this.ks, lives, this.hST, this.hiScoreDialog, this.filename);
        game.runLevels();
        //game.runLevels(allLevels);

       this.stop = true;
       return null;
    }
    /**.
     * Return if it should stop
     * @return stop
     */
    public boolean shouldStop() {
        return this.stop;
    }
 }