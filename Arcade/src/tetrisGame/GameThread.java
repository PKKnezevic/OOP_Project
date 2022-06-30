package tetrisGame;

public class GameThread extends Thread {
	private GamePanel gp;
	private GameFrame gf; //Putting a field reference of the frame to update values
	private int score;
	private int level = 1;
	private int scorePerLevel = 5;
	
	private int pause = 1000;
	private int speedupPerLevel = 150;
	
	public GameThread(GamePanel gp, GameFrame gf) {
		this.gp = gp;
		this.gf = gf;
	}
	@Override
	public void run() {
		while(true) {
			gp.spawnBlock();
			while(gp.moveGridDown()) {
				try {
					Thread.sleep(pause);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
			
			if(gp.isBlockOutOfBounds()) {
				System.out.println("GAME OVER");
				break;
			}
			
			gp.moveBlockToBackground();
			score += gp.clearLines();
			gf.updateScore(score);
			
			int lvl = (score/scorePerLevel) + 1;
			if(lvl > level) {
				level = lvl;
				gf.updateLevel(level);
				pause -= speedupPerLevel;
			}
		}
	}
}
