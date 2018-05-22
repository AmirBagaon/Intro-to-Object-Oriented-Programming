
import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**.
 *
 * A class of game managment
 * Contains the background and the field of the game,
 * including blocks and bounds information.
 * Has methods of running the game
 */
public class Game {
       private static final int BOUND_SIZE = 15;
       private static final int HEIGHT = 600;
       private static final int WIDTH = 800;
       private static final int BLOCK_WIDTH = 50;
       private static final int BLOCK_HEIGHT = 30;
       private static final int PADDLE_HEIGHT = 10;
       private static final int PADDLE_WIDTH = 150;


       private SpriteCollection sprites;
       private GameEnvironment environment;
       private GUI gui;
       private Sleeper sleeper;

       /**.
        * Create a Game manager
        */
       public Game() {
           this.environment = new GameEnvironment();
           this.sprites = new SpriteCollection();
       }
       /**.
        * Add a collidable to the collidable's list
        * @param c = collidable
        */
       public void addCollidable(Collidable c) {
           this.environment.addCollidable(c);
       }
       /**.
        * Add a sprite to the sprite's list
        * @param s - sprite
        */
       public void addSprite(Sprite s) {
           this.sprites.addSprite(s);
       }

       /**.
        * Initialize a new game: create the Blocks and Ball (and Paddle)
        * and add them to the game
        *
        * @param horizontalBound
        * @param verticalBound
        * @param margin
        */
        public void initialize() {

            //Create GUI and Sleeper
            this.gui = new GUI("WOW! WOW!", WIDTH, HEIGHT);
            this.sleeper = new Sleeper();

            //Create Bounds
            Color color = Color.gray;
            Block upperBound = new Block(0, 0, WIDTH, BOUND_SIZE, color, 0);
            Block leftBound = new Block(0, 0, BOUND_SIZE, HEIGHT, color, 0);
            Block botBound = new Block(0, HEIGHT - BOUND_SIZE, WIDTH, HEIGHT, color, 0);
            Block rightBound = new Block(WIDTH - BOUND_SIZE, 0, BOUND_SIZE, HEIGHT, color, 0);
            upperBound.addToGame(this);
            leftBound.addToGame(this);
            botBound.addToGame(this);
            rightBound.addToGame(this);

            //Create Blocks and add them to game
            int x = WIDTH - BOUND_SIZE - BLOCK_WIDTH;
            int health = 2;
            for (int i = 0; i < 6; i++) {
                color = new Color((int) (Math.random() * 0x1000000));
                Block[] row = new Block[12 - i];
                int y = 100 + i * BLOCK_HEIGHT;
                for (int j = 0; j < 12 - i; j++) {
                    row[j] = new Block(x - j * BLOCK_WIDTH, y,
                            BLOCK_WIDTH, BLOCK_HEIGHT, color, health);
                    row[j].addToGame(this);
                }
                health = 1;
            }

            //Create Balls
            Ball b1 = new Ball(WIDTH / 2, HEIGHT - BOUND_SIZE - 30, 4, Color.GREEN, this.environment);
            b1.setVelocity(-3, -2);
            b1.addToGame(this);

            Ball b2 = new Ball(WIDTH / 2, HEIGHT - BOUND_SIZE - 30, 4, Color.WHITE, this.environment);
            b2.setVelocity(6.3, -2.3);
            b2.addToGame(this);

          //Create Paddle
            Point p = new Point(WIDTH / 2 - PADDLE_WIDTH, HEIGHT - BOUND_SIZE - PADDLE_HEIGHT + 1);
            Rectangle r = new Rectangle(p, PADDLE_WIDTH, PADDLE_HEIGHT);
            Paddle paddle = new Paddle(r, Color.MAGENTA, WIDTH, BOUND_SIZE, gui.getKeyboardSensor());
            paddle.addToGame(this);

        }

        /**.
         * Starts the Animation
         */
        public void run() {
               int framesPerSecond = 60;
               int millisecondsPerFrame = 1000 / framesPerSecond;
               while (true) {
                  long startTime = System.currentTimeMillis(); // timing

                  DrawSurface d = gui.getDrawSurface();
                  //Create Background
                    Color color = Color.darkGray;
                    d.setColor(color);
                    d.fillRectangle(0, 0, WIDTH, HEIGHT);

                  this.sprites.drawAllOn(d);
                  gui.show(d);
                  this.sprites.notifyAllTimePassed();

                  // timing
                  long usedTime = System.currentTimeMillis() - startTime;
                  long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
                  if (milliSecondLeftToSleep > 0) {
                      sleeper.sleepFor(milliSecondLeftToSleep);
                  }
               }
            }
    }