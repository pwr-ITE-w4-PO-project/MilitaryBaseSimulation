package MilitaryBaseSimulation.Commander;
import MilitaryBaseSimulation.IMilitary.*;
import MilitaryBaseSimulation.TargetUnit.*;
import MilitaryBaseSimulation.Gunner.*;
import MilitaryBaseSimulation.Scout.*;

import java.util.ArrayList;

public class Commander implements ISender, IReceiver{
	public Commander(ArrayList<IScout> scouts, ArrayList<IGunner> gunners) {
		
	}
	
	public void send(String report, ITargetUnit unit, IReceiver receiver) {}
	public void receive(String report, ITargetUnit unit) {}
}
