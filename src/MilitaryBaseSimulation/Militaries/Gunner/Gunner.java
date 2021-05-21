package MilitaryBaseSimulation.Militaries.Gunner;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.ITargetUnit;
import MilitaryBaseSimulation.Militaries.interfaces.IReceiver;
import java.util.Random;

public class Gunner implements IReceiver, IGunner{
	private int accuracy;
	
	/**
	 * Constructor
	 * @param accuracy Value which will decide Gunner's accuracy
	 */
	public Gunner(int accuracy) {
		this.accuracy = accuracy;
	}
	
	/**
	 * 
	 * @param unit Unit which will be attacked by Gunner
	 * @param accuracy Propability of succeding the attack
	 */
	public void attack(ITargetUnit unit, int accuracy) {
		Random rand = new Random();
		int n = rand.nextInt(100);
		if(n<accuracy)
			unit.getDestroyed();
	}
	
	/**
	 * Manages receiving report from Commander
	 * @param report String which contains info about attack
	 * @param unit Unit which will be attacked
	 */
	public void receive(String report, ITargetUnit unit) {
		if(report == "yes")
			this.attack(unit,accuracy);
	}
	
	/**
	 * Gets the accuracy field
	 * For test purposes
	 */
	public int getAccuracy()
	{
		return accuracy;
	}
}
