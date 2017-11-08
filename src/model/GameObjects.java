/**
 *	@author Ariana Fairbanks
 */

package model;

import java.util.ArrayList;

public enum GameObjects 
{

	NOTHING, ALL, KEYS, LAMP, GRATE, GRATE_, CAGE, ROD, ROD2,
	TREADS, TREADS_, BIRD, DOOR, PILLOW, SNAKE, CRYSTAL, 
	CRYSTAL_, TABLET, CLAM, OYSTER, MAG, DWARF, KNIFE, 
	FOOD, BOTTLE, WATER, OIL, MIRROR, MIRROR_, PLANT,
	PLANT2, PLANT2_, STALACTITE, SHADOW, SHADOW_, AXE, 
	ART, PIRATE, DRAGON, DRAGON_, BRIDGE, BRIDGE_, TROLL, 
	TROLL_, TROLL2, TROLL2_, BEAR, MESSAGE, GEYSER, PONY, 
	BATTERIES, MOSS, GOLD, DIAMONDS, SILVER, JEWELS, COINS, 
	CHEST, EGGS, TRIDENT, VASE, EMERALD, PYRAMID, PEARL, 
	RUG, RUG_, SPICES, CHAIN;

	private ArrayList<GameObjects> mobileObjects = new ArrayList<GameObjects>();
	
	public void setUp()
	{
		mobileObjects.add(KEYS);
		mobileObjects.add(LAMP);
		mobileObjects.add(CAGE);
		mobileObjects.add(ROD);
		mobileObjects.add(ROD2);
		mobileObjects.add(BIRD);
		mobileObjects.add(PILLOW);
		mobileObjects.add(CLAM);
		mobileObjects.add(OYSTER);
		mobileObjects.add(MAG);
		mobileObjects.add(FOOD);
		mobileObjects.add(BOTTLE);
		mobileObjects.add(AXE);
		mobileObjects.add(GOLD);
		mobileObjects.add(DIAMONDS);
		mobileObjects.add(SILVER);
		mobileObjects.add(JEWELS);
		mobileObjects.add(COINS);
		mobileObjects.add(CHEST);
		mobileObjects.add(EGGS);
		mobileObjects.add(TRIDENT);
		mobileObjects.add(VASE);
		mobileObjects.add(EMERALD);
		mobileObjects.add(PYRAMID);
		mobileObjects.add(PEARL);
		mobileObjects.add(SPICES);
		mobileObjects.add(CHAIN);
	}
	
	public boolean isTreasure(GameObjects thisThing)
	{
		boolean treasure = false;
		if(thisThing.ordinal() > GameObjects.MOSS.ordinal())
		{
			treasure = true;
		}
		return treasure;
	}
	
	public boolean isLesserTreasure(GameObjects thisThing)
	{
		boolean treasure = false;
		if(thisThing.ordinal() < GameObjects.CHEST.ordinal())
		{
			treasure = true;
		}
		return treasure;
	}
	
	public boolean canTake(GameObjects thisThing)
	{
		boolean result = false;
		if(mobileObjects.contains(thisThing))
		{
			result = true;
		}
		return result;
	}

	public String getItemDescription(Location location, GameObjects object, 
			boolean light, boolean grate, int plant, int bottle, boolean cage, boolean oilDoor,
			boolean bearAxe, boolean dragon, int bear, int usedBatteries, boolean broken,
			int chain, boolean gold, boolean crystalBridge, boolean collapse, int rod1, int rod2,
			int bottles, int lamps, int oysters, int pillows, int grates, int cages, int birds, 
			int snakes)
	{
		String output = "";
		switch(object)
		{
			case KEYS:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tSet of Keys");
				}
				else
				{
					output = new String("\n\tThere are some keys on the ground here.");
				}
				break;
				
			case LAMP:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tBrass Lantern");
				}
				else if(lamps == 0)
				{
					if(light)
					{
						output = new String("\n\tThere is a lamp shining nearby.");
					}
					else
					{
						output = new String("\n\tThere is a shiny brass lamp nearby.");
					}
				}
				else
				{
					output = "";
				}
				break;
				
			case GRATE: case GRATE_:
				if(grates == 1)
				{
					output = "";
				}
				else if(grate)
				{
					output = new String("\n\tThe grate is open.");
				}
				else
				{
					output = new String("\n\tThe grate is locked.");	
				}
				break;
				
			case CAGE:
				if(cages == 1)
				{
					output = "";
				}
				else if(location == Location.INHAND)
				{
					output = new String("\n\t\tWicker Cage");
				}
				else
				{
					output = new String("\n\tThere is a small wicker cage discarded nearby.");
				}
				break;
				
			case BIRD:
				if(birds == 1)
				{
					output = "";
				}
				else if(location == Location.INHAND)
				{
					output = new String("\n\t\tLittle Bird in Cage");
				}
				else
				{
					if(cage)
					{
						output = new String("\n\tThere is a little bird in the cage.");
					}
					else
					{
						output = new String("\n\tA cheerful little bird is sitting here singing.");
					}
				}
				break;
				
