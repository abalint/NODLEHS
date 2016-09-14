package display;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Launch {
	
	
	public static JTextArea launch()
	{
		
		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(new BorderLayout());  //give your JPanel a BorderLayout
		JTextArea board = new JTextArea(); 
		board.setEditable(false);
		board.setFont(new Font("monospaced", Font.PLAIN, 12));
		JScrollPane boardPane = new JScrollPane(board); //place the JTextArea in a scroll pane
		boardPanel.add(boardPane, BorderLayout.CENTER); //add the JScrollPane to the panel
		// CENTER will use up all available space
		
		
		JPanel consolePanel = new JPanel();
		consolePanel.setLayout(new BorderLayout());  //give your JPanel a BorderLayout
		JTextArea console = new JTextArea(); 
		console.setEditable(true);
		console.setFont(new Font("monospaced", Font.PLAIN, 12));
		JScrollPane consolePane = new JScrollPane(console); //place the JTextArea in a scroll pane
		boardPanel.add(console);
		//consolePanel.add(consolePane, BorderLayout.SOUTH); //add the JScrollPane to the panel
		// CENTER will use up all available space
		
		JPanel composit = new JPanel();
		//composit.add(consolePanel);
		//composit.add(boardPanel);
		
		JFrame frame = new JFrame();
		frame.add(boardPanel);
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(3);
		
	
		
		return board;
		
	}
	
}
