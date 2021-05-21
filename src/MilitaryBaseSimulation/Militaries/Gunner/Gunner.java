package MilitaryBaseSimulation.Militaries.Gunner;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.ITargetUnit;
import MilitaryBaseSimulation.Militaries.interfaces.IReceiver;
import java.util.Random;

public class Gunner implements IReceiver, IGunner{
	public int accuracy;
	
	public Gunner(int accuracy) {
		this.accuracy = accuracy;
	}
	
	public void attack(ITargetUnit unit, int accuracy) {
		Random rand = new Random();
		int n = rand.nextInt(100);
		n++;
		if(n>accuracy)
			unit.getDestroyed();
	}
	
	public void receive(String report, ITargetUnit unit) {
		if(report == "yes")
			this.attack(unit,accuracy);
	}
	

	public int getAccuracy()
	{
		return accuracy;
	}
}
