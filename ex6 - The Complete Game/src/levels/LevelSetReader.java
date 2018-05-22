package levels;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**.
 * Read the level's set from a text
 *
 *
 */
public class LevelSetReader {

    /**.
     * return a list of level set info, which the information
     * of it comes from the text that was read by the reader
     * @param reader r
     * @return list
     * @throws Exception if read fails
     */
    public static List<LevelSetInfo> fromReader(java.io.Reader reader) throws Exception {
        List<LevelSetInfo> allSets = new ArrayList<LevelSetInfo>();

        LineNumberReader lNR = null;
        LevelSetInfo set = null;
        try {
               lNR = new LineNumberReader(reader);
               String line;
               String key = null;
               String description = null;
               while ((line = lNR.readLine()) != null) {
                   line = line.trim();
                   int num = lNR.getLineNumber();
                   if ((num % 2) == 1) {
                       String[] params = line.split(":");
                       if (params.length != 2) {
                           throw new RuntimeException("The parameters arn't good at:" + line);
                       }
                       key = params[0];
                       description = params[1];
                   } else {
                       String path = line;
                       set = new LevelSetInfo(path, key, description);
                       allSets.add(set);

                       //Reset them
                       path = null;
                       key = null;
                       description = null;
                   }
               }
        } finally {
                if (lNR != null) {
                try {
                    lNR.close();
                } catch (IOException e) {
                    System.out.println("Failed closing the file!");
                }
                }
        }
        return allSets;
    }
}
