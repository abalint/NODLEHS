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
//		JTextArea board = display.Launch.launch();
//		List<List<Character>> map = getMap();
//		Player player = new Player();
//		initialSetup(map, board, player);
//		double time_passed = 0;
//		double delta_time = 0;
//		int i = 0;
//			while (true) { // keep running
//		    	long startLoopTime = Calendar.getInstance().getTimeInMillis(); 
//		   
//		        // update game logic once for every tick passed
//		  
//		        while (time_passed >= .01) {
//		        	
//		            listener(board, player, map , i);
//		            time_passed -= .01;
//		            // You might limit the number of iterations here (like 10)
//		            // to not get stuck due to processing taking too long (and time adding up)
//		        }
//
//		        // draw screen content
//		        //drawStuff(delta_time);
//
//		        // update timing
//		    	long endLoopTime = Calendar.getInstance().getTimeInMillis(); 
//		        delta_time = startLoopTime - endLoopTime;
//		        time_passed += delta_time;
//		        i++;
//		       
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
	
	
	public static void listener (JTextArea board, Player player, List<List<Character>> map, int i)
	{
		
		
		board.setText(""+i);
//		board.addKeyListener(new KeyListener(){
//		    @Override
//		    public void keyPressed(KeyEvent e){
//		        switch (e.getKeyCode()){
//		        
//		        case KeyEvent.VK_W:
//		        	board.setText("YOU PRESSED W");
//		        	break;
//		        case KeyEvent.VK_S:
//		        	board.setText("YOU PRESSED S");
//		        	break;
//		        case KeyEvent.VK_A:
//		        	board.setText("YOU PRESSED A");
//		        	break;
//		        case KeyEvent.VK_D:
//		        	List<List<Character>> reMap = moveToRight(map, player);
//		        	String reMapString = convertMapToString(reMap);
//		        	board.setText(reMapString);
//		        	//text.setText(convertMapToString(map));
//		        	break;
//		        case KeyEvent.VK_I:
//		        	board.setText("YOU PRESSED I");
//		        	break;
//		        }
//		    }
//		    @Override
//		    public void keyTyped(KeyEvent e) {
//		    }
//		    @Override
//		    public void keyReleased(KeyEvent e) {
//		    }
//		});
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
	
	public static List<List<Character>> moveToRight(List<List<Character>> map, Player player)
	{
		
		if(map.get(player.getPlayerYCoord()).size() -1 == player.getPlayerXCoord())
			return map;
		
		Character tempChar = player.getReplacedChar();
		player.setReplacedChar(map.get(player.getPlayerYCoord()).get(player.getPlayerXCoord()+1));
		map.get(player.getPlayerYCoord()).set(player.getPlayerXCoord()+1, player.gerPlayerIcon());
		
		return map;
	}

	
	
	
}
