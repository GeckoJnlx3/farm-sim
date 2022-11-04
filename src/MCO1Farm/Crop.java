package MCO1Farm;

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

    public String getCropName(){
        return this.cropName;
    }

    public int getAge()
    {
        return this.age;
    }

    public int getMaxAge()
    {
        return this.maxAge;
    }

    public boolean getWitherStatus()
    {
        return this.isWithered;
    }

    public boolean getHarvestStatus()
    {
        return this.isHarvestable;
    }
    
	public int getWaterAmt() {
		return this.waterAmt;
	}
	
	public int getCropCost() {
		return this.cropCost;
	}
	
	public int getProducedAmt() {
		return this.producedAmt;
	}
    
    boolean increaseWaterAmt() {
    	if (this.waterAmt < this.waterBonus) {
    		this.waterAmt++;
    		return true;
    	} else return false;
    }

    void updatePlantStage(){
        this.age = age + 1;
    }

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

    // Generate amt. of crop items obtained from harvesting a single crop
    private void generateYield(){
    	this.producedAmt =  produceMin + (int)(Math.random() * ((produceMax - produceMin) + 1));
    }

    private double computeHarvestTotal()
    {
    	generateYield();
    	return this.producedAmt* (sellPrice + farmerEarningTypeBonus);
    }

    private double computeWaterBonus()
    {
        double i = computeHarvestTotal();

        return i * 0.2 * (waterAmt - 1);
    }

    double computeHarvestEarnings() {
        return computeHarvestTotal() + computeWaterBonus();
    }

}

