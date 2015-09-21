package PuzzleThreeFiles;

public class Building implements Comparable<Building>{

	/**
	 * List of pieces used in the building
	 */
	BuildingPiece[] list;
	
	/**
	 * Sets the pieces list of the object
	 * @param list
	 */
	Building(BuildingPiece[] list){
		this.list = list;
	}
	
	/**
	 * Returns the list of pieces in the building
	 * @return
	 */
	public BuildingPiece[] getList(){
		return list;
	}
	
	/**
	 * Returns the sum of all the consts of the pieces in the building
	 * @return
	 */
	public int getCost(){
		int sum = 0;
		for(int i = 0; i < list.length; i++){
			sum += list[i].getCost();
		}
		
		return sum;
	}
	
	/**
	 * Returns whether the building is valid given: the
	 * strength of the pieces, the width of the pieces,
	 * the order of the pieces, and the possible pieces 
	 * that it can use.
	 * @return
	 */
	public boolean isValidBuilding(BuildingPiece[] possiblePieces){
		return false;
	}
	
	/**
	 * Returns the fitness of the building based on its pieces
	 * @return
	 */
	public int getFitness(){
		return 0;
	}

	/* (non-Javadoc)
	 * Compares the building objects based on their fitness functions
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Building building1) {
		// get the fitness functions of each building
		int fitness0 = this.getFitness();
		int fitness1 = building1.getFitness();
		
		// compare and return the appropriate value
		if(fitness0 > fitness1)
			return 1;
		else if(fitness0 < fitness1){
			return -1;
		}
		
		return 0;
	}
}
