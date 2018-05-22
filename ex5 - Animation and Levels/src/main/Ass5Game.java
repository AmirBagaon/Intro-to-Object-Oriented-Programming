package main;
import java.util.ArrayList;
import java.util.List;

import animation.AnimationRunner;
import biuoop.GUI;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;
import others.GameFlow;
import others.LevelInformation;

/**.
 *
 * @author Amir Bagaon
 *
 * Block Breaker Game
 * Main class
 */
public class Ass5Game {

    /**.
     * The main class
     * @param args the arguments
     */

    public static void main(String[] args) {
        GUI gui = new GUI("Wow!!!!!", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui, 60);
        List<LevelInformation> lvls = new ArrayList<LevelInformation>();
        if (args.length == 0) {
            lvls.add(new Level1());
            lvls.add(new Level2());
            lvls.add(new Level3());
            lvls.add(new Level4());
        } else {
            for (String i : args) {
                int num = -1;
                try {
                    num = Integer.parseInt(i);
                } catch (Exception e) {
                    System.out.println("The arg: " + i + " isn't a number");
                    continue;
                }
                if (num == 1) {
                    lvls.add(new Level1());
                }
                if (num == 2) {
                    lvls.add(new Level2());
                    }
                if (num == 3) {
                    lvls.add(new Level3());
                }
                if (num == 4) {
                    lvls.add(new Level4());
                    }
                num = -1;
                }
            }
        int lives = 7;
        GameFlow game = new GameFlow(ar, gui.getKeyboardSensor(), lives, 800, 600);
        game.runLevels(lvls);
        gui.close();
    }

}
