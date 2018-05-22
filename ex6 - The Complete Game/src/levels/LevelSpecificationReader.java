package levels;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import others.Block;
import others.LevelInformation;
import others.Velocity;
/**.
 * Read the level information from a text
 * @author PCP-RENT
 *
 */
public class LevelSpecificationReader {

    private int startX = 0;
    private int currentX = 0;
    private int startY = 0;
    private int currentY = 0;
    private int rowHeight = 0;
    private int currentRow = 0;
    private BlocksFromSymbolsFactory bFactory;
    private boolean appearStartX = false;
    private boolean appearRowHeight = false;
    private boolean appearBlockDefinitions = false;
    private boolean appearStartY = false;


    /**.
     * Read the levels's information from a text and returns a list of level information
     * @param reader r
     * @return list of level information
     * @throws Exception if read fails
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) throws Exception {

        List<LevelInformation> allLvls = new ArrayList<LevelInformation>();

        BufferedReader is = null;
            try {
                is = new BufferedReader((reader));
                String line;

            //Parameters for the TextLevel
            TextLevel lvl = null;
            boolean blocksCharsMode = false;
            while ((line = is.readLine()) != null) {

                //omitting leading and trailing whitespace
                line = line.trim();

                if (line.startsWith("#") || line.equals("")) {
                    continue;
                }
                //System.out.println(line);
                //System.out.println("Mode:" + blocksCharsMode);

            switch (line) {
            //Check for new level
            case "START_LEVEL":
                lvl = new TextLevel();
                this.bFactory = new BlocksFromSymbolsFactory();
                break;

          //For finish lvl, add the lvl to the list, and reset all parameters
            case "END_LEVEL":
                checkBlockParams();
                checkAllParams(lvl);
                allLvls.add(lvl);

                //reset all params
                this.currentY = 0;
                this.currentX = 0;
                this.currentRow = 0;
                this.startX = 0;
                this.startY = 0;
                this.rowHeight = 0;
                lvl = null;
                this.bFactory = null;
                this.appearBlockDefinitions = false;
                this.appearRowHeight = false;
                this.appearStartX = false;
                this.appearStartY = false;
                break;

            //For blocks part
            case "START_BLOCKS":
                blocksCharsMode = true;
                break;
            case "END_BLOCKS":
                blocksCharsMode = false;
                //System.out.println("MODE IS OFF");
                break;

              //For others:
            default:
                //When reading the chars of the blocks
                if (blocksCharsMode) {
                    if (!this.appearBlockDefinitions) {
                        throw new RuntimeException("you didn't enter blockDefinitions path");
                    }
                    this.currentY = this.startY + (this.rowHeight * this.currentRow);
                    this.currentX = this.startX;
                    for (int i = 0; i < line.length(); i++) {
                        String symb = String.valueOf(line.charAt(i));
                        if (bFactory.isBlockSymbol(symb)) {
                            Block block = bFactory.getBlock(symb, this.currentX, this.currentY);
                            lvl.addBlock(block);
                            this.currentX  += (int) block.getCollisionRectangle().getWidth();
                        } else if (bFactory.isSpaceSymbol(symb)) {
                            int width = bFactory.getSpaceWidth(symb);
                            this.currentX += width;
                        } else {
                            throw new RuntimeException("Error in symbol" + symb);
                        }
                    }
                    this.currentRow++;
                    continue;
                }

                levelBuilder(line, lvl);
              break;
            //End switch
            }
          //End while
            }
            } catch (IOException e) {
            System.out.println("Something went wrong while reading!");
            e.printStackTrace();
            } finally {
            if (is != null) {
            try {
            is.close();
            } catch (IOException e) {
            System.out.println("Failed closing the file!");
                }
                 }
                 }
            return allLvls;
             }
    /**.
     * Gather the blocks-part in the level reader
     * @param key k
     * @param value v
     * @return true if one of these paramters has been read
     */
    public boolean blockBuilder(String key, String value) {
        switch (key) {
        case "blocks_start_x":
            if (Integer.parseInt(value) < 0) {
                throw new RuntimeException("blocks_start_x must be >= 0");
            }
            this.appearStartX = true;
            this.startX = Integer.parseInt(value);
            return true;
        case "blocks_start_y":
            if (Integer.parseInt(value) < 0) {
                throw new RuntimeException("blocks_start_y must be >= 0");
            }
            this.appearStartY = true;
            this.startY = Integer.parseInt(value);
            return true;
        case "row_height":
            if (Integer.parseInt(value) <= 0) {
                throw new RuntimeException("row_height must be positive");
            }
            this.appearRowHeight = true;
            this.rowHeight = Integer.parseInt(value);
            return true;
        case "block_definitions":
            this.appearBlockDefinitions = true;
            try {
                 InputStreamReader iSR = new InputStreamReader(
                         ClassLoader.getSystemClassLoader().getResourceAsStream(value));
                 this.bFactory = BlocksDefinitionReader.fromReader(iSR);
                 return true;

            } catch (Exception e) {
                e.printStackTrace();
            }
            default:
                return false;
        }
    }

