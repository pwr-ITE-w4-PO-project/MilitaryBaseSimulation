package MilitaryBaseSimulation.Militaries.Commander;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.*;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.*;
import MilitaryBaseSimulation.Militaries.Gunner.IGunner;
import MilitaryBaseSimulation.Militaries.interfaces.*;


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
		scout.trustLevel=level;
	}
	
	public void commandAttack(ITargetUnit unit) {
		int bestI = 0;
		int bestA = 0;
		for(int i =0;i<gunners.size();i++)
		{
			if(gunners.get(i).getAccuracy() > bestA)
			{
				bestI=i;
				bestA=gunners.get(i).getAccuracy();
			}
		}
		send("yes",unit,(IReceiver) gunners.get(bestI));
	}
	public void send(String report, ITargetUnit unit, IReceiver receiver) {
		receiver.receive(report,unit);
		
	}
	public void receive(String report, ITargetUnit unit) {
		// czekam na strukturê raportu, póki co implementujê randomowo
		Random rand = new Random();
		int n = rand.nextInt(100);
		n++;
		if(n>50)
		{ 
			commandAttack(unit);
		}
	}
}
