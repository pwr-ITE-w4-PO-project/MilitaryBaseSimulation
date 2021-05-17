/**
 * 
 */
package Map;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.Map.Map;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class IsPositionWithinMapTest {

	@Before
	void setUp() {
		Map.GetInstance().initializeMap();
	}
	
	@Test
	void sayIsWithinMap() {
		int[] pos = {10,50};
		assertTrue(Map.GetInstance().isPositionWithinMap(pos), "Position is within map.");
		assertFalse(!Map.GetInstance().isPositionWithinMap(pos), "Position should be within map.");
	}
	
	@Test
	void sayIsNotWithinMap() {
		int[] pos = {101,50};
		assertTrue(!Map.GetInstance().isPositionWithinMap(pos), "Position is not within map.");
		assertFalse(Map.GetInstance().isPositionWithinMap(pos), "Position should not be within map.");
	}

}
