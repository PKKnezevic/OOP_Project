package arcadeGui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.NoSuchElementException;

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

public class Arcade extends JFrame{

	private JPanel contentPane;
	private JTextField txtChoseAGame;
	private JTextField numberOfGamesPong;
	private JTextField pongLastResult;
	private JTextField numberOfGamesTetris;
	private JTextField highScoreTetris;
	private JTextField numberOfGamesPacMan;
	private JTextField highScorePacMan;
	private int playedPongGames = 0;
	private int playedTetrisGames;
	private int playedPacManGames = 0;
	GameFrame pongGame;
	PacManGame pacManGame;
	private JButton pongButton;
	private JButton tetrisButton;
	private JButton pacManButton;
	
	public Arcade() {
		super("Arcade");
		setBounds(100, 100, 650, 500);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(txtChoseAGame);
		contentPane.add(numberOfGamesPong);
		contentPane.add(pongLastResult);
		contentPane.add(pongButton);
		contentPane.add(numberOfGamesTetris);
		contentPane.add(highScoreTetris);
		contentPane.add(tetrisButton);
		contentPane.add(numberOfGamesPacMan);
		contentPane.add(highScorePacMan);
		contentPane.add(pacManButton);
		
		layoutComponents();
		setComponents();
	}
	
	private void initComponents() {
		contentPane = new JPanel();
		txtChoseAGame = new JTextField(10);
		txtChoseAGame.setEditable(false);
		numberOfGamesPong = new JTextField(10);
		numberOfGamesPong.setEditable(false);
		pongLastResult = new JTextField(10);
		pongLastResult.setEditable(false);
		highScoreTetris = new JTextField(10);
		highScoreTetris.setEditable(false);
		numberOfGamesTetris = new JTextField(10);
		numberOfGamesTetris.setEditable(false);
		pongButton = new JButton("Pong");
		highScorePacMan = new JTextField(10);
		highScorePacMan.setEditable(false);
		tetrisButton = new JButton("Tetris");
		numberOfGamesPacMan = new JTextField(10);
		numberOfGamesPacMan.setEditable(false);
		pacManButton = new JButton("PacMan");
	}
	
	private void layoutComponents() {
		txtChoseAGame.setBounds(70, 10, 490, 50);
		numberOfGamesPong.setBounds(50, 330, 150, 30);
		pongLastResult.setBounds(50, 370, 150, 30);
		pongButton.setBounds(75, 410, 100, 40);
		numberOfGamesTetris.setBounds(240, 330, 150, 30);
		highScoreTetris.setBounds(240, 370, 150, 30);
		tetrisButton.setBounds(265, 410, 100, 40);
		numberOfGamesPacMan.setBounds(430, 330, 150, 30);
		highScorePacMan.setBounds(430, 370, 150, 30);
		pacManButton.setBounds(455, 410, 100, 40);
	}
	
	private void setComponents() {
		txtChoseAGame.setHorizontalAlignment(SwingConstants.CENTER);
		txtChoseAGame.setFont(new Font("Consolas", Font.BOLD, 25));
		txtChoseAGame.setText("Chose a game you'd like to play!");
		
		pongButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		pongButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pongGame = new GameFrame();
				
				if(pongGame.isActive() != true) {
					LinkedList<String> pongScores = DataHandling.getPongScoresFromFile("./PongScore.txt");
					String lastScore = null;
					try{
						lastScore = pongScores.getLast();
					} catch (NoSuchElementException nsee) {
						System.out.println("No such element exception");
					}
					numberOfGamesPong.setText("Played games: " + ++playedPongGames);
					if(lastScore == null) {
						pongLastResult.setText("No last result");
					} else {
						pongLastResult.setText("Last result: " + lastScore);											
					}
				}
			}		
		});
		
		tetrisButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		tetrisButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		pacManButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		pacManButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numberOfGamesPacMan.setText("Played games: " + ++playedPacManGames);
				pacManGame = new PacManGame();
				
			}
		});
	}
}
