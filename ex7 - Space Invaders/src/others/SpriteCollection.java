package others;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
/**.
 * A class of Sprite collection
 * The class collect and manage all the sprites in the game
 * @author Amir Bagaon
 *
 */
public class SpriteCollection {

private List<Sprite> list;

    /**.
    * Construct the sprite collection list
    */
    public SpriteCollection() {
    this.list = new ArrayList<Sprite>();
    }
   /**.
    * Add sprite to the sprite list
    * @param s - The sprite
    */
   public void addSprite(Sprite s) {
   this.list.add(s);
   }
   /**.
    * Remove sprite from the sprite list
    * @param s - The sprite
    */
   public void removeSprite(Sprite s) {
       if (this.list.contains(s)) {
           this.list.remove(s);
       }
   }

   /**.
    * Call timePassed() on all sprites.
    * @param dt the dt value
    */
   public void notifyAllTimePassed(double dt) {
       for (int i = 0; i < this.list.size(); i++) {
           this.list.get(i).timePassed(dt);
       }
   }

   /**.
    * Call drawOn(d) on all sprites
    * @param d - The DrawSurface
    */
   public void drawAllOn(DrawSurface d) {
       for (int i = 0; i < this.list.size(); i++) {
           this.list.get(i).drawOn(d);
       }
   }

   /**.
    * Return the sprite's list
    * @return list
    */
   public List<Sprite> getList() {
       return this.list;
   }
}