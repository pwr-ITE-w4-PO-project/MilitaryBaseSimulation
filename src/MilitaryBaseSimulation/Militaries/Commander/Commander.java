package MilitaryBaseSimulation.Militaries.Commander;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.IScout;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.ITargetUnit;
import MilitaryBaseSimulation.Militaries.Gunner.IGunner;
import MilitaryBaseSimulation.Militaries.interfaces.*;

import java.util.ArrayList;

public class Commander implements ISender, IReceiver{
	public Commander(ArrayList<IScout> scouts, ArrayList<IGunner> gunners) {
		
	}
	
	public void send(String report, ITargetUnit unit, IReceiver receiver) {}
	public void receive(String report, ITargetUnit unit) {}
}
