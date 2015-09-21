
public class Bins implements Comparable<Bins> {

	int[] binOne;
	int[] binTwo;
	int[] binThree;
	
	public Bins(){
		binOne = new int[0];
		binTwo = new int[0];
		binThree = new int[0];
	}
	
	public Bins(int[] binOne, int[] binTwo, int[] binThree){
		this.binOne = binOne;
		this.binTwo = binTwo;
		this.binThree = binThree;
	}
	
	public void setBins(int[] binOne, int[] binTwo, int[] binThree){
		this.binOne = binOne;
		this.binTwo = binTwo;
		this.binThree = binThree;
	}
	
	public void setBinOne(int[] binOne){
		this.binOne = binOne;
	}
	
	public void setBinTwo(int[] binTwo){
		this.binTwo = binTwo;
	}
	
	public void setBinThree(int[] binThree){
		this.binThree = binThree;
	}
	
	public int getFitness(){
		return 0;
	}
	
	public boolean areBinsValid(){
		return false;
	}
	
	public int[][] getBins(){
		return new int[][]{binOne, binTwo, binThree};
	}
	
	public int[] getBinOne(){
		return binOne;
	}
	
	public int[] getBinTwo(){
		return binTwo;
	}
	
	public int[] getBinThree(){
		return binThree;
	}
	
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
