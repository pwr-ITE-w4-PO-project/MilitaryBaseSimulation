package MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout;

import MilitaryBaseSimulation.MapUnits.Unit.IUnit;
import MilitaryBaseSimulation.MapUnits.Unit.Unit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.NeutralUnit.NeutralUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.ITargetUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.EnemyUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit.DisguisedEnemyUnit;
import MilitaryBaseSimulation.Militaries.interfaces.IReceiver;
import MilitaryBaseSimulation.Militaries.interfaces.ISender;

import java.util.Random;

import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.Map.Map;



public class Scout extends Unit implements ISender, IScout{
	/**
	 * Constructor.
	 * @param movementRange Range of motion.
	 * @param position Initial placement on the map.
	 * @param effectiveness Correct detecting probability.
	 * @param trustLevel Commander's trust.
	 * @param visionRange Detecting range.
	 */
	public Scout(int movementRange, int[] position, int effectiveness, int trustLevel, int visionRange) {
		super(movementRange, position);
		this.trustLevel = trustLevel;
		this.effectiveness = effectiveness;

	}
	
	private int trustLevel;
	private int effectiveness;
	private int visionRange;
	private Random random = new Random();
	private final String enemy = "Enemy unit detected!";
	private final String neutral = "Neutral unit detected!";
	
	
	
	@Override
	protected final int[] handlePositionBeyondMap(int[] newPosition) {
		int vectorX = this.position[0] - newPosition[0];
		int vectorY = this.position[1]-newPosition[1];
		newPosition[0]=this.position[0]+vectorX;
		newPosition[1]=this.position[1]+vectorY;
		
		 return this.position;
	}
	
	@Override
	public char getUnitChar() {
		return '0';
	}
	/**
	 * Gets trustLevel.
	 * @return trustLevel
	 */
	public int getTrustLevel() {
		return trustLevel;
	}
	/**
	 * Modifies trustLevel.
	 */	
	public void setTrustLevel(int level) {
		this.trustLevel += level;
	}
	/**
	 * Sends reports.
	 * @param report Report sent.
	 * @param unit Targeted unit.
	 * @param receiver Receiver to send to.
	 */
	public void send(String report, ITargetUnit unit, IReceiver receiver) {
		receiver.receive(report, unit);
	}

	/**
	 * Checks nearest area based on vision range. Detects other units, 
	 * identifies them and sends reports to commander.
	 */
	public void search() {
		IUnit[][] map = Map.getInstance().getMap();
		for(int x = position[0] - visionRange; x <= position[0] + visionRange; x++) {
			for(int y = position[1] - visionRange; y <= position[1] + visionRange; y++) {
				if(map[x][y]!=null) {
					if(map[x][y] instanceof ITargetUnit) {
						if(map[x][y] instanceof EnemyUnit) {
							send(enemy, (ITargetUnit) map[x][y], MilitaryBaseSimulation.getCommander() );
						}
						else if(map[x][y] instanceof DisguisedEnemyUnit){
							int probability = random.nextInt(100);
							if(probability<effectiveness) {
								send(enemy, (ITargetUnit) map[x][y], MilitaryBaseSimulation.getCommander() );
							}
							else {
								send(neutral, (ITargetUnit) map[x][y], MilitaryBaseSimulation.getCommander() );
							}
						}
						else if(map[x][y] instanceof NeutralUnit){
							int probability = random.nextInt(100);
							if(probability<effectiveness) {
								send(neutral, (ITargetUnit) map[x][y], MilitaryBaseSimulation.getCommander() );
							}
							else {
								send(enemy, (ITargetUnit) map[x][y], MilitaryBaseSimulation.getCommander() );
							}							
						}
					}
					
				}
			}
		}
		
	}
	
}