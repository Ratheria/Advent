package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntFunction;

import model.Locations;
import state.GameStateHandler;
import view.AdventureFrame;

//TODO beginning/normal/serious question/etc. enum instead of ints
//TODO maybe change AdventData to have n save slots for advent games 
//TODO questions as enums

public class AdventMain 
{
	public static AdventGame ADVENT;
	
	public static Random random = new Random();
	public static final Locations places = Locations.THEVOID;
	public static final ActionWords actions = ActionWords.NOTHING;
	public static final GameObjects things = GameObjects.NOTHING;
	public static final MessageWords messages = MessageWords.DENNIS;
	
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
	public static final String[] sMessages = new String[] 
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
	
	public static double generate()
	{	return random.nextDouble();	}
	
	public static void logGameInfo()
	{
		String toPrint = "";
		toPrint += " | Turns: " + ADVENT.turns;
		toPrint += " | Previous: " + ADVENT.previousLocation;
		toPrint += " | Current: " + ADVENT.currentLocation;
		toPrint += " | Lamp: " + ADVENT.lamp;
		toPrint += " | In Hand: " + ADVENT.itemsInHand;
		toPrint += " | Tally: " + ADVENT.tally;
		toPrint += " | Score: " + ADVENT.score;
		toPrint += " | ";
		toPrint += printQuestionsAndHintsStatus();
		toPrint += "\n";
		System.out.println(toPrint);
	}
	
	public static String printQuestionsAndHintsStatus()
	{
		String toPrint = "\n";
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
					if(ADVENT.found.get(object) != true)
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
		
		private Questions(boolean serious)
		{ this.serious = serious; }
	}
	
	public enum Hints 
	{
		NONE("", 0, -1, null, null),
		INSTRUCTIONS("Instructions", 5, -1, null, "Somewhere nearby is Colossal Cave, where others have found great fortunes in treasure and gold, though it is rumored that some who enter are never seen again. Magic is said to work in the cave. I will be your eyes and hands. Direct me with commands of 1 or 2 words. I should warn you that I only look at only the first five letters of each word, so you'll have to enter \"northeast\" as \"ne\" to distinguish it from \"north\" (Should you get stuck, type \"help\" for some general hints. For information on how to end your adventure, etc., type \"info\".)\n\n"),
/*0*/	BLAST("Blast", 10, -1, null, "It says, 'There is something strange about this place, such that one of the words I've always known now has a new effect.'"),
/*1*/	GRATE("Grate", 2, 4, "\n\nAre you trying to get into the cave?", "The grate is very solid and has a hardened steel lock. You cannot enter without a key, and there are no keys in sight. I would recommend looking elsewhere for the keys."),
/*2*/	BIRD("Bird", 2, 5, "\n\nAre you trying to catch the bird?", "Something seems to be frightening the bird just now and you cannot catch it no matter what you try. Perhaps you might try later."),
/*3*/	SNAKE("Snake", 2, 8, "\n\nAre you trying to deal somehow with the snake?", "You can't kill the snake, or drive it away, or avoid it, or anything like that. There is a way to get by, but you don't have the necessary resources right now."),
/*4*/	MAZE("Maze", 4, 75, "\n\nDo you need help getting out of the maze?", "You can make the passages look less alike by dropping things."),
/*5*/	DARK("Dark", 5, 25, "\n\nAre you trying to explore beyond the Plover Room?", "There is a way to explore that region without having to worry about falling into a pit."),
/*6*/	WITT("Witt", 3, 20, "\n\nDo you need help getting out of here?", "Don't go west."),
		WEST("West", 0, 10, null, "\n\nIf you prefer, simply type W rather than WEST.");
		
		public final String name;
		public final int cost;
		public final int threshold;
		public final String question;
		public final String hint;
		public int proc;
		public boolean given;
		
