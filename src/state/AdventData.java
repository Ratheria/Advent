/**
 * @author Ariana Fairbanks
 */

package state;

import java.io.Serializable;
import controller.AdventGame;
import controller.AdventMain;
import controller.AdventMain.GameObjects;
import controller.AdventMain.Hints;
import controller.AdventMain.Locations;

public class AdventData implements Serializable
{
	private static final long serialVersionUID = -4962116536507971292L;
	public AdventGame  game;
	public String      log;
	public Locations[] objectLocations;
	public int[]       visits;
	public boolean[]   hintGiven;
	public int[]       hintProc;

	public AdventData(String log)
	{
		this.game            = AdventMain.ADVENT;
		this.log             = log;
		this.objectLocations = GameObjects.getLocations();
		this.visits          = Locations.getVisitsArray();
		this.hintGiven       = Hints.getHintGiven();
		this.hintProc        = Hints.getHintProc();
	}
}
