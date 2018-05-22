package others;

import java.io.Serializable;

/**.
 *
 * The score info (name, score)
 *
 */
public class ScoreInfo  implements Serializable {
    private String name;
    private int score;
    /**.
     * Constructor
     * @param name name
     * @param score score
     */
   public ScoreInfo(String name, int score) {

       this.name = name;
       this.score = score;
   }
   /**.
    * Return name
    * @return n
    */
   public String getName() {
       return this.name;
   }
   /**.
    * Return score
    * @return s
    */
   public int getScore() {
       return this.score;
   }
}