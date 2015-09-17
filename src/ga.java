import java.util.*;

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
				"Arguements taken in:\n" +
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
			
			System.out.println("Goal from file: " + pz1.getGoal());
			
			System.out.println("Possible numbers to use in sequence:");
			int[] possNums = pz1.getPossibleNumbers();
			for(int i = 0; i < possNums.length; i++){
				System.out.print(possNums[i] + ", ");
			}
			System.out.print("\n");
			
			pz1.solvePuzzle();

			System.out.print("Printing out randomly generated population\n");
			ArrayList<NumberSequence> ans = pz1.getPopulation();
			Collections.sort(ans);
			for(int i = 0; i < ans.size(); i++){
				System.out.print("NumberSequence[" + i + "] sum: " + ans.get(i).getFitness() + " sequence: ");
				for(int j = 0; j < ans.get(i).getSequence().length; j++){
					System.out.print(ans.get(i).getSequence()[j] + ", ");
				}
				System.out.print("Goal: " + pz1.getGoal() + "\n");
			}
			
			System.out.println("Most fit sequence:");
			NumberSequence ns = pz1.mostFitInPopulation();
			for(int i = 0; i < ns.getSequence().length; i++){
				System.out.print(ns.getSequence()[i] + ", ");
			}
			System.out.println("\nFitness: " + ns.getFitness());
			System.out.println("\nGeneration: " + pz1.getGeneration());
			
			System.out.println("\nTime: " + c.delta());
			break;
		}
	}

}
