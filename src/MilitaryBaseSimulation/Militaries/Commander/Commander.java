package MilitaryBaseSimulation.Militaries.Commander;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.*;
import MilitaryBaseSimulation.Militaries.Gunner.IGunner;
//import MilitaryBaseSimulation.MapUnits.Unit.subclasses.NeutralUnit.*;

import java.util.Random;
import java.util.ArrayList;

public class Commander implements ICommander {
	private int rating;
	private Random rand = new Random();
	private ArrayList<IGunner> gunners;
	private ArrayList<IScout> scouts;
	/**
	 * Constructor
	 * @param scouts List of Scouts in simulation
	 * @param gunners List of Gunners in simulation
	 */
	public Commander(ArrayList<IScout> scouts, ArrayList<IGunner> gunners) {
		this.gunners = gunners;
		this.scouts = scouts;
	}
	/**
	 * Changes trust level of scout which sent report
	 * @param scout Scout which sent report
	 * @param level Value which will be added to scouts trustLevel
	 */
	public void changeTrustLevel(IScout scout, int level) {
		scout.modifyTrustLevel(level);
	}
	
	/**
	 * Commands random Gunner to attack a unit
	 * @param unit Unit which was chosen to be attacked
	 */
	private void commandAttack(IDestroyable unit) {
		if(gunners != null)
			gunners.get(rand.nextInt(gunners.size())).receive(ReportInfo.ATTACK,unit);
	}
	
	/**
	 * Manages receiving rating from Headquarters
	 * @param rate Value which will be added to commanders rating
	 */
	public void recevieRating(int rate)
	{
		this.rating+=rate;
	}
	
	public int getRating()
	{
		return rating;
	}
	
	/**
	 * Manages receiving report from scout
	 * @param report String which contains info about unit
	 * @param unit Unit detected by Scout
	 */
	public void receive(ReportInfo report, IIdentified unit) {
		if(MilitaryBaseSimulation.generateRandomEventHappening(unit.getIdentifiedBy().getTrustLevel())) {
			if(report == ReportInfo.ENEMY)
				commandAttack((IDestroyable)unit);
		}
		else {
			if(report == ReportInfo.NEUTRAL)
				commandAttack((IDestroyable)unit);
		}
	}
	
	/**
	 * @param report String which contains info about unit
	 * @param unit Unit detected by Scout
	 */
	public void manage(ReportInfo report, IIdentified unit)
	{
		IScout scout = unit.getIdentifiedBy();
		if(scout != null ) {
			int param = 0;
			if(report == ReportInfo.POSITIVE)
				if(unit.getIsCorrectlyIdentified())
					param = 1;
				else
					param = -1;
			else if(report == ReportInfo.NEGATIVE)
				if(unit.getIsCorrectlyIdentified())
					param = 2;
				else
					param = -2;	
			int level = param*(rand.nextInt(3)+2);
			changeTrustLevel(scout,level);	
		}
	}
}
