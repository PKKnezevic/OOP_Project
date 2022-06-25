package pongGame;

import java.awt.Color;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	GamePanel gp;
	
	protected GameFrame(){
		gp = new GamePanel();
		add(gp);
		setTitle("Pong");
		setResizable(false);
		setBackground(Color.black);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
