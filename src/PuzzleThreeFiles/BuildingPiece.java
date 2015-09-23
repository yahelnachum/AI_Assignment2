package PuzzleThreeFiles;

public class BuildingPiece {

	/**
	 * Final strings for the possible types of pieces
	 */
	public static final String TYPE_DOOR = "Door";
	public static final String TYPE_WALL = "Wall";
	public static final String TYPE_LOOKOUT = "Lookout";
	
	/**
	 * The attributes of each piece of the building
	 */
	private String type;
	private int width;
	private int strength;
	private int cost;
	
	/**
	 * Create a building piece, and set all of its attributes with the given information
	 * @param type The type of piece
	 * @param width The width of the piece
	 * @param strength The strength of the piece
	 * @param cost The cost of the piece
	 */
	public BuildingPiece(String type, int width, int strength, int cost){
		// check for invalid types
		if(!(type.equals(TYPE_DOOR) || type.equals(TYPE_WALL) || type.equals(TYPE_LOOKOUT))){
				System.out.println("BuildingPiece:BuildingPiece(): type given is not supported!");
				return;
		}
		
		this.type = type;
		this.width = width;
		this.strength = strength;
		this.cost = cost;
	}
	
	/**
	 * Returns the type of the building piece
	 * @return
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * Returns the width of the building piece
	 * @return
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * Returns the strength of the piece
	 * @return
	 */
	public int getStrength(){
		return strength;
	}
	
	/**
	 * Returns the cost of the piece
	 * @return
	 */
	public int getCost(){
		return cost;
	}
	
	/* (non-Javadoc)
	 * Return a string representation of the piece for printing
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return type + ", " + width + ", " + strength + ", " + cost;
	}
}
