package MyFarm.crop;

public enum CropType {
    EMPTY("", "", 0,0,0,0,0,0,0,0,0,0),
	TURNIP("root", "turnip", 2, 1, 2, 0, 1, 1, 2, 5, 6, 5),
    CARROT("root", "carrot", 3, 1, 2, 0, 1, 1, 2, 10, 9, 7.5),
    POTATO("root", "potato", 5, 3, 4, 1, 2, 1, 10, 20, 3, 12.5),
    ROSE("flower", "rose", 1, 1, 2, 0, 1, 1, 1, 5, 5, 2.5),
    TURNIPS("flower", "turnips", 2, 2, 3, 0, 1, 1, 1, 10, 9, 5),
    SUNFLOWER("flower", "sunflower", 3, 2, 3, 1, 2, 1, 1, 20, 19, 7.5),
    MANGO("tree", "mango", 10, 7, 7, 4, 4, 5, 15, 100, 8, 25),
    APPLE("tree", "apple", 10, 7, 7, 5, 5, 10, 15, 200, 5, 25);

    final String cropCategory;
    final String cropName;
    final int maxAge; // AKA Harvest Time
    final int waterMin;
    final int waterBonus;
    final int fertilizerMin;
    final int fertilizerBonus;
    final int produceMin;
    final int produceMax;
    final double cropCost;
    final double sellPrice;
    final double expYield;

    CropType(String cropCategory, String cropName, int maxAge, int waterMin, int waterBonus, 
    		int fertilizerMin, int fertilizerBonus, int produceMin, int produceMax, 
    		double cropCost, double sellPrice, double expYield) {
        this.cropCategory = cropCategory;
        this.cropName = cropName;
        this.maxAge = maxAge;
        this.waterMin = waterMin;
        this.waterBonus = waterBonus;
        this.fertilizerMin = fertilizerMin;
        this.fertilizerBonus = fertilizerBonus;
        this.produceMin = produceMin;
        this.produceMax = produceMax;
        this.cropCost = cropCost;
        this.sellPrice = sellPrice;
        this.expYield = expYield;
    }

    public String getCropName(){
        return cropName;
    }

    public int getWaterBonus(){
        return this.waterBonus;
    }

    public int getFertilizerBonus(){
        return this.fertilizerBonus;
    }
}
