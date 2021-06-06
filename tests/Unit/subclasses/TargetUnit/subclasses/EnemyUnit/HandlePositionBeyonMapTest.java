/**
 * 
 */
package Unit.subclasses.TargetUnit.subclasses.EnemyUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.Map.Map;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;
import MilitaryBaseSimulation.Militaries.Headquarters.Headquarters;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class HandlePositionBeyonMapTest {

	@BeforeAll
	static void setup() {
		Map.getInstance().initializeMap();
	}
	

	@Test
	void sayPositionIsWithinMap() {
		int[] pos = {0,0};
		EnemyUnit enemy = new EnemyUnit(1, pos, 0);
		try {
			Method handle = EnemyUnit.class.getDeclaredMethod("handlePositionBeyondMap", pos.getClass());	
			handle.setAccessible(true);
			
			int[] posToHandle = {1, -1};
			int[] newPos = (int[])handle.invoke(enemy, posToHandle);
			
			assertTrue(Map.getInstance().isPositionWithinMap(newPos), "Returned position should be within map.");
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}
	
	@Test
	void sayBaseIsDamaged() {
		int[] pos = {30,0};
		int hp = 100;
		int dmg = 50;
		EnemyUnit enemy = new EnemyUnit(20, pos, dmg);
		try {
			Method handle = EnemyUnit.class.getDeclaredMethod("handlePositionBeyondMap", pos.getClass());	
			handle.setAccessible(true);
			
			Field hq = MilitaryBaseSimulation.class.getDeclaredField("headquarters");
			hq.setAccessible(true);
			hq.set(null, new Headquarters(null));
			
			Field baseHp = MilitaryBaseSimulation.class.getDeclaredField("baseHP");
			baseHp.setAccessible(true);
			baseHp.set(null, hp);
			
			int[] posToHandle = {50, 0};
			
			handle.invoke(enemy, posToHandle);
			
			int hpAfter = baseHp.getInt(null);
			
			assertTrue(hpAfter == hp - dmg, "Base was not damaged.");
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
		
	}

	@Test
	void sayNewPosIsNull() {
		int[] pos = {30,0};
		EnemyUnit enemy = new EnemyUnit(20, pos, 0);
		int[] posToHandle = {50, 0};
		
		try {
			Method handle = EnemyUnit.class.getDeclaredMethod("handlePositionBeyondMap", pos.getClass());	
			handle.setAccessible(true);
			
			Field hq = MilitaryBaseSimulation.class.getDeclaredField("headquarters");
			hq.setAccessible(true);
			hq.set(null, new Headquarters(null));
						
			int[] posAfter = (int[])handle.invoke(enemy, posToHandle);
			
			assertTrue(posAfter == null, "Base was not damaged.");
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}
}
