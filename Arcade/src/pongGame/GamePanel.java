package pongGame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dataHandling.DataHandling;

public class GamePanel extends JPanel implements Runnable {
	static final int WIDTH = 1280;
	static final int HEIGHT = 720;
	static final Dimension screenSize = new Dimension(WIDTH, HEIGHT);
	static final int BALL_DIM = 15;
	static final int PADDLE_WIDTH = 20;
	static final int PADDLE_HEIGHT = 100;
	protected int playerWon;
	private boolean status = true;
	private int choice;
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
		Random random = new Random();
		ball = new Ball((WIDTH/2) - (BALL_DIM/2), random.nextInt(HEIGHT-BALL_DIM), BALL_DIM, BALL_DIM);
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
		ball.draw(g);
		score.draw(g);
	}
	
	protected void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	
	/**
	 * Provjera sudaranja lopte i lopatica, te samih granica prozora
	 */
	protected void checkCollision() {
		//Odbijanje loptice
		if(ball.y <= 0) {
			ball.setYDirection(-ball.yVelocity);
		} else if(ball.y >= HEIGHT-BALL_DIM) {
			ball.setYDirection(-ball.yVelocity);
		}
		
		//Odbijanje loptice od lopatice
		if(ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity += 1;
			if(ball.yVelocity > 0) {
				ball.yVelocity += 1;
			} else {
				ball.yVelocity--;
			}
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		} else if(ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity += 1;
			if(ball.yVelocity > 0) {
				ball.yVelocity += 1;
			} else {
				ball.yVelocity--;
			}
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		
		//Zaustavlja igraca ako zeli izaci van prozora
		if(paddle1.y <= 0) {
			paddle1.y = 0;
		} else if(paddle1.y >= (HEIGHT - PADDLE_HEIGHT)) {
			paddle1.y = HEIGHT - PADDLE_HEIGHT;
		}
		
		if(paddle2.y <= 0) {
			paddle2.y = 0;
		} else if(paddle2.y >= (HEIGHT - PADDLE_HEIGHT)) {
			paddle2.y = HEIGHT - PADDLE_HEIGHT;
		}
		
		//Provjera dali je lopta prosla igraca
		if(ball.x <= 0) {
			score.player2++;
			newBall();
		} else if(ball.x  >= WIDTH-BALL_DIM) {
			score.player1++;
			newBall();
		}
		
		//Provjera koji je igrac pobijedio
		playerWon = score.checkResults();
		if(playerWon > 0) {
			status = false;
			DataHandling.saveScoresToFile("./Scores.bin", score);
			SwingUtilities.getWindowAncestor(this).dispose();
			gameThread.interrupt();
		}
	}
	
	/**
	 * Jednostavna petlja za igru
	 */
	public void run() {
		//Petlja u kojoj se vrti igra
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double nanoSeconds = 1000000000 / amountOfTicks;
		double delta = 0;
		while(status) {
			long now = System.nanoTime();
			delta += (now-lastTime)/nanoSeconds;
			lastTime = now;
			if(delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	
	/**
	 * Slusanje tipki od strane korisnika
	 * @author Peter
	 *
	 */
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