		private Hints(String name, int cost, int threshold, String question, String hint)
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
		ROAD, HILL, ENTER, UPSTREAM, DOWNSTREAM, FOREST, FORWARD, BACK, 
		VALLEY, STAIRS, OUT, BUILDING, GULLY, STREAM, ROCK, BED, CRAWL, 
		COBBLE, IN, SURFACE, NOWHERE, DARK, PASSAGE, LOW, CANYON, 
		AWKWARD, GIANT, VIEW, UP, DOWN, PIT, OUTDOORS, CRACK, STEPS, DOME,
		LEFT, RIGHT, HALL, JUMP, BARREN, OVER, ACROSS, EAST, WEST, NORTH, 
		SOUTH, NORTHEAST, SOUTHEAST, SOUTHWEST,NORTHWEST, 
		DEBRIS, HOLE, WALL, BROKEN, Y2, CLIMB, FLOOR, ROOM, 
		SLIT, SLAB, XYZZY, DEPRESSION, ENTRANCE, PLUGH, SECRET, CAVE,
		CROSS, BEDQUILT, PLOVER, ORIENTAL, CAVERN, SHELL, RESERVOIR,
		OFFICE, FORK;

		@Override
		public byte getType() { return 0; }
	}
	
	public enum GameObjects implements KnownWord
	{
		//TODO art string
		NOTHING, ALL, 	
		KEYS(true, Locations.BUILDING,			new String[] {"\n\t\tSet of Keys", "\n\tThere are some keys on the ground here."}), 
		LAMP(true, Locations.BUILDING,			new String[] {"\n\t\tBrass Lantern", "\n\tThere is a lamp shining nearby.", "\n\tThere is a shiny brass lamp nearby."}), 
		GRATE(Locations.OUTSIDE, 				new String[] {"\n\tThe grate is open.", "\n\tThe grate is locked."}), GRATE_(Locations.INSIDE, null), 
		CAGE(true, Locations.COBBLES,			new String[] {"\n\t\tWicker Cage", "\n\tThere is a small wicker cage discarded nearby."}), 
		ROD(true, Locations.DEBRIS,				new String[] {"\n\t\tBlack Rod", "\n\tA three foot black rod with a rusty star on an end lies nearby."}), 
		ROD2(true, Locations.THEVOID,			new String[] {"\n\t\tBlack Rod", "\n\tA three foot black rod with a rusty mark on an end lies nearby."}),
		TREADS(Locations.EASTMIST,				new String[] {"\n\tRough stone steps lead up the dome."}), 
		TREADS_(Locations.SMALLPIT,				new String[] {"\n\tRough stone steps lead down the pit."}), 
		BIRD(true, Locations.BIRD,				new String[] {"\n\t\tLittle Bird in Cage", "\n\tThere is a little bird in the cage.", "\n\tA cheerful little bird is sitting here singing."}), 
		DOOR(Locations.IMMENSE,					new String[] {"\n\tThe way north is barred by a massive, rusty, iron door.", "\n\tThe way north leads through a massive, rusty, iron door."}), 
		PILLOW(true, Locations.SOFT,			new String[] {"\n\t\tVelvet Pillow", "\n\tA small velvet pillow lies on the floor."}), 
		SNAKE(Locations.HALLOFMOUNTAINKING,		new String[] {"\n\tA huge green fierce snake bars the way!"}), 
		CRYSTAL(Locations.EASTFISSURE,			new String[] {"\n\tA crystal bridge now spans the fissure."}), CRYSTAL_(Locations.WESTFISSURE, null), 
		TABLET(Locations.DROOM,					new String[] {"\n\tA massive stone tablet imbedded in the wall reads:\n\t\"CONGRATULATIONS ON BRINGING LIGHT TO THE DARK-ROOM!\""}), 
		CLAM(true, Locations.SHELL,				new String[] {"\n\t\tGiant Clam >GRUNT!<", "\n\tThere is an enormous clam here with its shell tightly closed."}), 
		OYSTER(true, Locations.THEVOID,			new String[] {"\n\t\tGiant Oyster >GROAN!<", "\n\tThere is an enormous oyster here with its shell tightly closed."}), 
		MAG(true, Locations.ANTE,				new String[] {"\n\t\t\"Spelunker Today\"", "\n\tThere are a few recent issues of \"Spelunker Today\" magazine here."}), 
		DWARF, KNIFE, 
		FOOD(true, Locations.BUILDING,			new String[] {"\n\t\tTasty Food", "\n\tThere is tasty food here."}), 
		BOTTLE(true, Locations.BUILDING,		new String[] {"\n\t\tSmall Bottle", "\n\t\tBottle of Water", "\n\t\tBottle of Oil", "\n\tThere is an empty bottle here.", "\n\tThere is a bottle of water here.", "\n\tThere is a bottle of oil here."}), 
		WATER, OIL, 
		MIRROR(Locations.MIRROR, null), 
		MIRROR_, 
		PLANT(Locations.WESTPIT,				new String[] {"\n\tThere is a tiny little plant in the pit, murmuring \"Water, water, ...\"", "\n\tThere is a 12-foot-tall beanstalk stretching up out of the pit, bellowing \"Water!! Water!!\"", "\n\tThere is a gigantic beanstalk stretching all the way up to the hole.", ""}), 
		PLANT2(Locations.WEST2PIT,				new String[] {"", "\n\tThe top of a 12-foot-tall beanstalk is poking up out of the west pit.", "\n\tThere is a huge beanstalk growing out of the west pit up to the hole.", ""}), PLANT2_(Locations.EAST2PIT,	null), 
		STALACTITE(Locations.STALACTITE,		new String[] {}), 
		SHADOW(Locations.EASTWINDOW,			new String[] {"\n\tThe shadowy figure seems to be trying to attract your attention."}), SHADOW_(Locations.WESTWINDOW, null), 
		AXE(true, Locations.THEVOID,			new String[] {"\n\t\tDwarf's Axe", "\n\tThere is a little axe lying beside the bear.", "\n\tThere is a little axe here."}), 
		ART(Locations.ORIENTAL, null), 
		PIRATE, 
		DRAGON(Locations.SCAN1,					new String[] {"\n\tA huge green fierce dragon bars the way!", "\n\tThe body of a huge green dead dragon is lying off to one side."}), DRAGON_(Locations.SCAN3, null), 
		BRIDGE(Locations.SWSIDE,				new String[] {"\n\tA rickety wooden bridge extends across the chasm, vanishing into the mist. \n\tA sign posted on the bridge reads: \n\t\t\"Stop!  Pay Troll!\"", "\n\tThe wreckage of a bridge (and a dead bear) can be seen at the bottom of the chasm."}), BRIDGE_(Locations.NESIDE, null), 
		TROLL(Locations.SWSIDE,					new String[] {"\n\tA burly troll stands by the bridge and insists you throw him a treasure before you may cross."}), TROLL_(Locations.NESIDE, null), 
		TROLL2(Locations.THEVOID,				new String[] {"\n\tThe troll is nowhere to be seen."}), TROLL2_(Locations.THEVOID, null), 
		BEAR(Locations.BARR,					new String[] {"\n\tThere is a ferocious cave bear eyeing you from the far end of the room!", "\n\tThere is a gentle cave bear sitting placidly in one corner.", "\n\nYou are being followed by a very large, tame bear.", "", "\n\tThere is a contented-looking bear wandering about nearby."}), 
		MESSAGE(Locations.THEVOID,				new String[] {"\n\tThere is a message scrawled in the dust in a flowery script, reading:\n\t\t\"This is not the maze where the pirate leaves his treasure chest.\""}), 
		GEYSER(Locations.VIEW, null), 
		PONY(Locations.PONY,					new String[] {"\n\tThere is a massive vending machine here.  \nThe instructions on it read:\n\t\t\"Drop coins here to receive fresh batteries.\""}), 
		BATTERIES(Locations.THEVOID,			new String[] {"\n\tThere are fresh batteries here.", "\n\tSome worn-out batteries have been discarded nearby."}), 
		MOSS(Locations.SOFT, null), 
		
