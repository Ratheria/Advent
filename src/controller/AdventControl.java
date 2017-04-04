package controller;

import model.Location;

import java.util.ArrayList;

import model.GameObjects;
import model.HashMaps;
import model.Movement;
import view.AdventureFrame;

public class AdventControl 
{
	@SuppressWarnings("unused")
	private AdventureFrame frame;
	private HashMaps hash = new HashMaps();
	private Location currentLocation;
	private Location previousLocation;
	private GameObjects things;
	private boolean dead;
	private boolean brief;
	private boolean beginning;
	private boolean grateUnlocked;
	private boolean crystalBridge;
	private boolean light;
	private boolean snake;
	private boolean oilDoor;
	private boolean dragon;
	private boolean troll;
	private boolean birdInCage;
	private boolean bearAxe;
	private boolean usedBatteries;
	private boolean broken;
	private boolean haveGold;
	private boolean collapse;
	private int deaths;
	private int plant;
	private int bottle;
	private int bear;
	private int chain;
	//	private String beforeTurnBeforeLast;
	//	private String turnBeforeLast;
	//	private String turnLast;


	public AdventControl()
	{
		frame = new AdventureFrame(this);
		//		beforeTurnBeforeLast = "";
		//		turnBeforeLast = "";
		//		turnLast = "";
		currentLocation = Location.ROAD;
		previousLocation = null;
		things = GameObjects.NOTHING;
		dead = false;
		brief = false;
		beginning = true;
		grateUnlocked = false;
		crystalBridge = false;
		light = false;
		snake = true;
		oilDoor = false;
		dragon = true;
		troll = true;
		birdInCage = false;
		bearAxe = false;
		broken = false;
		haveGold = false;
		collapse = false;
		deaths = 0;
		plant = 0;
		bottle = 1;
		bear = 0;
		chain = 0;
		currentLocation.setUp();
	}

	public String determineAction(String input) 
	{
		String output = null;
		if(beginning)
		{
			int answer = askYesNo(input);
			if(answer == 1)
			{
				output = "\tSomewhere nearby is Colossal Cave, where others have found great fortunes in "
						+ "treasure and gold, though it is rumored that some who enter are never seen "
						+ "again. Magic is said to work in the cave. I will be your eyes and hands. "
						+ "Direct me with commands of 1 or 2 words. I should warn you that I only "
						+ "look at only the first five letters of each word, so you'll have to enter "
						+ "\"northeast\" as \"ne\" to distinguish it from \"north\" "
						+ "(Should you get stuck, type \"help\" for some general hints. "
						+ "For information on how to end your adventure, etc., type \"info\".)\n\n"
						+ hash.getDescription(Location.ROAD, brief);
				beginning = false;
			}
			else if(answer == 2)
			{
				output = hash.getDescription(Location.ROAD, brief);
				beginning = false;
			}
			else
			{
				output = "Just yes or no, please.";
			}
		}
		else
		{
			if(hash.isMovement(input))
			{				
				output = attemptMovement(input);
			}
		}

		System.out.println("previous " + previousLocation);
		System.out.println("current " + currentLocation);
		return output;
	}

	public String determineAction(String input1, String input2) 
	{
		String output = null;

		if(beginning)
		{
			output = "Just yes or no, please.";
		}
		else
		{
			if(hash.isAction(input1))
			{

			}
			else if(hash.isAction(input2))
			{

			}
			else
			{
				output = nonsense();
			}
		}

		return output;
	}
	
	private String listItemsHere()
	{
		String output = "";
		ArrayList<GameObjects> objects = hash.objectsHere(currentLocation);
		if(objects != null)
		{
			for(GameObjects thing:objects)
			{
				output = new String(output + things.getItemDescription(currentLocation, thing, 
						light, grateUnlocked, plant, bottle, birdInCage, oilDoor, bearAxe, dragon,
						bear, usedBatteries, broken, chain, haveGold, crystalBridge, collapse));
				System.out.println(thing);
			}
		}
		return output;
	}
	
	private int askYesNo(String input)
	{
		int answer = 0;
		if(input.contains("y"))
		{
			answer = 1;
		}
		else if(input.contains("n"))
		{
			answer = 2;
		}
		return answer;
	}

