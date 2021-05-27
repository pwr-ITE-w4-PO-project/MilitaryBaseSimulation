/**
 * 
 */
package Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit.DisguisedEnemyUnit;

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
			new DisguisedEnemyUnit(0, pos, 0);
		}
		assertTrue(DisguisedEnemyUnit.getCount() == n, "DisguisedEnemyUnit instances were badly counted.");
	}

}
