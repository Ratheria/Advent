package data;

import java.util.*;

public class Communication
{
    public static final String SYSTEM_ACTION = "SYSTEM";

    // Common Text
    public static final String EMPTY = "";
    public static final String OKAY = "Okay.";

    public static final String ALIKE_PASSAGES = "You are in a maze of twisty little passages, all alike.";
    public static final String ALIKE_T	= "Maze All Alike";
    public static final String DIFF_T = "Maze All Different";
    public static final String SECRET_CANYON = "Secret Canyon";
    public static final String SECRET_CANYON_DESC = "You're in a secret canyon that exits to the north and east.";
    public static final String DEAD_END = "Dead end.";
    public static final String DEAD_END_T = "Dead End";

    public static final String DONT_HAVE = "You are not carrying it!";
    public static final String NOTHING = "Nothing happens.";
    public static final String DONT_SEE_ANY = "I don't see any ";
    public static final String OKAY_BUT_HOW = "I'm game. Would you care to explain how?";
    public static final String CANT_BE_SERIOUS = "You can't be serious!";
    public static final String CAN_NOT_DO_THAT = "You can not do that.";

    public static final String ENTER_STREAM = "enter stream";
    public static final String WEST_STRING = "west";

    // Miscellaneous Text
    public static final String BOTTLE_FULL = "Your bottle is already full.";
    public static final String BIRD_ALONE = "Oh, leave the poor unhappy bird alone.";
    public static final String NO_KEYS = "You have no keys!";
    public static final String GROUND_IS_WET = "Your bottle is empty and the ground is wet.";
    public static final String HOM_PASSAGE	= "You have crawled through a very low wide passage parallel to and north of the Hall of Mists.\n";
    public static final String KNIVES_VANISH = "The dwarves' knives vanish as they strike the walls of the cave.";
    public static final String[] FEE_FIE_FOE = {"fee", "fie", "foe", "foo", "fum"};

    // Variable Dependant Text
    public static final String[] S_MESSAGES =
        {"You are obviously a rank amateur. Better luck next time.",
         "Your score qualifies you as a novice class adventurer.",
         "You have now achieved the rating 'Experienced Adventurer'.",
         "You may now consider yourself a 'Seasoned Adventurer'.",
         "You have reached 'Junior Master' status.",
         "Your score puts you in Master Adventurer Class C.",
         "Your score puts you in Master Adventurer Class B.",
         "Your score puts you in Master Adventurer Class A.",
         "All of Adventuredom gives tribute to you, Adventure Grandmaster!" };
    public static final String[] RES_OFFER =
        {"\n\nNow you've really done it! I'm out of orange smoke! You don't expect me to do a decent reincarnation without any orange smoke, do you?",
         "\n\nYou clumsy oaf, you've done it again! I don't know how long I can keep this up. Do you want me to try reincarnating you again?",
         "\n\nOh dear, you seem to have gotten yourself killed. I might be able to help you out, but I've never really done this before. Do you want me to try to reincarnate you?"};
    public static final String[] RES_MESSAGE =
        {"Okay, if you're so smart, do it yourself! I'm leaving!\n\n",
         "Okay, now where did I put my resurrection kit?....\n\t>POOF!<\nEverything disappears in a dense cloud of orange smoke.\n\n",
         "All right. But don't blame me if something goes wr......\n\t---POOF!!---\nYou are engulfed in a cloud of orange smoke. Coughing and gasping, you emerge from the smoke to find....\n\n"};
    public static final String[] DEATH_MESSAGES =
        {"\n\nYou fell into a pit and broke every bone in your body!",
         "\nThe resulting ruckus has awakened the Dwarves.\nThere are now several threatening little Dwarves in the room with you! Most of them throw knives at you! All of them get you!",
         "Just as you reach the other side, the bridge buckles beneath the weight of the bear, who was still following you around. You scrabble desperately for support, but as the bridge collapses you stumble back and fall into the chasm."};
    public static final String[] BATTERY_WARNING =
        {". You'd best start wrapping this up, unless you can find some fresh batteries. I seem to recall that there's a vending machine in the maze. Bring some coins with you.",
         ". You'd best go back for those batteries.",
         ", and you're out of spare batteries. You'd best start wrapping this up."};

    // TODO: is abstain missing?

