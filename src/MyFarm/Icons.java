package MyFarm;

import javax.swing.ImageIcon;

public enum Icons {
    //land images (needs rock)
    PLOWED("src/MyFarm/icons/plowed.png"),
    UNPLOWED("src/MyFarm/icons/unplowed.png"),
    BLOCKED("src/MyFarm/icons/blocked.png"),

    //image of plants growing
    SEEDLING("src/MyFarm/icons/seedling.png"),
    WITHERED("src/MyFarm/icons/withered.png"),

    //player related stats
    OBJECTCOINS("src/MyFarm/icons/objectcoins.png"),
    XP("src/MyFarm/icons/xp.png"),
    LVL("src/MyFarm/icons/lvl.png"),
    TITLE("src/MyFarm/icons/title.png"),
    SHOP("src/MyFarm/icons/shop.png"),
    PLAYER("src/MyFarm/icons/player.png"),
    DAY("src/MyFarm/icons/day.png"),
    
    //plant types
    TURNIP("src/MyFarm/icons/turnip.png"), 
    APPLE("src/MyFarm/icons/apple.png"), 
    CARROT("src/MyFarm/icons/carrot.png"), 
    MANGO("src/MyFarm/icons/mango.png"), 
    POTATO("src/MyFarm/icons/potato.png"), 
    ROSE("src/MyFarm/icons/rose.png"), 
    SUNFLOWER("src/MyFarm/icons/sunflower.png"), 
    TURNIPS("src/MyFarm/icons/turnips.png");


    private final ImageIcon btnImage;

    private Icons(String filename) {
        this.btnImage = new ImageIcon(filename);
    }

    public ImageIcon getImageIcon(){
        return this.btnImage;
    }

}
