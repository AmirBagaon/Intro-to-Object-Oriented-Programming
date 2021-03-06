package others;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

/**.
 * Block Class
 *
 *
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private static final int DEFAULT = 15;

    private Rectangle rec;
    private Color color;
    private int health = DEFAULT;
    private List<HitListener> hitListeners;
    private Color frame;
      /**.
       * Create Block from coordinate.
       * Frame default is black.
       * @param x - upperLeft corner's x
       * @param y -upperLeft corner's y
       * @param width - rectangle width
       * @param height - rectangle height
       * @param color - rectangle's color
       * @param health - health remaning
       */
     public Block(double x, double y, double width, double height, Color color, int health) {
     Point p = new Point(x, y);
     this.rec = new Rectangle(p, width, height);
     this.color = color;
     this.health = health;
     this.hitListeners = new ArrayList<HitListener>();
     this.frame = Color.black;
 }
     /**.
      * Create Block from coordinate and frame color
      *
      * @param x - upperLeft corner's x
      * @param y -upperLeft corner's y
      * @param width - rectangle width
      * @param height - rectangle height
      * @param color - rectangle's color
      * @param health - health remaning
      * @param frame - The block's frame color
      */
    public Block(double x, double y, double width, double height, Color color, Color frame, int health) {
    Point p = new Point(x, y);
    this.rec = new Rectangle(p, width, height);
    this.color = color;
    this.health = health;
    this.hitListeners = new ArrayList<HitListener>();
    this.frame = frame;
}

      /**.
       * Returns the block's rectangle
       * @return this.rec
       */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

     /**.
     *  Returns the new velocity after the hit
     *  Also notify the block that a hit occurred
     * @param hitter - The ball that hit
     * @param collisionPoint - the point of the collision
     * @param currentVelocity - the current velocity
     * @return the new velocity
     */
     public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
         //notify that the block was hit
         this.notifyHit(hitter);
         this.health--;

         double dx = currentVelocity.getDx();
         double dy = currentVelocity.getDy();

         if (this.rec.getRight().containsPoint(collisionPoint)) {
             dx = Math.abs(dx);
         }
         if (this.rec.getLeft().containsPoint(collisionPoint)) {
             dx = -Math.abs(dx);
         }
         if (this.rec.getTop().containsPoint(collisionPoint)) {
             dy = -Math.abs(dy);
         }
         if (this.rec.getBot().containsPoint(collisionPoint)) {
             dy = Math.abs(dy);
         }
         Velocity v = new Velocity(dx, dy);
         return v;
     }
      /**.
        * Draw the block on the given DrawSurface
        * @param surface - the surface
        */
          public void drawOn(DrawSurface surface) {
              //Draws the block
              surface.setColor(this.color);
              surface.fillRectangle((int) this.rec.getUpperLeft().getX(),
                      (int) this.rec.getUpperLeft().getY(), (int) this.rec.getWidth(),
                      (int) this.rec.getHeight());

              //Draws the frame
              surface.setColor(this.frame);
              surface.drawRectangle((int) this.rec.getUpperLeft().getX(),
                      (int) this.rec.getUpperLeft().getY(), (int) this.rec.getWidth(),
                      (int) this.rec.getHeight());

              //Draws Health
             /*
              int x = (int) (this.rec.getUpperLeft().getX() + this.rec.getWidth() / 2);
              int y = (int) (this.rec.getUpperLeft().getY() + this.rec.getHeight() / 2);
                 if (this.health > 0) {
                  surface.drawText(x, y + 3, Integer.toString(this.health), 14);
              } else {
                  surface.drawText(x, y + 3, "X", 14);
              }
              */
        }
          /**.
           * Not used yet
           */
        public void timePassed() {

        }
        /**.
         * Add the block to the game's coolidable list and sprite list
         * @param game the game that contains the lists
         */
        public void addToGame(GameLevel game) {
            game.addSprite(this);
            game.addCollidable(this);
        }
        /**.
         * Remove the block from the game's coolidable list and sprite list
         * @param game the game that contains the lists
         */
        public void removeFromGame(GameLevel game) {
            game.removeCollidable(this);
            game.removeSprite(this);
        }
        /**.
         * Notify that the block was hit
         * @param hitter the ball
         */
        public void notifyHit(Ball hitter) {
            // Make a copy of the hitListeners before iterating over them.
            List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
            // Notify all listeners about a hit event:
            for (HitListener hl : listeners) {
               hl.hitEvent(this, hitter);
            }
        }
        /**.
         * Return the Remaning Health Point
         * @return hitpoints
         */
        public int getHitPoints() {
            return this.health;
        }

        /**.
         * Add a HitListener to the block
         * @param l the listener
         */
        public void addHitListener(HitListener l) {
            if (l != null) {
                this.hitListeners.add(l);
            }
        }
        /**.
         * Removes a HitListener from the block
         * @param l the listener
         */
        public void removeHitListener(HitListener l) {
        this.hitListeners.remove(l);
        }
}