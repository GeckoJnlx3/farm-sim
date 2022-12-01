package MyFarm;

import javax.swing.ImageIcon;
import javax.swing.JButton;

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
                else if(model.land.landState[nRow][nCol] == LandState.PLOWED && 
                    !view.rightPanel.wateringCan.getText().equals("selected"))
                    view.rightPanel.cardLayout.next(view.rightPanel.rightCardPanel);
                }
        });

    }

    public void setPlotView(LandState landState, Crop crop){
        this.setFocusable(false);
        switch (landState){
            case BLOCKED: 
                this.setBackground(Palette.ROCK.getColor());
                break;
            case PLOWED:
                this.setIcon(Icons.PLOWED.getImageIcon());
                break;
            case PLANTED:
                if (crop.getWaterAmt() == crop.cropType.waterBonus)
                    this.setBackground(Palette.WATERED_PLOT.getColor());
                    
                setPlantIcon(crop.cropType);
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
                this.setIcon(new ImageIcon("src/MyFarm/icon/turnip.png"));
                break;
            case CARROT:
                this.setIcon(new ImageIcon("src/MyFarm/icon/carrot.png"));
                break;
            case POTATO:
                this.setIcon(new ImageIcon("src/MyFarm/icon/potato.png"));
                break;
            case ROSE:
                this.setIcon(new ImageIcon("src/MyFarm/icon/rose.png"));
                break;            
            case TURNIPS:
                this.setIcon(new ImageIcon("src/MyFarm/icon/turnips.png"));
                break;
            case SUNFLOWER:
                this.setIcon(new ImageIcon("src/MyFarm/icon/sunflower.png"));
                break;            
            case MANGO:
                this.setIcon(new ImageIcon("src/MyFarm/icon/mango.png"));
                break;
            case APPLE:
                this.setIcon(new ImageIcon("src/MyFarm/icon/apple.png"));
                break;    
            default:
                break;        
        }
    }

    public int getnRow(){
        return nRow;
    }

    public int getnCol(){
        return nCol;
    }
}
