package MilitaryBaseSimulation.Militaries.Gunner;

import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.IDestroyable;

public interface IGunner{
	public int getAccuracy();
	public void receive(ReportInfo report, IDestroyable unit);
}
