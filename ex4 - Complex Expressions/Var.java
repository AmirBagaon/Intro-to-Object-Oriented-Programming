import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Number class.
 * Contains and act as a variable
 *
 */
public class Var implements Expression {

    private String vari;

    /**.
     * Constructor
     * @param str str
     */
    public Var(String str) {
        this.vari = str;
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
           Double var;
           var = assignment.get(this.vari);
           if (var == null) {
               throw new Exception("The variable isn't in the assignment");
           }
           return var;
       }

       /**.
        * Like above, the evaluation, but in this class this method
        * throws exception because the var isn't in the assignment
        * @throws Exception when there's a var and not num
        * @return the val
        */
       public double evaluate() throws Exception {
           throw new Exception("The variable isn't in the assignment");
       }

       /**.
        * Returns a list with the var in it
        *
        * @return the list
        */
       public List<String> getVariables() {
           List<String> l = new ArrayList<String>();
           l.add(this.vari);
            return l;
       }

       /**.
        * Returns a nice string representation of the expression.
        * @return str
        */
       public String toString() {
             return this.vari;
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
           if (var.equals(this.vari)) {
               return expression;
           }
           return this;
       }
       /**.
        * Calculate the devariate
        * In this class it always returns 0
        * @param var the variable
        * @return Num with 0
        */
       public Expression differentiate(String var) {
       if (var.equals(this.vari)) {
               return new Num(1);
           }
           return new Num(0);
       }

       /**.
        * Returned a simplified version of the current expression
        * @return string
        */
       public Expression simplify() {
           return this;
       }

       /**.
        * Return the var
        * @return Return the var
        */
       public String getVar() {
           return this.vari;
       }

}