	private String attemptMovement(String input)
	{
		String output = "";
		haveGold = (hash.objectIsHere(GameObjects.GOLD, Location.INHAND));
		boolean haveEmerald = (hash.objectIsHere(GameObjects.EMERALD, Location.INHAND));
		boolean haveClam = (hash.objectIsHere(GameObjects.CLAM, Location.INHAND));
		boolean haveOyster = (hash.objectIsHere(GameObjects.OYSTER, Location.INHAND));
		boolean trollIsHere = (hash.getObjectLocation(GameObjects.TROLL) == currentLocation || hash.getObjectLocation(GameObjects.TROLL_) == currentLocation);
		Movement destination = hash.whichMovement(input);
		Location locationResult = currentLocation.moveTo(destination, currentLocation, grateUnlocked,
				haveGold, crystalBridge, snake, haveEmerald, haveClam, haveOyster, plant, oilDoor,
				dragon, troll, trollIsHere);
		if(locationResult.equals(Location.THEVOID))
		{
			if(destination.equals(Movement.XYZZY)||destination.equals(Movement.PLUGH)||destination.equals(Movement.PLUGH))
			{
				output = "Nothing happens.";
			}
			else if(destination.equals(Movement.NORTH) ||
					destination.equals(Movement.SOUTH) ||
					destination.equals(Movement.EAST)  ||
					destination.equals(Movement.WEST)  ||
					destination.equals(Movement.NORTHEAST)||
					destination.equals(Movement.NORTHWEST)||
					destination.equals(Movement.SOUTHEAST)||
					destination.equals(Movement.SOUTHWEST))
			{
				output = "There are no exits in that direction.";
			}
			else
			{
				output = "I don't know how to apply that word here.";
			}
		}
		else if(locationResult.equals(Location.CRACK))
		{
			output = "That crack is far too small for you to follow.";
		}
		else if(locationResult.equals(Location.NECK))
		{
			output = "You are at the bottom of the pit with a broken neck.";
			//
			// implement death here
			//
		}
		else if(locationResult.equals(Location.LOSE))
		{
			output = "You didn't make it.";
			//
			// implement death here
			//
		}
		else if(locationResult.equals(Location.CANT))
		{
			output = "The dome is unclimbable.";
		}
		else if(locationResult.equals(Location.CLIMB))
		{
			setLocation(Location.NARROW);
			output = "You clamber up the plant and scurry through the hole at the top.\n" + getDescription(currentLocation, brief);
		}
		else if(locationResult.equals(Location.CHECK))
		{
			if(plant == 1)
			{
				setLocation(Location.WEST2PIT);
				output = "You have climbed up the plant and out of the pit.\n" + getDescription(currentLocation, brief);
			}
			else
			{
				output = "There is nothing here to climb. Use \"UP\" or \"OUT\" to leave the pit.";
			}
		}
		else if(locationResult.equals(Location.SNAKED))
		{
			output = "You can't get by the snake.";
		}
		else if(locationResult.equals(Location.THRU))
		{
			setLocation(Location.WESTMIST);
			output = "You have crawled through a very low wide passage parallel to and north of the Hall of Mists.\n" + getDescription(currentLocation, brief);
		}
		else if(locationResult.equals(Location.DUCK))
		{
			setLocation(Location.WESTFISSURE);
			output = "You have crawled through a very low wide passage parallel to and north of the Hall of Mists.\n" + getDescription(currentLocation, brief);
		}
		else if(locationResult.equals(Location.SEWER))
		{
			output = "The stream flows out through a pair of 1-foot-diameter sewer pipes.\nIt would be advisable to use the exit.";
		}
		else if(locationResult.equals(Location.UPNOUT))
		{
			output = "There is nothing here to climb. Use \"UP\" or \"OUT\" to leave the pit.";
		}
		else if(locationResult.equals(Location.DIDIT))
		{
			setLocation(Location.WEST2PIT);
			output = "You have climbed up the plant and out of the pit.\n" + getDescription(currentLocation, brief);
		}
		else if(locationResult.equals(Location.REMARK))
		{
			if(currentLocation.equals(Location.SLIT)||currentLocation.equals(Location.WET))
			{
				output = "You can't fit through a two-inch slit!";
			}
			else if(currentLocation.equals(Location.INSIDE)||currentLocation.equals(Location.OUTSIDE)||currentLocation.equals(Location.DEBRIS)||currentLocation.equals(Location.AWKWARD)||
					currentLocation.equals(Location.BIRD)||currentLocation.equals(Location.SMALLPIT))
			{
				output = "You can't go through a locked steel grate!";
			}
			else if(currentLocation.equals(Location.SWSIDE) && destination != Movement.JUMP)
			{
				if(troll)
				{
					output = "The troll refuses to let you cross.";
				}
				else
				{
					output = "There is no longer any way across the chasm.";
				}
			}
			else if(currentLocation.equals(Location.NESIDE)||currentLocation.equals(Location.SWSIDE)||currentLocation.equals(Location.EASTFISSURE)||currentLocation.equals(Location.WESTFISSURE))
			{
				if(destination.equals(Movement.JUMP))
				{
					output = "I respectfully suggest that you go across the bridge instead of jumping.";	
				}
				else
				{
					output = "There is no way to cross the fissure";
				}
			}
			else if(currentLocation.equals(Location.HALLOFMOUNTAINKING))
			{
				output = "You can't get by the snake.";
			}
			else if(currentLocation.equals(Location.SHELL))
			{
				if(haveClam)
				{
					output = "You can't fit this five-foot clam through that little passage!";
				}
				else
				{
					output = "You can't fit this five-foot oyster through that little passage!";
				}
			}
			else if(currentLocation.equals(Location.WITT)&&destination.equals(Movement.WEST))
			{
				output = "You have crawled around in some little holes and found your way blocked by a recent cave-in.\nYou are now back in the main passage.";
			}
			else if(currentLocation.equals(Location.WITT)||currentLocation.equals(Location.BEDQUILT)||currentLocation.equals(Location.CHEESE))
			{
				output = "You have crawled around in some little holes and wound up back in the main passage.";
			}
			else if(currentLocation.equals(Location.IMMENSE))
			{
				output = "The door is extremely rusty and refuses to open.";
			}
			else if(currentLocation.equals(Location.SCAN1)||currentLocation.equals(Location.SCAN3))
			{
				output = "That dragon looks rather nasty. You'd best not try to get by.";
			}
			else if(currentLocation.equals(Location.VIEW))
			{
				output = "Don't be ridiculous!";
			}
			else
			{
				output = "You can not do that.";
			}
		}
		else
		{
			setLocation(locationResult);
			if(!canISee(currentLocation))
			{
				//HAVE THEM MAYBE FALL INTO A PIT HERE
				boolean pitifulDeath = fallInPit();
				if(pitifulDeath)
				{

				}
				else
				{
					output = "It is now pitch dark. If you proceed you will likely fall into a pit.";
				}
			}
			else
			{
				output = getDescription(currentLocation, brief);
				if(currentLocation.equals(Location.Y2))
				{
					if(Math.random() > .74)
					{
						//hollow voice
						output = new String(output + "\n\n");
					}
				}
			}
		}


		return output;
	}
	
