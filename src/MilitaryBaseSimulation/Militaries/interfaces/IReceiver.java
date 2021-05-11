package MilitaryBaseSimulation.Militaries.interfaces;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.ITargetUnit;

public interface IReceiver {
	public void receive(String report, ITargetUnit unit);
}
