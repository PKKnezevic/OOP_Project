package dataHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class DataHandling{
	
	public static void saveScoresToFile(String filePath, int player1, int player2) {
		File file = new File(filePath);		
		try {
			StringBuilder strb = new StringBuilder();
			strb.append(String.valueOf(player1));
			strb.append(",");
			strb.append(String.valueOf(player2));
			strb.append("\n");
			PrintWriter pw = new PrintWriter(file);
			pw.write(strb.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveScoreToFile(String filePath, int score) {
		File file = new File(filePath);
		try {
			StringBuilder strb = new StringBuilder();
			strb.append(score);
			PrintWriter pw = new PrintWriter(file);
			pw.write(strb.toString());
			pw.flush();
			pw.close();
			
		} catch (IOException ioe) {
			System.out.println("Error while writing high score!");
		}
	}
	
	public static LinkedList<String> getPongScoresFromFile(String filepath) {
		File pongFile = new File(filepath);
		LinkedList<String> scores = new LinkedList<>();
		try {
			Scanner sc = new Scanner(pongFile);
			while(sc.hasNext()) {
				scores.add(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return scores;
	}
	
	public static int getHighScore(String filepath) {
		File file = new File(filepath);
		int num = 0;
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNext()) {
				num = sc.nextInt();
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("File missing..");
		}
		return num;
	}
}