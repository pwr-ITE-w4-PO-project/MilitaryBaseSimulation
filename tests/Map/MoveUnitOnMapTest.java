/**
 * 
 */
package Map;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.NeutralUnit.*;

import java.lang.reflect.Field;
import java.util.List;

import MilitaryBaseSimulation.Map.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class MoveUnitOnMapTest {

	int[] oldPos = {0,0};
	int[] newPos = {1,1};
	NeutralUnit unit;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		Map.GetInstance().initializeMap();
		unit = new NeutralUnit(0, oldPos);
		Map.GetInstance().placeUnitOnMap(unit);
		
		Map.GetInstance().moveUnitOnMap(oldPos, newPos);
	}

	@Test
	void sayUnitIsMoved() {
		assertTrue(Map.GetInstance().getMap()[oldPos[0]][oldPos[1]] == null,"Unit is moved from old position.");
		assertTrue(Map.GetInstance().getMap()[newPos[0]][newPos[1]] == unit, "Unit is placed on new position.");
		assertFalse(!(Map.GetInstance().getMap()[oldPos[0]][oldPos[1]] == null), "Unit should me moved from old position.");
		assertFalse(!(Map.GetInstance().getMap()[newPos[0]][newPos[1]] == unit), "Unit should be placed on new position.");
	}
	@Test
	void sayOldPositionIsAccessible() {
		try{
			Field field = Map.GetInstance().getClass().getDeclaredField("availablePositions");
			field.setAccessible(true);
			
			List<int[]> posList = (List<int[]>) field.get(Map.GetInstance());
			
			boolean positionFoundInAccessibles = false;
			
			for(int[] position: posList) {
				if(position[0] == oldPos[0] && position[1] == oldPos[1]) {
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
	
	@Test
	void sayNewPositionIsInaccessible() {
		try{
			Field field = Map.GetInstance().getClass().getDeclaredField("availablePositions");
			field.setAccessible(true);
			
			List<int[]> posList = (List<int[]>) field.get(Map.GetInstance());
			
			boolean positionNotFoundInAccessibles = true;
			
			for(int[] position: posList) {
				if(position[0] == newPos[0] && position[1] == newPos[1]) {
					positionNotFoundInAccessibles = false;
					break;
				}
			}
			
			assertTrue(positionNotFoundInAccessibles, "Unit's new position is inaccessible.");
			assertFalse(!positionNotFoundInAccessibles, "Unit's new position should be inaccessible.");
			
		}
		catch(Exception e) {
			fail("Field avaialblePositions is not found - cannot test functionality.");
		}
	}

}
