/**
 * @author Ariana Fairbanks
 */

package controller;

import model.Location;
import model.MessageWords;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ActionWords;
import model.GameObjects;
import model.HashMaps;
import model.Movement;
import view.AdventureFrame;

public class AdventControl 
{
	private static Random random;
	private AdventureFrame frame;
	private HashMaps hash;
	@SuppressWarnings("unused")
	private MessageWords messages;
	@SuppressWarnings("unused")
	private ActionWords actions;
	private GameObjects things;
	private Location currentLocation;
	private Location previousLocation;
	@SuppressWarnings("unused")
	private Location eldestLocation;
	private int[] scores;
	private String[] sMessages;
	private String[] feeFieFoe;
	private String okay;
	private String dontHave;
	private String nothing;
	private boolean dead;
	private boolean beginning;
	private boolean closing, closed;
	private boolean grateUnlocked;
	private boolean crystalBridge;
	private boolean light;
	private boolean snake;
	private boolean oilDoor;
	private boolean dragon;
	private boolean birdInCage;
	private boolean bearAxe;
	private boolean broken;
	private boolean haveGold;
	private static boolean relocate;
	private static boolean collapse;
	private static boolean justCollapsed;
	private boolean lampWarn;
	private boolean panic;
	private boolean over;
	private boolean shortcut;
	private boolean dwarvesOn;
	private boolean battleUpdate;
	private boolean wayIsBlocked;
	private boolean justBlocked;
	private boolean locationChange;
	private boolean seriousQuestion;
	private boolean increaseTurns;
	private boolean noMore;
	
	private boolean wellInCave;
	private boolean read;
	private boolean quit;
	private int hint;
	private int h1, h2, h3, h4, h5, h6;
	
	private int clock1, clock2;
	private int quest;
	private int brief;
	private int score;
	private int bonus;
	private int turns;
	private int lamp;
	private int itemsInHand;
	private int deaths;
	private int fatality;
	private static int tally;
	private int lostTreasures;
	private int plant;
	private int bottle;
	private int usedBatteries;
	private int pirate;
	private int movesWOEncounter;
	private int dwarves;
	//nothing, reached hall no dwarf, met dwarf no knives, knife misses, knife hit .095, .190, .285
	@SuppressWarnings("unused")
	private int deadDwarves;
	private int dwarvesLeft;
	private int dwarfPresent;
	//none, new, current, old
	private static int troll;
	//there, hidden, dead, can pass;
	private static int bear;
	//default, fed + idle, fed + following, dead, was following ide
	private int chain;
	//locked to bear, unlocked, locked
	private int west;
	private int foo;
	private int rod1, rod2;
	private int bottles;
	private int lamps;
	private int pillows;
	private int oysters;
	private int grates;
	private int cages;
	private int birds;
	private int snakes;
	
	public AdventControl()
	{
		frame = new AdventureFrame(this);
		hash = new HashMaps();
		actions = ActionWords.NOTHING;
		things = GameObjects.NOTHING;
		scores = new int[] {35, 100, 130, 200, 250, 300, 330, 349, 350};
		sMessages = new String[] 
				{	
					"You are obviously a rank amateur. Better luck next time.",
					"Your score qualifies you as a novice class adventurer.",
					"You have now achieved the rating 'Experienced Adventurer'.",
					"You may now consider yourself a 'Seasoned Adventurer'.",
					"You have reached 'Junior Master' status.",
					"Your score puts you in Master Adventurer Class C.",
					"Your score puts you in Master Adventurer Class B.",
					"Your score puts you in Master Adventurer Class A.",
					"All of Adventuredom gives tribute to you, Adventure Grandmaster!"
				};
		feeFieFoe = new String[] {"fee", "fie", "foe", "foo", "fum"};
		okay = new String("Okay.");
		dontHave = new String("You are not carrying it!");
		nothing = new String("Nothing happens.");
		random = new Random();
		setUp();
	}
	
	public void setUp()
	{
		currentLocation = Location.ROAD;
		previousLocation = null;
		eldestLocation = null;
		dead = false;
		beginning = true;
		closed = false;
		grateUnlocked = false;
		crystalBridge = false;
		light = false;
		snake = true;
		oilDoor = false;
		dragon = true;
		birdInCage = false;
		bearAxe = false;
		broken = false;
		haveGold = false;
		relocate = false;
		justCollapsed = false;
		collapse = false;
		lampWarn = false;
		over = false;
		shortcut = false;
		dwarvesOn = true;
		battleUpdate = false;
		panic = false;
		wayIsBlocked = false;
		justBlocked = false;
		locationChange = false;
		seriousQuestion = false;
		noMore = false;
		increaseTurns = false;
		wellInCave = false;
		read = false;
		quit = false;
		hint = 0;
		h1 = 0;
		h2 = 0;
		h3 = 0;
		h4 = 0;
		h5 = 0;
		h6 = 0;
		clock1 = 15;
		clock2 = 15;
		quest = 0;
		brief = 0;
		score = 0;
		bonus = 0;
		turns = 0;
		lamp = 330;
		itemsInHand = 0;
		deaths = 3;
		fatality = 0;
		//default, pit, dwarf
		tally = 0;
		lostTreasures = 0;
		plant = 0;
		bottle = 1;
		usedBatteries = 0;
		pirate = 0;
		movesWOEncounter = 1;
		dwarves = 0;
		deadDwarves = 0;
		dwarvesLeft = 5;
		dwarfPresent = 0;
		troll = 0;
		bear = 0;
		chain = 0;
		west = 0;
		foo = 0;
		rod1 = 0;
		rod2 = 0;
		bottles = 0;
		pillows = 0;
		lamps = 0;
		oysters = 0;
		grates = 0;
		cages = 0;
		birds = 0;
		snakes = 0;
		currentLocation.setUp(this);
		things.setUp();
		frame.setUp();
		hash.setUpHashMaps();
	}

	public String determineAction(String input) 
	{
		System.out.println("\n" + input);
		String output = null;
		increaseTurns = true;
		int answer = askYesNo(input);
		boolean thisIsAnObject = hash.isObject(input);
		boolean thisIsAnAction = hash.isAction(input);
		GameObjects itsAn = null;
		ActionWords action = null;
		if(thisIsAnObject)
		{	itsAn = (hash.whichObject(input));	}
		if(thisIsAnAction)
		{	action = (hash.whichAction(input));	}
		if(beginning)
		{
			if(shortcut)
			{
				output = lamp("");
				beginning = false;
			}
			else if(answer == 1)
			{
				hint = 5;
				output = "\tSomewhere nearby is Colossal Cave, where others have found great fortunes in "
						+ "treasure and gold, though it is rumored that some who enter are never seen "
						+ "again. Magic is said to work in the cave. I will be your eyes and hands. "
						+ "Direct me with commands of 1 or 2 words. I should warn you that I only "
						+ "look at only the first five letters of each word, so you'll have to enter "
						+ "\"northeast\" as \"ne\" to distinguish it from \"north\" "
						+ "(Should you get stuck, type \"help\" for some general hints. "
						+ "For information on how to end your adventure, etc., type \"info\".)\n\n"
						+ hash.getDescription(Location.ROAD, brief);
				lamp = 1000;
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
				increaseTurns = false;
			}
		}
		else if(quest == 1 && (input.toLowerCase().contains("yes") || input.equalsIgnoreCase("y") || 
				input.toLowerCase().contains("yes")))
		{
			output = "Congratulations! You have just vanquished a dragon with your bare hands! "
					+ "(Unbelievable, isn't it?)";
			quest = 0;
			dragon = false;
			currentLocation = Location.SCAN2;
			voidObject(GameObjects.DRAGON_);
			voidObject(GameObjects.RUG_);
			hash.dropObject(GameObjects.DRAGON, currentLocation);
			hash.dropObject(GameObjects.RUG, currentLocation);
		}
		else if(seriousQuestion && answer == 0)
		{
			output = "Just yes or no, please.";
			increaseTurns = false;
		}
		else if(quest == 2 && answer == 1)
		{
			quest = 0;		
			seriousQuestion = false;
			quit = true;
			over = true;
		}
		else if(quest == 3 && thisIsAnObject && itsAn != GameObjects.NOTHING)
		{
			output = attemptAction(ActionWords.TAKE, hash.whichObject(input), input);
			quest = 0;
		}
		else if(quest == 4 && thisIsAnObject && itsAn != GameObjects.NOTHING)
		{
			output = attemptAction(ActionWords.DROP, hash.whichObject(input), input);
			quest = 0;
		}
		else if(quest == 5 && thisIsAnObject && itsAn != GameObjects.NOTHING)
		{
			output = attemptAction(ActionWords.OPEN, hash.whichObject(input), input);
			quest = 0;
		}
		else if(quest == 6 && thisIsAnObject && itsAn != GameObjects.NOTHING)
		{
			output = attemptAction(ActionWords.CLOSE, hash.whichObject(input), input);
			quest = 0;
		}
		else if(input.equals("fee") || (quest == 7 && thisIsAnAction && action == ActionWords.FEEFIE))
		{
			quest = 0;
			output = attemptAction(action, GameObjects.NOTHING, input);
		}
		else if(quest == 8)
		{
			quest = 50;
			increaseTurns = false;
			seriousQuestion = false;
			if(answer == 2)
			{	over = true;	}
			else
			{
				switch(deaths)
				{
					case 2:
						dead = false;
						output = "All right. But don't blame me if something goes wr......\n\t---POOF!!---"
								+ "\nYou are engulfed in a cloud of orange smoke. Coughing and gasping, you "
								+ "emerge from the smoke to find....\n" 
								+ hash.getDescription(currentLocation, brief); 
						break;
					case 1:
						dead = false;
						output = "Okay, now where did I put my resurrection kit?....\n\t>POOF!<"
								+ "\nEverything disappears in a dense cloud of orange smoke.\n" 
								+ hash.getDescription(currentLocation, brief);
						break;
					default:
						output = "Okay, if you're so smart, do it yourself! I'm leaving!";
						over = true;
						break;
				}
			}
		}
		else if(quest == 9)
		{
			if(answer == 1)
			{	setUp();	}
			else
			{
				noMore = true;
				System.exit(0);
			}
		}
		else if(quest == 10 && thisIsAnObject && itsAn != GameObjects.NOTHING)
		{
			output = attemptAction(ActionWords.KILL, hash.whichObject(input), input);
			quest = 0;
		}
		else if(quest == 11 && thisIsAnObject && itsAn != GameObjects.NOTHING)
		{
			output = attemptAction(ActionWords.FEED, hash.whichObject(input), input);
			quest = 0;
		}
		else if(quest == 20)
		{
			quest = 0;
			seriousQuestion = false;
			if(answer == 1)
			{
				read = true;
				hint += 10;
				output = "It says, 'There is something strange about this place, such that one of the "
						+ "words I've always known now has a new effect.'";
			}
			else
			{	output = okay;	}
		}
		else if(quest == 21 && answer == 1)
		{
			output = hintMessage(2);
			quest = 31;
		}
		else if(quest == 31 && answer == 1)
		{
			giveHint(2);
			output = "The grate is very solid and has a hardened steel lock. You cannot enter without a "
					+ "key, and there are no keys in sight. I would recommend looking elsewhere for the keys.";
		}
		else if(quest == 22 && answer == 1)
		{
			output = hintMessage(2);
			quest = 32;
		}
		else if(quest == 32 && answer == 1)
		{
			giveHint(2);
			output = "Something seems to be frightening the bird just now and you cannot catch it no"
					+ " matter what you try. Perhaps you might try later.";
		}
		else if(quest == 23 && answer == 1)
		{
			output = hintMessage(2);
			quest = 33;
		}
		else if(quest == 33 && answer == 1)
		{
			giveHint(2);
			output = "You can't kill the snake, or drive it away, or avoid it, or anything like that."
					+ " There is a way to get by, but you don't have the necessary resources right now.";
		}
		else if(quest == 24 && answer == 1)
		{
			output = hintMessage(4);
			quest = 34;
		}
		else if(quest == 34 && answer == 1)
		{
			giveHint(4);
			output = "You can make the passages look less alike by dropping things.";
		}
		else if(quest == 25 && answer == 1)
		{
			output = hintMessage(5);
			quest = 35;
		}
		else if(quest == 35 && answer == 1)
		{
			giveHint(5);
			output = "There is a way to explore that region without having to worry about falling into a pit.";
			if(!(objectIsPresent(GameObjects.EMERALD)))
			{
				output += " None of the objects available is immediately useful for discovering the secret.";
			}
		}
		else if(quest == 26 && answer == 1)
		{
			output = hintMessage(3);
			quest = 36;
		}
		else if(quest == 36 && answer == 1)
		{
			giveHint(3);
			output = "Don't go west.";
		}
		else
		{
			if(quest != 0)
			{
				quest = 0;
				foo = 0;
			}
			if(input.length() > 5)
			{
				input = input.substring(0, 5);
			}
			if(hash.isMovement(input))
			{				
				output = attemptMovement(input);
			}
			else if(hash.isObject(input) && objectIsPresent(hash.whichObject(input)) 
					&& hash.whichObject(input) == GameObjects.KNIFE)
			{
				output = new String("The dwarves' knives vanish as they strike the walls of the cave.");
				increaseTurns = false;
			}
			else if(hash.isAction(input))
			{
				output = attemptAction(hash.whichAction(input), GameObjects.NOTHING, "");
			}
			else if(hash.isObject(input))
			{
				GameObjects currentObject = hash.whichObject(input);
				if(objectIsPresent(currentObject))
				{
					output = "What would you like to do with the " + input + "?";
					increaseTurns = false;
				}
				else if(currentObject == GameObjects.NOTHING)
				{
					output = "Okay.";
				}
				else
				{
					output = new String("I don't see any " + input + ".");
					increaseTurns = false;
				}
			}
			else if(hash.isMessage(input))
			{
				MessageWords message = hash.whichMessage(input);
				output = getText(message);
			}
			else
			{	output = nonsense();	}
		}
		if(input.equalsIgnoreCase("west"))
		{	west++;	}
		output = finishAction(output);
		return output;
	}

