package tetrisGame;

import javax.swing.SwingUtilities;

public class TetristGame {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new GameFrame();
			}
		});
	}
}
