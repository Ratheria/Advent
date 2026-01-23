package data;

import java.util.*;

import static controller.AdventMain.*;

public enum GameObjects implements KnownWord
{
    NOTHING, ALL,
    KEYS	( true, Locations.BUILDING,		new String[] {"\n\t\tSet of Keys", 			"\n\tThere are some keys on the ground here."}),
    LAMP	( true, Locations.BUILDING,		new String[] {"\n\t\tBrass Lantern", 		"\n\tThere is a lamp shining nearby.", "\n\tThere is a shiny brass lamp nearby."}),
    GRATE	(       Locations.OUTSIDE, 		new String[] {"\n\tThe grate is open.", 	"\n\tThe grate is locked."}), GRATE_(Locations.INSIDE, null),
    CAGE	( true, Locations.COBBLES,		new String[] {"\n\t\tWicker Cage", 			"\n\tThere is a small wicker cage discarded nearby."}),
    ROD		( true, Locations.DEBRIS,		new String[] {"\n\t\tBlack Rod", 			"\n\tA three foot black rod with a rusty star on an end lies nearby."}),
    ROD2	( true, Locations.VOID,         new String[] {"\n\t\tBlack Rod",            "\n\tA three foot black rod with a rusty mark on an end lies nearby."}),
    TREADS	(       Locations.EASTMIST,		new String[] {"\n\tRough stone steps lead up the dome."}),
    TREADS_	(       Locations.SMALLPIT,		new String[] {"\n\tRough stone steps lead down the pit."}),
    BIRD	( true, Locations.BIRD,			new String[] {"\n\t\tLittle Bird in Cage", 	"\n\tThere is a little bird in the cage.", "\n\tA cheerful little bird is sitting here singing."}),
    DOOR	(       Locations.IMMENSE,		new String[] {"\n\tThe way north is barred by a massive, rusty, iron door.", "\n\tThe way north leads through a massive, rusty, iron door."}),
    PILLOW	( true, Locations.SOFT,			new String[] {"\n\t\tVelvet Pillow", 		"\n\tA small velvet pillow lies on the floor."}),
    SNAKE	(       Locations.MTKHALL,      new String[] {"\n\tA huge green fierce snake bars the way!"}),
    CRYSTAL	(       Locations.VOID,         new String[] {"\n\tA crystal bridge now spans the fissure."}), CRYSTAL_(Locations.VOID, null),
    TABLET	(		Locations.DROOM,		new String[] {"\n\tA massive stone tablet embedded in the wall reads:\n\t\"CONGRATULATIONS ON BRINGING LIGHT TO THE DARK-ROOM!\""}),
    CLAM	( true, Locations.SHELL,		new String[] {"\n\t\tGiant Clam >GRUNT!<", 	"\n\tThere is an enormous clam here with its shell tightly closed."}),
    OYSTER	( true, Locations.VOID,         new String[] {"\n\t\tGiant Oyster >GROAN!<", "\n\tThere is an enormous oyster here with its shell tightly closed."}),
    MAG		( true, Locations.ANTE,			new String[] {"\n\t\t\"Spelunker Today\"", 	"\n\tThere are a few recent issues of \"Spelunker Today\" magazine here."}),
    DWARF, KNIFE,
    FOOD	( true, Locations.BUILDING,		new String[] {"\n\t\tTasty Food", 			"\n\tThere is tasty food here."}),
    BOTTLE	( true, Locations.BUILDING,		new String[] {"\n\t\tSmall Bottle", 		"\n\t\tBottle of Water", "\n\t\tBottle of Oil", "\n\tThere is an empty bottle here.", "\n\tThere is a bottle of water here.", "\n\tThere is a bottle of oil here."}),
    WATER, OIL,
    MIRROR	(		Locations.MIRROR,       null), MIRROR_,
    PLANT	(		Locations.WESTPIT,		new String[] {"\n\tThere is a tiny little plant in the pit, murmuring \"Water, water, ...\"", "\n\tThere is a 12-foot-tall beanstalk stretching up out of the pit, bellowing \"Water!! Water!!\"", "\n\tThere is a gigantic beanstalk stretching all the way up to the hole.", ""}),
    PLANT2	(		Locations.WEST2PIT,		new String[] {"", "\n\tThe top of a 12-foot-tall beanstalk is poking up out of the west pit.", "\n\tThere is a huge beanstalk growing out of the west pit up to the hole.", ""}), PLANT2_(Locations.EAST2PIT,	null),
    STALACTITE(		Locations.STALACTITE,	new String[] {}),
    SHADOW	(       Locations.EWINDOW,      new String[] {"\n\tThe shadowy figure seems to be trying to attract your attention."}), SHADOW_(Locations.WWINDOW, null),
    AXE		( true, Locations.VOID,         new String[] {"\n\t\tDwarf's Axe", "\n\tThere is a little axe lying beside the bear.", "\n\tThere is a little axe here."}),
    ART		(		Locations.ORIENTAL,     null),
    PIRATE,
    DRAGON	(		Locations.SCAN1,		new String[] {"\n\tA huge green fierce dragon bars the way!", "\n\tThe body of a huge green dead dragon is lying off to one side."}), DRAGON_(Locations.SCAN3, null),
    BRIDGE	(		Locations.SWSIDE,		new String[] {"\n\tA rickety wooden bridge extends across the chasm, vanishing into the mist. \n\tA sign posted on the bridge reads: \n\t\t\"Stop!  Pay Troll!\"", "\n\tThe wreckage of a bridge (and a dead bear) can be seen at the bottom of the chasm."}), BRIDGE_(Locations.NESIDE, null),
    TROLL	(		Locations.SWSIDE,		new String[] {"\n\tA burly troll stands by the bridge and insists you throw him a treasure before you may cross."}), TROLL_(Locations.NESIDE, null),
    TROLL2	(       Locations.VOID,         new String[] {"\n\tThe troll is nowhere to be seen."}), TROLL2_(Locations.VOID, null),
    BEAR	(		Locations.BARR,			new String[] {"\n\tThere is a ferocious cave bear eyeing you from the far end of the room!", "\n\tThere is a gentle cave bear sitting placidly in one corner.", "\n\nYou are being followed by a very large, tame bear.", "", "\n\tThere is a contented-looking bear wandering about nearby."}),
    MESSAGE	(       Locations.VOID,         new String[] {"\n\tThere is a message scrawled in the dust in a flowery script, reading:\n\t\t\"This is not the maze where the pirate leaves his treasure chest.\""}),
    GEYSER	(		Locations.VIEW,         null),
    PONY	(		Locations.PONY,			new String[] {"\n\tThere is a massive vending machine here.  \nThe instructions on it read:\n\t\t\"Drop coins here to receive fresh batteries.\""}),
    BATTERIES(      Locations.VOID,         new String[] {"\n\tThere are fresh batteries here.", "\n\tSome worn-out batteries have been discarded nearby."}),
    MOSS	(		Locations.SOFT,         null),

