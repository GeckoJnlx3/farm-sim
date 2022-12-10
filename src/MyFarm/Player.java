package MyFarm;

import MyFarm.crop.Crop;
import MyFarm.land.LandState;

import java.text.DecimalFormat;

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

    void setXP(double XP)
    {
        this.xp += XP;
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