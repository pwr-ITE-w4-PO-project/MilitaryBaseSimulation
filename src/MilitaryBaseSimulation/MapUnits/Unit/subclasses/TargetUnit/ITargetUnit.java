package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.Scout;

public interface ITargetUnit {
	public void getDestroyed();
	public int getCount();
	public Scout getIdentifiedBy();
}
