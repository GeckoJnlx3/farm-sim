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

    public LeftPanel(MyFarmModel model, MyFarmView view){
        leftCardPanel.setBackground(Palette.GRASS.getColor());
        leftCardPanel.setPreferredSize(new Dimension(135,100));

        infoPanel.setBackground(Palette.GRASS.getColor());
        infoPanel.setPreferredSize(new Dimension(135,100));
        titlePanel.setBackground(Palette.GRASS.getColor());
        titlePanel.setPreferredSize(new Dimension(135,100));

        setInfoIcons();
        initializeGameInfo(model, view);
        initializeTitles(model,view);

        df.setMaximumFractionDigits(2);

        leftCardPanel.add(infoPanel, "info");
        leftCardPanel.add(titlePanel, "title");
    }

    public void initializeGameInfo(MyFarmModel model, MyFarmView view)
    {
        updateLeftPanel(model);

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

    public void updateLeftPanel(MyFarmModel model){
        currDay.setText("Day " + model.player.getDay());
        objectCoins.setText(df.format(model.player.getCoins()));
        currExp.setText(df.format(model.player.getXP()));
        currLvl.setText(Integer.toString(model.player.getLevel()));
        currTitle.setText(model.player.getTitle().getTitleName());
        updateTitleButton(model.player.getTitle());
    }

    private void setInfoIcons(){
        currDay.setIcon(Icons.DAY.getImageIcon());
        objectCoins.setIcon(Icons.OBJECTCOINS.getImageIcon());
        currExp.setIcon(Icons.XP.getImageIcon());
        currLvl.setIcon(Icons.LVL.getImageIcon());
        currTitle.setIcon(Icons.PLAYER.getImageIcon());
    }

    public void initializeTitles(MyFarmModel model, MyFarmView view)
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
                model.player.setTitle(Title.REGISTERED_FARMER, view, model);
            }
        });

        titleDis.setFocusable(false);
        titleDis.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e){
                model.player.setTitle(Title.DISTINGUISHED_FARMER, view, model);
            }
        });

        titleLeg.setFocusable(false);
        titleLeg.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e){
                model.player.setTitle(Title.LEGENDARY_FARMER, view, model);
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