	public String determineAction(String input1, String input2) 
	{
		System.out.println("\n" + input1 + " " + input2);
		String output = null;
		increaseTurns = true;
		if(!seriousQuestion && quest != 0)
		{
			quest = 0;
			foo = 0;
		}
		if(beginning||seriousQuestion)
		{
			output = "Just yes or no, please.";
			increaseTurns = false;
		}
		else
		{
			String input12 = input1;
			String input22 = input2;
			if(input1.length() > 5)
			{	input12 = input1.substring(0, 5);	}
			if(input2.length() > 5)
			{	input22 = input2.substring(0, 5);	}
			if(hash.isMessage(input12))
			{
				MessageWords message = hash.whichMessage(input12);
				output = getText(message);
			}
			else if(hash.isMessage(input22))
			{
				MessageWords message = hash.whichMessage(input22);
				output = getText(message);
			}
			else if(hash.isMovement(input12))
			{
				if(hash.whichMovement(input12) == Movement.ENTER)
				{
					if(input22.equalsIgnoreCase("water")||input22.equalsIgnoreCase("strea"))
					{
						if(currentLocation == Location.ROAD || currentLocation == Location.BUILDING 
								|| currentLocation == Location.VALLEY || currentLocation == Location.SLIT 
								|| currentLocation == Location.WET || currentLocation == Location.FALLS 
								|| currentLocation == Location.RESER)
						{
							output = "Your feet are now wet.";
							turns++;
						}
						else
						{
							output = "I don't see any water.";
						}
					}
					else
					{	output = attemptAction(ActionWords.GO, determineNature(input22), input2);	}
				}
				else
				{	output = attemptMovement(input12);	}
			}
			else if(hash.isMovement(input22))
			{	output = attemptMovement(input22);	}
			else if(hash.isObject(input12) && hash.isObject(input22) 
					&& (hash.whichObject(input12) == GameObjects.WATER 
					&& hash.whichObject(input22) == GameObjects.PLANT 
					|| hash.whichObject(input22) == GameObjects.WATER 
					&& hash.whichObject(input12) == GameObjects.PLANT))
			{	output = attemptAction(ActionWords.POUR, GameObjects.WATER, "");	}
			else if(hash.isObject(input12) && hash.isObject(input22) 
					&& (hash.whichObject(input12) == GameObjects.OIL 
					&& hash.whichObject(input22) == GameObjects.DOOR 
					|| hash.whichObject(input22) == GameObjects.OIL 
					&& hash.whichObject(input12) == GameObjects.DOOR))
			{	output = attemptAction(ActionWords.POUR, GameObjects.OIL, "");	}
			else if(hash.isObject(input12) && objectIsPresent(hash.whichObject(input12)) 
					&& hash.whichObject(input12) == GameObjects.KNIFE)
			{
				output = new String("The dwarves' knives vanish as they strike the walls of the cave.");
				increaseTurns = false;
			}
			else if(hash.isObject(input22) && objectIsPresent(hash.whichObject(input22)) 
					&& hash.whichObject(input22) == GameObjects.KNIFE)
			{
				output = new String("The dwarves' knives vanish as they strike the walls of the cave.");
				increaseTurns = false;
			}
			else if(hash.isAction(input12))
			{	output = attemptAction(hash.whichAction(input12), determineNature(input22), input2);	}
			else if(hash.isAction(input22))
			{	output = attemptAction(hash.whichAction(input22), determineNature(input12), input1);	}
			else
			{	output = nonsense();	}
		}
		if(input1.equalsIgnoreCase("west")||input2.equalsIgnoreCase("west"))
		{	west++;	}
		output = finishAction(output);
		return output;
	}
	
	private String finishAction(String output)
	{
		if(!wellInCave)
		{
			if(currentLocation.getOrdinal(currentLocation) >= currentLocation.minLoc())
			{
				wellInCave = true;
				dwarves = 1;
			}
		}
		if(locationChange && increaseTurns)
		{
			output = lamp(output);
			locationChange = false;
			if(pirate == 1)
			{
				ArrayList<GameObjects> currentlyHolding = hash.objectsHere(Location.INHAND);
				boolean treasure = false;
				pirate = 2;
				hash.dropObject(GameObjects.MESSAGE, Location.PONY);
				hash.dropObject(GameObjects.CHEST, Location.DEAD2);
				if(currentlyHolding != null)
				{
					for(GameObjects object : currentlyHolding)
					{
						if(things.isTreasure(object))
						{
							treasure = true;
							hash.dropObject(object, Location.DEAD2);
							itemsInHand--;
						}
					}
				}
				if(treasure)
				{
					output += "\n\nOut from the shadows behind you pounces a bearded pirate!\n\"Har, har,\" he "
							+ "chortles, \"I'll just take all this booty and hide it away with me chest deep in "
							+ "the maze!\"\nHe snatches your treasure and vanishes into the gloom.";
				}
				else
				{
					output += "\n\nThere are faint rustling noises from the darkness behind you. "
							+ "As you turn toward them, the beam of your lamp falls across a bearded pirate. "
							+ "He is carrying a large chest.\n\"Shiver me timbers!\" he cries, \"I've been spotted!"
							+ " I'd best hie meself off to the maze to hide me chest!\"\nWith that, he vanishes "
							+ "into the gloom.";
				}
			}
		}
		if(increaseTurns)
		{	
			turns++;	
			if(dwarfPresent == 1)
			{
				if(dwarves == 1)
				{	
					dwarves = 2;	
					dwarfPresent = 0;
					dwarvesLeft -= (Math.floor(generate() * 3));
					output += "\n\nA little dwarf just walked around a corner, saw you, threw a little axe at you, "
							+ "cursed, and ran away. (The axe missed.)";
					hash.dropObject(GameObjects.AXE, currentLocation);
				}
				else
				{
					output = "\n\nThere is a threatening little dwarf in the room with you!";
				}
			}
			else if (dwarfPresent == 2 && battleUpdate)
			{
				output = "\n\nThere is a threatening little dwarf in the room with you!\nOne sharp nasty knife is "
						+ "thrown at you!";
				boolean hit = false;
				if(dwarves >= 3)
				{
					if(generate() * 1000 < 95 * (dwarves - 2))
					{	hit = true;	}
				}
				else
				{	dwarves++;	}
				if(hit)
				{	
					output += "\nIt gets you!";	
					dead = true;
				}
				else
				{	output += "\nIt misses!";	}
			}
		}
		if(15 - tally == lostTreasures && lamp > 35)
		{	lamp = 35;	}
		getCurrentScore();
		if(dead && quest != 50)
		{	output += death(output); }
		if(over)
		{	output = quit(output);	}
		else
		{
			if(quest == 50)
			{
				quest = 0;
			}
			output = output + checkForHints();
			System.out.println("previous " + previousLocation);
			System.out.println("current " + currentLocation);
			System.out.println("lamp " + lamp);
			System.out.println("items " + itemsInHand);
			System.out.println("tally " + tally);
		}
		battleUpdate = false;
		return output;
	}
	
	private String checkForHints()
	{
		String output = new String("");
		if(!closed)
		{
			if(west == 10)
			{
				output = "\nIf you prefer, simply type W rather than WEST.";
			}
			if(!grateUnlocked && currentLocation == Location.OUTSIDE && !(objectIsPresent(GameObjects.KEYS)))
			{
				h1++;
				if(h1 == 4)
				{	
					output = "\nAre you trying to get into the cave?";	
					quest = 21;
					h1++;
				}	
			}
			if(h2 == 5)
			{
				output = "\nAre you trying to catch the bird?";	
				quest = 22;
				h2++;
			}
			if(objectIsPresent(GameObjects.SNAKE) && !(objectIsPresent(GameObjects.BIRD)))
			{
				h3++;
				if(h3 == 8)
				{	
					output = "\nAre you trying to deal somehow with the snake?";	
					quest = 23;
					h3++;
				}	
			}
			if(currentLocation == Location.ALIKE1 || 
					currentLocation == Location.ALIKE2 || currentLocation == Location.ALIKE3 || 
					currentLocation == Location.ALIKE4 || currentLocation == Location.ALIKE5 || 
					currentLocation == Location.ALIKE6 || currentLocation == Location.ALIKE7 || 
					currentLocation == Location.ALIKE8 || currentLocation == Location.ALIKE9 || 
					currentLocation == Location.ALIKE10 || currentLocation == Location.ALIKE11 || 
					currentLocation == Location.ALIKE12 || currentLocation == Location.ALIKE13 || 
					currentLocation == Location.ALIKE14 || currentLocation == Location.DEAD1 || 
					currentLocation == Location.DEAD3 || currentLocation == Location.DEAD4 || 
					currentLocation == Location.DEAD5 || currentLocation == Location.DEAD6 || 
					currentLocation == Location.DEAD7 || currentLocation == Location.DEAD9 || 
					currentLocation == Location.DEAD10 || currentLocation == Location.DEAD11)
			{
				h4++;
				if(h4 == 75)
				{	
					output = "\nDo you need help getting out of the maze?";	
					quest = 24;
					h4++;
				}	
			}
			if((currentLocation == Location.PROOM && !(objectIsPresent(GameObjects.LAMP))) 
					|| currentLocation == Location.ALCOVE)
			{
				h5++;
				if(h5 == 25)
				{	
					output = "\nAre you trying to explore beyond the Plover Room?";	
					quest = 25;
					h5++;
				}	
			}
			if(currentLocation == Location.WITT)
			{
				h6++;
				if(h6 == 20)
				{	
					output = "\nDo you need help getting out of here?";	
					quest = 26;
					h6++;
				}	
			}		
		}
		return output;
	}
	
