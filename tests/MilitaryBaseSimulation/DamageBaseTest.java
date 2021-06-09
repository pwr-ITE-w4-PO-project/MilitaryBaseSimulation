package MilitaryBaseSimulation;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Przemys³aw Ma³ecki
 *
 */
class DamageBaseTest {

	@Test
	void sayBaseWasDamaged() {
		try {
			int initial = 100;
			int dmg = 50;
			
			Field hp = MilitaryBaseSimulation.class.getDeclaredField("baseHP");
			hp.setAccessible(true);
			hp.set(null, initial);
			
			MilitaryBaseSimulation.damageBase(dmg);
			int after = hp.getInt(null);
			
			assertTrue(after == initial - dmg, "Base wasn't damaged.");
			
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}

}
