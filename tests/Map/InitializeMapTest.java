/**
 * 
 */
package Map;
import MilitaryBaseSimulation.Map.*;
import MilitaryBaseSimulation.MapUnits.Unit.IUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class InitializeMapTest {

	@Test
	void sayMapIsCorrectylInitialized() {
		
		boolean initResult = true;
		
		Map.getInstance().initializeMap();
		try {
			Field map = Map.class.getDeclaredField("unitMap");
			map.setAccessible(true);
			IUnit[][] unitMap = (IUnit[][]) map.get(Map.getInstance());
			
			if(unitMap == null) initResult = false;
			
			Field avPos = Map.class.getDeclaredField("availablePositions");
			avPos.setAccessible(true);
			List<int[]> avPosList = (List<int[]>) avPos.get(Map.getInstance());
			
			if(avPosList == null) initResult = false;
			if(avPosList.size() != 50*50) initResult = false;
			
			int[] pos = new int[2];
			
			for(int x = 0; x<50; x++) {
				for(int y = 0; y<50; y++) {
					pos[0] = x;
					pos[1] = y;
					if(!checkPosContainment(pos, avPosList)) initResult = false;
				}
			}
			
			assertTrue(initResult, "Map was incorrectly initialized.");
			
		}catch(Exception e) {
			fail("Test found and error: " + e.getMessage());
		}
	}
	
	boolean checkPosContainment(int[] checked, List<int[]> list) {
		
		for(int[] pos: list) {
			if(checked[0] == pos[0] && checked[1] == pos[1]) {
				return true;
			}
		}
		
		return false;
	}

}
