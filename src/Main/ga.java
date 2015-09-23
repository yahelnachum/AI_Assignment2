package Main;
import java.util.*;

import PuzzleOneFiles.NumberSequence;
import PuzzleOneFiles.PuzzleOne;
import PuzzleThreeFiles.PuzzleThree;
import Utility.Clock;
import Utility.FileInputOutput;

public class ga {

	static final int PUZZLE_ONE = 1;
	static final int PUZZLE_TWO = 2;
	static final int PUZZLE_THREE = 3;
	
	public static void main(String[] args) {
		Clock c = new Clock();
		// convert arguments to usable forms
		int puzzleToSolve = Integer.parseInt(args[0]);
		String fileName = args[1];
		int targetSeconds = Integer.parseInt(args[2]);
		
		System.out.println(
				"Arguments taken in:\n" +
				"args[0] = " + puzzleToSolve + "\n" +
				"args[1] = " + fileName + "\n" +
				"args[2] = " + targetSeconds + "\n");
		
		// go to the puzzle wanted to solve
		switch(puzzleToSolve){
		case PUZZLE_ONE:
			// set the clock
			PuzzleOne pz1 = new PuzzleOne(targetSeconds*1000);
			
			// get the goal and possible numbers to use from the file
			FileInputOutput.fileToPuzzleOne(fileName, pz1);
			
			// solve puzzle for remaining time
			pz1.solvePuzzle();

			break;
			
		case PUZZLE_TWO:
			break;
			
		case PUZZLE_THREE:	
			// set the clock
			PuzzleThree pz3 = new PuzzleThree(targetSeconds*1000);
			
			// get the possible pieces to use from the file
			FileInputOutput.fileToPuzzleThree(fileName, pz3);
			
			// solve puzzle for remaining time
			pz3.solvePuzzle();
			
			break;
		}
	}

}
