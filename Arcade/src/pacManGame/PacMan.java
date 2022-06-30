package pacManGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import dataHandling.DataHandling;

/**
 * Adding the interface ActionListener enables the JPanel to respond to button pressses
 * @author Peter
 *
 */
public class PacMan extends JPanel implements ActionListener {
	private Dimension d;  //Dimension of the Panel
	private final Font smallFont = new Font("Arial", Font.BOLD, 13);
	private boolean inGame = false;
	private boolean dying = false;
	private final int BLOCK_SIZE = 24;  //Size of a single block unit
	private final int NUM_OF_BLOCKS = 15; //Number of rows and collumns
	private final int SCREEN_SIZE = NUM_OF_BLOCKS * BLOCK_SIZE; //Size of the GameScreen specifically
	private final int PACMAN_SPEED = 6;
	private int lives, scores;
	private int numberOfGhosts;
	private final int MAX_GHOSTS = 4;
	private int[] dx, dy; //Data position for the ghosts
	private int[] ghostX, ghostY, ghostDX, ghostDY, ghostSpeed; //General ghost data 
	
	private Image heart, ghost;
	private Image up, down, left, right;
	
	private int pacmanX, pacmanY, pacmanComanndX, pacmanCommandY; // Starting positions, Positional changes aka commands
	private int reqDX, reqDY; //Key inputs
	
	private final int validSpeeds[] = {1,2,3,4,5,6};
	private final int MAX_SPEED = 6;
	private int currentSpeed = 3;
	private short[] screenData; //Array of all the data that's displayed on the screen
	private Timer timer; //Game loop
	
//	 private final short levelData[] = {
//		    	19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
//		        17, 16, 16, 16, 16, 24, 16, 16, 16, 16, 16, 16, 16, 16, 20,
//		        25, 24, 24, 24, 28, 0, 17, 16, 16, 16, 16, 16, 16, 16, 20,
//		        0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 20,
//		        19, 18, 18, 18, 18, 18, 16, 16, 16, 16, 24, 24, 24, 24, 20,
//		        17, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0,  0,  0,   0, 21,
//		        17, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0,  0,  0,   0, 21,
//		        17, 16, 16, 16, 24, 16, 16, 16, 16, 20, 0,  0,  0,   0, 21,
//		        17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 18, 18, 18, 18, 20,
//		        17, 24, 24, 28, 0, 25, 24, 24, 16, 16, 16, 16, 16, 16, 20,
//		        21, 0,  0,  0,  0,  0,  0,   0, 17, 16, 16, 16, 16, 16, 20,
//		        17, 18, 18, 22, 0, 19, 18, 18, 16, 16, 16, 16, 16, 16, 20,
//		        17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,
//		        17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,
//		        25, 24, 24, 24, 26, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28
//		    };
	
	
	private final short levelData[] = {
			/**
			 * Left border holds the value -> 1     
			 * Top border holds the value -> 2
			 * Right border holds the value -> 4
			 * Bottom border holds the value -> 8 
			 * White dots aka Points hold the value -> 16
			 */
			19, 26, 26, 26, 18, 18, 18, 26, 18, 18, 18, 26, 18, 18, 22,
			21,  0,  0,  0, 17, 16, 20,  0, 17, 16, 20,  0, 17, 16, 20,
			21,  0, 19, 18, 16, 16, 28,  0, 25, 16, 20,  0, 17, 16, 20,
			21,  0, 25, 24, 16, 20,  0,  0,  0, 17, 20,  0, 17, 16, 20,
			21,  0,  0,  0, 17, 16, 18, 18, 18, 24, 28,  0, 17, 16, 20,
			17, 18, 22,  0, 17, 16, 16, 16, 20,  0,  0,  0, 17, 16, 20,
			17, 16, 20,  0, 17, 16, 16, 16, 20,  0, 19, 18, 16, 16, 20,
			17, 16, 20,  0, 17, 16, 16, 16, 20,  0, 17, 16, 24, 16, 20,
			17, 16, 16, 18, 16, 16, 16, 16, 20,  0, 17, 20,  0, 17, 20,
			17, 16, 16, 16, 16, 16, 16, 24, 16, 18, 16, 20,  0, 17, 20,
			17, 16, 16, 16, 16, 16, 20,  0, 17, 16, 16, 20,  0, 17, 20,
			17, 16, 16, 16, 16, 16, 20,  0, 25, 24, 24, 28,  0, 17, 20,
			17, 16, 16, 16, 16, 16, 20,  0,  0,  0,  0,  0,  0, 17, 20,
			17, 16, 16, 16, 16, 16, 16, 18, 18, 18, 18, 18, 18, 16, 20,
			25, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28
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
		up = new ImageIcon("./src/PacManImages/up.gif").getImage();
		left = new ImageIcon("./src/PacManImages/left.gif").getImage();
		right = new ImageIcon("./src/PacManImages/right.gif").getImage();
		ghost = new ImageIcon("./src/PacManImages/ghost.gif").getImage();
		heart = new ImageIcon("./src/PacManImages/heart.png").getImage();
	}
	
