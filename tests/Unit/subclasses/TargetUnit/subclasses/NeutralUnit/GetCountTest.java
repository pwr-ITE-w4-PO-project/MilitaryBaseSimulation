/**
 * 
 */
package Unit.subclasses.TargetUnit.subclasses.NeutralUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
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

	@BeforeAll
	static void setup() {
		try {
			Field hq = MilitaryBaseSimulation.class.getDeclaredField("headquarters");
			hq.setAccessible(true);
			hq.set(null, new Headquarters(null));
			
			Field commander = MilitaryBaseSimulation.class.getDeclaredField("commander");
			commander.setAccessible(true);
			commander.set(null, new Commander(null, null));
			
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}
	
	@Test
	void sayCorrectCount() {
		try{//below is used to clear counted instances from other tests
			Field count = NeutralUnit.class.getDeclaredField("count");
			count.setAccessible(true);
			count.set(null, 0);
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
		
		int n = 17;
		for(int i = 0; i<n; i++) {
			int[] pos = {0,0};
			new NeutralUnit(0, pos);
		}
		assertTrue(NeutralUnit.getCount() == n, "NeutralUnit instances were badly counted. " + NeutralUnit.getCount());
	}
}
