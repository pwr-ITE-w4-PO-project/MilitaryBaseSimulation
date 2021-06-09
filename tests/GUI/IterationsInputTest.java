/**
 * 
 */
package GUI;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.GUI.GUI;
import MilitaryBaseSimulation.Map.Map;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class IterationsInputTest {

	@Test
	void sayReturnedCorrectValue() {
		int value = 10;
		GUI tested = new GUI(Map.getInstance(), false);
		tested.setIterations(value);
		int set = tested.getIterations();
		
		assertTrue(set == value, "GUI mismatched values.");
	}

}
