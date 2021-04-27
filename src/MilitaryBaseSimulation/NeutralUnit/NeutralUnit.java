package MilitaryBaseSimulation.NeutralUnit;
import MilitaryBaseSimulation.TargetUnit.*;

public class NeutralUnit extends TargetUnit{
	public int getCount() {return 0;} //ma zwracac statyczna ilosc obiektow
	public void getDestroyed() {} //usuniecie z mapy + wywolanie oceniania commandera
}
