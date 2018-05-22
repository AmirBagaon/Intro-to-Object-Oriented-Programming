/**.
 *
 * Negative class. a kind of Unary Expression.
 *
 */
public class Neg extends UnaryExpression implements Expression {

    /**.
     * Constructor from expression
     * @param exp the expression
     */
    public Neg(Expression exp) {
        super(exp);
    }
    /**.
     * Shortcut Constructor from double
     * @param exp the double
     */
    public Neg(double exp) {
        super(exp);
    }
    /**.
     * Shortcut Constructor from string
     * @param exp the expression
     */
    public Neg(String exp) {
        super(exp);
    }
    /**.
     * Calculates the negative of the val
     *
     * @param val the value
     * @return the result
     */
    public double calculate(double val) {
        double negative = (-1) * val;
        return negative;
    }

    /**.
     * Returns a nice string representation of the expression.
     * @return string
     */
    public String toString() {
        return this.strPrint("(-");
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
        Neg p = new Neg(this.getExp().assign(var, expression));
        return p;
    }

    /**.
     * Calculates the derivative of the expression among the given var
     *
     * @param var the variable
     * @return the result
     */
    public Expression differentiate(String var) {
        Expression e = this.getExp().differentiate(var);
        return e;
    }

    /**.
     * Returned a simplified version of the current expression
     *
     * @return string
     */
    public Expression simplify() {
        Expression exp = this.getExp().simplify();
        if (exp.getVariables().isEmpty()) {
            try {
                Num n = new Num(this.evaluate());
                return n;
            } catch (Exception e) {
               int checkStyleFix = 0;
            }
        }
        Neg s = new Neg(exp);
        return s;
    }
}
