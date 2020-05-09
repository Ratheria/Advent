/**
 * @author Ariana Fairbanks
 *
 *	 Instance Data
 *	 Gameplay
 *	 Helper Methods
 */

package controller;

import controller.AdventMain.Locations;
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

	private	boolean dwarvesAllowed = true;

	EnumMap<GameObjects, Boolean> found = new EnumMap<>(GameObjects.class);

	String 		lastInput;
	Locations 	currentLocation, previousLocation, locationAtStartOfAction;
	
	boolean	over			, quit           ,
			relocate		, collapse		 , justCollapsed	  , playerIsDead	  , playerJustDied	 ,
			grateIsUnlocked	, crystalBridge	 , lampIsLit		  , snakeInHotMK	  , doorHasBeenOiled ,
			dragonIsAlive	, birdInCage	 , bearAxe			  , vaseIsBroken	  , goldInInventory	 ,
			caveIsClosing	, caveIsClosed	 , extraMovesForPanic , lowBatteryWarning ,
			battleUpdate	, locationChange , increaseTurns      , wellInCave	      , newDwarf         ;

	ActionWords	actionToAttempt;
	Questions 	questionAsked;
	Hints 		hintToOffer, offeredHint;

	int 	brief, score, bonus, tally, turns, lamp;

	byte 	clock1			, clock2		   , itemsInHand , lives       , lostTreasures ,
			pirate			, movesWOEncounter , deadDwarves , dwarvesLeft ,
			stateOfThePlant	, stateOfTheBottle ,

			fatality				, 	/* default, pit, dwarf, collapse */
			dwarfFlag				,	/* nothing, reached hall no dwarf, met dwarf no knives, knife misses, knife hit .095, .190, .285 */
			dwarfPresent			,   /* none, new, current, old */
			stateOfTheTroll			,   /* there, hidden, dead, can pass */
			stateOfTheBear			,   /* default, fed + idle, fed + following, dead, was following idle */
			stateOfTheChain			,   /* locked to bear, unlocked, locked */
			stateOfSpareBatteries	,	/* default, purchased, used to replace old */
			fooMagicWordProgression	;

	boolean[] 	endGameObjectsStates;	// bottles, lamps, pillows, and rods  -  invisible until interacted with (end game only)

	public AdventGame() { setUp(); }

	//  Setup For New Game  //
	void setUp()
	{
		for(GameObjects object : GameObjects.values())
		{ if(GameObjects.isTreasure(object) && object != GameObjects.RUG_){	found.put(object, false);	} }		// No treasure found yet

		dwarvesAllowed 	= true;
		currentLocation = Locations.ROAD; previousLocation = null; locationAtStartOfAction = Locations.ROAD;
		lastInput 		= AdventMain.Empty;

		over 			= false; 	quit 			= false;
		relocate 		= false; 	collapse 		= false; 	justCollapsed 		= false; 	playerIsDead 		= false; 	playerJustDied 		= false;
		grateIsUnlocked	= false; 	crystalBridge 	= false; 	lampIsLit 			= false; 	snakeInHotMK 		= true; 	doorHasBeenOiled 	= false;
		dragonIsAlive 	= true; 	birdInCage 		= false; 	bearAxe 			= false; 	vaseIsBroken 		= false; 	goldInInventory 	= false;
		caveIsClosing 	= false; 	caveIsClosed 	= false; 	extraMovesForPanic	= false; 	lowBatteryWarning 	= false;
		battleUpdate 	= false; 	locationChange 	= false; 	increaseTurns   	= false; 	wellInCave 			= false;    newDwarf            = false;

		actionToAttempt = ActionWords.NOTHING;
		questionAsked 	= Questions.INSTRUCTIONS;
		hintToOffer 	= Hints.NONE; offeredHint = Hints.INSTRUCTIONS;

		brief = 0; score = 0; bonus = 0; tally = 0; turns = 0; lamp = 330;

		clock1                  = 15; clock2            = 15; itemsInHand     = 0; lives                 = 3; lostTreasures   = 0 ; fatality         = 0 ;
		pirate                  = 0 ; movesWOEncounter  = 1 ; deadDwarves     = 0; dwarvesLeft           = 5; dwarfFlag       = 0 ; dwarfPresent     = 0 ;
		stateOfTheTroll         = 0 ; stateOfTheBear    = 0 ; stateOfTheChain = 0; stateOfSpareBatteries = 0; stateOfThePlant = 0 ; stateOfTheBottle = 1 ;
		fooMagicWordProgression = 0 ;

		endGameObjectsStates = new boolean[] {false, false, false, false, false, false, false, false, false, false};
	}


