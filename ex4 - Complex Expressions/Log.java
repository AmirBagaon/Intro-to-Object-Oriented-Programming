/**.
 *
 * A Log class, which is kind of Binary Expression
 */
public class Log extends BinaryExpression implements Expression {

    /**.
     * Contructor from 2 expression
     * @param l the left exp
     * @param r the right exp
     */
    public Log(Expression l, Expression r) {
        super(l, r);
    }

    /**.
     * Shorcut Constructor from exp and string
     * @param l the left exp
     * @param r the right exp
     */
    public Log(Expression l, String r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from exp and double.
     * The order is important, so we won't let the
     * superclass change it.
     * @param l the left exp
     * @param r the right exp
     */
    public Log(Expression l, double r) {
        super(l, r);
        this.setLeft(l);
        this.setRight(r);
        }
    /**.
     * Shorcut Constructor from string and exp
     * @param l the left exp
     * @param r the right exp
     */
    public Log(String l, Expression r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from double and exp
     * @param l the left exp
     * @param r the right exp
     */
    public Log(double l, Expression r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from double and string
     * @param l the left exp
     * @param r the right exp
     */
    public Log(double l, String r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from double and double
     * @param l the left exp
     * @param r the right exp
     */
    public Log(double l, double r) {
        super(l, r);
        }
    /**.
     * Shorcut Constructor from string and double.
     * The order is important, so we won't let the
     * superclass change it.
     * @param l the left exp
     * @param r the right exp
     */
    public Log(String l, double r) {
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
    public Log(String l, String r) {
        super(l, r);
        this.setLeft(l);
        this.setRight(r);
    }

    /**.
     * Calculates: Log left\right (left is the base)
     * @param left the left num
     * @param right the right num
     * @throws Exception if the base <= 0
     * @return the result
     */
    @Override
    public double calculate(double left, double right) throws Exception {
        //'Left' is the base
        if (left <= 0) {
            throw new Exception("Log Error. The base can't be <= 0");
        }
        return Math.log(right) / Math.log(left);
    }

    /**.
     * Returns a nice string representation of the expression.
     * @return str
     */
    public String toString() {
        return "Log" + this.strPrint(", ");
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
       Log p = new Log(this.getLeft().assign(var, expression),
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

       Log lnBase = new Log("e", this.getLeft());
       //In case no var in the base
       if (!(this.getLeft().getVariables().contains(var))) {
       Mult m = new Mult(lnBase, this.getRight());
       Expression rDiff = this.getRight().differentiate(var);
       Expression e = new Div(rDiff, m);
       return e;
       }
       //Calculates log with possible-var base
       Log lnRight = new Log("e", this.getRight());
       Div d = new Div(lnRight, lnBase);
       return d.differentiate(var);
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
               Log p = new Log(left, right);
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


       Log p = new Log(left, right);
       return p;
   }


}
