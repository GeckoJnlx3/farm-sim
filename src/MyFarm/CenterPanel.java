package MyFarm;

import java.awt.*;
import javax.swing.JPanel;

/**
 * CenterPanel class - extends the JPanel.
 * Contains all buttons.
 */
public class CenterPanel extends JPanel{
    Plot plotBtn [][] = new Plot [5][10];
    
    /**
     * Constructor for CenterPanel.
     * Specifies the gridlayout and the color.
     * @param model
     * @param view
     */
    public CenterPanel(){
        super(new GridLayout(5,10,5,5));
        setOpaque(true);
        setBackground(Palette.GRASS.getColor());
    }
}