    /**
     * Known word map for resolving user input.
     */
    public static final Map<String, KnownWord> knownWords = new HashMap<>()
    {{
        put("road"	, Movement.ROAD);
        put("hill"	, Movement.HILL);
        put("enter"	, Movement.ENTER);
        put("downs"	, Movement.DOWNSTREAM);
        put("upstr"	, Movement.UPSTREAM);
        put("fores"	, Movement.FOREST);
        put("forwa"	, Movement.FORWARD);		put("onwar" , Movement.FORWARD);
        put("back"	, Movement.BACK); 			put("retre" , Movement.BACK);           put("retur" , Movement.BACK);
        put("valle"	, Movement.VALLEY);
        put("stair"	, Movement.STAIRS);
        put("out"	, Movement.OUT); 			put("outsi" , Movement.OUT);            put("exit"  , Movement.OUT);		put("leave" , Movement.OUT);
        put("build"	, Movement.BUILDING); 		put("house" , Movement.BUILDING);
        put("gully"	, Movement.GULLY);
        put("strea"	, Movement.STREAM);
        put("rock"	, Movement.ROCK);
        put("bed"	, Movement.BED);
        put("crawl"	, Movement.CRAWL);
        put("cobbl"	, Movement.COBBLE);
        put("in"	, Movement.IN); 			put("insid" , Movement.IN);             put("inwar" , Movement.IN);
        put("surfa"	, Movement.SURFACE);
        put("nowhe"	, Movement.NOWHERE);
        put("dark"	, Movement.DARK);
        put("passa"	, Movement.PASSAGE); 		put("tunne" , Movement.PASSAGE);
        put("canyo"	, Movement.CANYON);
        put("akwar"	, Movement.AWKWARD);
        put("giant"	, Movement.GIANT);
        put("view"	, Movement.VIEW);
        put("up"	, Movement.UP); 			put("u"     , Movement.UP);             put("above" , Movement.UP);			put("acend" , Movement.UP);			put("upwar" , Movement.UP);
        put("down"	, Movement.DOWN); 			put("d"     , Movement.DOWN);           put("decen" , Movement.DOWN);		put("downw" , Movement.DOWN);
        put("pit"	, Movement.PIT);
        put("outdo"	, Movement.OUTDOORS);
        put("crack"	, Movement.CRACK);
        put("steps"	, Movement.STEPS);
        put("dome"	, Movement.DOME);
        put("left"	, Movement.LEFT);
        put("right"	, Movement.RIGHT);
        put("hall"	, Movement.HALL);
        put("jump"	, Movement.JUMP);
        put("barre" , Movement.BARREN);
        put("over"	, Movement.OVER);
        put("acros" , Movement.ACROSS);
        put("e"		, Movement.EAST); 			put("east"  , Movement.EAST);
        put("w"		, Movement.WEST); 			put("west"  , Movement.WEST);
        put("n"		, Movement.NORTH); 			put("north" , Movement.NORTH);
        put("s"		, Movement.SOUTH); 			put("south" , Movement.SOUTH);
        put("ne"	, Movement.NORTHEAST);
        put("se"	, Movement.SOUTHEAST);
        put("sw"	, Movement.SOUTHWEST);
        put("nw"	, Movement.NORTHWEST);
        put("debri"	, Movement.DEBRIS);
        put("hole"	, Movement.HOLE);
        put("wall"	, Movement.WALL);
        put("broke"	, Movement.BROKEN);
        put("y2"	, Movement.Y2);
        put("climb"	, Movement.CLIMB);
        put("floor"	, Movement.FLOOR);
        put("room"	, Movement.ROOM);
        put("slit"	, Movement.SLIT);
        put("slab"	, Movement.SLAB); 			put("slabr" , Movement.SLAB);
        put("xyzzy"	, Movement.XYZZY);
        put("depre"	, Movement.DEPRESSION);
        put("entra"	, Movement.ENTRANCE);
        put("plugh"	, Movement.PLUGH);
        put("secre"	, Movement.SECRET);
        put("cave"	, Movement.CAVE);
        put("cross"	, Movement.CROSS);
        put("bedqu"	, Movement.BEDQUILT);
        put("plove"	, Movement.PLOVER);
        put("orien"	, Movement.ORIENTAL);
        put("caver"	, Movement.CAVERN);
        put("shell"	, Movement.SHELL);
        put("main"	, Movement.OFFICE); 		put("offic" , Movement.OFFICE);
        put("reser"	, Movement.RESERVOIR);
        put("fork"	, Movement.FORK);


        put("all"	, GameObjects.ALL);
        put("key"	, GameObjects.KEYS);        put("keys"  , GameObjects.KEYS);
        put("lamp"	, GameObjects.LAMP);        put("lante" , GameObjects.LAMP);		put("headl" , GameObjects.LAMP);
        put("grate"	, GameObjects.GRATE);
        put("cage"	, GameObjects.CAGE);
        put("rod"	, GameObjects.ROD);
        put("bird"	, GameObjects.BIRD);
        put("door"	, GameObjects.DOOR);
        put("pillo"	, GameObjects.PILLOW);
        put("snake"	, GameObjects.SNAKE);
        put("fissu"	, GameObjects.CRYSTAL);
        put("table"	, GameObjects.TABLET);
        put("clam"	, GameObjects.CLAM);
        put("oyste"	, GameObjects.OYSTER);
        put("magaz"	, GameObjects.MAG);         put("issue" , GameObjects.MAG);			put("spelu" , GameObjects.MAG);			put("\"spel", GameObjects.MAG);
        put("dwarf"	, GameObjects.DWARF);       put("dwarv" , GameObjects.DWARF);
        put("knife"	, GameObjects.KNIFE);       put("knive" , GameObjects.KNIFE);
        put("food"	, GameObjects.FOOD);        put("ratio" , GameObjects.FOOD);
        put("bottl"	, GameObjects.BOTTLE);      put("jar"   , GameObjects.BOTTLE);
        put("water"	, GameObjects.WATER);       put("h2o"   , GameObjects.WATER);
        put("oil"	, GameObjects.OIL);
        put("mirro"	, GameObjects.MIRROR);
        put("plant"	, GameObjects.PLANT);       put("beans" , GameObjects.PLANT);
        put("stala"	, GameObjects.STALACTITE);
        put("shado"	, GameObjects.SHADOW);      put("figur" , GameObjects.SHADOW);
        put("axe"	, GameObjects.AXE);
        put("drawi"	, GameObjects.ART);
        put("pirat"	, GameObjects.PIRATE);
        put("drago"	, GameObjects.DRAGON);
        put("chasm"	, GameObjects.BRIDGE);
        put("troll"	, GameObjects.TROLL);
        put("bear"	, GameObjects.BEAR);
        put("messa"	, GameObjects.MESSAGE);
        put("volca"	, GameObjects.GEYSER);      put("geyse" , GameObjects.GEYSER);
        put("vendi"	, GameObjects.PONY);        put("machi" , GameObjects.PONY);
        put("batte"	, GameObjects.BATTERIES);
        put("moss"	, GameObjects.MOSS);        put("carpe" , GameObjects.MOSS);

        put("gold"	, GameObjects.GOLD);        put("nugge" , GameObjects.GOLD);
        put("diamo"	, GameObjects.DIAMONDS);
        put("silve"	, GameObjects.SILVER);      put("bars"  , GameObjects.SILVER);
        put("jewel"	, GameObjects.JEWELS);
        put("coins"	, GameObjects.COINS);
        put("chest"	, GameObjects.CHEST);       put("box"   , GameObjects.CHEST);		put("treas" , GameObjects.CHEST);
        put("eggs"	, GameObjects.EGGS);        put("egg"   , GameObjects.EGGS);		put("nest"  , GameObjects.EGGS);
        put("tride"	, GameObjects.TRIDENT);
        put("ming"	, GameObjects.VASE);        put("vase"  , GameObjects.VASE);		put("shard" , GameObjects.VASE);		put("potte" , GameObjects.VASE);
        put("emera"	, GameObjects.EMERALD);
        put("plati"	, GameObjects.PYRAMID);     put("pyram" , GameObjects.PYRAMID);
        put("pearl"	, GameObjects.PEARL);
        put("rug"	, GameObjects.RUG);
        put("spice"	, GameObjects.SPICES);
        put("chain"	, GameObjects.CHAIN);


        put("look"	, ActionWords.LOOK);        put("descr" , ActionWords.LOOK);		put("exami" , ActionWords.LOOK);
        put("take"	, ActionWords.TAKE);        put("carry" , ActionWords.TAKE);		put("keep"  , ActionWords.TAKE);		put("catch" , ActionWords.TAKE);
        put("captu" , ActionWords.TAKE);        put("steal" , ActionWords.TAKE);		put("get"   , ActionWords.TAKE);		put("tote"  , ActionWords.TAKE);
        put("drop"	, ActionWords.DROP);        put("relea" , ActionWords.DROP);		put("free"  , ActionWords.DROP);		put("disca" , ActionWords.DROP);		put("dump"  , ActionWords.DROP);
        put("open"	, ActionWords.OPEN);        put("unloc" , ActionWords.OPEN);
        put("close"	, ActionWords.CLOSE);       put("lock"  , ActionWords.CLOSE);
        put("light"	, ActionWords.ON);          put("on"    , ActionWords.ON);
        put("extin"	, ActionWords.OFF);         put("off"   , ActionWords.OFF);
        put("wave"	, ActionWords.WAVE);        put("shake" , ActionWords.WAVE);		put("swing" , ActionWords.WAVE);
        put("calm"	, ActionWords.CALM);        put("placa" , ActionWords.CALM);		put("tame"  , ActionWords.CALM);
        put("walk"	, ActionWords.GO);          put("run"   , ActionWords.GO);			put("trave" , ActionWords.GO);			put("go"    , ActionWords.GO);			put("proce" , ActionWords.GO);
        put("explo" , ActionWords.GO);          put("goto"  , ActionWords.GO);			put("follo" , ActionWords.GO);			put("turn"  , ActionWords.GO);
        put("nothi"	, ActionWords.RELAX);       put("absta" , ActionWords.RELAX);		put("wait"  , ActionWords.RELAX);
        put("pour"	, ActionWords.POUR);
        put("eat"	, ActionWords.EAT);         put("devou" , ActionWords.EAT);
        put("drink"	, ActionWords.DRINK);
        put("rub"	, ActionWords.RUB);
        put("throw"	, ActionWords.TOSS);        put("toss"  , ActionWords.TOSS);
        put("wake"	, ActionWords.WAKE);        put("distu" , ActionWords.WAKE);
        put("feed"	, ActionWords.FEED);
        put("fill"	, ActionWords.FILL);
        put("break"	, ActionWords.BREAK);       put("smash" , ActionWords.BREAK);		put("shatt" , ActionWords.BREAK);
        put("blast"	, ActionWords.BLAST);       put("deton" , ActionWords.BLAST);		put("ignit" , ActionWords.BLAST);		put("blowu" , ActionWords.BLAST);
        put("attac"	, ActionWords.KILL);        put("kill"  , ActionWords.KILL);		put("fight" , ActionWords.KILL);		put("hit"   , ActionWords.KILL);		put("strik" , ActionWords.KILL);		put("slay"  , ActionWords.KILL);
        put("say"	, ActionWords.SAY);         put("chant" , ActionWords.SAY);			put("sing"  , ActionWords.SAY); 		put("utter" , ActionWords.SAY);			put("mumbl" , ActionWords.SAY);
        put("read"	, ActionWords.READ);        put("perus" , ActionWords.READ);
        put("fee"	, ActionWords.FEEFIE);      put("fie"   , ActionWords.FEEFIE);		put("foe"   , ActionWords.FEEFIE);		put("foo"   , ActionWords.FEEFIE);		put("fum"   , ActionWords.FEEFIE);
        put("brief"	, ActionWords.BRIEF);
        put("verbo"	, ActionWords.VERBOSE);
        put("find"  , ActionWords.FIND);        put("where" , ActionWords.FIND);
        put("inven" , ActionWords.INVENTORY);
        put("score" , ActionWords.SCORE);
        put("quit"  , ActionWords.QUIT);

        put("abra"  , MessageWords.MAGIC);      put("abrac" , MessageWords.MAGIC);		put("opens" , MessageWords.MAGIC);		put("sesam" , MessageWords.MAGIC);
        put("shaza" , MessageWords.MAGIC);      put("hocus" , MessageWords.MAGIC);		put("pocus" , MessageWords.MAGIC);
        put("help"  , MessageWords.HELP);       put("?"     , MessageWords.HELP);
        put("tree"  , MessageWords.TREE);       put("trees" , MessageWords.TREE);
        put("dig"   , MessageWords.DIG);        put("excav" , MessageWords.DIG);
        put("lost"  , MessageWords.LOST);
        put("mist"  , MessageWords.MIST);
        put("fuck"  , MessageWords.CUSS);       put("shit"  , MessageWords.CUSS);		put("damn"  , MessageWords.CUSS);		put("dick"  , MessageWords.CUSS);
        put("stop"	, MessageWords.STOP);
        put("info"  , MessageWords.INFO);       put("infor" , MessageWords.INFO);
        put("swim"  , MessageWords.SWIM);
        put("denni" , MessageWords.DENNIS);
    }};

    private Communication()
    { }
}
