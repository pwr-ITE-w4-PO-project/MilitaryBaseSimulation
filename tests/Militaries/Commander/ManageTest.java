/**
 * 
 */
package Militaries.Commander;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.Map.Map;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.Scout;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.TargetUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;
import MilitaryBaseSimulation.Militaries.Commander.Commander;

/**
 * @author Bartosz S³omowicz
 *
 */
class ManageTest {

	@Test
	void sayReportHasBeenSent() {
		int[] posCE = {0,0};
		int[] posFE = {1,0};
		int[] posCN = {0,1};
		int[] posFN = {1,1};
		int[] posS = {2,2};
		EnemyUnit correctEnemy = new EnemyUnit(3, posCE, 5);
		EnemyUnit falseEnemy = new EnemyUnit(3, posFE, 5);
		NeutralUnit correctNeutral = new NeutralUnit(2,posCN);
		NeutralUnit falseNeutral = new NeutralUnit(2,posFN);
		Commander commander = new Commander(null);
		Scout scout = new Scout(2, posS, 100, 50, 2, commander);
		Map.getInstance().initializeMap();
		
		try
		{
			Field field = TargetUnit.class.getDeclaredField("identifiedBy");
			Field correctIdentification = TargetUnit.class.getDeclaredField("isCorrectlyIdentified");
			Field trust = Scout.class.getDeclaredField("trustLevel");
			
			field.setAccessible(true);
			correctIdentification.setAccessible(true);
			trust.setAccessible(true);
			
			field.set(correctEnemy, scout);
			correctIdentification.set(correctEnemy, true);
			
			field.set(falseEnemy, scout);
			
			field.set(correctNeutral, scout);
			correctIdentification.set(correctNeutral, true);
			
			field.set(falseNeutral, scout);
			
			commander.manage(ReportInfo.POSITIVE, correctEnemy);
			assertTrue(scout.getTrustLevel()>50);
			trust.set(scout, 50);
			
			commander.manage(ReportInfo.POSITIVE, falseEnemy);
			assertTrue(scout.getTrustLevel()<50);
			trust.set(scout, 50);
			
			commander.manage(ReportInfo.NEGATIVE, correctNeutral);
			assertTrue(scout.getTrustLevel()>50);
			trust.set(scout, 50);
			
			commander.manage(ReportInfo.NEGATIVE, falseNeutral);
			assertTrue(scout.getTrustLevel()<50);
		}
		catch(Exception e)
		{
			fail("Test found an error: " + e.getMessage());
		}
	}

}
