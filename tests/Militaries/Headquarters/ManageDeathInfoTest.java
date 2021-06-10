/**
 * 
 */
package Militaries.Headquarters;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IIdentified;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;
import MilitaryBaseSimulation.Militaries.Commander.Commander;
import MilitaryBaseSimulation.Militaries.Headquarters.Headquarters;

/**
 * @author Bartosz S³omowicz
 *
 */
class ManageDeathInfoTest {

	@Test
	void test() {
		int posE[] = {0,0};
		int posN[] = {1,1};
		Commander commander = new Commander(null);
		Headquarters hq = new Headquarters(commander);
		IIdentified eUnit = new EnemyUnit(2,posE,2);
		IIdentified nUnit = new NeutralUnit(2,posN);
		
		try
		{
			Field field = Commander.class.getDeclaredField("rating");
			field.setAccessible(true);
			field.set(commander, 40);
			
			hq.manageDeathInfo(nUnit);
			assertTrue(commander.getRating()<40);
			
			field.set(commander, 40);
			hq.manageDeathInfo(eUnit);
			assertTrue(commander.getRating()>40);
			
		}
		catch(Exception e)
		{
			fail("Test found an error: " + e.getMessage());
		}
		
		
	}

}
