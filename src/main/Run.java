package main;


import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextArea;
import javax.swing.UIManager;

import ai.EnemyAI;
import display.LaunchWindow;
import item.Bullet;
import item.Item;
import map.InteractableObject;
import map.Map;
import player.NPC;
import player.Player;
import connectionManager.InitialConnect;
import main.Setups;
import player.Checks;
import display.Console;

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
		Setups.initialSetup(map, board, player);	
		List<NPC> npcList = Setups.NPCSetup(map, board);
		
		long gameLaunchTime = Calendar.getInstance().getTimeInMillis();
		long startLoopTime = Calendar.getInstance().getTimeInMillis();
		long updateLoopTime = Calendar.getInstance().getTimeInMillis();
		boolean firstLoop = true;
		Listeners.boardListener(board, player, map);
		Listeners.consoleListener(console, player, map);
		int tickCount = 0;
		
		player.addToConsoleOutput("welcome!");
		console.setText(player.getConvertedConsoleOutputToString());
		
		InitialConnect.startConnection(board, console);

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
	        	QueueExecuters.executeBoardQueue(map, player, board, console);
	        	}catch(Exception e){
	        		//console.setText(e.toString());
	        	}
	        	if(player.getBulletList().size() !=0)
	        		board.setText(Updaters.updateBullets(map, player, board, npcList).getMapString());
	        	if(Checks.checkNPCHealth(npcList, map))
	        		board.setText(map.getMapString());
	        	QueueExecuters.executeConsoleQueue(map, player, board, console, startLoopTime, updateLoopTime, tickCheck, tickCount, runTimeFormated);
	        	if(player.getInConsoleInfo())
	        		Console.getStatsInConsole(console, player, startLoopTime, updateLoopTime, tickCheck, tickCount, runTimeFormated);
	        	firstLoop = false;
	        	updateLoopTime = Calendar.getInstance().getTimeInMillis();
	        	if(tickCount % 25 ==0)
	        	{
	        		Updaters.updateNPC(map, npcList, player);
	        		board.setText(map.getMapString());
	        	}
	        	
	        	
	        }
	    }
	}


}

