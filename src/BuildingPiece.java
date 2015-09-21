
public class BuildingPiece {

	public final String TYPE_DOOR = "TYPE:DOOR";
	public final String TYPE_WALL = "TYPE:WALL";
	public final String TYPE_LOOKOUT = "TYPE:LOOKOUT";
	
	private String type;
	private int width;
	private int strength;
	private int cost;
	
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
	
	public String getType(){
		return type;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getStrength(){
		return strength;
	}
	
	public int getCost(){
		return cost;
	}
}
