package PuzzleOneFiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Utility.Clock;

public class PuzzleOne {

	/**
	 * The size of the population
	 */
	private final int POPULATION_SIZE = 100;

	/**
	 * Percent of the population to act upon. Actions include culling the
	 * population and reproducing for the next generation.
	 */
	private final double POPULATION_ACTION_PERCENT = 0.10;

	/**
	 * Goal that the optimal sequence will try to sum up to and not go over
	 */
	private int goal = -1;

	/**
	 * An array of the possible numbers that are able to be used in a sequence
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

	/**
	 * Random number generator
	 */
	private Random randomGenerator = new Random();

	/**
	 * Construct a PuzzleOne with a time limit
	 * 
	 * @param time
	 *            The time in milliseconds that the algorithm has to solve the
	 *            problem
	 */
	public PuzzleOne(int time) {
		clock = new Clock(time);
	}

	/**
	 * Set the goal for the sequence to sum up to.
	 * 
	 * @param goal
	 *            An int.
	 */
	public void setGoal(int goal) {
		this.goal = goal;

		// print out goal
		System.out.println("Goal from file: " + goal);
	}

	/**
	 * Get the goal the sequence should try to sum up to.
	 * 
	 * @return An int;
	 */
	public int getGoal() {
		return goal;
	}

	/**
	 * Set the array of possible numbers in a sequence
	 * 
	 * @param possibleNumbers
	 *            An array of ints.
	 */
	public void setPossibleNumbers(int[] possibleNumbers) {
		this.possibleNumbers = possibleNumbers;

		// print out possible numbers
		System.out.println("Possible numbers to use in sequence:");
		for (int i = 0; i < possibleNumbers.length; i++) {
			System.out.print(possibleNumbers[i] + ", ");
			if (i % 10 == 0)
				System.out.print("\n");
		}
		System.out.print("\n");
	}

	/**
	 * Return the array of possible numbers that the sequence can be made up of
	 * 
	 * @return An array of ints
	 */
	public int[] getPossibleNumbers() {
		return possibleNumbers;
	}

	/**
	 * Initialize the population, then run a cull and reproduce cycle until the
	 * algorithm is out of time
	 */
	public void solvePuzzle() {
		if (goal == -1 || possibleNumbers == null) {
			System.out
					.println("PuzzleOne::solvePuzzle(): goal or possibleNumbers is not initialized!");
			return;
		}

		// initialize population with random sequences
		initializePopulation();

		// print header of results
		System.out.printf("%23s\t%23s\t%23s\t%23s\t%23s\t%23s\t%23s\n",
				"Current Generation", "Most Fit Fitness",
				"Most Fit Generation", "Median Fit Fitness",
				"Median Fit Generation", "Worst Fit Fitness",
				"Worst Fit Generation");
		// keep culling and reproducing until time is up
		while (!clock.overTargetTime()) {
			// every few generations print out most fit, median fit, and worst
			// fit
			if (generation % 200 == 0) {
				Collections.sort(population);

				NumberSequence mostFit = population.get(population.size()-1);
				NumberSequence medianFit = population
						.get((int) (population.size() / 2) - 1);
				NumberSequence worstFit = population.get(0);
				System.out.printf("%23s\t%23s\t%23s\t%23s\t%23s\t%23s\t%23s\n\n",
						generation, mostFit.getFitness(),
						mostFit.getGeneration(), medianFit.getFitness(),
						medianFit.getGeneration(), worstFit.getFitness(),
						worstFit.getGeneration());
			}

			// remove a portion of the population - comment out for no culling
			cullPopulation();

			// reproduce with the fittest more likely being parents
			reproduce();

			generation++;
		}
		Collections.sort(population);
		NumberSequence mostFit1 = population.get(population.size() - 1);
		NumberSequence medianFit = population
				.get((int) (population.size() / 2) - 1);
		NumberSequence worstFit = population.get(0);
		System.out.printf("%23s\t%23s\t%23s\t%23s\t%23s\t%23s\t%23s\n",
				generation, mostFit1.getFitness(), mostFit1.getGeneration(),
				medianFit.getFitness(), medianFit.getGeneration(),
				worstFit.getFitness(), worstFit.getGeneration());
		
		// print out information about most fit
		Collections.sort(population);
		Collections.reverse(population);
		NumberSequence mostFit = population.get(0);
		int sum = 0;
		System.out.printf("\n\nBest in population is\n");
		System.out.printf("Sequence: \n");
		for(int i = 0; i < mostFit.getSequence().length; i++){
			if(mostFit.getSequence()[i]){
				System.out.printf("%d, ", possibleNumbers[i]);
				sum += possibleNumbers[i];
			}
		}
		if(sum > goal){
			sum = 0;
		}
		System.out.printf("\nFitness: %d\n", mostFit.getFitness());
		System.out.printf("Goal: %d\n", goal);
		System.out.printf("Score: %d\n", sum);

	}

