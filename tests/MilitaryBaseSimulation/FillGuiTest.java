/**
 * 
 */
package MilitaryBaseSimulation;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.GUI.GUI;
import MilitaryBaseSimulation.GUI.IGUI;
import MilitaryBaseSimulation.Map.Map;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class FillGuiTest {

	@Test
	void sayGuiReturnedCorrectValues() {
		try {
			Method tested = MilitaryBaseSimulation.class.getDeclaredMethod("fillGui", IGUI.class, int[].class);
			tested.setAccessible(true);
			//1 5 20 50 50 1 100 200 5 3 20
			int[] args = {1, 5, 20, 50, 50, 1, 100, 200, 5, 3, 20};
			
			IGUI xyz = new GUI(Map.getInstance(), false);
	
			Field gui = MilitaryBaseSimulation.class.getDeclaredField("gui");
			gui.setAccessible(true);
			gui.set(null, xyz);
			
			tested.invoke(null, xyz, args);

			boolean result = true;
			
			ArrayList<int[]> scouts = xyz.getScout();
			int scoutsCount = scouts.size();
			if(scoutsCount != args[0]) result = false;
			
			for(int i = 0 ; i < 4; i++) {
				if(scouts.get(0)[i] != args[i+1]) result = false;
			}
			
			List<Integer> gunners = xyz.getGunner();
			int gunnersCount = gunners.size();
			if(gunnersCount != args[5]) result = false;
			
			if(gunners.get(0) != args[6]) result = false;
			
			if(xyz.getBaseHP() != args[7]) result = false;
			
			if(xyz.getEnemy() != args[8]) result = false;
			
			if(xyz.getDisguisedEnemy() != args[9]) result = false;
			
			if(xyz.getIterations() != args[10]) result = false;
			
			assertTrue(result, "Method filled gui incorrectly.");
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}

}
