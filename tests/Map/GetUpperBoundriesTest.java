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
		int[] pos = Map.GetInstance().getUpperBoundaries();
		
		Field ymax;
		Field xmax;
		int ymaxValue = -1;
		int xmaxValue = -1;
		
		try{
			ymax = Map.GetInstance().getClass().getDeclaredField("yMax"); //z jakiegos powodu nie znajduje pola
			ymax.setAccessible(true);
			ymaxValue = ymax.getInt(ymax);
		}catch(Exception e) {
			fail("yMax field not found.");
		}
		try {
			xmax = Map.GetInstance().getClass().getDeclaredField("xMax"); //z jakiegos powodu nie znajduje pola
			xmax.setAccessible(true);
			xmaxValue = xmax.getInt(xmax);
		}catch(Exception e) {
			fail("xMax field not found.");
		}
		
		assertTrue((xmaxValue == pos[0] && ymaxValue == pos[1]), "Returned correct boundires.");
		assertFalse(!(xmaxValue == pos[0] && ymaxValue == pos[1]), "Should return correct boundires.");
	}

}
