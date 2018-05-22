
public class Factorial {
    /**.
     * Calculate factorial using iterative procedure
     *
     * @param n the number that will be calculated
     * @return sum the factorial of n
     */
    public static long factorialIter(long n) {
        long sum = 1;
        for (long i = 1; i <= n; i++) {
            sum *= i;
        }
        return sum;
    }
    /**.
     * Calculate factorial using recursive definition
     *
     * @param n the number that will be calculated
     * @return the factorial of n
     */
    public static long factorialRecursive(long n) {
        if (n == 0) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }
    /**
    *
    * @param args input from the user
    */
    public static void main(String[]args) {
        int n = Integer.parseInt(args[0]);
        System.out.println("recursive: " + factorialRecursive(n));
        System.out.println("iterative: " + factorialIter(n));
    }
}
