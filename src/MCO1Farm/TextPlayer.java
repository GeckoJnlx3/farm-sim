package MCO1Farm;

/**
 * Player class interacts with land to plant,
 * water and harvest crops.
 */

class TextPlayer {

    private double objectCoins = 100;
    private int time = 1;

    /**
     * getter method which returns the current day.
     * @return current day (time).
     */
    int getDay()
    {
        return this.time;
    }

    /**
     * getter method which returns player's current
     * objectcoin count.
     * @return objectCoins amount.
     */
    double getCoins()
    {
        return this.objectCoins;
    }

    /**
     * Advances time by one.
     */
    void advanceTime()
    {
        this.time += 1;
    }

    /**
     * Ages all crops by one after time has
     * been incremented.
     * Updates plant to be withered/harvestable.
     * @param land land's state to be changed if
     *             a crop is withered or harvestable.
     */
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

    /**
     * Tries to plant a crop on a land tile.
     * @param land land's state to be changed if
     *             a crop is planted.
     */
    public void plantCrop(TextLand land) {
        if (land.landState == LandState.PLOWED) {
            land.landState = LandState.PLANTED;
            land.crop = new Crop("Turnip");
            this.objectCoins -= land.crop.getCropCost();
            System.out.println("You have sucessfully "
            		+ "printed a turnip.");
        } else {
            System.out.println("You cannot plant a turnip."
            		+ " Make it plantable (remove the plant "
            		+ "then plow it)");
        }
    }

    /**
     * Tries to harvest a crop from a land tile.
     * @param land land's state to be changed if
     *             a crop is harvested.
     */
    public void harvestCrop(TextLand land) 
    {
        if (land.crop.getHarvestStatus() == true) {
            double earned = land.crop.computeHarvestEarnings();
            this.objectCoins += earned;

            System.out.println("You harvested "+ land.crop.getProducedAmt() + " turnip(s) and earned " + earned
                    + " coins!");

            land.landState = LandState.UNPLOWED; // revert to unplowed land
            land.crop = new Crop(""); // remove crop
        } else {
            System.out.println("The plant is not harvestable yet!");
        }
    }

    /**
     * Tries to plow one tile of land.
     * @param land land's state to be changed
     *             if land is plowable.
     */
    public void plowLand (TextLand land) {
        if (land.landState == LandState.UNPLOWED) {
            land.landState = LandState.PLOWED;
            System.out.println("The land is plowed.");
        }
        else
            System.out.println("You cannot plow the land!");
    }

    /**
     * Attempts to increase a plant's water amount.
     * @param land land's state to be checked if
     *             anything is waterable.
     */
    public void waterPlant(TextLand land) {
        if (land.landState == LandState.PLANTED) {
            boolean isWatered = land.crop.increaseWaterAmt();
            if (isWatered)
                System.out.println("The plant has been watered " + 
                		land.crop.getWaterAmt() + " times.");
            else
                System.out.println("The plant has reached it's max water amount!");
        }
        else
            System.out.println("You cannot water the land!");
    }

    /**
     * Uses the shovel on a tile.
     * Removes any type of plants if there is one.
     * @param land land's state to be updated if
     *             anything is removed from the tile.
     */
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
            System.out.println("The withered plant was removed.");
        }
    }

}