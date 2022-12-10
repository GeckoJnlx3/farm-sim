package MyFarm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

import MyFarm.crop.Crop;
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
        initializeCenterPanel();
        addRestartListener();
    }

    /**
     * starts up the view 
     */
    public void startView(){
        this.view.setVisible(true);
    }

    // ======================= RIGHT PANEL==================================
    /**
     * adds all the buttons and gives them actionlisteners
     */
    private void initializeRightPanel(){
        initializeTools(model, view);
        initializeSeeds(model, view);

        view.rightPanel.rightCardPanel.add(view.rightPanel.toolPanel, "tool");
        view.rightPanel.rightCardPanel.add(view.rightPanel.seedPanel, "seed");
    }

    /**
     * gives actionlisteners to the tool buttons
     * @param model required to add in the action listeners 
     * @param view holds the buttons
     */
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

                if (model.land.crops[i][j].getWitherStatus())
                {
                    model.land.landState[i][j] = LandState.WITHERED;
                    setPlotView(view.centerPanel.plotBtn[i][j],model.player,model.land.landState[i][j], model.land.crops[i][j]);;
                }
        
                else if (model.land.crops[i][j].getHarvestStatus())
                {
                    model.land.landState[i][j] = LandState.HARVESTABLE;
                    setPlotView(view.centerPanel.plotBtn[i][j],model.player,model.land.landState[i][j], model.land.crops[i][j]);;
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
    }

    //======================= LEFT PANEL=================================

    public void initializeLeftPanel(){
        
        initializeGameInfo();
        initializeTitles();
        view.leftPanel.df.setMaximumFractionDigits(2);
        view.leftPanel.leftCardPanel.add(view.leftPanel.infoPanel, "info");
        view.leftPanel.leftCardPanel.add(view.leftPanel.titlePanel, "title");
    }
    /**
     * adds actionListeners to buttons
     */
    public void initializeGameInfo()
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
        addInfoButtons();
    }

    public void updateLeftPanel(MyFarmModel model){
        view.leftPanel.currDay.setText("Day " + model.player.getDay());
        view.leftPanel.objectCoins.setText(view.leftPanel.df.format(model.player.getCoins()));
        view.leftPanel.currExp.setText(view.leftPanel.df.format(model.player.getXP()));
        view.leftPanel.currLvl.setText(Integer.toString(model.player.getLevel()));
        view.leftPanel.currTitle.setText(model.player.getTitle().getTitleName());
        updateTitleButton(model.player.getTitle());
    }

    public void initializeTitles()
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
                implementTitle(Title.REGISTERED_FARMER, view, model);
            }
        });

        view.leftPanel.titleDis.setFocusable(false);
        view.leftPanel.titleDis.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e){
                implementTitle(Title.DISTINGUISHED_FARMER, view, model);
            }
        });
        view.leftPanel.titleLeg.setFocusable(false);
        view.leftPanel.titleLeg.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e){
                implementTitle(Title.LEGENDARY_FARMER, view, model);
            }
        });
        addTitleButtons();
    }
    private void addInfoButtons(){
        view.leftPanel.infoPanel.add(view.leftPanel.titlePanelSwap);
        view.leftPanel.infoPanel.add(view.leftPanel.currDay);
        view.leftPanel.infoPanel.add(view.leftPanel.objectCoins);
        view.leftPanel.infoPanel.add(view.leftPanel.currExp);
        view.leftPanel.infoPanel.add(view.leftPanel.currLvl);
        view.leftPanel.infoPanel.add(view.leftPanel.currTitle);
    }
    private void addTitleButtons(){
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

    //============================= CENTER PANEL ============================
    /**
     * sets the view for every plot button based on information from model
     * and adds every button into the center panel
     */
    public void initializeCenterPanel(){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 10; j++){
                view.centerPanel.plotBtn[i][j] = new Plot(i, j);
                initializePlotButton(view.centerPanel.plotBtn[i][j], model.land.landState[i][j]);

                setPlotView(view.centerPanel.plotBtn[i][j],model.player,model.land.landState[i][j], model.land.crops[i][j]);
                view.centerPanel.add(view.centerPanel.plotBtn[i][j]);
            }
        }
    }
    /**
     * sets the view of each plot (to not double the addition of buttons)
     */
    public void resetCenterPanelButtons(){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 10; j++){
                setPlotView(view.centerPanel.plotBtn[i][j],model.player,model.land.landState[i][j], model.land.crops[i][j]);
            }
        }
    }
    
    public void resetPanels(MyFarmModel model)
    { 
        resetCenterPanelButtons();
        view.bottomPanel.clearBottompanel();
        updateLeftPanel(model);
    }


    //========== GAME OVER PANEL=========================
    
    private void addRestartListener(){
        view.gameOverPanel.restart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                model.resetModel();
                resetPanels(model);

                view.cardLayout.next(view.mainPanel);
            }
        });
        
        view.gameOverPanel.add(view.gameOverPanel.restart, BorderLayout.SOUTH);
    }

    //=============== PLOT BUTTON ===========================
    void initializePlotButton(Plot plot, LandState landState){
        
        setPlotView(plot, model.player, landState, null);

        plot.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.land.landState[plot.nRow][plot.nCol] == LandState.HARVESTABLE)
                    harvestCrop(model, view, plot.nRow, plot.nCol);
                else if (view.rightPanel.toolButtonList.get(BtnIndex.HOE.index).getText().equals("selected")) {
                    plowLand(model, view, plot.nRow, plot.nCol);
                }
                else if (view.rightPanel.toolButtonList.get(BtnIndex.WATERING_CAN.index).getText().equals("selected")) {
                    waterPlant(model, view, plot.nRow, plot.nCol);
                }
                else if (view.rightPanel.toolButtonList.get(BtnIndex.SHOVEL.index).getText().equals("selected")) {
                    removePlant(model, view, plot.nRow, plot.nCol);
                } 
                else if (view.rightPanel.toolButtonList.get(BtnIndex.PICKAXE.index).getText().equals("selected")){
                    removeRock(model, view, plot.nRow, plot.nCol);
                }
                else if (view.rightPanel.toolButtonList.get(BtnIndex.FERTILIZER.index).getText().equals("selected")){
                    fertilizeCrop(model, view, plot.nRow, plot.nCol);
                }
                else if (checkSelectedSeed(view) != null){ // if a seed button is selected
                    String selectedCropName = checkSelectedSeed(view).getCropName();

                    plantSeed(model, view, plot.nRow,  plot.nCol, selectedCropName);
                }
                else if (model.land.landState[ plot.nRow][ plot.nCol] == LandState.PLANTED){
                    viewCropInfo(model, view,  plot.nRow,  plot.nCol);
                }
                levelUp(model, view);
            }
        });
    }
    
    public void setPlotView(Plot plot, Player p1, LandState landState, Crop crop){
        
        plot.setBackground(Palette.UNWATERED_PLOT.getColor());
        plot.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        switch (landState){
            case BLOCKED: 
                plot.setIcon(Icons.BLOCKED.getImageIcon());
                break;
            case PLOWED:
                plot.setIcon(Icons.PLOWED.getImageIcon());
                break;
            case PLANTED:
                plot.setIcon(Icons.SEEDLING.getImageIcon());
                if (crop.getWaterAmt() == crop.cropType.getWaterBonus() + p1.getTitle().getWaterBonusLimitIncrease())
                    plot.setBackground(Palette.WATERED_PLOT.getColor());
                if (crop.getFertilizerAmt() == crop.cropType.getFertilizerBonus() + p1.getTitle().getFertBonusLimit())
                    plot.setBorder(BorderFactory.createLineBorder(Palette.FERTILIZED_PLOT.getColor(), 3));
                break;
            case HARVESTABLE:
                plot.setPlantIcon(crop.cropType);
                break;
            case UNPLOWED:
                plot.setIcon(Icons.UNPLOWED.getImageIcon());
                break;
            case WITHERED:
                plot.setIcon(Icons.WITHERED.getImageIcon());
                break;
            default:
                plot.setBackground(Palette.UNWATERED_PLOT.getColor()); //brown
                break;
        }
    }

    private CropType checkSelectedSeed(MyFarmView view)
    {
        for (SeedButton s : view.rightPanel.seedButtonList){
            if (s.getBackground().equals(Palette.SELECTED.getColor()))
                return s.crop;
        }
        return null;
    }

    //================== PLAYER ===========================

    void levelUp(MyFarmModel model, MyFarmView view) {
        if (model.player.getXP() >= ((model.player.getLevel() + 1) * 100)){
            model.player.addLevel();
            view.bottomPanel.playerAction.setText("Congratulations! You have reached level" + model.player.getLevel() + "!");
        }
    }

    public void implementTitle(Title choice, MyFarmView view, MyFarmModel model) {
        boolean meetsReqs = model.player.getLevel() >= choice.getLevelReq() &&
                model.player.getCoins() >= choice.getRegistrationFee();

        if (meetsReqs && isBeneficialTitle(choice)){
            switch (choice) {
                case REGISTERED_FARMER:
                    view.bottomPanel.playerAction.setText("You are now a Registered Farmer!");
                    model.player.setTitle(choice);
                    model.player.setCoins(model.player.getCoins() - choice.getRegistrationFee());
                    break;
                case DISTINGUISHED_FARMER:
                    view.bottomPanel.playerAction.setText("You are now a Distinguished Farmer!");
                    model.player.setTitle(choice);
                    model.player.setCoins(model.player.getCoins() - choice.getRegistrationFee());
                    break;
                case LEGENDARY_FARMER:
                    view.bottomPanel.playerAction.setText("You are now a Legendary Farmer!");
                    model.player.setTitle(choice);
                    model.player.setCoins(model.player.getCoins() - choice.getRegistrationFee());
                    break;
                default:
                    break;
            }
        } else if (!meetsReqs){
            view.bottomPanel.playerAction.setText("You have not met the requirements.");
        } else if (!isBeneficialTitle(choice)){
            view.bottomPanel.playerAction.setText("There is no point buying this title.");
        }

        updateLeftPanel(model);
    }

    public void viewCropInfo(MyFarmModel model, MyFarmView view, int i, int j)
    {
        String name = model.land.crops[i][j].cropType.getCropName();
        int age = model.land.crops[i][j].getAge();
        int water = model.land.crops[i][j].getWaterAmt();
        int fertilizer = model.land.crops[i][j].getFertilizerAmt();
        int harvest = model.land.crops[i][j].getMaxAge() - age;

        view.bottomPanel.playerAction.setText("<html>A " + name + " is planted here. <br/>It is " + age +
                " day/s old.<br/>It has been watered " + water + " time/s.<br/>It has been fertilized " + fertilizer +
                " time/s.<br/>It will be ready for harvest in " + harvest + " day/s.</html>");
    }

    public void plantSeed(MyFarmModel model, MyFarmView view, int i, int j, String selectedCropName)
    {
        if (model.land.landState[i][j] == LandState.PLOWED){
            if (model.player.getCoins() >= new Crop(selectedCropName).getCropCost() -
                    model.player.getTitle().getseedDiscount()){
                model.land.crops[i][j] = new Crop(selectedCropName);
                model.land.landState[i][j] = LandState.PLANTED;
                view.centerPanel.plotBtn[i][j].setIcon(Icons.SEEDLING.getImageIcon());

                purchaseSeedWDiscount(model, i, j);

                view.bottomPanel.playerAction.setText("You planted a(n) " + selectedCropName + ".");

                updateLeftPanel(model);
            }
            else
                view.bottomPanel.playerAction.setText("You cannot afford to plant this crop seed!");
        }
        else
            view.bottomPanel.playerAction.setText("You cannot plant a seed here!");

    }

    private void purchaseSeedWDiscount(MyFarmModel model, int i, int j){
        double currSeedCost =  model.land.crops[i][j].getCropCost() +
                model.player.getTitle().getseedDiscount();

        model.player.setCoins(model.player.getCoins() - currSeedCost);
    }

    public void harvestCrop(MyFarmModel model, MyFarmView view, int i, int j) // only for turnip rn
    {
        double earned = model.land.crops[i][j].computeHarvestEarnings(model.player.getTitle());
        model.player.setCoins(model.player.getCoins() + earned);
        model.player.setXP(model.player.getXP() + model.land.crops[i][j].getExpYield());
        this.levelUp(model, view);

        view.bottomPanel.playerAction.setText("<html>You harvested a " +
                model.land.crops[i][j].cropType.getCropName() + "<br/> It produced " + model.player.df.format(model.land.crops[i][j].getProducedAmt()) +
                " individual crop items." + "<br/>In total, you earned " + model.player.df.format(earned)
                + " coins and " + model.player.df.format(model.land.crops[i][j].getExpYield()) + " XP!</html>");

        model.land.landState[i][j] = LandState.UNPLOWED; // revert to unplowed land
        view.centerPanel.plotBtn[i][j].setIcon(Icons.UNPLOWED.getImageIcon()); // icon unplowed
        model.land.crops[i][j] = new Crop(""); // remove crop

        setPlotView(view.centerPanel.plotBtn[i][j], model.player, model.land.landState[i][j], model.land.crops[i][j]);
        updateLeftPanel(model);
    }

    public void plowLand (MyFarmModel model, MyFarmView view, int i, int j) {
        if (model.land.landState[i][j] == LandState.UNPLOWED) {
            model.land.landState[i][j] = LandState.PLOWED;
            view.bottomPanel.playerAction.setText("The land is plowed.");
            setPlotView(view.centerPanel.plotBtn[i][j], model.player, model.land.landState[i][j], null);
            model.player.setXP(model.player.getXP() + 0.5);
            this.levelUp(model, view);
        }
        else
            view.bottomPanel.playerAction.setText("You cannot plow the land!");

        updateLeftPanel(model);
    }

    public void fertilizeCrop (MyFarmModel model, MyFarmView view, int i, int j) {
        if (model.land.landState[i][j] == LandState.PLANTED) {
            boolean isFertilized = model.land.crops[i][j].increaseFertAmt(model.player.getCoins(), model.player.getTitle());
            if (isFertilized && model.player.getCoins() >= 10){
                view.bottomPanel.playerAction.setText("The plant has been fertilized " +
                        model.land.crops[i][j].getFertilizerAmt() + " times");
                model.player.setCoins(model.player.getCoins() - 10);
                model.player.setXP(model.player.getXP() + 4);
                levelUp(model, view);
            } else if (model.player.getCoins() < 10){
                view.bottomPanel.playerAction.setText("You don't have enough ObjectCoins");
            } else if (!isFertilized){
                view.bottomPanel.playerAction.setText("You have reached the max amount of fertilizer.");
            }
        } else
            view.bottomPanel.playerAction.setText("You cannot fertilize the land!");
       setPlotView(view.centerPanel.plotBtn[i][j], model.player,model.land.landState[i][j], model.land.crops[i][j]);
       updateLeftPanel(model);
    }

    public void waterPlant(MyFarmModel model, MyFarmView view, int i, int j) {
        if (model.land.landState[i][j] == LandState.PLANTED) {
            boolean isWatered = model.land.crops[i][j].increaseWaterAmt(model.player.getTitle());
            if (isWatered) {
                view.bottomPanel.playerAction.setText("The plant has been watered "
                        + model.land.crops[i][j].getWaterAmt() + " times.");
                model.player.setXP(model.player.getXP() + 0.5);
                levelUp(model, view);
            } else
                view.bottomPanel.playerAction.setText("The plant "
                        +"has reached it's max water amount!");
        }
        else
            view.bottomPanel.playerAction.setText("You cannot"+
                    " water the land!");
        setPlotView(view.centerPanel.plotBtn[i][j], model.player,model.land.landState[i][j], model.land.crops[i][j]);
        updateLeftPanel(model);
    }

    public void removePlant(MyFarmModel model, MyFarmView view, int i, int j) {
        model.player.setCoins(model.player.getCoins() - 7);
        if (model.land.landState[i][j] == LandState.UNPLOWED ||
                model.land.landState[i][j] == LandState.PLOWED) {
            model.land.landState[i][j] = LandState.UNPLOWED;
            view.bottomPanel.playerAction.setText("You shoveled nothing... you lost 7 coins.");
        } else if (model.land.landState[i][j] == LandState.BLOCKED) {
            view.bottomPanel.playerAction.setText("You tried to shovel the rock... you lost 7 coins.");
        } else if (model.land.landState[i][j] == LandState.PLANTED) {
            model.land.landState[i][j] = LandState.UNPLOWED;
            view.centerPanel.plotBtn[i][j].setIcon(Icons.UNPLOWED.getImageIcon());
            model.land.crops[i][j] = new Crop("");
            view.bottomPanel.playerAction.setText("You shoveled your growing plant out... you lost 7 coins.");
        } else if (model.land.landState[i][j] == LandState.WITHERED) {
            model.land.landState[i][j] = LandState.UNPLOWED;
            view.centerPanel.plotBtn[i][j].setIcon(Icons.UNPLOWED.getImageIcon());
            model.land.crops[i][j] = new Crop("");
            model.player.setXP(model.player.getXP() + 2);
            levelUp(model, view);
            view.bottomPanel.playerAction.setText("The withered plant was removed.");
        }
        updateLeftPanel(model);
    }

    public void removeRock(MyFarmModel model, MyFarmView view, int i, int j) {
        boolean enoughCoins = model.player.getCoins() > 50;
        boolean isRock = model.land.landState[i][j] == LandState.BLOCKED;

        if (enoughCoins && isRock) {
            model.land.landState[i][j] = LandState.UNPLOWED;
            view.bottomPanel.playerAction.setText("You have successfully removed a rock");
            model.player.setCoins(model.player.getCoins() - 50);
            model.player.setXP(model.player.getXP() + 15);
            levelUp(model, view);
            setPlotView(view.centerPanel.plotBtn[i][j], model.player,model.land.landState[i][j], model.land.crops[i][j]);
        } else if (!isRock){
            view.bottomPanel.playerAction.setText("There is no rock to remove.");
        } else if (!enoughCoins){
            view.bottomPanel.playerAction.setText("You don't have enough coins.");
        }
        updateLeftPanel(model);
    }

    private boolean isBeneficialTitle(Title title){
        switch (title){
            case REGISTERED_FARMER:
                return model.player.getTitle() == Title.FARMER;
            case DISTINGUISHED_FARMER:
                return model.player.getTitle() == Title.FARMER ||
                        model.player.getTitle() == Title.REGISTERED_FARMER;
            case LEGENDARY_FARMER:
                return !(model.player.getTitle() == Title.LEGENDARY_FARMER);
            default:
                return false;
        }
    }
}