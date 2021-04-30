package MilitaryBaseSimulation.EnemyUnit;
import MilitaryBaseSimulation.TargetUnit.*;

public class EnemyUnit extends TargetUnit {
	public EnemyUnit(int movementRange, int[] position) {
		super(movementRange, position);
	}
	
	public int getCount() {return 0;} //zwraca statyczna ilosc obiektow
	public void getDestroyed() {}//usuwanie z mapy + wywolanie oceniania commandera
}
