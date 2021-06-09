/**
 * 
 */
package Militaries.Commander;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
		commander.changeTrustLevel(scout, 10);
		assertEquals(scout.getTrustLevel(),10);
		
	}

}
