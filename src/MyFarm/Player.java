package MyFarm;

import javax.swing.*;

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

    public void harvestCrop(Land land, JButton[][] landArray, JLabel playerAction, LeftPanel leftPanel) // only for turnip rn
    {
        double earned = land.crops[0][0].computeHarvestEarnings();
        this.objectCoins += earned;
        this.xp += 5;

        playerAction.setText("You harvested a turnip and earned " + earned + " coins and 5 XP!");

        land.landState[0][0] = LandState.UNPLOWED; // revert to unplowed land
        landArray[0][0].setIcon(unplowed); // icon unplowed
        land.crops[0][0] = new Crop(""); // remove crop

        leftPanel.initializeGameInfo(this);
    }

    public void plowLand (Land land, JButton[][] landArray, JLabel playerAction, LeftPanel leftPanel) {
        if (land.landState[0][0] == LandState.UNPLOWED) {
        	land.landState[0][0] = LandState.PLOWED;
        	playerAction.setText("The land is plowed.");
        	landArray[0][0].setIcon(plowed);
        	this.xp += 0.5;
        }
        else
            playerAction.setText("You cannot plow the land!");
        leftPanel.initializeGameInfo(this);
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
    public void waterPlant(Land land, JButton[][] landArray, JLabel playerAction, LeftPanel leftPanel) {
        if (land.landState[0][0] == LandState.PLANTED) {
        	boolean isWatered = land.crops[0][0].increaseWaterAmt();
            if (isWatered) { 
            	playerAction.setText("The plant has been watered " + land.crops[0][0].getWaterAmt() + " times.");
                this.xp += 0.5;            	
            } else 
            	playerAction.setText("The plant has reached it's max water amount!");
        }
        else
            playerAction.setText("You cannot water the land!");
        leftPanel.initializeGameInfo(this);
    }

    public void removePlant(Land land, JButton[][] landArray, JLabel playerAction, LeftPanel leftPanel) {
    	this.objectCoins -= 7;
    	if (land.landState[0][0] == LandState.UNPLOWED || 
        		land.landState[0][0] == LandState.PLOWED) {
        	land.landState[0][0] = LandState.UNPLOWED;
        	playerAction.setText("You shoveled nothing... you lost 7 coins.");
        } else if (land.landState[0][0] == LandState.BLOCKED) {
        	playerAction.setText("You tried to shovel the rock... you lost 7 coins.");
        } else if (land.landState[0][0] == LandState.PLANTED) {
            land.landState[0][0] = LandState.UNPLOWED;
            landArray[0][0].setIcon(unplowed);
            land.crops[0][0] = new Crop("");
            playerAction.setText("You shoveled your growing plant out... you lost 7 coins.");
        } else if (land.landState[0][0] == LandState.WITHERED) {
            land.landState[0][0] = LandState.UNPLOWED;
            landArray[0][0].setIcon(unplowed);
            land.crops[0][0] = new Crop("");
            this.xp += 2;
            playerAction.setText("The withered plant was removed.");
        } 
        leftPanel.initializeGameInfo(this);
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