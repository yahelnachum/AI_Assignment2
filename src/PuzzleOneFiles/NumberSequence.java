package PuzzleOneFiles;

public class NumberSequence implements Comparable<NumberSequence> {

	/**
	 * The integers that are part of the sequence.
	 */
	private int[] sequence;
	
	/**
	 * The number that the sequence is trying to add up to 
	 */
	private int goal;
	
	/**
	 * The generation of the number sequence.
	 */
	private int generation;
	
	/**
	 * An array of the possible numbers that 
	 * are able to be used in a sequence
	 */
	private int[] possibleNumbers = null;
	
	/**
	 * Create an object with the given array as the sequence of numbers trying to add up to the goal.
	 * @param sequence The array of numbers trying to add up to get the goal or second closest that doesn't go over the goal.
	 * @param goal The goal that the array is supposed to sum to without going over.
	 * @param generation The generation number of the current generation.
	 */
	NumberSequence(int[] sequence, int goal, int generation, int[] possibleNumbers){
		this.sequence = sequence;
		this.goal = goal;
		this.generation = generation;
		this.possibleNumbers = possibleNumbers;
	}
	
	/**
	 * Returns the sequence of integers
	 * @return An int array of the integers
	 */
	public int[] getSequence(){
		return sequence;
	}
	
	/**
	 * Gets the fitness of the sequence
	 * If the sum is over the goal the fitness 
	 * is the (goal - sum) so that solutions that 
	 * will recieve a score of 0 can still be compared 
	 * for fitness, otherwise the sum equals the fitness.
	 * Then the algorithm checks if it is a valid
	 * sequence, if it isn't the fitness score is
	 * deducted 100 points.
	 * @return An integer representing the fitness
	 */
	public int getFitness(){
		// add up all the ints in the array
		int sum = 0;
		for(int i = 0; i < sequence.length; i++){
			sum += sequence[i];
		}
		
		// if sum is over goal then return goal - sum
		// otherwise return the sum
		int fitness = 0;
		if(sum > goal){
			fitness += goal - sum;
		}
		else {
			fitness += sum;
		}
		
		if(!isSequenceValid()){
			fitness -= goal;
		}
		
		return fitness;
	}
	
	/**
	 * Returns whether or not the sequence is valid given the possible numbers it can contain.
	 * @returnA boolean stating if the sequence is valid.
	 */
	public boolean isSequenceValid(){
		
		/* if sequence is bigger than possibleNumbers 
		 * then it is not possible that sequence is a valid */
		if(sequence.length > possibleNumbers.length){
			return false;
		}
		
		// use array of 0 and of length possibleNumbers 
		// to track if a number has already been used
		int[] dirtyBit = new int[possibleNumbers.length];
		
		// go through the sequence
		for(int i = 0; i < sequence.length; i++){
			boolean found = false;
			for(int j = 0; j < possibleNumbers.length; j++){
				/* check to see if the sequence number is in the possibleNumbers list
				 * if it hasn't been found already, is in the possibleNumbers list, 
				 * and that possibleNumber hasn't been already used then set found to 
				 * true and set the possibleNumber's dirtyBit to 1 */
				if(!found &&
					possibleNumbers[j] == sequence[i] &&
					dirtyBit[j] != 1){
					found = true;
					dirtyBit[j] = 1;
				}
			}
			
			// if not found in possibleNumbers then the sequence is not valid
			if(!found){
				return false;
			}
		}
		
		// if it got to here then it found all the sequence in the possibleNumbers
		return true;
	}

	/* (non-Javadoc)
	 * 
	 * Compares two number sequences together based on their fitness functions.
	 * If this has a higher fitness function then it returns 1,
	 * else if this has a lower fitness function then it returns -1,
	 * else it returns 0
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(NumberSequence ns1) {
		// get the fitness functions of each sequence
		int fitness0 = this.getFitness();
		int fitness1 = ns1.getFitness();
		
		// compare and return the appropriate value
		if(fitness0 > fitness1)
			return 1;
		else if(fitness0 < fitness1){
			return -1;
		}
		// if fitnesses are equal then the oldest the sequence wins
		else if(fitness0 == fitness1){
			if(this.generation < ns1.generation){
				return 1;
			}
			else if(this.generation > ns1.generation){
				return -1;
			} 
		}
		
		return 0;
	}
	
	/**
	 * Add a possible number to the end of the sequence
	 * @param possibleNumber The number to add on to the sequence
	 */
	public void addToSequence(int possibleNumber){
		int[] temp = new int[sequence.length + 1];
		for(int i = 0; i < sequence.length; i++){
			temp[i] = sequence[i];
		}
		
		temp[sequence.length] = possibleNumber;
		sequence = temp;
	}
	
	/**
	 * Change a number in the sequence to the given number
	 * @param sequenceIndex The index to change.
	 * @param possibleNumber The number to change to
	 */
	public void changeInSequence(int sequenceIndex, int possibleNumber){
		sequence[sequenceIndex] = possibleNumber;
	}
	
	/**
	 * Returns the generation that the sequence was made in
	 * @return
	 */
	public int getGeneration(){
		return this.generation;
	}
}
