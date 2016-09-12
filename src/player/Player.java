package player;

import java.util.List;
import item.Item;

public class Player {

	Character playerIcon = '@';
	Character replacedChar;
	int playerXcoord;
	int playerYcoord;
	List<Item> itemList;
	
	
	public void setPlayerIcon(Character playerIconIn){this.playerIcon = playerIconIn ;}
	public void setReplacedChar(Character replacedCharIn){this.replacedChar = replacedCharIn ;}
	public void setPlayerXCoord(int playerXcoordIn){this.playerXcoord = playerXcoordIn;}
	public void setPlayerYCoord(int playerYcoordIn){this.playerYcoord = playerYcoordIn;}
	public void setItemList(List<Item> itemListIn){this.itemList = itemListIn;}
	
	public Character gerPlayerIcon(){return this.playerIcon;}
	public Character getReplacedChar(){return this.replacedChar;}
	public int getPlayerXCoord(){return this.playerXcoord;}
	public int getPlayerYCoord(){return this.playerYcoord;}
	public List<Item> getItemList(){return this.itemList;}
	
	public void addItemToItemList(Item itemIn){this.itemList.add(itemIn);}
	
}
