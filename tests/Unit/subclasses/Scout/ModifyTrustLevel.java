/**
 * 
 */
package Unit.subclasses.Scout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.Scout;

/**
 * @author lenovo
 *
 */
class ModifyTrustLevel {

	@Test
	void sayTrustLevelModifiedCorrectly() {
		int[] pos = {0,0};
		Scout scout = new Scout(0,pos, 0,50,0, null);
		
		scout.modifyTrustLevel(50);
		assertTrue(scout.getTrustLevel() == 100, "Scout's trust level was modified by incorrect value.");
	}

}
