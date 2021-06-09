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
class NumberOfScoutsTest {

	@Test
	void sayReturnedCorrectValue() {
		int value = 5;
		GUI tested = new GUI(Map.getInstance(), false);
		tested.setNumberOfScouts(value);
		tested.setParameters();
		int set = tested.getScout().size();
		
		assertTrue(set == value, "GUI mismatched values.");
	}

}
