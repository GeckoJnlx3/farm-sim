package MyFarm;

import java.awt.Dimension;

import javax.swing.*;

public class LeftPanel extends JPanel{

    JLabel objectCoins = new JLabel();
    JLabel currExp = new JLabel();
    JLabel currLvl = new JLabel();
    JLabel currTitle = new JLabel();
    JLabel currDay = new JLabel();
    JButton shopTab = new JButton();
    JButton playerTab = new JButton();

    public LeftPanel(Player p1){
        this.setBackground(Palette.GRASS.getColor());
        this.setPreferredSize(new Dimension(125,100));

        initializeGameInfo(p1);
    }

    public void initializeGameInfo(Player p1)
    {
        currDay.setText("Day " + p1.getDay());
        currDay.setIcon(Icons.DAY.getImageIcon());
        objectCoins.setText(Double.toString(p1.getCoins()));
        objectCoins.setIcon(Icons.OBJECTCOINS.getImageIcon());
        currExp.setText(Double.toString(p1.getXP()));
        currExp.setIcon(Icons.XP.getImageIcon());
        currLvl.setText(Integer.toString(p1.getLevel()));
        currLvl.setIcon(Icons.LVL.getImageIcon());
        currTitle.setText("Farmer");
        currTitle.setIcon(Icons.PLAYER.getImageIcon());

        this.add(currDay);
        this.add(objectCoins);
        this.add(currExp);
        this.add(currLvl);
        this.add(currTitle);
    }
}