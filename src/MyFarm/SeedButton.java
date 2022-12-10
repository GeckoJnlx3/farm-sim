package MyFarm;

import javax.swing.JButton;
import java.awt.*;

import MyFarm.crop.CropType;

public class SeedButton extends JButton {

    CropType crop;

    public SeedButton(CropType crop){
        this.crop = crop;
        
        setFocusable(false);
        setSeedIcon(crop);
        setBackground(Palette.SEED_SLOT.getColor());
        setFocusable(false);
        setPreferredSize(new Dimension(50,50));
    }

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

    void selectButton(){
        setBackground(Palette.SELECTED.getColor());
    }

    void deselectButton(){
        setBackground(Palette.SEED_SLOT.getColor());
    }

}
