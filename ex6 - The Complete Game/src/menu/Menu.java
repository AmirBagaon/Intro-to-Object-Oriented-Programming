package menu;

import animation.Animation;
/**.
 * An interface of a menu
 *
 *
 * @param <T> generic parameter
 */
public interface Menu<T> extends Animation {
    /**.
     * Add an element to the menu
     * @param key key
     * @param message the description near the key
     * @param returnVal the return value
     */
    void addSelection(String key, String message, T returnVal);
    /**.
     * return the status (an action)
     * @return status
     */
    T getStatus();
    /**.
     * Add a sub menu
     * @param key keys in sub menu
     * @param message descrption of the keys
     * @param subMenu a menu that contians the keys and the values
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
 }