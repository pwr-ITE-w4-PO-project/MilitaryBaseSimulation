package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.IScout;

public interface ITargetUnit {
	public void getDestroyed();
	public IScout getIdentifiedBy();
	public boolean getIsCorrectlyIdentified();
	public void setIsCorrectlyIdentified(boolean value);
	public boolean getIsIdentified();
	public void setIsIdentified(boolean value);
	public void setIdentifiedBy(IScout scout);
}
