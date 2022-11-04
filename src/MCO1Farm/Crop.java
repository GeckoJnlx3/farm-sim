package MCO1Farm;

/**
 * Crop class - object that is planted
 * on a plot of land.
 * Requires a TextLand tile in the PLOWED
 * state in order to be utilized.
 */

public class Crop
{
    // 1 (Root) - Turnip, Carrot, Potato
    // 2 (Flower) - Rose, Turnips, Sunflower
    // 3 (Fruit Tree) - Mango, Apple

    // These are variables inherent to each crop category
    private String cropName;
    private int maxAge; // AKA Harvest Time
    private int waterMin;
    private int waterBonus;
    private int cropCost;
    private int produceMin;
    private int produceMax;
    private int sellPrice;

    // Variables that are set to the same default value for all crops
    private int age = 0;
    private int waterAmt = 0;
    private boolean isWithered = false;
    private boolean isHarvestable = false;
    private int farmerEarningTypeBonus = 0; // Create getter class for Farmer title?
    private int producedAmt = 0;

    /**
     * Constructor for Crop class.
     * @param cropName crop name that determines
     *                 what type of crop should
     *                 be initialized.
     */
    Crop(String cropName)
    {
        switch(cropName)
        {
            case "Turnip":
                this.cropName = cropName;
                this.maxAge = 2;
                this.waterMin = 1;
                this.waterBonus = 2;
                this.cropCost = 5;
                this.produceMin = 1;
                this.produceMax = 2;
                this.sellPrice = 6;
                break;
            default:
                this.cropName = "empty";
                this.maxAge = 0;
                this.waterMin = 0;
                this.waterBonus = 0;
                this.cropCost = 0;
                this.produceMin = 0;
                this.produceMax = 0;
                this.sellPrice = 0;
        }
    }

    /**
     * getter method which returns cropName.
     * @return crop's name.
     */
    public String getCropName(){
        return this.cropName;
    }

    /**
     * getter method which returns isWithered.
     * @return crop's wither status.
     */
    public boolean getWitherStatus()
    {
        return this.isWithered;
    }

    /**
     * getter method which returns isHarvestable.
     * @return crop's harvest status.
     */
    public boolean getHarvestStatus()
    {
        return this.isHarvestable;
    }

    /**
     * getter method which returns waterAmt.
     * @return crop's water amount.
     */
    public int getWaterAmt() {
        return this.waterAmt;
    }

    /**
     * getter method which returns cropCost.
     * @return crop's seed cost.
     */
    public int getCropCost() {
        return this.cropCost;
    }

    /**
     * getter method which returns producedAmt.
     * @return crop's harvest produce amount.
     */
    public int getProducedAmt() {
        return this.producedAmt;
    }

    /**
     * Returns the crop's water amount as long
     * as it is less than the crop's maximum
     * water amount.
     * @return boolean which determines if the
     *         crop was successfully watered.
     */
    boolean increaseWaterAmt() {
        if (this.waterAmt < this.waterBonus) {
            this.waterAmt++;
            return true;
        } else return false;
    }

    /**
     * ages the crop by one.
     */
    void updatePlantStage(){
        this.age = age + 1;
    }

    /**
     * Updates the crop's withered and harvestable
     * status by checking its age.
     */
    void checkCropStatus()
    {
        if (!this.cropName.equals("empty")){
            if (age > maxAge ||
                    (age == maxAge && waterAmt < waterMin))
                this.isWithered = true;
            else if (age == maxAge)
                this.isHarvestable = true;
        }
    }

    /**
     * Generates a random value between the crop's
     * minimum and maximum produce values.
     */
    // Generate amt. of crop items obtained from harvesting a single crop
    private void generateYield(){
        this.producedAmt =  produceMin + (int)(Math.random() * ((produceMax - produceMin) + 1));
    }

    /**
     * Computes the crop's yield (in terms
     * of objectcoins), exclusive of title bonus.
     * @return the crop's price yield.
     */
    private double computeHarvestTotal()
    {
        generateYield();
        return this.producedAmt* (sellPrice + farmerEarningTypeBonus);
    }

    /**
     * Computes the water bonus of the crop
     * @return crop's water bonus.
     */
    private double computeWaterBonus()
    {
        double i = computeHarvestTotal();

        return i * 0.2 * (waterAmt - 1);
    }

    /**
     * Computes the final yield of the crop
     * in terms of objectcoins, inclusive of
     * farmer title bonus.
     * @return crop's final harvest price.
     */
    double computeHarvestEarnings() {
        return computeHarvestTotal() + computeWaterBonus();
    }

}
