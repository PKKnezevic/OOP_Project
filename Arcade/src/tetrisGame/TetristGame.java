package tetrisGame;

public class TetristGame implements Runnable {
	GameFrame gf;
	
	public TetristGame() {
		run();
	}

	@Override
	public void run() {
		gf = new GameFrame();
	}
	
}
