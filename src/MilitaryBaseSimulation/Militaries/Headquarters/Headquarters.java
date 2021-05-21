package MilitaryBaseSimulation.Militaries.Headquarters;

//import MilitaryBaseSimulation.TargetUnit.ITargetUnit;
import MilitaryBaseSimulation.Militaries.Commander.*;
import MilitaryBaseSimulation.MilitaryBaseSimulation;

public class Headquarters {//implements ISender{
	//public void send(String report, ITargetUnit unit, IReceiver receiver) {};
	//private Commander commander = MilitaryBaseSimulation.commander;
	Headquarters() {}
	public void rateCommander(int rate, Commander commander)
	{
		commander.rating+=rate;
	}
	
}
