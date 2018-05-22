/**.
 *
 * A Pow class, which is kind of Binary Expression
 */
public class Pow extends BinaryExpression implements Expression {
    /**.
     * Contructor from 2 expression
     * @param l the left exp
     * @param r the right exp
     */
    public Pow(Expression l, Expression r) {
        super(l, r);
    }

    /**.
     * Shorcut Constructor from exp and string
     * @param l the left exp
     * @param r the right exp
     */
    public Pow(Expression l, String r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from exp and double.
     * The order is important, so we won't let the
     * superclass change it.
     * @param l the left exp
     * @param r the right exp
     */
    public Pow(Expression l, double r) {
        super(l, r);
        this.setLeft(l);
        this.setRight(r);
        }
    /**.
     * Shorcut Constructor from string and exp
     * @param l the left exp
     * @param r the right exp
     */
    public Pow(String l, Expression r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from double and exp
     * @param l the left exp
     * @param r the right exp
     */
    public Pow(double l, Expression r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from double and string
     * @param l the left exp
     * @param r the right exp
     */
    public Pow(double l, String r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from double and double
     * @param l the left exp
     * @param r the right exp
     */
    public Pow(double l, double r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from string and double.
     * The order is important, so we won't let the
     * superclass change it.
     * @param l the left exp
     * @param r the right exp
     */
    public Pow(String l, double r) {
        super(l, r);
        this.setLeft(l);
        this.setRight(r);
        }
    /**.
     * Shorcut Constructor from string and string.
     * The order is important, so we won't let the
     * superclass change it.
     * @param l the left exp
     * @param r the right exp
     */
    public Pow(String l, String r) {
        super(l, r);
        this.setLeft(l);
        this.setRight(r);
    }

    /**.
     * Calculates: left ^ right
     * @param left the left num
     * @param right the right num
     * @return the result
     */
    public double calculate(double left, double right) {
        return Math.pow(left, right);
    }

    /**.
     * Returns a nice string representation of the expression.
     * @return str
     */
    public String toString() {
        return this.strPrint("^");
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
       Pow p = new Pow(this.getLeft().assign(var, expression),
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
       Log l = new Log(new Var("e"), this.getLeft());
       Mult mRight = new Mult(rDiff, l);

       Div d = new Div(this.getRight(), this.getLeft());
       Mult mLeft = new Mult(lDiff, d);

       Plus p = new Plus(mLeft, mRight);
       return new Mult(this, p);

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
               Pow p = new Pow(left, right);
               Num n = new Num(p.evaluate());
               return n;
           } catch (Exception e) {
         int checkStyleFix;
           }
       }
        //Search for optional special cases
           try {
               if (right.evaluate() == 0) {
                   return new Num(1);
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

           try {
               if (left.evaluate() == 0) {
                   return new Num(0);
               }
           } catch (Exception e) {
          int checkStyleFix;
           }
           try {
               if (left.evaluate() == 1) {
                   return new Num(1);
               }
           } catch (Exception e) {
           int checkStyleFix;
           }
           //Bonus Part
           if (this.getLeft() instanceof Pow) {
              helper(this.getLeft());
           }
           if (this.getLeft() instanceof Mult) {
               try {
               BinaryExpression b = (BinaryExpression) this.getLeft();
               Pow factor = new Pow(b.getLeft(), this.getRight()); //5832
               Expression thePow = helper(b.getRight());
               Mult m3 = new Mult(factor.simplify(), thePow.simplify());
               return m3.simplify();
               /*
               //System.out.println("Factor"+factor);
               Mult m = new Mult(b.getRight(), this.getRight());
               Expression num;
               if(b.getRight() instanceof Pow) {
                   num = ((BinaryExpression) b).getRight();
               } else {
                   num = new Num(1);
               }
               Pow a = new Pow(b.getRight(), num);
               Mult m2 = new Mult (factor.simplify(), a.simplify());
               return m2.simplify();
               */
           } catch (Exception e) {
          int checkStyleFix;
           }
           }

       Pow p = new Pow(left, right);
       return p;
   }
   /**.
    * Get a simple power Expression and calculates it's new power
    * @param e the expression
    * @return the expression
    */
   private Expression helper(Expression e) {
           BinaryExpression b = (BinaryExpression) e;
           Mult m = new Mult(b.getRight(), this.getRight());
           Pow a = new Pow(b.getLeft(), m.simplify());
           return a;
   }

}
