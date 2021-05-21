package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.Map.Map;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.TargetUnit;
import MilitaryBaseSimulation.Militaries.Headquarters.Headquarters;

public class EnemyUnit extends TargetUnit {
	/**
	 * Constructor.
	 * @param movement Range Range of motion.
	 * @param position Initial placement on the map.
	 * @param damage Damage dealt.
	 */
	public EnemyUnit(int movementRange, int[] position, int damage) {
		super(movementRange, position);
		count++;
		this.damage=damage;
	}
	
	private int damage;
	static int count;
	
	
	@Override
	protected final int[] handlePositionBeyondMap(int[] newPosition) {
		if(newPosition[0]>100) {
			MilitaryBaseSimulation.damageBase(damage);;
			//wywo³anie headquarters gdy baza zostanie zaatakowana
			return null;
		}
		else {
			int vectorX = this.position[0] - newPosition[0];
			int vectorY = this.position[1]-newPosition[1];
			newPosition[0] = this.position[0]+vectorX;
			newPosition[1] = this.position[1]+vectorY;
			return newPosition;
		}
	}
	
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
	@Override
	public void getDestroyed() {
		count--;
		super.getDestroyed();
	}
}
