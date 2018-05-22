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
     * @param start    the point that starts the line
     * @param end the point that ends the line
     */
    public Line(Point start, Point end) {
         //ensure that the 'start' point is more left than 'end' point
         if (start.getX() < end.getX()) {
         this.start = start;
         this.end = end;
         } else {
             this.start = end;
             this.end = start;
         }
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
         if (x1 < x2) {
             this.start = new Point(x1, y1);
             this.end = new Point(x2, y2);
         } else {
             this.start = new Point(x2, y2);
             this.end = new Point(x1, y1);
         }

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
         if ((p.getX() < this.start.getX()) || (p.getX() > this.end.getX())) {
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
      *
      * @param other the other line that might intersect
      * @return the intersection point, or null otherwise
      */
     public Point intersectionWith(Line other) {
         double m1 = this.getSlope();
         double m2 = other.getSlope();
         if (m1 == m2) {
             if ((this.middle().distance(other.middle())
                     == ((double) ((this.length() + other.length()) / 2)))) {
                 if (this.end.equals(other.start())) {
                     return this.end;
                     }
                 if (this.start.equals(other.end())) {
                     return this.start;
                     }
             }
             return null;
         }
         double b1 = this.start.getY() - (this.start.getX() * m1);
         double b2 = other.start().getY() - (other.start().getX() * m2);
         double x = (b2 - b1) / (m1 - m2);
         double y = m1 * x + b1;
         Point p = new Point(x, y);
         if (containsPoint(p) && (other.containsPoint(p))) {
             return p;
             }
         return null;
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

}