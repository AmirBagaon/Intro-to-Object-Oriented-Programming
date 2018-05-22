
public class Sort {

      /**.
      * Sort the array from the lowest number to the highest
      *
      * @param numbers the array that will be sorted
      * @return
      */
    public static void sortToHighest(int[] numbers) {
        int n = numbers.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (numbers[j - 1] > numbers[j]) {
                    //swap the elements!
                    temp = numbers[j - 1];
                    numbers[j - 1] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
    }
    /**.
    * Sort the array from the highest number to the lowest
    *
    * @param numbers the array that will be sorted
    * @return
    */
    public static void sortToLowest(int[] numbers) {
        int n = numbers.length;
         int temp = 0;
         for (int i = 0; i < n; i++) {
             for (int j = 1; j < (n - i); j++) {
                 if (numbers[j - 1] < numbers[j]) {
                     //Swapping
                     temp = numbers[j - 1];
                     numbers[j - 1] = numbers[j];
                     numbers[j] = temp;
                 }
             }
         }
    }
    /**.
    * Print the array
    *
    * @param numbers the array that will be printed
    * @return
    */
    public static void printArr(int[]numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.print(numbers[numbers.length - 1]);
        System.out.println();
    }
    /**
    *
    * @param args input from the user
    */
    public static void main(String[]args) {
        String s = args[0];
        int[] numbers = new int[args.length - 1];
        //change strings to ints
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(args[i + 1]);
        }
        if (s.equals("asc")) {
            sortToHighest(numbers);
            printArr(numbers);
        }    else
                if (s.equals("desc")) {
                sortToLowest(numbers);
                printArr(numbers);
        }
    }
}
