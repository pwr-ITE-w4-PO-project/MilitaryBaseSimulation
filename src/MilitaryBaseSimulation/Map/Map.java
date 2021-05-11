package MilitaryBaseSimulation.Map;

import java.util.ArrayList;
import java.util.List;

import MilitaryBaseSimulation.MapUnits.Unit.IUnit;

public class Map {
	public static IUnit[][] unitMap;
	//map upper boundaries
	private final static int yMax = 100;
	private final static int xMax = 100;
	
	/**
	 * Returns map of units.
	 * @return 2D array of IUnit representing map with units.
	 */
	public static IUnit[][] getMap(){
		return unitMap;
	}
	
	/**
	 * Removes unit from map.
	 * @param unit Unit which is removed.
	 */
	public static void removeUnitFromMap(IUnit unit) {
		int[] pos = unit.getPosition();
		
		unitMap[pos[0]][pos[1]] = null;
	}
	
	/**
	 * Moves unit on map.
	 * @param from Position from which unit is moved.
	 * @param to Position to which unit is moved.
	 */
	public static void moveUnitOnMap(int[] from, int[] to) {
		unitMap[to[0]][to[1]] = unitMap[from[0]][from[1]];
		unitMap[from[0]][from[1]] = null;	
	}
	
	/**
	 * Checks accessibility of positions on the map. If any of position's coordinates is smaller than 0,
	 * it is ignored and added to returned list for further handle.
	 * The same happens when any of these coordinates is greater than map boundary.
	 * @param positions List of positions to check.
	 * @return List<int[]> representing accessible positions on the map.
	 */
	public static List<int[]> positionsChecker(List<int[]> positions) {
		List<int[]> accessibles = new ArrayList<int[]>();
		
		for(int[] pos:positions) {
			if(!isPositionWithinMap(pos)) {
				accessibles.add(pos);
			}
			else if(unitMap[pos[0]][pos[1]] == null) {
				accessibles.add(pos);
			}
		}
		
		return accessibles;
	}
	
	/**
	 * Checks if posision isn't beyond the map.
	 * @param position Position to check.
	 * @return True if position is within the map; false if beyond. 
	 */
	public static boolean isPositionWithinMap(int[] position) {
		return position[0] < 0 || position[1] < 0 || position[0] >= xMax || position[1] >= yMax;
	}
}
