package MilitaryBaseSimulation.Militaries.Commander;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IDestroyable;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IIdentified;
import MilitaryBaseSimulation.Militaries.Gunner.IGunner;

import java.util.Random;
import java.util.List;

/**
 * 
 * @author Bartosz S³omowicz
 *
 */
public class Commander implements ICommander {
	private int rating;
	private Random rand = new Random();
	private List<IGunner> gunners;
	
	/**
	 * Constructor.
	 * @param scouts List of Scouts in simulation.
	 * @param gunners List of Gunners in simulation.
	 */
	public Commander(List<IGunner> gunners) {
		this.gunners = gunners;
	}
	
	/**
	 * Changes trust level of scout which sent report.
	 * @param scout Scout which sent report.
	 * @param level Value which will be added to scouts trustLevel.
	 */
	public void changeTrustLevel(IScout scout, int level) {
		scout.modifyTrustLevel(level);
	}
	
	/**
	 * Commands random Gunner to attack a unit.
	 * @param unit Unit which was chosen to be attacked.
	 */
	private void commandAttack(IDestroyable unit) {
		if(gunners != null)
			gunners.get(rand.nextInt(gunners.size())).receive(ReportInfo.ATTACK,unit);
	}
	
	/**
	 * Manages receiving rating from Headquarters.
	 * @param rate Value which will be added to commanders rating.
	 */
	public void recevieRating(int rate){
		this.rating+=rate;
	}
	
	/**
	 * Gets commander's rating.
	 * @return Integer value representing the rating.
	 */
	public int getRating(){
		return rating;
	}
	
	/**
	 * Manages receiving report from scout.
	 * @param report String which contains info about unit.
	 * @param unit Unit detected by Scout.
	 */
	public void receive(ReportInfo report, IIdentified unit) {
		//checks if scouts is trusted
		if(MilitaryBaseSimulation.generateRandomEventHappening(unit.getIdentifiedBy().getTrustLevel())) {
			
			//scout is trusted, and enemy is reported
			if(report == ReportInfo.ENEMY)
				commandAttack((IDestroyable)unit);
		}
		else {
			
			//scout is not trusted, so neutral units are found as enemies and vice versa
			if(report == ReportInfo.NEUTRAL)
				commandAttack((IDestroyable)unit);
		}
	}
	
	/**
	 * Manages report from headquarters, when commander is being rated.
	 * @param report String which contains info about unit.
	 * @param unit Unit detected by Scout.
	 */
	public void manage(ReportInfo report, IIdentified unit)
	{
		IScout scout = unit.getIdentifiedBy();
		
		//checks if unit was identified (the unit can traverse all map and not be identified)
		if(scout != null ) {
			
			int param = 0;
			
			//evaluate parameter's value
			if(report == ReportInfo.POSITIVE)
				param = unit.getIsCorrectlyIdentified() ? 1 : -1;
			
			else if(report == ReportInfo.NEGATIVE)
				param = unit.getIsCorrectlyIdentified() ? 2 : -2;

			int level = param * (rand.nextInt(3) + 1);
			this.changeTrustLevel(scout, level);	
		}
	}
}
