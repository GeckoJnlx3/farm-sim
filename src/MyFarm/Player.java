package MyFarm;

import MyFarm.crop.Crop;
import MyFarm.land.LandState;

class Player {

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

//    public void buyTitle(Title choice) {
//        switch (choice) {
//            case REGISTERED_FARMER:
//                if (this.level >= 5 && this.objectCoins >= 200 &&
//                        this.title != Title.REGISTERED_FARMER) {
//                    System.out.println("You are now a Registered Farmer!");
//                    this.title = Title.REGISTERED_FARMER;
//                    this.objectCoins -= 200;
//                }
//            case DISTINGUISHED_FARMER:
//                if (this.level >= 10 && this.objectCoins >= 300 &&
//                        this.title != Title.DISTINGUISHED_FARMER) {
//                    System.out.println("You are now a Distinguished Farmer!");
//                    title = Title.DISTINGUISHED_FARMER;
//                    this.objectCoins -= 300;
//                }
//            case LEGENDARY_FARMER:
//                if (this.level >= 15 && this.objectCoins >= 400 &&
//                        this.title != Title.LEGENDARY_FARMER) {
//                    System.out.println("You are now a Legendary Farmer!");
//                    title = Title.LEGENDARY_FARMER;
//                    this.objectCoins -= 400;
//                }
//            default:
//                while (choice == title) {
//                    System.out.println("You already have this title.");
//                    int input = sc.nextInt();
//                    choice = choice.setTitle(input);
//                }
//        }
//    }

    public void viewCropInfo(MyFarmModel model, MyFarmView view, int i, int j)
    {
        String name = model.land.crops[i][j].cropType.getCropName();
        int age = model.land.crops[i][j].getAge();
        int water = model.land.crops[i][j].getWaterAmt();
        int fertilizer = model.land.crops[i][j].getFertilizerAmt();
        int harvest = model.land.crops[i][j].getMaxAge() - age;

        view.bottomPanel.playerAction.setText("A " + name + " is planted here. It is " + age +
                " day/s old, it has been watered " + water + " time/s, it has been fertilized " + fertilizer +
                " time/s, and will be ready for harvest in " + harvest + " day/s.");
    }

    public void plantSeed(MyFarmModel model, MyFarmView view, int i, int j, String selectedCropName)
    {
        if (model.land.landState[i][j] == LandState.PLOWED){
            if (model.player.getCoins() >= new Crop(selectedCropName).getCropCost()){
                model.land.crops[i][j] = new Crop(selectedCropName);
                model.land.landState[i][j] = LandState.PLANTED;
                view.centerPanel.plotBtn[i][j].setIcon(Icons.SEEDLING.getImageIcon());

                model.player.setCoins(model.player.getCoins() - model.land.crops[i][j].getCropCost());

                view.leftPanel.initializeGameInfo(model.player);
                view.bottomPanel.playerAction.setText("You planted a(n) " + selectedCropName + ".");

                view.leftPanel.initializeGameInfo(this);
            }
            else
                view.bottomPanel.playerAction.setText("You cannot afford to plant this crop seed!");
        }
        else
            view.bottomPanel.playerAction.setText("You cannot plant a seed here!");

    }

    public void harvestCrop(MyFarmModel model, MyFarmView view, int i, int j) // only for turnip rn
    {
        double earned = model.land.crops[i][j].computeHarvestEarnings();
        this.objectCoins += earned;
        this.xp += 5;

        view.bottomPanel.playerAction.setText("You harvested a turnip and earned " + earned + " coins and 5 XP!");

        model.land.landState[i][j] = LandState.UNPLOWED; // revert to unplowed land
        view.centerPanel.plotBtn[i][j].setIcon(Icons.UNPLOWED.getImageIcon()); // icon unplowed
        model.land.crops[i][j] = new Crop(""); // remove crop

        view.leftPanel.initializeGameInfo(this);
    }

    public void plowLand (MyFarmModel model, MyFarmView view, int i, int j) {
        if (model.land.landState[i][j] == LandState.UNPLOWED) {
        	model.land.landState[i][j] = LandState.PLOWED;
        	view.bottomPanel.playerAction.setText("The land is plowed.");
        	view.centerPanel.plotBtn[i][j].setIcon(Icons.PLOWED.getImageIcon());
        	this.xp += 0.5;
        }
        else
            view.bottomPanel.playerAction.setText("You cannot plow the land!");

        view.leftPanel.initializeGameInfo(this);
    }

