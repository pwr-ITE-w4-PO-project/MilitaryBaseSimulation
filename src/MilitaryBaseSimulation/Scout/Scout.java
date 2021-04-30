package MilitaryBaseSimulation.Scout;
import MilitaryBaseSimulation.Unit.Unit;
import MilitaryBaseSimulation.IMilitary.*;
import MilitaryBaseSimulation.TargetUnit.ITargetUnit;

public class Scout extends Unit implements ISender, IScout{
	public Scout(int movementRange, int[] position, int effectiveness, int trustLevel, int visionRange) {
		super(movementRange, position);
	}
	
	public void send(String report, ITargetUnit unit, IReceiver receiver) {} //ma wysylac raporty commanderowi
}
