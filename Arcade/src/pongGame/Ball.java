package pongGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball extends Rectangle {
	Random rand;
	int yVelocity;
	int xVelocity;
	int initSpeed = 2;
	
	protected Ball(int x, int y, int width, int height){
		super(x,y,width,height);
		rand = new Random();
		int randX = rand.nextInt(2);
		if(randX == 0) {
			randX--;
		}
		setXDirection(randX * initSpeed);
		int randY = rand.nextInt(2);
		if(randY == 0) {
			randY--;
		}
		setYDirection(randY * initSpeed);
		
		
	}
	
	protected void setXDirection(int x) {
		xVelocity = x;
	}
	
	protected void setYDirection(int y) {
		yVelocity = y;
	}
	
	protected void move() {
		x += xVelocity;
		y += yVelocity;
	}
	
	protected void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}
	
	
}
