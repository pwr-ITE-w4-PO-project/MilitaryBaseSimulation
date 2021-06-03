/**
 * 
 */
package MilitaryBaseSimulation;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.Militaries.Gunner.Gunner;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class SetGunnersTest {
	InputStream systemIn = System.in;
	Scanner sc;


	@Test
	void sayGunnerIsOk() {
		
		String testInput = "1\n1\n";
		
		ByteArrayInputStream testInputBytes= new ByteArrayInputStream(testInput.getBytes());
		System.setIn(testInputBytes);
	    sc = new Scanner(System.in);
		
	    try {
			Method testedMethod = MilitaryBaseSimulation.class.getDeclaredMethod("setGunners", Scanner.class);
			testedMethod.setAccessible(true);
			@SuppressWarnings("unchecked")
			ArrayList<Gunner> gunners = (ArrayList<Gunner>) testedMethod.invoke(null, sc);	
			
			assertTrue(gunners.size() == 1 && gunners.get(0).getAccuracy() == 1, "The gunner is incorrectly built.");
		
	    }catch(Exception e) {
			fail("Test found an error :" + e.getMessage());	
		}
	}
	
	@Test
	void sayAllGunnersAreOk() {
		
		String testInput = "2\n3\n4\n";
		
		ByteArrayInputStream testInputBytes= new ByteArrayInputStream(testInput.getBytes());
		System.setIn(testInputBytes);
	    sc = new Scanner(System.in);
		
	    try {
			Method testedMethod = MilitaryBaseSimulation.class.getDeclaredMethod("setGunners", Scanner.class);
			testedMethod.setAccessible(true);
			@SuppressWarnings("unchecked")
			ArrayList<Gunner> gunners = (ArrayList<Gunner>) testedMethod.invoke(null, sc);	
			
			assertTrue(gunners.size() == 2 && gunners.get(0).getAccuracy() == 3 &&gunners.get(1).getAccuracy() == 4, "Some gunnes are incorrectly built.");
		
	    }catch(Exception e) {
			fail("Test found an error :" + e.getMessage());	
		}
	}
	
	 @After
	 public void restoreSystemInputOutput() {
		 System.setIn(systemIn);
		 sc.close();
	 }

}
