/**
 * 
 */
package Militaries.Commander;



import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.IScout;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.Scout;
import MilitaryBaseSimulation.Militaries.Commander.*;

/**
 * @author Bartosz S³omowicz
 *
 */
class ChangeTrustLevelTest {
	
	@Test
	void sayChangedTrustLevelValue() {
		int[] pos = {0,0};
		Scout scout = new Scout(0,pos,0,0, 0, null);
		Commander commander = new Commander(null);
		
		try {
			Method tested = Commander.class.getDeclaredMethod("changeTrustLevel", IScout.class, int.class);
			tested.setAccessible(true);
			tested.invoke(commander, scout, 10);
			
			assertEquals(scout.getTrustLevel(),10);
		}catch(Exception e) {
			fail("Test found and error: " + e.getMessage());
		}
	}

}
