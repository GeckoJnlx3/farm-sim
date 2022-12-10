package MyFarm;

/**
 * Title enum - contains discounts and bonuses of the titles
 */
public enum Title {
    FARMER("Farmer",0, 0, 0, 0, 0, 0),
    REGISTERED_FARMER("Registered Farmer",5,1,1,0,0,200),
    DISTINGUISHED_FARMER("Distinguished Farmer",10, 2,2,1,0,300),
    LEGENDARY_FARMER("Legendary Farmer", 15,4,3,2,1,400);

    private final String titleName;
    private final int levelReq;
    private final int bonusEarnings;
    private final int seedDiscount;
    private final int waterBonusLimitIncrease;
    private final int fertBonusLimitIncrease;
    private final int registrationFee;

    /**
     * Constructor for Title
     * @param titleName                 Name of the title
     * @param levelReq                  level required to get the title
     * @param bonusEarnings             bonus coins from harvesting
     * @param seedDiscount              discount when purchasing
     * @param waterBonusLimitIncrease   increase of water bonus limit
     * @param fertBonusLimitIncrease    increase of fertilizer bonus limit
     * @param registrationFee           price of the title
     */
    private Title(String titleName, int levelReq, int bonusEarnings, int seedDiscount,
    int waterBonusLimitIncrease, int fertBonusLimitIncrease, int registrationFee){
        this.titleName = titleName;
        this.levelReq = levelReq;
        this.bonusEarnings = bonusEarnings;
        this.seedDiscount = seedDiscount;
        this.waterBonusLimitIncrease = waterBonusLimitIncrease;
        this.fertBonusLimitIncrease = fertBonusLimitIncrease;
        this.registrationFee = registrationFee;
    }
/**
 * getter for title name
 * @return titleName
 */
    public String getTitleName(){
        return this.titleName;
    }

    /**
     * getter for level requirement
     * @return levelReq
     */
    public int getLevelReq(){
        return this.levelReq;
    }

    /**
     * getter for bonus earnings
     * @return bonusEarnings
     */
    public int getBonusEarnings(){
        return this.bonusEarnings;
    }

    /**
     * getter for seedDiscount
     * @return seedDiscount
     */
    public int getseedDiscount(){
        return this.seedDiscount;
    }

    /**
     * getter for limit increase (water)
     * @return waterBonusLimitIncrease
     */
    public int getWaterBonusLimitIncrease(){
        return this.waterBonusLimitIncrease;
    }

    /**
     * getter for limit increase (fertilizer)
     * @return waterBonusLimitIncrease
     */
    public int getFertBonusLimit(){
        return this.fertBonusLimitIncrease;
    }

    /**
     * getter for registrationFee
     * @return registrationFee
     */
    public int getRegistrationFee(){
        return this.registrationFee;
    }


}