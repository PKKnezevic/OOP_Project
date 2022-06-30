package arcadeGui;

import javax.swing.SwingUtilities;

public class RunArcade {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Arcade();
			}
		});
	}

}
