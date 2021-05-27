/**
 * 
 */
package Unit.subclasses.TargetUnit.subclasses.NeutralUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.*;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class HandlePositionBeyondMap {

	@Test
	void sayNullIsReturned() {
		int[] pos = {0,0};
		NeutralUnit scout = new NeutralUnit(1, pos);
		try {
			Method handle = NeutralUnit.class.getDeclaredMethod("handlePositionBeyondMap", pos.getClass());	
			handle.setAccessible(true);
			
			int[] posToHandle = {-1, -1};
			int[] newPos = (int[])handle.invoke(scout, posToHandle);
			
			assertTrue(newPos == null, "Returned position should be null.");
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}

}
