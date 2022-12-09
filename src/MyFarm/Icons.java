package MyFarm;

import javax.swing.ImageIcon;

public enum Icons {
    //land images (needs rock)
    PLOWED("farm-sim/src/MyFarm/icons/plowed.png"),
    UNPLOWED("farm-sim/src/MyFarm/icons/unplowed.png"),
    BLOCKED("farm-sim/src/MyFarm/icons/rock.png"),

    //image of plants growing
    SEEDLING("farm-sim/src/MyFarm/icons/seedling.png"),
    WITHERED("farm-sim/src/MyFarm/icons/withered.png"),

    //player related stats
    OBJECTCOINS("farm-sim/src/MyFarm/icons/objectcoins.png"),
    XP("farm-sim/src/MyFarm/icons/xp.png"),
    LVL("farm-sim/src/MyFarm/icons/lvl.png"),
    TITLE("farm-sim/src/MyFarm/icons/title.png"),
    SHOP("farm-sim/src/MyFarm/icons/shop.png"),
    PLAYER("farm-sim/src/MyFarm/icons/player.png"),
    DAY("farm-sim/src/MyFarm/icons/day.png"),
    
    //plant types
    TURNIP("farm-sim/src/MyFarm/icons/turnip.png"),
    APPLE("farm-sim/src/MyFarm/icons/apple.png"),
    CARROT("farm-sim/src/MyFarm/icons/carrot.png"),
    MANGO("farm-sim/src/MyFarm/icons/mango.png"),
    POTATO("farm-sim/src/MyFarm/icons/potato.png"),
    ROSE("farm-sim/src/MyFarm/icons/rose.png"),
    SUNFLOWER("farm-sim/src/MyFarm/icons/sunflower.png"),
    TURNIPS("farm-sim/src/MyFarm/icons/turnips.png"),
    GAME_OVER("farm-sim/src/MyFarm/icons/game_over.png");


    private final ImageIcon btnImage;

    private Icons(String filename) {
        this.btnImage = new ImageIcon(filename);
    }

    public ImageIcon getImageIcon(){
        return this.btnImage;
    }

}
