package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JTextArea;

import item.Item;
import map.InteractableObject;
import map.Map;
import player.NPC;
import player.Player;
import display.DisplayStrings;

public class Setups {
	
	public static void initialSetup(Map map,JTextArea board,Player player)
	{
			
		player.setPlayerXCoord(3);//set base x
		player.setPlayerYCoord(3);//sent base y
		
		//====building a test item to put into the users inventory
		Item log = new Item();
		log.setName("Log");
		log.setDescription("Its a log.");
		log.setPicture("  _\n / /\n/ /");
		List<Item> playerItemList = new ArrayList<Item>(); //make a new item list for the player
		playerItemList.add(log);  //add the log to the item list
		player.setItemList(playerItemList); //set that item list to the players

		//=====building a test item for interactable objects
		InteractableObject fishingRod = new InteractableObject(); //set up a new object
		fishingRod.setName("Worn Rod"); //set the name
		Item rod = new Item(); //make a new item to test interactable
		rod.setName("Worn Rod"); //name it
		rod.setDescription("An old fishing rod"); //describe it
		rod.setPicture(" /\\\n.  \\\n    \\");	// draw it
		fishingRod.setItem(rod);//add the item to the interactable object
		List<Integer> rodCoords = new ArrayList<Integer>(); //make an arraylist for the coords
		rodCoords.add(17); //add the x coord
		rodCoords.add(16); //add y coord
		fishingRod.setXYCoordinateList(rodCoords); // set the coords
		
		List<InteractableObject> mapObjectList = new ArrayList<InteractableObject>(); // make new interactable list
		map.setInteractableObjectList(mapObjectList); // set the list to the map
		map.addToInteractableObjectList(fishingRod); // add the fishing rod to the object list
		
		player.setReplacedChar(map.getCharacterList().get(player.getPlayerYCoord()).get(player.getPlayerXCoord()));//get the first chracter the player will be overwritting
		map.getCharacterList().get(player.getPlayerYCoord()).set(player.getPlayerXCoord(), player.getPlayerIcon());//set the player icon on the map
		map.setMapString(DisplayStrings.convertMapToString(map.getCharacterList()));//get the map
		
		board.setText(map.getMapString());//draw the map
	}//end initial setup
	
	public static List<NPC> NPCSetup(Map map, JTextArea board)
	{
		List<NPC> npcList = new ArrayList();
		
		for(int i = 0; i < 5; i++)
		{
			NPC enemy = new NPC();
			enemy.setHealth(20);
			enemy.setIcon('Q');
			Random rand = new Random();
			map.getCharacterList().size();
			Character mapChar = '#';
			int y = 0;
			int x = 0;
			//assign a random blank space on the map as an enemy spawn point
			while(mapChar != ' ')
			{
				y = rand.nextInt(map.getCharacterList().size());
				x = rand.nextInt(map.getCharacterList().get(y).size());
				mapChar = map.getCharacterList().get(y).get(x);
			}//end while
			
			enemy.setXCoord(x);
			enemy.setYCoord(y);
			enemy.setReplacedChar(map.getCharacterList().get(enemy.getYCoord()).get(enemy.getXCoord()));
			map.getCharacterList().get(enemy.getYCoord()).set(enemy.getXCoord(), enemy.getIcon());//set the enemy icon on the map
			enemy.setNPCType("enemy");
			npcList.add(enemy);
			
		}//end for
		
		map.setMapString(DisplayStrings.convertMapToString(map.getCharacterList()));//get the map
		board.setText(map.getMapString());//draw the map
		
		return npcList;
	}
	
}