    GOLD	( true, Locations.NUGGET,		new String[] {"\n\t\tLarge Gold Nugget", 	"\n\tThere is a large sparkling nugget of gold here!"}),
    DIAMONDS( true, Locations.WESTFISSURE,	new String[] {"\n\t\tSeveral Diamonds", 	"\n\tThere are diamonds here!"}),
    SILVER	( true, Locations.NS,			new String[] {"\n\t\tBars of Silver", 		"\n\tThere are bars of silver here!"}),
    JEWELS	( true, Locations.SOUTH,		new String[] {"\n\t\tPrecious Jewelry", 	"\n\tThere is precious jewelry here!"}),
    COINS	( true, Locations.WEST,			new String[] {"\n\t\tRare Coins", 			"\n\tThere are many coins here!"}),
    CHEST	( true, Locations.VOID,         new String[] {"\n\t\tTreasure Chest",       "\n\tThe pirate's treasure chest is here!"}),
    EGGS	( true, Locations.GIANT,		new String[] {"\n\t\tGolden Eggs", 			"\n\tThere is a large nest here, full of golden eggs!"}),
    TRIDENT	( true, Locations.FALLS,		new String[] {"\n\t\tJeweled Trident", 		"\n\tThere is a jewel-encrusted trident here!"}),
    VASE	( true, Locations.ORIENTAL,		new String[] {"\n\t\tMing Vase", 			"\n\tThe vase is now resting, delicately, on a velvet pillow.", "\n\tThere is a delicate, precious, Ming vase here!", "\n\tThe floor is littered with worthless shards of pottery."}),
    EMERALD	( true, Locations.PROOM,		new String[] {"\n\t\tEgg-Sized Emerald", 	"\n\tThere is an emerald here the size of a plover's egg!"}),
    PYRAMID	( true, Locations.DROOM,		new String[] {"\n\t\tPlatinum Pyramid", 	"\n\tThere is a platinum pyramid here, 8 inches on a side!"}),
    PEARL	( true, Locations.VOID,         new String[] {"\n\t\tGlistening Pearl",     "\n\tOff to one side lies a glistening pearl!"}),
    RUG		( true, Locations.SCAN1,		new String[] {"\n\t\tPersian Rug", 			"\n\tThe dragon is sprawled out on a persian rug!", "\n\tThere is a persian rug spread out on the floor!"}), RUG_(true, Locations.SCAN3,	null),
    SPICES	( true, Locations.CHAMBER,		new String[] {"\n\t\tRare Spices", 			"\n\tThere are rare spices here!"}),
    CHAIN	( true, Locations.BARR,			new String[] {"\n\t\tGolden Chain", 		"\n\tThe bear is locked to the wall with a golden chain!", "\n\tThere is a golden chain lying in a heap on the floor!", "\n\tThere is a golden chain locked to the wall!"});

