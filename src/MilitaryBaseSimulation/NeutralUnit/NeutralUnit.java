package MilitaryBaseSimulation.NeutralUnit;
import MilitaryBaseSimulation.TargetUnit.*;

public class NeutralUnit extends TargetUnit{
	public NeutralUnit(int movementRange, int[] position) {
		super(movementRange, position);
	}
	
	
	@Override
	protected final void positionBeyondMapHandler(int[] newPosition) {
		//ma zniknac z mapy
	}
	public int getCount() {return 0;} //ma zwracac statyczna ilosc obiektow
	public void getDestroyed() {} //usuniecie z mapy + wywolanie oceniania commandera
}
