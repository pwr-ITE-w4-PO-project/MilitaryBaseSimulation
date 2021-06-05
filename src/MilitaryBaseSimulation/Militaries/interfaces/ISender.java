package MilitaryBaseSimulation.Militaries.interfaces;
import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.ITargetUnit;

public interface ISender {
	public void send(ReportInfo report, ITargetUnit unit, IReceiver receiver);
	
}
