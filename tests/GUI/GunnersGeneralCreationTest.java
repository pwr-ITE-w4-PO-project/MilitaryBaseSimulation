/**
 * 
 */
package GUI;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.GUI.GUI;
import MilitaryBaseSimulation.Map.Map;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class GunnersGeneralCreationTest {

	@Test
	void sayReturnedCorrectArray() {
		int count = 4;
		GUI tested = new GUI(Map.getInstance(), false);
		tested.setNumberOfGunners(count);
		for(int i = 0; i < count; i++) {
			tested.setGunner(i, i);
		}
	
		List<Integer> output = tested.getGunner();
		boolean result = true;
		
		int set = output.size();
		for(int i = 0; i < count; i++) {
			if(output.get(i) != i) result = false;
		}
		
		if(set != count) result = false;
		
		assertTrue(result, "GUI mismatched gunners values.");
	}

}
