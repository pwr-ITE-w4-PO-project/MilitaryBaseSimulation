/**
 * 
 */
package MoveGenerators.AlliesMoveGenerator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.Map.*;
import MilitaryBaseSimulation.MoveGenerators.AlliesMoveGenerator;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.NeutralUnit.*;
/**
 * @author Przemys³aw Ma³ecki
 *
 */
class NextPositionTest {

	@Test
	void sayPositionIsCorrect() {
		
		AlliesMoveGenerator tested = new AlliesMoveGenerator();
		
		int[] initialPos = {6,10};
		int movRange = 5;
		
		int[] newPos = tested.nextPosition(initialPos, movRange);
		
		Map.getInstance().initializeMap();
		assertTrue(Map.getInstance().isPositionWithinMap(newPos), "Position should be within map.");
	}
	
	@Test
	void sayPositionReturnedIsInitialPosition() {
		
		Map.getInstance().initializeMap();
		AlliesMoveGenerator tested = new AlliesMoveGenerator();
		
		int[] initialPos = {6,10};
		int movRange = 1;
		
		for(int i = 5; i<8;i++) {
			for(int j = 9; j<12; j++) {
				int[] pos = {i,j};
					Map.getInstance().placeUnitOnMap(new NeutralUnit(0, pos));
			}
		}
		
		int[] newPos = tested.nextPosition(initialPos, movRange);

		assertTrue(newPos[0] ==  6 && newPos[1] == 10, "Position should be inititial position.");
	}

}
