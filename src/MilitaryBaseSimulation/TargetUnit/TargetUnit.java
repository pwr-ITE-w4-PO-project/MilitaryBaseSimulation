package MilitaryBaseSimulation.TargetUnit;
import  MilitaryBaseSimulation.Unit.Unit;

public abstract class TargetUnit extends Unit implements ITargetUnit{
	public TargetUnit(int movementRange, int[] position) {
		super(movementRange, position);
	}
}
