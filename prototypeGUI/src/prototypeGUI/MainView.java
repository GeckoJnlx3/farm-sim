package prototypeGUI;

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
	
	public MainView () {
		this.mainFrame = new JFrame();
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setLayout(new BorderLayout());
		this.mainFrame.setSize(700,420);
		this.mainFrame.setResizable(false);
		this.mainFrame.setTitle("Farming Simulator");
		this.mainFrame.setIconImage(new ImageIcon("seedling.png").getImage());
		
		initializePanels();
		
		this.mainFrame.setVisible(true);
		
	} 
	
	void initializePanels(){
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color (0x5D5D5D)); //gray
		
		JLabel playerAction = new JLabel("");
		playerAction.setForeground(new Color (0xFFFFFF)); //white
		
		bottomPanel.add(playerAction);
		bottomPanel.setPreferredSize(new Dimension(50,100));
		this.mainFrame.add(bottomPanel, BorderLayout.SOUTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(5,10,5,5));
		centerPanel.setBackground(new Color (0xC0E5C8)); //green
		
		initializeSidePanels();
		
		ImageIcon seedling = new ImageIcon("seedling.png");
		
		JButton [][] landArray = new JButton [5][10];
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				landArray[i][j] = new JButton();
				
				landArray[i][j].setBackground(new Color (0x9F8C83)); //brown
				landArray[i][j].setForeground(new Color(0x8EE779)); //plant color
				landArray[i][j].setIcon(seedling);
				centerPanel.add(landArray[i][j]);
				
				landArray[i][j].addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						playerAction.setText("Land selected");
					}
				});
			}
		}

		this.mainFrame.add(centerPanel, BorderLayout.CENTER);
	}
	
	void initializeSidePanels() {
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		
		leftPanel.setBackground(new Color(0xC0E5C8));
		leftPanel.setPreferredSize(new Dimension(100,100));
		rightPanel.setBackground(new Color(0xC0E5C8));
		rightPanel.setPreferredSize(new Dimension(100,100));
		
		this.mainFrame.add(leftPanel, BorderLayout.WEST);
		this.mainFrame.add(rightPanel, BorderLayout.EAST);
	}
}
