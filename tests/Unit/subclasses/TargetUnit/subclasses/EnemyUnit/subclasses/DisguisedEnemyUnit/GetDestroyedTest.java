/**
 * 
 */
package Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.Map.Map;
import MilitaryBaseSimulation.MapUnits.Unit.IUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit.DisguisedEnemyUnit;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class GetDestroyedTest {

	@Test
	void sayCountDecreased() {
		int n = 17;
		DisguisedEnemyUnit[] units = new DisguisedEnemyUnit[n];
		for(int i =0; i<n;i++) {
			int[] pos = {i,i};
			units[i] = new DisguisedEnemyUnit(0, pos, 0);
		}
		
		units[0].getDestroyed();
		
		assertTrue(DisguisedEnemyUnit.getCount() == n-1, "Disguised enemy units count did not decrease.");
	}
	
	@Test
	void sayUnitRemovedFromMap() {
		int[] pos = {0,0};
		DisguisedEnemyUnit unit = new DisguisedEnemyUnit(0, pos, 0);
		Map.getInstance().placeUnitOnMap(unit);
		
		unit.getDestroyed();
		
		boolean result = true;
		
		IUnit[][] map = Map.getInstance().getMap();
		for(IUnit[] row:map) {
			for(IUnit u :row) {
				if(u == unit) {
					result = false;
					break;
				}
			}
		}
		assertTrue(result, "Disguised enemy unit was not removed from the map.");
	}

}
