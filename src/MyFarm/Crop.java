package MyFarm;

public class Crop
{
    // 1 (Root) - Turnip, Carrot, Potato
    // 2 (Flower) - Rose, Turnips, Sunflower
    // 3 (Fruit Tree) - Mango, Apple

    // These are variables inherent to each crop category
    private String cropName;
    private int cropCategory;
    private int maxAge; // AKA Harvest Time
    private int waterMin;
    private int fertilizerMin;
    private int waterBonus;
    private int fertilizerBonus;
    private int cropCost;
    private int produceMin;
    private int produceMax;
    private int sellPrice;
    private double expYield;

    // Variables that are set to the same default value for all crops
    private int age = 0;
    private int waterAmt = 0;
    private int fertilizerAmt = 0;
    private int totalYield = generateYield(produceMin, produceMax);
    private boolean isWithered = false;
    private int farmerEarningTypeBonus; // Create getter class for Farmer title?

    Crop(String cropName)
    {
        switch(cropName)
        {
            case "Turnip":
                this.cropName = cropName;
                this.cropCategory = 1;
                this.maxAge = 2;
                this.waterMin = 1;
                this.fertilizerMin = 0;
                this.waterBonus = 2;
                this.fertilizerBonus = 1;
                this.cropCost = 5;
                this.produceMin = 1;
                this.produceMax = 2;
                this.sellPrice = 6;
                this.expYield = 5;
            case "Carrot":
                this.cropName = cropName;
                this.cropCategory = 1;
                this.maxAge = 3;
                this.waterMin = 1;
                this.fertilizerMin = 0;
                this.waterBonus = 2;
                this.fertilizerBonus = 1;
                this.cropCost = 10;
                this.produceMin = 1;
                this.produceMax = 2;
                this.sellPrice = 9;
                this.expYield = 7.5;
            case "Potato":
                this.cropName = cropName;
                this.cropCategory = 1;
                this.maxAge = 5;
                this.waterMin = 3;
                this.fertilizerMin = 1;
                this.waterBonus = 4;
                this.fertilizerBonus = 2;
                this.cropCost = 20;
                this.produceMin = 1;
                this.produceMax = 10;
                this.sellPrice = 3;
                this.expYield = 12.5;
            case "Rose":
                this.cropName = cropName;
                this.cropCategory = 2;
                this.maxAge = 1;
                this.waterMin = 1;
                this.fertilizerMin = 0;
                this.waterBonus = 2;
                this.fertilizerBonus = 1;
                this.cropCost = 5;
                this.produceMin = 1;
                this.produceMax = 1;
                this.sellPrice = 5;
                this.expYield = 2.5;
            case "Turnips":
                this.cropName = cropName;
                this.cropCategory = 2;
                this.maxAge = 2;
                this.waterMin = 2;
                this.fertilizerMin = 0;
                this.waterBonus = 3;
                this.fertilizerBonus = 1;
                this.cropCost = 10;
                this.produceMin = 1;
                this.produceMax = 1;
                this.sellPrice = 9;
                this.expYield = 5;
            case "Sunflower":
                this.cropName = cropName;
                this.cropCategory = 2;
                this.maxAge = 3;
                this.waterMin = 2;
                this.fertilizerMin = 1;
                this.waterBonus = 3;
                this.fertilizerBonus = 2;
                this.cropCost = 20;
                this.produceMin = 1;
                this.produceMax = 1;
                this.sellPrice = 19;
                this.expYield = 7.5;
            case "Mango":
                this.cropName = cropName;
                this.cropCategory = 3;
                this.maxAge = 10;
                this.waterMin = 7;
                this.fertilizerMin = 4;
                this.waterBonus = 7;
                this.fertilizerBonus = 4;
                this.cropCost = 100;
                this.produceMin = 5;
                this.produceMax = 15;
                this.sellPrice = 8;
                this.expYield = 25;
            case "Apple":
                this.cropName = cropName;
                this.cropCategory = 3;
                this.maxAge = 10;
                this.waterMin = 7;
                this.fertilizerMin = 5;
                this.waterBonus = 7;
                this.fertilizerBonus = 5;
                this.cropCost = 200;
                this.produceMin = 10;
                this.produceMax = 15;
                this.sellPrice = 5;
                this.expYield = 25;
        }
    }

    private void updatePlantStage(int age)
    {
        this.age = age + 1;
    }

    private void checkForWither(int age, int maxAge)
    {
        if (age > maxAge)
            this.isWithered = true;
    }

    // Generate amt. of crop items obtained from harvesting a single crop
    private int generateYield(int produceMin, int produceMax)
    {
        return (int)((Math.random() * (produceMax - produceMin)) + produceMin);
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

        if (this.cropCategory == 2)
            return (a + b + c) * 1.1;

        return a + b + c;
    }
}

