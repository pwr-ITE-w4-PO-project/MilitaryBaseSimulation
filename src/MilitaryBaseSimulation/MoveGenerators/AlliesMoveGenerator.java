package MilitaryBaseSimulation.MoveGenerators;

import MilitaryBaseSimulation.Map.*;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * 
 * @author Przemys?aw Ma?ecki
 *
 */
public class AlliesMoveGenerator implements IMoveGenerator{
	/**
	 * Returns randomly generated new position.
	 * This position is randomly chosen from surrounding accessible positions. 
	 * @param currentPosition Current position of an unit.
	 * @param movementRange Maximum range of move in single iteration.
	 * @return int[] representing new position for unit to move to.
	 */
	public int[] nextPosition(int[] currentPosition, int movementRange) {
		List<int[]> positions = new ArrayList<int[]>();  //create new collection
		
		int[] pos;
		Random random = new Random();
		
		//add each surrounding position within movement range to collection
		for(int x = currentPosition[0] - movementRange; x <= currentPosition[0] + movementRange; x++) {
			for(int y = currentPosition[1] - movementRange; y <= currentPosition[1] + movementRange; y++) {
				pos = new int[2];
				pos[0] = x;
				pos[1] = y;
				
				positions.add(pos);
			}
		}
		
		//checks for accessibility; ignores negative and greater than max coordinates
		positions = Map.getInstance().checkPositionsAccessbility(positions);
		
		//returns random position or current if accessible position doesn't exist
		if(positions.size() > 0) return positions.get(random.nextInt(positions.size()));
		else return currentPosition;
		
	}
}
