/**
 * CCPROG3 MACHINE PROJECT - FARMING SIMULATOR
 * Agero, Janelle
 * Burce, Vincent Maximus
 */

package MyFarm;

 /**
 * Driver class for the program.
 * Initializes the TextPlayer and TextLand classes that
 * will be utilized throughout the simulator.
 */
public class Driver {
    /**
     * Main method for the driver class.
     * Displays main game information and collects
     * player's choices throughout the game.
     * @param args
     */
    public static void main(String[] args) {
        MyFarmController controller = new MyFarmController();
        controller.startView();
    }
}