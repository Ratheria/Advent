/**
 * @author Ariana Fairbanks
 */

package controller;

import model.Locations;
import controller.AdventMain.MessageWords;
import controller.AdventMain.Movement;
import controller.AdventMain.Questions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import controller.AdventMain.ActionWords;
import controller.AdventMain.GameObjects;
import controller.AdventMain.Hints;
import controller.AdventMain.KnownWord;

public class AdventGame implements Serializable
{	
	private static final long serialVersionUID = 216265234662749493L;

	public EnumMap<GameObjects, Boolean> found = new EnumMap<>(GameObjects.class);
	
	public String lastInput;
	public Locations currentLocation;
	public Locations previousLocation;
	public Locations locationAtStartOfAction;
	//private Locations oldestLocation;
	
	public boolean textFieldEditable = true;
	
	private boolean dwarvesAllowed;
	
	public boolean relocate, collapse, justCollapsed;
	
	public boolean playerIsDead;
	private boolean caveIsClosing, caveIsClosed;
	private boolean lampLowBatteryWarning;
	private boolean allowExtraMovesForPanic;
	public boolean over, noMore;
	
	public boolean grateIsUnlocked, crystalBridgeIsThere, lampIsLit, snakeInHotMK,
				   doorHasBeenOiled, dragonIsAlive, birdInCage, bearAxe, vaseIsBroken, goldInInventory;

	private boolean battleUpdate;
	private boolean wayIsBlocked, justBlocked;
	private boolean locationChange;
	private boolean increaseTurns;
	
	private boolean wellInCave;
	private boolean quit;
	
	private byte clock1, clock2;
	public ActionWords actionToAttempt;
	public Questions questionAsked;
	public Hints hintToOffer, offeredHint;
	public int brief;
	public int score;
	private int bonus;
	public int turns;
	public int lamp;
	public byte itemsInHand;
	private byte deaths;
	private byte fatality;
	public int tally;
	private byte lostTreasures;
	public byte stateOfThePlant;
	public byte stateOfTheBottle;
	public byte stateOfSpareBatteries;
	//default, purchased, used to replace old
	
	private byte pirate;
	private byte movesWOEncounter;
	private byte dwarves;
	//nothing, reached hall no dwarf, met dwarf no knives, knife misses, knife hit .095, .190, .285

	private byte deadDwarves;
	private byte dwarvesLeft;
	//certain objects have different behaviours in the end game
	//bottles, lamps, pillows, and rods - invisible until interacted with
	public boolean[] endGameObjectsStates;

	private byte dwarfPresent;
	//none, new, current, old
	
	public byte stateOfTheTroll;
	//there, hidden, dead, can pass;
	public byte stateOfTheBear;
	//default, fed + idle, fed + following, dead, was following idle
	public byte stateOfTheChain;
	//locked to bear, unlocked, locked
	private byte fooMagicWordProgression;
	
	
	
	//private byte fileSlot;
	// nothing, save, load
	//private byte currentFileOperation;
	//public static boolean choosingFileSlot;
	
	public AdventGame()
	{ setUp(); }
	
	public void setUp()
	{
		//No treasure found yet
		for(GameObjects object : GameObjects.values())
		{
			if(GameObjects.isTreasure(object) && object != GameObjects.RUG_) 
			{	found.put(object, false);	}
		}
		
		lastInput = "";
		currentLocation = Locations.ROAD;
		previousLocation = null;
		locationAtStartOfAction = Locations.ROAD;
		//oldestLocation = null;
		playerIsDead = false;
		caveIsClosed = false;
		grateIsUnlocked = false;
		crystalBridgeIsThere = false;
		lampIsLit = false;
		snakeInHotMK = true;
		doorHasBeenOiled = false;
		dragonIsAlive = true;
		birdInCage = false;
		bearAxe = false;
		vaseIsBroken = false;
		goldInInventory = false;
		relocate = false;
		justCollapsed = false;
		collapse = false;
		lampLowBatteryWarning = false;
		over = false;
		dwarvesAllowed = true;
		battleUpdate = false;
		allowExtraMovesForPanic = false;
		wayIsBlocked = false;
		justBlocked = false;
		locationChange = false;
		noMore = false;
		increaseTurns = false;
		wellInCave = false;
		quit = false;
		clock1 = 15;
		clock2 = 15;
		actionToAttempt = ActionWords.NOTHING;
		questionAsked = Questions.INSTRUCTIONS;
		hintToOffer = Hints.NONE;
		offeredHint = Hints.INSTRUCTIONS;
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
		stateOfThePlant = 0;
		stateOfTheBottle = 1;
		stateOfSpareBatteries = 0;
		pirate = 0;
		movesWOEncounter = 1;
		dwarves = 0;
		deadDwarves = 0;
		dwarvesLeft = 5;
		dwarfPresent = 0;
		stateOfTheTroll = 0;
		stateOfTheBear = 0;
		stateOfTheChain = 0;

		fooMagicWordProgression = 0;
		endGameObjectsStates = new boolean[] {false, false, false, false, false, false, false, false, false, false};
//		fileSlot = 0;
//		currentFileOperation = 0;
	}

	/*
	 * Determine what happens given a single word of input.
	 */
	public String determineAction(String input) 
	{
		lastInput = input;
		String output = null;
		increaseTurns = true;
		locationAtStartOfAction = currentLocation;
		int answer = askYesNo(input);
		
		input = AdventMain.truncate.apply(input);
		
		KnownWord word = GameObjects.NOTHING;
		byte wordType = -1;
		if(AdventMain.KnownWords.containsKey(input))
		{
			word = AdventMain.KnownWords.get(input);
			wordType = word.getType();
		}
		
		if(actionToAttempt != ActionWords.NOTHING && wordType == 1)
		{
			output = attemptAction(actionToAttempt, word, input);
			actionToAttempt = ActionWords.NOTHING;
		}
		else if(questionAsked.serious && answer == 0)
		{
			output = "Just yes or no, please.";
			increaseTurns = false;
		}
		else if(questionAsked == Questions.DRAGON && (input.contains("ye") || input.equals("y") || input.contains("ok")))
		{
			output = "Congratulations! You have just vanquished a dragon with your bare hands! (Unbelievable, isn't it?)";
			resetHintsAndQuestions();
			dragonIsAlive = false;
			currentLocation = Locations.SCAN2;
			voidObject(GameObjects.DRAGON_);
			voidObject(GameObjects.RUG_);
			AdventMain.places.placeObject(GameObjects.DRAGON, currentLocation);
			AdventMain.places.placeObject(GameObjects.RUG, currentLocation);
		}
		else if(questionAsked != Questions.NONE && answer > 0) //(questionAsked.serious) || (answer == 1)
		{
			switch(questionAsked)
			{
				case INSTRUCTIONS: 
					output = "";
					if(answer == 1)
					{
						output = giveHint(offeredHint);
						lamp = 1000;	
					}
					output += AdventMain.places.getDescription(Locations.ROAD, brief);
					break;
					
				case RESURRECT:
					increaseTurns = false;
					if(answer == 2) {	over = true;	}
					else { output = resurrection(); }
					break;
				
				case PLAYAGAIN:
					if(answer == 1)
					{	setUp();	}
					else
					{
						noMore = true;
						System.exit(0);
					}
					break;
					
				case QUIT: case SCOREQUIT:
					if(answer == 1)
					{ 
						quit = true;
						over = true;
					}
					else 
					{ output = AdventMain.okay; }
					break;
					
				case READBLASTHINT:
					if(answer == 1)
					{ output = giveHint(offeredHint); Hints.BLAST.proc = 1; }
					else
					{	output = AdventMain.okay;	}
					break;
					
				default: System.out.println("reached default in question switch"); break;
			}			
			resetHintsAndQuestions();
		}
		else if(hintToOffer != Hints.NONE && answer > 0)
		{ output = ( answer == 1 ? offerHint() : AdventMain.okay ); }
		else if(offeredHint != Hints.NONE && answer > 0)
		{ output = ( answer == 1 ? giveHint(offeredHint) : AdventMain.okay ); }
		else if((word == ActionWords.FEEFIE) && (fooMagicWordProgression > 0 || input.equals("fee")))
		{ output = attemptAction((ActionWords) word, GameObjects.NOTHING, input); }
		else
		{
			fooMagicWordProgression = 0;
			resetHintsAndQuestions();
			
			if(wordType == 0)
			{ output = attemptMovement((Movement) word, input); }
			else if(wordType == 2)
			{ output = attemptAction((ActionWords) word, GameObjects.NOTHING, ""); }
			else if(wordType == 1)
			{
				if(objectIsPresent((GameObjects) word))
				{
					if(word == GameObjects.KNIFE)
					{
						output = "The dwarves' knives vanish as they strike the walls of the cave.";
						increaseTurns = false;
					}
					else
					{
						output = "What would you like to do with the " + input + "?";
						increaseTurns = false;
						//TODO continuing objects like actions
						//TODO can do continuting action then object and visa versa
					}
				}
				else
				{
					output = new String("I don't see any " + input + ".");
					increaseTurns = false;
				}
			}
			else if(wordType == 3)
			{
				output = ((MessageWords) word).message;
			}
			else
			{	output = nonsense();	}
		}
		
		if(input.equals("west"))
		{ Hints.WEST.proc++; }
		
		return finishAction(output);
	}

