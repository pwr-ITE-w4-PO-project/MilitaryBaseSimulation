package Map;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.Map.Map;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;

class IsPositionAccessibleTest {

	@BeforeEach
	void setup() {
		Map.getInstance().initializeMap();
	}
	
	@Test
	void sayPositionIsAccessible() {
		int[] pos = {10,10};
		assertTrue(Map.getInstance().isPositionAccessible(pos), "Position should be accessible, but was found inacessible.");
	}

	@Test
	void sayPositionIsInaccessible() {
		int[] pos = {50,10};
		assertFalse(Map.getInstance().isPositionAccessible(pos), "Position should not be accessible, but was found acessible.");
	}
	
	@Test
	void sayPositionIsOccupied() {
		int[] pos = {0,10};
		
		NeutralUnit unit = new NeutralUnit(0, pos);
		
		Map.getInstance().placeUnitOnMap(unit);
		
		assertFalse(Map.getInstance().isPositionAccessible(pos), "Position should not be accessible, but was found acessible.");
	}
}
