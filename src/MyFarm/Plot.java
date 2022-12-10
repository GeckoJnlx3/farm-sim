package MyFarm;

import javax.swing.JButton;

import MyFarm.crop.CropType;

/**
 * Plot class - extends from the button
 */
public class Plot extends JButton{
    int nRow; 
    int nCol;

    /**
     * Constructor for Plot
     * @param nRow row position
     * @param nCol column position
     */
    public Plot(int nRow, int nCol){
        this.nRow = nRow;
        this.nCol = nCol;
        this.setFocusable(false);
    }


    /**
     * sets the Image when harvestable
     * @param cropType type of crop
     */
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
