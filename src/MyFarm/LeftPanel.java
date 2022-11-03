package MyFarm;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class LeftPanel {
	JPanel panel;
	
    ImageIcon objectcoins = new ImageIcon("src/MyFarm/icons/objectcoins.png");
    ImageIcon xp = new ImageIcon("src/MyFarm/icons/xp.png");
    ImageIcon lvl = new ImageIcon("src/MyFarm/icons/lvl.png");
    ImageIcon title = new ImageIcon("src/MyFarm/icons/title.png");
    ImageIcon shop = new ImageIcon("src/MyFarm/icons/shop.png");
    ImageIcon player = new ImageIcon("src/MyFarm/icons/player.png");
    ImageIcon day = new ImageIcon("src/MyFarm/icons/day.png");
	
    JLabel objectCoins = new JLabel();
    JLabel currExp = new JLabel();
    JLabel currLvl = new JLabel();
    JLabel currTitle = new JLabel();
    JLabel currDay = new JLabel();
    JButton shopTab = new JButton();
    JButton playerTab = new JButton();
    
	LeftPanel(Player p1){ 
		this.panel = new JPanel();
		this.panel.setBackground(new Color(0xC0E5C8));
		this.panel.setPreferredSize(new Dimension(125,100));
		
		initializeGameInfo(p1);
	}
	
    void initializeGameInfo(Player p1)
    {
        currDay.setText("Day " + p1.getDay());
        currDay.setIcon(day);
        objectCoins.setText(Integer.toString(p1.getCoins()));
        objectCoins.setIcon(objectcoins);
        currExp.setText(Integer.toString(p1.getXP()));
        currExp.setIcon(xp);
        currLvl.setText(Integer.toString(p1.getLevel()));
        currLvl.setIcon(lvl);
        currTitle.setText("Farmer");
        currTitle.setIcon(title);

        this.panel.add(currDay);
        this.panel.add(objectCoins);
        this.panel.add(currExp);
        this.panel.add(currLvl);
        this.panel.add(currTitle);
    }
}
