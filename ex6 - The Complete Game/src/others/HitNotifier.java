package others;
/**.
 * The HitNotifer interface
 * Indicates that objects that implement it send notifications when they are being hit
 */
public interface HitNotifier {
    /**.
     * Add hl as a listener to hit events.
     * @param hl listener
     */
   void addHitListener(HitListener hl);

   /**.
    * Remove hl as a listener from the hit events.
    * @param hl listener
    */
   void removeHitListener(HitListener hl);
}