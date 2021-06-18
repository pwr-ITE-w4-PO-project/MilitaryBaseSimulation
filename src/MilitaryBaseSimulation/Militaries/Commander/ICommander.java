package MilitaryBaseSimulation.Militaries.Commander;

import MilitaryBaseSimulation.Militaries.Commander.interfaces.IRatable;
import MilitaryBaseSimulation.Militaries.Commander.interfaces.IReportReceiver;

public interface ICommander extends IReportReceiver, IRatable {
	/**
	 * Gets rating value.
	 * @return Integer value representing rating.
	 */
	public int getRating();
}
