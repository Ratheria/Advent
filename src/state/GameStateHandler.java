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
	private final File dataFile = new File(System.getProperty("user.home") + "/.AdventData");

	public String loadGame(String currentLog)
	{
		System.out.println(dataFile.getAbsolutePath());
		if(dataFile.exists()) { return readData(); }
		return currentLog + "\n\nNo Save Data Available\n";
	}
	
	private String readData()
	{
		String result = "Exception Encountered: Failed To Read Save Data";

		try
		{
			FileInputStream fileReader  = new FileInputStream(dataFile);
			ObjectInputStream objectReader = new ObjectInputStream(fileReader);
			AdventData saveData = (AdventData) objectReader.readObject();
			objectReader.close();
			fileReader.close();

			// TODO: Close resources.

			result = saveData.log + "\n\nGame Loaded\n";
			AdventMain.advent = saveData.game;
			GameObjects.loadObjectLocations(saveData.objectLocations);
			Hints.loadHintStates(saveData.hintGiven, saveData.hintProc);
			Locations.loadVisits(saveData.visits);
			System.out.println("Game Data Loaded");
		} 
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		return result;
	}
	
	public String writeData(String logData)
	{
		String result = "Game Saved";

		if (!AdventMain.advent.isDead())
		{
			try
			{
				FileOutputStream fileWriter = new FileOutputStream(dataFile);
				ObjectOutputStream objectWriter = new ObjectOutputStream(fileWriter);
			    objectWriter.writeObject(new AdventData(logData));
			    objectWriter.close();
			    fileWriter.close();
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
