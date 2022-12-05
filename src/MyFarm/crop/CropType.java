package MyFarm.crop;

/**
 * Enum class to store all crop types 
 * Contains all information for the crops
 */

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


    private final String cropCategory;
    private final String cropName;
    private final int maxAge; // AKA Harvest Time
    private final int waterMin;
    private final int waterBonus;
    private final int fertilizerMin;
    private final int fertilizerBonus;
    private final int produceMin;
    private final int produceMax;
    private final double cropCost;
    private final double sellPrice;
    private final double expYield;

    /**
     * Constructor for CropType. Initializes all the 
     * infomration for each crop
     * @param cropCategory      the crop category (root, flower, tree)
     * @param cropName          the name of the crop
     * @param maxAge            the harvest age of the crop
     * @param waterMin          the minimum amount of water the plant should 
     *                          receive
     * @param waterBonus        the maximum amount of water the plant can receive
     * @param fertilizerMin     the minimum amount of fertilizer the plant 
     *                          should receive
     * @param fertilizerBonus   the maximum amount of fertilizer the plant can 
     *                          receive
     * @param produceMin        the minimum amount of crops produced
     * @param produceMax        the maximum amount of crops produced
     * @param cropCost          the price of the crop when bought
     * @param sellPrice         the earnings a player gets from a single yield 
     *                          of the crop
     * @param expYield          the amount of experience a pleyer earns from 
     *                          harvesting the plant
     */
    CropType(String cropCategory, String cropName, int maxAge, int waterMin, 
            int waterBonus, int fertilizerMin, int fertilizerBonus, 
            int produceMin, int produceMax, double cropCost, double sellPrice, 
            double expYield) {
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
     * getter for cropCategory
     * @return cropType's cropCategory
     */
    public String getCropCategory(){
        return cropCategory;
    }

    /**
     * getter for cropName
     * @return cropType's cropName
     */
    public String getCropName(){
        return cropName;
    }

    /**
     * getter for maxAge
     * @return cropType's maxAge
     */
    public int getMaxAge(){
        return maxAge;
    }

    /**
     * getter for waterMin
     * @return cropType's waterMin
     */
    public int getWaterMin(){
        return waterMin;
    }

    /**
     * getter for waterBonus
     * @return cropType's waterBonus
     */
    public int getWaterBonus(){
        return waterBonus;
    }

    /**
     * getter for fertilizerMin
     * @return cropType's fertilizerMin
     */
    public int getFertilizerMin(){
        return fertilizerMin;
    }

    /**
     * getter for fertilizerBonus
     * @return cropType's fertilizerBonus
     */
    public int getFertilizerBonus(){
        return fertilizerBonus;
    }
    
    /**
     * getter for produceMin
     * @return cropType's produceMin
     */
    public int getProduceMin(){
        return produceMin;
    }
    
    /**
     * getter for produceMax
     * @return cropType's produceMax
     */
    public int getProduceMax(){
        return produceMax;
    }
    
    /**
     * getter for cropCost
     * @return cropType's cropCost
     */
    public double getCropCost(){
        return cropCost;
    }

    /**
     * getter for sellPrice
     * @return cropType's sellPrice
     */
    public double getSellPrice(){
        return sellPrice;
    }

    /**
     * getter for expYield
     * @return cropType's expYield
     */
    public double getExpYield(){
        return expYield;
    }
}
