package MyFarm;

enum BtnIndex{
    //INDEXES FOR TOOLBUTTON
    WATERING_CAN (0), 
    PICKAXE(1),
    SHOVEL(2),
    HOE(3),
    FERTILIZER(4),
    
    //INDEXES FOR SEED BUTTON
    TURNIP(0),
    CARROT(1),
    POTATO(2),
    ROSE(3),
    SUNFLOWER(4),
    TURNIPS(5),
    MANGO(6),
    APPLE(7);

    final int index;

    private BtnIndex(int index){
        this.index = index;
    }
}