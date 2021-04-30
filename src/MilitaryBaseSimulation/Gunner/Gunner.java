package MilitaryBaseSimulation.Gunner;
import MilitaryBaseSimulation.IMilitary.IReceiver;
import MilitaryBaseSimulation.TargetUnit.*;

public class Gunner implements IReceiver, IGunner{
	public Gunner(int accuracy) {
		
	}
	
	public void receive(String report, ITargetUnit unit) {}//otrzymanie rozkazu ataku
}
