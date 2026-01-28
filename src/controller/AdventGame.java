/**
 * @author Ariana Fairbanks
 */

package controller;

import java.io.*;
import java.util.*;

import data.*;

import static controller.AdventMain.*;
import static data.Communication.*;

public class AdventGame implements Serializable
{
	@Serial private static final long serialVersionUID = 216265234662749493L;

	// A map tracking found objects.
	EnumMap<GameObjects, Boolean> objectFound = new EnumMap<>(GameObjects.class);

	// The last user input string.
	String lastInput;

	// Current and past location information.
	Locations currentLocation;
	Locations previousLocation;
	Locations locationAtStartOfAction;

	// Flags reflecting the current game state.
	boolean	over,				quit,
			relocate,			collapse,			justCollapsed,			playerIsDead,		playerJustDied,
			grateIsUnlocked,	crystalBridge,		lampIsLit,				snakeInHotMK,		doorHasBeenOiled,
			dragonIsAlive,		birdInCage,			bearAxe,				vaseIsBroken,		goldInInventory,
			caveIsClosing,		caveIsClosed,		extraMovesForPanic,		lowBatteryWarning,
			battleUpdate,		locationChange,		increaseTurns,			wellInCave,			newDwarf;

	// The action we will attempt to resolve this turn.
	ActionWords	actionToAttempt;

	// If the player was just asked a question it will appear here. We will check to see if they answered at resolution.
	Questions questionAsked;

	// The next available and just offered hints, if any.
	Hints hintToOffer;
	Hints offeredHint;

	// Verbosity, score values, and lamp information.
	int brief;
	int score;
	int bonus;
	int tally;
	int turns;
	int lamp;

	// Byte flags reflecting the current game state.
	byte	clock1,			clock2,				itemsInHand, 	livesLeft,		lostTreasures,
			pirate,			movesWOEncounter,	deadDwarves,	dwarvesLeft,
			stateOfPlant,	stateOfBottle;

	byte fatality;			/* default, pit, dwarf, collapse */
	byte dwarfFlag;			/* nothing, reached hall no dwarf, met dwarf no knives, knife misses, knife hit .095, .190, .285 */
	byte dwarfPresent;		/* none, new, current, old */
	byte stateOfTroll;		/* there, hidden, dead, can pass */
	byte stateOfBear;		/* default, fed + idle, fed + following, dead, was following idle */
	byte stateOfChain;		/* locked to bear, unlocked, locked */
	byte spareBatteries;	/* default, purchased, used to replace old */
	byte fooMagicWordProgression;

	// The state of certain end game objects which are invisible until you interact with them.
	public boolean[] endGameObjectsStates;	// bottles, lamps, pillows, rods

	/**
	 * New Game Constructor
	 */
	public AdventGame()
	{
		setUp();
	}

	/**
	 * Resets the game state.
	 */
	void setUp()
	{
		// "Un-find" any found treasure.
		for (GameObjects object : GameObjects.values())
		{
			if (GameObjects.isTreasure(object) && object != GameObjects.RUG_)
			{
				objectFound.put(object, false);
			}
		}

		lastInput = EMPTY;
		currentLocation = Locations.ROAD; previousLocation = null; locationAtStartOfAction = Locations.ROAD;

		over = false; 				quit = false;
		relocate = false; 			collapse = false;		justCollapsed = false;		playerIsDead = false;		playerJustDied = false;
		grateIsUnlocked	= false;	crystalBridge = false;	lampIsLit = false;			snakeInHotMK = true; 		doorHasBeenOiled = false;
		dragonIsAlive = true;		birdInCage = false;		bearAxe = false;			vaseIsBroken = false;		goldInInventory = false;
		caveIsClosing = false;		caveIsClosed = false;	extraMovesForPanic = false;	lowBatteryWarning = false;
		battleUpdate = false;		locationChange = false;	increaseTurns = false;		wellInCave = false;			newDwarf = false;

		actionToAttempt = ActionWords.NOTHING;
		questionAsked = Questions.INSTRUCTIONS;
		hintToOffer = Hints.NONE; offeredHint = Hints.INSTRUCTIONS;

		brief = 0; score = 0; bonus = 0; tally = 0; turns = 0; lamp = 330;

		clock1 = 15;	clock2 = 15;			itemsInHand = 0;	livesLeft = 3;		lostTreasures = 0;
		pirate = 0;		movesWOEncounter = 1;	deadDwarves = 0;	dwarvesLeft = 5;

		fatality = 0;
		dwarfFlag = 0;
		dwarfPresent = 0;
		stateOfTroll = 0;
		stateOfBear = 0;
		stateOfChain = 0;
		spareBatteries = 0;
		stateOfPlant = 0;
		stateOfBottle = 1;
		fooMagicWordProgression = 0;

		endGameObjectsStates = new boolean[] {false, false, false, false, false, false, false, false, false, false};
	}


	/**
	 * Resolve and attempt to act upon single word input.
	 */
	public String determineAndExecuteCommand(String input)
	{
		lastInput = input;
		locationAtStartOfAction = currentLocation;
		increaseTurns = true;

		String output;
		int isYesNoAnswer = responseYesNo(input);

		input = AdventMain.truncate.apply(input);
		KnownWord word = knownWords.getOrDefault(input, GameObjects.NOTHING);

		if (actionToAttempt != ActionWords.NOTHING && word instanceof GameObjects)
		{
			// Attempt to follow through on an action initiated by the previous input.
			output = attemptAction(actionToAttempt, word, input);
			actionToAttempt = ActionWords.NOTHING;
		}
		else if (questionAsked.serious && isYesNoAnswer == 0)
		{
			// The player was asked a serious question. They will not be allowed to proceed until they answer it.
			output = "Just yes or no, please.";
			increaseTurns = false;
		}
		else if (questionAsked != Questions.NONE && isYesNoAnswer > 0)
		{
			// A question was asked and answered. Determine the result.
			output = handleQuestionAnswer(isYesNoAnswer);
		}
		else if (hintToOffer != Hints.NONE && isYesNoAnswer > 0)
		{
			// A hint offer threshold has been reached. Offer the hint and display the score cost.
			output = (isYesNoAnswer == 1 ? offerHint() : OKAY);
		}
		else if (offeredHint != Hints.NONE && isYesNoAnswer > 0)
		{
			// Display the hint and deduct the cost from the score if the offer was accepted.
			output = isYesNoAnswer == 1 ? giveHint(offeredHint) : OKAY;
		}
		else if (word == ActionWords.FEEFIE && (fooMagicWordProgression > 0 || input.equals("fee")))
		{
			// Start or continue the FEE FIE FOE FOO magic word progression.
			output = attemptAction((ActionWords) word, GameObjects.NOTHING, input);
		}
		else
		{
			// This input doesn't require special processing.
			fooMagicWordProgression = 0;
			resetHintAndQuestionStatus();

			output = resolveSingleWordCommand(word, input);
		}

		if (input.equals(WEST_STRING))
		{
			Hints.WEST.proc++;
		}

		return finishInputProcessing(output);
	}

	/**
	 * Respond appropriately when the player answers a question.
	 */
	private String handleQuestionAnswer(int isYesNoAnswer)
	{
		String output = null;

		switch (questionAsked)
		{
			case DRAGON:
				// The dragon question is unusual and requires special handling. It seems rhetorical, but a
				// positive answer is the only way to slay the dragon.
				output = "Congratulations! You have just vanquished a dragon with your bare hands! (Unbelievable, isn't it?)";
				dragonIsAlive = false;

				// The dragon prevented movement through the secret canyon from either direction. This was modeled by
				// splitting the canyon into two locations, each forbidding passage to the other. The dragon and the rug
				// it is resting on are in the center of the canyon and therefore present in both rooms, so each of those
				// objects are similarly split into two—a regular object and a duplicate dummy object—which allows them to
				// exist in both locations at the same time.

				// Now that the dragon is dead, we will secretly replace the original canyon locations with a new, single
				// location and dispose of the dummy objects.
				currentLocation = Locations.SCAN2;
				voidObject(GameObjects.DRAGON_); voidObject(GameObjects.RUG_);
				drop(GameObjects.DRAGON);
				drop(GameObjects.RUG);
				break;

			case INSTRUCTIONS:
				if (isYesNoAnswer == 1)
				{
					// Give the instructions, then describe the starting location.
					output = giveHint(offeredHint) + Locations.getDescription(Locations.ROAD, brief);

					// Accepting instructions at the start of the game increases the lamp charge. This
					// allows beginners more time to explore and experiment before the lights go out.
					lamp = 1000;
				}
				else
				{
					// Instructions or no, we will describe the starting location.
					output = Locations.getDescription(Locations.ROAD, brief);
				}
				break;

			case RESURRECT:
				increaseTurns = false;
				if (isYesNoAnswer == 2)
				{
					// No resurrection means game over.
					over = true;
				}
				else
				{
					// Attempt to resurrect the player.
					output = RES_MESSAGE[livesLeft] + (livesLeft > 0 ? Locations.getDescription(currentLocation, brief) + GameObjects.listItemsHere(currentLocation) : "");
				}
				break;

			case QUIT, SCOREQUIT:
				if (isYesNoAnswer == 1)
				{
					// The player has quit and the game is over.
					quit = true;
					over = true;
				}
				else
				{
					output = OKAY;
				}
				break;

			case READBLASTHINT:
				if (isYesNoAnswer == 1)
				{
					output = giveHint(offeredHint);
					Hints.BLAST.proc = 1;
				}
				else
				{
					output = OKAY;
				}
				break;
		}

		resetHintAndQuestionStatus();
		return output;
	}

	private String resolveSingleWordCommand(KnownWord word, String input)
	{
		// Attempt to follow through with the command associated with the given input.
		if (word instanceof Movement)
		{
			// The input is a movement word. Try to move accordingly.
			return attemptMovement((Movement) word);
		}
		else if (word instanceof ActionWords)
		{
			// The input is an action word. Try to act accordingly.
			return attemptAction((ActionWords) word, GameObjects.NOTHING, "");
		}
		else if (word instanceof GameObjects && word != GameObjects.NOTHING)
		{
			boolean objectPresent = objectIsPresent((GameObjects) word);

			if (!objectPresent && (word == GameObjects.WATER || word == GameObjects.OIL))
			{
				// We will attempt to pour water or oil from a container in the inventory if none is
				// present at the current location and no action is specified.
				return attemptAction(ActionWords.POUR, word, "");
			}

			increaseTurns = false;

			// The player is trying to interact with an object.
			if (objectPresent)
			{
				// The player can't interact with the knives thrown by the dwarves.
				// For any other object, we will ask for an action to perform.
				return word == GameObjects.KNIFE ? KNIVES_VANISH : "What would you like to do with the " + lastInput + "?" ;
			}
			else
			{
				// We can't interact with something that isn't present.
				return DONT_SEE_ANY + input + ".";
			}
		}
		else if (word instanceof MessageWords)
		{
			// The user input is something that always triggers a static message.
			return ((MessageWords) word).message;
		}
		else
		{
			// We don't understand the input.
			return nonsense();
		}
	}

	/**
	 * Resolve and attempt to act upon double word input.
	 */
	public String determineAndExecuteCommand(String input1, String input2)
	{
		lastInput = input1 + " " + input2;
		locationAtStartOfAction = currentLocation;
		increaseTurns = true;

		String output;

		if (questionAsked.serious)
		{
			// The player was asked a serious question. They will not be allowed to proceed until they answer it.
			output = "Just yes or no, please.";
			increaseTurns = false;
		}
		else
		{
			fooMagicWordProgression = 0;
			resetHintAndQuestionStatus();

			output = resolveDoubleWordCommand(input1, input2);
		}

		if (input1.equals(WEST_STRING) || input2.equals(WEST_STRING))
		{
			Hints.WEST.proc++;
		}

		return finishInputProcessing(output);
	}

