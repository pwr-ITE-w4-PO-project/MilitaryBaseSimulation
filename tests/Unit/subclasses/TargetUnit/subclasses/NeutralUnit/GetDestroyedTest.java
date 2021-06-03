/**
 * 
 */
package Unit.subclasses.TargetUnit.subclasses.NeutralUnit;
import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.Map.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MapUnits.Unit.IUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;
import MilitaryBaseSimulation.Militaries.Commander.Commander;
import MilitaryBaseSimulation.Militaries.Headquarters.Headquarters;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class GetDestroyedTest {
	@BeforeAll
	static void setup() {
		Map.getInstance().initializeMap();
		try {
			Field hq = MilitaryBaseSimulation.class.getDeclaredField("headquarters");
			hq.setAccessible(true);
			hq.set(null, new Headquarters());
			
			Field commander = MilitaryBaseSimulation.class.getDeclaredField("commander");
			commander.setAccessible(true);
			commander.set(null, new Commander(null, null));
			
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}
	
	@Test
	void sayCountDecreased() {
		int n = 17;
		NeutralUnit[] units = new NeutralUnit[n];
		for(int i =0; i<n;i++) {
			int[] pos = {i,i};
			units[i] = new NeutralUnit(0, pos);
		}
		
		units[0].getDestroyed();
		
		assertTrue(NeutralUnit.getCount() == n-1, "Neutral units count did not decrease.");
	}
	
	@Test
	void sayUnitRemovedFromMap() {
		int[] pos = {0,0};
		NeutralUnit unit = new NeutralUnit(0, pos);
		Map.getInstance().placeUnitOnMap(unit);
		
		unit.getDestroyed();
		
		boolean result = true;
		
		IUnit[][] map = Map.getInstance().getMap();
		for(IUnit[] row:map) {
			for(IUnit u :row) {
				if(u == unit) {
					result = false;
					break;
				}
			}
		}
		assertTrue(result, "NeutralUnit was not removed from the map.");
	}

}
