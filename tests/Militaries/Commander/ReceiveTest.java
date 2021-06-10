/**
 * 
 */
package Militaries.Commander;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.Map.Map;
import MilitaryBaseSimulation.MapUnits.Unit.IUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.Scout;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.TargetUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IIdentified;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;
import MilitaryBaseSimulation.Militaries.Commander.Commander;
import MilitaryBaseSimulation.Militaries.Gunner.Gunner;
import MilitaryBaseSimulation.Militaries.Gunner.IGunner;
import MilitaryBaseSimulation.Militaries.Headquarters.Headquarters;

/**
 * @author Bartosz S³omowicz
 *
 */
class ReceiveTest {

	@Test
	void sayAttackHasBeenCommanded() {
		int posU[] = {0,0};
		int posS[] = {1,1};
		Gunner gunner = new Gunner(100);
		ArrayList<IGunner> gunners = new ArrayList<IGunner>(1);
		gunners.add(gunner);
		Map.getInstance().initializeMap();
		Commander commander = new Commander(gunners);
		IIdentified unit = new NeutralUnit(2,posU);
		Scout scout = new Scout(2, posS, 100, 100, 2, commander);
		try
		{
			Field field = TargetUnit.class.getDeclaredField("identifiedBy");
			Field hq = MilitaryBaseSimulation.class.getDeclaredField("headquarters");
			Field trust = Scout.class.getDeclaredField("trustLevel");
			
			field.setAccessible(true);
			hq.setAccessible(true);
			trust.setAccessible(true);
			
			hq.set(null, new Headquarters(commander));
			field.set(unit, scout);
			Map.getInstance().placeUnitOnMap((NeutralUnit)unit);
			
			commander.receive(ReportInfo.ENEMY, unit);
			IUnit[][] map = Map.getInstance().getMap();
			boolean result = true;
			for(IUnit[] row: map) {
				for(IUnit u : row) {
					if(u == unit) {
						result = false;
						break;
					}
				}
			}
			assertTrue(result);
			
			unit = new NeutralUnit(2,posU);
			field.set(unit, scout);
			Map.getInstance().placeUnitOnMap((NeutralUnit)unit);
			commander.receive(ReportInfo.NEUTRAL, unit);
			result = false;
			for(IUnit[] row: map) {
				for(IUnit u : row) {
					if(u == unit) {
						result = true;
						break;
					}
				}
			}
			assertTrue(result);
			
			trust.set(scout, 0);
			commander.receive(ReportInfo.ENEMY, unit);
			result = false;
			for(IUnit[] row: map) {
				for(IUnit u : row) {
					if(u == unit) {
						result = true;
						break;
					}
				}
			}
			assertTrue(result);
			
			commander.receive(ReportInfo.NEUTRAL, unit);
			result = true;
			for(IUnit[] row: map) {
				for(IUnit u : row) {
					if(u == unit) {
						result = false;
						break;
					}
				}
			}
			assertTrue(result);
			
		}
		catch(Exception e)
		{
			fail("Test found an error: " + e.getMessage());
		}
	}

}
