package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.TargetUnit;
import MilitaryBaseSimulation.MoveGenerators.EnemyMoveGenerator;
import MilitaryBaseSimulation.Map.Map;
public class EnemyUnit extends TargetUnit {
	private int damage;
	private static int count;
	
	/**
	 * Constructor.
	 * @param movement Range Range of motion.
	 * @param position Initial placement on the map.
	 * @param damage Damage dealt.
	 */
	public EnemyUnit(int movementRange, int[] position, int damage) {
		super(movementRange, position);
		EnemyUnit.count++;
		this.damage = damage;
		this.unitChar = 'E';
		this.moveGenerator = new EnemyMoveGenerator();
	}
	
	@Override
	protected final int[] handlePositionBeyondMap(int[] newPosition) {
		if(newPosition[0] >= Map.getInstance().getUpperBoundaries()[0]) {
			MilitaryBaseSimulation.damageBase(damage);;
			//wywo³anie headquarters gdy baza zostanie zaatakowana
			return null;
		}
		else {
			int vectorY = this.position[1] - newPosition[1];
			newPosition[1] = this.position[1] + vectorY;
			return newPosition;
		}
	}
	
	/**
	 * Gets count.
	 * @return Number of instances.
	 */
	public static int getCount() {
		return EnemyUnit.count;	
	} 
	
	@Override
	public void getDestroyed() {
		EnemyUnit.count--;
		super.getDestroyed();
	}
	
	/**
	 * Gets the damage of the unit.
	 * @return Integer value representing unit's damage.
	 */
	public int getDamage() {
		return this.getDamage();
	}
}
