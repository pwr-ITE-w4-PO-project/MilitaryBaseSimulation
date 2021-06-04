/**
 * 
 */
package Unit.subclasses.Scout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.Scout;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class ModifyTrustLevel {

	@Test
	void sayTrustLevelModifiedCorrectly() {
		int[] pos = {0,0};
		Scout scout = new Scout(0,pos, 0,50,0);
		
		scout.modifyTrustLevel(50);
		assertTrue(scout.getTrustLevel() == 100, "Scout's trust level was modified by incorrect value.");
	}

}
