package tetrisGame;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Window;

public class GameFrame extends JFrame{
	private GamePanel gamePanel;
	
	
	public GameFrame() {
		initComponents();
		setTitle("TETRIS");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocation(800, 200);
		pack();
		startGame();
		
	}
	
	private void initComponents() {
		gamePanel = new GamePanel();
		getContentPane().add(gamePanel);
		gamePanel.setBackground(Color.LIGHT_GRAY);
	}
	
	private void startGame() {
		new GameThread(gamePanel).start();
	}
}
