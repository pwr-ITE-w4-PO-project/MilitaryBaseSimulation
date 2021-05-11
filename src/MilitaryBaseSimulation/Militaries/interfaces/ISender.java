package MilitaryBaseSimulation.Militaries.interfaces;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.ITargetUnit;

public interface ISender {
	public void send(String report, ITargetUnit unit, IReceiver receiver);
}
