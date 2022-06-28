package tetrisGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private static final Dimension SCREEN_SIZE = new Dimension(400, 600);
	private static final int CELL_SIZE = 30;
	private static int numberOfRows = 20;
	private static final int NUMBER_OF_COLLUMNS = 10;
	private TetrisBlock block;
	private Color[][] background;
	GameThread gameThread;
	
	
	public GamePanel() {
		spawnBlock();
		setFocusable(true);
		setPreferredSize(SCREEN_SIZE);
		background = new Color[numberOfRows][NUMBER_OF_COLLUMNS];
	}
	
	protected void spawnBlock() {
		block = new TetrisBlock(new int[][]{{1,0},{1,0},{1,1}},Color.blue);
		block.spawnBlock(NUMBER_OF_COLLUMNS);
	}
	
	protected boolean moveGridDown() {
		if(checkBottom() == false) {
			moveBlockToBackground();
			return false;
		} else {
			block.moveDown();
			repaint();
			return true;			
		}
	}
	
	private boolean checkBottom() {
		if(block.getBottom() == numberOfRows) {
			return false;
		} else {
			return true;
		}
	}
	
	private void moveBlockToBackground() {
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
					
					int x = i * CELL_SIZE;
					int y = j * CELL_SIZE;
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

	@Override
	protected void paintComponent(Graphics g) {
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
