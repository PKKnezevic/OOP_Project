package tetrisGame;

import java.awt.Color;
import java.util.Random;

public class TetrisBlock {
	private int[][] shape;
	private Color color;
	private int x,y;
	private int[][][] shapes;
	private int currentRotation;
	private Color[] colours = {Color.blue, Color.red, Color.yellow, Color.green};
	
	public TetrisBlock(int[][] shape) {
		this.shape = shape;
		initShapes();
	}
	
	public void spawnBlock(int gridWidth) {
		Random rand = new Random();
		currentRotation = rand.nextInt(shapes.length);
		shape = shapes[currentRotation];
		
		y = -getHeight();
		x = rand.nextInt(0, gridWidth - getWidth());
		
		color = colours[rand.nextInt(colours.length)];
	}
	
	private void initShapes() {
		shapes = new int[4][][];
		
		for(int i = 0; i < 4;i++) {
			int r = shape[0].length;
			int c = shape.length;
			
			shapes[i] = new int[r][c];
			
			for(int y = 0; y < r; y++) {
				for(int x = 0; x < c; x++) {
					shapes[i][y][x] = shape[c-x-1][y];
				}
			}
			
			shape = shapes[i];
		}
	}
	
	public int[][] getShape() {
		return shape;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getHeight() {
		return shape.length;
	}
	
	public int getWidth() {
		return shape[0].length;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void moveDown() {
		y++;
	}
	
	public void moveLeft() {
		x--;
	}
	
	public void moveRight() {
		x++;
	}
	
	public void rotateBlock() {
		currentRotation++;
		if(currentRotation > 3) {
			currentRotation = 0;
		}
		shape = shapes[currentRotation];
	}
	
	public int getBottom() {
		return y + getHeight();
	}
	
	public int getLeftEdge() {
		return x;
	}
	
	public int getRightEdge() {
		return x + getWidth();
	}
}
