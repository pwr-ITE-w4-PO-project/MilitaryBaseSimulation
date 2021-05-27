/**
 * 
 */
package Unit.subclasses.TargetUnit.subclasses.EnemyUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;

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
			new EnemyUnit(0, pos, 0);
		}
		assertTrue(EnemyUnit.getCount() == n, "EnemyUnit instances were badly counted.");
	}

}
