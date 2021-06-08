package MilitaryBaseSimulation;

import MilitaryBaseSimulation.GUI.GUI;
import MilitaryBaseSimulation.Map.Map;
import MilitaryBaseSimulation.MapUnits.Unit.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit.DisguisedEnemyUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;
import MilitaryBaseSimulation.Militaries.Commander.Commander;
import MilitaryBaseSimulation.Militaries.Commander.ICommander;
import MilitaryBaseSimulation.Militaries.Commander.interfaces.IRatable;
import MilitaryBaseSimulation.Militaries.Commander.interfaces.IReportReceiver;
import MilitaryBaseSimulation.Militaries.Gunner.*;
import MilitaryBaseSimulation.Militaries.Headquarters.Headquarters;
import MilitaryBaseSimulation.Militaries.Headquarters.IHeadquarters;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.util.*;
import java.util.List;

public class MilitaryBaseSimulation {

	public static void main(String[] args) {
		
		GUI gui = new GUI();

		if(args.length > 0) {
			List<Integer> argsInt = new ArrayList<Integer>();
			//converts args to integers and exits if unsuccessful
			for(int i = 0; i<args.length; i++) {
				try {
					argsInt.add(i, Integer.parseInt(args[i]));
				}catch(Exception e) {
					System.out.println("All of input arguments must be integer numbers.");
					System.out.println("Argument no." + i +", " + args[i] + ", was detected not to be integer number.");
					System.out.println("Please try again.");
					System.exit(0);
				}
			}
			int scoutsCount = argsInt.get(0); //number of scouts
			int gunnersCount = argsInt.get(1+ scoutsCount*4); //number of gunners
			int expectedArgsLength = scoutsCount*4 + gunnersCount + 6;
			
			boolean enoughArgs = expectedArgsLength == argsInt.size() ? true : false;
			
			if(!enoughArgs) {
				System.out.println("Not enough input arguments. The rest must be filled in GUI.");
			}
			
			gui.setNumberOfScouts(scoutsCount);
			
			try {
				
			}catch(Exception e) {
				
			}
			
			//below is used to transfer args to existing input scheme
			String userInput = "";
			for(String value: args) {
				userInput += value + '\n';
			}
			ByteArrayInputStream input = new ByteArrayInputStream(userInput.getBytes());
			System.setIn(input);
		}
		
		//GUI x = new GUI();
		
		//buildSimulation();
		//run();
	}
	//randomness handler
	private static Random random = new Random();
	
	//objects to access
	private static ICommander commander;
	private static IHeadquarters headquarters;
	private static List<IScout> scouts;
	
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
		//List<IUnit> units = Map.getInstance().getAllUnits();
		IUnit[][] map = Map.getInstance().getMap();
		
		//below code must be changed to fit gui
		try {
			FileWriter writer = new FileWriter("simulationData.txt");	
			for(int i = 0; i<iterations;i++) {
				for(IUnit[] unitRow: map) {
					for(IUnit unit: unitRow) {
						if(unit != null) {
							try {
								unit.move();
							}catch(Exception e) {
								System.out.println("Simulation approached an error: " + e.getMessage());
								writer.close();
								return;
							}
							System.out.print(unit.getUnitChar());
						}
						else {
							System.out.print(" ");
						}
					
						if(baseHP <= 0) {
							saveSimulationData(writer, i);
							writer.close();
							return;
						}
					}
					System.out.print("#\n");
				}
				saveSimulationData(writer, i);
			}
				writer.close();
		}catch(Exception e) {
			System.out.println("Cannot access simulationData.txt, simulation data cannot be saved. " + e.getMessage());
		}
	}
	
	/**
	 * Builds simulation parameters and its actors via interacting with user.
	 */
	public static void buildSimulation() {
		Map.getInstance().initializeMap();;
		
		Scanner scanner = new Scanner(System.in); //outer Scanner is used to bypass bugs
		
		scouts = setScouts(scanner);
		
		commander = new Commander(
				setGunners(scanner)
				);
		
		System.out.println();
		
		enemyFreq = setEnemyFreq(scanner);
		disguisedEnemyFreq = setDisguisedEnemyFreq(scanner);
		iterations = setIterations(scanner);
		baseHP = setBaseHP(scanner);
		
		headquarters = new Headquarters(commander);
		
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
		baseHP -= damage;
	}
	
	/**
	 * Gets the commander.
	 * @return
	 */
	public static IRatable getCommander() {
		return commander;
	}
	
	/**
	 * Gets the headquarters.
	 * @return
	 */
	public static IHeadquarters getHeadquarters() {
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
			
			newScout = new Scout(movementRange, Map.getInstance().getRandomPosition(), effectiveness, trustLevel, visionRange, (IReportReceiver) commander);
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
	/**
	 * Generates boolean value representing occurence of
	 * random event happening with probabilty.
	 * @param probabilty The probability of the event.
	 * @return Boolean value representing whether event happened or not.
	 */
	public static boolean generateRandomEventHappening(int probabilty) {
		return random.nextInt(100) < probabilty;
	}
	
	/**
	 * Saves simulation data to simulationData.txt file.
	 * @param writer Writer which is used for writing to the file.
	 * @throws Exception Exception met when writer fails to write to file.
	 */
	private static void saveSimulationData(FileWriter writer, int iteration) throws Exception{
		writer.write("Iteration: " + iteration + "\n");
		writer.write("Number of units on map: " + Unit.getCount() +"\n");
		writer.write("Number of neutral units on map: " + NeutralUnit.getCount() + "\n");
		writer.write("Number of enemy units on map: " + EnemyUnit.getCount() +"\n");
		writer.write("Number of disguised enemy units on map: " + DisguisedEnemyUnit.getCount() +"\n");
		/*
		List<IScout> scouts = commander.getScouts();
		int size = scouts.size();
		for(int i = 0; i < size; i++){
			writer.write("Scout no." + i + " " trust level: + " scout.getTrustLevel());
		}
		writer.write("Commanders current rating: " + Commander.getRating() + "\n");
		*/
		writer.write("\n");
	}
}
