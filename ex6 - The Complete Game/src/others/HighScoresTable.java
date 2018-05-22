package others;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import biuoop.DialogManager;

/**.
 *
 * The HighScore table
 *
 */
public class HighScoresTable implements Serializable {
    private static final int MAX = 5;
    private int size;
    private List<ScoreInfo> hScore;

  /**.
  *  Create an empty high-scores table with the specified size.
  *  The size means that the table holds up to size top scores.
  * @param size the size
  */
     public HighScoresTable(int size) {
         this.size = size;
         this.hScore = new ArrayList<ScoreInfo>();
     }


     /**.
      * Add a high-score.
      * @param score score
      */
     public void add(ScoreInfo score) {
         int index = getRank(score.getScore());

         if (index > size) {
             System.out.println("Problem: " + score.getScore());
             return;
         }
         index--;
         this.hScore.add(index, score);

         //If there are more scores than table's size
         if (this.hScore.size() > this.size) {
                 this.hScore.remove(this.size);
         }
     }
     /**.
      * Return table size.
      * @return table
      */
     public int size() {
         return this.size;
     }
     /**.
      * Return the current high scores.
      * The list is sorted such that the highest scores come first
      * @return high score
      */
     public List<ScoreInfo> getHighScores() {
         return this.hScore;
     }

     /**>
      * return the rank of the current score: where will it
      * be on the list if added?
      * Rank 1 means the score will be highest on the list.
      * Rank `size` means the score will be lowest.
      * Rank > `size` means the score is too low and will not
      * be added to the list.
      * @param score the score
      * @return the rank
      */
     public int getRank(int score) {
         int i = 0;
         for (; i < this.hScore.size(); i++) {
             if (score > this.hScore.get(i).getScore()) {
                 return (i + 1);
             }
         }
         return (i + 1);
     }

     /**.
      *  Clears the table
      */
     public void clear() {
         this.hScore.clear();
     }

     /**.
      * Load table data from file.
      * Current table data is cleared.
      * @param filename f
      * @throws IOException ex
      */
     public void load(File filename) throws IOException {
         ObjectInputStream oIS = null;
         try {
             oIS = new ObjectInputStream(new FileInputStream(filename.getName()));

             //Reads the HighScoresTable (hST) from the file
             HighScoresTable hST = (HighScoresTable) (oIS.readObject());

             //Set the current table and size
             this.hScore = hST.getHighScores();
             this.size = hST.size;
         } catch (ClassNotFoundException e) {
             System.out.println("Class not found");
             e.printStackTrace();
         } catch (FileNotFoundException e) {
             System.out.println("None HighScoresTable was detected");
             e.printStackTrace();

             //Create New Table
             HighScoresTable table = new HighScoresTable(MAX);
             this.size = table.size;
             this.hScore = table.getHighScores();
             table.save(filename);
         } finally {

             //Close the file
             try {
                 if (oIS != null) {
                     oIS.close();
                 }
             } catch (IOException e) {
                     System.out.println("Fail to close the file");
                     e.printStackTrace();
                 }
             }
     }


     /**.
      * Save table data to the specified file.
      * @param filename f
      * @throws IOException ex
      */
     public void save(File filename) throws IOException {
        ObjectOutputStream oOS = null;
         try {
             oOS = new ObjectOutputStream(new FileOutputStream(filename.getName()));
             //Write the HighScores on file
             oOS.writeObject(this);
         } catch (IOException e) {
             System.out.println("Fail to save the file");
             e.printStackTrace();
         } finally {

             //Close the File
             try {
                 if (oOS != null) {
                     oOS.close();
                 }
             } catch (IOException e) {
                     System.out.println("Fail to close the file");
                     e.printStackTrace();
                 }
             }
         }

     /**.
      * Read a table from file and return it.
      * If the file does not exist, or there is a problem with
      * reading it, an empty table is returned.
      * @param filename the file name
      * @return the loaded hST or empty table
      */
     public static HighScoresTable loadFromFile(File filename) {
         HighScoresTable hST = new HighScoresTable(MAX);
         if (!filename.exists()) {
             return hST;
         }
         try {
                 hST.load(filename);
         } catch (IOException e) {
             System.out.println("Fail");
             e.printStackTrace();
         }
         return hST;
     }

     /**.
      * Check if the score is a new highscore and update the table
      * @param question the question dialog
      * @param score the new score
      *
      * @return true if it is a new highscore, else false
      */
     public boolean updateTable(DialogManager question, int score) {
         int index = getRank(score);
         if (index > this.size) {
             return false;
         }
         String name = question.showQuestionDialog("Name", "A New High Score!\nWhat is your name?", "");
         ScoreInfo highScore = new ScoreInfo(name, score);
         this.add(highScore);
         return true;
     }
  }