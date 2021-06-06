package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.IScout;

public interface IDestroyable {
	public void getDestroyed();
	public IScout getIdentifiedBy();
}
