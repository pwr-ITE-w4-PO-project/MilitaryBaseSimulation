package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.IScout;

public interface IIdentified {
	public IScout getIdentifiedBy();
	public boolean getIsCorrectlyIdentified();
}
