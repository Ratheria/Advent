/**
 *	@author Ariana Fairbanks
 */

package model;

public enum GameObjects 
{

	NOTHING, KEYS, LAMP, GRATE, GRATE_, CAGE, ROD, ROD2,
	TREADS, TREADS_, BIRD, DOOR, PILLOW, SNAKE, CRYSTAL, 
	CRYSTAL_, TABLET, CLAM, OYSTER, MAG, DWARF, KNIFE, 
	FOOD, BOTTLE, WATER, OIL, MIRROR, MIRROR_, PLANT,
	PLANT2, PLANT2_, STALACTITE, SHADOW, SHADOW_, AXE, 
	ART, PIRATE, DRAGON, DRAGON_, BRIDGE, BRIDGE_, TROLL, 
	TROLL_, TROLL2, TROLL2_, BEAR, MESSAGE, GEYSER, PONY, 
	BATTERIES, MOSS, GOLD, DIAMONDS, SILVER, JEWELS, COINS, 
	CHEST, EGGS, TRIDENT, VASE, EMERALD, PYRAMID, PEARL, 
	RUG, RUG_, SPICES, CHAIN;

	public boolean isTreasure(GameObjects thisThing)
	{
		boolean treasure = false;
		if(thisThing.ordinal() > GameObjects.MOSS.ordinal())
		{
			treasure = true;
		}
		return treasure;
	}

	public String getItemDescription(Location location, GameObjects object, 
			boolean light, boolean grate, int plant, int bottle, boolean cage, boolean oilDoor,
			boolean bearAxe)
	{
		String output = "";
		switch(object)
		{
			case KEYS:
				if(location == Location.INHAND)
				{
					output = new String("\n\tSet of Keys");
				}
				else
				{
					output = new String("\n\tThere are some keys on the ground here.");
				}
				break;
				
			case LAMP:
				if(location == Location.INHAND)
				{
					output = new String("\n\tBrass Lantern");
				}
				else
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
				break;
				
			case GRATE:
				if(grate)
				{
					output = new String("\n\tThe grate is open.");
				}
				else
				{
					output = new String("\n\tThe grate is locked.");	
				}
				break;
				
			case GRATE_:
				if(grate)
				{
					output = new String("\n\tThe grate is open.");
				}
				else
				{
					output = new String("\n\tThe grate is locked.");	
				}
				break;
				
			case CAGE:
				if(location == Location.INHAND)
				{
					output = new String("\n\tWicker Cage");
				}
				else
				{
					output = new String("\n\tThere is a small wicker cage discarded nearby.");
				}
				break;
				
			case BIRD:
				if(location == Location.INHAND)
				{
					output = new String("\n\tLittle Bird in Cage");
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
					output = new String("\n\tBlack Rod");
				}
				else
				{
					output = new String("\n\tA three foot black rod with a rusty star on an end lies nearby.");
				}
				break;				
				
			case ROD2:
				if(location == Location.INHAND)
				{
					output = new String("\n\tBlack Rod");
				}
				else
				{
					output = new String("\n\tA three foot black rod with a rusty mark on an end lies nearby.");
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
					output = new String("\n\tVelvet Pillow");
				}
				else
				{
					output = new String("\n\tA small velvet pillow lies on the floor.");
				}
				break;			
				
			case VASE:
				if(location == Location.INHAND)
				{
					output = new String("\n\tOriental Vase");
				}
				else
				{
					output = new String("\n\tA small velvet pillow lies on the floor.");
				}
				break;		
				
			case SNAKE:
				output = new String("\n\tA huge green fierce snake bars the way!");
				break;		
				
			case TABLET:
				output = new String("\n\tA massive stone tablet imbedded in the wall reads:"
						+ "\n\t\"CONGRATULATIONS ON BRINGING LIGHT TO THE DARK-ROOM!\"");
				break;		
				
			case CLAM:
				if(location == Location.INHAND)
				{
					output = new String("\n\tGiant Clam >GRUNT!<");
				}
				else
				{
					output = new String("\n\tThere is an enormous clam here with its shell tightly closed.");
				}
				break;		
				
			case OYSTER:
				if(location == Location.INHAND)
				{
					output = new String("\n\tGiant Oyster >GROAN!<");
				}
				else
				{
					output = new String("\n\tThere is an enormous oyster here with its shell tightly closed.");
				}
				break;		
				
			case MAG:
				if(location == Location.INHAND)
				{
					output = new String("\n\t\"Spelunker Today\"");
				}
				else
				{
					output = new String("\n\tThere are a few recent issues of \"Spelunker Today\" magazine here.");
				}
				break;		
				
			case FOOD:
				if(location == Location.INHAND)
				{
					output = new String("\n\tTasty Food");
				}
				else
				{
					output = new String("\n\tThere is tasty food here.");
				}
				break;		
				
			case BOTTLE:
				if(bottle == 0)
				{
					if(location == Location.INHAND)
					{
						output = new String("\n\tSmall Bottle");
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
						output = new String("\n\tBottle of Water");
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
						output = new String("\n\tBottle of Oil");
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
				
			case PLANT2:
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
					output = new String("\n\tDwarf's Axe");
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
				//cave art
				output = new String("\n\t");
				break;
				
		}
		return output;
	}
}

