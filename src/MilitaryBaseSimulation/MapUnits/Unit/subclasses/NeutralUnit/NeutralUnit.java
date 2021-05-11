package MilitaryBaseSimulation.MapUnits.Unit.subclasses.NeutralUnit;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.TargetUnit;
public class NeutralUnit extends TargetUnit{
	public NeutralUnit(int movementRange, int[] position) {
		super(movementRange, position);
	}
	
	
	@Override
	protected final int[] handlePositionBeyondMap(int[] newPosition) {
		//ma zniknac z mapy
		return null;
	}
	@Override
	protected char printUnit() {
		return '0';
	}
	public int getCount() {return 0;} //ma zwracac statyczna ilosc obiektow
	public void getDestroyed() {} //usuniecie z mapy + wywolanie oceniania commandera
}
