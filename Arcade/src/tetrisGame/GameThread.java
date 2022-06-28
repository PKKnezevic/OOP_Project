package tetrisGame;

public class GameThread extends Thread {
	private GamePanel gp;
	
	public GameThread(GamePanel gp) {
		this.gp = gp;
	}
	@Override
	public void run() {
		while(true) {
			gp.spawnBlock();
			while(gp.moveGridDown()) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
		}
	}
}
