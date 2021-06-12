package MilitaryBaseSimulation.Map;

import java.util.List;

import MilitaryBaseSimulation.MapUnits.Unit.IUnit;

public interface IMap {
	public int[] getRandomPosition();
	public int[] getRandomStartingPosition();
	public void initializeMap();
	public int[] getUpperBoundaries();
	public void placeUnitOnMap(IUnit unit);
	public IUnit[][] getMap();
	public void removeUnitFromMap(IUnit unit);
	public void moveUnitOnMap(int[] from, int[] to);
	public List<IUnit> getAllUnits();
	public List<int[]> checkPositionsAccessbility(List<int[]> positions);
	public boolean isPositionAccessible(int[] position);
	public boolean isPositionWithinMap(int[] position);
	public boolean isPositionWithinMap(int x, int y);
}
