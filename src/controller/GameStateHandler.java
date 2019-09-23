package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import controller.AdventMain;
import model.AdventData;
import model.GameObjects;
import model.Location;

public class GameStateHandler 
{
	
	private File dataFile = new File(System.getProperty("user.home") + "/.AdventData");
	private FileInputStream fileReader;
	private ObjectInputStream objectReader;
	private FileOutputStream fileWriter;
	private ObjectOutputStream objectWriter;
	
	public String loadGame(String currentLog)
	{
		String result = currentLog;
		if(dataFile.exists())
		{
			result = readData() + "\n\nData Successfully Loaded\n";
		}
		else
		{
			result += "\n\nNo Load Data Available\n";
		}
		return result;
	}
	
	private String readData()
	{
		String result = "Something went wrong.";
		try
		{
			fileReader = new FileInputStream(dataFile);
			objectReader = new ObjectInputStream(fileReader);
			AdventData saveData = (AdventData) objectReader.readObject();
			objectReader.close();
			fileReader.close();
			result = saveData.log;
			AdventMain.ADVENT = saveData.game;
			GameObjects.loadLocations(saveData.mobileObjectsLocations);
			Location.loadVisits(saveData.visits);
			System.out.println("Data Loaded");
		} 
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public String writeData(String logData)
	{
		String result = "Game saved.";
		if(!AdventMain.ADVENT.playerIsDead)
		{
			try
			{
			    fileWriter = new FileOutputStream(dataFile);
			    objectWriter = new ObjectOutputStream(fileWriter);
			    objectWriter.writeObject(new AdventData(logData));
			    objectWriter.close();
			    fileWriter.close();
			} 
			catch (IOException e)
			{
				result = "Exception encountered. Game not saved.";
				e.printStackTrace();
			}
		}
		else
		{
			result = "You may not save now.";
		}
		return result;
	}
	
}
