import java.io.*;
import java.util.ArrayList;

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
	static void fileToPuzzleOne(String fileName, PuzzleOne obj) {
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
	
	static void fileToPuzzleTwo(String fileName) {
		
	}
	
	static void fileToPuzzleThree(String fileName) {
		
	}
	
	
}

