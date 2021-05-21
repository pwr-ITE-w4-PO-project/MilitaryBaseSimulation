package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit;
import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.Map.Map;
import MilitaryBaseSimulation.MapUnits.Unit.Unit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.*;

public abstract class TargetUnit extends Unit implements ITargetUnit{
	/**
	 * Constructor.
	 * @param movement Range Range of motion.
	 * @param position Initial placement on the map.
	 */
	public TargetUnit(int movementRange, int[] position) {
		super(movementRange, position);
		count++;
	}
	boolean isCorrectlyIdentified;
	static int count;
	private Scout identifiedBy;
	/**
	 * Gets count.
	 * @return Number of instances.
	 */
	public static int getCount(){
		return count;
	}
	/**
	 * Occurs when unit is shot by gunner.
	 */
	public void getDestroyed() {
		MilitaryBaseSimulation.getHeadquarters().deathInfo(this);
		Map.getInstance().removeUnitFromMap(this);
	}
	/**
	 *  Gets identifiedBy.
	 *  @return Scout who identificated the unit.
	 */
	public Scout getIdentifiedBy() {
		return identifiedBy;
	}
	/**
	 *  Gets isCorrectlyIdentified.
	 *  @return Identification result.
	 */	
	public boolean getIsCorrectlyIdentified(){
		return isCorrectlyIdentified;
	}
}
