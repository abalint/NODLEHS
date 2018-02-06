package display;

import java.util.List;

import item.Item;
import player.Player;

public class DisplayStrings {

	public static String convertMapToString(List<List<Character>> map)
	{
		String mapString = new String();
		
		
		for(List<Character> mapLine : map)
		{
			for(Character mapChar : mapLine)
			{
				mapString += mapChar;
			}
			mapString += "\n";
		}
		
		return mapString;
	}
	

	public static String inventoryString(Player player)
	{
		String inventoryString = "INVENTORY: \n";
		for(Item item : player.getItemList())
		{
			inventoryString += item.getName()+"\n"+item.getPicture()+"\n"+item.getDescription()+"\n\n";
		}
		
		return inventoryString;
	}
	
}
