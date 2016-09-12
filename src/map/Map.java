package map;

import java.util.List;

public class Map {

	List<List<Character>> characterList;
	List<InteractableObject> interactableList;
	String mapString;
	String hitBoxCharacterList = "#\\/.|-";
	
	public List<List<Character>> getCharacterList(){return this.characterList;}
	public String getMapString(){return this.mapString;}
	public String getHitBoxCharacterList(){return this.hitBoxCharacterList;}
	public List<InteractableObject> getInteractableObjectList(){return this.interactableList;}
	
	public void setCharacterList(List<List<Character>> characterListIn){this.characterList = characterListIn;}
	public void setMapString(String mapStringIn){this.mapString = mapStringIn;}
	public void setInteractableObjectList(List<InteractableObject> interactableObjectListIn){this.interactableList = interactableObjectListIn;}
	
	public void addToInteractableObjectList(InteractableObject object){this.interactableList.add(object);}
	
}
