package others;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import invadors.Formation;
import invadors.Level;
import invadors.Shield;
import invadors.ShieldRemover;

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
       private static final int SHIELD_Y = 500;


       private SpriteCollection sprites;
       private GameEnvironment environment;
       private Counter remaningEnemys;
       private Counter score;
       private Counter lifeRemaning;
       private Paddle paddle;
       private AnimationRunner runner;
       private boolean running;
       private KeyboardSensor keyboard;
       private LevelInformation lvlInfo;
       private int gWidth;
       private int gHeight;
       private Formation formation = null;
       private List<Ball> shots;

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
           this.remaningEnemys = new Counter(lvlInfo.numberOfEnemysRemove());
           this.score = score;
           this.lifeRemaning = new Counter();
           this.runner = ar;
           this.keyboard = ks;
           this.lifeRemaning = lifeRemaning;
           this.gHeight = gHeight;
           this.gWidth = gWidth;
           this.shots = new ArrayList<Ball>();
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
            //Listeners
            ScoreTrackingListener scoreT = new ScoreTrackingListener(this.score);
            BlockRemover blockR = new BlockRemover(this);
            BallRemover ballRemover = new BallRemover(this);
            EnemyRemover enemyRemove = new EnemyRemover(this, this.remaningEnemys);
            ShieldRemover shieldRemove = new ShieldRemover(this);


            Shield[] shields = new Shield[3];
            for (int i = 0; i < shields.length; i++) {
                shields[i] = new Shield(100 + 200 * i, SHIELD_Y);
                shields[i].create();
                shields[i].addToGame(this, shieldRemove);
            }

            Image img = lvlInfo.getEnemy();
            int speed = ((Level) (lvlInfo)).speed();
            this.formation = new Formation(img, speed);
            this.formation.create();
            this.formation.addToGame(this, enemyRemove, scoreT);

            //Create indicators
            Block indicatorsBlock = new Block(0, 0, this.gWidth, INDICATORS_SIZE, Color.white, Color.white);
            indicatorsBlock.addHitListener(ballRemover);
            indicatorsBlock.addToGame(this);
            indicators();

            //Create Paddle
            Point p = new Point(this.gWidth / 2 - this.lvlInfo.paddleWidth() / 2,
                    this.gHeight - BOUND_SIZE - PADDLE_HEIGHT + 1);
            Rectangle r = new Rectangle(p, this.lvlInfo.paddleWidth(), PADDLE_HEIGHT);
            this.paddle = new Paddle(r, Color.MAGENTA, this.gWidth,
                    this.keyboard, this.lvlInfo.paddleSpeed(), this, this.environment);
            this.paddle.addToGame(this);


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
//            System.out.println("Enemys:" + this.remaningEnemys.getValue());
          this.sprites.drawAllOn(d);
          this.sprites.notifyAllTimePassed(dt);
          //System.out.println(this.sprites.getList().size());

          //Pause
          if (this.keyboard.isPressed("p")) {
              Animation pause = new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY,
                      new PauseScreen());
              this.runner.run(pause);
           }

          //When Win or Lose
              if (this.remaningEnemys.getValue() <= 0) {
                  this.running = false;
              } else if (this.paddle.wasHit()) {
                      this.running = false;
                      this.lifeRemaning.decrease(1);
                      this.formation.resetPoistion();
                } else if (this.formation.shouldStop()) {
                  this.lifeRemaning.decrease(1);
                  this.formation.resetPoistion();
                  this.running = false;
                }

              if (!this.running) {
                  if (shots != null) {
                      for (Ball b: shots) {
                          this.sprites.removeSprite(b);
                      }
                      shots.removeAll(shots);
                  }
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
         * Returns the number of enemys remaning
         * @return num
         */
        public int enemysRemaning() {
            return this.remaningEnemys.getValue();
        }
        /**.
         * Return a list of the active balls int the game
         * @return balls
         */
        public List<Ball> shots() {
            return this.shots;
        }
        /**.
         * Return the game environment
         * @return env
         */
        public GameEnvironment getEnvironment() {
            return this.environment;
        }

}
