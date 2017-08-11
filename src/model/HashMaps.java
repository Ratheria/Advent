/**
 *	@author Ariana Fairbanks
 */

package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class HashMaps 
{
	private HashMap<String, Movement> movement = new HashMap();
	private HashMap<String, GameObjects> objects =new HashMap();
	private HashMap<String, ActionWords> actions = new HashMap();
	private HashMap<String, MessageWords> mwords = new HashMap();
	private HashMap<Location, Integer> visits = new HashMap();
	private HashMap<GameObjects, Boolean> found = new HashMap();
	private HashMap<Location, String> longDescription = new HashMap();
	private HashMap<Location, String> shortDescription = new HashMap();
	private HashMap<Location, String> title = new HashMap();
	private HashMap<GameObjects, Location> objectLocation = new HashMap();
	
	public HashMaps()
	{
		setUpHashMaps();
	}
	
	private void setUpHashMaps()
	{
		movement.put("road", Movement.ROAD);
		movement.put("hill", Movement.HILL);
		movement.put("enter", Movement.ENTER);
		movement.put("downs", Movement.DOWNSTREAM);
		movement.put("upstr", Movement.UPSTREAM);
		movement.put("fores", Movement.FOREST);
		movement.put("forwa", Movement.FORWARD); 
		movement.put("onwar", Movement.FORWARD);
		movement.put("back", Movement.BACK);
		movement.put("retre", Movement.BACK); 
		movement.put("retur", Movement.BACK);
		movement.put("valle", Movement.VALLEY);
		movement.put("stair", Movement.STAIRS);
		movement.put("out", Movement.OUT); 
		movement.put("outsi", Movement.OUT); 
		movement.put("exit", Movement.OUT);	
		movement.put("leave", Movement.OUT);
		movement.put("build", Movement.BUILDING); 
		movement.put("house", Movement.BUILDING);
		movement.put("gully", Movement.GULLY);
		movement.put("strea", Movement.STREAM);
		movement.put("rock", Movement.ROCK);
		movement.put("bed", Movement.BED);
		movement.put("crawl", Movement.CRAWL);
		movement.put("cobbl", Movement.COBBLE);
		movement.put("in", Movement.IN); 
		movement.put("insid", Movement.IN); 
		movement.put("inwar", Movement.IN);
		movement.put("surfa", Movement.SURFACE);
		movement.put("nowhe", Movement.NOWHERE);
		movement.put("dark", Movement.DARK);
		movement.put("passa", Movement.PASSAGE); 
		movement.put("tunne", Movement.PASSAGE);
		movement.put("canyo", Movement.CANYON);
		movement.put("akwar", Movement.AWKWARD);
		movement.put("giant", Movement.GIANT);
		movement.put("view", Movement.VIEW);
		movement.put("up", Movement.UP); 
		movement.put("u", Movement.UP); 
		movement.put("above", Movement.UP); 
		movement.put("acend", Movement.UP); 
		movement.put("upwar", Movement.UP);
		movement.put("down", Movement.DOWN); 
		movement.put("d", Movement.DOWN); 
		movement.put("decen", Movement.DOWN);
		movement.put("downw", Movement.DOWN);
		movement.put("pit", Movement.PIT);
		movement.put("outdo", Movement.OUTDOORS);
		movement.put("crack", Movement.CRACK);
		movement.put("steps", Movement.STEPS);
		movement.put("dome", Movement.DOME);
		movement.put("left", Movement.LEFT);
		movement.put("right", Movement.RIGHT);
		movement.put("hall", Movement.HALL);
		movement.put("jump", Movement.JUMP);
		movement.put("barren", Movement.BARREN);
		movement.put("over", Movement.OVER);
		movement.put("across", Movement.ACROSS);
		movement.put("e", Movement.EAST);
		movement.put("east", Movement.EAST);
		movement.put("w", Movement.WEST);
		movement.put("west", Movement.WEST);
		movement.put("n", Movement.NORTH);
		movement.put("north", Movement.NORTH);
		movement.put("s", Movement.SOUTH);
		movement.put("south", Movement.SOUTH);
		movement.put("ne", Movement.NORTHEAST);
		movement.put("se", Movement.SOUTHEAST);
		movement.put("sw", Movement.SOUTHWEST);
		movement.put("nw", Movement.NORTHWEST);
		movement.put("debri", Movement.DEBRIS);
		movement.put("hole", Movement.HOLE);
		movement.put("wall", Movement.WALL);
		movement.put("broke", Movement.BROKEN);
		movement.put("y2", Movement.Y2);
		movement.put("climb", Movement.CLIMB);
		movement.put("look", Movement.LOOK); 
		movement.put("exami", Movement.LOOK);
		movement.put("descr", Movement.LOOK);
		movement.put("l", Movement.LOOK);
		movement.put("touch", Movement.LOOK);
		movement.put("floor", Movement.FLOOR);
		movement.put("room", Movement.ROOM);
		movement.put("slit", Movement.SLIT);
		movement.put("slab", Movement.SLAB); 
		movement.put("slabr", Movement.SLAB);
		movement.put("xyzzy", Movement.XYZZY);
		movement.put("depre", Movement.DEPRESSION);
		movement.put("entra", Movement.ENTRANCE);
		movement.put("plugh", Movement.PLUGH);
		movement.put("secre", Movement.SECRET);
		movement.put("cave", Movement.CAVE);
		movement.put("cross", Movement.CROSS);
		movement.put("bedqu", Movement.BEDQUILT);
		movement.put("plove", Movement.PLOVER);
		movement.put("orien", Movement.ORIENTAL);
		movement.put("caver", Movement.CAVERN);
		movement.put("shell", Movement.SHELL);
		movement.put("main", Movement.OFFICE); 
		movement.put("offic", Movement.OFFICE);
		movement.put("reser", Movement.RESERVOIR);
		movement.put("fork", Movement.FORK);
		
		objects.put("key", GameObjects.KEYS);
		objects.put("keys", GameObjects.KEYS);
		objects.put("lamp", GameObjects.LAMP);
		objects.put("lante", GameObjects.LAMP);
		objects.put("headl", GameObjects.LAMP);
		objects.put("grate", GameObjects.GRATE);
		objects.put("cage", GameObjects.CAGE);
		objects.put("rod", GameObjects.ROD);
		objects.put("bird", GameObjects.BIRD);
		objects.put("door", GameObjects.DOOR);
		objects.put("pillo", GameObjects.PILLOW);
		objects.put("snake", GameObjects.SNAKE);
		objects.put("fissu", GameObjects.CRYSTAL);
		objects.put("table", GameObjects.TABLET);
		objects.put("clam", GameObjects.CLAM);
		objects.put("oyste",GameObjects. OYSTER);
		objects.put("magaz", GameObjects.MAG);
		objects.put("issue", GameObjects.MAG);
		objects.put("spelu", GameObjects.MAG);
		objects.put("\"spel",GameObjects. MAG);
		objects.put("dwarf", GameObjects.DWARF);
		objects.put("dwarv", GameObjects.DWARF);
		objects.put("knife", GameObjects.KNIFE);
		objects.put("knive", GameObjects.KNIFE);
		objects.put("food", GameObjects.FOOD);
		objects.put("ratio", GameObjects.FOOD);
		objects.put("bottl", GameObjects.BOTTLE);
		objects.put("jar", GameObjects.BOTTLE);
		objects.put("water", GameObjects.WATER);
		objects.put("h2o", GameObjects.WATER);
		objects.put("oil", GameObjects.OIL);
		objects.put("mirro", GameObjects.MIRROR);
		objects.put("plant", GameObjects.PLANT);
		objects.put("beans", GameObjects.PLANT);
		objects.put("stala", GameObjects.STALACTITE);
		objects.put("shado", GameObjects.SHADOW);
		objects.put("figur", GameObjects.SHADOW);
		objects.put("axe", GameObjects.AXE);
		objects.put("drawi", GameObjects.ART);
		objects.put("pirat", GameObjects.PIRATE);
		objects.put("drago", GameObjects.DRAGON);
		objects.put("chasm", GameObjects.BRIDGE);
		objects.put("troll", GameObjects.TROLL);
		objects.put("bear", GameObjects.BEAR);
		objects.put("messa", GameObjects.MESSAGE);
		objects.put("volca", GameObjects.GEYSER);
		objects.put("geyse", GameObjects.GEYSER);
		objects.put("vendi", GameObjects.PONY);
		objects.put("machi", GameObjects.PONY);
		objects.put("batte", GameObjects.BATTERIES);
		objects.put("moss", GameObjects.MOSS);
		objects.put("carpe", GameObjects.MOSS);
		objects.put("gold", GameObjects.GOLD);
		objects.put("nugge", GameObjects.GOLD);
		objects.put("diamo", GameObjects.DIAMONDS);
		objects.put("silve", GameObjects.SILVER);
		objects.put("bars", GameObjects.SILVER);
		objects.put("jewel", GameObjects.JEWELS);
		objects.put("coins", GameObjects.COINS);
		objects.put("chest", GameObjects.CHEST);
		objects.put("box", GameObjects.CHEST);
		objects.put("treas", GameObjects.CHEST);
		objects.put("eggs", GameObjects.EGGS);
		objects.put("egg", GameObjects.EGGS);
		objects.put("nest", GameObjects.EGGS);
		objects.put("tride", GameObjects.TRIDENT);
		objects.put("ming", GameObjects.VASE);
		objects.put("vase", GameObjects.VASE);
		objects.put("shard", GameObjects.VASE);
		objects.put("potte", GameObjects.VASE);
		objects.put("emera", GameObjects.EMERALD);
		objects.put("plati", GameObjects.PYRAMID);
		objects.put("pyram", GameObjects.PYRAMID);
		
		actions.put("look", ActionWords.LOOK);
		actions.put("descr", ActionWords.LOOK);
		actions.put("take", ActionWords.TAKE);
		actions.put("carry", ActionWords.TAKE);
		actions.put("keep", ActionWords.TAKE);
		actions.put("catch", ActionWords.TAKE);
		actions.put("captu", ActionWords.TAKE);
		actions.put("steal", ActionWords.TAKE);
		actions.put("get", ActionWords.TAKE);
		actions.put("tote", ActionWords.TAKE);
		actions.put("drop", ActionWords.DROP);
		actions.put("relea", ActionWords.DROP);
		actions.put("free", ActionWords.DROP);
		actions.put("disca", ActionWords.DROP);
		actions.put("dump", ActionWords.DROP);
		actions.put("open", ActionWords.OPEN);
		actions.put("unloc", ActionWords.OPEN);
		actions.put("close", ActionWords.CLOSE);
		actions.put("lock", ActionWords.CLOSE);
		actions.put("light", ActionWords.ON);
		actions.put("on", ActionWords.ON);
		actions.put("extin", ActionWords.OFF);
		actions.put("off", ActionWords.OFF);
		actions.put("wave", ActionWords.WAVE);
		actions.put("shake", ActionWords.WAVE);
		actions.put("swing", ActionWords.WAVE);
		actions.put("calm", ActionWords.CALM);
		actions.put("placa", ActionWords.CALM);
		actions.put("tame", ActionWords.CALM);
		actions.put("walk", ActionWords.GO);
		actions.put("run", ActionWords.GO);
		actions.put("trave", ActionWords.GO);
		actions.put("go", ActionWords.GO);
		actions.put("proce", ActionWords.GO);
		actions.put("explo", ActionWords.GO);
		actions.put("goto", ActionWords.GO);
		actions.put("follo", ActionWords.GO);
		actions.put("turn", ActionWords.GO);
		actions.put("nothi", ActionWords.RELAX);
		actions.put("pour", ActionWords.POUR);
		actions.put("eat", ActionWords.EAT);
		actions.put("devou", ActionWords.EAT);
		actions.put("drink", ActionWords.DRINK);
		actions.put("rub", ActionWords.RUB);
		actions.put("throw", ActionWords.TOSS);
		actions.put("toss", ActionWords.TOSS);
		actions.put("wake", ActionWords.WAKE);
		actions.put("distu", ActionWords.WAKE);
		actions.put("feed", ActionWords.FEED);
		actions.put("fill", ActionWords.FILL);
		actions.put("break", ActionWords.BREAK);
		actions.put("smash", ActionWords.BREAK);
		actions.put("shatt", ActionWords.BREAK);
		actions.put("blast", ActionWords.BLAST);
		actions.put("deton", ActionWords.BLAST);
		actions.put("ignit", ActionWords.BLAST);
		actions.put("blowu", ActionWords.BLAST);
		actions.put("attac", ActionWords.KILL);
		actions.put("kill", ActionWords.KILL);
		actions.put("fight", ActionWords.KILL);
		actions.put("hit", ActionWords.KILL);
		actions.put("strik", ActionWords.KILL);
		actions.put("slay", ActionWords.KILL);
		actions.put("say", ActionWords.SAY);
		actions.put("chant", ActionWords.SAY);
		actions.put("sing", ActionWords.SAY);
		actions.put("utter", ActionWords.SAY);
		actions.put("mumbl", ActionWords.SAY);
		actions.put("read", ActionWords.READ);
		actions.put("perus", ActionWords.READ);
		actions.put("fee", ActionWords.FEEFIE);
		actions.put("fie", ActionWords.FEEFIE);
		actions.put("foe", ActionWords.FEEFIE);
		actions.put("foo", ActionWords.FEEFIE);
		actions.put("fum", ActionWords.FEEFIE);
		actions.put("brief", ActionWords.BRIEF);
		actions.put("verbo", ActionWords.VERBOSE);
		actions.put("find", ActionWords.FIND);
		actions.put("where", ActionWords.FIND);
		actions.put("inven", ActionWords.INVENTORY);
		actions.put("score", ActionWords.SCORE);
		actions.put("quit", ActionWords.QUIT);
		
		mwords.put("abra", MessageWords.MAGIC);
		mwords.put("abrac", MessageWords.MAGIC);
		mwords.put("opens", MessageWords.MAGIC);
		mwords.put("sesam", MessageWords.MAGIC);
		mwords.put("shaza", MessageWords.MAGIC);
		mwords.put("hocus", MessageWords.MAGIC);
		mwords.put("pocus", MessageWords.MAGIC);
		mwords.put("help", MessageWords.HELP);
		mwords.put("?", MessageWords.HELP);
		mwords.put("tree", MessageWords.TREE);
		mwords.put("trees", MessageWords.TREE);
		mwords.put("dig", MessageWords.DIG);
		mwords.put("excav", MessageWords.DIG);
		mwords.put("lost", MessageWords.LOST);
		mwords.put("mist", MessageWords.MIST);
		mwords.put("fuck", MessageWords.CUSS);
		mwords.put("shit", MessageWords.CUSS);
		mwords.put("damn", MessageWords.CUSS);
		mwords.put("dick", MessageWords.CUSS);
		mwords.put("info", MessageWords.INFO);
		mwords.put("infor", MessageWords.INFO);
		mwords.put("swim", MessageWords.SWIM);
		mwords.put("denni", MessageWords.DENNIS);
		
		visits.put(Location.ROAD, 0);
		visits.put(Location.HILL, 0);
		visits.put(Location.BUILDING, 0);
		visits.put(Location.VALLEY, 0);
		visits.put(Location.FOREST, 0);
		visits.put(Location.WOODS, 0);
		visits.put(Location.SLIT, 0);
		visits.put(Location.OUTSIDE, 0);
		visits.put(Location.INSIDE, 0);
		visits.put(Location.COBBLES, 0);
		visits.put(Location.DEBRIS, 0);
		visits.put(Location.AWKWARD, 0);
		visits.put(Location.BIRD, 0);
		visits.put(Location.SMALLPIT, 0);
		visits.put(Location.EASTMIST, 0);
		visits.put(Location.NUGGET, 0);
		visits.put(Location.EASTFISSURE, 0);
		visits.put(Location.WESTFISSURE, 0);
		visits.put(Location.WESTMIST, 0);
		visits.put(Location.BRINK, 0);
		visits.put(Location.EASTLONG, 0);
		visits.put(Location.WESTLONG, 0);
		visits.put(Location.PONY, 0);
		visits.put(Location.CROSS, 0);
		visits.put(Location.HALLOFMOUNTAINKING, 0);
		visits.put(Location.WEST, 0);
		visits.put(Location.SOUTH, 0);
		visits.put(Location.NS, 0);
		visits.put(Location.Y2, 0);
		visits.put(Location.JUMBLE, 0);
		visits.put(Location.EASTWINDOW, 0);
		visits.put(Location.DIRTY, 0);
		visits.put(Location.CLEAN, 0);
		visits.put(Location.WET, 0);
		visits.put(Location.DUSTY, 0);
		visits.put(Location.COMPLEX, 0);
		visits.put(Location.SHELL, 0);
		visits.put(Location.ARCH, 0);
		visits.put(Location.RAGGED, 0);
		visits.put(Location.CULDESAC, 0);
		visits.put(Location.ANTE, 0);
		visits.put(Location.WITT, 0);
		visits.put(Location.BEDQUILT, 0);
		visits.put(Location.CHEESE, 0);
		visits.put(Location.SOFT, 0);
		visits.put(Location.EAST2PIT, 0);
		visits.put(Location.WEST2PIT, 0);
		visits.put(Location.EASTPIT, 0);
		visits.put(Location.WESTPIT, 0);
		visits.put(Location.NARROW, 0);
		visits.put(Location.GIANT, 0);
		visits.put(Location.BLOCK, 0);
		visits.put(Location.IMMENSE, 0);
		visits.put(Location.FALLS, 0);
		visits.put(Location.STEEP, 0);
		visits.put(Location.ABOVEP, 0);
		visits.put(Location.SJUNC, 0);
		visits.put(Location.TIGHT, 0);
		visits.put(Location.LOW, 0);
		visits.put(Location.CRAWL, 0);
		visits.put(Location.WESTWINDOW, 0);
		visits.put(Location.ORIENTAL, 0);
		visits.put(Location.MISTY, 0);
		visits.put(Location.ALCOVE, 0);
		visits.put(Location.PROOM, 0);
		visits.put(Location.DROOM, 0);
		visits.put(Location.SLAB, 0);
		visits.put(Location.ABOVER, 0);
		visits.put(Location.MIRROR, 0);
		visits.put(Location.RESER, 0);
		visits.put(Location.SCAN1, 0);
		visits.put(Location.SCAN2, 0);
		visits.put(Location.SCAN3, 0);
		visits.put(Location.SECRET, 0);
		visits.put(Location.WIDE, 0);
		visits.put(Location.STALACTITE, 0);
		visits.put(Location.TALL, 0);
		visits.put(Location.BOULDERS, 0);
		visits.put(Location.SCORR, 0);
		visits.put(Location.SWSIDE, 0);
		visits.put(Location.NESIDE, 0);
		visits.put(Location.CORR, 0);
		visits.put(Location.FORK, 0);
		visits.put(Location.WARM, 0);
		visits.put(Location.VIEW, 0);
		visits.put(Location.CHAMBER, 0);
		visits.put(Location.LIME, 0);
		visits.put(Location.FBARR, 0);
		visits.put(Location.BARR, 0);
		visits.put(Location.NEEND, 0);
		visits.put(Location.SWEND, 0);
		
		found.put(GameObjects.GOLD, false);
		found.put(GameObjects.DIAMONDS, false);
		found.put(GameObjects.SILVER, false);
		found.put(GameObjects.JEWELS, false);
		found.put(GameObjects.COINS, false);
		found.put(GameObjects.CHEST, false);
		found.put(GameObjects.EGGS, false);
		found.put(GameObjects.TRIDENT, false);
		found.put(GameObjects.VASE, false);
		found.put(GameObjects.EMERALD, false);
		found.put(GameObjects.PYRAMID, false);
		found.put(GameObjects.PEARL, false);
		found.put(GameObjects.RUG, false);
		found.put(GameObjects.SPICES, false);
		found.put(GameObjects.CHAIN, false);
		
		longDescription.put(Location.ROAD, "You are standing at the end of a road before a small brick building. Around you is a forest. A small stream flows out of the building and down a gully.");
		longDescription.put(Location.HILL, "You have walked up a hill, still in the forest. The road slopes back down the other side of the hill. There is a building in the distance.");
		longDescription.put(Location.BUILDING, "You are inside a building, a well house for a large spring.");
		longDescription.put(Location.VALLEY, "You are in a valley in the forest beside a stream tumbling along a rocky bed.");
		longDescription.put(Location.FOREST, "You are in open forest, with a deep valley to one side.");
		longDescription.put(Location.WOODS, "You are in open forest near both a valley and a road.");
		longDescription.put(Location.SLIT, "At your feet all the water of the stream splashes into a 2-inch slit in the rock. Downstream the streambed is bare rock.");
		longDescription.put(Location.OUTSIDE, "You are in a 20-foot depression floored with bare dirt. Set into the dirt is a strong steel grate mounted in concrete. A dry streambed leads into the depression.");
		longDescription.put(Location.INSIDE, "You are in a small chamber beneath a 3x3 steel grate to the surface. A low crawl over cobbles leads inward to the West.");
		longDescription.put(Location.COBBLES, "You are crawling over cobbles in a low passage. There is a dim light at the east end of the pasage.");
		longDescription.put(Location.DEBRIS, "You are in a debris room filled with stuff washed in from the surface. A low wide passage with cobbles becomes plugged with mud and debris here, but an awkward canyon leads upward and west. \nA note on the wall says:\n\tMagic Word \"XYZZY\"");
		longDescription.put(Location.BIRD, "You are in a splendid chamber thirty feet high. The walls are frozen rivers of orange stone. An awkward canyon and a good passage exit from east and west sides of the chamber.");
		longDescription.put(Location.SMALLPIT, "At your feet is a small pit breathing traces of white mist. An east passage ends here except for a small crack leading on.");
		longDescription.put(Location.EASTMIST, "You are at one end of a vast hall stretching forward out of sight to the west. There are openings to either side. Nearby, a wide stone staircase leads downward. The hall is filled with wisps of white mist swaying to and fro almost as if alive. A cold wind blows up the staircase. There is a passage at the top of a dome behind you.");
		longDescription.put(Location.NUGGET, "This is a low room with a crude note on the wall. The note says, \n\t\"You won't get it up the steps\".");
		longDescription.put(Location.EASTFISSURE, "You are on the east bank of a fissure slicing clear across the hall. The mist is quite thick here, and the fissure is too wide to jump.");
		longDescription.put(Location.WESTMIST, "You are at the west end of Hall of Mists. A low wide crawl continues west and another goes north. To the south is a little passage 6 feet off the floor.");
		longDescription.put(Location.BRINK, "You are on the brink of a thirty-foot pit with a massive orange column down one wall. You could climb down here but you could not get back up. The maze continues at this level.");
		longDescription.put(Location.EASTLONG, "You are at the east end of a very long hall apparently without side chambers. To the east a low wide crawl slants up. To the north a round two foot hole slants down.");
		longDescription.put(Location.WESTLONG, "You are at the west end of a very long featureless hall. The hall joins up with a narrow north/south passage.");
		longDescription.put(Location.HALLOFMOUNTAINKING, "You are in the Hall of the Mountain King, with passages off in all directions.");
		longDescription.put(Location.WEST, "You are in the west side chamber of the Hall of the Mountain King. A passage continues west and up here.");
		longDescription.put(Location.NS, "You are in a low N/S passage at a hole in the floor. The hole goes down to an E/W passage.");
		longDescription.put(Location.Y2, "You are in a large room, with a passage to the south, a passage to the west, and a wall of broken rock to the east. There is a large \"Y2\" on a rock in the room's center.");
		longDescription.put(Location.EASTWINDOW, "You're at a low window overlooking a huge pit, which extends up out of sight.	A floor is indistinctly visible over 50 feet below. Traces of white mist cover the floor of the pit, becoming thicker to the right. Marks in the dust around the window would seem to indicate that someone has been here recently. Directly across the pit from you and 25 feet away there is a similar window looking into a lighted room. A shadowy figure can be seen there peering back at you.");
		longDescription.put(Location.DIRTY, "You are in a dirty broken passage. To the east is a crawl. To the west is a large passage. Above you is a hole to another passage.");
		longDescription.put(Location.CLEAN, "You are on the brink of a small clean climbable pit. A crawl leads west.");
		longDescription.put(Location.WET, "You are in the bottom of a small pit with a little stream, which enters and exits through tiny slits.");
		longDescription.put(Location.DUSTY, "You are in a large room full of dusty rocks. There is a big hole in the floor. There are cracks everywhere, and a passage leading east.");
		longDescription.put(Location.COMPLEX, "You are at a complex junction. A low hands and knees passage from the north joins a higher crawl from the east to make a walking passage going west. There is also a large room above. The air is damp here.");
		longDescription.put(Location.SHELL, "You are in a large room carved out of sedimentary rock. The floor and walls are littered with bits of shells embedded in the stone. A shallow passage proceeds downward, and a somewhat steeper one leads up. A low hands-and-knees passage enters from the south.");
		longDescription.put(Location.ARCH, "You are in an arched hall. A coral passage once continued up and east from here, but is now blocked by debris. The air smells of sea water.");
		longDescription.put(Location.ANTE, "You are in an anteroom leading to a large passage to the east. Small passages go west and up. The remnants of recent digging are evident.\n\nA sign in midair here says \n\t\"CAVE UNDER CONSTRUCTION BEYOND THIS POINT.\n\tPROCEED AT OWN RISK\n\t[WITT CONSTRUCTION COMPANY]\"");
		longDescription.put(Location.WITT, "You are at Witt's End. Passages lead off in \"all\" directions.");
		longDescription.put(Location.BEDQUILT, "You are in Bedquilt, a long E/W passage with holes everywhere. \nTo explore at random select north, south, up or down.");
		longDescription.put(Location.CHEESE, "You are in a room whose walls resemble swiss cheese. \nObvious passages go west, east, ne, and nw. Part of the room is occupied by a large bedrock block.");
		longDescription.put(Location.SOFT, "You are in the Soft Room. The walls are covered with heavy curtains, the floor with a thick pile carpet. Moss covers the ceiling.");
		longDescription.put(Location.EAST2PIT, "You are at the east end of the twopit room. The floor here is littered with thin rock slabs, which make it easy to descend the pits. There is a path here bypassing the pits to connect passages from east and west. There are holes all over, but the only big one is on the wall directly over the west pit where you can't get at it.");
		longDescription.put(Location.WEST2PIT, "You are at the west end of the twopit room. There is a large hole in the wall above the pit at this end of the room.");
		longDescription.put(Location.EASTPIT, "You are that the bottom of the eastern pit in the twopit room. There is a small pool of oil in one corner of the pit.");
		longDescription.put(Location.WESTPIT, "You are at the bottom of the western pit in the twopit room. There is a large hole in the wall about 25 feet above you.");
		longDescription.put(Location.NARROW, "You are in a long, narrow corridor stretching out of sight to the west. At the eastern end is a hole through which you can see a profusion of leaves.");
		longDescription.put(Location.GIANT, "You are in the Giant Room. The ceiling here is too high up for your lamp to show it. Cavernous passages lead east, north, and south. \n\nOn the west wall is scrawled the inscription, \n\t\"FEE FIE FOE FOO\"[sic].");
		longDescription.put(Location.FALLS, "You are in a magnificet cavern with a rushing stream, which cascades over a sparkling waterfall into a roaring whirlpool that disappears through a hole in the floor.\nPassages exit to the south and west.");
		longDescription.put(Location.STEEP, "You are at the top of a steep incline above a large room. You could climb down here, but you would not be able to climb up. There is a passage leading back to the north.");
		longDescription.put(Location.SJUNC, "You are in a secret canyon at a junction of three canyons, bearing north, south, and SE. The north one is as tall as the other two combined.");
		longDescription.put(Location.STALACTITE, "A large stalactite extends from the roof and almost reaches the floor below. You could climb down, but you would be unable to reach it to climb back up.");
		longDescription.put(Location.WESTWINDOW, "You're at a low window overlooking a huge pit, which extends up out of sight. A floor is indistinctly visible over 50 feet below. Traces of white mist cover the floor of the pit, becoming thicker to the left. Marks in the dust around the window would seem to indicate that someone has been here recently. Directly across the pit from you and 25 feet away there is a similar window looking into a lighted room. A shadowy figure can be seen there peering back at you.");
		longDescription.put(Location.ORIENTAL, "This is the Oriental Room. Ancient oriental cave drawings cover the walls. A gently sloping passage leads upward to the north, another passage leads se, and a hands and knees crawl leads west.");
		longDescription.put(Location.MISTY, "You are following a wide path around the outer edge of a large cavern. Far below, through a heavy white mist, strange splashing noises can be heard. The mist rises up through a fissure in the ceiling. The path exits to the south and west.");
		longDescription.put(Location.ALCOVE, "You are in an alcove. A small NW path seems to widen after a short distance. An extremely tight tunnel leads east. It looks like a very tight squeeze. An eerie light can be seen at the other end.");
		longDescription.put(Location.PROOM, "You're in a small chamber lit by an eerie green light. An extremely narrow tunnel exits to the west. A dark corridor leads NE.");
		longDescription.put(Location.DROOM, "You're in the Dark-Room. A corridor leading south is the only exit.");
		longDescription.put(Location.SLAB, "You are in a large low circular chamber whose floor is an immense slab fallen from the ceiling. (Slab Room). There once were large passages to the east and west, but they are now filled with boulders. Low small passages go north and south, and the south one quickly bends west around the boulders.");
		longDescription.put(Location.MIRROR, "You are in a north/south canyon about 25 feet across. The floor is covered by white mist seeping in from the north. The walls extend upward for well over 100 feet. Suspended from some unseen point far above you, an enormous two-sided mirror is hanging parallel to and midway between the canyon walls. (The mirror is obviously provided for the use of the dwarves, who as you know are extremely vain.) A small window can be seen in either wall, some fifty feet up.");
		longDescription.put(Location.RESER, "You are at the edge of a large underground reservoir. An opaque cloud of white mist fills the room and rises rapidly upward. The lake is fed by a stream, which tumbles out of a hole in the wall about 10 feet overhead and splashes noisily into the water somewhere within the mist. The only passage goes back toward the south.");
		longDescription.put(Location.SECRET, "You are in a secret canyon, which here runs E/W. It crosses over an very tight canyon 15 feet below. If you go down you may not be able to get back up.");
		longDescription.put(Location.TALL, "You are in a tall E/W canyon. A low tight crawl goes 3 feet north and seems to open up.");
		longDescription.put(Location.SCORR, "You are in a long winding corridor sloping out of sight in both directions.");
		longDescription.put(Location.SWSIDE, "You are on one side of a large, deep chasm. A heavy white mist rising up from below obscures all view of the far side. A SW path leads away from the chasm into a winding corridor.");
		longDescription.put(Location.NESIDE, "You are on the far side of the chasm. A NE path leads away from the chasm on this side.");
		longDescription.put(Location.CORR, "You're in a long east/west corridor. A faint rumbling noise can be heard in the distance.");
		longDescription.put(Location.FORK, "The path forks here. The left fork leads northeast. A dull rumbling seems to get louder in that direction. The right fork leads southeast down a gentle slope. The main corridor enters from the west.");
		longDescription.put(Location.WARM, "The walls are quite warm here. From the north can be heard a steady roar, so loud that the entire cave seems to be trembling. Another passage leads south, and a low crawl goes east.");
		longDescription.put(Location.VIEW, "You are on the edge of a breath-taking view. Far below you is an active volcano, from which great gouts of molten lava come surging out, cascading back down into the depths. The glowing rock fills the farthest reaches of the cavern with a blood-red glare, giving every-thing an eerie, macabre appearance. The air is filled with flickering sparks of ash and a heavy smell of brimstone. The walls are hot to the touch, and the thundering of the volcano drowns out all other sounds. Embedded in the jagged roof far overhead are myriad twisted formations, composed of pure white alabaster, which scatter the murky light into sinister apparitions upon the walls. To one side is a deep gorge, filled with a bizarre chaos of tortured rock that seems to have been crafted by the Devil himself. An immense river of fire crashes out from the depths of the volcano, burns its way through the gorge, and plummets into a bottomless pit far off to your left. To the right, am immense geyser of blistering steam erupts continuously from a barren island in the center of a sulfurous lake, which bubbles ominously. The far right wall is aflame with an incandescence of its own, which lends an additional infernal splendor to the already hellish scene. \nA dark, foreboding passage exits to the south.");
		longDescription.put(Location.CHAMBER, "You are in a small chamber filled with large boulders. The walls are very warm, causing the air in the room to be almost stifling from the heat. The only exit is a crawl heading west, through which a low rumbling noise is coming.");
		longDescription.put(Location.LIME, "You are walking along a gently sloping north/south passage lined with oddly shapped limestone formations.");
		longDescription.put(Location.FBARR, "You are standing at the entrance to a large, barren room. \nA sign posted above the entrance reads:\n\t\"CAUTION! BEAR IN ROOM!\"");
		longDescription.put(Location.BARR, "You are inside a barren room. The center of the room is completely empty except for some dust. Marks in the dust lead away toward the far end of the room. The only exit is the way you came in.");
		longDescription.put(Location.NEEND, "You are at the northeast end of an immense room, even larger than the Giant Room. It appears to be a repository for the \"Adventure\" program. Massive torches far overhead bathe the room with smoky yellow light. Scattered about you can be seen a pile of bottles (all of them empty), a nursery of young beanstalks murmering quietly, a bed of oysters, a bundle of black rods with rusty stars on their ends, and a collection of brass lanterns. Off to one side a great many dwarves are sleeping on the floor, snoring loudly. \nA sign nearby reads:\n\t\"DO NOT DISTURB THE DWARVES!\"\nAn immense mirror is hanging against one wall, and stretches to the other end of the room, where various other sundry objects can be glimpsed dimly in the distance.");
		longDescription.put(Location.SWEND, "You are at the southwest end of the repository. To one side is a pit full of fierce green snakes. On the other side is a row of small wicker cages, each of which contains a little sulking bird. In one corner is a bundle of black rods with rusty marks on their ends. A large number of velvet pillows are scattered about on the floor. A vast mirror stretches off to the northeast. At your feet is a large steel grate, next to which is a sign that reads, \n\t\"TREASURE VAULT. KEYS IN MAIN OFFICE.\"");
		
		
		shortDescription.put(Location.ROAD, "You're at end of road again.");
		shortDescription.put(Location.HILL, "You're at hill in road.");
		shortDescription.put(Location.BUILDING, "You're inside building.");
		shortDescription.put(Location.VALLEY, "You're in valley.");
		shortDescription.put(Location.FOREST, "You're in forest.");
		shortDescription.put(Location.WOODS, "You're in forest near a road.");
		shortDescription.put(Location.SLIT, "You're at slit in streambed.");
		shortDescription.put(Location.OUTSIDE, "You're outside grate.");
		shortDescription.put(Location.INSIDE, "You're below the grate.");
		shortDescription.put(Location.COBBLES, "You're in cobble crawl.");
		shortDescription.put(Location.DEBRIS, "You're in debris room.");
		shortDescription.put(Location.AWKWARD, "You are in an awkward sloping east/west canyon.");
		shortDescription.put(Location.BIRD, "You're in bird chamber.");
		shortDescription.put(Location.SMALLPIT, "You're at top of small pit.");
		shortDescription.put(Location.EASTMIST, "You're in Hall of Mists.");
		shortDescription.put(Location.NUGGET, "You're in nugget of gold room.");
		shortDescription.put(Location.EASTFISSURE, "You're on east bank of fissure.");
		shortDescription.put(Location.WESTFISSURE, "You're on the west side of the fissure in the Hall of Mists");
		shortDescription.put(Location.WESTMIST, "You're at west end of Hall of Mists.");
		shortDescription.put(Location.ALIKE1, "You are in a maze of twisty little passages, all alike.");
		shortDescription.put(Location.ALIKE2, "You are in a maze of twisty little passages, all alike.");
		shortDescription.put(Location.ALIKE3, "You are in a maze of twisty little passages, all alike.");
		shortDescription.put(Location.ALIKE4, "You are in a maze of twisty little passages, all alike.");
		shortDescription.put(Location.ALIKE5, "You are in a maze of twisty little passages, all alike.");
		shortDescription.put(Location.ALIKE6, "You are in a maze of twisty little passages, all alike.");
		shortDescription.put(Location.ALIKE7, "You are in a maze of twisty little passages, all alike.");
		shortDescription.put(Location.ALIKE8, "You are in a maze of twisty little passages, all alike.");
		shortDescription.put(Location.ALIKE9, "You are in a maze of twisty little passages, all alike.");
		shortDescription.put(Location.ALIKE10, "You are in a maze of twisty little passages, all alike.");
		shortDescription.put(Location.ALIKE11, "You are in a maze of twisty little passages, all alike.");
		shortDescription.put(Location.ALIKE12, "You are in a maze of twisty little passages, all alike.");
		shortDescription.put(Location.ALIKE13, "You are in a maze of twisty little passages, all alike.");
		shortDescription.put(Location.ALIKE14, "You are in a maze of twisty little passages, all alike.");
		shortDescription.put(Location.BRINK, "You're at brink of pit.");
		shortDescription.put(Location.EASTLONG, "You are at east end of long hall.");
		shortDescription.put(Location.WESTLONG, "You are at west end of long hall.");
		shortDescription.put(Location.DIFF0, "You are in a maze of twisty little passages, all different.");
		shortDescription.put(Location.DIFF1, "You are in a maze of twisting little passages, all different.");
		shortDescription.put(Location.DIFF2, "You are in a little maze of twisty passages, all different.");
		shortDescription.put(Location.DIFF3, "You are in a twisting maze of little passages, all different.");
		shortDescription.put(Location.DIFF4, "You are in a twisting little maze of passages, all different.");
		shortDescription.put(Location.DIFF5, "You are in a twisty little maze of passages, all different.");
		shortDescription.put(Location.DIFF6, "You are in a twisty maze of little passages, all different.");
		shortDescription.put(Location.DIFF7, "You are in a little twisty maze of passages, all different.");
		shortDescription.put(Location.DIFF8, "You are in a maze of little twisting passages, all different.");
		shortDescription.put(Location.DIFF9, "You are in a maze of little twisty passages, all different.");
		shortDescription.put(Location.DIFF10, "You are in a little maze of twisting passages, all different.");
		shortDescription.put(Location.PONY, "Dead end.");
		shortDescription.put(Location.CROSS, "You are at a crossover of a high N/S passage and a low E/W one.");
		shortDescription.put(Location.HALLOFMOUNTAINKING, "You're in Hall of Mt King.");
		shortDescription.put(Location.WEST, "You're in west side chamber.");
		shortDescription.put(Location.SOUTH, "You are in the south side chamber.");
		shortDescription.put(Location.NS, "You're in a N/S passage.");
		shortDescription.put(Location.Y2, "You're at \"Y2\".");
		shortDescription.put(Location.JUMBLE, "You are in a jumble of rock, with cracks everywhere.");
		shortDescription.put(Location.EASTWINDOW, "You're at window on pit.");
		shortDescription.put(Location.DIRTY, "You're in dirty passage.");
		shortDescription.put(Location.CLEAN, "You're by a clean pit.");
		shortDescription.put(Location.WET, "You're in pit by stream.");
		shortDescription.put(Location.DUSTY, "You're in dusty rock room.");
		shortDescription.put(Location.COMPLEX, "You're at complex junction.");
		shortDescription.put(Location.SHELL, "You're in Shell Room.");
		shortDescription.put(Location.ARCH, "You're in arched hall.");
		shortDescription.put(Location.RAGGED, "You are in a long sloping corridor with ragged sharp walls.");
		shortDescription.put(Location.CULDESAC, "You are in a cul-de-sac about eight feet across.");
		shortDescription.put(Location.ANTE, "You're in anteroom.");
		shortDescription.put(Location.WITT, "You are at Witt's End.");
		shortDescription.put(Location.BEDQUILT, "You're in Bedquilt.");
		shortDescription.put(Location.CHEESE, "You're in Swiss Cheese Room.");
		shortDescription.put(Location.SOFT, "You're in Soft Room.");
		shortDescription.put(Location.EAST2PIT, "You're at east end of Twopit Room.");
		shortDescription.put(Location.WEST2PIT, "You're at west end of Twopit Room.");
		shortDescription.put(Location.EASTPIT, "You're in east pit.");
		shortDescription.put(Location.WESTPIT, "You're in west pit.");
		shortDescription.put(Location.NARROW, "You're in narrow corridor.");
		shortDescription.put(Location.GIANT, "You're in Giant Room.");
		shortDescription.put(Location.BLOCK, "The passage here is blocked by a recent cave-in.");
		shortDescription.put(Location.IMMENSE, "You are at one end of an immense north/south passage.");
		shortDescription.put(Location.FALLS, "You're in cavern with waterfall.");
		shortDescription.put(Location.STEEP, "You're at steep incline above large room.");
		shortDescription.put(Location.ABOVEP, "You are in a secret N/S canyon above a sizable passage.");
		shortDescription.put(Location.SJUNC, "You're at junction of three secret canyons.");
		shortDescription.put(Location.STALACTITE, "You're on top of stalactite.");
		shortDescription.put(Location.LOW, "You are in a large low room. Crawls lead north, SE, and SW.");
		shortDescription.put(Location.CRAWL, "Dead end crawl.");
		shortDescription.put(Location.WESTWINDOW, "You're at window on pit.");
		shortDescription.put(Location.ORIENTAL, "You're in Oriental Room.");
		shortDescription.put(Location.MISTY, "You're in misty cavern.");
		shortDescription.put(Location.ALCOVE, "You're in alcove.");
		shortDescription.put(Location.PROOM, "You are in Plover Room.");
		shortDescription.put(Location.DROOM, "You're in the Dark-Room.");
		shortDescription.put(Location.SLAB, "You're in Slab Room.");
		shortDescription.put(Location.ABOVER, "You are in a secret N/S canyon above a large room.");
		shortDescription.put(Location.MIRROR, "You're in mirror canyon.");
		shortDescription.put(Location.RESER, "You're at reservoir.");
		shortDescription.put(Location.SCAN1, "You're in a secret canyon that exits to the north and east.");
		shortDescription.put(Location.SCAN2, "You're in a secret canyon that exits to the north and east.");
		shortDescription.put(Location.SCAN3, "You're in a secret canyon that exits to the north and east.");
		shortDescription.put(Location.SECRET, "You're in secret E/W canyon above tight canyon.");
		shortDescription.put(Location.WIDE, "You're at a wide place in a very tight N/S canyon.");
		shortDescription.put(Location.TIGHT, "The canyon here becomes too tight to go further south.");
		shortDescription.put(Location.TALL, "You're in tall E/W canyon.");
		shortDescription.put(Location.BOULDERS, "The conyon runs into a mass of boulders --- dead end.");
		shortDescription.put(Location.SCORR, "You are in sloping corridor.");
		shortDescription.put(Location.SWSIDE, "You're on SW side of chasm.");
		shortDescription.put(Location.DEAD0, "Dead end.");
		shortDescription.put(Location.DEAD1, "Dead end.");
		shortDescription.put(Location.DEAD2, "Dead end.");
		shortDescription.put(Location.DEAD3, "Dead end.");
		shortDescription.put(Location.DEAD4, "Dead end.");
		shortDescription.put(Location.DEAD5, "Dead end.");
		shortDescription.put(Location.DEAD6, "Dead end.");
		shortDescription.put(Location.DEAD7, "Dead end.");
		shortDescription.put(Location.DEAD8, "Dead end.");
		shortDescription.put(Location.DEAD9, "Dead end.");
		shortDescription.put(Location.DEAD10, "Dead end.");
		shortDescription.put(Location.DEAD11, "Dead end.");
		shortDescription.put(Location.NESIDE, "You're on NE side of chasm.");
		shortDescription.put(Location.CORR, "You're in corridor.");
		shortDescription.put(Location.FORK, "You're at fork in path.");
		shortDescription.put(Location.WARM, "You're at junction with warm walls.");
		shortDescription.put(Location.VIEW, "You're at breath-taking view.");
		shortDescription.put(Location.CHAMBER, "You're in chamber of boulders.");
		shortDescription.put(Location.LIME, "You're in limestone passage.");
		shortDescription.put(Location.FBARR, "You're in front of barren room.");
		shortDescription.put(Location.BARR, "You're in barren room.");
		shortDescription.put(Location.NEEND, "You're at NE end.");
		shortDescription.put(Location.SWEND, "You're at SW end.");
		
		
		//TODO titles
		title.put(Location.ROAD, "End Of Road");
		title.put(Location.HILL, "Hill");
		title.put(Location.BUILDING, "Building");
		title.put(Location.VALLEY, "Valley");
		title.put(Location.FOREST, "Forest");
		title.put(Location.WOODS, "Forest");
		title.put(Location.SLIT, "Slit");
		title.put(Location.OUTSIDE, "Outside Grate");
		title.put(Location.INSIDE, "Below Grate");
		title.put(Location.COBBLES, "Cobble Crawl");
		title.put(Location.DEBRIS, "Debris Room");
		title.put(Location.AWKWARD, "Awkward Canyon");
		title.put(Location.BIRD, "Bird Chamber");
		title.put(Location.SMALLPIT, "Small Pit");
		title.put(Location.EASTMIST, "Hall Of Mists");
		title.put(Location.NUGGET, "Nugget Of Gold Room");
		title.put(Location.EASTFISSURE, "East Bank");
		title.put(Location.WESTFISSURE, "West Bank");
		title.put(Location.WESTMIST, "Hall Of Mists");
		title.put(Location.ALIKE1, "You are in a maze of twisty little passages, all alike.");
		title.put(Location.ALIKE2, "You are in a maze of twisty little passages, all alike.");
		title.put(Location.ALIKE3, "You are in a maze of twisty little passages, all alike.");
		title.put(Location.ALIKE4, "You are in a maze of twisty little passages, all alike.");
		title.put(Location.ALIKE5, "You are in a maze of twisty little passages, all alike.");
		title.put(Location.ALIKE6, "You are in a maze of twisty little passages, all alike.");
		title.put(Location.ALIKE7, "You are in a maze of twisty little passages, all alike.");
		title.put(Location.ALIKE8, "You are in a maze of twisty little passages, all alike.");
		title.put(Location.ALIKE9, "You are in a maze of twisty little passages, all alike.");
		title.put(Location.ALIKE10, "You are in a maze of twisty little passages, all alike.");
		title.put(Location.ALIKE11, "You are in a maze of twisty little passages, all alike.");
		title.put(Location.ALIKE12, "You are in a maze of twisty little passages, all alike.");
		title.put(Location.ALIKE13, "You are in a maze of twisty little passages, all alike.");
		title.put(Location.ALIKE14, "You are in a maze of twisty little passages, all alike.");
		title.put(Location.BRINK, "You're at brink of pit.");
		title.put(Location.EASTLONG, "You are at east end of long hall.");
		title.put(Location.WESTLONG, "You are at west end of long hall.");
		title.put(Location.DIFF0, "You are in a maze of twisty little passages, all different.");
		title.put(Location.DIFF1, "You are in a maze of twisting little passages, all different.");
		title.put(Location.DIFF2, "You are in a little maze of twisty passages, all different.");
		title.put(Location.DIFF3, "You are in a twisting maze of little passages, all different.");
		title.put(Location.DIFF4, "You are in a twisting little maze of passages, all different.");
		title.put(Location.DIFF5, "You are in a twisty little maze of passages, all different.");
		title.put(Location.DIFF6, "You are in a twisty maze of little passages, all different.");
		title.put(Location.DIFF7, "You are in a little twisty maze of passages, all different.");
		title.put(Location.DIFF8, "You are in a maze of little twisting passages, all different.");
		title.put(Location.DIFF9, "You are in a maze of little twisty passages, all different.");
		title.put(Location.DIFF10, "You are in a little maze of twisting passages, all different.");
		title.put(Location.PONY, "Dead end.");
		title.put(Location.CROSS, "You are at a crossover of a high N/S passage and a low E/W one.");
		title.put(Location.HALLOFMOUNTAINKING, "You're in Hall of Mt King.");
		title.put(Location.WEST, "You're in west side chamber.");
		title.put(Location.SOUTH, "You are in the south side chamber.");
		title.put(Location.NS, "You're in a N/S passage.");
		title.put(Location.Y2, "You're at \"Y2\".");
		title.put(Location.JUMBLE, "You are in a jumble of rock, with cracks everywhere.");
		title.put(Location.EASTWINDOW, "You're at window on pit.");
		title.put(Location.DIRTY, "You're in dirty passage.");
		title.put(Location.CLEAN, "You're by a clean pit.");
		title.put(Location.WET, "You're in pit by stream.");
		title.put(Location.DUSTY, "You're in dusty rock room.");
		title.put(Location.COMPLEX, "You're at complex junction.");
		title.put(Location.SHELL, "You're in Shell Room.");
		title.put(Location.ARCH, "You're in arched hall.");
		title.put(Location.RAGGED, "You are in a long sloping corridor with ragged sharp walls.");
		title.put(Location.CULDESAC, "You are in a cul-de-sac about eight feet across.");
		title.put(Location.ANTE, "You're in anteroom.");
		title.put(Location.WITT, "You are at Witt's End.");
		title.put(Location.BEDQUILT, "You're in Bedquilt.");
		title.put(Location.CHEESE, "You're in Swiss Cheese Room.");
		title.put(Location.SOFT, "You're in Soft Room.");
		title.put(Location.EAST2PIT, "You're at east end of Twopit Room.");
		title.put(Location.WEST2PIT, "You're at west end of Twopit Room.");
		title.put(Location.EASTPIT, "You're in east pit.");
		title.put(Location.WESTPIT, "You're in west pit.");
		title.put(Location.NARROW, "You're in narrow corridor.");
		title.put(Location.GIANT, "You're in Giant Room.");
		title.put(Location.BLOCK, "The passage here is blocked by a recent cave-in.");
		title.put(Location.IMMENSE, "You are at one end of an immense north/south passage.");
		title.put(Location.FALLS, "You're in cavern with waterfall.");
		title.put(Location.STEEP, "You're at steep incline above large room.");
		title.put(Location.ABOVEP, "You are in a secret N/S canyon above a sizable passage.");
		title.put(Location.SJUNC, "You're at junction of three secret canyons.");
		title.put(Location.STALACTITE, "You're on top of stalactite.");
		title.put(Location.LOW, "You are in a large low room. Crawls lead north, SE, and SW.");
		title.put(Location.CRAWL, "Dead end crawl.");
		title.put(Location.WESTWINDOW, "You're at window on pit.");
		title.put(Location.ORIENTAL, "You're in Oriental Room.");
		title.put(Location.MISTY, "You're in misty cavern.");
		title.put(Location.ALCOVE, "You're in alcove.");
		title.put(Location.PROOM, "You are in Plover Room.");
		title.put(Location.DROOM, "You're in the Dark-Room.");
		title.put(Location.SLAB, "You're in Slab Room.");
		title.put(Location.ABOVER, "You are in a secret N/S canyon above a large room.");
		title.put(Location.MIRROR, "You're in mirror canyon.");
		title.put(Location.RESER, "You're at reservoir.");
		title.put(Location.SCAN1, "You're in a secret canyon that exits to the north and east.");
		title.put(Location.SCAN2, "You're in a secret canyon that exits to the north and east.");
		title.put(Location.SCAN3, "You're in a secret canyon that exits to the north and east.");
		title.put(Location.SECRET, "You're in secret E/W canyon above tight canyon.");
		title.put(Location.WIDE, "You're at a wide place in a very tight N/S canyon.");
		title.put(Location.TIGHT, "The canyon here becomes too tight to go further south.");
		title.put(Location.TALL, "You're in tall E/W canyon.");
		title.put(Location.BOULDERS, "The conyon runs into a mass of boulders --- dead end.");
		title.put(Location.SCORR, "You are in sloping corridor.");
		title.put(Location.SWSIDE, "You're on SW side of chasm.");
		title.put(Location.DEAD0, "Dead End");
		title.put(Location.DEAD1, "Dead End");
		title.put(Location.DEAD2, "Dead End");
		title.put(Location.DEAD3, "Dead End");
		title.put(Location.DEAD4, "Dead End");
		title.put(Location.DEAD5, "Dead End");
		title.put(Location.DEAD6, "Dead End");
		title.put(Location.DEAD7, "Dead End");
		title.put(Location.DEAD8, "Dead End");
		title.put(Location.DEAD9, "Dead End");
		title.put(Location.DEAD10, "Dead End.");
		title.put(Location.DEAD11, "Dead End.");
		title.put(Location.NESIDE, "You're on NE side of chasm.");
		title.put(Location.CORR, "You're in corridor.");
		title.put(Location.FORK, "You're at fork in path.");
		title.put(Location.WARM, "You're at junction with warm walls.");
		title.put(Location.VIEW, "You're at breath-taking view.");
		title.put(Location.CHAMBER, "You're in chamber of boulders.");
		title.put(Location.LIME, "You're in limestone passage.");
		title.put(Location.FBARR, "You're in front of barren room.");
		title.put(Location.BARR, "You're in barren room.");
		title.put(Location.NEEND, "You're at NE end.");
		title.put(Location.SWEND, "You're at SW end.");
		
		objectLocation.put(GameObjects.KEYS, Location.BUILDING);
		objectLocation.put(GameObjects.LAMP, Location.BUILDING);
		objectLocation.put(GameObjects.GRATE, Location.OUTSIDE);
		objectLocation.put(GameObjects.GRATE_, Location.INSIDE);
		objectLocation.put(GameObjects.CAGE, Location.COBBLES);
		objectLocation.put(GameObjects.ROD, Location.DEBRIS);
		objectLocation.put(GameObjects.ROD2, Location.THEVOID);
		objectLocation.put(GameObjects.TREADS, Location.EASTMIST);
		objectLocation.put(GameObjects.TREADS_, Location.SMALLPIT);
		objectLocation.put(GameObjects.BIRD, Location.BIRD);
		objectLocation.put(GameObjects.DOOR, Location.IMMENSE);
		objectLocation.put(GameObjects.PILLOW, Location.SOFT);
		objectLocation.put(GameObjects.SNAKE, Location.HALLOFMOUNTAINKING);
		objectLocation.put(GameObjects.CRYSTAL, Location.EASTFISSURE);
		objectLocation.put(GameObjects.CRYSTAL_, Location.WESTFISSURE);
		objectLocation.put(GameObjects.TABLET, Location.DROOM);
		objectLocation.put(GameObjects.CLAM, Location.SHELL);
		objectLocation.put(GameObjects.OYSTER, Location.THEVOID);
		objectLocation.put(GameObjects.MAG, Location.ANTE);
		objectLocation.put(GameObjects.DWARF, Location.THEVOID);
		objectLocation.put(GameObjects.KNIFE, Location.THEVOID);
		objectLocation.put(GameObjects.FOOD, Location.BUILDING);
		objectLocation.put(GameObjects.BOTTLE, Location.BUILDING);
		objectLocation.put(GameObjects.WATER, Location.THEVOID);
		objectLocation.put(GameObjects.OIL, Location.THEVOID);
		objectLocation.put(GameObjects.MIRROR, Location.MIRROR);
		objectLocation.put(GameObjects.MIRROR_, Location.THEVOID);
		objectLocation.put(GameObjects.PLANT, Location.WESTPIT);
		objectLocation.put(GameObjects.PLANT2, Location.WEST2PIT);
		objectLocation.put(GameObjects.PLANT2_, Location.EAST2PIT);
		objectLocation.put(GameObjects.STALACTITE, Location.STALACTITE);
		objectLocation.put(GameObjects.SHADOW, Location.EASTWINDOW);
		objectLocation.put(GameObjects.SHADOW_, Location.WESTWINDOW);
		objectLocation.put(GameObjects.AXE, Location.THEVOID);
		objectLocation.put(GameObjects.ART, Location.ORIENTAL);
		objectLocation.put(GameObjects.PIRATE, Location.THEVOID);
		objectLocation.put(GameObjects.DRAGON, Location.SCAN1);
		objectLocation.put(GameObjects.DRAGON_, Location.SCAN3);
		objectLocation.put(GameObjects.BRIDGE, Location.SWSIDE);
		objectLocation.put(GameObjects.BRIDGE_, Location.NESIDE);
		objectLocation.put(GameObjects.TROLL, Location.SWSIDE);
		objectLocation.put(GameObjects.TROLL_, Location.NESIDE);
		objectLocation.put(GameObjects.TROLL2, Location.THEVOID);
		objectLocation.put(GameObjects.TROLL2_, Location.THEVOID);
		objectLocation.put(GameObjects.BEAR, Location.THEVOID);
		objectLocation.put(GameObjects.MESSAGE, Location.THEVOID);
		objectLocation.put(GameObjects.GEYSER, Location.VIEW);
		objectLocation.put(GameObjects.PONY, Location.PONY);
		objectLocation.put(GameObjects.BATTERIES, Location.THEVOID);
		objectLocation.put(GameObjects.MOSS, Location.SOFT);
		
		//TODO finish treasure locations
		objectLocation.put(GameObjects.GOLD, Location.NUGGET);
		objectLocation.put(GameObjects.DIAMONDS, Location.THEVOID);
		objectLocation.put(GameObjects.SILVER, Location.NS);
		objectLocation.put(GameObjects.JEWELS, Location.THEVOID);
		objectLocation.put(GameObjects.COINS, Location.THEVOID);
		objectLocation.put(GameObjects.CHEST, Location.THEVOID);
		objectLocation.put(GameObjects.EGGS, Location.GIANT);
		objectLocation.put(GameObjects.TRIDENT, Location.THEVOID);
		objectLocation.put(GameObjects.VASE, Location.ORIENTAL);
		objectLocation.put(GameObjects.EMERALD, Location.PROOM);
		objectLocation.put(GameObjects.PYRAMID, Location.DROOM);
		objectLocation.put(GameObjects.PEARL, Location.THEVOID);
		objectLocation.put(GameObjects.RUG, Location.THEVOID);
		objectLocation.put(GameObjects.RUG_, Location.THEVOID);
		objectLocation.put(GameObjects.SPICES, Location.THEVOID);
		objectLocation.put(GameObjects.CHAIN, Location.BARR);
	}
	
	public boolean isMovement(String input)
	{
		boolean contains = false;
		if(movement.containsKey(input))
		{
			contains = true;
		}
		return contains;
	}
	
	public Movement whichMovement(String input)
	{
		return movement.get(input);
	}
	
	public boolean isAction(String input)
	{
		boolean contains = false;
		if(actions.containsKey(input))
		{
			contains = true;
		}
		return contains;
	}
	
	public ActionWords whichAction(String input)
	{
		return actions.get(input);
	}
	
	public boolean isMessage(String input)
	{
		boolean contains = false;
		if(mwords.containsKey(input))
		{
			contains = true;
		}
		return contains;
	}
	
	public MessageWords whichMessage(String input)
	{
		return mwords.get(input);
	}
	
	public boolean isObject(String input)
	{
		boolean contains = false;
		if(objects.containsKey(input))
		{
			contains = true;
		}
		return contains;
	}
	
	public boolean isObject(Object input)
	{
		return objects.containsValue(input);
	}
	
	public GameObjects whichObject(String input)
	{
		GameObjects result = GameObjects.NOTHING;
		if(objects.containsKey(input))
		{
			result = objects.get(input);
		}
		return result;
	}
	
	public Location getObjectLocation(GameObjects object)
	{
		Location result = Location.THEVOID;
		if(objectLocation.containsKey(object))
		{
			result = objectLocation.get(object);
		}
		return result;
	}
	
	public ArrayList<GameObjects> objectsHere(Location here)
	{
		ArrayList<GameObjects> result = null;
		if(objectLocation.containsValue(here))
		{
			result = new ArrayList<GameObjects>();
			if(objectIsHere(GameObjects.KEYS, here))
			{	result.add(GameObjects.KEYS);}
			if(objectIsHere(GameObjects.LAMP, here))
			{	result.add(GameObjects.LAMP);}
			if(objectIsHere(GameObjects.GRATE, here))
			{	result.add(GameObjects.GRATE);}
			if(objectIsHere(GameObjects.GRATE_, here))
			{	result.add(GameObjects.GRATE_);}
			if(objectIsHere(GameObjects.CAGE, here))
			{	result.add(GameObjects.CAGE);}
			if(objectIsHere(GameObjects.ROD, here))
			{	result.add(GameObjects.ROD);}
			if(objectIsHere(GameObjects.ROD2, here))
			{	result.add(GameObjects.ROD2);}
			if(objectIsHere(GameObjects.TREADS, here))
			{	result.add(GameObjects.TREADS);}
			if(objectIsHere(GameObjects.TREADS_, here))
			{	result.add(GameObjects.TREADS_);}
			if(objectIsHere(GameObjects.BIRD, here))
			{	result.add(GameObjects.BIRD);}
			if(objectIsHere(GameObjects.DOOR, here))
			{	result.add(GameObjects.DOOR);}
			if(objectIsHere(GameObjects.PILLOW, here))
			{	result.add(GameObjects.PILLOW);}
			if(objectIsHere(GameObjects.SNAKE, here))
			{	result.add(GameObjects.SNAKE);}
			if(objectIsHere(GameObjects.CRYSTAL, here))
			{	result.add(GameObjects.CRYSTAL);}
			if(objectIsHere(GameObjects.CRYSTAL_, here))
			{	result.add(GameObjects.CRYSTAL_);}
			if(objectIsHere(GameObjects.TABLET, here))
			{	result.add(GameObjects.TABLET);}
			if(objectIsHere(GameObjects.CLAM, here))
			{	result.add(GameObjects.CLAM);}
			if(objectIsHere(GameObjects.OYSTER, here))
			{	result.add(GameObjects.OYSTER);}
			if(objectIsHere(GameObjects.MAG, here))
			{	result.add(GameObjects.MAG);}
			if(objectIsHere(GameObjects.DWARF, here))
			{	result.add(GameObjects.DWARF);}
			if(objectIsHere(GameObjects.KNIFE, here))
			{	result.add(GameObjects.KNIFE);}
			if(objectIsHere(GameObjects.FOOD, here))
			{	result.add(GameObjects.FOOD);}
			if(objectIsHere(GameObjects.BOTTLE, here))
			{	result.add(GameObjects.BOTTLE);}
			if(objectIsHere(GameObjects.WATER, here))
			{	result.add(GameObjects.WATER);}
			if(objectIsHere(GameObjects.OIL, here))
			{	result.add(GameObjects.OIL);}
			if(objectIsHere(GameObjects.MIRROR, here))
			{	result.add(GameObjects.MIRROR);}
			if(objectIsHere(GameObjects.MIRROR_, here))
			{	result.add(GameObjects.MIRROR_);}
			if(objectIsHere(GameObjects.PLANT, here))
			{	result.add(GameObjects.PLANT);}
			if(objectIsHere(GameObjects.PLANT2, here))
			{	result.add(GameObjects.PLANT2);}
			if(objectIsHere(GameObjects.PLANT2_, here))
			{	result.add(GameObjects.PLANT2_);}
			if(objectIsHere(GameObjects.STALACTITE, here))
			{	result.add(GameObjects.STALACTITE);}
			if(objectIsHere(GameObjects.SHADOW, here))
			{	result.add(GameObjects.SHADOW);}
			if(objectIsHere(GameObjects.SHADOW_, here))
			{	result.add(GameObjects.SHADOW_);}
			if(objectIsHere(GameObjects.AXE, here))
			{	result.add(GameObjects.AXE);}
			if(objectIsHere(GameObjects.ART, here))
			{	result.add(GameObjects.ART);}
			if(objectIsHere(GameObjects.PIRATE, here))
			{	result.add(GameObjects.PIRATE);}
			if(objectIsHere(GameObjects.DRAGON, here))
			{	result.add(GameObjects.DRAGON);}
			if(objectIsHere(GameObjects.DRAGON_, here))
			{	result.add(GameObjects.DRAGON_);}
			if(objectIsHere(GameObjects.BRIDGE, here))
			{	result.add(GameObjects.BRIDGE);}
			if(objectIsHere(GameObjects.BRIDGE_, here))
			{	result.add(GameObjects.BRIDGE_);}
			if(objectIsHere(GameObjects.TROLL, here))
			{	result.add(GameObjects.TROLL);}
			if(objectIsHere(GameObjects.TROLL_, here))
			{	result.add(GameObjects.TROLL_);}
			if(objectIsHere(GameObjects.TROLL2, here))
			{	result.add(GameObjects.TROLL2);}
			if(objectIsHere(GameObjects.TROLL2_, here))
			{	result.add(GameObjects.TROLL2_);}
			if(objectIsHere(GameObjects.BEAR, here))
			{	result.add(GameObjects.BEAR);}
			if(objectIsHere(GameObjects.MESSAGE, here))
			{	result.add(GameObjects.MESSAGE);}
			if(objectIsHere(GameObjects.GEYSER, here))
			{	result.add(GameObjects.GEYSER);}
			if(objectIsHere(GameObjects.PONY, here))
			{	result.add(GameObjects.PONY);}
			if(objectIsHere(GameObjects.BATTERIES, here))
			{	result.add(GameObjects.BATTERIES);}
			if(objectIsHere(GameObjects.MOSS, here))
			{	result.add(GameObjects.MOSS);}
			if(objectIsHere(GameObjects.GOLD, here))
			{	result.add(GameObjects.GOLD);}
			if(objectIsHere(GameObjects.DIAMONDS, here))
			{	result.add(GameObjects.DIAMONDS);}
			if(objectIsHere(GameObjects.SILVER, here))
			{	result.add(GameObjects.SILVER);}
			if(objectIsHere(GameObjects.JEWELS, here))
			{	result.add(GameObjects.JEWELS);}
			if(objectIsHere(GameObjects.COINS, here))
			{	result.add(GameObjects.COINS);}
			if(objectIsHere(GameObjects.CHEST, here))
			{	result.add(GameObjects.CHEST);}
			if(objectIsHere(GameObjects.EGGS, here))
			{	result.add(GameObjects.EGGS);}
			if(objectIsHere(GameObjects.TRIDENT, here))
			{	result.add(GameObjects.TRIDENT);}
			if(objectIsHere(GameObjects.VASE, here))
			{	result.add(GameObjects.VASE);}
			if(objectIsHere(GameObjects.EMERALD, here))
			{	result.add(GameObjects.EMERALD);}
			if(objectIsHere(GameObjects.PYRAMID, here))
			{	result.add(GameObjects.PYRAMID);}
			if(objectIsHere(GameObjects.PEARL, here))
			{	result.add(GameObjects.PEARL);}
			if(objectIsHere(GameObjects.RUG, here))
			{	result.add(GameObjects.RUG);}
			if(objectIsHere(GameObjects.RUG_, here))
			{	result.add(GameObjects.RUG_);}
			if(objectIsHere(GameObjects.SPICES, here))
			{	result.add(GameObjects.SPICES);}
			if(objectIsHere(GameObjects.CHAIN, here))
			{	result.add(GameObjects.CHAIN);}
		}
		return result;
	}
	
	public boolean objectIsHere(GameObjects object, Location here)
	{
		return getObjectLocation(object) == here;
	}
	
	public boolean beenHere(Location here)
	{
		boolean result = false;
		int visit = visits.get(here);
		if(visit == 0 || visit % 5 == 0)
		{
			result = false;
		}
		return result;
	}
	
	public void visit(Location here, boolean canSee)
	{
		if(canSee)
		{
			int visit = visits.get(here);
			visits.replace(here, visit + 1);
		}
	}

	public String getDescription(Location here, int breif)
	{
		String description = null;
		int visit = visits.get(here);
		if((longDescription.containsKey(here)) && ((breif == 0 && visit % 5 == 0) || (breif == 2) || (visits.get(here) == 0)))
		{
			description = longDescription.get(here);
		}
		else
		{
			description = shortDescription.get(here);
		}
		System.out.println("visit " + visit);
		visits.replace(here, visit + 1);
		return description;
	}
	
	public String getLongDescription(Location here)
	{
		String description = "Sorry, but I am not allowed to go into more detail. I will repeat the long description of your location.\n\n";
		if(longDescription.containsKey(here))
		{
			description = longDescription.get(here);
		}
		return description;
	}
	
	public void takeObject(GameObjects thing)
	{
		objectLocation.put(thing, Location.INHAND);
	}
	
	public void voidObject(GameObjects thing)
	{
		objectLocation.put(thing, Location.THEVOID);
	}
	
	public void dropObject(GameObjects thing, Location here)
	{
		objectLocation.put(thing, here);
	}
	
	public boolean haveIFound(GameObjects thing)
	{
		return found.get(thing);
	}
	
	public void wasFound(GameObjects thing)
	{
		found.put(thing, true);
	}
	
}
