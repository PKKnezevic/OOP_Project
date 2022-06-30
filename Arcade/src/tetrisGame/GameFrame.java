package tetrisGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class GameFrame extends JFrame{
	private GamePanel gamePanel;
	
	
	public GameFrame() {
		initComponents();
		setTitle("TETRIS");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocation(800, 200);
		pack();
		initControls();
		startGame();
		
	}
	
	private void initControls() {
		InputMap im = this.getRootPane().getInputMap();
		ActionMap am = this.getRootPane().getActionMap();
		
		im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
		im.put(KeyStroke.getKeyStroke("LEFT"), "left");
		im.put(KeyStroke.getKeyStroke("DOWN"), "down");
		im.put(KeyStroke.getKeyStroke("UP"), "up");
		
		am.put("right", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.moveBlockRight();
			}
		});
		
		am.put("left", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.moveBlockLeft();
			}
		});
		
		am.put("down", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.dropBlockDown();
			}
		});
		
		am.put("up", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.rotateBlock();
			}
		});
	}
	
	private void initComponents() {
		gamePanel = new GamePanel();
		getContentPane().add(gamePanel, BorderLayout.CENTER);
		gamePanel.setBackground(Color.LIGHT_GRAY);
		gamePanel.setLayout(null);
	}
	
	private void startGame() {
		new GameThread(gamePanel).start();
	}
}
