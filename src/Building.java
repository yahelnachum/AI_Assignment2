
public class Building implements Comparable<Building>{

	BuildingPiece[] list;
	
	Building(BuildingPiece[] list){
		this.list = list;
	}
	
	public BuildingPiece[] getList(){
		return list;
	}
	
	public int getCost(){
		int sum = 0;
		for(int i = 0; i < list.length; i++){
			sum += list[i].getCost();
		}
		
		return sum;
	}
	
	public boolean isValidBuilding(){
		return false;
	}
	
	public int getFitness(){
		return 0;
	}

	@Override
	public int compareTo(Building building1) {
		// get the fitness functions of each sequence
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