	/**
	 * Initialize the population of the algorithm.
	 */
	public void initializePopulation() {

		// create the population
		for (int i = 0; i < POPULATION_SIZE; i++) {
			// get a random length for the number sequence
			// int randArraySize =
			// randomGenerator.nextInt(possibleNumbers.length-1)+1;

			boolean[] numSeq = new boolean[possibleNumbers.length];
			/*
			 * for(int j = 0; j < randArraySize; j++){ // get a random possible
			 * number and insert it into the sequence int randIndex =
			 * randomGenerator.nextInt(possibleNumbers.length); numSeq[j] =
			 * possibleNumbers[randIndex]; }
			 */
			/*
			 * add a new number sequence with the newly created sequence and the
			 * goal from the input
			 */
			for (int j = 0; j < possibleNumbers.length; j++) {
				// get whether a particular number will be added and insert it
				// into the sequence
				int randIndex = randomGenerator.nextInt(2);
				if (randIndex == 0) {
					numSeq[j] = false;
				} else {
					numSeq[j] = true;
				}
				
			}
			population.add(new NumberSequence(numSeq, goal, generation,
					possibleNumbers));
		}
	}

	/**
	 * Cull the current population to the fittest genes
	 */
	public void cullPopulation() {

		// sort the population by the fitness function from low to high
		Collections.sort(population);
		
		//no elitism
		//Collections.shuffle(population, new Random());

		// remove 10% of the weakest part of the population
		for (int i = 0; i < (int) (POPULATION_SIZE * POPULATION_ACTION_PERCENT); i++) {
			population.remove(0);
		}
	}

	/**
	 * Reproduce with the current population
	 */
	public void reproduce() {

		// sort the population from strongest to weakest
		Collections.sort(population);
		Collections.reverse(population);
		//no elitism
		//Collections.shuffle(population, new Random());

		for (int i = 0; i < (int) (POPULATION_SIZE * POPULATION_ACTION_PERCENT); i += 2) {

			// get two random indexes for reproduction based on the exponential
			// function
			// (1/1000) * (randInt)^2 = index
			// this function favors sequences with higher fitness scores
			int randIndex1 = randomGenerator.nextInt((int) Math.sqrt(1000
					* POPULATION_SIZE * (1 - POPULATION_ACTION_PERCENT)));
			int randIndex2 = randomGenerator.nextInt((int) Math.sqrt(1000
					* POPULATION_SIZE * (1 - POPULATION_ACTION_PERCENT)));

			randIndex1 = (int) (Math.pow(randIndex1, 2) / 1000);
			randIndex2 = (int) (Math.pow(randIndex2, 2) / 1000);

			// get two sequences
			boolean[] array1 = population.get(randIndex1).getSequence();
			boolean[] array2 = population.get(randIndex2).getSequence();

			// find a splicing point
			int splicePoint = findSplicingPoint(array1, array2);

			// generate two new arrays based on the splicing point
			boolean[] newArray1 = generateNewArray(array1, array2, splicePoint);
			boolean[] newArray2 = generateNewArray(array2, array1, splicePoint);

			// create children number sequences from the new arrays
			NumberSequence ns1 = new NumberSequence(newArray1, goal,
					generation, possibleNumbers);
			NumberSequence ns2 = new NumberSequence(newArray2, goal,
					generation, possibleNumbers);

			// mutate the number sequences
			mutateArray(ns1);
			mutateArray(ns2);

			// add them into the population
			population.add(randIndex1, ns1);
			population.add(randIndex2, ns2);
		}
	}

