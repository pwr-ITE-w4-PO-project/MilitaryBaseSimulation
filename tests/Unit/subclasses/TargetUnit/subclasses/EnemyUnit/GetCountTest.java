/**
 * 
 */
package Unit.subclasses.TargetUnit.subclasses.EnemyUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class GetCountTest {
	@Test
	void sayCorrectCount() {
		try{//below is used to clear counted instances from other tests
			Field count = EnemyUnit.class.getDeclaredField("count");
			count.setAccessible(true);
			count.set(null, 0);
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
		
		int n = 17;
		for(int i = 0; i<n; i++) {
			int[] pos = {0,0};
			new EnemyUnit(0, pos, 0);
		}
		assertTrue(EnemyUnit.getCount() == n, "EnemyUnit instances were badly counted." + EnemyUnit.getCount());
	}
}
