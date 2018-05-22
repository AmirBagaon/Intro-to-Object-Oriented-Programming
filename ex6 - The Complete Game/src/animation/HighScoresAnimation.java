package animation;
import java.awt.Color;
import java.awt.Image;
import java.io.InputStream;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;
import others.HighScoresTable;

/**.
 * The Class of the high score animation
 *
 */
public class HighScoresAnimation implements Animation {

       private HighScoresTable table;
       private boolean stop;
       private static final int TEXT_SIZE = 35;

       /**.
        * Constructor
        *
        * @param scores scores
        */
       public HighScoresAnimation(HighScoresTable scores) {
           this.stop = false;
           this.table = scores;
       }

        /**.
        * Starts the pause screen
        * finished when space is pressed
        * @param d the surface
        * @param dt the dt
        */
       public void doOneFrame(DrawSurface d, double dt) {
           d.setColor(Color.white);
           d.fillRectangle(0, 0, 800, 600);
           d.setColor(Color.CYAN.darker());
           d.drawText(200, 60, "High Scores", 50);
           for (int i = 0; i < table.getHighScores().size(); i++) {
               int y = d.getHeight() / 5 + (i * TEXT_SIZE) + 10;
               d.setColor(Color.black);

          d.drawText(100, y, (i + 1) + ". " + this.table.getHighScores().get(i).getName(), TEXT_SIZE);
          String score = Integer.toString(this.table.getHighScores().get(i).getScore());
          d.drawText(300, y, score, TEXT_SIZE);
              }
           Image img = null;
           try {
               InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(
                       "background_images/high_scores.jpg");
               img = ImageIO.read(is);
               d.drawImage(45, 299, img);
           } catch (Exception e) {
               e.printStackTrace();
           }

          d.setColor(Color.green.brighter());
          d.drawText(200, 500, "Press space to continue", 40);
       }
       /**.
        * Return if the method should stop
        * @return true or false
        */
       public boolean shouldStop() {
           return this.stop;
           }
}