enum CropType {
    TURNIP("root", 2, 1, 2, 0, 1, 1, 2,
            5, 6, 5),
    CARROT("root", 3, 1, 2, 0, 1, 1, 2,
            10, 9, 7.5);
    POTATO("root", 5, 3, 4, 1, 2, 1, 10, 20, 3, 12.5),
    ROSE("flower", 1, 1, 2, 0, 1, 1, 1, 5, 5, 2.5),
    TURNIPS("flower", 2, 2, 3, 0, 1, 1, 1, 10, 9, 5),
    SUNFLOWER("flower", 3, 2, 3, 1, 2, 1, 1, 20, 19, 7.5),
    MANGO("tree", 10, 7, 7, 4, 4, 5, 15, 100, 8, 25),
    APPLE("tree", 10, 7, 7, 5, 5, 10, 15, 200, 5, 25);


    final private String cropCategory;
    final private int maxAge; // AKA Harvest Time
    final private int waterMin;
    final private int waterBonus;
    final private int fertilizerMin;
    final private int fertilizerBonus;
    final private int produceMin;
    final private int produceMax;
    final private int cropCost;
    final private int sellPrice;
    final private double expYield;

    CropType(String cropCategory, int maxAge, int waterMin, int waterMax, int fertilizerMin, int fertilizerBonus,
             int produceMin, int produceMax, double cropCost, double sellPrice, double expYield) {
        this.cropCategory = cropCategory;
        this.maxAge = maxAge;
        this.waterMin = waterMin;
        this.waterMax = waterMax;
        this.fertilizerMin = fertilizerMin;
        this.fertilizerMax = fertilizerMax;
        this.produceMin = produceMin;
        this.produceMax = produceMax;
        this.cropCost = cropCost;
        this.sellPrice = sellPrice;
        this.expYield = expYield;
    }
}
