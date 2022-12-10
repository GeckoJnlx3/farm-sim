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
        
        setPlotView(model.player, landState, null);

        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.land.landState[nRow][nCol] == LandState.HARVESTABLE)
                    model.player.harvestCrop(model, view, nRow, nCol);
                else if (view.rightPanel.toolButtonList.get(BtnIndex.HOE.index).getText().equals("selected")) {
                    model.player.plowLand(model, view, nRow, nCol);
                }
                else if (view.rightPanel.toolButtonList.get(BtnIndex.WATERING_CAN.index).getText().equals("selected")) {
                    model.player.waterPlant(model, view, nRow, nCol);
                }
                else if (view.rightPanel.toolButtonList.get(BtnIndex.SHOVEL.index).getText().equals("selected")) {
                    model.player.removePlant(model, view, nRow, nCol);
                } 
                else if (view.rightPanel.toolButtonList.get(BtnIndex.PICKAXE.index).getText().equals("selected")){
                    model.player.removeRock(model, view, nRow, nCol);
                }
                else if (view.rightPanel.toolButtonList.get(BtnIndex.FERTILIZER.index).getText().equals("selected")){
                    model.player.fertilizeCrop(model, view, nRow, nCol);
                }
                else if (checkSelectedSeed(view) != null){ // if a seed button is selected
                    String selectedCropName = checkSelectedSeed(view).getCropName();

                    model.player.plantSeed(model, view, nRow, nCol, selectedCropName);
                }
                else if (model.land.landState[nRow][nCol] == LandState.PLANTED){
                    model.player.viewCropInfo(model, view, nRow, nCol);
                }
                model.player.levelUp(model, view);
            }
        });

    }

    public void setPlotView(Player p1, LandState landState, Crop crop){
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
                if (crop.getWaterAmt() == crop.cropType.getWaterBonus() + p1.getTitle().getWaterBonusLimitIncrease())
                    this.setBackground(Palette.WATERED_PLOT.getColor());
                if (crop.getFertilizerAmt() == crop.cropType.getFertilizerBonus() + p1.getTitle().getFertBonusLimit())
                    this.setBorder(BorderFactory.createLineBorder(Palette.FERTILIZED_PLOT.getColor(), 3));
                break;
            case HARVESTABLE:
                this.setPlantIcon(crop.cropType);
                break;
            case UNPLOWED:
                this.setIcon(Icons.UNPLOWED.getImageIcon());
                break;
            case WITHERED:
                this.setIcon(Icons.WITHERED.getImageIcon());
                break;
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

    private CropType checkSelectedSeed(MyFarmView view)
    {
        for (SeedButton s : view.rightPanel.seedButtonList){
            if (s.getBackground().equals(Palette.SELECTED.getColor()))
                return s.crop;
        }
        return null;
    }
}
