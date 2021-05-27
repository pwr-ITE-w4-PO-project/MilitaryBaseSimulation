/**
 * 
 */
package Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit.DisguisedEnemyUnit;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class TryResetDisguiseTest {

	@Test
	void sayDisguiseIsReset() {
		int[] pos = {0,0};
		DisguisedEnemyUnit unit = new DisguisedEnemyUnit(0,pos, 0);
		try {
			Field iden = DisguisedEnemyUnit.class.getDeclaredField("disguiseResetProbability");
			iden.setAccessible(true);
			iden.set(unit, 100);
			
			Method reset = DisguisedEnemyUnit.class.getDeclaredMethod("tryResetDisguise", null);
			reset.setAccessible(true);
			reset.invoke(unit, null);
			
			assertTrue(unit.getIsIdentified() == false, "Disguised unit did not reset its disguise.");
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}

}