	/*
	 * Determine what happens given two words of input
	 */
	public String determineAction(String input1, String input2) 
	{
		lastInput = input1 + " " + input2;
		String output = null;
		increaseTurns = true;
		locationAtStartOfAction = currentLocation;
		
		if(questionAsked.serious)
		{
			output = "Just yes or no, please.";
			increaseTurns = false;
		}
		else
		{
			fooMagicWordProgression = 0;
			resetHintsAndQuestions();
			
			String input12 = AdventMain.truncate.apply(input1);
			String input22 = AdventMain.truncate.apply(input2);
			
			KnownWord word = GameObjects.NOTHING;
			KnownWord word2 = GameObjects.NOTHING;
			byte wordType = -1;
			byte wordType2 = -1;
			
			if(AdventMain.KnownWords.containsKey(input12))
			{
				word = AdventMain.KnownWords.get(input12);
				wordType = word.getType();
			}
			if(AdventMain.KnownWords.containsKey(input22))
			{
				word2 = AdventMain.KnownWords.get(input22);
				wordType2 = word2.getType();
			}
			
			if(objectIsPresent(GameObjects.KNIFE) && (word == GameObjects.KNIFE || word2 == GameObjects.KNIFE))
			{
				//TODO evaluate this
				output = new String("The dwarves' knives vanish as they strike the walls of the cave.");
				increaseTurns = false;
			}
			else if(wordType == 3)
			{
				output = ((MessageWords) word).message;
			}
			else if(wordType == 0)
			{
				if(word == Movement.ENTER )
				{
					if(input22.equals("water")||input22.equals("strea"))
					{
						increaseTurns = currentLocation.hasWater;
						output = (increaseTurns ? "Your feet are now wet." : "I don't see any water.");
					}
					else
					{	output = attemptAction(ActionWords.GO, word2, input2);	}
				}
				else
				{	output = attemptMovement((Movement) word, input12);	}
			}
			else if(word == GameObjects.WATER && wordType2 == 1)
			{	output = attemptAction(ActionWords.POUR, GameObjects.WATER, "");	}
			else if(word == GameObjects.OIL && wordType2 == 1)
			{	output = attemptAction(ActionWords.POUR, GameObjects.OIL, "");	}
			else if(wordType == 2)
			{	output = attemptAction((ActionWords) word, word2, input2);	}
			else if(wordType2 == 0)
			{
				if(word2 == Movement.ENTER )
				{
					if(input12.equals("water")||input12.equals("strea"))
					{
						increaseTurns = currentLocation.hasWater;
						output = (increaseTurns ? "Your feet are now wet." : "I don't see any water.");
					}
					else
					{	output = attemptAction(ActionWords.GO, word, input1);	}
				}
				else
				{	output = attemptMovement((Movement) word2, input22);	}
			}
			else if(wordType2 == 2)
			{	output = attemptAction((ActionWords) word2, word, input1);	}
			else
			{	output = nonsense();	}
		}
		if(input1.equals("west")||input2.equals("west"))
		{	Hints.WEST.proc++;	}
		return finishAction(output);
	}
	
