package MilitaryBaseSimulation.IMilitary;
import MilitaryBaseSimulation.TargetUnit.ITargetUnit;

public interface IReceiver {
	public void receive(String report, ITargetUnit unit);
}
