package MilitaryBaseSimulation.Militaries.Headquarters;

//import MilitaryBaseSimulation.TargetUnit.ITargetUnit;
import MilitaryBaseSimulation.Militaries.Commander.*;
import MilitaryBaseSimulation.Militaries.interfaces.IReceiver;
import MilitaryBaseSimulation.Militaries.interfaces.ISender;

import java.util.Random;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;
//import MilitaryBaseSimulation.MapUnits.Unit.subclasses.NeutralUnit.*;
import java.util.Random;

public class Headquarters implements ISender{
	private ICommander commander;
	public Headquarters(ICommander commander) {
		this.commander = commander;
	}
	
	/**
	 * Changes rating of Commander
	 * @param rate Value which will be added to commander.rating
	 */
	private void rateCommander(int rate)
	{
		commander.recevieRating(rate);
	}
	
	/**
	 * Manages info after destroying unit
	 * @param destroyedUnit Unit which was destroyed by Gunner	
	 */
	public void manageDeathInfo(ITargetUnit destroyedUnit)
	{
		Random rand = new Random();
		if(destroyedUnit instanceof NeutralUnit)
		{
			rateCommander(-2*(rand.nextInt(5)+1));
			send("Enum1",destroyedUnit,commander);
		}else{
			rateCommander(2*(rand.nextInt(5)+1));
			send("Enum2",destroyedUnit,commander);
		}
		
	}
	
	public void receiveBaseAttack(ITargetUnit unit)
	{
		Random rand = new Random();
		MilitaryBaseSimulation.damageBase(unit.getDamage());
		rateCommander(-2*(rand.nextInt(10)+1));
		send("Enum1",unit,commander);
		
	}
	
	public void send(String report, ITargetUnit unit, IReceiver receiver) 
	{
		receiver.receive(report, unit);
	}
	
}
