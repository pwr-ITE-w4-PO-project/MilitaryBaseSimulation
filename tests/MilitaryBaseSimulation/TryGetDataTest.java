package MilitaryBaseSimulation;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Przemys³aw Ma³ecki
 *
 */
class TryGetDataTest {
	
	@Test
	void say1returned() {
		try {
			Method tested = MilitaryBaseSimulation.class.getDeclaredMethod("tryGetData", int.class, int[].class);
			tested.setAccessible(true);
			
			int[] args = {1,2,3};
			
			tested.invoke(tested, 1, args);
			
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}
	
	@Test
	void say2returned() {
		try {
			Method tested = MilitaryBaseSimulation.class.getDeclaredMethod("tryGetData", int.class, int[].class);
			tested.setAccessible(true);
			
			int[] args = {1,2,3};
			
			tested.invoke(tested, 2, args);
			
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}

	@Test
	void say3returned() {
		try {
			Method tested = MilitaryBaseSimulation.class.getDeclaredMethod("tryGetData", int.class, int[].class);
			tested.setAccessible(true);
			
			int[] args = {1,2,3};
			
			tested.invoke(tested, 3, args);
			
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}

	@Test
	void say0returned() {
		try {
			Method tested = MilitaryBaseSimulation.class.getDeclaredMethod("tryGetData", int.class, int[].class);
			tested.setAccessible(true);
			
			int[] args = {1,2,3};
			
			tested.invoke(tested, 4, args);
			
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}
}
