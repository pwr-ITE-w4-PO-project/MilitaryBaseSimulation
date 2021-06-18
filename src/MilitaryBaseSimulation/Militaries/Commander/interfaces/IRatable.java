package MilitaryBaseSimulation.Militaries.Commander.interfaces;

import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IIdentified;

/**
 * 
 * @author Bartosz S³omowicz
 *
 */
public interface IRatable {
	/**
	 * Handles received rating.
	 * @param rate to handle.
	 */
	public void recevieRating(int rate);
	/**
	 * Manages report info about unit.
	 * @param report Report to handle.
	 * @param unit Unit which caused the flow.
	 */
	public void manage(ReportInfo report, IIdentified unit);
}
