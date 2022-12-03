package MyFarm;

import java.awt.Color;

public enum Palette {
    WHITE(0xFFFFFF), 
    GRASS(0xC0E5C8),
    SEED_SLOT(0xAAE29F),
    UNWATERED_PLOT(0x9F8C83),
    ROCK(0xBABABA),
    WATERED_PLOT(0x654f2b),
    BOTTOM_PANEL(0x5D5D5D);    

    private final Color color;

    private Palette(int color){
        this.color = new Color(color);
    }

    public Color getColor(){
        return this.color;
    }
}
