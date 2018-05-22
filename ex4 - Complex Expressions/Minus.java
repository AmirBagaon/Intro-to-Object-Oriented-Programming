/**.
 *
 * A Minus class, which is kind of Binary Expression
 */
public class Minus extends BinaryExpression implements Expression {

    /**.
     * Contructor from 2 expression
     * @param l the left exp
     * @param r the right exp
     */
    public Minus(Expression l, Expression r) {
        super(l, r);
    }
    /**.
     * Shorcut Constructor from exp and string
     * @param l the left exp
     * @param r the right exp
     */
    public Minus(Expression l, String r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from exp and double
     * @param l the left exp
     * @param r the right exp
     */
    public Minus(Expression l, double r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from string and exp
     * @param l the left exp
     * @param r the right exp
     */
    public Minus(String l, Expression r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from double and exp
     * @param l the left exp
     * @param r the right exp
     */
    public Minus(double l, Expression r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from double and string
     * @param l the left exp
     * @param r the right exp
     */
    public Minus(double l, String r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from double and double
     * @param l the left exp
     * @param r the right exp
     */
    public Minus(double l, double r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from string and double
     * @param l the left exp
     * @param r the right exp
     */
    public Minus(String l, double r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from string and string
     * @param l the left exp
     * @param r the right exp
     */
    public Minus(String l, String r) {
        super(l, r);
    }

    /**.
     * Calculates: left - right
     * @param left the left num
     * @param right the right num
     * @return the result
     */
    public double calculate(double left, double right) {
        return left - right;
    }


    /**.
     * Returns a nice string representation of the expression.
     * @return str
     */
    public String toString() {
        return this.strPrint(" - ");
    }

    /**.
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var the variable
     * @param expression the expression
     * @return the exp
     */
   public Expression assign(String var, Expression expression) {
       Minus p = new Minus(this.getLeft().assign(var, expression),
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
       Expression e = new Minus(lDiff, rDiff);
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
               Minus p = new Minus(left, right);
               Num n = new Num(p.evaluate());
               return n;
           } catch (Exception e) {
              int checkStyleFix;
           }
       }
     //Search for optional special cases
       if (right.toString().equals(left.toString())) {
           return new Num(0);
           }
       try {
           if (right.evaluate() == 0) {
               return left;
           }
       } catch (Exception e) {
          int checkStyleFix;
       }
       try {
           if (left.evaluate() == 0) {
               Neg neg = new Neg(right);
               return neg;
           }
       } catch (Exception e) {
           int checkStyleFix;

       }
       try {
           if (right == left) {
               return new Num(0);
           }
       } catch (Exception e) {
         int checkStyleFix;
       }

       //Bonus Part
       if (left instanceof Mult) {
            if (right instanceof Mult) {
                 BinaryExpression b1 = (BinaryExpression) left;
                 BinaryExpression b2 = (BinaryExpression) right;
                 if (b1.getRight().toString().equals(b2.getRight().toString())) {
                      Minus p = new Minus(b1.getLeft(), b2.getLeft());
                      return new Mult(p.simplify(), b1.getRight());
                 }
            }
       }

     Minus p = new Minus(left, right);
     return p;




   }

}
