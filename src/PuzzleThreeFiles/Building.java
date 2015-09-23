package PuzzleThreeFiles;

public class Building implements Comparable<Building>{

	/**
	 * List of pieces used in the building
	 */
	BuildingPiece[] list;
	
	/**
	 * The generation of the building sequence.
	 */
	private int generation;
	
	/**
	 * An array of the possible pieces that 
	 * are able to be used in a building
	 */
	private BuildingPiece[] possiblePieces = null;
	
	/**
	 * Sets the pieces list of the object
	 * @param list
	 */
	public Building(BuildingPiece[] list, int generation, BuildingPiece[] possiblePieces){
		this.list = list;
		this.generation = generation;
		this.possiblePieces = possiblePieces;
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
	 * possible pieces that it can use.
	 * @return
	 */
	public boolean isValidBuilding(){
		/* if list is bigger than possiblePieces 
		 * then it is not possible that list is a valid */
		if(list.length > possiblePieces.length){
			return false;
		}
		
		// use array of 0 and of length possiblePieces 
		// to track if a number has already been used
		int[] dirtyBit = new int[possiblePieces.length];
		
		// go through the list
		for(int i = 0; i < list.length; i++){
			boolean found = false;
			for(int j = 0; j < possiblePieces.length; j++){
				/* check to see if the list buildingPiece is in the possiblePiece list
				 * if it hasn't been found already, is in the possiblePieces list, 
				 * and that possiblePiece hasn't been already used then set found to 
				 * true and set the possiblePieces's dirtyBit to 1 */
				if(!found &&
					possiblePieces[j] == list[i] &&
					dirtyBit[j] != 1){
					found = true;
					dirtyBit[j] = 1;
				}
			}
			
			// if not found in possiblePiece then the list is not valid
			if(!found){
				return false;
			}
		}
		
		// if it got to here then it found all the list in the possiblePieces
		return true;
	}
	
	/**
	 * Returns the fitness of the building based on its pieces
	 * @return
	 */
	public int getFitness(){
		
		int fitness = 0;
		
		/* look for number of doors and lookouts
		 * 1 of each is more favorable than multiple 
		 * 1 of each with the doors on the bottom and 
		 * the lookout on the top is even more favorable */
		
		/* check minimum width up the structure and deduct
		 * point for any widths that are bigger than the 
		 * current minimum width */
		int minWidth = list[0].getWidth();
		
		/* keep track of all the strengths of each piece and
		 * deduct form the strength as you go up to see if structure
		 * holds stable */
		int[] strength = new int[list.length];
		
		// run through the list checking all of the above
		for(int i = 0; i < list.length; i++){
			if(list[i].getType().equals(BuildingPiece.TYPE_DOOR)){
				if(i == 0){
					// add points if door is on bottom floor
					fitness += 50;
				}
				else{
					// subtract points for every door not on the bottom floor
					fitness -= 50;
				}
			}
			else if(list[i].getType().equals(BuildingPiece.TYPE_LOOKOUT)){
				if(i == list.length - 1){
					// add points if door is on top floor
					fitness += 50;
				}
				else{
					// subtract points for every door not on the top floor
					fitness -= 50;
				}
			}
			
			// for every width above that is bigger than a smaller width underneath it subtract points
			if(list[i].getWidth() > minWidth){
				fitness -= 10;
			}
			// else update minimum width
			else{
				minWidth = list[i].getWidth();
			}
			
			// keep track of strength and deduct points accordingly
			for(int j = i; j >= 0; j--){
				// if strength not in list yet then add to strength list
				if(j == i)
					strength[j] = list[i].getStrength();
				// else deduct point from strength
				else
					strength[j]--;
				
				// if the strength at that spot is less than 0 then the piece cannot hold up the structure
				if(strength[j] < 0)
					fitness -= 1;
				// if strength is still positive then add points for structural integrity
				else{
					fitness += 1;
				}
				
			}
		}
		
		// check if building is valid and its score
		if(!isValidBuilding()){
			fitness -= 100;
		}
		if(getScore() == 0){
			fitness -= 100;
		}
		else{
			// higher scoring buildings have a higher fitness
			fitness += 5*getScore();
		}

		return fitness;
	}
	
	/**
	 * Returns the score of the tower
	 * If any of the properties of a tower
	 * are violated then the tower gets a 
	 * score of 0
	 * else the tower gets a score equal to
	 * 10 plus the height squared minus the 
	 * cost of the tower.
	 * @return Returns the integer value of the score of the tower
	 */
	public int getScore(){
		
		// check for a door on the bottom and a lookout on the top
		boolean doorOnBottom = list[0].getType().equals(BuildingPiece.TYPE_DOOR);
		boolean lookoutOnTop = list[list.length - 1].getType().equals(BuildingPiece.TYPE_LOOKOUT);
		
		if(!(doorOnBottom && lookoutOnTop)){
			return 0;
		}
		
		// keep track of minimum width and strength of each piece
		int minWidth = list[0].getWidth();
		int[] strength = new int[list.length];
		
		// look through the piece list to check for an invalid building attributes
		for(int i = 0; i < list.length; i++){
			// check for doors and lookouts in invalid locations
			if((i != 0 && list[i].getType().equals(BuildingPiece.TYPE_DOOR)) ||
			   (i != list.length - 1 && list[i].getType().equals(BuildingPiece.TYPE_LOOKOUT))){
				return 0;
			}
			// if a piece is bigger than the minimum width it is invalid
			if(minWidth < list[i].getWidth()){
				return 0;
			}
			// else update minimum width
			else{
				minWidth = list[i].getWidth();
			}
			
			//check strength of pieces
			for(int j = i; j >= 0; j--){
				// if strength not in list yet then add to strength list
				if(j == i)
					strength[j] = list[i].getStrength();
				// else deduct point from strength
				else
					strength[j]--;
				
				// if the strength at that spot is less than 0 then the piece cannot hold up the structure
				if(strength[j] < 0){
					return 0;
				}
			}
		}
	
		// if algorithm has gotten to this point then building is valid
		// return score of building based on height and cost
		return (int) (10 + Math.pow(list.length, 2) - getCost());
	}
	
	/**
	 * Add a building piece to the list in a certain location
	 * @param index	The index to add the piece at
	 * @param piece	The piece to add to the list
	 */
	public void addToList(int index, BuildingPiece piece){
		// create a new list 1 more element bigger than the current list
		BuildingPiece[] newList = new BuildingPiece[list.length + 1];
		
		// instantiate the new list
		for(int i = 0; i < newList.length; i++){
			if(i < index){
				newList[i] = list[i];
			}
			else if(i > index){
				newList[i] = list[i-1];
			}
			else{
				newList[i] = piece;
			}
		}
		
		// update list to new list
		list = newList;
	}
	
	/**
	 * Change a piece in the list at a certain location
	 * @param index The index of the piece to change
	 * @param piece The piece to change to
	 */
	public void changeInList(int index, BuildingPiece piece){
		list[index] = piece;
	}
	
	/**
	 * Return the generation that the building was made in
	 * @return
	 */
	public int getGeneration(){
		return generation;
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
		else{
			if(this.generation < building1.generation){
				return 1;
			}
			else if(this.generation > building1.generation){
				return -1;
			}
		}
		
		return 0;
	}
}
