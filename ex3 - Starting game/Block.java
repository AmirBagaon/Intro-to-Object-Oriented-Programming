import java.awt.Color;

import biuoop.DrawSurface;

/**.
 * Block Class
 *
 *
 */
public class Block implements Collidable, Sprite {
    private static final int DEFAULT = 15;

    private Rectangle rec;
    private Color color;
    private int health = DEFAULT;
      /**.
       * Create Block from coordinate
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
     * @param collisionPoint - the point of the collision
     * @param currentVelocity - the current velocity
     * @return the new velocity
     */
     public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
         //notify that the block was hit
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

              //Draws Black frame
              surface.setColor(Color.BLACK);
              surface.drawRectangle((int) this.rec.getUpperLeft().getX(),
                      (int) this.rec.getUpperLeft().getY(), (int) this.rec.getWidth(),
                      (int) this.rec.getHeight());
              int x = (int) (this.rec.getUpperLeft().getX() + this.rec.getWidth() / 2);
              int y = (int) (this.rec.getUpperLeft().getY() + this.rec.getHeight() / 2);

              //Draws Health
              if (this.health > 0) {
                  surface.drawText(x, y + 3, Integer.toString(this.health), 14);
              } else {
                  surface.drawText(x, y + 3, "X", 14);
              }
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
        public void addToGame(Game game) {
            game.addSprite(this);
            game.addCollidable(this);
        }

}