	private String resolveDoubleWordCommand(String input1, String input2)
	{
		String input1Truncated = AdventMain.truncate.apply(input1);
		String input2Truncated = AdventMain.truncate.apply(input2);
		KnownWord word1 = knownWords.getOrDefault(input1Truncated, GameObjects.NOTHING);
		KnownWord word2 = knownWords.getOrDefault(input2Truncated, GameObjects.NOTHING);

		if (word1 != GameObjects.NOTHING && word2 != GameObjects.NOTHING)
		{
			if (objectIsPresent(GameObjects.KNIFE) && (word1 == GameObjects.KNIFE || word2 == GameObjects.KNIFE))
			{
				// The player can't interact with the knives thrown by the dwarves.
				increaseTurns = false;
				return KNIVES_VANISH;
			}
			else if (word1 instanceof MessageWords)
			{
				// The user input is something that always triggers a static message.
				return ((MessageWords) word1).message;
			}
			else if (word1 == Movement.ENTER)
			{
				// The first word indicates the "GO" action, so we will attempt to "GO" somewhere.
				// "STREAM" is actually recognized as a location or movement word, so a special indicator is
				// given if the user is trying to enter the "STREAM" so it isn't treated as nonsense.
				String alt = word2 == Movement.STREAM ? ENTER_STREAM : input2Truncated;
				return attemptAction(ActionWords.GO, word2, alt);
			}
			else if (word1 == Movement.STREAM && word2 == Movement.ENTER)
			{
				// TODO: does the original handle this edge case?
				// "STREAM" is actually recognized as a location or movement word, so a special indicator is
				// given if the user is trying to enter the "STREAM" so it isn't treated as nonsense.
				return attemptAction(ActionWords.GO, word1, ENTER_STREAM);
			}
			else if (word1 instanceof Movement)
			{
				// The first word resolves to indicate movement.
				return attemptMovement((Movement) word1);
			}
			else if ((word1 == GameObjects.WATER || word1 == GameObjects.OIL) && word2 instanceof GameObjects)
			{
				// TODO: should we handle the reverse case?
				// "WATER" and "OIL" are game objects that can also be used as verbs. If both words resolve to
				// objects and the first is "WATER" or "OIL" then we will assume the former is actually an action.
				return attemptAction(ActionWords.POUR, word1, "");
			}
			else if (word1 instanceof ActionWords)
			{
				// The first word resolves to an action.
				return attemptAction((ActionWords) word1, word2, input2);
			}
			else if (word2 == Movement.ENTER)
			{
				// The second word indicates the "GO" action and the first word wasn't resolved as action or
				// movement. So, we will attempt to "GO" somewhere.
				return attemptAction(ActionWords.GO, word1, input1Truncated);
			}
			else if (word2 instanceof Movement)
			{
				// The first word didn't trigger any handled scenarios and the second resolves to indicate movement.
				return attemptMovement((Movement) word2);
			}
			else if (word2 instanceof ActionWords)
			{
				// The first word didn't trigger any handled scenarios and the second resolves to an action.
				return attemptAction((ActionWords) word2, word1, input1);
			}
		}

		// We didn't understand the input.
		return nonsense();
	}

