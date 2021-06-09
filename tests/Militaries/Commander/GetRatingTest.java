/**
 * 
 */
package Militaries.Commander;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.Militaries.Commander.Commander;

/**
 * @author Bartosz S³omowicz
 *
 */
class GetRatingTest {

	@Test
	void test() {
		Commander commander = new Commander(null);
		try
		{
			Field field = commander.getClass().getDeclaredField("rating");
			field.setAccessible(true);
			field.set(commander, 10);
			assertEquals(10, commander.getRating());
		}
		catch(Exception e)
		{
			fail("Test found an error: " + e.getMessage());
		}
	}

}
