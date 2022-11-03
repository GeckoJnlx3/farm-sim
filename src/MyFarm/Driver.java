package MyFarm;

public class Driver {
    public static void main(String[] args) {
        Land land = new Land();
        Player p1 = new Player();
        
        land.createRock();
        land.setRocks();
        
        MainView mainFrame = new MainView(land, p1);
    }
}
