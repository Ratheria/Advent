/**
 *	@author Ariana Fairbanks
 */

package model;

import java.util.ArrayList;
import java.util.HashMap;

import controller.AdventControl;

public class HashMaps 
{
	private HashMap<String, Movement> movement = new HashMap<String, Movement>();
	private HashMap<String, GameObjects> objects = new HashMap<String, GameObjects>();
	private HashMap<String, ActionWords> actions = new HashMap<String, ActionWords>();
	private HashMap<String, MessageWords> mwords = new HashMap<String, MessageWords>();
	public HashMap<GameObjects, Boolean> found = new HashMap<GameObjects, Boolean>();
	public HashMap<GameObjects, Location> objectLocation = new HashMap<GameObjects, Location>();
	
	public HashMaps()
	{
		setUpHashMaps();
	}
	
	public void setUpHashMaps()
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
		
		objects.put("all", GameObjects.ALL);
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
		objects.put("pearl", GameObjects.PEARL);
		objects.put("rug", GameObjects.RUG);
		objects.put("spice", GameObjects.SPICES);
		objects.put("chain", GameObjects.CHAIN);
		
		actions.put("look", ActionWords.LOOK);
		actions.put("descr", ActionWords.LOOK);
		actions.put("exami", ActionWords.LOOK);
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
		actions.put("absta", ActionWords.RELAX);
		actions.put("wait", ActionWords.RELAX);
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
		objectLocation.put(GameObjects.BEAR, Location.BARR);
		objectLocation.put(GameObjects.MESSAGE, Location.THEVOID);
		objectLocation.put(GameObjects.GEYSER, Location.VIEW);
		objectLocation.put(GameObjects.PONY, Location.PONY);
		objectLocation.put(GameObjects.BATTERIES, Location.THEVOID);
		objectLocation.put(GameObjects.MOSS, Location.SOFT);
		
		objectLocation.put(GameObjects.GOLD, Location.NUGGET);
		objectLocation.put(GameObjects.DIAMONDS, Location.WESTFISSURE);
		objectLocation.put(GameObjects.SILVER, Location.NS);
		objectLocation.put(GameObjects.JEWELS, Location.SOUTH);
		objectLocation.put(GameObjects.COINS, Location.WEST);
		objectLocation.put(GameObjects.CHEST, Location.THEVOID);
		objectLocation.put(GameObjects.EGGS, Location.GIANT);
		objectLocation.put(GameObjects.TRIDENT, Location.FALLS);
		objectLocation.put(GameObjects.VASE, Location.ORIENTAL);
		objectLocation.put(GameObjects.EMERALD, Location.PROOM);
		objectLocation.put(GameObjects.PYRAMID, Location.DROOM);
		objectLocation.put(GameObjects.PEARL, Location.THEVOID);
		objectLocation.put(GameObjects.RUG, Location.SCAN1);
		objectLocation.put(GameObjects.RUG_, Location.SCAN3);
		objectLocation.put(GameObjects.SPICES, Location.CHAMBER);
		objectLocation.put(GameObjects.CHAIN, Location.BARR);
	}
	
	public boolean isMovement(String input)
	{
		boolean contains = false;
		if(movement.containsKey(input))
		{	contains = true;	}
		return contains;
	}
	
	public Movement whichMovement(String input)
	{	return movement.get(input);	}
	
	public boolean isAction(String input)
	{
		boolean contains = false;
		if(actions.containsKey(input))
		{	contains = true;	}
		return contains;
	}
	
	public ActionWords whichAction(String input)
	{	return actions.get(input);	}
	
	public boolean isMessage(String input)
	{
		boolean contains = false;
		if(mwords.containsKey(input))
		{	contains = true;	}
		return contains;
	}
	
	public MessageWords whichMessage(String input)
	{	return mwords.get(input);	}
	
	public boolean isObject(String input)
	{
		boolean contains = false;
		if(objects.containsKey(input))
		{	contains = true;	}
		return contains;
	}
	
	public boolean isObject(Object input)
	{	return objects.containsValue(input);	}
	
	public GameObjects whichObject(String input)
	{
		GameObjects result = GameObjects.NOTHING;
		if(objects.containsKey(input))
		{	result = objects.get(input);	}
		return result;
	}
	
	public Location getObjectLocation(GameObjects object)
	{
		Location result = Location.THEVOID;
		if(objectLocation.containsKey(object))
		{	result = objectLocation.get(object);	}
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
		
		if(!(result == null))
		{
			for(GameObjects object : result)
			{
				if(found.containsKey(object))
				{
					if(found.get(object) != true)
					{
						AdventControl.updateTally();
						found.put(object, true);
					}
				}
				else if(object == GameObjects.RUG_)
				{
					if(found.get(GameObjects.RUG) != true)
					{
						AdventControl.updateTally();
						found.put(GameObjects.RUG, true);
					}
				}
			}
		}
		return result;
	}
	
	public boolean objectIsHere(GameObjects object, Location here)
	{	return getObjectLocation(object) == here;	}
	
	public void takeObject(GameObjects thing)
	{	objectLocation.put(thing, Location.INHAND);	}
	
	public void voidObject(GameObjects thing)
	{	objectLocation.put(thing, Location.THEVOID);	}
	
	public void dropObject(GameObjects thing, Location here)
	{	objectLocation.put(thing, here);	}
	
	public boolean haveIFound(GameObjects thing)
	{	return found.get(thing);	}
	
	public void wasFound(GameObjects thing)
	{	found.put(thing, true);	}
	
}
