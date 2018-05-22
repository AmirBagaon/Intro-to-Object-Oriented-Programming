package geometry;
import java.util.List;
/**
 *
 * @author Amir Bagaon
 *
 * Line Class
 */
public class Line {
     // constructors
    private Point start;
    private Point end;
    private double slope;
    private boolean isVertical;
    /**.
     * constructor to build line from 2 points
     *
     * @param start the point that starts the line
     * @param end the point that ends the line
     */
    public Line(Point start, Point end) {
         this.start = start;
         this.end = end;

         if (start.getX() == end.getX()) {
           this.isVertical = true;
         } else {
            this.isVertical = false;
         }
         if (this.isVertical) {
             this.slope = 0;
         } else {
             this.slope = (double) ((start.getY() - end.getY()) / (start.getX() - end.getX()));
         }
     }
    /**.
     * construct line from 4 points
     *
     * @param x1 First point x's coordinate
     * @param y1 First point y's coordinate
     * @param x2 Second point x's coordinate
     * @param y2 Second point y's coordinate
     */
     public Line(double x1, double y1, double x2, double y2) {
             this.start = new Point(x1, y1);
             this.end = new Point(x2, y2);

         if (start.getX() == end.getX()) {
    this.isVertical = true;
    } else {
    this.isVertical = false;
         }
         if (this.isVertical) {
             this.slope = 0;
         } else {
             this.slope = (double) ((this.start.getY() - this.end.getY()) / (start.getX() - end.getX()));
         }
     }
    /**.
      * Return the length of the line
      * @return the length
      */
     public double length() {
         return start.distance(end);
     }
/**.
 * Returns the middle point of the line
 *
 * @return p - the middle point
 */
     public Point middle() {
         double x = (this.start.getX() + this.end.getX()) / 2;
         double y = (this.start.getY() + this.end.getY()) / 2;
         Point p = new Point(x, y);
     return p;
     }
     /**.
      * Returns the start point of the line
      * @return start
      */
     public Point start() {
         return start;
     }
     /**.
     * Returns the end point of the line
     * @return end
     */
     public Point end() {
         return end;
     }
     /**.
      * Return if the line is vertical or not
      * @return true or false
      */
     public boolean isVertical() {
        return this.isVertical;
     }

     /**.
      * Returns true if the lines intersect, false otherwise
      *
      * @param other the line we check
      * @return true if the lines intersect, false otherwise
      */
     public boolean isIntersecting(Line other) {

         if (intersectionWith(other) != null) {
         return true;
         }
        return false;
     }
     /**.
      * Returns true if the line contains the point
      *
      * @param p the point that we check
      * @return true if the line contains the point, otherwise false
      */
     public boolean containsPoint(Point p) {
         if (p == null) {
             return false;
         }
         double minX = Math.min(this.start.getX(), this.end.getX());
         double maxX = Math.max(this.start.getX(), this.end.getX());

         if ((p.getX() < minX) || (p.getX() > maxX)) {
                 return false;
         }
         double minY = Math.min(this.start.getY(), this.end.getY());
         double maxY = Math.max(this.start.getY(), this.end.getY());
         if ((p.getY() < minY) || (p.getY() > maxY)) {
            return false;
         }
         return true;
     }
     /**.
      * Returns the intersection point if the lines intersect,
      * and null otherwise.
      * The calculation is based on denominator.
      * Based on answer showed in: http://stackoverflow.com/
      * questions/16314069/calculation-of-intersections-between-line-segments
      *
      * @param other the other line that might intersect
      * @return the intersection point, or null otherwise
      */
         public Point intersectionWith(Line other) {
             //Getting line 1 and line 2 x's
             double l1x1 = this.start().getX();
             double l1x2 = this.end().getX();
             double l2x1 = other.start().getX();
             double l2x2 = other.end().getX();

            //Getting line 1 and line 2 y's
             double l1y1 = (this.start().getY());
             double l1y2 = (this.end().getY());
             double l2y1 = (other.start().getY());
             double l2y2 = (other.end().getY());

             //Calculate denominator
             double denominator = (l1x1 - l1x2) * (l2y1 - l2y2) - (l1y1 - l1y2) * (l2x1 - l2x2);

             //If line are parallel
             if (denominator == 0) {
             return null;
             }

             //Calculating intersection point
             double newX = ((l2x1 - l2x2) * (l1x1 * l1y2 - l1y1 * l1x2)
                     - (l1x1 - l1x2) * (l2x1 * l2y2 - l2y1 * l2x2)) / denominator;
             double newY = ((l2y1 - l2y2) * (l1x1 * l1y2 - l1y1 * l1x2)
                     - (l1y1 - l1y2) * (l2x1 * l2y2 - l2y1 * l2x2)) / denominator;
             Point p = new Point(newX, newY);
             if (!(this.containsPoint(p)) || !(other.containsPoint(p))) {
                 return null;
             }
             return p;
         }
     /**.
      * check if 2 lines are equals
      *
      * @param other - other line that will be compared
      * @return true if the lines are equal, false otherwise
      */
     public boolean equals(Line other) {
         if ((this.start.equals(other.start()) && this.end.equals(other.end()))) {
             return true;
             }
         return false;
     }
     /**.
      * Returns the slope of the line
      * @return this.slope - the slope of the line
      */
     public double getSlope() {
         return this.slope;
     }
  // If this line does not intersect with the rectangle, return null.
     // Otherwise, return the closest intersection point to the
     // start of the line.

     /**.
      * If this line does not intersect with the rectangle, return null.
      * Otherwise, return the closest intersection point to the
      * start of the line.
      *
      * @param rect - The rectangle that might be intersection with
      * @return point that it is the closet intersection
      */
     public Point closestIntersectionToStartOfLine(Rectangle rect) {
         List<Point> l = rect.intersectionPoints(this);
         if (l.size() == 0) {
             return null;
         }
         Point p = l.get(0);
         for (int i = 1; i < l.size(); i++) {
             if (p.distance(this.start) > l.get(i).distance(this.start)) {
                 p = l.get(i);
             }
         }
         return p;
     }

}