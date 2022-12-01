package MyFarm;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BottomPanel extends JPanel {
    JLabel playerAction;

    public BottomPanel(){
        
        setBackground(Palette.BOTTOM_PANEL.getColor()); //gray

        playerAction = new JLabel("");
        playerAction.setForeground(Palette.WHITE.getColor()); //white

        add(playerAction);
        setPreferredSize(new Dimension(50,100));
    }
}
