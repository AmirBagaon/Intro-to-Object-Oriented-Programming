/**.
 *
 * @author Amir Bagaon
 * A Simplification Demo
 *
 */
public class SimplificationDemo {

    /**.
     * Demonstrate the simplification
     * @param args args
     */
    public static void main(String[] args) {
        // Plus and Minus
        Mult a = new Mult("x", 2);
        Mult a2 = new Mult("x", 3);
        Mult a3 = new Mult("x", -8);
        Plus p = new Plus(a, a2);
        System.out.println(p);
        System.out.println(p.simplify());
        Minus p2 = new Minus(a, new Mult("x", 7));
        System.out.println(p2);
        System.out.println(p2.simplify());
        Minus p3 = new Minus(p2, a3);
        System.out.println(p3);
        System.out.println(p3.simplify());

        // Mult
        System.out.println("Mult");
        Mult b = new Mult(a, a2);
        b = new Mult(b, a2);
        System.out.println(b);
        System.out.println(b.simplify());

        //Pow
        Pow c = new Pow(b.simplify(), 3);
        System.out.println("Pow");
        System.out.println(c);
        System.out.println(c.simplify());
    }

}
