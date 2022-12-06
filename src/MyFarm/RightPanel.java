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

    JButton seedPanelSwap = new JButton("seed shop");
    JButton toolPanelSwap = new JButton("    Back    ");

    JButton forwardButton = new JButton("advance day");
    
    ToolButton wateringCan = new ToolButton("watering can");
    ToolButton pickaxe = new ToolButton("pickaxe");
    ToolButton shovel = new ToolButton ("shovel");
    ToolButton hoe = new ToolButton ("hoe");
    ToolButton fertilizer = new ToolButton("fertilizer");

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
        seedPanelSwap.setFocusable(false);
        seedPanelSwap.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cardLayout.next(rightCardPanel);
            }
        });

        forwardButton.setFocusable(false);
        forwardButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                model.player.advanceTime(); //controller should change

                updateCrops(model, view);

                view.leftPanel.initializeGameInfo(model.player);
                view.bottomPanel.playerAction.setText("Advanced to the next day!");
            }
        });

        wateringCan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.bottomPanel.playerAction.setText("Select on a land to water");
                selectTool(view, wateringCan, pickaxe, shovel, hoe, fertilizer);
            }
        });

        pickaxe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.bottomPanel.playerAction.setText("Select on a land to remove a rock");
                selectTool(view, pickaxe, wateringCan, shovel, hoe, fertilizer);
            }
        });

        shovel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.bottomPanel.playerAction.setText("Select on a plant to remove");
                selectTool(view, shovel, wateringCan, pickaxe, hoe, fertilizer);
            }
        });

        hoe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.bottomPanel.playerAction.setText("Select on a land to plow");
                selectTool(view, hoe, wateringCan, pickaxe, fertilizer, shovel);
            }
        });

        fertilizer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                view.bottomPanel.playerAction.setText("Select on a land to fertilize");
                selectTool(view, fertilizer, wateringCan, pickaxe, hoe, shovel);
            }
        });

        toolPanel.add(seedPanelSwap);
        toolPanel.add(forwardButton);
        toolPanel.add(wateringCan);
        toolPanel.add(pickaxe);
        toolPanel.add(shovel);
        toolPanel.add(hoe);
        toolPanel.add(fertilizer);
    }

    //change this to work with the seeds
    public void initializeSeeds(MyFarmModel model, MyFarmView view)
    {
        SeedButton seedTurnip = new SeedButton(CropType.TURNIP, model, view);
        SeedButton seedCarrot = new SeedButton(CropType.CARROT, model, view);
        SeedButton seedPotato = new SeedButton(CropType.POTATO, model, view);
        SeedButton seedRose = new SeedButton(CropType.ROSE, model, view);
        SeedButton seedSunflower = new SeedButton(CropType.SUNFLOWER, model, view);
        SeedButton seedTurnips = new SeedButton(CropType.TURNIPS, model, view);
        SeedButton seedMango = new SeedButton(CropType.MANGO, model, view);
        SeedButton seedApple = new SeedButton(CropType.APPLE, model, view);

        toolPanelSwap.setFocusable(false);
        toolPanelSwap.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cardLayout.next(rightCardPanel);
            }
        });

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
            }
        });

        seedPanel.add(toolPanelSwap);
        seedPanel.add(seedTurnip);
        seedPanel.add(seedCarrot);
        seedPanel.add(seedPotato);
        seedPanel.add(seedRose);
        seedPanel.add(seedSunflower);
        seedPanel.add(seedTurnips);
        seedPanel.add(seedApple);
        seedPanel.add(seedMango);
    }

    public void updateCrops(MyFarmModel model, MyFarmView view)
    {
        for (int i = 0; i < 5;i++){
            for (int j = 0; j < 10; j++){
                if (!(model.land.crops[i][j].cropType == CropType.EMPTY)){
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

    public void selectTool(MyFarmView view, ToolButton btn1, ToolButton btn2,ToolButton btn3,ToolButton btn4,ToolButton btn5) {
        //if there's already a selected tool, replace it with the tool being selected
            boolean isSelectingOther =  btn2.getText().equals("selected") || 
                                    btn3.getText().equals("selected") ||
                                    btn4.getText().equals("selected") ||
                                    btn5.getText().equals("selected")? true : false;
            
            //if the tool is selected again
            boolean isAlreadySelected = btn1.getText().equals("selected") ? true:false;
    
            if (isSelectingOther) {
                btn1.setText("selected");
                btn2.setText(btn2.name);
                btn3.setText(btn3.name);
                btn4.setText(btn4.name);
                btn5.setText(btn5.name);
            } else if (isAlreadySelected) {
                btn1.setText(btn1.name);
                view.bottomPanel.playerAction.setText("");
            } else btn1.setText("selected");
    
        }

    public void selectSeed(MyFarmView view, SeedButton btn1, SeedButton btn2,SeedButton btn3, SeedButton btn4,
                           SeedButton btn5, SeedButton btn6, SeedButton btn7, SeedButton btn8) {
        //if there's already a selected seed, replace it with the seed being selected
        boolean isSelectingOther =  btn2.getText().equals("selected") ||
                btn3.getText().equals("selected") ||
                btn4.getText().equals("selected") ||
                btn5.getText().equals("selected")? true : false;

        //if the seed is selected again
        boolean isAlreadySelected = btn1.getText().equals("selected") ? true:false;

        if (isSelectingOther) {
            btn1.setText("selected");
            btn2.setText(btn2.name);
            btn3.setText(btn3.name);
            btn4.setText(btn4.name);
            btn5.setText(btn5.name);
        } else if (isAlreadySelected) {
            btn1.setText(btn1.name);
            view.bottomPanel.playerAction.setText("");
        } else btn1.setText("selected");

    }
}
