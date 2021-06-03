/**
 * 
 */
package Unit.subclasses.TargetUnit.subclasses.EnemyUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;
import MilitaryBaseSimulation.Militaries.Commander.Commander;
import MilitaryBaseSimulation.Militaries.Headquarters.Headquarters;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class GetCountTest {
	private static List<EnemyUnit> list = new ArrayList<EnemyUnit>();
	
	@BeforeAll
	static void setup() {
		try {
			Field hq = MilitaryBaseSimulation.class.getDeclaredField("headquarters");
			hq.setAccessible(true);
			hq.set(null, new Headquarters());
			
			Field commander = MilitaryBaseSimulation.class.getDeclaredField("commander");
			commander.setAccessible(true);
			commander.set(null, new Commander(null, null));
			
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}
	
	
	@Test
	void sayCorrectCount() {
		int n = 17;
		for(int i = 0; i<n; i++) {
			int[] pos = {0,0};
			list.add( new EnemyUnit(0, pos, 0) );
		}
		assertTrue(EnemyUnit.getCount() == n, "EnemyUnit instances were badly counted." + EnemyUnit.getCount());
	}
	
	@AfterAll
	static void clear() {
		for(EnemyUnit u : list) {
			u.getDestroyed();
		}
	}

}
