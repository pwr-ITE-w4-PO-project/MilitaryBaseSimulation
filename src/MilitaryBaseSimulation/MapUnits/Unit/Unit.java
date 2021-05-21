package MilitaryBaseSimulation.MapUnits.Unit;
import MilitaryBaseSimulation.MoveGenerators.IMoveGenerator;
import MilitaryBaseSimulation.Map.*;

public abstract class Unit implements IUnit{
	protected int position[];
	private int movementRange;
	private IMoveGenerator moveGenerator;
	
	/**
	 * @param movementRange Maximum range of movement in single iteration.
	 * @param position Starting position.
	 */
	public Unit(int movementRange, int[] position) {
		this.position = new int[2];
		this.movementRange = movementRange;
		this.position[0] = position[0];
		this.position[1] = position[1];
		//MilitaryBaseSimulation.Map.Map.placeUnitOnMap(this);
	}
	
	/**
	 * Moves unit on the map.
	 */
	public void move(){
		int[] newPosition = moveGenerator.nextPosition(position, movementRange);
		
		if(!Map.getInstance().isPositionWithinMap(newPosition)) {
			newPosition = handlePositionBeyondMap(newPosition);
			if(newPosition==null) {
				this.disappearFromMap();
				return;
			}
		}
		
		Map.getInstance().moveUnitOnMap(position, newPosition);
		position = newPosition;
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
		Map.getInstance().removeUnitFromMap(this);
	}
	
	/**
	 * Invoked when new position is beyond the map.
	 * @param newPosition Position that is beyond the map.
	 */
	protected abstract int[] handlePositionBeyondMap(int[] newPosition);
	
	/**
	 * Prints unit on the map.
	 * @return Char representing unit.
	 */
	public abstract char getUnitChar();
}
