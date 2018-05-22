import java.util.Map;
import java.util.TreeMap;

/**.
 *
 * @author Amir Bagaon
 *
 * Test the expressions
 */
public class ExpressionsTest {

    /**.
     * Test the expressions
     * @param args args
     */
    public static void main(String[] args) {
        Mult m = new Mult(2, "x");
        Sin s = new Sin(new Mult(4, "y"));
        Pow po = new Pow("e", "x");
        Plus pl = new Plus(m, s);
        Expression e = new Plus(pl, po);
        System.out.println(e);
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);
        try {
            System.out.println(e.evaluate(assignment));
        } catch (Exception exc) {
            System.out.println(exc);
        }
        System.out.println(e.differentiate("x"));
        try {
            System.out.println(e.differentiate("x").evaluate(assignment));
        } catch (Exception exc) {
            System.out.println(exc);
        }
        System.out.println(e.differentiate("x").simplify());

    }
}

