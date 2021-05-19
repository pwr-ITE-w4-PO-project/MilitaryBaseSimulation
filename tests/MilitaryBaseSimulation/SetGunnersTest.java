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
import org.junit.jupiter.api.BeforeEach;
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
	void sayAllGunnersOk() {
		
		String testInput = "1\n1\n";
		
		ByteArrayInputStream testInputBytes= new ByteArrayInputStream(testInput.getBytes());
		System.setIn(testInputBytes);
	    sc = new Scanner(System.in);
		
	    try {
			Method testedMethod = MilitaryBaseSimulation.class.getDeclaredMethod("setGunners", Scanner.class);
			testedMethod.setAccessible(true);
			ArrayList<Gunner> gunners = (ArrayList<Gunner>) testedMethod.invoke(null, sc);	
			
			fail("Gunner class is not yet implemented");
		
	    }catch(Exception e) {
			fail("Test found an arrer :" + e.getMessage());	
		}
	    
		fail("Not yet implemented");
	}
	
	 @After
	 public void restoreSystemInputOutput() {
		 System.setIn(systemIn);
		 sc.close();
	 }

}
