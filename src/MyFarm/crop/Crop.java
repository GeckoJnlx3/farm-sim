package MyFarm.crop;

import MyFarm.Title;

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

        setCropType(cropName);
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
        this.producedAmt = this.cropType.produceMin + random.nextInt(this.cropType.produceMax - this.cropType.produceMin + 1);
    }

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

    public boolean increaseWaterAmt(Title title) {
        int currWaterBonus = this.cropType.waterBonus + title.getWaterBonusLimitIncrease();
        boolean isValidAction = this.waterAmt < currWaterBonus;
        
        if (isValidAction) 
    		this.waterAmt++;

        return isValidAction;
    }

    public boolean increaseFertAmt(double objectCoins, Title title) {
    	int currFertBonus = this.cropType.fertilizerBonus + title.getFertBonusLimit();
        boolean isValidAction = this.fertilizerAmt < currFertBonus && objectCoins >= 4;
        
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

    public double computeHarvestTotal(Title title)
    {
        this.generateProducedAmt();
        double currCropSellPrice = cropType.sellPrice + title.getBonusEarnings();
        return this.producedAmt * (currCropSellPrice);
    }

    public double computeWaterBonus(Title title)
    {
        double i = computeHarvestTotal(title);

        return i * 0.2 * (waterAmt - 1);
    }

    public double computeFertilizerBonus(Title title)
    {
        double i = computeHarvestTotal(title);

        return i * 0.5 * fertilizerAmt;
    }

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

