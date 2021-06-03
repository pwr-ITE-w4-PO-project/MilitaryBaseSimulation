package Map;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;
import MilitaryBaseSimulation.Militaries.Commander.Commander;
import MilitaryBaseSimulation.Militaries.Headquarters.Headquarters;

import java.util.List;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.Map.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeAll;
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
		Map.getInstance().initializeMap();
	}
	
	@Test
	void sayUnitIsPlaced() {
		int[] pos = {0,48}; 
		NeutralUnit unit = new NeutralUnit(0, pos);
		Map.getInstance().placeUnitOnMap(unit);
		
		boolean result = Map.getInstance().getMap()[pos[0]][pos[1]] == unit;

		assertTrue(result, "Unit wasn't placed on the map.");
	}
	
	@Test
	void sayPositionIsInaccessible() {
		int[] pos = {0,48}; 
		NeutralUnit unit = new NeutralUnit(0, pos);
		Map.getInstance().placeUnitOnMap(unit);
		
		try{
			Field field = Map.getInstance().getClass().getDeclaredField("availablePositions");
			field.setAccessible(true);
			@SuppressWarnings("unchecked")
			List<int[]> posList = (List<int[]>) field.get(Map.getInstance());
			
			boolean positionNotFoundInAccessibles = true;
			
			for(int[] position: posList) {
				if(position[0] == pos[0] && position[1] == pos[1]) {
					positionNotFoundInAccessibles = false;
					break;
				}
			}
			assertTrue(positionNotFoundInAccessibles, "Unit's position is accessible, though it should be inaccessible.");
		}
		catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}

}
