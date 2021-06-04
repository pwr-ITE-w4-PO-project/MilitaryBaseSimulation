/**
 * 
 */
package Unit.subclasses.Scout;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.Map.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.Scout;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit.DisguisedEnemyUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;
import MilitaryBaseSimulation.Militaries.Commander.Commander;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class SearchTest {

	@BeforeAll
	static void setup() {
		try {
			Field commander = MilitaryBaseSimulation.class.getDeclaredField("commander");
			commander.setAccessible(true);
			commander.set(null, new Commander(null, null));
			
			
			
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}	
	
	@Test
	void sayNeutralWasIdentifiedCorrectly() {
		int[] npos = {0,0};
		NeutralUnit neutral = new NeutralUnit(0, npos);
		Map.getInstance().initializeMap();
		Map.getInstance().placeUnitOnMap(neutral);
		
		int[] pos = {1,1};
		Scout scout = new Scout(0, pos, 100, 0, 1);
		
		scout.move(); //move method invokes search method
		
		boolean result = true;
		
		if(neutral.getIsIdentified() == false) result = false;
		if(neutral.getIsCorrectlyIdentified() == false) result = false;
		if(neutral.getIdentifiedBy() != scout) result = false;
		
		assertTrue(result, "Scout identified neutral unit incorrectly.");
	}
	
	@Test
	void sayNeutralWasIdentifiedIncorrectly(){
		int[] npos = {0,0};
		NeutralUnit neutral = new NeutralUnit(0, npos);
		Map.getInstance().initializeMap();
		Map.getInstance().placeUnitOnMap(neutral);
		
		int[] pos = {1,1};
		Scout scout = new Scout(0, pos, 0, 0, 1);
		
		scout.move(); //move method invokes search method
		
		boolean result = true;
		
		if(neutral.getIsIdentified() == false) result = false;
		if(neutral.getIsCorrectlyIdentified() == true) result = false;
		if(neutral.getIdentifiedBy() != scout) result = false;
		
		assertTrue(result, "Scout identified neutral unit correctly, which is wrong in this test.");
	}
	
	@Test
	void sayDisguisedWasIdentifiedCorrectly() {
		int[] npos = {0,0};
		DisguisedEnemyUnit disguised = new DisguisedEnemyUnit(0, npos, 0);
		Map.getInstance().initializeMap();
		Map.getInstance().placeUnitOnMap(disguised);
		
		int[] pos = {1,1};
		Scout scout = new Scout(0, pos, 100, 0, 1);
		
		scout.move(); //move method invokes search method
		
		boolean result = true;
		
		if(disguised.getIsIdentified() == false) result = false;
		if(disguised.getIsCorrectlyIdentified() == false) result = false;
		if(disguised.getIdentifiedBy() != scout) result = false;
		
		assertTrue(result, "Scout identified disguised enemy unit unit incorrectly.");
	}
	
	@Test
	void sayDisguisedWasIdentifiedIncorrectly(){
		int[] npos = {0,0};
		DisguisedEnemyUnit disguised = new DisguisedEnemyUnit(0, npos, 0);
		Map.getInstance().initializeMap();
		Map.getInstance().placeUnitOnMap(disguised);
		
		int[] pos = {1,1};
		Scout scout = new Scout(0, pos, 0, 0, 1);
		
		scout.move(); //move method invokes search method
		
		boolean result = true;
		
		if(disguised.getIsIdentified() == false) result = false;
		if(disguised.getIsCorrectlyIdentified() == true) result = false;
		if(disguised.getIdentifiedBy() != scout) result = false;
		
		assertTrue(result, "Scout identified disguised enemy unit correctly, which is wrong in this test.");
	}
	
	@Test
	void sayEnemyWasIdentifiedCorrectly() {
		int[] npos = {0,0};
		EnemyUnit enemy = new EnemyUnit(0, npos, 0);
		Map.getInstance().initializeMap();
		Map.getInstance().placeUnitOnMap(enemy);
		
		int[] pos = {1,1};
		Scout scout = new Scout(0, pos, 50, 0, 1);
		
		scout.move(); //move method invokes search method
		
		boolean result = true;
		
		if(enemy.getIsIdentified() == false) result = false;
		if(enemy.getIsCorrectlyIdentified() == false) result = false;
		if(enemy.getIdentifiedBy() != scout) result = false;
		
		assertTrue(result, "Scout identified enemy unit unit incorrectly.");
	}

}
