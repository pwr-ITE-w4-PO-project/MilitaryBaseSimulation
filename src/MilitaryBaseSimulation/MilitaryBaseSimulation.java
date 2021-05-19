package MilitaryBaseSimulation;

import MilitaryBaseSimulation.Map.Map;
import MilitaryBaseSimulation.MapUnits.Unit.IUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.NeutralUnit.NeutralUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit.DisguisedEnemyUnit;
import MilitaryBaseSimulation.Militaries.Commander.Commander;
import MilitaryBaseSimulation.Militaries.Gunner.*;
import MilitaryBaseSimulation.Militaries.Headquarters.Headquarters;

import java.util.*;

public class MilitaryBaseSimulation {

	public static void main(String[] args) {
		buildSimulation();
		//run();
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
	 * Sets number of base hit points.
	 * @param scanner Scanner object for getting input.
	 * @return Integer number representing base' hit points.
	 */
	private static int setBaseHP(Scanner scanner) {
		return getNumberFromUser(100, 1000000, "Podaj pocz¹tkow¹ iloœæ punktów trafieñ bazy (od 100 do 1000000): ", scanner);
	}
	
	/**
	 * Sets number of iterations representing duration of simulation.
	 * @param scanner Scanner object for getting input.
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
		int trustLevel;
		
		int scoutsCount = getNumberFromUser(1, 5, "Podaj iloœæ Scoutów (od 1 do 5): ", scanner);
		ArrayList<IScout> scouts = new ArrayList<IScout>(scoutsCount);
		
		Scout newScout;
		
		for(int i = 0; i < scoutsCount; i++) {
			System.out.println();
			
			movementRange = getNumberFromUser(1, 3, "Podaj prêdkoœæ Scouta #" + (i+1) +"(od 1 do 3): ", scanner);
			visionRange = getNumberFromUser(5, 20, "Podaj zasiêg widzenia Scouta #"+ (i+1) +"(od 5 do 20): ", scanner);
			effectiveness = getNumberFromUser(0, 100, "Podaj efektywnoœæ Scouta #"+ (i+1) +" w procentach (od 0 do 100): ", scanner);
			trustLevel = getNumberFromUser(0, 100, "Podaj zaufanie do Scouta #"+ (i+1) +" w procentach (od 0 do 100): ", scanner);
			
			newScout = new Scout(movementRange, Map.getInstance().getRandomPosition(), effectiveness, trustLevel, visionRange);
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
