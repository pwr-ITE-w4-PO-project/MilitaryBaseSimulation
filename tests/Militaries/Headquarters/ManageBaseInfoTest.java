/**
 * 
 */
package Militaries.Headquarters;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IIdentified;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;
import MilitaryBaseSimulation.Militaries.Commander.Commander;
import MilitaryBaseSimulation.Militaries.Headquarters.Headquarters;

/**
 * @author Bartosz S³omowicz
 *
 */
class ManageBaseInfoTest {

	@Test
	void sayBaseHasBeenAttacked() {
		int pos[] = {0,0};
		Commander commander = new Commander(null);
		Headquarters hq = new Headquarters(commander);
		IIdentified unit = new EnemyUnit(2,pos,2);
		try
		{
			Field field = Commander.class.getDeclaredField("rating");
			field.setAccessible(true);
			field.set(commander, 40);
			
			hq.manageBaseAttack(unit);
			
			assertTrue(commander.getRating()<40);
		}
		catch(Exception e)
		{
			fail("Test found an error: " + e.getMessage());
		}
	}

}
