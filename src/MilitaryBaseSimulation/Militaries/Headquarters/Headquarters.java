package MilitaryBaseSimulation.Militaries.Headquarters;

//import MilitaryBaseSimulation.TargetUnit.ITargetUnit;
import MilitaryBaseSimulation.Militaries.Commander.*;
import MilitaryBaseSimulation.MilitaryBaseSimulation;

public class Headquarters {//implements ISender{
	//public void send(String report, ITargetUnit unit, IReceiver receiver) {};
	//private Commander commander = MilitaryBaseSimulation.commander;
	Headquarters() {}
	
	/**
	 * Changes rating of Commander
	 * @param rate Value which will be added to commander.rating
	 * @param commander Commander, which rating will be changed
	 */
	public void rateCommander(int rate, Commander commander)
	{
		commander.rating+=rate;
	}
	
}
