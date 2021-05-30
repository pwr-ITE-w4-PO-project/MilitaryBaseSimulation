package MilitaryBaseSimulation;

import MilitaryBaseSimulation.Map.Map;
import MilitaryBaseSimulation.MapUnits.Unit.IUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit.DisguisedEnemyUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;
import MilitaryBaseSimulation.Militaries.Commander.Commander;
import MilitaryBaseSimulation.Militaries.Gunner.*;
import MilitaryBaseSimulation.Militaries.Headquarters.Headquarters;

import java.util.*;

public class MilitaryBaseSimulation {

	public static void main(String[] args) {
		buildSimulation();
		//run();
	}
	
	//objects to access
	private static Commander commander = new Commander(null, null);
	private static Headquarters headquarters = new Headquarters();
	
	//base hit points
	private static int baseHP;
	
	//private fields for simulation control
	private static int enemyFreq;
	private static int disguisedEnemyFreq;
	private static int iterations;
	
	/**
	 * Starts simulation based on input parameters. Ends after reaching input iterations limit,
	 * or when base hp drops to 0.
	 */
	public static void run() {
		IUnit[][] map = Map.getInstance().getMap();
		
		for(int i = 0; i<iterations;i++) {
			for(IUnit[] unitRow: map) {
				for(IUnit unit: unitRow) {
					if(unit != null) {
						unit.move();
						System.out.print(unit.getUnitChar());
					}
					else {
						System.out.print(" ");
					}
					
					if(baseHP <= 0) {
						break;
					}
				}
				System.out.print("#\n");
				if(baseHP <= 0) {
					break;
				}
			}	
		}
	}
	
	/**
	 * Builds simulation parameters and its actors via interacting with user.
	 */
	public static void buildSimulation() {
		Map.getInstance().initializeMap();;
		
		Scanner scanner = new Scanner(System.in); //outer Scanner is used to bypass bugs
		
		commander = new Commander(
				setScouts(scanner), 
				setGunners(scanner)
				);
		
		System.out.println();
		
		enemyFreq = setEnemyFreq(scanner);
		disguisedEnemyFreq = setDisguisedEnemyFreq(scanner);
		iterations = setIterations(scanner);
		baseHP = setBaseHP(scanner);
		
		headquarters = new Headquarters();
		
		scanner.close();
		
		//filling 10% of 2d map with random units
		Random random = new Random();
		for(int i = 0; i < 100; i++) {
			IUnit newUnit;
			if( i%disguisedEnemyFreq == 0) {
				newUnit = new DisguisedEnemyUnit(random.nextInt(3)+1, Map.getInstance().getRandomPosition(), random.nextInt(5)+1); 
			}
			else if( i%enemyFreq == 0) {
				newUnit = new EnemyUnit(random.nextInt(3)+1, Map.getInstance().getRandomPosition(), random.nextInt(5)+1);
			}
			else {
				newUnit = new NeutralUnit(random.nextInt(3)+1, Map.getInstance().getRandomPosition());
			}
			Map.getInstance().placeUnitOnMap(newUnit);
		}
	}
	
	/**
	 * Damages base.
	 * @param damage Damage inflicted to the base hit points.
	 */
	public static void damageBase(int damage) {
		baseHP += damage;
	}
	
	/**
	 * Gets the commander.
	 * @return
	 */
	public static Commander getCommander() {
		return commander;
	}
	
	/**
	 * Gets the headquarters.
	 * @return
	 */
	public static Headquarters getHeadquarters() {
		return headquarters;
	}
	
	/**
	 * Sets number of base hit points.
	 * @param scanner Scanner object for getting input.
	 * @return Integer number representing base' hit points.
	 */
	private static int setBaseHP(Scanner scanner) {
		return getNumberFromUser(100, 1000000, "Set initial base hit points (from 100 to 1000000): ", scanner);
	}
	
	/**
	 * Sets number of iterations representing duration of simulation.
	 * @param scanner Scanner object for getting input.
	 * @return Integer number representing duration of simulation.
	 */
	private static int setIterations(Scanner scanner) {
		return getNumberFromUser(1, 1000000, "Set duration of the simulation (from 1 to 1000000): ", scanner);
	}
	
	/**
	 * Sets period of iterations of generating DisguisedEnemyUnit object. It has priority over generating EnemyUnit object if periods overlap.
	 * @param scanner Scanner object for getting input.
	 * @return Integer number representing period of iterations.
	 */
	private static int setDisguisedEnemyFreq(Scanner scanner) {
		return getNumberFromUser(1, 10, "Set disguised enemy unit generating period (from 1 to 10): ", scanner);
	}
	
	/**
	 * Sets period of iterations of generating EnemyUnit object. Generating DisguisedEnemyUnit object has priority over this if periods overlap.
	 * @param scanner Scanner object for getting input.
	 * @return Integer number representing period of iterations.
	 */
	private static int setEnemyFreq(Scanner scanner) {
		return getNumberFromUser(1, 10, "Set enemy units generating period (from 1 to 10): ", scanner);
	}
	
	/**
	 * Sets ArrayList of Gunner for Commander.
	 * @param scanner Scanner object for getting input.
	 * @return ArrayList<Gunner> representing Gunners under Commander's duty.
	 */
	private static ArrayList<IGunner> setGunners(Scanner scanner){
		int accuracy;
		
		int gunnersCount = getNumberFromUser(1, 5, "\nSet number of Gunners (from 1 to 5): ", scanner);
		ArrayList<IGunner> gunners = new ArrayList<IGunner>(gunnersCount);
		
		for(int i = 0; i < gunnersCount; i++) {
			System.out.println();
			
			accuracy = getNumberFromUser(0, 100, "Set accuracy of Gunner no."+ (i+1) +" in percentages (from 1 to 100): ", scanner);
			
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
		int trustLevel;
		
		int scoutsCount = getNumberFromUser(1, 5, "Set number of scouts (from 1 to 5): ", scanner);
		ArrayList<IScout> scouts = new ArrayList<IScout>(scoutsCount);
		
		Scout newScout;
		
		for(int i = 0; i < scoutsCount; i++) {
			System.out.println();
			
			movementRange = getNumberFromUser(1, 3, "Set movement speed of scout no." + (i+1) +"(from 1 to 3): ", scanner);
			visionRange = getNumberFromUser(5, 20, "Set vision range of Scout no."+ (i+1) +"(from 5 to 20): ", scanner);
			effectiveness = getNumberFromUser(1, 100, "Set effectiveness of Scout no."+ (i+1) +" in percentages (from 1 to 100): ", scanner);
			trustLevel = getNumberFromUser(1, 100, "Set initial trust level of Scout no."+ (i+1) +" in percentages (from 1 to 100): ", scanner);
			
			newScout = new Scout(movementRange, Map.getInstance().getRandomPosition(), effectiveness, trustLevel, visionRange, commander);
			scouts.add(newScout);
			Map.getInstance().placeUnitOnMap(newScout);
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
				System.out.println("Input number is beyond given range.");
				System.out.print(message);
				input = scanner.nextInt();
				scanner.nextLine();
			}
		}catch(Exception e) {
			System.out.println("Incorrect input type. Integer number is required.");
			scanner.nextLine();
			input = getNumberFromUser(min, max, message, scanner);
		}
		return input;
	}
}
