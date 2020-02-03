/**
 * @author Ariana Fairbanks
 */

package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntFunction;
import state.GameStateHandler;
import view.AdventureFrame;

//TODO closer look at death/resurrection	- 	non y/n answer?

public class AdventMain 
{
	public static AdventGame ADVENT;
	
	public static Random random = new Random();
	
	public static final String empty = "";
	public static final String alikePassages = "You are in a maze of twisty little passages, all alike.";
	public static final String alikeT = "Maze All Alike";
	public static final String diffT = "Maze All Different";
	public static final String secretCanyon = "Secret Canyon";
	public static final String deadEnd = "Dead end.";
	public static final String deadEndT = "Dead End";
	public static final String okay = "Okay.";
	public static final String dontHave = "You are not carrying it!";
	public static final String nothing = "Nothing happens.";
	
	public static final String[] feeFieFoe = new String[] {"fee", "fie", "foe", "foo", "fum"};
	public static final int[] scores = new int[] {35, 100, 130, 200, 250, 300, 330, 349, 350};
	public static final String[] sMessages = new String[] { "You are obviously a rank amateur. Better luck next time.", "Your score qualifies you as a novice class adventurer.", "You have now achieved the rating 'Experienced Adventurer'.", "You may now consider yourself a 'Seasoned Adventurer'.", "You have reached 'Junior Master' status.", "Your score puts you in Master Adventurer Class C.", "Your score puts you in Master Adventurer Class B.", "Your score puts you in Master Adventurer Class A.", "All of Adventuredom gives tribute to you, Adventure Grandmaster!" };
	
	public static AdventureFrame frame;
	public static GameStateHandler stateHandler = new GameStateHandler();
	
	public static void main(String[] args)
	{
		ADVENT = new AdventGame();
		frame = new AdventureFrame();
		frame.setUp();
	}

	public static IntFunction<String> offerHintMessage = cost -> "\nI am prepared to give you a hint, but it will cost you " + cost + " points.\nDo you want the hint?";
	public static Function<String, String> truncate = s -> s.substring(0, Math.min(s.length(), 5));
	
	public static double generate(){ return random.nextDouble(); }


	public static void logGameInfo()
	{
		String toPrint = "";
		toPrint += "   " + ADVENT.lastInput + "\n";
		toPrint += " | Turns: " + ADVENT.turns;
		toPrint += " | Just Arrived: " + (ADVENT.locationAtStartOfAction != ADVENT.currentLocation);
		toPrint += " | Previous: " + ADVENT.previousLocation;
		toPrint += " | Current: " + ADVENT.currentLocation;
		toPrint += " | Lamp: " + ADVENT.lamp;
		toPrint += " | In Hand: " + ADVENT.itemsInHand;
		toPrint += " | Tally: " + ADVENT.tally;
		toPrint += " | Score: " + ADVENT.score;
		toPrint += " | Deaths: " + ADVENT.deaths;
		toPrint += " | ";
		toPrint += "\n | Objects Here: " + objectsHere(ADVENT.currentLocation) + " | ";
		toPrint += "\n" + printQuestionsAndHintsStatus();
		toPrint += "\n";
		System.out.println(toPrint);
	}
	
	public static String printQuestionsAndHintsStatus()
	{
		String toPrint = "";
		toPrint += " | Question: " + ADVENT.questionAsked;
		toPrint += " | To Offer: " + ADVENT.hintToOffer;
		toPrint += " | Offered: " + ADVENT.offeredHint;
		toPrint += " |\n | ";
		for(Hints hint : Hints.values())
		{ toPrint += ( hint != Hints.NONE ? ("" + hint.name + ": " + hint.cost + " " + hint.proc + "/" + hint.threshold + " " + hint.given + " " + " | ") : "" ); }
		return toPrint;
	}
	
	public static ArrayList<GameObjects> objectsHere(Locations here)
	{
		ArrayList<GameObjects> result = null;
		for(GameObjects object : GameObjects.values())
		{
			if(object.location == here)
			{
				if(result == null) {result = new ArrayList<GameObjects>();}
				result.add(object);
				if(ADVENT.found.containsKey(object))
				{
					if(object == GameObjects.RUG_){ object = GameObjects.RUG; }
					if(!ADVENT.found.get(object))
					{
						ADVENT.tally++;
						ADVENT.found.put(object, true);
					}
				}
			}
		}
		return result;
	}
	
	public enum Questions 
	{
		NONE(false), INSTRUCTIONS(true), DRAGON(false), RESURRECT(true), PLAYAGAIN(true), SCOREQUIT(true), QUIT(true), READBLASTHINT(true);
		
		public final boolean serious;
		
		Questions(boolean serious)
		{ this.serious = serious; }
	}
	
	public enum Hints 
	{
		NONE		("", 			0,  -1, null, null),
		INSTRUCTIONS("Instructions",	5,  -1, null, "Somewhere nearby is Colossal Cave, where others have found great fortunes in treasure and gold, though it is rumored that some who enter are never seen again. Magic is said to work in the cave. I will be your eyes and hands. Direct me with commands of 1 or 2 words. I should warn you that I only look at only the first five letters of each word, so you'll have to enter \"northeast\" as \"ne\" to distinguish it from \"north\" (Should you get stuck, type \"help\" for some general hints. For information on how to end your adventure, etc., type \"info\".)\n\n"),
/*0*/	BLAST		("Blast", 		10, -1, null, "It says, 'There is something strange about this place, such that one of the words I've always known now has a new effect.'"),
/*1*/	GRATE		("Grate", 		2,  4,  "\n\nAre you trying to get into the cave?", "The grate is very solid and has a hardened steel lock. You cannot enter without a key, and there are no keys in sight. I would recommend looking elsewhere for the keys."),
/*2*/	BIRD		("Bird", 		2,  5,  "\n\nAre you trying to catch the bird?", "Something seems to be frightening the bird just now and you cannot catch it no matter what you try. Perhaps you might try later."),
/*3*/	SNAKE		("Snake", 		2,  8,  "\n\nAre you trying to deal somehow with the snake?", "You can't kill the snake, or drive it away, or avoid it, or anything like that. There is a way to get by, but you don't have the necessary resources right now."),
/*4*/	MAZE		("Maze", 		4,  75, "\n\nDo you need help getting out of the maze?", "You can make the passages look less alike by dropping things."),
/*5*/	DARK		("Dark", 		5,  25, "\n\nAre you trying to explore beyond the Plover Room?", "There is a way to explore that region without having to worry about falling into a pit."),
/*6*/	WITT		("Witt", 		3,  20, "\n\nDo you need help getting out of here?", "Don't go west."),
		WEST		("West", 		0,  10, null, "\n\nIf you prefer, simply type W rather than WEST.");
		
		public final String name;
		public final int cost;
		public final int threshold;
		public final String question;
		public final String hint;
		public int proc;
		public boolean given;
		
		Hints(String name, int cost, int threshold, String question, String hint)
		{ 
			this.name = name; this.cost = cost; this.threshold = threshold; this.question = question; this.hint = hint;
			this.given = false;
			this.proc = 0;
		}
	}
	
	public interface KnownWord 
	{
		// 0:movement  1:object  2:action  3:message
		public byte getType();
	}
	
	public enum Movement implements KnownWord
	{
		ROAD, HILL, ENTER, UPSTREAM, DOWNSTREAM, FOREST, FORWARD, BACK, VALLEY, STAIRS, OUT, BUILDING, GULLY, STREAM, ROCK, BED, CRAWL, 
		COBBLE, IN, SURFACE, NOWHERE, DARK, PASSAGE, LOW, CANYON, AWKWARD, GIANT, VIEW, UP, DOWN, PIT, OUTDOORS, CRACK, STEPS, DOME, 
		LEFT, RIGHT, HALL, JUMP, BARREN, OVER, ACROSS, EAST, WEST, NORTH, SOUTH, NORTHEAST, SOUTHEAST, SOUTHWEST,NORTHWEST, 
		DEBRIS, HOLE, WALL, BROKEN, Y2, CLIMB, FLOOR, ROOM, SLIT, SLAB, XYZZY, DEPRESSION, ENTRANCE, PLUGH, SECRET, CAVE, 
		CROSS, BEDQUILT, PLOVER, ORIENTAL, CAVERN, SHELL, RESERVOIR, OFFICE, FORK;
		@Override
		public byte getType() { return 0; }
	}
	
	public enum GameObjects implements KnownWord
	{
		//TODO art string
		NOTHING, ALL, 	
		KEYS(	true,	Locations.BUILDING,				new String[] {"\n\t\tSet of Keys", 			"\n\tThere are some keys on the ground here."}),
		LAMP(	true,	Locations.BUILDING,				new String[] {"\n\t\tBrass Lantern", 		"\n\tThere is a lamp shining nearby.", "\n\tThere is a shiny brass lamp nearby."}),
		GRATE(					Locations.OUTSIDE, 				new String[] {"\n\tThe grate is open.", 	"\n\tThe grate is locked."}), GRATE_(Locations.INSIDE, null),
		CAGE(	true, 	Locations.COBBLES,				new String[] {"\n\t\tWicker Cage", 			"\n\tThere is a small wicker cage discarded nearby."}),
		ROD(	true, 	Locations.DEBRIS,				new String[] {"\n\t\tBlack Rod", 			"\n\tA three foot black rod with a rusty star on an end lies nearby."}),
		ROD2(	true, 	Locations.THEVOID,				new String[] {"\n\t\tBlack Rod", 			"\n\tA three foot black rod with a rusty mark on an end lies nearby."}),
		TREADS(					Locations.EASTMIST,				new String[] {"\n\tRough stone steps lead up the dome."}),
		TREADS_(				Locations.SMALLPIT,				new String[] {"\n\tRough stone steps lead down the pit."}),
		BIRD(	true,	Locations.BIRD,					new String[] {"\n\t\tLittle Bird in Cage", "\n\tThere is a little bird in the cage.", "\n\tA cheerful little bird is sitting here singing."}),
		DOOR(					Locations.IMMENSE,				new String[] {"\n\tThe way north is barred by a massive, rusty, iron door.", "\n\tThe way north leads through a massive, rusty, iron door."}),
		PILLOW(	true, 	Locations.SOFT,					new String[] {"\n\t\tVelvet Pillow", "\n\tA small velvet pillow lies on the floor."}),
		SNAKE(					Locations.HALLOFMOUNTAINKING,	new String[] {"\n\tA huge green fierce snake bars the way!"}),
		CRYSTAL(				Locations.EASTFISSURE,			new String[] {"\n\tA crystal bridge now spans the fissure."}), CRYSTAL_(Locations.WESTFISSURE, null),
		TABLET(					Locations.DROOM,				new String[] {"\n\tA massive stone tablet imbedded in the wall reads:\n\t\"CONGRATULATIONS ON BRINGING LIGHT TO THE DARK-ROOM!\""}),
		CLAM(	true, 	Locations.SHELL,				new String[] {"\n\t\tGiant Clam >GRUNT!<", "\n\tThere is an enormous clam here with its shell tightly closed."}),
		OYSTER(	true, 	Locations.THEVOID,				new String[] {"\n\t\tGiant Oyster >GROAN!<", "\n\tThere is an enormous oyster here with its shell tightly closed."}),
		MAG(	true, 	Locations.ANTE,					new String[] {"\n\t\t\"Spelunker Today\"", "\n\tThere are a few recent issues of \"Spelunker Today\" magazine here."}),
		DWARF, KNIFE, 
		FOOD(	true, 	Locations.BUILDING,				new String[] {"\n\t\tTasty Food", "\n\tThere is tasty food here."}),
		BOTTLE(	true, 	Locations.BUILDING,				new String[] {"\n\t\tSmall Bottle", "\n\t\tBottle of Water", "\n\t\tBottle of Oil", "\n\tThere is an empty bottle here.", "\n\tThere is a bottle of water here.", "\n\tThere is a bottle of oil here."}),
		WATER, OIL, 
		MIRROR(					Locations.MIRROR, null),
		MIRROR_, 
		PLANT(					Locations.WESTPIT,				new String[] {"\n\tThere is a tiny little plant in the pit, murmuring \"Water, water, ...\"", "\n\tThere is a 12-foot-tall beanstalk stretching up out of the pit, bellowing \"Water!! Water!!\"", "\n\tThere is a gigantic beanstalk stretching all the way up to the hole.", ""}),
		PLANT2(					Locations.WEST2PIT,				new String[] {"", "\n\tThe top of a 12-foot-tall beanstalk is poking up out of the west pit.", "\n\tThere is a huge beanstalk growing out of the west pit up to the hole.", ""}), PLANT2_(Locations.EAST2PIT,	null),
		STALACTITE(				Locations.STALACTITE,			new String[] {}),
		SHADOW(					Locations.EASTWINDOW,			new String[] {"\n\tThe shadowy figure seems to be trying to attract your attention."}), SHADOW_(Locations.WESTWINDOW, null),
		AXE(	true, 	Locations.THEVOID,				new String[] {"\n\t\tDwarf's Axe", "\n\tThere is a little axe lying beside the bear.", "\n\tThere is a little axe here."}),
		ART(					Locations.ORIENTAL, null),
		PIRATE, 
		DRAGON(					Locations.SCAN1,				new String[] {"\n\tA huge green fierce dragon bars the way!", "\n\tThe body of a huge green dead dragon is lying off to one side."}), DRAGON_(Locations.SCAN3, null),
		BRIDGE(					Locations.SWSIDE,				new String[] {"\n\tA rickety wooden bridge extends across the chasm, vanishing into the mist. \n\tA sign posted on the bridge reads: \n\t\t\"Stop!  Pay Troll!\"", "\n\tThe wreckage of a bridge (and a dead bear) can be seen at the bottom of the chasm."}), BRIDGE_(Locations.NESIDE, null),
		TROLL(					Locations.SWSIDE,				new String[] {"\n\tA burly troll stands by the bridge and insists you throw him a treasure before you may cross."}), TROLL_(Locations.NESIDE, null),
		TROLL2(					Locations.THEVOID,				new String[] {"\n\tThe troll is nowhere to be seen."}), TROLL2_(Locations.THEVOID, null),
		BEAR(					Locations.BARR,					new String[] {"\n\tThere is a ferocious cave bear eyeing you from the far end of the room!", "\n\tThere is a gentle cave bear sitting placidly in one corner.", "\n\nYou are being followed by a very large, tame bear.", "", "\n\tThere is a contented-looking bear wandering about nearby."}),
		MESSAGE(				Locations.THEVOID,				new String[] {"\n\tThere is a message scrawled in the dust in a flowery script, reading:\n\t\t\"This is not the maze where the pirate leaves his treasure chest.\""}),
		GEYSER(					Locations.VIEW, null),
		PONY(					Locations.PONY,					new String[] {"\n\tThere is a massive vending machine here.  \nThe instructions on it read:\n\t\t\"Drop coins here to receive fresh batteries.\""}),
		BATTERIES(				Locations.THEVOID,				new String[] {"\n\tThere are fresh batteries here.", "\n\tSome worn-out batteries have been discarded nearby."}),
		MOSS(					Locations.SOFT, null),
		
