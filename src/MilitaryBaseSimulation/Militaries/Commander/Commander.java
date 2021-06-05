package MilitaryBaseSimulation.Militaries.Commander;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;
import MilitaryBaseSimulation.Militaries.Gunner.IGunner;
import MilitaryBaseSimulation.Militaries.interfaces.*;
//import MilitaryBaseSimulation.MapUnits.Unit.subclasses.NeutralUnit.*;

import java.util.Random;
import java.util.ArrayList;

public class Commander implements ICommander {
	private int rating;
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
	private void commandAttack(ITargetUnit unit) {
		Random random = new Random();
		send(ReportInfo.ATTACK,unit,gunners.get(random.nextInt(gunners.size())));
	}
	
	/**
	 * Sends report to Gunner
	 * @param report String which contains info about unit
	 * @param unit unit detected by Scout
	 * @param receiver Gunner which will get report
	 */
	public void send(ReportInfo report, ITargetUnit unit, IReceiver receiver) {
		receiver.receive(report,unit);
	}
	
	/**
	 * Manages receiving rating from Headquarters
	 * @param rate Value which will be added to commanders rating
	 */
	public void recevieRating(int rate)
	{
		this.rating+=rate;
	}
	
	/**
	 * Manages receiving report from scout
	 * @param report String which contains info about unit
	 * @param unit Unit detected by Scout
	 */
	public void receive(ReportInfo report, ITargetUnit unit) {
		if(report == ReportInfo.HQ_INFO){
			
		}
		//Scout's report case
		else if(report == ReportInfo.SCOUT) {
			if(MilitaryBaseSimulation.generateRandomEventHappening(unit.getIdentifiedBy().getTrustLevel())) {
				if(unit instanceof EnemyUnit)
					commandAttack(unit);
			}
			else {
				if(unit instanceof NeutralUnit)
					commandAttack(unit);
			}
		}
	}
}
