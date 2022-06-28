package dataHandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import pongGame.ScoreTable;

public class DataHandling {
	public static void saveScoresToFile(String filePath, Object scoreToSave) {
		File binFile = new File(filePath);		
		try {
			FileOutputStream fos = new FileOutputStream(binFile, true);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(scoreToSave);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			System.out.println("Error while writting to file!");
			return;
		}
	}
	
	public static LinkedList<ScoreTable> getPongScoresFromFile() {
		File binFile = new File("./ScoresPong.bin");
		LinkedList<ScoreTable> scores = new LinkedList<>();
		try {
			FileInputStream fis = new FileInputStream(binFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			while(fis.available() > 0) {
				ScoreTable score = (ScoreTable) ois.readObject();
				scores.add(score);
			}
			ois.close();
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return scores;
	}
}
