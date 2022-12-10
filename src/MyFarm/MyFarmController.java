package MyFarm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MyFarm.crop.CropType;
import MyFarm.land.LandState;

//if the view changes, the controller changes the model
//view should respond to changes in model

//REMINDER TO SELF: REMOVE THE MODEL IN THE VIEW AND PUT IT IN 
//THE CONTROLLER INSTEAD
public class MyFarmController {
    MyFarmView view; 
    MyFarmModel model;

    public MyFarmController () {
        model = new MyFarmModel();
        view = new MyFarmView(model);
        
        initializeRightPanel();
    }

    public void startView(){
        this.view.setVisible(true);
    }

    private void initializeRightPanel(){
        initializeTools(model, view);
        initializeSeeds(model, view);

        view.rightPanel.rightCardPanel.add(view.rightPanel.toolPanel, "tool");
        view.rightPanel.rightCardPanel.add(view.rightPanel.seedPanel, "seed");
    }

    public void initializeTools(MyFarmModel model, MyFarmView view) {
        view.rightPanel.seedPanelSwap.setFocusable(false);
        view.rightPanel.seedPanelSwap.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                deselectAllButtons();
                view.rightPanel.cardLayout.next(view.rightPanel.rightCardPanel);
            }
        });

        //FORWARD BUTTON (bugged)
        view.rightPanel.forwardButton.setFocusable(false);
        view.rightPanel.forwardButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                model.player.advanceTime(); //controller should change
                updateCrops();

                if (view.rightPanel.checkForGameOver(model))
                    view.gameOver();
                else{
                    view.leftPanel.updateLeftPanel(model);
                    view.bottomPanel.playerAction.setText("Advanced to the next day!");
                }
            }
        });

        view.rightPanel.toolButtonList.add(new ToolButton("watering can"));
        view.rightPanel.toolButtonList.add(new ToolButton("pickaxe"));
        view.rightPanel.toolButtonList.add(new ToolButton("shovel"));
        view.rightPanel.toolButtonList.add(new ToolButton("hoe"));
        view.rightPanel.toolButtonList.add(new ToolButton("fertilizer"));
        
        //watering can
        view.rightPanel.toolButtonList.get(BtnIndex.WATERING_CAN.index).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.bottomPanel.playerAction.setText("Select on a land to water");
                selectTool(view.rightPanel.toolButtonList.get(BtnIndex.WATERING_CAN.index));
            }
        });
        
        //pickaxe
        view.rightPanel.toolButtonList.get(BtnIndex.PICKAXE.index).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.bottomPanel.playerAction.setText("Select on a land to remove a rock");
                selectTool(view.rightPanel.toolButtonList.get(BtnIndex.PICKAXE.index));
            }
        });

        //shovel
        view.rightPanel.toolButtonList.get(BtnIndex.SHOVEL.index).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.bottomPanel.playerAction.setText("Select on a plant to remove");
                selectTool(view.rightPanel.toolButtonList.get(BtnIndex.SHOVEL.index));
            }
        });

        //HOE
        view.rightPanel.toolButtonList.get(BtnIndex.HOE.index).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.bottomPanel.playerAction.setText("Select on a land to plow");
                selectTool(view.rightPanel.toolButtonList.get(BtnIndex.HOE.index));
            }
        });

        //FERTILIZER
        view.rightPanel.toolButtonList.get(BtnIndex.FERTILIZER.index).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                view.bottomPanel.playerAction.setText("Select on a land to fertilize");
                selectTool(view.rightPanel.toolButtonList.get(BtnIndex.FERTILIZER.index));
            }
        });

        view.rightPanel.toolPanel.add(view.rightPanel.seedPanelSwap);
        view.rightPanel.toolPanel.add(view.rightPanel.forwardButton);
        addAllToolButtons();
    }

    public void initializeSeeds(MyFarmModel model, MyFarmView view)
    {
        view.rightPanel.toolPanelSwap.setFocusable(false);
        view.rightPanel.toolPanelSwap.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                deselectSeedButtons();
                view.rightPanel.cardLayout.next(view.rightPanel.rightCardPanel);
            }
        });

        //extract into a dif function
        view.rightPanel.seedButtonList.add(new SeedButton(CropType.TURNIP));
        view.rightPanel.seedButtonList.add(new SeedButton(CropType.CARROT));
        view.rightPanel.seedButtonList.add(new SeedButton(CropType.POTATO));
        view.rightPanel.seedButtonList.add(new SeedButton(CropType.ROSE));
        view.rightPanel.seedButtonList.add(new SeedButton(CropType.SUNFLOWER));
        view.rightPanel.seedButtonList.add(new SeedButton(CropType.TURNIPS));
        view.rightPanel.seedButtonList.add(new SeedButton(CropType.MANGO));
        view.rightPanel.seedButtonList.add(new SeedButton(CropType.APPLE));
        
        //turnip
        view.rightPanel.seedButtonList.get(BtnIndex.TURNIP.index).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.bottomPanel.playerAction.setText("Select a plot to plant a Turnip on (Cost: 5)");
                selectSeed(view.rightPanel.seedButtonList.get(BtnIndex.TURNIP.index));
            }
        });

        //carrot
        view.rightPanel.seedButtonList.get(BtnIndex.CARROT.index).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.bottomPanel.playerAction.setText("Select a plot to plant a Carrot on (Cost: 10)");
                selectSeed(view.rightPanel.seedButtonList.get(BtnIndex.CARROT.index));
            }
        });

        //potato
        view.rightPanel.seedButtonList.get(BtnIndex.POTATO.index).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.bottomPanel.playerAction.setText("Select a plot to plant a Potato on (Cost: 20)");
                selectSeed(view.rightPanel.seedButtonList.get(BtnIndex.POTATO.index));
            }
        });

        //rose
        view.rightPanel.seedButtonList.get(BtnIndex.ROSE.index).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.bottomPanel.playerAction.setText("Select a plot to plant a Rose on (Cost: 5)");
                selectSeed(view.rightPanel.seedButtonList.get(BtnIndex.ROSE.index));
            }
        });

        //sunflower
        view.rightPanel.seedButtonList.get(BtnIndex.SUNFLOWER.index).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.bottomPanel.playerAction.setText("Select a plot to plant a Sunflower on (Cost: 10)");
                selectSeed(view.rightPanel.seedButtonList.get(BtnIndex.SUNFLOWER.index));
            }
        });

        //turnips (flower)
        view.rightPanel.seedButtonList.get(BtnIndex.TURNIPS.index).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.bottomPanel.playerAction.setText("Select a plot to plant a Turnips on (Cost: 20)");
                selectSeed(view.rightPanel.seedButtonList.get(BtnIndex.TURNIPS.index));
            }
        });

        //mango
        view.rightPanel.seedButtonList.get(BtnIndex.MANGO.index).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.bottomPanel.playerAction.setText("Select a plot to plant a Mango on (Cost: 100)");
                selectSeed(view.rightPanel.seedButtonList.get(BtnIndex.MANGO.index));
            }
        });

        //apple
        view.rightPanel.seedButtonList.get(BtnIndex.APPLE.index).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.bottomPanel.playerAction.setText("Select a plot to plant a Apple on (Cost: 200)");
                selectSeed(view.rightPanel.seedButtonList.get(BtnIndex.APPLE.index));
            }
        });

        view.rightPanel.seedPanel.add(view.rightPanel.toolPanelSwap);
        addAllSeedButtons();
    }

    public void updateCrops()
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
                    view.centerPanel.plotBtn[i][j].setPlotView(model.player,model.land.landState[i][j], model.land.crops[i][j]);;
                }
        
                else if (model.land.crops[i][j].getHarvestStatus() == true)
                {
                    model.land.landState[i][j] = LandState.HARVESTABLE;
                    view.centerPanel.plotBtn[i][j].setPlotView(model.player,model.land.landState[i][j], model.land.crops[i][j]);;
                }
            }    
        }
    }

    private void selectTool(ToolButton btn) {
        boolean isAlreadySelected = btn.getText().equals("selected");
        boolean isSelectingOther = false;

        for (ToolButton t : view.rightPanel.toolButtonList){
            if (t != btn && t.getText().equals("selected")){
                isSelectingOther = true;
            }
        }    

        if (isSelectingOther) {
            deselectToolButtons();
            btn.selectButton();
        } else if (isAlreadySelected) {
            btn.deselectButton();
            view.bottomPanel.clearBottompanel();
        } else btn.selectButton();
    }

    private void selectSeed(SeedButton btn) {
        //if there's already a selected seed, replace it with the seed being selected
        boolean isSelectingOther = false;
        boolean isAlreadySelected = btn.getBackground().equals(Palette.SELECTED.getColor());

        for (SeedButton s : view.rightPanel.seedButtonList){
            if (s != btn && s.getBackground().equals(Palette.SELECTED.getColor())){
                isSelectingOther = true;
            }
        }  

        if (isSelectingOther) {
            deselectSeedButtons();
            btn.selectButton();
        } else if (isAlreadySelected) {
            btn.deselectButton();
            view.bottomPanel.clearBottompanel();
        } else btn.selectButton();

    }

    private void deselectAllButtons(){
        deselectToolButtons();
        deselectSeedButtons();
    }

    private void deselectSeedButtons(){
        for (SeedButton s: view.rightPanel.seedButtonList){
            s.deselectButton();
        }
    }

    private void deselectToolButtons(){
        for (ToolButton t: view.rightPanel.toolButtonList){
            t.deselectButton();
        }
    }

    private void addAllSeedButtons(){
        for (SeedButton s: view.rightPanel.seedButtonList){
            view.rightPanel.seedPanel.add(s);
        } 
    }

    private void addAllToolButtons(){
        for (ToolButton s: view.rightPanel.toolButtonList){
            view.rightPanel.toolPanel.add(s);
        } 
    }

}