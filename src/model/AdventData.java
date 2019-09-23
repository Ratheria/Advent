/**
 * @author Ariana Fairbanks
 */

package model;

import java.io.Serializable;
import controller.AdventControl;
import controller.AdventMain;

public class AdventData implements Serializable
{
	private static final long serialVersionUID = -4962116536507971292L;
	public AdventControl game;
	public String log;
	public Location[] mobileObjectsLocations;
	public int[] visits;
	
	public AdventData(String log)
	{
		this.game = AdventMain.ADVENT;
		this.log = log;
		this.mobileObjectsLocations = GameObjects.getLocations();
		this.visits = Location.getVisitsArray();
	}
}