    public final boolean mobile;
    public Locations location;
    public String[] descriptions;

    static final GameObjects[] objects = GameObjects.values();

    GameObjects()
    {
        this.mobile = false;
        this.location = Locations.VOID;
        this.descriptions = null;
    }

    GameObjects(Locations location, String[] descriptions)
    {
        this.mobile = false;
        this.location = location;
        this.descriptions = descriptions;
    }

    GameObjects(boolean mobile, Locations location, String[] descriptions)
    {
        this.mobile = mobile;
        this.location = location;
        this.descriptions = descriptions;
    }

    public static boolean isTreasure(GameObjects thisThing)
    {
        return thisThing.ordinal() > GameObjects.MOSS.ordinal();
    }

    public static boolean isLesserTreasure(GameObjects thisThing)
    {
        return isTreasure(thisThing) && thisThing.ordinal() < GameObjects.CHEST.ordinal();
    }

    public static Locations[] getObjectLocations()
    {
        Locations[] locations = new Locations[GameObjects.values().length];
        for (int i = 0; i < GameObjects.values().length; i++)
        {
            locations[i] = GameObjects.values()[i].location;
        }
        return locations;
    }

    public static void loadObjectLocations(Locations[] locations)
    {
        for (int i = 0; i < GameObjects.values().length; i++)
        {
            GameObjects.values()[i].location = locations[i];
        }
    }

