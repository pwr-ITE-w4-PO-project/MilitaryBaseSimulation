package MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit;

import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.TargetUnit;

public class EnemyUnit extends TargetUnit {
	public EnemyUnit(int movementRange, int[] position, int damage) {
		super(movementRange, position);
	}
	
	int damage;
	static int count;
	
	@Override
	protected final int[] handlePositionBeyondMap(int[] newPosition) {
		//jest dotrze do bazy - zadanie obrazen
		//jesli przekroczy granice g�ra/d� - ma ruszyc w przeciwna strone
		return null;
	}
	
	@Override
	public char getUnitChar() {
		return '0';
	}
	public int getCount() {return 0;} //zwraca statyczna ilosc obiektow
	public void getDestroyed() {
		
	}//usuwanie z mapy + wywolanie oceniania commandera
}
