package main;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import javax.swing.JTextArea;

import map.Map;
import player.Player;

public class Run {

	public static void main(String[] args)
	{		
		System.out.println("hello");
		JTextArea board = display.Launch.launch();
		Map map = getMap();
		Player player = new Player();
		initialSetup(map, board, player);
		double time_passed = 0;
		double delta_time = 0;
		long startLoopTime = Calendar.getInstance().getTimeInMillis();
		long updateLoopTime = Calendar.getInstance().getTimeInMillis();
		boolean firstLoop = true;
		
		while (true) { // keep running
	    	startLoopTime = Calendar.getInstance().getTimeInMillis();
	        long tickCheck = startLoopTime - updateLoopTime;
	        if(firstLoop || tickCheck > 1000000 )
	        {
	        	firstLoop = false;
	        	listener(board, player, map);
	        	updateLoopTime = Calendar.getInstance().getTimeInMillis();
	        }
	        //board.setText("nstartLoopTime: "+startLoopTime+"\nupdateLoopTime: "+updateLoopTime+"\ndeltaTime: "+tickCheck);       
	    }
		
		
//kinda works...		
//		
////		for(int i = 0; i < 2; i++)
//		//{
//			while (true) { // keep running
//		    	long startLoopTime = Calendar.getInstance().getTimeInMillis();
//		        // update game logic once for every tick passed
//		    	boolean playerHasntMoved = true;
//		        while (time_passed >= 100) {
//		        	listener(board, player, map);
//		            time_passed -= 100;
//		            // You might limit the number of iterations here (like 10)
//		            // to not get stuck due to processing taking dtoo long (and time adding up)
//		        }
//		        //String mapString = convertMapToString(map);
//		        //board.setText("test");
//		        // draw screen content
//		        // update timing
//		    	long endLoopTime = Calendar.getInstance().getTimeInMillis(); 
//		        delta_time = endLoopTime - startLoopTime;
//		        time_passed += delta_time;
//		        board.setText("in while true \nstartLoopTime: "+startLoopTime+"\nendLoopTime: "+endLoopTime+"\ndeltaTime: "+delta_time+"\ntimePassed: "+time_passed);       
//		    }
	}
	
	
	public static void initialSetup(Map map,JTextArea board,Player player)
	{
			
		player.setPlayerXCoord(3);
		player.setPlayerYCoord(3);

		player.setReplacedChar(map.getCharacterList().get(player.getPlayerYCoord()).get(player.getPlayerXCoord()));
		map.getCharacterList().get(player.getPlayerYCoord()).set(player.getPlayerXCoord(), player.gerPlayerIcon());
		map.setMapString(convertMapToString(map.getCharacterList()));
		
		board.setText(map.getMapString());
	}
	
	
	public static void listener (JTextArea board, Player player, Map map)
	{
		
		
		//board.setText("in listener");
		board.addKeyListener(new KeyListener(){
		    @Override
		    public void keyPressed(KeyEvent e){
		    	switch (e.getKeyCode()){
		        
		        case KeyEvent.VK_W:
		        	Map reMap = moveMapUp(map, player);
		        	board.setText(map.getMapString());
		        	return;
		        case KeyEvent.VK_S:
		        	reMap = moveMapDown(map, player);
		        	board.setText(map.getMapString());
		        	return;
		        case KeyEvent.VK_A:
		        	reMap = moveMapToLeft(map, player);
		        	board.setText(map.getMapString());
		        	return;
		        case KeyEvent.VK_D:
		        	reMap = moveMapToRight(map, player);
		        	board.setText(map.getMapString());
		        	return;
		        case KeyEvent.VK_I:
		        	board.setText("YOU PRESSED I");
		        	return;
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
	
	
	public static Map getMap()
	{
		List<List<Character>> mapCharacterList = new ArrayList<List<Character>>();
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileReader("C:/Users/andrew.balint.SATS/workspace/NODLEHS/src/Files/map.txt")); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		while(scanner.hasNextLine())
		{
			List<Character>mapLine = new ArrayList<Character>();
			String line = scanner.nextLine();
			for(int i = 0; i < line.length(); i ++)
			{
				mapLine.add(line.charAt(i));
			}
			mapCharacterList.add(mapLine);
		}
		
		Map map = new Map();
		map.setCharacterList(mapCharacterList);
		map.setMapString(convertMapToString(mapCharacterList));
		return map;
	}//end getMap
	
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
	
}
