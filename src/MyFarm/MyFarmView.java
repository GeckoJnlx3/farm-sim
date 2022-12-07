package MyFarm;

import java.awt.*;

import javax.swing.JFrame;
//interaction with viewers
public class MyFarmView extends JFrame{
    MyFarmModel myFarmModel = new MyFarmModel();

    CenterPanel centerPanel = new CenterPanel(myFarmModel, this);
    BottomPanel bottomPanel = new BottomPanel();
    LeftPanel leftPanel = new LeftPanel(myFarmModel.player, myFarmModel, this);
    RightPanel rightPanel = new RightPanel(myFarmModel, this);

    public MyFarmView(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,460);
        this.setResizable(false);
        this.setTitle("Farming Simulator");
        this.setIconImage(Icons.SEEDLING.getImageIcon().getImage());
        this.setLayout(new BorderLayout(8,2));
 
        this.add(rightPanel.rightCardPanel, BorderLayout.EAST);
        this.add(leftPanel.leftCardPanel, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }
}
