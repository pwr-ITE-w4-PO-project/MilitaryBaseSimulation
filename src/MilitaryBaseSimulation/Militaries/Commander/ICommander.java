package MilitaryBaseSimulation.Militaries.Commander;

import MilitaryBaseSimulation.Militaries.Commander.interfaces.IRatable;
import MilitaryBaseSimulation.Militaries.Commander.interfaces.IReportReceiver;

public interface ICommander extends IReportReceiver, IRatable {
	public int getRating();
}
