package MyFarm;

import java.awt.*;
import javax.swing.JPanel;

public class CenterPanel extends JPanel{
    Plot plotBtn [][] = new Plot [5][10];
    
    public CenterPanel(MyFarmModel model, MyFarmView view){
        super(new GridLayout(5,10,5,5));
        setOpaque(true);
        setBackground(Palette.GRASS.getColor()); 
        
        // initialize buttons
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 10; j++){
                plotBtn[i][j] = new Plot(model.land.landState[i][j], i, j, model, view);
                this.add(plotBtn[i][j]);
            }
        }
    }

    public void resetCenterPanelButtons(MyFarmModel model){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 10; j++){
                plotBtn[i][j].setPlotView(model.player,model.land.landState[i][j], model.land.crops[i][j]);
                this.add(plotBtn[i][j]);
            }
        }
    }


}
