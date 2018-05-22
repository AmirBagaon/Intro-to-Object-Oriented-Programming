package others;
/**.
 * A Counter counts something (can be score, blocks, lives etc.)
 *
 */
public class Counter {
    private int count;

    /**.
     * Constructor with no parameters starts count with 0
     */
    public Counter() {
        this.count = 0;
    }
    /**.
     * Constructor with parameter starts count with the parameter
     * @param value the val
     */
    public Counter(int value) {
        this.count = value;
    }

   /**.
    * add number to current count.
    * @param number num
    */
    void increase(int number) {
       this.count += number;
   }
    /**.
     *subtract number from current count.
     * @param number num
     */
    void decrease(int number) {
       this.count -= number;
   }
    /**.
     * Get current count.
     * @return count
     */
   int getValue() {
       return this.count;
   }
}