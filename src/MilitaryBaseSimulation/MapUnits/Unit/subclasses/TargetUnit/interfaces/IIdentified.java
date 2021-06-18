package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.IScout;

/**
 * 
 * @author Przemys³aw Ma³ecki
 *
 */
public interface IIdentified {
	/**
	 * Gets the identifiedBy field.
	 * @return Scout who identified the unit.
	 */
	public IScout getIdentifiedBy();
	/**
	 * Gets the isCorrectlyIdentified field.
	 * @return Boolean value representing identification.
	 */
	public boolean getIsCorrectlyIdentified();
}