		GOLD(		true, Locations.NUGGET,			new String[] {"\n\t\tLarge Gold Nugget", 	"\n\tThere is a large sparkling nugget of gold here!"}),
		DIAMONDS(	true, Locations.WESTFISSURE,	new String[] {"\n\t\tSeveral Diamonds", 	"\n\tThere are diamonds here!"}),
		SILVER(		true, Locations.NS,				new String[] {"\n\t\tBars of Silver", 		"\n\tThere are bars of silver here!"}),
		JEWELS(		true, Locations.SOUTH,			new String[] {"\n\t\tPrecious Jewelry", 	"\n\tThere is precious jewelry here!"}),
		COINS(		true, Locations.WEST,			new String[] {"\n\t\tRare Coins", 			"\n\tThere are many coins here!"}),
		CHEST(		true, Locations.THEVOID,		new String[] {"\n\t\tTreasure Chest", 		"\n\tThe pirate's treasure chest is here!"}),
		EGGS(		true, Locations.GIANT,			new String[] {"\n\t\tGolden Eggs", 			"\n\tThere is a large nest here, full of golden eggs!"}),
		TRIDENT(	true, Locations.FALLS,			new String[] {"\n\t\tJeweled Trident", 		"\n\tThere is a jewel-encrusted trident here!"}),
		VASE(		true, Locations.ORIENTAL,		new String[] {"\n\t\tMing Vase", 			"\n\tThe vase is now resting, delicately, on a velvet pillow.", "\n\tThere is a delicate, precious, Ming vase here!", "\n\tThe floor is littered with worthless shards of pottery."}),
		EMERALD(	true, Locations.PROOM,			new String[] {"\n\t\tEgg-Sized Emerald", 	"\n\tThere is an emerald here the size of a plover's egg!"}),
		PYRAMID(	true, Locations.DROOM,			new String[] {"\n\t\tPlatinum Pyramid", 	"\n\tThere is a platinum pyramid here, 8 inches on a side!"}),
		PEARL(		true, Locations.THEVOID,		new String[] {"\n\t\tGlistening Pearl", 	"\n\tOff to one side lies a glistening pearl!"}),
		RUG(		true, Locations.SCAN1,			new String[] {"\n\t\tPersian Rug", 			"\n\tThe dragon is sprawled out on a persian rug!", "\n\tThere is a persian rug spread out on the floor!"}), RUG_(true, Locations.SCAN3,	null),
		SPICES(		true, Locations.CHAMBER,		new String[] {"\n\t\tRare Spices", 			"\n\tThere are rare spices here!"}),
		CHAIN(		true, Locations.BARR,			new String[] {"\n\t\tGolden Chain", 		"\n\tThe bear is locked to the wall with a golden chain!", "\n\tThere is a golden chain lying in a heap on the floor!", "\n\tThere is a golden chain locked to the wall!"});

		public final boolean mobile;
		public Locations location;
		public String[] descriptions;
		
		GameObjects()
		{ this.mobile = false; this.location = Locations.THEVOID; this.descriptions = null; }
		
		GameObjects(Locations location, String[] descriptions)
		{ this.mobile = false; this.location = location; this.descriptions = descriptions; }
		
		GameObjects(boolean mobile, Locations location, String[] descriptions)
		{ this.mobile = mobile; this.location = location; this.descriptions = descriptions; }
		
		public static boolean isTreasure(GameObjects thisThing)
		{ return thisThing.ordinal() > GameObjects.MOSS.ordinal(); }
		
		public static boolean isLesserTreasure(GameObjects thisThing)
		{ return thisThing.ordinal() < GameObjects.CHEST.ordinal(); }
		
		public static Locations[] getLocations()
		{
			Locations[] locations = new Locations[GameObjects.values().length];
			for(int i = 0; i < GameObjects.values().length; i++)
			{ locations[i] = GameObjects.values()[i].location; }
			return locations;
		}
		
		public static void loadLocations(Locations[] locations)
		{ for(int i = 0; i < GameObjects.values().length; i++) { GameObjects.values()[i].location = locations[i]; } }

		public static String getItemDescription(Locations location, GameObjects object)
		{
			String output = empty;
			//these local variables make the switch statement significantly less cluttered and more readable
			boolean inHand = location == Locations.INHAND;
			boolean[] endGameObjectStates = ADVENT.endGameObjectsStates;
			String[] descriptions = object.descriptions;
			
			switch(object)
			{
				case LAMP: if(!endGameObjectStates[1]) { output = (descriptions[ (inHand ? 0 : ((ADVENT.lampIsLit) ? 1 : 2)) ]); } break;
				
				case GRATE: case GRATE_: if(!endGameObjectStates[4]) { output = GRATE.descriptions[((ADVENT.grateIsUnlocked) ? 0 : 1)]; } break;
				
				case CAGE: if(!endGameObjectStates[5]) { output = descriptions[((inHand) ? 0 : 1)]; } break;
				
				case BIRD: if(!endGameObjectStates[6]) { output = (descriptions[ (inHand ? 0 : ((ADVENT.birdInCage) ? 1 : 2)) ]); } break;
					
				case ROD: if(!endGameObjectStates[8]) { output = descriptions[((inHand) ? 0 : 1)]; } break;				
					
				case ROD2: if(!endGameObjectStates[9]) { output = descriptions[((inHand) ? 0 : 1)]; } break;

				case DOOR: output = descriptions[((!ADVENT.doorHasBeenOiled) ? 0 : 1)]; break;
					
				case PILLOW: if(!endGameObjectStates[2]) { output = descriptions[((inHand) ? 0 : 1)]; } break;			
					
				case VASE: output = descriptions[((ADVENT.vaseIsBroken ? 3 : (inHand ? 0 : (PILLOW.location == location ? 1 : 2))))]; break;
					
				case SNAKE: if(!endGameObjectStates[7]) { output = descriptions[0]; } break;				
					
				case OYSTER: if(!endGameObjectStates[3]) { output = descriptions[((inHand) ? 0 : 1)]; } break;		
					
				case MAG: output = descriptions[((inHand) ? 0 : 1)]; break;
					
				case BOTTLE: if(!endGameObjectStates[0]) { output = descriptions[ ADVENT.stateOfTheBottle + (inHand ? 0 : 3) ]; } break;
					
				case PLANT: output = descriptions[ADVENT.stateOfThePlant]; break;
				
				case PLANT2: case PLANT2_: output = PLANT2.descriptions[ADVENT.stateOfThePlant]; break;
					
				case AXE: output = descriptions[(inHand ? 0 : (ADVENT.bearAxe ? 1 : 2))]; break;
					
				case DRAGON: case DRAGON_: output = DRAGON.descriptions[ADVENT.dragonIsAlive ? 0 : 1]; break;
					
				case RUG: case RUG_: output = RUG.descriptions[inHand ? 0 : (ADVENT.dragonIsAlive ? 1 : 2)]; break;
					
				case BEAR: output = descriptions[ADVENT.stateOfTheBear]; break;
					
				case BATTERIES: output = descriptions[(ADVENT.stateOfSpareBatteries == 1) ? 0 : 1]; break;
					
				case CHAIN: output = descriptions[inHand ? 0 : ADVENT.stateOfTheChain + 1]; break;
					
				case TREADS: case TREADS_: if(!ADVENT.goldInInventory){ output = descriptions[0]; } break;
					
				case CRYSTAL: case CRYSTAL_: if(!ADVENT.crystalBridgeIsThere){ output = CRYSTAL.descriptions[0]; } break;
					
				case BRIDGE: case BRIDGE_: output = BRIDGE.descriptions[!ADVENT.collapse ? 0 : 1]; break;
					
				case SHADOW: case SHADOW_: output = SHADOW.descriptions[0]; break;
					
				case TROLL: case TROLL_: output = TROLL.descriptions[0]; break;
					
				case TROLL2: case TROLL2_: output = TROLL2.descriptions[0]; break;
			
				default: if(descriptions != null){ output = descriptions[((inHand || descriptions.length == 1) ? 0 : 1)]; } break;
			}
			return output;
		}

		@Override
		public byte getType() { return 1; }
	}
	
	public static enum ActionWords implements KnownWord
	{
		NOTHING, LOOK, ABSTAIN, TAKE, DROP, OPEN, CLOSE, ON, OFF, WAVE, CALM, GO, RELAX, POUR, EAT, DRINK, RUB, TOSS, 
		WAKE, FEED, FILL, BREAK, BLAST, KILL, SAY, READ, FEEFIE, BRIEF, VERBOSE, FIND, INVENTORY, SCORE, QUIT, SAVE, LOAD;
		@Override
		public byte getType() { return 2; }
	}
	
	public enum MessageWords implements KnownWord
	{
		MAGIC("Good try, but that is an old worn-out magic word."), 
		HELP("I know of places, actions, and things. Most of my vocabulary describes places and is used to move you there. To move, try words like forest, building, downstream, enter, east, west, north, south, up, or down. I know about a few special objects, like a black rod hidden in the cave. These objects can be manipulated using some of the action words that I know. Usually you will need to give both the object and the action words (in either order), but sometimes I can infer the object from the verb alone. Some objects also imply verbs; in particular, \"inventory\" implies \"take inventory\", which causes me to give you a list of what you are carrying. The objects have side effects; for instance, the rod scares the bird. Usually people having trouble moving just need to try a few more words. Usually people trying unsuccessfully to manupulate an object are attempting beyond To speed the game you can sometimes move long distances with a single word. For example, \"building\" usually gets you to the building from anywhere above ground except when lost in the forest. Also, note that cave passages turn a lot, and that leaving a room to the north does not guarantee entering the next from the south. \nGood luck!"), 
		TREE("The trees of the forest are large hardwood oak and maple, with an occasional grove of pine or spruce. There is quite a bit of under-growth, largely birch and ash saplings plus nondescript bushes of various sorts. This time of year visibility is quite restricted by all the leaves, but travel is quite easy if you detour around the spruce and berry bushes."), 
		DIG("Digging without a shovel is quite impractical. Even with a shovel progress is unlikely."), 
		LOST("I'm as confused as you are."), 
		MIST("Mist is a white vapor, usually water, seen from time to time in caverns. It can be found anywhere but is frequently a sign of a deep pit leading down to water."), 
		CUSS("Watch it!"), 
		STOP("I don't know the word \"stop\". Use \"quit\" if you want to give up."),
		INFO("If you want to end your adventure early, say \"quit\". To get full credit for a treasure, you must have left it safely in the building, though you get partial credit just for locating it. You lose points for getting killed, or for quitting, though the former costs you more. There are also points based on how much (if any) of the cave you've managed to explore; in particular, there is a large bonus just for getting in (to distinguish the beginners from the rest of the pack), and there are other ways to determine whether you've been through some of the more harrowing sections. If you think you've found all the treasures, just keep exploring for a while. If nothing interesting happens, you haven't found them all yet. If something interesting DOES happen, it means you're getting a bonus and have an opportunity to garner many more points in the master's section. I may occasionally offer hints if you seem to be having trouble. If I do, I will warn you in advance how much it will affect your score to accept the hints. Finally, to save paper, you may specify \"brief\", which tells me never to repeat the full description of a place unless you explicitly ask me to. You may also specify \"verbose\", which tells me only to repeat the long description of a place."), 
		SWIM("I don't know how."), 
		DENNIS("Thou cannotst go there. Who do you think thou art? A magistrate?!");
		
