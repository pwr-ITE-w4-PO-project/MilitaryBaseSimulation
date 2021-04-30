package MilitaryBaseSimulation.Unit;

public abstract class Unit implements IUnit{
	public Unit(int movementRange, int[] position) {
		
	}
	
	public void move() {} //przypisanie pozycji z generatora + przesuniecie na mapie
	public int[] getPosition() {
		return null; //ma zwracac aktualna pozycje
	}
}