	private String finishAction(String output)
	{
		if(!wellInCave && currentLocation.getOrdinal(currentLocation) >= currentLocation.minLoc())
		{
			wellInCave = true;
			dwarves = 1;
		}
		if(locationChange && increaseTurns)
		{
			output = lamp(output);
			locationChange = false;
			if(pirate == 1)
			{
				pirate = 2;
				AdventMain.places.placeObject(GameObjects.MESSAGE, Locations.PONY);
				AdventMain.places.placeObject(GameObjects.CHEST, Locations.DEAD2);
				
				ArrayList<GameObjects> currentlyHolding = AdventMain.objectsHere(Locations.INHAND);
				boolean treasure = false;
				if(currentlyHolding != null)
				{
					for(GameObjects object : currentlyHolding)
					{
						if(GameObjects.isTreasure(object))
						{
							treasure = true;
							AdventMain.places.placeObject(object, Locations.DEAD2);
							itemsInHand--;
						}
					}
				}
				output += (treasure ? "\n\nOut from the shadows behind you pounces a bearded pirate!\n\"Har, har,\" he chortles, \"I'll just take all this booty and hide it away with me chest deep in the maze!\"\nHe snatches your treasure and vanishes into the gloom."
							: "\n\nThere are faint rustling noises from the darkness behind you. As you turn toward them, the beam of your lamp falls across a bearded pirate. He is carrying a large chest.\n\"Shiver me timbers!\" he cries, \"I've been spotted! I'd best hie meself off to the maze to hide me chest!\"\nWith that, he vanishes into the gloom.");
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
					dwarvesLeft -= (Math.floor(AdventMain.generate() * 3));
					output += "\n\nA little dwarf just walked around a corner, saw you, threw a little axe at you, "
							+ "cursed, and ran away. (The axe missed.)";
					AdventMain.places.placeObject(GameObjects.AXE, currentLocation);
				}
				else
				{
					output += "\n\nThere is a threatening little dwarf in the room with you!";
					dwarfPresent = 2;
					AdventMain.places.placeObject(GameObjects.DWARF, currentLocation);
				}
			}
			else if (dwarfPresent == 2 && battleUpdate)
			{
				output += "\n\nThere is a threatening little dwarf in the room with you!\nOne sharp nasty knife is "
						+ "thrown at you!";
				boolean hit = false;
				if(dwarves >= 3)
				{
					if(AdventMain.generate() * 1000 < 95 * (dwarves - 2))
					{	hit = true;	}
				}
				else
				{	dwarves++;	}
				if(hit)
				{	
					output += "\nIt gets you!";	
					playerIsDead = true;
				}
				else
				{	output += "\nIt misses!";	}
			}
		}
		battleUpdate = false;
		if(15 - tally == lostTreasures && lamp > 35)
		{	lamp = 35;	}
		getCurrentScore();
		if(playerIsDead && !over)
		{	output += death(output); }
		if(over)
		{	output = quit(output);	}
		else
		{
			output = output + checkForHints();
		}
		AdventMain.logGameInfo();
		return output;
	}
	
	private String checkForHints()
	{
		String output = new String("");
		boolean justArrived = (locationAtStartOfAction != currentLocation);
		if(!caveIsClosed)
		{
			if(actionToAttempt == ActionWords.NOTHING)
			{
				if((Hints.GRATE.proc < Hints.GRATE.threshold) && !grateIsUnlocked && currentLocation == Locations.OUTSIDE && !justArrived && !(objectIsPresent(GameObjects.KEYS))) { Hints.GRATE.proc++; }

				if((Hints.SNAKE.proc < Hints.SNAKE.threshold) && objectIsPresent(GameObjects.SNAKE) && !(objectIsPresent(GameObjects.BIRD)) && !justArrived) 
				{ Hints.SNAKE.proc++; }
				
				if((Hints.MAZE.proc < Hints.MAZE.threshold) &&  ((currentLocation.ordinal() >= Locations.ALIKE1.ordinal()) && (currentLocation.ordinal() <= Locations.ALIKE14.ordinal())) || (currentLocation != Locations.DEAD2 && currentLocation != Locations.DEAD8 && (currentLocation.ordinal() >= Locations.DEAD1.ordinal()) && (currentLocation.ordinal() <= Locations.DEAD11.ordinal())))
				{ Hints.MAZE.proc++; }
				
				if((Hints.DARK.proc < Hints.DARK.threshold) && currentLocation == Locations.ALCOVE || (currentLocation == Locations.PROOM && !(objectIsPresent(GameObjects.LAMP))))
				{ Hints.DARK.proc++; }
				
				if((Hints.WITT.proc < Hints.WITT.threshold) && currentLocation == Locations.WITT)
				{ Hints.WITT.proc++; }		
			}	
			
			for(Hints hint : Hints.values())
			{
				if(hint.proc == hint.threshold && questionAsked == Questions.NONE && hintToOffer == Hints.NONE && offeredHint == Hints.NONE)
				{
					switch(hint)
					{
						case WEST: hint.proc++; hint.given = true; output += hint.hint; break;				
						default: output += confirmIntentBeforeHint(hint); break; 
					}
				}
			}
		}
		return output;
	}
	
	private void resetHintsAndQuestions()
	{ questionAsked = Questions.NONE; hintToOffer = Hints.NONE; offeredHint = Hints.NONE; }
	
	private String resurrection()
	{
		switch(deaths)
		{
			case 2:
				playerIsDead = false;
				return "All right. But don't blame me if something goes wr......\n\t---POOF!!---\nYou are engulfed in a cloud of orange smoke. Coughing and gasping, you emerge from the smoke to find....\n" + AdventMain.places.getDescription(currentLocation, brief) + listItemsHere(currentLocation); 
			case 1:
				playerIsDead = false;
				return "Okay, now where did I put my resurrection kit?....\n\t>POOF!<\nEverything disappears in a dense cloud of orange smoke.\n" + AdventMain.places.getDescription(currentLocation, brief) + listItemsHere(currentLocation); 
			default:
				over = true;
				return "Okay, if you're so smart, do it yourself! I'm leaving!";
		}
	}

	private String confirmIntentBeforeHint(Hints hint)
	{
		hint.proc++;
		hintToOffer = hint;
		return hint.question;
	}
	
	private String offerHint()
	{
		offeredHint = hintToOffer;
		hintToOffer = Hints.NONE;
		return AdventMain.offerHintMessage.apply(offeredHint.cost);
	}
	
	private String giveHint(Hints hint)
	{
		hint.given = true;
		offeredHint = Hints.NONE;
		if(lamp > 30)
		{	lamp += 30 * hint.cost;	}
		return hint.hint + (hint == Hints.DARK ? (objectIsPresent(GameObjects.EMERALD) ? "" : " None of the objects available is immediately useful for discovering the secret.") : "");
	}
	
	private String listItemsHere(Locations here)
	{
		String output = "";
		ArrayList<GameObjects> objects = AdventMain.objectsHere(here);
		if(objects != null)
		{
			for(GameObjects thing : objects)
			{
				output = new String(output + AdventMain.things.getItemDescription(here, thing));
				
				if(GameObjects.isTreasure(thing))
				{
					if(!haveIFound(GameObjects.RUG) && thing == GameObjects.RUG_)
					{	wasFound(GameObjects.RUG);	}
					else if(!(thing == GameObjects.RUG_) && !haveIFound(thing))
					{	wasFound(thing);	}
				}
			}
		}
		return output;
	}
	
	private int askYesNo(String input)
	{ return ( (input.substring(0, 1).equals("n")) ? 2 : ( (input.substring(0, 1).equals("y")) ? 1 : 0 ) ); }

	private String attemptAction(ActionWords verb, Object other, String alt)
	{
		String output = "I'm game. Would you care to explain how?";
		if(other == null)
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
					
					if(endGameObjectsStates[8] && object == GameObjects.ROD) { endGameObjectsStates[8] = false; }
					if(endGameObjectsStates[1] && object == GameObjects.LAMP) { endGameObjectsStates[1] = false; }
					if(endGameObjectsStates[0] && object == GameObjects.BOTTLE) { endGameObjectsStates[0] = false; }
					if(endGameObjectsStates[2] && object == GameObjects.PILLOW) { endGameObjectsStates[2] = false; }
					
					if(object == GameObjects.ROD && !objectIsHere(GameObjects.ROD) && objectIsHere(GameObjects.ROD2))
					{
						output = attemptAction(ActionWords.TAKE, GameObjects.ROD2, alt);
						if(endGameObjectsStates[9]) { endGameObjectsStates[9] = false; }
					}
					else if(object == GameObjects.RUG && !objectIsHere(GameObjects.RUG) && objectIsHere(GameObjects.RUG_))
					{	output = attemptAction(ActionWords.TAKE, GameObjects.RUG_, alt);	}
					else if(object == GameObjects.ALL)
					{
						output = "";
						ArrayList<GameObjects> itemsHere = AdventMain.objectsHere(currentLocation);
						if(!(itemsHere == null))
						{ 
							for(GameObjects item : itemsHere) 
							{	
								increaseTurns = true;
								output += attemptAction(ActionWords.TAKE, item, "") + "\n";	
								if(increaseTurns) { turns++; }
							} 
						}
						else
						{
							output = "There is nothing to take.";
						}
						increaseTurns = false;
					}
					else if(object == GameObjects.WATER)
					{
						if(GameObjects.BOTTLE.location == Locations.INHAND)
						{
							if(stateOfTheBottle == 0)
							{
								if(currentLocation.hasWater)
								{
									output = new String("You fill the bottle with water.");
									stateOfTheBottle = 1;
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
						else if(objectIsHere(GameObjects.BOTTLE) && stateOfTheBottle == 1)
						{	output = attemptAction(verb, GameObjects.BOTTLE, alt);	}
						else
						{
							output = new String("You have nothing in which to carry it.");
							increaseTurns = false;
						}
					}
					else if(object == GameObjects.OIL)
					{
						if(GameObjects.BOTTLE.location == Locations.INHAND)
						{
							if(stateOfTheBottle == 0)
							{
								if(Locations.EASTPIT == currentLocation)
								{
									output = new String("You fill the bottle with oil.");
									stateOfTheBottle = 2;
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
						else if(objectIsHere(GameObjects.BOTTLE) && stateOfTheBottle == 2)
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
						actionToAttempt = verb;
						increaseTurns = false;
					}
					else if(objectIsPresent(object))
					{
						if(object.location == Locations.INHAND)
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
						}
						else if(object == GameObjects.BEAR && stateOfTheBear == 3)
						{
							output = "You can't be serious!";
							increaseTurns = false;
						}
						else if(object == GameObjects.BEAR && stateOfTheChain == 0)
						{
							output = new String("The bear is still chained to the wall.");
							increaseTurns = false;
						}
						else if(object == GameObjects.BEAR && stateOfTheChain != 0)
						{
							stateOfTheBear = 2;
							output = "The bear is now following you.";
						}
						else if(object == GameObjects.CHAIN && stateOfTheChain != 1)
						{
							output = new String("The chain is still locked.");
							increaseTurns = false;
						}
						else if(caveIsClosed && (object == GameObjects.BIRD || object == GameObjects.CAGE))
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
							if(isInHand(GameObjects.ROD) && !birdInCage)
							{
								output = new String("The bird was unafraid when you entered, but as you approach it becomes disturbed and you cannot catch it.");
								if(Hints.BIRD.proc < Hints.BIRD.threshold) { Hints.BIRD.proc++; }
								increaseTurns = false;
							}
							else if(!isInHand(GameObjects.CAGE))
							{
								output = new String("You can catch the bird, but you cannot carry it.");
								increaseTurns = false;
							}
							else if(isInHand(GameObjects.CAGE))
							{
								birdInCage = true;
								takeObject(GameObjects.BIRD);
								output = AdventMain.okay;
							}
							else
							{
								if(birdInCage)
								{
									takeObject(GameObjects.BIRD);
									takeObject(GameObjects.CAGE);
									output = AdventMain.okay;
								}
							}
						}
						else if(object == GameObjects.RUG || object == GameObjects.RUG_)
						{
							if(objectIsHere(GameObjects.RUG) || objectIsHere(GameObjects.RUG_))
							{
								takeObject(GameObjects.RUG);
								AdventMain.places.voidObject(GameObjects.RUG_);
								output = AdventMain.okay;
							}
						}
						else if(object == GameObjects.AXE && bearAxe && stateOfTheBear == 0)
						{
							output = "There is no way past the bear to get the axe, which is probably just as well.";
						}
						else if(object == GameObjects.VASE && vaseIsBroken == true)
						{
							output = "You can't be serious!";
						}
						else if(object.mobile && objectIsHere(object))
						{
							takeObject(object);
							output = AdventMain.okay;
						}
					}
					else
					{
						if(caveIsClosed && object == GameObjects.MIRROR){	}
						else
						{
							output = new String("I don't see any " + alt + ".");
						}
						increaseTurns = false;
					}
					if(caveIsClosed && object == GameObjects.OYSTER && endGameObjectsStates[3]) 
					{
						endGameObjectsStates[3] = false; 
						output = "Interesting. There seems to be something written on the under-side of the oyster."; //TODO does this work right?
					}
				break;
					
				case DROP:
					output = new String("");
					if(isInHand(GameObjects.ROD2) && object == GameObjects.ROD && !isInHand(GameObjects.ROD))
					{
						dropObject(GameObjects.ROD2);
						output = AdventMain.okay;
					}
					else if(object == GameObjects.ALL)
					{
						ArrayList<GameObjects> itemsHere = AdventMain.objectsHere(Locations.INHAND);
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
						actionToAttempt = verb;
						increaseTurns = false;
					}
					else if(object == GameObjects.BEAR)
					{
						if(stateOfTheBear == 2)
						{
							if(objectIsHere(GameObjects.TROLL)||objectIsHere(GameObjects.TROLL_))
							{
								voidObject(GameObjects.TROLL);
								voidObject(GameObjects.TROLL_);
								AdventMain.places.placeObject(GameObjects.TROLL2_, Locations.NESIDE);
								AdventMain.places.placeObject(GameObjects.TROLL2, Locations.SWSIDE);
								stateOfTheBear = 4;
								stateOfTheTroll = 2;
								output = "The bear lumbers toward the troll, who lets out a startled shriek and "
										+ "scurries away. The bear soon gives up pursuit and wanders back.";
							}
							else
							{
								stateOfTheBear = 4;
								output = AdventMain.okay;
							}
						}
						else
						{
							output = AdventMain.dontHave;
						}
					}
					else if(isInHand(object))
					{
						if(object == GameObjects.CAGE && birdInCage)
						{
							dropObject(GameObjects.CAGE);
							dropObject(GameObjects.BIRD);
							output = AdventMain.okay;
						}
						else if(object == GameObjects.BIRD)
						{
							if(objectIsHere(GameObjects.SNAKE))
							{
								birdInCage = false;
								dropObject(GameObjects.BIRD);
								voidObject(GameObjects.SNAKE);
								snakeInHotMK = false;
								output = new String("The little bird attacks the green snake, and in an astounding "
										+ "flurry drives the snake away.");
								if(caveIsClosed)
								{
									playerIsDead = true;
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
								output = AdventMain.okay;
								birdInCage = false;
								AdventMain.places.placeObject(GameObjects.BIRD, currentLocation);
							}
						}
						else if(object == GameObjects.COINS && objectIsHere(GameObjects.PONY))
						{
							voidObject(GameObjects.COINS);
							lostTreasures++;
							dropObject(GameObjects.BATTERIES);
							stateOfSpareBatteries = 1;
						}
						else if(object == GameObjects.VASE && !(objectIsHere(GameObjects.PILLOW) 
								|| currentLocation == Locations.SOFT))
						{
							output = "The Ming vase drops with a delicate crash.";
							dropObject(GameObjects.VASE);
							vaseIsBroken = true;
							lostTreasures++;
						}
						else
						{
							dropObject(object);
							output = AdventMain.okay;
						}	
					}
					else
					{
						output = AdventMain.dontHave;
						increaseTurns = false;
					}
					break;
					
				case OPEN:
					if(object == GameObjects.GRATE)
					{
						if(!objectIsPresent(GameObjects.GRATE) && !objectIsPresent(GameObjects.GRATE_))
						{
							output = new String("I don't see any grate");
							increaseTurns = false;
						}
						else if(objectIsPresent(GameObjects.KEYS))
						{
							output = new String("The grate is now unlocked.");
							grateIsUnlocked = true;
						}
						else
						{
							output = new String("You don't have any keys!");
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
								AdventMain.places.placeObject(GameObjects.OYSTER, currentLocation);
								output = new String("A glistening pearl falls out of the clam and rolls away. "
										+ "Goodness, this must really be an oyster! (I never was very good at "
										+ "identifying bivalves.)\nWhatever it is, it has now snapped shut again.");
								AdventMain.places.placeObject(GameObjects.PEARL, Locations.CULDESAC);
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
							if(doorHasBeenOiled)
							{
								output = AdventMain.okay;
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
							else if(stateOfTheChain == 0)
							{
								if(stateOfTheBear != 1)
								{
									output = new String("There is no way to get past the bear to unlock the chain, "
											+ "which is probably just as well.");
									increaseTurns = false;
								}
								else
								{
									stateOfTheChain = 1;
									output = new String("You unlock the chain and set the tame bear free.");
								}
							}
							else if(stateOfTheChain == 2)
							{
								stateOfTheChain = 1;
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
						actionToAttempt = verb;
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
						else if(!grateIsUnlocked)
						{
							output = new String("It was already locked.");
							increaseTurns = false;
						}
						else
						{
							output = new String("The grate is now locked.");
							grateIsUnlocked = false;
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
							else if(stateOfTheChain != 1)
							{
								output = new String("It was already locked.");
								increaseTurns = false;
							}
							else if(stateOfTheChain == 1)
							{
								if(!(currentLocation == Locations.BARR))
								{
									output = new String("There is nothing here to which the chain can be locked.");
									increaseTurns = false;
								}
								else
								{
									stateOfTheChain = 2;
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
							}
							else
							{
								output = new String("Your lamp is now on.\n\n" 
							+ getDescription(currentLocation, brief));
							}
							lampIsLit = true;
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
						lampIsLit = false;
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
						actionToAttempt = verb;
						increaseTurns = false;
					}
					else if (!isInHand(object) && (object != GameObjects.ROD || !isInHand(GameObjects.ROD2)))
					 {
						output = AdventMain.dontHave;
						increaseTurns = false;
					 }
					 else if(object != GameObjects.ROD || caveIsClosed)
					 {
						 if(!isInHand(object))
						 {
							output = new String("I don't see any " + alt + ".");
							increaseTurns = false;
						 }
					 }
					 else if((currentLocation != Locations.EASTFISSURE && currentLocation != Locations.WESTFISSURE))
					 {
						output = AdventMain.nothing;
						increaseTurns = false;
					 }
					 else
					 {
						 if(!caveIsClosing)
						 {
							 if(!crystalBridgeIsThere)
							 {
								 output = new String("A crystal bridge now spans the fissure.");
								 AdventMain.places.placeObject(GameObjects.CRYSTAL, Locations.EASTFISSURE);
								 AdventMain.places.placeObject(GameObjects.CRYSTAL_, Locations.WESTFISSURE);
								 crystalBridgeIsThere = true;
							 }
							 else
							 {
								 output = new String("The crystal bridge has vanished!");
								 voidObject(GameObjects.CRYSTAL);
								 voidObject(GameObjects.CRYSTAL_);
								 crystalBridgeIsThere = false;
							 }
						 }
						 else
						 {	output = AdventMain.nothing;	}
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
						if(stateOfTheBottle == 1)
						{	object = GameObjects.WATER;	}
						else if(stateOfTheBottle == 2)
						{	object = GameObjects.OIL;	}
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
					else if(!isInHand(GameObjects.BOTTLE))
					{
						output = "You have nothing to pour.";
						increaseTurns = false;
					}
					else if(object == GameObjects.WATER && stateOfTheBottle == 1)
					{
						if(currentLocation == Locations.WESTPIT)
						{
							stateOfThePlant++;
							if(stateOfThePlant == 1)
							{
								output = new String("The plant spurts into furious growth for a few seconds."
										+ "\n\n\tThere is a 12-foot-tall beanstalk stretching up out of the pit, "
										+ "bellowing \"Water!! Water!!\"");
							}
							else if(stateOfThePlant == 2)
							{
								output = new String("The plant grows explosively, almost filling the bottom of "
										+ "the pit.\n\n\tThere is a gigantic beanstalk stretching all the way up "
										+ "to the hole.");
							}
							else if(stateOfThePlant == 3)
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
							doorHasBeenOiled = false;
							output = new String("The hinges are quite thoroughly rusted now and won't budge.");
						}
						else
						{	output = new String("Your bottle is empty and the ground is wet.");	}
						stateOfTheBottle = 0;
					}
					else if(object == GameObjects.OIL && stateOfTheBottle == 2)
					{
						if(currentLocation == Locations.WESTPIT)
						{
							if(stateOfThePlant == 1)
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
							doorHasBeenOiled = true;
							output = new String("The oil has freed up the hinges so that the door will now move,"
									+ " although it requires some effort.");
						}
						else
						{	output = new String("Your bottle is empty and the ground is wet.");	}
						stateOfTheBottle = 0;
					}	
					break;
					
				case EAT:
					if(object == GameObjects.NOTHING)
					{
						output = "What would you like to eat?";
						actionToAttempt = verb;
						increaseTurns = false;
					}
					else if(object == GameObjects.FOOD)
					{
						if(!objectIsPresent(GameObjects.FOOD))
						{	output = "You don't have any.";	}
						else
						{
							voidObject(GameObjects.FOOD);
							if(isInHand(GameObjects.FOOD))
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
					else if(object == GameObjects.NOTHING)
					{
						output = "What would you like to throw?";
						actionToAttempt = verb;
						increaseTurns = false;
					}
					else if(object == GameObjects.BEAR)
					{
						if(stateOfTheBear == 2)
						{
							if(objectIsHere(GameObjects.TROLL)||objectIsHere(GameObjects.TROLL_))
							{
								voidObject(GameObjects.TROLL);
								voidObject(GameObjects.TROLL_);
								AdventMain.places.placeObject(GameObjects.TROLL2_, Locations.NESIDE);
								AdventMain.places.placeObject(GameObjects.TROLL2, Locations.SWSIDE);
								stateOfTheBear = 4;
								stateOfTheTroll = 2;
								output = "The bear lumbers toward the troll, who lets out a startled shriek and "
										+ "scurries away. The bear soon gives up pursuit and wanders back.";
							}
							else
							{
								stateOfTheBear = 4;
								output = AdventMain.okay;
							}
						}
						else
						{
							output = AdventMain.dontHave;
						}
					}
					else if(!(isInHand(object)))
					{
						output = AdventMain.dontHave;
						increaseTurns = false;
					}
					else if((objectIsHere(GameObjects.TROLL_) || objectIsHere(GameObjects.TROLL)) 
							&& GameObjects.isTreasure(object))
					{
						voidObject(object);
						voidObject(GameObjects.TROLL);
						voidObject(GameObjects.TROLL_);
						AdventMain.places.placeObject(GameObjects.TROLL2, Locations.SWSIDE);
						AdventMain.places.placeObject(GameObjects.TROLL2_, Locations.NESIDE);
						stateOfTheTroll = 3;
						itemsInHand--;
						output = "The troll catches your treasure and scurries away out of sight.";
						if(object != GameObjects.EGGS)
						{	lostTreasures++;	}
					}
					else if(object == GameObjects.FOOD && objectIsHere(GameObjects.BEAR))
					{	output = attemptAction(ActionWords.FEED, GameObjects.BEAR, "");	}
					else if(!(object == GameObjects.AXE))
					{	
						output = attemptAction(ActionWords.DROP, object, alt);	
					}
					else if(objectIsHere(GameObjects.DWARF))
					{
						battleUpdate = true;
						if(AdventMain.generate() * 3 > 1)
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
						AdventMain.places.placeObject(GameObjects.AXE, currentLocation);
						itemsInHand--;
					}
					else if((objectIsHere(GameObjects.DRAGON_) || objectIsHere(GameObjects.DRAGON)) && dragonIsAlive)
					{
						output = "The axe bounces harmlessly off the dragon's thick scales.";
						AdventMain.places.placeObject(GameObjects.AXE, currentLocation);
						itemsInHand--;
					}
					else if((objectIsHere(GameObjects.TROLL_) || objectIsHere(GameObjects.TROLL)))
					{
						output = "The troll deftly catches the axe, examines it carefully, and tosses it back, "
								+ "declaring, \"Good workmanship, but it's not valuable enough.\"";
					}
					else if(objectIsHere(GameObjects.BEAR) && stateOfTheBear == 0)
					{
						bearAxe = true;
						AdventMain.places.placeObject(GameObjects.AXE, currentLocation);
						itemsInHand--;
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
							vaseIsBroken = true;
							lostTreasures++;
							dropObject(GameObjects.VASE);
						}
					}
					else if(object == GameObjects.MIRROR)
					{
						if(caveIsClosed)
						{
							output = "You strike the mirror a resounding blow, whereupon it shatters into "
									+ "a myriad tiny fragments.";
							playerIsDead = true;
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
						actionToAttempt = verb;
						increaseTurns = false;
					}
					else if(object == GameObjects.BIRD && caveIsClosed)
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
					else if(object == GameObjects.DWARF && caveIsClosed)
					{
						playerIsDead = true;
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
							if(stateOfTheBear == 0)
							{
								output = new String("With what? Your bare hands? Against HIS bear hands??");
								increaseTurns = false;
							}
							else if(stateOfTheBear != 3)
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
							if(!dragonIsAlive)
							{
								output = new String("For crying out loud, the poor thing is already dead!");
								increaseTurns = false;
							}
							else
							{
								output = new String("With what? Your bare hands?");
								questionAsked = Questions.DRAGON;
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
					{	output = MessageWords.CUSS.message;	}
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
					else if(caveIsClosed && objectIsPresent(GameObjects.OYSTER))
					{
						if(Hints.BIRD.proc > 0){ output = "It says the same thing it did before."; }
						else
						{
							output = "Hmmm, this looks like a clue, which means it'll cost you 10 points to read it. Should I go ahead and read it anyway?";
							questionAsked = Questions.READBLASTHINT;
							offeredHint = Hints.BLAST;
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
						output = "\t   -----" + listItemsHere(Locations.INHAND) + "\n\t   -----";
						if(stateOfTheBear == 2)
						{	output = output + GameObjects.BEAR.descriptions[2];	}
					}
					else
					{	output = new String("\t   -----\n\t\tYou're not carrying anything.\n\t   -----");	}
					break;
					
				case SCORE:
					output = "If you were to quit now, you would score " + getCurrentScore() + " out of a possible 350. \nDo you indeed wish to quit now?";
					questionAsked = Questions.QUIT;
					increaseTurns = false;
					break;
					
				case QUIT:
					output = "Do you really wish to quit now?";
					questionAsked = Questions.QUIT;
					increaseTurns = false;
					break;
					
				case FEED:
					output = "There is nothing here it wants to eat (except perhaps you).";
					if(object == GameObjects.TROLL && (objectIsHere(GameObjects.TROLL) || objectIsHere(GameObjects.TROLL_)))
					{ output = "Gluttony is not one of the troll's vices. Avarice, however, is."; }
					else if(object == GameObjects.DRAGON && (objectIsHere(GameObjects.DRAGON) || objectIsHere(GameObjects.DRAGON_)))
					{
						if(!dragonIsAlive){ output = "Don't be ridiculous!"; }
					}
					else if(objectIsHere(object))
					{
						if(object == GameObjects.BIRD)
						{ output = "It's not hungry (it's merely pinin' for the fjords). Besides, you have no bird seed."; }
						else if(object == GameObjects.SNAKE)
						{
							if(!caveIsClosed && objectIsPresent(GameObjects.BIRD))
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
								stateOfTheBear = 1;
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
							actionToAttempt = verb;
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
					if(caveIsClosed && object == GameObjects.DWARF)
					{
						output = "You wake the nearest dwarf, who wakes up grumpily, takes one look at you, "
								+ "curses, and grabs for his axe.";
						playerIsDead = true;
						fatality = 2;
					}
					else
					{
						output = "You can't be serious!";
						increaseTurns = false;
					}
					break;
					
				case DRINK:
					boolean waterIsHere = currentLocation.hasWater;
					output = "You have nothing to drink.";
					if(waterIsHere && !(isInHand(GameObjects.BOTTLE) && stateOfTheBottle == 1))
					{
						output = "You have taken a drink from the stream. The water tastes strongly of "
								+ "minerals, but is not unpleasant. It is extremely cold.";
					}
					else if(isInHand(GameObjects.BOTTLE) && stateOfTheBottle == 1)
					{
						stateOfTheBottle = 0;
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
					if(!canISee(currentLocation))
					{
						output = "You have no source of light.";
					}
					else if(object == GameObjects.NOTHING)
					{
						output = AdventMain.places.getDescription(currentLocation, 2);
						output += "\n" + listItemsHere(currentLocation);
					}
					else
					{
						output = "Sorry, but I am not allowed to give more detail. I will repeat the long "
								+ "description of your location.\n\n" + AdventMain.places.getDescription(currentLocation, 2);
					}
					break;
					
				case CALM:
					output = "I'm game. Would you care to explain how?";
					break;

				case FILL:
					boolean liquidHere = (currentLocation.hasWater 
							|| currentLocation == Locations.EASTPIT);
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
							vaseIsBroken = true;
							AdventMain.places.placeObject(GameObjects.VASE, currentLocation);
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
					else if(stateOfTheBottle != 0)
					{
						output = "Your bottle is already full.";
						increaseTurns = false;
					}
					else if(!liquidHere)
					{
						output = "You have nothing with which to fill the bottle.";
						increaseTurns = false;
					}
					else if(currentLocation == Locations.EASTPIT)
					{
						increaseTurns = true;
						output = new String("You fill the bottle with oil.");
						stateOfTheBottle = 2;
					}
					else
					{
						increaseTurns = true;
						output = new String("You fill the bottle with water.");
						stateOfTheBottle = 1;
					}
					break;
					
				case BLAST:
					if(caveIsClosed)
					{
						bonus = (objectIsPresent(GameObjects.ROD2) ? 25 : currentLocation == Locations.NEEND ? 30 : 45);
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
					boolean fum = (alt.equals(AdventMain.feeFieFoe[fooMagicWordProgression]));
					System.out.println(alt + " " + fum);
					if(fum)
					{
						if(fooMagicWordProgression < 3)
						{
							fooMagicWordProgression++;
							output = AdventMain.okay;
						}
						else
						{
							fooMagicWordProgression = 0;
							if(GameObjects.EGGS.location == Locations.GIANT)
							{ output = AdventMain.nothing; }
							else 
							{
								if(currentLocation != Locations.GIANT)
								{
									if(objectIsPresent(GameObjects.EGGS))
									{	output = "The nest of golden eggs disappears!";	}
									else
									{	output = "Done!";	}
								}
								else
								{ output = "There is a large nest here, full of golden eggs!"; }
								
								if(isInHand(GameObjects.EGGS)){ itemsInHand--; } //TODO check this in method?
								AdventMain.places.placeObject(GameObjects.EGGS, Locations.GIANT);
							}
						}
					}
					else
					{
						if(fooMagicWordProgression > 0)
						{ output = "What's the matter, can't you read? Now you'd best start over."; }
						else
						{ output = "Nothing happens."; }
						fooMagicWordProgression = 0;
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
		catch(NullPointerException e)
		{
			increaseTurns = false;
		}
		return output;
	}
	
	private String attemptMovement(String input)
	{
		if(!AdventMain.KnownWords.containsKey(input))
		{
			increaseTurns = false;
			return "You can not do that.";
		}
		return attemptMovement((Movement) AdventMain.KnownWords.get(input), input);
	}
	
	private String attemptMovement(Movement destination, String input)
	{
		String output = "";
		locationChange = true;
		
		Locations locationResult = currentLocation.moveTo(destination, currentLocation);
		
		if(caveIsClosed && locationResult != Locations.THEVOID && (locationResult.compareTo(Locations.THEVOID) < 10))
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
			int neordinal = currentLocation.getOrdinal(Locations.NESIDE);
			if(currentLocation.getOrdinal(GameObjects.CHAIN.location) >= neordinal)
			{
				lostTreasures++;
				previousLocation = currentLocation;
			}
			if(currentLocation.getOrdinal(GameObjects.SPICES.location) >= neordinal)
			{
				lostTreasures++;
				previousLocation = currentLocation;
			}
		}
		else if(locationResult.equals(Locations.THEVOID))
		{
			if(destination.equals(Movement.XYZZY)||destination.equals(Movement.PLOVER)
					||destination.equals(Movement.PLUGH))
			{
				output = AdventMain.nothing;
				output = output + "\n" + getDescription(currentLocation, brief);
				increaseTurns = false;
			}
			else if(!canISee(currentLocation))
			{
				output = pitchDark(output);
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
				increaseTurns = false;
				output = "There are no exits in that direction.\n";
				output = output + getDescription(currentLocation, brief);
			}
			else
			{
				output = "I don't know how to apply that word here.\n";
				output = output + getDescription(currentLocation, brief);
				increaseTurns = false;
			}
		}
		else if(locationResult.equals(Locations.CRACK))
		{
			output = "That crack is far too small for you to follow.";
			increaseTurns = false;
		}
		else if(locationResult.equals(Locations.NECK))
		{
			output = "You are at the bottom of the pit with a broken neck.";
			increaseTurns = false;
			playerIsDead = true;
			fatality = 1;
		}
		else if(locationResult.equals(Locations.LOSE))
		{
			output = "You didn't make it.";
			increaseTurns = false;
			playerIsDead = true;
			fatality = 1;
		}
		else if(locationResult.equals(Locations.CANT))
		{
			output = "The dome is unclimbable.";
			increaseTurns = false;
		}
		else if(locationResult.equals(Locations.CLIMB))
		{
			setLocation(Locations.NARROW);
			output = "You clamber up the plant and scurry through the hole at the top.\n" 
					+ getDescription(currentLocation, brief);
		}
		else if(locationResult.equals(Locations.CHECK))
		{
			if(stateOfThePlant == 1)
			{
				setLocation(Locations.WEST2PIT);
				output = "You have climbed up the plant and out of the pit.\n" 
						+ getDescription(currentLocation, brief);
			}
			else
			{
				output = "There is nothing here to climb. Use \"UP\" or \"OUT\" to leave the pit.";
				increaseTurns = false;
			}
		}
		else if(locationResult.equals(Locations.SNAKED))
		{
			output = "You can't get by the snake.";
			increaseTurns = false;
		}
		else if(locationResult.equals(Locations.THRU))
		{
			setLocation(Locations.WESTMIST);
			output = "You have crawled through a very low wide passage parallel to and north "
					+ "of the Hall of Mists.\n" + getDescription(currentLocation, brief);
		}
		else if(locationResult.equals(Locations.DUCK))
		{
			setLocation(Locations.WESTFISSURE);
			output = "You have crawled through a very low wide passage parallel to and north "
					+ "of the Hall of Mists.\n" + getDescription(currentLocation, brief);
		}
		else if(locationResult.equals(Locations.SEWER))
		{
			output = "The stream flows out through a pair of 1-foot-diameter sewer pipes."
					+ "\n\nIt would be advisable to use the exit.";
			increaseTurns = false;
		}
		else if(locationResult.equals(Locations.UPNOUT))
		{
			output = "There is nothing here to climb. Use \"UP\" or \"OUT\" to leave the pit.";
			increaseTurns = false;
		}
		else if(locationResult.equals(Locations.DIDIT))
		{
			setLocation(Locations.WEST2PIT);
			output = "You have climbed up the plant and out of the pit.\n" 
					+ getDescription(currentLocation, brief);
		}
		else if(locationResult.equals(Locations.REMARK))
		{
			if(currentLocation.equals(Locations.SLIT)||currentLocation.equals(Locations.WET))
			{
				output = "You can't fit through a two-inch slit!";
				increaseTurns = false;
			}
			else if(currentLocation.equals(Locations.INSIDE)||currentLocation.equals(Locations.OUTSIDE)
					||currentLocation.equals(Locations.DEBRIS)||currentLocation.equals(Locations.AWKWARD)||
					currentLocation.equals(Locations.BIRD)||currentLocation.equals(Locations.SMALLPIT))
			{
				output = "You can't go through a locked steel grate!";
				increaseTurns = false;
			}
			else if((currentLocation.equals(Locations.SWSIDE) || currentLocation.equals(Locations.NESIDE)) 
					&& destination != Movement.JUMP)
			{
				increaseTurns = false;
				if(stateOfTheTroll == 0)
				{	output = "The troll refuses to let you cross.";	}
				else if(stateOfTheTroll == 1)
				{
					output = "The troll steps out from beneath the bridge and blocks your way.";
					stateOfTheTroll = 0;
					voidObject(GameObjects.TROLL2);
					voidObject(GameObjects.TROLL2_);
					AdventMain.places.placeObject(GameObjects.TROLL, Locations.SWSIDE);
					AdventMain.places.placeObject(GameObjects.TROLL_, Locations.NESIDE);
				}
				else
				{	output = "There is no longer any way across the chasm.";	}
			}
			else if(currentLocation.equals(Locations.NESIDE)||currentLocation.equals(Locations.SWSIDE)
					||currentLocation.equals(Locations.EASTFISSURE)
					||currentLocation.equals(Locations.WESTFISSURE))
			{
				increaseTurns = false;
				if(destination.equals(Movement.JUMP))
				{	output = "I respectfully suggest that you go across the bridge instead of jumping.";	}
				else
				{	output = "There is no way to cross the fissure.";	}
			}
			else if(currentLocation.equals(Locations.HALLOFMOUNTAINKING))
			{
				output = "You can't get by the snake.";
				increaseTurns = false;
			}
			else if(currentLocation.equals(Locations.SHELL))
			{
				increaseTurns = false;
				if(isInHand(GameObjects.CLAM))
				{	output = "You can't fit this five-foot clam through that little passage!";	}
				else
				{	output = "You can't fit this five-foot oyster through that little passage!";	}
			}
			else if(currentLocation.equals(Locations.WITT)&&destination.equals(Movement.WEST))
			{
				output = "You have crawled around in some little holes and found your way blocked"
						+ " by a recent cave-in.\nYou are now back in the main passage.";
			}
			else if(currentLocation.equals(Locations.WITT)||currentLocation.equals(Locations.BEDQUILT)
					||currentLocation.equals(Locations.CHEESE))
			{
				output = "You have crawled around in some little holes and wound up back in the main "
						+ "passage.";
			}
			else if(currentLocation.equals(Locations.IMMENSE))
			{
				output = "The door is extremely rusty and refuses to open.";
				increaseTurns = false;
			}
			else if(currentLocation.equals(Locations.SCAN1)||currentLocation.equals(Locations.SCAN3))
			{
				output = "That dragon looks rather nasty. You'd best not try to get by.";
				increaseTurns = false;
			}
			else if(currentLocation.equals(Locations.VIEW))
			{
				output = "Don't be ridiculous!";
				increaseTurns = false;
			}
			else if(currentLocation.equals(Locations.PROOM) || currentLocation.equals(Locations.DROOM) || currentLocation.equals(Locations.ALCOVE))
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
			if(caveIsClosing && currentLocation.outsideCave(locationResult))
			{
				if(!allowExtraMovesForPanic)
				{
					clock2 = 15;
					allowExtraMovesForPanic = true;
					output = "A mysterious recorded voice groans into life and announces: "
							+ "\n\t\"This exit is closed. Please leave via main office.\"";
				}
			}
			boolean follow = false;
			if(dwarfPresent == 2)
			{
				if(justBlocked)
				{
					justBlocked = false;
					if(locationResult.critters(locationResult))
					{
						follow = true;
						dwarfPresent = 1;
					}
				}
				else
				{	
					wayIsBlocked = true;
				}
			}

			if(wayIsBlocked)
			{
				battleUpdate = true;
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
					output = pitchDark(output);
				}
				else
				{
					//System.out.println("d " + dwarves);
					if(currentLocation.critters(currentLocation))
					{
						double chance = AdventMain.generate();
						if(pirate == 0)
						{
							movesWOEncounter++;
							double likely = (movesWOEncounter * 10 / 8)/8; 
							if(chance * 100 <= likely)
							{	pirate = 1;	}
							//System.out.println("likely " + likely + "\npirate " + pirate);
						}
						chance = AdventMain.generate();
						if(dwarvesAllowed && dwarves > 0 && dwarvesLeft > 0 && !follow)
						{
							double encounter = (dwarvesLeft * 1000)/50;
							if(chance * 1000 <= encounter)
							{	dwarfPresent = 1;	}
							//System.out.println("encounter " + encounter + "\nchance " + chance * 1000);
						}
					}
					output = getDescription(currentLocation, brief);
					if(stateOfTheBear == 2)
					{	output += "\n\tYou are being followed by a very large, tame bear.";	}
					if(follow)
					{	AdventMain.places.placeObject(GameObjects.DWARF, currentLocation);	}
					if(currentLocation.equals(Locations.Y2))
					{
						double chance = AdventMain.generate();
						if(chance > .74)
						{	output = new String(output + "\n\nA hollow voice says \"PLUGH\"");	}
					}
				}
			}
			if(relocate)
			{
				AdventMain.places.placeObject(GameObjects.EMERALD, Locations.PROOM);
				itemsInHand--;
				relocate = false;
			}
		}
		return output;
	}
	
	private String pitchDark(String output)
	{
		boolean pitifulDeath = fallInPit();
		if(pitifulDeath && !canISee(previousLocation))
		{
			playerIsDead = true;
			fatality = 1;
		}
		else
		{
			output = new String("It is now pitch dark. If you proceed you will likely "
					+ "fall into a pit.");
		}
		return output;
	}
	
	private void setLocation(Locations newLocation)
	{
		//oldestLocation = previousLocation;
		previousLocation = currentLocation;
		currentLocation = newLocation;
	}

	private String getDescription(Locations here, int brief)
	{
		String output = AdventMain.places.getDescription(here, brief);
		output = output + listItemsHere(currentLocation);
		return output;
	}
	
	private String nonsense()
	{
		String output = null;
		increaseTurns = false;
		double randomOutput = AdventMain.generate();
		if(randomOutput < .34)
		{	output = "I have no idea what you are talking about!";	}
		else if(randomOutput < .67)
		{	output = "I don't understand that!";	}
		else
		{	output = "You're not making any sense!";	}
		return output;
	}

	public boolean canISee(Locations here)
	{
		return (currentLocation.dontNeedLamp(here)) || (lampIsLit && objectIsPresent(GameObjects.LAMP));
	}
	
	private int getCurrentScore()
	{
		int currentScore = (2 + (2 * (tally)) + (deaths * 10));
		
		for(GameObjects item : GameObjects.values())
		{
			if(item.location == Locations.BUILDING && GameObjects.isTreasure(item) && !(item == GameObjects.VASE && vaseIsBroken))
			{ currentScore += ( GameObjects.isLesserTreasure(item) ? 10 : ( item == GameObjects.CHEST ? 12 : 14 ) ); }
		}
		if(GameObjects.MAG.location == Locations.WITT){ currentScore++; }
		for(Hints hint : Hints.values()) { if(hint.given) { currentScore -= hint.cost; } }
		if(wellInCave){	currentScore += 25;	}
		if(caveIsClosed){ currentScore += 25; }
		if(!quit){ currentScore += 4; }
		currentScore += bonus;
		score = currentScore;
		return currentScore;
	}

	private boolean fallInPit()
	{
		boolean pitifulDeath = false;
		double chance = AdventMain.generate();
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
			output = "\nThe resulting ruckus has awakened the Dwarves.\nThere are now several threatening "
					+ "little Dwarves in the room with you! Most of them throw knives at you! All of them get you!";
		}
		if(isInHand(GameObjects.LAMP))
		{
			lampIsLit = false;
			AdventMain.places.placeObject(GameObjects.LAMP, Locations.ROAD);
		}
		attemptAction(ActionWords.DROP, GameObjects.ALL, "");
		currentLocation = Locations.BUILDING;
		previousLocation = Locations.BUILDING;
		fatality = 0;
		if(caveIsClosing)
		{
			output += "\n\nIt looks as though you you're dead. Well, seeing as how it's so close to closing "
					+ "time anyway, let's just call it a day.";
			over = true;
		}
		else
		{
			questionAsked = Questions.RESURRECT;
			currentLocation = Locations.BUILDING;
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
		textFieldEditable = false;
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
			if(!found && score <= AdventMain.scores[i])
			{
				found = true;
				output += AdventMain.sMessages[i] + "\n\tTo achieve the next higher rating";
				if(i < 8)
				{	
					int next = 1 + AdventMain.scores[i] - score;
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
		//output += "\n\n\n\tWould you like to play again?";
		//quest = 9;
		//seriousQuestion = true;
		return output;
	}
	
	private String lamp(String output)
	{
		if(clock2 == 0)
		{
			output = "The sepulchral voice intones, \n\t\"The cave is now closed.\"\nAs the echoes fade, "
					+ "there is a blinding flash of light (and a small puff of orange smoke)..."
					+ "\nThen your eyes refocus: you look around and find...\n";
			caveIsClosed = true;
			bonus = 10;
			attemptAction(ActionWords.DROP, GameObjects.ALL, "");
			attemptAction(ActionWords.OFF, GameObjects.NOTHING, "");
			AdventMain.places.placeObject(GameObjects.BOTTLE, Locations.NEEND);
			AdventMain.places.placeObject(GameObjects.PLANT, Locations.NEEND);
			AdventMain.places.placeObject(GameObjects.OYSTER, Locations.NEEND);
			AdventMain.places.placeObject(GameObjects.LAMP, Locations.NEEND);
			AdventMain.places.placeObject(GameObjects.ROD, Locations.NEEND);
			AdventMain.places.placeObject(GameObjects.DWARF, Locations.NEEND);
			AdventMain.places.placeObject(GameObjects.MIRROR, Locations.NEEND);
			currentLocation = Locations.NEEND;
			previousLocation = Locations.NEEND;
			AdventMain.places.placeObject(GameObjects.GRATE, Locations.SWEND);
			AdventMain.places.placeObject(GameObjects.SNAKE, Locations.SWEND);
			AdventMain.places.placeObject(GameObjects.BIRD, Locations.SWEND);
			AdventMain.places.placeObject(GameObjects.CAGE, Locations.SWEND);
			AdventMain.places.placeObject(GameObjects.ROD2, Locations.SWEND);
			AdventMain.places.placeObject(GameObjects.PILLOW, Locations.SWEND);
			AdventMain.places.placeObject(GameObjects.MIRROR, Locations.SWEND);
			endGameObjectsStates = new boolean[] { true, true, true, true, true, true, true, true, true, true };
			stateOfThePlant = 3;
			output += AdventMain.places.getDescription(currentLocation, brief);
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
			AdventMain.places.placeObject(GameObjects.TROLL2_, Locations.NESIDE);
			AdventMain.places.placeObject(GameObjects.TROLL2, Locations.SWSIDE);
			if(stateOfTheBear != 3)
			{	voidObject(GameObjects.BEAR);	}
			grateIsUnlocked = false;
			caveIsClosing = true;
		}
		else if(tally == 15 && !(currentLocation.outsideCave(currentLocation)) && !(currentLocation == Locations.Y2))
		{	clock1--;	}
		else if(lampIsLit && !(caveIsClosing || caveIsClosed))
		{
			if(lamp < 0)
			{
				output = output + "\nYour lamp has run out of power.";
				if(!canISee(currentLocation))
				{	output = output + "\nIt is now pitch dark. If you proceed you will likely fall into a pit.";	}
				lampIsLit = false;
			}
			else if(lamp < 0 && currentLocation.outside(currentLocation))
			{
				output += "\n\nThere's not much point in wandering around out here, and you can't explore the "
						+ "gave without a lamp. So let's just call it a day.";
				over = true;
			}
			else if(lamp < 31 && stateOfSpareBatteries == 1 && objectIsPresent(GameObjects.BATTERIES) 
					&& objectIsPresent(GameObjects.LAMP))
			{
				output += "\n\nYour lamp is getting dim. I'm taking the liberty of replacing the batteries.";
				AdventMain.places.placeObject(GameObjects.BATTERIES, currentLocation);
				stateOfSpareBatteries = 2;
				lamp = 2500;
			}
			else if(lamp < 31 && !lampLowBatteryWarning && objectIsPresent(GameObjects.LAMP))
			{
				String dim = "\n\nYour lamp is getting dim";
				if(stateOfSpareBatteries == 2)
				{	output += dim + ", and you're out of spare batteries. You'd best start wrapping this up.";	}
				else if(stateOfSpareBatteries == 1)
				{	output += dim + ". You'd best go back for those batteries.";	}
				else
				{	
					output += dim + ". You'd best start wrapping this up, unless you can find some fresh "
						+ "batteries. I seem to recall that there's a vending machine in the maze."
						+ " Bring some coins with you.";	
				}
				lampLowBatteryWarning = true;
			}
			else
			{	lamp--;	}
		}
		return output;
	}
	
	private void takeObject(GameObjects thing)
	{
		AdventMain.places.takeObject(thing);
		if(thing != GameObjects.BIRD){	itemsInHand++;	}
	}

	public boolean isInHand(GameObjects thing)
	{	return thing.location == Locations.INHAND;	}
	
	private boolean objectIsHere(GameObjects thing)
	{	return thing.location == currentLocation;	}
	
	public boolean objectIsPresent(GameObjects thing)
	{
		return objectIsHere(thing) || isInHand(thing);
	}
	
	//TODO evaluate all getting and dropping stuff
	private void dropObject(GameObjects thing)
	{
		AdventMain.places.placeObject(thing, currentLocation);
		if(thing != GameObjects.BIRD){	itemsInHand--;	}
	}
	
	private void voidObject(GameObjects thing)
	{ AdventMain.places.voidObject(thing); }
	
	public void relocate()
	{ relocate = true; }
	
	public int getScore()
	{ return (questionAsked == Questions.INSTRUCTIONS ? 0 : score); }
	
	public void collapse()
	{
		justCollapsed = true;
		collapse = true;
		stateOfTheBear = 3;
	}
	
	public void setTroll()
	{ stateOfTheTroll = 1; }
	
	public boolean haveIFound(GameObjects thing)
	{ return found.get(thing); }
	
	public void wasFound(GameObjects thing)
	{ found.put(thing, true); }

}