package MyFarm;

import java.awt.Dimension;

import javax.swing.*;

/**
 * BottomPanel class - Extends JPanel and contains specifics on how the 
 * bottom panel should look
 */
public class BottomPanel extends JPanel {
    JLabel playerAction;

    /**
     * Constructor for BottomPanel class.
     * Gives Color and adds the JLabel.
     */
    public BottomPanel(){
        setBackground(Palette.BOTTOM_PANEL.getColor());
        setPreferredSize(new Dimension(50,100));
        
        playerAction = new JLabel("");
        playerAction.setForeground(Palette.WHITE.getColor()); 

        add(playerAction);
    }

    /**
     * Function that clears the text on player action.
     */
    void clearBottompanel(){
        playerAction.setText("");
    }
}
