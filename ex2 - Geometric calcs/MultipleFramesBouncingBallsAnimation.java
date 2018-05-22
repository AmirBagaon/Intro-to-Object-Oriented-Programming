import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.util.Random;
import java.awt.Color;

/**
 *
 * @author Amir Bagaon
 *
 * A class of multiple bouncing balls in rectangles
 */
public class MultipleFramesBouncingBallsAnimation {
    //Screen size
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
  /**
   * Create an array of balls
   *
   * Accordingly to the user's input, the first half of the balls will locate
   * in the gray rectangle, and the second half will locate in the yellow rectangle.
   * Each ball has it's own size accordingly to the user's input, and will locate in
   * random place, with random color
   *
   * @param sizes  -  an array with sizes of balls
   * @return balls  -  an array of balls with random x&y
   */
  public static Ball[] createBalls(double[] sizes) {
   Random r = new Random();
   double x, y, speed;
   Velocity v;
   Color c;
   int lBound, rBound, uBound, dBound;

   Ball[] balls = new Ball[sizes.length];
   for (int i = 0; i < sizes.length; i++) {
       if (i < sizes.length / 2) {
           lBound = 50; uBound = 50;
           dBound = 500; rBound = 500;
       } else {
           lBound = 450; uBound = 450;
           dBound = 600; rBound = 600;
       }
       //Set x&y accordingly to the bounds
       System.out.println("Size:" + sizes[i]);
       x = checkSize(sizes[i], rBound, lBound);
       y = checkSize(sizes[i], dBound, uBound);

        //Set random color
        c = new Color((int) (Math.random() * 0x1000000));

        //Set speed
        if (sizes[i] > 50) {
           speed = 1;
        } else {
           speed = (double) 60 / sizes[i];
        }

        //Create the ball, and set the bounds and the angle&speed
        balls[i] = new Ball(x, y, sizes[i], c);
        balls[i].setBounds(uBound, dBound, lBound, rBound);
        v = Velocity.fromAngleAndSpeed(r.nextInt(360), speed);
        balls[i].setVelocity(v);
     } // end of loop
   return balls;
  }
  /**.
   * Check if the radius is not to big for the limits, and if not
   * it gives random coordinate for the ball
   *
   * @param size the radius size
   * @param firstB the first bound (right or bottom)
   * @param secondB the second bound (left or upper)
   * @return exit if the size is to big, otherwise randoms a number
   */
  public static double checkSize(double size, double firstB, double secondB) {
    double x = 0;
    if ((firstB - 2 * size - secondB) <= 0) {
         System.out.println("The Size: " + size + " is to big");
         System.exit(1);
    } else {
        Random r = new Random();
         x = r.nextInt((int) (firstB - 2 * size - secondB))
                 + secondB + size; //so it won't stuck at 0,0 or at the end of the screen
    }
    return x;
  }
  /**
   *
   * @param args  -  the user insert sizes of radiuses
   */
    public static void main(String[] args) {
       GUI gui = new GUI("title", WIDTH, LENGTH);
       Sleeper sleeper = new Sleeper();
       double[] sizes = stringsToDoubles(args);
       Ball[] balls = createBalls(sizes);

       while (true) {
           for (int i = 0; i < sizes.length; i++) {
               balls[i].moveOneStep();
               DrawSurface d = gui.getDrawSurface();
               d.setColor(Color.gray);;
               d.fillRectangle(50, 50, 450, 450);
               d.setColor(Color.yellow);;
               d.fillRectangle(450, 450, 150, 150);
               balls[i].drawOn(d);
               gui.show(d);
               sleeper.sleepFor(5); // wait for 50 milliseconds.
           }
       }
    }

}