	private String hintMessage(int cost)
	{
		String output = "\nI am prepared to give you a hint, but it will cost you " + cost + " points."
				+ "\nDo you want the hint?";
		return output;
	}
	
	private void giveHint(int cost)
	{
		hint += cost;
		quest = 0;
		if(lamp > 30)
		{	lamp += 30 * cost;	}
	}
	
	private String listItemsHere(Location here)
	{
		String output = "";
		ArrayList<GameObjects> objects = hash.objectsHere(here);
		if(objects != null)
		{
			//output = output + "\n";
			for(GameObjects thing : objects)
			{
				boolean pillow = (objectIsHere(GameObjects.PILLOW));
				output = new String(output + things.getItemDescription(here, thing, 
						light, grateUnlocked, plant, bottle, birdInCage, oilDoor, bearAxe, dragon,
						bear, usedBatteries, broken, chain, haveGold, crystalBridge, collapse, rod1, 
						rod2, bottles, lamps, oysters, pillows, grates, cages, birds, snakes, pillow));
				
				if(things.isTreasure(thing))
				{
					if(!hash.haveIFound(GameObjects.RUG) && thing == GameObjects.RUG_)
					{	hash.wasFound(GameObjects.RUG);	}
					else if(!(thing == GameObjects.RUG_) && !hash.haveIFound(thing))
					{	hash.wasFound(thing);	}
				}
				System.out.println(thing);
			}
		}
		return output;
	}
	
	private int askYesNo(String input)
	{
		int answer = 0;
		if(input.substring(0, 1).equals("y"))
		{	answer = 1;	}
		else if(input.substring(0, 1).equals("n"))
		{	answer = 2;	}
		return answer;
	}
	
	private Object determineNature(String input)
	{
		Object result = GameObjects.NOTHING;
		if(input.length() > 5)
		{	input = input.substring(0, 5);	}
		if(hash.isMovement(input))
		{	result = hash.whichMovement(input);	}
		else if(hash.isObject(input))
		{	result = hash.whichObject(input);	}
		return result;
	}

