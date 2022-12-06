package MyFarm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LeftPanel{

    CardLayout cardLayout = new CardLayout();
    JPanel leftCardPanel = new JPanel(cardLayout);
    JPanel infoPanel = new JPanel();
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

    public LeftPanel(Player p1){
        leftCardPanel.setBackground(Palette.GRASS.getColor());
        leftCardPanel.setPreferredSize(new Dimension(135,100));

        infoPanel.setBackground(Palette.GRASS.getColor());
        infoPanel.setPreferredSize(new Dimension(135,100));
        titlePanel.setBackground(Palette.GRASS.getColor());
        titlePanel.setPreferredSize(new Dimension(135,100));

        initializeGameInfo(p1);
        initializeTitles(p1);

        leftCardPanel.add(infoPanel, "info");
        leftCardPanel.add(titlePanel, "title");
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

        titlePanelSwap.setFocusable(false);
        titlePanelSwap.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cardLayout.next(leftCardPanel);
            }
        });

        infoPanel.add(titlePanelSwap);
        infoPanel.add(currDay);
        infoPanel.add(objectCoins);
        infoPanel.add(currExp);
        infoPanel.add(currLvl);
        infoPanel.add(currTitle);
    }

    public void initializeTitles(Player p1)
    {
        infoPanelSwap.setFocusable(false);
        infoPanelSwap.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cardLayout.next(leftCardPanel);
            }
        });

        titlePanel.add(infoPanelSwap);
        titlePanel.add(titleReg);
        titlePanel.add(titleDis);
        titlePanel.add(titleLeg);
    }
}