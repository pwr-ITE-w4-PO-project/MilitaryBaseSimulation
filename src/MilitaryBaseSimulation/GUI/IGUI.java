package MilitaryBaseSimulation.GUI;

import java.util.ArrayList;
import java.util.List;

public interface IGUI {
	public int getBaseHP();
	public int getIterations();
	public int getEnemy();
	public int getDisguisedEnemy();
	public ArrayList<int[]> getScout();
	public List<Integer> getGunner();
	public void setBaseHP(int HP);
	public void setIterations(int iterations);
	public void setEnemy(int enemy);
	public void setDisguisedEnemy(int disguisedEnemy);
	public void setNumberOfScouts(int scouts);
	public void setNumberOfGunners(int gunners);
	public void setScout(int scoutID, int movement, int effectiveness, int trust, int vision);
	public void setGunner(int gunnerID, int accuracy);
	public void drawMap();

}