	/**
	 * Mutate a percentage of the population. To form new sequences that are not
	 * in the original population.
	 */
	private void mutateArray(NumberSequence ns) {
		/*
		 * // randomly choose to either // 0 = add a random possible number to
		 * the sequence // 1 = change a number in the sequence randomly int
		 * randChoice = randomGenerator.nextInt(2);
		 * 
		 * // if choice is to add a random possible number to the sequence
		 * if(randChoice == 0){ // get a random possible number // add the
		 * possible number to the sequence int randPossibleNum =
		 * possibleNumbers[randomGenerator.nextInt(possibleNumbers.length)];
		 * ns.addToSequence(randPossibleNum); }
		 * 
		 * // if the choice is to change a number in the sequence else{ // get a
		 * random sequence index and a random possible number // change the
		 * number at the sequence index to the possible number int randSeqIndex
		 * = randomGenerator.nextInt(ns.getSequence().length); int
		 * randPossibleNum =
		 * possibleNumbers[randomGenerator.nextInt(possibleNumbers.length)];
		 * ns.changeInSequence(randSeqIndex, randPossibleNum); }
		 */
		// get a random sequence index to change whether a number is included in
		// the sequence or not
		int randSeqIndex = randomGenerator.nextInt(ns.getSequence().length);
		ns.changeInSequence(randSeqIndex);
		// make the change at the index

	}

	/**
	 * Finds a random position that will allow the algorithm to cut both arrays
	 * in half.
	 * 
	 * @param sequence1
	 *            The first sequence that will be cut.
	 * @param sequence2
	 *            The second sequence that will be cut.
	 * @return The position in the array to cut it in half.
	 */
	private int findSplicingPoint(boolean[] sequence1, boolean[] sequence2) {
		int shortestArrayLength = 0;

		// find out which array is shorter
		if (sequence1.length < sequence2.length)
			shortestArrayLength = sequence1.length;
		else
			shortestArrayLength = sequence2.length;

		if (shortestArrayLength == 0)
			return 0;

		// return a random number between 0 and the shortest ray length
		return randomGenerator.nextInt(shortestArrayLength);
	}

	/**
	 * Generates an array with the left side of the first array from the
	 * splicing point and to the right side of the array from the splicing point
	 * 
	 * @param array1
	 *            The first array that will be cut and spliced (uses the left
	 *            side of this array)
	 * @param array2
	 *            The second array that will be cut and spliced (uses the right
	 *            side of this array)
	 * @param splicePoint
	 *            The point where the cut will be made
	 * @return Returns a new array formed from the old arrays cut at the splice
	 *         point.
	 */
	private static boolean[] generateNewArray(boolean[] array1,
			boolean[] array2, int splicePoint) {
		if (splicePoint > array1.length || splicePoint > array2.length) {
			System.out
					.println("Splice point is bigger than the smallest array length!");
			return new boolean[0];
		}

		// create a new array of array2.length
		boolean[] newArray = new boolean[array2.length];

		for (int i = 0; i < array2.length; i++) {
			// if it is before the splice point then take numbers from the first
			// array
			if (i < splicePoint) {
				newArray[i] = array1[i];
			}
			// if it is after the splice point then take numbers from the second
			// array
			else {
				newArray[i] = array2[i];
			}
		}

		return newArray;
	}

	/**
	 * Returns the number sequences that make up the population.
	 * 
	 * @return An ArrayList<NumberSequence> of the population
	 */
	public ArrayList<NumberSequence> getPopulation() {
		return population;
	}

	/**
	 * Returns the most fit number sequence from the population
	 * 
	 * @return A NumberSequence that has the highest fitness function from the
	 *         population.
	 */
	public NumberSequence mostFitInPopulation() {
		Collections.sort(population);
		return population.get(population.size() - 1);
	}

	/**
	 * Returns the generation that the algorithm is on.
	 * 
	 * @return An int representing the generation.
	 */
	public int getGeneration() {
		return generation;
	}
}
