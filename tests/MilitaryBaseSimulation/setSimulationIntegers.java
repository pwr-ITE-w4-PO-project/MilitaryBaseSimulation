/**
 * 
 */
package MilitaryBaseSimulation;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Scanner;

import org.junit.After;
import org.junit.jupiter.api.Test;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class setSimulationIntegers {
	InputStream systemIn = System.in;
	Scanner sc;
    
	/**
	 * @throws java.lang.Exception
	 */

	//test dla 4 metod wykonany w ten sposób, gdy¿ z jakeigoœ powodu @Before nie dzia³a
	@Test
	void sayAllMethodsReturnedCorrectValue() {
		String testInput = "5\n5\n150\n5\n";
		
		ByteArrayInputStream testInputBytes= new ByteArrayInputStream(testInput.getBytes());
		System.setIn(testInputBytes);
	    sc = new Scanner(System.in);
		boolean[] results = {false, false, false, false};
		
		//setDisguisedEnemyFreq
	    try {
			Method testedMethod = MilitaryBaseSimulation.class.getDeclaredMethod("setDisguisedEnemyFreq", Scanner.class);
			testedMethod.setAccessible(true);
			int result = (int)testedMethod.invoke(null, sc);	
			
			if(result == 5)
				results[0] = true;
		
	    }catch(Exception e) {
			fail("Test found an arrer :" + e.getMessage());	
		}
	    
	    //setEnemyFreq
	    try {
			Method testedMethod = MilitaryBaseSimulation.class.getDeclaredMethod("setEnemyFreq", Scanner.class);
			testedMethod.setAccessible(true);
			int result = (int)testedMethod.invoke(null, sc);	

			if(result == 5)
				results[1] = true;
		
		}catch(Exception e) {
			fail("Test found an arrer :" + e.getMessage());	
		}
	    
	    //setBaseHP
	    try {
			Method testedMethod = MilitaryBaseSimulation.class.getDeclaredMethod("setBaseHP", Scanner.class);
			testedMethod.setAccessible(true);
			int result = (int)testedMethod.invoke(null, sc);	
			

			if(result == 150)
				results[2] = true;
		
		}catch(Exception e) {
			fail("Test found an arrer :" + e.getMessage());	
		}
	    
	    //setIterations
	    try {
			Method testedMethod = MilitaryBaseSimulation.class.getDeclaredMethod("setIterations", Scanner.class);
			testedMethod.setAccessible(true);
			int result = (int)testedMethod.invoke(null, sc);	
			

			if(result == 5)
				results[3] = true;
		
		}catch(Exception e) {
			fail("Test found an arrer :" + e.getMessage());	
		}
	    
	    boolean res = true;
	    for(int i = 0; i < 4; i++) {
	    	if(!results[i]) {
	    		res = false;
	    		break;
	    	}
	    }
	    
	    assertTrue(res, "Some methods did not return 5.");
	}
	
	 @After
	    public void restoreSystemInputOutput() {
	        System.setIn(systemIn);
	        sc.close();
	    }

}
