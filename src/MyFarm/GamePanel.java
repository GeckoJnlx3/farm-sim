package MyFarm;

import javax.swing.*;
import java.awt.*;

/**
 * GamePanel class - extends JPanel
 * the panel that contains every panel.
 */
public class GamePanel extends JPanel
{
    /**
     * Constructor for GamePanel.
     * Sets the size and the layout.
     */
    public GamePanel(){
        this.setPreferredSize(new Dimension(800,460));
        this.setLayout(new BorderLayout(8,2));
    }
}
