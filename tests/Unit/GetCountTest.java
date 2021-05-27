/**
 * 
 */
package Unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MapUnits.Unit.Unit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class GetCountTest {

	@Test
	void sayCorrectCount() {
		int n = 17;
		for(int i = 0; i<n; i++) {
			int[] pos = {0,0};
			new NeutralUnit(0, pos);
		}
		assertTrue(Unit.getCount() == n, "Unit instances were badly counted.");
	}

}
