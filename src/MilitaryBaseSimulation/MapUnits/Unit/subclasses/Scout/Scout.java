package MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout;

import MilitaryBaseSimulation.MapUnits.Unit.Unit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.ITargetUnit;
import MilitaryBaseSimulation.Militaries.interfaces.IReceiver;
import MilitaryBaseSimulation.Militaries.interfaces.ISender;

public class Scout extends Unit implements ISender, IScout{
	public Scout(int movementRange, int[] position, int effectiveness, int trustLevel, int visionRange) {
		super(movementRange, position);
	}
	
	@Override
	protected final int[] handlePositionBeyondMap(int[] newPosition) {
		//ma poruszyc sie w przeciwna strone (this.position[0] = this.position[0] - newPosition[0] itd)
		 return null;
	}
	
	@Override
	public char getUnitChar() {
		return '0';
	}
	
	public void send(String report, ITargetUnit unit, IReceiver receiver) {} //ma wysylac raporty commanderowi
}
