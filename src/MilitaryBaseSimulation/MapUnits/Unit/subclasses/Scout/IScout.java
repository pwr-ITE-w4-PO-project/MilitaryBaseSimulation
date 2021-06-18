package MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout;

/**
 * 
 * @author Mateusz Torski
 *
 */
public interface IScout {
	/**
	 * Changes trust level.
	 * @param value Value by which trust level is changed.
	 */
	public void modifyTrustLevel(int value);
	/**
	 * Gets the trust level.
	 * @return Integer value representing trust level.
	 */
	public int getTrustLevel();
}
