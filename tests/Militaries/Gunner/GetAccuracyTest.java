/**
 * 
 */
package Militaries.Gunner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.Militaries.Gunner.Gunner;

/**
 * @author Bartosz S³omowicz
 *
 */
class GetAccuracyTest {

	@Test
	void sayAccuracyIsCorrect() {
		Gunner gunner = new Gunner(50);
		assertEquals(gunner.getAccuracy(),50);
	}

}
