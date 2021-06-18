package MilitaryBaseSimulation.MapUnits.Unit;

/**
 * 
 * @author Przemys³aw Ma³ecki
 *
 */
public interface IUnit {
	/**
	 * Moves unit on map.
	 */
	public void move();
	/**
	 * Gets current position.
	 * @return Int[] representing position.
	 */
	public int[] getPosition();
	/**
	 * Gets char representing subtype of unit.
	 * @return
	 */
	public char getUnitChar();
	/**
	 * Refreshes movement.
	 */
	public void refreshMovement();
}
