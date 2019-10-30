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
	
	public static final HashMap<String, KnownWord> KnownWords = new HashMap<String, KnownWord>()
	{{
		put("road", Movement.ROAD);
		put("hill", Movement.HILL);
		put("enter", Movement.ENTER);
		put("downs", Movement.DOWNSTREAM);
		put("upstr", Movement.UPSTREAM);
		put("fores", Movement.FOREST);
		put("forwa", Movement.FORWARD); 
		put("onwar", Movement.FORWARD);
		put("back", Movement.BACK); put("retre", Movement.BACK); put("retur", Movement.BACK);
		put("valle", Movement.VALLEY);
		put("stair", Movement.STAIRS);
		put("out", Movement.OUT); put("outsi", Movement.OUT); put("exit", Movement.OUT);	
		put("leave", Movement.OUT);
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
		put("e", Movement.EAST);
		put("east", Movement.EAST);
		put("w", Movement.WEST);
		put("west", Movement.WEST);
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
		put("slab", Movement.SLAB); 
		put("slabr", Movement.SLAB);
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
		put("main", Movement.OFFICE); 
		put("offic", Movement.OFFICE);
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
		put("silve", GameObjects.SILVER);
		put("bars", GameObjects.SILVER);
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
		put("take", ActionWords.TAKE); put("carry", ActionWords.TAKE); put("keep", ActionWords.TAKE); put("catch", ActionWords.TAKE); put("captu", ActionWords.TAKE);
		put("steal", ActionWords.TAKE); put("get", ActionWords.TAKE); put("tote", ActionWords.TAKE);
		put("drop", ActionWords.DROP); put("relea", ActionWords.DROP); put("free", ActionWords.DROP); put("disca", ActionWords.DROP); put("dump", ActionWords.DROP);
		put("open", ActionWords.OPEN); put("unloc", ActionWords.OPEN);
		put("close", ActionWords.CLOSE); put("lock", ActionWords.CLOSE);
		put("light", ActionWords.ON); put("on", ActionWords.ON);
		put("extin", ActionWords.OFF); put("off", ActionWords.OFF);
		put("wave", ActionWords.WAVE); put("shake", ActionWords.WAVE); put("swing", ActionWords.WAVE);
		put("calm", ActionWords.CALM); put("placa", ActionWords.CALM); put("tame", ActionWords.CALM);
		put("walk", ActionWords.GO); put("run", ActionWords.GO); put("trave", ActionWords.GO); put("go", ActionWords.GO); put("proce", ActionWords.GO); put("explo", ActionWords.GO);
		put("goto", ActionWords.GO); put("follo", ActionWords.GO); put("turn", ActionWords.GO);
		put("nothi", ActionWords.RELAX); put("absta", ActionWords.RELAX);
		put("wait", ActionWords.RELAX);
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
		put("pet", ActionWords.PET);

		
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
	
	public static ArrayList<GameObjects> objectsHere(Locations here)
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
					if(object == GameObjects.RUG_){ object = GameObjects.RUG; }
					if(AdventMain.ADVENT.found.get(object) != true)
					{
						AdventMain.ADVENT.updateTally();
						AdventMain.ADVENT.found.put(object, true);
					}
				}
			}
		}
		return result;
	}
	
}
