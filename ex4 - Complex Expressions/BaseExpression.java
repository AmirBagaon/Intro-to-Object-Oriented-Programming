import java.util.Map;
import java.util.TreeMap;
/**.
 *
 * Class of Base Expression
 *
 */
public abstract class BaseExpression {


    /**.
     * An abstract method which guide the Binary/Unary Expression to
     * evulate an expression
     * @param assignment the map of the variables
     * @return the exp after being evaluted
     * @throws Exception if there are var's in the expression
     */
    abstract double evaluate(Map<String, Double> assignment) throws Exception;
    /**.
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result.  If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     * @return the exp after being evaluted
     * @throws Exception if there are var's in the expression
     */
    public double evaluate() throws Exception {
        Map<String, Double> assignment = new TreeMap<String, Double>();
        return this.evaluate(assignment);
    }
}
