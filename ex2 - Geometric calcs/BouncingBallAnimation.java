import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 *
 * @author Amir
 * A class of Bouncing ball
 */
public class BouncingBallAnimation {
    private static final int LENGTH = 400;
    private static final int WIDTH = 600;
    private static final double RADIUS = 24;
    /**.
     * Main
     * @param args non
     */
    public static void main(String[] args) {

           GUI gui = new GUI("title", WIDTH, LENGTH);
              Sleeper sleeper = new Sleeper();
              //Ball's x&y equals to raduis so it won't stuck at 0,0
              Ball ball = new Ball(RADIUS, RADIUS, RADIUS, java.awt.Color.BLACK, WIDTH, LENGTH);
              Velocity v = Velocity.fromAngleAndSpeed(32.53, 5);
              ball.setVelocity(v);
             //optional: ball.setVelocity(5, 5);
              while (true) {
                 ball.moveOneStep();
                 DrawSurface d = gui.getDrawSurface();
                 ball.drawOn(d);
                 gui.show(d);
                 sleeper.sleepFor(50);  // wait for 50 milliseconds.
          }
    }

}
