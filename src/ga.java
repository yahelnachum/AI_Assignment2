import java.util.*;

public class ga {

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
		case 1:
			// set the clock
			PuzzleOne pz1 = new PuzzleOne(targetSeconds*1000);
			
			// get the goal and possible numbers to use from the file
			FileInputOutput.fileToPuzzleOne(fileName, pz1);
			
			System.out.println("Goal from file: " + pz1.getGoal());
			
			int[] possNums = pz1.getPossibleNumbers();
			for(int i = 0; i < possNums.length; i++){
				System.out.print(possNums[i] + ", ");
			}
			System.out.print("\n");
			
			System.out.print("Testing Collections.sort \n");
			ArrayList<NumberSequence> test = new ArrayList<NumberSequence>();
			NumberSequence n1 = new NumberSequence(new int[]{1,2,3}, 10);
			NumberSequence n2 = new NumberSequence(new int[]{1,7,3}, 10);
			NumberSequence n3 = new NumberSequence(new int[]{7,3}, 10);
			
			test.add(n3);
			test.add(n2);
			test.add(n1);
			
			Collections.sort(test);
			Collections.reverse(test);
			for(int i = 0; i < test.size(); i++){
				int[] seq = test.get(i).getSequence();
				for(int j = 0; j < seq.length; j++){
					System.out.print(seq[j] + ", ");
				}
				System.out.print("\n");
			}
			
			//pz1.solvePuzzle();
			System.out.println("Time: " + c.delta());
			break;
		}
	}

}
