import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PuzzleOne {

	private final int POPULATION_SIZE = 100;
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
	 * The population of number sequences.
	 */
	private ArrayList<NumberSequence> population = new ArrayList<NumberSequence>();
	
	/**
	 * Number of generations the algorithm went for.
	 */
	private int generation = 0;
	
	private Random randomGenerator = new Random();
	
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
			generation++;
		}
		
		System.out.println("Best solution is ...");
	}
	
	/**
	 * Initialize the population of the algorithm.
	 */
	public void initializePopulation(){
		
		// create the population
		for(int i = 0; i < POPULATION_SIZE; i++){
			// get a random length for the number sequence
			int randArraySize = randomGenerator.nextInt(possibleNumbers.length);
			
			int[] numSeq = new int[randArraySize];
			for(int j = 0; j < randArraySize; j++){
				// get a random possible number and insert it into the sequence
				int randIndex = randomGenerator.nextInt(possibleNumbers.length);
				numSeq[j] = possibleNumbers[randIndex];
			}
			
			/* add a new number sequence with the newly created 
			 * sequence and the goal from the input */
			population.add(new NumberSequence(numSeq, goal));
		}
	}
	
	/**
	 * Cull the current population to the fittest genes
	 */
	public void cullPopulation(){
		
		// sort the population by the fitness function from low to high
		Collections.sort(population);
		
		// remove 10% of the weakest part of the population
		for(int i = 0; i < (int)(POPULATION_SIZE * 0.1); i++){
			population.remove(0);
		}
	}
	
	
	/**
	 * Reproduce with the current population
	 * **** NEEDS TO BE CHANGED, RIGHT NOW THERE IS A VERY SIMPLE REPRODUCE THAT JUST ADDS NEW OBJECTS INTO THE POPULATION ****
	 */
	public void reproduce(){
		
		for(int i = 0; i < (int)(POPULATION_SIZE / 10); i++){
			int randArraySize = randomGenerator.nextInt(possibleNumbers.length);
			
			int[] numSeq = new int[randArraySize];
			for(int j = 0; j < randArraySize; j++){
				int randIndex = randomGenerator.nextInt(possibleNumbers.length);
				numSeq[j] = possibleNumbers[randIndex];
			}
			
			population.add(new NumberSequence(numSeq, goal));
		}
	}
	
	/**
	 * Finds a random position that will allow the algorithm to cut both arrays in half.
	 * @param sequence1	The first sequence that will be cut.
	 * @param sequence2	The second sequence that will be cut.
	 * @return The position in the array to cut it in half.
	 */
	private int findSplicingPoint(int[] sequence1, int[] sequence2){
		int shortestArrayLength;
		if(sequence1.length < sequence2.length)
			shortestArrayLength = sequence1.length;
		else
			shortestArrayLength = sequence2.length;
		
		return randomGenerator.nextInt(shortestArrayLength);
	}
	
	/**
	 * Returns the number sequences that make up the population.
	 * @return An ArrayList<NumberSequence> of the population
	 */
	public ArrayList<NumberSequence> getPopulation(){
		return population;
	}
	
	/**
	 * Returns the most fit number sequence from the population
	 * @return A NumberSequence that has the highest fitness function from the population.
	 */
	public NumberSequence mostFitInPopulation(){
		Collections.sort(population);
		return population.get(population.size() - 1);
	}
	
	/**
	 * Returns the generation that the algorithm is on.
	 * @return An int representing the generation.
	 */
	public int getGeneration(){
		return generation;
	}
}