		public final String message;
		
		private MessageWords(String message){ this.message = message; }

		@Override
		public byte getType() { return 3; }
	}
	
	@SuppressWarnings("serial")
	public static final HashMap<String, KnownWord> KnownWords = new HashMap<String, KnownWord>()
	{{
		put("road", Movement.ROAD);
		put("hill", Movement.HILL);
		put("enter", Movement.ENTER);
		put("downs", Movement.DOWNSTREAM);
		put("upstr", Movement.UPSTREAM);
		put("fores", Movement.FOREST);
		put("forwa", Movement.FORWARD); put("onwar", Movement.FORWARD);
		put("back", Movement.BACK); put("retre", Movement.BACK); put("retur", Movement.BACK);
		put("valle", Movement.VALLEY);
		put("stair", Movement.STAIRS);
		put("out", Movement.OUT); put("outsi", Movement.OUT); put("exit", Movement.OUT); put("leave", Movement.OUT);
		put("build", Movement.BUILDING); put("house", Movement.BUILDING);
		put("gully", Movement.GULLY);
		put("strea", Movement.STREAM);
		put("rock", Movement.ROCK);
		put("bed", Movement.BED);
		put("crawl", Movement.CRAWL);
		put("cobbl", Movement.COBBLE);
		put("in", Movement.IN); put("insid", Movement.IN); put("inwar", Movement.IN);
		put("surfa", Movement.SURFACE);
		put("nowhe", Movement.NOWHERE);
		put("dark", Movement.DARK);
		put("passa", Movement.PASSAGE); put("tunne", Movement.PASSAGE);
		put("canyo", Movement.CANYON);
		put("akwar", Movement.AWKWARD);
		put("giant", Movement.GIANT);
		put("view", Movement.VIEW);
		put("up", Movement.UP); put("u", Movement.UP); put("above", Movement.UP); put("acend", Movement.UP); put("upwar", Movement.UP);
		put("down", Movement.DOWN); put("d", Movement.DOWN); put("decen", Movement.DOWN); put("downw", Movement.DOWN);
		put("pit", Movement.PIT);
		put("outdo", Movement.OUTDOORS);
		put("crack", Movement.CRACK);
		put("steps", Movement.STEPS);
		put("dome", Movement.DOME);
		put("left", Movement.LEFT);
		put("right", Movement.RIGHT);
		put("hall", Movement.HALL);
		put("jump", Movement.JUMP);
		put("barren", Movement.BARREN);
		put("over", Movement.OVER);
		put("across", Movement.ACROSS);
		put("e", Movement.EAST); put("east", Movement.EAST);
		put("w", Movement.WEST); put("west", Movement.WEST);
		put("n", Movement.NORTH); put("north", Movement.NORTH);
		put("s", Movement.SOUTH); put("south", Movement.SOUTH);
		put("ne", Movement.NORTHEAST);
		put("se", Movement.SOUTHEAST);
		put("sw", Movement.SOUTHWEST);
		put("nw", Movement.NORTHWEST);
		put("debri", Movement.DEBRIS);
		put("hole", Movement.HOLE);
		put("wall", Movement.WALL);
		put("broke", Movement.BROKEN);
		put("y2", Movement.Y2);
		put("climb", Movement.CLIMB);
		put("floor", Movement.FLOOR);
		put("room", Movement.ROOM);
		put("slit", Movement.SLIT);
		put("slab", Movement.SLAB); put("slabr", Movement.SLAB);
		put("xyzzy", Movement.XYZZY);
		put("depre", Movement.DEPRESSION);
		put("entra", Movement.ENTRANCE);
		put("plugh", Movement.PLUGH);
		put("secre", Movement.SECRET);
		put("cave", Movement.CAVE);
		put("cross", Movement.CROSS);
		put("bedqu", Movement.BEDQUILT);
		put("plove", Movement.PLOVER);
		put("orien", Movement.ORIENTAL);
		put("caver", Movement.CAVERN);
		put("shell", Movement.SHELL);
		put("main", Movement.OFFICE); put("offic", Movement.OFFICE);
		put("reser", Movement.RESERVOIR);
		put("fork", Movement.FORK);

		
		put("all", GameObjects.ALL);
		put("key", GameObjects.KEYS); put("keys", GameObjects.KEYS);
		put("lamp", GameObjects.LAMP); put("lante", GameObjects.LAMP); put("headl", GameObjects.LAMP);
		put("grate", GameObjects.GRATE);
		put("cage", GameObjects.CAGE);
		put("rod", GameObjects.ROD);
		put("bird", GameObjects.BIRD);
		put("door", GameObjects.DOOR);
		put("pillo", GameObjects.PILLOW);
		put("snake", GameObjects.SNAKE);
		put("fissu", GameObjects.CRYSTAL);
		put("table", GameObjects.TABLET);
		put("clam", GameObjects.CLAM);
		put("oyste",GameObjects. OYSTER);
		put("magaz", GameObjects.MAG); put("issue", GameObjects.MAG); put("spelu", GameObjects.MAG); put("\"spel",GameObjects. MAG);
		put("dwarf", GameObjects.DWARF); put("dwarv", GameObjects.DWARF);
		put("knife", GameObjects.KNIFE); put("knive", GameObjects.KNIFE);
		put("food", GameObjects.FOOD); put("ratio", GameObjects.FOOD);
		put("bottl", GameObjects.BOTTLE); put("jar", GameObjects.BOTTLE);
		put("water", GameObjects.WATER); put("h2o", GameObjects.WATER);
		put("oil", GameObjects.OIL);
		put("mirro", GameObjects.MIRROR);
		put("plant", GameObjects.PLANT); put("beans", GameObjects.PLANT);
		put("stala", GameObjects.STALACTITE);
		put("shado", GameObjects.SHADOW); put("figur", GameObjects.SHADOW);
		put("axe", GameObjects.AXE);
		put("drawi", GameObjects.ART);
		put("pirat", GameObjects.PIRATE);
		put("drago", GameObjects.DRAGON);
		put("chasm", GameObjects.BRIDGE);
		put("troll", GameObjects.TROLL);
		put("bear", GameObjects.BEAR);
		put("messa", GameObjects.MESSAGE);
		put("volca", GameObjects.GEYSER); put("geyse", GameObjects.GEYSER);
		put("vendi", GameObjects.PONY); put("machi", GameObjects.PONY);
		put("batte", GameObjects.BATTERIES);
		put("moss", GameObjects.MOSS); put("carpe", GameObjects.MOSS);
		
		put("gold", GameObjects.GOLD); put("nugge", GameObjects.GOLD);
		put("diamo", GameObjects.DIAMONDS);
		put("silve", GameObjects.SILVER); put("bars", GameObjects.SILVER);
		put("jewel", GameObjects.JEWELS);
		put("coins", GameObjects.COINS);
		put("chest", GameObjects.CHEST); put("box", GameObjects.CHEST); put("treas", GameObjects.CHEST);
		put("eggs", GameObjects.EGGS); put("egg", GameObjects.EGGS); put("nest", GameObjects.EGGS);
		put("tride", GameObjects.TRIDENT);
		put("ming", GameObjects.VASE); put("vase", GameObjects.VASE); put("shard", GameObjects.VASE); put("potte", GameObjects.VASE);
		put("emera", GameObjects.EMERALD);
		put("plati", GameObjects.PYRAMID); put("pyram", GameObjects.PYRAMID);
		put("pearl", GameObjects.PEARL);
		put("rug", GameObjects.RUG);
		put("spice", GameObjects.SPICES);
		put("chain", GameObjects.CHAIN);

		
		put("look", ActionWords.LOOK); put("descr", ActionWords.LOOK); put("exami", ActionWords.LOOK);
		put("take", ActionWords.TAKE); put("carry", ActionWords.TAKE); put("keep", ActionWords.TAKE); put("catch", ActionWords.TAKE); put("captu", ActionWords.TAKE); put("steal", ActionWords.TAKE); put("get", ActionWords.TAKE); put("tote", ActionWords.TAKE);
		put("drop", ActionWords.DROP); put("relea", ActionWords.DROP); put("free", ActionWords.DROP); put("disca", ActionWords.DROP); put("dump", ActionWords.DROP);
		put("open", ActionWords.OPEN); put("unloc", ActionWords.OPEN);
		put("close", ActionWords.CLOSE); put("lock", ActionWords.CLOSE);
		put("light", ActionWords.ON); put("on", ActionWords.ON);
		put("extin", ActionWords.OFF); put("off", ActionWords.OFF);
		put("wave", ActionWords.WAVE); put("shake", ActionWords.WAVE); put("swing", ActionWords.WAVE);
		put("calm", ActionWords.CALM); put("placa", ActionWords.CALM); put("tame", ActionWords.CALM);
		put("walk", ActionWords.GO); put("run", ActionWords.GO); put("trave", ActionWords.GO); put("go", ActionWords.GO); put("proce", ActionWords.GO); put("explo", ActionWords.GO); put("goto", ActionWords.GO); put("follo", ActionWords.GO); put("turn", ActionWords.GO);
		put("nothi", ActionWords.RELAX); put("absta", ActionWords.RELAX); put("wait", ActionWords.RELAX);
		put("pour", ActionWords.POUR);
		put("eat", ActionWords.EAT); put("devou", ActionWords.EAT);
		put("drink", ActionWords.DRINK);
		put("rub", ActionWords.RUB);
		put("throw", ActionWords.TOSS); put("toss", ActionWords.TOSS);
		put("wake", ActionWords.WAKE); put("distu", ActionWords.WAKE);
		put("feed", ActionWords.FEED);
		put("fill", ActionWords.FILL);
		put("break", ActionWords.BREAK); put("smash", ActionWords.BREAK); put("shatt", ActionWords.BREAK);
		put("blast", ActionWords.BLAST); put("deton", ActionWords.BLAST); put("ignit", ActionWords.BLAST); put("blowu", ActionWords.BLAST);
		put("attac", ActionWords.KILL); put("kill", ActionWords.KILL); put("fight", ActionWords.KILL); put("hit", ActionWords.KILL); put("strik", ActionWords.KILL); put("slay", ActionWords.KILL);
		put("say", ActionWords.SAY); put("chant", ActionWords.SAY); put("sing", ActionWords.SAY); put("utter", ActionWords.SAY); put("mumbl", ActionWords.SAY);
		put("read", ActionWords.READ); put("perus", ActionWords.READ);
		put("fee", ActionWords.FEEFIE); put("fie", ActionWords.FEEFIE); put("foe", ActionWords.FEEFIE); put("foo", ActionWords.FEEFIE); put("fum", ActionWords.FEEFIE);
		put("brief", ActionWords.BRIEF);
		put("verbo", ActionWords.VERBOSE);
		put("find", ActionWords.FIND); put("where", ActionWords.FIND);
		put("inven", ActionWords.INVENTORY);
		put("score", ActionWords.SCORE);
		put("quit", ActionWords.QUIT);
		
		put("abra", MessageWords.MAGIC); put("abrac", MessageWords.MAGIC); put("opens", MessageWords.MAGIC); put("sesam", MessageWords.MAGIC);
		put("shaza", MessageWords.MAGIC); put("hocus", MessageWords.MAGIC); put("pocus", MessageWords.MAGIC);
		put("help", MessageWords.HELP); put("?", MessageWords.HELP);
		put("tree", MessageWords.TREE); put("trees", MessageWords.TREE);
		put("dig", MessageWords.DIG); put("excav", MessageWords.DIG);
		put("lost", MessageWords.LOST);
		put("mist", MessageWords.MIST);
		put("fuck", MessageWords.CUSS); put("shit", MessageWords.CUSS); put("damn", MessageWords.CUSS); put("dick", MessageWords.CUSS);
		put("info", MessageWords.INFO); put("infor", MessageWords.INFO);
		put("swim", MessageWords.SWIM);
		put("denni", MessageWords.DENNIS);
	}};
	
