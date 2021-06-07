package MilitaryBaseSimulation.Militaries.Headquarters;

import MilitaryBaseSimulation.Militaries.Commander.interfaces.IRatable;

import java.util.Random;

import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IIdentified;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;

public class Headquarters implements IHeadquarters{
	private IRatable commander;
	private Random rand = new Random();
	
	/**
	 * Constructor
	 * @param commander Subordinate Commander.
	 */
	public Headquarters(IRatable commander) {
		this.commander = commander;
	}
	
	/**
	 * Changes rating of Commander
	 * @param rate Value which will be added to commander.rating.
	 */
	private void rateCommander(int rate)
	{
		commander.recevieRating(rate);
	}
	
	/**
	 * Manages info after destroying unit.
	 * @param destroyedUnit Unit which was destroyed by Gunner.
	 */
	public void manageDeathInfo(IIdentified destroyedUnit)
	{
		if(this.commander != null) {
			if(destroyedUnit instanceof NeutralUnit)
			{
				rateCommander(-2*(rand.nextInt(5)+1));
				commander.manage(ReportInfo.NEGATIVE, destroyedUnit);
			}else{
				rateCommander(2*(rand.nextInt(5)+1));
				commander.manage(ReportInfo.POSITIVE, destroyedUnit);
			}
		}
	}
	
	/**
	 * 
	 */
	public void manageBaseAttack(IIdentified unit)
	{
		if(this.commander != null) {
			rateCommander(-2*(rand.nextInt(10)+1));
			commander.manage(ReportInfo.NEGATIVE, unit);
		}
		
	}
	
}
