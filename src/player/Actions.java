package player;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import item.Bullet;
import map.InteractableObject;
import map.Map;
import display.DisplayStrings;

public class Actions {
	public static Map moveMapToRight(Map map, Player player)
	{
		if(map.getCharacterList().get(player.getPlayerYCoord()).size() -1 == player.getPlayerXCoord() || map.getHitBoxCharacterList().contains(""+map.getCharacterList().get(player.getPlayerYCoord()).get(player.getPlayerXCoord()+1)))
			return map;
		player.setLastMoved('R');
		Character tempChar = player.getReplacedChar();
		player.setReplacedChar(map.getCharacterList().get(player.getPlayerYCoord()).get(player.getPlayerXCoord()+1));
		map.getCharacterList().get(player.getPlayerYCoord()).set(player.getPlayerXCoord(), tempChar);
		map.getCharacterList().get(player.getPlayerYCoord()).set(player.getPlayerXCoord()+1, player.getPlayerIcon());
		player.setPlayerXCoord(player.getPlayerXCoord()+1);
		map.setMapString(DisplayStrings.convertMapToString(map.getCharacterList()));
		return map;
	}
	
	public static Map moveMapToLeft(Map map, Player player)
	{
		if(0 == player.getPlayerXCoord() || map.getHitBoxCharacterList().contains(""+map.getCharacterList().get(player.getPlayerYCoord()).get(player.getPlayerXCoord()-1)))
			return map;
		player.setLastMoved('L');
		Character tempChar = player.getReplacedChar();
		player.setReplacedChar(map.getCharacterList().get(player.getPlayerYCoord()).get(player.getPlayerXCoord()-1));
		map.getCharacterList().get(player.getPlayerYCoord()).set(player.getPlayerXCoord(), tempChar);
		map.getCharacterList().get(player.getPlayerYCoord()).set(player.getPlayerXCoord()-1, player.getPlayerIcon());
		player.setPlayerXCoord(player.getPlayerXCoord()-1);
		map.setMapString(DisplayStrings.convertMapToString(map.getCharacterList()));
		return map;
	}
	
	public static Map moveMapDown(Map map, Player player)
	{
		if(map.getCharacterList().get(player.getPlayerYCoord()).size() -1 == player.getPlayerYCoord() || map.getHitBoxCharacterList().contains(""+map.getCharacterList().get(player.getPlayerYCoord()+1).get(player.getPlayerXCoord())))
			return map;
		player.setLastMoved('D');
		Character tempChar = player.getReplacedChar();
		player.setReplacedChar(map.getCharacterList().get(player.getPlayerYCoord()+1).get(player.getPlayerXCoord()));
		map.getCharacterList().get(player.getPlayerYCoord()).set(player.getPlayerXCoord(), tempChar);
		map.getCharacterList().get(player.getPlayerYCoord()+1).set(player.getPlayerXCoord(), player.getPlayerIcon());
		player.setPlayerYCoord(player.getPlayerYCoord()+1);
		map.setMapString(DisplayStrings.convertMapToString(map.getCharacterList()));
		return map;
	}
	
	public static Map moveMapUp(Map map, Player player)
	{
		if(0 == player.getPlayerYCoord()|| map.getHitBoxCharacterList().contains(""+map.getCharacterList().get(player.getPlayerYCoord()-1).get(player.getPlayerXCoord())))
			return map;
		player.setLastMoved('U');
		Character tempChar = player.getReplacedChar();
		player.setReplacedChar(map.getCharacterList().get(player.getPlayerYCoord()-1).get(player.getPlayerXCoord()));
		map.getCharacterList().get(player.getPlayerYCoord()).set(player.getPlayerXCoord(), tempChar);
		map.getCharacterList().get(player.getPlayerYCoord()-1).set(player.getPlayerXCoord(), player.getPlayerIcon());
		player.setPlayerYCoord(player.getPlayerYCoord()-1);
		map.setMapString(DisplayStrings.convertMapToString(map.getCharacterList()));
		return map;
	}
	
	public static void interact(Map map, Player player, JTextArea console)
	{
		
		List<InteractableObject> iObject = new ArrayList();
		for(InteractableObject object : map.getInteractableObjectList())
		{
			if(player.getPlayerXCoord() == object.getXYCoordinateList().get(0) && player.getPlayerYCoord() == object.getXYCoordinateList().get(1))
			{
				iObject.add(object);
			}
		}
		
		
		for(InteractableObject object : iObject)
		{
			if(object.getisItem())
			{
				player.addItemToItemList(object.getItem());
				map.getInteractableObjectList().remove(object);
				player.setReplacedChar(' ');
				player.addToConsoleOutput("You have found a "+ object.getName()+"!\n");
				console.setText(player.getConvertedConsoleOutputToString());
			}
		}
		
	}
	
	public static void fire(Map map, Player player, JTextArea console)
	{
		Bullet bullet = new Bullet();
		bullet.setDirection(player.getLastMoved());
		if(bullet.getDirection() == 'U' || bullet.getDirection() == 'D')
			bullet.setBulletIcon('|');
		else
			bullet.setBulletIcon('-');
		bullet.setDamage(10);
		bullet.setSpeed(1);
		bullet.setReplacedChar('@');
		bullet.setBulletXCoord(player.getPlayerXCoord());
		bullet.setBulletYCoord(player.getPlayerYCoord());
		player.addBulletToList(bullet);
		
		return;
	}//end fire
	
}