	public static enum Locations
	{
		THEVOID(), INHAND(),
		ROAD	(	true,	"End Of Road",				"You're at end of road again.",			"You are standing at the end of a road before a small brick building. Around you is a forest. A small stream flows out of the building and down a gully."	),
		HILL	(					"Hill",					"You're at hill in road.",				"You have walked up a hill, still in the forest. The road slopes back down the other side of the hill. There is a building in the distance."	),
		BUILDING(	true,	"Building",				"You're inside building.",				"You are inside a building, a well house for a large spring."	),
		VALLEY	(	true,	"Valley",					"You're in valley.",						"You are in a valley in the forest beside a stream tumbling along a rocky bed."	),
		FOREST	(					"Forest",					"You're in forest.",						"You are in open forest, with a deep valley to one side."	),
		WOODS	(					"Forest",					"You're in forest near a road.",			"You are in open forest near both a valley and a road."	),
		SLIT	(	true,	"Slit",					"You're at slit in streambed.",			"At your feet all the water of the stream splashes into a 2-inch slit in the rock. Downstream the streambed is bare rock."	),
		OUTSIDE	(					"Outside Grate",			"You're outside grate.",					"You are in a 20-foot depression floored with bare dirt. Set into the dirt is a strong steel grate mounted in concrete. A dry streambed leads into the depression."	),
		
		INSIDE	(					"Below Grate",				"You're below the grate.",				"You are in a small chamber beneath a 3x3 steel grate to the surface. A low crawl over cobbles leads inward to the west."	),
		COBBLES	(					"Cobble Crawl",			"You're in cobble crawl.",				"You are crawling over cobbles in a low passage. There is a dim light at the east end of the passage."	),
		DEBRIS	(					"Debris Room",				"You're in debris room.",				"You are in a debris room filled with stuff washed in from the surface. A low wide passage with cobbles becomes plugged with mud and debris here, but an awkward canyon leads upward and west. \nA note on the wall says:\n\tMagic Word \"XYZZY\""	),
		AWKWARD	(					"Awkward Canyon",			"You are in an awkward sloping east/west canyon."	),
		BIRD	(					"Bird Chamber",			"You're in bird chamber.",				"You are in a splendid chamber thirty feet high. The walls are frozen rivers of orange stone. An awkward canyon and a good passage exit from east and west sides of the chamber."	),
		SMALLPIT(					"Small Pit",				"You're at top of small pit.",			"At your feet is a small pit breathing traces of white mist. An east passage ends here except for a small crack leading on."	),
		
		EASTMIST(					"Hall Of Mists",			"You're in Hall of Mists.",				"You are at one end of a vast hall stretching forward out of sight to the west. There are openings to either side. Nearby, a wide stone staircase leads downward. The hall is filled with wisps of white mist swaying to and fro almost as if alive. A cold wind blows up the staircase. There is a passage at the top of a dome behind you."	),
		NUGGET	(					"Nugget Of Gold Room",		"You're in nugget of gold room.",		"This is a low room with a crude note on the wall. The note says, \n\t\"You won't get it up the steps\"."	),
		EASTFISSURE(				"East Bank",				"You're on east bank of fissure.",		"You are on the east bank of a fissure slicing clear across the hall. The mist is quite thick here, and the fissure is too wide to jump."	),
		WESTFISSURE(				"West Bank",				"You're on the west side of the fissure in the Hall of Mists."	),
		WESTMIST(					"Hall Of Mists",			"You're at west end of Hall of Mists.",	"You are at the west end of Hall of Mists. A low wide crawl continues west and another goes north. To the south is a little passage 6 feet off the floor."	),
		
		ALIKE1(	AdventMain.alikeT,	AdventMain.alikePassages	),	ALIKE2(	AdventMain.alikeT,	AdventMain.alikePassages	),	ALIKE3(	AdventMain.alikeT,	AdventMain.alikePassages	),	ALIKE4(	AdventMain.alikeT,	AdventMain.alikePassages	),	ALIKE5(	AdventMain.alikeT,	AdventMain.alikePassages	),	ALIKE6(	AdventMain.alikeT,	AdventMain.alikePassages	),	ALIKE7(	AdventMain.alikeT,	AdventMain.alikePassages	),	ALIKE8(	AdventMain.alikeT,	AdventMain.alikePassages	),	 ALIKE9(	AdventMain.alikeT,	AdventMain.alikePassages	),	ALIKE10(	AdventMain.alikeT,	AdventMain.alikePassages	),	ALIKE11(	AdventMain.alikeT,	AdventMain.alikePassages	),	ALIKE12(	AdventMain.alikeT,	AdventMain.alikePassages	),	ALIKE13(	AdventMain.alikeT,	AdventMain.alikePassages	),	ALIKE14(	AdventMain.alikeT,	AdventMain.alikePassages	),	
		
		BRINK	(					"Brink Of Pit",			"You're at brink of pit.",				"You are on the brink of a thirty-foot pit with a massive orange column down one wall. You could climb down here but you could not get back up. The maze continues at this level."	),
		EASTLONG(					"Long Hall East",			"You are at east end of long hall.",		"You are at the east end of a very long hall apparently without side chambers. To the east a low wide crawl slants up. To the north a round two foot hole slants down."	),
		WESTLONG(					"Long Hall West",			"You are at west end of long hall.",		"You are at the west end of a very long featureless hall. The hall joins up with a narrow north/south passage."	),
		
		DIFF0	(	AdventMain.diffT,	"You are in a maze of twisty little passages, all different."	),
		DIFF1	(	AdventMain.diffT,	"You are in a maze of twisting little passages, all different."	), 
		DIFF2	(	AdventMain.diffT,	"You are in a little maze of twisty passages, all different."	),
		DIFF3	(	AdventMain.diffT,	"You are in a twisting maze of little passages, all different."	),
		DIFF4	(	AdventMain.diffT,	"You are in a twisting little maze of passages, all different."	),
		DIFF5	(	AdventMain.diffT,	"You are in a twisty little maze of passages, all different."	),
		DIFF6	(	AdventMain.diffT,	"You are in a twisty maze of little passages, all different."	),
		DIFF7	(	AdventMain.diffT,	"You are in a little twisty maze of passages, all different."	),
		DIFF8	(	AdventMain.diffT,	"You are in a maze of little twisting passages, all different."	),
		DIFF9	(	AdventMain.diffT,	"You are in a maze of little twisty passages, all different."	),
		DIFF10	(	AdventMain.diffT,	"You are in a little maze of twisting passages, all different."	),
		PONY	(	AdventMain.deadEndT,	AdventMain.deadEnd	),
		
		CROSS	(					"Crossover",				"You are at a crossover of a high N/S passage and a low E/W one."	),
		HALLOFMOUNTAINKING(			"Hall Of The Mt. King",	"You're in Hall of Mt King.",			"You are in the Hall of the Mountain King, with passages off in all directions."	),
		WEST	(					"West Side Chamber",		"You're in west side chamber.",			"You are in the west side chamber of the Hall of the Mountain King. A passage continues west and up here."	),
		SOUTH	(					"South Side Chamber",		"You are in the south side chamber."),
		NS		(					"North/South Passage",		"You're in a N/S passage.",				"You are in a low N/S passage at a hole in the floor. The hole goes down to an E/W passage."	),
		Y2		(					"\"Y2\"",					"You're at \"Y2\".",						"You are in a large room, with a passage to the south, a passage to the west, and a wall of broken rock to the east. There is a large \"Y2\" on a rock in the room's center."	),
		JUMBLE	(					"Rock Jumble",				"You are in a jumble of rock, with cracks everywhere."	),
		EASTWINDOW(					"Window",					"You're at window on pit.",				"You're at a low window overlooking a huge pit, which extends up out of sight.	A floor is indistinctly visible over 50 feet below. Traces of white mist cover the floor of the pit, becoming thicker to the right. Marks in the dust around the window would seem to indicate that someone has been here recently. Directly across the pit from you and 25 feet away there is a similar window looking into a lighted room. A shadowy figure can be seen there peering back at you."	),
		
		DIRTY	(					"Dirty Passage",			"You're in dirty passage.",				"You are in a dirty broken passage. To the east is a crawl. To the west is a large passage. Above you is a hole to another passage."	),
		CLEAN	(					"Clean Pit",				"You're by a clean pit.",				"You are on the brink of a small clean climbable pit. A crawl leads west."	),
		WET		(	true,	"Wet Pit",					"You're in pit by stream.",				"You are in the bottom of a small pit with a little stream, which enters and exits through tiny slits."	), 
		DUSTY	(					"Dusty Room",				"You're in dusty rock room.",			"You are in a large room full of dusty rocks. There is a big hole in the floor. There are cracks everywhere, and a passage leading east."	),
		COMPLEX	(					"Complex Junction",		"You're at complex junction.",			"You are at a complex junction. A low hands and knees passage from the north joins a higher crawl from the east to make a walking passage going west. There is also a large room above. The air is damp here."	),
		
		SHELL	(					"Shell Room",				"You're in Shell Room.",					"You are in a large room carved out of sedimentary rock. The floor and walls are littered with bits of shells embedded in the stone. A shallow passage proceeds downward, and a somewhat steeper one leads up. A low hands-and-knees passage enters from the south."	),
		ARCH	(					"Arched Hall",				"You're in arched hall.",				"You are in an arched hall. A coral passage once continued up and east from here, but is now blocked by debris. The air smells of sea water."	),
		RAGGED	(					"Ragged Corridor",			"You are in a long sloping corridor with ragged sharp walls."	),
		CULDESAC(					"Cul-de-sac",				"You are in a cul-de-sac about eight feet across."	),
		ANTE	(					"Anteroom",				"You're in anteroom.",					"You are in an anteroom leading to a large passage to the east. Small passages go west and up. The remnants of recent digging are evident.\n\n\tA sign in midair here says \n\t\t\"CAVE UNDER CONSTRUCTION BEYOND THIS POINT.\n\t\tPROCEED AT OWN RISK\n\t\t[WITT CONSTRUCTION COMPANY]\""	),
		WITT	(					"Witt's End",				"You are at Witt's End.",				"You are at Witt's End. Passages lead off in \"all\" directions."	),

		BEDQUILT(					"Bedquilt",				"You're in Bedquilt.",					"You are in Bedquilt, a long E/W passage with holes everywhere. \nTo explore at random select north, south, up or down."	),
		CHEESE	(					"Swiss Cheese Room",		"You're in Swiss Cheese Room.",			"You are in a room whose walls resemble swiss cheese. \nObvious passages go west, east, ne, and nw. Part of the room is occupied by a large bedrock block."	),
		SOFT	(					"Soft Room",				"You're in Soft Room.",					"You are in the Soft Room. The walls are covered with heavy curtains, the floor with a thick pile carpet. Moss covers the ceiling."	),
		
		EAST2PIT(					"East Twopit Room",		"You're at east end of Twopit Room.",	"You are at the east end of the twopit room. The floor here is littered with thin rock slabs, which make it easy to descend the pits. There is a path here bypassing the pits to connect passages from east and west. There are holes all over, but the only big one is on the wall directly over the west pit where you can't get at it."	),
		WEST2PIT(					"West Twopit Room",		"You're at west end of Twopit Room.",	"You are at the west end of the twopit room. There is a large hole in the wall above the pit at this end of the room."	),
		EASTPIT	(					"East Pit",				"You're in east pit.",					"You are that the bottom of the eastern pit in the twopit room. There is a small pool of oil in one corner of the pit."	),
		WESTPIT	(					"West Pit",				"You're in west pit.",					"You are at the bottom of the western pit in the twopit room. There is a large hole in the wall about 25 feet above you."	),
			
		NARROW	(					"Narrow Corridor",			"You're in narrow corridor.",			"You are in a long, narrow corridor stretching out of sight to the west. At the eastern end is a hole through which you can see a profusion of leaves."	),
		GIANT	(					"Giant Room",				"You're in Giant Room.",					"You are in the Giant Room. The ceiling here is too high up for your lamp to show it. Cavernous passages lead east, north, and south. \n\nOn the west wall is scrawled the inscription, \n\t\"FEE FIE FOE FOO\"[sic]."	),
		BLOCK	(					"Blocked Passage",			"The passage here is blocked by a recent cave-in."	),
		IMMENSE	(					"Immense Passage",			"You are at one end of an immense north/south passage."	),
		
		FALLS	(	true,	"Falls",					"You're in cavern with waterfall.",		"You are in a magnificet cavern with a rushing stream, which cascades over a sparkling waterfall into a roaring whirlpool that disappears through a hole in the floor.\nPassages exit to the south and west."	),
		STEEP	(					"Steep Incline",			"You're at steep incline above large room.",	"You are at the top of a steep incline above a large room. You could climb down here, but you would not be able to climb up. There is a passage leading back to the north."	),
		ABOVEP	(					AdventMain.secretCanyon,		"You are in a secret N/S canyon above a sizable passage."	),
		SJUNC	(					"Secret Canyon Junction",	"You're at junction of three secret canyons.",	"You are in a secret canyon at a junction of three canyons, bearing north, south, and SE. The north one is as tall as the other two combined."	),
		TIGHT	(					"Tight Canyon",			"The canyon here becomes too tight to go further south."	),
		LOW		(					"Low Room",				"You are in a large low room. Crawls lead north, SE, and SW."	),
		CRAWL	(					"Dead End Crawl",			"Dead end crawl."	),
		WESTWINDOW(					"Window",					"You're at window on pit.",				"You're at a low window overlooking a huge pit, which extends up out of sight. A floor is indistinctly visible over 50 feet below. Traces of white mist cover the floor of the pit, becoming thicker to the left. Marks in the dust around the window would seem to indicate that someone has been here recently. Directly across the pit from you and 25 feet away there is a similar window looking into a lighted room. A shadowy figure can be seen there peering back at you."	),
		
