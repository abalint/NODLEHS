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
	
	
	public static JTextArea launch()
	{
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());  //give your JPanel a BorderLayout

		JTextArea text = new JTextArea(); 
		text.setEditable(false);
		text.setFont(new Font("monospaced", Font.PLAIN, 12));
		JScrollPane scroll = new JScrollPane(text); //place the JTextArea in a scroll pane
		panel.add(scroll, BorderLayout.CENTER); //add the JScrollPane to the panel
		// CENTER will use up all available space
		JFrame frame = new JFrame();
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(3);
		
		return text;
		
	}
	
}
