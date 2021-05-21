package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit;
import MilitaryBaseSimulation.MapUnits.Unit.Unit;

public abstract class TargetUnit extends Unit implements ITargetUnit{
	public TargetUnit(int movementRange, int[] position) {
		super(movementRange, position);
		count++;
	}
	boolean isCorrectlyIdentified;
	static int count;

	
	public int getCount(){
		return count;
	}
	
	public void getDestroyed() {
		this.disappearFromMap();
	}
}
