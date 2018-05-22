/**.
 *
 * Cosinus class. a kind of Unary Expression.
 *
 */
public class Cos extends UnaryExpression implements Expression {

    /**.
     * Constructor from expression
     * @param exp the expression
     */
    public Cos(Expression exp) {
        super(exp);
    }
    /**.
     * Shortcut Constructor from double
     * @param exp the double
     */
    public Cos(double exp) {
        super(exp);
    }
    /**.
     * Shortcut Constructor from string
     * @param exp the expression
     */
    public Cos(String exp) {
        super(exp);
    }

    /**.
     * Calculates the cos of the val
     *
     * @param val the value
     * @return the result
     */

    public double calculate(double val) {
        double cosinus = Math.toRadians(val);
        return Math.cos(cosinus);
    }

    /**.
     * Returns a nice string representation of the expression.
     * @return the string
     */
    public String toString() {
        return this.strPrint("cos(");
    }

    /**.
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var the variable
     * @param expression the expression
     * @return p the assign
     */
    public Expression assign(String var, Expression expression) {
        Cos p = new Cos(this.getExp().assign(var, expression));
        return p;
    }
    /**.
     * Calculates the derivative of the expression among the given var
     *
     * @param var the variable
     * @return the result
     */
    public Expression differentiate(String var) {
        Expression sin = new Sin(this.getExp());
        Expression diff = this.getExp().differentiate(var);
        diff = new Neg(diff);
        Expression e = new Mult(diff, sin);
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
        Cos s = new Cos(exp);
        return s;
    }

}
