package MilitaryBaseSimulation.Militaries.Gunner;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.ITargetUnit;

public class Gunner implements IGunner {
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
		if(MilitaryBaseSimulation.generateRandomEventHappening(accuracy))
			unit.getDestroyed();
	}
	
	/**
	 * Manages receiving report from Commander
	 * @param report String which contains info about attack
	 * @param unit Unit which will be attacked
	 */
	public void receive(ReportInfo report, ITargetUnit unit) {
		if(report == ReportInfo.ATTACK)
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
