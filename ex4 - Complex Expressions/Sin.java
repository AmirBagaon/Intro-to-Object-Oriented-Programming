/**.
 *
 * Sinus class. a kind of Unary Expression.
 *
 */
public class Sin extends UnaryExpression implements Expression {

    /**.
     * Constructor from expression
     * @param exp the expression
     */
    public Sin(Expression exp) {
        super(exp);
    }
    /**.
     * Shortcut Constructor from double
     * @param exp the double
     */
    public Sin(double exp) {
        super(exp);
    }
    /**.
     * Shortcut Constructor from string
     * @param exp the expression
     */
    public Sin(String exp) {
        super(exp);
    }
    /**.
     * Calculates the sinus of the val
     *
     * @param val the value
     * @return the result
     */
    public double calculate(double val) {
        double sinus = Math.toRadians(val);
        return Math.sin(sinus);
    }

    /**.
     * Returns a nice string representation of the expression.
     * @return string
     */
    public String toString() {
        return this.strPrint("sin(");
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
        Sin p = new Sin(this.getExp().assign(var, expression));
        return p;
    }

    /**.
     * Calculates the derivative of the expression among the given var
     *
     * @param var the variable
     * @return the result
     */
    public Expression differentiate(String var) {
        Expression cos = new Cos(this.getExp());
        Expression diff = this.getExp().differentiate(var);
        Expression e = new Mult(diff, cos);
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
            int checkStyleFix;
            }
        }
        Sin s = new Sin(exp);
        return s;
    }


}
