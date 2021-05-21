package MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout;

import MilitaryBaseSimulation.MapUnits.Unit.IUnit;
import MilitaryBaseSimulation.MapUnits.Unit.Unit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.NeutralUnit.NeutralUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.ITargetUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.TargetUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit.DisguisedEnemyUnit;
import MilitaryBaseSimulation.Militaries.Commander.Commander;
import MilitaryBaseSimulation.Militaries.interfaces.IReceiver;
import MilitaryBaseSimulation.Militaries.interfaces.ISender;

import java.util.Random;

import MilitaryBaseSimulation.Map.Map;

public class Scout extends Unit implements ISender, IScout{
	public Scout(int movementRange, int[] position, int effectiveness, int trustLevel, int visionRange) {
		super(movementRange, position);
	}
	
	public int trustLevel;
	int effectiveness;
	int visionRange;
	Random random = new Random();
	
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
		IUnit[][] map = Map.getInstance().getMap();
		for(int x = position[0] - visionRange; x <= position[0] + visionRange; x++) {
			for(int y = position[1] - visionRange; y <= position[1] + visionRange; y++) {
				if(map[x][y]!=null) {
					if(map[x][y] instanceof ITargetUnit) {
						if(map[x][y] instanceof EnemyUnit) {
							// wstawić senda send("Wykryto wroga!", unit, commander);
						}
						else if(map[x][y] instanceof DisguisedEnemyUnit){
							int probability = random.nextInt(100);
							if(probability<effectiveness) {
								//wyslij poprawne
							}
							else {
								//wyslij na odwrot
							}
						}
						else if(map[x][y] instanceof NeutralUnit){
							int probability = random.nextInt(100);
							if(probability<effectiveness) {
								//wyslij poprawne
							}
							else {
								//wyslij na odwrot
							}							
						}
					}
					
				}
			}
		}
		
	}
	
}