		ORIENTAL(					"Oriental Room",			"You're in Oriental Room.",				"This is the Oriental Room. Ancient oriental cave drawings cover the walls. A gently sloping passage leads upward to the north, another passage leads se, and a hands and knees crawl leads west."	),
		MISTY	(					"Misty Cavern",			"You're in misty cavern.",				"You are following a wide path around the outer edge of a large cavern. Far below, through a heavy white mist, strange splashing noises can be heard. The mist rises up through a fissure in the ceiling. The path exits to the south and west."	),
		ALCOVE	(					"Alcove",					"You're in alcove.",						"You are in an alcove. A small NW path seems to widen after a short distance. An extremely tight tunnel leads east. It looks like a very tight squeeze. An eerie light can be seen at the other end."	),
		PROOM	(					"Plover Room",				"You are in Plover Room.",				"You're in a small chamber lit by an eerie green light. An extremely narrow tunnel exits to the west. A dark corridor leads NE."	),
		DROOM	(					"Dark Room",				"You're in the Dark-Room.",				"You're in the Dark-Room. A corridor leading south is the only exit."	),

		SLAB	(					"Slab Room",				"You're in Slab Room.",					"You are in a large low circular chamber whose floor is an immense slab fallen from the ceiling. (Slab Room). There once were large passages to the east and west, but they are now filled with boulders. Low small passages go north and south, and the south one quickly bends west around the boulders."	),
		ABOVER	(					AdventMain.secretCanyon,		"You are in a secret N/S canyon above a large room."	),
		MIRROR	(					"Mirror Canyon",			"You're in mirror canyon.",				"You are in a north/south canyon about 25 feet across. The floor is covered by white mist seeping in from the north. The walls extend upward for well over 100 feet. Suspended from some unseen point far above you, an enormous two-sided mirror is hanging parallel to and midway between the canyon walls. (The mirror is obviously provided for the use of the dwarves, who as you know are extremely vain.) A small window can be seen in either wall, some fifty feet up."	),
		RESER	(	true,	"Reservoir",				"You're at reservoir.",					"You are at the edge of a large underground reservoir. An opaque cloud of white mist fills the room and rises rapidly upward. The lake is fed by a stream, which tumbles out of a hole in the wall about 10 feet overhead and splashes noisily into the water somewhere within the mist. The only passage goes back toward the south."	), 
		
		SCAN1	(			AdventMain.secretCanyon,				"You're in a secret canyon that exits to the north and east."	),
		SCAN2	(			AdventMain.secretCanyon,				"You're in a secret canyon that exits to the north and east."	),
		SCAN3	(			AdventMain.secretCanyon,				"You're in a secret canyon that exits to the north and east."	),
		SECRET	(			AdventMain.secretCanyon,				"You're in secret E/W canyon above tight canyon.",	"You are in a secret canyon, which here runs E/W. It crosses over an very tight canyon 15 feet below. If you go down you may not be able to get back up."	),

		WIDE	(					"Wide Place",				"You're at a wide place in a very tight N/S canyon."	),
		STALACTITE(					"Stalactite",				"You're on top of stalactite.",			"A large stalactite extends from the roof and almost reaches the floor below. You could climb down, but you would be unable to reach it to climb back up."	),
		TALL	(					"Tall Canyon",				"You're in tall E/W canyon.",			"You are in a tall E/W canyon. A low tight crawl goes 3 feet north and seems to open up."	),
		BOULDERS(					AdventMain.deadEndT,			"The canyon runs into a mass of boulders --- dead end."	),
		
		SCORR	(					"Sloping Corridor",		"You are in sloping corridor.",			"You are in a long winding corridor sloping out of sight in both directions."	),
		SWSIDE	(					"South West Side",			"You're on SW side of chasm.",			"You are on one side of a large, deep chasm. A heavy white mist rising up from below obscures all view of the far side. A SW path leads away from the chasm into a winding corridor."	),
		
		DEAD0(	AdventMain.deadEndT,	AdventMain.deadEnd	),	DEAD1(	AdventMain.deadEndT,	AdventMain.deadEnd	),	DEAD2(	AdventMain.deadEndT,	AdventMain.deadEnd	),	DEAD3(	AdventMain.deadEndT,	AdventMain.deadEnd	),	DEAD4(	AdventMain.deadEndT,	AdventMain.deadEnd	),	DEAD5(	AdventMain.deadEndT,	AdventMain.deadEnd	),	DEAD6(	AdventMain.deadEndT,	AdventMain.deadEnd	),	DEAD7(	AdventMain.deadEndT,	AdventMain.deadEnd	),	DEAD8(	AdventMain.deadEndT,	AdventMain.deadEnd	),	DEAD9(	AdventMain.deadEndT,	AdventMain.deadEnd	),	DEAD10(	AdventMain.deadEndT,	AdventMain.deadEnd	),	DEAD11(	AdventMain.deadEndT,	AdventMain.deadEnd	),
		
		NESIDE	(					"North East Side",			"You're on NE side of chasm.",			"You are on the far side of the chasm. A NE path leads away from the chasm on this side."	),
		CORR	(					"Corridor",				"You're in corridor.",					"You're in a long east/west corridor. A faint rumbling noise can be heard in the distance."	),
		FORK	(					"Fork In Path",			"You're at fork in path.",				"The path forks here. The left fork leads northeast. A dull rumbling seems to get louder in that direction. The right fork leads southeast down a gentle slope. The main corridor enters from the west."	),
		WARM	(					"Warm Junction",			"You're at junction with warm walls.",	"The walls are quite warm here. From the north can be heard a steady roar, so loud that the entire cave seems to be trembling. Another passage leads south, and a low crawl goes east."	),
		VIEW	(					"Breath-Taking View",		"You're at breath-taking view.",			"You are on the edge of a breath-taking view. Far below you is an active volcano, from which great gouts of molten lava come surging out, cascading back down into the depths. The glowing rock fills the farthest reaches of the cavern with a blood-red glare, giving every-thing an eerie, macabre appearance. The air is filled with flickering sparks of ash and a heavy smell of brimstone. The walls are hot to the touch, and the thundering of the volcano drowns out all other sounds. Embedded in the jagged roof far overhead are myriad twisted formations, composed of pure white alabaster, which scatter the murky light into sinister apparitions upon the walls. To one side is a deep gorge, filled with a bizarre chaos of tortured rock that seems to have been crafted by the Devil himself. An immense river of fire crashes out from the depths of the volcano, burns its way through the gorge, and plummets into a bottomless pit far off to your left. To the right, am immense geyser of blistering steam erupts continuously from a barren island in the center of a sulfurous lake, which bubbles ominously. The far right wall is aflame with an incandescence of its own, which lends an additional infernal splendor to the already hellish scene. \nA dark, foreboding passage exits to the south."	),
		CHAMBER	(					"Boulder Chamber",			"You're in chamber of boulders.",		"You are in a small chamber filled with large boulders. The walls are very warm, causing the air in the room to be almost stifling from the heat. The only exit is a crawl heading west, through which a low rumbling noise is coming."	),
		LIME	(					"Limestone Passage",		"You're in limestone passage.",			"You are walking along a gently sloping north/south passage lined with oddly shapped limestone formations."	),
		FBARR	(					"In Front Of Barren Room",	"You're in front of barren room.",		"You are standing at the entrance to a large, barren room. \nA sign posted above the entrance reads:\n\t\"CAUTION! BEAR IN ROOM!\""	),
		BARR	(					"Barren Room",				"You're in barren room.",				"You are inside a barren room. The center of the room is completely empty except for some dust. Marks in the dust lead away toward the far end of the room. The only exit is the way you came in."	),
			
		NEEND	(					"North East End",			"You're at NE end.",						"You are at the northeast end of an immense room, even larger than the Giant Room. It appears to be a repository for the \"Adventure\" program. Massive torches far overhead bathe the room with smoky yellow light. Scattered about you can be seen a pile of bottles (all of them empty), a nursery of young beanstalks murmering quietly, a bed of oysters, a bundle of black rods with rusty stars on their ends, and a collection of brass lanterns. Off to one side a great many dwarves are sleeping on the floor, snoring loudly. \nA sign nearby reads:\n\t\"DO NOT DISTURB THE DWARVES!\"\nAn immense mirror is hanging against one wall, and stretches to the other end of the room, where various other sundry objects can be glimpsed dimly in the distance."	),
		SWEND	(					"South West End",			"You're at SW end.",						"You are at the southwest end of the repository. To one side is a pit full of fierce green snakes. On the other side is a row of small wicker cages, each of which contains a little sulking bird. In one corner is a bundle of black rods with rusty marks on their ends. A large number of velvet pillows are scattered about on the floor. A vast mirror stretches off to the northeast. At your feet is a large steel grate, next to which is a sign that reads, \n\t\"TREASURE VAULT. KEYS IN MAIN OFFICE.\""	),

		CRACK(), NECK(), LOSE(), CANT(), CLIMB(), CHECK(), SNAKED(), THRU(), DUCK(), SEWER(), UPNOUT(), DIDIT(),
		REMARK();
		
		public static Locations[] locate = Locations.values();
		
		public final String title;
		public final String shortDescription;
		public final String longDescription;
		public final boolean hasWater;
		public int visits = 0;
		
		private Locations()
		{	this.title = AdventMain.empty;	this.shortDescription = AdventMain.empty;	this.longDescription = AdventMain.empty;	this.hasWater = false;	}
		
		private Locations(String title, String shortDescription)
		{	this.title = title;	this.shortDescription = shortDescription;	this.longDescription = AdventMain.empty;	this.hasWater = false;	}
		
		private Locations(String title, String shortDescription, String longDescription)
		{	this.title = title;	this.shortDescription = shortDescription;	this.longDescription = longDescription;	this.hasWater = false;	}
		
		Locations(boolean hasWater, String title, String shortDescription)
		{	this.title = title;	this.shortDescription = shortDescription;	this.longDescription = AdventMain.empty;	this.hasWater = hasWater;	}
		
		Locations(boolean hasWater, String title, String shortDescription, String longDescription)
		{	this.title = title;	this.shortDescription = shortDescription;	this.longDescription = longDescription;	this.hasWater = hasWater;	}
		
		public int minLoc(){ return EASTMIST.ordinal(); }
		
		public int maxLoc(){ return DEAD0.ordinal(); }
		
		public boolean critters(Locations here)
		{	return (here.ordinal() > EASTMIST.ordinal() && here.ordinal() < DEAD0.ordinal() && !(here == PROOM) && !(here == DROOM));	}
		
		public boolean outside(Locations here)
		{	return (here.ordinal() > INHAND.ordinal() && here.ordinal() < DEBRIS.ordinal());	}
		
		public boolean outsideCave(Locations here)
		{	return (here.ordinal() > INHAND.ordinal() && here.ordinal() < INSIDE.ordinal());	}

		//TODO should not be outside.ordinal twice
		public boolean upperCave(Locations here)
		{	return (here.ordinal() > OUTSIDE.ordinal() && here.ordinal() < OUTSIDE.ordinal());	}
		
		public int getOrdinal(Locations here){ return here.ordinal(); }
		
		public boolean dontNeedLamp(Locations here)
		{	return (outside(here)||here == VIEW||here == NEEND||here == SWEND||here == PROOM);	}
		
		public static String getDescription(Locations here, int brief)
		{
			//here.visits++;
			return (!(here.longDescription.equals(AdventMain.empty)) && ((brief == 0 && here.visits % 5 == 0) || (brief == 2) || (here.visits == 0))) ? here.longDescription : here.shortDescription;
		}
		
		public String getLongDescription(Locations here)
		{	return (here.longDescription.equals(AdventMain.empty)) ? "" : here.longDescription;	}
		
		public static int[] getVisitsArray()
		{
			int[] visitsArray = new int[locate.length];
			for(int i = 0; i < locate.length; i++)
			{	visitsArray[i] = locate[i].visits;	}
			return visitsArray;
		}
		
		public static void loadVisits(int[] visits)
		{
			for(int i = 0; i < locate.length; i++)
			{	locate[i].visits = visits[i];	}
		}
		
		public static void takeObject(GameObjects thing){ thing.location = INHAND; }
		
		public static void voidObject(GameObjects thing){ thing.location = THEVOID; }
		
		public static void placeObject(GameObjects thing, Locations here){ thing.location = here; }
		
