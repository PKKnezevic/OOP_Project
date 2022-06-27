package tetrisGame;

import java.awt.Color;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	private GamePanel gamePanel;
	
	public GameFrame() {
		initComponents();
		this.add(gamePanel);
		this.setSize(500, 640);
		setTitle("TETRIS");
		setResizable(false);
		setBackground(Color.gray);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	private void initComponents() {
		gamePanel = new GamePanel();
		
		
	}
}
