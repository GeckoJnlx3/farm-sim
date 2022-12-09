package MyFarm.crop;

import MyFarm.MyFarmModel;

import java.util.Random;

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
    Random random = new Random();

    public Crop(String cropName) {
        this.age = 0;
        this.waterAmt = 0;
        this.fertilizerAmt = 0;
        this.isHarvestable = false;
        this.isWithered = false;

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

    public int getAge()
    {
        return this.age;
    }

    public int getMaxAge()
    {
        return this.cropType.maxAge;
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

    public int getFertilizerAmt() {
        return this.fertilizerAmt;
    }
	
	public double getCropCost() {
		return this.cropType.cropCost;
	}

    public double getExpYield(){
        return this.cropType.expYield;
    }

    public int getProducedAmt(){
        return this.producedAmt;
    }

    private void generateProducedAmt(){
        this.producedAmt = this.cropType.produceMin + random.nextInt(this.cropType.produceMax);
    }

    public boolean increaseWaterAmt() {
    	boolean isValidAction = this.waterAmt < this.cropType.waterBonus;
        
        if (isValidAction) 
    		this.waterAmt++;

        return isValidAction;
    }

    public boolean increaseFertAmt(double objectCoins) {
    	boolean isValidAction = this.fertilizerAmt < this.cropType.fertilizerBonus && objectCoins >= 4;
        
        if (isValidAction) 
    		this.fertilizerAmt++;

        return isValidAction;
    }

    public void updatePlantStage()
    {
        this.age = age + 1;
    }

    public void checkCropStatus()
    {
        this.isHarvestable = age == cropType.maxAge;
        this.isWithered = age > cropType.maxAge ||
                (age == cropType.maxAge && waterAmt < cropType.waterMin);
    }

    public double computeHarvestTotal()
    {
        this.generateProducedAmt();
        return this.producedAmt * (cropType.sellPrice);
    }

    public double computeWaterBonus()
    {
        double i = computeHarvestTotal();

        return i * 0.2 * (waterAmt - 1);
    }

    public double computeFertilizerBonus()
    {
        double i = computeHarvestTotal();

        return i * 0.5 * fertilizerAmt;
    }

    public double computeHarvestEarnings()
    {
        double a = computeHarvestTotal();
        double b = computeWaterBonus();
        double c = computeFertilizerBonus();

        if (this.cropType.cropCategory.equals("flower"))
            return (a + b + c) * 1.1;

        return a + b + c;
    }
}

