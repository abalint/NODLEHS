package player;

public class Player {

	Character playerIcon = '@';
	Character replacedChar;
	int playerXcoord;
	int playerYcoord;
	
	
	public void setPlayerIcon(Character playerIconIn){this.playerIcon = playerIconIn ;}
	public void setReplacedChar(Character replacedCharIn){this.replacedChar = replacedCharIn ;}
	public void setPlayerXCoord(int playerXcoordIn){this.playerXcoord = playerXcoordIn;}
	public void setPlayerYCoord(int playerYcoordIn){this.playerYcoord = playerYcoordIn;}
	
	public Character gerPlayerIcon(){return this.playerIcon;}
	public Character getReplacedChar(){return this.replacedChar;}
	public int getPlayerXCoord(){return this.playerXcoord;}
	public int getPlayerYCoord(){return this.playerYcoord;}
	
}
