import java.io.File;
import java.io.IOException;

import animation.Animation;
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.MenuAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
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
 * Space Invaders Game
 * Main class
 */
public class Ass7Game {

    private static final int TEXT_SIZE = 20;
    /**.
     * The main class
     * @param args the arguments
     * @throws IOException if load/save are not succssessfull
     */

    public static void main(String[] args) throws IOException {
        //Basic
        GUI gui = new GUI("Space Invaders", 800, 600);
        KeyboardSensor ks = gui.getKeyboardSensor();
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);

        //Creating HS table
        File filename = new File("highscores");
        HighScoresTable hst = HighScoresTable.loadFromFile(filename);
        HighScoresAnimation hSA = new HighScoresAnimation(hst);
        Animation highscores = new KeyPressStoppableAnimation(ks, ks.SPACE_KEY, hSA);

      //Creating menu
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(
                "Space Invaders", ks, animationRunner);

        //Add content to the menu
        menu.addSelection("s", "start game", new StartTask(animationRunner, ks, hst, gui.getDialogManager(), filename));
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

