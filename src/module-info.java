module MilitaryBaseSimulation {
	//exports MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.NeutralUnit;
	exports MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit;
	exports MilitaryBaseSimulation;
	exports MilitaryBaseSimulation.Militaries.Headquarters;
	exports MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit;
	exports MilitaryBaseSimulation.MapUnits.Unit.subclasses.TargetUnit.subclasses.EnemyUnit.subclasses.DisguisedEnemyUnit;
	exports MilitaryBaseSimulation.Map;
	exports MilitaryBaseSimulation.Militaries.Gunner;
	exports MilitaryBaseSimulation.MapUnits.Unit.subclasses.Scout;
	exports MilitaryBaseSimulation.Militaries.Commander;
	exports MilitaryBaseSimulation.MoveGenerators;
	exports MilitaryBaseSimulation.MapUnits.Unit;
	exports Map;
	exports MilitaryBaseSimulation.Enums;
	exports MilitaryBaseSimulation.Militaries.Commander.interfaces;

	requires junit;
	requires org.junit.jupiter.api;
	//requires org.eclipse.swt.win32.win32.x86_64;
	requires java.desktop;
}