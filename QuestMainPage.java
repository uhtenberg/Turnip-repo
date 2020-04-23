
import java.awt.Color;
import java.awt.Font;


import javax.swing.*;

public class QuestMainPage {
	
	
	
	public static void main (String[] args) {
		
		JFrame mainFrame = new JFrame("QuestTest");
		JButton newGame = new JButton("New Game");
		JButton exitGame = new JButton("Exit");
		JLabel titleMain = new JLabel("Epic Quest Test");
		JPanel startPanel = new JPanel();
		
		
		Font titleFont = new Font("Arial", Font.BOLD,40);
		titleMain.setFont(titleFont);
		
		
		startPanel.setLayout(null);
		startPanel.setBackground(Color.ORANGE);
		startPanel.add(newGame);
		newGame.setBounds(200, 340, 100, 50);
		startPanel.add(exitGame);
		exitGame.setBounds(200, 400, 100, 50);
		startPanel.add(titleMain);
		titleMain.setBounds(100, 10, 300, 100);
		
		
		mainFrame.setContentPane(startPanel);
		mainFrame.setSize(500, 500);
		mainFrame.setVisible(true);
	}

}