    public void fertilizeCrop (MyFarmModel model, MyFarmView view, int i, int j) {
        if (model.land.landState[i][j] == LandState.PLANTED) {
            boolean isFertilized = model.land.crops[i][j].increaseFertAmt(model.player.objectCoins);
            if (isFertilized && this.objectCoins >= 4){
                view.bottomPanel.playerAction.setText("The plant has been fertilized " + 
                model.land.crops[i][j].getFertilizerAmt() + " times");
                this.objectCoins -= 4;
                this.xp +=4;
            } else if (this.objectCoins < 4){
                view.bottomPanel.playerAction.setText("You don't have enough ObjectCoins");
            } else if (!isFertilized){
                view.bottomPanel.playerAction.setText("You have reached the max amount of fertilizer.");
            }
            } else
                view.bottomPanel.playerAction.setText("You cannot fertilize the land!");

        view.leftPanel.initializeGameInfo(this);
    }

    public void waterPlant(MyFarmModel model, MyFarmView view, int i, int j) {
        if (model.land.landState[i][j] == LandState.PLANTED) {
        	boolean isWatered = model.land.crops[i][j].increaseWaterAmt();
            if (isWatered) { 
            	view.bottomPanel.playerAction.setText("The plant has been watered " 
                + model.land.crops[i][j].getWaterAmt() + " times.");
                this.xp += 0.5;            	
            } else 
            	view.bottomPanel.playerAction.setText("The plant "
                +"has reached it's max water amount!");
        }
        else
            view.bottomPanel.playerAction.setText("You cannot"+
            " water the land!");
        view.leftPanel.initializeGameInfo(this);
    }

    public void removePlant(MyFarmModel model, MyFarmView view, int i, int j) {
    	this.objectCoins -= 7;
    	if (model.land.landState[i][j] == LandState.UNPLOWED || 
            model.land.landState[i][j] == LandState.PLOWED) {
            model.land.landState[i][j] = LandState.UNPLOWED;
        	view.bottomPanel.playerAction.setText("You shoveled nothing... you lost 7 coins.");
        } else if (model.land.landState[i][j] == LandState.BLOCKED) {
        	view.bottomPanel.playerAction.setText("You tried to shovel the rock... you lost 7 coins.");
        } else if (model.land.landState[i][j] == LandState.PLANTED) {
            model.land.landState[i][j] = LandState.UNPLOWED;
            view.centerPanel.plotBtn[i][j].setIcon(Icons.UNPLOWED.getImageIcon());
            model.land.crops[i][j] = new Crop("");
            view.bottomPanel.playerAction.setText("You shoveled your growing plant out... you lost 7 coins.");
        } else if (model.land.landState[i][j] == LandState.WITHERED) {
            model.land.landState[i][j] = LandState.UNPLOWED;
            view.centerPanel.plotBtn[i][j].setIcon(Icons.UNPLOWED.getImageIcon());
            model.land.crops[i][j] = new Crop("");
            this.xp += 2;
            view.bottomPanel.playerAction.setText("The withered plant was removed.");
        } 
        view.leftPanel.initializeGameInfo(this);
    }

    public void removeRock(MyFarmModel model, MyFarmView view, int i, int j) {
        boolean enoughCoins = model.player.getCoins() > 50;
        boolean isRock = model.land.landState[i][j] == LandState.BLOCKED;
        
        if (enoughCoins && isRock) {
            model.land.landState[i][j] = LandState.UNPLOWED;
            view.bottomPanel.playerAction.setText("You have successfully removed a rock");
            this.objectCoins -= 50;
            this.xp += 15;
            view.centerPanel.plotBtn[i][j].setPlotView(model.land.landState[i][j], model.land.crops[i][j]);
        } else if (!isRock){
            view.bottomPanel.playerAction.setText("There is no rock to remove.");
        } else if (!enoughCoins){
            view.bottomPanel.playerAction.setText("You don't have enough coins.");
        }
        view.leftPanel.initializeGameInfo(this);
   }

}