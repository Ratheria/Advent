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
	private ActionWords actions;
	private GameObjects things;
	private Location currentLocation;
	private Location previousLocation;
	private boolean dead;
	private boolean beginning;
	private boolean closed;
	private boolean grateUnlocked;
	private boolean crystalBridge;
	private boolean light;
	private boolean snake;
	private boolean oilDoor;
	private boolean dragon;
	private boolean dragonQuest;
	private boolean troll;
	private boolean birdInCage;
	private boolean bearAxe;
	private boolean usedBatteries;
	private boolean broken;
	private boolean haveGold;
	private boolean collapse;
	private boolean wayIsBlocked;
	private int brief;
	private int score;
	private int turns;
	private int lamp;
	private int itemsInHand;
	private int deaths;
	private int plant;
	private int bottle;
	private int bear;
	//default, fed + idle, fed + following, dead
	private int chain;
	//locked to bear, unlocked, locked
	//	private String beforeTurnBeforeLast;
	//	private String turnBeforeLast;
	//	private String turnLast;
	
	public AdventControl()
	{
		//		beforeTurnBeforeLast = "";
		//		turnBeforeLast = "";
		//		turnLast = "";
		currentLocation = Location.ROAD;
		previousLocation = null;
		actions = ActionWords.NOTHING;
		things = GameObjects.NOTHING;
		dead = false;
		brief = 0;
		beginning = true;
		closed = false;
		grateUnlocked = false;
		crystalBridge = false;
		light = false;
		snake = true;
		oilDoor = false;
		dragon = true;
		dragonQuest = false;
		troll = true;
		birdInCage = false;
		bearAxe = false;
		broken = false;
		haveGold = false;
		collapse = false;
		score = 0;
		turns = 0;
		lamp = 100;
		//TODO lamp more time?
		itemsInHand = 0;
		deaths = 0;
		plant = 0;
		bottle = 1;
		bear = 0;
		chain = 0;
		currentLocation.setUp();
		things.setUp();
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
		else if(dragonQuest && (input.toLowerCase().contains("yes") || input.equalsIgnoreCase("y")))
		{
			//TODO you killed the dragon message
			dragonQuest = false;
			dragon = false;
			turns++;
		}
		else
		{
			if(dragonQuest)
			{
				dragonQuest = false;
			}
			turns++;
			if(input.length() > 5)
			{
				input = input.substring(0, 5);
			}
			if(hash.isMovement(input))
			{				
				output = attemptMovement(input);
			}
			else if(hash.isAction(input))
			{
				output = attemptAction(hash.whichAction(input), GameObjects.NOTHING, "");
			}
			else if(hash.isMessage(input))
			{
				output = messages.getText(hash.whichMessage(input));
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

		if(beginning)
		{
			output = "Just yes or no, please.";
		}
		else
		{
			turns++;
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
				output = attemptMovement(input12);
			}
			else if(hash.isMovement(input22))
			{
				output = attemptMovement(input22);
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
		//15
		//cave - outside
		//bird - bird
		//twist - alike 1-14 + dead1 + dead3-7 + dead 9-11
		//snake - hall of mountain king
		//wit - wit's end
		//dark - alcove + plover
		//
		return output;
	}
	
	private String listItemsHere()
	{
		String output = "";
		ArrayList<GameObjects> objects = hash.objectsHere(currentLocation);
		if(objects != null)
		{
			output = output + "\n";
			for(GameObjects thing : objects)
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
		}
		
		try
		{
			GameObjects object = (GameObjects) other;
			switch(verb)
			{
				case ABSTAIN:
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
								}
							}
							else
							{
								output = new String("Your bottle is already full.");
							}
						}
						else
						{
							output = new String("You have nothing in which to carry it.");
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
								}
							}
							else
							{
								output = new String("Your bottle is already full.");
							}
						}
						else
						{
							output = new String("You have nothing in which to carry it.");
						}
					}
					else if(objectIsPresent(object))
					{
						if(hash.objectIsHere(object, Location.INHAND))
						{
							output = new String("You are already carrying it!");
						}
						else if(object == GameObjects.PLANT && objectIsHere(object))
						{
							output = new String("The plant has exceptionally deep roots and cannot be pulled free.");
						}
						else if(object == GameObjects.BEAR && chain == 0)
						{
							output = new String("The bear is still chained to the wall.");
						}
						else if(object == GameObjects.BEAR && chain != 0)
						{
							//TODO bear is following
						}
						else if(object == GameObjects.CHAIN && chain == 2)
						{
							output = new String("The chain is still locked.");
						}
						else if(itemsInHand >= 7)
						{
							output = new String("You can't carry anything more. You'll have to drop something first.");
						}
						else if(object == GameObjects.BIRD && objectIsHere(GameObjects.BIRD))
						{
							if(hash.objectIsHere(GameObjects.ROD, Location.INHAND) && !birdInCage)
							{
								output = new String("The bird was unafraid when you entered, but as you approach it becomes disturbed and you cannot catch it.");
							}
							else if(!hash.objectIsHere(GameObjects.CAGE, Location.INHAND))
							{
								output = new String("You can catch the bird, but you cannot carry it.");
							}
							else if(isInHand(GameObjects.CAGE))
							{
								birdInCage = true;
								takeObject(GameObjects.BIRD);
								itemsInHand--;
								output = new String("Okay.");
							}
							else
							{
								if(birdInCage)
								{
									takeObject(GameObjects.BIRD);
									takeObject(GameObjects.CAGE);
									itemsInHand--;
									output = new String("Okay.");
								}
								
							}
						}
						else if(object == GameObjects.RUG)
						{
							if(objectIsHere(GameObjects.RUG) || objectIsHere(GameObjects.RUG_))
							{
								takeObject(GameObjects.RUG);
								hash.voidObject(GameObjects.RUG_);
								output = new String("Okay.");
							}
						}
						else if(object == GameObjects.ROD && !objectIsHere(GameObjects.ROD) && objectIsHere(GameObjects.ROD2))
						{
							takeObject(GameObjects.ROD2);
							output = new String("Okay.");
						}
						else if(object == GameObjects.AXE && bearAxe && bear == 0)
						{
							//TODO you can't retrieve the axe after throwing it at the bear
						}
						else if(object == GameObjects.VASE && broken == true){	}
						else if(things.canTake(object) && objectIsHere(object))
						{
							takeObject(object);
							output = new String("Okay.");
						}
					}
					else
					{
						output = new String("I don't see any " + alt + ".");
					}
					
					if(currentLocation == Location.BUILDING && things.isTreasure(object) && output.equals("Okay."))
					{
						//TODO treasure score
					}
				break;
					
				case DROP:

					output = new String("");
					if(isInHand(GameObjects.ROD2) && object == GameObjects.ROD && !isInHand(GameObjects.ROD))
					{
						//TODO DYNAMITE
					}
					if(isInHand(object))
					{
						if(object == GameObjects.CAGE && birdInCage)
						{
							dropObject(GameObjects.CAGE);
							itemsInHand++;
							dropObject(GameObjects.BIRD);
							output = new String("Okay.");
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
								output = new String("Okay.");
								birdInCage = false;
								itemsInHand++;
								dropObject(GameObjects.BIRD);
							}
						}
						else if(object == GameObjects.COINS && objectIsHere(GameObjects.PONY))
						{
							voidObject(GameObjects.COINS);
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
						}
						else
						{
							dropObject(object);
							output = new String("Okay.");
						}
						
					}
					else
					{
						output = new String("You aren't carrying it!");
					}
					
					break;
					
				case OPEN:
					
					if(object == GameObjects.GRATE || object == GameObjects.GRATE_)
					{
						if(!objectIsPresent(GameObjects.KEYS))
						{
							output = new String("You don't have any keys!");
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
							}
							else if(!isInHand(GameObjects.TRIDENT))
							{
								output = new String("You don't have anything strong enough to open the clam.");
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
							}
							else if(!isInHand(GameObjects.TRIDENT))
							{
								output = new String("You don't have anything strong enough to open the oyster.");
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
								output = new String("Okay.");
							}
							else
							{
								output = new String("The door is extremely rusty and refuses to open.");
							}
						}
						else if(object == GameObjects.CAGE)
						{
							output = new String("It has no lock.");
						}
						else if(object == GameObjects.KEYS)
						{
							output = new String("You can't unlock the keys.");
						}
						else if(object == GameObjects.CHAIN)
						{
							if(!objectIsPresent(GameObjects.KEYS))
							{
								output = new String("You have no keys!");
							}
							else if(chain == 0)
							{
								if(bear != 1)
								{
									output = new String("There is no way to get past the bear to unlock the chain, which is probably just as well.");
								}
								else
								{
									chain = 1;
									output = new String("You unlock the chain and set the tame bear free");
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
							}
						}
					}
					else
					{
						output = new String("I don't know how to lock or unlock such a thing.");
					}
					break;
					
					
				case CLOSE:
					
					if(object == GameObjects.GRATE || object == GameObjects.GRATE_)
					{
						if(!objectIsPresent(GameObjects.KEYS))
						{
							output = new String("You don't have any keys!");
						}
						else if(!grateUnlocked)
						{
							output = new String("It was already locked.");
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
						}
						else if(object == GameObjects.CAGE)
						{
							output = new String("It has no lock.");
						}
						else if(object == GameObjects.KEYS)
						{
							output = new String("You can't unlock the keys.");
						}
						else if(object == GameObjects.CHAIN)
						{
							if(!objectIsPresent(GameObjects.KEYS))
							{
								output = new String("You have no keys!");
							}
							else if(chain != 1)
							{
								output = new String("It was already locked.");
							}
							else if(chain == 1)
							{
								if(!(currentLocation == Location.BARR))
								{
									output = new String("There is nothing here to which the chain can be locked.");
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
						}
					}
					else if(object == GameObjects.NOTHING || objectIsPresent(object))
					{
						output = new String("You have no source of light.");
					}
					else
					{
						output = new String("I don't see any " + alt + ".");
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
					}
					else if (!isInHand(object) && (object != GameObjects.ROD || !isInHand(GameObjects.ROD2)))
					 {
						output = new String("You aren't carrying it!");
					 }
					 else if(object != GameObjects.ROD || closed)
					 {
						 if(!isInHand(object))
						 {
							 output = new String("I don't see any " + alt + ".");
						 }
					 }
					 else if((currentLocation != Location.EASTFISSURE && currentLocation != Location.WESTFISSURE))
					 {
						 output = new String("Nothing happens.");
					 }
					 else
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
					break;
					
				case CALM:
					break;
					
				case GO:
					if(alt.equals(""))
					{
						output = new String("Where?");
					}
					else
					{
						output = attemptMovement(alt);
					}
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
					if(other == GameObjects.LAMP)
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
					
					if(object == GameObjects.BIRD && objectIsPresent(GameObjects.BIRD))
					{
						voidObject(GameObjects.BIRD);
						output = new String("The little bird is now dead. Its body disappears.");
					}
					else if(object == GameObjects.BIRD && closed)
					{
						output = new String("Oh, leave the poor unhappy bird alone.");
					}
					else if(object == GameObjects.CLAM || object == GameObjects.OYSTER)
					{
						output = new String("The shell is very strong and impervious to attack.");
					}
					else if(object == GameObjects.SNAKE)
					{
						output = new String("Attacking the snake both doesn't work and is very dangerous.");
					}
					else if(object == GameObjects.DWARF && objectIsHere(GameObjects.DWARF))
					{
						output = new String("With what? Your bare hands?");
					}
					else if(object == GameObjects.DWARF && closed)
					{
						//TODO dwarf end
						/**
							"The resulting ruckus has awakened the Dwarves. There are now several threatening little Dwarves in the room with you! Most of them throw knives at you! All of them get you!"
						 */
					}
					else if(object == GameObjects.TROLL && (objectIsHere(GameObjects.TROLL)|| objectIsHere(GameObjects.TROLL2)))
					{
						output = new String("Trolls are close relatives with the rocks and have skin as tough as that of a rhinoceros. The troll fends off your blows effortlessly.");
					}
					else if(object == GameObjects.BEAR && objectIsHere(GameObjects.BEAR))
					{
						if(bear == 0)
						{
							output = new String("With what? Your bare hands? Against HIS bear hands??");
						}
						else if(bear != 3)
						{
							output = new String("The bear is confused; he only wants to be your friend.");
						}
						else
						{
							output = new String("For crying out loud, the poor thing is already dead!");
						}
					}
					else if(object == GameObjects.DRAGON)
					{
						if(!dragon)
						{
							output = new String("For crying out loud, the poor thing is already dead!");
						}
						else
						{
							output = new String("With what? Your bare hands?");
							dragonQuest = true;
						}
					}
					else
					{
						output = new String("There is nothing here to attack.");
					}
					break;
					
				case SAY:
					if(alt.equals(""))
					{
						output = new String("What do you want to say?");
					}
					else if(other == MessageWords.CUSS)
					{
						output = messages.getText(MessageWords.CUSS);
					}
					else
					{
						output = new String("Okay, \"" + alt + "\".");
					}
					break;
					
				case READ:
					break;
					
				case FEEFIE:
					break;
					
				case BRIEF:
					break;
					
				case FIND:
					break;
					
				case INVENTORY:
					break;
					
				case SCORE:
					break;
					
				case QUIT:
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
				}
				else
				{
					output = attemptMovement(alt);
				}
			}
			else
			{
				output = "You can not do that.";
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
			boolean haveLamp = hash.getObjectLocation(GameObjects.LAMP) == Location.INHAND;
			boolean haveEmerald = (hash.objectIsHere(GameObjects.EMERALD, Location.INHAND));
			boolean haveClam = (hash.objectIsHere(GameObjects.CLAM, Location.INHAND));
			boolean haveOyster = (hash.objectIsHere(GameObjects.OYSTER, Location.INHAND));
			boolean trollIsHere = (hash.getObjectLocation(GameObjects.TROLL) == currentLocation || hash.getObjectLocation(GameObjects.TROLL_) == currentLocation);

			try
			{
				Movement destination = hash.whichMovement(input);
				Location locationResult = currentLocation.moveTo(destination, currentLocation, grateUnlocked,
						haveGold, crystalBridge, snake, haveEmerald, haveClam, haveOyster, plant, oilDoor,
						dragon, troll, trollIsHere, haveLamp);
				if(closed && (locationResult.compareTo(Location.THEVOID) < 10))
				{
					output = "A mysterious recorded voice groans into life and announces: \n\t\"This exit is closed. Please leave via main office.\"";
				}
				else if(locationResult.equals(Location.THEVOID))
				{
					if(destination.equals(Movement.XYZZY)||destination.equals(Movement.PLUGH)||destination.equals(Movement.PLUGH))
					{
						output = "Nothing happens.\n";
						output = output + getDescription(currentLocation, brief);
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
					}
					else
					{
						output = "I don't know how to apply that word here.\n";
						output = output + getDescription(currentLocation, brief);
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
					//TODO death
					//
				}
				else if(locationResult.equals(Location.LOSE))
				{
					output = "You didn't make it.";
					//
					// TODO death 
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
					output = "The stream flows out through a pair of 1-foot-diameter sewer pipes.\n\nIt would be advisable to use the exit.";
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
			}
		}
		else
		{
			output = "";
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
		output = output + listItemsHere();
		return output;
	}
	
	private String nonsense()
	{
		String output = null;
		if(turns > 1)
		{
			turns--;
		}
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
		else if(light && isInHand(GameObjects.LAMP))
		{
			canSee = true;
		}
		else if(light && objectIsHere(GameObjects.LAMP))
		{
			canSee = true;
		}
		System.out.print("can see ");System.out.print(currentLocation.dontNeedLamp(here)); System.out.println("\n");
		System.out.println(currentLocation);
		return canSee;
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
				dead = Boolean.parseBoolean(line);
					line = updateLine(reader, row);
				beginning = Boolean.parseBoolean(line);
					line = updateLine(reader, row);
				grateUnlocked = Boolean.parseBoolean(line);
					line = updateLine(reader, row);
				crystalBridge = Boolean.parseBoolean(line);
					line = updateLine(reader, row);
				light = Boolean.parseBoolean(line);
					line = updateLine(reader, row);
				snake = Boolean.parseBoolean(line);
					line = updateLine(reader, row);
				oilDoor = Boolean.parseBoolean(line);
					line = updateLine(reader, row);
				dragon = Boolean.parseBoolean(line);
					line = updateLine(reader, row);
				troll = Boolean.parseBoolean(line);
					line = updateLine(reader, row);
				birdInCage = Boolean.parseBoolean(line);
					line = updateLine(reader, row);
				bearAxe = Boolean.parseBoolean(line);
					line = updateLine(reader, row);
				broken = Boolean.parseBoolean(line);
					line = updateLine(reader, row);
				haveGold = Boolean.parseBoolean(line);
					line = updateLine(reader, row);
				collapse = Boolean.parseBoolean(line);
					line = updateLine(reader, row);
				wayIsBlocked = Boolean.parseBoolean(line);
					line = updateLine(reader, row);
				turns = Integer.parseInt(line);
					line = updateLine(reader, row);
				itemsInHand = Integer.parseInt(line);
					line = updateLine(reader, row);
				deaths = Integer.parseInt(line);
					line = updateLine(reader, row);
				plant = Integer.parseInt(line);
					line = updateLine(reader, row);
				bottle = Integer.parseInt(line);
					line = updateLine(reader, row);
				bear = Integer.parseInt(line);
					line = updateLine(reader, row);
				chain = Integer.parseInt(line);
					line = updateLine(reader, row);
					
					
				//ALL OBJECT LOCATIONS AS WELL
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
		//TODO
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

	public int getTurns()
	{
		return turns;
	}
	
	public int getScore()
	{
		return score;
	}
}



