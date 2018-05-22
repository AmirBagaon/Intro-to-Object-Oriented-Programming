import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**.
 * A class of the Paddle
 * Can move it to the sides
 *
 */
public class Paddle implements Sprite, Collidable {
       private biuoop.KeyboardSensor keyboard;
       private Rectangle rec;
       private Color color;
       private double lBound = 0;
       private double rBound;

       /**.
        * Construct the paddle
        *
        * @param rec the paddle's rectangle
        * @param c the paddle's color
        * @param gWidth the Game's screen width
        * @param boundSize the size of the bound blocks
        * @param keyboard - keyboard sensor
        */
       public Paddle(Rectangle rec, Color c, int gWidth, int boundSize, biuoop.KeyboardSensor keyboard) {
            this.rBound = gWidth - boundSize;
            this.lBound = boundSize;
            this.keyboard = keyboard;
            this.rec = rec;
            this.color = c;
        }

       /**.
        * Moves the paddle one step left if it is possible
        */
       public void moveLeft() {
           if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
               if (this.rec.getUpperLeft().getX() > this.lBound + 2) {
               Point p = new Point(this.rec.getUpperLeft().getX() - 3, this.rec.getUpperLeft().getY());
               this.rec = new Rectangle(p, this.rec.getWidth(), this.rec.getHeight());
               }
           }
       }
       /**.
        * Moves the paddle one step right if it is possible
        */
       public void moveRight() {
           if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
               if ((this.rec.getUpperLeft().getX() + this.rec.getWidth()) < this.rBound - 2) {
               Point p = new Point(this.rec.getUpperLeft().getX() + 3, this.rec.getUpperLeft().getY());
               this.rec = new Rectangle(p, this.rec.getWidth(), this.rec.getHeight());
               }
           }
       }

       /**.
        * Draws the paddle on the surface
        * @param d The drawSurface
        */
       public void drawOn(DrawSurface d) {
           d.setColor(this.color);
           d.fillRectangle((int) this.rec.getUpperLeft().getX(),
                   (int) this.rec.getUpperLeft().getY(),
                   (int) this.rec.getWidth(), (int) this.rec.getHeight());
       }
       /**.
        * Returns the paddle's rectangle
        *
        * @return the paddle's rectangle
        */
       public Rectangle getCollisionRectangle() {
           return this.rec;
       }
       /**
        * Returns the new velocity if the top of the paddle was hit
        * The paddle's top has 5 different areas that affect the
        * new velocity in different ways.
        * The middle will send the ball up, the righter will send it much right,
        * and the most left area will send it much left.
        *
        * @param p the collision point
        * @param currentVelocity the current Velocity
        * @return the new Velocity
        */
       public Velocity hitTop(Point p, Velocity currentVelocity) {
           double width = (double) this.rec.getWidth() / 5;
           double x1 = this.rec.getUpperLeft().getX();
           double x2 = x1 + width;
           Velocity v = null;
           for (int i = 0; i < 5; i++) {
               if ((p.getX() >= x1) && (p.getX() <= x2)) {
                   return Velocity.fromAngleAndSpeed(300 + (30 * i), currentVelocity.getSpeed());
               }
               x1 = x2;
               x2 += width;
           }
           return v;
       }

       /**
        * Calculate and returns the new velocity after the hit
        * The paddle's top has 5 different areas that affect the
        * new velocity in different ways.
        *
        * @param collisionPoint the collision point
        * @param currentVelocity the current Velocity
        * @return the new Velocity
        */
       public Velocity hit(Point collisionPoint, Velocity currentVelocity) {

           if (this.rec.getTop().containsPoint(collisionPoint)) {
               return hitTop(collisionPoint, currentVelocity);
           }
             double xChange = 1;
             double yChange = 1;

             if (this.rec.getRight().containsPoint(collisionPoint)
                     || this.rec.getLeft().containsPoint(collisionPoint)) {
                 xChange *= -1;
             }
             if (this.rec.getBot().containsPoint(collisionPoint)) {
                 yChange *= -1;
             }
         Velocity v = new Velocity(currentVelocity.getDx() * xChange, currentVelocity.getDy() * yChange);
             return v;
         }
       /**.
        * Add this paddle to the game
        *
        * @param g - The game
        */
       public void addToGame(Game g) {
           g.addCollidable(this);
           g.addSprite(this);
       }
       /**.
        * Updates the sprite in the game
        */
       public void timePassed() {
           this.moveRight();
           this.moveLeft();
       }
    }

