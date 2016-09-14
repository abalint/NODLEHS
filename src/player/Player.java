package player;

import java.util.ArrayList;
import java.util.List;
import item.Item;

public class Player {

	Character playerIcon = '@';
	Character replacedChar;
	int playerXcoord;
	int playerYcoord;
	List<Item> itemList;
	boolean inInventory;
	List<String> inputQueue;
	
	
	public void setPlayerIcon(Character playerIconIn){this.playerIcon = playerIconIn ;}
	public void setReplacedChar(Character replacedCharIn){this.replacedChar = replacedCharIn ;}
	public void setPlayerXCoord(int playerXcoordIn){this.playerXcoord = playerXcoordIn;}
	public void setPlayerYCoord(int playerYcoordIn){this.playerYcoord = playerYcoordIn;}
	public void setItemList(List<Item> itemListIn){this.itemList = itemListIn;}
	public void setInInventory(boolean inInventoryIn){this.inInventory = inInventoryIn;}
	public void setInputQueue(List<String> inputQueueIn){this.inputQueue = inputQueueIn;}
	
	public Character gerPlayerIcon(){return this.playerIcon;}
	public Character getReplacedChar(){return this.replacedChar;}
	public int getPlayerXCoord(){return this.playerXcoord;}
	public int getPlayerYCoord(){return this.playerYcoord;}
	public List<Item> getItemList(){return this.itemList;}
	public boolean getInInventory(){return this.inInventory;}
	public List<String> getinputQueue(){return this.inputQueue;}
	
	public void addItemToItemList(Item itemIn){this.itemList.add(itemIn);}
	public void appendToInputQueue(String appendString){this.inputQueue.add(appendString);}
	public void clearInputQueue(){this.inputQueue.clear();}
	
	public Player()
	{
		this.inputQueue = new ArrayList<String>();
	}
}
