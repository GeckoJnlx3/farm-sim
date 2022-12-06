package MyFarm;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MyFarm.crop.Crop;
import MyFarm.crop.CropType;
import MyFarm.land.LandState;

public class SeedButton extends JButton {
    
    public SeedButton(CropType crop, MyFarmModel model, MyFarmView view){
        setSeedIcon(crop);
        setBackground(Palette.SEED_SLOT.getColor());
        setFocusable(false);
        setPreferredSize(new Dimension(50,50));

        addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // reimplement with model and view in a bit

                model.land.crops[2][2] = new Crop(crop.getCropName());
                model.land.landState[2][2] = LandState.PLANTED;
                view.centerPanel.plotBtn[2][2].setIcon(Icons.SEEDLING.getImageIcon());

                model.player.setCoins(model.player.getCoins() - 5);

                view.leftPanel.initializeGameInfo(model.player);
                view.bottomPanel.playerAction.setText("You planted a(n) "+crop.getCropName()+" .");
                view.rightPanel.cardLayout.next(view.rightPanel.rightCardPanel);
            }
        });
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