		GOLD(true, Locations.NUGGET,			new String[] {"\n\t\tLarge Gold Nugget", "\n\tThere is a large sparkling nugget of gold here!"}), 
		DIAMONDS(true, Locations.WESTFISSURE,	new String[] {"\n\t\tSeveral Diamonds", "\n\tThere are diamonds here!"}), 
		SILVER(true, Locations.NS,				new String[] {"\n\t\tBars of Silver", "\n\tThere are bars of silver here!"}), 
		JEWELS(true, Locations.SOUTH,			new String[] {"\n\t\tPrecious Jewelry", "\n\tThere is precious jewelry here!"}), 
		COINS(true, Locations.WEST,				new String[] {"\n\t\tRare coins", "\n\tThere are many coins here!"}), 
		CHEST(true, Locations.THEVOID,			new String[] {"\n\t\tTreasure Chest", "\n\tThe pirate's treasure chest is here!"}), 
		EGGS(true, Locations.GIANT,				new String[] {"\n\t\tGolden Eggs", "\n\tThere is a large nest here, full of golden eggs!"}), 
		TRIDENT(true, Locations.FALLS,			new String[] {"\n\t\tJeweled Trident", "\n\tThere is a jewel-encrusted trident here!"}), 
		VASE(true, Locations.ORIENTAL,			new String[] {"\n\t\tMing Vase", "\n\tThe vase is now resting, delicately, on a velvet pillow.", "\n\tThere is a delicate, precious, Ming vase here!", "\n\tThe floor is littered with worthless shards of pottery."}), 
		EMERALD(true, Locations.PROOM,			new String[] {"\n\t\tEgg-Sized Emerald", "\n\tThere is an emerald here the size of a plover's egg!"}), 
		PYRAMID(true, Locations.DROOM,			new String[] {"\n\t\tPlatinum Pyramid", "\n\tThere is a platinum pyramid here, 8 inches on a side!"}), 
		PEARL(true, Locations.THEVOID,			new String[] {"\n\t\tGlistening Pearl", "\n\tOff to one side lies a glistening pearl!"}), 
		RUG(true, Locations.SCAN1,				new String[] {"\n\t\tPersian Rug", "\n\tThe dragon is sprawled out on a persian rug!", "\n\tThere is a persian rug spread out on the floor!"}), RUG_(true, Locations.SCAN3,	null), 
		SPICES(true, Locations.CHAMBER,			new String[] {"\n\t\tRare Spices", "\n\tThere are rare spices here!"}), 
		CHAIN(true, Locations.BARR,				new String[] {"\n\t\tGolden Chain", "\n\tThe bear is locked to the wall with a golden chain!", "\n\tThere is a golden chain lying in a heap on the floor!", "\n\tThere is a golden chain locked to the wall!"});

