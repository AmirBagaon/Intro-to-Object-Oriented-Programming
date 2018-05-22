import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.util.Random;
import java.awt.Color;

/**
 *
 * @author Amir Bagaon
 *
 * A class of multiple bouncing balls
 */
public class MultipleBouncingBallsAnimation {

    private static final int LENGTH = 600;
    private static final int WIDTH = 600;

    /**.
     * Convert Strings to doubles
     *
     * @param numbers the array that will be converted
     * @return The converted array
     */
    public static double[] stringsToDoubles(String[] numbers) {
      double[] nums =  new double[numbers.length];
      for (int i = 0; i  <  numbers.length; i++) {
     try {
          nums[i] = Double.parseDouble(numbers[i]);
          // Check size's legal
           if (nums[i] <= 0) {
          System.out.println("Size must be bigger than 0");
          System.exit(1);
           }
           // Change numbers smaller than 1 to 1, so it won't be 0 after casting
           if (nums[i] <= 1) {
        nums[i] = 1;
           }
      } catch (Exception e) {
         System.out.println("The input: '" + numbers[i] + "' is "
            + "illegal.Only numbers allowed");
         System.out.println("Instead, the size has been set to 1");
         nums[i] = 1;
      }
      }
      return nums;
   }
  /**.
   * Create an array of balls
   *
   * Accordingly to the user's input, create balls with the
   * input's radiuses and place them in random place with random angle
   * @param sizes  -  an array with sizes of balls
   * @return balls  -  an array of balls with random x&y
   */
  public static Ball[] createBalls(double[] sizes) {
   Random r = new Random();
     double x, y, speed;
     Velocity v;
     Color c;

       Ball[] balls = new Ball[sizes.length];
   for (int i = 0; i < sizes.length; i++) {
    x = checkSize(sizes[i], WIDTH);
    y = checkSize(sizes[i], LENGTH);
    c = new Color((int) (Math.random() * 0x1000000));
       if (sizes[i] > 50) {
      speed = 1.5;
       } else {
      speed = (double) 100 / sizes[i];
       }
      balls[i] = new Ball(x, y, sizes[i], c, WIDTH, LENGTH);
      v = Velocity.fromAngleAndSpeed(r.nextInt(360), speed);
      balls[i].setVelocity(v);
       }
   return balls;
  }

  /**.
   * Check if the radius is not to big for the limits, and if not
   * it gives random coordinate for the ball
   *
   * @param size the radius size
   * @param firstB the first bound (right or bottom)
   * @return exit if the size is to big, otherwise randoms a number
   */
  public static double checkSize(double size, double firstB) {
    double x = 0;
    if ((firstB - 2 * size) <= 0) {
    System.out.println("The Size: " + size + " is to big");
    System.exit(1);
    } else {
        Random r = new Random();
    x = r.nextInt((int) (firstB - 2 * size)) + size;
    }
    return x;
  }

  /**
   * @param args - get sizes of radiuses from user
   */
    public static void main(String[] args) {
    GUI gui = new GUI("title", WIDTH, LENGTH);
       Sleeper sleeper = new Sleeper();
       //Ball's x&y equals to raduis so it won't stuck at 0,0
       double[] sizes = stringsToDoubles(args);
      Ball[] balls = createBalls(sizes);
//       ball.setVelocity(5, 5);
       while (true) {
      for (int i = 0; i < sizes.length; i++) {
          balls[i].moveOneStep();
        DrawSurface d = gui.getDrawSurface();
        balls[i].drawOn(d);
        gui.show(d);
        sleeper.sleepFor(10); // wait for 50 milliseconds.
      }
       }
    }

}
