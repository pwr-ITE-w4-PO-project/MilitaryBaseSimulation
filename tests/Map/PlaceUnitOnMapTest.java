package Map;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.NeutralUnit.*;

import java.util.List;

import MilitaryBaseSimulation.Map.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Przemys³aw Ma³ecki
 *
 */

class PlaceUnitOnMapTest {

	@BeforeEach
	void setUp() {
		Map.GetInstance().initializeMap();
	}
	
	@Test
	void sayUnitIsPlaced() {
		int[] pos = {0,98}; 
		NeutralUnit unit = new NeutralUnit(0, pos);
		Map.GetInstance().placeUnitOnMap(unit);
		
		assertTrue(Map.GetInstance().getMap()[pos[0]][pos[1]] == unit, "Unit is placed on the map.");
		assertFalse(!(Map.GetInstance().getMap()[pos[0]][pos[1]] == unit), "Unit should be placed on the map.");
	}
	
	@Test
	void sayPositionIsInaccessible() {
		int[] pos = {0,98}; 
		NeutralUnit unit = new NeutralUnit(0, pos);
		Map.GetInstance().placeUnitOnMap(unit);
		
		try{
			Field field = Map.GetInstance().getClass().getDeclaredField("availablePositions");
			field.setAccessible(true);
			
			List<int[]> posList = (List<int[]>) field.get(Map.GetInstance());
			
			boolean positionNotFoundInAccessibles = true;
			
			for(int[] position: posList) {
				if(position[0] == pos[0] && position[1] == pos[1]) {
					positionNotFoundInAccessibles = false;
					break;
				}
			}
			
			assertTrue(positionNotFoundInAccessibles, "Unit's position is inaccessible.");
			assertFalse(!positionNotFoundInAccessibles, "Unit's position should be inaccessible.");
			
		}
		catch(Exception e) {
			fail("Field avaialblePositions is not found - cannot test functionality.");
		}
	}

}
