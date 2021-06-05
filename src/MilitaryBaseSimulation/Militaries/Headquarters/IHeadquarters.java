package MilitaryBaseSimulation.Militaries.Headquarters;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.ITargetUnit;
import MilitaryBaseSimulation.Militaries.interfaces.ISender;

public interface IHeadquarters extends ISender {
	public void manageDeathInfo(ITargetUnit destroyedUnit);
	public void receiveBaseAttack(ITargetUnit unit);
	
}
