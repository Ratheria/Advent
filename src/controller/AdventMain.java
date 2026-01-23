/**
 * @author Ariana Fairbanks
 */

package controller;

import java.util.*;
import java.util.function.*;

import data.*;
import state.GameStateHandler;
import view.AdventureFrame;

public class AdventMain
{
	// Most printed values will be padded to be divisible by this uniform length for ease of readability.
	private static final int LOG_SEGMENT_LENGTH = 20;

	// An option to turn dwarves off for easier testing.
	public static final boolean DWARVES_ALLOWED = true;

	// The chance of encountering the pirate is increased by this value x the number of moves made in critter locations.
	public static final double PIRATE_ENCOUNTER_MODIFIER = .1;

	// The chance of encountering a dwarf is this modifier x the number of remaining dwarves.
	public static final double DWARF_ENCOUNTER_MODIFIER = 50;


	// TODO: forward. back, critters, validate death

	// The current game.
	public static AdventGame advent;

	public static final AdventureFrame FRAME = new AdventureFrame();
	public static final GameStateHandler STATE_HANDLER = new GameStateHandler();

	protected static Locations[] defaultLocations;
	protected static int[] defaultHintProc ;

	static IntFunction<String> offerHintMessage	= cost -> "\nI am prepared to give you a hint, but it will cost you " + cost + " points.\nDo you want the hint?";
	static UnaryOperator<String> truncate = s -> s.substring(0, Math.min(s.length(), 5));

	static Random random = new Random();

	static final int[] SCORES = {35, 100, 130, 200, 250, 300, 330, 349, 350};

	public static void main(String[] args)
	{
		advent = new AdventGame();
		FRAME.setUp();
		defaultLocations = GameObjects.getObjectLocations();
		defaultHintProc  = Hints.getHintProcValues();
	}

	public static void newGame()
	{
		advent = new AdventGame();
		GameObjects.loadObjectLocations(AdventMain.defaultLocations);
		Locations.loadVisits(new int[Locations.getVisitsArray().length]);
		Hints.loadHintStates(new boolean[Hints.getHintGivenStatus().length], AdventMain.defaultHintProc);
	}

	public static double generate()
	{
		return random.nextDouble();
	}

	static void logGameInfo()
	{
		String toPrint = "   > " + advent.lastInput + "\n"
		 		+  norm(" | Turns : " + advent.turns)
				+  norm(" | New Loc : " + (advent.locationAtStartOfAction != advent.currentLocation))
				+  norm(" | At : " + advent.currentLocation)
				+  norm(" | Last : " + advent.previousLocation)
				+  norm(" | Water : " + advent.currentLocation.hasWater)
				+  norm(" | Lamp : " + advent.lamp)
				+  norm(" | Tally : " + advent.tally)
				+  norm(" | Score : " + advent.score)
				+  norm(" | Lives : " + advent.livesLeft)
				+  " | \n"
				+  norm(" | Inventory : " + advent.itemsInHand + " " + GameObjects.getObjectsHere(Locations.INHAND))
				+  " | \n"
				+  norm(" | Objects Here: " + GameObjects.getObjectsHere(advent.currentLocation)) + " | "
				+  "\n" + printQuestionsAndHintsStatus()
				+  "\n" + critterInfo()
			    // +  "\n" + clockInfo()
			    // +  "\n" + scoreInfo()
				+  "\n";
		System.out.println(toPrint);
	}

	static String printQuestionsAndHintsStatus()
	{
		String toPrint = ""
				+  norm(" | Question : " + advent.questionAsked)
				+  norm(" | To Offer : " + advent.hintToOffer)
				+  norm(" | Offered : "  + advent.offeredHint)
				+  " |\n";

		StringBuilder hints = new StringBuilder();
		for (Hints hint : Hints.values())
		{
			hints.append(hint != Hints.NONE ? hintInfoString(hint) : "");
		}

		if (hints.length() > 0)
		{
			hints.append(" | ");
		}

		return toPrint + hints;
	}

	static String hintInfoString(Hints hint)
	{
		return norm(" | " +
						hint.name + ": " + hint.cost + " " + hint.proc + "/" + hint.threshold + " " +
						(hint.given ? "1 " : "0 "));
	}

	static String critterInfo()
	{
		return ""
			// + norm(" | Allowed : " + Locations.crittersAllowed(ADVENT.currentLocation))
			+ norm(" | Dwarves Here : " + advent.dwarfPresent)
			+ norm(" | New Dwarf : " + advent.newDwarf)
			+ norm(" | Dwarves Left : " + advent.dwarvesLeft)
			+ norm(" | Dwarf % : " + (advent.dwarvesLeft - advent.dwarfPresent) * DWARF_ENCOUNTER_MODIFIER)
			+ norm(" | Pirate State : " + advent.pirate)
			+ norm(" | Pirate % : " + advent.movesWOEncounter * PIRATE_ENCOUNTER_MODIFIER)
			+ norm(" | Moves W/O Pirate : " + advent.movesWOEncounter)
			+  " |\n";
	}

	static String clockInfo()
	{
		return ""
			+ norm(" | Clock 1 : " + advent.clock1)
			+ norm(" | Clock 2 : " + advent.clock2)
			+ norm(" | Closing : " + advent.caveIsClosing)
			+ norm(" | Closed : " + advent.caveIsClosed)
			+  " |\n";
	}

	static String norm(String input)
	{
        StringBuilder inputBuilder = new StringBuilder(input);
        while(inputBuilder.length() % LOG_SEGMENT_LENGTH != 0)
		{
			inputBuilder.append(" ");
		}

        return inputBuilder.toString();
	}
}
