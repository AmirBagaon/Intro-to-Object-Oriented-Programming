package menu;

/**.
 * An interface of generic task
 * @author PCP-RENT
 *
 * @param <T> generic param
 */
public interface Task<T> {
    /**.
     * Run the task
     * @return null
     */
    T run();
}