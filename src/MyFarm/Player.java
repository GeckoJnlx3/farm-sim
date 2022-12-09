package MyFarm;

import MyFarm.crop.Crop;
import MyFarm.land.LandState;

import java.text.DecimalFormat;
import java.util.Random;

class Player {

    private double xp = 0;
    private int level = 0;
    private Title title = Title.FARMER;
    private double objectCoins = 100;
    private int time = 1;

    DecimalFormat df = new DecimalFormat();

    public Player(){
        df.setMaximumFractionDigits(2);
    }

    int getDay()
    {
        return this.time;
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

    void levelUp(MyFarmModel model, MyFarmView view) {
        if (this.xp >= ((this.level + 1) * 100)){
            this.level++;
            view.bottomPanel.playerAction.setText("Congratulations! You have reached level" + level + "!");
        }
    }

   public void setTitle(Title choice, MyFarmView view) {
        boolean meetsReqs = this.level >= choice.getLevelReq() && 
                            this.objectCoins >= choice.getRegistrationFee();

        if (meetsReqs && isBeneficialTitle(choice)){
            switch (choice) {
                case REGISTERED_FARMER:
                    view.bottomPanel.playerAction.setText("You are now a Registered Farmer!");
                    this.title = choice;
                    this.objectCoins -= choice.getRegistrationFee();
                    break;                    
                case DISTINGUISHED_FARMER:
                    view.bottomPanel.playerAction.setText("You are now a Distinguished Farmer!");
                    this.title = choice;
                    this.objectCoins -= choice.getRegistrationFee();
                    break;                    
                case LEGENDARY_FARMER:
                    view.bottomPanel.playerAction.setText("You are now a Legendary Farmer!");
                    this.title = choice;
                    this.objectCoins -= choice.getRegistrationFee();
                    break;                    
                default:
                    break;                    
            }
        } else if (!meetsReqs){
            view.bottomPanel.playerAction.setText("You have not met the requirements.");
        } else if (!isBeneficialTitle(choice)){
            view.bottomPanel.playerAction.setText("There is no point buying this title.");
        }

        view.leftPanel.updateLeftPanel(this);
   }

    public void viewCropInfo(MyFarmModel model, MyFarmView view, int i, int j)
    {
        String name = model.land.crops[i][j].cropType.getCropName();
        int age = model.land.crops[i][j].getAge();
        int water = model.land.crops[i][j].getWaterAmt();
        int fertilizer = model.land.crops[i][j].getFertilizerAmt();
        int harvest = model.land.crops[i][j].getMaxAge() - age;

        view.bottomPanel.playerAction.setText("<html>A " + name + " is planted here. <br/>It is " + age +
                " day/s old.<br/>It has been watered " + water + " time/s.<br/>It has been fertilized " + fertilizer +
                " time/s.<br/>It will be ready for harvest in " + harvest + " day/s.</html>");
    }

    public void plantSeed(MyFarmModel model, MyFarmView view, int i, int j, String selectedCropName)
    {
        if (model.land.landState[i][j] == LandState.PLOWED){
            if (model.player.getCoins() >= new Crop(selectedCropName).getCropCost() -
                    model.player.getTitle().getseedDiscount()){
                model.land.crops[i][j] = new Crop(selectedCropName);
                model.land.landState[i][j] = LandState.PLANTED;
                view.centerPanel.plotBtn[i][j].setIcon(Icons.SEEDLING.getImageIcon());

                model.player.setCoins(model.player.getCoins() - model.land.crops[i][j].getCropCost());

                view.bottomPanel.playerAction.setText("You planted a(n) " + selectedCropName + ".");

                view.leftPanel.updateLeftPanel(this);
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
        this.xp += model.land.crops[i][j].getExpYield();
        this.levelUp(model, view);

        view.bottomPanel.playerAction.setText("<html>You harvested a " +
        model.land.crops[i][j].cropType.getCropName() + "<br/> It produced " + df.format(model.land.crops[i][j].getProducedAmt()) +
                " individual crop items." + "<br/>In total, you earned " + df.format(earned)
        + " coins and " + df.format(model.land.crops[i][j].getExpYield()) + " XP!</html>");

        model.land.landState[i][j] = LandState.UNPLOWED; // revert to unplowed land
        view.centerPanel.plotBtn[i][j].setIcon(Icons.UNPLOWED.getImageIcon()); // icon unplowed
        model.land.crops[i][j] = new Crop(""); // remove crop
        
        view.centerPanel.plotBtn[i][j].setPlotView(model.land.landState[i][j], model.land.crops[i][j]);
        view.leftPanel.updateLeftPanel(this);
    }

    public void plowLand (MyFarmModel model, MyFarmView view, int i, int j) {
        if (model.land.landState[i][j] == LandState.UNPLOWED) {
        	model.land.landState[i][j] = LandState.PLOWED;
        	view.bottomPanel.playerAction.setText("The land is plowed.");
        	view.centerPanel.plotBtn[i][j].setIcon(Icons.PLOWED.getImageIcon());
        	this.xp += 0.5;
            this.levelUp(model, view);
        }
        else
            view.bottomPanel.playerAction.setText("You cannot plow the land!");

        view.leftPanel.updateLeftPanel(this);
    }

    public void fertilizeCrop (MyFarmModel model, MyFarmView view, int i, int j) {
        if (model.land.landState[i][j] == LandState.PLANTED) {
            boolean isFertilized = model.land.crops[i][j].increaseFertAmt(model.player.objectCoins);
            if (isFertilized && this.objectCoins >= 4){
                view.bottomPanel.playerAction.setText("The plant has been fertilized " + 
                model.land.crops[i][j].getFertilizerAmt() + " times");
                this.objectCoins -= 4;
                this.xp +=4;
                this.levelUp(model, view);
            } else if (this.objectCoins < 4){
                view.bottomPanel.playerAction.setText("You don't have enough ObjectCoins");
            } else if (!isFertilized){
                view.bottomPanel.playerAction.setText("You have reached the max amount of fertilizer.");
            }
            } else
                view.bottomPanel.playerAction.setText("You cannot fertilize the land!");
        view.centerPanel.plotBtn[i][j].setPlotView(model.land.landState[i][j], model.land.crops[i][j]);
        view.leftPanel.updateLeftPanel(this);
    }

    public void waterPlant(MyFarmModel model, MyFarmView view, int i, int j) {
        if (model.land.landState[i][j] == LandState.PLANTED) {
        	boolean isWatered = model.land.crops[i][j].increaseWaterAmt();
            if (isWatered) { 
            	view.bottomPanel.playerAction.setText("The plant has been watered " 
                + model.land.crops[i][j].getWaterAmt() + " times.");
                this.xp += 0.5;
                this.levelUp(model, view);
            } else 
            	view.bottomPanel.playerAction.setText("The plant "
                +"has reached it's max water amount!");
        }
        else
            view.bottomPanel.playerAction.setText("You cannot"+
            " water the land!");
        view.centerPanel.plotBtn[i][j].setPlotView(model.land.landState[i][j], model.land.crops[i][j]);
        view.leftPanel.updateLeftPanel(this);
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
            this.levelUp(model, view);
            view.bottomPanel.playerAction.setText("The withered plant was removed.");
        } 
        view.leftPanel.updateLeftPanel(this);
    }

    public void removeRock(MyFarmModel model, MyFarmView view, int i, int j) {
        boolean enoughCoins = model.player.getCoins() > 50;
        boolean isRock = model.land.landState[i][j] == LandState.BLOCKED;
        
        if (enoughCoins && isRock) {
            model.land.landState[i][j] = LandState.UNPLOWED;
            view.bottomPanel.playerAction.setText("You have successfully removed a rock");
            this.objectCoins -= 50;
            this.xp += 15;
            this.levelUp(model, view);
            view.centerPanel.plotBtn[i][j].setPlotView(model.land.landState[i][j], model.land.crops[i][j]);
        } else if (!isRock){
            view.bottomPanel.playerAction.setText("There is no rock to remove.");
        } else if (!enoughCoins){
            view.bottomPanel.playerAction.setText("You don't have enough coins.");
        }
        view.leftPanel.updateLeftPanel(this);
    }

    private boolean isBeneficialTitle(Title title){
        switch (title){
            case REGISTERED_FARMER:
                return this.title == Title.FARMER ? true:false;
            case DISTINGUISHED_FARMER:
                return this.title == Title.FARMER || 
                this.title == Title.REGISTERED_FARMER ? true:false;
            case LEGENDARY_FARMER:
                return !(this.title == Title.LEGENDARY_FARMER) ? true:false;
            default:
                return false;
        }
    }
}