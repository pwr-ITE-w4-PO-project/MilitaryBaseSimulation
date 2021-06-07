package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.IScout;

public interface IIdentifiable {
	public void setIsCorrectlyIdentified(boolean value);
	public boolean getIsIdentified();
	public void setIsIdentified(boolean value);
	public void setIdentifiedBy(IScout scout);
}
