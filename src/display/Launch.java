package display;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JWindow;

import player.Player;

public class Launch {
	
	
	public static void launch()
	{
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());  //give your JPanel a BorderLayout

		JTextArea text = new JTextArea(); 
		text.setText("testaskldjfklajsdfkl;ajsdfk");
		text.setEditable(false);
		
		Player player = new Player();	
		player.setPlayerXCoord(3);
		player.setPlayerYCoord(3);
		
		List<List<Character>> map = getMap();
		player.setReplacedChar(map.get(player.getPlayerYCoord()).get(player.getPlayerXCoord()));
		map.get(player.getPlayerYCoord()).set(player.getPlayerXCoord(), player.gerPlayerIcon());
		String mapString = convertMapToString(map);
		text.setFont(new Font("monospaced", Font.PLAIN, 12));
		text.setText(mapString);
		
		//===key listener
		text.addKeyListener(new KeyListener(){
		    @Override
		    public void keyPressed(KeyEvent e){
		        switch (e.getKeyCode()){
		        
		        case KeyEvent.VK_W:
		        	text.setText("YOU PRESSED W");
		        	break;
		        case KeyEvent.VK_S:
		        	text.setText("YOU PRESSED S");
		        	break;
		        case KeyEvent.VK_A:
		        	text.setText("YOU PRESSED A");
		        	break;
		        case KeyEvent.VK_D:
		        	//map = moveToRight(map, player);
		        	text.setText(convertMapToString(map));
		        	break;
		        case KeyEvent.VK_I:
		        	text.setText("YOU PRESSED I");
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
		//===key listener
		System.out.println("test");
		
		
		JScrollPane scroll = new JScrollPane(text); //place the JTextArea in a scroll pane
		panel.add(scroll, BorderLayout.CENTER); //add the JScrollPane to the panel
		// CENTER will use up all available space
		
		JFrame frame = new JFrame();
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(3);
		
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
