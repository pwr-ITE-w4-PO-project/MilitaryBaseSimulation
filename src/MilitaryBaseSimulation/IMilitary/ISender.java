package MilitaryBaseSimulation.IMilitary;
import MilitaryBaseSimulation.TargetUnit.ITargetUnit;

public interface ISender {
	public void send(String report, ITargetUnit unit, IReceiver receiver);
}
