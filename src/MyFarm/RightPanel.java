package MyFarm;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RightPanel {
	JPanel toolPanel = new JPanel();
	JPanel seedPanel = new JPanel();
	
    ImageIcon forward = new ImageIcon("src/MyFarm/icons/forward.png");
    ImageIcon turnip = new ImageIcon("src/MyFarm/icons/turnip.png");
    ImageIcon plowed = new ImageIcon("src/MyFarm/icons/plowed.png");

    CardLayout cardLayout = new CardLayout();
    JPanel rightCardPanel = new JPanel(cardLayout);
    
    JButton seedTurnip = new JButton();
    
    JButton forwardButton = new JButton("forward");
    JButton wateringCan = new JButton("watering can");
    JButton pickaxe = new JButton("pickaxe");
    JButton shovel = new JButton ("shovel");
    JButton hoe = new JButton ("hoe");
    
    RightPanel(JLabel playerAction){
        rightCardPanel.setBackground(new Color(0xC0E5C8));
        rightCardPanel.setPreferredSize(new Dimension(125,100));
        
        toolPanel.setBackground(new Color(0xC0E5C8));
        toolPanel.setPreferredSize(new Dimension(125,100));
        
        seedPanel.setBackground(new Color(0xC0E5C8));
        seedPanel.setPreferredSize(new Dimension(125,100));
        
        initializeTools(playerAction);
        initializeSeeds(playerAction);
        
        rightCardPanel.add(toolPanel, "tool");
        rightCardPanel.add(seedPanel, "seed");
    }
    
    void initializeSeeds(JLabel playerAction)
    {
    	this.seedTurnip.setIcon(turnip);
    	this.seedTurnip.setBackground(new Color(0xAAE29F));
    	this.seedTurnip.setFocusable(false);

        seedTurnip.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                playerAction.setText("You planted a turnip.");
                cardLayout.next(rightCardPanel); // only next if planting is valid
            }
        });

        this.seedPanel.add(seedTurnip);
    }
    
    void initializeTools(JLabel playerAction) {

        
    	forwardButton.setFocusable(false);
    	forwardButton.setIcon(forward);

    	wateringCan.setFocusable(false);
    	wateringCan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerAction.setText("Select on a land to water");
                selectTool(wateringCan, pickaxe, shovel, hoe, 
                		"watering can", "pickaxe", "shovel", "hoe", playerAction);
            }
        });

    	pickaxe.setFocusable(false);
    	pickaxe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerAction.setText("Select on a land to remove a rock");
                selectTool(pickaxe, wateringCan, shovel, hoe, 
                		"pickaxe", "watering can", "shovel", "hoe", playerAction);
            }
        });

    	shovel.setFocusable(false);
    	shovel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerAction.setText("Select on a plant to remove");
                selectTool(shovel, wateringCan, pickaxe, hoe, 
                		"shovel", "watering can", "pickaxe", "hoe", playerAction);
            }
        });

    	hoe.setFocusable(false);
    	hoe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerAction.setText("Select on a land to plow");
                selectTool(hoe, wateringCan, shovel, pickaxe, 
                		"hoe", "watering can", "shovel", "pickaxe", playerAction);
            }
        });

        toolPanel.add(forwardButton);
        toolPanel.add(wateringCan);
        toolPanel.add(pickaxe);
        toolPanel.add(shovel);
        toolPanel.add(hoe);
    }

    // assume btn1 is the button, the rest are other btns

    void selectTool(JButton btn1, JButton btn2, JButton btn3, JButton btn4,
                    String toolName1, String toolName2, String toolName3,
                    String toolName4, JLabel playerAction) {
        if (btn2.getText().equals("selected") || //if theres already a selected tool, replace it with the tool being selected
                btn3.getText().equals("selected") ||
                btn4.getText().equals("selected")) {
            btn1.setText("selected");
            btn2.setText(toolName2);
            btn3.setText(toolName3);
            btn4.setText(toolName4);
        }
        else if (btn1.getText().equals("selected")) {
            btn1.setText(toolName1);
            playerAction.setText("");
        }
        else
            btn1.setText("selected");
    }

}
