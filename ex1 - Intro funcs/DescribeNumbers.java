
public class DescribeNumbers {
    /**.
     * Convert Strings to ints
     *
     * @param numbers the array that will be converted
     * @return The converted array
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] nums =  new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            nums[i] = Integer.parseInt(numbers[i]);
        }
        return nums;
    }
    /**.
     * Returns the minimum value
     *
     * @param numbers the array that contains numbers
     * @return The minimum number in the array
     */
    public static int min(int[] numbers) {
        int min = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return min;
    }
    /**.
     * Returns the maximum value
     *
     * @param numbers the array that contains numbers
     * @return The maximum number in the array
     */
    public static int max(int[] numbers) {
        int max = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }
    /**.
     * Returns the average
     *
     * @param numbers the array that contains numbers
     * @return The maximum number in the array
     */
    public static float avg(int[] numbers) {
        float avg = 0;
        for (int i = 0; i < numbers.length; i++) {
            avg += numbers[i];
        }
    return (float) (avg / numbers.length);
    }
    /**
    *
    * @param args input from the user
    */
    public static void main(String[]args) {
        int[] numbers = stringsToInts(args);
        System.out.println("min: " + min(numbers));
        System.out.println("max: " + max(numbers));
        System.out.println("avg: " + avg(numbers));
    }
}
