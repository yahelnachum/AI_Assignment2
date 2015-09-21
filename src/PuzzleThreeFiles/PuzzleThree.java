package PuzzleThreeFiles;

import java.util.ArrayList;
import java.util.Collections;

import Utility.Clock;

public class PuzzleThree {

	/**
	 * A clock to stop the algorithm when the time is up
	 */
	private Clock clock;
	
	/**
	 * An array of the possible pieces that 
	 * are able to be used in a building
	 */
	private BuildingPiece[] possiblePieces = null;
	
	/**
	 * Number of generations the algorithm went for.
	 */
	private int generation = 0;
	
	/**
	 * ArrayList of the buildings in the population
	 */
	private ArrayList<Building> population = new ArrayList<Building>();
	
	/**
	 * Sets the amount of time that the algorithm can run for
	 * @param time
	 */
	PuzzleThree(int time){
		clock = new Clock(time);
	}
	

	/**
	 * Sets the possible pieces that are able to be used in each building
	 * @param possiblePieces
	 */
	public void setPossiblePieces(BuildingPiece[] possiblePieces){
		this.possiblePieces = possiblePieces;
	}
	
	/**
	 * Gets the possible pieces that are able to be used in each building
	 * @return
	 */
	public BuildingPiece[] getPossibleNumbers(){
		return possiblePieces;
	}
	
	/**
	 * Solves the puzzle by initializing the population,
	 * then going into a loop of culling the population,
	 * reproducing it, and mutating it until the algorithm runs out of time.
	 */
	public void solvePuzzle(){
		if(possiblePieces == null){
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
	
	/**
	 * Mutate a portion of the population
	 */
	private void mutatePopulation(){

	}
	
	/**
	 * Get the current population of building objects
	 * @return
	 */
	public ArrayList<Building> getPopulation(){
		return population;
	}
	
	/**
	 * Returns the most fit building from the population
	 * @return A building that has the highest fitness function from the population.
	 */
	public Building mostFitInPopulation(){
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
