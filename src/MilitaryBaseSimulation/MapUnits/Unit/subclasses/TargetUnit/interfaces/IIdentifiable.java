package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.IScout;

/**
 * 
 * @author Przemys³aw Ma³ecki
 *
 */
public interface IIdentifiable {
	/**
	 * Sets the isCorrectylIdentified field.
	 * @param value Value to set.
	 */
	public void setIsCorrectlyIdentified(boolean value);
	/**
	 * Gets the isIdentifed field.
	 * @return boolean value representing identification.
	 */
	public boolean getIsIdentified();
	/**
	 * Sets the isIdentified field.
	 * @param value Value to set.
	 */
	public void setIsIdentified(boolean value);
	/**
	 * Sets the identifiedBy field.
	 * @param scout Scout who identified.
	 */
	public void setIdentifiedBy(IScout scout);
}
