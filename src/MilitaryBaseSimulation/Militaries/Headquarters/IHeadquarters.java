package MilitaryBaseSimulation.Militaries.Headquarters;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IIdentified;

/**
 * 
 * @author Bartosz S³omowicz
 *
 */
public interface IHeadquarters {
	/**
	 * Manages case when unit is destroyed.
	 * @param destroyedUnit Unit on which depends the handling.
	 */
	public void manageDeathInfo(IIdentified destroyedUnit);
	/**
	 * Manages base being attacked.
	 * @param unit Unit which attacked the base.
	 */
	public void manageBaseAttack(IIdentified unit);
	
}
