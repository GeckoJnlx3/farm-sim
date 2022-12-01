package MyFarm;

public class MyFarmController {
    MyFarmView view = new MyFarmView();
    MyFarmModel model = new MyFarmModel();

    public MyFarmController () {
        this.view.setVisible(true);
    }

}