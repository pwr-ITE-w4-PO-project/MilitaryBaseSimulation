package MilitaryBaseSimulation.MoveGenerators;

import java.util.*;

public class AlliesMoveGenerator implements IMoveGenerator{
	/**
	 * Returns randomly generated new position.
	 * This position is randomly chosen from surrounding accessible positions. 
	 * @param currentPosition Current position of an unit.
	 * @param movementRange Maximum range of move in single iteration.
	 * @return int[] representing new position for unit to move to.
	 */
	public int[] nextPosition(int[] currentPosition, int movementRange) {
		List<int[]> positions = new ArrayList<int[]>();
		
		int[] pos;
		Random random = new Random();
		
		for(int x = currentPosition[0] - movementRange; x <= currentPosition[0] + movementRange; x++) {
			for(int y = currentPosition[1] - movementRange; y <= currentPosition[1] + movementRange; y++) {
				pos = new int[2];
				pos[0] = x;
				pos[1] = y;
				
				positions.add(pos);
			}
		}
		
		//checks for accessibility; ignores negativeand greater than max coordinates
		positions = MilitaryBaseSimulation.MilitaryBaseSimulation.positionsChecker(positions);
		
		//returns random position
		return positions.get(random.nextInt(positions.size()+1));
	}
}
