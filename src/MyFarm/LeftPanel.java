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

    public LeftPanel(Player p1, MyFarmModel model, MyFarmView view){
        leftCardPanel.setBackground(Palette.GRASS.getColor());
        leftCardPanel.setPreferredSize(new Dimension(135,100));

        infoPanel.setBackground(Palette.GRASS.getColor());
        infoPanel.setPreferredSize(new Dimension(135,100));
        titlePanel.setBackground(Palette.GRASS.getColor());
        titlePanel.setPreferredSize(new Dimension(135,100));

        initializeGameInfo(p1);
        initializeTitles(p1,view);

        leftCardPanel.add(infoPanel, "info");
        leftCardPanel.add(titlePanel, "title");
    }

    public void initializeGameInfo(Player p1)
    {
        updateLeftPanel(p1);

        titlePanelSwap.setFocusable(false);
        titlePanelSwap.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cardLayout.next(leftCardPanel);
            }
        });

        addInfoPanels();
    }

    public void updateLeftPanel(Player p1){
        currDay.setText("Day " + p1.getDay());
        currDay.setIcon(Icons.DAY.getImageIcon());
        objectCoins.setText(Double.toString(p1.getCoins()));
        objectCoins.setIcon(Icons.OBJECTCOINS.getImageIcon());
        currExp.setText(Double.toString(p1.getXP()));
        currExp.setIcon(Icons.XP.getImageIcon());
        currLvl.setText(Integer.toString(p1.getLevel()));
        currLvl.setIcon(Icons.LVL.getImageIcon());
        currTitle.setText(p1.getTitle().getTitleName());
        currTitle.setIcon(Icons.PLAYER.getImageIcon());

        updateTitleButton(p1.getTitle());
    }

    public void initializeTitles(Player p1, MyFarmView view)
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

        titleReg.setFocusable(false);
        titleReg.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e){
                p1.setTitle(Title.REGISTERED_FARMER, view);
            }
        });

        titleDis.setFocusable(false);
        titleDis.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e){
                p1.setTitle(Title.DISTINGUISHED_FARMER, view);
            }
        });

        titleLeg.setFocusable(false);
        titleLeg.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e){
                p1.setTitle(Title.LEGENDARY_FARMER, view);
            }
        });

        addTitlePanels();
    }

    private void addInfoPanels(){
        infoPanel.add(titlePanelSwap);
        infoPanel.add(currDay);
        infoPanel.add(objectCoins);
        infoPanel.add(currExp);
        infoPanel.add(currLvl);
        infoPanel.add(currTitle);
    }

    private void addTitlePanels(){
        titlePanel.add(infoPanelSwap);
        titlePanel.add(titleReg);
        titlePanel.add(titleDis);
        titlePanel.add(titleLeg);
    }

    private void updateTitleButton(Title title){
        switch(title){
            case LEGENDARY_FARMER:
                titleLeg.setBackground(Color.GRAY);
            case DISTINGUISHED_FARMER:
                titleDis.setBackground(Color.GRAY);
            case REGISTERED_FARMER:
                titleReg.setBackground(Color.GRAY);
                break;
            default:
                break;
        }
    }
}