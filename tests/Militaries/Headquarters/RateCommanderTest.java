/**
 * 
 */
package Militaries.Headquarters;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.Militaries.Commander.Commander;
import MilitaryBaseSimulation.Militaries.Headquarters.Headquarters;

/**
 * @author Bartosz S³omowicz
 *
 */
class RateCommanderTest {

	@Test
	void sayRatingHasChanged() {
		
		Commander commander = new Commander(null);
		Headquarters hq = new Headquarters(commander);

		try
		{
			Method method = Headquarters.class.getDeclaredMethod("rateCommander", int.class);
			Field field = Commander.class.getDeclaredField("rating");
			
			method.setAccessible(true);
			field.setAccessible(true);
			
			field.set(commander, 20);
			method.invoke(hq, 30);
			assertTrue(commander.getRating()==50);
			
		}
		catch(Exception e)
		{
			fail("Test found an error: " + e.getMessage());
		}
	}

}
