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
			boolean light, boolean grate)
	{
		String output = "";
		switch(object)
		{
			case KEYS:
				if(location == Location.INHAND)
				{
					output = new String("\n\tSet of keys");
				}
				else
				{
					output = new String("\n\tThere are some keys on the ground here.");
				}
				break;
				
			case LAMP:
				if(location == Location.INHAND)
				{
					output = new String("\n\tBrass lantern");
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
					output = new String("\n\tWicker cage");
				}
				else
				{
					output = new String("\n\tThere is a small wicker cage discarded nearby.");
				}
				break;
				
			case ROD:
				if(location == Location.INHAND)
				{
					output = new String("Black rod");
				}
				else
				{
					output = new String("A three foot black rod with a rusty star on an end lies nearby.");
				}
				break;				
				
			case ROD2:
				if(location == Location.INHAND)
				{
					output = new String("Black rod");
				}
				else
				{
					output = new String("A three foot black rod with a rusty mark on an end lies nearby.");
				}
				break;
				
		}
		return output;
	}
}

