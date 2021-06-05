package MilitaryBaseSimulation.Militaries.Commander;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.IScout;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.ITargetUnit;
import MilitaryBaseSimulation.Militaries.interfaces.IReceiver;
import MilitaryBaseSimulation.Militaries.interfaces.ISender;

public interface ICommander extends ISender, IReceiver {
	public void changeTrustLevel(IScout scout, int level);
	public void commandAttack(ITargetUnit unit);
	public void send(String report, ITargetUnit unit, IReceiver receiver);
	public void recevieRating(int rate);
	public void receive(String report, ITargetUnit unit);
	
}
