package PuzzleTwoFiles;
import java.util.ArrayList;
import java.util.Collections;

import Utility.Clock;

public class PuzzleTwo {

	/**
	 * A clock to stop the algorithm when the time is up
	 */
	private Clock clock;
	
	/**
	 * An array of the possible numbers that 
	 * are able to be used in a sequence
	 */
	private int[] possibleNumbers = null;
	
	/**
	 * Number of generations the algorithm went for.
	 */
	private int generation = 0;
	
	private ArrayList<Bins> population = new ArrayList<Bins>();
	
	PuzzleTwo(int time){
		clock = new Clock(time);
	}
	

	public void setPossibleNumbers(int[] possibleNumbers){
		this.possibleNumbers = possibleNumbers;
	}
	
	public int[] getPossibleNumbers(){
		return possibleNumbers;
	}
	
	public void solvePuzzle(){
		if(possibleNumbers == null){
			System.out.println("PuzzleOne::solvePuzzle(): possibleNumbers is not initialized!");
			return;
		}
		
		// initialize population with random sequences
		initializePopulation();
		
		// keep culling and reproducing until time is up
		while(!clock.overTargetTime()){
			// remove a portion of the population
			cullPopulation();
			
			// reproduce with the fittest more likely being parents
			reproduce();
			
			// mutate a portion of the population
			mutatePopulation();
			
			generation++;
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
	
	private void mutatePopulation(){

	}
	
	public ArrayList<Bins> getPopulation(){
		return population;
	}
	
	/**
	 * Returns the most fit bins from the population
	 * @return A Bins that has the highest fitness function from the population.
	 */
	public Bins mostFitInPopulation(){
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
