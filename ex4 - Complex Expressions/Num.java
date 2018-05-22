import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Number class.
 * Contains and act as a simple number
 *
 */
public class Num implements Expression {

    private double value;

    /**.
     * Constructor
     * @param num the value
     */
    public Num(double num) {
        this.value = num;
    }
    /**.
     * Returns the num
     * @return the num
     */
    public double getNum() {
        return this.value;
    }
    /**.
     * Evaluates the Expression and return the evaluated num
     * In this class, this method does nothing
     * @param assignment the map of the vars
     * @assignment the var's values
     * @throws Exception if there are var's in the exp which isn't in the map
     * @return the number itself
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.value;
    }

    /**.
     * Like above, the evaluation, but in this class this method
     * does nothing and just returns the num.
     * @throws Exception when there's a var and not num
     * @return the val
     */
    public double evaluate() throws Exception {
        return this.value;
    }

    /**.
     * Returns a nice string representation of the expression.
     * @return str
     */
    public String toString() {
        String str = new Double(value).toString();
         return str;
    }

   /**.
    *  Returns a new expression in which all occurrences of the variable
    * var are replaced with the provided expression (Does not modify the
    * current expression).
    *
    * @param var the variable
    * @param expression The Expression
    * @return the number itself
    */
   public Expression assign(String var, Expression expression) {
       return this;
   }
   /**.
    * Calculate the devariate
    * In this class it always returns 0
    * @param var the variable
    * @return Num with 0
    */
   public Expression differentiate(String var) {
       return new Num(0);
   }

   /**.
    * Returned a simplified version of the current expression
    * (In the class - the number itself)
    * @return string
    */
   public Expression simplify() {
       return this;
   }
   /**.
    * Returns a list with the vars
    * (Does nothing in this class)
    * @return the list
    */
   public List<String> getVariables() {
        List<String> l = new ArrayList<String>();
        return l;
    }

}
