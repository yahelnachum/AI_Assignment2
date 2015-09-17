
public class PuzzleOne {

	/**
	 * Goal that the optimal sequence will 
	 * try to sum up to and not go over
	 */
	private int goal = -1;
	
	/**
	 * An array of the possible numbers that 
	 * are able to be used in a sequence
	 */
	private int[] possibleNumbers = null;
	
	/**
	 * A clock to stop the algorithm when the time is up
	 */
	private Clock clock;
	
	/**
	 * Construct a PuzzleOne with a time limit
	 * @param time The time in milliseconds that the algorithm has to solve the problem
	 */
	PuzzleOne(int time){
		clock = new Clock(time);
	}
	
	/**
	 * Set the goal for the sequence to sum up to.
	 * @param goal An int.
	 */
	public void setGoal(int goal){
		this.goal = goal;
	}
	
	/**
	 * Get the goal the sequence should try to sum up to.
	 * @return An int;
	 */
	public int getGoal(){
		return goal;
	}
	
	/**
	 * Set the array of possible numbers in a sequence
	 * @param possibleNumbers An array of ints.
	 */
	public void setPossibleNumbers(int[] possibleNumbers){
		this.possibleNumbers = possibleNumbers;
	}
	
	/**
	 * Return the array of possible numbers that the sequence can be made up of
	 * @return An array of ints
	 */
	public int[] getPossibleNumbers(){
		return possibleNumbers;
	}
	
	public void solvePuzzle(){
		if(goal == -1 || possibleNumbers == null){
			System.out.println("PuzzleOne::solvePuzzle(): goal or possibleNumbers is not initialized!");
			return;
		}
		
		initializePopulation();
		
		while(!clock.overTargetTime()){
			cullPopulation();
			reproduce();
		}
		
		System.out.println("Best solution is ...");
	}
	
	/**
	 * Initialize the population of the algorithm.
	 */
	public void initializePopulation(){
		
	}
	
	/**
	 * Cull the current population to the fittest genes
	 */
	public void cullPopulation(){
		
	}
	
	
	/**
	 * Reproduce with the current population
	 */
	public void reproduce(){
		
	}
}
