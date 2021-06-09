/**
 * 
 */
package GUI;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.GUI.GUI;
import MilitaryBaseSimulation.Map.Map;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class ScoutsGeneralCreationTest {

	@Test
	void sayReturnedCorrectArrays() {
		int count = 2;
		GUI tested = new GUI(Map.getInstance(), false);
		tested.setNumberOfScouts(count);
		
		tested.setParameters();
		
		for(int i = 0; i < count; i++) {
			tested.setScout(i, i, i, i, i);
		}
	
		List<int[]> output = tested.getScout();
		boolean result = true;
		
		int set = output.size();
		for(int i = 0; i < count; i++) {
			
			try {
				for(int j = 0; j < 4; j++) {
					if(output.get(i)[j] != i) result = false;
				}
			}catch(Exception e) {
				fail("GUI failed to load integers successfully.");
			}
		}
		
		if(set != count) result = false;
		
		assertTrue(result, "GUI mismatched gunners values.");
	}

}
