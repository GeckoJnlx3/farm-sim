package MyFarm;

public class MyFarmController {
    MyFarmView view;
    MyFarmModel model;

    public MyFarmController () {
        view = new MyFarmView();
        model = new MyFarmModel();
    }

    public void startView(){
        this.view.setVisible(true);
    }

}