package pacManGame;

import javax.swing.JFrame;

public class PacManGame extends JFrame implements Runnable {
	PacMan pacMan;
	public PacManGame(){
		add(pacMan = new PacMan());
		run();
	}
	@Override
	public void run() {
		setVisible(true);
		setTitle("PacMan");
		setSize(380, 420);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}