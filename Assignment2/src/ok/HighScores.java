package ok;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author AhmedShemy
 */

/**
 * The HighScores class has everything related to the HighScores; saving it to a file, retrieving the highest score
 * from the high scores sheet, keeping the high scores sheet sorted descendingly and writing a new high score to the sheet.
 * 
 * @param highScore Integer holds the value of the high score.
 * @param saveScoresPath String holds the path of the high scores sheet file.
 * @param fileName String holds the name of the file containing the high scores.
 * @param currentNumber Integer holds the high score value in the line used for sorting purpose.
 * @param file File that is the high scores file.
 */

public class HighScores{
	private static int highScore = 0;
	private static String saveScoresPath;
	private static String fileName = "SaveScores";
	private static int currentNumber;
	private static File file = new File(saveScoresPath,fileName);

	/**
	 * Constructor. Initializes saveScoresPath, loads the highest score value.
	 */

	public HighScores(){
		try {
			saveScoresPath = HighScores.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		loadHighScore();
	}

	/**
	 * Method to load the highest score value from the high scores file if it exists and not empty. If file is empty, 
	 * it will set high score value to zero. if file does not exist it will create it.
	 */

	public static void loadHighScore() {
		try {
			if(!getFile().exists()) {
				getFile().createNewFile();
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(getFile())));
			if(getFile().length() == 0)
			{
				highScore = 0;
			}else {
				highScore = Integer.parseInt(reader.readLine());
			}
			reader.close();
			setHighScore();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to sort the high scores file in descending order by reading the file line by line and converting each line
	 * into int and adding it to an array list which will be sorted in reverse order "descending" and then the 
	 * high scores will be written to the file after clearing it (a high score per line) as strings.
	 */

	public static void sortHighScores() {
		BufferedReader reader = null; 
		BufferedWriter writer = null;
		try{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(getFile())));
			String currentLine = reader.readLine();
			ArrayList<Integer> highScoreLines = new ArrayList<Integer>();
			while (currentLine != null && !"".equals(currentLine))
			{
				currentNumber = Integer.parseInt(currentLine);
				highScoreLines.add(currentNumber);
				currentLine = reader.readLine();
			}
			Collections.sort(highScoreLines, Collections.reverseOrder());
			writer = new BufferedWriter(new FileWriter(getFile(), false));
			for (Integer i : highScoreLines)
			{
				writer.write(String.valueOf(i));
				writer.newLine();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally{
			try{
				if (reader != null)
				{
					reader.close();
				}

				if(writer != null)
				{
					writer.close();
				}
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method to set the high score if it was surpassed and write the new high score achieved to the high scores file.
	 */

	public static void setHighScore() {
		FileWriter output = null;
		if(StartFrame.rb5.isSelected()) {
			if(PlayerVsMachineFrame.getScorePlayer1() >= highScore) {
				highScore = PlayerVsMachineFrame.getScorePlayer1();
				try {
					output = new FileWriter(getFile(), true);
					BufferedWriter writer = new BufferedWriter(output);
					writer.append("" + highScore);
					writer.close();
					sortHighScores();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(StartFrame.rb4.isSelected()) {
			if(MultiPlayerFrame.getScorePlayer1() >= highScore) {
				highScore = MultiPlayerFrame.getScorePlayer1();
				try {
					output = new FileWriter(getFile(), true);
					BufferedWriter writer = new BufferedWriter(output);
					writer.append("" + highScore);
					writer.close();
					sortHighScores();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(MultiPlayerFrame.getScorePlayer2() >= highScore) {
				highScore = MultiPlayerFrame.getScorePlayer2();
				try {
					output = new FileWriter(getFile(), true);
					BufferedWriter writer = new BufferedWriter(output);
					writer.append("" + highScore);
					writer.close();
					sortHighScores();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//getters for different variables
	public static int getHighScore() {
		return highScore;
	}

	public static File getFile() {
		return file;
	}
}
