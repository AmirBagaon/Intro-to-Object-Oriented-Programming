package animation;
import java.awt.Color;
import java.awt.Image;
import java.io.InputStream;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**.
 * Shows an EndScreen in the end of the game
 * Shows up until 'space' is pressed
 */
public class EndScreen implements Animation {
   private boolean isWin;
   private int score;

   /**.
    * Constructor
    * @param k the keyboard
    * @param win - If the player win or lose
    * @param score - The score
    */
   public EndScreen(KeyboardSensor k, boolean win, int score) {
      this.isWin = win;
      this.score = score;
   }
   /**.
    * Does the animation
    * @param d the surface
    * @param dt - the dt
    */
   public void doOneFrame(DrawSurface d, double dt) {
       d.setColor(Color.white);
       d.fillRectangle(0, 0, 800, 600);
       Image img = null;
       if (isWin) {
           try {
               InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("background_images/thewin.jpg");
               img = ImageIO.read(is);
               d.drawImage(400, 100, img);
           } catch (Exception e) {
               e.printStackTrace();
           }
          d.setColor(Color.CYAN);
          d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + score, 30);
          d.setColor(Color.black);
          d.drawText(12, d.getHeight() / 2 + 2, "You Win! Your score is " + score, 30);
      } else {
          try {
              InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("background_images/gameOver.png");
              img = ImageIO.read(is);
              d.drawImage(0, 0, img);
          } catch (Exception e) {
              e.printStackTrace();
          }
          d.setColor(Color.red);
          d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + score, 50);
      }
      d.setColor(Color.green.brighter());
      d.drawText(200, 500, "Press space to continue", 40);
   }

   /**.
    * Returns if the anim should stop
    * @return true or false
    */
   public boolean shouldStop() {
       return false;
   }
}
