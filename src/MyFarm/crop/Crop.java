package MyFarm.crop;

import MyFarm.Title;

import java.util.Random;

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
    private int producedAmt;

    //random generator
    Random random = new Random();

    /**
     * intializes crop to have the default set values
     * @param cropName inputted string to determine the crop type
     */
    public Crop(String cropName) {
        this.age = 0;
        this.waterAmt = 0;
        this.fertilizerAmt = 0;
        this.isHarvestable = false;
        this.isWithered = false;

        setCropType(cropName);

        if (cropType != CropType.EMPTY)
            this.producedAmt = cropType.produceMin +(random.nextInt(this.cropType.produceMax) - this.cropType.produceMin + 1);
    }

    /**
     * getter for current age
     * @return current age
     */
    public int getAge()
    {
        return this.age;
    }

    /**
     * getter for harvest age
     * @return maxAge
     */
    public int getMaxAge()
    {
        return this.cropType.maxAge;
    }

    /**
     * getter for wither status
     * @return isWithered
     */
    public boolean getWitherStatus()
    {
        return this.isWithered;
    }

    /**
     * getter for harvest status
     * @return isHarvestable
     */
    public boolean getHarvestStatus()
    {
        return this.isHarvestable;
    }
    
    /**
     * getter for amount of times plant has been watered
     * @return waterAmt
     */
	public int getWaterAmt() {
		return this.waterAmt;
	}

    /**
     * getter for amount of times plant has been watered
     * @return waterAmt
     */
    public int getFertilizerAmt() {
        return this.fertilizerAmt;
    }
	
    /**
     * getter for crop cost
     * @return cropCost
     */
	public double getCropCost() {
		return this.cropType.cropCost;
	}

    /**
     * getter for expYield
     * @return expYield
     */
    public double getExpYield(){
        return this.cropType.expYield;
    }

    /**
     * getter for amount of crop produced
     * @return producedAmt
     */
    public int getProducedAmt(){
        return this.producedAmt;
    }

    /**
     * generates the amount of crop that will be produced
     */
    private int generateProducedAmt(){
        int randomRange = (random.nextInt(this.cropType.produceMax) - this.cropType.produceMin + 1);
        return this.cropType.produceMin + randomRange;
    }

    /**
     * sets the crop type based on the type of crop
     * @param cropName string that will be used to know what crop is being 
     * planted
     */
    private void setCropType(String cropName){
        switch (cropName) {
            case "turnip":
                this.cropType = CropType.TURNIP;
                break;
            case "carrot":
                this.cropType = CropType.CARROT;
                break;
            case "potato":
                this.cropType = CropType.POTATO;
                break;
            case "rose":
                this.cropType = CropType.ROSE;
                break;
            case "turnips":
                this.cropType = CropType.TURNIPS;
                break;
            case "sunflower":
                this.cropType = CropType.SUNFLOWER;
                break;
            case "mango":
                this.cropType = CropType.MANGO;
                break;
            case "apple":
                this.cropType = CropType.APPLE;
                break;
            default:
                this.cropType = CropType.EMPTY;
                break;
        }
    }

    /**
     * Waters the plant and returns a boolean if the water amount increased
     * @param title increases the bonus limit of the plant
     * @return true if the plant is watered and false if not
     */
    public boolean increaseWaterAmt(Title title) {
        int currWaterBonus = this.cropType.waterBonus + title.getWaterBonusLimitIncrease();
        boolean isValidAction = this.waterAmt < currWaterBonus;
        
        if (isValidAction) 
    		this.waterAmt++;

        return isValidAction;
    }

    /**
     * Fertilizes the plant and returns a boolean if the fertilize amount 
     * increased.
     * @param objectCoins checks if there is enough coins
     * @param title increases the fertilize limit
     * @return true if the plant is fertilized, false if not
     */
    public boolean increaseFertAmt(double objectCoins, Title title) {
    	int currFertBonus = this.cropType.fertilizerBonus + title.getFertBonusLimit();
        boolean isValidAction = this.fertilizerAmt < currFertBonus && objectCoins >= 4;
        
        if (isValidAction) 
    		this.fertilizerAmt++;

        return isValidAction;
    }

    /**
     * increases the plant's age by one
     */
    public void updatePlantStage()
    {
        this.age = age + 1;
    }

    /**
     * updates isHarvestable and isWithered (done after advancing a day)
     */
    public void checkCropStatus()
    {
        this.isHarvestable = age == cropType.maxAge;
        this.isWithered = age > cropType.maxAge ||
                (age == cropType.maxAge && waterAmt < cropType.waterMin);
    }

    /**
     * computes the price of the crop harvested (without water and fertilize 
     * bonus)
     * @param title increases the amount of money earned from a single crop
     * @return the harvest total
     */
    public double computeHarvestTotal(Title title)
    {

        double currCropSellPrice = cropType.sellPrice + title.getBonusEarnings();
        return this.producedAmt * (currCropSellPrice);
    }

    /**
     * computes the water bonus of the crop
     * @param title useed to generate harvest total
     * @return computed water bonus
     */
    public double computeWaterBonus(Title title)
    {
        double i = computeHarvestTotal(title);

        return i * 0.2 * (waterAmt - 1);
    }

    /**
     * computes the fertilize bonus
     * @param title used to compute harvest total
     * @return computed fertilizer bopus
     */
    public double computeFertilizerBonus(Title title)
    {
        double i = computeHarvestTotal(title);

        return i * 0.5 * fertilizerAmt;
    }

    /**
     * computes the total harvest earnings 
     * @param title used to compute the harvest total
     * @return  total value of havest, water, and fertilizer bonus
     */
    public double computeHarvestEarnings(Title title)
    {
        double a = computeHarvestTotal(title);
        double b = computeWaterBonus(title);
        double c = computeFertilizerBonus(title);

        if (this.cropType.cropCategory.equals("flower"))
            return (a + b + c) * 1.1;

        return a + b + c;
    }
}

