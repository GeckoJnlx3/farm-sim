package MyFarm;

import javax.swing.ImageIcon;

public enum Icons {
    PLOWED("src/MyFarm/icons/plowed.png"),
    UNPLOWED("src/MyFarm/icons/unplowed.png"),
    SEEDLING("src/MyFarm/icons/seedling.png"),
    WITHERED("src/MyFarm/icons/withered.png"),
    OBJECTCOINS("src/MyFarm/icons/objectcoins.png"),
    XP("src/MyFarm/icons/xp.png"),
    LVL("src/MyFarm/icons/lvl.png"),
    TITLE("src/MyFarm/icons/title.png"),
    SHOP("src/MyFarm/icons/shop.png"),
    PLAYER("src/MyFarm/icons/player.png"),
    DAY("src/MyFarm/icons/day.png"),
    TURNIP("src/MyFarm/icons/turnip.png");


    private final ImageIcon btnImage;

    private Icons(String filename) {
        this.btnImage = new ImageIcon(filename);
    }

    public ImageIcon getImageIcon(){
        return this.btnImage;
    }

}
