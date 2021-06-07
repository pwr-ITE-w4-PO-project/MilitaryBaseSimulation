package MilitaryBaseSimulation.Militaries.Commander.interfaces;

import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IIdentified;

public interface IRatable {
	public void recevieRating(int rate);
	public void manage(ReportInfo report, IIdentified unit);
}
