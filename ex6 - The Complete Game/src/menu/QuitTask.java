package menu;

import biuoop.GUI;

/**.
 * Quit from the game
 */
public class QuitTask implements Task<Void> {

    private GUI gui;
    /**.
     * Constructor
     * @param gui gui
     */
    public QuitTask(GUI gui) {
        this.gui = gui;
    }

    /**.
     * Close the gui and finish the run
     * @return null
     */
    public Void run() {
        this.gui.close();
        System.exit(0);
        return null;
    }
}
