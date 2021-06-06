/**
 * 
 */
package Unit;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MapUnits.Unit.Unit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class GetCountTest {
	
	@Test
	void sayCorrectCount() {
		try{//below is used to clear counted instances from other tests
			Field count = Unit.class.getDeclaredField("count");
			count.setAccessible(true);
			count.set(null, 0);
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
		
		int n = 17;
		for(int i = 0; i<n; i++) {
			int[] pos = {0,0};
			new NeutralUnit(0, pos);
		}
		assertTrue(Unit.getCount() == n, "Unit instances were badly counted." + Unit.getCount());
	}
}
