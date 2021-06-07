package MilitaryBaseSimulation.Militaries.Gunner;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IDestroyable;

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
	 */
	public void attack(IDestroyable unit) {
		if(MilitaryBaseSimulation.generateRandomEventHappening(this.accuracy))
			unit.getDestroyed();
	}
	
	/**
	 * Manages receiving report from Commander
	 * @param report String which contains info about attack
	 * @param unit Unit which will be attacked
	 */
	public void receive(ReportInfo report, IDestroyable unit) {
		if(report == ReportInfo.ATTACK)
			this.attack(unit);
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
