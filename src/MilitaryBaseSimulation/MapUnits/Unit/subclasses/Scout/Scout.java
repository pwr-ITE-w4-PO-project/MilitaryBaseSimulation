package MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout;

import MilitaryBaseSimulation.MapUnits.Unit.IUnit;
import MilitaryBaseSimulation.MapUnits.Unit.Unit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IIdentifiable;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.interfaces.IIdentified;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit.DisguisedEnemyUnit;
import MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit.NeutralUnit;
import MilitaryBaseSimulation.Militaries.Commander.interfaces.IReportReceiver;
import MilitaryBaseSimulation.MoveGenerators.AlliesMoveGenerator;
import MilitaryBaseSimulation.MilitaryBaseSimulation;
import MilitaryBaseSimulation.Enums.ReportInfo;
import MilitaryBaseSimulation.Map.Map;

/**
 * 
 * @author Mateusz Torski
 *
 */
public class Scout extends Unit implements IScout{
	private int trustLevel;
	private int effectiveness;
	private int visionRange;
	private IReportReceiver commander;

	/**
	 * Constructor.
	 * @param movementRange Range of motion.
	 * @param position Initial placement on the map.
	 * @param effectiveness Correct detecting probability.
	 * @param trustLevel Commander's trust.
	 * @param visionRange Detecting range.
	 */
	public Scout(int movementRange, int[] position, int effectiveness, int trustLevel, int visionRange, IReportReceiver commander) {
		super(movementRange, position);
		this.trustLevel = trustLevel;
		this.effectiveness = effectiveness;
		this.visionRange = visionRange;
		this.unitChar = 'S';
		this.moveGenerator = new AlliesMoveGenerator();
		this.commander = commander;
	}

	@Override
	/**
	 * Returns opposite position.
	 */
	protected final int[] handlePositionBeyondMap(int[] newPosition) {
		//calculates the negated movement vector
		int vectorX = this.position[0] - newPosition[0];
		int vectorY = this.position[1] - newPosition[1];
		
		//calculates opposite position
		newPosition[0] = this.position[0]+vectorX;
		newPosition[1] = this.position[1]+vectorY;
		
		return this.position;
	}
	
	@Override
	/**
	 * Moves unit on the map, and searches for other units to identification.
	 */
	public void move() {
		super.move();
		this.search();
	}
	
	/**
	 * Gets trustLevel.
	 * @return trustLevel
	 */
	public int getTrustLevel() {
		return trustLevel;
	}
	/**
	 * Modifies trustLevel by given value.
	 * @param value A value by which trust level is being modified.
	 */	
	public void modifyTrustLevel(int value) {
		this.trustLevel += value;
	}

	/**
	 * Checks nearest area based on vision range. Detects other units, 
	 * identifies them and sends reports to commander.
	 */
	public void search(){
		IUnit[][] map = Map.getInstance().getMap();
		IIdentifiable unit;
		
		//foreach surrounding position in vision range
		for(int x = position[0] - visionRange; x <= position[0] + visionRange; x++) {
			for(int y = position[1] - visionRange; y <= position[1] + visionRange; y++) {
				
				//checks if searched position isn't beyond map, and isn't unoccupied
				if(Map.getInstance().isPositionWithinMap(x, y) && map[x][y] != null) {
					
					//checks if unit is identifiable (is not another scout)
					if(map[x][y] instanceof IIdentifiable) {
						
						unit = (IIdentifiable) map[x][y];
						
						//checks if unit hasn't been already identified
						if(unit.getIsIdentified() == false) {
							
							ReportInfo report;
							
							if(unit instanceof NeutralUnit || unit instanceof DisguisedEnemyUnit) {
								//draw identification result and identify unit according to the draw
								boolean identifiactionResult = MilitaryBaseSimulation.generateRandomEventHappening(this.effectiveness);
								unit.setIsCorrectlyIdentified(identifiactionResult);
								
								if(unit instanceof NeutralUnit) { //doing below code via ternary operators throws jdk errors
									if(identifiactionResult) report = ReportInfo.NEUTRAL;
									else report = ReportInfo.ENEMY;
								}
								else {
									if(identifiactionResult) report = ReportInfo.ENEMY;
									else report = ReportInfo.NEUTRAL;
								}
							}
							else{//unit is type of EnemyUnit, which are always identified as enemy
								unit.setIsCorrectlyIdentified(true);
								report = ReportInfo.ENEMY;
							}
							
							unit.setIsIdentified(true);
							unit.setIdentifiedBy((IScout)this);
							
							//sends report to the commander, if exists
							if(this.commander != null)
								this.commander.receive(report, (IIdentified)unit);
						}
					}
				}
			}
		}
	}
}