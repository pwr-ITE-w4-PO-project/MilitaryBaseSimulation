package MilitaryBaseSimulation.Militaries.Commander.interfaces;

import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IIdentified;

/**
 * 
 * @author Bartosz S³omowicz
 *
 */
public interface IReportReceiver {
	/**
	 * Receives report and handles it.
	 * @param report Report to handle.
	 * @param unit Unit reported.
	 */
	public void receive(ReportInfo report, IIdentified unit);
}
