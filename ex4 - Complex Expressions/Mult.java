/**.
 *
 * A Mult class, which is kind of Binary Expression
 */
public class Mult extends BinaryExpression implements Expression {

    /**.
     * Contructor from 2 expression
     * @param l the left exp
     * @param r the right exp
     */
    public Mult(Expression l, Expression r) {
        super(l, r);
    }
    /**.
     * Shorcut Constructor from exp and string
     * @param l the left exp
     * @param r the right exp
     */
    public Mult(Expression l, String r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from exp and double
     * @param l the left exp
     * @param r the right exp
     */
    public Mult(Expression l, double r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from string and exp
     * @param l the left exp
     * @param r the right exp
     */
    public Mult(String l, Expression r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from double and exp
     * @param l the left exp
     * @param r the right exp
     */
    public Mult(double l, Expression r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from double and string
     * @param l the left exp
     * @param r the right exp
     */
    public Mult(double l, String r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from double and double
     * @param l the left exp
     * @param r the right exp
     */
    public Mult(double l, double r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from string and double
     * @param l the left exp
     * @param r the right exp
     */
    public Mult(String l, double r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from string and string
     * @param l the left exp
     * @param r the right exp
     */
    public Mult(String l, String r) {
        super(l, r);
    }

    /**.
     * Calculates: left * right
     * @param left the left num
     * @param right the right num
     * @return the result
     */
    public double calculate(double left, double right) {
        return left * right;
    }


    /**.
     * Returns a nice string representation of the expression.
     * @return str
     */
    public String toString() {
        return this.strPrint(" * ");
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
       Mult p = new Mult(this.getLeft().assign(var, expression),
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
       Expression e = new Plus(lDiff, rDiff);
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
               Mult p = new Mult(left, right);
               Num n = new Num(p.evaluate());
               return n;
           } catch (Exception e) {
           int checkStyleFix = 0;
           }
       }

     //Search for optional special cases
       try {
           if (right.evaluate() == 0) {
               return new Num(0);
           }
       } catch (Exception e) {
    int checkStyleFix = 0;
       }

       try {
           if (left.evaluate() == 0) {
               return new Num(0);
           }
       } catch (Exception e) {
    int checkStyleFix = 0;
       }
      try {
           if (right.evaluate() == 1) {
               return left;
           }
       } catch (Exception e) {
         int checkStyleFix = 0;
       }

       try {
           if (left.evaluate() == 1) {
               return right;
           }
       } catch (Exception e) {
          int checkStyleFix = 0;
       }

       //Bonus Part
       if (left instanceof Mult) {
           if (right instanceof Mult) {
               BinaryExpression b1 = (BinaryExpression) left;
               BinaryExpression b2 = (BinaryExpression) right;
                   Mult factor = new Mult(b1.getLeft(), b2.getLeft());
               if (b1.getRight().toString().equals(b2.getRight().toString())) {
                   Pow pow = new Pow(b1.getRight(), 2);
                   Mult p2 = new Mult(factor.simplify(), pow.simplify());
                   return p2;
               } else {
                   if (b1.getVariables().size() == 1) {
                       if (b2.getVariables().size() == 1) {
                           if (b1.getVariables().containsAll(b2.getVariables())) {
                               Expression e1 = b1.getRight();
                               Expression e2 = b2.getRight();
                               if (e1 instanceof Var) {
                                   e1 = new Pow(e1, 1);
                               }
                               if (e2 instanceof Var) {
                                   e2 = new Pow(e2, 1);
                               }
                               BinaryExpression b3 = (BinaryExpression) e1;
                               BinaryExpression b4 = (BinaryExpression) e2;
                               Plus m2 = new Plus(b3.getRight(), b4.getRight());
                               return new Mult(factor.simplify(), new Pow(b3.getLeft(), m2.simplify()));
                           }
                       }
                   }
               }
           }
       }

       Mult p = new Mult(left, right);
       return p;
    }

}

