package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit;

import java.util.Random;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;

public class DisguisedEnemyUnit extends EnemyUnit{
	private static int count;
	private int disguiseResetProbability = 50;
	/**
	 * Constructor.
	 * @param movement Range Range of motion.
	 * @param position Initial placement on the map.
	 * @param damage Damage dealt.
	 */
	public DisguisedEnemyUnit(int movementRange, int[] position, int damage) {
		super(movementRange, position, damage);
		this.unitChar = 'D';
		DisguisedEnemyUnit.count++;
	}
	
	/**
	 * Gets count.
	 * @return Number of instances.
	 */
	public static int getCount() {
		return DisguisedEnemyUnit.count;
	} 	
	
	@Override
	public void getDestroyed() {
		DisguisedEnemyUnit.count--;
		super.getDestroyed();
	}
	
	/**
	 * Resets disguise with 50% chance. If triggered, changes isIdentified field to false,
	 * which forces scouts to identify the unit once again.
	 */
	private void tryResetDisguise() {
		Random random = new Random();
		
		if(random.nextInt(100) < disguiseResetProbability) {
			this.setIsIdentified(false);
		}
	}
	
	@Override
	public void move() {
		tryResetDisguise();
		super.move();
	}
}
