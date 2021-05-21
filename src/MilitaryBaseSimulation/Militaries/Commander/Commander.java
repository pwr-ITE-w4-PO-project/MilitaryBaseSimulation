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
	public int rating;
	private ArrayList<IGunner> gunners;
	private ArrayList<IScout> scouts;
	public Commander(ArrayList<IScout> scouts, ArrayList<IGunner> gunners) {
		this.gunners = gunners;
		this.scouts = scouts;
	}
	public void changeTrustLevel(Scout scout, int level) {
		scout.setTrustLevel(level);
	}
	
	public void commandAttack(ITargetUnit unit) {
		Random random = new Random();
		send("yes",unit,(IReceiver) gunners.get(random.nextInt(gunners.size())));
	}
	public void send(String report, ITargetUnit unit, IReceiver receiver) {
		receiver.receive(report,unit);
		
	}
	public void receive(String report, ITargetUnit unit) {
		Random random = new Random();
		if(unit.getIdentifiedBy().getTrustLevel()<=random.nextInt(100))
		{
			if(report=="Wykryto wrog¹ jednostkê!")
			{
				commandAttack(unit);
				if(unit instanceof EnemyUnit) {
					changeTrustLevel(unit.getIdentifiedBy(),3*random.nextInt(5));
				}else {
					changeTrustLevel(unit.getIdentifiedBy(),-3*random.nextInt(5));
				}
			}else if(report == "Wykryto neutraln¹ jednostkê!") {
				if(unit instanceof EnemyUnit) {
					changeTrustLevel(unit.getIdentifiedBy(),-3*random.nextInt(5));
				}else {
					changeTrustLevel(unit.getIdentifiedBy(), 3*random.nextInt(5));
				}
			}
		}else{
			if(report=="Wykryto wrog¹ jednostkê!") {
					if(unit instanceof EnemyUnit) {
						changeTrustLevel(unit.getIdentifiedBy(),3*random.nextInt(5));
					}else {
						changeTrustLevel(unit.getIdentifiedBy(),-3*random.nextInt(5));
					}
			}
			else if(report == "Wykryto neutraln¹ jednostkê!") {
				commandAttack(unit);
				if(unit instanceof EnemyUnit) {
					changeTrustLevel(unit.getIdentifiedBy(),-3*random.nextInt(5));
				}else {
					changeTrustLevel(unit.getIdentifiedBy(), 3*random.nextInt(5));
				}
			}
		}
	}
}
