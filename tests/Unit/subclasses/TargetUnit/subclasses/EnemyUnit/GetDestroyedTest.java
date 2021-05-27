/**
 * 
 */
package Unit.subclasses.TargetUnit.subclasses.EnemyUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.Map.Map;
import MilitaryBaseSimulation.MapUnits.Unit.IUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class GetDestroyedTest {

	@Test
	void sayCountDecreased() {
		int n = 17;
		EnemyUnit[] units = new EnemyUnit[n];
		for(int i =0; i<n;i++) {
			int[] pos = {i,i};
			units[i] = new EnemyUnit(0, pos, 0);
		}
		
		units[0].getDestroyed();
		
		assertTrue(EnemyUnit.getCount() == n-1, "Enemy units count did not decrease.");
	}
	
	@Test
	void sayUnitRemovedFromMap() {
		int[] pos = {0,0};
		EnemyUnit unit = new EnemyUnit(0, pos, 0);
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
		assertTrue(result, "Enemy was not removed from the map.");
	}
}
