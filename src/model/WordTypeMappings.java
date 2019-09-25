/**
 *	@author Ariana Fairbanks
 */

package model;

import java.util.ArrayList;
import java.util.HashMap;

import controller.AdventGame;
import controller.AdventMain;

public class WordTypeMappings 
{
	private HashMap<String, Movement> movement = new HashMap<String, Movement>();
	private HashMap<String, GameObjects> objects = new HashMap<String, GameObjects>();
	private HashMap<String, ActionWords> actions = new HashMap<String, ActionWords>();
	private HashMap<String, MessageWords> mwords = new HashMap<String, MessageWords>();
	private HashMap<String, YesAndNo> answers = new HashMap<String, YesAndNo>();
	
	public WordTypeMappings()
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
		actions.put("pet", ActionWords.PET);
		
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
		
		answers.put("yes", YesAndNo.YES);
		answers.put("y", YesAndNo.YES);
		answers.put("yeah", YesAndNo.YES);
		answers.put("okay", YesAndNo.YES);
		answers.put("no", YesAndNo.NO);
		answers.put("n", YesAndNo.NO);
		answers.put("nah", YesAndNo.NO);
		answers.put("nope", YesAndNo.NO);
		answers.put("maybe", YesAndNo.MAYBE);
	}
	
	public boolean isMovement(String input)
	{	return movement.containsKey(input);	}
	
	public Movement whichMovement(String input)
	{	return movement.get(input);	}
	
	public boolean isAction(String input)
	{	return actions.containsKey(input);	}
	
	public ActionWords whichAction(String input)
	{	return actions.get(input);	}
	
	public boolean isMessage(String input)
	{	return mwords.containsKey(input);	}
	
	public MessageWords whichMessage(String input)
	{	return mwords.get(input);	}
	
	public boolean isObject(String input)
	{	return objects.containsKey(input);	}
	
	public boolean isObject(Object input)
	{	return objects.containsValue(input);	}
	
	public GameObjects whichObject(String input)
	{
		GameObjects result = GameObjects.NOTHING;
		if(objects.containsKey(input))
		{	result = objects.get(input);	}
		return result;
	}
	
	public ArrayList<GameObjects> objectsHere(Locations here)
	{
		ArrayList<GameObjects> result = null;
		for(GameObjects object : GameObjects.values())
		{
			if(object.location == here)
			{
				if(result == null) {result = new ArrayList<GameObjects>();}
				result.add(object);
				if(AdventMain.ADVENT.found.containsKey(object))
				{
					if(AdventMain.ADVENT.found.get(object) != true)
					{
						AdventMain.ADVENT.updateTally();
						AdventMain.ADVENT.found.put(object, true);
					}
				}
				else if(object == GameObjects.RUG_)
				{
					if(AdventMain.ADVENT.found.get(GameObjects.RUG) != true)
					{
						AdventMain.ADVENT.updateTally();
						AdventMain.ADVENT.found.put(GameObjects.RUG, true);
					}
				}
			}
		}
		return result;
	}
	
}
