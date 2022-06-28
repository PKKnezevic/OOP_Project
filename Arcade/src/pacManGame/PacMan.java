package pacManGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PacMan extends JPanel implements ActionListener {
	private Dimension field;
	private final Font font = new Font("Arial", Font.BOLD, 13);
	private boolean inGame = false;
	private boolean dying = false;
	private final int BLOCK_SIZE = 24;
	private final int NUM_OF_BLOCKS = 15;
	private final int SCREEN_SIZE = NUM_OF_BLOCKS * BLOCK_SIZE;
	private final int MAX_GHOSTS = 4;
	private final int PACMAN_SPEED = 5;
	
	private int numberOfGhosts = 1;
	private int lives, scores;
	private int[] dx, dy;
	private int[] ghostX, ghostY, ghostDX, ghostDY, ghostSpeed;
	
	private Image heart, ghost;
	private Image up, down, left, right;
	
	private int pacmanX, pacmanY, pacmanComanndX, pacmanCommandY;
	private int reqDX, reqDY;
	
	private final int validSpeeds[] = {1,2,3,4,5,6};
	private final int MAX_SPEED = 6;
	private int currentSpeed = 3;
	private short[] screenData;
	private Timer timer;
	
	private final short levelData[] = {
			
	};
	
	public PacMan() {
		loadImages();
		initComponents();
		addKeyListener(new KeyClass());
		setFocusable(true);
		initGame();
		
	}
	
	private void loadImages() {
		down = new ImageIcon("./src/PacManImages/down.gif").getImage();
		up = new ImageIcon("/src/PacManImages/up.gif").getImage();
		left = new ImageIcon("/src/PacManImages/left.gif").getImage();
		right = new ImageIcon("/src/PacManImages/right.gif").getImage();
		ghost = new ImageIcon("/src/PacManImages/ghost.gif").getImage();
		heart = new ImageIcon("/src/PacManImages/haert.png").getImage();
	}
	
	private void initComponents() {
		screenData = new short[NUM_OF_BLOCKS*NUM_OF_BLOCKS];
		field = new Dimension(400,400);
		ghostX = new int [MAX_GHOSTS];
		ghostDX = new int [MAX_GHOSTS];
		ghostY = new int [MAX_GHOSTS];
		ghostDY = new int [MAX_GHOSTS];
		ghostSpeed = new int [MAX_GHOSTS];
		dx = new int[4];
		dy = new int[4];
		
		timer = new Timer(500, this);
		timer.start();
	}
	
	private void initGame() {
		lives = 3;
		scores = 0;
		initLevel();
		numberOfGhosts = 0;
		currentSpeed = 3;
	}
	
	private void initLevel() {
		for(int i = 0; i < NUM_OF_BLOCKS * NUM_OF_BLOCKS; i++) {
			screenData[i] = levelData[i];
		}
	}
	
	private void playGame(Graphics2D g2d) {
		if(dying) {
			death();
		} else {
			movePacMan();
			drawPacMan(g2d);
			moveGhosts(g2d);
			checkMaze();
		}
	}
	
	private void movePacMan() {
		int pos;
		short ch;
		
		if(pacmanX % BLOCK_SIZE == 0 && pacmanY % BLOCK_SIZE == 0) {
			pos = pacmanX / BLOCK_SIZE + NUM_OF_BLOCKS * (int) (pacmanY / BLOCK_SIZE);
			ch = screenData[pos];
			if((ch & 16) != 0) {
				screenData[pos] = (short) (ch & 15);
				scores++;
			}
			
			if(reqDX != 0 || reqDY != 0) {
				if(!((reqDX == -1 && reqDY == 0 && (ch & 1) != 0) 
				|| (reqDY == 1 && reqDY == 0 && (ch & 4) != 0) 
				|| (reqDX == 0 && reqDY == -1 && (ch & 2) != 0) 
				|| (reqDX == 0 && reqDY == 1 && (ch & 8) != 0))) {
					pacmanComanndX = reqDX;
					pacmanCommandY = reqDY;
				}
			}
			
			if((pacmanComanndX == -1 && pacmanY == 0 && (ch & 1) != 0)
			|| (pacmanComanndX == 1 && pacmanCommandY == 0 && (ch & 4) != 0)
			|| (pacmanComanndX == 0 && pacmanCommandY == -1 && (ch & 2) != 0)
			|| (pacmanComanndX == 0 && pacmanCommandY == 1 && (ch & 8) != 0)) {
				pacmanComanndX = 0;
				pacmanCommandY = 0;
			}
		}
		
		pacmanX = pacmanX + PACMAN_SPEED * pacmanComanndX;
		pacmanY = pacmanY + PACMAN_SPEED * pacmanCommandY;
	}
	
	private void drawPacman(Graphics2D g2d) {
		if(reqDX == -1) {
			g2d.drawImage(left, pacmanX+1, pacmanY +1, this);
		} else if(reqDX == 1) {
			g2d.drawImage(right, pacmanX+1, pacmanY +1, this);			
		} else if(reqDY == -1) {
			g2d.drawImage(up, pacmanX+1, pacmanY +1, this);						
		} else if(reqDY == 1) {
			g2d.drawImage(down, pacmanX+1, pacmanY +1, this);			
			
		}
	}
	
	private 
	
	private void continueLevel() {
		int dx = 1;
		int random;
		
		for(int i = 0; i < numberOfGhosts; i++) {
			ghostY[i] = 4 * BLOCK_SIZE;
			ghostX[i] = 4 * BLOCK_SIZE;
			ghostDY[i] = 0;
			ghostDX[i] = dx;
			dx = -dx;
			random = (int) (Math.random() * (currentSpeed + 1));
			
			if(random > currentSpeed) {
				random = currentSpeed;
			}
			
			ghostSpeed[i] = validSpeeds[random];
		}
		
		pacmanX = 7 * BLOCK_SIZE;
		pacmanY = 11 * BLOCK_SIZE;
		pacmanComanndX = 0;
		pacmanCommandY = 0;
		reqDX = 0;
		reqDY = 0;
		dying = false;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, field.width, field.height);
		
		drawMaze(g2d);
		drawScore(g2d);
		
		if(inGame) {
			playGame(g2d);
		} else {
			showIntroScreen(g2d);
		}
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	protected class KeyClass extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(inGame) {
				if(key == KeyEvent.VK_LEFT){
					reqDX = -1;
					reqDY = 0;
				} else if(key == KeyEvent.VK_RIGHT){
					reqDX = 1;
					reqDY = 0;
				} else if(key == KeyEvent.VK_DOWN){
					reqDX = 0;
					reqDY = 1;
				} else if(key == KeyEvent.VK_UP){
					reqDX = 0;
					reqDY = -1;
				} else if(key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
					inGame = false;
				}
			} else {
				if(key == KeyEvent.VK_SPACE) {
					inGame = true;
					initGame();
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
