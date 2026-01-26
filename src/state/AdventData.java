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

	private AdventGame game;
	private String log;
	private Locations[] objectLocations;
	private int[] visits;
	private boolean[] hintGiven;
	private int[] hintProc;

	public AdventData(String log)
	{
		this.game = AdventMain.advent;
		this.log = log;
		this.objectLocations = GameObjects.getObjectLocations();
		this.visits = Locations.getVisitsArray();
		this.hintGiven = Hints.getHintGivenStatus();
		this.hintProc = Hints.getHintProcValues();
	}

	public AdventGame getGame()
	{
		return game;
	}

	public String getLog()
	{
		return log;
	}

	public Locations[] getObjectLocations()
	{
		return objectLocations;
	}

	public int[] getVisits()
	{
		return visits;
	}

	public boolean[] getHintGiven()
	{
		return hintGiven;
	}

	public int[] getHintProc()
	{
		return hintProc;
	}
}
