package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit;

import MilitaryBaseSimulation.MapUnits.Unit.*;
import java.lang.reflect.Field;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.TargetUnit;
import MilitaryBaseSimulation.MoveGenerators.AlliesMoveGenerator;
public class NeutralUnit extends TargetUnit{
	/**
	 * Constructor.
	 * @param movementRange Range of motion.
	 * @param position Initial placement on the map.
	 */
	public NeutralUnit(int movementRange, int[] position) {
		super(movementRange, position);
		this.unitChar = 'N';
		NeutralUnit.count++;
		this.moveGenerator = new AlliesMoveGenerator();
	}
	static private int count;
	
	@Override
	protected final int[] handlePositionBeyondMap(int[] newPosition) {
		return null;
	}

	/**
	 * Gets count.
	 * @return Number of instances.
	 */
	public static int getCount() {
		return NeutralUnit.count;
	} 
	@Override
	public void getDestroyed() {
		NeutralUnit.count--;
		
		super.getDestroyed();
	}
}
