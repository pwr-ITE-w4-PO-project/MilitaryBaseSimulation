/**
 * 
 */
package MoveGenerators.EnemyMoveGenerator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import MilitaryBaseSimulation.Map.Map;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;
import MilitaryBaseSimulation.MoveGenerators.EnemyMoveGenerator;

/**
 * @author Przemys³aw Ma³ecki
 *
 */
class NextPositionTest {


	@Test
	void sayPositionIsCorrect() {
		
		EnemyMoveGenerator tested = new EnemyMoveGenerator();
		
		int[] initialPos = {6,10};
		int movRange = 5;
		
		int[] newPos = tested.nextPosition(initialPos, movRange);
		
		Map.getInstance().initializeMap();
		assertTrue(Map.getInstance().isPositionWithinMap(newPos), "Position should be within map.");
	}
	
	@Test
	void sayPositionReturnedIsInitialPosition() {
		
		Map.getInstance().initializeMap();
		EnemyMoveGenerator tested = new EnemyMoveGenerator();
		
		int[] initialPos = {6,10};
		int movRange = 1;
		
		for(int i = 5; i<8;i++) {
			for(int j = 9; j<12; j++) {
				int[] pos = {i,j};
				if(i != 6 || j != 10)
					Map.getInstance().placeUnitOnMap(new NeutralUnit(0, pos));
			}
		}
		
		int[] newPos = tested.nextPosition(initialPos, movRange);

		assertTrue(newPos[0] ==  6 && newPos[1] == 10, "Position should be inititial position.");
	}
	
	@Test
	void sayPositionIsCloserToBase() {
		
		EnemyMoveGenerator tested = new EnemyMoveGenerator();
		
		int[] initialPos = {6,10};
		int movRange = 5;
		
		int[] newPos = tested.nextPosition(initialPos, movRange);
		
		Map.getInstance().initializeMap();
		int x = newPos[0] - initialPos[0];
		int y = newPos[1] - initialPos[1];
		assertTrue(x>=0 && y>=0, "New position wasn't closer to the base.");
	}
}
