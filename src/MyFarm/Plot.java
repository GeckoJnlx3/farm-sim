package MyFarm;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MyFarm.crop.Crop;
import MyFarm.crop.CropType;
import MyFarm.land.LandState;

public class Plot extends JButton{
    int nRow; 
    int nCol;

    public Plot(LandState landState, int nRow, int nCol, MyFarmModel model, MyFarmView view  ){
        this.nRow = nRow;
        this.nCol = nCol;
        this.setFocusable(false);
    }


    public void setPlantIcon(CropType cropType){
        switch (cropType){
            case TURNIP:
                this.setIcon(Icons.TURNIP.getImageIcon());
                break;
            case CARROT:
                this.setIcon(Icons.CARROT.getImageIcon());
                break;
            case POTATO:
                this.setIcon(Icons.POTATO.getImageIcon());
                break;
            case ROSE:
                this.setIcon(Icons.ROSE.getImageIcon());
                break;            
            case TURNIPS:
                this.setIcon(Icons.TURNIPS.getImageIcon());
                break;
            case SUNFLOWER:
                this.setIcon(Icons.SUNFLOWER.getImageIcon());
                break;            
            case MANGO:
                this.setIcon(Icons.MANGO.getImageIcon());
                break;
            case APPLE:
                this.setIcon(Icons.APPLE.getImageIcon());
                break;    
            default:
                break;        
        }
    }


}
