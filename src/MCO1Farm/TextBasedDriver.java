/**
 * CCPROG3 MACHINE PROJECT - FARMING SIMULATOR
 * Agero, Janelle
 * Burce, Vincent Maximus
 */

package MCO1Farm;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Driver class for the program.
 * Initializes the TextPlayer and TextLand classes that
 * will be utilized throughout the simulator.
 */

public class TextBasedDriver {

    /**
     * Main method for the driver class.
     * Displays main game information and collects
     * player's choices throughout the game.
     * @param args
     */
    public static void main(String[] args) {
        TextPlayer p1 = new TextPlayer();
        TextLand land = new TextLand();

        int isRunning = 1;
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("Farm sim.\n1-start game\n0-close program");
        isRunning = sc.nextInt();
        while (isRunning != 0 &&
                isRunning != 1) {
            System.out.println("Invalid input.");
            System.out.println("Farm sim.\n1-start game\n0-close program");
            isRunning = sc.nextInt();
        }
        while (isRunning == 1) {
            displayLandInfo(land, p1);
            choice = collectChoice(sc);
            isRunning = updateIsRunning(choice);
            switch (choice) {
                case 1: //plant
                    p1.plantCrop(land);
                    break;
                case 2: //water
                    p1.waterPlant(land);
                    break;
                case 3: //shovel
                    p1.removePlant(land);
                    break;
                case 4: //hoe
                    p1.plowLand(land);
                    break;
                case 5:
                    p1.harvestCrop(land);
                    break;
                case 6: //advance day
                    p1.skipDay(land);
                    break;
                case 0:
                    break;
            }
            if (choice < 0 || choice >6)
                System.out.println("Input is invalid.");
        }
        System.out.println("See you next time!");
        sc.close();
    }

    /**
     * Displays the land information to the console.
     * @param land 	TextLand instance where crop is planted
     * @param p1 	TextPlayer instance that interacts with land
     */
    private static void displayLandInfo(TextLand land, TextPlayer p1) {
        System.out.println("Day: " + p1.getDay() + "\t\t\tObjectCoins:" + p1.getCoins());
        System.out.println("Land state: " + land.landState.toString() + "\tCrop: " + land.crop.getCropName().toString());
        System.out.println();
    }

    /**
     * Collects player input and validates it
     * for game actions.
     * @param 	sc Scanner object for intake of
     *           player choice.
     * @return 	integer that corresponds with
     *          player choice.
     */
    private static int collectChoice(Scanner sc) {
        int temp = -1;
        do {
            try {
                System.out.println("1-plant turnip\n2-water"
                		+ "\n3-shovel\n4-hoe\n5-harvest crop"
                        + "\n6-advance day\n0-close game");
                temp = sc.nextInt();
                if (temp <= 0 && temp >= 6)
                    System.out.println("Invalid input.Please "
                    		+ "enter only the following choices.");
                return temp;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.Please "
                		+ "enter only the following choices.");
            } sc.nextLine();
        } while (temp <= 0 && temp >= 6);

        return temp;

    }

    /**
     * Checks if player has selected to close game.
     * @param choice Inputed player choice.
     * @return int 	that determines whether to
     *          terminate the program or not.
     */
    private static int updateIsRunning(int choice) {
        if (choice == 0)
            return 0;
        else return 1;
    }
}