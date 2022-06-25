package pongGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {
	private int id;
	private int yVelocitiy;
	private int speed = 10;
	
	protected Paddle(int x, int y, int width, int height, int id){
		super(x,y,width, height);
		this.id = id;
	}
	
	protected void keyPressed(KeyEvent ke) {
		switch(id) {
		case 1:
			if(ke.getKeyCode() == KeyEvent.VK_W) {
				setYdirection(-speed);
				move();
			} else if(ke.getKeyCode() == KeyEvent.VK_S) {
				setYdirection(speed);
				move();
			}
			break;
		case 2:
			if(ke.getKeyCode() == KeyEvent.VK_UP) {
				setYdirection(-speed);
				move();
			} else if(ke.getKeyCode() == KeyEvent.VK_DOWN) {
				setYdirection(speed);
				move();
			}
			break;
		}
	}
		
	protected void keyReleased(KeyEvent ke) {	
		switch(id) {
		case 1:
			if(ke.getKeyCode() == KeyEvent.VK_W) {
				setYdirection(0);
				move();
			} else if(ke.getKeyCode() == KeyEvent.VK_S) {
				setYdirection(0);
				move();
			}
			break;
		case 2:
			if(ke.getKeyCode() == KeyEvent.VK_UP) {
				setYdirection(0);
				move();
			} else if(ke.getKeyCode() == KeyEvent.VK_DOWN) {
				setYdirection(0);
				move();
			}
			break;
		}
	}
	
	protected void setYdirection(int yDirection) {
		yVelocitiy = yDirection;
	}
	
	protected void move() {
		y = y + yVelocitiy;
	}
	
	protected void draw(Graphics g) {
		if(id == 1) {
			g.setColor(Color.blue);
			
		} else {
			g.setColor(Color.red);
		}
		g.fillRect(x, y, width, height);
	}
}
