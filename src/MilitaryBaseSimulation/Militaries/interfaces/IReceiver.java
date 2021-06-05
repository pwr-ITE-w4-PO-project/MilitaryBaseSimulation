package MilitaryBaseSimulation.Militaries.interfaces;
import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.ITargetUnit;

public interface IReceiver {
	public void receive(ReportInfo report, ITargetUnit unit);
}