			case ROD:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tBlack Rod");
				}
				else if(rod1 == 0)
				{
					output = new String("\n\tA three foot black rod with a rusty star on an end lies nearby.");
				}
				else
				{
					output = "";
				}
				break;				
				
			case ROD2:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tBlack Rod");
				}
				else if(rod2 == 0)
				{
					output = new String("\n\tA three foot black rod with a rusty mark on an end lies nearby.");
				}
				else
				{
					output = "";
				}
				break;
				
			case DOOR:
				if(!oilDoor)
				{
					output = new String("\n\tThe way north is barred by a massive, rusty, iron door.");
				}
				else
				{
					output = new String("\n\tThe way north leads through a massive, rusty, iron door.");
				}
				break;
				
			case PILLOW:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tVelvet Pillow");
				}
				else if(pillows == 0)
				{
					output = new String("\n\tA small velvet pillow lies on the floor.");
				}
				else
				{
					output = "";
				}
				break;			
				
			case VASE:
				if(!broken)
				{
					if(location == Location.INHAND)
					{
						output = new String("\n\t\tMing Vase");
					}
					else
					{
						output = new String("\n\tThere is a delicate, precious, Ming vase here!");
					}
				}
				else
				{
					output = new String("\n\tThe floor is littered with worthless shards of pottery.");
				}
				break;		
				
			case SNAKE:
				if(snakes == 1)
				{
					output = "";
				}
				else
				{
					output = new String("\n\tA huge green fierce snake bars the way!");
				}
				break;		
				
			case TABLET:
				output = new String("\n\tA massive stone tablet imbedded in the wall reads:"
						+ "\n\t\"CONGRATULATIONS ON BRINGING LIGHT TO THE DARK-ROOM!\"");
				break;		
				
			case CLAM:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tGiant Clam >GRUNT!<");
				}
				else
				{
					output = new String("\n\tThere is an enormous clam here with its shell tightly closed.");
				}
				break;		
				
			case OYSTER:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tGiant Oyster >GROAN!<");
				}
				else if(oysters == 0)
				{
					output = new String("\n\tThere is an enormous oyster here with its shell tightly closed. Interesting, there seems to be something written on the underside of the oyster.");
				}
				else
				{
					output = "";
				}
				break;		
				
			case MAG:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\t\"Spelunker Today\"");
				}
				else
				{
					output = new String("\n\tThere are a few recent issues of \"Spelunker Today\" magazine here.");
				}
				break;		
				
			case FOOD:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tTasty Food");
				}
				else
				{
					output = new String("\n\tThere is tasty food here.");
				}
				break;		
				
			case BOTTLE:
				if(bottles == 1)
				{
					output = "";
				}
				else if(bottle == 0)
				{
					if(location == Location.INHAND)
					{
						output = new String("\n\t\tSmall Bottle");
					}
					else
					{
						output = new String("\n\tThere is an empty bottle here.");
					}
				}
				else if(bottle == 1)
				{
					if(location == Location.INHAND)
					{
						output = new String("\n\t\tBottle of Water");
					}
					else
					{
						output = new String("\n\tThere is a bottle of water here.");
					}
				}
				else if(bottle == 2)
				{
					if(location == Location.INHAND)
					{
						output = new String("\n\t\tBottle of Oil");
					}
					else
					{
						output = new String("\n\tThere is a bottle of oil here.");
					}
				}
				break;
				
			case PLANT:
				if(plant == 0)
				{
					output = new String("\n\tThere is a tiny little plant in the pit, murmuring \"Water, water, ...\"");
				}
				else if(plant == 1)
				{
					output = new String("\n\tThere is a 12-foot-tall beanstalk stretching up out of the pit, bellowing \"Water!! Water!!\"");
				}
				else if(plant == 2)
				{
					output = new String("\n\tThere is a gigantic beanstalk stretching all the way up to the hole.");
				}
				else
				{
					output = "";
				}
				break;
				
			case PLANT2: case PLANT2_:
				if(plant == 1)
				{
					output = new String("\n\tThe top of a 12-foot-tall beanstalk is poking up out of the west pit.");
				}
				else if(plant == 2)
				{
					output = new String("\n\tThere is a huge beanstalk growing out of the west pit up to the hole.");
				}
				else
				{
					output = "";
				}
				break;
				
			case AXE:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tDwarf's Axe");
				}
				else if(bearAxe)
				{
					output = new String("\n\tThere is a little axe lying beside the bear.");
				}
				else
				{
					output = new String("\n\tThere is a little axe here.");
				}
				break;		
		
			case ART:
				//TODO cave art
				output = new String("\n\t");
				break;
				
			case DRAGON: case DRAGON_:
				if(dragon)
				{
					output = new String("\n\tA huge green fierce dragon bars the way!");
				}
				else
				{
					output = new String("\n\tThe body of a huge green dead dragon is lying off to one side.");
				}
				break;		
				
			case RUG:
				if(dragon)
				{
					output = new String("\n\tThe dragon is sprawled out on a persian rug!");
				}
				else if(location == Location.INHAND)
				{
					output = new String("\n\t\tPersian Rug");
				}
				else
				{
					output = new String("\n\tThere is a persian rug spread out on the floor!");
				}
				break;		
				
			case BEAR:
				if(bear == 0)
				{
					output = new String("\n\tThere is a ferocious cave bear eyeing you from the far end of the room!");
				}
				else if(bear == 1)
				{
					output = new String("\n\tThere is a gentle cave bear sitting placidly in one corner.");
				}
				else if(bear == 2)
				{
					output = new String("\n\tThere is a contented-looking bear wandering about nearby.");
				}
				else
				{
					output = "";
				}
				break;
				
			case MESSAGE:
				output = new String("\n\tThere is a message scrawled in the dust in a flowery script, reading:"
						+ "\n\t\t\"This is not the maze where the pirate leaves his treasure chest.\"");
				break;
				
			case PONY:
				output = new String("\n\tThere is a massive vending machine here.  \nThe instructions on it read:"
						+ "\n\t\t\"Drop coins here to receive fresh batteries.\"");
				break;
				
			case BATTERIES:
				if(usedBatteries == 1)
				{
					output = new String("\n\tThere are fresh batteries here.");
				}
				else
				{
					output = new String("\n\tSome worn-out batteries have been discarded nearby.");
				}
				break;	
				
			case MOSS:
				//TODO moss
				output = new String("\n\t");
				break;
				
			case GOLD:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tLarge Gold Nugget");
				}
				else
				{
					output = new String("\n\tThere is a large sparkling nugget of gold here!");
				}
				break;		
				
			case DIAMONDS:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tSeveral Diamonds");
				}
				else
				{
					output = new String("\n\tThere are diamonds here!");
				}
				break;		
				
			case SILVER:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tBars of Silver");
				}
				else
				{
					output = new String("\n\tThere are bars of silver here!");
				}
				break;		
				
			case JEWELS:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tPrecious Jewelry");
				}
				else
				{
					output = new String("\n\tThere is precious jewelry here!");
				}
				break;		

			case COINS:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tRare coins");
				}
				else
				{
					output = new String("\n\tThere are many coins here!");
				}
				break;		
				
			case CHEST:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tTreasure Chest");
				}
				else
				{
					output = new String("\n\tThe pirate's treasure chest is here!");
				}
				break;		
				
			case EGGS:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tGolden Eggs");
				}
				else
				{
					output = new String("\n\tThere is a large nest here, full of golden eggs!");
				}
				break;		
				
			case TRIDENT:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tJeweled Trident");
				}
				else
				{
					output = new String("\n\tThere is a jewel-encrusted trident here!");
				}
				break;			
				
			case EMERALD:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tEgg-Sized Emerald");
				}
				else
				{
					output = new String("\n\tThere is an emerald here the size of a plover's egg!");
				}
				break;		
				
			case PYRAMID:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tPlatinum Pyramid");
				}
				else
				{
					output = new String("\n\tThere is a platinum pyramid here, 8 inches on a side!");
				}
				break;		
				
			case PEARL:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tGlistening Pearl");
				}
				else
				{
					output = new String("\n\tOff to one side lies a glistening pearl!");
				}
				break;		
				
			case SPICES:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tRare Spices");
				}
				else
				{
					output = new String("\n\tThere are rare spices here!");
				}
				break;		
				
			case CHAIN:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\tGolden Chain");
				}
				else
				{
					if(chain == 0)
					{
						output = new String("\n\tThe bear is locked to the wall with a golden chain!");
					}
					else if(chain == 1)
					{
						output = new String("\n\tThere is a golden chain lying in a heap on the floor!");
					}
					if(chain == 2)
					{
						output = new String("\n\tThere is a golden chain locked to the wall!");
					}
				}
				break;		
				
			case TREADS:
				if(!gold)
				{
					output = new String("\n\tRough stone steps lead up the dome.");
				}
				break;	
				
			case TREADS_:
				if(!gold)
				{
					output = new String("\n\tRough stone steps lead down the pit.");
				}
				break;	
				
			case CRYSTAL: case CRYSTAL_:
				if(crystalBridge)
				{
					output = new String("\n\tA crystal bridge now spans the fissure.");
				}
				break;		
				
			case BRIDGE: case BRIDGE_:
				if(!collapse)
				{
					output = new String("\n\tA rickety wooden bridge extends across the chasm, vanishing into the mist. \n\tA sign posted on the bridge reads: \n\t\t\"Stop!  Pay Troll!\"");
				}
				else
				{
					output = new String("\n\tThe wreckage of a bridge (and a dead bear) can be seen at the bottom of the chasm.");
				}
				break;	
				
			case SHADOW: case SHADOW_:
				output = new String("\n\tThe shadowy figure seems to be trying to attract your attention.");
				break;	
				
			case TROLL_: case TROLL:
				output = new String("\n\tA burly troll stands by the bridge and insists you throw him a treasure before you may cross.");
				break;	
				
			case TROLL2_: case TROLL2:
				output = new String("\n\tThe troll is nowhere to be seen.");
				break;
		
			default:
				break;
				
		}
		return output;
	}
}

