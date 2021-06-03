/**
 * 
 */
package Unit.subclasses.TargetUnit.subclasses.NeutralUnit;
import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.Map.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
	private static List<NeutralUnit> list = new ArrayList<NeutralUnit>();
	
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
			
			//below is used to clear counted instances from other tests
			Field count = NeutralUnit.class.getDeclaredField("count");
			count.setAccessible(true);
			count.set(null, 0);
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}
	
	@Test
	void sayCountDecreased() {
		int n = 17;
		for(int i =0; i<n;i++) {
			int[] pos = {i,i};
			list.add(new NeutralUnit(0, pos));
		}
		
		list.get(0).getDestroyed();
		list.remove(0);
		
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