    /**.
     * Gather the parameters the relevant to the level itself, such as paddle, and handle them.
     * @param line line
     * @param lvl text level
     * @throws Exception if color parser fails
     */
    public void levelBuilder(String line, TextLevel lvl) throws Exception {
        String[] separate = line.split(":");
        if (separate.length != 2) {
            throw new RuntimeException("Params are not good at line: "  + line);
        }
        String key = separate[0];
        String value = separate[1];
        if (blockBuilder(key, value)) {
            return;
        }
        switch (key) {
        case "level_name":
            lvl.setLevelName(value);
            break;
        case "paddle_speed":
            lvl.setPaddleSpeed(Integer.parseInt(value));
            break;
        case "paddle_width":
            lvl.setPaddleWidth(Integer.parseInt(value));
            break;
        case "num_blocks":
            lvl.setNumberOfBlocksToRemove(Integer.parseInt(value));
            break;

        case "background":
            //Image
            if (value.startsWith("image(")) {
                if (value.endsWith(")")) {
                    Image img = null;
                    String imgName = value.substring("image(".length(), value.length() - 1);
                    try {
                        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(imgName);
                        img = ImageIO.read(is);
                        BackGround bg = new BackGround(img);
                        lvl.setBackground(bg);
                    } catch (Exception e) {
                        throw new RuntimeException("Can't load background image:"  + imgName);
                    }
                }
            } else {
            //COLOR
            if (value.startsWith("color(")) {
                if (value.endsWith(")")) {
                    String colorName = value.substring("color(".length(), value.length() - 1);
                    Color color = ColorsParser.colorFromString(colorName);
                    BackGround bg = new BackGround(color);
                    lvl.setBackground(bg);
                }
            }
            }
        break;
        case "ball_velocities":
            String[] velocities = value.split(" ");
            if (velocities == null) {
             System.out.println("It's Null");
            } else {
                System.out.println("Size:" + velocities.length);
            }
            for (int i = 0; i < velocities.length; i++) {
                String[] devided = velocities[i].split(",");
                if (devided.length != 2) {
                    throw new Exception("Velocity needs 2 parametrs");
                }
                double angle = Double.parseDouble(devided[0]);
                double speed = Double.parseDouble(devided[1]);

                if (speed <= 0) {
                    throw new Exception("Velocity needs 2 parametrs");
                }
                lvl.addInitialBallVelocities(Velocity.fromAngleAndSpeed(angle, speed));
            }
            lvl.setNnumberOfBalls(velocities.length);
            break;
         default:
            throw new Exception("Illegal key:"  +  key);

    }
    }
    /**.
     * Check that all level params was appeared
     * @param lvl the level
     */
    public void checkAllParams(LevelInformation lvl) {
        if (lvl.initialBallVelocities() == null || lvl.initialBallVelocities().size() == 0) {
            throw new RuntimeException("you didn't enter ball velocities");
         }
        if (lvl.numberOfBalls() == 0) {
           throw new RuntimeException("number of balls must be positive");
        }
        if (lvl.levelName() == null) {
           throw new RuntimeException("no level name");
        }
        if (lvl.getBackground() == null) {
            throw new RuntimeException("no level background");
         }
        if (lvl.blocks() == null || lvl.blocks().size() == 0) {
            throw new RuntimeException("you didn't enter blocks in the level");
         }
        if (lvl.numberOfBlocksToRemove() == 0) {
            throw new RuntimeException("you didn't enter blocks to remove in the level");
        }
        if (lvl.paddleSpeed() == 0) {
            throw new RuntimeException("you didn't enter paddleSpeed");
        }
        if (lvl.paddleWidth() == 0) {
            throw new RuntimeException("you didn't enter paddleWidth");
        }
    }
    /**.
     * Check that all block params was appearad
     */
    public void checkBlockParams() {
     if (!this.appearRowHeight) {
         throw new RuntimeException("you didn't enter row_height");
     }
     if (!this.appearStartY) {
         throw new RuntimeException("you didn't enter block_start_y");
     }
     if (!this.appearStartX) {
         throw new RuntimeException("you didn't enter block_start_x");
     }
    }
}


