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

import player.Player;

public class Run {

	public static void main(String[] args)
	{		
		System.out.println("hello");
		JTextArea board = display.Launch.launch();
		List<List<Character>> map = getMap();
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
	        if(firstLoop || tickCheck > 10000 )
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
	
	
	public static void initialSetup(List<List<Character>> map,JTextArea board,Player player)
	{
			
		player.setPlayerXCoord(3);
		player.setPlayerYCoord(3);

		player.setReplacedChar(map.get(player.getPlayerYCoord()).get(player.getPlayerXCoord()));
		map.get(player.getPlayerYCoord()).set(player.getPlayerXCoord(), player.gerPlayerIcon());
		String mapString = convertMapToString(map);
		
		board.setText(mapString);
	}
	
	
	public static void listener (JTextArea board, Player player, List<List<Character>> map)
	{
		
		
		//board.setText("in listener");
		board.addKeyListener(new KeyListener(){
		    @Override
		    public void keyPressed(KeyEvent e){
		    	switch (e.getKeyCode()){
		        
		        case KeyEvent.VK_W:
		        	board.setText("YOU PRESSED W");
		        	return;
		        case KeyEvent.VK_S:
		        	board.setText("YOU PRESSED S");
		        	return;
		        case KeyEvent.VK_A:
		        	board.setText("YOU PRESSED A");
		        	return;
		        case KeyEvent.VK_D:
	        		List<List<Character>> reMap = moveMapToRight(map, player);
		        	String reMapString = convertMapToString(reMap);
		        	board.setText(reMapString);
		        	//board.setText(""+player.getPlayerXCoord());
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
	
	
	public static List<List<Character>> getMap()
	{
		List<List<Character>> map = new ArrayList<List<Character>>();
		
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
			map.add(mapLine);
		}
		
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
	
	public static List<List<Character>> moveMapToRight(List<List<Character>> map, Player player)
	{
		
		if(map.get(player.getPlayerYCoord()).size() -1 == player.getPlayerXCoord())
			return map;
		
		Character tempChar = player.getReplacedChar();
		player.setReplacedChar(map.get(player.getPlayerYCoord()).get(player.getPlayerXCoord()+1));
		map.get(player.getPlayerYCoord()).set(player.getPlayerXCoord(), tempChar);
		map.get(player.getPlayerYCoord()).set(player.getPlayerXCoord()+1, player.gerPlayerIcon());
		player.setPlayerXCoord(player.getPlayerXCoord()+1);
		
		return map;
	}
	public static Player movePlayerToRight(List<List<Character>> map, Player player)
	{
		
		if(map.get(player.getPlayerYCoord()).size() -1 == player.getPlayerXCoord())
			return player;
		
		Character tempChar = player.getReplacedChar();
		player.setReplacedChar(map.get(player.getPlayerYCoord()).get(player.getPlayerXCoord()+1));
		player.setPlayerXCoord(player.getPlayerXCoord()+1);
		
		return player;
	}
	
	
	
}
