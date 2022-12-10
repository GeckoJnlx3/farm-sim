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

    public RightPanel()
    {
        rightCardPanel.setBackground(Palette.GRASS.getColor());
        rightCardPanel.setPreferredSize(new Dimension(125,100));

        toolPanel.setBackground(Palette.GRASS.getColor());
        toolPanel.setPreferredSize(new Dimension(125,100));
        seedPanel.setBackground(Palette.GRASS.getColor());
        seedPanel.setPreferredSize(new Dimension(125,100));
    }
}
