/**
 * 
 */
package Unit.subclasses.Scout;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.Scout;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class GetTrustLevelTest {

	@Test
	void sayGotCorrectValue() {
		int[] pos = {0,0};
		Scout scout = new Scout(0,pos,0,50,0);
		
		try {
			Field tl = scout.getClass().getDeclaredField("trustLevel");
			tl.setAccessible(true);
			int trust = tl.getInt(scout);
			
			int tested = scout.getTrustLevel();
			
			assertTrue(trust == tested, "Getter returned incorrect value.");
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}

}
