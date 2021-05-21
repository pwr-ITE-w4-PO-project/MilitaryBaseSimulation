package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;

public class DisguisedEnemyUnit extends EnemyUnit{
	/**
	 * Constructor.
	 * @param movement Range Range of motion.
	 * @param position Initial placement on the map.
	 * @param damage Damage dealt.
	 */
	public DisguisedEnemyUnit(int movementRange, int[] position, int damage) {
		super(movementRange, position, damage);
		count++;
	}
	
	static int count;
	
	@Override
	public char getUnitChar() {
		return '0';
	}
	
	/**
	 * Gets count.
	 * @return Number of instances.
	 */
	public static int getCount() {
		return count;
	} 	
}
