package MyFarm;

import javax.swing.*;
import java.awt.*;

/**
 * GameOverPanel class - extends JPanel
 * This panel appears when the game ends
 */
public class GameOverPanel extends JPanel
{
    JLabel gameOver = new JLabel("BRUH!!!");
    JButton restart = new JButton("Start a New Game");
    
    /**
     * Constructor for GamveOverPanel 
     * Sets the size and color.
     */
    public GameOverPanel(){
        this.setPreferredSize(new Dimension(800,460));
        this.setBackground(Palette.SELECTED.getColor());
        this.setLayout(new BorderLayout());

        gameOver.setIcon(Icons.GAME_OVER.getImageIcon());

        this.add(gameOver, BorderLayout.CENTER);
    }
}
