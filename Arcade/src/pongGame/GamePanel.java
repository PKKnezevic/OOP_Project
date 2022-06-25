package pongGame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	static final int WIDTH = 1280;
	static final int HEIGHT = 720;
	static final Dimension screenSize = new Dimension(WIDTH, HEIGHT);
	static final int BALL_DIM = 15;
	static final int PADDLE_WIDTH = 20;
	static final int PADDLE_HEIGHT = 100;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random rand;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	ScoreTable score;
	
	
	protected GamePanel(){
		newPaddle();
		newBall();
		score = new ScoreTable(WIDTH, HEIGHT);
		setFocusable(true);
		addKeyListener(new KeyListen());
		setPreferredSize(screenSize);
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	protected void newBall() {
		
	}
	
	protected void newPaddle() {
		paddle1 = new Paddle(5, (HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new Paddle(-5+WIDTH-PADDLE_WIDTH, (HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
	}
	
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}
	
	protected void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
	}
	
	protected void move() {
		
	}
	
	protected void checkCollision() {
		
	}
	
	public void run() {
		//Petlja u kojoj se vrti igra
		long lastTime = System.nanoTime();
		double amountOfTicks = 60;
		double nanoSeconds = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now-lastTime);
			lastTime = now;
			if(delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	
	public class KeyListen extends KeyAdapter {
		public void keyPressed(KeyEvent ke) {
			paddle1.keyPressed(ke);
			paddle2.keyPressed(ke);
		}
		
		public void keyReleased(KeyEvent ke) {
			paddle1.keyReleased(ke);
			paddle2.keyReleased(ke);
		}
	}
}
