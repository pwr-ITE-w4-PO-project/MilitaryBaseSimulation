package MilitaryBaseSimulation.Militaries.interfaces;
import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.IIdentified;
public interface IReceiver {
	public void receive(ReportInfo report, IIdentified unit);
}
