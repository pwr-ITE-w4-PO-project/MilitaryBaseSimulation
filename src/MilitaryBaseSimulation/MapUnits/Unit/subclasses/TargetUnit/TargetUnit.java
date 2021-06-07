package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit;
import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.MapUnits.Unit.Unit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout.IScout;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IDestroyable;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IIdentifiable;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IIdentified;

public abstract class TargetUnit extends Unit implements IDestroyable, IIdentified, IIdentifiable{
	private boolean isCorrectlyIdentified;
	private boolean isIdentified;
	private static int count;
	private IScout identifiedBy;
	
	/**
	 * Constructor.
	 * @param movement Range Range of motion.
	 * @param position Initial placement on the map.
	 */
	public TargetUnit(int movementRange, int[] position) {
		super(movementRange, position);
		TargetUnit.count++;
		this.isIdentified = false;
	}
	/**
	 * Gets count.
	 * @return Number of instances.
	 */
	public static int getCount(){
		return TargetUnit.count;
	}
	/**
	 * Occurs when unit is shot by gunner.
	 */
	public void getDestroyed() {
		MilitaryBaseSimulation.getHeadquarters().manageDeathInfo(this);
		super.disappearFromMap();
	}
	/**
	 *  Gets identifiedBy.
	 *  @return Scout who identified the unit.
	 */
	public IScout getIdentifiedBy() {
		return identifiedBy;
	}
	
	public void setIdentifiedBy(IScout scout) {
		this.identifiedBy = scout;
	}
	/**
	 *  Gets isCorrectlyIdentified.
	 *  @return Boolean value representing if the unit was correctly identified.
	 */	
	public boolean getIsCorrectlyIdentified(){
		return isCorrectlyIdentified;
	}
	/**
	 * Sets isCorrectlyIdentified.
	 * @param value Identification result.
	 */
	public void setIsCorrectlyIdentified(boolean value) {
		this.isCorrectlyIdentified = value;
	}
	
	/**
	 * Sets isIdentified.
	 * @param value Representing if unit was identified.
	 */
	public void setIsIdentified(boolean value) {
		this.isIdentified = value;
	}
	/**
	 *  Gets isIdetified.
	 *  @return Boolean value representing if the unit was already identified.
	 */	
	public boolean getIsIdentified(){
		return isIdentified;
	}
}