	private void initComponents() {
		screenData = new short[NUM_OF_BLOCKS * NUM_OF_BLOCKS];
		d = new Dimension(400,400);
		ghostX = new int[MAX_GHOSTS];
		ghostDX = new int[MAX_GHOSTS];
		ghostY = new int[MAX_GHOSTS];
		ghostDY = new int[MAX_GHOSTS];
		ghostSpeed = new int[MAX_GHOSTS];
		dx = new int[4];
		dy = new int[4];
		
		timer = new Timer(40, this);
		timer.start();
	}
	
	private void playGame(Graphics2D g2d) {
		int i = 0;
		if(dying) {
			death();
		} else {
			movePacMan();
			drawPacMan(g2d);
			moveGhosts(g2d);
			checkMaze(i);
		}
	}
	
	private void showIntroScreen(Graphics2D g2d) {
		String start = "Press SPACE to start";
		String movement = "Use arrows for movement";
		String escape = "Use ESC to leave";
		g2d.setColor(Color.yellow);
		g2d.drawString(start, SCREEN_SIZE/4, 200);
		g2d.drawString(escape, SCREEN_SIZE/4, 225);
		g2d.drawString(movement, SCREEN_SIZE/4, 250);
	}
	
	private void drawScore(Graphics2D g2d) {
		g2d.setFont(smallFont);
		g2d.setColor(Color.red);
		String score = "Score: " + scores;
		g2d.drawString(score, SCREEN_SIZE/2 + 96, SCREEN_SIZE + 16);
		
		for(int i = 0; i < lives; i++) {
			g2d.drawImage(heart, i * 28 + 8, SCREEN_SIZE + 1, this);
		}
	}
	
	/**
	 * Constantly checking if the maze has any points to collect
	 * @param i
	 */
	private void checkMaze(int i) {
		boolean finished = true;
		int var = NUM_OF_BLOCKS * NUM_OF_BLOCKS;
		while(i < var && finished) {
			if((screenData[i] & 16) != 0) {
				finished = false;
			}
			i++;
		} 
		
		if(finished) {
			scores += 50;
			if(numberOfGhosts < MAX_GHOSTS) {
				numberOfGhosts++;
			}
			if(currentSpeed < MAX_SPEED) {
				currentSpeed++;
			}
			initLevel();
		}
	}
	/**
	 * Upon running out of lives save score to file, else continue level
	 */
	private void death() {
		lives--;
		if(lives == 0) {
			inGame = false;
			DataHandling.saveScoreToFile("./PacManScore.txt", scores);
		}
		continueLevel();
	}
	/**
	 * Moving ghosts while making sure they can't go over borders
	 * @param g2d
	 */
	private void moveGhosts(Graphics2D g2d) {
		int pos;
		int count;
		for(int i = 0; i < numberOfGhosts; i++) {
			if(ghostX[i] % BLOCK_SIZE == 0 && ghostY[i] % BLOCK_SIZE == 0) {
				pos = ghostX[i] / BLOCK_SIZE + NUM_OF_BLOCKS * (int) (ghostY[i] / BLOCK_SIZE);
				count = 0;
				if((screenData[pos] & 1) == 0 && ghostDX[i] != 1) {
					dx[count] = -1;
					dy[count] = 0;
					count++;
				}
				if((screenData[pos] & 2) == 0 && ghostDY[i] != 1) {
					dx[count] = 0;
					dy[count] = -1;
					count++;
				}
				if((screenData[pos] & 4) == 0 && ghostDX[i] != -1) {
					dx[count] = 1;
					dy[count] = 0;
					count++;
				}
				if((screenData[pos] & 8) == 0 && ghostDY[i] != -1) {
					dx[count] = 0;
					dy[count] = 1;
					count++;
				}
				
				if(count == 0) {
					if((screenData[pos] & 15) == 15) {
						ghostDY[i] = 0;
						ghostDX[i] = 0;
					} else {
						ghostDY[i] = -ghostDY[i];
						ghostDX[i] = -ghostDX[i];
					}
				} else {
					count = (int) (Math.random() * count);
					if(count > 3) {
						count = 3;
					}
					ghostDX[i] = dx[count];
					ghostDY[i] = dy[count];
				}
			}
			ghostX[i] = ghostX[i] + (ghostDX[i] * ghostSpeed[i]);
			ghostY[i] = ghostY[i] + (ghostDY[i] * ghostSpeed[i]);
			drawGhost(g2d, ghostX[i] + 1, ghostY[i] + 1);
			
			if(pacmanX > (ghostX[i] - 12) && pacmanX < (ghostX[i] + 12) 
				&& pacmanY > (ghostY[i] - 12) && pacmanY < (ghostY[i] + 12) 
				&& inGame) {
				dying = true;
			}
		}
	}
	
