package MyFarm;

import java.text.DecimalFormat;

/**
 * Player class - contains player data
 */
class Player {

    private double xp;
    private int level;
    private Title title;
    private double objectCoins;
    private int time;

    DecimalFormat df = new DecimalFormat();

    /**
     * Constructor for Player
     */
    public Player(){
        xp = 0;
        level = 0;
        title = Title.FARMER;
        objectCoins = 100;
        time = 1;
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

    void setXP(double XP)
    {
        this.xp = XP;
    }

    int getLevel()
    {
        return this.level;
    }

    void addLevel()
    {
        this.level++;
    }

    Title getTitle()
    {
        return this.title;
    }

    void setTitle(Title title)
    {
        this.title = title;
    }

    void advanceTime()
    {
        this.time++;
    }
}