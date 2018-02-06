package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

import map.Map;
import player.Player;

public class Listeners {

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
	
}
