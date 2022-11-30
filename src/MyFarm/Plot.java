package MyFarm;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import MyFarm.crop.Crop;
import MyFarm.crop.CropType;
import MyFarm.land.LandState;

public class Plot extends JButton{
    int x; 
    int y;

    public Plot(LandState landState, int x, int y  ){
        setPlotView(landState, null);
        this.setFocusable(false);

        this.x = x;
        this.y = y;
    }

    public void setPlotView(LandState landState, Crop crop){
        switch (landState){
            case BLOCKED: 
                this.setBackground(Palette.ROCK.getColor());
                break;
            case PLOWED:
                this.setIcon(new ImageIcon("src/MyFarm/icons/plowed.png"));
                break;
            case PLANTED:
                if (crop.getWaterAmt() == crop.cropType.waterBonus)
                    this.setBackground(Palette.WATERED_PLOT.getColor());
                    
                setPlantIcon(crop.cropType);
            case UNPLOWED:
                this.setIcon(new ImageIcon("src/MyFarm/icons/unplowed.png"));
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

}
