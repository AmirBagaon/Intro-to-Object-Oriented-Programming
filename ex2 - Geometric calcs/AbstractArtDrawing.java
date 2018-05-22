import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * @author Amir Bagaon
 */
public class AbstractArtDrawing {
/**.
 * The program draws random lines, and fill the middle of each line
 * with blue circle, and also fill their intersection with red circle
 */
private static final int LENGTH = 400;
private static final int WIDTH = 300;
private static final int AMOUNT = 10;

/**.
 * Create a window with the title "Draw Lines",
 * which is WIDTH pixels wide and LENGTH pixels high,
 * and draw random lines with their middle in blue,
 * and their intersection in red
 */
public void drawRandomLines() {
  GUI gui = new GUI("Draw Lines", WIDTH, LENGTH);
  DrawSurface d = gui.getDrawSurface();
  //Create and draw AMOUNT number of lines
  Line[] arr = new Line[AMOUNT];
  for (int i = 0; i < AMOUNT; i++) {
    arr[i] = createLine();
    drawLine(d, arr[i]);
    drawMiddle(d, arr[i]);
    for (int j = 0; j < i; j++) {
      drawIntersection(d, arr[i], arr[j]);
    }
  }
  gui.show(d);
}
/**.
 * Gets a line and draws it
 *
 * @param d the draw surface
 * @param l the line that will be draw
 */
public void drawLine(DrawSurface d, Line l) {
d.setColor(Color.black);
d.drawLine((int) (l.start().getX()), (int) (l.start().getY()),
(int) (l.end().getX()), (int) (l.end().getY()));
}
/**.
 * Draws the middle point of the line with blue
 *
 * @param d the draw surface
 * @param l the line which contains the middle point
 */
public void drawMiddle(DrawSurface d, Line l) {
d.setColor(Color.blue);
d.fillCircle((int) l.middle().getX(), (int) l.middle().getY(), 2);
}
/**.
 * Draws the intersection between 2 lines with red
 *
 * @param d the draw surface
 * @param l1 the first line of the intersection
 * @param l2 the second line
 */
public void drawIntersection(DrawSurface d, Line l1, Line l2) {
d.setColor(Color.red);
Point p = l1.intersectionWith(l2);
if (p != null) {
d.fillCircle((int) p.getX(), (int) p.getY(), 2);
}
}

/**.
 * Create random line from random points
 *
 * @return l - the random line
 */
public Line createLine() {
Random r = new Random();
Point start = new Point(r.nextInt(WIDTH) + 1, r.nextInt(LENGTH) + 1);
Point end = new Point(r.nextInt(WIDTH) + 1, r.nextInt(LENGTH) + 1);
Line l = new Line(start, end);
return l;
}
/**
 *
 * @param args the parameters from user
 */
 public static void main(String[] args) {
 AbstractArtDrawing art = new AbstractArtDrawing();
   art.drawRandomLines();

 }
}
