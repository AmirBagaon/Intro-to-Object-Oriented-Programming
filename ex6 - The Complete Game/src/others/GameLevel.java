package others;

import java.awt.Color;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;

/**.
 *
 * A class of game managment
 * Contains the background and the field of the game,
 * including blocks and bounds information.
 * Has methods of running the game
 */
public class GameLevel implements Animation {
       private static final int BOUND_SIZE = 25;
       private static final int BALL_SIZE = 7;
       private static final int PADDLE_HEIGHT = 10;
       private static final int INDICATORS_SIZE = 15;


       private SpriteCollection sprites;
       private GameEnvironment environment;
       private Counter remaningBlocks;
       private Counter remaningBalls;
       private Counter score;
       private Counter lifeRemaning;
       private Paddle paddle;
       private AnimationRunner runner;
       private boolean running;
       private KeyboardSensor keyboard;
       private LevelInformation lvlInfo;
       private int gWidth;
       private int gHeight;

       /**.
        * Create a Game manager
        *
        * @param lvlInfo - The info of the level
        * @param ks - the Keyboard
        * @param ar - the animation runner
        * @param score - the score (even from before)
        * @param lifeRemaning - lives remaning
        * @param gWidth - game Width
        * @param gHeight - game Height
        */
       public GameLevel(LevelInformation lvlInfo, KeyboardSensor ks,
               AnimationRunner ar, Counter score, Counter lifeRemaning, int gWidth, int gHeight) {
           this.lvlInfo = lvlInfo;
           this.environment = new GameEnvironment();
           this.sprites = new SpriteCollection();
           this.remaningBlocks = new Counter(lvlInfo.numberOfBlocksToRemove());
           this.remaningBalls = new Counter(0);
           this.score = score;
           this.lifeRemaning = new Counter();
           this.runner = ar;
           this.keyboard = ks;
           this.lifeRemaning = lifeRemaning;
           this.gHeight = gHeight;
           this.gWidth = gWidth;
       }
       /**.
        * Add a collidable to the collidable's list
        * @param c = collidable
        */
       public void addCollidable(Collidable c) {
           this.environment.addCollidable(c);
       }
       /**.
        * Remove a collidable from the collidable's list
        * @param c = collidable
        */
       public void removeCollidable(Collidable c) {
           this.environment.removeCollidable(c);
       }
       /**.
        * Add a sprite to the sprite's list
        * @param s - sprite
        */
       public void addSprite(Sprite s) {
           this.sprites.addSprite(s);
       }
       /**.
        * Remove a sprite from the sprite's list
        * @param s - sprite
        */
       public void removeSprite(Sprite s) {
           this.sprites.removeSprite(s);
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

            //Create BackGround
            this.sprites.addSprite(lvlInfo.getBackground());

            //Create Bounds
            Color color = Color.gray;
            Block indicatorsBlock = new Block(0, 0, this.gWidth, INDICATORS_SIZE, Color.white, Color.white, 0);
            Block upperBound = new Block(0, INDICATORS_SIZE, this.gWidth, BOUND_SIZE, color, color, 0);
            Block leftBound = new Block(0, INDICATORS_SIZE, BOUND_SIZE, this.gHeight, color, color, 0);
            Block rightBound = new Block(this.gWidth - BOUND_SIZE, INDICATORS_SIZE, BOUND_SIZE,
                    this.gHeight, color, color, 0);
            upperBound.addToGame(this);
            leftBound.addToGame(this);
            rightBound.addToGame(this);
            indicatorsBlock.addToGame(this);

            //Create DeathBlock and it's Listener
            Block deathBlock = new Block(-this.gWidth, this.gHeight + 2 * BALL_SIZE,
                    this.gWidth * 3, this.gHeight, color, color, 0);
            deathBlock.addToGame(this);
            BallRemover ballR = new BallRemover(this, remaningBalls);
            deathBlock.addHitListener(ballR);

            //Create indicators
            indicators();

            //Create Paddle
            Point p = new Point(this.gWidth / 2 - this.lvlInfo.paddleWidth() / 2,
                    this.gHeight - BOUND_SIZE - PADDLE_HEIGHT + 1);
            Rectangle r = new Rectangle(p, this.lvlInfo.paddleWidth(), PADDLE_HEIGHT);
            this.paddle = new Paddle(r, Color.MAGENTA, this.gWidth,
                    BOUND_SIZE, this.keyboard, this.lvlInfo.paddleSpeed());
            this.paddle.addToGame(this);

            //Listeners
            ScoreTrackingListener scoreT = new ScoreTrackingListener(this.score);
            BlockRemover blockR = new BlockRemover(this, remaningBlocks);
            //PrintingHitListener health = new PrintingHitListener();


            //Create Blocks and add them to game
              for (Block i : this.lvlInfo.blocks()) {
                //i.addHitListener(health);
                  i.addHitListener(blockR);
                  i.addHitListener(scoreT);
                  i.addToGame(this);
              }
        }