		public final boolean mobile;
		public Locations location;
		public String[] descriptions;
		
		private GameObjects()
		{ this.mobile = false; this.location = Locations.THEVOID; this.descriptions = null; }
		
		private GameObjects(Locations location, String[] descriptions)
		{ this.mobile = false; this.location = location; this.descriptions = descriptions; }
		
		private GameObjects(boolean mobile, Locations location, String[] descriptions)
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
		{
			for(int i = 0; i < GameObjects.values().length; i++)
			{ GameObjects.values()[i].location = locations[i]; }
		}

		public String getItemDescription(Locations location, GameObjects object)
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
					
				case TREADS: if(!ADVENT.goldInInventory){ output = descriptions[0]; } break;	
					
				case TREADS_: if(!ADVENT.goldInInventory){ output = descriptions[0]; } break;	
					
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
	
	public enum ActionWords implements KnownWord
	{
		NOTHING, LOOK, ABSTAIN, TAKE, DROP, OPEN, CLOSE, ON, OFF, WAVE, CALM, 
		GO, RELAX, POUR, EAT, DRINK, RUB, TOSS, WAKE, FEED, 
		FILL, BREAK, BLAST, KILL, SAY, READ, FEEFIE, BRIEF, VERBOSE,
		FIND, INVENTORY, SCORE, QUIT, SAVE, LOAD;

		@Override
		public byte getType() { return 2; }
	}
	
	public enum MessageWords implements KnownWord
	{
		MAGIC("Good try, but that is an old worn-out magic word."), 
		HELP("I know of places, actions, and things. "
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
			+ "not guarantee entering the next from the south. \nGood luck!"), 
		TREE("The trees of the forest are large hardwood oak and maple, with an occasional "
			+ "grove of pine or spruce. "
			+ "There is quite a bit of under-growth, largely birch and ash saplings plus nondescript"
			+ " bushes of various sorts. "
			+ "This time of year visibility is quite restricted by all the leaves, but travel is quite"
			+ " easy if you detour around the spruce and berry bushes."), 
		DIG("Digging without a shovel is quite impractical. Even with a shovel progress is unlikely."), 
		LOST("I'm as confused as you are."), 
		MIST("Mist is a white vapor, usually water, seen from time to time in caverns. It can be found anywhere but is frequently a sign of a deep pit leading down to water."), 
		CUSS("Watch it!"), 
		STOP("I don't know the word \"stop\". Use \"quit\" if you want to give up."),
		INFO("If you want to end your adventure early, say \"quit\". "
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
			+ "description of a place."), 
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
}