		public Locations moveTo(Movement destination, Locations here)
		{
			AdventGame game = AdventMain.ADVENT;
			double chance = AdventMain.generate();
			Locations next = null;
			switch(here)
			{
				case ROAD: next = switch(destination)
					{
						case WEST, UP, ROAD, HILL					:	yield HILL;
						case EAST, BUILDING, IN, ENTER				: 	yield BUILDING;
						case SOUTH, DOWN, GULLY, STREAM, DOWNSTREAM	: 	yield VALLEY;
						case NORTH, FOREST							:	yield FOREST;
						case DEPRESSION								:	yield OUTSIDE;
						default: yield THEVOID;
					};break;
				
				case HILL: next = switch(destination)
					{
						case ROAD, BUILDING, FORWARD, EAST, DOWN	:	yield ROAD;
						case FOREST, NORTH, SOUTH					:	yield FOREST;
						default: yield THEVOID;
					};break;
				
				case BUILDING: next = switch(destination)
					{
						case ENTER, OUT, OUTDOORS, WEST			:	yield ROAD;
						case XYZZY								:	yield DEBRIS;
						case PLUGH								:	yield Y2;
						case DOWNSTREAM, STREAM					:	yield SEWER;
						default: yield THEVOID;
					};break;
				
				case VALLEY: next = switch(destination)
					{
						case UPSTREAM, BUILDING, NORTH			:	yield ROAD;
						case FOREST, EAST, WEST, UP				:	yield FOREST;
						case DOWNSTREAM, SOUTH, DOWN			:	yield SLIT;
						case DEPRESSION							:	yield OUTSIDE;
						default: yield THEVOID;
					};break;
					
				case FOREST: next = switch(destination)
					{
						case VALLEY, EAST, DOWN					:	yield VALLEY;
						case FOREST								:	yield (chance > .49 ? FOREST : WOODS);
						case FORWARD							:	yield (chance < .51 ? FOREST : WOODS);
						case NORTH								:	yield (chance > .49 ? FOREST : WOODS);
						case WEST, SOUTH						:	yield FOREST;
						default: yield THEVOID;
					};break;
					
				case WOODS: next = switch(destination)
					{
						case ROAD, NORTH						:	yield ROAD;
						case VALLEY, EAST, WEST, DOWN			:	yield VALLEY;
						case FOREST, SOUTH						:	yield FOREST;
						default: yield THEVOID;
					};break;
					
				case SLIT: next = switch(destination)
					{
						case BUILDING							:	yield ROAD;
						case UPSTREAM, UP, NORTH				:	yield VALLEY;
						case FOREST, WEST, EAST					:	yield FOREST;
						case DOWNSTREAM, DOWN, ROCK, BED, SOUTH	:	yield OUTSIDE;
						case SLIT								:	yield REMARK;
						default: yield THEVOID;
					};break;

				case OUTSIDE: next = switch(destination)
					{
						case FOREST, EAST, WEST, SOUTH			:	yield FOREST;
						case BUILDING, ROAD						:	yield ROAD;
						case UPSTREAM, UP, GULLY, NORTH			:	yield SLIT;
						case ENTER, IN, DOWN					: 	yield throughGrate(true);
						default: yield THEVOID;
					};break;
					
				case INSIDE: next = switch(destination)
					{
						case CRAWL, COBBLE, IN, WEST			:	yield COBBLES;
						case PIT								:	yield SMALLPIT;
						case DEBRIS								:	yield DEBRIS;
						case OUT, UP							:	yield throughGrate(false);
						default: yield THEVOID;
					};break;
						
				case COBBLES: next = switch(destination)
					{
						case OUT, SURFACE, NOWHERE, EAST		:	yield INSIDE;
						case DEBRIS, DARK, WEST, IN				:	yield DEBRIS;
						case PIT								:	yield SMALLPIT;
						default: yield THEVOID;
					};break;
					
				case DEBRIS: next = switch(destination)
					{
						case DEPRESSION							:	yield throughGrate(false);
						case ENTRANCE							:	yield INSIDE;
						case COBBLE, CRAWL, PASSAGE, LOW, EAST	:	yield COBBLES;
						case CANYON, IN, UP, WEST				:	yield AWKWARD;
						case XYZZY								:	yield BUILDING;
						case PIT								:	yield SMALLPIT;
						default: yield THEVOID;
					};break;
				
				case AWKWARD: next = switch(destination)
					{
						case DEPRESSION						:	yield throughGrate(false);
						case ENTRANCE						:	yield INSIDE;
						case DOWN, EAST, DEBRIS				:	yield DEBRIS;
						case IN, UP, WEST					:	yield BIRD;
						case PIT							:	yield SMALLPIT;
						default: yield THEVOID;
					};break;
				
				case BIRD: next = switch(destination)
					{
						case DEPRESSION						:	yield throughGrate(false);
						case ENTRANCE						:	yield INSIDE;
						case DEBRIS							:	yield DEBRIS;
						case CANYON, EAST					:	yield AWKWARD;
						case PASSAGE, PIT, WEST				:	yield SMALLPIT;
						default: yield THEVOID;
					};break;
					
				case SMALLPIT: next = switch(destination)
					{
						case DEPRESSION						:	yield throughGrate(false);
						case ENTRANCE						:	yield INSIDE;
						case DEBRIS							:	yield DEBRIS;
						case PASSAGE, EAST					:	yield BIRD;
						case DOWN, PIT, STEPS				:	yield (game.goldInInventory ? NECK : EASTMIST);
						case CRACK, WEST					:	yield CRACK;
						default: yield THEVOID;
					};break;
					
				case EASTMIST: next = switch(destination)
					{
						case LEFT, SOUTH					:	yield NUGGET;
						case FORWARD, HALL, WEST			:	yield EASTFISSURE;
						case STAIRS, DOWN, NORTH			:	yield HALLOFMOUNTAINKING;
						case UP, PIT						: 	yield (game.goldInInventory ? CANT : SMALLPIT);
						case STEPS, DOME, PASSAGE, EAST		:	yield (game.goldInInventory ? CANT : THEVOID);
						case Y2								:	yield JUMBLE;
						default: yield THEVOID;
					};break;
				
				case NUGGET: next = switch(destination)
					{
						case HALL, OUT, NORTH				:	yield EASTMIST;
						default: yield THEVOID;
					};break;
					
				case EASTFISSURE: next = switch(destination)
					{
						case HALL, EAST						:	yield EASTMIST;
						case JUMP, FORWARD					:	yield ((!game.crystalBridgeIsThere) ? LOSE : THEVOID);
						case OVER, ACROSS, WEST, CROSS		:	yield bridgeRemark(true);
						default: yield THEVOID;
					};break;
					
				case WESTFISSURE: next = switch(destination)
					{
						case NORTH								:	yield THRU;
						case WEST								:	yield WESTMIST;
						case JUMP								:	yield ((game.crystalBridgeIsThere) ? REMARK : THEVOID);
						case FORWARD, OVER, ACROSS, EAST, CROSS	:	yield bridgeRemark(false);
						default: yield THEVOID;
					};break;
					
				case WESTMIST: next = switch(destination)
					{
						case SOUTH, UP, PASSAGE, CLIMB			:	yield ALIKE1;
						case EAST								:	yield WESTFISSURE;
						case NORTH								:	yield DUCK;
						case WEST, CRAWL						:	yield EASTLONG;
						default: yield THEVOID;
					};break;
					
				case ALIKE1: next = switch(destination)
					{
						case UP			:	yield WESTMIST;
						case NORTH		:	yield ALIKE1;
						case EAST		:	yield ALIKE2;
						case SOUTH		:	yield ALIKE4;
						case WEST		:	yield ALIKE11;
						default: yield THEVOID;
					};break;
					
				case ALIKE2: next = switch(destination)
					{
						case EAST		:	yield ALIKE4;
						case SOUTH		:	yield ALIKE3;
						case WEST		:	yield ALIKE1;
						default: yield THEVOID;
					};break;
					
				case ALIKE3: next = switch(destination)
					{
						case DOWN		:	yield DEAD5;
						case NORTH		:	yield DEAD9;
						case EAST		:	yield ALIKE2;
						case SOUTH		:	yield ALIKE6;
						default: yield THEVOID;
					};break;
					
				case ALIKE4: next = switch(destination)
					{
						case UP, DOWN	:	yield ALIKE14;
						case NORTH		:	yield ALIKE2;
						case EAST		:	yield DEAD3;
						case SOUTH		:	yield DEAD4;
						case WEST		:	yield ALIKE1;
						default: yield THEVOID;
					};break;
				
				case ALIKE5: next = switch(destination)
					{
						case EAST		:	yield ALIKE6;
						case WEST		:	yield ALIKE7;
						default: yield THEVOID;
					};break;
			
				case ALIKE6: next = switch(destination)
					{
						case DOWN		:	yield ALIKE7;
						case WEST		:	yield ALIKE5;
						case EAST		:	yield ALIKE3;
						case SOUTH		:	yield ALIKE8;
						default: yield THEVOID;
					};break;
					
				case ALIKE7: next = switch(destination)
					{
						case UP			:	yield ALIKE6;
						case WEST		:	yield ALIKE5;
						case EAST		:	yield ALIKE8;
						case SOUTH		:	yield ALIKE9;
						default: yield THEVOID;
					};break;
					
				case ALIKE8: next = switch(destination)
					{
						case UP			:	yield ALIKE9;
						case WEST		:	yield ALIKE6;
						case EAST		:	yield ALIKE7;
						case SOUTH		:	yield ALIKE8;
						case NORTH		:	yield ALIKE10;
						case DOWN		:	yield DEAD11;
						default: yield THEVOID;
					};break;
					
				case ALIKE9: next = switch(destination)
					{
						case WEST		:	yield ALIKE7;
						case NORTH		:	yield ALIKE8;
						case SOUTH		:	yield DEAD6;
						default: yield THEVOID;
					};break;
					
				case ALIKE10: next = switch(destination)
					{
						case NORTH		:	yield ALIKE10;
						case WEST		:	yield ALIKE8;
						case EAST		:	yield BRINK;
						case DOWN		:	yield DEAD7;
						default: yield THEVOID;
					};break;
					
				case ALIKE11: next = switch(destination)
					{
						case NORTH		:	yield ALIKE1;
						case EAST		:	yield DEAD1;
						case SOUTH, WEST:	yield ALIKE11;
						default: yield THEVOID;
					};break;
					
				case ALIKE12: next = switch(destination)
					{
						case WEST		:	yield DEAD10;
						case EAST		:	yield ALIKE13;
						case SOUTH		:	yield BRINK;
						default: yield THEVOID;
					};break;

				case ALIKE13: next = switch(destination)
					{
						case NORTH		:	yield BRINK;
						case WEST		:	yield ALIKE12;
						case NORTHWEST	:	yield DEAD2;
						default: yield THEVOID;
					};break;
					
				case ALIKE14: next = switch(destination)
					{
						case UP, DOWN	:	yield ALIKE4;
						default: yield THEVOID;
					};break;
					
				case BRINK: next = switch(destination)
					{
						case DOWN, CLIMB:	yield BIRD;
						case WEST		:	yield ALIKE10;
						case SOUTH		:	yield DEAD8;
						case NORTH		:	yield ALIKE12;
						case EAST		:	yield ALIKE13;
						default: yield THEVOID;
					};break;
					
				case EASTLONG: next = switch(destination)
					{
						case EAST, UP, CRAWL	:	yield WESTMIST;
						case WEST				:	yield WESTLONG;
						case NORTH, DOWN, HOLE	:	yield CROSS;
						default: yield THEVOID;
					};break;
					
				case WESTLONG: next = switch(destination)
					{
						case NORTH		:	yield CROSS;
						case EAST		:	yield EASTLONG;
						case SOUTH		:	yield DIFF0;
						default: yield THEVOID;
					};break;
					
				case DIFF0: next = switch(destination)
					{
						case SOUTH		:	yield DIFF1;
						case SOUTHWEST	:	yield DIFF2;
						case NORTHEAST	:	yield DIFF3;
						case SOUTHEAST	:	yield DIFF4;
						case UP			:	yield DIFF5;
						case NORTHWEST	:	yield DIFF6;
						case EAST		:	yield DIFF7;
						case WEST		:	yield DIFF8;
						case NORTH		:	yield DIFF9;
						case DOWN		:	yield WESTLONG;
						default: yield THEVOID;
					};break;
					
				case DIFF1: next = switch(destination)
					{
						case WEST		:	yield DIFF0;
						case SOUTHEAST	:	yield DIFF1;
						case NORTHWEST	:	yield DIFF3;
						case SOUTHWEST	:	yield DIFF4;
						case NORTHEAST	:	yield DIFF5;
						case UP			:	yield DIFF6;
						case DOWN		:	yield DIFF7;
						case NORTH		:	yield DIFF8;
						case SOUTH		:	yield DIFF9;
						case EAST		:	yield DIFF10;
						default: yield THEVOID;
					};break;
					
				case DIFF2: next = switch(destination)
					{
						case NORTHWEST	:	yield DIFF0;
						case UP			:	yield DIFF1;
						case NORTH		:	yield DIFF3;
						case SOUTH		:	yield DIFF4;
						case WEST		:	yield DIFF5;
						case SOUTHWEST	:	yield DIFF6;
						case NORTHEAST	:	yield DIFF7;
						case EAST		:	yield DIFF8;
						case DOWN		:	yield DIFF9;
						case SOUTHEAST	:	yield DIFF10;
						default: yield THEVOID;
					};break;
					
				case DIFF3: next = switch(destination)
					{
						case UP			:	yield DIFF0;
						case DOWN		:	yield DIFF1;
						case WEST		:	yield DIFF2;
						case NORTHEAST	:	yield DIFF4;
						case SOUTHWEST	:	yield DIFF5;
						case EAST		:	yield DIFF6;
						case NORTH		:	yield DIFF7;
						case NORTHWEST	:	yield DIFF8;
						case SOUTHEAST	:	yield DIFF9;
						case SOUTH		:	yield DIFF10;
						default: yield THEVOID;
					};break;
					
				case DIFF4: next = switch(destination)
					{
						case NORTHEAST	:	yield DIFF0;
						case NORTH		:	yield DIFF1;
						case NORTHWEST	:	yield DIFF2;
						case SOUTHEAST	:	yield DIFF3;
						case EAST		:	yield DIFF5;
						case DOWN		:	yield DIFF6;
						case SOUTH		:	yield DIFF7;
						case UP			:	yield DIFF8;
						case WEST		:	yield DIFF9;
						case SOUTHWEST	:	yield DIFF10;
						default: yield THEVOID;
					};break;
					
				case DIFF5: next = switch(destination)
					{
						case NORTH		:	yield DIFF0;
						case SOUTHEAST	:	yield DIFF1;
						case DOWN		:	yield DIFF2;
						case SOUTH		:	yield DIFF3;
						case EAST		:	yield DIFF4;
						case WEST		:	yield DIFF6;
						case SOUTHWEST	:	yield DIFF7;
						case NORTHEAST	:	yield DIFF8;
						case NORTHWEST	:	yield DIFF9;
						case UP			:	yield DIFF10;
						default: yield THEVOID;
					};break;
					
				case DIFF6: next = switch(destination)
					{
						case EAST		:	yield DIFF0;
						case WEST		:	yield DIFF1;
						case UP			:	yield DIFF2;
						case SOUTHWEST	:	yield DIFF3;
						case DOWN		:	yield DIFF4;
						case SOUTH		:	yield DIFF5;
						case NORTHWEST	:	yield DIFF7;
						case SOUTHEAST	:	yield DIFF8;
						case NORTHEAST	:	yield DIFF9;
						case NORTH		:	yield DIFF10;
						default: yield THEVOID;
					};break;
					
				case DIFF7: next = switch(destination)
					{
						case EAST		:	yield DIFF0;
						case WEST		:	yield DIFF1;
						case UP			:	yield DIFF2;
						case SOUTHWEST	:	yield DIFF3;
						case DOWN		:	yield DIFF4;
						case SOUTH		:	yield DIFF5;
						case NORTHWEST	:	yield DIFF6;
						case SOUTHEAST	:	yield DIFF8;
						case NORTHEAST	:	yield DIFF9;
						case NORTH		:	yield DIFF10;
						default: yield THEVOID;
					};break;
					
				case DIFF8: next = switch(destination)
					{
						case DOWN		:	yield DIFF0;
						case EAST		:	yield DIFF1;
						case NORTHEAST	:	yield DIFF2;
						case UP			:	yield DIFF3;
						case WEST		:	yield DIFF4;
						case NORTH		:	yield DIFF5;
						case SOUTH		:	yield DIFF6;
						case SOUTHEAST	:	yield DIFF7;
						case SOUTHWEST	:	yield DIFF9;
						case NORTHWEST	:	yield DIFF10;
						default: yield THEVOID;
					};break;
					
				case DIFF9: next = switch(destination)
					{
						case SOUTHWEST	:	yield DIFF0;
						case NORTHWEST	:	yield DIFF1;
						case EAST		:	yield DIFF2;
						case WEST		:	yield DIFF3;
						case NORTH		:	yield DIFF4;
						case DOWN		:	yield DIFF5;
						case SOUTHEAST	:	yield DIFF6;
						case UP			:	yield DIFF7;
						case SOUTH		:	yield DIFF8;
						case NORTHEAST	:	yield DIFF10;
						default: yield THEVOID;
					};break;
					
				case DIFF10: next = switch(destination)
					{
						case SOUTHWEST	:	yield DIFF1;
						case NORTH		:	yield DIFF2;
						case EAST		:	yield DIFF3;
						case NORTHWEST	:	yield DIFF4;
						case SOUTHEAST	:	yield DIFF5;
						case NORTHEAST	:	yield DIFF6;
						case WEST		:	yield DIFF7;
						case DOWN		:	yield DIFF8;
						case UP			:	yield DIFF9;
						case SOUTH		:	yield PONY;
						default: yield THEVOID;
					};break;
					
				case PONY: next = switch(destination)
					{
						case NORTH, OUT	:	yield DIFF10;
						default: yield THEVOID;
					};break;
					
				case CROSS: next = switch(destination)
					{
						case WEST				:	yield EASTLONG;
						case NORTH				:	yield DEAD0;
						case EAST				:	yield WEST;
						case SOUTH				:	yield WESTLONG;
						default: yield THEVOID;
					};break;
					
				case HALLOFMOUNTAINKING: next = switch(destination)
					{
						case STAIRS, UP, EAST	:	yield EASTMIST;
						case NORTH, LEFT		:	yield (game.snakeInHotMK ? REMARK : NS);
						case SOUTH, RIGHT		:	yield (game.snakeInHotMK ? REMARK : SOUTH);
						case WEST, FORWARD		:	yield (game.snakeInHotMK ? REMARK : WEST);
						case SOUTHWEST			:	yield (game.snakeInHotMK ? ( Math.random() < .36 ? SECRET : REMARK ) : SECRET);
						case SECRET				:	yield SECRET;
						default: yield THEVOID;
					};break;
					
				case WEST: next = switch(destination)
					{
						case HALL, OUT, EAST	:	yield HALLOFMOUNTAINKING;
						case WEST, UP			:	yield CROSS;
						default: yield THEVOID;
					};break;
					
				case SOUTH: next = switch(destination)
					{
						case HALL, OUT, NORTH	:	yield HALLOFMOUNTAINKING;
						default: yield THEVOID;
					};break;
					
				case NS: next = switch(destination)
					{
						case HALL, OUT, SOUTH	:	yield HALLOFMOUNTAINKING;
						case Y2, NORTH			:	yield Y2;
						case DOWN, HOLE			:	yield DIRTY;
						default: yield THEVOID;
					};break;
					
				case Y2: next = switch(destination)
					{
						case PLUGH				:	yield BUILDING;
						case SOUTH				:	yield NS;
						case EAST, WALL, BROKEN	:	yield JUMBLE;
						case WEST				:	yield EASTWINDOW;
						case PLOVER				:	yield PROOM;
						default: yield THEVOID;
					};break;
					
				case JUMBLE: next = switch(destination)
					{
						case DOWN, Y2			:	yield Y2;
						case UP					:	yield EASTMIST;
						default: yield THEVOID;
					};break;
					
				case EASTWINDOW: next = switch(destination)
					{
						case EAST, Y2			:	yield Y2;
						case JUMP				:	yield NECK;
						default: yield THEVOID;
					};break;
					
				case DIRTY: next = switch(destination)
					{
						case EAST, CRAWL		:	yield CLEAN;
						case UP, HOLE			:	yield NS;
						case WEST				:	yield DUSTY;
						case BEDQUILT			:	yield BEDQUILT;
						default: yield THEVOID;
					};break;
					
				case CLEAN: next = switch(destination)
					{
						case WEST, CRAWL		:	yield DIRTY;
						case DOWN, PIT, CLIMB	:	yield WET;
						default: yield THEVOID;
					};break;
					
				case WET: next = switch(destination)
					{
						case CLIMB, UP, OUT		:	yield CLEAN;
						case SLIT, STREAM, DOWN, UPSTREAM, DOWNSTREAM:	yield REMARK;
						default: yield THEVOID;
					};break;
					
				case DUSTY: next = switch(destination)
					{
						case EAST, PASSAGE		:	yield DIRTY;
						case DOWN, HOLE, FLOOR	:	yield COMPLEX;
						case BEDQUILT			:	yield BEDQUILT;
						default: yield THEVOID;
					};break;
					
				case COMPLEX: next = switch(destination)
					{
						case UP, CLIMB, ROOM	:	yield DUSTY;
						case WEST, BEDQUILT		:	yield BEDQUILT;
						case NORTH, SHELL		:	yield SHELL;
						case EAST				:	yield ANTE;
						default: yield THEVOID;
					};break;
					
				case SHELL: next = switch(destination)
					{
						case UP, HALL			:	yield ARCH;
						case DOWN				:	yield RAGGED;
						case WEST				:	yield BEDQUILT;
						case SOUTH				:	yield ((game.isInHand(GameObjects.CLAM)||game.isInHand(GameObjects.OYSTER)) ? REMARK : COMPLEX);
						default: yield THEVOID;
					};break;
					
				case ARCH: next = switch(destination)
					{
						case DOWN, SHELL, OUT	:	yield SHELL;
						default: yield THEVOID;
					};break;
					
				case RAGGED: next = switch(destination)
					{
						case UP, SHELL			:	yield SHELL;
						case DOWN				:	yield CULDESAC;
						default: yield THEVOID;
					};break;
					
				case CULDESAC: next = switch(destination)
					{
						case UP, OUT			:	yield RAGGED;
						case SHELL				:	yield SHELL;
						default: yield THEVOID;
					};break;
					
				case ANTE: next = switch(destination)
					{
						case UP					:	yield COMPLEX;
						case WEST				:	yield BEDQUILT;
						case EAST				:	yield WITT;
						default: yield THEVOID;
					};break;
					
				case WITT: next = switch(destination)
					{
						case EAST, NORTH, SOUTH, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST, UP, DOWN:	yield atWittsEnd();
						case WEST			:	yield REMARK;
						default: yield THEVOID;
					};break;
					
				case BEDQUILT: next = switch(destination)
					{
						case EAST			:	yield COMPLEX;
						case WEST			:	yield CHEESE;
						case SOUTH			:	yield (chance < .81 ? REMARK : SLAB);
						case SLAB			:	yield SLAB;
						case UP				:	yield (chance < .81 ? REMARK : ( chance > .49 ? ABOVEP : DUSTY));
						case NORTH			:	yield (chance < .61 ? REMARK : ( chance < .76 ? LOW : SJUNC));
						case DOWN			: 	yield (chance < .81 ? REMARK : ANTE);
						default: yield THEVOID;
					};break;
					
				case CHEESE: next = switch(destination)
					{
						case NORTHEAST		:	yield BEDQUILT;
						case WEST			:	yield EAST2PIT;
						case SOUTH			:	yield (chance < .81 ? REMARK : TALL);
						case CANYON			:	yield TALL;
						case EAST			:	yield SOFT;
						case NORTHWEST		:	yield (chance < .51 ? REMARK : ORIENTAL);
						case ORIENTAL		:	yield ORIENTAL;
						default: yield THEVOID;
					};break;
					
				case SOFT: next = switch(destination)
					{
						case WEST, OUT		:	yield CHEESE;
						default: yield THEVOID;
					};break;
					
				case EAST2PIT: next = switch(destination)
					{
						case EAST			:	yield CHEESE;
						case WEST, ACROSS	:	yield WEST2PIT;
						case DOWN, PIT		:	yield EASTPIT;
						default: yield THEVOID;
					};break;
					
				case WEST2PIT: next = switch(destination)
					{
						case EAST, ACROSS	:	yield EAST2PIT;
						case WEST, SLAB		:	yield SLAB;
						case DOWN, PIT		:	yield WESTPIT;
						default: yield THEVOID;
					};break;

				case EASTPIT: next = switch(destination)
					{
						case UP, OUT			:	yield EAST2PIT;
						default: yield THEVOID;
					};break;
					
				case WESTPIT: next = switch(destination)
					{
						case UP, OUT			:	yield WEST2PIT;
						case CLIMB				:	yield ((!(game.stateOfThePlant == 2)) ? CHECK : CLIMB);
						default: yield THEVOID;
					};break;
					
				case NARROW: next = switch(destination)
					{
						case DOWN, CLIMB, EAST	:	yield WESTPIT;
						case JUMP				:	yield NECK;
						case WEST, GIANT		:	yield GIANT;
						default: yield THEVOID;
					};break;
					
				case GIANT: next = switch(destination)
					{
						case SOUTH				:	yield NARROW;
						case EAST				:	yield BLOCK;
						case NORTH				:	yield IMMENSE;
						default: yield THEVOID;
					};break;
					
				case BLOCK: next = switch(destination)
					{
						case SOUTH, GIANT, OUT	:	yield GIANT;
						default: yield THEVOID;
					};break;
					
				case IMMENSE: next = switch(destination)
					{
						case SOUTH, GIANT, PASSAGE	:	yield GIANT;
						case NORTH, ENTER, CAVERN	:	yield (game.doorHasBeenOiled ? FALLS : REMARK);
						default: yield THEVOID;
					};break;

				case FALLS: next = switch(destination)
					{
						case SOUTH, OUT				:	yield IMMENSE;
						case GIANT					:	yield GIANT;
						case WEST					:	yield STEEP;
						default: yield THEVOID;
					};break;
					
				case STEEP: next = switch(destination)
					{
						case NORTH, CAVERN, PASSAGE	:	yield FALLS;
						case DOWN, CLIMB			:	yield LOW;
						default: yield THEVOID;
					};break;
					
				case ABOVEP: next = switch(destination)
					{
						case NORTH					:	yield SJUNC;
						case DOWN, PASSAGE			:	yield BEDQUILT;
						case SOUTH					:	yield STALACTITE;
						default: yield THEVOID;
					};break;
					
				case SJUNC: next = switch(destination)
					{
						case SOUTHEAST				:	yield BEDQUILT;
						case SOUTH					:	yield ABOVEP;
						case NORTH					:	yield WESTWINDOW;
						default: yield THEVOID;
					};break;
					
				case STALACTITE: next = switch(destination)
					{
						case NORTH					:	yield ABOVEP;
						case DOWN, JUMP, CLIMB		:	yield (chance < .41 ? ALIKE6 : ( chance > .49 ? ALIKE9 : ALIKE4 ));
						default: yield THEVOID;
					};break;
					
				case LOW: next = switch(destination)
					{
						case BEDQUILT				:	yield BEDQUILT;
						case SOUTHWEST				:	yield SCORR;
						case NORTH					:	yield CRAWL;
						case SOUTHEAST, ORIENTAL	:	yield ORIENTAL;
						default: yield THEVOID;
					};break;
					
				case CRAWL: next = switch(destination)
					{
						case SOUTH, CRAWL, OUT		:	yield LOW;
						default: yield THEVOID;
					};break;
					
				case WESTWINDOW: next = switch(destination)
					{
						case WEST					:	yield SJUNC;
						case JUMP					:	yield NECK;
						default: yield THEVOID;
					};break;
					
				case ORIENTAL: next = switch(destination)
					{
						case SOUTHEAST				:	yield CHEESE;
						case WEST, CRAWL			:	yield LOW;
						case UP, NORTH, CAVERN		:	yield MISTY;
						default: yield THEVOID;
					};break;
					
				case MISTY: next = switch(destination)
					{
						case SOUTH, ORIENTAL		:	yield ORIENTAL;
						case WEST					:	yield ALCOVE;
						default: yield THEVOID;
					};break;
					
				case ALCOVE: next = switch(destination)
					{
						case NORTHWEST, CAVERN		:	yield MISTY;
						case EAST, PASSAGE, PLOVER	:	yield ((game.itemsInHand > 1 || (game.itemsInHand > 0 && !game.isInHand(GameObjects.EMERALD))) ? REMARK : PROOM);
						default: yield THEVOID;
					};break;
					
				case PROOM: next = switch(destination)
					{
						case WEST, PASSAGE, OUT	:	yield ((game.itemsInHand > 1 || (game.itemsInHand > 0 && !game.isInHand(GameObjects.EMERALD))) ? REMARK : ALCOVE);
						case PLOVER: 
							if(game.isInHand(GameObjects.EMERALD)){ game.relocate(); }
							yield Y2;
						case NORTHEAST, DARK	:	yield DROOM;
						default: yield THEVOID;
					};break;
					
				case DROOM: next = switch(destination)
					{
						case SOUTH, PLOVER, OUT	:	yield PROOM;
						default: yield THEVOID;
					};break;
					
				case SLAB: next = switch(destination)
					{
						case SOUTH				:	yield WEST2PIT;
						case UP, CLIMB			:	yield ABOVER;
						case NORTH				:	yield BEDQUILT;
						default: yield THEVOID;
					};break;
					
				case ABOVER: next = switch(destination)
					{
						case DOWN, SLAB			:	yield SLAB;
						case SOUTH				:	yield (game.dragonIsAlive ? SCAN1 : SCAN2);
						case NORTH				:	yield MIRROR;
						case RESERVOIR			:	yield RESER;
						default: yield THEVOID;
					};break;
					
				case MIRROR: next = switch(destination)
					{
						case SOUTH				:	yield ABOVER;
						case NORTH, RESERVOIR	:	yield RESER;
						default: yield THEVOID;
					};break;
					
				case RESER: next = switch(destination)
					{
						case SOUTH, OUT			:	yield MIRROR;
						default: yield THEVOID;
					};break;
					
				case SCAN1: next = switch(destination)
					{
						case NORTH, OUT			:	yield ABOVER;
						case EAST, FORWARD		:	yield REMARK;
						default: yield THEVOID;
					};break;
					
				case SCAN2: next = switch(destination)
					{
						case NORTH				:	yield ABOVER;
						case EAST				:	yield SECRET;
						default: yield THEVOID;
					};break;
					
				case SCAN3: next = switch(destination)
					{
						case EAST, OUT			:	yield SECRET;
						case NORTH, FORWARD		:	yield REMARK;
						default: yield THEVOID;
					};break;
					
				case SECRET: next = switch(destination)
					{
						case EAST				:	yield HALLOFMOUNTAINKING;
						case WEST				:	yield (game.dragonIsAlive ? SCAN3 : SCAN2);
						case DOWN				:	yield WIDE;
						default: yield THEVOID;
					};break;
					
				case WIDE: next = switch(destination)
					{
						case SOUTH				:	yield TIGHT;
						case NORTH				:	yield TALL;
						default: yield THEVOID;
					};break;
					
				case TIGHT: next = switch(destination)
					{
						case NORTH				:	yield WIDE;
						default: yield THEVOID;
					};break;
					
				case TALL: next = switch(destination)
					{
						case EAST				:	yield WIDE;
						case WEST				:	yield BOULDERS;
						case NORTH, CRAWL		:	yield CHEESE;
						default: yield THEVOID;
					};break;
					
				case BOULDERS: next = switch(destination)
					{
						case SOUTH				:	yield TALL;
						default: yield THEVOID;
					};break;
					
				case SCORR: next = switch(destination)
					{
						case DOWN				:	yield LOW;
						case UP					:	yield SWSIDE;
						default: yield THEVOID;
					};break;
					
				case SWSIDE: next = switch(destination)
					{
						case SOUTHWEST			:	yield SCORR;
						case OVER, ACROSS, CROSS, NORTHEAST:
							if(game.stateOfTheTroll < 2 || game.collapse){	yield REMARK;	}
							else
							{
								if(game.stateOfTheTroll == 3){ game.setTroll(); }
								yield NESIDE;
							}
						case JUMP			:	yield (game.collapse ? LOSE : REMARK);
						default: yield THEVOID;
					};break;
					
				case DEAD0: next = switch(destination)
					{
						case SOUTH, OUT		:	yield CROSS;
						default: yield THEVOID;
					};break;
					
				case DEAD1: next = switch(destination)
					{
						case WEST, OUT		:	yield ALIKE11;
						default: yield THEVOID;
					};break;
					
				case DEAD2: next = switch(destination)
					{
						case SOUTHEAST		:	yield ALIKE13;
						default: yield THEVOID;
					};break;
					
				case DEAD3: next = switch(destination)
					{
						case WEST, OUT		:	yield ALIKE4;
						default: yield THEVOID;
					};break;
					
				case DEAD4: next = switch(destination)
					{
						case EAST, OUT		:	yield ALIKE4;
						default: yield THEVOID;
					};break;
					
				case DEAD5: next = switch(destination)
					{
						case UP, OUT		:	yield ALIKE3;
						default: yield THEVOID;
					};break;
					
				case DEAD6: next = switch(destination)
					{
						case WEST, OUT		:	yield ALIKE9;
						default: yield THEVOID;
					};break;
					
				case DEAD7: next = switch(destination)
					{
						case UP, OUT		: 	yield ALIKE10;
						default: yield THEVOID;
					};break;
					
				case DEAD8: next = switch(destination)
					{
						case EAST, OUT		:	yield BRINK;
						default: yield THEVOID;
					};break;
					
				case DEAD9: next = switch(destination)
					{
						case SOUTH, OUT		:	yield ALIKE3;
						default: yield THEVOID;
					};break;
					
					
				case DEAD10: next = switch(destination)
					{
						case EAST, OUT		:	yield ALIKE12;
						default: yield THEVOID;
					};break;
					
				case DEAD11: next = switch(destination)
					{
						case UP, OUT		:	yield ALIKE8;
						default: yield THEVOID;
					};break;
					
				case NESIDE: next = switch(destination)
					{
						case NORTHEAST	:	yield CORR;
						case OVER, ACROSS, CROSS, SOUTHWEST:
							//System.out.println("troll " + game.stateOfTheTroll);
							if(game.stateOfTheTroll == 0 || game.stateOfTheTroll == 1){ yield REMARK; }
							else
							{	
								if(game.collapse){ yield REMARK; }
								else
								{
									if(game.stateOfTheTroll == 3) { game.stateOfTheTroll = 1; }
									else { if(game.stateOfTheBear == 2){ game.collapse(); } }
									yield SWSIDE;
								}
							}
						case JUMP		:	yield REMARK;
						case FORK		:	yield FORK;
						case VIEW		:	yield VIEW;
						case BARREN		:	yield FBARR;
						default: yield THEVOID;
					};break;
					
				case CORR: next = switch(destination)
					{
						case WEST						:	yield NESIDE;
						case EAST, FORK					:	yield FORK;
						case VIEW						:	yield VIEW;
						case BARREN						:	yield FBARR;
						default: yield THEVOID;
					};break;
					
				case FORK: next = switch(destination)
					{
						case WEST						:	yield CORR;
						case NORTHEAST, LEFT			:	yield WARM;
						case SOUTHEAST, RIGHT, DOWN		:	yield LIME;
						case VIEW						:	yield VIEW;
						case BARREN						:	yield FBARR;
						default: yield THEVOID;
					};break;
					
				case WARM: next = switch(destination)
					{
						case SOUTH, FORK				:	yield FORK;
						case NORTH, VIEW				:	yield VIEW;
						case EAST, CRAWL				:	yield CHAMBER;
						default: yield THEVOID;
					};break;
					
				case VIEW: next = switch(destination)
					{
						case SOUTH, PASSAGE, OUT		:	yield WARM;
						case FORK, DOWN					:	yield REMARK;
						case JUMP						:	yield FORK;
						default: yield THEVOID;
					};break;
					
				case CHAMBER: next = switch(destination)
					{
						case WEST, OUT, CRAWL			:	yield WARM;
						case FORK						:	yield FORK;
						case VIEW						:	yield VIEW;
						default: yield THEVOID;
					};break;
					
				case LIME: next = switch(destination)
					{
						case NORTH, UP, FORK			:	yield FORK;
						case SOUTH, DOWN, BARREN		:	yield FBARR;
						case VIEW						:	yield VIEW;
						default: yield THEVOID;
					};break;
					
				case FBARR: next = switch(destination)
					{
						case WEST, UP					:	yield LIME;
						case FORK						:	yield FORK;
						case EAST, IN, BARREN, ENTER	:	yield BARR;
						case VIEW						:	yield VIEW;
						default: yield THEVOID;
					};break;

				case BARR: next = switch(destination)
					{
						case WEST, OUT					:	yield FBARR;
						case FORK						:	yield FORK;
						case VIEW						:	yield VIEW;
						default: yield THEVOID;
					};break;
					
				case NEEND: next = switch(destination)
					{
						case SOUTHWEST					:	yield SWEND;
						default: yield THEVOID;
					};break;
					
				case SWEND: next = switch(destination)
					{
						case NORTHEAST					:	yield NEEND;
//						case DOWN						:	yield GRATE_RMK;
						default: yield THEVOID;
					};break;

				default: next = REMARK;	break;
			}
			return next;
		}
		
		private Locations atWittsEnd()
		{ return ( AdventMain.generate() < .06 ? ANTE : REMARK ); }
		
		private Locations bridgeRemark(boolean onEastSide)
		{ return ( AdventMain.ADVENT.crystalBridgeIsThere ? ( onEastSide ? WESTFISSURE : EASTFISSURE ) : REMARK ); }
		
		private Locations throughGrate(boolean goingIn)
		{ return ( AdventMain.ADVENT.grateIsUnlocked ? ( goingIn ? INSIDE : OUTSIDE ) : REMARK ) ; }

	}
}
