
public class NumberSequence {

	/**
	 * The integers that are part of the sequence.
	 */
	private int[] sequence;
	
	/**
	 * The number that the sequency is trying to add up to 
	 */
	private int goal;
	
	/**
	 * Create an object with the given array as the sequence of numbers trying to add up to the goal.
	 * @param sequence The array of numbers trying to add up to get the goal or second closest that doesn't go over the goal.
	 * @param goal The goal that the array is supposed to sum to without going over.
	 */
	NumberSequence(int[] sequence, int goal){
		this.sequence = sequence;
		this.goal = goal;
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
	 * If the sum is over the goal the fitness is 0
	 * otherwise the sum equals the fitness
	 * @return An integer representing the fitness
	 */
	public int getFitness(){
		// add up all the ints in the array
		int sum = 0;
		for(int i = 0; i < sequence.length; i++){
			sum += sequence[i];
		}
		
		// if sum is over goal then return 0
		// otherwise return the sum
		if(sum > goal)
			return 0;
		else
			return sum;
	}
	
	public static boolean isSequenceValid(int[] sequence, int[] possibleNumbers){
		
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
}
