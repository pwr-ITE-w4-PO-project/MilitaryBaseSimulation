package MilitaryBaseSimulation.MapUnits.Unit;

/**
 * 
 * @author Przemys³aw Ma³ecki
 *
 */
public interface IUnit {
	public void move();
	public int[] getPosition();
	public char getUnitChar();
	public void refreshMovement();
}
