/**
 * 
 */
package Unit;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.Map.*;
import MilitaryBaseSimulation.MapUnits.Unit.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;
import MilitaryBaseSimulation.Militaries.Commander.Commander;
import MilitaryBaseSimulation.Militaries.Headquarters.Headquarters;
/**
 * @author Przemys³aw Ma³ecki
 *
 */
class MoveTest {
	
	@BeforeAll
	static void setup() {
		try {
			Field hq = MilitaryBaseSimulation.class.getDeclaredField("headquarters");
			hq.setAccessible(true);
			hq.set(null, new Headquarters(null));
			
			Field commander = MilitaryBaseSimulation.class.getDeclaredField("commander");
			commander.setAccessible(true);
			commander.set(null, new Commander(null));
			
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}
	
	@Test
	void sayUnitMovedWithinMap() {
		Map.getInstance().initializeMap();
		int[] posInit = {10,10};
		
		Unit unit = new NeutralUnit(1, posInit);
		
		unit.move();
		
		assertTrue(Map.getInstance().isPositionWithinMap(unit.getPosition()), "Unit should be within map.");
	}
	
	@Test
	void sayUnitMovedToCorrectPosition() {
		Map.getInstance().initializeMap();
		int[] posInit = new int[2];
		Unit unit;
		for(int i = 9; i<12; i++) {
			for(int j = 9; j<12;j++) {
				if((i != 10 || j != 10) && (i != 11 || j != 11)) {
					int[] pos = {i,j};
					unit = new NeutralUnit(0, pos);
					
					Map.getInstance().placeUnitOnMap(unit);
				}
			}
		}
		
		posInit[0] = 10;
		posInit[1] = 10;
		unit = new NeutralUnit(1, posInit);
		Map.getInstance().placeUnitOnMap(unit);
		
		unit.move();
		
		int[] newPos = unit.getPosition();
		
		assertTrue(newPos[0] == 11 && newPos[1] == 11, "Unit should be at coordinates: 11,11.");
	}
}
