/**
 * 
 */
package Map;

import MilitaryBaseSimulation.Map.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.NeutralUnit.*;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class RemoveUnitFromMapTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		Map.GetInstance().initializeMap();
	}

	@Test
	void sayUnitIsRemoved(){
		int[] pos = {0,0}; 
		NeutralUnit unit = new NeutralUnit(0, pos);
		Map.GetInstance().placeUnitOnMap(unit);
		
		Map.GetInstance().removeUnitFromMap(unit);
		
		assertTrue(Map.GetInstance().getMap()[pos[0]][pos[1]] == null, "Unit is removed from the map.");
		assertFalse(Map.GetInstance().getMap()[pos[0]][pos[1]] == unit, "Unit should be removed from the map.");
	}
	
	@Test
	void sayPositionIsAccessible() {
		int[] pos = {0,0}; 
		NeutralUnit unit = new NeutralUnit(0, pos);
		Map.GetInstance().placeUnitOnMap(unit);
		
		Map.GetInstance().removeUnitFromMap(unit);
		
		try{
			Field field = Map.GetInstance().getClass().getDeclaredField("availablePositions");
			field.setAccessible(true);
			
			List<int[]> posList = (List<int[]>) field.get(Map.GetInstance());
			
			boolean positionFoundInAccessibles = false;
			
			for(int[] position: posList) {
				if(position[0] == pos[0] && position[1] == pos[1]) {
					positionFoundInAccessibles = true;
					break;
				}
			}
			
			assertTrue(positionFoundInAccessibles, "Freed position is accessible.");
			assertFalse(!positionFoundInAccessibles, "Freed position should be naccessible.");
			
		}
		catch(Exception e) {
			fail("Field avaialblePositions is not found - cannot test functionality.");
		}
	}

}