	private String attemptAction(ActionWords verb, Object other, String alt)
	{
		String output = "I'm game. Would you care to explain how?";
		if(!hash.isObject(other))
		{
			output = new String("I don't see any " + alt + ".");
			increaseTurns = false;
		}
		try
		{
			GameObjects object = (GameObjects) other;
			switch(verb)
			{
				case RELAX: case NOTHING: case ABSTAIN:
					output = "Okay.";
					break;
					
				case TAKE:
					output = new String("You can't be serious!");
					if(object == GameObjects.ROD && objectIsHere(GameObjects.ROD)) { rod1 = 0; }
					if(object == GameObjects.LAMP && lamps != 0) { lamps = 0; }
					if(object == GameObjects.BOTTLE && bottles != 0) { bottles = 0; }
					if(object == GameObjects.PILLOW && pillows != 0) { pillows = 0; }
					if(object == GameObjects.ROD && !objectIsHere(GameObjects.ROD) && objectIsHere(GameObjects.ROD2))
					{
						output = attemptAction(ActionWords.TAKE, GameObjects.ROD2, alt);
						if(rod2 != 0) {	rod2 = 0; }
					}
					else if(object == GameObjects.RUG && !objectIsHere(GameObjects.RUG) 
							&& objectIsHere(GameObjects.RUG_))
					{	output = attemptAction(ActionWords.TAKE, GameObjects.RUG_, alt);	}
					else if(object == GameObjects.ALL)
					{
						ArrayList<GameObjects> itemsHere = hash.objectsHere(currentLocation);
						if(!(itemsHere == null))
						{
							output = "";
							for(GameObjects item : itemsHere)
							{	output += attemptAction(ActionWords.TAKE, item, "") + "\n";	}
						}
						else
						{
							output = "There is nothing to take.";
							increaseTurns = false;
						}
					}
					else if(object == GameObjects.WATER)
					{
						if(hash.objectIsHere(GameObjects.BOTTLE, Location.INHAND))
						{
							if(bottle == 0)
							{
								if(currentLocation.isWaterHere(currentLocation))
								{
									output = new String("You fill the bottle with water.");
									bottle = 1;
								}
								else
								{
									output = new String("I don't see any water.");
									increaseTurns = false;
								}
							}
							else
							{
								output = new String("Your bottle is already full.");
								increaseTurns = false;
							}
						}
						else if(objectIsHere(GameObjects.BOTTLE) && bottle == 1)
						{	output = attemptAction(verb, GameObjects.BOTTLE, alt);	}
						else
						{
							output = new String("You have nothing in which to carry it.");
							increaseTurns = false;
						}
					}
					else if(object == GameObjects.OIL)
					{
						if(hash.objectIsHere(GameObjects.BOTTLE, Location.INHAND))
						{
							if(bottle == 0)
							{
								if(Location.EASTPIT == currentLocation)
								{
									output = new String("You fill the bottle with oil.");
									bottle = 2;
								}
								else
								{
									output = new String("I don't see any oil.");
									increaseTurns = false;
								}
							}
							else
							{
								output = new String("Your bottle is already full.");
								increaseTurns = false;
							}
						}
						else if(objectIsHere(GameObjects.BOTTLE) && bottle == 2)
						{
							output = attemptAction(verb, GameObjects.BOTTLE, alt);
						}
						else
						{
							output = new String("You have nothing in which to carry it.");
							increaseTurns = false;
						}
					}
					else if(object == GameObjects.NOTHING)
					{
						output = "What would you like to take?";
						quest = 3;
						increaseTurns = false;
					}
					else if(objectIsPresent(object))
					{
						if(hash.objectIsHere(object, Location.INHAND))
						{
							output = new String("You are already carrying it!");
							increaseTurns = false;
						}
						else if(object == GameObjects.KNIFE)
						{
							output = new String("The dwarves' knives vanish as they strike the walls of the cave.");
							increaseTurns = false;
						}
						else if(object == GameObjects.PLANT && objectIsHere(object))
						{
							output = new String("The plant has exceptionally deep roots and cannot be pulled free.");
							increaseTurns = false;
						}
						else if(object == GameObjects.BEAR && bear == 3)
						{
							output = "You can't be serious!";
							increaseTurns = false;
						}
						else if(object == GameObjects.BEAR && chain == 0)
						{
							output = new String("The bear is still chained to the wall.");
							increaseTurns = false;
						}
						else if(object == GameObjects.BEAR && chain != 0)
						{
							bear = 2;
							output = "The bear is now following you.";
						}
						else if(object == GameObjects.CHAIN && chain != 1)
						{
							output = new String("The chain is still locked.");
							increaseTurns = false;
						}
						else if(closed && (object == GameObjects.BIRD || object == GameObjects.CAGE))
						{
							output = new String("Oh, leave the poor unhappy bird alone.");
						}
						else if(itemsInHand >= 7)
						{
							output = new String("You can't carry anything more. You'll have to drop something first.");
							increaseTurns = false;
						}
						else if(object == GameObjects.BIRD && objectIsHere(GameObjects.BIRD))
						{
							if(hash.objectIsHere(GameObjects.ROD, Location.INHAND) && !birdInCage)
							{
								output = new String("The bird was unafraid when you entered, but as you approach "
										+ "it becomes disturbed and you cannot catch it.");
								h2++;
								increaseTurns = false;
							}
							else if(!hash.objectIsHere(GameObjects.CAGE, Location.INHAND))
							{
								output = new String("You can catch the bird, but you cannot carry it.");
								increaseTurns = false;
							}
							else if(isInHand(GameObjects.CAGE))
							{
								birdInCage = true;
								takeObject(GameObjects.BIRD);
								itemsInHand--;
								output = okay;
							}
							else
							{
								if(birdInCage)
								{
									takeObject(GameObjects.BIRD);
									takeObject(GameObjects.CAGE);
									itemsInHand--;
									output = okay;
								}
							}
						}
						else if(object == GameObjects.RUG || object == GameObjects.RUG_)
						{
							if(objectIsHere(GameObjects.RUG) || objectIsHere(GameObjects.RUG_))
							{
								takeObject(GameObjects.RUG);
								hash.voidObject(GameObjects.RUG_);
								output = okay;
							}
						}
						else if(object == GameObjects.AXE && bearAxe && bear == 0)
						{
							output = "There is no way past the bear to get the axe, which is probably just as well.";
							increaseTurns = false;
						}
						else if(object == GameObjects.VASE && broken == true)
						{
							output = "You can't be serious!";
						}
						else if(things.canTake(object) && objectIsHere(object))
						{
							takeObject(object);
							output = okay;
						}
					}
					else
					{
						if(closed && object == GameObjects.MIRROR){	}
						else
						{
							output = new String("I don't see any " + alt + ".");
						}
						increaseTurns = false;
					}
					if(object == GameObjects.OYSTER && oysters != 0) 
					{
						oysters = 0; 
						output = "Interesting. There seems to be something written on the under-side of the oyster.";
					}
				break;
					
				case DROP:
					output = new String("");
					if(isInHand(GameObjects.ROD2) && object == GameObjects.ROD && !isInHand(GameObjects.ROD))
					{
						dropObject(GameObjects.ROD2);
						output = okay;
					}
					else if(object == GameObjects.ALL)
					{
						ArrayList<GameObjects> itemsHere = hash.objectsHere(Location.INHAND);
						if(!(itemsHere == null))
						{
							output = "";
							for(GameObjects item : itemsHere)
							{
								output += attemptAction(ActionWords.DROP, item, "") + "\n"; 
							}
						}
						else
						{
							output = "You aren't carrying anything.";
							increaseTurns = false;
						}
					}
					else if(object == GameObjects.NOTHING)
					{
						output = "What would you like to drop?";
						quest = 4;
						increaseTurns = false;
					}
					else if(object == GameObjects.BEAR)
					{
						if(bear == 2)
						{
							if(objectIsHere(GameObjects.TROLL)||objectIsHere(GameObjects.TROLL_))
							{
								voidObject(GameObjects.TROLL);
								voidObject(GameObjects.TROLL_);
								hash.dropObject(GameObjects.TROLL2_, Location.NESIDE);
								hash.dropObject(GameObjects.TROLL2, Location.SWSIDE);
								bear = 4;
								troll = 2;
								output = "The bear lumbers toward the troll, who lets out a startled shriek and "
										+ "scurries away. The bear soon gives up pursuit and wanders back.";
							}
							else
							{
								bear = 4;
								output = okay;
							}
						}
						else
						{
							output = dontHave;
						}
					}
					else if(isInHand(object))
					{
						if(object == GameObjects.CAGE && birdInCage)
						{
							dropObject(GameObjects.CAGE);
							itemsInHand++;
							dropObject(GameObjects.BIRD);
							output = okay;
						}
						else if(object == GameObjects.BIRD)
						{
							if(hash.objectIsHere(GameObjects.SNAKE, currentLocation))
							{
								birdInCage = false;
								itemsInHand++;
								dropObject(GameObjects.BIRD);
								voidObject(GameObjects.SNAKE);
								snake = false;
								output = new String("The little bird attacks the green snake, and in an astounding "
										+ "flurry drives the snake away.");
								if(closed)
								{
									dead = true;
									fatality = 2;
								}
							}
							else if(objectIsHere(GameObjects.DRAGON) || objectIsHere(GameObjects.DRAGON_))
							{
								output = new String("The little bird attacks the green dragon, and in an astounding "
										+ "flurry gets burnt to a cinder. The ashes blow away.");
								birdInCage = false;
								voidObject(GameObjects.BIRD);
							}
							else
							{
								output = okay;
								birdInCage = false;
								itemsInHand++;
								dropObject(GameObjects.BIRD);
							}
						}
						else if(object == GameObjects.COINS && objectIsHere(GameObjects.PONY))
						{
							voidObject(GameObjects.COINS);
							lostTreasures++;
							//itemsInHand++;
							dropObject(GameObjects.BATTERIES);
							usedBatteries = 1;
						}
						else if(object == GameObjects.VASE && !(objectIsHere(GameObjects.PILLOW) 
								|| currentLocation == Location.SOFT))
						{
							output = "The Ming vase drops with a delicate crash.";
							dropObject(GameObjects.VASE);
							broken = true;
							lostTreasures++;
						}
						else
						{
							dropObject(object);
							output = okay;
						}	
					}
					else
					{
						output = dontHave;
						increaseTurns = false;
					}
					break;
					
				case OPEN:
					if(object == GameObjects.GRATE)
					{
						if(objectIsPresent(GameObjects.GRATE) || objectIsPresent(GameObjects.GRATE_))
						{
							output = new String("The grate is now unlocked.");
							grateUnlocked = true;
						}
						else if(!objectIsPresent(GameObjects.KEYS))
						{
							output = new String("You don't have any keys!");
							increaseTurns = false;
						}
						else
						{
							output = new String("I don't see any grate");
							increaseTurns = false;
						}
					}
					else if(objectIsPresent(object))
					{
						if(object == GameObjects.CLAM)
						{
							if(isInHand(GameObjects.CLAM))
							{
								output = new String("I advise you to put down the clam before opening it. >STRAIN!<");
								increaseTurns = false;
							}
							else if(!isInHand(GameObjects.TRIDENT))
							{
								output = new String("You don't have anything strong enough to open the clam.");
								increaseTurns = false;
							}
							else
							{
								voidObject(GameObjects.CLAM);
								itemsInHand++;
								dropObject(GameObjects.OYSTER);
								output = new String("A glistening pearl falls out of the clam and rolls away. "
										+ "Goodness, this must really be an oyster! (I never was very good at "
										+ "identifying bivalves.)\nWhatever it is, it has now snapped shut again.");
								hash.dropObject(GameObjects.PEARL, Location.CULDESAC);
							}
						}
						else if(object == GameObjects.OYSTER)
						{
							if(isInHand(GameObjects.OYSTER))
							{
								output = new String("I advise you to put down the oyster before opening it. >WRENCH!<");
								increaseTurns = false;
							}
							else if(!isInHand(GameObjects.TRIDENT))
							{
								output = new String("You don't have anything strong enough to open the oyster.");
								increaseTurns = false;
							}
							else
							{
								output = new String("The oyster creaks open, revealing nothing but oyster inside. "
										+ "It promptly snaps shut again.");
							}
						}
						else if(object == GameObjects.DOOR)
						{
							if(oilDoor)
							{
								output = okay;
							}
							else
							{
								output = new String("The door is extremely rusty and refuses to open.");
								increaseTurns = false;
							}
						}
						else if(object == GameObjects.CAGE)
						{
							output = new String("It has no lock.");
							increaseTurns = false;
						}
						else if(object == GameObjects.KEYS)
						{
							output = new String("You can't unlock the keys.");
							increaseTurns = false;
						}
						else if(object == GameObjects.CHAIN)
						{
							if(!objectIsPresent(GameObjects.KEYS))
							{
								output = new String("You have no keys!");
								increaseTurns = false;
							}
							else if(chain == 0)
							{
								if(bear != 1)
								{
									output = new String("There is no way to get past the bear to unlock the chain, "
											+ "which is probably just as well.");
									increaseTurns = false;
								}
								else
								{
									chain = 1;
									output = new String("You unlock the chain and set the tame bear free.");
								}
							}
							else if(chain == 2)
							{
								chain = 1;
								output = new String("The chain is now unlocked.");
							}
							else
							{
								output = new String("It was already unlocked.");
								increaseTurns = false;
							}
						}
					}
					else if(object == GameObjects.NOTHING)
					{
						output = "What would you like to open?";
						quest = 5;
						increaseTurns = false;
					}
					else
					{
						output = new String("I don't see any " + alt + ".");
						increaseTurns = false;
					}
					break;
									
				case CLOSE:
					if(object == GameObjects.GRATE || object == GameObjects.GRATE_)
					{
						if(!objectIsPresent(GameObjects.KEYS))
						{
							output = new String("You don't have any keys!");
							increaseTurns = false;
						}
						else if(!grateUnlocked)
						{
							output = new String("It was already locked.");
							increaseTurns = false;
						}
						else
						{
							output = new String("The grate is now locked.");
							grateUnlocked = false;
						}
					}
					else if(objectIsPresent(object))
					{
						if(object == GameObjects.CLAM || object == GameObjects.OYSTER)
						{
							output = new String("What?");
							increaseTurns = false;
						}
						else if(object == GameObjects.CAGE)
						{
							output = new String("It has no lock.");
							increaseTurns = false;
						}
						else if(object == GameObjects.KEYS)
						{
							output = new String("You can't lock the keys.");
							increaseTurns = false;
						}
						else if(object == GameObjects.CHAIN)
						{
							if(!objectIsPresent(GameObjects.KEYS))
							{
								output = new String("You have no keys!");
								increaseTurns = false;
							}
							else if(chain != 1)
							{
								output = new String("It was already locked.");
								increaseTurns = false;
							}
							else if(chain == 1)
							{
								if(!(currentLocation == Location.BARR))
								{
									output = new String("There is nothing here to which the chain can be locked.");
									increaseTurns = false;
								}
								else
								{
									chain = 2;
									output = new String("The chain is now locked.");
								}
							}
						}
					}
					else
					{
						output = new String("I don't know how to lock or unlock such a thing.");
						increaseTurns = false;
					}
					break;
					
				case ON:
					if(objectIsPresent(GameObjects.LAMP))
					{
						if(lamp > 0)
						{
							if(canISee(currentLocation))
							{
								output = new String("Your lamp is now on.");
								light = true;
							}
							else
							{
								output = new String("Your lamp is now on.\n\n" 
							+ getDescription(currentLocation, brief));
								light = true;
							}
						}
						else
						{
							output = new String("Your lamp has run out of power.");
							increaseTurns = false;
						}
					}
					else if(object == GameObjects.NOTHING || objectIsPresent(object))
					{
						output = new String("You have no source of light.");
						increaseTurns = false;
					}
					else
					{
						output = new String("I don't see any " + alt + ".");
						increaseTurns = false;
					}
					break;
					
				case OFF: 
					if(objectIsPresent(GameObjects.LAMP))
					{
						light = false;
						output = new String("Your lamp is now off.");
						if(!canISee(currentLocation))
						{
							output = output + "\n\nIt is now pitch dark. If you proceed you will likely fall into a "
									+ "pit.";
						}
					}
					break;
					
				case WAVE:
					if(object == GameObjects.NOTHING)
					{
						output = new String("What would you like to wave?");
						increaseTurns = false;
					}
					else if (!isInHand(object) && (object != GameObjects.ROD || !isInHand(GameObjects.ROD2)))
					 {
						output = dontHave;
						increaseTurns = false;
					 }
					 else if(object != GameObjects.ROD || closed)
					 {
						 if(!isInHand(object))
						 {
							output = new String("I don't see any " + alt + ".");
							increaseTurns = false;
						 }
					 }
					 else if((currentLocation != Location.EASTFISSURE && currentLocation != Location.WESTFISSURE))
					 {
						output = nothing;
						increaseTurns = false;
					 }
					 else
					 {
						 if(!closing)
						 {
							 if(!crystalBridge)
							 {
								 output = new String("A crystal bridge now spans the fissure.");
								 hash.dropObject(GameObjects.CRYSTAL, Location.EASTFISSURE);
								 hash.dropObject(GameObjects.CRYSTAL_, Location.WESTFISSURE);
								 crystalBridge = true;
							 }
							 else
							 {
								 output = new String("The crystal bridge has vanished!");
								 voidObject(GameObjects.CRYSTAL);
								 voidObject(GameObjects.CRYSTAL_);
								 crystalBridge = false;
							 }
						 }
						 else
						 {	output = nothing;	}
					 }
					break;
					
				case GO:
					if(alt.equals(""))
					{	output = new String("Where?");	increaseTurns = false;	}
					else
					{	output = attemptMovement(alt);	}
					break;
					
				case POUR:
					if(object == GameObjects.NOTHING || object == GameObjects.BOTTLE)
					{
						if(bottle == 1)
						{	object = GameObjects.WATER;	}
						else if(bottle == 2)
						{	object = GameObjects.OIL;	}
						else
						{
							object = GameObjects.NOTHING;
							output = new String("You can't pour that.");
							increaseTurns = false;
						}					
					}
					if(object == GameObjects.NOTHING)
					{
						output = new String("You can't pour that.");
						//increaseTurns = false;			
					}
					else if(!isInHand(GameObjects.BOTTLE))
					{
						output = "You have nothing to pour.";
						increaseTurns = false;
					}
					else if(object == GameObjects.WATER && bottle == 1)
					{
						if(currentLocation == Location.WESTPIT)
						{
							plant++;
							if(plant == 1)
							{
								output = new String("The plant spurts into furious growth for a few seconds."
										+ "\n\n\tThere is a 12-foot-tall beanstalk stretching up out of the pit, "
										+ "bellowing \"Water!! Water!!\"");
							}
							else if(plant == 2)
							{
								output = new String("The plant grows explosively, almost filling the bottom of "
										+ "the pit.\n\n\tThere is a gigantic beanstalk stretching all the way up "
										+ "to the hole.");
							}
							else if(plant == 3)
							{
								output = new String("You've over-watered the plant! It's shriveling up! It's, It's...");
								voidObject(GameObjects.PLANT);
								voidObject(GameObjects.PLANT2);
								voidObject(GameObjects.PLANT2_);
							}
							else
							{
								output = new String("Your bottle is empty and the ground is wet.");
							}
						}
						else if(objectIsPresent(GameObjects.DOOR))
						{
							oilDoor = false;
							output = new String("The hinges are quite thoroughly rusted now and won't budge.");
						}
						else
						{	output = new String("Your bottle is empty and the ground is wet.");	}
						bottle = 0;
					}
					else if(object == GameObjects.OIL && bottle == 2)
					{
						if(currentLocation == Location.WESTPIT)
						{
							if(plant == 1)
							{
								output = new String("The plant indignantly shakes the oil off its leaves and asks: "
										+ "\"Water?\".");
							}
							else
							{
								output = new String("Your bottle is empty and the ground is wet.");
							}
						}
						else if(objectIsPresent(GameObjects.DOOR))
						{
							oilDoor = true;
							output = new String("The oil has freed up the hinges so that the door will now move,"
									+ " although it requires some effort.");
						}
						else
						{	output = new String("Your bottle is empty and the ground is wet.");	}
						bottle = 0;
					}	
					break;
					
				case EAT:
					if(object == GameObjects.NOTHING)
					{
						output = "What would you like to eat?";
						increaseTurns = false;
					}
					else if(object == GameObjects.FOOD)
					{
						if(!objectIsPresent(GameObjects.FOOD))
						{	output = "You don't have any.";	}
						else
						{
							voidObject(GameObjects.FOOD);
							if(hash.getObjectLocation(GameObjects.FOOD) == Location.INHAND)
							{	itemsInHand--;	}
							output = "Thank you, it was delicious!";
						}
					}
					else
					{	output = "I think I just lost my appetite.";	}
					break;

				case RUB:
					if(object == GameObjects.LAMP)
					{	output = new String("Rubbing the electric lamp is not particularly rewarding. Anyway, "
							+ "nothing exciting happens.");	}
					else
					{	output = new String("Peculiar. Nothing unexpected happens.");	}
					break;
					
				case TOSS:
					if(object == GameObjects.ROD && isInHand(GameObjects.ROD2) && !(isInHand(GameObjects.ROD)))
					{	output = attemptAction(ActionWords.DROP, GameObjects.ROD, "");	}
					else if(!(isInHand(object)))
					{
						output = dontHave;
						increaseTurns = false;
					}
					else if((objectIsHere(GameObjects.TROLL_) || objectIsHere(GameObjects.TROLL)) 
							&& things.isTreasure(object))
					{
						voidObject(object);
						voidObject(GameObjects.TROLL);
						voidObject(GameObjects.TROLL_);
						hash.dropObject(GameObjects.TROLL2, Location.SWSIDE);
						hash.dropObject(GameObjects.TROLL2_, Location.NESIDE);
						troll = 3;
						itemsInHand--;
						output = "The troll catches your treasure and scurries away out of sight.";
						if(object != GameObjects.EGGS)
						{	lostTreasures++;	}
					}
					else if(object == GameObjects.FOOD && objectIsHere(GameObjects.BEAR))
					{	output = attemptAction(ActionWords.FEED, GameObjects.BEAR, "");	}
					else if(!(object == GameObjects.AXE))
					{	output = attemptAction(ActionWords.DROP, object, alt);	}
					else if(objectIsHere(GameObjects.DWARF))
					{
						if(generate() * 3 < 2)
						{
							deadDwarves++;
							dwarvesLeft--;
							dwarves++;
							voidObject(GameObjects.DWARF);
							dwarfPresent = 0;
							output = "You killed a little dwarf.";
							if(deadDwarves == 1)
							{	output += " The body vanishes in a cloud of greasy black smoke.";	}
						}
						else
						{	output = "You attack a little dwarf, but he dodges out of the way.";	}
						hash.dropObject(GameObjects.AXE, currentLocation);
					}
					else if((objectIsHere(GameObjects.DRAGON_) || objectIsHere(GameObjects.DRAGON)) && dragon)
					{
						output = "The axe bounces harmlessly off the dragon's thick scales.";
						dropObject(GameObjects.AXE);
					}
					else if((objectIsHere(GameObjects.TROLL_) || objectIsHere(GameObjects.TROLL)))
					{
						output = "The troll deftly catches the axe, examines it carefully, and tosses it back, "
								+ "declaring, \"Good workmanship, but it's not valuable enough.\"";
					}
					else if(objectIsHere(GameObjects.BEAR) && bear == 0)
					{
						bearAxe = true;
						dropObject(GameObjects.AXE);
						output = "The axe misses and lands near the bear where you can't get at it.";
					}
					else
					{	output = attemptAction(ActionWords.DROP, object, alt);	}
					break;

				case BREAK:
					if(object == GameObjects.NOTHING)
					{
						output = "You can't be serious!";
						increaseTurns = false;
					}
					else if(object == GameObjects.VASE)
					{
						if(isInHand(GameObjects.VASE))
						{
							output = "You have taken the vase and hurled it delicately to the ground.";
							broken = true;
							lostTreasures++;
							dropObject(GameObjects.VASE);
						}
					}
					else if(object == GameObjects.MIRROR)
					{
						if(closed)
						{
							output = "You strike the mirror a resounding blow, whereupon it shatters into "
									+ "a myriad tiny fragments.";
							dead = true;
							fatality = 2;
						}
						else if(objectIsHere(GameObjects.MIRROR) || objectIsHere(GameObjects.MIRROR_))
						{
							output = "It is too far up for you to reach.";
							increaseTurns = false;
						}
						else
						{
							output = "It is beyond your power to do that.";
							increaseTurns = false;
						}
					}
					break;

				case KILL:
					if(object == GameObjects.NOTHING)
					{
						output = "What would you like to kill?";
						quest = 10;
						increaseTurns = false;
					}
					else if(object == GameObjects.BIRD && closed)
					{
						if(objectIsHere(GameObjects.BIRD))
						{
							output = new String("Oh, leave the poor unhappy bird alone.");
						}
						else
						{
							output = new String("I don't see any bird.");
						}
						increaseTurns = false;
					}
					else if(object == GameObjects.BIRD)
					{
						if(objectIsHere(GameObjects.BIRD))
						{
							voidObject(GameObjects.BIRD);
							output = new String("The little bird is now dead. Its body disappears.");
						}
						else
						{
							output = new String("I don't see any bird.");
							increaseTurns = false;
						}
					} 
					else if(object == GameObjects.CLAM || object == GameObjects.OYSTER)
					{
						if(objectIsPresent(object))
						{
							output = new String("The shell is very strong and impervious to attack.");
						}
						else
						{
							output = new String("I don't see any " + alt + ".");
						}
						increaseTurns = false;
					}
					else if(object == GameObjects.SNAKE)
					{
						if(objectIsHere(GameObjects.SNAKE))
						{
							output = new String("Attacking the snake both doesn't work and is very dangerous.");
						}
						else
						{
							output = new String("I don't see any snake.");
						}
						increaseTurns = false;
					}
					else if(object == GameObjects.DWARF && closed)
					{
						dead = true;
						fatality = 2;
					}
					else if(object == GameObjects.DWARF)
					{
						if(objectIsHere(GameObjects.DWARF))
						{
							output = new String("With what? Your bare hands?");
						}
						else
						{
							output = new String("I don't see any dwarf.");
						}
						increaseTurns = false;
					}
					else if(object == GameObjects.TROLL)
					{
						if(objectIsHere(GameObjects.TROLL)|| objectIsHere(GameObjects.TROLL_))
						{
							output = new String("Trolls are close relatives with the rocks and have skin as"
									+ " tough as that of a rhinoceros. The troll fends off your blows effortlessly.");
						}
						else
						{
							output = new String("I don't see any troll.");
						}
						increaseTurns = false;
					}
					else if(object == GameObjects.BEAR)
					{
						if(objectIsHere(GameObjects.BEAR))
						{
							if(bear == 0)
							{
								output = new String("With what? Your bare hands? Against HIS bear hands??");
								increaseTurns = false;
							}
							else if(bear != 3)
							{
								output = new String("The bear is confused; he only wants to be your friend.");
							}
							else
							{
								output = new String("For crying out loud, the poor thing is already dead!");
								increaseTurns = false;
							}
						}
						else
						{
							output = new String("I don't see any bear.");
							increaseTurns = false;
						}
					}
					else if(object == GameObjects.DRAGON)
					{
						if(objectIsPresent(GameObjects.DRAGON) || objectIsPresent(GameObjects.DRAGON_))
						{
							if(!dragon)
							{
								output = new String("For crying out loud, the poor thing is already dead!");
								increaseTurns = false;
							}
							else
							{
								output = new String("With what? Your bare hands?");
								quest = 1;
								increaseTurns = false;
							}
						}
						else
						{
							output = new String("I don't see any dragon.");
							increaseTurns = false;
						}
					}
					else 
					{
						if(!objectIsPresent(object) && !(object == GameObjects.NOTHING))
						{
							output = new String("I don't see any " + alt + ".");
							increaseTurns = false;
						}
						else
						{
							if(objectIsHere(GameObjects.DWARF))
							{	attemptAction(ActionWords.TOSS, GameObjects.AXE, "");	}
							else if(objectIsHere(GameObjects.SNAKE))
							{	attemptAction(ActionWords.KILL, GameObjects.SNAKE, "");	}
							else if(objectIsHere(GameObjects.TROLL) || objectIsHere(GameObjects.TROLL_))
							{	attemptAction(ActionWords.KILL, GameObjects.TROLL, "");	}
							else if(objectIsHere(GameObjects.BEAR))
							{	attemptAction(ActionWords.KILL, GameObjects.BEAR, "");	}
							else if(objectIsHere(GameObjects.BIRD))
							{	attemptAction(ActionWords.KILL, GameObjects.BIRD, "");	}
						}
					}
					break;
					
				case SAY:
					if(alt.equals(""))
					{
						output = new String("What do you want to say?");
						increaseTurns = false;
					}
					else if(other == MessageWords.CUSS)
					{	output = getText(MessageWords.CUSS);	}
					else
					{	output = new String("Okay, \"" + alt + "\".");	}
					break;
					
				case READ:
					if(!canISee(currentLocation))
					{
						output = "You can't read in the dark!";
						increaseTurns = false;
					}
					else if(objectIsPresent(GameObjects.MAG))
					{
						output = "I'm afraid the magazine is written in dwarvish.";
						increaseTurns = false;
					}
					else if(objectIsPresent(GameObjects.TABLET))
					{	output = "'CONGRATULATIONS ON BRINGING LIGHT INTO THE DARK ROOM!'";	}
					else if(objectIsPresent(GameObjects.MESSAGE))
					{	output = "'This is not the maze where the pirate hides his treasure chest.'";	}
					else if(closed && objectIsPresent(GameObjects.OYSTER))
					{
						if(read)
						{	output = "It says the same thing it did before.";	}
						else
						{
							output = "Hmmm, this looks like a clue, which means it'll cost you 10 points to read"
									+ " it. Should I go ahead and read it anyway?";
							quest = 20;
							seriousQuestion = true;
						}
					}
					else
					{	output = "I'm game. Care to explain how?";	}
					break;
					
				case BRIEF:
					if(brief == 0)
					{
						brief = 1;
						output = "Okay, from now on I'll only describe a place in full the first time you come "
								+ "to it. To get the full description, say \"LOOK\".";
					}
					else
					{
						brief = 0;
						output = "Okay, I'll return to giving descriptions in the original fashion.";
					}
					break;
					
				case VERBOSE:
					if(brief == 0)
					{
						brief = 2;
						output = "Okay, from now on I'll describe a place in full every time you come to it.";
					}
					else
					{
						brief = 0;
						output = "Okay, I'll return to giving descriptions in the original fashion.";
					}
					break;
					
				case FIND:
					if(isInHand(object))
					{
						output = new String("You are already carrying it!");
						increaseTurns = false;
					}
					else if(objectIsPresent(object))
					{
						output = new String("I believe what you want is right here with you.");
					}
					else
					{
						output = "I can only tell you what you see as you move about and manipulate things. "
								+ "I can not tell you where remote things are.";
						increaseTurns = false;
					}
					break;
					
				case INVENTORY:
					if(itemsInHand > 0)
					{
						output = "\t   -----" + listItemsHere(Location.INHAND) + "\n\t   -----";
						if(bear == 2)
						{	output = output + "\n\nYou are being followed by a very large, tame bear.";	}
					}
					else
					{	output = new String("\t   -----\n\t\tYou're not carrying anything.\n\t   -----");	}
					break;
					
				case SCORE:
					output = "If you were to quit now, you would score " + getCurrentScore() 
					+ " out of a possible 350. \nDo you indeed wish to quit now?";
					seriousQuestion = true;
					quest = 2;
					increaseTurns = false;
					break;
					
				case QUIT:
					output = "Do you really wish to quit now?";
					seriousQuestion = true;
					quest = 2;
					increaseTurns = false;
					break;
					
				case FEED:
					output = "There is nothing here it wants to eat (except perhaps you).";
					if(object == GameObjects.TROLL && (objectIsHere(GameObjects.TROLL) 
							|| objectIsHere(GameObjects.TROLL_)))
					{
						output = "Gluttony is not one of the troll's vices. Avarice, however, is.";	
					}
					else if(object == GameObjects.DRAGON && (objectIsHere(GameObjects.DRAGON) 
							|| objectIsHere(GameObjects.DRAGON_)))
					{
						if(!dragon)
						{	output = "Don't be ridiculous!";	}
					}
					else if(objectIsHere(object))
					{
						if(object == GameObjects.BIRD)
						{
							output = "It's not hungry (it's merely pinin' for the fjords). Besides, you have"
									+ " no bird seed.";
						}
						else if(object == GameObjects.SNAKE)
						{
							if(!closed && objectIsPresent(GameObjects.BIRD))
							{
								voidObject(GameObjects.BIRD);
								output = "The snake has now devoured your bird.";
							}
						}
						else if(object == GameObjects.BEAR)
						{
							output = "There is nothing here to eat.";
							if(isInHand(GameObjects.FOOD))
							{
								itemsInHand--;
								voidObject(GameObjects.FOOD);
								bear = 1;
								bearAxe = false;
								output = "The bear eagerly wolfs down your food, after which he seems to "
										+ "calm down considerably and even becomes rather friendly.";
							}
						}
						else if(object == GameObjects.DWARF)
						{
							output = "There is nothing here to eat.";
							if(isInHand(GameObjects.FOOD))
							{
								dwarves++;
								output = "You fool, dwarves eat only coal! Now you've made him REALLY mad!";
							}
						}
						else
						{	output = "I'm game. Would you care to explain how?";	}
					}
					else
					{
						if(object == GameObjects.NOTHING)
						{
							output = "What would you like to feed?";
							quest = 11;
							increaseTurns = false;
						}
						else
						{
							output = "I don't see any " + alt + ".";
							increaseTurns = false;
						}
					}
					break;
					
				case WAKE:
					if(closed && object == GameObjects.DWARF)
					{
						output = "You wake the nearest dwarf, who wakes up grumpily, takes one look at you, "
								+ "curses, and grabs for his axe.";
						dead = true;
						fatality = 2;
					}
					else
					{
						output = "You can't be serious!";
						increaseTurns = false;
					}
					break;
					
				case DRINK:
					boolean waterIsHere = currentLocation.isWaterHere(currentLocation);
					output = "You have nothing to drink.";
					if(waterIsHere && !(isInHand(GameObjects.BOTTLE) && bottle == 1))
					{
						output = "You have taken a drink from the stream. The water tastes strongly of "
								+ "minerals, but is not unpleasant. It is extremely cold.";
					}
					else if(isInHand(GameObjects.BOTTLE) && bottle == 1)
					{
						bottle = 0;
						output = "The bottle of water is now empty.";
					}
					else if(!(object == GameObjects.WATER || object == GameObjects.NOTHING))
					{
						output = "You can't be serious!";
						increaseTurns = false;
					}
					break;

				case LOOK:
					battleUpdate = true;
					if(object == GameObjects.NOTHING)
					{
						output = hash.getDescription(currentLocation, 2);
						output += "\n" + listItemsHere(currentLocation);
					}
					else
					{
						output = "Sorry, but I am not allowed to give more detail. I will repeat the long "
								+ "description of your location.\n\n" + hash.getDescription(currentLocation, 2);
					}
					break;
					
				case CALM:
					output = "I'm game. Would you care to explain how?";
					break;

				case FILL:
					boolean liquidHere = (currentLocation.isWaterHere(currentLocation) 
							|| currentLocation == Location.EASTPIT);
					if(object == GameObjects.VASE)
					{
						if(!liquidHere)
						{
							output = "There is nothing here with which to fill the vase.";
							increaseTurns = false;
						}
						else if(!isInHand(GameObjects.VASE))
						{
							output = "You aren't carrying it!";
							increaseTurns = false;
						}
						else
						{
							broken = true;
							hash.dropObject(GameObjects.VASE, currentLocation);
							output = "The sudden change in temperature has delicately shattered the vase.";
							lostTreasures++;
						}
					}
					else if(!(object == GameObjects.NOTHING || object == GameObjects.BOTTLE))
					{
						output = "You can not fill that.";
						increaseTurns = false;
					}
					else if(!isInHand(GameObjects.BOTTLE))
					{
						increaseTurns = false;
						if(object == GameObjects.NOTHING)
						{	output = "You have nothing to fill.";	}
						else
						{	output = "You are not carrying it!";	}
					}
					else if(bottle != 0)
					{
						output = "Your bottle is already full.";
						increaseTurns = false;
					}
					else if(!liquidHere)
					{
						output = "You have nothing with which to fill the bottle.";
						increaseTurns = false;
					}
					else if(currentLocation == Location.EASTPIT)
					{
						increaseTurns = true;
						output = new String("You fill the bottle with oil.");
						bottle = 2;
					}
					else
					{
						increaseTurns = true;
						output = new String("You fill the bottle with water.");
						bottle = 1;
					}
					break;
					
				case BLAST:
					if(closed)
					{
						bonus = (objectIsPresent(GameObjects.ROD2) ? 25 : currentLocation == Location.NEEND ? 30 : 45);
						switch(bonus)
						{
							case 25:
								output = "There is a loud explosion, and you are suddenly splashed across the "
										+ "walls of the room.";
								break;
							case 30:
								output = "There is a loud explosion, and a twenty-foot hole appears in the far"
										+ " wall, burying the snakes in the rubble. A river of molten lava pours"
										+ " in through the hole, destroying everything in its path, including you!";
								break;
							default:
								output = "There is a loud explosion, and a twenty-foot hole appears in the far "
										+ "wall, burying the dwarves in the rubble. You march through the hole "
										+ "and find yourself in the Main Office, where a cheering band of "
										+ "friendly elves carry the conquering adventurer off into the sunset.";		
						}
						over = true;
					}
					else
					{	output = "Blasting requires dynamite.";	}
					break;
					
				case FEEFIE:
					boolean fum = (alt.equals(feeFieFoe[foo]));
					System.out.println(alt + " " + fum);
					if(fum)
					{
						if(foo < 3)
						{
							quest = 7;
							foo++;
							output = okay;
						}
						else
						{
							quest = 0;
							foo = 0;
							if(hash.objectIsHere(GameObjects.EGGS, Location.GIANT))
							{
								output = nothing;
							}
							else if(currentLocation != Location.GIANT)
							{
								if(hash.getObjectLocation(GameObjects.EGGS) == Location.INHAND)
								{	itemsInHand--;	}
								if(objectIsPresent(GameObjects.EGGS))
								{	output = "The nest of golden eggs disappears!";	}
								else
								{	output = "Done!";	}
								hash.dropObject(GameObjects.EGGS, Location.GIANT);
							}
							else
							{
								output = "There is a large nest here, full of golden eggs!";
								hash.dropObject(GameObjects.EGGS, Location.GIANT);
							}
						}
					}
					else
					{
						output = "What's the matter, can't you read? Now you'd best start over.";
						quest = 0;
						foo = 0;
					}
					break;
					
				default:
					output = new String("You broke something.");
					increaseTurns = false;
					break;
			}
		}
		catch(ClassCastException e)
		{
			if(verb == ActionWords.GO)
			{
				if(alt.equals(""))
				{
					output = new String("Where?");
					increaseTurns = false;
				}
				else
				{	output = attemptMovement(alt);	}
			}
			else
			{
				output = "You can not do that.";
				increaseTurns = false;
			}
		}
		return output;
	}
	
