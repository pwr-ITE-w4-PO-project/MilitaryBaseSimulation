package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.TargetUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IIdentified;
import MilitaryBaseSimulation.MoveGenerators.EnemyMoveGenerator;
import MilitaryBaseSimulation.Map.Map;

/**
 * 
 * @author Mateusz Torski
 *
 */
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
	/**
	 * If unit was about to move out of the map on the right side - damages base and removes unit.
	 * If on the top or the bottom - bounces off the the edge.
	 */
	protected final int[] handlePositionBeyondMap(int[] newPosition) {
		if(newPosition[0] >= Map.getInstance().getUpperBoundaries()[0]) {
			MilitaryBaseSimulation.damageBase(damage);
			MilitaryBaseSimulation.getHeadquarters().manageBaseAttack((IIdentified) this);
			return null;
		}
		else {
			//calculate reverse movement in Y direction.
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
