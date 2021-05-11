package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;

public class DisguisedEnemyUnit extends EnemyUnit{
	public DisguisedEnemyUnit(int movementRange, int[] position) {
		super(movementRange, position);
	}
	@Override
	protected char printUnit() {
		return '0';
	}
}
