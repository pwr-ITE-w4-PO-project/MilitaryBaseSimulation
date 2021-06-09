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
class DisguisedEnemyInputTest {

	@Test
	void sayReturnedCorrectValue() {
		int value = 2;
		GUI tested = new GUI(Map.getInstance(), false);
		tested.setDisguisedEnemy(value);
		int set = tested.getDisguisedEnemy();
		
		assertTrue(set == value, "GUI mismatched values.");
	}

}
