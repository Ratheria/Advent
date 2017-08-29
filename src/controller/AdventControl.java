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
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ActionWords;
import model.GameObjects;
import model.HashMaps;
import model.Movement;
import view.AdventureFrame;

public class AdventControl 
{
	private AdventureFrame frame = new AdventureFrame(this);;
	private HashMaps hash = new HashMaps();
	private MessageWords messages;
	@SuppressWarnings("unused")
	private ActionWords actions;
	private GameObjects things;
	private Location currentLocation;
	private Location previousLocation;
	private String okay;
	private boolean dead;
	private boolean beginning;
	private boolean closed;
	private boolean grateUnlocked;
	private boolean crystalBridge;
	private boolean light;
	private boolean snake;
	private boolean oilDoor;
	private boolean dragon;
<<<<<<< HEAD
=======
	private boolean dragonQuest;
>>>>>>> parent of 0c57575... Work on continuing actions, questions, and score
	private boolean troll;
	private boolean birdInCage;
	private boolean bearAxe;
	private boolean usedBatteries;
	private boolean broken;
	private boolean haveGold;
	private boolean collapse;
	private boolean wayIsBlocked;
<<<<<<< HEAD
	private boolean seriousQuestion;
=======
	private boolean question;
>>>>>>> parent of 0c57575... Work on continuing actions, questions, and score
	
	private boolean instructions;
	private boolean enteredCave;
	private boolean quit;
	
	private boolean increaseTurns;
	private int quest;
	private int brief;
	private int score;
	private int turns;
	private int lamp;
	private int itemsInHand;
	private int deaths;
	private int tally;
	private int lostTreasures;
	private int plant;
	private int bottle;
	private int bear;
	//default, fed + idle, fed + following, dead
	private int chain;
	//locked to bear, unlocked, locked
	//	private String beforeTurnBeforeLast;
	//	private String turnBeforeLast;
	//	private String turnLast;
	private int west;
	
	public AdventControl()
	{
		//		beforeTurnBeforeLast = "";
		//		turnBeforeLast = "";
		//		turnLast = "";
		currentLocation = Location.ROAD;
		previousLocation = null;
		actions = ActionWords.NOTHING;
		things = GameObjects.NOTHING;
		okay = new String("Okay.");
		dead = false;
		beginning = true;
		closed = false;
		grateUnlocked = false;
		crystalBridge = false;
		light = false;
		snake = true;
		oilDoor = false;
		dragon = true;
<<<<<<< HEAD
=======
		dragonQuest = false;
>>>>>>> parent of 0c57575... Work on continuing actions, questions, and score
		troll = true;
		birdInCage = false;
		bearAxe = false;
		broken = false;
		haveGold = false;
		collapse = false;
		wayIsBlocked = false;
<<<<<<< HEAD
		seriousQuestion = false;
=======
		question = false;
>>>>>>> parent of 0c57575... Work on continuing actions, questions, and score
		instructions = false;
		enteredCave = false;
		quit = false;
		increaseTurns = false;
		quest = 0;
		brief = 0;
		score = 36;
		turns = 1;
		lamp = 1000;
		itemsInHand = 0;
		deaths = 3;
		tally = 15;
		lostTreasures = 0;
		plant = 0;
		bottle = 1;
		bear = 0;
		chain = 0;
		west = 0;
		currentLocation.setUp(this);
		things.setUp();
	}

