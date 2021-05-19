/**
 * 
 */
package Map;
import MilitaryBaseSimulation.Map.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.NeutralUnit.NeutralUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class CheckPositionsAccessbilityTest {

	List<int[]> positions = new ArrayList<int[]>();
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		Map.getInstance().initializeMap();
		for(int i = -1; i<4; i++) {
			for(int j = 96; j<101; j++) {
				int[] pos = new int[2];
				pos[0] = i;
				pos[1] = j;
				positions.add(pos);
			}
		}
	}

	@Test
	void sayAllAreAccessible() {
		List<int[]> accessibles = Map.getInstance().checkPositionsAccessbility(positions);
		
		boolean foundPos = false;
		
		for(int[] pos: positions) {
			for(int[] acc: accessibles) {
				if(pos[0] == acc[0] && pos[1] == acc[1]) {
					foundPos = true;
					break;
				}
			}
			if(foundPos == false) {
				break;
			}
		}
		
		assertTrue(foundPos, "Some positions are inaccessible.");
	}
	
	@Test
	void saySomeAreAccessible() {
		int[] takenPos = {0,98};
		Map.getInstance().placeUnitOnMap(new NeutralUnit(0, takenPos));
		
		List<int[]> accessibles = Map.getInstance().checkPositionsAccessbility(positions);
		
		positions.removeIf(s -> s[0] == takenPos[0] && s[1] == takenPos[1]);
		
		boolean foundPos = false;
		
		for(int[] pos: positions) {
			for(int[] acc: accessibles) {
				if(pos[0] == acc[0] && pos[1] == acc[1]) {
					foundPos = true;
					break;
				}
			}
			if(foundPos == false) {
				break;
			}
		}
		
		if(accessibles.size() > positions.size()) foundPos = false;
		
		assertTrue(foundPos, "Some returned positions did not match expected ones.");
	}

}
