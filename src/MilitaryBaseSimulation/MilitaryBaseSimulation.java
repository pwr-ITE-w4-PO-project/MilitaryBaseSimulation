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
		setScouts();
	}
	
	public static void run() {
		
	}
	
	/**
	 * Sets ArrayList of Scout for Commander.
	 * @return ArrayList<Scout>
	 */
	private static ArrayList<Scout> setScouts(){
		int scoutsCount = getNumberFromUser(1, 5);
		ArrayList<Scout> scouts = new ArrayList<Scout>(scoutsCount);
		
		for(int i = 0; i < scoutsCount; i++) {
			scouts.add(new Scout());
		}
		
		return scouts;
	}
	
	/**
	 * Gets number in range [min, max] from the user.
	 * @return Number in range [min, max].
	 */
	private static int getNumberFromUser(int min, int max){
		System.out.print("\nPodaj iloœæ Scoutów (od "+ min + " do " + max + "): ");
		Scanner scanner =  new Scanner(System.in);
		int input;
		try 
		{
			input = scanner.nextInt();
			while(input < min || input > max) {
				System.out.println("Podano nieprawid³owy zakres.");
				System.out.print("\nPodaj iloœæ Scoutów (od "+ min + " do " + max + "): ");
				input = scanner.nextInt();
			}
		}catch(Exception e) {
			System.out.println("Niepoprawne dane wejœciowe. Wprowadz liczbê ca³kowit¹!");
			input = getNumberFromUser(min, max);
		}
		scanner.close();
		return input;
	}
}
