package MilitaryBaseSimulation.Map;

import java.util.List;

import MilitaryBaseSimulation.MapUnits.Unit.IUnit;

/**
 *
 * @author Przemys³aw Ma³ecki
 *
 */
public interface IMap {
	/**
	 * Gets random position on the map.
	 * @return Int[] representing position.
	 */
	public int[] getRandomPosition();
	/**
	 * Gets random starting position on the map.
	 * @return Int[] representing position.
	 */
	public int[] getRandomStartingPosition();
	/**
	 * Initializes the map.
	 */
	public void initializeMap();
	/**
	 * Gets the upper limits of the map.
	 * @return Int[] representing max X coord and max Y coord.
	 */
	public int[] getUpperBoundaries();
	/**
	 * Places unit on the map.
	 * @param unit Unit to place.
	 */
	public void placeUnitOnMap(IUnit unit);
	/**
	 * Gets the current state of the map.
	 * @return IUnit[][] representing map of units.
	 */
	public IUnit[][] getMap();
	/**
	 * Removes unit from the map.
	 * @param unit Unit to remove.
	 */
	public void removeUnitFromMap(IUnit unit);
	/**
	 * Moves unit on the map.
	 * @param from Position from which move the unit.
	 * @param to Position to which move the unit.
	 */
	public void moveUnitOnMap(int[] from, int[] to);
	/**
	 * Gets the list of all units.
	 * @return List<IUnit> representing all units.
	 */
	public List<IUnit> getAllUnits();
	/**
	 * Checks the accessibility of positions.
	 * @param positions Positions to check.
	 * @return List<int[]> representing accessible positions.
	 */
	public List<int[]> checkPositionsAccessbility(List<int[]> positions);
	/**
	 * Checks if position is accessible.
	 * @param position Position to check.
	 * @return Boolean value representing whether position is accessible or not.
	 */
	public boolean isPositionAccessible(int[] position);
	/**
	 * Checks if position is within the map.
	 * @param position Position to check.
	 * @return Boolean value representing whether position is within map or not.
	 */
	public boolean isPositionWithinMap(int[] position);
	/**
	 * Checks if position is within the map.
	 * @param x X coordinate to check.
	 * @param y Y coordinate to check.
	 * @return Boolean value representing whether position is within map or not.
	 */
	public boolean isPositionWithinMap(int x, int y);
}
