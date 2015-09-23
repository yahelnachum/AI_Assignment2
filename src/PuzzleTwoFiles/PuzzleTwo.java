package PuzzleTwoFiles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import PuzzleOneFiles.NumberSequence;
import Utility.Clock;

public class PuzzleTwo {
	private final int POPULATION_SIZE = 30;
	private final double POPULATION_ACTION_PERCENT = 0.10;
	private Random randomGenerator = new Random();
	/**
	 * A clock to stop the algorithm when the time is up
	 */
	private Clock clock;
	
	/**
	 * An array of the possible numbers that 
	 * are able to be used in a sequence
	 */
	private double[] possibleNumbers = null;
	
	/**
	 * Number of generations the algorithm went for.
	 */
	private int generation = 0;
	
	private ArrayList<Bins> population = new ArrayList<Bins>();
	
	public PuzzleTwo(int time){
		clock = new Clock(time);
	}
	

	public void setPossibleNumbers(double[] possNums){
		this.possibleNumbers = possNums;
	}
	
	
	public double[] getPossibleNumbers(){
		return possibleNumbers;
	}
	
	public void solvePuzzle(){
		if(possibleNumbers == null){
			System.out.println("PuzzleOne::solvePuzzle(): possibleNumbers is not initialized!");
			return;
		}
		
		// initialize population with random sequences
		initializePopulation();
		System.out.printf("%23s\t%23s\t%23s\t%23s\t%23s\t%23s\t%23s\n", "Current Generation"
				, "Most Fit Fitness"
				, "Most Fit Generation"
				, "Median Fit Fitness"
				, "Median Fit Generation"
				, "Worst Fit Fitness"
				, "Worst Fit Generation");
		// keep culling and reproducing until time is up
		while(!clock.overTargetTime()){
			if(generation % 5000 == 0){
				Collections.sort(population);
				Bins mostFit = population.get(POPULATION_SIZE-1);
				Bins medianFit = population.get((int)(POPULATION_SIZE / 2) - 1);
				Bins worstFit = population.get(0);
				System.out.printf("%23s\t%23s\t%23s\t%23s\t%23s\t%23s\t%23s\n", generation, 
									mostFit.getFitness(), mostFit.getGeneration(),
									medianFit.getFitness(), medianFit.getGeneration(),
									worstFit.getFitness(), worstFit.getGeneration());
			}
			// remove a portion of the population
			cullPopulation();
			
			// reproduce with the fittest more likely being parents
			reproduce();
			
			// mutate a portion of the population
			//mutatePopulation();
			
			generation++;
		}
		
		System.out.println("Best solution is ...");
	}
	
	/**
	 * Initialize the population of the algorithm.
	 */
	public void initializePopulation(){
		for(int i = 0; i < POPULATION_SIZE; i++){			
			double[] numbin1 = new double[10];
			double[] numbin2 = new double[10];
			double[] numbin3 = new double[10];
			for(int j = 0; j < 10; j++){
				// get a random possible number and insert it into the sequence
				int randIndex = randomGenerator.nextInt(possibleNumbers.length);
				numbin1[j] = possibleNumbers[randIndex];
				numbin2[j] = possibleNumbers[randIndex];
				numbin3[j] = possibleNumbers[randIndex];
			}
			
			/* add a new number sequence with the newly created 
			 * sequence and the goal from the input */
			population.add(new Bins(numbin1, numbin2, numbin3, generation, possibleNumbers));
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
		Collections.sort(population);
		Collections.reverse(population);
		for(int i = 0; i < (int)(POPULATION_SIZE*POPULATION_ACTION_PERCENT); i+=2){
			int randIndex1 = randomGenerator.nextInt((int)Math.sqrt(1000*POPULATION_SIZE*(1-POPULATION_ACTION_PERCENT)));
			int randIndex2 = randomGenerator.nextInt((int)Math.sqrt(1000*POPULATION_SIZE*(1-POPULATION_ACTION_PERCENT)));
			
			randIndex1 = (int) (Math.pow(randIndex1, 2) / 1000);
			randIndex2 = (int) (Math.pow(randIndex2, 2) / 1000);
			
			double[] arraybin11 = population.get(randIndex1).getBinOne();
			double[] arraybin12 = population.get(randIndex2).getBinOne();
			
			double[] arraybin21 = population.get(randIndex1).getBinTwo();
			double[] arraybin22 = population.get(randIndex2).getBinTwo();
			
			double[] arraybin31 = population.get(randIndex1).getBinThree();
			double[] arraybin32 = population.get(randIndex2).getBinThree();
			
			int splicePointbin1 = findSplicingPoint(arraybin11, arraybin12);
			int splicePointbin2 = findSplicingPoint(arraybin21, arraybin22);
			int splicePointbin3 = findSplicingPoint(arraybin31, arraybin32);
			
			double[] newArraybin11 = generateNewArray(arraybin11, arraybin12, splicePointbin1);
			double[] newArraybin12 = generateNewArray(arraybin12, arraybin11, splicePointbin1);
			
			double[] newArraybin21 = generateNewArray(arraybin21, arraybin22, splicePointbin2);
			double[] newArraybin22 = generateNewArray(arraybin22, arraybin21, splicePointbin2);

			double[] newArraybin31 = generateNewArray(arraybin31, arraybin32, splicePointbin3);
			double[] newArraybin32 = generateNewArray(arraybin32, arraybin31, splicePointbin3);
			
			Bins nbin1st = new Bins(newArraybin11,newArraybin21,newArraybin31, generation, possibleNumbers);
			Bins nbin2nd = new Bins(newArraybin12,newArraybin22,newArraybin32, generation, possibleNumbers);
			
			mutatePopulation(nbin1st);
			mutatePopulation(nbin2nd);
			
			population.add(randIndex1, nbin1st);
			population.add(randIndex2, nbin2nd);
			
			
		}
		
	}
	
	private void mutatePopulation(Bins nbin){
		
		// randomly choose to either
		// 0 = add a random possible number to the sequence
		// 1 = change a number in the sequence randomly
		int randChoice = randomGenerator.nextInt(2);
			
		// if choice is to add a random possible number to the sequence
		if(randChoice == 0){
			// get a random possible number
			// add the possible number to the sequence
			int index1 = randomGenerator.nextInt(possibleNumbers.length/3-1);
			double randPossibleNum = possibleNumbers[index1];
			nbin.addToBin1(randPossibleNum);
		}
			
		// if the choice is to change a number in the sequence
		else{
			// get a random sequence index and a random possible number
			// change the number at the sequence index to the possible number
			int index2 = randomGenerator.nextInt(possibleNumbers.length/3-1);
			double randPossibleNum = possibleNumbers[index2];
			nbin.addToBin2(randPossibleNum);
		}
	}
	
	private int findSplicingPoint(double[] sequence1, double[] sequence2){
		int shortestArrayLength = 0;
		if(sequence1.length < sequence2.length)
			shortestArrayLength = sequence1.length;
		else
			shortestArrayLength = sequence2.length;
		
		if(shortestArrayLength == 0)
			return 0;
		
		return randomGenerator.nextInt(shortestArrayLength);
	}
	
	private static double[] generateNewArray(double[] array1, double[] array2, int splicePoint){
		if(splicePoint > array1.length || splicePoint > array2.length){
			System.out.println("Splice point is bigger than the smallest array length!");
			return new double[0];
		}
		
		// create a new array of array2.length
		double[] newArray = new double[array2.length];
		
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
