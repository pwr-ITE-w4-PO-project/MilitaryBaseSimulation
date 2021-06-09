/**
 * 
 */
package Militaries.Commander;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.Map.Map;
import MilitaryBaseSimulation.MapUnits.Unit.IUnit;
import MilitaryBaseSimulation.MapUnits.Unit.Unit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IDestroyable;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;
import MilitaryBaseSimulation.Militaries.Commander.Commander;
import MilitaryBaseSimulation.Militaries.Gunner.Gunner;
import MilitaryBaseSimulation.Militaries.Gunner.IGunner;
import MilitaryBaseSimulation.Militaries.Headquarters.Headquarters;

/**
 * @author Bartosz S³omowicz
 *
 */
class CommandAttackTest {

	@Test
	void sayUnitGotDestroyed() {
		int[] pos = {0,0};
		EnemyUnit unit = new EnemyUnit(3, pos, 5);
		Map.getInstance().initializeMap();
		Gunner gunner = new Gunner(100);
		ArrayList<IGunner> gunners = new ArrayList<IGunner>(1);
		gunners.add(gunner);
		Commander commander = new Commander(gunners);
		try
		{
			Method command = commander.getClass().getDeclaredMethod("commandAttack", IDestroyable.class);
			Field field = MilitaryBaseSimulation.class.getDeclaredField("headquarters");
			field.setAccessible(true);
			field.set(null, new Headquarters(commander));
			Method tested = Unit.class.getDeclaredMethod("disappearFromMap");
			command.setAccessible(true);
			tested.setAccessible(true);
			command.invoke(commander,unit);
			tested.invoke(unit);
			
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
			
			assertTrue(result, "Unit should disappear from map.");
		}
		catch(Exception e)
		{
			fail("Test found an error: " + e.getMessage());
		}
	}

}
