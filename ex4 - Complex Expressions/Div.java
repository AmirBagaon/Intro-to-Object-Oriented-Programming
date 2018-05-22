/**.
 *
 * A Div class, which is kind of Binary Expression
 */
public class Div extends BinaryExpression implements Expression {

    /**.
     * Contructor from 2 expression
     * @param l the left exp
     * @param r the right exp
     */
    public Div(Expression l, Expression r) {
        super(l, r);
    }

    /**.
     * Shorcut Constructor from exp and string
     * @param l the left exp
     * @param r the right exp
     */
    public Div(Expression l, String r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from exp and double
     * The order is important, so we won't let the
     * superclass change it.
     * @param l the left exp
     * @param r the right exp
     */
    public Div(Expression l, double r) {
        super(l, r);
        this.setLeft(l);
        this.setRight(r);
        }
    /**.
     * Shorcut Constructor from string and exp
     * @param l the left exp
     * @param r the right exp
     */
    public Div(String l, Expression r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from double and exp
     * @param l the left exp
     * @param r the right exp
     */
    public Div(double l, Expression r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from double and string
     * @param l the left exp
     * @param r the right exp
     */
    public Div(double l, String r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from double and double
     * @param l the left exp
     * @param r the right exp
     */
    public Div(double l, double r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from string and double
     * The order is important, so we won't let the
     * superclass change it.
     * @param l the left exp
     * @param r the right exp
     */
    public Div(String l, double r) {
        super(l, r);
        this.setLeft(l);
        this.setRight(r);
        }
    /**.
     * Shorcut Constructor from string and string
     * The order is important, so we won't let the
     * superclass change it.
     * @param l the left exp
     * @param r the right exp
     */
    public Div(String l, String r) {
        super(l, r);
        this.setLeft(l);
        this.setRight(r);
    }

    /**.
     * Calculates: left / right
     * @param left the left num
     * @param right the right num
     * @return the result
     */
    public double calculate(double left, double right) {
        return left / right;
    }

    /**.
     * Returns a nice string representation of the expression.
     * @return str
     */
    public String toString() {
        return this.strPrint(" / ");
    }

    /**.
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var the variable
     * @param expression the expression
     * @return the assigned exp
     */
   public Expression assign(String var, Expression expression) {
       Div p = new Div(this.getLeft().assign(var, expression),
               this.getRight().assign(var, expression));
       return p;
   }

   /**.
    * Calculates the derivative of the expression among the given var
    *
    * @param var the variable
    * @return the result
    */
   public Expression differentiate(String var) {
       Expression lDiff = this.getLeft().differentiate(var);
       Expression rDiff = this.getRight().differentiate(var);
       lDiff = new Mult(lDiff, this.getRight());
       rDiff = new Mult(rDiff, this.getLeft());
       Expression numerator = new Minus(lDiff, rDiff);
       Expression denominator = new Pow(this.getRight(), 2);
       Expression e = new Div(numerator, denominator);
       return e;
   }

   /**.
    * Returned a simplified version of the current expression
    *
    * @return string
    */
   public Expression simplify() {
       Expression left, right;
       left = this.getLeft().simplify();
       right = this.getRight().simplify();
       //In case no vars in the expression
       if (left.getVariables().isEmpty()
               && right.getVariables().isEmpty()) {
           try {
               Div p = new Div(left, right);
               Num n = new Num(p.evaluate());
               return n;
           } catch (Exception e) {
             int checkStyleFix;
           }
       }

     //Search for optional special cases
       if (right.toString().equals(left.toString())) {
           return new Num(1);
           }

       try {
           if (right.evaluate() == 0) {
               throw new RuntimeException("Can't be devide by 0");
           }
       } catch (Exception e) {
         int checkStyleFix;
       }

       try {
           if (left.evaluate() == 0) {
               return new Num(0);
           }
       } catch (Exception e) {
         int checkStyleFix;
       }
      try {
           if (right.evaluate() == 1) {
               return left;
           }
       } catch (Exception e) {
         int checkStyleFix;
       }

       Div p = new Div(left, right);
       return p;
    }

}
