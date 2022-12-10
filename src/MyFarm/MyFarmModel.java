package MyFarm;

import MyFarm.land.Land;

/**
 * MyFarmModel - The model of the program. Contains game related data
 */
public class MyFarmModel {
    Land land;
    Player player;

    /**
     * Constructor for MyFarmModel. Initializes both land and player.
     */
    public MyFarmModel(){
        land = new Land();
        player = new Player();
    }
}
