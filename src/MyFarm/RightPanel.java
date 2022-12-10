package MyFarm;

import javax.swing.*;

import MyFarm.crop.CropType;

import MyFarm.land.LandState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightPanel
{
    //Panels
    CardLayout cardLayout = new CardLayout();
    JPanel rightCardPanel = new JPanel(cardLayout);
    JPanel toolPanel = new JPanel(new GridLayout(7,1,3,3));
    JPanel seedPanel = new JPanel();

    //Buttons for swapping card layouts
    JButton seedPanelSwap = new JButton("seed shop");
    JButton toolPanelSwap = new JButton("    Back    ");

    JButton forwardButton = new JButton("advance day");
    
    //Tools    
    ToolButton wateringCan = new ToolButton("watering can");
    ToolButton pickaxe = new ToolButton("pickaxe");
    ToolButton shovel = new ToolButton ("shovel");
    ToolButton hoe = new ToolButton ("hoe");
    ToolButton fertilizer = new ToolButton("fertilizer");

    //Seeds
    SeedButton seedTurnip = new SeedButton(CropType.TURNIP);
    SeedButton seedCarrot = new SeedButton(CropType.CARROT);
    SeedButton seedPotato = new SeedButton(CropType.POTATO);
    SeedButton seedRose = new SeedButton(CropType.ROSE);
    SeedButton seedSunflower = new SeedButton(CropType.SUNFLOWER);
    SeedButton seedTurnips = new SeedButton(CropType.TURNIPS);
    SeedButton seedMango = new SeedButton(CropType.MANGO);
    SeedButton seedApple = new SeedButton(CropType.APPLE);


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
                deselectAllButtons();
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

                if (checkForGameOver(model))
                    view.gameOver();
                else{
                    view.leftPanel.initializeGameInfo(model.player, view);
                    view.bottomPanel.playerAction.setText("Advanced to the next day!");
                }
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
        addAllToolButtons();
    }

    //change this to work with the seeds
    public void initializeSeeds(MyFarmModel model, MyFarmView view)
    {
        toolPanelSwap.setFocusable(false);
        toolPanelSwap.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                deselectSeedButtons();
                cardLayout.next(rightCardPanel);
            }
        });

        seedTurnip.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.bottomPanel.playerAction.setText("Select a plot to plant a Turnip on (Cost: 5)");
                selectSeed(view, seedTurnip, seedCarrot, seedPotato, seedRose, seedSunflower, seedTurnips,
                        seedApple, seedMango);
            }
        });

        seedCarrot.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.bottomPanel.playerAction.setText("Select a plot to plant a Carrot on (Cost: 10)");
                selectSeed(view, seedCarrot, seedTurnip, seedPotato, seedRose, seedSunflower, seedTurnips,
                        seedApple, seedMango);
            }
        });

        seedPotato.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.bottomPanel.playerAction.setText("Select a plot to plant a Potato on (Cost: 20)");
                selectSeed(view, seedPotato, seedTurnip, seedCarrot, seedRose, seedSunflower, seedTurnips,
                        seedApple, seedMango);
            }
        });

        seedRose.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.bottomPanel.playerAction.setText("Select a plot to plant a Rose on (Cost: 5)");
                selectSeed(view, seedRose, seedTurnip, seedCarrot, seedPotato, seedSunflower, seedTurnips,
                        seedApple, seedMango);
            }
        });

        seedSunflower.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.bottomPanel.playerAction.setText("Select a plot to plant a Sunflower on (Cost: 10)");
                selectSeed(view, seedSunflower, seedTurnip, seedCarrot, seedPotato, seedRose, seedTurnips,
                        seedApple, seedMango);
            }
        });

        seedTurnips.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.bottomPanel.playerAction.setText("Select a plot to plant a Turnips on (Cost: 20)");
                selectSeed(view, seedTurnips, seedTurnip, seedCarrot, seedPotato, seedRose, seedSunflower,
                        seedApple, seedMango);
            }
        });

        seedMango.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.bottomPanel.playerAction.setText("Select a plot to plant a Mango on (Cost: 100)");
                selectSeed(view, seedMango, seedTurnip, seedCarrot, seedPotato, seedRose, seedSunflower,
                        seedTurnips, seedApple);
            }
        });

        seedApple.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.bottomPanel.playerAction.setText("Select a plot to plant a Apple on (Cost: 200)");
                selectSeed(view, seedApple, seedTurnip, seedCarrot, seedPotato, seedRose, seedSunflower,
                        seedTurnips, seedMango);
            }
        });

        seedPanel.add(toolPanelSwap);
        addAllSeedButtons();
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
                    // change to respective icon
                    view.centerPanel.plotBtn[i][j].setPlotView(model.land.landState[i][j], model.land.crops[i][j]);;
                }
            }    
        }
    }

    private void selectTool(MyFarmView view, ToolButton btn1, ToolButton btn2,ToolButton btn3,ToolButton btn4,ToolButton btn5) {
        //if there's already a selected tool, replace it with the tool being selected
            boolean isSelectingOther = btn2.getText().equals("selected") ||
                    btn3.getText().equals("selected") ||
                    btn4.getText().equals("selected") ||
                    btn5.getText().equals("selected");
            
            //if the tool is selected again
            boolean isAlreadySelected = btn1.getText().equals("selected");
    
            if (isSelectingOther) {
                deselectToolButtons();
                btn1.selectButton();
            } else if (isAlreadySelected) {
                btn1.deselectButton();
                view.bottomPanel.clearBottompanel();
            } else btn1.selectButton();
    
        }

    private void selectSeed(MyFarmView view, SeedButton btn1, SeedButton btn2,SeedButton btn3, SeedButton btn4,
                           SeedButton btn5, SeedButton btn6, SeedButton btn7, SeedButton btn8) {
        //if there's already a selected seed, replace it with the seed being selected
        boolean isSelectingOther = btn2.getBackground().equals(Palette.SELECTED.getColor()) ||
                btn3.getBackground().equals(Palette.SELECTED.getColor()) ||
                btn4.getBackground().equals(Palette.SELECTED.getColor()) ||
                btn5.getBackground().equals(Palette.SELECTED.getColor()) ||
                btn6.getBackground().equals(Palette.SELECTED.getColor()) ||
                btn7.getBackground().equals(Palette.SELECTED.getColor()) ||
                btn8.getBackground().equals(Palette.SELECTED.getColor());

        //if the seed is selected again
        boolean isAlreadySelected = btn1.getBackground().equals(Palette.SELECTED.getColor());

        if (isSelectingOther) {
            deselectSeedButtons();
            btn1.selectButton();
        } else if (isAlreadySelected) {
            btn1.deselectButton();
            view.bottomPanel.clearBottompanel();
        } else btn1.selectButton();

    }

    private void deselectAllButtons(){
        deselectToolButtons();
        deselectSeedButtons();
    }

    private void deselectSeedButtons(){
        seedTurnip.deselectButton();
        seedApple.deselectButton();
        seedCarrot.deselectButton();
        seedMango.deselectButton();
        seedPotato.deselectButton();
        seedRose.deselectButton();
        seedSunflower.deselectButton();
        seedTurnips.deselectButton();
    }

    private void deselectToolButtons(){
        wateringCan.deselectButton();
        hoe.deselectButton();
        pickaxe.deselectButton();
        fertilizer.deselectButton();
        shovel.deselectButton();
    }

    private void addAllSeedButtons(){
        seedPanel.add(seedTurnip);
        seedPanel.add(seedCarrot);
        seedPanel.add(seedPotato);
        seedPanel.add(seedRose);
        seedPanel.add(seedSunflower);
        seedPanel.add(seedTurnips);
        seedPanel.add(seedMango);
        seedPanel.add(seedApple);
    }

    private void addAllToolButtons(){
        toolPanel.add(forwardButton);
        toolPanel.add(wateringCan);
        toolPanel.add(pickaxe);
        toolPanel.add(shovel);
        toolPanel.add(hoe);
        toolPanel.add(fertilizer);
    }

    private boolean checkIfHasCrops(MyFarmModel model){
        // returns true if not a single seed/fully grown crop is present
        boolean flag = false;

        for (int i = 0; i < 5 && !flag; i++)
        {
            for (int j = 0; j < 10 && !flag; j++)
            {
                if (model.land.landState[i][j].equals(LandState.PLANTED) ||
                    model.land.landState[i][j].equals(LandState.HARVESTABLE))
                    flag = true;
            }
        }

        return flag;
    }

    private boolean checkIfAllWithered(MyFarmModel model)
    {
        // returns true if all plots contain withered crop
        boolean flag = true;

        for (int i = 0; i < 5 && flag; i++)
        {
            for (int j = 0; j < 10 && flag; j++)
            {
                if (!model.land.landState[i][j].equals(LandState.WITHERED))
                    flag = false;
            }
        }

        return flag;
    }

    public boolean checkForGameOver(MyFarmModel model){
        return (!checkIfHasCrops(model) && model.player.getCoins() < 5) ||
                checkIfAllWithered(model);
        // should return true if a game over condition is met
    }
}
