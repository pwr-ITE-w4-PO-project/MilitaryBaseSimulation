/**
 * 
 */
package GUI;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.GUI.GUI;
import MilitaryBaseSimulation.Map.*;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class NumberOfGunnersInputTest {

	@Test
	void sayReturnedCorrectValue() {
		int value = 4;
		GUI tested = new GUI(Map.getInstance(), false);
		tested.setNumberOfGunners(value);
		int set = tested.getGunner().size();
		
		assertTrue(set == value, "GUI mismatched values.");
	}
}
