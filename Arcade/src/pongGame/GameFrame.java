package pongGame;

import java.awt.Color;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	GamePanel gp;
	
	public GameFrame(){
		gp = new GamePanel();
		add(gp);
		setTitle("Pong ---> First to 15");
		setResizable(false);
		setBackground(Color.black);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public GamePanel getPanel() {
		return gp;
	}
}
