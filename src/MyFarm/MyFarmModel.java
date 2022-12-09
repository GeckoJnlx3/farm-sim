package MyFarm;

import MyFarm.land.Land;

public class MyFarmModel {
    Land land;
    Player player;

    public MyFarmModel(){
        land = new Land();
        player = new Player();
    }

    public void resetModel(){
        land = new Land();
        player = new Player();
    }
}
