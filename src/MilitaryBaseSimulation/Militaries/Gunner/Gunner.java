package MilitaryBaseSimulation.Militaries.Gunner;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.ITargetUnit;
import MilitaryBaseSimulation.Militaries.interfaces.IReceiver;

public class Gunner implements IReceiver, IGunner{
	public Gunner(int accuracy) {
		
	}
	
	public void receive(String report, ITargetUnit unit) {}//otrzymanie rozkazu ataku
}
