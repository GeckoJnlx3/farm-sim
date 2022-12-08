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
    private int nRow; 
    private int nCol;

    public Plot(LandState landState, int nRow, int nCol, MyFarmModel model, MyFarmView view  ){
        this.nRow = nRow;
        this.nCol = nCol;
        
        setPlotView(landState, null);

        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.land.landState[nRow][nCol] == LandState.HARVESTABLE)
                    model.player.harvestCrop(model, view, nRow, nCol);
                else if (view.rightPanel.hoe.getText().equals("selected")) {
                    model.player.plowLand(model, view, nRow, nCol);
                    view.rightPanel.hoe.setText("hoe");
                }
                else if (view.rightPanel.wateringCan.getText().equals("selected")) {
                    model.player.waterPlant(model, view, nRow, nCol);
                    view.rightPanel.wateringCan.setText("watering can");
                }
                else if (view.rightPanel.shovel.getText().equals("selected")) {
                    model.player.removePlant(model, view, nRow, nCol);
                    view.rightPanel.shovel.setText("shovel");
                } 
                else if (view.rightPanel.pickaxe.getText().equals("selected")){
                    model.player.removeRock(model, view, nRow, nCol);
                    view.rightPanel.pickaxe.setText("pickaxe");
                }
                else if (view.rightPanel.fertilizer.getText().equals("selected")){
                    model.player.fertilizeCrop(model, view, nRow, nCol);
                    view.rightPanel.fertilizer.setText("fertilizer");
                }
                else if (checkSelectedSeed(view) != null){ // if a seed button is selected
                    String selectedCropName = checkSelectedSeed(view).getCropName();

                    model.player.plantSeed(model, view, nRow, nCol, selectedCropName);
                    deselect(view);
                }
                else if (model.land.landState[nRow][nCol] == LandState.PLANTED){
                    model.player.viewCropInfo(model, view, nRow, nCol);
                }
            }
        });

    }

    public void setPlotView(LandState landState, Crop crop){
        this.setFocusable(false);
        this.setBackground(Palette.UNWATERED_PLOT.getColor());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        switch (landState){
            case BLOCKED: 
                this.setIcon(Icons.BLOCKED.getImageIcon());
                break;
            case PLOWED:
                this.setIcon(Icons.PLOWED.getImageIcon());
                break;
            case PLANTED:
                this.setIcon(Icons.SEEDLING.getImageIcon());
                if (crop.getWaterAmt() == crop.cropType.getWaterBonus())
                    this.setBackground(Palette.WATERED_PLOT.getColor());
                if (crop.getFertilizerAmt() == crop.cropType.getFertilizerBonus())
                    this.setBorder(BorderFactory.createLineBorder(Palette.FERTILIZED_PLOT.getColor(), 3));
                break;
            case HARVESTABLE:
                this.setPlantIcon(crop.cropType);
                break;
            case UNPLOWED:
                this.setIcon(Icons.UNPLOWED.getImageIcon());
            default:
                this.setBackground(Palette.UNWATERED_PLOT.getColor()); //brown
                break;
        }
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

    public CropType checkSelectedSeed(MyFarmView view)
    {
        if (view.rightPanel.seedTurnip.getBackground().equals(Palette.SELECTED.getColor()))
            return CropType.TURNIP;
        else if (view.rightPanel.seedCarrot.getBackground().equals(Palette.SELECTED.getColor()))
            return CropType.CARROT;
        else if (view.rightPanel.seedPotato.getBackground().equals(Palette.SELECTED.getColor()))
            return CropType.POTATO;
        else if (view.rightPanel.seedRose.getBackground().equals(Palette.SELECTED.getColor()))
            return CropType.ROSE;
        else if (view.rightPanel.seedSunflower.getBackground().equals(Palette.SELECTED.getColor()))
            return CropType.SUNFLOWER;
        else if (view.rightPanel.seedTurnips.getBackground().equals(Palette.SELECTED.getColor()))
            return CropType.TURNIPS;
        else if (view.rightPanel.seedApple.getBackground().equals(Palette.SELECTED.getColor()))
            return CropType.APPLE;
        else if (view.rightPanel.seedMango.getBackground().equals(Palette.SELECTED.getColor()))
            return CropType.MANGO;

        return null;
    }

    public void deselect(MyFarmView view){ // THIS FUNC IS SO INEFFICIENT BUT MY HEAD HURTSKJDK optimize this later
        if (view.rightPanel.seedTurnip.getBackground().equals(Palette.SELECTED.getColor()))
            view.rightPanel.seedTurnip.setBackground(Palette.SEED_SLOT.getColor());

        else if (view.rightPanel.seedCarrot.getBackground().equals(Palette.SELECTED.getColor()))
            view.rightPanel.seedCarrot.setBackground(Palette.SEED_SLOT.getColor());

        else if (view.rightPanel.seedPotato.getBackground().equals(Palette.SELECTED.getColor()))
            view.rightPanel.seedPotato.setBackground(Palette.SEED_SLOT.getColor());

        else if (view.rightPanel.seedRose.getBackground().equals(Palette.SELECTED.getColor()))
            view.rightPanel.seedRose.setBackground(Palette.SEED_SLOT.getColor());

        else if (view.rightPanel.seedSunflower.getBackground().equals(Palette.SELECTED.getColor()))
            view.rightPanel.seedSunflower.setBackground(Palette.SEED_SLOT.getColor());

        else if (view.rightPanel.seedTurnips.getBackground().equals(Palette.SELECTED.getColor()))
            view.rightPanel.seedTurnips.setBackground(Palette.SEED_SLOT.getColor());

        else if (view.rightPanel.seedApple.getBackground().equals(Palette.SELECTED.getColor()))
            view.rightPanel.seedApple.setBackground(Palette.SEED_SLOT.getColor());

        else if (view.rightPanel.seedMango.getBackground().equals(Palette.SELECTED.getColor()))
            view.rightPanel.seedMango.setBackground(Palette.SEED_SLOT.getColor());
    }
}
