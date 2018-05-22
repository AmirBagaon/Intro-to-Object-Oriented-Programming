package invadors;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import biuoop.DrawSurface;
import others.Ball;
import others.GameLevel;
import others.HitListener;
import others.Sprite;

/**.
 * A class of formation.
 * Contains list of lists of enemy,
 * and manage the movement of them.
 *
 */
public class Formation implements Sprite {

    private static final int ROW = 5;
    private static final int COL = 10;
    private static final int STARTX = 100;
    private static final int STARTY = 50;
    private static final int SPACE = 50;
    private static final int WIDTH = 40;

    private Image enemy;
    private List<List<Enemy>> formation;
    private double speed;
    private double originalSpeed;
    private boolean shouldStop = false;
    private boolean ableToShoot = true;
    private long shootTime = System.currentTimeMillis();
    private GameLevel game;

    /**.
     * Constructor
     * @param enemy the image of enemy
     * @param speed the level's speed
     */
    public Formation(Image enemy, double speed) {
        this.enemy = enemy;
        this.formation = new ArrayList<List<Enemy>>();
        this.speed = speed;
        this.originalSpeed = speed;
    }

    /**.
     * Add the formation to the level
     */
    public void create() {
        for (int i = 0; i < COL; i++) {
            List<Enemy> list = new ArrayList<Enemy>();
            for (int j = 0; j < ROW; j++) {
                Enemy e = new Enemy(STARTX + i * SPACE, STARTY + j * (SPACE - 10), enemy);
                list.add(e);
            }
            this.formation.add(list);
        }
    }

    /**.
     * Add the formation (it's enemy's blocks) to the game.
     * Add a listener which remove the ball and the block if theres a hit
     * @param g the game
     * @param remove the remove listener
     * @param score the score listener
     */
    public void addToGame(GameLevel g, HitListener remove, HitListener score) {
        this.game = g;

        //Add the formation to the sprites
        g.addSprite(this);

        //Add each enemy
        for (List<Enemy> list: this.formation) {
            for (Enemy e: list) {
                e.enemyBlock().addHitListener(remove);
                e.enemyBlock().addHitListener(score);
                e.enemyBlock().addToGame(g);
            }
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
    }

    @Override
    public void timePassed(double dt) {
        /*
        Set<Integer> d = numColumn();
        List<Integer> l = new ArrayList<Integer>();
        l.addAll(d);
        Collections.sort(l);
        System.out.println(l.toString());
        */
      //System.out.println(this.formation.size());
        System.out.println(this.speed);
        checkHits();
        isAbleToShoot();
        if (this.ableToShoot) {
            enemyShoot();
        }
        boolean down = false;
        double lBound = this.formation.get(0).get(0).currentX();
        if ((lBound + this.speed) < 0) {
            down = true;
        } else {
            double rBound = this.formation.get(this.formation.size() - 1).get(0).currentX();
            //System.out.println(rBound);
            if ((rBound + WIDTH + this.speed) > 800) {
                //System.out.println(true);
                down = true;
            }
        }

        if (down) {
            this.speed *= -1.1;
            for (List<Enemy> list2: this.formation) {
            for (Enemy e: list2) {
                if (e.lineDown(20)) {
                    this.shouldStop = true;
                }
              }
            }
        } else {
            //If the formation hit the walls
              for (List<Enemy> list: this.formation) {
                  for (Enemy e: list) {
                      e.move(this.speed);
                  }
              }
          }

      }

    /**.
     * Return if it should stop
     * @return stop
     */
    public boolean shouldStop() {
        return this.shouldStop;
    }
    /**.
     * Set the formation at the begining position
     */
    public void resetPoistion() {
        for (List<Enemy> list: this.formation) {
            for (Enemy e: list) {
                e.resetPosition();
            }
        }
        this.shouldStop = false;
        this.speed = this.originalSpeed;
    }

    /**.
     * Check and remove empty enemys and enemys's lists
     */
    public void checkHits() {

        //int j = 0;
        for (List<Enemy> list: this.formation) {
            //j++;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).enemyBlock().wasHit()) {
                    list.remove(i);
                 //  System.out.println("Removed:" + i + "From list: "+j);
                }
            }
        }
        for (int i = 0; i < this.formation.size(); i++) {
            if (this.formation.get(i).size() == 0) {
                this.formation.remove(i);
//                System.out.println("List Removed: "+i);
            }
        }
    }
    /**.
     * Check if the formation is able to shoot.
     * If true - it shoots
     */
    public void enemyShoot() {
        if (this.ableToShoot) {
            //Find the spot of shooting
            Random rand = new Random();
            int num = rand.nextInt(this.formation.size());
            int size = this.formation.get(num).size() - 1;
            try {
            Enemy e = this.formation.get(num).get(size);
            double x = e.enemyBlock().getCollisionRectangle().getUpperLeft().getX();
            x += e.enemyBlock().getCollisionRectangle().getWidth() / 2;
            double y = e.enemyBlock().getCollisionRectangle().getUpperLeft().getY();
            y += e.enemyBlock().getCollisionRectangle().getHeight();

            //Create the shot
            Ball b = new Ball(x, y + 1, 4, Color.red, this.game.getEnvironment());
            b.setVelocity(0, 225);
            b.addToGame(this.game);
            this.game.shots().add(b);
            this.shootTime = System.currentTimeMillis();
            this.ableToShoot = false;
            } catch (Exception e2) {
                ;
            }
        }
    }
    /**.
     * Checks if 0.5 seconds passed, and if true - makes the formation able to shoot
     */
    public void isAbleToShoot() {
        if (!this.ableToShoot) {
            long elapsedTime = 0L;
            elapsedTime = (new Date()).getTime() - this.shootTime;
            if (elapsedTime > 0.5 * 1000) {
                this.ableToShoot = true;
            }
        }
    }

}
