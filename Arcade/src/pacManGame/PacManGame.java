package pacManGame;

import javax.swing.JFrame;

public class PacManGame extends JFrame {
	
	public PacManGame(){
		add(new PacMan());
	}
	
	public static void main(String[] args) {
		PacManGame appWindow = new PacManGame();
		appWindow.setVisible(true);
		appWindow.setTitle("PacMan");
		appWindow.setSize(380, 420);
		appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appWindow.setLocationRelativeTo(null);
	}
}
