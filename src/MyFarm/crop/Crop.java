package MyFarm.crop;

/**
 * Crop class - object that is planted
 * on a plot of land.
 * Requires a TextLand tile in the PLOWED
 * state in order to be utilized.
 */

public class Crop
{
    // These are variables inherent to each crop category
    public CropType cropType;

    // Variables that are set to the same default value for all crops
    private int age;
    private int waterAmt;
    private int fertilizerAmt;
    private boolean isWithered;
    private boolean isHarvestable;
    // TO DO: Create getter class for Farmer title
    private int farmerEarningTypeBonus = 0; 

    /**
     * Constructor for Crop class.
     * @param cropName crop name that determines
     *                 what type of crop should
     *                 be initialized.
     */
    public Crop(String cropName) {
        this.age = 0;
        this.waterAmt = 0;
        this.fertilizerAmt = 0;
        this.isHarvestable = false;
        this.isWithered = false;

        switch (cropName) {
            case "Turnip":
                this.cropType = CropType.TURNIP;
                break;
            case "Carrot":
                this.cropType = CropType.CARROT;
                break;
            case "Potato":
                this.cropType = CropType.POTATO;
                break;
            case "Rose":
                this.cropType = CropType.ROSE;
                break;
            case "Turnips":
                this.cropType = CropType.TURNIPS;
                break;
            case "Sunflower":
                this.cropType = CropType.SUNFLOWER;
                break;
            case "Mango":
                this.cropType = CropType.MANGO;
                break;
            case "Apple":
                this.cropType = CropType.APPLE;
                break;
            default:
                this.cropType = CropType.EMPTY;
                break;
        }
    }
    
    /**
     * getter method for age
     * @return crop's age
     */
    public int getAge()
    {
        return this.age;
    }

    /**
     * getter method for maxAge
     * @return crop's maxAge
     */
    public int getMaxAge()
    {
        return this.cropType.getMaxAge();
    }

    /**
     * getter method for isWithered
     * @return crop's isWithered
     */
    public boolean getWitherStatus()
    {
        return this.isWithered;
    }

    /**
     * getter method for isHarvestable
     * @return crop's isHarvestable
     */
    public boolean getHarvestStatus()
    {
        return this.isHarvestable;
    }
    
    /**
     * getter method for waterAmt
     * @return crop's waterAmt
     */
	public int getWaterAmt() {
		return this.waterAmt;
	}
	
    /**
     * getter method for waterAmt
     * @return crop's waterAmt
     */
	public double getCropCost() {
		return this.cropType.getCropCost();
	}

    /**
     * Increases the water amount as long as
     * it has not exceeded its maximum value.
     * @return boolean which determines if the
     *         crop was successfully watered.
     */
    public boolean increaseWaterAmt() {
    	boolean isValidAction = this.waterAmt < this.cropType.getWaterBonus() ? 
        true : false;
        
        if (isValidAction) 
    		this.waterAmt++;

        return isValidAction;
    }

    /**
     * Increases the fertilizer amount as long as
     * it has not exceeded its maximum value.
     * @return boolean which determines if the
     *         crop was successfully watered.
     */
    public boolean increaseFertAmt(double objectCoins) {
    	boolean isValidAction = this.fertilizerAmt < this.cropType.getFertilizerBonus() 
        && objectCoins >= 4 ? true : false;
        
        if (isValidAction) 
    		this.fertilizerAmt++;

        return isValidAction;
    }

    /**
     * Ages the crop by one day.
     */
    public void updatePlantStage()
    {
        this.age = age + 1;
    }

    /**
     * Updates the crop's withered and harvestable
     * status by checking its age.
     */
    public void checkCropStatus()
    {
        this.isHarvestable = age == cropType.getMaxAge() ? true : false;
        this.isWithered = age > cropType.getMaxAge() || 
        (age == cropType.getMaxAge() && waterAmt < cropType.getWaterMin()) ? 
        true : false;
    }

    /**
     * Generates the amount of crop harvested
     * @return the amount of crop produced
     */
    public int generateYield()
    {
        return cropType.getProduceMin() + (int)(Math.random() * 
        ((cropType.getProduceMax() - cropType.getProduceMin()) + 1));
    }

    /**
     * Generates the sell price of 
     * all crops produced
     * @return the sell price of crop produced
     */
    public double computeHarvestTotal()
    {
        return generateYield() * (cropType.getSellPrice() + 
        farmerEarningTypeBonus);
    }

    /**
     * Generates the amount of money
     * earned from water bonus
     * @return the bonus earned from water
     * bonus 
     */
    public double computeWaterBonus()
    {
        double i = computeHarvestTotal();

        return i * 0.2 * (waterAmt - 1);
    }

    /**
     * Generates the amount of money
     * earned from fertilizer bonus
     * @return the bonus earned from 
     * fertilizer bonus 
     */
    public double computeFertilizerBonus()
    {
        double i = computeHarvestTotal();

        return i * 0.5 * fertilizerAmt;
    }

    /**
     * Computes the final yield of the crop
     * in terms of objectcoins, inclusive of
     * farmer title bonus.
     * @return crop's final harvest price.
     */
    public double computeHarvestEarnings()
    {
        double a = computeHarvestTotal();
        double b = computeWaterBonus();
        double c = computeFertilizerBonus();

        if (this.cropType.getCropCategory().equals("flower"))
            return (a + b + c) * 1.1;

        return a + b + c;
    }

    public int getFertilizerAmt() {
        return fertilizerAmt;
    }
}

