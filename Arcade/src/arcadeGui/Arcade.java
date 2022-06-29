package arcadeGui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dataHandling.DataHandling;
import pacManGame.PacManGame;
import pongGame.GameFrame;
import pongGame.ScoreTable;

public class Arcade extends JFrame {

	private JPanel contentPane;
	private JTextField txtChoseAGame;
	private JTextField numberOfGamesPong;
	private JTextField pongLastResult;
	private JTextField numberOfGamesTetris;
	private JTextField highScoreTetris;
	private JTextField numberOfGamesPacMan;
	private JTextField highScorePacMan;
	private int playedPongGames = 1;
	private int playedTetrisGames;
	private int playedPacManGames = 0;
	GameFrame pongGame;
	PacManGame pacManGame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Arcade frame = new Arcade();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Arcade() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtChoseAGame = new JTextField();
		txtChoseAGame.setHorizontalAlignment(SwingConstants.CENTER);
		txtChoseAGame.setFont(new Font("Consolas", Font.BOLD, 25));
		txtChoseAGame.setText("Chose a game you'd like to play!");
		txtChoseAGame.setBounds(69, 11, 491, 52);
		contentPane.add(txtChoseAGame);
		txtChoseAGame.setColumns(10);
		
		numberOfGamesPong = new JTextField();
		numberOfGamesPong.setBounds(50, 330, 150, 30);
		contentPane.add(numberOfGamesPong);
		numberOfGamesPong.setColumns(10);
		
		pongLastResult = new JTextField();
		pongLastResult.setBounds(50, 370, 150, 30);
		contentPane.add(pongLastResult);
		pongLastResult.setColumns(10);
		
		JButton pongButton = new JButton("Pong");
		pongButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		pongButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pongGame = new GameFrame();
				
				if(pongGame.isActive() != true) {
					LinkedList<ScoreTable> pongScores = DataHandling.getPongScoresFromFile();
					ScoreTable  lastScore = pongScores.getLast();
					numberOfGamesPong.setText("Played games: " + ++playedPongGames);
					pongLastResult.setText("Last result: B->" + lastScore.getPlayerScores()[0] + " " + lastScore.getPlayerScores()[1] + "<-R");					
				}
			}		
		});
		pongButton.setBounds(75, 410, 100, 40);
		contentPane.add(pongButton);
		
		numberOfGamesTetris = new JTextField();
		numberOfGamesTetris.setBounds(240, 330, 150, 30);
		contentPane.add(numberOfGamesTetris);
		numberOfGamesTetris.setColumns(10);
		
		highScoreTetris = new JTextField();
		highScoreTetris.setColumns(10);
		highScoreTetris.setBounds(240, 370, 150, 30);
		contentPane.add(highScoreTetris);
		
		JButton tetrisButton = new JButton("Tetris");
		tetrisButton.setEnabled(false);
		tetrisButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		tetrisButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tetrisButton.setBounds(265, 410, 100, 40);
		contentPane.add(tetrisButton);
		
		numberOfGamesPacMan = new JTextField();
		numberOfGamesPacMan.setColumns(10);
		numberOfGamesPacMan.setBounds(430, 330, 150, 30);
		contentPane.add(numberOfGamesPacMan);
		
		highScorePacMan = new JTextField();
		highScorePacMan.setColumns(10);
		highScorePacMan.setBounds(430, 370, 150, 30);
		contentPane.add(highScorePacMan);
		
		JButton pacManButton = new JButton("PacMan");
		pacManButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		pacManButton.setBounds(455, 410, 100, 40);
		pacManButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numberOfGamesPacMan.setText("Played games: " + ++playedPacManGames);
				pacManGame = new PacManGame();
				
			}
		});
		contentPane.add(pacManButton);
	}
}
