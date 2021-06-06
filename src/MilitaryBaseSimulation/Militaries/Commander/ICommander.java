package MilitaryBaseSimulation.Militaries.Commander;

import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.IScout;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.IIdentified;
import MilitaryBaseSimulation.Militaries.interfaces.IReceiver;

public interface ICommander extends IReceiver {
	public void changeTrustLevel(IScout scout, int level);
	public void recevieRating(int rate);
	public void manage(ReportInfo report, IIdentified unit);
	
}
