package main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import map.InteractableObject;
import map.Map;
import player.Player;
import player.Actions;
import display.DisplayStrings;
import display.Console;

public class QueueExecuters {
	
public static void executeBoardQueue(Map map, Player player, JTextArea board, JTextArea console)
{
	int loopCounter = 0;
	for(String input : player.getBoardInputQueue())
	{
		if(loopCounter == 5)
			break;
		if(input == "W")
		{
        	if(player.getInInventory())
        	{
        		//do some stuff
        	}
        	else
        	board.setText(Actions.moveMapUp(map, player).getMapString());//moves palyer up one tile on the map
		}//end W case
		if(input == "S")
		{
        	if(player.getInInventory())
        	{
        		//do some stuff
        	}
        	else
        		board.setText(Actions.moveMapDown(map, player).getMapString()); //moves player down one tile on the map
		}//end S case
		if(input == "A")
		{
        	if(player.getInInventory())
        	{
        		//do some stuff
        	}
        	else	         
        		board.setText(Actions.moveMapToLeft(map, player).getMapString());
		}//end A case
		if(input == "D")
		{
        	if(player.getInInventory())
        	{
        		//do some stuff
        	}
        	else
        		board.setText(Actions.moveMapToRight(map, player).getMapString());
		}//end D case
		if(input == "I")
		{
			if(player.getBulletList().size() == 0){
	        	if(!player.getInInventory()){
	        		board.setText(DisplayStrings.inventoryString(player));
	        		player.setInInventory(true);
	        	}
	        	else{
	        		board.setText(map.getMapString());
	        		player.setInInventory(false);
	        	}
			}
		}//end I case
		if(input == "SPACE")
		{
        	if(player.getInInventory())
        	{
        		//do some stuff
        	}
        	else{
        		boolean onItem = false;
        		List<InteractableObject> iObject = new ArrayList();
        		for(InteractableObject object : map.getInteractableObjectList())
        		{
        			if(player.getPlayerXCoord() == object.getXYCoordinateList().get(0) && player.getPlayerYCoord() == object.getXYCoordinateList().get(1))
        			{
        				onItem = true;
        			}
        		}
        		//if you are standing on an item, pick it up
        		if(onItem)
        			Actions.interact(map, player, console);
        		//else shoot your gun
        		else{
        			Actions.fire(map, player, console);
        			
        		}
        			
        		
        	}//end else
        }//end if
		loopCounter++;
		
	}//end for loop
	player.clearBoardInputQueue();
}//end executeQueue

public static void executeConsoleQueue(Map map, Player player, JTextArea board, JTextArea console, long startLoopTime, long updateLoopTime, long tickCheck, long tickCount, String runTime)
{
	
	for(String input : player.getConsoleInputQueue())
	{
		if(input == "I")
		{
        	if(player.getInConsoleInfo())
        	{
        		console.setText(player.getConvertedConsoleOutputToString());
        		player.setInConsoleInfo(false);
        	}
        	else
        		Console.getStatsInConsole(console, player, startLoopTime, updateLoopTime, tickCheck, tickCount,runTime);
        	
		}//end W case
	}//end for
	player.clearConsoleInputQueue();
}//end method

public static void executeTextBarQueue(Map map, Player player, JTextArea board, JTextArea console)
{
	
}

}
