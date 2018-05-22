import java.util.List;
import java.util.Map;

/**.
 *
 * An interface of Expression
 *
 */
public interface Expression {
   /**.
     * Evaluate the expression using the variable values provided
     *  in the assignment, and return the result.  If the expression
     * contains a variable which is not in the assignment, an exception
     *  is thrown.
     *
     * @param assignment the map
     * @return the evaluated exp
     * @throws Exception if the var isn't in the assignment
     */
   double evaluate(Map<String, Double> assignment) throws Exception;

   /**.
    * A convenience method. Like the `evaluate(assignment)` method above,
    * but uses an empty assignment.
    * @return the evaluated exp
    * @throws Exception if the var isn't in the assignment
    */
   double evaluate() throws Exception;

   /**.
    * Returns a list of the variables in the expression.
    * @return the list
    */
   List<String> getVariables();

   /**.
    * Returns a nice string representation of the expression.
    * @return str
    */
   String toString();

   /**>
    * Returns a new expression in which all occurrences of the variable
    * var are replaced with the provided expression (Does not modify the
    * current expression).
    * @param var the variable
    * @param expression the exp
    * @return the assigned exp
    */
   Expression assign(String var, Expression expression);

   /**>
    * Returns the expression tree resulting from differentiating
    * the current expression relative to variable `var`.
    * @param var the variable
    * @return the devairate
    */
  Expression differentiate(String var);

   /**.
    * Returned a simplified version of the current expression.
    * @return the exp
    */
   Expression simplify();
}