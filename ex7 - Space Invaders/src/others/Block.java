package others;
import java.awt.Color;
import java.awt.Image;
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

    private Rectangle rec;
    private List<HitListener> hitListeners;
    private Color frameColor;
    private boolean hasFrame = false;
    private boolean isImage = false;
    private Color blockColor = null;
    private Image img = null;
    private boolean wasHit = false;
      /**.
       * Create Block from coordinate.
       * No Frame.
       * @param x - upperLeft corner's x
       * @param y -upperLeft corner's y
       * @param width - rectangle width
       * @param height - rectangle height
       * @param color - rectangle's color
       */
     public Block(double x, double y, double width, double height, Color color) {
     Point p = new Point(x, y);
     this.rec = new Rectangle(p, width, height);
     this.blockColor = color;
     this.hitListeners = new ArrayList<HitListener>();
     this.hasFrame = false;
 }

     /**.
      * Constructor with image
      * @param x x
      * @param y y
      * @param width width
      * @param height height
      * @param img image
      */
     public Block(double x, double y, double width, double height, Image img) {
         Point p = new Point(x, y);
         this.rec = new Rectangle(p, width, height);
         this.img = img;
         this.isImage = true;
         this.hitListeners = new ArrayList<HitListener>();
         this.hasFrame = true;
         this.frameColor = Color.black;
     }
     /**.
      * Create Block from coordinate and frame color
      *
      * @param x - upperLeft corner's x
      * @param y -upperLeft corner's y
      * @param width - rectangle width
      * @param height - rectangle height
      * @param color - rectangle's color
      * @param frame - The block's frame color
      */
    public Block(double x, double y, double width, double height, Color color, Color frame) {
    Point p = new Point(x, y);
    this.rec = new Rectangle(p, width, height);
    this.blockColor = color;
    this.hitListeners = new ArrayList<HitListener>();
    this.frameColor = frame;
    this.hasFrame = true;
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
         if (hitter.getDy() < 0) {
         this.wasHit = true;
         }
         this.notifyHit(hitter);

         double dx = currentVelocity.getDx();
         double dy = currentVelocity.getDy();

         if (this.rec.getRight().containsPoint(collisionPoint)) {
             dx = Math.abs(dx);
             dy = dy * 100000;
         }
         if (this.rec.getLeft().containsPoint(collisionPoint)) {
             dx = -Math.abs(dx);
             dy = dy * 100000;
         }
         if (this.rec.getTop().containsPoint(collisionPoint)) {
             dy = dy * 100000;
         }
         if (this.rec.getBot().containsPoint(collisionPoint)) {
             dy = dy * 100000;
         }
         Velocity v = new Velocity(dx, dy);
         return v;
     }
      /**.
        * Draw the block on the given DrawSurface
        * @param surface - the surface
        */
          public void drawOn(DrawSurface surface) {

              //From ass6
              int x = (int) this.rec.getUpperLeft().getX();
              int y = (int) this.rec.getUpperLeft().getY();

              if (this.isImage) {
                  surface.drawImage(x, y, this.img);
              } else {
                  surface.setColor(this.blockColor);
                  surface.fillRectangle(x, y, (int) this.rec.getWidth(), (int) this.rec.getHeight());
              }

              //Draws the frame
              if (this.hasFrame) {
              surface.setColor(this.frameColor);
              surface.drawRectangle(x, y, (int) this.rec.getWidth(), (int) this.rec.getHeight());
              }
        }
          /**.
           * Notify the sprite that time has passed
           * @param dt the dt value
           */
          public void timePassed(double dt) {
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
        /**.
         * move the block by x,y
         * @param x x
         * @param y y
         */
        public void move(double x, double y) {
            Point p = new Point(this.rec.getUpperLeft().getX() + x, this.rec.getUpperLeft().getY() + y);
            this.rec = new Rectangle(p, this.rec.getWidth(), this.rec.getHeight());
        }
        /**.
         * Move the block to x,y
         * @param x x
         * @param y y
         */
        public void moveTo(double x, double y) {
            Point p = new Point(x, y);
            this.rec = new Rectangle(p, this.rec.getWidth(), this.rec.getHeight());
        }
        /**.
         * Return if the block was hit
         * @return was hit
         */
        public boolean wasHit() {
            return this.wasHit;
        }

}