package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;

public class DisguisedEnemyUnit extends EnemyUnit{
	public DisguisedEnemyUnit(int movementRange, int[] position, int damage) {
		super(movementRange, position, damage);
		count++;
	}
	
	int count;
	
	@Override
	public char getUnitChar() {
		return '0';
	}
	
	//zwraca statyczna ilosc obiektow
	public int getCount() {
		return count;
	} 	
}
