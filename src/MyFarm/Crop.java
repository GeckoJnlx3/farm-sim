package MyFarm;

public class Crop
{
    // These are variables inherent to each crop category
    CropType cropType;

    // Variables that are set to the same default value for all crops
    private int age = 0;
    private int waterAmt = 0;
    private int fertilizerAmt = 0;
    private boolean isWithered = false;
    private boolean isHarvestable = false;
    private int farmerEarningTypeBonus = 0; // Create getter class for Farmer title?

    Crop(String cropName) {
        switch (cropName) {
            default:
                this.age = 0;
                this.waterAmt = 0;
                this.fertilizerAmt = 0;
                this.isHarvestable = false;
                this.isWithered = false;
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
	
	public double getCropCost() {
		return this.cropType.cropCost;
	}
    
    public boolean increaseWaterAmt() {
    	if (this.waterAmt < this.cropType.waterBonus) {
    		this.waterAmt++;
    		return true;
    	} else return false;
    }

    public void updatePlantStage()
    {
        this.age = age + 1;
    }

    public void checkCropStatus()
    {
        if (!this.cropType.cropName.equals("")){
        	if (age > cropType.maxAge || 
        			(age == cropType.maxAge && waterAmt < cropType.waterMin))
                this.isWithered = true;
            else if (age == cropType.maxAge)
                this.isHarvestable = true;
        }
    }

    // Generate amt. of crop items obtained from harvesting a single crop
    public int generateYield()
    {
        return cropType.produceMin + (int)(Math.random() * ((cropType.produceMax - cropType.produceMin) + 1));
    }

    public double computeHarvestTotal()
    {
        return generateYield() * (cropType.sellPrice + farmerEarningTypeBonus);
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

