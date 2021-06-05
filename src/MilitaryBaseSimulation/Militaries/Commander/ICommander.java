package MilitaryBaseSimulation.Militaries.Commander;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.IScout;
import MilitaryBaseSimulation.Militaries.interfaces.IReceiver;
import MilitaryBaseSimulation.Militaries.interfaces.ISender;

public interface ICommander extends ISender, IReceiver {
	public void changeTrustLevel(IScout scout, int level);
	public void recevieRating(int rate);
	
}