    static String getItemDescription(Locations location, GameObjects object)
    {
        String output = Communication.EMPTY;

        boolean inHand = location == Locations.INHAND;
        boolean[] endGameStates = advent.endGameObjectsStates;
        String[] descriptions = object.descriptions;

        switch(object)
        {
            case BOTTLE		: if(!endGameStates[0]) { output = descriptions[
                advent.getStateOfBottle() + (inHand ? 0 : 3)];  						}	        break;
            case LAMP		: if(!endGameStates[1]) { output = (descriptions[(inHand ? 0 : ((advent.isLampLit()) ? 1 : 2))]); 						}	        break;
            case PILLOW		: if(!endGameStates[2]) { output = descriptions[((inHand) ? 0 : 1)];  													}	        break;
            case OYSTER		: if(!endGameStates[3]) { output = descriptions[((inHand) ? 0 : 1)];  													}	        break;
            case GRATE_		:
            case GRATE		: if(!endGameStates[4]) { output = GRATE.descriptions[((advent.isGrateUnlocked()) ? 0 : 1)]; 							}	        break;
            case CAGE		: if(!endGameStates[5]) { output = descriptions[((inHand) ? 0 : 1)]; 													}	        break;
            case BIRD		: if(!endGameStates[6]) { output = (descriptions[(inHand ? 0 : ((advent.isBirdInCage()) ? 1 : 2))]); 					}	        break;
            case SNAKE		: if(!endGameStates[7]) { output = descriptions[0];  																	}	        break;
            case ROD		: if(!endGameStates[8]) { output = descriptions[((inHand) ? 0 : 1)]; 													}           break;
            case ROD2		: if(!endGameStates[9]) { output = descriptions[((inHand) ? 0 : 1)]; 													}	        break;
            case DOOR		: 						  output = descriptions[((!advent.doorHasBeenOiled()) ? 0 : 1)]; 									        break;
            case VASE		:						  output = descriptions[(advent.isVaseBroken() ? 3 : (inHand ? 0 : (PILLOW.location==location ? 1 : 2)))];	break;
            case MAG		: 						  output = descriptions[((inHand) ? 0 : 1)]; 														        break;
            case AXE		:						  output = descriptions[(inHand ? 0 : (advent.isBearAxe() ? 1 : 2))]; 							            break;
            case BEAR		: 					 	  output = descriptions[advent.getStateOfBear()]; 												            break;
            case BATTERIES	: 						  output = descriptions[(advent.getSpareBatteries() == 1) ? 0 : 1]; 						                break;
            case CHAIN		: 						  output = descriptions[inHand ? 0 : advent.getStateOfChain() + 1]; 							            break;
            case PLANT		: 						  output = descriptions[advent.getStateOfPlant()]; 													        break;

            case PLANT2_,   PLANT2  : 								        output = PLANT2.descriptions[advent.getStateOfPlant()]; 					break;
            case DRAGON_,   DRAGON	: 								        output = DRAGON.descriptions[advent.isDragonAlive() ? 0 : 1]; 				break;
            case RUG_,      RUG		:								        output = RUG.descriptions[inHand ? 0 : (advent.isDragonAlive() ? 1 : 2)]; 	break;
            case TREADS_,   TREADS	: if(!advent.isGoldInInventory())	{output = descriptions[0];  											}	    break;
            case CRYSTAL_,  CRYSTAL	: if(!advent.isCrystalBridge())		{output = CRYSTAL.descriptions[0];  									}	    break;
            case BRIDGE_,   BRIDGE	: 								        output = BRIDGE.descriptions[!advent.isCollapse() ? 0 : 1]; 				break;
            case SHADOW_,   SHADOW	:								        output = SHADOW.descriptions[0]; 											break;
            case TROLL_,    TROLL	: 								        output = TROLL.descriptions[0]; 											break;
            case TROLL2_,   TROLL2	: 								        output = TROLL2.descriptions[0]; 											break;

            default	: if(descriptions != null)    {	output = descriptions[((inHand || descriptions.length == 1) ? 0 : 1)]; } break;
        }
        return output;
    }

    public static List<GameObjects> getObjectsHere(Locations here)
    {
        ArrayList<GameObjects> result = new ArrayList<>();
        for (GameObjects object : GameObjects.values())
        {
            if(object.location == here)
            {
                result.add(object);
            }
        }
        return result;
    }

    public static String listItemsHere(Locations here)
    {
        StringBuilder output = new StringBuilder();
        List<GameObjects> objects = getObjectsHere(here);
        if(!objects.isEmpty())
        {
            for (GameObjects thing : objects)
            {
                output.append(getItemDescription(here, thing));
                advent.updateFoundTreasure(thing);
            }
        }
        return output.toString();
    }
}