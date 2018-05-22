import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import animation.Animation;
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.MenuAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelSetInfo;
import levels.LevelSetReader;
import menu.Menu;
import menu.QuitTask;
import menu.ShowHiScoresTask;
import menu.StartTask;
import menu.Task;
import others.HighScoresTable;

/**.
 *
 * @author Amir Bagaon
 *
 * Block Breaker Game
 * Main class
 */
public class Ass6Game {

    private static final int TEXT_SIZE = 20;
    /**.
     * The main class
     * @param args the arguments
     * @throws IOException if load/save are not succssessfull
     */

    public static void main(String[] args) throws IOException {
        //Basic
        GUI gui = new GUI("Arkanoid", 800, 600);
        KeyboardSensor ks = gui.getKeyboardSensor();
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);

        //Creating HS table
        File filename = new File("highscores");
        HighScoresTable hst = HighScoresTable.loadFromFile(filename);
        HighScoresAnimation hSA = new HighScoresAnimation(hst);
        Animation highscores = new KeyPressStoppableAnimation(ks, ks.SPACE_KEY, hSA);

      //Creating menu and sub menu
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(
                "Arkanoid", ks, animationRunner);
        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>(
                "Sets", ks, animationRunner);

        //Try to load level sets
        Reader reader = null;
        InputStream is = null;
        List<LevelSetInfo> allSets = null;
        try {
            if (args.length != 0) {
                is = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
            } else {
                is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
            }
           reader = new InputStreamReader(is);

           //Read all the sets
           allSets = LevelSetReader.fromReader(reader);
        } catch (Exception e) {
            System.out.println("Could not read the level_sets");
            e.printStackTrace();
        }
        //Add to the subMenu
        for (LevelSetInfo set : allSets) {
            StartTask mode = new StartTask(animationRunner, set, ks, hst, gui.getDialogManager(), filename);
            subMenu.addSelection(set.getKey(), set.getDescription(), mode);
        }

        //Add content to the menu
        menu.addSubMenu("s", "start game", subMenu);
        menu.addSelection("e", "Exit", new QuitTask(gui));
        menu.addSelection("h", "High Scores", new ShowHiScoresTask(animationRunner, highscores));
        //Run the animation
        while (true) {
            System.out.println("new Loop");
            animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            if (task != null) {
                task.run();
            }
            ((MenuAnimation<Task<Void>>) menu).again();
        }
    }
}

