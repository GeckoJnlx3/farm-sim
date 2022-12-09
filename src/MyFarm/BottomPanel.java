package MyFarm;

import java.awt.Dimension;

import javax.swing.*;

public class BottomPanel extends JPanel {
    JLabel playerAction;

    public BottomPanel(){
        
        setBackground(Palette.BOTTOM_PANEL.getColor()); //gray
        setPreferredSize(new Dimension(50,100));
        
        playerAction = new JLabel("");
        playerAction.setForeground(Palette.WHITE.getColor()); //white

        add(playerAction);

    }
}
