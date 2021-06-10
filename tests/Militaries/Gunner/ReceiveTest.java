/**
 * 
 */
package Militaries.Gunner;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.Map.Map;
import MilitaryBaseSimulation.MapUnits.Unit.IUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IDestroyable;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;
import MilitaryBaseSimulation.Militaries.Gunner.Gunner;
import MilitaryBaseSimulation.Militaries.Headquarters.Headquarters;

/**
 * @author Bartosz S³omowicz
 *
 */
class ReceiveTest {

	@Test
	void sayReceivedReport() {
		int pos[] = {0,0};
		Gunner gunner = new Gunner(100);
		IDestroyable unit = new EnemyUnit(2,pos,2);
		Map.getInstance().initializeMap();
		
		try
		{
			Field hq = MilitaryBaseSimulation.class.getDeclaredField("headquarters");
			Field acc = Gunner.class.getDeclaredField("accuracy");
					
			hq.setAccessible(true);
			acc.setAccessible(true);
			
			hq.set(null, new Headquarters(null));
			
			gunner.receive(ReportInfo.ATTACK, unit);
			
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
			
			unit = new EnemyUnit(2,pos,2);
			Map.getInstance().placeUnitOnMap((EnemyUnit)unit);
			acc.set(gunner, 0);
			gunner.receive(ReportInfo.ATTACK, unit);
			
			map = Map.getInstance().getMap();
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
			
		}
		catch(Exception e)
		{
			fail("Test found an error: " + e.getMessage());
		}
	}

}
