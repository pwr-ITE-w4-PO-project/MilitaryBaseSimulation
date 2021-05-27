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
		Map.getInstance().initializeMap();
	}
	
	@Test
	void sayIsWithinMap() {
		int[] pos = {10,40};
		assertTrue(Map.getInstance().isPositionWithinMap(pos), "Position isn't within map, though it should be.");
	}
	
	@Test
	void sayIsNotWithinMap() {
		int[] pos = {101,50};
		assertTrue(!Map.getInstance().isPositionWithinMap(pos), "Position is within map, though it should not be.");
	}

}
