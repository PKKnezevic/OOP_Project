package tetrisGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private static final Dimension SCREEN_SIZE = new Dimension(400, 600);
	private static final int CELL_SIZE = 30;
	private static int numberOfRows = 20;
	private static final int NUMBER_OF_COLLUMNS = 10;
	private TetrisBlock block;
	private Color[][] background;
	GameThread gameThread;
	private TetrisBlock[] tetrisBlocks;
	
	
	public GamePanel() {
		tetrisBlocks = new TetrisBlock[] {new IBlock(), new JBlock(), new SBlock(), new TBlock(), new OBlock(), new LBlock(), new ZBlock()};
		background = new Color[numberOfRows][NUMBER_OF_COLLUMNS];
		spawnBlock();
		setFocusable(true);
		setPreferredSize(SCREEN_SIZE);
	}
	
	protected void spawnBlock() {
		Random rand = new Random();
		block = tetrisBlocks[rand.nextInt(0, tetrisBlocks.length)];
		block.spawnBlock(NUMBER_OF_COLLUMNS);
	}
	
	protected boolean moveGridDown() {
		if(checkBottom() == false) {
			
			
			return false;
		}
			block.moveDown();
			repaint();
			return true;			
	}
	
	public boolean isBlockOutOfBounds() {
		if(block.getY() < 0) {
			block = null;
			return true;
		}
		return false;
	}
	
	
	
	public int clearLines() {
		boolean lineFilled;
		int linesCleard = 0;
		for(int row = numberOfRows - 1; row >= 0; row --) {
			lineFilled = true;
			for(int col = 0; col < NUMBER_OF_COLLUMNS; col++) {
				if(background[row][col] == null) {
					lineFilled = false;
					break;
				}
			}
			
			if(lineFilled) {
				linesCleard++;
				clearLine(row);
				shiftDown(row);
				clearLine(0);
				repaint();
			}
		}
		return linesCleard;
	}
	
	private void clearLine(int row) {
		for(int i = 0; i < NUMBER_OF_COLLUMNS; i++) {
			background[row][i] = null;
		}
	}
	
	private void shiftDown(int row) {
		for(int r = row; r > 0; r--) {
			for(int col = 0; col < NUMBER_OF_COLLUMNS; col++) {
				background[r][col] = background[r-1][col];
			}
		}
	}
	
	public void moveBlockToBackground() {
		int[][] shape = block.getShape();
		int height = block.getHeight();
		int width = block.getWidth();
		
		int xPosition = block.getX();
		int yPosition = block.getY();
		
		Color color = block.getColor();
		
		for(int r = 0; r < height; r++) {
			for(int c = 0; c < width; c++) {
				if(shape[r][c] == 1) {
					background[r + yPosition][c + xPosition] = color;
				}
			}
		}
	}
	
	private void drawBlock(Graphics g) {
		int h = block.getHeight();
		int w = block.getWidth();
		Color c = block.getColor();
		int[][] shape = block.getShape();
		
		for(int row = 0; row < h; row++) {
			for(int col = 0; col < w; col++) {
				if(shape[row][col] == 1) {
					int x = (block.getX() + col) * CELL_SIZE;
					int y = (block.getY() + row) * CELL_SIZE;
					
					drawSquare(g, c, x, y);
				}
			}
		}
	}
	
	private void drawBackground(Graphics g) {
		Color color;
		
		for(int i = 0; i < numberOfRows; i++) {
			for(int j = 0; j < NUMBER_OF_COLLUMNS; j++) {
				color = background[i][j];
				if(color != null) {
					
					int x = j * CELL_SIZE;
					int y = i * CELL_SIZE;
					drawSquare(g, color, x, y);
				}
			}
		}
	}

	private void drawSquare(Graphics g, Color color, int x, int y) {
		g.setColor(color);
		g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
		g.setColor(Color.black);
		g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
	}
	
	public void moveBlockRight() {
		if(block == null) {
			return;
		}else if(checkRight()) {
			block.moveRight();			
		}
		repaint();
	}
	
	public void moveBlockLeft() {
		if(block == null) {
			return;
		}else if(checkLeft()) {
			block.moveLeft();			
		}
		repaint();
	}
	
	public void dropBlockDown() {
		if(block == null) {
			return;
		}
		while(checkBottom()) {
			block.moveDown();
		}
		repaint();
	}
	
	public void rotateBlock() {
		if(block == null) {
			return;
		}
		block.rotateBlock();
		if(block.getLeftEdge() < 0) {
			block.setX(0);
		}
		if(block.getRightEdge() >= NUMBER_OF_COLLUMNS) {
			block.setX(NUMBER_OF_COLLUMNS - block.getWidth());
		}
		if(block.getBottom() >= numberOfRows) {
			block.setY(numberOfRows - block.getHeight());
		}
		repaint();
	}
	
	private boolean checkBottom() {
		if(block.getBottom() == numberOfRows) {
			return false;
		} else {
			int[][] shape = block.getShape();
			int width = block.getWidth();
			int height = block.getHeight();
			
			for(int col = 0; col < width; col++) {
				for(int row = height-1; row >= 0; row--) {
					if(shape[row][col] != 0) {
						int x = col + block.getX();
						int y = row + block.getY() + 1;
						if(y < 0) {
							break;
						}
						if(background[y][x] != null) {
							return false;
						}
						break;
					}
				}
			}
			return true;
		}
	}
	
	private boolean checkLeft() {
		if(block.getLeftEdge() == 0) {
			return false;
		} else {
			int[][] shape = block.getShape();
			int width = block.getWidth();
			int height = block.getHeight();
			
			for(int row = 0; row < height; row++) {
				for(int col = 0; col < width; col++) {
					if(shape[row][col] != 0) {
						int x = col + block.getX() - 1;
						int y = row + block.getY();
						if(y < 0) {
							break;
						}
						if(background[y][x] != null) {
							return false;
						}
						break;
					}
				}
			}
			
			return true;
		}
	}
	
	private boolean checkRight() {
		if(block.getRightEdge() == NUMBER_OF_COLLUMNS) {
			return false;
		}
		int[][] shape = block.getShape();
		int width = block.getWidth();
		int height = block.getHeight();
		
		for(int row = 0; row < height; row++) {
			for(int col = width-1; col >= 0; col--) {
				if(shape[row][col] != 0) {
					int x = col + block.getX() + 1;
					int y = row + block.getY();
					if(y < 0) {
						break;
					}
					if(background[y][x] != null) {
						return false;
					}
					break;
				}
			}
		}
			
		return true; 
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBlock(g);
		drawBackground(g);
		
		for(int i = 0; i < NUMBER_OF_COLLUMNS; i++ ) {
			for(int j = 0; j < numberOfRows; j++) {
				g.drawRect(i*CELL_SIZE, j * CELL_SIZE,CELL_SIZE, CELL_SIZE);				
			}
		}
	}
}