	private String attemptMovement(String input)
	{
		battleUpdate = true;
		String output = "";
		locationChange = true;
		haveGold = (hash.objectIsHere(GameObjects.GOLD, Location.INHAND));
		boolean haveEmerald = (hash.objectIsHere(GameObjects.EMERALD, Location.INHAND));
		boolean haveClam = (hash.objectIsHere(GameObjects.CLAM, Location.INHAND));
		boolean haveOyster = (hash.objectIsHere(GameObjects.OYSTER, Location.INHAND));
		boolean trollIsHere = (hash.getObjectLocation(GameObjects.TROLL) == currentLocation 
				|| hash.getObjectLocation(GameObjects.TROLL_) == currentLocation);

		try
		{
			Movement destination = hash.whichMovement(input);
			Location locationResult = currentLocation.moveTo(destination, currentLocation, grateUnlocked,
					haveGold, crystalBridge, snake, haveEmerald, haveClam, haveOyster, plant, oilDoor,
					dragon, troll, trollIsHere, itemsInHand, collapse, bear);
			if(closed && locationResult != Location.THEVOID && (locationResult.compareTo(Location.THEVOID) < 10))
			{
				output = "A mysterious recorded voice groans into life and announces: \n\t\"This exit is "
						+ "closed. Please leave via main office.\"";
				increaseTurns = false;
			}
			else if(justCollapsed)
			{
				setLocation(locationResult);
				output = "Just as you reach the other side, the bridge buckles beneath the weight of the "
						+ "bear, who was still following you around. You scrabble desperately for support, "
						+ "but the bridge collapses you stumble back and fall into the chasm.";
				justCollapsed = false;
				int neordinal = currentLocation.getOrdinal(Location.NESIDE);
				if(currentLocation.getOrdinal(hash.getObjectLocation(GameObjects.CHAIN)) >= neordinal)
				{
					lostTreasures++;
					previousLocation = currentLocation;
				}
				if(currentLocation.getOrdinal(hash.getObjectLocation(GameObjects.SPICES)) >= neordinal)
				{
					lostTreasures++;
					previousLocation = currentLocation;
				}
			}
			else if(locationResult.equals(Location.THEVOID))
			{
				if(destination.equals(Movement.XYZZY)||destination.equals(Movement.PLOVER)
						||destination.equals(Movement.PLUGH))
				{
					output = nothing;
					output = output + "\n" + getDescription(currentLocation, brief);
					increaseTurns = false;
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
					output = "There are no exits in that direction.\n";
					output = output + getDescription(currentLocation, brief);
					increaseTurns = false;
				}
				else
				{
					output = "I don't know how to apply that word here.\n";
					output = output + getDescription(currentLocation, brief);
					increaseTurns = false;
				}
			}
			else if(locationResult.equals(Location.CRACK))
			{
				output = "That crack is far too small for you to follow.";
				increaseTurns = false;
			}
			else if(locationResult.equals(Location.NECK))
			{
				output = "You are at the bottom of the pit with a broken neck.";
				increaseTurns = false;
				dead = true;
				fatality = 1;
			}
			else if(locationResult.equals(Location.LOSE))
			{
				output = "You didn't make it.";
				increaseTurns = false;
				dead = true;
				fatality = 1;
			}
			else if(locationResult.equals(Location.CANT))
			{
				output = "The dome is unclimbable.";
				increaseTurns = false;
			}
			else if(locationResult.equals(Location.CLIMB))
			{
				setLocation(Location.NARROW);
				output = "You clamber up the plant and scurry through the hole at the top.\n" 
						+ getDescription(currentLocation, brief);
			}
			else if(locationResult.equals(Location.CHECK))
			{
				if(plant == 1)
				{
					setLocation(Location.WEST2PIT);
					output = "You have climbed up the plant and out of the pit.\n" 
							+ getDescription(currentLocation, brief);
				}
				else
				{
					output = "There is nothing here to climb. Use \"UP\" or \"OUT\" to leave the pit.";
					increaseTurns = false;
				}
			}
			else if(locationResult.equals(Location.SNAKED))
			{
				output = "You can't get by the snake.";
				increaseTurns = false;
			}
			else if(locationResult.equals(Location.THRU))
			{
				setLocation(Location.WESTMIST);
				output = "You have crawled through a very low wide passage parallel to and north "
						+ "of the Hall of Mists.\n" + getDescription(currentLocation, brief);
			}
			else if(locationResult.equals(Location.DUCK))
			{
				setLocation(Location.WESTFISSURE);
				output = "You have crawled through a very low wide passage parallel to and north "
						+ "of the Hall of Mists.\n" + getDescription(currentLocation, brief);
			}
			else if(locationResult.equals(Location.SEWER))
			{
				output = "The stream flows out through a pair of 1-foot-diameter sewer pipes."
						+ "\n\nIt would be advisable to use the exit.";
				increaseTurns = false;
			}
			else if(locationResult.equals(Location.UPNOUT))
			{
				output = "There is nothing here to climb. Use \"UP\" or \"OUT\" to leave the pit.";
				increaseTurns = false;
			}
			else if(locationResult.equals(Location.DIDIT))
			{
				setLocation(Location.WEST2PIT);
				output = "You have climbed up the plant and out of the pit.\n" 
						+ getDescription(currentLocation, brief);
			}
			else if(locationResult.equals(Location.REMARK))
			{
				if(currentLocation.equals(Location.SLIT)||currentLocation.equals(Location.WET))
				{
					output = "You can't fit through a two-inch slit!";
					increaseTurns = false;
				}
				else if(currentLocation.equals(Location.INSIDE)||currentLocation.equals(Location.OUTSIDE)
						||currentLocation.equals(Location.DEBRIS)||currentLocation.equals(Location.AWKWARD)||
						currentLocation.equals(Location.BIRD)||currentLocation.equals(Location.SMALLPIT))
				{
					output = "You can't go through a locked steel grate!";
					increaseTurns = false;
				}
				else if((currentLocation.equals(Location.SWSIDE) || currentLocation.equals(Location.NESIDE)) 
						&& destination != Movement.JUMP)
				{
					increaseTurns = false;
					if(troll == 0)
					{	output = "The troll refuses to let you cross.";	}
					else if(troll == 1)
					{
						output = "The troll steps out from beneath the bridge and blocks your way.";
						troll = 0;
						voidObject(GameObjects.TROLL2);
						voidObject(GameObjects.TROLL2_);
						hash.dropObject(GameObjects.TROLL, Location.SWSIDE);
						hash.dropObject(GameObjects.TROLL_, Location.NESIDE);
					}
					else
					{	output = "There is no longer any way across the chasm.";	}
				}
				else if(currentLocation.equals(Location.NESIDE)||currentLocation.equals(Location.SWSIDE)
						||currentLocation.equals(Location.EASTFISSURE)
						||currentLocation.equals(Location.WESTFISSURE))
				{
					increaseTurns = false;
					if(destination.equals(Movement.JUMP))
					{	output = "I respectfully suggest that you go across the bridge instead of jumping.";	}
					else
					{	output = "There is no way to cross the fissure.";	}
				}
				else if(currentLocation.equals(Location.HALLOFMOUNTAINKING))
				{
					output = "You can't get by the snake.";
					increaseTurns = false;
				}
				else if(currentLocation.equals(Location.SHELL))
				{
					increaseTurns = false;
					if(haveClam)
					{	output = "You can't fit this five-foot clam through that little passage!";	}
					else
					{	output = "You can't fit this five-foot oyster through that little passage!";	}
				}
				else if(currentLocation.equals(Location.WITT)&&destination.equals(Movement.WEST))
				{
					output = "You have crawled around in some little holes and found your way blocked"
							+ " by a recent cave-in.\nYou are now back in the main passage.";
				}
				else if(currentLocation.equals(Location.WITT)||currentLocation.equals(Location.BEDQUILT)
						||currentLocation.equals(Location.CHEESE))
				{
					output = "You have crawled around in some little holes and wound up back in the main "
							+ "passage.";
				}
				else if(currentLocation.equals(Location.IMMENSE))
				{
					output = "The door is extremely rusty and refuses to open.";
					increaseTurns = false;
				}
				else if(currentLocation.equals(Location.SCAN1)||currentLocation.equals(Location.SCAN3))
				{
					output = "That dragon looks rather nasty. You'd best not try to get by.";
					increaseTurns = false;
				}
				else if(currentLocation.equals(Location.VIEW))
				{
					output = "Don't be ridiculous!";
					increaseTurns = false;
				}
				else if(currentLocation.equals(Location.PROOM) || currentLocation.equals(Location.DROOM))
				{
					output = "Something you are carrying won't fit through the tunnel with you. "
							+ "You'd best take inventory and drop something.";
					increaseTurns = false;
				}
				else
				{
					output = "You can not do that.";
					increaseTurns = false;
				}
			}
			else
			{
				if(closing && currentLocation.outsideCave(locationResult))
				{
					if(!panic)
					{
						clock2 = 15;
						panic = true;
						output = "A mysterious recorded voice groans into life and announces: "
								+ "\n\t\"This exit is closed. Please leave via main office.\"";
					}
				}
				boolean follow = false;
				if(dwarfPresent == 2)
				{
					if(justBlocked)
					{
						follow = true;
						dwarfPresent = 1;
						justBlocked = false;
					}
					else
					{	
						wayIsBlocked = true;
					}
				}

				if(wayIsBlocked)
				{
					locationChange = false;
					output = "A little dwarf with a big knife blocks your way.";
					wayIsBlocked = false;
					justBlocked = true;
				}
				else
				{
					setLocation(locationResult);
					if(!canISee(currentLocation))
					{
						boolean pitifulDeath = fallInPit();
						if(pitifulDeath && !canISee(previousLocation))
						{
							dead = true;
							fatality = 1;
						}
						else
						{
							output = new String("It is now pitch dark. If you proceed you will likely "
									+ "fall into a pit.");
						}
					}
					else
					{
						System.out.println("d " + dwarves);
						if(currentLocation.critters(currentLocation))
						{
							double chance = generate();
							if(pirate == 0)
							{
								movesWOEncounter++;
								double likely = (movesWOEncounter * 10 / 8)/8; 
								if(chance * 100 <= likely)
								{	pirate = 1;	}
								System.out.println("likely " + likely + "\npirate " + pirate);
							}
							if(pirate == 2 && dwarvesOn && dwarves > 0 && dwarvesLeft > 0 && !follow)
							{
								double encounter = (dwarvesLeft * 1000)/60;
								if(chance * 1000 <= encounter)
								{	dwarfPresent = 1;	}
								System.out.println("encounter " + encounter + "\nchance " + chance * 1000);
							}
						}
						output = getDescription(currentLocation, brief);
						if(bear == 2)
						{	output += "\n\tYou are being followed by a very large, tame bear.";	}
						if(follow)
						{	hash.dropObject(GameObjects.DWARF, currentLocation);	}
						if(currentLocation.equals(Location.Y2))
						{
							double chance = generate();
							if(chance > .74)
							{	output = new String(output + "\n\nA hollow voice says \"PLUGH\"");	}
						}
					}
				}
				if(relocate)
				{
					hash.dropObject(GameObjects.EMERALD, Location.PROOM);
					itemsInHand--;
					relocate = false;
				}
			}
		}
		catch(NullPointerException e)
		{
			output = "You can not do that.";
			increaseTurns = false;
		}
		return output;
	}
	
