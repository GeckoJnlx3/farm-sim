package MyFarm;

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

    public String getTitleName(){
        return this.titleName;
    }

    public int getLevelReq(){
        return this.levelReq;
    }

    public int getBonusEarnings(){
        return this.bonusEarnings;
    }

    public int getseedDiscount(){
        return this.seedDiscount;
    }

    public int getWaterBonusLimitIncrease(){
        return this.waterBonusLimitIncrease;
    }

    public int getFertBonusLimit(){
        return this.fertBonusLimitIncrease;
    }

    public int getRegistrationFee(){
        return this.registrationFee;
    }


}