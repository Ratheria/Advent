/**
 * @author Ariana Fairbanks
 */

package state;

import java.io.Serializable;
import controller.AdventGame;
import controller.AdventMain;
import model.GameObjects;
import model.Locations;

public class AdventData implements Serializable
{
	private static final long serialVersionUID = -4962116536507971292L;
	public AdventGame game;
	public String log;
	public Locations[] mobileObjectsLocations;
	public int[] visits;
	
	public AdventData(String log)
	{
		this.game = AdventMain.ADVENT;
		this.log = log;
		this.mobileObjectsLocations = GameObjects.getLocations();
		this.visits = Locations.getVisitsArray();
	}
}
