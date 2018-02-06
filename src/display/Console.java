package display;

import javax.swing.JTextArea;

import player.Player;

public class Console {

	public static void getStatsInConsole(JTextArea console, Player player,long startLoopTime, long updateLoopTime, long tickCheck, long tickCount, String runTime)
	{
		String bulletXY = "no bullets";
		if(player.getBulletList().size() != 0)
			bulletXY = "("+player.getBulletList().get(0).getBulletXCoord()+","+player.getBulletList().get(0).getBulletYCoord()+")";
		console.setText("RunTime: "+runTime+
				"\nTickCount: "+tickCount+
				"\ntickCheck: "+tickCheck+
				"\nstartLoopTime: "+startLoopTime+
				"\nupdateLoopTime: "+updateLoopTime+
				"\nPlayer Location: ("+player.getPlayerXCoord()+","+player.getPlayerYCoord()+")"+
				"\nBullets: "+player.getBulletList().size()+
				"\nFirst bullet location "+bulletXY);
		player.setInConsoleInfo(true);
	}
	
}
