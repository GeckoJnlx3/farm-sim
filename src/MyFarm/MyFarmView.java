package MyFarm;

import java.awt.*;

import javax.swing.*;

/**
 * MyFarmView class - extends JFrame 
 * Contains all of the panels. 
 */
public class MyFarmView extends JFrame{

    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel = new JPanel(cardLayout);
    GameOverPanel gameOverPanel;
    GamePanel gamePanel = new GamePanel();
    CenterPanel centerPanel;
    BottomPanel bottomPanel = new BottomPanel();
    LeftPanel leftPanel;
    RightPanel rightPanel;

    /**
     * Constructor for MyFarmView
     */ 
    public MyFarmView(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,460);
        this.setResizable(false);
        this.setTitle("Farming Simulator");
        this.setIconImage(Icons.SEEDLING.getImageIcon().getImage());
        this.setLayout(new BorderLayout(8,2));

        gameOverPanel = new GameOverPanel();
        centerPanel = new CenterPanel();
        leftPanel = new LeftPanel();
        rightPanel = new RightPanel();

        gamePanel.add(rightPanel.rightCardPanel, BorderLayout.EAST);
        gamePanel.add(leftPanel.leftCardPanel, BorderLayout.WEST);
        gamePanel.add(centerPanel, BorderLayout.CENTER);
        gamePanel.add(bottomPanel, BorderLayout.SOUTH);

        mainPanel.add(gamePanel, "game");
        mainPanel.add(gameOverPanel, "gameover");

        this.add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Changes the CardLayout to the game over panel
     */
    public void gameOver()
    {
        cardLayout.next(mainPanel);
    }
}