	public String determineAction(String input) 
	{
		String output = null;
		increaseTurns = true;
<<<<<<< HEAD
		int answer = askYesNo(input);
		boolean thisIsAnObject = hash.isObject(input);
		GameObjects itsAn = null;
		if(thisIsAnObject)
		{
			itsAn = (hash.whichObject(input));
		}
=======
>>>>>>> parent of 0c57575... Work on continuing actions, questions, and score
		if(beginning)
		{
			//TODO your answer changes your score and lamp value
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
				increaseTurns = false;
			}
		}
<<<<<<< HEAD
		else if(quest == 1 && (input.toLowerCase().contains("yes") || input.equalsIgnoreCase("y") || input.toLowerCase().contains("yes")))
		{
			output = "Congratulations! You have just vanquished a dragon with your bare hands! (Unbelievable, isn't it?)";
			quest = 0;
			dragon = false;
		}
		else if(seriousQuestion && answer == 0)
		{
			output = "Just yes or no, please.";
			increaseTurns = false;
		}
		else if(quest == 2 && answer == 1)
		{
			//TODO game over
			quest = 0;		
			seriousQuestion = false;
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
		else
		{
			if(quest != 0)
			{
				quest = 0;
=======
		else if(question)
		{
			int answer = askYesNo(input);
			//TODO questions
		}
		else if(dragonQuest && (input.toLowerCase().contains("yes") || input.equalsIgnoreCase("y") || input.toLowerCase().contains("yes")))
		{
			//TODO you killed the dragon message
			output = "Dead";
			dragonQuest = false;
			dragon = false;
		}
		else
		{
			if(dragonQuest)
			{
				dragonQuest = false;
>>>>>>> parent of 0c57575... Work on continuing actions, questions, and score
			}
			if(input.length() > 5)
			{
				input = input.substring(0, 5);
			}
			if(hash.isMovement(input))
			{				
				output = attemptMovement(input);
			}
			else if(hash.isObject(input) && objectIsPresent(hash.whichObject(input)) && hash.whichObject(input) == GameObjects.KNIFE)
			{
				output = new String("The dwarves' knives vanish as they strike the walls of the cave.");
				increaseTurns = false;
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
			else if(hash.isAction(input))
			{
				output = attemptAction(hash.whichAction(input), GameObjects.NOTHING, "");
			}
			else if(hash.isMessage(input))
			{
				MessageWords message = hash.whichMessage(input);
				output = getText(message);
				increaseTurns = false;
			}
			else
			{
				output = nonsense();
			}
		}
		if(light)
		{
			lamp--;
		}
		if(lamp < 0)
		{
			//TODO batteries
			output = output + "\nYour lamp has run out of power.";
			if(!canISee(currentLocation))
			{
				output = output + "\nIt is now pitch dark. If you proceed you will likely fall into a pit.";
			}
			light = false;
		}
		if(input.equalsIgnoreCase("west"))
		{
			west++;
		}
		if(increaseTurns)
		{
			turns++;
		}
		getCurrentScore();
		output = output + checkForHints();
		System.out.println("previous " + previousLocation);
		System.out.println("current " + currentLocation);
		System.out.println("lamp " + lamp);
		System.out.println("items " + itemsInHand);
		return output;
	}

	public String determineAction(String input1, String input2) 
	{
		String output = null;
		increaseTurns = true;
<<<<<<< HEAD
		if(!seriousQuestion && quest != 0)
		{
			quest = 0;
=======
		if(dragonQuest)
		{
			dragonQuest = false;
>>>>>>> parent of 0c57575... Work on continuing actions, questions, and score
		}
		if(beginning||question)
		{
			output = "Just yes or no, please.";
			increaseTurns = false;
		}
		else
		{
			String input12 = input1;
			String input22 = input2;
			
			if(input1.length() > 5)
			{
				input12 = input1.substring(0, 5);
			}
			if(input2.length() > 5)
			{
				input22 = input2.substring(0, 5);
			}
			if(hash.isMovement(input12))
			{
				if(hash.whichMovement(input12) == Movement.ENTER)
				{
					if(input22.equalsIgnoreCase("water")||input22.equalsIgnoreCase("strea"))
					{
						if(currentLocation == Location.ROAD || currentLocation == Location.BUILDING || currentLocation == Location.VALLEY || currentLocation == Location.SLIT || currentLocation == Location.WET || currentLocation == Location.FALLS || currentLocation == Location.RESER)
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
					{
						output = attemptAction(ActionWords.GO, determineNature(input22), input2);
					}
				}
				else
				{
					output = attemptMovement(input12);
				}
			}
			else if(hash.isMovement(input22))
			{
				output = attemptMovement(input22);
			}
			else if(hash.isObject(input12) && objectIsPresent(hash.whichObject(input12)) && hash.whichObject(input12) == GameObjects.KNIFE)
			{
				output = new String("The dwarves' knives vanish as they strike the walls of the cave.");
				increaseTurns = false;
			}
			else if(hash.isObject(input22) && objectIsPresent(hash.whichObject(input22)) && hash.whichObject(input22) == GameObjects.KNIFE)
			{
				output = new String("The dwarves' knives vanish as they strike the walls of the cave.");
				increaseTurns = false;
			}
			else if(hash.isAction(input12))
			{
				output = attemptAction(hash.whichAction(input12), determineNature(input22), input2);
			}
			else if(hash.isAction(input22))
			{
				output = attemptAction(hash.whichAction(input22), determineNature(input12), input1);
			}
			else
			{
				output = nonsense();
			}
		}
		if(light)
		{
			lamp--;
		}
		if(lamp < 0)
		{
			output = output + "\nYour lamp has run out of power.";
			if(!canISee(currentLocation))
			{
				output = output + "\nIt is now pitch dark. If you proceed you will likely fall into a pit.";
			}
			light = false;
		}
		if(input1.equalsIgnoreCase("west")||input2.equalsIgnoreCase("west"))
		{
			west++;
		}
		if(increaseTurns)
		{
			turns++;
		}
		getCurrentScore();
		output = output + checkForHints();
		System.out.println("previous " + previousLocation);
		System.out.println("current " + currentLocation);
		System.out.println("lamp " + lamp);
		System.out.println("items " + itemsInHand);
		return output;
	}
	
	private String checkForHints()
	{
		String output = new String("");
		if(west == 10)
		{
			output = "If you prefer, simply type W rather than WEST.";
		}
		//15
		//cave - outside - 2 points 
		//bird - bird
		//twist - alike 1-14 + dead1 + dead3-7 + dead 9-11
		//snake - hall of mountain king
		//wit - wit's end
		//dark - alcove + plover - 5 points - 
		//
		return output;
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
				output = new String(output + things.getItemDescription(here, thing, 
						light, grateUnlocked, plant, bottle, birdInCage, oilDoor, bearAxe, dragon,
						bear, usedBatteries, broken, chain, haveGold, crystalBridge, collapse));
				
				if(things.isTreasure(thing))
				{
					if(!hash.haveIFound(GameObjects.RUG) && thing == GameObjects.RUG_)
					{
						hash.wasFound(GameObjects.RUG);
					}
					else if(!hash.haveIFound(thing))
					{
						hash.wasFound(thing);
					}
				}
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
	
	private Object determineNature(String input)
	{
		Object result = GameObjects.NOTHING;
		if(input.length() > 5)
		{
			input = input.substring(0, 5);
		}
		if(hash.isMovement(input))
		{
			result = hash.whichMovement(input);
		}
		else if(hash.isObject(input))
		{
			result = hash.whichObject(input);
		}
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
				case ABSTAIN:
					/*if(other == GameObjects.NOTHING)
					{
						output = "Okay.";
					}*/
					output = "Okay.";
					break;
					
				case LOOK:
					
					break;
					
				case TAKE:
					output = new String("You can't be serious!");
					
					if(object == GameObjects.WATER)
					{
						if(hash.objectIsHere(GameObjects.BOTTLE, Location.INHAND))
						{
							if(bottle == 0)
							{
								if(currentLocation == Location.ROAD || currentLocation == Location.BUILDING || currentLocation == Location.VALLEY || currentLocation == Location.SLIT || currentLocation == Location.WET || currentLocation == Location.FALLS || currentLocation == Location.RESER)
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
						else
						{
							output = new String("You have nothing in which to carry it.");
							increaseTurns = false;
						}
					}
<<<<<<< HEAD
					else if(object == GameObjects.NOTHING)
					{
						output = "What would you like to take?";
						quest = 3;
						increaseTurns = false;
					}
=======
>>>>>>> parent of 0c57575... Work on continuing actions, questions, and score
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
						else if(object == GameObjects.BEAR && chain == 0)
						{
							output = new String("The bear is still chained to the wall.");
							increaseTurns = false;
						}
						else if(object == GameObjects.BEAR && chain != 0)
						{
							//TODO bear is following
						}
						else if(object == GameObjects.CHAIN && chain == 2)
						{
							output = new String("The chain is still locked.");
							increaseTurns = false;
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
								output = new String("The bird was unafraid when you entered, but as you approach it becomes disturbed and you cannot catch it.");
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
						else if(object == GameObjects.RUG)
						{
							if(objectIsHere(GameObjects.RUG) || objectIsHere(GameObjects.RUG_))
							{
								takeObject(GameObjects.RUG);
								hash.voidObject(GameObjects.RUG_);
								output = okay;
							}
						}
						else if(object == GameObjects.ROD && !objectIsHere(GameObjects.ROD) && objectIsHere(GameObjects.ROD2))
						{
							takeObject(GameObjects.ROD2);
							output = okay;
						}
						else if(object == GameObjects.AXE && bearAxe && bear == 0)
						{
							//TODO you can't retrieve the axe after throwing it at the bear
						}
						else if(object == GameObjects.VASE && broken == true){	}
						else if(things.canTake(object) && objectIsHere(object))
						{
							takeObject(object);
							output = okay;
						}
					}
					else
					{
						output = new String("I don't see any " + alt + ".");
						increaseTurns = false;
					}
				break;
					
				case DROP:

					output = new String("");
					if(isInHand(GameObjects.ROD2) && object == GameObjects.ROD && !isInHand(GameObjects.ROD))
					{
						//TODO DYNAMITE
					}
<<<<<<< HEAD
					if(object == GameObjects.NOTHING)
					{
						output = "What would you like to drop?";
						quest = 4;
						increaseTurns = false;
					}
					else if(isInHand(object))
=======
					if(isInHand(object))
>>>>>>> parent of 0c57575... Work on continuing actions, questions, and score
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
								output = new String("The little bird attacks the green snake, and in an astounding flurry drives the snake away.");
								//TODO wake dwarves if closed
							}
							else if(objectIsHere(GameObjects.DRAGON) || objectIsHere(GameObjects.DRAGON_))
							{
								output = new String("The little bird attacks the green dragon, and in an astounding flurry gets burnt to a cinder. The ashes blow away.");
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
							itemsInHand++;
							dropObject(GameObjects.BATTERIES);
							//TODO change batteries?
						}
						else if(object == GameObjects.BEAR)
						{
							//TODO if the troll is here make it leave
							/**
							 * "The bear lumbers toward the troll, who lets out a startled shriek and scurries away. The bear soon gives up pursuit and wanders back."
							 */
						}
						else if(object == GameObjects.VASE && !(objectIsHere(GameObjects.PILLOW) || currentLocation == Location.SOFT))
						{
							//TODO vase breaking
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
						output = new String("You aren't carrying it!");
						increaseTurns = false;
					}
					
					break;
					
				case OPEN:
					if(object == GameObjects.GRATE || object == GameObjects.GRATE_)
					{
						if(!objectIsPresent(GameObjects.KEYS))
						{
							output = new String("You don't have any keys!");
							increaseTurns = false;
						}
						else
						{
							output = new String("The grate is now unlocked.");
							grateUnlocked = true;
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
								output = new String("A glistening pearl falls out of the clam and rolls away. Goodness, this must really be an oyster! (I never was very good at identifying bivalves.)\nWhatever it is, it has now snapped shut again.");
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
								output = new String("The oyster creaks open, revealing nothing but oyster inside. It promptly snaps shut again.");
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
									output = new String("There is no way to get past the bear to unlock the chain, which is probably just as well.");
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
<<<<<<< HEAD
					else if(object == GameObjects.NOTHING)
					{
						output = "What would you like to open?";
						quest = 5;
						increaseTurns = false;
					}
=======
>>>>>>> parent of 0c57575... Work on continuing actions, questions, and score
					else
					{
						output = new String("I don't know how to lock or unlock such a thing.");
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
								output = new String("Your lamp is now on.\n\n" + getDescription(currentLocation, brief));
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
							output = output + "\n\nIt is now pitch dark. If you proceed you will likely fall into a pit.";
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
						output = new String("You aren't carrying it!");
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
						output = new String("Nothing happens.");
						increaseTurns = false;
					 }
					 else
					 {
						 //TODO bridge destroyed when closing, I think
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
					break;
					
				case CALM:
					break;
					
				case GO:
					if(alt.equals(""))
					{	output = new String("Where?");	increaseTurns = false;	}
					else
					{	output = attemptMovement(alt);	}
					break;
					
				case RELAX:
					break;
					
				case POUR:
					if(object == GameObjects.NOTHING || object == GameObjects.BOTTLE)
					{
						if(bottle == 1)
						{
							object = GameObjects.WATER;
						}
						else if(bottle == 2)
						{
							object = GameObjects.OIL;
						}
						else
						{
							object = GameObjects.NOTHING;
						}					
					}
					if(object == GameObjects.NOTHING)
					{
						output = new String("You can't pour that.");
						increaseTurns = false;
					}
					else if(object == GameObjects.WATER && bottle == 1)
					{
						if(currentLocation == Location.WESTPIT)
						{
							plant++;
							if(plant == 1)
							{
								output = new String("The plant spurts into furious growth for a few seconds.\n\n\tThere is a 12-foot-tall beanstalk stretching up out of the pit, bellowing \"Water!! Water!!\"");
							}
							else if(plant == 2)
							{
								output = new String("The plant grows explosively, almost filling the bottom of the pit.\n\n\tThere is a gigantic beanstalk stretching all the way up to the hole.");
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
						{
							output = new String("Your bottle is empty and the ground is wet.");
						}
						bottle = 0;
					}
					else if(object == GameObjects.OIL && bottle == 2)
					{
						if(currentLocation == Location.WESTPIT)
						{
							if(plant == 1)
							{
								output = new String("The plant indignantly shakes the oil off its leaves and asks: \"Water?\".");
							}
							else
							{
								output = new String("Your bottle is empty and the ground is wet.");
							}
						}
						else if(objectIsPresent(GameObjects.DOOR))
						{
							oilDoor = true;
							output = new String("The oil has freed up the hinges so that the door will now move, although it requires some effort.");
						}
						else
						{
							output = new String("Your bottle is empty and the ground is wet.");
						}
						bottle = 0;
					}
						
					break;
					
				case EAT:
					break;
					
				case DRINK:
					break;
					
				case RUB:
					if(object == GameObjects.LAMP)
					{	output = new String("Rubbing the electric lamp is not particularly rewarding. Anyway, nothing exciting happens.");	}
					else
					{	output = new String("Peculiar. Nothing unexpected happens.");	}
					break;
					
				case TOSS:
					break;
					
				case WAKE:
					break;
					
				case FEED:
					break;
					
				case FILL:
					break;
					
				case BREAK:
					break;
					
				case BLAST:
					break;
					
				case KILL:
					if(object == GameObjects.BIRD)
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
					else if(object == GameObjects.BIRD && closed)
					{
						output = new String("Oh, leave the poor unhappy bird alone.");
						increaseTurns = false;
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
						//TODO dwarf end
						/**
							"The resulting ruckus has awakened the Dwarves. There are now several threatening little Dwarves in the room with you! Most of them throw knives at you! All of them get you!"
						 */
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
					else if(object == GameObjects.TROLL && (objectIsHere(GameObjects.TROLL)|| objectIsHere(GameObjects.TROLL2)))
					{
						output = new String("Trolls are close relatives with the rocks and have skin as tough as that of a rhinoceros. The troll fends off your blows effortlessly.");
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
						}
						else
						{
							output = new String("There is nothing here to attack.");
						}
						increaseTurns = false;
					}
					break;
					
				case SAY:
					if(alt.equals(""))
					{
						output = new String("What do you want to say?");
						increaseTurns = false;
					}
					else if(other == MessageWords.CUSS)
					{
						output = getText(MessageWords.CUSS);
					}
					else
					{
						output = new String("Okay, \"" + alt + "\".");
					}
					break;
					
				case READ:
					if(!canISee(currentLocation))
					{
						output = "You can't read in the dark!";
						increaseTurns = false;
					}
					else if(objectIsPresent(GameObjects.MAG))
					{
						//TODO reading all these things
					}
					else if(objectIsPresent(GameObjects.TABLET))
					{
						
					}
					else if(objectIsPresent(GameObjects.MESSAGE))
					{
						
					}
					else if(objectIsPresent(GameObjects.OYSTER))
					{
						
					}
					break;
					
				case FEEFIE:
					break;
					
				case BRIEF:
//					if(brief == 0)
//					{
						brief = 1;
						output = "Okay, from now on I'll only describe a place in full the first time you come to it. To get the full description, say \"LOOK\".";
/*					}
					else
					{
						brief = 0;
						output = "Okay, I'll return to giving descriptions in the original fashion.";
					}
*/					break;
					
				case VERBOSE:
//					if(brief == 0)
//					{
						brief = 2;
						output = "Okay, from now on I'll describe a place in full every time you come to it.";
/*					}
					else
					{
						brief = 0;
						output = "Okay, I'll return to giving descriptions in the original fashion.";
					}
*/					break;
					
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
						output = "I can only tell you what you see as you move about and manipulate things. I can not tell you where remote things are.";
						increaseTurns = false;
					}
					break;
					
				case INVENTORY:
					if(itemsInHand > 0)
					{
						output = "\t   -----" + listItemsHere(Location.INHAND) + "\n\t   -----";
						if(bear == 2)
						{
							output = output + "\n\nYou are being followed by a very large, tame bear.";
						}
					}
					else
					{
						output = new String("\t   -----\n\t\tYou're not carrying anything.\n\t   -----");
					}
					break;
					
				case SCORE:
					break;
					
				case QUIT:
<<<<<<< HEAD
					output = "Do you really wish to quit now?";
					seriousQuestion = true;
					quest = 2;
					increaseTurns = false;
=======
>>>>>>> parent of 0c57575... Work on continuing actions, questions, and score
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
				{
					output = attemptMovement(alt);
				}
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
		String output = "";
		if(!wayIsBlocked)
		{
			haveGold = (hash.objectIsHere(GameObjects.GOLD, Location.INHAND));
			boolean haveEmerald = (hash.objectIsHere(GameObjects.EMERALD, Location.INHAND));
			boolean haveClam = (hash.objectIsHere(GameObjects.CLAM, Location.INHAND));
			boolean haveOyster = (hash.objectIsHere(GameObjects.OYSTER, Location.INHAND));
			boolean trollIsHere = (hash.getObjectLocation(GameObjects.TROLL) == currentLocation || hash.getObjectLocation(GameObjects.TROLL_) == currentLocation);

			try
			{
				Movement destination = hash.whichMovement(input);
				Location locationResult = currentLocation.moveTo(destination, currentLocation, grateUnlocked,
						haveGold, crystalBridge, snake, haveEmerald, haveClam, haveOyster, plant, oilDoor,
						dragon, troll, trollIsHere, itemsInHand);
				if(closed && (locationResult.compareTo(Location.THEVOID) < 10))
				{
					output = "A mysterious recorded voice groans into life and announces: \n\t\"This exit is closed. Please leave via main office.\"";
					increaseTurns = false;
				}
				else if(locationResult.equals(Location.THEVOID))
				{
					if(destination.equals(Movement.XYZZY)||destination.equals(Movement.PLUGH)||destination.equals(Movement.PLUGH))
					{
						output = "Nothing happens.\n";
						output = output + getDescription(currentLocation, brief);
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
					//
					//TODO death
					//
				}
				else if(locationResult.equals(Location.LOSE))
				{
					output = "You didn't make it.";
					increaseTurns = false;
					//
					// TODO death 
					//
				}
				else if(locationResult.equals(Location.CANT))
				{
					output = "The dome is unclimbable.";
					increaseTurns = false;
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
					output = "You have crawled through a very low wide passage parallel to and north of the Hall of Mists.\n" + getDescription(currentLocation, brief);
				}
				else if(locationResult.equals(Location.DUCK))
				{
					setLocation(Location.WESTFISSURE);
					output = "You have crawled through a very low wide passage parallel to and north of the Hall of Mists.\n" + getDescription(currentLocation, brief);
				}
				else if(locationResult.equals(Location.SEWER))
				{
					output = "The stream flows out through a pair of 1-foot-diameter sewer pipes.\n\nIt would be advisable to use the exit.";
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
					output = "You have climbed up the plant and out of the pit.\n" + getDescription(currentLocation, brief);
				}
				else if(locationResult.equals(Location.REMARK))
				{
					if(currentLocation.equals(Location.SLIT)||currentLocation.equals(Location.WET))
					{
						output = "You can't fit through a two-inch slit!";
						increaseTurns = false;
					}
					else if(currentLocation.equals(Location.INSIDE)||currentLocation.equals(Location.OUTSIDE)||currentLocation.equals(Location.DEBRIS)||currentLocation.equals(Location.AWKWARD)||
							currentLocation.equals(Location.BIRD)||currentLocation.equals(Location.SMALLPIT))
					{
						output = "You can't go through a locked steel grate!";
						increaseTurns = false;
					}
					else if(currentLocation.equals(Location.SWSIDE) && destination != Movement.JUMP)
					{
						increaseTurns = false;
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
						increaseTurns = false;
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
						increaseTurns = false;
					}
					else if(currentLocation.equals(Location.SHELL))
					{
						increaseTurns = false;
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
					else
					{
						output = "You can not do that.";
						increaseTurns = false;
					}
				}
				else
				{
					setLocation(locationResult);
					if(!canISee(currentLocation))
					{
						//TODO death
						boolean pitifulDeath = fallInPit();
						if(pitifulDeath)
						{

						}
						else
						{
							output = new String("It is now pitch dark. If you proceed you will likely fall into a pit.");
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
								output = new String(output + "\n\nA hollow voice says \"PLUGH\"");
							}
						}
						if(bear == 2)
						{
							output = output + "You are being followed by a very large, tame bear.";
						}
					}
				}
			}
//			catch(ClassCastException e)
//			{
//				output = "You can not do that.";
//			}
			catch(NullPointerException e)
			{
				output = "You can not do that.";
				increaseTurns = false;
			}
		}
		else
		{
			output = "";
			increaseTurns = false;
			//TODO blocked by dwarf or something
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
		else if(light && objectIsPresent(GameObjects.LAMP))
		{
			canSee = true;
		}
		System.out.print("can see "); System.out.print(currentLocation.dontNeedLamp(here)); System.out.println("\n");
		System.out.println(currentLocation);
		return canSee;
	}
	
	private int getCurrentScore()
	{
		//TODO finish normal and in building scores
		int currentScore = (2 + (2 * (15 - tally)) + (deaths * 10));
		ArrayList<GameObjects> buildingItems = hash.objectsHere(Location.BUILDING);
		
		for(GameObjects item : buildingItems)
		{
			if(things.isTreasure(item))
			{
				if(things.isLesserTreasure(item))
				{
					currentScore = currentScore + 12;
				}
				else if(item == GameObjects.VASE && broken){	}
				else if(item == GameObjects.CHEST)
				{
					currentScore = currentScore + 14;
				}
				else
				{
					currentScore = currentScore + 16;
				}
			}
		}
		
		if(instructions){	currentScore = currentScore - 5;	}
		if(enteredCave){	currentScore = currentScore + 25;	} //TODO check for this
		if(closed){	currentScore = currentScore + 25;	}
		if(!quit){	currentScore = currentScore + 4;	}

		//TODO point for witt's end
		score = currentScore;
		return currentScore;
	}
	
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
	
	private boolean saveGame()
	{
		boolean result = false;
		//TODO save 
		return result;
	}

	private boolean fallInPit()
	{
		boolean pitifulDeath = false;

		return pitifulDeath;
	}
	
	private void takeObject(GameObjects thing)
	{
		hash.takeObject(thing);
		itemsInHand++;
	}

	private boolean isInHand(GameObjects thing)
	{
		return hash.objectIsHere(thing, Location.INHAND);
	}
	
	private boolean objectIsHere(GameObjects thing)
	{
		return hash.objectIsHere(thing, currentLocation);
	}
	
	private boolean objectIsPresent(GameObjects thing)
	{
		boolean result = false;
		if(objectIsHere(thing) || isInHand(thing))
		{
			result = true;
		}
		return result;
	}
	
	private void dropObject(GameObjects thing)
	{
		hash.dropObject(thing, currentLocation);
		itemsInHand--;
	}
	
	private void voidObject(GameObjects thing)
	{
		hash.voidObject(thing);
	}
	
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
						+ "To move, try words like forest, building, downstream, enter, east, west, north, south, up, or down. "
						+ "I know about a few special objects, like a black rod hidden in the cave. "
						+ "These objects can be manipulated using some of the action words that I know. "
						+ "Usually you will need to give both the object and the action words (in either order), but sometimes I can infer the object from the verb alone. "
						+ "Some objects also imply verbs; in particular, \"inventory\" implies \"take inventory\", which causes me to give you a list of what you are carrying. "
						+ "The objects have side effects; for instance, the rod scares the bird. "
						+ "Usually people having trouble moving just need to try a few more words. "
						+ "Usually people trying unsuccessfully to manupulate an object are attempting beyond their (or my!) capabilities and should try a completely different tack. "
						+ "To speed the game you can sometimes move long distances with a single word. "
						+ "For example, \"building\" usually gets you to the building from anywhere above ground except when lost in the forest. "
						+ "Also, note that cave passages turn a lot, and that leaving a room to the north does not guarantee entering the next from the south. \nGood luck!");
				break;
				
			case TREE:
				result = new String("The trees of the forest are large hardwood oak and maple, with an occasional grove of pine or spruce. "
						+ "There is quite a bit of under-growth, largely birch and ash saplings plus nondescript bushes of various sorts. "
						+ "This time of year visibility is quite restricted by all the leaves, but travel is quite easy if you detour around the spruce and berry bushes.");
				break;
				
			case DIG:
				result = new String("Digging without a shovel is quite impractical. Even with a shovel progress is unlikely.");
				break;
				
			case LOST:
				result = new String("I'm as confused as you are.");
				break;
				
			case MIST:
				result = new String("Mist is a white vapor, usually water, seen from time to time in caverns. It can be found anywhere but is frequently a sign of a deep pit leading down to water.");
				break;
				
			case CUSS:
				result = new String("Watch it!");
				break;
				
			case INFO:
				result = new String("If you want to end your adventure early, say \"quit\". "
						+ "To get full credit for a treasure, you must have left it safely in the building, though you get partial credit just for locating it. "
						+ "You lose points for getting killed, or for quitting, though the former costs you more. "
						+ "There are also points based on how much (if any) of the cave you've managed to explore; in particular, there is a large bonus just for getting in (to distinguish the beginners from the rest of the pack), and there are other ways to determine whether you've been through some of the more harrowing sections. "
						+ "If you think you've found all the treasures, just keep exploring for a while. If nothing interesting happens, you haven't found them all yet. If something interesting DOES happen, it means you're getting a bonus and have an opportunity to garner many more points in the master's section. "
						+ "I may occasionally offer hints if you seem to be having trouble. If I do, I will warn you in advance how much it will affect your score to accept the hints. "
						+ "Finally, to save paper, you may specify \"brief\", which tells me never to repeat the full description of a place unless you explicitly ask me to. "
						+ "You may also specify \"verbose\", which tells me only to repeat the long description of a place.");
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
	
	public void relocate()
	{
		hash.dropObject(GameObjects.EMERALD, currentLocation);
	}

	public int getTurns()
	{
		return turns;
	}
	
	public int getScore()
	{
		return score;
	}
}



