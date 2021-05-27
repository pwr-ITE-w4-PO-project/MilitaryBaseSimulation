/**
 * 
 */
package MilitaryBaseSimulation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
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
class GetNumberFromUserTest {
	InputStream systemIn = System.in;

    ByteArrayInputStream testInputBytes;
	
	@Test
	void sayAllInputMatchedResult() {
		String testInput = "1\n2\n-2\n3\nfgh !@2a\n4\n150\n5\n";	//example method parameters
		
		int min = 1;
		int max = 5;

		testInputBytes = new ByteArrayInputStream(testInput.getBytes()); //stream of tested input
		List<Integer> results = new ArrayList<Integer>(); //list collecting results
		
		System.setIn(testInputBytes);
		int number;
		
		try {
			Method testedMethod = MilitaryBaseSimulation.class.getDeclaredMethod("getNumberFromUser", int.class, int.class, String.class, Scanner.class);
			testedMethod.setAccessible(true);
			
			Scanner sc = new Scanner(System.in);
			
			for(int i = 0; i<5; i++) {
				number = (int)testedMethod.invoke(null, min, max, "This is unit test.\n", sc);	
				results.add(number);
			}
			
			sc.close();
			
			boolean allCorrect = true;
			int[] correct = {1,2,3,4,5};
			
			for(int i= 0; i < 5; i++) {//check if all match
				if(results.get(i) != correct[i]) {
					allCorrect = false;
					break;
				}
			}
			if(results.size() != 5) allCorrect = false;
			
			assertTrue(allCorrect, "Some input did not match expected results.");
		}catch(Exception e) {
			fail("Test found an arrero :" + e.getMessage());
		}
	}
	
    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
    }
}
