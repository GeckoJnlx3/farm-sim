package MyFarm;

import java.awt.*;

import javax.swing.*;

//interaction with viewers
public class MyFarmView extends JFrame{
    MyFarmModel myFarmModel = new MyFarmModel();

    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel = new JPanel(cardLayout);
    GameOverPanel gameOverPanel = new GameOverPanel(myFarmModel, this);
    GamePanel gamePanel = new GamePanel();
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

        gamePanel.add(rightPanel.rightCardPanel, BorderLayout.EAST);
        gamePanel.add(leftPanel.leftCardPanel, BorderLayout.WEST);
        gamePanel.add(centerPanel, BorderLayout.CENTER);
        gamePanel.add(bottomPanel, BorderLayout.SOUTH);

        mainPanel.add(gamePanel, "game");
        mainPanel.add(gameOverPanel, "gameover");

        this.add(mainPanel, BorderLayout.CENTER);
    }

    public void resetPanels()
    { // i dunno if this works!?
        this.centerPanel.resetCenterPanelButtons(myFarmModel);
        this.bottomPanel.playerAction.setText("");
        this.leftPanel.updateLeftPanel(myFarmModel.player);
    }

    public void gameOver()
    {
        cardLayout.next(mainPanel);
    }
}
