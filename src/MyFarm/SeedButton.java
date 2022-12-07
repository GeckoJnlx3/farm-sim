package MyFarm;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MyFarm.crop.CropType;

public class SeedButton extends JButton {

    String name;

    public SeedButton(CropType crop){

        this.name = crop.getCropName();

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
}
