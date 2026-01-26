/**
 * @author Ariana Fairbanks
 */

package state;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import controller.AdventMain;
import data.*;

public class GameStateHandler 
{
	private static final File dataFile = new File(System.getProperty("user.home") + "/.AdventData");

	private GameStateHandler()
	{ }

	public static String loadGame(String currentLog)
	{
		System.out.println(dataFile.getAbsolutePath());

		if (dataFile.exists())
		{
			// If we manage to read data we will replace the current log with the log in the save file. This
			// way the player can go back through all actions taken previously in the save they loaded.
			return readData(currentLog);
		}

		return currentLog + "\n\nNo Save Data Available\n";
	}
	
	private static String readData(String currentLog)
	{
		String result = currentLog + "\n\nException Encountered: Failed To Read Save Data";

		try (ObjectInputStream objectReader = new ObjectInputStream(new FileInputStream(dataFile)))
		{
			AdventData saveData = (AdventData) objectReader.readObject();

			result = saveData.getLog() + "\n\nGame Loaded\n";
			AdventMain.advent = saveData.getGame();
			GameObjects.loadObjectLocations(saveData.getObjectLocations());
			Hints.loadHintStates(saveData.getHintGiven(), saveData.getHintProc());
			Locations.loadVisits(saveData.getVisits());
			System.out.println("Game Data Loaded");
		} 
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		return result;
	}
	
	public static String writeData(String logData)
	{
		String result = "Game Saved";

		if (!AdventMain.advent.isDead())
		{
			try (ObjectOutputStream objectWriter = new ObjectOutputStream(new FileOutputStream(dataFile)))
			{
				objectWriter.writeObject(new AdventData(logData));
			}
			catch (IOException e)
			{
				result = "Exception Encountered: Failed To Save";
				e.printStackTrace();
			}
		}
		else
		{
			result = "You May Not Save Now";
		}

		return result;
	}
	
}
