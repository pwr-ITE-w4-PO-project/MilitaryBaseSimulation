package GUI;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.GUI.GUI;
import MilitaryBaseSimulation.Map.Map;
/**
 * 
 * @author Przemys³aw Ma³ecki
 *
 */
class BaseHpInputTest {

	@Test
	void sayReturnedCorrectValue() {
		int value = 100;
		GUI tested = new GUI(Map.getInstance(), false);
		tested.setBaseHP(value);
		int set = tested.getBaseHP();
		
		assertTrue(set == value, "GUI mismatched values.");
	}

}
