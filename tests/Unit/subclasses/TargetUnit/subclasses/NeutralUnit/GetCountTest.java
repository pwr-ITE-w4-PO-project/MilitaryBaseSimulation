/**
 * 
 */
package Unit.subclasses.TargetUnit.subclasses.NeutralUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;
import MilitaryBaseSimulation.Militaries.Commander.Commander;
import MilitaryBaseSimulation.Militaries.Headquarters.Headquarters;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class GetCountTest {
	private static List<NeutralUnit> list = new ArrayList<NeutralUnit>();
	
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
			list.add( new NeutralUnit(0, pos) );
		}
		assertTrue(NeutralUnit.getCount() == n, "NeutralUnit instances were badly counted. " + NeutralUnit.getCount());
	}
	
	@AfterAll
	static void clear() {
		for(NeutralUnit u : list) {
			u.getDestroyed();
		}
	}
}
