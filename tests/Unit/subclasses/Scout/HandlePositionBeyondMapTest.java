/**
 * 
 */
package Unit.subclasses.Scout;
import MilitaryBaseSimulation.Map.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.Scout;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class HandlePositionBeyondMapTest {

	@Test
	void sayPositionIsWithinMap() {
		int[] pos = {0,0};
		Scout scout = new Scout(1, pos, 0,0,0);
		try {
			Method handle = Scout.class.getDeclaredMethod("handlePositionBeyondMap", pos.getClass());	
			handle.setAccessible(true);
			
			int[] posToHandle = {-1, -1};
			int[] newPos = (int[])handle.invoke(scout, posToHandle);
			
			assertTrue(Map.getInstance().isPositionWithinMap(newPos), "Returned position should be within map.");
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}

}