        /**.
         * Create the balls and the paddle, balls will be with random color
         */
        public void ballsAndPaddle() {
            //Create Balls
              //Color color = new Color((int) (Math.random() * 0x1000000));
              for (Velocity i : lvlInfo.initialBallVelocities()) {
                  Ball b1 = new Ball(this.gWidth / 2, this.gHeight - BOUND_SIZE - 30, BALL_SIZE,
                          Color.white, this.environment);
                  b1.setVelocity(i);
                  b1.addToGame(this);
                this.remaningBalls.increase(1);
              }

            //Move Paddle to middle
              this.paddle.moveTo(this.paddle.getMiddle());
        }
        /**.
         * Initiallize the indicators
         */
        public void indicators() {
            int distance = this.gWidth - (2 * BOUND_SIZE);
            int x = distance / 8;
            int y = INDICATORS_SIZE / 3 * 2;
            y += y / 2 - 1;

            //Create Life Remaning Indicator
            LivesIndicator lIndic = new LivesIndicator(this.lifeRemaning, x + 10, y);
            lIndic.addToGame(this);

            //Create Score Indicator
            ScoreIndicator sIndic = new ScoreIndicator(this.score, x * 4 + 10, y);
            sIndic.addToGame(this);

            //Create name Indicator
            LevelNameIndicator nameIndic = new LevelNameIndicator(this.lvlInfo.levelName(), x * 6 + 10, y);
            nameIndic.addToGame(this);
        }
        /**.
         * Starts the Animation
         */
        public void playOneTurn() {
          this.ballsAndPaddle();
          this.runner.run(new CountdownAnimation(2, 3, this.sprites));

          this.running = true;
          // use our runner to run the current animation -- which is one turn of the game.
          this.runner.run(this);
        }

        /**.
         * Moves the animation one frame next
         * @param d the surface
         * @param dt the game dt value
         */
        public void doOneFrame(DrawSurface d, double dt) {
           /* Color color = Color.darkGray;
            d.setColor(color);
            d.fillRectangle(0, 0, this.gWidth, this.gHeight);
            */

          this.sprites.drawAllOn(d);
          this.sprites.notifyAllTimePassed(dt);

          //Pause
          if (this.keyboard.isPressed("p")) {
              Animation pause = new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY,
                      new PauseScreen());
              this.runner.run(pause);
           }

          //When Win or Lose
              if (this.remaningBlocks.getValue() <= 0) {
                  score.increase(100);
                  this.running = false;
              }
              if (this.remaningBalls.getValue() <= 0) {
                  this.lifeRemaning.decrease(1);
                  this.running = false;
              }


         }
        /**.
         * Returns if the animation should stop
         * @return true or false
         */
        public boolean shouldStop() {
            return !this.running;
        }
        /**.
         * Returns the number of blocks remaning
         * @return num
         */
        public int blocksRemaning() {
            return this.remaningBlocks.getValue();
        }

}
