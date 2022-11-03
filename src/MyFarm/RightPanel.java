package MyFarm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightPanel
{
    ImageIcon turnip = new ImageIcon("icons/turnip.png");

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

    public RightPanel(JLabel playerAction)
    {
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

    public void initializeTools(JLabel playerAction) {
        forwardButton.setFocusable(false);

        wateringCan.setFocusable(false);
        wateringCan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerAction.setText("Select on a land to water");
                selectTool(playerAction, wateringCan, pickaxe, shovel, hoe, "watering can", "pickaxe", "shovel", "hoe");
            }
        });

        pickaxe.setFocusable(false);
        pickaxe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerAction.setText("Select on a land to remove a rock");
                selectTool(playerAction, pickaxe, wateringCan, shovel, hoe, "pickaxe", "watering can", "shovel", "hoe");
            }
        });

        shovel.setFocusable(false);
        shovel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerAction.setText("Select on a plant to remove");
                selectTool(playerAction, shovel, wateringCan, pickaxe, hoe, "shovel", "watering can", "pickaxe", "hoe");
            }
        });

        hoe.setFocusable(false);
        hoe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerAction.setText("Select on a land to plow");
                selectTool(playerAction, hoe, wateringCan, shovel, pickaxe, "hoe", "watering can", "shovel", "pickaxe");
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

    public void initializeSeeds(JLabel playerAction)
    {
        seedTurnip.setIcon(turnip);
        seedTurnip.setBackground(new Color(0xAAE29F));
        seedTurnip.setFocusable(false);

        seedTurnip.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                playerAction.setText("You planted a turnip.");
                cardLayout.next(rightCardPanel); // only next if planting is valid
            }
        });

        seedPanel.add(seedTurnip);
    }
}
