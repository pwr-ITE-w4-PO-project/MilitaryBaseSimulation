/**
 * 
 */
package Map;
import MilitaryBaseSimulation.Map.*;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class GetUpperBoundriesTest {
	
	@Test
	void sayCorrectBoundries() {
		int[] pos = Map.getInstance().getUpperBoundaries();
		
		Field ymax;
		Field xmax;
		int ymaxValue = -1;
		int xmaxValue = -1;
		
		try{
			ymax = Map.getInstance().getClass().getDeclaredField("yMax");
			ymax.setAccessible(true);
			ymaxValue = ymax.getInt(Map.getInstance());
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
		try {
			xmax = Map.getInstance().getClass().getDeclaredField("xMax");
			xmax.setAccessible(true);
			xmaxValue = xmax.getInt(Map.getInstance());
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
		
		assertTrue((xmaxValue == pos[0] && ymaxValue == pos[1]), "Returned incorrect boundires.");
	}

}
