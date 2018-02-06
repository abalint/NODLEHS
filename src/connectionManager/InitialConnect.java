package connectionManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JTextArea;

public class InitialConnect {

	public static void startConnection(JTextArea board, JTextArea console)
	{
		BufferedReader in = null;
	    PrintWriter out = null;
		try{
		String serverAddress = "localhost";
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
	}//end startConnection
}
