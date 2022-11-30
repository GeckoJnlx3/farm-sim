package MyFarm;

import MyFarm.land.Land;

public class Driver {
    public static void main(String[] args) {
        Land land = new Land();
        Player p1 = new Player();
        
        MainView mainFrame = new MainView(land, p1);
    }
}