	private void setLocation(Location newLocation)
	{
		eldestLocation = previousLocation;
		previousLocation = currentLocation;
		currentLocation = newLocation;
	}

	private String getDescription(Location here, int brief)
	{
		String output = hash.getDescription(here, brief);
		output = output + listItemsHere(currentLocation);
		return output;
	}
	
	private String nonsense()
	{
		String output = null;
		increaseTurns = false;
		double randomOutput = generate();
		if(randomOutput < .34)
		{	output = "I have no idea what you are talking about!";	}
		else if(randomOutput < .67)
		{	output = "I don't understand that!";	}
		else
		{	output = "You're not making any sense!";	}
		return output;
	}

	private boolean canISee(Location here)
	{
		boolean canSee = false;
		if(currentLocation.dontNeedLamp(here))
		{	canSee = true;	}
		else if(light && objectIsPresent(GameObjects.LAMP))
		{	canSee = true;	}
		System.out.print("can see "); 
		System.out.print(currentLocation.dontNeedLamp(here)); 
		System.out.println("\n");
		System.out.println(currentLocation);
		return canSee;
	}
	
	private int getCurrentScore()
	{
		int currentScore = (2 + (2 * (tally)) + (deaths * 10));
		ArrayList<GameObjects> buildingItems = hash.objectsHere(Location.BUILDING);
		ArrayList<GameObjects> witItems = hash.objectsHere(Location.WITT);
		
		if(!(buildingItems == null))
		{
			for(GameObjects item : buildingItems)
			{
				if(things.isTreasure(item))
				{
					if(things.isLesserTreasure(item))
					{	currentScore = currentScore + 10;	}
					else if(item == GameObjects.VASE && broken){	}
					else if(item == GameObjects.CHEST)
					{	currentScore = currentScore + 12;	}
					else
					{	currentScore = currentScore + 14;	}
				}
			}
		}
		if(!(witItems == null))
		{
			for(GameObjects item : witItems)
			{
				if(item == GameObjects.MAG)
				{	currentScore = currentScore + 1;	}
			}
		}
		currentScore -= hint;
		if(wellInCave){	currentScore = currentScore + 25;	}
		if(closed){	currentScore = currentScore + 25;	}
		if(!quit){	currentScore = currentScore + 4;	}
		currentScore += bonus;
		score = currentScore;
		return currentScore;
	}
	
