package MilitaryBaseSimulation.Militaries.Headquarters;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.IIdentified;

public interface IHeadquarters {
	public void manageDeathInfo(IIdentified destroyedUnit);
	public void manageBaseAttack(IIdentified unit);
	
}
