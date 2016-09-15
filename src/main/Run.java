package main;


import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextArea;
import javax.swing.UIManager;

import display.LaunchWindow;
import item.Item;
import map.InteractableObject;
import map.Map;
import player.Player;


//window builder software for neon install url:
//http://download.eclipse.org/windowbuilder/WB/integration/4.6/
//
//windowbuilder lectures
//https://www.youtube.com/watch?v=KdTsY3G_To0

public class Run {

	public static void main(String[] args)
	{		
		LaunchWindow frame;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		frame = new LaunchWindow();
		frame.setVisible(true);
		
		
		JTextArea board = frame.getBoard();		
		JTextArea console = frame.getConsole();
		JTextArea textBar = frame.getInputTextArea();
		Map map = new Map();
		Player player = new Player();
		initialSetup(map, board, player);	
	
		
		long gameLaunchTime = Calendar.getInstance().getTimeInMillis();
		long startLoopTime = Calendar.getInstance().getTimeInMillis();
		long updateLoopTime = Calendar.getInstance().getTimeInMillis();
		boolean firstLoop = true;
		boardListener(board, player, map);
		consoleListener(console, player, map);
		int tickCount = 0;
		
		player.addToConsoleOutput("welcome!");
		console.setText(player.getConvertedConsoleOutputToString());

		while (true) { // keep running
	    	startLoopTime = Calendar.getInstance().getTimeInMillis();
	        long tickCheck = startLoopTime - updateLoopTime;
	        long runTime = startLoopTime - gameLaunchTime;
	        String runTimeFormated = String.format("%02d:%02d:%02d", 
	        		TimeUnit.MILLISECONDS.toHours(runTime),
	        		TimeUnit.MILLISECONDS.toMinutes(runTime) -  
	        		TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(runTime)), // The change is in this line
	        		TimeUnit.MILLISECONDS.toSeconds(runTime) - 
	        		TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(runTime)));
	        if(firstLoop || tickCheck > 10)
	        {
	        	tickCount++;
	        	try{
	        	executeBoardQueue(map, player, board, console);
	        	}catch(Exception e){
	        		//console.setText(e.toString());
	        		}
	        	executeConsoleQueue(map, player, board, console, startLoopTime, updateLoopTime, tickCheck, tickCount, runTimeFormated);
	        	if(player.getInConsoleInfo())
	        		getStatsInConsole(console, player, startLoopTime, updateLoopTime, tickCheck, tickCount, runTimeFormated);
	        	firstLoop = false;
	        	updateLoopTime = Calendar.getInstance().getTimeInMillis();
	        }
	    }
	}
	
	
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
		rod.setName("Worn Rod"); //anem it
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
		map.getCharacterList().get(player.getPlayerYCoord()).set(player.getPlayerXCoord(), player.gerPlayerIcon());//set the player icon on the map
		map.setMapString(convertMapToString(map.getCharacterList()));//get the map
		
		board.setText(map.getMapString());//draw the map
	}
	
	
	public static void boardListener (JTextArea board, Player player, Map map)
	{
		
		board.addKeyListener(new KeyListener(){
		    @Override
		    public void keyPressed(KeyEvent e){
		    	switch (e.getKeyCode()){
		        case KeyEvent.VK_W:
		        	player.appendToBoardInputQueue("W");
		        	break;
		        case KeyEvent.VK_S:
		        	player.appendToBoardInputQueue("S");
		        	break;
		        case KeyEvent.VK_A:
		        	player.appendToBoardInputQueue("A");
		        	break;
		        case KeyEvent.VK_D:
		        	player.appendToBoardInputQueue("D");
		        	break;
		        case KeyEvent.VK_I:      
		        	player.appendToBoardInputQueue("I");
		        	break;	
		        case KeyEvent.VK_SPACE:
		        	player.appendToBoardInputQueue("SPACE");
		        	break;
		        }
		    }
		    @Override
		    public void keyTyped(KeyEvent e) {
		    }
		    @Override
		    public void keyReleased(KeyEvent e) {
		    }
		});
	}
	
	public static void consoleListener (JTextArea console, Player player, Map map)
	{
		console.addKeyListener(new KeyListener(){
		    @Override
		    public void keyPressed(KeyEvent e){
		    	switch (e.getKeyCode()){
		        case KeyEvent.VK_I:
		        	player.appendToConsoleInputQueue("I");

		        }
		    }
		    @Override
		    public void keyTyped(KeyEvent e) {
		    }
		    @Override
		    public void keyReleased(KeyEvent e) {
		    }
		});
	}
			
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
	        	board.setText(moveMapUp(map, player).getMapString());//moves palyer up one tile on the map
			}//end W case
			if(input == "S")
			{
	        	if(player.getInInventory())
	        	{
	        		//do some stuff
	        	}
	        	else
	        		board.setText(moveMapDown(map, player).getMapString()); //moves player down one tile on the map
			}//end S case
			if(input == "A")
			{
	        	if(player.getInInventory())
	        	{
	        		//do some stuff
	        	}
	        	else	         
	        		board.setText(moveMapToLeft(map, player).getMapString());
			}//end A case
			if(input == "D")
			{
	        	if(player.getInInventory())
	        	{
	        		//do some stuff
	        	}
	        	else
	        		board.setText(moveMapToRight(map, player).getMapString());
			}//end D case
			if(input == "I")
			{
	        	if(!player.getInInventory()){
	        		board.setText(inventoryString(player));
	        		player.setInInventory(true);
	        	}
	        	else{
	        		board.setText(map.getMapString());
	        		player.setInInventory(false);
	        	}
			}//end I case
			if(input == "SPACE")
			{
	        	if(player.getInInventory())
	        	{
	        		//do some stuff
	        	}
	        	else
	        		interact(map, player, console);
			}
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
	        		getStatsInConsole(console, player, startLoopTime, updateLoopTime, tickCheck, tickCount,runTime);
	        	
			}//end W case
		}//end for
		player.clearConsoleInputQueue();
	}//end method
	
	public static void executeTextBarQueue(Map map, Player player, JTextArea board, JTextArea console)
	{
		
	}
	
	public static void getStatsInConsole(JTextArea console, Player player,long startLoopTime, long updateLoopTime, long tickCheck, long tickCount, String runTime)
	{
		console.setText("RunTime: "+runTime+
				"\nTickCount: "+tickCount+
				"\ntickCheck: "+tickCheck+
				"\nstartLoopTime: "+startLoopTime+
				"\nupdateLoopTime: "+updateLoopTime+
				"\nPlayer Location: ("+player.getPlayerXCoord()+","+player.getPlayerYCoord()+")"+
				"");
		player.setInConsoleInfo(true);
	}
	
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
	
	public static Map moveMapToRight(Map map, Player player)
	{
		if(map.getCharacterList().get(player.getPlayerYCoord()).size() -1 == player.getPlayerXCoord() || map.getHitBoxCharacterList().contains(""+map.getCharacterList().get(player.getPlayerYCoord()).get(player.getPlayerXCoord()+1)))
			return map;
		
		Character tempChar = player.getReplacedChar();
		player.setReplacedChar(map.getCharacterList().get(player.getPlayerYCoord()).get(player.getPlayerXCoord()+1));
		map.getCharacterList().get(player.getPlayerYCoord()).set(player.getPlayerXCoord(), tempChar);
		map.getCharacterList().get(player.getPlayerYCoord()).set(player.getPlayerXCoord()+1, player.gerPlayerIcon());
		player.setPlayerXCoord(player.getPlayerXCoord()+1);
		map.setMapString(convertMapToString(map.getCharacterList()));
		return map;
	}
	
	public static Map moveMapToLeft(Map map, Player player)
	{
		if(0 == player.getPlayerXCoord() || map.getHitBoxCharacterList().contains(""+map.getCharacterList().get(player.getPlayerYCoord()).get(player.getPlayerXCoord()-1)))
			return map;
		
		Character tempChar = player.getReplacedChar();
		player.setReplacedChar(map.getCharacterList().get(player.getPlayerYCoord()).get(player.getPlayerXCoord()-1));
		map.getCharacterList().get(player.getPlayerYCoord()).set(player.getPlayerXCoord(), tempChar);
		map.getCharacterList().get(player.getPlayerYCoord()).set(player.getPlayerXCoord()-1, player.gerPlayerIcon());
		player.setPlayerXCoord(player.getPlayerXCoord()-1);
		map.setMapString(convertMapToString(map.getCharacterList()));
		return map;
	}
	
	public static Map moveMapDown(Map map, Player player)
	{
		if(map.getCharacterList().get(player.getPlayerYCoord()).size() -1 == player.getPlayerYCoord() || map.getHitBoxCharacterList().contains(""+map.getCharacterList().get(player.getPlayerYCoord()+1).get(player.getPlayerXCoord())))
			return map;
		
		Character tempChar = player.getReplacedChar();
		player.setReplacedChar(map.getCharacterList().get(player.getPlayerYCoord()+1).get(player.getPlayerXCoord()));
		map.getCharacterList().get(player.getPlayerYCoord()).set(player.getPlayerXCoord(), tempChar);
		map.getCharacterList().get(player.getPlayerYCoord()+1).set(player.getPlayerXCoord(), player.gerPlayerIcon());
		player.setPlayerYCoord(player.getPlayerYCoord()+1);
		map.setMapString(convertMapToString(map.getCharacterList()));
		return map;
	}
	
	public static Map moveMapUp(Map map, Player player)
	{
		if(0 == player.getPlayerYCoord()|| map.getHitBoxCharacterList().contains(""+map.getCharacterList().get(player.getPlayerYCoord()-1).get(player.getPlayerXCoord())))
			return map;
		
		Character tempChar = player.getReplacedChar();
		player.setReplacedChar(map.getCharacterList().get(player.getPlayerYCoord()-1).get(player.getPlayerXCoord()));
		map.getCharacterList().get(player.getPlayerYCoord()).set(player.getPlayerXCoord(), tempChar);
		map.getCharacterList().get(player.getPlayerYCoord()-1).set(player.getPlayerXCoord(), player.gerPlayerIcon());
		player.setPlayerYCoord(player.getPlayerYCoord()-1);
		map.setMapString(convertMapToString(map.getCharacterList()));
		return map;
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
	
	public static void interact(Map map, Player player, JTextArea console)
	{
		
		List<InteractableObject> iObject = new ArrayList();
		for(InteractableObject object : map.getInteractableObjectList())
		{
			if(player.getPlayerXCoord() == object.getXYCoordinateList().get(0) && player.getPlayerYCoord() == object.getXYCoordinateList().get(1))
			{
				iObject.add(object);
			}
		}
		
		
		for(InteractableObject object : iObject)
		{
			if(object.getisItem())
			{
				player.addItemToItemList(object.getItem());
				map.getInteractableObjectList().remove(object);
				player.setReplacedChar(' ');
				player.addToConsoleOutput("You have found a "+ object.getName()+"!\n");
				console.setText(player.getConvertedConsoleOutputToString());
			}
		}
		
	}
	
}
