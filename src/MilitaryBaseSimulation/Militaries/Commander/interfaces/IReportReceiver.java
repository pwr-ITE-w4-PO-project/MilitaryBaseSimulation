package MilitaryBaseSimulation.Militaries.Commander.interfaces;

import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IIdentified;

public interface IReportReceiver {
	public void receive(ReportInfo report, IIdentified unit);
}
