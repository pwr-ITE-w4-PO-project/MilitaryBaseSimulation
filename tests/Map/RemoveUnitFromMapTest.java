/**
 * 
 */
package Map;

import MilitaryBaseSimulation.Map.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;

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
		Map.getInstance().initializeMap();
	}

	@Test
	void sayUnitIsRemoved(){
		int[] pos = {0,0}; 
		NeutralUnit unit = new NeutralUnit(0, pos);
		Map.getInstance().placeUnitOnMap(unit);
		
		Map.getInstance().removeUnitFromMap(unit);
		
		assertTrue(Map.getInstance().getMap()[pos[0]][pos[1]] == null, "Unit wasn't removed from the map.");
	}
	
	@Test
	void sayPositionIsAccessible() {
		int[] pos = {0,0}; 
		NeutralUnit unit = new NeutralUnit(0, pos);
		Map.getInstance().placeUnitOnMap(unit);
		
		Map.getInstance().removeUnitFromMap(unit);
		
		try{
			Field field = Map.getInstance().getClass().getDeclaredField("availablePositions");
			field.setAccessible(true);
			@SuppressWarnings("unchecked")
			List<int[]> posList = (List<int[]>) field.get(Map.getInstance());
			
			boolean positionFoundInAccessibles = false;
			
			for(int[] position: posList) {
				if(position[0] == pos[0] && position[1] == pos[1]) {
					positionFoundInAccessibles = true;
					break;
				}
			}
			
			assertTrue(positionFoundInAccessibles, "Freed position is inaccessible, though it should be accessible.");
			
		}
		catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}

}