	private void setLocation(Location newLocation)
	{
		previousLocation = currentLocation;
		currentLocation = newLocation;
	}

	//	private String orderLog(String log)
	//	{
	//		this.beforeTurnBeforeLast = turnBeforeLast;
	//		this.turnBeforeLast = turnLast;
	//		this.turnLast = log;
	//		return null;
	//	}

	private String getDescription(Location here, boolean breif)
	{
		String output = hash.getDescription(here, breif);
		output = new String(output + listItemsHere());
		return output;
	}
	
	private String nonsense()
	{
		String output = null;
		double randomOutput = Math.random();
		if(randomOutput < .34)
		{
			output = "I have no idea what you are talking about!";
		}
		else if(randomOutput < .67)
		{
			output = "I don't understand that!";
		}
		else
		{
			output = "You're not making any sense!";
		}
		return output;
	}

	private boolean canISee(Location here)
	{
		boolean canSee = false;
		if(currentLocation.dontNeedLamp(here))
		{	
			canSee = true;
		}
		else if(light && hash.objectIsHere(GameObjects.LAMP, Location.INHAND))
		{
			canSee = true;
		}
		else if(light && hash.objectIsHere(GameObjects.LAMP, currentLocation))
		{
			canSee = true;
		}
		System.out.print("can see ");System.out.print(currentLocation.dontNeedLamp(here)); System.out.println("\n");
		System.out.println(currentLocation);
		return canSee;
	}

	private boolean fallInPit()
	{
		boolean pitifulDeath = false;

		return pitifulDeath;
	}


}



