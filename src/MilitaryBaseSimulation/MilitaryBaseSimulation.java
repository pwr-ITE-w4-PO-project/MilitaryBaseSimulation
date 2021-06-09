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

import MilitaryBaseSimulation.Militaries.Gunner.*;
import MilitaryBaseSimulation.Militaries.Headquarters.Headquarters;
import MilitaryBaseSimulation.Militaries.Headquarters.IHeadquarters;

import java.io.FileWriter;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MilitaryBaseSimulation {

	public static void main(String[] args) {

		gui = new GUI(Map.getInstance());
		
		if(args.length > 0) { //below code handles command line arguments
			int[] argsInt = new int[args.length];
			//converts args to integers and exits if unsuccessful
			for(int i = 0; i<args.length; i++) {
				try {
					argsInt[i] = Integer.parseInt(args[i]);
				}catch(Exception e) {
					System.out.println("All of input arguments must be integer numbers.");
					System.out.println("Argument no." + i +", " + args[i] + ", was detected not to be integer number.");
					System.out.println("Please try again.");
					System.exit(0);
				}
			}
			
			int expectedArgsLength = fillGui(gui, argsInt);
			
			if(expectedArgsLength > args.length) {
				System.out.println("Provided input had too few arguments. The required missing input arguments must be provided manually in gui.");
			}
			else {
				buildSimulation();
				run();
			}
		}
	}
	//randomness handler
	private static Random random = new Random();
	
	//objects to access
	private static ICommander commander;
	private static IHeadquarters headquarters;
	private static List<IScout> scouts;
	private static GUI gui;
	
	//base hit points
	private static int baseHP;
	
	//private fields for simulation control
	private static int enemyFreq;
	private static int disguisedEnemyFreq;
	private static int iterations;
	
	/**
	 * Tries to get data at given position from args.
	 * @param position Position from which it is tried to extract data from.
	 * @param args Array of integers from which the data is tried to be extraced from.
	 * @return Data at given position or 0 if it fails, since empty gui fields have 0 as value by default.
	 */
	private static int tryGetData(int position, int[] args) {
		try {
			return args[position];
		}catch(Exception e) {
			return 0; //empty gui fields have 0 value as default
		}
	}
	
	/**
	 * Fills gui's input text fields with given arguments.
	 * @param gui Gui to fill data with.
	 * @param args Array of arguments.
	 * @return Integer value representing counted necessary input fields.
	 */
	private static int fillGui(GUI gui, int[] args) {
		int numberOfScouts = args[0];
		gui.setNumberOfScouts(numberOfScouts);
		
		int argsIterator = 0;
		for(int i = 0; i < numberOfScouts; i++, argsIterator += 4){
			gui.setScout(i, 
				tryGetData(1 + argsIterator, args), 
				tryGetData(2 + argsIterator, args), 
				tryGetData(3 + argsIterator, args),
				tryGetData(4 + argsIterator, args)
				);
		}
		argsIterator++;
		
		int numberOfGunners = tryGetData(argsIterator, args);
		gui.setNumberOfGunners(numberOfGunners);
		
		argsIterator++;
		for(int i = 0; i < numberOfGunners; i++, argsIterator++) {
			gui.setGunner(i, tryGetData(argsIterator, args));
		}
		
		gui.setBaseHP(tryGetData(argsIterator, args));
		argsIterator++;
		gui.setEnemy(tryGetData(argsIterator, args));
		argsIterator++;
		gui.setDisguisedEnemy(tryGetData(argsIterator, args));
		argsIterator++;
		gui.setIterations(tryGetData(argsIterator, args));
		argsIterator++;
		
		return argsIterator;
	}
	
	/**
	 * Starts simulation based on input parameters. Ends after reaching input iterations limit,
	 * or when base hp drops to 0.
	 */
	public static void run() {
		IUnit[][] map = Map.getInstance().getMap();
		
		List<IUnit> units = Map.getInstance().getAllUnits();
		gui.drawMap();
		
		try {
			FileWriter writer = new FileWriter("simulationData.csv");	
			writer.write("Iteration;Commander's rating;");
			for(int j = 0; j<scouts.size(); j++) writer.write("Scout no." + j + 1 + " trust level;");
			writer.write("Unit count; NeutralUnit count; EnemyUnit count; DisguisedEnemyUnit count");
			writer.write("\n");
			
			for(int i = 0; i<iterations;i++) {
				for(IUnit[] row : map) {
					for(IUnit unit : row) {
						try{
							unit.move();
							if(baseHP <= 0) {
								System.out.println("Base was destroyed. Simulation ended.");
								return;
							}
						}catch(Exception e) {
							System.out.println("Simulation approached unexpected error." + e.getMessage());
							writer.close();
							return;
						}
					}
				}
				gui.drawMap();
				TimeUnit.SECONDS.sleep(1);
				for(IUnit unit : units) unit.refreshMovement();
				
				writer.write(i + ";" + commander.getRating() + ";");
				for(int j = 0; j<scouts.size(); j++) writer.write(scouts.get(j).getTrustLevel() + ";");
				writer.write(Unit.getCount() + ";" + NeutralUnit.getCount() + ";" + EnemyUnit.getCount() + ";" + DisguisedEnemyUnit.getCount());
				writer.write("\n");
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
		
		List<Integer> gunnersParams = gui.getGunner();
		List<IGunner> gunners = new ArrayList<>();
		for(Integer acc : gunnersParams) {
			gunners.add(new Gunner(acc));
		}
		
		commander = new Commander(gunners);
		
		List<int[]> scoutsParams = gui.getScout();
		scouts = new ArrayList<>();
		IScout scout;
		for(int[] params : scoutsParams) {
			scout = new Scout(params[0], Map.getInstance().getRandomPosition(), params[1], params[2], params[3], commander);
			scouts.add(scout);
			Map.getInstance().placeUnitOnMap((IUnit)scout);
		}
		
		enemyFreq = gui.getEnemy();
		disguisedEnemyFreq = gui.getDisguisedEnemy();
		iterations = gui.getIterations();
		baseHP = gui.getBaseHP();
		
		headquarters = new Headquarters(commander);
		
		//filling 10% of 2d map with random units
		

		
		random = new Random();
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
	 * Generates boolean value representing occurence of
	 * random event happening with probabilty.
	 * @param probabilty The probability of the event.
	 * @return Boolean value representing whether event happened or not.
	 */
	public static boolean generateRandomEventHappening(int probabilty) {
		return random.nextInt(100) < probabilty;
	}
}
