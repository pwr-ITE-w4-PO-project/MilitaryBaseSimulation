package MilitaryBaseSimulation.Unit;
import MilitaryBaseSimulation.MoveGenerators.IMoveGenerator;

public abstract class Unit implements IUnit{
	private int position[];
	private int movementRange;
	private IMoveGenerator moveGenerator;
	
	/**
	 * @param movementRange Maximum range of movement in single iteration.
	 * @param position Starting position.
	 */
	public Unit(int movementRange, int[] position) {
		this.position[0] = position[0];
		this.position[1] = position[1];
		this.movementRange = movementRange;
	}
	
	/**
	 * Moves unit on the map.
	 */
	public void move(){
		int[] newPosition = moveGenerator.nextPosition(position, movementRange);
		
		if(MilitaryBaseSimulation.MilitaryBaseSimulation.isPositionWithinMap(newPosition)) {
			position = newPosition;
			
			//dodac przesuniecie na mapa w militarybasesimulation	
		}
		else {
			positionBeyondMapHandler(newPosition);
		}
	}
	
	/**
	 * Gets current position of an unit.
	 */
	public int[] getPosition() {
		return position;
	}
	
	/**
	 * Removes unit from the map.
	 */
	protected void disappearFromMap() {
		//dodac usuniecie z mapy w militaryabsesimulation
	}
	
	/**
	 * Invoked when new position is beyond the map.
	 * @param newPosition Position that is beyond the map.
	 */
	protected abstract void positionBeyondMapHandler(int[] newPosition);
}
