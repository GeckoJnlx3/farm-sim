package MyFarm;

import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainView {
    JFrame mainFrame;

    JButton [][] landArray = new JButton [5][10];

    LeftPanel leftPanel;
    RightPanel rightPanel;

    ImageIcon seedling = new ImageIcon("icons/seedling.png");
    ImageIcon unplowed = new ImageIcon("icons/unplowed.png");
    ImageIcon plowed = new ImageIcon("icons/plowed.png");
    ImageIcon withered = new ImageIcon("icons/withered.png");

    JLabel playerAction = new JLabel("");
    JPanel bottomPanel = new JPanel();

    public MainView (Land land, Player P1) {
        this.mainFrame = new JFrame();
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(700,420);
        this.mainFrame.setResizable(false);
        this.mainFrame.setTitle("Farming Simulator");
        this.mainFrame.setIconImage(new ImageIcon("icons/seedling.png").getImage());
        this.mainFrame.setLayout(new BorderLayout(8,2));

        leftPanel = new LeftPanel(P1);
        rightPanel = new RightPanel(playerAction, land, landArray, P1, leftPanel);

        initializePanels(land.landState, P1);

        this.mainFrame.setVisible(true);
    }

    public void initializePanels(LandState[][] landState, Player P1){

        bottomPanel.setBackground(new Color (0x5D5D5D)); //gray

        playerAction.setForeground(new Color (0xFFFFFF)); //white

        bottomPanel.add(playerAction);
        bottomPanel.setPreferredSize(new Dimension(50,100));
        this.mainFrame.add(bottomPanel, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5,10,5,5));
        centerPanel.setBackground(new Color (0xC0E5C8)); //green

        initializeSidePanels();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                landArray[i][j] = new JButton();

                if (landState[i][j] == LandState.UNPLOWED)
                    landArray[i][j].setBackground(new Color (0x9F8C83)); //brown
                else if (landState[i][j] == LandState.BLOCKED)
                    landArray[i][j].setBackground(Color.lightGray);

                landArray[i][j].setForeground(new Color(0x8EE779)); //plant color
                landArray[i][j].setIcon(unplowed);
                landArray[i][j].setFocusable(false);
                centerPanel.add(landArray[i][j]);
            }
        }

        landState[0][0] = LandState.PLOWED;
        landArray[0][0].setIcon(plowed);
        landArray[0][0].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(landState[0][0] == LandState.PLOWED)
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