package MyFarm;

import java.awt.*;
import java.text.DecimalFormat;

import javax.swing.*;

/**
 * LeftPanel class - contains player information and the shop to get titles
 */
public class LeftPanel{

    CardLayout cardLayout = new CardLayout();
    JPanel leftCardPanel = new JPanel(cardLayout);
    JPanel infoPanel = new JPanel(new GridLayout(6,1));
    JPanel titlePanel = new JPanel();

    JButton titlePanelSwap = new JButton("Buy a Title");

    JButton infoPanelSwap = new JButton("Back");
    JButton titleReg = new JButton("Registered Farmer");
    JButton titleDis = new JButton("Distinguished Farmer");
    JButton titleLeg = new JButton("Legendary Farmer");

    JLabel objectCoins = new JLabel();
    JLabel currExp = new JLabel();
    JLabel currLvl = new JLabel();
    JLabel currTitle = new JLabel();
    JLabel currDay = new JLabel();

    DecimalFormat df = new DecimalFormat();

    /**
     * Constructor for the LeftPanel
     * Sets the color and size of panels including the ones in the card panel.
     */
    public LeftPanel(){
        leftCardPanel.setBackground(Palette.GRASS.getColor());
        leftCardPanel.setPreferredSize(new Dimension(135,100));

        infoPanel.setBackground(Palette.GRASS.getColor());
        infoPanel.setPreferredSize(new Dimension(135,100));
        titlePanel.setBackground(Palette.GRASS.getColor());
        titlePanel.setPreferredSize(new Dimension(135,100));

        setInfoIcons();
    }

    /**
     * Sets the icons for every JLabel.
     */
    private void setInfoIcons(){
        currDay.setIcon(Icons.DAY.getImageIcon());
        objectCoins.setIcon(Icons.OBJECTCOINS.getImageIcon());
        currExp.setIcon(Icons.XP.getImageIcon());
        currLvl.setIcon(Icons.LVL.getImageIcon());
        currTitle.setIcon(Icons.PLAYER.getImageIcon());
    }
}