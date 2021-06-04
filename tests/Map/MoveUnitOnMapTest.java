/**
 * 
 */
package Map;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;

import java.lang.reflect.Field;
import java.util.List;

import MilitaryBaseSimulation.Map.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class MoveUnitOnMapTest {

	static int[] oldPos = {0,0};
	static int[] newPos = {1,1};
	static NeutralUnit unit;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUp() throws Exception {
		Map.getInstance().initializeMap();
		unit = new NeutralUnit(0, oldPos);
		Map.getInstance().placeUnitOnMap(unit);
		
		Map.getInstance().moveUnitOnMap(oldPos, newPos);
	}

	@Test
	void sayUnitIsMoved() {
		assertTrue(Map.getInstance().getMap()[oldPos[0]][oldPos[1]] == null,"Unit didn't move from old position.");
		assertTrue(Map.getInstance().getMap()[newPos[0]][newPos[1]] == unit, "Unit isn't placed on new position.");
	}
	@Test
	void sayOldPositionIsAccessible() {
		try{
			Field field = Map.getInstance().getClass().getDeclaredField("availablePositions");
			field.setAccessible(true);
			@SuppressWarnings("unchecked")
			List<int[]> posList = (List<int[]>) field.get(Map.getInstance());
			
			boolean positionFoundInAccessibles = false;
			
			for(int[] position: posList) {
				if(position[0] == oldPos[0] && position[1] == oldPos[1]) {
					positionFoundInAccessibles = true;
					break;
				}
			}
			
			assertTrue(positionFoundInAccessibles, "Freed position is inaccessible.");
			
		}
		catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}
	
	@Test
	void sayNewPositionIsInaccessible() {
		try{
			Field field = Map.getInstance().getClass().getDeclaredField("availablePositions");
			field.setAccessible(true);
			@SuppressWarnings("unchecked")
			List<int[]> posList = (List<int[]>) field.get(Map.getInstance());
			
			boolean positionNotFoundInAccessibles = true;
			
			for(int[] position: posList) {
				if(position[0] == newPos[0] && position[1] == newPos[1]) {
					positionNotFoundInAccessibles = false;
					break;
				}
			}
			
			assertTrue(positionNotFoundInAccessibles, "Unit's new position is accessible, though it should not be.");
			
		}
		catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}

}
