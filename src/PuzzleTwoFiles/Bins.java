package PuzzleTwoFiles;

public class Bins implements Comparable<Bins> {
	private int generation;
	private double[] possibleNumbers = null;
	/**
	 * The bins used for storage of numbers
	 * Bins must have an equal amount of numbers in them
	 */
	private double[] binOne;
	private double[] binTwo;
	private double[] binThree;

	Bins(double[] binOne, double[] binTwo, double[] binThree, int generation, double[] possibleNumbers){
		this.binOne = binOne;
		this.binTwo = binTwo;
		this.binThree = binThree;
		this.generation = generation;
		this.possibleNumbers = possibleNumbers;
	}
	
	/**
	 * Initializes all the bins to be empty
	 */
	public void addToBin1(double possibleNumber){
		double[] temp = new double[binOne.length + 1];
		for(int i = 0; i < binOne.length; i++){
			temp[i] = binOne[i];
		}
		
		temp[binOne.length] = possibleNumber;
		binOne = temp;
	}
	
	public void addToBin2(double possibleNumber){
		double[] temp = new double[binTwo.length + 1];
		for(int i = 0; i < binTwo.length; i++){
			temp[i] = binTwo[i];
		}
		
		temp[binTwo.length] = possibleNumber;
		binTwo = temp;
	}
	
	public void addToBin3(double possibleNumber){
		double[] temp = new double[binThree.length + 1];
		for(int i = 0; i < binThree.length; i++){
			temp[i] = binThree[i];
		}
		
		temp[binThree.length] = possibleNumber;
		binThree = temp;
	}
	
	
	public Bins(){
		binOne = new double[10];
		binTwo = new double[10];
		binThree = new double[10];
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
	public double getFitness(){
		double[] bin1 = this.getBinOne();
		double[] bin2 = this.getBinTwo();
		double score = this.getScore(bin1, bin2);
		if (score >0){
			return score;
		}
		else {
			return 1/Math.abs(score);
		}
		
	}
	
	public double getScore(double[] bin1, double[] bin2){
		double productbin1 = 1;
		double sumbin2 = 0;
		for (int i = 0; i < bin1.length; i++){
			productbin1 *= bin1[i];
		}
		for (int i = 0; i < bin2.length; i++){
			sumbin2 += bin2[i];
		}
		double result = (productbin1 + sumbin2)/2;
		return result;
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
		double fitness0 = this.getFitness();
		double fitness1 = bin1.getFitness();
		
		// compare and return the appropriate value
		if(fitness0 > fitness1)
			return 1;
		else if(fitness0 < fitness1){
			return -1;
		}
		
		return 0;
		
	}
	public int getGeneration(){
		return this.generation;
	}
}


