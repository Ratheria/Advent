/**
 *	@author Ariana Fairbanks
 */

package model;

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

	public boolean isTreasure(GameObjects thisThing)
	{
		boolean treasure = false;
		if(thisThing.ordinal() > GameObjects.MOSS.ordinal())
		{
			treasure = true;
		}
		return treasure;
	}

}

