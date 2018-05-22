package menu;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import animation.Animation;
import animation.AnimationRunner;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import levels.LevelSetInfo;
import levels.LevelSpecificationReader;
import others.GameFlow;
import others.HighScoresTable;
import others.LevelInformation;

/**.
 * Starts a game by reading a set of levels
 * @author PCP-RENT
 *
 */
public class StartTask implements Task<Void> {

    private AnimationRunner runner;
    private Animation highScoresAnimation;
    private boolean stop;
    private LevelSetInfo set;
    private KeyboardSensor ks;
    private HighScoresTable hST;
    private DialogManager hiScoreDialog;
    private File filename;

    /**.
     * Constructor
     * @param runner runner
     * @param set the set of levels
     * @param ks keyboard
     * @param hST high score table
     * @param d dialog manager
     * @param filename file
     */
    public StartTask(AnimationRunner runner, LevelSetInfo set, KeyboardSensor ks, HighScoresTable hST,
            DialogManager d, File filename) {
       this.stop = false;
       this.runner = runner;
       this.set = set;
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
        LevelSpecificationReader lSR = new LevelSpecificationReader();
        Reader reader = null;
        InputStream is = null;
        List<LevelInformation> allLevels = null;
        try {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(set.getPath());
            reader = new InputStreamReader(is);

           //Read all the sets
           allLevels = lSR.fromReader(reader);
        } catch (Exception e) {
            System.out.println("Could not read the level_sets");
            e.printStackTrace();
        }
        int lives = 7;
        GameFlow game = new GameFlow(this.runner, this.ks, lives, this.hST, this.hiScoreDialog, this.filename);
        game.runLevels(allLevels);

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