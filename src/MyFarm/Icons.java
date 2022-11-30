package MyFarm;

import javax.swing.ImageIcon;

public enum Icons {
    PLOWED("src/MyFarm/icons/plowed.png"),
    UNPLOWED("src/MyFarm/icons/unplowed.png"),
    SEEDLING("src/MyFarm/icons/seedling.png"),
    WITHERED("src/MyFarm/icons/withered.png");


    private final ImageIcon btnImage;

    private Icons(String filename) {
        this.btnImage = new ImageIcon(filename);
    }

    public ImageIcon getImageIcon(){
        return this.btnImage;
    }

}
