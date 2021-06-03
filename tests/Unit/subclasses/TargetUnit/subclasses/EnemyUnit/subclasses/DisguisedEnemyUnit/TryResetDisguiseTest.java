/**
 * 
 */
package Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit.DisguisedEnemyUnit;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class TryResetDisguiseTest {

	@BeforeAll
	static void setup() {
		try {
			Field random = MilitaryBaseSimulation.class.getDeclaredField("random");
			random.setAccessible(true);
			random.set(null, new java.util.Random());
			
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}	
	
	
	@Test
	void sayDisguiseIsReset() {
		int[] pos = {0,0};
		DisguisedEnemyUnit unit = new DisguisedEnemyUnit(0,pos, 0);
		try {
			Field iden = DisguisedEnemyUnit.class.getDeclaredField("disguiseResetProbability");
			iden.setAccessible(true);
			iden.set(unit, 100);
			
			Method reset = DisguisedEnemyUnit.class.getDeclaredMethod("tryResetDisguise");
			reset.setAccessible(true);
			reset.invoke(unit);
			
			assertTrue(unit.getIsIdentified() == false, "Disguised unit did not reset its disguise.");
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}

}
