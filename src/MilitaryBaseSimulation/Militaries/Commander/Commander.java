package MilitaryBaseSimulation.Militaries.Commander;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.*;
import MilitaryBaseSimulation.Militaries.Gunner.IGunner;
import MilitaryBaseSimulation.Militaries.interfaces.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.NeutralUnit.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.*;

import java.util.Random;
import java.util.ArrayList;

public class Commander implements ISender, IReceiver{
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
	public void changeTrustLevel(Scout scout, int level) {
		scout.setTrustLevel(level);
	}
	
	/**
	 * Commands random Gunner to attack a unit
	 * @param unit Unit which was chosen to be attacked
	 */
	public void commandAttack(ITargetUnit unit) {
		Random random = new Random();
		send("yes",unit,(IReceiver) gunners.get(random.nextInt(gunners.size())));
	}
	
	/**
	 * Sends report to Gunner
	 */
	public void send(String report, ITargetUnit unit, IReceiver receiver) {
		receiver.receive(report,unit);
	}
	
	public void recevieRating(int rate, ITargetUnit destroyedUnit)
	{
		Random random = new Random();
		this.rating+=rate;
			if(destroyedUnit.getIsCorrectlyIdentified()){
				changeTrustLevel(destroyedUnit.getIdentifiedBy(),3*(random.nextInt(5)+1));
			}else {
				changeTrustLevel(destroyedUnit.getIdentifiedBy(),-3*(random.nextInt(5)+1));
			}
	}
	
	/**
	 * Manages receiving report from scout
	 * @param report String which contains info about unit
	 * @param unit Unit detected by Scout
	 */
	public void receive(String report, ITargetUnit unit) {
		Random random = new Random();
		if(unit.getIdentifiedBy().getTrustLevel()<random.nextInt(100))
		{
			if(report=="Wykryto wrog¹ jednostkê!")
			{
				commandAttack(unit);
			}else if(report == "Wykryto neutraln¹ jednostkê!") {
			}
		}else{
			if(report=="Wykryto wrog¹ jednostkê!") {
			}
			else if(report == "Wykryto neutraln¹ jednostkê!") {
				commandAttack(unit);
			}
		}
	}
}
