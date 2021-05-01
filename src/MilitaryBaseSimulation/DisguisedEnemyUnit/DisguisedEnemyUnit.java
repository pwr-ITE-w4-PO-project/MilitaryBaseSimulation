package MilitaryBaseSimulation.DisguisedEnemyUnit;
import MilitaryBaseSimulation.EnemyUnit.*;

public class DisguisedEnemyUnit extends EnemyUnit{
	public DisguisedEnemyUnit(int movementRange, int[] position) {
		super(movementRange, position);
	}
	@Override
	protected char printUnit() {
		return '0';
	}
}