//  - - - -  Interpret and Act On Input  - - - -  //

	//  Single Word Input  //
	public String determineAndExecuteCommand(String input)
	{
		lastInput = input;
		String output = null;
		increaseTurns = true;
		locationAtStartOfAction = currentLocation;
		int isYesNoAnswer = askYesNo(input);
		
		input = AdventMain.truncate.apply(input);
		
		KnownWord word = AdventMain.KnownWords.getOrDefault(input, GameObjects.NOTHING);
		
		if(actionToAttempt != ActionWords.NOTHING && word instanceof GameObjects)
		{
			output = attemptAction(actionToAttempt, word, input);
			actionToAttempt = ActionWords.NOTHING;
		}
		else if(questionAsked.serious && isYesNoAnswer == 0)
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
			voidObject(GameObjects.DRAGON_); voidObject(GameObjects.RUG_);
			drop(GameObjects.DRAGON); drop(GameObjects.RUG);
		}
		else if(questionAsked != Questions.NONE && isYesNoAnswer > 0)
		{
			switch(questionAsked)
			{
				case INSTRUCTIONS: 
					output = "";
					if(isYesNoAnswer == 1) { output = giveHint(offeredHint); lamp = 1000; }
					output += AdventMain.Locations.getDescription(Locations.ROAD, brief);
					break;
					
				case RESURRECT:
					increaseTurns = false;
					if(isYesNoAnswer == 2) { over = true; }
					else { output = AdventMain.RES_MESSAGE[lives] + (lives > 0 ? AdventMain.Locations.getDescription(currentLocation, brief) + AdventMain.GameObjects.listItemsHere(currentLocation) : ""); }
					break;
					
				case QUIT: case SCOREQUIT:
					if(isYesNoAnswer == 1) { quit = true; over = true; }
					else { output = AdventMain.OKAY; }
					break;
					
				case READBLASTHINT:
					if(isYesNoAnswer == 1) { output = giveHint(offeredHint); Hints.BLAST.proc = 1; }
					else { output = AdventMain.OKAY; }
					break;
					
				default: System.out.println("reached default in question switch"); break;
			}			
			resetHintsAndQuestions();
		}
		else if(hintToOffer != Hints.NONE && isYesNoAnswer > 0)
		{ output = ( isYesNoAnswer == 1 ? offerHint() : AdventMain.OKAY); }
		else if(offeredHint != Hints.NONE && isYesNoAnswer > 0)
		{ output = ( isYesNoAnswer == 1 ? giveHint(offeredHint) : AdventMain.OKAY); }
		else if(word == ActionWords.FEEFIE && ((fooMagicWordProgression > 0 || input.equals("fee"))))
		{ output = attemptAction((ActionWords) word, GameObjects.NOTHING, input); }
		else
		{
			fooMagicWordProgression = 0;
			resetHintsAndQuestions();
			
			if(word instanceof Movement)
			{ output = attemptMovement((Movement) word); }
			else if(word instanceof ActionWords)
			{ output = attemptAction((ActionWords) word, GameObjects.NOTHING, ""); }
			else if(word instanceof GameObjects && word != GameObjects.NOTHING)
			{
				if(objectIsPresent((GameObjects) word))
				{ output = word == GameObjects.KNIFE ? "The dwarves' knives vanish as they strike the walls of the cave." : "What would you like to do with the " + lastInput + "?" ; }
				else if (word == GameObjects.WATER || word == GameObjects.OIL)
				{ output = attemptAction(ActionWords.POUR, word, ""); }
				else
				{ output = "I don't see any " + input + "."; }
				increaseTurns = false;
			}
			else if(word instanceof MessageWords)
			{ output = ((MessageWords) word).message; }
			else
			{ output = nonsense(); }
		}
		if(input.equals("west")){ Hints.WEST.proc++; }
		return finishAction(output);
	}

	//  Double Word Input  //
	public String determineAndExecuteCommand(String input1, String input2)
	{
		lastInput = input1 + " " + input2;
		String output = null;
		increaseTurns = true;
		locationAtStartOfAction = currentLocation;
		
		if(questionAsked.serious)
		{ output = "Just yes or no, please."; increaseTurns = false; }
		else
		{
			fooMagicWordProgression = 0;
			resetHintsAndQuestions();

			String input1Truncated = AdventMain.truncate.apply(input1), input2Truncated = AdventMain.truncate.apply(input2);

			KnownWord word  = AdventMain.KnownWords.getOrDefault(input1Truncated, GameObjects.NOTHING),
					  word2 = AdventMain.KnownWords.getOrDefault(input2Truncated, GameObjects.NOTHING);

			if(word != GameObjects.NOTHING && word2 != GameObjects.NOTHING)
			{
				if (objectIsPresent(GameObjects.KNIFE) && (word == GameObjects.KNIFE || word2 == GameObjects.KNIFE))
				{ output = "The dwarves' knives vanish as they strike the walls of the cave."; increaseTurns = false; }
				else if (word instanceof MessageWords)
				{ output = ((MessageWords) word).message; }
				else if (word == Movement.ENTER )
				{ output = attemptAction(ActionWords.GO, word2, word2 == Movement.STREAM ? "enter stream" : input2Truncated); }
				else if (word instanceof Movement)
				{ output = attemptMovement((Movement) word); }
				else if ((word == GameObjects.WATER || word == GameObjects.OIL) && word2 instanceof GameObjects)
				{ output = attemptAction(ActionWords.POUR, word, ""); }
				else if (word instanceof ActionWords)
				{ output = attemptAction((ActionWords) word, word2, input2); }
				else if (word2 == Movement.ENTER )
				{ output = attemptAction(ActionWords.GO, word, word == Movement.STREAM ? "enter stream" : input1Truncated); }
				else if (word2 instanceof Movement)
				{ output = attemptMovement((Movement) word2); }
				else if (word2 instanceof ActionWords)
				{ output = attemptAction((ActionWords) word2, word, input1); }
			}
			else
			{ output = nonsense(); }
		}
		if(input1.equals("west") || input2.equals("west")){ Hints.WEST.proc++; }
		return finishAction(output);
	}

	//  Finish Any Action Taken  //
	private String finishAction(String output)
	{
		if(!wellInCave && currentLocation.ordinal() >= Locations.minLoc())
		{ wellInCave = true; dwarfFlag = 1; }

		if(locationChange && increaseTurns)
		{
			output = clocksAndLamp(output);
			locationChange = false;
			if(pirate == 1)
			{
				pirate = 2;
				place(GameObjects.MESSAGE, Locations.PONY); place(GameObjects.CHEST, Locations.DEAD2);

				ArrayList<GameObjects> currentlyHolding = AdventMain.GameObjects.objectsHere(Locations.INHAND);
				boolean holdingTreasure = false;
				for(GameObjects object : currentlyHolding)
				{
					if(GameObjects.isTreasure(object))
					{
						holdingTreasure = true;
						place(object, Locations.DEAD2);
					}
				}
				output += holdingTreasure
				? "\n\nOut from the shadows behind you pounces a bearded pirate!\n\"Har, har,\" he chortles, \"I'll just take all this booty and hide it away with me chest deep in the maze!"
				+ "\"\nHe snatches your treasure and vanishes into the gloom."
				: "\n\nThere are faint rustling noises from the darkness behind you. As you turn toward them, the beam of your lamp falls across a bearded pirate. He is carrying a large chest."
				+ "\n\"Shiver me timbers!\" he cries, \"I've been spotted! I'd best hie meself off to the maze to hide me chest!\"\nWith that, he vanishes into the gloom." ;
			}
		}
		if(increaseTurns)
		{	
			turns++;
			if(dwarfPresent > 0)
			{
				if(GameObjects.DWARF.location == previousLocation && Locations.critters(currentLocation))
				{ drop(GameObjects.DWARF); }
				if(GameObjects.DWARF.location == currentLocation)
				{
					if(dwarfFlag == 1)
					{
						dwarfFlag    = 2;
						dwarfPresent = 0;
						dwarvesLeft -= (Math.floor(AdventMain.generate() * 3));
						output += "\n\nA little dwarf just walked around a corner, saw you, threw a little axe at you, cursed, and ran away. (The axe missed.)";
						drop(GameObjects.AXE);
						voidObject(GameObjects.DWARF);
					}
					else
					{
						output += "\n\nThere ";
						output += (dwarfPresent == 1 ? "is a threatening little dwarf" : "are " + dwarfPresent + " threatening little dwarves");
						output += " in the room with you!";
						drop(GameObjects.DWARF);
						if(battleUpdate || !locationChange)
						{
							var thrown = dwarfPresent - (newDwarf ? 1 : 0);
							if(thrown > 0)
							{
								output += "\n";
								output += (thrown == 1 ? "One sharp nasty knife is thrown" : (thrown == dwarfPresent ? "All" : thrown) + " of them throw knives");
								output += " at you!";
								int hit = 0;
								drop(GameObjects.KNIFE);
								if(dwarfFlag >= 3)
								{
									for(var d = 0 ; d < dwarfPresent ; d++)
									{ if((AdventMain.generate() * 1000) < (95 * (dwarfFlag - 2))){ hit++; } }
								}
								else
								{ dwarfFlag++; }

								if(hit > 0)
								{ playerIsDead = true; playerJustDied = true; }

								output += "\n";

								if(dwarfPresent == 1)
								{ output += (hit > 0 ? "It gets you!" : "It misses!"); }
								else
								{
									output += (hit > 0 ? (hit == 1 ? "One of them gets" : dwarfPresent + " of them get") : "None of them hit");
									output +=  " you!";
								}
							}
						}
					}
				}
			}
		}
		battleUpdate = false;
		newDwarf = false;

		if(15 - tally == lostTreasures && lamp > 35)
		{ lamp = 35; }
		getCurrentScore();

		if(playerIsDead && playerJustDied && !over)
		{ output += death(output); playerJustDied = false; }
		output = over ? quit(output) : output + checkForHints() ;

		goldInInventory = isInHand(GameObjects.GOLD);
        if(locationAtStartOfAction != currentLocation){ voidObject(GameObjects.KNIFE); }
		AdventMain.logGameInfo();
		return output;
	}


