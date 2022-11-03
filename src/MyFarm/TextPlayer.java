package MyFarm;

class TextPlayer {

    private double xp = 0;
    private int level = 0;
    private Title title = Title.FARMER;
    private double objectCoins = 100;
    private int time = 1;

    void levelUp() {
        this.level++;
        System.out.println("Congratulations! you have reached level" + level + "!");
    }

    int getDay()
    {
        return this.time;
    }

    void setDay(int day)
    {
        this.time = day;
    }

    double getCoins()
    {
        return this.objectCoins;
    }

    void setCoins(double coins)
    {
        this.objectCoins = coins;
    }

    double getXP()
    {
        return this.xp;
    }

    int getLevel()
    {
        return this.level;
    }

    Title getTitle()
    {
        return this.title;
    }

    void advanceTime()
    {
        this.time += 1;
    }
    
    public void skipDay(TextLand land) {
        advanceTime();
        
        land.crop.updatePlantStage();
        land.crop.checkCropStatus();
        if (land.crop.getWitherStatus() == true)
        	land.landState = LandState.WITHERED;
        else if (land.crop.getHarvestStatus() == true) {
        	land.landState = LandState.HARVESTABLE;
        }

        System.out.println("Advanced to the next day!");
    }
    
    public void plantCrop(TextLand land) {
    	if (land.landState == LandState.PLOWED) {
    		land.landState = LandState.PLANTED;
    		land.crop = new Crop("Turnip");
    		this.objectCoins -= land.crop.getCropCost();
    		System.out.println("You have sucessfully printed a turnip.");
    	} else {
    		System.out.println("You cannot plant a turnip. Make it plantable (remove the plant then plow it)");
    	}
    }
    
    public void harvestCrop(TextLand land) // only for turnip rn
    {
        if (land.crop.getHarvestStatus() == true) {
        	double earned = land.crop.computeHarvestEarnings();
            this.objectCoins += earned;
            this.xp += 5;

            System.out.println("You harvested a turnip and earned " + earned + " coins and 5 XP!");

            land.landState = LandState.UNPLOWED; // revert to unplowed land
            land.crop = new Crop(""); // remove crop
        } else {
        	System.out.println("The plant is not harvestable yet!");
        }
    }

    public void plowLand (TextLand land) {
        if (land.landState == LandState.UNPLOWED) {
        	land.landState = LandState.PLOWED;
        	System.out.println("The land is plowed.");
        	this.xp += 0.5;
        }
        else
        	System.out.println("You cannot plow the land!");
    }

    public void waterPlant(TextLand land) {
        if (land.landState == LandState.PLANTED) {
        	boolean isWatered = land.crop.increaseWaterAmt();
            this.xp += 0.5;
            if (isWatered)
            	System.out.println("The plant has been watered " + land.crop.getWaterAmt() + " times.");
            else 
            	System.out.println("The plant has reached it's max water amount!");
        }
        else
        	System.out.println("You cannot water the land!");
    }

    public void removePlant(TextLand land) {
        if (land.landState == LandState.UNPLOWED || 
        		land.landState == LandState.PLOWED) {
        	land.landState = LandState.UNPLOWED;
        	this.objectCoins -= 7;
        	System.out.println("You shoveled nothing... you lost 7 coins.");
        } else if (land.landState == LandState.BLOCKED) {
        	this.objectCoins -= 7;
        	System.out.println("You tried to shovel the rock... you lost 7 coins.");
        } else if (land.landState == LandState.PLANTED) {
            land.landState = LandState.UNPLOWED;
            land.crop = new Crop("");
            this.objectCoins -= 7;
            System.out.println("You shoveled your growing plant out... you lost 7 coins.");
        } else if (land.landState == LandState.WITHERED) {
            land.landState = LandState.UNPLOWED;
            land.crop= new Crop("");
            this.objectCoins -= 7;
            this.xp += 2;
            System.out.println("The withered plant was removed.");
        } 
    }

}