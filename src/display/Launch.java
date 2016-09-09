package display;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JWindow;

public class Launch {
	
	public static void launch()
	{
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());  //give your JPanel a BorderLayout

		JTextArea text = new JTextArea(); 
		text.setText("testaskldjfklajsdfkl;ajsdfk");
		text.setEditable(false);
		
		
		//===key listener
		text.addKeyListener(new KeyListener(){
		    @Override
		    public void keyPressed(KeyEvent e){
		        switch (e.getKeyCode()){
		        
		        case KeyEvent.VK_W:
		        	text.setText("YOU PRESSED W ");
		        	break;
		        case KeyEvent.VK_S:
		        	text.setText("YOU PRESSED S ");
		        	break;
		        case KeyEvent.VK_A:
		        	text.setText("YOU PRESSED A ");
		        	break;
		        case KeyEvent.VK_D:
		        	text.setText("YOU PRESSED D ");
		        	break;
		        case KeyEvent.VK_I:
		        	text.setText("YOU PRESSED I ");
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
		
		
		
		JScrollPane scroll = new JScrollPane(text); //place the JTextArea in a scroll pane
		panel.add(scroll, BorderLayout.CENTER); //add the JScrollPane to the panel
		// CENTER will use up all available space
		
		JFrame frame = new JFrame();
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(500, 500);
		
	
		
	}

}
