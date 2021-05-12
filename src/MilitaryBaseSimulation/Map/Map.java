package MilitaryBaseSimulation.Map;

import java.util.*;

import MilitaryBaseSimulation.MapUnits.Unit.IUnit;

public class Map {
	//map upper boundaries
	private final static int yMax = 100;
	private final static int xMax = 100;
	
	private static IUnit[][] unitMap = new IUnit[xMax][yMax];
	private static List<int[]> availablePositions = new ArrayList<int[]>();
	
	public static int[] getRandomPosition() {
		Random random = new Random();
		
		return availablePositions.get(random.nextInt(availablePositions.size()));
	}
	
	/**
	 * Initializes new map.
	 */
	public static void initializeMap() {
		int[] pos;
		for(int i = 0; i<xMax; i++) {
			for(int j =0; j<yMax; j++) {
				unitMap[i][j] = null;
				pos = new int[2];
				pos[0] = i;
				pos[1] = j;
				availablePositions.add(pos);
			}
		}
	}
	
	/**
	 * Gets upper map boundaries, whereas xMax is at index 0,
	 * and yMax is at index 1.
	 * @return Array of integers collecting map upper boundries.
	 */
	public static int[] getUpperBoundaries() {
		int[] boundaries = {yMax, xMax};
		return boundaries;
	}
	
	/**
	 * Places unit on 2D map.
	 * @param unit Unit to place.
	 */
	public static void placeUnitOnMap(IUnit unit) {
		int[] pos = unit.getPosition();
		unitMap[pos[0]][pos[1]] = unit;
		
		//remove available position
		availablePositions.removeIf(avPos -> (avPos[0] == pos[0] && avPos[1] == pos[1]));
	}
	
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
	 * Checks if position isn't beyond the map.
	 * @param position Position to check.
	 * @return True if position is within the map; false if beyond. 
	 */
	public static boolean isPositionWithinMap(int[] position) {
		return position[0] < 0 || position[1] < 0 || position[0] >= xMax || position[1] >= yMax;
	}
}
