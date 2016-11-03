package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextArea;
import javax.swing.UIManager;

import display.LaunchWindow;

public class RunClient {
	 
	public static void main(String[] args) throws IOException
	{		
	
	    
		LaunchWindow frame;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		frame = new LaunchWindow();
		frame.setVisible(true);
		JTextArea textBar = frame.getInputTextArea();
		JTextArea console = frame.getConsole();
		JTextArea board = frame.getBoard();
		
		Entered enter = new Entered();
		enter.setEntered(false);
	
		
		//player.addToConsoleOutput("welcome!");
		
		
		
		
		BufferedReader in = null;
	    PrintWriter out = null;
		try{
		String serverAddress = "10.79.51.153";
       Socket socket = new Socket(serverAddress, 9001);
       in = new BufferedReader(new InputStreamReader(
           socket.getInputStream()));
       out = new PrintWriter(socket.getOutputStream(), true);
		}
		catch(Exception e)
		{
			console.setText("Something broke while connecting");
		}
		
		
		board.setText("Client start\n");
		board.setText(board.getText()+"Connected to server\n");
		long gameLaunchTime = Calendar.getInstance().getTimeInMillis();
		long startLoopTime = Calendar.getInstance().getTimeInMillis();
		long updateLoopTime = Calendar.getInstance().getTimeInMillis();
		boolean firstLoop = true;
		int tickCount = 0;
		textBarListener(textBar, enter, out);
		
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
	        	//server check
	        	if(enter.getEntered())
	        		pressedEnter(textBar, console, enter, out);
	        	
	        	String line = in.readLine();      
	        	if (line.startsWith("SUBMITNAME")) {
	        		console.append("Enter your username\n");
	        	}
	        	else if(line.startsWith("SUBMITPASS"))
	        	{
	        		console.append("Enter password\n");
	        	}
		       	else if (line.startsWith("NAMEACCEPTED")) {
		             console.append("accepted\n");
		        }
		        else if(line.startsWith("INVALIDNAME")){
		        	console.append("User does not exist, would you like to create this character?\n");
		        }
		        else if(line.startsWith("SUBMITNEWPASS"))
		        {
		        	console.append("Enter password\n");
		        }
		        else if (line.startsWith("MESSAGE")) {
		            board.append(line.substring(8) + "\n");
		        }
	//	            if(enter.getEntered())
	//	            	console.setText("true");
	//	            else
	//	            	console.setText("false");
	    
	        	
	        	
	        	firstLoop = false;
	        	updateLoopTime = Calendar.getInstance().getTimeInMillis();
	        }
	    }
	}
	

	public static void pressedEnter(JTextArea textBar,JTextArea console,Entered enter, PrintWriter out)
	{
		//console.setText(console.getText()+""+textBar.getText());
		textBar.setText("");
		enter.setEntered(false);
		
	}
	public static void textBarListener (JTextArea textBar, Entered enter, PrintWriter out)
	{
		textBar.addKeyListener(new KeyListener(){
		    @Override
		    public void keyPressed(KeyEvent e){
		    	switch (e.getKeyCode()){
		        case KeyEvent.VK_ENTER:
		        	enter.setEntered(true);
		        	out.println(textBar.getText());
		        	
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

class Entered
{
	boolean entered;
	
	public boolean getEntered(){return entered;}
	
	public void setEntered(boolean input){this.entered = input;}
}
