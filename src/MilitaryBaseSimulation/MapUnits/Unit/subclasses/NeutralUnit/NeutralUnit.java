package MilitaryBaseSimulation.MapUnits.Unit.subclasses.NeutralUnit;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.TargetUnit;
public class NeutralUnit extends TargetUnit{
	public NeutralUnit(int movementRange, int[] position) {
		super(movementRange, position);
		count++;
	}
	static int count;
	
	@Override
	protected final int[] handlePositionBeyondMap(int[] newPosition) {
		//ma zniknac z mapy
		return null;
	}
	@Override
	public char getUnitChar() {
		return '0';
	}
	//zwraca statyczna ilosc obiektow
	public int getCount() {
		return count;
	} 
	public void getDestroyed() {
		
	} //usuniecie z mapy + wywolanie oceniania commandera	
}
