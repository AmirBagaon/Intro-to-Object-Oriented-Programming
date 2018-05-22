package levels;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**.
 * A class that translate the key (which comes from the blockmap in the BlockReader)
 * to the correct decorator
 */
public class DecoratorsManager {

    /**.
     * Creat the block
     * @param b the block that will be decorated
     * @param key the key
     * @param value v
     * @return the new block
     * @throws Exception if color parser doesn't work
     */
    public BlockCreator create(BlockCreator b, String key, String value) throws Exception {

        //In case of fill image/color
        if (key.startsWith("fill")) {
            int health;
            //In case of fill - k
            if (key.startsWith("fill-")) {
                String str = key.substring("fill-".length());
                if ((str != null) && str.length() > 0) {
                    health = Integer.parseInt(str);
                    if (health <= 0) {
                        throw new RuntimeException("The param: "  +  key  +  " must be positive hit_points");
                    }
                } else {
                    throw new RuntimeException("The param: "  +  key  +  " isn't valid");
                }
            } else {
                health = 0;
            }

          //Case of Image
            if (value.startsWith("image(")) {
                if (value.endsWith(")")) {
                    Image img = null;
                    String imgName = value.substring("image(".length(), value.length() - 1);
                    try {
                        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(imgName);
                        img = ImageIO.read(is);
                        return new ImageBlockDecorator(img, health, b);
                    } catch (IOException e) {
                        System.out.println("Can't load fill - block image:"  + imgName);
                    }
                }
            }

           //Case of color
            if (value.startsWith("color(")) {
                if (value.endsWith(")")) {
                    String colorName = value.substring("color(".length(), value.length() - 1);
                    Color color = ColorsParser.colorFromString(colorName);
                    return new ColorBlockDecorator(b, color, health);
                    }
            }
            return null;
        }
        if (key.equals("stroke")) {
        if (value.startsWith("color(")) {
            if (value.endsWith(")")) {
                String colorName = value.substring("color(".length(), value.length() - 1);
                return new StrokeBlockDecorator(colorName, b);
        }
        }
        }


        //Other cases
        int val = Integer.parseInt(value);
        if (val <= 0) {
            throw new RuntimeException(
                    "The key: " + key + ", with the param: " +  value  +  " must be positive");
        }
        switch (key) {
        case "width":
            return new WidthBlockDecorator(val, b);
        case "hit_points":
            return new HealthBlockDecorator(val, b);
        case "height":
            return new HeightBlockDecorator(val, b);
        case "stroke":
            return new StrokeBlockDecorator(value, b);
            default:
                throw new RuntimeException("The param: "  +  value  +  " isn't valid");
        }
    }
}
