/**
 * @author Ariana Fairbanks
 */

package state;

import java.io.*;

import controller.AdventGame;
import controller.AdventMain;
import data.*;

public class AdventData implements Serializable
{
	@Serial private static final long serialVersionUID = -4962116536507971292L;

	public AdventGame game;
	public String log;
	public Locations[] objectLocations;
	public int[] visits;
	public boolean[] hintGiven;
	public int[] hintProc;

	public AdventData(String log)
	{
		this.game = AdventMain.advent;
		this.log = log;
		this.objectLocations = GameObjects.getObjectLocations();
		this.visits = Locations.getVisitsArray();
		this.hintGiven = Hints.getHintGivenStatus();
		this.hintProc = Hints.getHintProcValues();
	}
}
