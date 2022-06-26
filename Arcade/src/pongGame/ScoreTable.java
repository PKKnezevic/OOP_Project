package pongGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ScoreTable extends Rectangle {
	/**
	 * Generirana automatska verzija serijaliziranog objekta
	 */
	private static final long serialVersionUID = 3724748560721367908L;
	private static int GAME_WIDTH;
	private static int GAME_HEIGHT;
	int player1;
	int player2;
	
	protected ScoreTable(int width, int height){
		ScoreTable.GAME_WIDTH = width;
		ScoreTable.GAME_HEIGHT = height;
	}
	
	protected void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Consolas", Font.BOLD, 100));
		
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		
		g.drawString(String.valueOf(player1/10) + String.valueOf(player1%10), (GAME_WIDTH/2) - 140, 80);
		g.drawString(String.valueOf(player2/10) + String.valueOf(player2%10), (GAME_WIDTH/2) + 30, 80);
		g.setFont(new Font("Verdana", Font.PLAIN, 15));
		g.drawString("Player_1 commands: W/S", (GAME_WIDTH/2)-215, 125);
		g.drawString("Player_2 commands: UP/DOWN", (GAME_WIDTH/2) + 15, 125);
	}
	
	protected int checkResults() {
		if(player1 == 15) {
			JFrame player1Won = new JFrame();
			JOptionPane.showMessageDialog(player1Won, "Player 1 won with a score of 15:" + player2);
			return 1;
		} else if(player2 == 15) {
			JFrame player2Won = new JFrame();
			JOptionPane.showMessageDialog(player2Won, "Player 2 won with a score of " + player1 + ":15");
			return 2;
		}
		return 0;
	}
}
