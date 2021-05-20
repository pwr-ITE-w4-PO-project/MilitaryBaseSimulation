package MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout;

import MilitaryBaseSimulation.MapUnits.Unit.Unit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.ITargetUnit;
import MilitaryBaseSimulation.Militaries.interfaces.IReceiver;
import MilitaryBaseSimulation.Militaries.interfaces.ISender;

public class Scout extends Unit implements ISender, IScout{
	public Scout(int movementRange, int[] position, int effectiveness, int trustLevel, int visionRange) {
		super(movementRange, position);
	}
	
	int trustLevel;
	int effectiveness;
	int visionRange;
	
	@Override
	protected final int[] handlePositionBeyondMap(int[] newPosition) {
		int vectorX = this.position[0] - newPosition[0];
		int vectorY = this.position[1]-newPosition[1];
		this.position[0]+=vectorX;
		this.position[1]+=vectorY;
		
		 return this.position;
	}
	
	@Override
	public char getUnitChar() {
		return '0';
	}
	
	public void send(String report, ITargetUnit unit, IReceiver receiver) {} //ma wysylac raporty commanderowi


	public void search() {
		
	}
	
}