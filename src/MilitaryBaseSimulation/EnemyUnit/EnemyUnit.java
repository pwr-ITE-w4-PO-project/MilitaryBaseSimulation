package MilitaryBaseSimulation.EnemyUnit;
import MilitaryBaseSimulation.TargetUnit.*;

public class EnemyUnit extends TargetUnit {
	public EnemyUnit(int movementRange, int[] position) {
		super(movementRange, position);
	}
	
	@Override
	protected final int[] handlePositionBeyondMap(int[] newPosition) {
		//jest dotrze do bazy - zadanie obrazen
		//jesli przekroczy granice góra/dó³ - ma ruszyc w przeciwna strone
		return null;
	}
	
	@Override
	protected char printUnit() {
		return '0';
	}
	public int getCount() {return 0;} //zwraca statyczna ilosc obiektow
	public void getDestroyed() {}//usuwanie z mapy + wywolanie oceniania commandera
}
