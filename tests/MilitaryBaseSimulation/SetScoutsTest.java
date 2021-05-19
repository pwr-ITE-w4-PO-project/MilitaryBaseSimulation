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
		
		String testInput = "1\n1\n";
		
		ByteArrayInputStream testInputBytes= new ByteArrayInputStream(testInput.getBytes());
		System.setIn(testInputBytes);
	    sc = new Scanner(System.in);
		
	    try {
			Method testedMethod = MilitaryBaseSimulation.class.getDeclaredMethod("setScouts", Scanner.class);
			testedMethod.setAccessible(true);
			ArrayList<Scout> scouts = (ArrayList<Scout>) testedMethod.invoke(null, sc);	
			
			fail("Scout class is not yet implemented");
		
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
