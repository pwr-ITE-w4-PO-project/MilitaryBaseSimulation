package MilitaryBaseSimulation.MoveGenerators;

import MilitaryBaseSimulation.Map.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyMoveGenerator implements IMoveGenerator{
	 /** Returns randomly generated new position.
	 * This position is randomly chosen from surrounding accessible positions nearest to base. 
	 * @param currentPosition Current position of an unit.
	 * @param movementRange Maximum range of move in single iteration.
	 * @return int[] representing new position for unit to move to.
	 */
	public int[] nextPosition(int[] currentPosition, int movementRange) {
		List<int[]> positions = new ArrayList<int[]>();  //create new collection
		
		int[] pos;
		Random random = new Random();
		
		//add each surrounding position that are closer to the base, within movement range, to collection
		for(int x = currentPosition[0] + movementRange; x > currentPosition[0]; x--) {
			for(int y = currentPosition[1] - movementRange; y <= currentPosition[1] + movementRange; y++) {
				pos = new int[2];
				pos[0] = x;
				pos[1] = y;
				
				positions.add(pos);
			}
			
			//checks for accessibility; ignores negative and grater than max coordinates
			positions = Map.getInstance().checkPositionsAccessbility(positions);
			
			if(positions.size() > 0) {
				break;
			}
		}
		
		//returns random position or current if accessible position doesn't exist
		if(positions.size() > 0) return positions.get(random.nextInt(positions.size()));
		else return currentPosition;
	}
	
}
