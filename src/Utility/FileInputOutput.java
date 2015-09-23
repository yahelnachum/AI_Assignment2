package Utility;
import java.io.*;
import java.util.ArrayList;

import PuzzleOneFiles.PuzzleOne;
import PuzzleThreeFiles.BuildingPiece;
import PuzzleThreeFiles.PuzzleThree;
import PuzzleTwoFiles.PuzzleTwo;

// skeleton of file input reader is taken from website in the next comment line
// http://stackoverflow.com/questions/4716503/best-way-to-read-a-text-file-in-java
public class FileInputOutput {
	
	
	/**
	 * Takes in a file name and parses it up for the genetic algorithm to 
	 * @param fileName The file to pars the numbers from.
	 */
	/**
	 * Takes in a file name and parses it up for the genetic algorithm to 
	 * @param fileName The file to pars the numbers from.
	 * @param obj The PuzzleOne object to set the goal and possible numbers array.
	 */
	public static void fileToPuzzleOne(String fileName, PuzzleOne obj) {
		try {
			// open up file
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			try {
				String line = br.readLine();
				
				if(line != null){
					obj.setGoal(Integer.parseInt(line));
					line = br.readLine();
				}
				
				ArrayList<Integer> possibleNumbers = new ArrayList<Integer>();
				
				while (line != null) {
					possibleNumbers.add(Integer.parseInt(line));
					line = br.readLine();
				}

				int[] possNums = new int[possibleNumbers.size()];
				for(int i = 0; i < possibleNumbers.size(); i++){
					possNums[i] = possibleNumbers.get(i);
				}
				
				obj.setPossibleNumbers(possNums);
				
				// close file
				br.close();
				
			} finally {

			}
		} catch (Exception e) {

		}
	}
	
	public static void fileToPuzzleTwo(String fileName, PuzzleTwo obj) {
		try {
			// open up file
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			try {
				String line = br.readLine();
				
				ArrayList<Double> possibleNumbers = new ArrayList<Double>();
				
				while (line != null) {
					possibleNumbers.add(Double.parseDouble(line));
					line = br.readLine();
				}

				double[] possNums = new double[possibleNumbers.size()];
				for(int i = 0; i < possibleNumbers.size(); i++){
					possNums[i] = possibleNumbers.get(i);
				}
				
				obj.setPossibleNumbers(possNums);
				
				// close file
				br.close();
				
			} finally {

			}
		}catch (Exception e) {

		}
		
	}
	
	public static void fileToPuzzleThree(String fileName, PuzzleThree obj) {
		try {
			// open up file
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			try {
				String line = br.readLine();

				ArrayList<BuildingPiece> possiblePieces = new ArrayList<BuildingPiece>();
				
				while (line != null) {
					String[] pieceAttributes = line.split("\t");
					possiblePieces.add(new BuildingPiece(	pieceAttributes[0],						// type
															Integer.parseInt(pieceAttributes[1]),	// width
															Integer.parseInt(pieceAttributes[2]), 	// strength
															Integer.parseInt(pieceAttributes[3])));	// cost
					line = br.readLine();
				}
				
				BuildingPiece[] possPieces = new BuildingPiece[possiblePieces.size()];
				for(int i = 0; i < possiblePieces.size(); i++){
					possPieces[i] = possiblePieces.get(i);
				}
				
				obj.setPossiblePieces(possPieces);
				
				// close file
				br.close();
				
			} finally {

			}
		} catch (Exception e) {

		}
	}
	
	
}

