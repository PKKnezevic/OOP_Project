package dataHandling;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
}