//  - - - -  Attempt Command Execution  - - - -  //

	private String attemptAction(ActionWords verb, KnownWord other, String alt)
	{
		String output = "I'm game. Would you care to explain how?";
		if(other == null)
		{
			output = "I don't see any " + alt + ".";
			increaseTurns = false;
		}
		if(verb == ActionWords.GO)
		{
			if(alt.equals(""))
			{ output = "Where?"; increaseTurns = false; }
			else if(other == GameObjects.WATER || (other == Movement.STREAM && alt.equals("enter stream")) )
			{
				increaseTurns = currentLocation.hasWater;
				output = (increaseTurns ? "Your feet are now wet." : "I don't see any water.");
			}
			else
			{ output = attemptMovement(alt); }
		}
		else if(other instanceof GameObjects)
		{
			GameObjects object = (GameObjects) other;
			switch(verb)
			{
				case RELAX: case NOTHING: case ABSTAIN:
					output = AdventMain.OKAY;
					break;
					
				case TAKE:
					output = "You can't be serious!";
					if(endGameObjectsStates[0] && object == GameObjects.BOTTLE	) { endGameObjectsStates[0] = false; }
					if(endGameObjectsStates[1] && object == GameObjects.LAMP	) { endGameObjectsStates[1] = false; }
					if(endGameObjectsStates[2] && object == GameObjects.PILLOW	) { endGameObjectsStates[2] = false; }
					if(endGameObjectsStates[8] && object == GameObjects.ROD		) { endGameObjectsStates[8] = false; }
					
					if(object == GameObjects.ROD && !objectIsHere(GameObjects.ROD) && objectIsHere(GameObjects.ROD2))
					{
						output = attemptAction(ActionWords.TAKE, GameObjects.ROD2, alt);
						if(endGameObjectsStates[9]) { endGameObjectsStates[9] = false; }
					}
					else if(object == GameObjects.ALL)
					{
						output = "";
						ArrayList<GameObjects> itemsHere = AdventMain.GameObjects.objectsHere(currentLocation);
						if(!itemsHere.isEmpty())
						{
							for(GameObjects item : itemsHere)
							{
								increaseTurns = !alt.equals("SYSTEM");
								output += attemptAction(ActionWords.TAKE, item, "") + "\n";
								if(increaseTurns) { turns++; }
							}
						}
						else
						{ output = "There is nothing here to take."; }
						increaseTurns = false;
					}
					else if(object == GameObjects.WATER)
					{
						if(GameObjects.BOTTLE.location == Locations.INHAND)
						{
							if(stateOfTheBottle == 0)
							{
								if(currentLocation.hasWater)
								{ output = "You fill the bottle with water."; stateOfTheBottle = 1; }
								else
								{ output = "I don't see any water."; increaseTurns = false; }
							}
							else
							{ output = "Your bottle is already full."; increaseTurns = false; }
						}
						else if(objectIsHere(GameObjects.BOTTLE) && stateOfTheBottle == 1)
						{ output = attemptAction(verb, GameObjects.BOTTLE, alt); }
						else
						{ output = "You have nothing in which to carry it."; increaseTurns = false; }
					}
					else if(object == GameObjects.OIL)
					{
						if(GameObjects.BOTTLE.location == Locations.INHAND)
						{
							if(stateOfTheBottle == 0)
							{
								if(Locations.EASTPIT == currentLocation)
								{ output = "You fill the bottle with oil."; stateOfTheBottle = 2; }
								else
								{ output = "I don't see any oil."; increaseTurns = false; }
							}
							else
							{ output = "Your bottle is already full."; increaseTurns = false; }
						}
						else if(objectIsHere(GameObjects.BOTTLE) && stateOfTheBottle == 2)
						{ output = attemptAction(verb, GameObjects.BOTTLE, alt); }
						else
						{ output = "You have nothing in which to carry it."; increaseTurns = false; }
					}
					else if(object == GameObjects.NOTHING && alt.equals(""))
					{ output = "What would you like to take?"; actionToAttempt = verb; increaseTurns = false; }
					else if(objectIsPresent(object))
					{
						if(object.location == Locations.INHAND)
						{ output = "You are already carrying it!"; increaseTurns = false; }
						else if(object == GameObjects.KNIFE)
						{ output = "The dwarves' knives vanish as they strike the walls of the cave."; increaseTurns = false; }
						else if(object == GameObjects.PLANT && objectIsHere(object))
						{ output = "The plant has exceptionally deep roots and cannot be pulled free."; }
						else if(object == GameObjects.BEAR && stateOfTheBear == 3)
						{ output = "You can't be serious!"; increaseTurns = false; }
						else if(object == GameObjects.BEAR)
						{
							if(stateOfTheChain == 0)
							{ output = "The bear is still chained to the wall."; increaseTurns = false; }
							else
							{ stateOfTheBear = 2; output = "The bear is now following you."; }
						}
						else if(object == GameObjects.CHAIN && stateOfTheChain != 1)
						{ output = "The chain is still locked."; increaseTurns = false; }
						else if(caveIsClosed && (object == GameObjects.BIRD || object == GameObjects.CAGE))
						{ output = "Oh, leave the poor unhappy bird alone."; }
						else if(itemsInHand >= 7)
						{ output = "You can't carry anything more. You'll have to drop something first."; increaseTurns = false; }
						else if(object == GameObjects.BIRD && objectIsHere(GameObjects.BIRD))
						{
							if(isInHand(GameObjects.ROD) && !birdInCage)
							{
								output = "The bird was unafraid when you entered, but as you approach it becomes disturbed and you cannot catch it.";
								if(Hints.BIRD.proc < Hints.BIRD.threshold) { Hints.BIRD.proc++; }
								increaseTurns = false;
							}
							else if(!isInHand(GameObjects.CAGE))
							{ output = "You can catch the bird, but you cannot carry it."; increaseTurns = false; }
							else
							{
								birdInCage = true;
								take(GameObjects.BIRD); take(GameObjects.CAGE);
								output = AdventMain.OKAY;
							}
						}
						else if(object == GameObjects.RUG || object == GameObjects.RUG_)
						{
							if(objectIsHere(object))
							{
								take(GameObjects.RUG);
								voidObject(GameObjects.RUG_);
								output = AdventMain.OKAY;
							}
						}
						else if(object == GameObjects.AXE && bearAxe && stateOfTheBear == 0)
						{ output = "There is no way past the bear to get the axe, which is probably just as well."; }
						else if(object == GameObjects.VASE && vaseIsBroken)
						{ output = "You can't be serious!"; }
						else if(object.mobile && objectIsHere(object))
						{
							take(object);
							output = AdventMain.OKAY;
						}
					}
					else
					{
						if(!(caveIsClosed && object == GameObjects.MIRROR))
						{ output = "I don't see any " + alt + "."; }
						increaseTurns = false;
					}
					if(caveIsClosed && object == GameObjects.OYSTER && endGameObjectsStates[3]) 
					{
						endGameObjectsStates[3] = false;
						output = "Interesting. There seems to be something written on the under-side of the oyster.";
					}
				break;
					
				case DROP:
					output = "";
					if(isInHand(GameObjects.ROD2) && object == GameObjects.ROD && !isInHand(GameObjects.ROD))
					{ drop(GameObjects.ROD2); output = AdventMain.OKAY; }
					else if(object == GameObjects.ALL)
					{
						ArrayList<GameObjects> itemsHere = AdventMain.GameObjects.objectsHere(Locations.INHAND);
						if(!itemsHere.isEmpty())
						{
							output = "";
							for(GameObjects item : itemsHere)
							{
							    increaseTurns = (!alt.equals("SYSTEM"));
								output += attemptAction(ActionWords.DROP, item, "") + "\n";
								if(increaseTurns){ turns++; }
							}
						}
						else
						{ output = "You aren't carrying anything."; }
                        increaseTurns = false;
					}
					else if(object == GameObjects.NOTHING)
					{ output = "What would you like to drop?"; actionToAttempt = verb; increaseTurns = false; }
					else if(object == GameObjects.BEAR)
					{
						if(stateOfTheBear == 2)
						{ output = attemptAction(ActionWords.TOSS, GameObjects.BEAR, alt); }
						else
						{ output = AdventMain.DONT_HAVE; }
					}
					else if(isInHand(object))
					{
						if(object == GameObjects.CAGE && birdInCage)
						{
							drop(GameObjects.CAGE);
							drop(GameObjects.BIRD);
							output = AdventMain.OKAY;
						}
						else if(object == GameObjects.BIRD)
						{
							if(objectIsHere(GameObjects.SNAKE))
							{
								birdInCage = false;
								drop(GameObjects.BIRD);
								voidObject(GameObjects.SNAKE);
								snakeInHotMK = false;
								output = "The little bird attacks the green snake, and in an astounding flurry drives the snake away.";
							}
							else if(objectIsHere(GameObjects.DRAGON) || objectIsHere(GameObjects.DRAGON_))
							{
								output = "The little bird attacks the green dragon, and in an astounding flurry gets burnt to a cinder. The ashes blow away.";
								birdInCage = false; voidObject(GameObjects.BIRD);
							}
							else
							{ output = AdventMain.OKAY; birdInCage = false; drop(GameObjects.BIRD); }
						}
						else if(object == GameObjects.COINS && objectIsHere(GameObjects.PONY))
						{
							voidObject(GameObjects.COINS); lostTreasures++;
							drop(GameObjects.BATTERIES); stateOfSpareBatteries = 1;
						}
						else if(object == GameObjects.VASE && !(objectIsHere(GameObjects.PILLOW) || currentLocation == Locations.SOFT))
						{
							output = "The Ming vase drops with a delicate crash.";
							drop(GameObjects.VASE); vaseIsBroken = true; lostTreasures++;
						}
						else
						{ drop(object); output = AdventMain.OKAY; }
					}
					else
					{ output = AdventMain.DONT_HAVE; increaseTurns = false; }
					break;
					
				case OPEN:
					if(object == GameObjects.GRATE)
					{
						if(!objectIsPresent(GameObjects.GRATE) && !objectIsPresent(GameObjects.GRATE_))
						{ output = "I don't see any grate"; increaseTurns = false; }
						else if(objectIsPresent(GameObjects.KEYS))
						{ output = "The grate is now unlocked."; grateIsUnlocked = true; }
						else
						{ output = "You don't have any keys!"; }
					}
					else if(objectIsPresent(object))
					{
						if(object == GameObjects.CLAM)
						{
							if(isInHand(GameObjects.CLAM))
							{ output = "I advise you to put down the clam before opening it. >STRAIN!<"; increaseTurns = false; }
							else if(!isInHand(GameObjects.TRIDENT))
							{ output = "You don't have anything strong enough to open the clam."; increaseTurns = false; }
							else
							{
								voidObject(GameObjects.CLAM); drop(GameObjects.OYSTER);
								output = "A glistening pearl falls out of the clam and rolls away. Goodness, this must really be an oyster! (I never was very good at identifying bivalves.)\nWhatever it is, it has now snapped shut again.";
								place(GameObjects.PEARL, Locations.CULDESAC);
							}
						}
						else if(object == GameObjects.OYSTER)
						{
							if(isInHand(GameObjects.OYSTER))
							{ output = "I advise you to put down the oyster before opening it. >WRENCH!<"; increaseTurns = false; }
							else if(!isInHand(GameObjects.TRIDENT))
							{ output = "You don't have anything strong enough to open the oyster."; increaseTurns = false; }
							else
							{ output = "The oyster creaks open, revealing nothing but oyster inside. It promptly snaps shut again."; }
						}
						else if(object == GameObjects.DOOR)
						{
							if(doorHasBeenOiled)
							{ output = AdventMain.OKAY; }
							else
							{ output = "The door is extremely rusty and refuses to open."; increaseTurns = false; }
						}
						else if(object == GameObjects.CAGE)
						{ output = "It has no lock."; increaseTurns = false; }
						else if(object == GameObjects.KEYS)
						{ output = "You can't unlock the keys."; increaseTurns = false; }
						else if(object == GameObjects.CHAIN)
						{
							if(!objectIsPresent(GameObjects.KEYS))
							{ output = "You have no keys!"; increaseTurns = false; }
							else if(stateOfTheChain == 0)
							{
								if(stateOfTheBear != 1)
								{ output = "There is no way to get past the bear to unlock the chain, which is probably just as well."; increaseTurns = false; }
								else
								{ output = "You unlock the chain and set the tame bear free."; stateOfTheChain = 1; }
							}
							else if(stateOfTheChain == 2)
							{ output = "The chain is now unlocked."; stateOfTheChain = 1; }
							else
							{ output = "It was already unlocked."; increaseTurns = false; }
						}
					}
					else if(object == GameObjects.NOTHING)
					{ output = "What would you like to open?"; actionToAttempt = verb; increaseTurns = false; }
					else
					{ output = "I don't see any " + alt + "."; increaseTurns = false; }
					break;
									
				case CLOSE:
					if(object == GameObjects.GRATE || object == GameObjects.GRATE_)
					{
						if(!objectIsPresent(GameObjects.KEYS))
						{ output = "You don't have any keys!"; increaseTurns = false; }
						else if(!grateIsUnlocked)
						{ output = "It was already locked."; increaseTurns = false; }
						else
						{ output = "The grate is now locked."; grateIsUnlocked = false; }
					}
					else if(objectIsPresent(object))
					{
						if(object == GameObjects.CLAM || object == GameObjects.OYSTER)
						{ output = "What?"; increaseTurns = false; }
						else if(object == GameObjects.CAGE)
						{ output = "It has no lock."; increaseTurns = false; }
						else if(object == GameObjects.KEYS)
						{ output = "You can't lock the keys."; increaseTurns = false; }
						else if(object == GameObjects.CHAIN)
						{
							if(!objectIsPresent(GameObjects.KEYS))
							{ output = "You have no keys!"; increaseTurns = false; }
							else if(stateOfTheChain != 1)
							{ output = "It was already locked."; increaseTurns = false; }
							else
							{
								if(!(currentLocation == Locations.BARR))
								{ output = "There is nothing here to which the chain can be locked."; increaseTurns = false; }
								else
								{ output = "The chain is now locked."; stateOfTheChain = 2; }
							}
						}
					}
					else
					{ output = "I don't know how to lock or unlock such a thing."; increaseTurns = false; }
					break;
					
				case ON:
					if(objectIsPresent(GameObjects.LAMP))
					{
						if(lamp > 0)
						{
							if(canISee(currentLocation))
							{ output = "Your lamp is now on."; }
							else
							{ output = "Your lamp is now on.\n\n" + getDescription(currentLocation, brief); }
							lampIsLit = true;
						}
						else
						{ output = "Your lamp has run out of power."; increaseTurns = false; }
					}
					else if(object == GameObjects.NOTHING || objectIsPresent(object))
					{ output = "You have no source of light."; increaseTurns = false; }
					else
					{ output = "I don't see any " + alt + "."; increaseTurns = false; }
					break;
					
				case OFF: 
					if(objectIsPresent(GameObjects.LAMP))
					{
						lampIsLit = false;
						output = "Your lamp is now off.";
						if(!canISee(currentLocation))
						{ output += "\n\nIt is now pitch dark. If you proceed you will likely fall into a pit."; }
					}
					break;
					
				case WAVE:
					if(object == GameObjects.NOTHING)
					{ output = "What would you like to wave?"; actionToAttempt = verb; increaseTurns = false; }
					else if (!isInHand(object) && (object != GameObjects.ROD || !isInHand(GameObjects.ROD2)))
					{ output = AdventMain.DONT_HAVE; increaseTurns = false; }
					else if(object != GameObjects.ROD || caveIsClosed)
					{
						if(!isInHand(object))
						{ output = "I don't see any " + alt + "."; }
						else
						{ output = AdventMain.NOTHING; }
						increaseTurns = false;
					}
					else if((currentLocation != Locations.EASTFISSURE && currentLocation != Locations.WESTFISSURE))
					{ output = AdventMain.NOTHING; increaseTurns = false; }
					else
					{
						if(!caveIsClosing)
						{
							if(!crystalBridge)
							{
								output = "A crystal bridge now spans the fissure.";
								place(GameObjects.CRYSTAL, Locations.EASTFISSURE); place(GameObjects.CRYSTAL_, Locations.WESTFISSURE);
								crystalBridge = true;
							}
							else
							{
								output = "The crystal bridge has vanished!";
								voidObject(GameObjects.CRYSTAL); voidObject(GameObjects.CRYSTAL_);
								crystalBridge = false;
							}
						}
						else
						{ output = AdventMain.NOTHING; increaseTurns = false; }
					}
					break;

				case POUR:
					if(object == GameObjects.NOTHING || object == GameObjects.BOTTLE)
					{
						if(stateOfTheBottle == 1) { object = GameObjects.WATER; }
						else if(stateOfTheBottle == 2) { object = GameObjects.OIL; }
						else { object = GameObjects.NOTHING; }
					}
					if(object == GameObjects.NOTHING)
					{ output = "You can't pour that."; increaseTurns = false; }
					else if(!isInHand(GameObjects.BOTTLE))
					{ output = "You have nothing to pour."; increaseTurns = false; }
					else if(object == GameObjects.WATER && stateOfTheBottle == 1)
					{
						if(currentLocation == Locations.WESTPIT)
						{
							stateOfThePlant++;
							if(stateOfThePlant == 1)
							{ output = "The plant spurts into furious growth for a few seconds.\n\n\tThere is a 12-foot-tall beanstalk stretching up out of the pit, bellowing \"Water!! Water!!\""; }
							else if(stateOfThePlant == 2)
							{ output = "The plant grows explosively, almost filling the bottom of the pit.\n\n\tThere is a gigantic beanstalk stretching all the way up to the hole."; }
							else if(stateOfThePlant == 3)
							{
								output = "You've over-watered the plant! It's shriveling up! It's, It's...";
								voidObject(GameObjects.PLANT); voidObject(GameObjects.PLANT2); voidObject(GameObjects.PLANT2_);
							}
							else
							{ output = "Your bottle is empty and the ground is wet."; }
						}
						else if(objectIsPresent(GameObjects.DOOR))
						{ output = "The hinges are quite thoroughly rusted now and won't budge."; doorHasBeenOiled = false; }
						else
						{ output = "Your bottle is empty and the ground is wet."; }
						stateOfTheBottle = 0;
					}
					else if(object == GameObjects.OIL && stateOfTheBottle == 2)
					{
						if(currentLocation == Locations.WESTPIT)
						{
							if(stateOfThePlant == 1)
							{ output = "The plant indignantly shakes the oil off its leaves and asks: \"Water?\"."; }
							else
							{ output = "Your bottle is empty and the ground is wet."; }
						}
						else if(objectIsPresent(GameObjects.DOOR))
						{ output = "The oil has freed up the hinges so that the door will now move, although it requires some effort."; doorHasBeenOiled = true; }
						else
						{ output = "Your bottle is empty and the ground is wet."; }
						stateOfTheBottle = 0;
					}	
					break;
					
				case EAT:
					if(object == GameObjects.NOTHING)
					{ output = "What would you like to eat?"; actionToAttempt = verb; increaseTurns = false; }
					else if(object == GameObjects.FOOD)
					{
						if(!objectIsPresent(GameObjects.FOOD))
						{ output = "You don't have any."; }
						else
						{ output = "Thank you, it was delicious!"; voidObject(GameObjects.FOOD); }
					}
					else
					{ output = "I think I just lost my appetite."; }
					break;

				case RUB:
					if(object == GameObjects.LAMP)
					{ output = "Rubbing the electric lamp is not particularly rewarding. Anyway, nothing exciting happens."; }
					else
					{ output = "Peculiar. Nothing unexpected happens."; }
					break;
					
				case TOSS:
					if(object == GameObjects.ROD && isInHand(GameObjects.ROD2) && !(isInHand(GameObjects.ROD)))
					{ output = attemptAction(ActionWords.DROP, GameObjects.ROD, ""); }
					else if(object == GameObjects.NOTHING)
					{ output = "What would you like to throw?"; actionToAttempt = verb; increaseTurns = false; }
					else if(object == GameObjects.BEAR)
					{
						if(stateOfTheBear == 2)
						{
							if(objectIsHere(GameObjects.TROLL)||objectIsHere(GameObjects.TROLL_))
							{
								voidObject(GameObjects.TROLL); voidObject(GameObjects.TROLL_);
								place(GameObjects.TROLL2_, Locations.NESIDE); place(GameObjects.TROLL2, Locations.SWSIDE);
								stateOfTheTroll = 2;
								output = "The bear lumbers toward the troll, who lets out a startled shriek and scurries away. The bear soon gives up pursuit and wanders back.";
							}
							else
							{ output = AdventMain.OKAY; }
							stateOfTheBear = 4;
							GameObjects.BEAR.location = currentLocation;
						}
						else
						{ output = AdventMain.DONT_HAVE; }
					}
					else if(!(isInHand(object)))
					{ output = AdventMain.DONT_HAVE; increaseTurns = false; }
					else if((objectIsHere(GameObjects.TROLL_) || objectIsHere(GameObjects.TROLL)) && GameObjects.isTreasure(object))
					{
						voidObject(object);
						voidObject(GameObjects.TROLL); voidObject(GameObjects.TROLL_);
						place(GameObjects.TROLL2, Locations.SWSIDE); place(GameObjects.TROLL2_, Locations.NESIDE);
						stateOfTheTroll = 3;
						output = "The troll catches your treasure and scurries away out of sight.";
						if(object != GameObjects.EGGS)
						{ lostTreasures++; }
					}
					else if(object == GameObjects.FOOD && objectIsHere(GameObjects.BEAR))
					{ output = attemptAction(ActionWords.FEED, GameObjects.BEAR, ""); }
					else if(!(object == GameObjects.AXE))
					{ output = attemptAction(ActionWords.DROP, object, alt); }
					else if(objectIsHere(GameObjects.DWARF))
					{
						battleUpdate = true;
						if(AdventMain.generate() * 3 > 1)
						{
							deadDwarves++;
							dwarfFlag++;
							dwarfPresent--;
							if (dwarfPresent == 0)
							{ voidObject(GameObjects.DWARF); }
							output = "You killed a little dwarf.";
							if(deadDwarves == 1)
							{ output += " The body vanishes in a cloud of greasy black smoke."; }
						}
						else
						{ output = "You attack a little dwarf, but he dodges out of the way."; }
						drop(GameObjects.AXE);
					}
					else if((objectIsHere(GameObjects.DRAGON_) || objectIsHere(GameObjects.DRAGON)) && dragonIsAlive)
					{ output = "The axe bounces harmlessly off the dragon's thick scales."; drop(GameObjects.AXE); }
					else if((objectIsHere(GameObjects.TROLL_) || objectIsHere(GameObjects.TROLL)))
					{ output = "The troll deftly catches the axe, examines it carefully, and tosses it back, declaring, \"Good workmanship, but it's not valuable enough.\""; }
					else if(objectIsHere(GameObjects.BEAR) && stateOfTheBear == 0)
					{
						bearAxe = true;
						AdventMain.Locations.placeObject(GameObjects.AXE, currentLocation);
						output = "The axe misses and lands near the bear where you can't get at it.";
					}
					else
					{ output = attemptAction(ActionWords.DROP, object, alt); }
					break;

				case BREAK:
					if(object == GameObjects.NOTHING)
					{ output = "You can't be serious!"; increaseTurns = false; }
					else if(object == GameObjects.VASE)
					{
						if(isInHand(GameObjects.VASE))
						{
							output = "You have taken the vase and hurled it delicately to the ground.";
							vaseIsBroken = true;
							lostTreasures++;
							drop(GameObjects.VASE);
						}
					}
					else if(object == GameObjects.MIRROR)
					{
						if(caveIsClosed)
						{
							output = "You strike the mirror a resounding blow, whereupon it shatters into a myriad tiny fragments.";
							playerIsDead = true;
							playerJustDied = true;
						}
						else if(objectIsHere(GameObjects.MIRROR) || objectIsHere(GameObjects.MIRROR_))
						{ output = "It is too far up for you to reach."; increaseTurns = false; }
						else
						{ output = "It is beyond your power to do that."; increaseTurns = false; }
					}
					break;

				case KILL:
					if(object == GameObjects.NOTHING)
					{ output = "What would you like to kill?"; actionToAttempt = verb; increaseTurns = false; }
					else if(object == GameObjects.BIRD && caveIsClosed)
					{
						if(objectIsHere(GameObjects.BIRD))
						{ output = "Oh, leave the poor unhappy bird alone."; }
						else
						{ output = "I don't see any bird."; }
						increaseTurns = false;
					}
					else if(object == GameObjects.BIRD)
					{
						if(objectIsHere(GameObjects.BIRD))
						{ output = "The little bird is now dead. Its body disappears."; voidObject(GameObjects.BIRD); }
						else
						{ output = "I don't see any bird."; increaseTurns = false; }
					} 
					else if(object == GameObjects.CLAM || object == GameObjects.OYSTER)
					{
						if(objectIsPresent(object))
						{ output = "The shell is very strong and impervious to attack."; }
						else
						{ output = "I don't see any " + alt + "."; }
						increaseTurns = false;
					}
					else if(object == GameObjects.SNAKE)
					{
						if(objectIsHere(GameObjects.SNAKE))
						{ output = "Attacking the snake both doesn't work and is very dangerous."; }
						else
						{ output = "I don't see any snake."; }
						increaseTurns = false;
					}
					else if(object == GameObjects.DWARF && caveIsClosed)
					{
						playerIsDead = true;
						playerJustDied = true;
						fatality = 2;
					}
					else if(object == GameObjects.DWARF)
					{
						if(objectIsHere(GameObjects.DWARF))
						{ output = "With what? Your bare hands?"; }
						else
						{ output = "I don't see any dwarf."; }
						increaseTurns = false;
					}
					else if(object == GameObjects.TROLL)
					{
						if(objectIsHere(GameObjects.TROLL)|| objectIsHere(GameObjects.TROLL_))
						{ output = "Trolls are close relatives with the rocks and have skin as tough as that of a rhinoceros. The troll fends off your blows effortlessly."; }
						else
						{ output = "I don't see any troll."; }
						increaseTurns = false;
					}
					else if(object == GameObjects.BEAR)
					{
						if(objectIsHere(GameObjects.BEAR))
						{
							if(stateOfTheBear == 0)
							{ output = "With what? Your bare hands? Against HIS bear hands??"; increaseTurns = false; }
							else if(stateOfTheBear != 3)
							{ output = "The bear is confused; he only wants to be your friend."; }
							else
							{ output = "For crying out loud, the poor thing is already dead!"; increaseTurns = false; }
						}
						else
						{ output = "I don't see any bear."; increaseTurns = false; }
					}
					else if(object == GameObjects.DRAGON)
					{
						if(objectIsPresent(GameObjects.DRAGON) || objectIsPresent(GameObjects.DRAGON_))
						{
							if(!dragonIsAlive){ output = "For crying out loud, the poor thing is already dead!"; }
							else
							{ output = "With what? Your bare hands?"; questionAsked = Questions.DRAGON; }
						}
						else
						{ output = "I don't see any dragon."; }
						increaseTurns = false;
					}
					else 
					{
						if(!objectIsPresent(object))
						{ output = "I don't see any " + alt + "."; increaseTurns = false; }
						else
						{
							if(objectIsHere(GameObjects.DWARF))
							{ attemptAction(ActionWords.TOSS, GameObjects.AXE, "");	}
							else if(objectIsHere(GameObjects.SNAKE))
							{ attemptAction(ActionWords.KILL, GameObjects.SNAKE, ""); }
							else if(objectIsHere(GameObjects.TROLL) || objectIsHere(GameObjects.TROLL_))
							{ attemptAction(ActionWords.KILL, GameObjects.TROLL, ""); }
							else if(objectIsHere(GameObjects.BEAR))
							{ attemptAction(ActionWords.KILL, GameObjects.BEAR, ""); }
							else if(objectIsHere(GameObjects.BIRD))
							{ attemptAction(ActionWords.KILL, GameObjects.BIRD, ""); }
						}
					}
					break;
					
				case SAY:
					if(alt.equals(""))
					{ output = "What do you want to say?"; increaseTurns = false; }
					else if(other == MessageWords.CUSS)
					{ output = MessageWords.CUSS.message; }
					else
					{ output = "Okay, \"" + alt + "\"."; }
					break;
					
				case READ:
					if(!canISee(currentLocation))
					{ output = "You can't read in the dark!"; increaseTurns = false; }
					else if(objectIsPresent(GameObjects.MAG))
					{ output = "I'm afraid the magazine is written in dwarvish."; increaseTurns = false; }
					else if(objectIsPresent(GameObjects.TABLET))
					{	output = "'CONGRATULATIONS ON BRINGING LIGHT INTO THE DARK ROOM!'";	}
					else if(objectIsPresent(GameObjects.MESSAGE))
					{	output = "'This is not the maze where the pirate hides his treasure chest.'";	}
					else if(caveIsClosed && objectIsPresent(GameObjects.OYSTER))
					{
						if(Hints.BIRD.proc > 0)
						{ output = "It says the same thing it did before."; }
						else
						{
							output = "Hmmm, this looks like a clue, which means it'll cost you 10 points to read it. Should I go ahead and read it anyway?";
							questionAsked = Questions.READBLASTHINT;
							offeredHint = Hints.BLAST;
						}
					}
					else
					{ output = "I'm game. Care to explain how?"; }
					break;
					
				case BRIEF:
					if(brief == 0)
					{ output = "Okay, from now on I'll only describe a place in full the first time you come to it. To get the full description, say \"LOOK\"."; brief = 1; }
					else
					{ output = "Okay, I'll return to giving descriptions in the original fashion."; brief = 0; }
					break;
					
				case VERBOSE:
					if(brief == 0)
					{ output = "Okay, from now on I'll describe a place in full every time you come to it."; brief = 2; }
					else
					{ output = "Okay, I'll return to giving descriptions in the original fashion."; brief = 0; }
					break;
					
				case FIND:
					if(isInHand(object))
					{ output = "You are already carrying it!"; increaseTurns = false; }
					else if(objectIsPresent(object))
					{ output = "I believe what you want is right here with you."; }
					else
					{ output = "I can only tell you what you see as you move about and manipulate things. I can not tell you where remote things are."; increaseTurns = false; }
					break;
					
				case INVENTORY:
					increaseTurns = false;
					if(itemsInHand > 0)
					{ output = "\t   -----" + AdventMain.GameObjects.listItemsHere(Locations.INHAND) + "\n\t   -----"; }
					else
					{ output = "\t   -----\n\t\tYou're not carrying anything.\n\t   -----"; }
					if(stateOfTheBear == 2)
					{ output += GameObjects.BEAR.descriptions[2]; }
					break;
					
				case SCORE:
					output = "If you were to quit now, you would score " + getCurrentScore() + " out of a possible 350. \nDo you indeed wish to quit now?";
					questionAsked = Questions.QUIT;
					increaseTurns = false;
					break;
					
				case QUIT: output = "Do you really wish to quit now?"; questionAsked = Questions.QUIT; increaseTurns = false; break;
					
				case FEED:
					output = "There is nothing here it wants to eat (except perhaps you).";
					if(object == GameObjects.TROLL && (objectIsHere(GameObjects.TROLL) || objectIsHere(GameObjects.TROLL_)))
					{ output = "Gluttony is not one of the troll's vices. Avarice, however, is."; }
					else if(object == GameObjects.DRAGON && (objectIsHere(GameObjects.DRAGON) || objectIsHere(GameObjects.DRAGON_)))
					{
						if(!dragonIsAlive)
						{ output = "Don't be ridiculous!"; }
					}
					else if(objectIsHere(object))
					{
						if(object == GameObjects.BIRD)
						{ output = "It's not hungry (it's merely pinin' for the fjords). Besides, you have no bird seed."; }
						else if(object == GameObjects.SNAKE)
						{
							if(!caveIsClosed && objectIsPresent(GameObjects.BIRD))
							{ output = "The snake has now devoured your bird."; voidObject(GameObjects.BIRD); }
						}
						else if(object == GameObjects.BEAR)
						{
							output = "There is nothing here to eat.";
							if(objectIsPresent(GameObjects.FOOD))
							{
								voidObject(GameObjects.FOOD);
								stateOfTheBear = 1;
								bearAxe = false;
								output = "The bear eagerly wolfs down your food, after which he seems to calm down considerably and even becomes rather friendly.";
							}
						}
						else if(object == GameObjects.DWARF)
						{
							output = "There is nothing here to eat.";
							if(isInHand(GameObjects.FOOD))
							{ output = "You fool, dwarves eat only coal! Now you've made him REALLY mad!"; dwarfFlag++; }
						}
						else
						{ output = "I'm game. Would you care to explain how?"; }
					}
					else
					{
						if(object == GameObjects.NOTHING)
						{ output = "What would you like to feed?"; actionToAttempt = verb; }
						else
						{ output = "I don't see any " + alt + "."; }
						increaseTurns = false;
					}
					break;
					
				case WAKE:
					if(caveIsClosed && object == GameObjects.DWARF)
					{
						output = "You wake the nearest dwarf, who wakes up grumpily, takes one look at you, curses, and grabs for his axe.";
						playerIsDead = true;
						playerJustDied = true;
						fatality = 2;
					}
					else
					{ output = "You can't be serious!"; increaseTurns = false; }
					break;
					
				case DRINK:
					boolean waterIsHere = currentLocation.hasWater;
					output = "You have nothing to drink.";
					if(waterIsHere && !(isInHand(GameObjects.BOTTLE) && stateOfTheBottle == 1))
					{ output = "You have taken a drink from the stream. The water tastes strongly of minerals, but is not unpleasant. It is extremely cold."; }
					else if(isInHand(GameObjects.BOTTLE) && stateOfTheBottle == 1)
					{ output = "The bottle of water is now empty."; stateOfTheBottle = 0; }
					else if(!(object == GameObjects.WATER || object == GameObjects.NOTHING))
					{ output = "You can't be serious!"; increaseTurns = false; }
					break;

				case LOOK:
					battleUpdate = true;
					if(!canISee(currentLocation))
					{ output = "You have no source of light."; }
					else if(object == GameObjects.NOTHING)
					{
						output = AdventMain.Locations.getDescription(currentLocation, 2);
						output += "\n" + AdventMain.GameObjects.listItemsHere(currentLocation);
					}
					else
					{ output = "Sorry, but I am not allowed to give more detail. I will repeat the long description of your location.\n\n" + AdventMain.Locations.getDescription(currentLocation, 2); }
					break;
					
				case CALM:
					output = "I'm game. Would you care to explain how?";
					break;

				case FILL:
					boolean liquidHere = (currentLocation.hasWater || currentLocation == Locations.EASTPIT);
					if(object == GameObjects.VASE)
					{
						if(!liquidHere)
						{ output = "There is nothing here with which to fill the vase."; increaseTurns = false; }
						else if(!isInHand(GameObjects.VASE))
						{ output = "You aren't carrying it!"; increaseTurns = false; }
						else
						{
							vaseIsBroken = true;
							drop(GameObjects.VASE);
							output = "The sudden change in temperature has delicately shattered the vase.";
							lostTreasures++;
						}
					}
					else if(!(object == GameObjects.NOTHING || object == GameObjects.BOTTLE))
					{ output = "You can not fill that."; increaseTurns = false; }
					else if(!isInHand(GameObjects.BOTTLE))
					{
						increaseTurns = false;
						if(object == GameObjects.NOTHING)
						{ output = "You have nothing to fill."; }
						else
						{ output = "You are not carrying it!"; }
					}
					else if(stateOfTheBottle != 0)
					{ output = "Your bottle is already full."; increaseTurns = false; }
					else if(!liquidHere)
					{ output = "You have nothing with which to fill the bottle."; increaseTurns = false; }
					else if(currentLocation == Locations.EASTPIT)
					{ output = "You fill the bottle with oil."; stateOfTheBottle = 2; }
					else
					{ output = "You fill the bottle with water."; stateOfTheBottle = 1; }
					break;
					
				case BLAST:
					if(caveIsClosed)
					{
						bonus = (objectIsPresent(GameObjects.ROD2) ? 25 : currentLocation == Locations.NEEND ? 30 : 45);
						switch(bonus)
						{
							case 25:
							    output = "There is a loud explosion, and you are suddenly splashed across the walls of the room.";
							    break;
							case 30:
								output = "There is a loud explosion, and a twenty-foot hole appears in the far wall, burying the snakes in the rubble. "
									   + "A river of molten lava pours in through the hole, destroying everything in its path, including you!";
								break;
							default:
								output = "There is a loud explosion, and a twenty-foot hole appears in the far wall, burying the dwarves in the rubble. "
                                       + "You march through the hole and find yourself in the Main Office, where a cheering band of friendly elves carry the conquering adventurer off into the sunset.";
								break;
						}
						over = true;
					}
					else
					{ output = "Blasting requires dynamite."; }
					break;
					
				case FEEFIE:
					boolean fum = (alt.equals(AdventMain.FEE_FIE_FOE[fooMagicWordProgression]));
					//System.out.println(alt + " " + fum);
					if(fum)
					{
						if(fooMagicWordProgression < 3)
						{ output = AdventMain.OKAY; fooMagicWordProgression++; }
						else
						{
							fooMagicWordProgression = 0;
							if(GameObjects.EGGS.location == Locations.GIANT){ output = AdventMain.NOTHING; }
							else 
							{
								if(currentLocation != Locations.GIANT)
								{
									if(objectIsPresent(GameObjects.EGGS))
									{ output = "The nest of golden eggs disappears!"; }
									else
									{ output = "Done!"; }
								}
								else
								{ output = "There is a large nest here, full of golden eggs!"; }
								place(GameObjects.EGGS, Locations.GIANT);
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
					output = "You broke something.";
					increaseTurns = false;
					break;
			}
		}
		else if(verb == ActionWords.TAKE && other == ActionWords.ABSTAIN)
		{ output = AdventMain.OKAY; }
		else
		{ output = "You can not do that."; increaseTurns = false; }
		return output;
	}
	
	private String attemptMovement(String input)
	{
		input = AdventMain.truncate.apply(input);
		var word = AdventMain.KnownWords.getOrDefault(input, GameObjects.NOTHING);
		if(!(word instanceof Movement))
		{
			increaseTurns = false;
			return "You can not do that.";
		}
		return attemptMovement((Movement) word);
	}
	
	private String attemptMovement(Movement destination)
	{
		String output = "";
		locationChange = true;
		
		Locations locationResult = Locations.moveTo(destination, currentLocation);
		
		if(justCollapsed)
		{
			playerIsDead = true;
			playerJustDied = true;
			justCollapsed = false;
			fatality = 3;
		}
		else
		{
			switch (locationResult)
			{
				case THEVOID:
					if(destination == Movement.XYZZY || destination == Movement.PLOVER || destination == Movement.PLUGH)
					{ output = AdventMain.NOTHING; }
					else if(!canISee(currentLocation))
					{ output = pitchDark(output); increaseTurns = false; }
					else if(destination == Movement.UP || destination == Movement.DOWN
							|| (destination.ordinal() >= Movement.EAST.ordinal() && destination.ordinal() <= Movement.NORTHWEST.ordinal()))
					{ output = "There are no exits in that direction.\n" + getDescription(currentLocation, brief); increaseTurns = false; }
					else
					{ output = "I don't know how to apply that word here.\n"; increaseTurns = false; }
					break;

				case CLIMB :
					setLocation(Locations.NARROW);
					output = "You clamber up the plant and scurry through the hole at the top.\n" + getDescription(currentLocation, brief);
					break;

				case CHECK :
					if(stateOfThePlant == 1)
					{
						setLocation(Locations.WEST2PIT);
						output = "You have climbed up the plant and out of the pit.\n" + getDescription(currentLocation, brief);
					}
					else
					{
						output = "There is nothing here to climb." + (currentLocation == Locations.WESTPIT ? " Use \"UP\" or \"OUT\" to leave the pit." : "");
						increaseTurns = false;
					}
					break;

				case CRACK : output = "That crack is far too small for you to follow."; increaseTurns = false; break;
				case NECK  : output = "You are at the bottom of the pit with a broken neck."; increaseTurns = false; playerIsDead = true; playerJustDied = true; break;
				case LOSE  : output = "You didn't make it."; increaseTurns = false; playerIsDead = true; playerJustDied = true; break;
				case CANT  : output = "The dome is unclimbable."; increaseTurns = false; break;
				case THRU  : setLocation(Locations.WESTMIST); output = AdventMain.HOM_PASSAGE + getDescription(currentLocation, brief); break;
				case DUCK  : setLocation(Locations.WESTFISSURE); output = AdventMain.HOM_PASSAGE + getDescription(currentLocation, brief); break;
				case SEWER : output = "The stream flows out through a pair of 1-foot-diameter sewer pipes.\n\nIt would be advisable to use the exit."; increaseTurns = false; break;

				case REMARK:
					switch (currentLocation)
					{
						case SHELL:
							output = "You can't fit this five-foot " + (isInHand(GameObjects.CLAM) ? "clam" : "oyster") + " through that little passage!";
							increaseTurns = false;
							break;

						case PROOM: case DROOM: case ALCOVE:
							output = "Something you are carrying won't fit through the tunnel with you. You'd best take inventory and drop something.";
							increaseTurns = false;
							break;

						case NESIDE: case SWSIDE: case EASTFISSURE: case WESTFISSURE:
							if(destination.equals(Movement.JUMP))
							{ output = "I respectfully suggest that you go across the bridge instead of jumping."; }
							else if(currentLocation == Locations.NESIDE || currentLocation == Locations.SWSIDE)
							{
								if(stateOfTheTroll == 0){ output = "The troll refuses to let you cross."; }
								else if(stateOfTheTroll == 1)
								{
									output = "The troll steps out from beneath the bridge and blocks your way.";
									stateOfTheTroll = 0;
									voidObject(GameObjects.TROLL2);
									voidObject(GameObjects.TROLL2_);
									place(GameObjects.TROLL, Locations.SWSIDE);
									place(GameObjects.TROLL_, Locations.NESIDE);
								}
								else
								{ output = "There is no longer any way across the chasm."; }
							}
							else
							{ output = "There is no way to cross the fissure."; }
							increaseTurns = false;
							break;

						case WITT: case BEDQUILT: case CHEESE:
							output = "You have crawled around in some little holes and "
							       + (currentLocation == Locations.WITT && destination == Movement.WEST
							       ? "found your way blocked by a recent cave-in.\nYou are now back in the main passage." : "wound up back in the main passage.");
							break;

						case SLIT: case WET: output = "You can't fit through a two-inch slit!"; increaseTurns = false; break;
						case INSIDE: case OUTSIDE: output = "You can't go through a locked steel grate!"; increaseTurns = false; break;
						case HALLOFMOUNTAINKING: output = "You can't get by the snake."; increaseTurns = false;	break;
						case IMMENSE: output = "The door is extremely rusty and refuses to open."; increaseTurns = false; break;
						case SCAN1: case SCAN3: output = "That dragon looks rather nasty. You'd best not try to get by."; increaseTurns = false; break;
						case VIEW: output = "Don't be ridiculous!"; increaseTurns = false; break;
						default: output = "You can not do that."; increaseTurns = false; break;
					}
					break;

				default:
					if(caveIsClosing && Locations.outsideCave(locationResult))
					{
						if(!extraMovesForPanic)
						{ clock2 = 15; extraMovesForPanic = true; }
						increaseTurns = false;
						output = "A mysterious recorded voice groans into life and announces: \n\t\"This exit is closed. Please leave via main office.\"";
					}

					if(dwarfPresent > 0 && Locations.critters(locationResult) && AdventMain.generate() < .20)
					{
						battleUpdate = true;
						locationChange = false;
						output = "A little dwarf with a big knife blocks your way.";
					}
					else
					{
						setLocation(locationResult);
						if(!canISee(currentLocation)){ output = pitchDark(output); }
						else
						{
							//System.out.println("d " + dwarves);
							if(Locations.critters(currentLocation))
							{
								double chance = AdventMain.generate();
								if(pirate == 0)
								{
									movesWOEncounter++;
									double likely = (movesWOEncounter * 10 / 8.0)/8.0;
									if(chance * 100 <= likely){ pirate = 1; }
								}
								if(pirate != 1 && dwarvesAllowed && dwarfFlag > 0 && dwarvesLeft > dwarfPresent)
								{
									chance = AdventMain.generate() * 1000;
									double encounter = ((dwarvesLeft - dwarfPresent) * 1000.00)/50.00;
									System.out.println("left " + dwarvesLeft + " present " + dwarfPresent);
									if(chance <= encounter)
									{
										if(GameObjects.DWARF.location != Locations.THEVOID && GameObjects.DWARF.location != currentLocation)
										{
											dwarvesLeft += dwarfPresent;
											dwarfPresent = 0;
										}
										dwarvesLeft -= dwarfFlag == 1 ? 0 : 1;
										dwarfPresent++;
										drop(GameObjects.DWARF);
									}
									newDwarf = true;
									System.out.println("left " + dwarvesLeft + " present " + dwarfPresent);
									System.out.println("encounter " + encounter + "\nchance " + chance);
								}
							}
							output = getDescription(currentLocation, brief);
							if(stateOfTheBear == 2){ output += "\n\tYou are being followed by a very large, tame bear."; }
							if(currentLocation.equals(Locations.Y2))
							{
								double chance = AdventMain.generate();
								if(chance > .74){ output += "\n\nA hollow voice says \"PLUGH\""; }
							}
						}
					}
					break;
			}
		}

		if(relocate)
		{ place(GameObjects.EMERALD, Locations.PROOM); relocate = false; }
		return output;
	}


//  - - - -  Movement Helpers  - - - -  //

	private String pitchDark(String output)
	{
		boolean pitifulDeath = fallInPit();
		if(pitifulDeath && !canISee(previousLocation))
		{
			playerIsDead = true;
			playerJustDied = true;
			fatality = 1;
		}
		else
		{ output = "It is now pitch dark. If you proceed you will likely fall into a pit."; }
		return output;
	}
	
	private void setLocation(Locations newLocation)
	{
		previousLocation = currentLocation;
		currentLocation = newLocation;
	}

	private boolean fallInPit()
	{ return AdventMain.generate() < .35; }

	private String getDescription(Locations here, int brief)
	{
		String output = AdventMain.Locations.getDescription(here, brief);
		output += AdventMain.GameObjects.listItemsHere(currentLocation);
		return output;
	}

	boolean canISee(Locations here)
	{ return (currentLocation.dontNeedLamp(here)) || (lampIsLit && objectIsPresent(GameObjects.LAMP)); }



//  - - - -  Death, Resurrection, & Quiting  - - - -  //

	private String death(String output)
	{
		output = fatality - 1 > -1 ? AdventMain.DEATH_MESSAGES[fatality - 1] : "";
		if(isInHand(GameObjects.LAMP))
		{ place(GameObjects.LAMP, Locations.BUILDING); lampIsLit = false; }
		attemptAction(ActionWords.DROP, GameObjects.ALL, "SYSTEM");
		if(fatality == 3)
		{
			for(GameObjects object : GameObjects.values())
			{
				if(GameObjects.isTreasure(object) && object.location.ordinal() >= Locations.NESIDE.ordinal() && object != GameObjects.EGGS)
				{ lostTreasures++; }
			}
		}
		currentLocation = Locations.BUILDING;
		previousLocation = Locations.BUILDING;
		fatality = 0;
		if(caveIsClosing)
		{
			output += "\n\nIt looks as though you you're dead. Well, seeing as how it's so close to closing time anyway, let's just call it a day.";
			over = true;
		}
		else
		{
			questionAsked = Questions.RESURRECT;
			lives--;
			output += AdventMain.RES_OFFER[lives];
		}
		return output;
	}
	
	private String quit(String output)
	{
		getCurrentScore();
		if(output == null) { output = ""; }
		else { output += "\n\n"; }
		output += "\tYou scored " + score + " points out of a possible 350, using " + turns + " turn";
		if(turns > 1) { output += "s"; }
		output += ".\n\t";

		boolean found = false;
		for(int i = 0; i < 9; i++)
		{
			if(!found && score <= AdventMain.SCORES[i])
			{
				found = true;
				output += AdventMain.S_MESSAGES[i] + "\n\tTo achieve the next higher rating";
				if(i < 8)
				{	
					int next = 1 + AdventMain.SCORES[i] - score;
					String s = "s";
					if(next == 1){ s = ""; }
					output += ", you need " + next + " more point" + s + ".";
				}
				else
				{ output += " would be a neat trick!\n\tCongratulations!!"; }
			}
		}
		return output + "\n\n\n> Press Enter to Play Again <";
	}


//  - - - -  Passage of Time & Endgame Trigger  - - - -  //

	private String clocksAndLamp(String output)
	{
		if(clock2 == 0)
		{
			output = "The sepulchral voice intones, \n\t\"The cave is now closed.\"\n\nAs the echoes fade, there is a blinding flash of light (and a small puff of orange smoke)..."
				   + "\nThen your eyes refocus: you look around and find...\n\n";
			caveIsClosed = true;
			bonus = 10;
			attemptAction(ActionWords.DROP, GameObjects.ALL, "SYSTEM");
			attemptAction(ActionWords.OFF, GameObjects.NOTHING, "SYSTEM");
			currentLocation = Locations.SWEND;
			drop(GameObjects.GRATE);	drop(GameObjects.SNAKE);	drop(GameObjects.BIRD);		drop(GameObjects.CAGE);
			drop(GameObjects.ROD2);		drop(GameObjects.PILLOW);	drop(GameObjects.MIRROR);
			currentLocation = Locations.NEEND; previousLocation = Locations.NEEND;
			drop(GameObjects.BOTTLE);	drop(GameObjects.PLANT);	drop(GameObjects.OYSTER);	drop(GameObjects.LAMP);
			drop(GameObjects.ROD);		drop(GameObjects.DWARF);	drop(GameObjects.MIRROR);
			endGameObjectsStates = new boolean[] { true, true, true, true, true, true, true, true, true, true };
			stateOfThePlant = 3;
			output += AdventMain.Locations.getDescription(currentLocation, brief);
			clock1 = -2; clock2 = -2;
		}
		else if(clock1 == -1){ clock2--; }
		else if(clock1 == 0)
		{
			output = "A sepulchral voice, reverberating through the cave, says, \n\t\"Cave closing soon. All adventurers exit immediately through main office.\"";
			clock1 = -1;
			dwarvesLeft = 0;
			voidObject(GameObjects.TROLL); voidObject(GameObjects.TROLL_);
			place(GameObjects.TROLL2_, Locations.NESIDE); place(GameObjects.TROLL2, Locations.SWSIDE);
			if(stateOfTheBear != 3){ voidObject(GameObjects.BEAR); }
			grateIsUnlocked = false;
			caveIsClosing = true;
		}
		else if(tally == 15 && !(Locations.outsideCave(currentLocation)) && !(currentLocation == Locations.Y2))
		{ clock1--; }
		else if(lampIsLit && !(caveIsClosing || caveIsClosed))
		{
			if(lamp < 0 && currentLocation.outside(currentLocation))
			{
				output += "\n\nThere's not much point in wandering around out here, and you can't explore the gave without a lamp. So let's just call it a day.";
				over = true;
			}
			else if(lamp < 0)
            {
                output += "\nYour lamp has run out of power.";
                if(!canISee(currentLocation))
                { output += "\nIt is now pitch dark. If you proceed you will likely fall into a pit."; }
                lampIsLit = false;
            }
			else if(lamp < 31 && stateOfSpareBatteries == 1 && objectIsPresent(GameObjects.BATTERIES) && objectIsPresent(GameObjects.LAMP))
			{
				output += "\n\nYour lamp is getting dim. I'm taking the liberty of replacing the batteries.";
				place(GameObjects.BATTERIES, currentLocation);
				stateOfSpareBatteries = 2;
				lamp = 2500;
			}
			else if(lamp < 31 && !lowBatteryWarning)
			{
				output += "\n\nYour lamp is getting dim" + AdventMain.BATTERY_WARNING[stateOfSpareBatteries];
				lowBatteryWarning = true;
			}
			else
			{ lamp--; }
		}
		return output;
	}


//  - - - -  Object Helpers  - - - -  //

	// Shortcuts
	boolean isInHand(GameObjects thing) 	   { return thing.location == Locations.INHAND;		}
	boolean objectIsHere(GameObjects thing)	   { return thing.location == currentLocation;		}
	boolean objectIsPresent(GameObjects thing) { return objectIsHere(thing) || isInHand(thing); }

	private void voidObject(GameObjects thing) 				{ AdventMain.Locations.placeObject(thing, Locations.THEVOID); }
	private void take(GameObjects thing) 					{ AdventMain.Locations.placeObject(thing, Locations.INHAND);  }
	private void drop(GameObjects thing)					{ AdventMain.Locations.placeObject(thing, currentLocation);   }
	private void place(GameObjects thing, Locations place)	{ AdventMain.Locations.placeObject(thing, place); 			  }

//  - - - -  Hint Helpers  - - - -  //

	private String checkForHints()
	{
		boolean justArrived = (locationAtStartOfAction != currentLocation);
		if(!caveIsClosed)
		{
			if(actionToAttempt == ActionWords.NOTHING)
			{
				if((Hints.GRATE.proc < Hints.GRATE.threshold) && !grateIsUnlocked && currentLocation == Locations.OUTSIDE && !justArrived && !(objectIsPresent(GameObjects.KEYS)))
				{ Hints.GRATE.proc++; }
				if((Hints.SNAKE.proc < Hints.SNAKE.threshold) && objectIsPresent(GameObjects.SNAKE) && !(objectIsPresent(GameObjects.BIRD)) && !justArrived)
				{ Hints.SNAKE.proc++; }
				if((Hints.MAZE.proc  < Hints.MAZE.threshold ) && ((currentLocation.ordinal() >= Locations.ALIKE1.ordinal()) && (currentLocation.ordinal() <= Locations.ALIKE14.ordinal()))
                        || (currentLocation != Locations.DEAD2 && currentLocation != Locations.DEAD8 && (currentLocation.ordinal() >= Locations.DEAD1.ordinal()) && (currentLocation.ordinal() <= Locations.DEAD11.ordinal())))
				{ Hints.MAZE.proc++;  }
				if((Hints.DARK.proc  < Hints.DARK.threshold ) && currentLocation == Locations.ALCOVE || (currentLocation == Locations.PROOM && !(objectIsPresent(GameObjects.LAMP))))
				{ Hints.DARK.proc++;  }
				if((Hints.WITT.proc  < Hints.WITT.threshold ) && currentLocation == Locations.WITT)
				{ Hints.WITT.proc++;  }
			}

			for(Hints hint : Hints.values())
			{
				if(hint.proc == hint.threshold && questionAsked == Questions.NONE && hintToOffer == Hints.NONE && offeredHint == Hints.NONE)
				{
					if (hint == Hints.WEST)
					{ hint.proc++; hint.given = true; return hint.hint; }
					else
					{ return confirmIntentBeforeHint(hint); }
				}
			}
		}
		return AdventMain.Empty;
	}

	private void resetHintsAndQuestions()
	{ questionAsked = Questions.NONE; hintToOffer = Hints.NONE; offeredHint = Hints.NONE; }

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
		if(lamp > 30){ lamp += 30 * hint.cost; }
		return hint.hint + (hint == Hints.DARK ? (objectIsPresent(GameObjects.EMERALD) ? "" : " None of the objects available is immediately useful for discovering the secret.") : "");
	}


//  - - - -  Various Helpers  - - - -  //

	void updateFoundTreasure(GameObjects thing)
	{
		if(thing == GameObjects.RUG_){ thing = GameObjects.RUG; }
		if(GameObjects.isTreasure(thing) && !found.get(thing))
		{ found.put(thing, true); tally++; }
	}

	private int getCurrentScore()
	{
		int currentScore = 2 + (2 * tally) + (lives * 10);
		for(GameObjects item : GameObjects.values())
		{
			if(item.location == Locations.BUILDING && GameObjects.isTreasure(item) && !(item == GameObjects.VASE && vaseIsBroken))
			{ currentScore += ( GameObjects.isLesserTreasure(item) ? 10 : ( item == GameObjects.CHEST ? 12 : 14 ) ); }
		}

		if(GameObjects.MAG.location == Locations.WITT){ currentScore++; }
		for(Hints hint : Hints.values())
		{ if(hint.given) { currentScore -= hint.cost; } }
		if(wellInCave){ currentScore += 25;	}
		if(caveIsClosed){ currentScore += 25; }
		if(!quit){ currentScore += 4; }
		currentScore += bonus;
		score = currentScore;
		return currentScore;
    }

	private String nonsense()
	{
		String output;
		increaseTurns = false;
		double randomOutput = AdventMain.generate();
		if(randomOutput < .34)
		{ output = "I have no idea what you are talking about!"; }
		else if(randomOutput < .67)
		{ output = "I don't understand that!"; }
		else
		{ output = "You're not making any sense!"; }
		return output;
	}

	private int askYesNo(String input)
	{ return ( (input.substring(0, 1).equals("n")) ? 2 : ( (input.substring(0, 1).equals("y")) ? 1 : 0 ) ); }

	void collapse()
	{
		collapse = true; justCollapsed = true;
		stateOfTheBear = 3;
	}
	
	void setTroll()
	{ stateOfTheTroll = 1; }


//  - - - -  Getters  - - - -  //

	public boolean isDead() { return playerIsDead; }
	public boolean isOver() { return over		 ; }
	public int getTurns()	{ return turns		 ; }
	public int getScore() 	{ return (questionAsked == Questions.INSTRUCTIONS ? 0 : score); }

}