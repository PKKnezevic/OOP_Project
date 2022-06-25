package pongGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ScoreTable extends Rectangle {
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int player1;
	int player2;
	
	ScoreTable(int width, int height){
		ScoreTable.GAME_WIDTH = width;
		ScoreTable.GAME_HEIGHT = height;
	}
	
	protected void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Consolas", Font.BOLD, 100));
		
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		
		g.drawString(String.valueOf(player1/10) + String.valueOf(player1%10), (GAME_WIDTH/2) - 140, 80);
		g.drawString(String.valueOf(player1/10) + String.valueOf(player2%10), (GAME_WIDTH/2) + 30, 80);
	}
}
