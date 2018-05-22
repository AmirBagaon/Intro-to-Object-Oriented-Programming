import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**.
 *
 * Binary Expression class
 * Contains 2 expression: the left one, and the right one
 *
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression right;
    private Expression left;

    /**.
     * Constructor from 2 expressions
     * @param left - The left expression
     * @param right - The right expression
     */
    public BinaryExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    /**.
     * Shortcut Constructor from Expression and String
     * @param left exp
     * @param right exp
     */
    public BinaryExpression(Expression left, String right) {
        this.left = left;
        this.right = new Var(right);
    }
    /**.
     * Shortcut Constructor from Expression and double
     * Put the double to the left
     * @param left exp
     * @param right exp
     */
    public BinaryExpression(Expression left, double right) {
        this.left = new Num(right);
        this.right = left;
    }
    /**.
     * Shortcut Constructor from double and Expression
     * @param left exp
     * @param right exp
     */
    public BinaryExpression(double left, Expression right) {
        this.left = new Num(left);
        this.right = right;
    }
    /**.
     * Shortcut Constructor from double and String
     * @param left exp
     * @param right exp
     */
    public BinaryExpression(double left, String right) {
        this.left = new Num(left);
        this.right = new Var(right);
    }
    /**.
     * Shortcut Constructor from double and double
     * @param left exp
     * @param right exp
     */
    public BinaryExpression(double left, double right) {
        this.left = new Num(left);
        this.right = new Num(right);
    }
    /**.
     * Shortcut Constructor from String and double
     * put the double to the left
     * @param left exp
     * @param right exp
     */
    public BinaryExpression(String left, double right) {
        this.right = new Var(left);
        this.left = new Num(right);
    }
    /**.
     * Shortcut Constructor from String and String
     * Put them in alfabetic order
     * @param left left exp
     * @param right exp
     */
    public BinaryExpression(String left, String right) {
        if (left.compareTo(right) < 0) {
        this.right = new Var(right);
        this.left = new Var(left);
        } else {
            this.right = new Var(left);
            this.left = new Var(right);
        }
    }
    /**.
     * Shortcut Constructor from String and Expression
     * @param left left exp
     * @param right right exp
     */
    public BinaryExpression(String left, Expression right) {
        this.right = right;
        this.left = new Var(left);
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
            s.addAll(this.getLeft().getVariables());
            s.addAll(this.getRight().getVariables());
            //Add the hashset to the list
            l.addAll(s);
            return l;
        }
    /**.
     * Returns the left expression
     * @return the left expression
     */
    public Expression getLeft() {
        return this.left;
    }
    /**.
     * Returns the right expression
     * @return the right expression
     */
    public Expression getRight() {
        return this.right;
    }
    /**.
     * Print the Expression:
     * First the left exp, then the operator, the the right exp
     * @param str the operator
     * @return the whole expression
     */
    protected String strPrint(String str) {
        String l = this.left.toString();
        String r = this.right.toString();
        return "(" + l  + str  + r + ")";
    }

    /**
     * An abstract method which guide each of the binary expression
     * to calculate and evaluate the expression.
     * The calculate method makes the calculation more simple and more code-shared.
     * @param l - Left exp
     * @param r - Right exp
     * @throws Exception if there are var's in the expression
     * @return the evaluated number
     */
    abstract double calculate(double l, double r) throws Exception;


    /**.
     * Evaluates the Expression and return the evaluated num
     * @param assignment the map of the vars
     * @assignment the var's values
     * @throws Exception if there are var's in the exp which isn't in the map
     * @return the evaluated number
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double r = this.right.evaluate(assignment);
        double l = this.left.evaluate(assignment);
        double c = calculate(l, r);
        return c;
    }

    /**.
     * In Log, Pow and Div subclasses, the order of
     * the 2 parmaters is critic, so we can't change
     * it as we did in the constructor.
     * We can't override a constructor, so we made
     * methods that make us able to return them
     * to the previous order.
     *
     * @param e exp
     */
    protected void setRight(Expression e) {
        this.right = e;
    }
    /**.
     * Set the Expression
     * @param e exp
     */
    protected void setRight(String e) {
        this.right = new Var(e);
    }
    /**.
     * Set the Expression
     * @param e exp
     */
    protected void setRight(double e) {
        this.right = new Num(e);
    }
    /**.
     * Set the Expression
     * @param e exp
     */
    protected void setLeft(Expression e) {
        this.left = e;
    }
    /**.
     * Set the Expression
     * @param e exp
     */
    protected void setLeft(String e) {
        this.left = new Var(e);
    }
    /**.
     * Set the Expression
     * @param e exp
     */
    protected void setLeft(double e) {
        this.left = new Num(e);
    }

}
