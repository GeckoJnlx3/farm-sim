package MyFarm;

import javax.swing.*;

import MyFarm.land.LandState;

import java.awt.*;
import java.util.ArrayList;

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
    
    //Tools & Seed
    ArrayList <ToolButton> toolButtonList = new ArrayList<ToolButton>();
    ArrayList<SeedButton> seedButtonList = new ArrayList<SeedButton>();

    public RightPanel(MyFarmModel model, MyFarmView view)
    {
        rightCardPanel.setBackground(Palette.GRASS.getColor());
        rightCardPanel.setPreferredSize(new Dimension(125,100));

        toolPanel.setBackground(Palette.GRASS.getColor());
        toolPanel.setPreferredSize(new Dimension(125,100));
        seedPanel.setBackground(Palette.GRASS.getColor());
        seedPanel.setPreferredSize(new Dimension(125,100));
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
