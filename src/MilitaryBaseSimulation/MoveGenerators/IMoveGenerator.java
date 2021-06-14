package MilitaryBaseSimulation.MoveGenerators;

/**
 * 
 * @author Przemys³aw Ma³ecki
 *
 */
public interface IMoveGenerator {
	/**
	 * Returns randomly generated new position.
	 * @param currentPosition Current position of an unit.
	 * @param movementRange Maximum range of move in single iteration.
	 * @return int[] representing new position for unit to move to.
	 */
	public int[] nextPosition(int[] currentPosition, int movementRange);
}
