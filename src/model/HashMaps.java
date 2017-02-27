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
	private HashMap<Location, Boolean> firstVisit = new HashMap();
	private HashMap<Location, String> longDescription = new HashMap();
	private HashMap<Location, String> shortDescription = new HashMap();
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
		actions.put("find", ActionWords.FIND);
		actions.put("where", ActionWords.FIND);
		actions.put("inven", ActionWords.INVENTORY);
		actions.put("score", ActionWords.SCORE);
		actions.put("quit", ActionWords.QUIT);
		
		firstVisit.put(Location.ROAD, false);
		firstVisit.put(Location.HILL, false);
		firstVisit.put(Location.BUILDING, false);
		firstVisit.put(Location.VALLEY, false);
		firstVisit.put(Location.FOREST, false);
		firstVisit.put(Location.WOODS, false);
		firstVisit.put(Location.SLIT, false);
		firstVisit.put(Location.OUTSIDE, false);
		firstVisit.put(Location.INSIDE, false);
		firstVisit.put(Location.COBBLES, false);
		firstVisit.put(Location.DEBRIS, false);
		firstVisit.put(Location.AWKWARD, false);
		firstVisit.put(Location.BIRD, false);
		firstVisit.put(Location.SMALLPIT, false);
		firstVisit.put(Location.EASTMIST, false);
		firstVisit.put(Location.NUGGET, false);
		firstVisit.put(Location.EASTFISSURE, false);
		firstVisit.put(Location.WESTFISSURE, false);
		firstVisit.put(Location.WESTMIST, false);
		firstVisit.put(Location.BRINK, false);
		firstVisit.put(Location.EASTLONG, false);
		firstVisit.put(Location.WESTLONG, false);
		firstVisit.put(Location.PONY, false);
		firstVisit.put(Location.CROSS, false);
		firstVisit.put(Location.HALLOFMOUNTAINKING, false);
		firstVisit.put(Location.WEST, false);
		firstVisit.put(Location.SOUTH, false);
		firstVisit.put(Location.NS, false);
		firstVisit.put(Location.Y2, false);
		firstVisit.put(Location.JUMBLE, false);
		firstVisit.put(Location.EASTWINDOW, false);
		firstVisit.put(Location.DIRTY, false);
		firstVisit.put(Location.CLEAN, false);
		firstVisit.put(Location.WET, false);
		firstVisit.put(Location.DUSTY, false);
		firstVisit.put(Location.COMPLEX, false);
		firstVisit.put(Location.SHELL, false);
		firstVisit.put(Location.ARCH, false);
		firstVisit.put(Location.RAGGED, false);
		firstVisit.put(Location.CULDESAC, false);
		firstVisit.put(Location.ANTE, false);
		firstVisit.put(Location.WITT, false);
		firstVisit.put(Location.BEDQUILT, false);
		firstVisit.put(Location.CHEESE, false);
		firstVisit.put(Location.SOFT, false);
		firstVisit.put(Location.EAST2PIT, false);
		firstVisit.put(Location.WEST2PIT, false);
		firstVisit.put(Location.EASTPIT, false);
		firstVisit.put(Location.WESTPIT, false);
		firstVisit.put(Location.NARROW, false);
		firstVisit.put(Location.GIANT, false);
		firstVisit.put(Location.BLOCK, false);
		firstVisit.put(Location.IMMENSE, false);
		firstVisit.put(Location.FALLS, false);
		firstVisit.put(Location.STEEP, false);
		firstVisit.put(Location.ABOVEP, false);
		firstVisit.put(Location.SJUNC, false);
		firstVisit.put(Location.TIGHT, false);
		firstVisit.put(Location.LOW, false);
		firstVisit.put(Location.CRAWL, false);
		firstVisit.put(Location.WESTWINDOW, false);
		firstVisit.put(Location.ORIENTAL, false);
		firstVisit.put(Location.MISTY, false);
		firstVisit.put(Location.ALCOVE, false);
		firstVisit.put(Location.PROOM, false);
		firstVisit.put(Location.DROOM, false);
		firstVisit.put(Location.SLAB, false);
		firstVisit.put(Location.ABOVER, false);
		firstVisit.put(Location.MIRROR, false);
		firstVisit.put(Location.RESER, false);
		firstVisit.put(Location.SCAN1, false);
		firstVisit.put(Location.SCAN2, false);
		firstVisit.put(Location.SCAN3, false);
		firstVisit.put(Location.SECRET, false);
		firstVisit.put(Location.WIDE, false);
		firstVisit.put(Location.STALACTITE, false);
		firstVisit.put(Location.TALL, false);
		firstVisit.put(Location.BOULDERS, false);
		firstVisit.put(Location.SCORR, false);
		firstVisit.put(Location.SWSIDE, false);
		firstVisit.put(Location.NESIDE, false);
		firstVisit.put(Location.CORR, false);
		firstVisit.put(Location.FORK, false);
		firstVisit.put(Location.WARM, false);
		firstVisit.put(Location.VIEW, false);
		firstVisit.put(Location.CHAMBER, false);
		firstVisit.put(Location.LIME, false);
		firstVisit.put(Location.FBARR, false);
		firstVisit.put(Location.BARR, false);
		firstVisit.put(Location.NEEND, false);
		firstVisit.put(Location.SWEND, false);
		
		longDescription.put(Location.ROAD, "You are standing at the end of a road before a small brick building.  Around you is a forest.  A small stream flows out of the building and down a gully.");
		longDescription.put(Location.HILL, "You have walked up a hill, still in the forest.  The road slopes back down the other side of the hill.  There is a building in the distance.");
		longDescription.put(Location.BUILDING, "You are inside a building, a well house for a large spring.");
		longDescription.put(Location.VALLEY, "You are in a valley in the forest beside a stream tumbling along a rocky bed.");
		longDescription.put(Location.FOREST, "You are in open forest, with a deep valley to one side.");
		longDescription.put(Location.WOODS, "You are in open forest near both a valley and a road.");
		longDescription.put(Location.SLIT, "At your feet all the water of the stream splashes into a 2-inch slit in the rock.  Downstream the streambed is bare rock.");
		longDescription.put(Location.OUTSIDE, "You are in a 20-foot depression floored with bare dirt.  Set into the dirt is a strong steel grate mounted in concrete. A dry streambed leads into the depression.");
		longDescription.put(Location.INSIDE, "You are in a small chamber beneath a 3x3 steel grate to the surface.  A low crawl over cobbles leads inward to the West.");
		longDescription.put(Location.COBBLES, "You are crawling over cobbles in a low passage.  There is a dim light at the east end of the pasage.");
		longDescription.put(Location.DEBRIS, "You are in a debris room filled with stuff washed in from the surface. A low wide passage with cobbles becomes plugged with mud and debris here, but an awkward canyon leads upward and west. A note on the wall says:\n\tMagic Word \"XYZZY\"");
		longDescription.put(Location.AWKWARD, "You are in an awkward sloping east/west canyon.");
		longDescription.put(Location.BIRD, "You are in a splendid chamber thirty feet high. The walls are frozen rivers of orange stone.  An awkward canyon and a good passage exit from east and west sides of the chamber.");
		longDescription.put(Location.SMALLPIT, "At your feet is a small pit breathing traces of white mist. An east passage ends here except for a small crack leading on.");
		longDescription.put(Location.EASTMIST, "You are at one end of a vast hall stretching forward out of sight to the west.  There are openings to either side. Nearby, a wide stone staircase leads downward.  The hall is filled with wisps of white mist swaying to and fro almost as if alive.  A cold wind blows up the staircase. There is a passage at the top of a dome behind you.");
		longDescription.put(Location.NUGGET, "This is a low room with a crude note on the wall. The note says: \n\t\"You won't get it up the steps.\"");
		longDescription.put(Location.EASTFISSURE, "You are on the east bank of a fissure slicing clear across the hall.  The mist is quite thick here, and the fissure is too wide to jump.");
		longDescription.put(Location.WESTMIST, "");
		longDescription.put(Location.BRINK, "");
		longDescription.put(Location.EASTLONG, "");
		longDescription.put(Location.WESTLONG, "");
		longDescription.put(Location.PONY, "");
		longDescription.put(Location.CROSS, "");
		longDescription.put(Location.HALLOFMOUNTAINKING, "");
		longDescription.put(Location.WEST, "");
		longDescription.put(Location.SOUTH, "");
		longDescription.put(Location.NS, "");
		longDescription.put(Location.Y2, "");
		longDescription.put(Location.JUMBLE, "");
		longDescription.put(Location.EASTWINDOW, "");
		longDescription.put(Location.DIRTY, "");
		longDescription.put(Location.CLEAN, "");
		longDescription.put(Location.WET, "");
		longDescription.put(Location.DUSTY, "");
		longDescription.put(Location.COMPLEX, "");
		longDescription.put(Location.SHELL, "");
		longDescription.put(Location.ARCH, "");
		longDescription.put(Location.RAGGED, "");
		longDescription.put(Location.CULDESAC, "");
		longDescription.put(Location.ANTE, "");
		longDescription.put(Location.WITT, "");
		longDescription.put(Location.BEDQUILT, "");
		longDescription.put(Location.CHEESE, "");
		longDescription.put(Location.SOFT, "");
		longDescription.put(Location.EAST2PIT, "");
		longDescription.put(Location.WEST2PIT, "");
		longDescription.put(Location.EASTPIT, "");
		longDescription.put(Location.WESTPIT, "");
		longDescription.put(Location.NARROW, "");
		longDescription.put(Location.GIANT, "");
		longDescription.put(Location.BLOCK, "");
		longDescription.put(Location.IMMENSE, "");
		longDescription.put(Location.FALLS, "");
		longDescription.put(Location.STEEP, "");
		longDescription.put(Location.ABOVEP, "");
		longDescription.put(Location.SJUNC, "");
		longDescription.put(Location.TIGHT, "");
		longDescription.put(Location.LOW, "");
		longDescription.put(Location.CRAWL, "");
		longDescription.put(Location.WESTWINDOW, "");
		longDescription.put(Location.ORIENTAL, "");
		longDescription.put(Location.MISTY, "");
		longDescription.put(Location.ALCOVE, "");
		longDescription.put(Location.PROOM, "");
		longDescription.put(Location.DROOM, "");
		longDescription.put(Location.SLAB, "");
		longDescription.put(Location.ABOVER, "");
		longDescription.put(Location.MIRROR, "");
		longDescription.put(Location.RESER, "");
		longDescription.put(Location.SCAN1, "");
		longDescription.put(Location.SCAN2, "");
		longDescription.put(Location.SCAN3, "");
		longDescription.put(Location.SECRET, "");
		longDescription.put(Location.WIDE, "");
		longDescription.put(Location.STALACTITE, "");
		longDescription.put(Location.TALL, "");
		longDescription.put(Location.BOULDERS, "");
		longDescription.put(Location.SCORR, "");
		longDescription.put(Location.SWSIDE, "");
		longDescription.put(Location.NESIDE, "");
		longDescription.put(Location.CORR, "");
		longDescription.put(Location.FORK, "");
		longDescription.put(Location.WARM, "");
		longDescription.put(Location.VIEW, "");
		longDescription.put(Location.CHAMBER, "");
		longDescription.put(Location.LIME, "");
		longDescription.put(Location.FBARR, "");
		longDescription.put(Location.BARR, "");
		longDescription.put(Location.NEEND, "");
		longDescription.put(Location.SWEND, "");
		
		
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
		shortDescription.put(Location.EASTMIST, "You're in hall of mists.");
		shortDescription.put(Location.NUGGET, "You're in nugget of gold room.");
		shortDescription.put(Location.EASTFISSURE, "You're on east bank of fissure.");
		shortDescription.put(Location.WESTFISSURE, "");
		shortDescription.put(Location.WESTMIST, "");
		shortDescription.put(Location.ALIKE1, "");
		shortDescription.put(Location.ALIKE2, "");
		shortDescription.put(Location.ALIKE3, "");
		shortDescription.put(Location.ALIKE4, "");
		shortDescription.put(Location.ALIKE5, "");
		shortDescription.put(Location.ALIKE6, "");
		shortDescription.put(Location.ALIKE7, "");
		shortDescription.put(Location.ALIKE8, "");
		shortDescription.put(Location.ALIKE9, "");
		shortDescription.put(Location.ALIKE10, "");
		shortDescription.put(Location.ALIKE11, "");
		shortDescription.put(Location.ALIKE12, "");
		shortDescription.put(Location.BRINK, "");
		shortDescription.put(Location.EASTLONG, "");
		shortDescription.put(Location.WESTLONG, "");
		shortDescription.put(Location.DIFF0, "");
		shortDescription.put(Location.DIFF1, "");
		shortDescription.put(Location.DIFF2, "");
		shortDescription.put(Location.DIFF3, "");
		shortDescription.put(Location.DIFF4, "");
		shortDescription.put(Location.DIFF5, "");
		shortDescription.put(Location.DIFF6, "");
		shortDescription.put(Location.DIFF7, "");
		shortDescription.put(Location.DIFF8, "");
		shortDescription.put(Location.DIFF9, "");
		shortDescription.put(Location.DIFF10, "");
		shortDescription.put(Location.PONY, "");
		shortDescription.put(Location.CROSS, "");
		shortDescription.put(Location.HALLOFMOUNTAINKING, "");
		shortDescription.put(Location.WEST, "");
		shortDescription.put(Location.SOUTH, "");
		shortDescription.put(Location.NS, "");
		shortDescription.put(Location.Y2, "");
		shortDescription.put(Location.JUMBLE, "");
		shortDescription.put(Location.EASTWINDOW, "");
		shortDescription.put(Location.DIRTY, "");
		shortDescription.put(Location.CLEAN, "");
		shortDescription.put(Location.WET, "");
		shortDescription.put(Location.DUSTY, "");
		shortDescription.put(Location.COMPLEX, "");
		shortDescription.put(Location.SHELL, "");
		shortDescription.put(Location.ARCH, "");
		shortDescription.put(Location.RAGGED, "");
		shortDescription.put(Location.CULDESAC, "");
		shortDescription.put(Location.ANTE, "");
		shortDescription.put(Location.WITT, "");
		shortDescription.put(Location.BEDQUILT, "");
		shortDescription.put(Location.CHEESE, "");
		shortDescription.put(Location.SOFT, "");
		shortDescription.put(Location.EAST2PIT, "");
		shortDescription.put(Location.WEST2PIT, "");
		shortDescription.put(Location.EASTPIT, "");
		shortDescription.put(Location.WESTPIT, "");
		shortDescription.put(Location.NARROW, "");
		shortDescription.put(Location.GIANT, "");
		shortDescription.put(Location.BLOCK, "");
		shortDescription.put(Location.IMMENSE, "");
		shortDescription.put(Location.FALLS, "");
		shortDescription.put(Location.STEEP, "");
		shortDescription.put(Location.ABOVEP, "");
		shortDescription.put(Location.SJUNC, "");
		shortDescription.put(Location.TIGHT, "");
		shortDescription.put(Location.LOW, "");
		shortDescription.put(Location.CRAWL, "");
		shortDescription.put(Location.WESTWINDOW, "");
		shortDescription.put(Location.ORIENTAL, "");
		shortDescription.put(Location.MISTY, "");
		shortDescription.put(Location.ALCOVE, "");
		shortDescription.put(Location.PROOM, "");
		shortDescription.put(Location.DROOM, "");
		shortDescription.put(Location.SLAB, "");
		shortDescription.put(Location.ABOVER, "");
		shortDescription.put(Location.MIRROR, "");
		shortDescription.put(Location.RESER, "");
		shortDescription.put(Location.SCAN1, "");
		shortDescription.put(Location.SCAN2, "");
		shortDescription.put(Location.SCAN3, "");
		shortDescription.put(Location.SECRET, "");
		shortDescription.put(Location.WIDE, "");
		shortDescription.put(Location.STALACTITE, "");
		shortDescription.put(Location.TALL, "");
		shortDescription.put(Location.BOULDERS, "");
		shortDescription.put(Location.SCORR, "");
		shortDescription.put(Location.SWSIDE, "");
		shortDescription.put(Location.DEAD0, "");
		shortDescription.put(Location.DEAD1, "");
		shortDescription.put(Location.DEAD2, "");
		shortDescription.put(Location.DEAD3, "");
		shortDescription.put(Location.DEAD4, "");
		shortDescription.put(Location.DEAD5, "");
		shortDescription.put(Location.DEAD6, "");
		shortDescription.put(Location.DEAD7, "");
		shortDescription.put(Location.DEAD8, "");
		shortDescription.put(Location.DEAD9, "");
		shortDescription.put(Location.DEAD10, "");
		shortDescription.put(Location.DEAD11, "");
		shortDescription.put(Location.NESIDE, "");
		shortDescription.put(Location.CORR, "");
		shortDescription.put(Location.FORK, "");
		shortDescription.put(Location.WARM, "");
		shortDescription.put(Location.VIEW, "");
		shortDescription.put(Location.CHAMBER, "");
		shortDescription.put(Location.LIME, "");
		shortDescription.put(Location.FBARR, "");
		shortDescription.put(Location.BARR, "");
		shortDescription.put(Location.NEEND, "");
		shortDescription.put(Location.SWEND, "");
		shortDescription.put(Location.CRACK, "The crack is far too small for you to follow.");
		shortDescription.put(Location.NECK, "You are the the bottom of the pit with a broken neck.");
		shortDescription.put(Location.LOSE, "");
		shortDescription.put(Location.CANT, "");
		shortDescription.put(Location.CLIMB, "");
		shortDescription.put(Location.CHECK, "");
		shortDescription.put(Location.SNAKED, "");
		shortDescription.put(Location.THRU, "");
		shortDescription.put(Location.DUCK, "");
		shortDescription.put(Location.SEWER, "");
		shortDescription.put(Location.UPNOUT, "");
		shortDescription.put(Location.DIDIT, "");
		shortDescription.put(Location.PPASS, "");
		shortDescription.put(Location.PDROP, "");
		shortDescription.put(Location.TROLL, "");
		
		objectLocation.put(GameObjects.KEYS, Location.BUILDING);
		objectLocation.put(GameObjects.LAMP, Location.BUILDING);
		objectLocation.put(GameObjects.GRATE, Location.OUTSIDE);
		objectLocation.put(GameObjects.GRATE_, Location.INSIDE);
		objectLocation.put(GameObjects.CAGE, Location.THEVOID);
		objectLocation.put(GameObjects.ROD, Location.DEBRIS);
		objectLocation.put(GameObjects.ROD2, Location.THEVOID);
		objectLocation.put(GameObjects.TREADS, Location.THEVOID);
		objectLocation.put(GameObjects.TREADS_, Location.THEVOID);
		objectLocation.put(GameObjects.BIRD, Location.BIRD);
		objectLocation.put(GameObjects.DOOR, Location.THEVOID);
		objectLocation.put(GameObjects.PILLOW, Location.THEVOID);
		objectLocation.put(GameObjects.SNAKE, Location.THEVOID);
		objectLocation.put(GameObjects.CRYSTAL, Location.THEVOID);
		objectLocation.put(GameObjects.CRYSTAL_, Location.THEVOID);
		objectLocation.put(GameObjects.TABLET, Location.THEVOID);
		objectLocation.put(GameObjects.CLAM, Location.THEVOID);
		objectLocation.put(GameObjects.OYSTER, Location.THEVOID);
		objectLocation.put(GameObjects.MAG, Location.THEVOID);
		objectLocation.put(GameObjects.DWARF, Location.THEVOID);
		objectLocation.put(GameObjects.KNIFE, Location.THEVOID);
		objectLocation.put(GameObjects.FOOD, Location.BUILDING);
		objectLocation.put(GameObjects.BOTTLE, Location.BUILDING);
		objectLocation.put(GameObjects.WATER, Location.THEVOID);
		objectLocation.put(GameObjects.OIL, Location.THEVOID);
		objectLocation.put(GameObjects.MIRROR, Location.THEVOID);
		objectLocation.put(GameObjects.MIRROR_, Location.THEVOID);
		objectLocation.put(GameObjects.PLANT, Location.THEVOID);
		objectLocation.put(GameObjects.PLANT2, Location.THEVOID);
		objectLocation.put(GameObjects.PLANT2_, Location.THEVOID);
		objectLocation.put(GameObjects.STALACTITE, Location.THEVOID);
		objectLocation.put(GameObjects.SHADOW, Location.THEVOID);
		objectLocation.put(GameObjects.SHADOW_, Location.THEVOID);
		objectLocation.put(GameObjects.AXE, Location.THEVOID);
		objectLocation.put(GameObjects.ART, Location.THEVOID);
		objectLocation.put(GameObjects.PIRATE, Location.THEVOID);
		objectLocation.put(GameObjects.DRAGON, Location.THEVOID);
		objectLocation.put(GameObjects.DRAGON_, Location.THEVOID);
		objectLocation.put(GameObjects.BRIDGE, Location.THEVOID);
		objectLocation.put(GameObjects.BRIDGE_, Location.THEVOID);
		objectLocation.put(GameObjects.TROLL, Location.THEVOID);
		objectLocation.put(GameObjects.TROLL_, Location.THEVOID);
		objectLocation.put(GameObjects.TROLL2, Location.THEVOID);
		objectLocation.put(GameObjects.TROLL2_, Location.THEVOID);
		objectLocation.put(GameObjects.BEAR, Location.THEVOID);
		objectLocation.put(GameObjects.MESSAGE, Location.THEVOID);
		objectLocation.put(GameObjects.GEYSER, Location.THEVOID);
		objectLocation.put(GameObjects.PONY, Location.THEVOID);
		objectLocation.put(GameObjects.BATTERIES, Location.THEVOID);
		objectLocation.put(GameObjects.MOSS, Location.THEVOID);
		objectLocation.put(GameObjects.GOLD, Location.THEVOID);
		objectLocation.put(GameObjects.DIAMONDS, Location.THEVOID);
		objectLocation.put(GameObjects.SILVER, Location.THEVOID);
		objectLocation.put(GameObjects.JEWELS, Location.THEVOID);
		objectLocation.put(GameObjects.COINS, Location.THEVOID);
		objectLocation.put(GameObjects.CHEST, Location.THEVOID);
		objectLocation.put(GameObjects.EGGS, Location.THEVOID);
		objectLocation.put(GameObjects.TRIDENT, Location.THEVOID);
		objectLocation.put(GameObjects.VASE, Location.THEVOID);
		objectLocation.put(GameObjects.EMERALD, Location.THEVOID);
		objectLocation.put(GameObjects.PYRAMID, Location.THEVOID);
		objectLocation.put(GameObjects.PEARL, Location.THEVOID);
		objectLocation.put(GameObjects.RUG, Location.THEVOID);
		objectLocation.put(GameObjects.RUG_, Location.THEVOID);
		objectLocation.put(GameObjects.SPICES, Location.THEVOID);
		objectLocation.put(GameObjects.CHAIN, Location.THEVOID);
		
		
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
	
	public boolean isObject(String input)
	{
		boolean contains = false;
		if(objects.containsKey(input))
		{
			contains = true;
		}
		return contains;
	}
	
	public GameObjects whichObject(String input)
	{
		return objects.get(input);
	}
	
	public Location getObjectLocation(GameObjects object)
	{
		return objectLocation.get(object);
	}
	
	public ArrayList<GameObjects> objectsHere(Location here)
	{
		ArrayList<GameObjects> result = null;
		if(objectLocation.containsValue(here))
		{
			result = new ArrayList<GameObjects>();
			if(objectIsHere(GameObjects.KEYS, here))
			{
				result.add(GameObjects.KEYS);
			}
			else if(objectIsHere(GameObjects.LAMP, here))
			{
				result.add(GameObjects.LAMP);
			}
			else if(objectIsHere(GameObjects.GRATE, here))
			{
				result.add(GameObjects.GRATE);
			}
			else if(objectIsHere(GameObjects.GRATE_, here))
			{
				result.add(GameObjects.GRATE_);
			}
			else if(objectIsHere(GameObjects.CAGE, here))
			{
				result.add(GameObjects.CAGE);
			}
			else if(objectIsHere(GameObjects.ROD, here))
			{
				result.add(GameObjects.ROD);
			}
			else if(objectIsHere(GameObjects.ROD2, here))
			{
				result.add(GameObjects.ROD2);
			}
			else if(objectIsHere(GameObjects.TREADS, here))
			{
				result.add(GameObjects.LAMP);
			}
			else if(objectIsHere(GameObjects.TREADS_, here))
			{
				result.add(GameObjects.BIRD);
			}
			else if(objectIsHere(GameObjects.BIRD, here))
			{
				result.add(GameObjects.DOOR);
			}
			else if(objectIsHere(GameObjects.DOOR, here))
			{
				result.add(GameObjects.PILLOW);
			}
			else if(objectIsHere(GameObjects.PILLOW, here))
			{
				result.add(GameObjects.SNAKE);
			}
			else if(objectIsHere(GameObjects.SNAKE, here))
			{
				result.add(GameObjects.CRYSTAL);
			}
			else if(objectIsHere(GameObjects.CRYSTAL, here))
			{
				result.add(GameObjects.CRYSTAL_);
			}
			else if(objectIsHere(GameObjects.CRYSTAL_, here))
			{
				result.add(GameObjects.TABLET);
			}
			else if(objectIsHere(GameObjects.TABLET, here))
			{
				result.add(GameObjects.CLAM);
			}
			else if(objectIsHere(GameObjects.CLAM, here))
			{
				result.add(GameObjects.OYSTER);
			}
			else if(objectIsHere(GameObjects.OYSTER, here))
			{
				result.add(GameObjects.MAG);
			}
			else if(objectIsHere(GameObjects.MAG, here))
			{
				result.add(GameObjects.DWARF);
			}
			else if(objectIsHere(GameObjects.DWARF, here))
			{
				result.add(GameObjects.KNIFE);
			}
			else if(objectIsHere(GameObjects.KNIFE, here))
			{
				result.add(GameObjects.FOOD);
			}
			else if(objectIsHere(GameObjects.FOOD, here))
			{
				result.add(GameObjects.BOTTLE);
			}
			else if(objectIsHere(GameObjects.BOTTLE, here))
			{
				result.add(GameObjects.WATER);
			}
			else if(objectIsHere(GameObjects.WATER, here))
			{
				result.add(GameObjects.OIL);
			}
			else if(objectIsHere(GameObjects.OIL, here))
			{
				result.add(GameObjects.MIRROR);
			}
			else if(objectIsHere(GameObjects.MIRROR, here))
			{
				result.add(GameObjects.MIRROR_);
			}
			else if(objectIsHere(GameObjects.MIRROR_, here))
			{
				result.add(GameObjects.PLANT);
			}
			else if(objectIsHere(GameObjects.PLANT, here))
			{
				result.add(GameObjects.PLANT2);
			}
			else if(objectIsHere(GameObjects.PLANT2, here))
			{
				result.add(GameObjects.PLANT2_);
			}
			else if(objectIsHere(GameObjects.PLANT2_, here))
			{
				result.add(GameObjects.STALACTITE);
			}
			else if(objectIsHere(GameObjects.STALACTITE, here))
			{
				result.add(GameObjects.SHADOW);
			}
			else if(objectIsHere(GameObjects.SHADOW, here))
			{
				result.add(GameObjects.SHADOW_);
			}
			else if(objectIsHere(GameObjects.SHADOW_, here))
			{
				result.add(GameObjects.SHADOW_);
			}
			else if(objectIsHere(GameObjects.AXE, here))
			{
				result.add(GameObjects.AXE);
			}
			else if(objectIsHere(GameObjects.ART, here))
			{
				result.add(GameObjects.ART);
			}
			else if(objectIsHere(GameObjects.PIRATE, here))
			{
				result.add(GameObjects.PIRATE);
			}
			else if(objectIsHere(GameObjects.DRAGON, here))
			{
				result.add(GameObjects.DRAGON);
			}
			else if(objectIsHere(GameObjects.DRAGON_, here))
			{
				result.add(GameObjects.DRAGON_);
			}
			else if(objectIsHere(GameObjects.BRIDGE, here))
			{
				result.add(GameObjects.BRIDGE);
			}
			else if(objectIsHere(GameObjects.BRIDGE_, here))
			{
				result.add(GameObjects.BRIDGE_);
			}
			else if(objectIsHere(GameObjects.TROLL, here))
			{
				result.add(GameObjects.TROLL);
			}
			else if(objectIsHere(GameObjects.TROLL_, here))
			{
				result.add(GameObjects.TROLL_);
			}
			else if(objectIsHere(GameObjects.TROLL2, here))
			{
				result.add(GameObjects.TROLL2);
			}
			else if(objectIsHere(GameObjects.TROLL2_, here))
			{
				result.add(GameObjects.TROLL2_);
			}
			else if(objectIsHere(GameObjects.BEAR, here))
			{
				result.add(GameObjects.BEAR);
			}
			else if(objectIsHere(GameObjects.MESSAGE, here))
			{
				result.add(GameObjects.MESSAGE);
			}
			else if(objectIsHere(GameObjects.GEYSER, here))
			{
				result.add(GameObjects.GEYSER);
			}
			else if(objectIsHere(GameObjects.PONY, here))
			{
				result.add(GameObjects.PONY);
			}
			else if(objectIsHere(GameObjects.BATTERIES, here))
			{
				result.add(GameObjects.BATTERIES);
			}
			else if(objectIsHere(GameObjects.MOSS, here))
			{
				result.add(GameObjects.MOSS);
			}
			else if(objectIsHere(GameObjects.GOLD, here))
			{
				result.add(GameObjects.GOLD);
			}
			else if(objectIsHere(GameObjects.DIAMONDS, here))
			{
				result.add(GameObjects.DIAMONDS);
			}
			else if(objectIsHere(GameObjects.SILVER, here))
			{
				result.add(GameObjects.SILVER);
			}
			else if(objectIsHere(GameObjects.JEWELS, here))
			{
				result.add(GameObjects.JEWELS);
			}
			else if(objectIsHere(GameObjects.COINS, here))
			{
				result.add(GameObjects.COINS);
			}
			else if(objectIsHere(GameObjects.CHEST, here))
			{
				result.add(GameObjects.CHEST);
			}
			else if(objectIsHere(GameObjects.EGGS, here))
			{
				result.add(GameObjects.EGGS);
			}
			else if(objectIsHere(GameObjects.TRIDENT, here))
			{
				result.add(GameObjects.TRIDENT);
			}
			else if(objectIsHere(GameObjects.VASE, here))
			{
				result.add(GameObjects.VASE);
			}
			else if(objectIsHere(GameObjects.EMERALD, here))
			{
				result.add(GameObjects.EMERALD);
			}
			else if(objectIsHere(GameObjects.PYRAMID, here))
			{
				result.add(GameObjects.PYRAMID);
			}
			else if(objectIsHere(GameObjects.PEARL, here))
			{
				result.add(GameObjects.PEARL);
			}
			else if(objectIsHere(GameObjects.RUG, here))
			{
				result.add(GameObjects.RUG);
			}
			else if(objectIsHere(GameObjects.RUG_, here))
			{
				result.add(GameObjects.RUG_);
			}
			else if(objectIsHere(GameObjects.SPICES, here))
			{
				result.add(GameObjects.SPICES);
			}
			else if(objectIsHere(GameObjects.CHAIN, here))
			{
				result.add(GameObjects.CHAIN);
			}
	
			
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
		if(firstVisit.get(here) == true)
		{
			result = true;
		}
		return result;
	}
	
	public void visit(Location here, boolean canSee)
	{
		if(canSee)
		{
			firstVisit.replace(here, true);
		}
	}
	
	public String getDescription(Location here, boolean breif)
	{
		
		
		String description = null;
		if(!breif && longDescription.containsKey(here) && !beenHere(here))
		{
			description = longDescription.get(here);
			firstVisit.replace(here, true);
		}
		else
		{
			description = shortDescription.get(here);
		}
		return description;
	}
	
	public String getLongDescription(Location here)
	{
		String description = "I am not allowed to go into more detail.";
		if(longDescription.containsKey(here))
		{
			description = longDescription.get(here);
		}
		return description;
	}
	
}
