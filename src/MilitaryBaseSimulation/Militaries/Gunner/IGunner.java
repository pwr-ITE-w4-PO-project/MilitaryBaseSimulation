package MilitaryBaseSimulation.Militaries.Gunner;

import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IDestroyable;

/**
 * 
 * @author Przemys³aw Ma³ecki
 *
 */
public interface IGunner{
	/**
	 * Gets the accuracy.
	 * @return Integer value representing accuracy.
	 */
	public int getAccuracy();
	/**
	 * Receives report and handles it.
	 * @param report Report to handle.
	 * @param unit Unit to attack.
	 */
	public void receive(ReportInfo report, IDestroyable unit);
}
