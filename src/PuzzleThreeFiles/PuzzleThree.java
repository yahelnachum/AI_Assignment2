package PuzzleThreeFiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import PuzzleOneFiles.NumberSequence;
import Utility.Clock;

public class PuzzleThree {

	/**
	 * The size of the population
	 */
	private final int POPULATION_SIZE = 100;
	
	/**
	 * Percent of the population to act upon.
	 * Actions include culling the population and reproducing for the next generation.
	 */
	private final double POPULATION_ACTION_PERCENT = 0.10;
	
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
	 * Random number generator
	 */
	private Random randomGenerator = new Random();
	
	/**
	 * Sets the amount of time that the algorithm can run for
	 * @param time The time in milliseconds to run for
	 */
	public PuzzleThree(int time){
		clock = new Clock(time);
	}
	

	/**
	 * Sets the possible pieces that are able to be used in each building
	 * @param possiblePieces The building pieces that can be used in each building
	 */
	public void setPossiblePieces(BuildingPiece[] possiblePieces){
		this.possiblePieces = possiblePieces;
		
		// print out possible pieces
		System.out.println("Possible numbers to use in sequence:");
		for(int i = 0; i < possiblePieces.length; i++){
			System.out.printf("%10s, %5d, %5d, %5d\n", 	possiblePieces[i].getType(), 
														possiblePieces[i].getWidth(), 
														possiblePieces[i].getStrength(), 
														possiblePieces[i].getCost());
		}
		System.out.print("\n");
	}
	
	/**
	 * Gets the possible pieces that are able to be used in each building
	 * @return
	 */
	public BuildingPiece[] getPossiblePieces(){
		return possiblePieces;
	}
	
	/**
	 * Solves the puzzle by initializing the population,
	 * then going into a loop of culling the population,
	 * reproducing it, and mutating it until the algorithm runs out of time.
	 */
	public void solvePuzzle(){
		// check to make sure pieces was set
		if(possiblePieces == null){
			System.out.println("PuzzleThree::solvePuzzle(): possiblePieces is not initialized!");
			return;
		}
		
		// initialize population with random sequences
		initializePopulation();
		
		// print header of results
		System.out.printf("%23s\t%23s\t%23s\t%23s\t%23s\t%23s\t%23s\t%23s\t%23s\t%23s\n", "Current Generation"
				, "Most Fit Fitness"
				, "Most Fit Score"
				, "Most Fit Generation"
				, "Median Fit Fitness"
				, "Median Fit Score"
				, "Median Fit Generation"
				, "Worst Fit Fitness"
				, "Worst Fit Score"
				, "Worst Fit Generation");
		
				// keep culling and reproducing until time is up
		while(!clock.overTargetTime()){
			// every few generations print out generation, most fit, median fit, worst fit
			if(generation % 5000 == 0){
				Collections.sort(population);
				Building mostFit = population.get(POPULATION_SIZE-1);
				Building medianFit = population.get((int)(POPULATION_SIZE / 2) - 1);
				Building worstFit = population.get(0);
				System.out.printf("%23d\t%23d\t%23d\t%23d\t%23d\t%23d\t%23d\t%23d\t%23d\t%23d\n", generation, 
									mostFit.getFitness(), mostFit.getScore(), mostFit.getGeneration(),
									medianFit.getFitness(), medianFit.getScore(), medianFit.getGeneration(),
									worstFit.getFitness(), worstFit.getScore(), worstFit.getGeneration());
			}
			
			// remove a portion of the population
			cullPopulation();
			
			// reproduce with the fittest more likely being parents
			reproduce();
			
			generation++;
		}

		
		// print out information about most fit
		Collections.sort(population);
		Collections.reverse(population);
		Building mostFit = population.get(0);

		System.out.printf("\n\nBest in population is\n");
		System.out.printf("Sequence: \n");
		for(int i = 0; i < mostFit.getList().length; i++){
			System.out.printf("\t%s\n", mostFit.getList()[i].toString());

		}

		System.out.printf("\nFitness: %d\n", mostFit.getFitness());
		System.out.printf("Score: %d\n", mostFit.getScore());
	}
	
	/**
	 * Initialize the population of the algorithm.
	 */
	public void initializePopulation(){
		// create the population
		for(int i = 0; i < POPULATION_SIZE; i++){
			// get a random length for the building sequence
			int randArraySize = randomGenerator.nextInt(possiblePieces.length-1)+1;
			
			BuildingPiece[] pieceSequence = new BuildingPiece[randArraySize];
			for(int j = 0; j < randArraySize; j++){
				// get a random possible piece and insert it into the sequence
				int randIndex = randomGenerator.nextInt(possiblePieces.length);
				pieceSequence[j] = possiblePieces[randIndex];
			}
			
			/* add a new number sequence with the newly created 
			 * sequence from the input */
			population.add(new Building(pieceSequence, generation, possiblePieces));
		}
	}
	
