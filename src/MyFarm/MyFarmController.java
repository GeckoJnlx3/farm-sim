package MyFarm;

//if the view changes, the controller changes the model
//view should respond to changes in model
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