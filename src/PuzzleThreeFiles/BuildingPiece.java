package PuzzleThreeFiles;

public class BuildingPiece {

	/**
	 * Final strings for the possible types of pieces
	 */
	public final String TYPE_DOOR = "TYPE:DOOR";
	public final String TYPE_WALL = "TYPE:WALL";
	public final String TYPE_LOOKOUT = "TYPE:LOOKOUT";
	
	/**
	 * The attributes of each piece of the building
	 */
	private String type;
	private int width;
	private int strength;
	private int cost;
	
	/**
	 * Create a building piece, and set all of its attributes with the given information
	 * @param type
	 * @param width
	 * @param strength
	 * @param cost
	 */
	public BuildingPiece(String type, int width, int strength, int cost){
		if(type != TYPE_DOOR || type != TYPE_WALL || type != TYPE_LOOKOUT){
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
}