	/**
	 * Finish the input loop for the current turn.
	 */
	private String finishInputProcessing(String output)
	{
		if (!wellInCave && currentLocation.ordinal() >= Locations.minLocDeepInCave())
		{
			// The player proceeding deep enough into the cave (anywhere past the east end of the "HALL OF MISTS")
			// adds 25 points to their score and allows dwarves to start spawning.
			wellInCave = true;
			dwarfFlag = 1;
		}

		// We won't bother with time passing if the player just quit the game.
		if (!over)
		{
			// Now we will handle the passage of time and the movement of any other "critters" in the cave.
			if (locationChange && increaseTurns)
			{
				// The player has changed locations and the flag to increase the turn count after this input resolution
				// is set. Check and update our various timers to see if this passage of time has any consequences.
				output = passageOfTime(output);
				locationChange = false;

				// Since we changed locations there is a chance the pirate has appeared if we haven't seen him yet.
				// This flag is set after calculating the probability of his appearance during movement resolution,
				// but he doesn't actually "show up" until now, just before the finalization of this input loop.
				if (pirate == 1)
				{
					pirate = 2;

					// The pirate's chest and the message indicating the maze wherein it resides only appear after the
					// player has actually encountered the pirate.
					place(GameObjects.MESSAGE, Locations.PONY);
					place(GameObjects.CHEST, Locations.DEAD2);

					// If the player is holding any treasure when the pirate shows up he will steal it and hide it with
					// his treasure chest in the maze.
					List<GameObjects> currentlyHolding = GameObjects.getObjectsHere(Locations.INHAND);
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

			if (increaseTurns)
			{
				// Increase the turn count.
				turns++;

				// Handle dwarf movement and combat.
				output = handleDwarves(output);
			}

			battleUpdate = false;
			newDwarf = false;

			if (15 - tally == lostTreasures && lamp > 35)
			{
				// Losing too many treasures will forcefully deplete the lamp's batteries.
				lamp = 35;
			}
			getCurrentScore();

			// Add death output if the player just died.
			if (playerIsDead && playerJustDied && !over)
			{
				output += death();
				playerJustDied = false;
			}
		}

		// If the game is over we'll add the relevant text. Otherwise, we will check if we have any hints to offer.
		output = over ? quit(output) : output + checkForHints() ;

		if (locationAtStartOfAction != currentLocation)
		{
			// Clean up any knives that may be lying around when we leave a location.
			voidObject(GameObjects.KNIFE);
		}

		// Gold being in the player's inventory affects certain locations, so we keep track of its in-hand status.
		goldInInventory = isInHand(GameObjects.GOLD);

		// Log information to the console.
		AdventMain.logGameInfo();

		return output;
	}

	private String handleDwarves(String output)
	{
		if (dwarfPresent == 0)
		{
			return output;
		}

		StringBuilder outputString = new StringBuilder(output);

		if (GameObjects.DWARF.location == previousLocation)
		{
			if (Locations.crittersAllowed(currentLocation))
			{
				// The player has moved to a location where the dwarf can follow.
				drop(GameObjects.DWARF);

				// TODO: Chance to lose the dwarf?
			}
			else
			{
				// TODO: Chance the dwarf hangs around for a bit?
				returnPresentDwarves();
				voidObject(GameObjects.DWARF);
			}
		}

		if (GameObjects.DWARF.location == currentLocation)
		{
			// TODO: dwarf flag should have a 5% chance of increasing to "2" for every movement above min loc

			if (dwarfFlag == 1)
			{
				// This was the first dwarf encountered. He will always drop the "AXE" and run away.
				// We will now decrease the maximum number of dwarves that can spawn by up to 3.
				dwarvesLeft -= (byte) Math.floor(AdventMain.generate() * 3);
				dwarfFlag = 2;
				dwarfPresent = 0;
				outputString.append("\n\n - - - A little dwarf just walked around a corner, saw you, threw a little axe at you, cursed, and ran away. (The axe missed.)");
				drop(GameObjects.AXE);
				voidObject(GameObjects.DWARF);
			}
			else
			{
				// There is at least one dwarf at the player's location
				outputString.append("\n\n - - - There ");
				outputString.append(dwarfPresent == 1 ? "is a threatening little dwarf" : "are " + dwarfPresent + " threatening little dwarves");
				outputString.append(" in the room with you!");
				drop(GameObjects.DWARF);

				if (battleUpdate || !locationChange)
				{
					// TODO

					// The last action wasn't movement or anything else that would interrupt
					// dwarf combat, so they will now attempt to kill the player.
					var thrown = dwarfPresent - (newDwarf ? 1 : 0);
					if (thrown > 0)
					{
						outputString.append("\n - - - ");
						outputString.append(thrown == 1 ? "One sharp nasty knife is thrown" : (thrown == dwarfPresent ? "All" : thrown) + " of them throw knives");
						outputString.append(" at you!\n");
						int hit = 0;
						drop(GameObjects.KNIFE);

						// Did any of the knives hit the player?
						if (dwarfFlag >= 3)
						{
							for (var d = 0; d < dwarfPresent; d++)
							{
								if ((AdventMain.generate() * 1000) < (95 * (dwarfFlag - 2)))
								{
									hit++;
								}
							}
						}
						else
						{
							dwarfFlag++;
						}

						if (hit > 0)
						{
							// The player was killed in dwarf combat.
							playerIsDead = true;
							playerJustDied = true;
						}

						outputString.append(" - - - ");
						if (dwarfPresent == 1)
						{
							outputString.append(hit > 0 ? "It gets you!" : "It misses!");
						}
						else
						{
							outputString.append(hit > 0 ? (hit == 1 ? "One of them gets" : dwarfPresent + " of them get") : "None of them hit");
							outputString.append(" you!");
						}
					}
				}
			}
		}

		return outputString.toString();
	}

	private void returnPresentDwarves()
	{
		// The player has left the location where the dwarves are at, so we will return them to the remaining dwarf pile.
		dwarvesLeft += dwarfPresent;
		dwarfPresent = 0;
	}


	private String attemptAction(ActionWords verb, KnownWord other, String context)
	{
		String output = OKAY_BUT_HOW;
		if (other == null)
		{
			// Couldn't resolve the second input.
			output = DONT_SEE_ANY + context + ".";
			increaseTurns = false;
		}

		if (other == MessageWords.CUSS)
		{
			// No swearing allowed.
			output = MessageWords.CUSS.message;
		}
		else if (verb == ActionWords.GO)
		{
			if (context.isEmpty())
			{
				output = "Where?";
				increaseTurns = false;
			}
			else if (other == GameObjects.WATER || (other == Movement.STREAM && context.equals(ENTER_STREAM)))
			{
				increaseTurns = currentLocation.hasWater;
				output = increaseTurns ? "Your feet are now wet." : "I don't see any water.";
			}
			else
			{
				output = attemptMovement(context);
			}
		}
		else if (other instanceof GameObjects)
		{
			GameObjects object = (GameObjects) other;
			output = attemptAction(verb, object, context, output);
		}
		else if (verb == ActionWords.TAKE && other == ActionWords.ABSTAIN)
		{
			// Should this be elsewhere?
			output = OKAY;
		}
		else
		{
			output = CAN_NOT_DO_THAT;
			increaseTurns = false;
		}
		return output;
	}

	private String attemptAction(ActionWords verb, GameObjects object, String context, String output)
	{
		switch (verb)
		{
			case RELAX, NOTHING, ABSTAIN:
				return OKAY;

			case TAKE:
				return attemptTakeAction(object, context);

			case DROP:
				return attemptDropAction(object, context, output);

			case OPEN:
				return attemptOpenAction(object, context, output);

			case CLOSE:
				return attemptCloseAction(object, output);

			case ON:
				return attemptOnAction(object, context);

			case OFF:
				return attemptOffAction(output);

			case WAVE:
				return attemptWaveAction(object, context);

			case POUR:
				return attemptPourAction(object, output);

			case EAT:
                return attemptEatAction(object);

			case RUB:
                return attemptRubAction(object);

			case TOSS:
                return attemptTossAction(object, context);

			case BREAK:
                return attemptBreakAction(object);

			case KILL:
                return attemptKillAction(object, context, output);

			case SAY:
                return attemptSayAction(context);

			case READ:
                return attemptReadAction();

			case BRIEF:
                return attemptBriefAction();

			case VERBOSE:
                return attemptVerboseAction();

			case FIND:
				return attemptFindAction(object);

			case INVENTORY:
                return attemptInventoryAction();

			case SCORE:
				questionAsked = Questions.QUIT;
				increaseTurns = false;
                return "If you were to quit now, you would score " + getCurrentScore() + " out of a possible 350. \nDo you indeed wish to quit now?";

			case QUIT:
				questionAsked = Questions.QUIT;
				increaseTurns = false;
                return "Do you really wish to quit now?";

			case FEED:
				return attemptFeedAction(object, context);

			case WAKE:
                return attemptWakeAction(object);

			case DRINK:
                return attemptDrinkAction(object);

			case LOOK:
                return attemptLookAction(object);

			case CALM:
                return OKAY_BUT_HOW;

			case FILL:
                return attemptFillAction(object);

			case BLAST:
                return attemptBlastAction();

			case FEEFIE:
				return attemptFeeFieAction(context);

			default:
                increaseTurns = false;
				return "You broke something.";
		}
	}

	private String attemptTakeAction(GameObjects object, String context)
	{
		String output = CANT_BE_SERIOUS;

		if (caveIsClosed)
		{
			// TODO: Can this method be quit early after these checks? Move elsewhere?
			if (endGameObjectsStates[0] && object == GameObjects.BOTTLE	) { endGameObjectsStates[0] = false; }
			if (endGameObjectsStates[1] && object == GameObjects.LAMP	) { endGameObjectsStates[1] = false; }
			if (endGameObjectsStates[2] && object == GameObjects.PILLOW	) { endGameObjectsStates[2] = false; }
			if (endGameObjectsStates[8] && object == GameObjects.ROD	) { endGameObjectsStates[8] = false; }
		}

		if (object == GameObjects.ROD && !objectIsHere(GameObjects.ROD) && objectIsHere(GameObjects.ROD2))
		{
			// There are two different objects using the same 'rod' identifiers. The "ROD" with the rusty star is a
			// magic wand and the "ROD2" with a rusty mark is a stick of dynamite. We prioritize interacting with the
			// "ROD". If the "ROD" isn't around, attempt to preform the action with "ROD2" and update its state.
			output = attemptAction(ActionWords.TAKE, GameObjects.ROD2, context);
			endGameObjectsStates[9] = false;
		}
		else if (object == GameObjects.ALL)
		{
			// Attempt to take any and all items at the current location.
			StringBuilder fullOutput = new StringBuilder();
			List<GameObjects> itemsHere = GameObjects.getObjectsHere(currentLocation);
			if (!itemsHere.isEmpty())
			{
				// If the player didn't trigger this action then it is free. Otherwise, each
				// item taken will increase the turn counter by one.
				increaseTurns = !context.equals(SYSTEM_ACTION);
				for (GameObjects item : itemsHere)
				{
					fullOutput.append(attemptAction(ActionWords.TAKE, item, "")).append("\n");
					if (increaseTurns)
					{
						turns++;
					}
				}
				output = fullOutput.toString();
			}
			else
			{
				output = "There is nothing here to take.";
			}
			increaseTurns = false;
		}
		else if (object == GameObjects.WATER)
		{
			if (GameObjects.BOTTLE.location == Locations.INHAND)
			{
				if (stateOfBottle == 0)
				{
					// The player has an empty bottle in inventory.
					if (currentLocation.hasWater)
					{
						output = "You fill the bottle with water.";
						stateOfBottle = 1;
					}
					else
					{
						output = "I don't see any water.";
						increaseTurns = false;
					}
				}
				else
				{
					// The bottle is already full of something.
					output = BOTTLE_FULL;
					increaseTurns = false;
				}
			}
			else if (objectIsHere(GameObjects.BOTTLE) && stateOfBottle == 1)
			{
				// The bottle is present and full of water, but not in the player's inventory.
				// We will assume "WATER" in this context is referring to the bottle of water.
				output = attemptAction(ActionWords.TAKE, GameObjects.BOTTLE, context);
			}
			else
			{
				// The player doesn't have an empty bottle to carry the water in.
				output = "You have nothing in which to carry it.";
				increaseTurns = false;
			}
		}
		else if (object == GameObjects.OIL)
		{
			if (GameObjects.BOTTLE.location == Locations.INHAND)
			{
				if (stateOfBottle == 0)
				{
					// The player has an empty bottle in inventory.
					if (Locations.EASTPIT == currentLocation)
					{
						// The "EASTPIT" is the only location where oil can be found.
						output = "You fill the bottle with oil.";
						stateOfBottle = 2;
					}
					else
					{
						// There isn't any oil at the player's location.
						output = "I don't see any oil.";
						increaseTurns = false;
					}
				}
				else
				{
					// The bottle is already full of something.
					output = BOTTLE_FULL;
					increaseTurns = false;
				}
			}
			else if (objectIsHere(GameObjects.BOTTLE) && stateOfBottle == 2)
			{
				// The bottle is present and full of oil, but not in the player's inventory.
				// We will assume "OIL" in this context is referring to the bottle of oil.
				output = attemptAction(ActionWords.TAKE, GameObjects.BOTTLE, context);
			}
			else
			{
				// The player doesn't have an empty bottle to carry the oil in.
				output = "You have nothing in which to carry it.";
				increaseTurns = false;
			}
		}
		else if (object == GameObjects.NOTHING && context.isEmpty())
		{
			// We couldn't resolve an object to take. If the next input is a single object
			// then we will attempt to take that.
			output = "What would you like to take?";
			actionToAttempt = ActionWords.TAKE;
			increaseTurns = false;
		}
		else if (objectIsPresent(object))
		{
			if (object.location == Locations.INHAND)
			{
				// The player already has the object in question in inventory.
				output = "You are already carrying it!";
				increaseTurns = false;
			}
			else if (object == GameObjects.KNIFE)
			{
				// The knives can't be interacted with by the player.
				output = KNIVES_VANISH;
				increaseTurns = false;
			}
			else if (object == GameObjects.PLANT && objectIsHere(object))
			{
				output = "The plant has exceptionally deep roots and cannot be pulled free.";
			}
			else if (object == GameObjects.BEAR && stateOfBear == 3)
			{
				// The "BEAR" is dead and can't be made to follow the player anymore.
				output = CANT_BE_SERIOUS;
				increaseTurns = false;
			}
			else if (object == GameObjects.BEAR)
			{
				if (stateOfChain == 0)
				{
					// The bear has to be released from its chains before it can follow the player.
					output = "The bear is still chained to the wall.";
					increaseTurns = false;
				}
				else
				{
					stateOfBear = 2;
					output = "The bear is now following you.";
				}
			}
			else if (object == GameObjects.CHAIN && stateOfChain != 1)
			{
				output = "The chain is still locked.";
				increaseTurns = false;
			}
			else if (caveIsClosed && (object == GameObjects.BIRD || object == GameObjects.CAGE))
			{
				// The "BIRD" has already suffered enough.
				output = BIRD_ALONE;
			}
            else if (object == GameObjects.BIRD && objectIsHere(GameObjects.BIRD))
            {
                if (isInHand(GameObjects.ROD) && !birdInCage)
                {
                    // The "BIRD" is afraid of the "ROD" and won't let itself be caught. This
                    // isn't a problem if the player cages the "BIRD" before taking the "ROD".
                    output = "The bird was unafraid when you entered, but as you approach it becomes disturbed and you cannot catch it.";
                    if (Hints.BIRD.underThreshold())
                    {
                        Hints.BIRD.proc++;
                    }
                    increaseTurns = false;
                }
                else if (!isInHand(GameObjects.CAGE))
                {
                    // The player needs the "CAGE" to carry the bird around with them.
                    output = "You can catch the bird, but you cannot carry it.";
                    increaseTurns = false;
                }
                else
                {
                    // The "BIRD" and "CAGE" are taken separately, but the "BIRD" is unique in
                    // that it isn't counted towards the total items in inventory.
                    birdInCage = true;
                    take(GameObjects.BIRD);
                    take(GameObjects.CAGE);
                    output = OKAY;
                }
            }
			else if (itemsInHand >= 7)
			{
				// The player can't have more than 7 items in inventory at a time.
				output = "You can't carry anything more. You'll have to drop something first.";
				increaseTurns = false;
			}
			else if (object == GameObjects.RUG || object == GameObjects.RUG_)
			{
				if (objectIsHere(object))
				{
					// By the time the player is able to take the "RUG" there is no longer a need for two
					// separate objects. Attempting to take the "RUG_" will replace it with the "RUG".
					take(GameObjects.RUG);
					voidObject(GameObjects.RUG_);
					output = OKAY;
				}
			}
			else if (object == GameObjects.AXE && bearAxe && stateOfBear == 0)
			{
				// The player threw the "AXE" at the hungry "BEAR". The only way to get
				// past the "BEAR" to retrieve the "AXE" is to feed it the "FOOD" and pacify it.
				output = "There is no way past the bear to get the axe, which is probably just as well.";
			}
			// TODO: Should the axe be similarly unretrievable if thrown at the live dragon?
			else if (object == GameObjects.VASE && vaseIsBroken)
			{
				output = CANT_BE_SERIOUS;
			}
			else if (object.mobile && objectIsHere(object))
			{
				take(object);
				output = OKAY;
			}
		}
		else
		{
			if (!(caveIsClosed && object == GameObjects.MIRROR))
			{
				output = DONT_SEE_ANY + context + ".";
			}
			increaseTurns = false;
		}

		if (caveIsClosed && object == GameObjects.OYSTER && endGameObjectsStates[3])
		{
			// The first time the player picks up the "OYSTER" at the end of the game we inform them of a
			// message written on the bottom. If they read the message we will offer the oyster hint.
			endGameObjectsStates[3] = false;
			output = "Interesting. There seems to be something written on the under-side of the oyster.";
		}
		return output;
	}

	private String attemptDropAction(GameObjects object, String context, String output)
	{
		if (isInHand(GameObjects.ROD2) && object == GameObjects.ROD && !isInHand(GameObjects.ROD))
		{
			// There are two different objects using the same 'rod' identifiers. The "ROD" with the rusty
			// is a magic wand and the "ROD2" with a rusty mark is a stick of dynamite. We prioritize interacting
			// with the "ROD". If the "ROD" isn't around, attempt to preform the action with "ROD2".
			drop(GameObjects.ROD2);
			output = OKAY;
		}
		else if (object == GameObjects.ALL)
		{
			List<GameObjects> itemsHere = GameObjects.getObjectsHere(Locations.INHAND);
			if (!itemsHere.isEmpty())
			{
				StringBuilder fullOutput = new StringBuilder();
				for (GameObjects item : itemsHere)
				{
					// Dropping these items only takes time if this was a player initiated action.
					increaseTurns = !context.equals(SYSTEM_ACTION);

					fullOutput.append(attemptAction(ActionWords.DROP, item, "")).append("\n");
					if (increaseTurns)
					{
						turns++;
					}
				}
				output = fullOutput.toString();
			}
			else
			{
				output = "You aren't carrying anything.";
			}
			increaseTurns = false;
		}
		else if (object == GameObjects.NOTHING)
		{
			// No object was specified to drop, so we will ask for one. If the next input
			// is a single object then we will attempt to drop it.
			output = "What would you like to drop?";
			actionToAttempt = ActionWords.DROP;
			increaseTurns = false;
		}
		else if (object == GameObjects.BEAR)
		{
			if (stateOfBear == 2)
			{
				// Stop the bear from following the player or use it to intimidate the "TROLL".
				output = attemptAction(ActionWords.TOSS, GameObjects.BEAR, context);
			}
			else
			{
				// The bear isn't following right now.
				output = DONT_HAVE;
			}
		}
        else if (object == GameObjects.BIRD && birdInCage && objectIsHere(GameObjects.BIRD))
        {
			// This case was added to allow the player to free the bird from the cage without having to pick
			// up the cage first. There is no real reason for this except it bothered me that you couldn't.
            birdInCage = false;
			output = OKAY;
        }
		else if (isInHand(object))
		{
			if (object == GameObjects.CAGE && birdInCage)
			{
				// Drop the "BIRD" as well because it's inside the "CAGE".
				drop(GameObjects.CAGE);
				drop(GameObjects.BIRD);
				output = OKAY;
			}
			else if (object == GameObjects.BIRD)
			{
				// Free the "BIRD" from the "CAGE".
				birdInCage = false;
				if (objectIsHere(GameObjects.SNAKE))
				{
					// The "BIRD" chases away the "SNAKE".
					drop(GameObjects.BIRD);
					voidObject(GameObjects.SNAKE);
					snakeInHotMK = false;
					output = "The little bird attacks the green snake, and in an astounding flurry drives the snake away.";
				}
				else if (objectIsHere(GameObjects.DRAGON) || objectIsHere(GameObjects.DRAGON_))
				{
					// ( ;  ;)
					output = "The little bird attacks the green dragon, and in an astounding flurry gets burnt to a cinder. The ashes blow away.";
					voidObject(GameObjects.BIRD);
				}
				else
				{
					output = OKAY;
					drop(GameObjects.BIRD);
				}
			}
			else if (object == GameObjects.COINS && objectIsHere(GameObjects.PONY))
			{
				// The user has used their "COINS" to purchase spare batteries for the "LAMP".
				voidObject(GameObjects.COINS);
				lostTreasures++;
				drop(GameObjects.BATTERIES);
				spareBatteries = 1;
			}
			else if (object == GameObjects.VASE && !(objectIsHere(GameObjects.PILLOW) || currentLocation == Locations.SOFT))
			{
				// The "VASE" will break if it is set down in a location besides the soft room
				// unless the "PILLOW" was dropped at that location first.
				output = "The Ming vase drops with a delicate crash.";
				drop(GameObjects.VASE);
				vaseIsBroken = true;
				lostTreasures++;
			}
			else
			{
				// The object in question is in inventory and can be dropped without special processing.
				drop(object);
				output = OKAY;
			}
		}
		else
		{
			// The player doesn't have the object they tried to drop in inventory.
			output = DONT_HAVE;
			increaseTurns = false;
		}
		return output;
	}

	private String attemptOpenAction(GameObjects object, String context, String output)
	{
		// Most cases here don't cost a turn.
		increaseTurns = false;

		if (object == GameObjects.GRATE)
		{
			// The "GRATE" is another case where an object is split in two. Above and below it
			// are separate locations, but the "GRATE" needs to be present at both.
			if (!objectIsPresent(GameObjects.GRATE) && !objectIsPresent(GameObjects.GRATE_))
			{
				return "I don't see any grate";
			}
			else if (objectIsPresent(GameObjects.KEYS))
			{
				increaseTurns = true;
				grateIsUnlocked = true;
				return "The grate is now unlocked.";
			}
			else
			{
				return "You don't have any keys!";
			}
		}
		else if (objectIsPresent(object))
		{
			if (object == GameObjects.CLAM)
			{
				if (isInHand(GameObjects.CLAM))
				{
					return "I advise you to put down the clam before opening it. >STRAIN!<";
				}
				else if (!isInHand(GameObjects.TRIDENT))
				{
					return "You don't have anything strong enough to open the clam.";
				}
				else
				{
					// After opening the "CLAM" and seeing the "PEARL" the system realizes that this is
					// actually an "OYSTER" and changes the known words accepted to refer to the object
					// accordingly. This is secretly handled by swapping in a whole new object.
					increaseTurns = true;
					voidObject(GameObjects.CLAM);
					drop(GameObjects.OYSTER);
					place(GameObjects.PEARL, Locations.CULDESAC);
					return "A glistening pearl falls out of the clam and rolls away. Goodness, this must really be an oyster! (I never was very good at identifying bivalves.)\n" +
						"Whatever it is, it has now snapped shut again.";
				}
			}
			else if (object == GameObjects.OYSTER)
			{
				if (isInHand(GameObjects.OYSTER))
				{
					return "I advise you to put down the oyster before opening it. >WRENCH!<";
				}
				else if (!isInHand(GameObjects.TRIDENT))
				{
					return "You don't have anything strong enough to open the oyster.";
				}
				else
				{
					// The "OYSTER" is empty now because the "PEARL" has already been removed.
					increaseTurns = true;
					return "The oyster creaks open, revealing nothing but oyster inside. It promptly snaps shut again.";
				}
			}
			else if (object == GameObjects.DOOR)
			{
				if (doorHasBeenOiled)
				{
					increaseTurns = true;
					return OKAY;
				}
				else
				{
					// The player needs to oil the "DOOR" before it can be opened.
					return "The door is extremely rusty and refuses to open.";
				}
			}
			else if (object == GameObjects.CAGE)
			{
				return "It has no lock.";
			}
			else if (object == GameObjects.KEYS)
			{
				return "You can't unlock the keys.";
			}
			else if (object == GameObjects.CHAIN)
			{
				if (!objectIsPresent(GameObjects.KEYS))
				{
					return NO_KEYS;
				}
				else if (stateOfChain == 0)
				{
					if (stateOfBear != 1)
					{
						// The player must feed the "BEAR" before they can unlock the "CHAIN".
						return "There is no way to get past the bear to unlock the chain, which is probably just as well.";
					}
					else
					{
						// The "BEAR" is no longer chained.
						increaseTurns = true;
						stateOfChain = 1;
						return "You unlock the chain and set the tame bear free.";
					}
				}
				else if (stateOfChain == 2)
				{
					// The "CHAIN" can be locked back to the wall and unlocked again after setting
					// the "BEAR" free, but it is pointless and the "BEAR" isn't affected.
					increaseTurns = true;
					stateOfChain = 1;
					return "The chain is now unlocked.";
				}
				else
				{
					return "It was already unlocked.";
				}
			}
		}
		else if (object == GameObjects.NOTHING)
		{
			// No object was specified, so we will ask for one. If the next input is a single
			// object then we will attempt to open it.
			actionToAttempt = ActionWords.OPEN;
			return "What would you like to open?";
		}
		else
		{
			// The specified object isn't present.
			return DONT_SEE_ANY + context + ".";
		}
		return output;
	}

	private String attemptKillAction(GameObjects object, String context, String output)
	{
		// Most cases here don't cost a turn.
		increaseTurns = false;

		if (object == GameObjects.NOTHING)
		{
			// No object was specified to kill, so we will ask for one. If the next input
			// is a single object then we will attempt to kill it.
			actionToAttempt = ActionWords.KILL;
			return "What would you like to kill?";
		}
		else if (object == GameObjects.TROLL && (objectIsHere(GameObjects.TROLL) || objectIsHere(GameObjects.TROLL_)))
		{
			// The "TROLL" can't be killed. It must be scared away with the "BEAR".
			increaseTurns = true;
			return "Trolls are close relatives with the rocks and have skin as tough as that of a rhinoceros. The troll fends off your blows effortlessly.";
		}
		else if (object == GameObjects.DRAGON && (objectIsPresent(GameObjects.DRAGON) || objectIsPresent(GameObjects.DRAGON_)))
		{
			if (!dragonIsAlive)
			{
				return "For crying out loud, the poor thing is already dead!";
			}
			else
			{
				// This question appears sarcastic, but answering in the affirmative is the only way to kill the "DRAGON".
				questionAsked = Questions.DRAGON;
				return "With what? Your bare hands?";
			}
		}
		else if (!objectIsPresent(object))
		{
			return DONT_SEE_ANY + context + ".";
		}

		switch (object)
		{
			case BIRD:
				if (caveIsClosed)
				{
					// Seriously, the "BIRD" has suffered enough.
					return BIRD_ALONE;
				}
				else
				{
					// What a monstrous thing to do.
					voidObject(GameObjects.BIRD);
					increaseTurns = true;
					return "The little bird is now dead. Its body disappears.";
				}

			case CLAM, OYSTER:
				return "The shell is very strong and impervious to attack.";

			case SNAKE:
				// Attacking the "SNAKE" won't work. It must be chased away by the "BIRD".
				return "Attacking the snake both doesn't work and is very dangerous.";

			case DWARF:
				if (caveIsClosed)
				{
					// The player dies if they disturb the dwarves at the end of the game.
					playerIsDead = true;
					playerJustDied = true;
					fatality = 2;
					// Clear the current output or the 'okay, but how?' message will appear before we describe the death.
					return "";
				}
				else
				{
					// The player can only fight the "DWARF" by tossing the "AXE".
					return "With what? Your bare hands?";
				}

			case BEAR:
				if (stateOfBear == 0)
				{
					// The player can't do anything to kill the "BEAR" directly.
					return "With what? Your bare hands? Against HIS bear hands??";
				}
				else if (stateOfBear != 3)
				{
					// The player can't do anything to kill the "BEAR" directly.
					increaseTurns = true;
					return "The bear is confused; he only wants to be your friend.";
				}
				else
				{
					return "For crying out loud, the poor thing is already dead!";
				}

			default:
				return output;
		}
	}

    private String attemptCloseAction(GameObjects object, String output)
    {
		// Most cases here don't cost a turn.
		increaseTurns = false;

        if (object == GameObjects.GRATE)
        {
            // The "GRATE" is another case where an object is split in two. Above and below it
            // are separate locations, but the "GRATE" needs to be present at both.
            if (!objectIsPresent(GameObjects.GRATE) && !objectIsPresent(GameObjects.GRATE_))
            {
                return "I don't see any grate";
            }
            else if (!objectIsPresent(GameObjects.KEYS))
            {
                return "You don't have any keys!";
            }
            else if (!grateIsUnlocked)
            {
                // For some reason, trying to lock an already locked "GRATE" is an edge case that is
                // handled and doesn't take a turn, but trying to unlock an open "GRATE" isn't. It is
                // unclear (because I don't remember) if this is a reflection of the original logic, if I
                // forgot to add the edge when opening the "GRATE" but remembered here, or if I added
                // this edge case but didn't think to implement it when opening the "GRATE".
                return "It was already locked.";
            }
            else
            {
				increaseTurns = true;
                grateIsUnlocked = false;
                return "The grate is now locked.";
            }
        }
        else if (objectIsPresent(object))
        {
            if (object == GameObjects.CLAM || object == GameObjects.OYSTER)
            {
                // It is actually not possible to lock the "OYSTER" because it is an "OYSTER"...
                // In all seriousness though, it closes automatically immediately after being
                // opened, so there is no way or need to make it more closed.
                return "What?";
            }
            else if (object == GameObjects.CAGE)
            {
                return "It has no lock.";
            }
            else if (object == GameObjects.KEYS)
            {
                return "You can't lock the keys.";
            }
            else if (object == GameObjects.CHAIN)
            {
                if (!objectIsPresent(GameObjects.KEYS))
                {
                    return NO_KEYS;
                }
                else if (stateOfChain != 1)
                {
                    return "It was already locked.";
                }
                else
                {
                    if (currentLocation != Locations.BARR)
                    {
                        return "There is nothing here to which the chain can be locked.";
                    }
                    else
                    {
                        // The "CHAIN" can be locked back to the wall and unlocked again after setting
                        // the "BEAR" free, but it is pointless and the "BEAR" isn't affected.
						increaseTurns = true;
                        stateOfChain = 2;
                        return "The chain is now locked.";
                    }
                }
            }
        }
        else
        {
            return "I don't know how to lock or unlock such a thing.";
        }
        return output;
    }

    private String attemptOnAction(GameObjects object, String context)
    {
        // TODO: Maybe on + other object should be checked first instead of lamp present?
        String output;
        if (objectIsPresent(GameObjects.LAMP))
        {
            // Check the "LAMP" batteries.
            if (lamp > 0)
            {
                output = "Your lamp is now on.";
                if (!canISee(currentLocation))
                {
                    // We will now describe the location if we couldn't see before the "LAMP" was turned on.
                    output += "\n\n" + getDescription(currentLocation, brief);
                }
                lampIsLit = true;
            }
            else
            {
                // The "LAMP" is out of power. The only way to replenish it is to find the
                // "COINS" and spend them to purchase the replacement "BATTERIES" from the vending
                // machine (called "PONY" for some reason) in the All Different Maze.
                output = "Your lamp has run out of power.";
                increaseTurns = false;
            }
        }
        else if (object == GameObjects.NOTHING || objectIsPresent(object))
        {
            output = "You have no source of light.";
            increaseTurns = false;
        }
        else
        {
            output = DONT_SEE_ANY + context + ".";
            increaseTurns = false;
        }
        return output;
    }

    private String attemptOffAction(String output)
    {
        // TODO: What about objects besides the lamp?
        if (objectIsPresent(GameObjects.LAMP))
        {
            lampIsLit = false;
            output = "Your lamp is now off.";
            if (!canISee(currentLocation))
            {
                // There is a random chance for the player to fall into a pit and die every time
                // they attempt to move to another location in the dark. They have been warned.
                output += "\n\nIt is now pitch dark. If you proceed you will likely fall into a pit.";
            }
        }
        return output;
    }

    private String attemptWaveAction(GameObjects object, String context)
    {
		// Most cases here don't cost a turn.
		increaseTurns = false;

        if (object == GameObjects.NOTHING)
        {
            // No known object was specified, so we will ask for one. If the next input is a
            // single object then we will attempt to wave that.
            actionToAttempt = ActionWords.WAVE;
            return "What would you like to wave?";
        }
        else if (!isInHand(object) && (object != GameObjects.ROD || !isInHand(GameObjects.ROD2)))
        {
            // The specified object isn't here and isn't either "ROD".
            return DONT_HAVE;
        }
        else if (object != GameObjects.ROD || caveIsClosed)
        {
            // The only object that can be waved to any effect is the "ROD" before the cave closes.
            if (!isInHand(object))
            {
                return DONT_SEE_ANY + context + ".";
            }
            else
            {
                return NOTHING;
            }
        }
        else if ((currentLocation != Locations.EASTFISSURE && currentLocation != Locations.WESTFISSURE))
        {
            // If we get here the player is waving the "ROD" in a location where it won't do anything.
            return NOTHING;
        }
        else
        {
            if (!caveIsClosing)
            {
                // The "ROD" is actually a magic wand and waving it at "EASTFISSURE" or "WESTFISSURE"
                // will make the magical crystal bridge connecting those locations appear or disappear.
				increaseTurns = true;
                if (!crystalBridge)
                {
                    place(GameObjects.CRYSTAL, Locations.EASTFISSURE); place(GameObjects.CRYSTAL_, Locations.WESTFISSURE);
                    crystalBridge = true;
                    return "A crystal bridge now spans the fissure.";
                }
                else
                {
                    voidObject(GameObjects.CRYSTAL); voidObject(GameObjects.CRYSTAL_);
                    crystalBridge = false;
                    return "The crystal bridge has vanished!";
                }
            }
            else
            {
                // The player is in the right place but the "ROD" stops working after the cave starts to close.
                return NOTHING;
            }
        }
    }

    private String attemptPourAction(GameObjects object, String output)
    {
        if (object == GameObjects.NOTHING || object == GameObjects.BOTTLE)
        {
            // We will assume that the player is attempting to pour the contents of the bottle.
            if (stateOfBottle == 1)
            {
                object = GameObjects.WATER;
            }
            else if (stateOfBottle == 2)
            {
                object = GameObjects.OIL;
            }
            else
            {
                object = GameObjects.NOTHING;
            }
        }

        if (object == GameObjects.NOTHING)
        {
            increaseTurns = false;
            output = "You can't pour that.";
        }
        else if (!isInHand(GameObjects.BOTTLE))
        {
            // The player can only pour the contents from the "BOTTLE", so without it nothing can happen.
            increaseTurns = false;
            output = "You have nothing to pour.";
        }
        else if (object == GameObjects.WATER && stateOfBottle == 1)
        {
            if (currentLocation == Locations.WESTPIT)
            {
                // Pouring "WATER" at the "WESTPIT" will water the plant there.
                stateOfPlant++;
                if (stateOfPlant == 1)
                {
                    // The plant has been watered once and the player can now climb it to leave the pit.
                    output = "The plant spurts into furious growth for a few seconds." +
                        "\n\n\tThere is a 12-foot-tall beanstalk stretching up out of the pit, bellowing \"Water!! Water!!\"";
                }
                else if (stateOfPlant == 2)
                {
                    // The plant has been watered twice and can now be climbed to reach the narrow corridor.
                    output = "The plant grows explosively, almost filling the bottom of the pit." +
                        "\n\n\tThere is a gigantic beanstalk stretching all the way up to the hole.";
                }
                else if (stateOfPlant == 3)
                {
                    // Watering the plant three times will kill it.
                    output = "You've over-watered the plant! It's shriveling up! It's, It's...";
                    voidObject(GameObjects.PLANT);
                    voidObject(GameObjects.PLANT2);
                    voidObject(GameObjects.PLANT2_);
                }
                else
                {
                    // The plant has already died from over-watering, so nothing more happens.
                    output = GROUND_IS_WET;
                }
            }
            else if (objectIsPresent(GameObjects.DOOR))
            {
                // Despite what the message implies, doing this doesn't make the "DOOR" impossible
                // to open. It will only rust the hinges so they must be oiled again.
                output = "The hinges are quite thoroughly rusted now and won't budge.";
                doorHasBeenOiled = false;
            }
            else
            {
                // Only the "PLANT" and the "DOOR" can be watered to any effect.
                output = GROUND_IS_WET;
            }
            stateOfBottle = 0;
        }
        else if (object == GameObjects.OIL && stateOfBottle == 2)
        {
            if (currentLocation == Locations.WESTPIT)
            {
                if (stateOfPlant <= 1)
                {
                    // Oiling the "PLANT" will simply confuse it.
                    output = "The plant indignantly shakes the oil off its leaves and asks: \"Water?\".";
                }
                else
                {
                    output = GROUND_IS_WET;
                }
            }
            else if (objectIsPresent(GameObjects.DOOR))
            {
                // The "DOOR" can be opened now that the hinges have been oiled.
                output = "The oil has freed up the hinges so that the door will now move, although it requires some effort.";
                doorHasBeenOiled = true;
            }
            else
            {
                // Only the "PLANT" and the "DOOR" can be oiled to any effect.
                output = GROUND_IS_WET;
            }
            stateOfBottle = 0;
        }
        return output;
    }

    private String attemptEatAction(GameObjects object)
    {
		// Most cases here don't cost a turn.
		increaseTurns = false;

        if (object == GameObjects.NOTHING)
        {
            // No object was specified to eat, so we will ask for one. If the next input
            // is a single object then we will attempt to eat it.
            actionToAttempt = ActionWords.EAT;
            return "What would you like to eat?";
        }
        else if (object == GameObjects.FOOD)
        {
            if (!objectIsPresent(GameObjects.FOOD))
            {
                // No "FOOD" to eat.
                return "You don't have any.";
            }
            else
            {
                // The "FOOD" is gone now and can no longer be fed to the "BEAR".
				increaseTurns = true;
                voidObject(GameObjects.FOOD);
                return "Thank you, it was delicious!";
            }
        }
        else
        {
            // Only the "FOOD" can be eaten.
           return "I think I just lost my appetite.";
        }
    }

	private String attemptRubAction(GameObjects object)
	{
		// The player is expected to try rubbing the "LAMP" at some point.
		if (object == GameObjects.LAMP)
		{
			return "Rubbing the electric lamp is not particularly rewarding. Anyway, nothing exciting happens.";
		}
		return "Peculiar. Nothing unexpected happens.";
	}

	private String attemptTossAction(GameObjects object, String context)
	{
		String output;
		if (object == GameObjects.ROD && isInHand(GameObjects.ROD2) && !(isInHand(GameObjects.ROD)))
		{
			// Throwing a "ROD" will simply drop it.
			output = attemptAction(ActionWords.DROP, GameObjects.ROD, "");
		}
		else if (object == GameObjects.NOTHING)
		{
			// No object was specified to throw, so we will ask for one. If the next input
			// is a single object then we will attempt to throw it.
			output = "What would you like to throw?";
			actionToAttempt = ActionWords.TOSS;
			increaseTurns = false;
		}
		else if (object == GameObjects.BEAR)
		{
			// Unleashing the "BEAR" will scare away the "TROLL" for good.
			if (stateOfBear == 2)
			{
				if (objectIsHere(GameObjects.TROLL) || objectIsHere(GameObjects.TROLL_))
				{
					trollLeaves();
					stateOfTroll = 2;
					output = "The bear lumbers toward the troll, who lets out a startled shriek and scurries away. " +
						"The bear soon gives up pursuit and wanders back.";
				}
				else
				{
					output = OKAY;
				}
				stateOfBear = 4;
				GameObjects.BEAR.location = currentLocation;
			}
			else
			{
				output = DONT_HAVE;
			}
		}
		else if (!(isInHand(object)))
		{
			output = DONT_HAVE;
			increaseTurns = false;
		}
		else if ((objectIsHere(GameObjects.TROLL_) || objectIsHere(GameObjects.TROLL)) && GameObjects.isTreasure(object))
		{
			// Throwing a treasure to the "TROLL" will make it leave temporarily at the cost of the treasure.
			voidObject(object);
			trollLeaves();
			stateOfTroll = 3;
			output = "The troll catches your treasure and scurries away out of sight.";
			if (object != GameObjects.EGGS)
			{
				// The "EGGS" can be recalled using the right magic in the "GIANT" room. Any
				// other treasure will be lost forever.
				lostTreasures++;
			}
		}
		else if (object == GameObjects.FOOD && objectIsHere(GameObjects.BEAR))
		{
			// Throwing the "FOOD" at the "BEAR" will feed it.
			output = attemptAction(ActionWords.FEED, GameObjects.BEAR, "");
		}
		else if (object != GameObjects.AXE)
		{
			// Throwing any other object besides the "AXE" will simply drop it.
			output = attemptAction(ActionWords.DROP, object, context);
		}
		else if (objectIsHere(GameObjects.DWARF))
		{
			// The player must throw the "AXE" at a "DWARF" to initiate combat.
			battleUpdate = true;
			if (AdventMain.generate() * 3 > 1)
			{
				// There is a 30% chance to kill a "DWARF" after throwing the "AXE".
				deadDwarves++;
				dwarfFlag++;
				dwarfPresent--;
				if (dwarfPresent == 0)
				{
					// There may be more than one "DWARF" present. Only remove the object
					// if all of them are gone.
					voidObject(GameObjects.DWARF);
				}
				output = "You killed a little dwarf.";
				if (deadDwarves == 1)
				{
					// Explain that the bodies disappear the first time a dwarf dies.
					output += " The body vanishes in a cloud of greasy black smoke.";
				}
			}
			else
			{
				// No luck this time.
				output = "You attack a little dwarf, but he dodges out of the way.";
			}
			drop(GameObjects.AXE);
		}
		else if ((objectIsHere(GameObjects.DRAGON_) || objectIsHere(GameObjects.DRAGON)) && dragonIsAlive)
		{
			// The "DRAGON" can't be killed this way. The player must use their bare hands.
			output = "The axe bounces harmlessly off the dragon's thick scales.";
			drop(GameObjects.AXE);
		}
		else if ((objectIsHere(GameObjects.TROLL_) || objectIsHere(GameObjects.TROLL)))
		{
			// Luckily the "TROLL" doesn't seem to realize or care the player just tried to kill them.
			output = "The troll deftly catches the axe, examines it carefully, and tosses it back, declaring, " +
				"\"Good workmanship, but it's not valuable enough.\"";
		}
		else if (objectIsHere(GameObjects.BEAR) && stateOfBear == 0)
		{
			// Trying to throw the "AXE" at the chained, untamed "BEAR" will make it unaccessible
			// until the "BEAR" is set free.
			bearAxe = true;
			Locations.placeObject(GameObjects.AXE, currentLocation);
			output = "The axe misses and lands near the bear where you can't get at it.";
		}
		else
		{
			// There is nothing here to fight, so we will simply drop the "AXE".
			output = attemptAction(ActionWords.DROP, object, context);
		}
		return output;
	}

	private String attemptBreakAction(GameObjects object)
	{
		increaseTurns = false;

		if (object == GameObjects.NOTHING)
		{
			return CANT_BE_SERIOUS;
		}
		else if (object == GameObjects.VASE)
		{
			if (objectIsPresent(GameObjects.VASE))
			{
				// The player has decided to break the "VASE" on purpose. We will let them because
				// this is a beautiful game.
				increaseTurns = true;
				vaseIsBroken = true;
				lostTreasures++;
				drop(GameObjects.VASE);
				return "You have taken the vase and hurled it delicately to the ground.";
			}
			return DONT_HAVE;
		}
		else if (object == GameObjects.MIRROR)
		{
			if (caveIsClosed)
			{
				// It is possible for the player to break the "MIRROR" at the end of the game, but it kills them.
				increaseTurns = true;
				playerIsDead = true;
				playerJustDied = true;
				return "You strike the mirror a resounding blow, whereupon it shatters into a myriad tiny fragments.";
			}
			else if (objectIsHere(GameObjects.MIRROR) || objectIsHere(GameObjects.MIRROR_))
			{
				return "It is too far up for you to reach.";
			}

			return "It is beyond your power to do that.";
		}

		return OKAY_BUT_HOW;
	}

	private String attemptSayAction(String context)
	{
		if (context.isEmpty())
		{
			increaseTurns = false;
			return "What do you want to say?";
		}
		else
		{
			return "Okay, \"" + context + "\".";
		}
	}

	private String attemptReadAction()
	{
		if (!canISee(currentLocation))
		{
			increaseTurns = false;
			return "You can't read in the dark!";
		}
		else if (objectIsPresent(GameObjects.MAG))
		{
			increaseTurns = false;
			return "I'm afraid the magazine is written in dwarvish.";
		}
		else if (objectIsPresent(GameObjects.TABLET))
		{
			return "'CONGRATULATIONS ON BRINGING LIGHT INTO THE DARK ROOM!'";
		}
		else if (objectIsPresent(GameObjects.MESSAGE))
		{
			return "'This is not the maze where the pirate hides his treasure chest.'";
		}
		else if (caveIsClosed && objectIsPresent(GameObjects.OYSTER))
		{
			if (Hints.BLAST.proc > 0)
			{
				return "It says the same thing it did before.";
			}
			else
			{
				questionAsked = Questions.READBLASTHINT;
				offeredHint = Hints.BLAST;
				return "Hmmm, this looks like a clue, which means it'll cost you 10 points to read it. Should I go ahead and read it anyway?";
			}
		}
		else
		{
			return OKAY_BUT_HOW;
		}
	}

	private String attemptBriefAction()
	{
        increaseTurns = false;
		if (brief == 0)
		{
			brief = 1;
			return "Okay, from now on I'll only describe a place in full the first time you come to it. To get the full description, say \"LOOK\".";
		}

		brief = 0;
		return  "Okay, I'll return to giving descriptions in the original fashion.";
	}

	private String attemptVerboseAction()
	{
		// TODO: Check brief and verbose.
        increaseTurns = false;
		if (brief == 0)
		{
			brief = 2;
			return "Okay, from now on I'll describe a place in full every time you come to it.";
		}

		brief = 0;
		return "Okay, I'll return to giving descriptions in the original fashion.";
	}

	private String attemptFindAction(GameObjects object)
	{
		increaseTurns = false;
		if (isInHand(object))
		{
			return "You are already carrying it!";
		}
		else if (objectIsPresent(object))
		{
			increaseTurns = true;
			return "I believe what you want is right here with you.";
		}

		return "I can only tell you what you see as you move about and manipulate things. I can not tell you where remote things are.";
	}

	private String attemptInventoryAction()
	{
		String output;
		increaseTurns = false;
		if (itemsInHand > 0)
		{
			output = "\t\t-----" + GameObjects.listItemsHere(Locations.INHAND) + "\n\t\t-----";
		}
		else
		{
			output = "\t\t-----\n\t\tYou're not carrying anything.\n\t\t-----";
		}

		if (stateOfBear == 2)
		{
			// The "BEAR" isn't actually in inventory, but we make note of it as well if it is following the player.
			output += GameObjects.BEAR.descriptions[2];
		}
		return output;
	}

	private String attemptFeedAction(GameObjects object, String context)
	{
		increaseTurns = false;

		if (object == GameObjects.TROLL && (objectIsHere(GameObjects.TROLL) || objectIsHere(GameObjects.TROLL_)))
		{
			increaseTurns = true;
			return "Gluttony is not one of the troll's vices. Avarice, however, is.";
		}
		else if (object == GameObjects.DRAGON && (objectIsHere(GameObjects.DRAGON) || objectIsHere(GameObjects.DRAGON_)))
		{
			if (!dragonIsAlive)
			{
				return "Don't be ridiculous!";
			}

			return "There is nothing here it wants to eat (except perhaps you).";
		}
		else if (objectIsHere(object))
		{
			if (object == GameObjects.BIRD)
			{
				return "It's not hungry (it's merely pinin' for the fjords). Besides, you have no bird seed.";
			}
			else if (object == GameObjects.SNAKE)
			{
				if (!caveIsClosed && objectIsPresent(GameObjects.BIRD))
				{
					// This will make it impossible to drive off the "SNAKE".
					increaseTurns = true;
					voidObject(GameObjects.BIRD);
					return "The snake has now devoured your bird.";
				}

				return "There is nothing here it wants to eat (except perhaps you).";
			}
			else if (object == GameObjects.BEAR)
			{
				if (objectIsPresent(GameObjects.FOOD))
				{
					// Feeding the "BEAR" will tame it.
					increaseTurns = true;
					voidObject(GameObjects.FOOD);
					stateOfBear = 1;
					bearAxe = false;
					return "The bear eagerly wolfs down your food, after which he seems to calm down considerably and even becomes rather friendly.";
				}

				return "There is nothing here to eat.";
			}
			else if (object == GameObjects.DWARF)
			{
				if (isInHand(GameObjects.FOOD))
				{
					// Trying to feed a "DWARF" will increase the chance of their knives hitting the player.
					increaseTurns = true;
					dwarfFlag++;
					return "You fool, dwarves eat only coal! Now you've made him REALLY mad!";
				}

				return "There is nothing here to eat.";
			}
			else
			{
				return OKAY_BUT_HOW;
			}
		}
		else
		{
			if (object == GameObjects.NOTHING)
			{
				actionToAttempt = ActionWords.FEED;
				return "What would you like to feed?";
			}
			else
			{
				return DONT_SEE_ANY + context + ".";
			}
		}
	}

	private String attemptWakeAction(GameObjects object)
	{
		if (caveIsClosed && object == GameObjects.DWARF)
		{
			// This command can only be used to wake the "DWARF" at the end of the game, and it means death.
			playerIsDead = true;
			playerJustDied = true;
			fatality = 2;
			return "You wake the nearest dwarf, who wakes up grumpily, takes one look at you, curses, and grabs for his axe.";
		}

		increaseTurns = false;
		return CANT_BE_SERIOUS;
	}

	private String attemptDrinkAction(GameObjects object)
	{
		boolean waterIsHere = currentLocation.hasWater;

		if (isInHand(GameObjects.BOTTLE) && stateOfBottle == 1)
		{
			stateOfBottle = 0;
			return "The bottle of water is now empty.";
		}
		else if (waterIsHere)
		{
			return "You have taken a drink from the stream. The water tastes strongly of minerals, but is not unpleasant. It is extremely cold.";
		}
		else if (object != GameObjects.WATER && object != GameObjects.NOTHING)
		{
			increaseTurns = false;
			return CANT_BE_SERIOUS;
		}

		return "You have nothing to drink.";
	}

	private String attemptLookAction(GameObjects object)
	{
		battleUpdate = true;
		if (!canISee(currentLocation))
		{
			return "You have no source of light.";
		}
		else if (object == GameObjects.NOTHING)
		{
			return Locations.getDescription(currentLocation, 2) + "\n" + GameObjects.listItemsHere(currentLocation);
		}

		return "Sorry, but I am not allowed to give more detail. I will repeat the long description of your location.\n\n" + Locations.getDescription(currentLocation, 2);
	}

	private String attemptFillAction(GameObjects object)
	{
		// The "EASTPIT" is the only location with "OIL"; the only liquid in the game besides "WATER".
		boolean liquidHere = (currentLocation.hasWater || currentLocation == Locations.EASTPIT);
		increaseTurns = false;

		if (object == GameObjects.VASE)
		{
			if (!liquidHere)
			{
				return "There is nothing here with which to fill the vase.";
			}
			else if (!isInHand(GameObjects.VASE))
			{
				return "You aren't carrying it!";
			}
			else
			{
				// The "VASE" is very fragile and attempting to fill it is one of many ways to break it.
				increaseTurns = true;
				vaseIsBroken = true;
				drop(GameObjects.VASE);
				lostTreasures++;
				return "The sudden change in temperature has delicately shattered the vase.";
			}
		}
		else if (object != GameObjects.NOTHING && object != GameObjects.BOTTLE)
		{
			return "You can not fill that.";
		}
		else if (!isInHand(GameObjects.BOTTLE))
		{
			// If no object is specified to fill we assume the player is trying to fill the "BOTTLE",
			// which is why we check for the presence of the "BOTTLE" even if the given object is "NOTHING".
			if (object == GameObjects.NOTHING)
			{
				return "You have nothing to fill.";
			}
			else
			{
				return "You are not carrying it!";
			}
		}
		else if (stateOfBottle != 0)
		{
			// The bottle is already full of something.
			return BOTTLE_FULL;
		}
		else if (!liquidHere)
		{
			return "You have nothing with which to fill the bottle.";
		}
		else if (currentLocation == Locations.EASTPIT)
		{
			// If the player is in the "EASTPIT" then they must be trying to fill the bottle with "OIL".
			increaseTurns = true;
			stateOfBottle = 2;
			return "You fill the bottle with oil.";
		}
		else
		{
			increaseTurns = true;
			stateOfBottle = 1;
			return "You fill the bottle with water.";
		}
	}

	private String attemptBlastAction()
	{
		if (caveIsClosed)
		{
			over = true;
			// If "ROD2" is present when blasted, then the player is blown up because they are standing near the
			// dynamite. The player earns 25 bonus points for finishing the game. If the "ROD2" is detonated at
			// the "SWEND", the wall it destroys lets in a rush of lava. The player burns to death and is awarded 30
			// bonus points for finishing the game. If "ROD2" is detonated at the "NEEND" and the player has moved to
			// the other end of the room, then the wall they destroyed leads them to the Main Office. They finish the
			// game intact and are awarded 45 bonus points for getting the best ending.
			bonus = objectIsPresent(GameObjects.ROD2) ? 25 : currentLocation == Locations.NEEND ? 30 : 45;
			return switch (bonus)
			{
				case 25 ->
					"There is a loud explosion, and you are suddenly splashed across the walls of the room.";
				case 30 ->
					"There is a loud explosion, and a twenty-foot hole appears in the far wall, burying the snakes in the rubble. " +
						"A river of molten lava pours in through the hole, destroying everything in its path, including you!";
				default ->
					"There is a loud explosion, and a twenty-foot hole appears in the far wall, burying the dwarves in the rubble. " +
						"You march through the hole and find yourself in the Main Office, where a cheering band of friendly elves carry the conquering adventurer off into the sunset.";
			};
		}

		// The "ROD2" with a rusty mark instead of a star is dynamite, and it is only available at the end of the game.
		return "Blasting requires dynamite.";
	}

	private String attemptFeeFieAction(String context)
	{
		String output;
		// This bit of magic is unique because it is a whole phrase that must be given a word at a time
		// instead of a single word that works immediately. It these words must be, in order, "FEE", "FIE",
		// "FOE", and "FOO" (the last word being an intentional subversion of expectations). If given
		// correctly this phrase will teleport the golden "EGGS" from wherever they are to the "GIANT" room.
		// The player can use this to pass the "TROLL" and pay his fee without losing any treasures.
		boolean inputMatchesNextWord = context.equals(FEE_FIE_FOE[fooMagicWordProgression]);

		if (inputMatchesNextWord)
		{
			if (fooMagicWordProgression < 3)
			{
				output = OKAY;
				fooMagicWordProgression++;
			}
			else
			{
				fooMagicWordProgression = 0;
				if (GameObjects.EGGS.location == Locations.GIANT)
				{
					// Nothing happens because the "EGGS" are already in the "GIANT" room.
					output = NOTHING;
				}
				else
				{
					if (currentLocation != Locations.GIANT)
					{
						if (objectIsPresent(GameObjects.EGGS))
						{
							// The "EGGS" were at the player's location, so they can be seen to disappear.
							output = "The nest of golden eggs disappears!";
						}
						else
						{
							// The player doesn't have to have the "EGGS" present or be in the "GIANT" room to use
							// the magic, but since they can't see what happens we won't tell them what it does.
							output = "Done!";
						}
					}
					else
					{
						// The player is in the "GIANT" room so they can see the "EGGS" appear.
						output = "There is a large nest here, full of golden eggs!";
					}
					place(GameObjects.EGGS, Locations.GIANT);
				}
			}
		}
		else
		{
			// The player used the magic words in the wrong order or said "FUM" at the end instead of "FOO".
			if (fooMagicWordProgression > 0)
			{
				output = "What's the matter, can't you read? Now you'd best start over.";
			}
			else
			{
				output = "Nothing happens.";
			}
			fooMagicWordProgression = 0;
		}
		return output;
	}


	private String attemptMovement(String input)
	{
		input = AdventMain.truncate.apply(input);
		var word = knownWords.getOrDefault(input, GameObjects.NOTHING);
		if (!(word instanceof Movement))
		{
			increaseTurns = false;
			return CAN_NOT_DO_THAT;
		}

		return attemptMovement((Movement) word);
	}

	private String attemptMovement(Movement destination)
	{
		if (destination == Movement.NOWHERE)
		{
			return OKAY;
		}
		else if (destination == Movement.CAVE)
		{
			increaseTurns = false;

			if (Locations.outsideCave(currentLocation))
			{
				return "I can't see where the cave is, but hereabouts no stream can run on the surface for long. I would try the stream.";
			}

			return "I need more detailed instructions to do that.";
		}

		String output = "";
		Locations locationResult;
		locationChange = true;

		if (destination == Movement.BACK)
		{
			// TODO: can we go back if it's dark?
			return returnToLastLocation();
		}

		locationResult = Locations.moveTo(destination, currentLocation);

		if (justCollapsed)
		{
			// The player died in a collapse while attempting to move locations.
			playerIsDead = true;
			playerJustDied = true;
			justCollapsed = false;
			fatality = 3;
		}
		else
		{
			output = switch (locationResult)
			{
				case VOID -> resolveVoidedMovement(destination, output);
				case REMARK -> resolveMovementRemark(destination);
				default -> resolveRegularMovement(locationResult);
			};
		}

		if (relocate)
		{
			// TODO: Possible to remove this flag and drop emerald during movement resolution?
			place(GameObjects.EMERALD, Locations.PROOM);
			setRelocate(false);
		}

		return output;
	}

	private String returnToLastLocation()
	{
		String output = "You can't get there from here.";

		// TODO: can we go back if it's dark?
		if (previousLocation == currentLocation)
		{
			// The player got to their current location by irregular means, such as forced movement.
			output = "Sorry, but I no longer seem to remember how you got here.";
		}
		else
		{
			Locations[] possibleExits = Locations.POSSIBLE_SIMPLE_EXITS.get(currentLocation);

			for (Locations exit : possibleExits)
			{
				if (exit == previousLocation)
				{
					// The player's previous location can be reached from here. Move there normally.
					return resolveRegularMovement(exit);
				}
			}
		}

		increaseTurns = false;
		locationChange = false;
		return output;
	}

	private String resolveVoidedMovement(Movement destination, String output)
	{
		// A "VOID" destination is used to indicate that the player attempted to move with a valid movement
		// keyword and failed because their current location doesn't have a destination mapped to the given input.
		if (destination == Movement.XYZZY || destination == Movement.PLOVER || destination == Movement.PLUGH)
		{
			// The player attempted to use movement magic from an unsupported location.
			return NOTHING;
		}

		increaseTurns = false;
		if (!canISee(currentLocation))
		{
			// There was nowhere to go in the indicated direction, but the player doesn't know
			// that because it is too dark for them to see anything right now.
			return pitchDark(output);
		}
		else if (destination == Movement.UP || destination == Movement.DOWN ||
			(destination.ordinal() >= Movement.EAST.ordinal() && destination.ordinal() <= Movement.NORTHWEST.ordinal()))
		{
			// The player tried moving in a direction where there are no exits.
			return "There are no exits in that direction.\n" + getDescription(currentLocation, brief);
		}
		else
		{
			// The player tried indicating a nearby location to quickly move there directly, but the
			// location they named can't be quickly navigated to from their current location.
			return "I don't know how to apply that word here.\n";
		}
	}

	private String resolveMovementRemark(Movement destination)
	{
		// TODO: Check the logic for SMALLPIT, NE/SWSIDE, EAST/WESTFISSURE

		// The player entered a valid movement command resulting in a 'remark' specific to the current location, the
		// specific action attempted, and/or the current state of present objects. This usually results in a message
		// being output explaining why the movement wasn't possible or how it leads to death. Those few remarks
		// that do result in player movement bypass the usual movement resolution checks, such as 'critter' movement.
		increaseTurns = false;

		switch (currentLocation)
		{
			case SHELL:
				return "You can't fit this five-foot " + (isInHand(GameObjects.CLAM) ? "clam" : "oyster") + " through that little passage!";

			case PROOM, DROOM, ALCOVE:
				return "Something you are carrying won't fit through the tunnel with you. You'd best take inventory and drop something.";

			case NESIDE, SWSIDE:
				if (stateOfTroll == 0)
				{
					return "The troll refuses to let you cross.";
				}
				else if (stateOfTroll == 1)
				{
					stateOfTroll = 0;
					voidObject(GameObjects.TROLL2);
					voidObject(GameObjects.TROLL2_);
					place(GameObjects.TROLL, Locations.SWSIDE);
					place(GameObjects.TROLL_, Locations.NESIDE);
					return "The troll steps out from beneath the bridge and blocks your way.";
				}

				if (destination == Movement.JUMP)
				{
					if (isCollapse())
					{
						playerIsDead = true;
						playerJustDied = true;
						return "You didn't make it.";
					}
					else
					{
						return "I respectfully suggest that you go across the bridge instead of jumping.";
					}
				}

				return "There is no longer any way across the chasm.";

			case WESTFISSURE:
				if (destination == Movement.NORTH)
				{
					increaseTurns = true;
					setLocation(Locations.WESTMIST);
					return HOM_PASSAGE + getDescription(currentLocation, brief);
				}
				// Lack of break here is intentional.
			case EASTFISSURE:
				if (destination == Movement.JUMP)
				{
					if (isCrystalBridge())
					{
						return "I respectfully suggest that you go across the bridge instead of jumping.";
					}
					else
					{
						playerIsDead = true;
						playerJustDied = true;
						return "You didn't make it.";
					}
				}
				return "There is no way to cross the fissure.";

			case WITT, BEDQUILT, CHEESE:
				return "You have crawled around in some little holes and "
					+ (currentLocation == Locations.WITT && destination == Movement.WEST
					   ? "found your way blocked by a recent cave-in.\nYou are now back in the main passage."
					   : "wound up back in the main passage.");

			case SLIT, WET:
				return "You can't fit through a two-inch slit!";

			case INSIDE, OUTSIDE:
				return "You can't go through a locked steel grate!";

			case MTKHALL:
				return "You can't get by the snake.";

			case IMMENSE:
				return "The door is extremely rusty and refuses to open.";

			case SCAN1, SCAN3:
				return "That dragon looks rather nasty. You'd best not try to get by.";

			case VIEW:
				return "Don't be ridiculous!";

			case SMALLPIT:
				if (destination == Movement.CRACK || destination == Movement.WEST)
				{
					return "That crack is far too small for you to follow.";
				}
				// Lack of break here is intentional.
			case EWINDOW, NARROW, WWINDOW:
				playerIsDead = true;
				playerJustDied = true;
				return "You are at the bottom of the pit with a broken neck.";

			case EASTMIST:
				return "The dome is unclimbable.";

			case WESTMIST:
				increaseTurns = true;
				setLocation(Locations.WESTFISSURE);
				return HOM_PASSAGE + getDescription(currentLocation, brief);

			case WESTPIT:
				switch (stateOfPlant)
				{
					case 1:
						increaseTurns = true;
						setLocation(Locations.WEST2PIT);
						return "You have climbed up the plant and out of the pit.\n" + getDescription(currentLocation, brief);

					case 2:
						increaseTurns = true;
						setLocation(Locations.NARROW);
						return "You clamber up the plant and scurry through the hole at the top.\n" + getDescription(currentLocation, brief);

					// TODO: Right now only the WESTPIT can send us here, but other locations are treated as a
					//  possibility below. Check this.

					default:
						return "There is nothing here to climb." + (currentLocation == Locations.WESTPIT ? " Use \"UP\" or \"OUT\" to leave the pit." : "");
				}

                // TODO: Check that SNAKED was accounted for somewhere.

			case BUILDING:
				return "The stream flows out through a pair of 1-foot-diameter sewer pipes.\nIt would be advisable to use the exit.";

			default:
				return CAN_NOT_DO_THAT;
		}
	}

	private String resolveRegularMovement(Locations locationResult)
	{
		String output = "";
		if (caveIsClosing && Locations.outsideCave(locationResult))
		{
			// The player can't leave the cave once it has started closing.
			if (!extraMovesForPanic)
			{
				// Give the player a few extra turns the first time they try to leave the cave while closing.
				clock2 = 15;
				extraMovesForPanic = true;
			}
			increaseTurns = false;
			return "A mysterious recorded voice groans into life and announces: \n\t\"This exit is closed. Please leave via main office.\"";
		}

		// If there is a dwarf here they have a 20% chance of preventing player movement.
		if (dwarfPresent > 0 && Locations.crittersAllowed(locationResult) && AdventMain.generate() < .20)
		{
			battleUpdate = true;
			locationChange = false;
			return "A little dwarf with a big knife blocks your way.";
		}

		// We are finally ready to actually move the player.
		setLocation(locationResult);

		// See if they died in a pit trying to move around in the dark.
		if (!canISee(currentLocation))
		{
			// TODO: Verify that we don't do any critter checks in the dark.
			return pitchDark(output);
		}

		// Check for critter encounters or movement.
		if (Locations.crittersAllowed(currentLocation))
		{
			double chance = AdventMain.generate() * 100;

			// The likelihood of encountering the pirate increases very slightly every time the player moves
			// to a location where he could appear without seeing him. This makes it just a little less likely
			// for the pirate to ruin the player's chances of attaining Grandmasterhood by never showing up.
			if (pirate == 0)
			{
				movesWOEncounter++;
				if (chance <= movesWOEncounter * PIRATE_ENCOUNTER_MODIFIER)
				{
					pirate = 1;
				}
			}

			if (pirate != 1 && DWARVES_ALLOWED && dwarfFlag > 0 && dwarvesLeft > dwarfPresent)
			{
				chance = AdventMain.generate() * 100;
				// TODO: rework chance?

				if (chance <= (dwarvesLeft - dwarfPresent) * DWARF_ENCOUNTER_MODIFIER)
				{
					// if (GameObjects.DWARF.location != Locations.VOID && GameObjects.DWARF.location != currentLocation)
					// {
					// 	returnPresentDwarves();
					// }

					dwarvesLeft -= 1;//(byte) (dwarfFlag == 1 ? 0 : 1);
					dwarfPresent++;

					drop(GameObjects.DWARF);
				}
				newDwarf = true;
			}
		}

		// We finish up with a description of the new location.
		output = getDescription(currentLocation, brief);

		if (stateOfBear == 2)
		{
			output += "\n\tYou are being followed by a very large, tame bear.";
		}

		if (currentLocation.equals(Locations.Y2) && AdventMain.generate() > .74)
		{
			output += "\n\nA hollow voice says \"PLUGH\".";
		}

		return output;
	}

	private String pitchDark(String output)
	{
		// If the player's destination and previous location are both dark we will check to see if they
		// fell into a pit. If the player is still alive after the check we will remind them how dangerous
		// it is to move around the cave in the dark.
		if (!canISee(previousLocation) && fallInPit())
		{
			playerIsDead = true;
			playerJustDied = true;
			fatality = 1;
			return output;
		}
		else
		{
			return "It is now pitch dark. If you proceed you will likely fall into a pit.";
		}
	}

	private void setLocation(Locations newLocation)
	{
		previousLocation = currentLocation;
		currentLocation = newLocation;
	}

	private boolean fallInPit()
	{
		return AdventMain.generate() < .35;
	}

	private String getDescription(Locations here, int brief)
	{
		String output = Locations.getDescription(here, brief);
		output += GameObjects.listItemsHere(currentLocation);
		return output;
	}

	private boolean canISee(Locations here)
	{
		return (currentLocation.dontNeedLamp(here)) || (lampIsLit && objectIsPresent(GameObjects.LAMP));
	}


	private String death()
	{
		String output = fatality - 1 > -1 ? DEATH_MESSAGES[fatality - 1] : "";

		// Relocate the "LAMP" to the "ROAD" outside the "BUILDING" if the player was carrying it when the died.
		if (isInHand(GameObjects.LAMP))
		{
			place(GameObjects.LAMP, Locations.ROAD);
			lampIsLit = false;
		}

		// TODO: Ensure items are left at the last "safe" location.
		// Dump everything in inventory at the death location.
		attemptAction(ActionWords.DROP, GameObjects.ALL, SYSTEM_ACTION, OKAY_BUT_HOW);

		// Held treasures are lost if the player died in a collapse.
		if (fatality == 3)
		{
			for (GameObjects object : GameObjects.values())
			{
				// TODO: Verify this. What happens to items when the bridge collapses?
				if (GameObjects.isTreasure(object) && object.location.ordinal() >= Locations.NESIDE.ordinal() && object != GameObjects.EGGS)
				{
					lostTreasures++;
				}
			}
		}

		currentLocation = Locations.BUILDING;
		previousLocation = Locations.BUILDING;
		fatality = 0;
		returnPresentDwarves();

		if (caveIsClosing)
		{
			over = true;
			return output + "\n\nIt looks as though you you're dead. Well, seeing as how it's so close to closing time anyway, let's just call it a day.";
		}
		else
		{
			questionAsked = Questions.RESURRECT;
			livesLeft--;
			return output + RES_OFFER[livesLeft];
		}
	}

	private String quit(String output)
	{
		getCurrentScore();

		StringBuilder outputString = new StringBuilder();
		if (output != null)
		{
			outputString.append(output).append("\n\n");
		}

		outputString.append(String.format("\tYou scored %d points out of a possible 350, using %d turn", score, turns));

		if (turns > 1)
		{
			outputString.append("s");
		}
		outputString.append(".\n\t");

		boolean found = false;
		for (int i = 0; i < 9; i++)
		{
			if (!found && score <= AdventMain.SCORES[i])
			{
				found = true;
				outputString.append(S_MESSAGES[i]).append("\n\tTo achieve the next higher rating");
				if (i < 8)
				{
					int next = 1 + AdventMain.SCORES[i] - score;
					String s = next == 1 ? "" : "s";
					outputString.append(String.format(", you need %d more point%s.", score, s));
				}
				else
				{
					outputString.append(" would be a neat trick!\n\tCongratulations!!");
				}
			}
		}

		outputString.append("\n\n\n> Press Enter to Play Again <");
		return outputString.toString();
	}

	private String passageOfTime(String output)
	{
		if (clock2 == 0)
		{
			// The cave has closed.
			output = "The sepulchral voice intones, \n\t\"The cave is now closed.\"\n\nAs the echoes fade, there is a blinding flash of light (and a small puff of orange smoke)..."
				+ "\nThen your eyes refocus: you look around and find...\n\n" + Locations.getDescription(currentLocation, brief);
			caveIsClosed = true;
			bonus = 10;

			attemptAction(ActionWords.DROP, GameObjects.ALL, SYSTEM_ACTION);
			attemptAction(ActionWords.OFF, GameObjects.NOTHING, SYSTEM_ACTION);

			currentLocation = Locations.SWEND;
			drop(GameObjects.GRATE);	drop(GameObjects.SNAKE);	drop(GameObjects.BIRD);		drop(GameObjects.CAGE);
			drop(GameObjects.ROD2);		drop(GameObjects.PILLOW);	drop(GameObjects.MIRROR);

			currentLocation = Locations.NEEND; previousLocation = Locations.NEEND;
			drop(GameObjects.BOTTLE);	drop(GameObjects.PLANT);	drop(GameObjects.OYSTER);	drop(GameObjects.LAMP);
			drop(GameObjects.ROD);		drop(GameObjects.DWARF);	drop(GameObjects.MIRROR);

			endGameObjectsStates = new boolean[] { true, true, true, true, true, true, true, true, true, true };
			stateOfPlant = 3;
			clock1 = -2;
			clock2 = -2;
		}
		else if (clock1 == -1)
		{
			// The cave is closing.
			clock2--;
		}
		else if (clock1 == 0)
		{
			// The cave will now start to close.
			output = "A sepulchral voice, reverberating through the cave, says, \n\t\"Cave closing soon. All adventurers exit immediately through main office.\"";
			clock1 = -1;
			caveIsClosing = true;
			grateIsUnlocked = false;
			dwarvesLeft = 0;
			stateOfTroll = 2;
			trollLeaves();
			voidObject(GameObjects.BEAR);
		}
		else if (tally == 15 && !(Locations.outsideCave(currentLocation)) && currentLocation != Locations.Y2)
		{
			// All treasures have been found. The cave will start closing soon.
			clock1--;
		}
		else if (lampIsLit && !(caveIsClosing || caveIsClosed))
		{
			if (lamp < 0 && currentLocation.outside(currentLocation))
			{
				output += "\n\nThere's not much point in wandering around out here, and you can't explore the gave without a lamp. So let's just call it a day.";
				over = true;
			}
			else if (lamp < 0)
			{
				output += "\nYour lamp has run out of power.";
				if (!canISee(currentLocation))
				{
					output += "\nIt is now pitch dark. If you proceed you will likely fall into a pit.";
				}
				lampIsLit = false;
			}
			else if (lamp < 31 && spareBatteries == 1 && objectIsPresent(GameObjects.BATTERIES) && objectIsPresent(GameObjects.LAMP))
			{
				output += "\n\nYour lamp is getting dim. I'm taking the liberty of replacing the batteries.";
				place(GameObjects.BATTERIES, currentLocation);
				spareBatteries = 2;
				lamp = 2500;
			}
			else if (lamp < 31 && !lowBatteryWarning)
			{
				output += "\n\nYour lamp is getting dim" + BATTERY_WARNING[spareBatteries];
				lowBatteryWarning = true;
			}
			else
			{
				lamp--;
			}
		}
		return output;
	}


	public boolean isInHand(GameObjects thing)
	{
		return thing.location == Locations.INHAND;
	}

	public boolean objectIsHere(GameObjects thing)
	{
		return thing.location == currentLocation;
	}

	public boolean objectIsPresent(GameObjects thing)
	{
		return objectIsHere(thing) || isInHand(thing);
	}

	public void voidObject(GameObjects thing)
	{
		Locations.placeObject(thing, Locations.VOID);
	}

	public void take(GameObjects thing)
	{
		Locations.placeObject(thing, Locations.INHAND);
	}

	public void drop(GameObjects thing)
	{
		Locations.placeObject(thing, currentLocation);
	}

	public void place(GameObjects thing, Locations place)
	{
		Locations.placeObject(thing, place);
	}


	private String checkForHints()
	{
		// Some hints are only given if you've been somewhere for a while.
		boolean justArrived = (locationAtStartOfAction != currentLocation);

		// Hints are only given when the cave is still open.
		if (!caveIsClosed)
		{
			// Check to see if we need to increment the proc count for a hint. Proc count for the "BIRD" and "WEST"
			// hints are increased elsewhere. For the rest of the hints, the proc counter only increases when you
			// fail to perform an action in addition to meeting any other conditions.
			if (actionToAttempt == ActionWords.NOTHING)
			{
				if (!grateIsUnlocked && currentLocation == Locations.OUTSIDE && !justArrived && !objectIsPresent(GameObjects.KEYS))
				{
					// The player has been outside the locked grate with no keys for at least one turn.
					Hints.GRATE.proc();
				}

				if (objectIsPresent(GameObjects.SNAKE) && !objectIsPresent(GameObjects.BIRD) && !justArrived)
				{
					// The player has been in the same room as the snake without the bird for at least one turn.
					Hints.SNAKE.proc();
				}

				if ((currentLocation.ordinal() >= Locations.ALIKE1.ordinal() && currentLocation.ordinal() <= Locations.ALIKE14.ordinal()) ||
					(currentLocation != Locations.DEAD2 && currentLocation != Locations.DEAD8 && currentLocation.ordinal() >= Locations.DEAD1.ordinal() && currentLocation.ordinal() <= Locations.DEAD11.ordinal()))
				{
					// The player is in the "All Alike Maze".
					Hints.MAZE.proc();
				}

				if (currentLocation == Locations.ALCOVE || (currentLocation == Locations.PROOM && !objectIsPresent(GameObjects.LAMP)))
				{
					// The player is in the plover room or adjoining alcove without the lamp.
					Hints.DARK.proc();
				}

				if (currentLocation == Locations.WITT)
				{
					// The player is at Witt's end.
					Hints.WITT.proc();
				}
			}

			// Check to see if we need to give any hints.
			for (Hints hint : Hints.values())
			{
				if (hint.proc == hint.threshold && questionAsked == Questions.NONE && hintToOffer == Hints.NONE && offeredHint == Hints.NONE)
				{
					// We meet the threshold and there were no questions asked or hints offered yet this turn.
					if (hint == Hints.WEST)
					{
						// The "WEST" hint is free, so we give it straight away without asking if the threshold is met.
						hint.proc++;
						hint.given = true;
						return hint.hint;
					}
					else
					{
						// Offer the player the hint.
						return confirmIntentBeforeHint(hint);
					}
				}
			}
		}

		return EMPTY;
	}

	private void resetHintAndQuestionStatus()
	{
		questionAsked = Questions.NONE;
		hintToOffer = Hints.NONE;
		offeredHint = Hints.NONE;
	}

	private String confirmIntentBeforeHint(Hints hint)
	{
		// We will ask the player if they want the given hint and tell them the score cost if they accept.
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

		if (lamp > 30)
		{
			lamp += 30 * hint.cost;
		}

		return hint.hint +
			(hint == Hints.DARK ?
			 objectIsPresent(GameObjects.EMERALD) ? "" :
			 " None of the objects available is immediately useful for discovering the secret." : "");
	}


	public void updateFoundTreasure(GameObjects thing)
	{
		if (thing == GameObjects.RUG_)
		{
			thing = GameObjects.RUG;
		}

		if (GameObjects.isTreasure(thing) && !objectFound.get(thing))
		{
			objectFound.put(thing, true);
			tally++;
		}
	}

	/**
	 * Get the current game score.
	 */
	private int getCurrentScore()
	{
		// Initial score is based on the found treasure tally and lives left.
		int currentScore = 2 + (2 * tally) + (livesLeft * 10);

		// Add the score for each intact treasure currently in the building.
		for (GameObjects item : GameObjects.values())
		{
			if (item.location == Locations.BUILDING && GameObjects.isTreasure(item) && !(item == GameObjects.VASE && vaseIsBroken))
			{
				currentScore += (GameObjects.isLesserTreasure(item) ? 10 : (item == GameObjects.CHEST ? 12 : 14 ));
			}
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

	/**
	 * Return a response to "nonsense" user input that we couldn't understand.
	 */
	private String nonsense()
	{
		increaseTurns = false;
		double randomOutput = AdventMain.generate();

		if (randomOutput < .34)
		{
			return "I have no idea what you are talking about!";
		}
		else if (randomOutput < .67)
		{
			return "I don't understand that!";
		}
		else
		{
			return "You're not making any sense!";
		}
	}

	/**
	 * Determine if the given input is counted as a yes or no response.
	 */
	private int responseYesNo(String input)
	{
		return input.matches("^n$|^no$|nope|nah") ? 2 :
			   input.matches("^y$|yes|yeah|^ok$|okay|sure") ? 1 : 0;
	}

	/**
	 * Collapse the troll bridge.
	 */
	public void collapseBridge()
	{
		collapse = true;
		justCollapsed = true;
		stateOfBear = 3;
	}

	private void trollLeaves()
	{
		// "TROLL2" is actually an object with a description saying there is no troll.
		voidObject(GameObjects.TROLL);
		voidObject(GameObjects.TROLL_);
		place(GameObjects.TROLL2, Locations.SWSIDE);
		place(GameObjects.TROLL2_, Locations.NESIDE);
	}

	/**
	 * Reset the troll state.
	 */
	public void setTroll()
	{
		stateOfTroll = 1;
	}

	public void setRelocate(boolean relocate)
	{
		this.relocate = relocate;
	}

	public void setItemsInHand(byte itemsInHand)
	{
		this.itemsInHand = itemsInHand;
	}


	public boolean isDead()
	{
		return playerIsDead;
	}

	public boolean isOver()
	{
		return over;
	}

	public int getTurns()
	{
		return turns;
	}

	public boolean isCollapse()
	{
		return collapse;
	}

	public boolean isGrateUnlocked()
	{
		return grateIsUnlocked;
	}

	public boolean isCrystalBridge()
	{
		return crystalBridge;
	}

	public boolean isLampLit()
	{
		return lampIsLit;
	}

	public boolean isSnakeInHotMK()
	{
		return snakeInHotMK;
	}

	public boolean doorHasBeenOiled()
	{
		return doorHasBeenOiled;
	}

	public boolean isDragonAlive()
	{
		return dragonIsAlive;
	}

	public boolean isBirdInCage()
	{
		return birdInCage;
	}

	public boolean isBearAxe()
	{
		return bearAxe;
	}

	public boolean isVaseBroken()
	{
		return vaseIsBroken;
	}

	public boolean isGoldInInventory()
	{
		return goldInInventory;
	}

	public byte getItemsInHand()
	{
		return itemsInHand;
	}

	public byte getStateOfPlant()
	{
		return stateOfPlant;
	}

	public byte getStateOfBottle()
	{
		return stateOfBottle;
	}

	public byte getStateOfTroll()
	{
		return stateOfTroll;
	}

	public byte getStateOfBear()
	{
		return stateOfBear;
	}

	public byte getStateOfChain()
	{
		return stateOfChain;
	}

	public byte getSpareBatteries()
	{
		return spareBatteries;
	}

	public int getScore()
	{
		return (questionAsked == Questions.INSTRUCTIONS ? 0 : score);
	}
}