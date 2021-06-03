/**
 * 
 */
package Unit;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MapUnits.Unit.IUnit;
import MilitaryBaseSimulation.MapUnits.Unit.Unit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;
import MilitaryBaseSimulation.Map.*;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class DissapearFromMapTest {

	@Test
	void sayUnitDisappeared() {
		
		int[] pos = {0,0};
		
		Unit unit = new NeutralUnit(0, pos);
		Map.getInstance().initializeMap();
		try {
			Method tested = Unit.class.getDeclaredMethod("disappearFromMap");
			tested.setAccessible(true);
			
			tested.invoke(unit);
			
			IUnit[][] map = Map.getInstance().getMap();
			boolean result = true;
			for(IUnit[] row: map) {
				for(IUnit u : row) {
					if(u == unit) {
						result = false;
						break;
					}
				}
			}
			
			assertTrue(result, "Unit should disappear from map.");
		}catch(Exception e) {
			fail("Test found an error " + e.getMessage());
		}
	}

}
