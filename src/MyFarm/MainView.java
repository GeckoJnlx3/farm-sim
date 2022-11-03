package MyFarm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainView {
    JFrame mainFrame;

    ImageIcon seedling = new ImageIcon("icons/seedling.png");
    ImageIcon objectcoins = new ImageIcon("icons/objectcoins.png");
    ImageIcon xp = new ImageIcon("icons/xp.png");
    ImageIcon lvl = new ImageIcon("icons/lvl.png");
    ImageIcon title = new ImageIcon("icons/title.png");
    ImageIcon shop = new ImageIcon("icons/shop.png");
    ImageIcon player = new ImageIcon("icons/player.png");
    ImageIcon day = new ImageIcon("icons/day.png");
    ImageIcon forward = new ImageIcon("icons/forward.png");
    ImageIcon turnip = new ImageIcon("icons/turnip.png");
    ImageIcon plowed = new ImageIcon("icons/plowed.png");
    ImageIcon unplowed = new ImageIcon("icons/unplowed.png");
    ImageIcon withered = new ImageIcon("icons/withered.png");

    CardLayout cardLayout = new CardLayout();
    JLabel playerAction = new JLabel("");
    JPanel bottomPanel = new JPanel();
    JPanel leftPanel = new JPanel();
    JPanel rightCardPanel = new JPanel(cardLayout);
    JPanel rightPanel = new JPanel();
    JPanel seedPanel = new JPanel();

    JButton forwardButton = new JButton();
    JButton wateringCan = new JButton("watering can");
    JButton pickaxe = new JButton("pickaxe");
    JButton shovel = new JButton ("shovel");
    JButton hoe = new JButton ("hoe");

    JLabel objectCoins = new JLabel();
    JLabel currExp = new JLabel();
    JLabel currLvl = new JLabel();
    JLabel currTitle = new JLabel();
    JLabel currDay = new JLabel();
    JButton shopTab = new JButton();
    JButton playerTab = new JButton();

    JButton seedTurnip = new JButton();

    public MainView (Land land, Player P1) {
        this.mainFrame = new JFrame();
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(700,420);
        this.mainFrame.setResizable(false);
        this.mainFrame.setTitle("Farming Simulator");
        this.mainFrame.setIconImage(new ImageIcon("icons/seedling.png").getImage());
        this.mainFrame.setLayout(new BorderLayout(10,2));

        initializePanels(land.landState, P1);

        this.mainFrame.setVisible(true);
    }

    void initializePanels(LandState[][] landState, Player P1){

        bottomPanel.setBackground(new Color (0x5D5D5D)); //gray

        playerAction.setForeground(new Color (0xFFFFFF)); //white

        bottomPanel.add(playerAction);
        bottomPanel.setPreferredSize(new Dimension(50,100));
        this.mainFrame.add(bottomPanel, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5,10,5,5));
        centerPanel.setBackground(new Color (0xC0E5C8)); //green

        initializeSidePanels();
        initializeGameInfo(P1);

        JButton [][] landArray = new JButton [5][10];

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
        landArray[0][0].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(landState[0][0] == LandState.PLOWED)
                    cardLayout.next(rightCardPanel);
            }
        });

        this.mainFrame.add(centerPanel, BorderLayout.CENTER);
    }

    void initializeSidePanels() {
        leftPanel.setBackground(new Color(0xC0E5C8));
        leftPanel.setPreferredSize(new Dimension(125,100));

        RightPanel panel2 = new RightPanel(playerAction);


        this.mainFrame.add(leftPanel, BorderLayout.WEST);
        this.mainFrame.add(panel2.rightCardPanel, BorderLayout.EAST);
    }

    void initializeGameInfo(Player P1)
    {
        currDay.setText("Day " + P1.getDay());
        currDay.setIcon(day);
        objectCoins.setText(Integer.toString(P1.getCoins()));
        objectCoins.setIcon(objectcoins);
        currExp.setText(Integer.toString(P1.getXP()));
        currExp.setIcon(xp);
        currLvl.setText(Integer.toString(P1.getLevel()));
        currLvl.setIcon(lvl);
        currTitle.setText("Farmer");
        currTitle.setIcon(title);

        leftPanel.add(currDay);
        leftPanel.add(objectCoins);
        leftPanel.add(currExp);
        leftPanel.add(currLvl);
        leftPanel.add(currTitle);
    }
}