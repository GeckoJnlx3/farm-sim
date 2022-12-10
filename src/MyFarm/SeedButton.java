package MyFarm;

import javax.swing.JButton;
import java.awt.*;

import MyFarm.crop.CropType;

/**
 * Seedbutton class - extends JButton
 * plants the seed
 */
public class SeedButton extends JButton {

    CropType crop;

    /**
     * Constructor for SeedButton
     * @param crop type of crop it will plant
     */
    public SeedButton(CropType crop){
        this.crop = crop;
        
        setFocusable(false);
        setSeedIcon(crop);
        setBackground(Palette.SEED_SLOT.getColor());
        setFocusable(false);
        setPreferredSize(new Dimension(50,50));
    }

    /**
     * sets the image for the seed button
     * @param crop
     */
    private void setSeedIcon(CropType crop){
        switch (crop){
            case TURNIP:
                setIcon(Icons.TURNIP.getImageIcon());
                break;
            case APPLE:
                setIcon(Icons.APPLE.getImageIcon());   
                break;
            case CARROT:
                setIcon(Icons.CARROT.getImageIcon()); 
                break;
            case MANGO:
                setIcon(Icons.MANGO.getImageIcon()); 
                break;
            case POTATO:
                setIcon(Icons.POTATO.getImageIcon());                 
                break;
            case ROSE:
                setIcon(Icons.ROSE.getImageIcon()); 
                break;
            case SUNFLOWER:
                setIcon(Icons.SUNFLOWER.getImageIcon()); 
                break;
            case TURNIPS:
                setIcon(Icons.TURNIPS.getImageIcon()); 
                break;
            default:
                break;
        }
    }

    /**
     * changes the color when selected
     */
    void selectButton(){
        setBackground(Palette.SELECTED.getColor());
    }

    /**
     * changes the color back to deselected
     */
    void deselectButton(){
        setBackground(Palette.SEED_SLOT.getColor());
    }

}
