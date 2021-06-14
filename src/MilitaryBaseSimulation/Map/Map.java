package MilitaryBaseSimulation.Map;

import java.util.*;

import MilitaryBaseSimulation.MapUnits.Unit.IUnit;

/**
 * 
 * @author Przemys³aw Ma³ecki
 *
 */
public class Map implements IMap{
	//applied singleton pattern
	private static IMap instance;
	private Map() {}
	public static IMap getInstance() {
		if(instance == null) {
			instance = new Map();
		}
		return instance;
	}
	
	//map upper boundaries
	private int yMax = 50;
	private int xMax = 50;
	
	private IUnit[][] unitMap;
	private List<int[]> availablePositions;
	private List<IUnit> allUnits;
	private List<int[]> availableStartingPositions;
	
	private Random random = new Random();
	
	/**
	 * Gets randomly chosen accessible position from unit map.
	 * @return Integer array representing random accessible position, or null if position doesn't exist.
	 */
	public int[] getRandomPosition() {		
		if(this.availablePositions.size() > 0)
			return availablePositions.get(random.nextInt(availablePositions.size()));
		else return null;
	}
	
	/**
	 * Gets randomly chosen accessible starting position (those with x from 0 to 2) from unit map.
	 * @return Integer array representing random accessible position, or null if position doesn't exist.
	 */
	public int[] getRandomStartingPosition() {
		if(this.availableStartingPositions.size() > 0)
			return availableStartingPositions.get(random.nextInt(availableStartingPositions.size()));
		else return null;
	}
	/**
	 * Initializes new map.
	 */
	public void initializeMap() {
		int[] pos;
		this.allUnits = new ArrayList<IUnit>();	//init new collections
		this.unitMap = new IUnit[xMax][yMax];
		this.availableStartingPositions = new ArrayList<int[]>();
		this.availablePositions = new ArrayList<int[]>();
		
		for(int i = 0; i<this.xMax; i++) {
			for(int j = 0; j<this.yMax; j++) {
				pos = new int[2];
				pos[0] = i;
				pos[1] = j;
				this.availablePositions.add(pos);	//add positions to collections for later use
				if(i < 3) this.availableStartingPositions.add(pos);
			}
		}
	}
	
	/**
	 * Gets upper map boundaries, whereas xMax is at index 0,
	 * and yMax is at index 1.
	 * @return Array of integers collecting map upper boundaries.
	 */
	public int[] getUpperBoundaries() {
		int[] boundaries = {this.yMax, this.xMax};
		return boundaries;
	}
	
	/**
	 * Places unit on 2D map.
	 * @param unit Unit to place.
	 */
	public void placeUnitOnMap(IUnit unit) {
		int[] pos = unit.getPosition();
		
		//places unit on map and adds to collection
		this.unitMap[pos[0]][pos[1]] = unit;
		this.allUnits.add(unit);

		//remove position from available ones
		this.availablePositions.removeIf(avPos -> (avPos[0] == pos[0] && avPos[1] == pos[1]));
		if(pos[0] < 3) this.availableStartingPositions.removeIf(avPos -> (avPos[0] == pos[0] && avPos[1] == pos[1]));
	}
	
	/**
	 * Returns map of units.
	 * @return 2D array of IUnit representing map with units.
	 */
	public IUnit[][] getMap(){
		return this.unitMap;
	}
	
	/**
	 * Removes unit from map.
	 * @param unit Unit which is removed.
	 */
	public void removeUnitFromMap(IUnit unit) {
		int[] pos = unit.getPosition();
		
		this.unitMap[pos[0]][pos[1]] = null; //removes unit from map
		this.allUnits.remove(unit);
		
		int[] freedPos = new int[2]; //creates available position and adds to collections
		freedPos[0] = pos[0];
		freedPos[1] = pos[1];
		this.availablePositions.add(freedPos);
		if(freedPos[0] < 3) this.availableStartingPositions.add(freedPos);
	}
	
	/**
	 * Moves unit on map.
	 * @param from Position from which unit is moved.
	 * @param to Position to which unit is moved.
	 */
	public void moveUnitOnMap(int[] from, int[] to) {
		this.unitMap[to[0]][to[1]] = unitMap[from[0]][from[1]]; //moves unit on map
		this.unitMap[from[0]][from[1]] = null;
		
		this.availablePositions.removeIf(avPos -> (avPos[0] == to[0] && avPos[1] == to[1])); //removes position taken from available ones
		if(to[0] < 3) this.availableStartingPositions.removeIf(avPos -> (avPos[0] == to[0] && avPos[1] == to[1]));
		
		this.availablePositions.add(from);	//adds freed position to available ones
		if(from[0] < 3) this.availableStartingPositions.add(from);
	} 
	
	/**
	 * Gets the list of all units.
	 * @return List<IUnit> representing all units.
	 */
	public List<IUnit> getAllUnits() {
		return this.allUnits;
	}
	
	/**
	 * Checks accessibility of positions on the map. If any of position's coordinates is smaller than 0,
	 * it is ignored and added to returned list for further handle.
	 * The same happens when any of these coordinates is greater than map boundary.
	 * @param positions List of positions to check.
	 * @return List<int[]> representing accessible positions on the map.
	 */
	public List<int[]> checkPositionsAccessbility(List<int[]> positions) {
		List<int[]> accessibles = new ArrayList<int[]>(); //creates new collection of accessible positions
		
		for(int[] pos:positions) {			//for each checked position add those which are not within map
			if(!isPositionWithinMap(pos)) {	//since units are free to move out of the map
				accessibles.add(pos);		//and add those that are not occupied
			}
			else if(unitMap[pos[0]][pos[1]] == null) {
				accessibles.add(pos);
			}
		}
		return accessibles;
	}
	
	/**
	 * Verifies whether given position is accessible for moving to.
	 * @param position Position to check.
	 * @return Boolean value representing whether position is accessible.
	 */
	public boolean isPositionAccessible(int[] position) {
		boolean result = false;
		for(int[] pos : this.availablePositions) { 				//search for position in accessible positions
			if(pos[0] == position[0] && pos[1] == position[1]) {//and return result
				result = true;
				break;
			}
		}
		return result;
	}
	
	/**
	 * Checks if position is within the map.
	 * @param position Position to check.
	 * @return True if position is within the map; false if beyond. 
	 */
	public boolean isPositionWithinMap(int[] position) {
		return position[0] >= 0 && position[1] >= 0 && position[0] < xMax && position[1] < yMax;
	}
	
	/**
	 * Checks if position is within the map.
	 * @param x X coordinate of checked position.
	 * @param y Y coordinate of checked position.
	 * @return True if position is within the map; false if beyond. 
	 */
	public boolean isPositionWithinMap(int x, int y) {
		return x >= 0 && y >= 0 && x < xMax && y < yMax;
	}
}
