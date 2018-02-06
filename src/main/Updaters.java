package main;

import java.util.List;

import javax.swing.JTextArea;

import ai.EnemyAI;
import item.Bullet;
import map.Map;
import player.NPC;
import player.Player;
import display.DisplayStrings;

public class Updaters {
	public static Map updateBullets(Map map, Player player, JTextArea board, List<NPC> npcList)
	{
		//for each bullet update the movement
		for(Bullet bullet : player.getBulletList())
		{
			Character tempChar;
			switch (bullet.getDirection()){
	        case 'U':
	        	if(map.getNPCCharacterList().contains(""+map.getCharacterList().get(bullet.getBulletYCoord()-1).get(bullet.getBulletXCoord())))
	        	{
	        		for(NPC npc : npcList)
	        		{
	        			if(npc.getYCoord() == bullet.getBulletYCoord()-1 && npc.getXCoord() == bullet.getBulletXCoord())
	        			{
	        				npc.setHealth(npc.getHealth() - bullet.getDamage());
	        			}
	        		}//end foreach
	        	}//end if hits npc
	        		
	        		
	        	if(0 == bullet.getBulletYCoord()|| map.getHitBoxCharacterList().contains(""+map.getCharacterList().get(bullet.getBulletYCoord()-1).get(bullet.getBulletXCoord())))
	        	{
	        		player.getBulletList().remove(bullet);
	        		map.getCharacterList().get(bullet.getBulletYCoord()).set(bullet.getBulletXCoord(), bullet.getReplacedChar());
	        		map.setMapString(DisplayStrings.convertMapToString(map.getCharacterList()));
	        		return map;
	        	}//end if hits hitbox char
	        	
	        	tempChar = bullet.getReplacedChar();
	    		bullet.setReplacedChar(map.getCharacterList().get(bullet.getBulletYCoord()-1).get(bullet.getBulletXCoord()));
	    		map.getCharacterList().get(bullet.getBulletYCoord()).set(bullet.getBulletXCoord(), tempChar);
	    		map.getCharacterList().get(bullet.getBulletYCoord()-1).set(bullet.getBulletXCoord(), bullet.getBulletIcon());
	    		bullet.setBulletYCoord(bullet.getBulletYCoord()-1);
	    		map.setMapString(DisplayStrings.convertMapToString(map.getCharacterList()));
	        	break;
	        case 'D':
	        	if(map.getNPCCharacterList().contains(""+map.getCharacterList().get(bullet.getBulletYCoord()+1).get(bullet.getBulletXCoord())))
	        	{
	        		for(NPC npc : npcList)
	        		{
	        			if(npc.getYCoord() == bullet.getBulletYCoord()+1 && npc.getXCoord() == bullet.getBulletXCoord())
	        			{
	        				npc.setHealth(npc.getHealth() - bullet.getDamage());
	        			}
	        		}//end foreach
	        	}//end if hits npc
	        	if(map.getCharacterList().get(bullet.getBulletYCoord()).size() -1 == bullet.getBulletYCoord() || map.getHitBoxCharacterList().contains(""+map.getCharacterList().get(bullet.getBulletYCoord()+1).get(bullet.getBulletXCoord())))
	        	{
	        		player.getBulletList().remove(bullet);
	        		map.getCharacterList().get(bullet.getBulletYCoord()).set(bullet.getBulletXCoord(), bullet.getReplacedChar());
	        		map.setMapString(DisplayStrings.convertMapToString(map.getCharacterList()));
	        		return map;
	    		}
	    		tempChar = bullet.getReplacedChar();
	    		bullet.setReplacedChar(map.getCharacterList().get(bullet.getBulletYCoord()+1).get(bullet.getBulletXCoord()));
	    		map.getCharacterList().get(bullet.getBulletYCoord()).set(bullet.getBulletXCoord(), tempChar);
	    		map.getCharacterList().get(bullet.getBulletYCoord()+1).set(bullet.getBulletXCoord(), bullet.getBulletIcon());
	    		bullet.setBulletYCoord(bullet.getBulletYCoord()+1);
	    		map.setMapString(DisplayStrings.convertMapToString(map.getCharacterList()));
	        	break;
	        case 'L':
	        	if(map.getNPCCharacterList().contains(""+map.getCharacterList().get(bullet.getBulletYCoord()).get(bullet.getBulletXCoord()-1)))
	        	{
	        		for(NPC npc : npcList)
	        		{
	        			if(npc.getYCoord() == bullet.getBulletYCoord() && npc.getXCoord() == bullet.getBulletXCoord()-1)
	        			{
	        				npc.setHealth(npc.getHealth() - bullet.getDamage());
	        			}
	        		}//end foreach
	        	}//end if hits npc
	        	if(0 == bullet.getBulletXCoord() || map.getHitBoxCharacterList().contains(""+map.getCharacterList().get(bullet.getBulletYCoord()).get(bullet.getBulletXCoord()-1)))
	        	{
	        		player.getBulletList().remove(bullet);
	        		map.getCharacterList().get(bullet.getBulletYCoord()).set(bullet.getBulletXCoord(), bullet.getReplacedChar());
	        		map.setMapString(DisplayStrings.convertMapToString(map.getCharacterList()));
	        		return map;
	    		}
	        	tempChar = bullet.getReplacedChar();
	    		bullet.setReplacedChar(map.getCharacterList().get(bullet.getBulletYCoord()).get(bullet.getBulletXCoord()-1));
	    		map.getCharacterList().get(bullet.getBulletYCoord()).set(bullet.getBulletXCoord(), tempChar);
	    		map.getCharacterList().get(bullet.getBulletYCoord()).set(bullet.getBulletXCoord()-1, bullet.getBulletIcon());
	    		bullet.setBulletXCoord(bullet.getBulletXCoord()-1);
	    		map.setMapString(DisplayStrings.convertMapToString(map.getCharacterList()));
	        	break;
	        case 'R':
	        	if(map.getNPCCharacterList().contains(""+map.getCharacterList().get(bullet.getBulletYCoord()).get(bullet.getBulletXCoord()+1)))
	        	{
	        		for(NPC npc : npcList)
	        		{
	        			if(npc.getYCoord() == bullet.getBulletYCoord() && npc.getXCoord() == bullet.getBulletXCoord()+1)
	        			{
	        				npc.setHealth(npc.getHealth() - bullet.getDamage());
	        			}
	        		}//end foreach
	        	}//end if hits npc
	        	if(map.getCharacterList().get(bullet.getBulletYCoord()).size() -1 == bullet.getBulletXCoord() || map.getHitBoxCharacterList().contains(""+map.getCharacterList().get(bullet.getBulletYCoord()).get(bullet.getBulletXCoord()+1)))
	    		{
	        		map.getCharacterList().get(bullet.getBulletYCoord()).set(bullet.getBulletXCoord(), bullet.getReplacedChar());
	        		player.getBulletList().remove(bullet);
	        		map.setMapString(DisplayStrings.convertMapToString(map.getCharacterList()));
	        		return map;
	    		}
		    		tempChar = bullet.getReplacedChar();
		    		bullet.setReplacedChar(map.getCharacterList().get(bullet.getBulletYCoord()).get(bullet.getBulletXCoord()+1));
		    		map.getCharacterList().get(bullet.getBulletYCoord()).set(bullet.getBulletXCoord(), tempChar);
		    		map.getCharacterList().get(bullet.getBulletYCoord()).set(bullet.getBulletXCoord()+1, bullet.getBulletIcon());
		    		bullet.setBulletXCoord(bullet.getBulletXCoord()+1);
		    		map.setMapString(DisplayStrings.convertMapToString(map.getCharacterList()));
		    		//board.setText(map.getMapString());
	        	break;
			}//end switch case
			
		}//end foreach
		
		return map;
	}//end updateBullets
	
	public static void updateNPC(Map map, List<NPC> npcList, Player player)
	{
		
		for(NPC npc : npcList)
		{
			if(npc.getNPCType().compareTo ("enemy") == 0)
			{
				map = EnemyAI.update(map, npc, player);
			}
			
		}//end foreach
		map.setMapString(DisplayStrings.convertMapToString(map.getCharacterList()));
		return;
	}


}
