package MyFarm;

import java.awt.*;
import javax.swing.JPanel;

public class CenterPanel extends JPanel{
    Plot plotBtn [][] = new Plot [5][10];
    
    public CenterPanel(MyFarmModel model, MyFarmView view){
        super(new GridLayout(5,10,5,5));
        setOpaque(true);
        setBackground(Palette.GRASS.getColor());
    }
}
