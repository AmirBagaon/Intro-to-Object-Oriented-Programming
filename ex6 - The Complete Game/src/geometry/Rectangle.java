package geometry;
import java.util.List;
import java.util.ArrayList;
/**.
 *
 * A class of Rectangle
 *
 */
public class Rectangle {
    private Line up;
    private Line bottom;
    private Line left;
    private Line right;
    private Point upperLeft;
    private double width;
    private double height;

    /**.
     * Construct Rectangle
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft - The upper left point of the rectangle
     * @param width the width
     * @param height the height
     */
       public Rectangle(Point upperLeft, double width, double height) {
           //basic information
           this.upperLeft = upperLeft;
           this.width = width;
           this.height = height;

           //Also create Rect's Lines
           Point upRight = new Point(upperLeft.getX() + width, upperLeft.getY());
           Point botLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
           Point botRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
           this.up = new Line(upperLeft, upRight);
           this.bottom = new Line(botLeft, botRight);
           this.left = new Line(upperLeft, botLeft);
           this.right = new Line(upRight, botRight);
       }

       /**.
        * Return a (possibly empty) List of intersection points
        * with the specified line
        *
        * @param line - a line
        * @return list of intersection points
        */
       public List<Point> intersectionPoints(Line line) {
           List<Point> list = new ArrayList<Point>();
           if (line.isIntersecting(up)) {
               list.add(line.intersectionWith(up));
           }
           if (line.isIntersecting(bottom)) {
               list.add(line.intersectionWith(bottom));
           }
           if (line.isIntersecting(right)) {
               list.add(line.intersectionWith(right));
           }
           if (line.isIntersecting(left)) {
               list.add(line.intersectionWith(left));
           }
           return list;
       }

       /**.
        * Returns the width of the rectangle
        * @return the width
        */
       public double getWidth() {
           return this.width;
       }

       /**.
        * Returns the height of the rectangle
        * @return the height
        */
       public double getHeight() {
           return this.height;
       }

       /**.
        * Returns the upper-left point of the rectangle
        * @return upperLeft
        */
       public Point getUpperLeft() {
           return this.upperLeft;
       }
       /**.
        * Returns the upper line
        * @return the upper line
        */
       public Line getTop() {
           return this.up;
       }
       /**.
        * Returns the bottom line
        * @return the bottom line
        */
       public Line getBot() {
           return this.bottom;
       }
       /**.
        * Returns the left line
        * @return the left line
        */

       public Line getLeft() {
           return this.left;
       }
       /**.
        * Returns the right line
        * @return the right line
        */
       public Line getRight() {
           return this.right;
       }
       /**.
        * Check if the rectangle has a point in it. If true,
        * it calls whichLine(), which return him the closest rib
        * to the point.
        * If false, return 0.
        * @param p the point
        * @return number between 0-4
        */
       public int hasPoint(Point p) {
        double x = p.getX();
        double y = p.getY();
        if ((this.upperLeft.getX() <= x) && (this.right.start().getX() >= x)) {
            if ((this.upperLeft.getY() <= y) && (this.right.end().getY() >= y)) {
                return this.whichLine(p);
            }
        }
        return 0;
       }
       /**.
        * Return the closest rib to the point.
        * Returns 1 if it is the left rib
        * Returns 2 if it is the right rib
        * Returns 3 if it is the bottom rib
        * Returns 4 if it is the upper rib
        * @param p the point
        * @return number between 1-4
        */
       public int whichLine(Point p) {
           int i = 1;
           double distance = p.getX() - this.upperLeft.getX();
           if ((right.start().getX() - p.getX()) < distance) {
               i = 2;
               distance = right.start().getX() - p.getX();
           }
           if ((bottom.start().getY() - p.getY()) < distance) {
               i = 3;
               distance = (bottom.start().getY() - p.getY());
           }
           if ((p.getY() - up.start().getY()) < distance) {
               i = 4;
               distance = p.getY() - up.start().getY();
           }
       return i;
    }
}