	@SuppressWarnings("unused")
	private boolean loadGame()
	{
		boolean result = false;
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(frame);
		if(returnVal == JFileChooser.APPROVE_OPTION) 
		{
			try
			{
				BufferedReader reader = new BufferedReader(new FileReader(chooser.getSelectedFile()));
				int row = -1;
				String line = updateLine(reader, row);
				currentLocation = Location.locate[Integer.parseInt(line)];
					line = updateLine(reader, row);
				previousLocation = Location.locate[Integer.parseInt(line)];
					line = updateLine(reader, row);
				
					
					
				//TODO
			}
			catch(FileNotFoundException e){	}
			catch(NullPointerException e){	}
			catch(IOException e){	}
		}
		return result;
	}
	
	private String updateLine(BufferedReader reader, int row) throws IOException
	{
		row++;
		return reader.readLine();
	}
	
	@SuppressWarnings("unused")
	private boolean saveGame()
	{
		boolean result = false;
		//TODO save 
		return result;
	}

	private boolean fallInPit()
	{
		boolean pitifulDeath = false;
		double chance = generate();
		if(chance < .35)
		{
			pitifulDeath = true;
		}
		return pitifulDeath;
	}
	
	private String death(String output)
	{
		if(fatality == 1)
		{
			output = "\n\nYou fell into a pit and broke every bone in your body!";
			previousLocation = currentLocation;
		}
		if(fatality == 2)
		{
			output = "The resulting ruckus has awakened the Dwarves.\nThere are now several threatening "
					+ "little Dwarves in the room with you! Most of them throw knives at you! All of them get you!";
		}
		if(isInHand(GameObjects.LAMP))
		{
			light = false;
			hash.dropObject(GameObjects.LAMP, Location.ROAD);
		}
		attemptAction(ActionWords.DROP, GameObjects.ALL, "");
		currentLocation = Location.BUILDING;
		previousLocation = Location.BUILDING;
		fatality = 0;
		if(closing)
		{
			output += "\n\nIt looks as though you you're dead. Well, seeing as how it's so close to closing "
					+ "time anyway, let's just call it a day.";
			over = true;
		}
		else
		{
			seriousQuestion = true;
			quest = 8;
			currentLocation = Location.BUILDING;
			switch(deaths)
			{
				case 3:
					output += "\n\nOh dear, you seem to have gotten yourself killed. "
							+ "I might be able to help you out, but I've never really done this before. "
							+ "Do you want me to try to reincarnate you?";
					break;
				case 2:
					output += "\n\nYou clumsy oaf, you've done it again! I don't know how long I can keep this"
							+ " up. Do you want me to try reincarnating you again?";
					break;
				default:
					output += "\n\nNow you've really done it! I'm out of orange smoke! You don't expect me "
							+ "to do a decent reincarnation without any orange smoke, do you?";
					break;	
			}
			deaths--;
		}
		return output;
	}
	
