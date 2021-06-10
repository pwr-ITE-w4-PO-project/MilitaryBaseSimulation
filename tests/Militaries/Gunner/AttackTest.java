/**
 * 
 */
package Militaries.Gunner;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.Map.Map;
import MilitaryBaseSimulation.MapUnits.Unit.IUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IDestroyable;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;
import MilitaryBaseSimulation.Militaries.Gunner.Gunner;
import MilitaryBaseSimulation.Militaries.Headquarters.Headquarters;

/**
 * @author Bartosz S³omowicz
 */
class AttackTest {

	@Test
	void sayUnitHasBeenAttacked() {
		int pos[] = {0,0};
		Gunner gunner = new Gunner(0);
		IDestroyable unit = new EnemyUnit(2,pos,2);
		Map.getInstance().initializeMap();
		try
		{
			Method method = Gunner.class.getDeclaredMethod("attack", IDestroyable.class);
			Field field = Gunner.class.getDeclaredField("accuracy");
			Field hq = MilitaryBaseSimulation.class.getDeclaredField("headquarters");
			
			method.setAccessible(true);
			field.setAccessible(true);
			hq.setAccessible(true);
			
			hq.set(null, new Headquarters(null));
			Map.getInstance().placeUnitOnMap((EnemyUnit)unit);
			
			method.invoke(gunner, unit);
			IUnit[][] map = Map.getInstance().getMap();
			boolean result = false;
			for(IUnit[] row: map) {
				for(IUnit u : row) {
					if(u == unit) {
						result = true;
						break;
					}
				}
			}
			assertTrue(result);
			
			field.set(gunner, 100);
			method.invoke(gunner, unit);
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
