package MyFarm;

import javax.swing.*;

import MyFarm.crop.Crop;
import MyFarm.crop.CropType;

import MyFarm.land.LandState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightPanel
{
    CardLayout cardLayout = new CardLayout();
    JPanel rightCardPanel = new JPanel(cardLayout);
    JPanel toolPanel = new JPanel();
    JPanel seedPanel = new JPanel();

    JButton forwardButton = new JButton("advance day");
    JButton wateringCan = new JButton("watering can");
    JButton pickaxe = new JButton("pickaxe");
    JButton shovel = new JButton ("shovel");
    JButton hoe = new JButton ("hoe");

    JButton seedTurnip = new JButton();

    public RightPanel(MyFarmModel model, MyFarmView view)
    {
        rightCardPanel.setBackground(Palette.GRASS.getColor());
        rightCardPanel.setPreferredSize(new Dimension(125,100));

        toolPanel.setBackground(Palette.GRASS.getColor());
        toolPanel.setPreferredSize(new Dimension(125,100));
        seedPanel.setBackground(Palette.GRASS.getColor());
        seedPanel.setPreferredSize(new Dimension(125,100));

        initializeTools(model, view);
        initializeSeeds(model, view);

        rightCardPanel.add(toolPanel, "tool");
        rightCardPanel.add(seedPanel, "seed");
    }

    public void initializeTools(MyFarmModel model, MyFarmView view) {
        forwardButton.setFocusable(false);
        forwardButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                model.player.advanceTime();

                updateCrops(model, view);

                view.leftPanel.initializeGameInfo(model.player);
                view.bottomPanel.playerAction.setText("Advanced to the next day!");
            }
        });

        wateringCan.setFocusable(false);
        wateringCan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.bottomPanel.playerAction.setText("Select on a land to water");
                selectTool(view.bottomPanel.playerAction, wateringCan, pickaxe, shovel, hoe, "watering can", "pickaxe", "shovel", "hoe");
            }
        });

        pickaxe.setFocusable(false);
        pickaxe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.bottomPanel.playerAction.setText("Select on a land to remove a rock");
                selectTool(view.bottomPanel.playerAction, pickaxe, wateringCan, shovel, hoe, "pickaxe", "watering can", "shovel", "hoe");
            }
        });

        shovel.setFocusable(false);
        shovel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.bottomPanel.playerAction.setText("Select on a plant to remove");
                selectTool(view.bottomPanel.playerAction, shovel, wateringCan, pickaxe, hoe, "shovel", "watering can", "pickaxe", "hoe");
            }
        });

        hoe.setFocusable(false);
        hoe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.bottomPanel.playerAction.setText("Select on a land to plow");
                selectTool(view.bottomPanel.playerAction, hoe, wateringCan, shovel, pickaxe, "hoe", "watering can", "shovel", "pickaxe");
            }
        });

        toolPanel.add(forwardButton);
        toolPanel.add(wateringCan);
        toolPanel.add(pickaxe);
        toolPanel.add(shovel);
        toolPanel.add(hoe);
    }

    // assume btn1 is the button, the rest are other btns

    public void selectTool(JLabel playerAction, JButton btn1, JButton btn2, JButton btn3, JButton btn4,
                    String toolName1, String toolName2, String toolName3,
                    String toolName4) {
        //if there's already a selected tool, replace it with the tool being selected
        boolean isSelectingOther =  btn2.getText().equals("selected") || 
                                    btn3.getText().equals("selected") ||
                                    btn4.getText().equals("selected") ? true : false;
        //if the tool is selected again
        boolean isAlreadySelected = btn1.getText().equals("selected") ? true:false;

        if (isSelectingOther) {
            btn1.setText("selected");
            btn2.setText(toolName2);
            btn3.setText(toolName3);
            btn4.setText(toolName4);
        } else if (isAlreadySelected) {
            btn1.setText(toolName1);
            playerAction.setText("");
        } else btn1.setText("selected");
    }

    public void initializeSeeds(MyFarmModel model, MyFarmView view)
    {
        seedTurnip.setIcon(Icons.TURNIP.getImageIcon());
        seedTurnip.setBackground(new Color(0xAAE29F));
        seedTurnip.setFocusable(false);

        seedTurnip.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                model.land.crops[2][2] = new Crop("Turnip");
                model.land.landState[2][2] = LandState.PLANTED;
                view.centerPanel.plotBtn[2][2].setIcon(Icons.SEEDLING.getImageIcon());

                model.player.setCoins(model.player.getCoins() - 5);

                // update the left panel info?
                view.leftPanel.initializeGameInfo(model.player);

                view.bottomPanel.playerAction.setText("You planted a turnip.");

                cardLayout.next(rightCardPanel);
            }
        });

        seedPanel.add(seedTurnip);
    }

    public void updateCrops(MyFarmModel model, MyFarmView view)
    {
        for (int i = 0; i < 5;i++){
            for (int j = 0; j < 10; j++){
                if (model.land.crops[i][j].cropType != CropType.EMPTY){
                    model.land.crops[i][j].updatePlantStage();
                    model.land.crops[i][j].checkCropStatus();
                }

                if (model.land.crops[i][j].getWitherStatus() == true)
                {
                    model.land.landState[i][j] = LandState.WITHERED;
                    view.centerPanel.plotBtn[i][j].setIcon(Icons.WITHERED.getImageIcon());
                }
        
                else if (model.land.crops[i][j].getHarvestStatus() == true)
                {
                    model.land.landState[i][j] = LandState.HARVESTABLE;
                    view.centerPanel.plotBtn[i][j].setIcon(Icons.TURNIP.getImageIcon());
                }
            }    
        }
        
        


    }
}
