package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit;
import MilitaryBaseSimulation.MapUnits.Unit.Unit;

public abstract class TargetUnit extends Unit implements ITargetUnit{
	public TargetUnit(int movementRange, int[] position) {
		super(movementRange, position);
	}
	boolean isCorrectlyIdentified;
	static int count;
	
	public int getCount(){
		return 0;
	}
	
	public void getDestroyed() {
		this.disappearFromMap();
	}
}
