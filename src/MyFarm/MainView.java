package MyFarm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainView {
	JFrame mainFrame;
	ImageIcon seedling = new ImageIcon("seedling.png");
	
	JLabel playerAction = new JLabel("");
	JPanel bottomPanel = new JPanel();
	JPanel leftPanel = new JPanel();
	JPanel rightPanel = new JPanel();
	
	JButton wateringCan = new JButton("watering can");
	JButton pickaxe = new JButton("pickaxe");
	JButton shovel = new JButton ("shovel");
	JButton hoe = new JButton ("hoe");
	
	
	public MainView (Land land) {
		this.mainFrame = new JFrame();
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setSize(700,420);
		this.mainFrame.setResizable(false);
		this.mainFrame.setTitle("Farming Simulator");
		this.mainFrame.setIconImage(new ImageIcon("seedling.png").getImage());
		
		initializePanels(land.landState);
		
		this.mainFrame.setVisible(true);
		
	} 
	
	void initializePanels(LandState[][] landState){
		
		bottomPanel.setBackground(new Color (0x5D5D5D)); //gray
		
		playerAction.setForeground(new Color (0xFFFFFF)); //white
		
		bottomPanel.add(playerAction);
		bottomPanel.setPreferredSize(new Dimension(50,100));
		this.mainFrame.add(bottomPanel, BorderLayout.SOUTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(5,10,5,5));
		centerPanel.setBackground(new Color (0xC0E5C8)); //green
		
		initializeSidePanels();
		initializeTools();
		
		ImageIcon seedling = new ImageIcon("seedling.png");
		
		JButton [][] landArray = new JButton [5][10];
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				landArray[i][j] = new JButton();
				
				if (landState[i][j] == LandState.UNPLOWED)
					landArray[i][j].setBackground(new Color (0x9F8C83)); //brown
				else if (landState[i][j] == LandState.BLOCKED)
					landArray[i][j].setBackground(Color.lightGray);
				landArray[i][j].setForeground(new Color(0x8EE779)); //plant color
				landArray[i][j].setIcon(seedling);
				centerPanel.add(landArray[i][j]);
			}
		}
		this.mainFrame.add(centerPanel, BorderLayout.CENTER);
	}
	
	void initializeSidePanels() {
		leftPanel.setBackground(new Color(0xC0E5C8));
		leftPanel.setPreferredSize(new Dimension(100,100));
		rightPanel.setBackground(new Color(0xC0E5C8));
		rightPanel.setPreferredSize(new Dimension(100,100));
		
		this.mainFrame.add(leftPanel, BorderLayout.WEST);
		this.mainFrame.add(rightPanel, BorderLayout.EAST);
	}
	
	void initializeTools() {
		wateringCan.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	playerAction.setText("Select on a land to water");
		    	selectTool(wateringCan, pickaxe, shovel, hoe, "watering can", "pickaxe", "shovel", "hoe");
		    }
		});
		
		pickaxe.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	playerAction.setText("Select on a land to remove a rock");
		    	selectTool(pickaxe, wateringCan, shovel, hoe, "pickaxe", "watering can", "shovel", "hoe");
		    }
		});
		
		shovel.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	playerAction.setText("Select on a plant to remove");
		    	selectTool(shovel, wateringCan, pickaxe, hoe, "shovel", "watering can", "pickaxe", "hoe");
		    }
		});
		
		hoe.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	playerAction.setText("Select on a land to plow");
		    	selectTool(hoe, wateringCan, shovel, pickaxe, "hoe", "watering can", "shovel", "pickaxe");
		    }
		});
		
		rightPanel.add(wateringCan);
		rightPanel.add(pickaxe);
		rightPanel.add(shovel);
		rightPanel.add(hoe);
	}
	
	
	//assume btn1 is the button, the rest are other btns
	void selectTool(JButton btn1, JButton btn2, JButton btn3, JButton btn4, 
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
	
}
