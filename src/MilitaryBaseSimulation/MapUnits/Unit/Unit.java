package MilitaryBaseSimulation.MapUnits.Unit;
import MilitaryBaseSimulation.MoveGenerators.IMoveGenerator;
import MilitaryBaseSimulation.Map.*;

/**
 * 
 * @author Przemys³aw Ma³ecki
 *
 */
public abstract class Unit implements IUnit{
	protected int position[];
	protected char unitChar;
	protected IMoveGenerator moveGenerator;
	private int movementRange;
	private static int count = 0;
	private boolean haveMoved;
	
	/**
	 * @param movementRange Maximum range of movement in single iteration.
	 * @param position Starting position.
	 */
	public Unit(int movementRange, int[] position) {
		this.position = new int[2];
		this.movementRange = movementRange;
		this.position[0] = position[0];
		this.position[1] = position[1];
		Unit.count++;
		this.haveMoved = false;
	}
	
	/**
	 * Moves unit on the map, if the unit hasn't moved yet. Otherwise does nothing.
	 */
	public void move(){
		if(haveMoved == false) {
			int[] newPosition = moveGenerator.nextPosition(position, movementRange);
			
			//checks if position is within map
			if(!Map.getInstance().isPositionWithinMap(newPosition)) {
				//calls hanlder when position is beyond the map
				newPosition = handlePositionBeyondMap(newPosition);
				
				//removes unit
				if(newPosition == null) {
					this.disappearFromMap();
					return;
				}
				
				//verifies if handled position is accessible
				else if(!Map.getInstance().isPositionAccessible(newPosition)) {
					newPosition = position;
				}
			}//checks if new position isn't the same as current position
			if(newPosition[0] != this.position[0] || newPosition[1] != this.position[1]){	
				Map.getInstance().moveUnitOnMap(position, newPosition);
				position = newPosition;
			}
			//blocks movement so the unit can't move twice in one iteration
			this.haveMoved = true;
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
		Unit.count--;
		Map.getInstance().removeUnitFromMap(this);
	}
	
	/**
	 * Invoked when new position is beyond the map.
	 * Specifies unit's behavior in situations of moving out of the map/
	 * @param newPosition Position that is beyond the map.
	 */
	protected abstract int[] handlePositionBeyondMap(int[] newPosition);
	
	/**
	 * Prints unit on the map.
	 * @return Char representing unit.
	 */
	public char getUnitChar() {
		return unitChar;
	}
	
	/**
	 * Gets number of instances of Unit class.
	 * @return Integer number representing count of Unit instances.
	 */
	public static int getCount() {
		return Unit.count;
	}
	
	/**
	 * Refreshes unit's movements, so it can move in next iteration.
	 */
	public void refreshMovement() {
		this.haveMoved = false;
	}
}
