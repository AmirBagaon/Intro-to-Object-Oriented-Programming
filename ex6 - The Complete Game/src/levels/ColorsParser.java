package levels;

import java.awt.Color;

/**.
 * Parse color definition and return the specified color.
 * @author PCP-RENT
 *
 */
public class ColorsParser {

    /**.
     * parse color definition and return the specified color.
     * @param colorName name
     * @return the specified color.
     * @throws Exception if name is illegal
     */
    public static java.awt.Color colorFromString(String colorName) throws Exception {
        if (colorName.startsWith("RGB(")) {
            String nums = colorName.substring("RGB(".length(), colorName.length() - 1);
            String[] separate = nums.split(",");

            //Checks if there are R&G&B
            if (separate.length != 3) {
                System.out.println("RGB doesn't contain 3 params");
                throw new Exception("RGB doesn't contain 3 params!");
            }
            int r = Integer.parseInt(separate[0]);
            int g = Integer.parseInt(separate[1]);
            int b = Integer.parseInt(separate[2]);
            return new Color(r, g, b);
        }

        //When colorName has just the name of the color
        colorName = colorName.toLowerCase();
        switch (colorName) {
        case "yellow":
            return Color.yellow;
        case "red":
            return Color.red;
        case "white":
            return Color.white;
        case "orange":
            return Color.orange;
        case "pink":
            return Color.pink;
        case "green":
            return Color.green;
        case "lightgray":
            return Color.lightGray;
        case "gray":
            return Color.gray;
        case "black":
            return Color.black;
        case "blue":
            return Color.blue;
        case "cyan":
            return Color.cyan;
        default:
            throw new Exception("Color name donsn't exist");
        }
    }
 }