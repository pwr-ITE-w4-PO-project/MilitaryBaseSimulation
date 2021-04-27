package MilitaryBaseSimulation.Scout;
import MilitaryBaseSimulation.Unit.Unit;
import MilitaryBaseSimulation.IMilitary.*;
import MilitaryBaseSimulation.TargetUnit.ITargetUnit;

public class Scout extends Unit implements ISender{
	public void send(String report, ITargetUnit unit, IReceiver receiver) {} //ma wysylac raporty commanderowi
}
