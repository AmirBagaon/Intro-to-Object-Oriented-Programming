import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**.
*
* Unary Expression class
* Contains 1 expression
*
*/
public abstract class UnaryExpression extends BaseExpression {

    private Expression exp;

    /**.
     * Constructor from expression
     * @param e exp
     */
    public UnaryExpression(Expression e) {
        this.exp = e;
    }
    /**.
     * Constructor from string
     * @param e str
     */
    public UnaryExpression(String e) {
        this.exp = new Var(e);
    }
    /**.
     * Constructor from double
     * @param e double
     */
    public UnaryExpression(double e) {
        this.exp = new Num(e);
    }

    /**.
     * Return the exp
     * @return the exp
     */
    public Expression getExp() {
        return this.exp;
    }

    /**.
     *  Creating a list which would contain the variable
     *  put the first in a Hashset in order to have each
     *  variable only once.
     *  By the idea of: http://stackoverflow.com/
     *  questions/15205041/list-that-can-only-contain-a-value-once
     * @return the list with the vars
     */
    public List<String> getVariables() {
        //creating a list that would get all the variables.
        List<String> l = new ArrayList<String>();
        //Using Hashset in order to have each var only once
        Set<String> s = new HashSet<>();
        s.addAll(this.exp.getVariables());
        l.addAll(s);
        return l;
    }
    /**
     * An abstract method which guide each of the unary expression
     * to calculate and evaluate the expression.
     * The calculate method makes the calculation more simple and more orginized.
     * @param val - the exp
     * @throws Exception if there are var's in the expression
     * @return the evaluated number
     */
    abstract double calculate(double val) throws Exception;

    /**.
     * Evaluates the Expression and return the evaluated num
     * @param assignment the map of the vars
     * @assignment the var's values
     * @throws Exception if there are var's in the exp which isn't in the map
     * @return the evaluated number
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double val = this.exp.evaluate(assignment);
        double c = calculate(val);
        return c;
    }
    /**.
     * Print the Expression:
     * First the left exp, then the operator, the the right exp
     * @param str the operator
     * @return the whole expression
     */
    public String strPrint(String str) {
        String s1 = this.exp.toString();

        return (str + s1 + ")");
    }

}
