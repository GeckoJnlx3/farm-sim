package MyFarm;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MyFarm.crop.Crop;
import MyFarm.crop.CropType;
import MyFarm.land.LandState;

public class SeedButton extends JButton {
    
    public SeedButton(Icons icon, CropType crop, MyFarmModel model, MyFarmView view, int i, int j){
        setSeedIcon(crop);
        setBackground(Palette.SEED_SLOT.getColor());
        setFocusable(false);

        addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                model.land.crops[i][j] = new Crop(crop.getCropName());
                model.land.landState[i][j] = LandState.PLANTED;
                view.centerPanel.plotBtn[i][j].setIcon(Icons.SEEDLING.getImageIcon());
    
                model.player.setCoins(model.player.getCoins() - 5);
    
                view.leftPanel.initializeGameInfo(model.player);
                view.bottomPanel.playerAction.setText("You planted a "+crop.getCropName()+" .");
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
