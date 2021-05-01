package MilitaryBaseSimulation;

import MilitaryBaseSimulation.Commander.Commander;
import MilitaryBaseSimulation.Headquarters.Headquarters;
import MilitaryBaseSimulation.Unit.*;
import MilitaryBaseSimulation.Scout.*;
import MilitaryBaseSimulation.Gunner.*;

import java.util.*;

public class MilitaryBaseSimulation {

	public static void main(String[] args) {
		buildSimulation();
		run();
	}
	
	//static objects for easy access
	static int militaryBaseHP;
	static Commander commander;
	static Headquarters headquarters;
	
	//base hit points
	static int baseHP;
	
	//private fields for simulation control
	private static int enemyFreq;
	private static int disguisedEnemyFreq;
	private static int iterations;
	
	//map upper boundaries
	private final static int yMax = 100;
	private final static int xMax = 100;
	
	//map of units - may be delegated to different class later on
	private static Unit[][] unitMap;
	
	/**
	 * Builds simulation parameters and its actors via interacting with user.
	 */
	public static void buildSimulation() {
		Scanner scanner = new Scanner(System.in); //zewnêtrzny scanner jest uzywany, by ominac bug
		
		commander = new Commander(
				setScouts(scanner), 
				setGunners(scanner)
				);
		
		System.out.println();
		
		enemyFreq = setEnemyFreq(scanner);
		disguisedEnemyFreq = setDisguisedEnemyFreq(scanner);
		iterations = setIterations(scanner);
		baseHP = setBaseHP(scanner);
		
		scanner.close();
	}
	
	/**
	 * Starts simulation based on input parameters.
	 */
	public static void run() {
		
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
	
	/**
	 * Sets number of base hit points.
	 * @param scanner Scanner object for getting input.
	 * @return Integer number representing base' hit points.
	 */
	private static int setBaseHP(Scanner scanner) {
		return getNumberFromUser(100, 1000000, "Podaj pocz¹tkow¹ iloœæ punktów trafieñ bazy (od 100 do 1000000): ", scanner);
	}
	
	/**
	 * Sets number of iterations representing duartion of simulation.
	 * @param scanner Scanner object for gettin input.
	 * @return Integer number representing duration of simulation.
	 */
	private static int setIterations(Scanner scanner) {
		return getNumberFromUser(1, 1000000, "Podaj iloœæ cykli trwania symulacji (od 1 do 1000000): ", scanner);
	}
	
	/**
	 * Sets period of iterations of generating DisguisedEnemyUnit object. It has priority over generating EnemyUnit object if periods overlap.
	 * @param scanner Scanner object for getting input.
	 * @return Integer number representing period of iterations.
	 */
	private static int setDisguisedEnemyFreq(Scanner scanner) {
		return getNumberFromUser(1, 10, "Podaj co ile cykli generowaæ zamaskowan¹ wrog¹ jednostkê (od 1 do 10): ", scanner);
	}
	
	/**
	 * Sets period of iterations of generating EnemyUnit object. Generating DisguisedEnemyUnit object has priority over this if periods overlap.
	 * @param scanner Scanner object for getting input.
	 * @return Integer number representing period of iterations.
	 */
	private static int setEnemyFreq(Scanner scanner) {
		return getNumberFromUser(1, 10, "Podaj co ile cykli generowaæ wrog¹ jednostkê (od 1 do 10): ", scanner);
	}
	
	/**
	 * Sets ArrayList of Gunner for Commander.
	 * @param scanner Scanner object for getting input.
	 * @return ArrayList<Gunner> representing Gunners under Commander's duty.
	 */
	private static ArrayList<IGunner> setGunners(Scanner scanner){
		int accuracy;
		
		int gunnersCount = getNumberFromUser(1, 5, "\nPodaj iloœæ Gunnerów (od 1 do 5): ", scanner);
		ArrayList<IGunner> gunners = new ArrayList<IGunner>();
		
		for(int i = 0; i < gunnersCount; i++) {
			System.out.println();
			
			accuracy = getNumberFromUser(0, 100, "Podaj celnoœæ Gunnera #"+ (i+1) +" w procentach (od 0 do 100): ", scanner);
			
			gunners.add(new Gunner(accuracy));
		}
		
		return gunners;
	}
	
	
	/**
	 * Sets ArrayList of Scout for Commander.
	 * @param scanner Scanner object for getting input.
	 * @return ArrayList<Scout> representing Scouts under Commander's duty.
	 */
	private static ArrayList<IScout> setScouts(Scanner scanner){
		int movementRange;
		int visionRange;
		int effectiveness;
		int position[];
		int trustLevel;
		
		int scoutsCount = getNumberFromUser(1, 5, "Podaj iloœæ Scoutów (od 1 do 5): ", scanner);
		ArrayList<IScout> scouts = new ArrayList<IScout>(scoutsCount);
		
		for(int i = 0; i < scoutsCount; i++) {
			System.out.println();
			
			movementRange = getNumberFromUser(1, 3, "Podaj prêdkoœæ Scouta #" + (i+1) +"(od 1 do 3): ", scanner);
			visionRange = getNumberFromUser(5, 20, "Podaj zasiêg widzenia Scouta #"+ (i+1) +"(od 5 do 20): ", scanner);
			effectiveness = getNumberFromUser(0, 100, "Podaj efektywnoœæ Scouta #"+ (i+1) +" w procentach (od 0 do 100): ", scanner);
			trustLevel = getNumberFromUser(0, 100, "Podaj zaufanie do Scouta #"+ (i+1) +" w procentach (od 0 do 100): ", scanner);
			position = new int[2];
			position[0] = position[1] = 0; //zmienic pozniej na pozycje na mapie
			
			scouts.add(new Scout(movementRange, position, effectiveness, trustLevel, visionRange));
		}
		
		return scouts;
	}
	
	/**
	 * Gets number in range [min, max] from the user.
	 * @param scanner Scanner object for getting input.
	 * @param min Minimum number allowed.
	 * @param max Maximum number allowed.
	 * @param message String output with each ask.
	 * @return Integer number in range [min, max].
	 */
	private static int getNumberFromUser(int min, int max, String message, Scanner scanner){
		System.out.print(message);
		int input;
		try 
		{
			input = scanner.nextInt();
			scanner.nextLine();
			while(input < min || input > max) {
				System.out.println("Podano nieprawid³owy zakres.");
				System.out.print(message);
				input = scanner.nextInt();
				scanner.nextLine();
			}
		}catch(Exception e) {
			System.out.println("Niepoprawne dane wejœciowe. Wprowadz liczbê ca³kowit¹!");
			scanner.nextLine();
			input = getNumberFromUser(min, max, message, scanner);
		}
		return input;
	}
}
