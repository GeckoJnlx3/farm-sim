package MyFarm;

import java.awt.*;
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
        initializeLeftPanel();
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

                if (checkForGameOver(model))
                    view.gameOver();
                else{
                    updateLeftPanel(model);
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

    public void initializeLeftPanel(){
        setInfoIcons();
        initializeGameInfo(model, view);
        initializeTitles(model,view);

        view.leftPanel.df.setMaximumFractionDigits(2);

        view.leftPanel.leftCardPanel.add(view.leftPanel.infoPanel, "info");
        view.leftPanel.leftCardPanel.add(view.leftPanel.titlePanel, "title");
    }

    public void initializeGameInfo(MyFarmModel model, MyFarmView view)
    {
        updateLeftPanel(model);

        view.leftPanel.titlePanelSwap.setFocusable(false);
        view.leftPanel.titlePanelSwap.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.leftPanel.cardLayout.next(view.leftPanel.leftCardPanel);
            }
        });

        addInfoPanels();
    }


    public void updateLeftPanel(MyFarmModel model){
        view.leftPanel.currDay.setText("Day " + model.player.getDay());
        view.leftPanel.objectCoins.setText(view.leftPanel.df.format(model.player.getCoins()));
        view.leftPanel.currExp.setText(view.leftPanel.df.format(model.player.getXP()));
        view.leftPanel.currLvl.setText(Integer.toString(model.player.getLevel()));
        view.leftPanel.currTitle.setText(model.player.getTitle().getTitleName());
        updateTitleButton(model.player.getTitle());
    }

    private void setInfoIcons(){
        view.leftPanel.currDay.setIcon(Icons.DAY.getImageIcon());
        view.leftPanel.objectCoins.setIcon(Icons.OBJECTCOINS.getImageIcon());
        view.leftPanel.currExp.setIcon(Icons.XP.getImageIcon());
        view.leftPanel.currLvl.setIcon(Icons.LVL.getImageIcon());
        view.leftPanel.currTitle.setIcon(Icons.PLAYER.getImageIcon());
    }

    public void initializeTitles(MyFarmModel model, MyFarmView view)
    {
        view.leftPanel.infoPanelSwap.setFocusable(false);
        view.leftPanel.infoPanelSwap.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view.leftPanel.cardLayout.next(view.leftPanel.leftCardPanel);
            }
        });

        view.leftPanel.titleReg.setFocusable(false);
        view.leftPanel.titleReg.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e){
                model.player.setTitle(Title.REGISTERED_FARMER, view, model);
            }
        });

        view.leftPanel.titleDis.setFocusable(false);
        view.leftPanel.titleDis.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e){
                model.player.setTitle(Title.DISTINGUISHED_FARMER, view, model);
            }
        });

        view.leftPanel.titleLeg.setFocusable(false);
        view.leftPanel.titleLeg.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e){
                model.player.setTitle(Title.LEGENDARY_FARMER, view, model);
            }
        });

        addTitlePanels();
    }

    private void addInfoPanels(){
        view.leftPanel.infoPanel.add(view.leftPanel.titlePanelSwap);
        view.leftPanel.infoPanel.add(view.leftPanel.currDay);
        view.leftPanel.infoPanel.add(view.leftPanel.objectCoins);
        view.leftPanel.infoPanel.add(view.leftPanel.currExp);
        view.leftPanel.infoPanel.add(view.leftPanel.currLvl);
        view.leftPanel.infoPanel.add(view.leftPanel.currTitle);
    }

    private void addTitlePanels(){
        view.leftPanel.titlePanel.add(view.leftPanel.infoPanelSwap);
        view.leftPanel.titlePanel.add(view.leftPanel.titleReg);
        view.leftPanel.titlePanel.add(view.leftPanel.titleDis);
        view.leftPanel.titlePanel.add(view.leftPanel.titleLeg);
    }

    private void updateTitleButton(Title title){
        switch(title){
            case LEGENDARY_FARMER:
                view.leftPanel.titleLeg.setBackground(Color.GRAY);
            case DISTINGUISHED_FARMER:
                view.leftPanel.titleDis.setBackground(Color.GRAY);
            case REGISTERED_FARMER:
                view.leftPanel.titleReg.setBackground(Color.GRAY);
                break;
            default:
                break;
        }
    }

    public void initializeCenterPanel(){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 10; j++){
                view.centerPanel.plotBtn[i][j].setPlotView(model.player,model.land.landState[i][j], model.land.crops[i][j]);
                view.centerPanel.add(view.centerPanel.plotBtn[i][j]);
            }
        }
    }

    public void resetCenterPanelButtons(MyFarmModel model){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 10; j++){
                view.centerPanel.plotBtn[i][j].setPlotView(model.player,model.land.landState[i][j], model.land.crops[i][j]);
                view.centerPanel.add(view.centerPanel.plotBtn[i][j]);
            }
        }
    }

    public void resetPanels(MyFarmModel model)
    { // i dunno if this works!?
        resetCenterPanelButtons(model);
        view.bottomPanel.playerAction.setText("");
        updateLeftPanel(model);
    }
}