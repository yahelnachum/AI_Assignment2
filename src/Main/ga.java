package Main;
import java.util.*;

import PuzzleOneFiles.NumberSequence;
import PuzzleOneFiles.PuzzleOne;
import PuzzleThreeFiles.Building;
import PuzzleThreeFiles.BuildingPiece;
import PuzzleThreeFiles.PuzzleThree;
import PuzzleTwoFiles.Bins;
import PuzzleTwoFiles.PuzzleTwo;
import Utility.Clock;
import Utility.FileInputOutput;

public class ga {

	// constants for choosing which puzzle to solve
	static final int PUZZLE_ONE = 1;
	static final int PUZZLE_TWO = 2;
	static final int PUZZLE_THREE = 3;
	
	public static void main(String[] args) {
		
		// convert arguments to usable forms
		int puzzleToSolve = Integer.parseInt(args[0]);
		String fileName = args[1];
		int targetSeconds = Integer.parseInt(args[2]);
		
		// print out arguments taken
		System.out.println(
				"Arguments taken in:\n" +
				"args[0] = " + puzzleToSolve + "\n" +
				"args[1] = " + fileName + "\n" +
				"args[2] = " + targetSeconds + "\n");
		
		// go to the puzzle wanted to solve
		switch(puzzleToSolve){
		
		// puzzle one case
		case PUZZLE_ONE:
			// set the clock
			PuzzleOne pz1 = new PuzzleOne(targetSeconds*1000);
			
			// get the goal and possible numbers to use from the file
			FileInputOutput.fileToPuzzleOne(fileName, pz1);
			
			// solve puzzle for remaining time
			pz1.solvePuzzle();

			break;
			
		// puzzle two case
		case PUZZLE_TWO:
			// set the clock
						PuzzleTwo pz2 = new PuzzleTwo(targetSeconds*1000);			
						
						// get the possible pieces to use from the file
						FileInputOutput.fileToPuzzleTwo(fileName, pz2);
									
						// solve puzzle for remaining time
						pz2.solvePuzzle();
			break;
			
		// puzzle three case
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
