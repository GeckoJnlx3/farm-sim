package MyFarm;

class Player {

    private int xp = 0;
    private int level = 0;
    private Title title = Title.FARMER;
    private int objectCoins = 100;
    private int time = 1;

    void levelUp() {
        this.level++;
        System.out.println("Congratulations! you have reached level" + level + "!");
    }

    int getDay()
    {
        return this.time;
    }

    int getCoins()
    {
        return this.objectCoins;
    }

    int getXP()
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

//    public void plowLand (int row, int col) {
//
//
//        if (land[row][col].landState == LandState.UNPLOWED) {
//            land[row][col].landState = LandState.PLOWED;
//            this.XP += 0.5;
//        }
//        else
//            System.out.println("You cannot plow the land!");
//    }
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
//    public void waterPlant(int row, int col) {
//        if (land[row][col].landState == LandState.PLANTED) {
//            land[row][col].crop.waterAmt++;
//            this.XP +=0.5;
//        }
//        else
//            System.out.println("You cannot water the land!");
//    }
//
//    public void removePlant(int row, int col) {
//        if (land[row][col].landState == LandState.UNPLOWED)
//            this.objectCoins -= 7;
//        else if (land[row][col].landState == LandState.BLOCKED)
//            this.objectCoins -= 7;
//        else if (land[row][col].landState == LandState.PLANTED) {
//            land[row][col].landState = LandState.UNPLOWED;
//            this.objectCoins -= 7;
//        }
//        else if (land[row][col].landState == LandState.WITHERED) {
//            land[row][col].landState = LandState.UNPLOWED;
//            this.objectCoins -= 7;
//            this.xp += 2;
//        }
//    }

//    public void removeRock(int row, int col) {
//        if (land[row][col].landState == LandState.BLOCKED) {
//            land[row][col].landState = LandState.UNPLOWED;
//            this.objectCoins -= 50;
//            this.XP += 15;
//        }
//
//    }

}