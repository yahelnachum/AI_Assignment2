
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
			
			//pz1.solvePuzzle();
			System.out.println("Time: " + c.delta());
			break;
		}
	}

}