	/**
	 * Cull the current population to the fittest genes
	 */
	public void cullPopulation(){
		// sort the population by the fitness function from low to high
		Collections.sort(population);
		
		// remove 10% of the weakest part of the population
		for(int i = 0; i < (int)(POPULATION_SIZE*POPULATION_ACTION_PERCENT); i++){
			population.remove(0);
		}

	}
	
	
	/**
	 * Reproduce with the current population
	 */
	public void reproduce(){
		// sort the population from strongest to weakest
		Collections.sort(population);
		Collections.reverse(population);
		
		for(int i = 0; i < (int)(POPULATION_SIZE*POPULATION_ACTION_PERCENT); i+=2){
			
			// get two random indexes for reproduction based on the exponential function
			// (1/1000) * (randInt)^2 = index
			// this function favors sequences with higher fitness scores
			int randIndex1 = randomGenerator.nextInt((int)Math.sqrt(1000*POPULATION_SIZE*(1-POPULATION_ACTION_PERCENT)));
			int randIndex2 = randomGenerator.nextInt((int)Math.sqrt(1000*POPULATION_SIZE*(1-POPULATION_ACTION_PERCENT)));
			
			randIndex1 = (int) (Math.pow(randIndex1, 2) / 1000);
			randIndex2 = (int) (Math.pow(randIndex2, 2) / 1000);
			
			// get two pieces
			BuildingPiece[] array1 = population.get(randIndex1).getList();
			BuildingPiece[] array2 = population.get(randIndex2).getList();
			
			// find a splicing point
			int splicePoint = findSplicingPoint(array1, array2);
			
			// generate two new arrays based on the splicing point
			BuildingPiece[] newArray1 = generateNewArray(array1, array2, splicePoint);
			BuildingPiece[] newArray2 = generateNewArray(array2, array1, splicePoint);
			
			// make new buildings with the new arrays
			Building bp1 = new Building(newArray1, generation, possiblePieces);
			Building bp2 = new Building(newArray2, generation, possiblePieces);
			
			// mutate the new building sequences
			mutateArray(bp1);
			mutateArray(bp2);
			
			// add them into the population
			population.add(randIndex1, bp1);
			population.add(randIndex2, bp2);
		}
	}
	
	/**
	 * Mutate a portion of the population
	 */
	private void mutateArray(Building building){
		// randomly choose to either
		// 0 = add a random possible piece to the building
		// 1 = change a piece in the sequence randomly
		int randChoice = randomGenerator.nextInt(2);
		
		int randSeqIndex = randomGenerator.nextInt(building.getList().length);
		
		// if choice is to add a random possible piece to the sequence
		if(randChoice == 0){
			// get a random possible number
			// add the possible number to the sequence
			BuildingPiece randPossiblePiece = possiblePieces[randomGenerator.nextInt(possiblePieces.length)];
			building.addToList(randSeqIndex, randPossiblePiece);
		}
		
		// if the choice is to change a number in the sequence
		else{
			// get a random sequence index and a random possible number
			// change the number at the sequence index to the possible number
			BuildingPiece randPossiblePiece = possiblePieces[randomGenerator.nextInt(possiblePieces.length)];
			building.changeInList(randSeqIndex, randPossiblePiece);
		}
	}
	
	/**
	 * Finds a random position that will allow the algorithm to cut both arrays in half.
	 * @param buildingSeq1	The first building sequence that will be cut.
	 * @param buildingSeq2	The second building sequence that will be cut.
	 * @return The position in the array to cut it in half.
	 */
	private int findSplicingPoint(BuildingPiece[] buildingSeq1, BuildingPiece[] buildingSeq2){
		int shortestArrayLength = 0;
		if(buildingSeq1.length < buildingSeq2.length)
			shortestArrayLength = buildingSeq1.length;
		else
			shortestArrayLength = buildingSeq2.length;
		
		if(shortestArrayLength == 0)
			return 0;
		
		return randomGenerator.nextInt(shortestArrayLength);
	}
	
	/**
	 * Generates an array with the left side 
	 * of the first array from the splicing 
	 * point and to the right side of the 
	 * array from the splicing point
	 * @param array1 The first array that will be cut and spliced (uses the left side of this array)
	 * @param array2 The second array that will be cut and spliced (uses the right side of this array)
	 * @param splicePoint The point where the cut will be made
	 * @return Returns a new array formed from the old arrays cut at the splice point.
	 */
	private static BuildingPiece[] generateNewArray(BuildingPiece[] array1, BuildingPiece[] array2, int splicePoint){
		if(splicePoint > array1.length || splicePoint > array2.length){
			System.out.println("Splice point is bigger than the smallest array length!");
			return new BuildingPiece[0];
		}
		
		// create a new array of array2.length
		BuildingPiece[] newArray = new BuildingPiece[array2.length];
		
		for(int i = 0; i < array2.length; i++){
			// if it is before the splice point then take numbers from the first array
			if(i < splicePoint){
				newArray[i] = array1[i];
			}
			// if it is after the splice point then take numbers from the second array
			else{
				newArray[i] = array2[i];
			}
		}
		
		return newArray;
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
