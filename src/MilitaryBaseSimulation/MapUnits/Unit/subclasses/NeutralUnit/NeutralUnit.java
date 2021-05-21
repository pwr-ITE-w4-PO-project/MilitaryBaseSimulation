package MilitaryBaseSimulation.MapUnits.Unit.subclasses.NeutralUnit;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.TargetUnit;
public class NeutralUnit extends TargetUnit{
	/**
	 * Constructor.
	 * @param movementRange Range of motion.
	 * @param position Initial placement on the map.
	 */
	public NeutralUnit(int movementRange, int[] position) {
		super(movementRange, position);
		count++;
	}
	static int count;
	
	@Override
	protected final int[] handlePositionBeyondMap(int[] newPosition) {

		return null;
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
