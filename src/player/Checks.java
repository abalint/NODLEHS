package player;

import java.util.ArrayList;
import java.util.List;
import display.DisplayStrings;
import map.Map;

public class Checks {

	
	public static boolean checkNPCHealth(List<NPC> npcList, Map map)
	{
		boolean npcDied = false;
		List<NPC> removeNPC = new ArrayList();
		for(NPC npc : npcList)
		{
			if(npc.getHealth() <= 0)
			{
				map.getCharacterList().get(npc.getYCoord()).set(npc.getXCoord(), npc.getReplacedChar());
				removeNPC.add(npc);
				npcDied = true;
			}
			
		}//end foreach
		
		for(NPC npc : removeNPC)
		{
			npcList.remove(npc);
		}
		map.setMapString(DisplayStrings.convertMapToString(map.getCharacterList()));
		return npcDied;
	}
	
}
