package MyFarm.crop;

public enum CropType {
    //All the Types of crops
    EMPTY("", "", 0,0,0,0,0,0,0,0,0,0),
	TURNIP("root", "turnip", 2, 1, 2, 0, 1, 1, 2, 5, 6, 5),
    CARROT("root", "carrot", 3, 1, 2, 0, 1, 1, 2, 10, 9, 7.5),
    POTATO("root", "potato", 5, 3, 4, 1, 2, 1, 10, 20, 3, 12.5),
    ROSE("flower", "rose", 1, 1, 2, 0, 1, 1, 1, 5, 5, 2.5),
    TURNIPS("flower", "turnips", 2, 2, 3, 0, 1, 1, 1, 10, 9, 5),
    SUNFLOWER("flower", "sunflower", 3, 2, 3, 1, 2, 1, 1, 20, 19, 7.5),
    MANGO("tree", "mango", 10, 7, 7, 4, 4, 5, 15, 100, 8, 25),
    APPLE("tree", "apple", 10, 7, 7, 5, 5, 10, 15, 200, 5, 25);

    //Crop Data
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

    /**
     * Adds crop data to the crop type
     * @param cropCategory      crop category
     * @param cropName          name of the crop
     * @param maxAge            the harvest age of the crop
     * @param waterMin          minimum amount of water required
     * @param waterBonus        maximum amount of water that can be given
     * @param fertilizerMin     minimum amount of fertilizer required
     * @param fertilizerBonus   maximum amount of fertilizer that can be given
     * @param produceMin        minimum amount of produced crop 
     * @param produceMax        maximum amount of produced crop
     * @param cropCost          purchase price of the crop
     * @param sellPrice         sell price of the crop
     * @param expYield          amount of xp received from harvesting crop
     */
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

    /**
     * getter for cropName
     * @return cropName
     */
    public String getCropName(){
        return cropName;
    }

    /**
     * getter for waterBonus
     * @return waterBonus
     */
    public int getWaterBonus(){
        return this.waterBonus;
    }

    /**
     * getter for fertilizerBonus
     * @return fertilizerBonus
     */
    public int getFertilizerBonus(){
        return this.fertilizerBonus;
    }
}
