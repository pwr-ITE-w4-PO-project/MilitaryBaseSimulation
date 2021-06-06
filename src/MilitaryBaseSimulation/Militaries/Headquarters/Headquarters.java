package MilitaryBaseSimulation.Militaries.Headquarters;

//import MilitaryBaseSimulation.TargetUnit.ITargetUnit;
import MilitaryBaseSimulation.Militaries.Commander.*;

import java.util.Random;

import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;
//import MilitaryBaseSimulation.MapUnits.Unit.subclasses.NeutralUnit.*;

public class Headquarters implements IHeadquarters{
	private ICommander commander;
	private Random rand = new Random();
	
	/**
	 * Constructor
	 * @param commander Subordinate Commander.
	 */
	public Headquarters(ICommander commander) {
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
