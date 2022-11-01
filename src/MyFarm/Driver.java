package MyFarm;

public class Driver {
    public static void main(String[] args) {
        Land land = new Land();
        
        land.createRock();
        land.setRocks();
        
        MainView mainFrame = new MainView(land);
    }
}
