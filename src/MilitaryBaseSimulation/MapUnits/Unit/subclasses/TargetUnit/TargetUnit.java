package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit;
import MilitaryBaseSimulation.MapUnits.Unit.Unit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.*;

public abstract class TargetUnit extends Unit implements ITargetUnit{
	public TargetUnit(int movementRange, int[] position) {
		super(movementRange, position);
		count++;
	}
	boolean isCorrectlyIdentified;
	static int count;
	private Scout identifiedBy;
	
	public int getCount(){
		return count;
	}
	
	public void getDestroyed() {
		this.disappearFromMap();
	}
	
	public Scout getIdentifiedBy() {
		return identifiedBy;
	}
	public boolean getIsCorrectlyIdentified(){
		return isCorrectlyIdentified;
	}
}
