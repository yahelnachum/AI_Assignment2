package PuzzleTwoFiles;

public class Bins implements Comparable<Bins> {

	/**
	 * The bins used for storage of numbers
	 * Bins must have an equal amount of numbers in them
	 */
	double[] binOne;
	double[] binTwo;
	double[] binThree;
	
	/**
	 * Initializes all the bins to be empty
	 */
	public Bins(){
		binOne = new double[0];
		binTwo = new double[0];
		binThree = new double[0];
	}
	
	/**
	 * Initializes the bins with the given bins
	 * @param binOne
	 * @param binTwo
	 * @param binThree
	 */
	public Bins(double[] binOne, double[] binTwo, double[] binThree){
		this.binOne = binOne;
		this.binTwo = binTwo;
		this.binThree = binThree;
	}
	
	/**
	 * Sets the bins to the given bins
	 * @param binOne
	 * @param binTwo
	 * @param binThree
	 */
	public void setBins(double[] binOne, double[] binTwo, double[] binThree){
		this.binOne = binOne;
		this.binTwo = binTwo;
		this.binThree = binThree;
	}
	
	/**
	 * Sets the first bin
	 * @param binOne
	 */
	public void setBinOne(double[] binOne){
		this.binOne = binOne;
	}
	
	/**
	 * Sets the second bin
	 * @param binTwo
	 */
	public void setBinTwo(double[] binTwo){
		this.binTwo = binTwo;
	}
	
	/**
	 * Sets the third bin
	 * @param binThree
	 */
	public void setBinThree(double[] binThree){
		this.binThree = binThree;
	}
	
	/**
	 * Returns the fitness of this bins object
	 * @return
	 */
	public int getFitness(){
		return 0;
	}
	
	/**
	 * Returns whether the bins in this object are valid
	 * @return
	 */
	public boolean areBinsValid(){
		return false;
	}
	
	/**
	 * Returns the bins in an array of 3 elements.
	 * @return
	 */
	public double[][] getBins(){
		return new double[][]{binOne, binTwo, binThree};
	}
	
	/**
	 * Returns the first bin
	 * @return
	 */
	public double[] getBinOne(){
		return binOne;
	}
	
	/**
	 * Returns the second bin
	 * @return
	 */
	public double[] getBinTwo(){
		return binTwo;
	}
	
	/**
	 * Returns the third bin
	 * @return
	 */
	public double[] getBinThree(){
		return binThree;
	}
	
	/* (non-Javadoc)
	 * Compares the bins objects based on their fitness
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Bins bin1) {
		// get the fitness functions of each sequence
		int fitness0 = this.getFitness();
		int fitness1 = bin1.getFitness();
		
		// compare and return the appropriate value
		if(fitness0 > fitness1)
			return 1;
		else if(fitness0 < fitness1){
			return -1;
		}
		
		return 0;
	}
}
