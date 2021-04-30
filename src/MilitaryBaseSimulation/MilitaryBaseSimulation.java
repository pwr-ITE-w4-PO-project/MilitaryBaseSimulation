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
	
	//private fields for simulation control
	private int enemyFreq;
	private int disguiedEnemyFreq;
	private int iterations;
	
	//map of used - may be delegated to class later
	private Unit[][] unitMap;
	
	public static void buildSimulation() {
		Scanner scanner = new Scanner(System.in); //zewnetrzny scanner jest uzywany, by ominac bug
		
		commander = new Commander(
				setScouts(scanner), 
				setGunners(scanner)
				);
		
		scanner.close();
	}
	
	public static void run() {
		
	}
	
	private static ArrayList<IGunner> setGunners(Scanner scanner){
		int accuracy;
		
		int gunnersCount = getNumberFromUser(1, 5, "\nPodaj iloœæ Gunnerów (od "+ 1 + " do " + 5 + "): ", scanner);
		ArrayList<IGunner> gunners = new ArrayList<IGunner>();
		
		for(int i = 0; i < gunnersCount; i++) {
			System.out.println();
			
			accuracy = getNumberFromUser(0, 100, "Podaj celnoœæ Gunnera #"+ (i+1) +" w procentach (od "+ 0 + " do " + 100 + "): ", scanner);
			
			gunners.add(new Gunner(accuracy));
		}
		
		return gunners;
	}
	
	
	/**
	 * Sets ArrayList of Scout for Commander.
	 * @return ArrayList<Scout>
	 */
	private static ArrayList<IScout> setScouts(Scanner scanner){
		int movementRange;
		int visionRange;
		int effectiveness;
		int position[];
		int trustLevel;
		
		int scoutsCount = getNumberFromUser(1, 5, "Podaj iloœæ Scoutów (od "+ 1 + " do " + 5 + "): ", scanner);
		ArrayList<IScout> scouts = new ArrayList<IScout>(scoutsCount);
		
		for(int i = 0; i < scoutsCount; i++) {
			System.out.println();
			
			movementRange = getNumberFromUser(1, 3, "Podaj prêdkoœæ Scouta #" + (i+1) +"(od "+ 1 + " do " + 3 + "): ", scanner);
			visionRange = getNumberFromUser(5, 20, "Podaj zasiêg widzenia Scouta #"+ (i+1) +"(od "+ 5 + " do " + 20 + "): ", scanner);
			effectiveness = getNumberFromUser(0, 100, "Podaj efektywnoœæ Scouta #"+ (i+1) +" w procentach (od "+ 0 + " do " + 100 + "): ", scanner);
			trustLevel = getNumberFromUser(0, 100, "Podaj zaufanie do Scouta #"+ (i+1) +" w procentach (od "+ 0 + " do " + 100 + "): ", scanner);
			position = new int[2];
			position[0] = position[1] = 0; //zmienic pozniej na pozycje na mapie
			
			scouts.add(new Scout(movementRange, position, effectiveness, trustLevel, visionRange));
		}
		
		return scouts;
	}
	
	/**
	 * Gets number in range [min, max] from the user.
	 * @return Number in range [min, max].
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