	private String quit(String output)
	{
		getCurrentScore();
		if(output == null)
		{	output = "";	}
		else
		{	output += "\n\n";	}
		output += "\tYou scored " + score + " points out of a possible 350, using " + turns + " turn";
		if(turns > 1)
		{	output += "s";	}
		output += ".\n\t";
		boolean found = false;
		for(int i = 0; i < 9; i++)
		{
			if(!found && score <= scores[i])
			{
				found = true;
				output += sMessages[i] + "\n\tTo achieve the next higher rating";
				if(i < 8)
				{	
					int next = 1 + scores[i] - score;
					String s = "s";
					if(next == 1)
					{	s = "";	}
					output += ", you need " + next + " more point" + s + ".";
				}
				else
				{
					output += " would be a neat trick!\n\tCongratulations!!";
				}
			}
		}
		frame.done();
		//output += "\n\n\n\tWould you like to play again?";
		//quest = 9;
		//seriousQuestion = true;
		return output;
	}
	
	private String lamp(String output)
	{
		if(clock2 == 0 || shortcut)
		{
			output = "The sepulchral voice intones, \n\t\"The cave is now closed.\"\nAs the echoes fade, "
					+ "there is a blinding flash of light (and a small puff of orange smoke)..."
					+ "\nThen your eyes refocus: you look around and find...\n";
			closed = true;
			bonus = 10;
			attemptAction(ActionWords.DROP, GameObjects.ALL, "");
			attemptAction(ActionWords.OFF, GameObjects.NOTHING, "");
			if(shortcut)
			{
				wellInCave = true;
				hash.dropObject(GameObjects.GOLD, Location.BUILDING);
				hash.dropObject(GameObjects.DIAMONDS, Location.BUILDING);
				hash.dropObject(GameObjects.SILVER, Location.BUILDING);
				hash.dropObject(GameObjects.JEWELS, Location.BUILDING);
				hash.dropObject(GameObjects.COINS, Location.BUILDING);
				hash.dropObject(GameObjects.CHEST, Location.BUILDING);
				hash.dropObject(GameObjects.EGGS, Location.BUILDING);
				hash.dropObject(GameObjects.TRIDENT, Location.BUILDING);
				hash.dropObject(GameObjects.VASE, Location.BUILDING);
				hash.dropObject(GameObjects.EMERALD, Location.BUILDING);
				hash.dropObject(GameObjects.PYRAMID, Location.BUILDING);
				hash.dropObject(GameObjects.PEARL, Location.BUILDING);
				hash.dropObject(GameObjects.RUG, Location.BUILDING);
				hash.dropObject(GameObjects.SPICES, Location.BUILDING);
				hash.dropObject(GameObjects.CHAIN, Location.BUILDING);
				hash.dropObject(GameObjects.MAG, Location.WITT);
				shortcut = false;
			}
			hash.dropObject(GameObjects.BOTTLE, Location.NEEND);
			hash.dropObject(GameObjects.PLANT, Location.NEEND);
			hash.dropObject(GameObjects.OYSTER, Location.NEEND);
			hash.dropObject(GameObjects.LAMP, Location.NEEND);
			hash.dropObject(GameObjects.ROD, Location.NEEND);
			hash.dropObject(GameObjects.DWARF, Location.NEEND);
			hash.dropObject(GameObjects.MIRROR, Location.NEEND);
			currentLocation = Location.NEEND;
			previousLocation = Location.NEEND;
			hash.dropObject(GameObjects.GRATE, Location.SWEND);
			hash.dropObject(GameObjects.SNAKE, Location.SWEND);
			hash.dropObject(GameObjects.BIRD, Location.SWEND);
			hash.dropObject(GameObjects.CAGE, Location.SWEND);
			hash.dropObject(GameObjects.ROD2, Location.SWEND);
			hash.dropObject(GameObjects.PILLOW, Location.SWEND);
			hash.dropObject(GameObjects.MIRROR, Location.SWEND);
			rod1 = 1;
			rod2 = 1;
			bottles = 1;
			pillows = 1;
			lamps = 1;
			oysters = 1;
			grates = 1;
			cages = 1;
			birds = 1;
			snakes = 1;
			plant = 3;
			output += hash.getDescription(currentLocation, brief);
			clock1 = -2;
			clock2 = -2;
		}
		else if(clock1 == -1)
		{	clock2--;	}
		else if(clock1 == 0)
		{
			output = "A sepulchral voice, reverberating through the cave, says, \n\t\"Cave closing soon. "
					+ "All adventurers exit immediately through main office.\"";
			clock1 = -1;
			dwarvesLeft = 0;
			voidObject(GameObjects.TROLL);
			voidObject(GameObjects.TROLL_);
			hash.dropObject(GameObjects.TROLL2_, Location.NESIDE);
			hash.dropObject(GameObjects.TROLL2, Location.SWSIDE);
			if(bear != 3)
			{	voidObject(GameObjects.BEAR);	}
			grateUnlocked = false;
			closing = true;
		}
		else if(tally == 15 && !(currentLocation.outsideCave(currentLocation)) && !(currentLocation == Location.Y2))
		{	clock1--;	}
		else if(light && !(closing || closed))
		{
			if(lamp < 0)
			{
				output = output + "\nYour lamp has run out of power.";
				if(!canISee(currentLocation))
				{	output = output + "\nIt is now pitch dark. If you proceed you will likely fall into a pit.";	}
				light = false;
			}
			else if(lamp < 0 && currentLocation.outside(currentLocation))
			{
				output += "\n\nThere's not much point in wandering around out here, and you can't explore the "
						+ "gave without a lamp. So let's just call it a day.";
				over = true;
			}
			else if(lamp < 31 && usedBatteries == 1 && objectIsPresent(GameObjects.BATTERIES) 
					&& objectIsPresent(GameObjects.LAMP))
			{
				output += "\n\nYour lamp is getting dim. I'm taking the liberty of replacing the batteries.";
				dropObject(GameObjects.BATTERIES);
				usedBatteries = 2;
				lamp = 2500;
			}
			else if(lamp < 31 && !lampWarn && objectIsPresent(GameObjects.LAMP))
			{
				String dim = "\n\nYour lamp is getting dim";
				if(usedBatteries == 2)
				{	output += dim + ", and you're out of spare batteries. You'd best start wrapping this up.";	}
				else if(usedBatteries == 1)
				{	output += dim + ". You'd best go back for those batteries.";	}
				else
				{	
					output += dim + ". You'd best start wrapping this up, unless you can find some fresh "
						+ "batteries. I seem to recall that there's a vending machine in the maze."
						+ " Bring some coins with you.";	
				}
				lampWarn = true;
			}
			else
			{	lamp--;	}
		}
		return output;
	}
	
	private void takeObject(GameObjects thing)
	{
		hash.takeObject(thing);
		itemsInHand++;
	}

	private boolean isInHand(GameObjects thing)
	{	return hash.objectIsHere(thing, Location.INHAND);	}
	
	private boolean objectIsHere(GameObjects thing)
	{	return hash.objectIsHere(thing, currentLocation);	}
	
	private boolean objectIsPresent(GameObjects thing)
	{
		boolean result = false;
		if(objectIsHere(thing) || isInHand(thing))
		{	result = true;	}
		return result;
	}
	
	private void dropObject(GameObjects thing)
	{
		hash.dropObject(thing, currentLocation);
		itemsInHand--;
	}
	
	private void voidObject(GameObjects thing)
	{	hash.voidObject(thing);	}
	
	public String getText(MessageWords input)
	{
		String result = "I don't know how.";
		switch(input)
		{
			case MAGIC:
				result = new String("Good try, but that is an old worn-out magic word.");
				break;
			case HELP:
				result = new String("I know of places, actions, and things. "
						+ "Most of my vocabulary describes places and is used to move you there."
						+ "To move, try words like forest, building, downstream, enter, east, west, north, south,"
						+ " up, or down. "
						+ "I know about a few special objects, like a black rod hidden in the cave. "
						+ "These objects can be manipulated using some of the action words that I know. "
						+ "Usually you will need to give both the object and the action words (in either order),"
						+ " but sometimes I can infer the object from the verb alone. "
						+ "Some objects also imply verbs; in particular, \"inventory\" implies \"take"
						+ " inventory\", which causes me to give you a list of what you are carrying. "
						+ "The objects have side effects; for instance, the rod scares the bird. "
						+ "Usually people having trouble moving just need to try a few more words. "
						+ "Usually people trying unsuccessfully to manupulate an object are attempting beyond "
						+ "their (or my!) capabilities and should try a completely different tack. "
						+ "To speed the game you can sometimes move long distances with a single word. "
						+ "For example, \"building\" usually gets you to the building from anywhere above ground "
						+ "except when lost in the forest. "
						+ "Also, note that cave passages turn a lot, and that leaving a room to the north does "
						+ "not guarantee entering the next from the south. \nGood luck!");
				break;
			case TREE:
				result = new String("The trees of the forest are large hardwood oak and maple, with an occasional "
						+ "grove of pine or spruce. "
						+ "There is quite a bit of under-growth, largely birch and ash saplings plus nondescript"
						+ " bushes of various sorts. "
						+ "This time of year visibility is quite restricted by all the leaves, but travel is quite"
						+ " easy if you detour around the spruce and berry bushes.");
				break;
			case DIG:
				result = new String("Digging without a shovel is quite impractical. Even with a shovel progress "
						+ "is unlikely.");
				break;
			case LOST:
				result = new String("I'm as confused as you are.");
				break;
			case MIST:
				result = new String("Mist is a white vapor, usually water, seen from time to time in caverns. "
						+ "It can be found anywhere but is frequently a sign of a deep pit leading down to water.");
				break;
			case CUSS:
				result = new String("Watch it!");
				break;
			case INFO:
				result = new String("If you want to end your adventure early, say \"quit\". "
						+ "To get full credit for a treasure, you must have left it safely in the building, "
						+ "though you get partial credit just for locating it. "
						+ "You lose points for getting killed, or for quitting, though the former costs you more. "
						+ "There are also points based on how much (if any) of the cave you've managed to "
						+ "explore; in particular, there is a large bonus just for getting in (to distinguish "
						+ "the beginners from the rest of the pack), and there are other ways to determine "
						+ "whether you've been through some of the more harrowing sections. "
						+ "If you think you've found all the treasures, just keep exploring for a while. "
						+ "If nothing interesting happens, you haven't found them all yet. If something "
						+ "interesting DOES happen, it means you're getting a bonus and have an opportunity"
						+ " to garner many more points in the master's section. "
						+ "I may occasionally offer hints if you seem to be having trouble. If I do, I will "
						+ "warn you in advance how much it will affect your score to accept the hints. "
						+ "Finally, to save paper, you may specify \"brief\", which tells me never to repeat "
						+ "the full description of a place unless you explicitly ask me to. "
						+ "You may also specify \"verbose\", which tells me only to repeat the long "
						+ "description of a place.");
				break;
			case DENNIS:
				result = new String("Thou cannotst go there. Who do you think thou art? A magistrate?!");
				break;
			default:
				result = new String("I don't know how.");
				break;
		}
		System.out.println(result);
		return result;
	}
	
	public static void relocate()
	{	relocate = true;	}

	public boolean noMore()
	{	return noMore;	}
	
	public int getTurns()
	{	return turns;	}
	
	public int getScore()
	{
		if(beginning)
		{	return 0;	}
		return score;
	}
	
	public static void updateTally()
	{	tally++;	}
	
	public static void collapse()
	{
		justCollapsed = true;
		collapse = true;
		bear = 3;
	}
	
	public static double generate()
	{	return random.nextDouble();	}
	
	public static void setTroll()
	{	troll = 1;	}
	
}