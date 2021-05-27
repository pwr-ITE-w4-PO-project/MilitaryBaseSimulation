/**
 * 
 */
package Unit;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.MapUnits.Unit.Unit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class GetPositionTest {

	@Test
	void sayPositionIsOk() {
		int[] pos = {3,4};
		
		Unit unit = new NeutralUnit(0, pos);
		
		try {
			Field fpos = Unit.class.getDeclaredField("position");
			fpos.setAccessible(true);
			int[] pos2 = (int[])fpos.get(unit);
			
			int[] tested = unit.getPosition();
			
			assertTrue(tested[0] == pos2[0] && tested[1] == pos2[1], "Method should return valid position.");
		}catch(Exception e) {
			fail("Test found an error: " + e.getMessage());
		}
	}

}
