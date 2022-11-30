package MyFarm;

import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MyFarm.land.Land;
import MyFarm.land.LandState;

public class MainView {
    JFrame mainFrame;

    // JButton [][] landArray = new JButton [5][10];
    Plot [][]landArray = new Plot[5][10];

    LeftPanel leftPanel;
    RightPanel rightPanel;

    ImageIcon seedling = new ImageIcon("src/MyFarm/icons/seedling.png");
    ImageIcon unplowed = new ImageIcon("src/MyFarm/icons/unplowed.png");
    ImageIcon plowed = new ImageIcon("src/MyFarm/icons/plowed.png");
    ImageIcon withered = new ImageIcon("src/MyFarm/icons/withered.png");

    JLabel playerAction = new JLabel("");
    JPanel bottomPanel = new JPanel();

    public MainView (Land land, Player P1) {
        this.mainFrame = new JFrame();
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(700,420);
        this.mainFrame.setResizable(false);
        this.mainFrame.setTitle("Farming Simulator");
        this.mainFrame.setIconImage(new ImageIcon("src/MyFarm/icons/seedling.png").getImage());
        this.mainFrame.setLayout(new BorderLayout(8,2));

        leftPanel = new LeftPanel(P1);
        rightPanel = new RightPanel(playerAction, land, landArray, P1, leftPanel);

        initializePanels(P1, land);

        this.mainFrame.setVisible(true);
    }

    public void initializePanels(Player P1, Land land){

        bottomPanel.setBackground(Palette.BOTTOM_PANEL.getColor()); //gray

        playerAction.setForeground(Palette.WHITE.getColor()); //white

        bottomPanel.add(playerAction);
        bottomPanel.setPreferredSize(new Dimension(50,100));
        this.mainFrame.add(bottomPanel, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5,10,5,5));
        centerPanel.setBackground(Palette.GRASS.getColor()); //green

        initializeSidePanels();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                landArray[i][j] = new Plot(land.landState[i][j],i ,j);

                centerPanel.add(landArray[i][j]);
            }
        }

        land.landState[0][0] = LandState.UNPLOWED;
        landArray[0][0].setIcon(unplowed);
        landArray[0][0].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(land.landState[0][0] == LandState.HARVESTABLE)
                    P1.harvestCrop(land, landArray, playerAction, leftPanel);
                else if (rightPanel.hoe.getText().equals("selected")) {
                	P1.plowLand(land, landArray, playerAction, leftPanel);
                	rightPanel.hoe.setText("hoe");
                }
                else if (rightPanel.wateringCan.getText().equals("selected")) {
                	P1.waterPlant(land, landArray, playerAction, leftPanel);
                	rightPanel.wateringCan.setText("watering can");
                }
                else if (rightPanel.shovel.getText().equals("selected")) {
                	P1.removePlant(land, landArray, playerAction, leftPanel);
                	rightPanel.shovel.setText("shovel");
                }
                else if(land.landState[0][0] == LandState.PLOWED && 
                    !rightPanel.wateringCan.getText().equals("selected"))
                    rightPanel.cardLayout.next(rightPanel.rightCardPanel);
            }
        });

        this.mainFrame.add(centerPanel, BorderLayout.CENTER);
    }

    public void initializeSidePanels() {
        this.mainFrame.add(leftPanel.panel, BorderLayout.WEST);
        this.mainFrame.add(rightPanel.rightCardPanel, BorderLayout.EAST);
    }
}