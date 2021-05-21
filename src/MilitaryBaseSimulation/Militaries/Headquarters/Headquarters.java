package MilitaryBaseSimulation.Militaries.Headquarters;

//import MilitaryBaseSimulation.TargetUnit.ITargetUnit;
import MilitaryBaseSimulation.Militaries.Commander.*;

import java.util.Random;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.NeutralUnit.*;
import java.util.Random;

public class Headquarters {//implements ISender{
	//public void send(String report, ITargetUnit unit, IReceiver receiver) {};
	//private Commander commander = MilitaryBaseSimulation.commander;
	Headquarters() {}
	
	/**
	 * Changes rating of Commander
	 * @param rate Value which will be added to commander.rating
	 * @param destroyedUnit Unit which was destroyed by Gunner
	 */
	private void rateCommander(int rate, ITargetUnit destroyedUnit)
	{
		MilitaryBaseSimulation.getCommander().recevieRating(rate, destroyedUnit);
	}
	
	/**
	 * Manages info after destroying unit
	 * @param destroyedUnit Unit which was destroyed by Gunner
	 */
	public void deathInfo(ITargetUnit destroyedUnit)
	{
		Random rand = new Random();
		if(destroyedUnit instanceof NeutralUnit)
		{
			rateCommander(-2*(rand.nextInt(5)+1), destroyedUnit);
		}else{
			rateCommander(2*(rand.nextInt(5)+1), destroyedUnit);
		}
		
	}
	
}
