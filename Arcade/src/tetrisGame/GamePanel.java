package tetrisGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	private static final Dimension SCREEN_SIZE = new Dimension(400, 600);
	private static final int CELL_SIZE = 30;
	private static int numberOfRows = 20;
	private static final int NUMBER_OF_COLLUMNS = 10;
	private int[][] lBlock = {{1,0},{1,0},{1,1}};
	Thread gameThread;
	Image image;
	Graphics graphics;
	
	
	public GamePanel() {
		setFocusable(true);
		setPreferredSize(SCREEN_SIZE);
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	private void drawBlock(Graphics g) {
		for(int row = 0; row < lBlock.length; row++) {
			for(int col = 0; col < lBlock[0].length; col++) {
				if(lBlock[row][col] == 1) {
					g.setColor(Color.green);
					g.fillRect(col*CELL_SIZE, row*CELL_SIZE, CELL_SIZE, CELL_SIZE);
					g.setColor(Color.black);
					g.drawRect(col*CELL_SIZE, row*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				}
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		for(int i = 0; i < NUMBER_OF_COLLUMNS; i++ ) {
			for(int j = 0; j < numberOfRows; j++) {
				g.drawRect(i*CELL_SIZE, j * CELL_SIZE,CELL_SIZE, CELL_SIZE);				
			}
		}
		super.paintComponents(g);
		
		drawBlock(g);
	}

	@Override
	public void run() {
		
	}
}
