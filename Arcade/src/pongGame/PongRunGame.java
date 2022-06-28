package pongGame;

public class PongRunGame {
	static GameFrame gf;
	
	public PongRunGame(){}
	public void runGame() {
		gf = new GameFrame();
	}
	
	public GameFrame getGameFrame() {
		return gf;
	}
}
