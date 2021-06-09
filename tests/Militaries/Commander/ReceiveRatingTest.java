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
class ReceiveRatingTest {

	@Test
	void sayNewValueIsRight() {
		Commander commander = new Commander(null);
		try
		{
			Field field = commander.getClass().getDeclaredField("rating");
			field.setAccessible(true);
			field.set(commander, 10);
			commander.recevieRating(20);
			assertTrue((int)field.get(commander)==30);
		}
		catch(Exception e)
		{
			fail("Test found an error: " + e.getMessage());
		}
	}

}
