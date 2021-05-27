/**
 * 
 */
package MilitaryBaseSimulation;
import MilitaryBaseSimulation.Map.Map;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.Scout;

/**
 * @author lenovo
 *
 */
class SetScoutsTest {
	InputStream systemIn = System.in;
	Scanner sc;


	@Test
	void sayAllScoutsOk() {
		
		String testInput = "1\n2\n5\n2\n2\n";
		
		ByteArrayInputStream testInputBytes= new ByteArrayInputStream(testInput.getBytes());
		System.setIn(testInputBytes);
	    sc = new Scanner(System.in);
		
	    Map.getInstance().initializeMap();
	    
	    try {
			Method testedMethod = MilitaryBaseSimulation.class.getDeclaredMethod("setScouts", Scanner.class);
			testedMethod.setAccessible(true);
			ArrayList<Scout> scouts = (ArrayList<Scout>) testedMethod.invoke(null, sc);	
			
			boolean buildResult = true;
			
			if(scouts.size() != 1) buildResult = false;
			
			Scout scout = scouts.get(0);
			
			Field movRange = Scout.class.getSuperclass().getDeclaredField("movementRange");
			movRange.setAccessible(true);
			int movRangeV = (int)movRange.getInt(scout);
			if(movRangeV != 2) buildResult = false;

			Field visRange = Scout.class.getDeclaredField("visionRange");
			visRange.setAccessible(true);
			int visRangeV = (int)visRange.getInt(scout);
			if(visRangeV != 5) buildResult = false;
			
			Field eff = Scout.class.getDeclaredField("effectiveness");
			eff.setAccessible(true);
			int effV = (int)eff.getInt(scout);
			if(effV != 2) buildResult = false;
			
			Field trust = Scout.class.getDeclaredField("trustLevel");
			trust.setAccessible(true);
			int trustV = trust.getInt(scout);
			if(trustV != 2) buildResult = false;
			
			if(!Map.getInstance().isPositionWithinMap(scout.getPosition())) buildResult = false;
			
			assertTrue(buildResult, "Scout was built incorrectly.");
		
	    }catch(Exception e) {
			fail("Test found an arrer :" + e.getMessage());	
		}
	}
	
	 @After
	 public void restoreSystemInputOutput() {
		 System.setIn(systemIn);
		 sc.close();
	 }
}
