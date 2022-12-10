package MyFarm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;

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

    public LeftPanel(){
        leftCardPanel.setBackground(Palette.GRASS.getColor());
        leftCardPanel.setPreferredSize(new Dimension(135,100));

        infoPanel.setBackground(Palette.GRASS.getColor());
        infoPanel.setPreferredSize(new Dimension(135,100));
        titlePanel.setBackground(Palette.GRASS.getColor());
        titlePanel.setPreferredSize(new Dimension(135,100));
    }
}