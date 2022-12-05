package MyFarm;

import java.awt.*;

import javax.swing.JFrame;

public class MyFarmView extends JFrame{
    MyFarmModel myFarmModel = new MyFarmModel();

    CenterPanel centerPanel = new CenterPanel(myFarmModel, this);
    BottomPanel bottomPanel = new BottomPanel();
    LeftPanel leftPanel = new LeftPanel(myFarmModel.player);
    RightPanel rightPanel = new RightPanel(myFarmModel, this);

    public MyFarmView(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,420);
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
