package MyFarm;

import javax.swing.*;

import MyFarm.crop.Crop;
import MyFarm.land.LandState;

class Player {

    private double xp = 0;
    private int level = 0;
    private Title title = Title.FARMER;
    private double objectCoins = 100;
    private int time = 1;
    ImageIcon unplowed = new ImageIcon("src/MyFarm/icons/unplowed.png");
    ImageIcon plowed = new ImageIcon("src/MyFarm/icons/plowed.png");

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

    public void harvestCrop(MyFarmModel model, MyFarmView view, int i, int j) // only for turnip rn
    {
        double earned = model.land.crops[i][j].computeHarvestEarnings();
        this.objectCoins += earned;
        this.xp += 5;

        view.bottomPanel.playerAction.setText("You harvested a turnip and earned " + earned + " coins and 5 XP!");

        model.land.landState[i][j] = LandState.UNPLOWED; // revert to unplowed land
        view.centerPanel.plotBtn[i][j].setIcon(unplowed); // icon unplowed
        model.land.crops[0][0] = new Crop(""); // remove crop

        view.leftPanel.initializeGameInfo(this);
    }

    public void plowLand (MyFarmModel model, MyFarmView view, int i, int j) {
        if (model.land.landState[i][j] == LandState.UNPLOWED) {
        	model.land.landState[i][j] = LandState.PLOWED;
        	view.bottomPanel.playerAction.setText("The land is plowed.");
        	view.centerPanel.plotBtn[i][j].setIcon(plowed);
        	this.xp += 0.5;
        }
        else
            view.bottomPanel.playerAction.setText("You cannot plow the land!");
        view.leftPanel.initializeGameInfo(this);
    }
//
//    public void fertilizeCrop (int row, int col) {
//        if (land[row][col].landState == LandState.PLANTED) {
//            land[row][col].crop.fertilizerAmt++;
//            this.objectCoins -= 4;
//            this.XP +=4;
//        }
//        else
//            System.out.println("You cannot fertilize the land!");
//    }
//
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
            view.centerPanel.plotBtn[i][j].setIcon(unplowed);
            model.land.crops[i][j] = new Crop("");
            view.bottomPanel.playerAction.setText("You shoveled your growing plant out... you lost 7 coins.");
        } else if (model.land.landState[i][j] == LandState.WITHERED) {
            model.land.landState[i][j] = LandState.UNPLOWED;
            view.centerPanel.plotBtn[i][j].setIcon(unplowed);
            model.land.crops[i][j] = new Crop("");
            this.xp += 2;
            view.bottomPanel.playerAction.setText("The withered plant was removed.");
        } 
        view.leftPanel.initializeGameInfo(this);
    }

//    public void removeRock(int row, int col) {
//        if (land[row][col].landState == LandState.BLOCKED) {
//            land[row][col].landState = LandState.UNPLOWED;
//            this.objectCoins -= 50;
//            this.XP += 15;
//        }
//
//    }

}