	private void drawGhost(Graphics2D g2d, int x, int y) {
		g2d.drawImage(ghost, x, y, this);
	}
	
	/**
	 * Moving pacman over the screen while making sure it can't go over borders. Also giving a point upon moving over a dot
	 */
	private void movePacMan() {
		int pos;
		int ch;
		
		if(pacmanX % BLOCK_SIZE == 0 && pacmanY % BLOCK_SIZE == 0) {
			pos = pacmanX / BLOCK_SIZE + NUM_OF_BLOCKS * (int) (pacmanY / BLOCK_SIZE);
			ch = screenData[pos];
			
			if((ch & 16) != 0) {
				screenData[pos] = (short) (ch & 15);
				scores++;
			}
			
			if(reqDX != 0 || reqDY != 0) {
				if(!((reqDX == -1 && reqDY == 0 && (ch & 1) != 0) 
						|| (reqDX == 1 && reqDY == 0 && (ch & 4) != 0) 
						|| (reqDX == 0 && reqDY == -1 && (ch & 2) != 0) 
						|| (reqDX == 0 && reqDY == 1 && (ch & 8) != 0))) {
					pacmanComanndX = reqDX;
					pacmanCommandY = reqDY;
				}
			}
			
			//Checking if PacMan wants to go through the border
			if((pacmanComanndX == -1 && pacmanCommandY == 0 && (ch & 1) != 0)
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
	
	private void drawPacMan(Graphics2D g2d) {
		if(reqDX == -1) {
			g2d.drawImage(left, pacmanX + 1, pacmanY + 1, this);
		} else if(reqDX == 1) {
			g2d.drawImage(right, pacmanX + 1, pacmanY + 1, this);			
		} else if(reqDY == -1) {
			g2d.drawImage(up, pacmanX + 1, pacmanY + 1, this);						
		} else {
			g2d.drawImage(down, pacmanX + 1, pacmanY + 1, this);			
		}
	}
	
	private void drawMaze(Graphics2D g2d) {
		short i = 0;
		int x,y;
		
		for(y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
			for(x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {
				g2d.setColor(Color.magenta);
				g2d.setStroke(new BasicStroke(5));
				
				if(levelData[i] == 0) {
					g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
				}
				
				//Drawing left border
				if((screenData[i] & 1) != 0) {
					g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
				}
				
				//Drawing top border
				if((screenData[i] & 2) != 0) {
					g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
				}
				
				//Drawing right border
				if((screenData[i] & 4) != 0) {
					g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1, y + BLOCK_SIZE - 1);
				}
				
				//Drawing bottom border
				if((screenData[i] & 8) != 0) {
					g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1, y + BLOCK_SIZE - 1);
				}
				
				if((screenData[i] & 16) != 0){
					g2d.setColor(Color.white);
					g2d.fillOval(x + 10, y + 10, 6, 6);
				}
				i++;
			}
		}
	}
	
	private void initGame() {
		lives = 3;
		scores = 0;
		initLevel();
		numberOfGhosts = 4;
		currentSpeed = 3;
	}
	
	private void initLevel() {
		for(int i = 0; i < NUM_OF_BLOCKS * NUM_OF_BLOCKS; i++) {
			screenData[i] = levelData[i];
		}
		continueLevel();
	}
	
	/**
	 * Sets the ghosts and PacMan on their initial places
	 */
	private void continueLevel() {
		int dx = 1;
		int random;
		
		for(int i = 0; i < numberOfGhosts; i++) {
			ghostY[i] = 5 * BLOCK_SIZE;
			ghostX[i] = 5 * BLOCK_SIZE;
			ghostDY[i] = 0;
			ghostDX[i] = dx;
			dx = -dx;
			random = (int) (Math.random() * (currentSpeed + 1));
			
			if(random > currentSpeed) {
				random = currentSpeed;
			}
			
			ghostSpeed[i] = validSpeeds[random];
		}
		
		pacmanX = 8 * BLOCK_SIZE;
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
		g2d.fillRect(0, 0, d.width, d.height);
		
		drawMaze(g2d);
		drawScore(g2d);
		
		if(inGame) {
			playGame(g2d);
		} else {
			showIntroScreen(g2d);
		}
		
		Toolkit.getDefaultToolkit().sync();
		g2d.dispose();
	}
	
	private void disposeWindow() {
		SwingUtilities.getWindowAncestor(this).dispose();
	}
	
	/**
	 * Key listener which is used to operate the PacMan
	 * @author Peter
	 *
	 */
	class KeyClass extends KeyAdapter {
		
		@Override
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
					DataHandling.saveScoreToFile("./PacManScore.txt", scores);
					disposeWindow();
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
		repaint();
	}
}
