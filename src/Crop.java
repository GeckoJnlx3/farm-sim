public class Crop
{
    private String cropName;
    private int cropType; // 1 for Root, 2 for Flower, 3 for Fruit Tree
    private int age = 0;
    private int maxAge ; // AKA Harvest Time
    private int waterBonus;
    private int fertilizerBonus;
    private int waterAmt;
    private int fertilizerAmt;
    private int cropCost;
    private int sellPrice;
    private int expYield;
    private int totalYield; // Actual number of crop products harvested (randomized)
    private int farmerEarningTypeBonus; // This is from Farmer class though, hmm how to get it

    Crop(String cropName, int cropType, int maxAge, int waterBonus, int fertilizerBonus, int cropCost, int sellPrice,
         int expYield)
    {
        // 1 (Root) - Turnip, Carrot, Potato
        // 2 (Flower) - Rose, Turnips, Sunflower
        // 3 (Fruit Tree) - Mango, Apple
        this.cropName = cropName;
        this.cropType = cropType;
        this.maxAge = maxAge;
        this.waterBonus = waterBonus;
        this.fertilizerBonus = fertilizerBonus;
        this.cropCost = cropCost;
        this.sellPrice = sellPrice;
        this.expYield = expYield;
    }

    private void updatePlantStage(int age)
    {
        this.age = age + 1;
    }

    private double computerHarvestTotal()
    {
        return totalYield * (sellPrice + farmerEarningTypeBonus);
    }

    private double computeWaterBonus()
    {
        double i = computerHarvestTotal();

        return i * 0.2 * (waterAmt - 1);
    }

    private double computeFertilizerBonus()
    {
        double i = computerHarvestTotal();

        return i * 0.5 * fertilizerAmt;
    }

    private double computeHarvestEarnings()
    {
        double a = computerHarvestTotal();
        double b = computeWaterBonus();
        double c = computeFertilizerBonus();

        if (this.cropType == 2)
            return (a + b + c) * 1.1;

        return a + b + c;
    }
}

