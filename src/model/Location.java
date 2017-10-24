/**
 *	@author Ariana Fairbanks
 */

package model;


import controller.AdventControl;

public enum Location
{
	THEVOID,INHAND, ROAD, HILL, BUILDING, VALLEY, FOREST, WOODS, SLIT, OUTSIDE, 
	INSIDE, COBBLES, DEBRIS, AWKWARD, BIRD, SMALLPIT,
	EASTMIST, NUGGET, EASTFISSURE, WESTFISSURE, WESTMIST,
	ALIKE1, ALIKE2, ALIKE3, ALIKE4, ALIKE5, ALIKE6, ALIKE7, ALIKE8, ALIKE9, ALIKE10, ALIKE11, ALIKE12, ALIKE13, ALIKE14,
	BRINK, EASTLONG, WESTLONG, 
	DIFF0, DIFF1, DIFF2, DIFF3, DIFF4, DIFF5, DIFF6, DIFF7, DIFF8, DIFF9, DIFF10,
	PONY, CROSS, HALLOFMOUNTAINKING, WEST, SOUTH, NS, Y2, JUMBLE, EASTWINDOW,
	DIRTY, CLEAN, WET, DUSTY, COMPLEX, 
	SHELL, ARCH, RAGGED, CULDESAC, ANTE, WITT, 
	BEDQUILT, CHEESE, SOFT,
	EAST2PIT, WEST2PIT, EASTPIT, WESTPIT, 
	NARROW, GIANT, BLOCK, IMMENSE, 
	FALLS, STEEP, ABOVEP, SJUNC, TIGHT, LOW, CRAWL, WESTWINDOW,
	ORIENTAL, MISTY, ALCOVE, PROOM, DROOM, 
	SLAB, ABOVER, MIRROR, RESER, 
	SCAN1, SCAN2, SCAN3, SECRET, 
	WIDE, STALACTITE, TALL, BOULDERS,
	SCORR, SWSIDE, 
	DEAD0, DEAD1, DEAD2, DEAD3, DEAD4, DEAD5, DEAD6, DEAD7, DEAD8, DEAD9, DEAD10, DEAD11,
	NESIDE, CORR, FORK, WARM, VIEW, CHAMBER, LIME, FBARR, BARR,
	NEEND, SWEND,
	CRACK, NECK, LOSE, CANT, CLIMB, CHECK, SNAKED, THRU, DUCK, SEWER, UPNOUT, DIDIT,
	REMARK;
	
	private AdventControl base;
	public static Location[] locate = Location.values();
	
	public void setUp(AdventControl base)
	{	
		this.base = base;
	}
	
	public boolean isWaterHere(Location here)
	{
		boolean result = false;
		if(here == ROAD || here == BUILDING || here == VALLEY || here == SLIT || here == WET || here == FALLS || here == RESER)
		{
			result = true;
		}
		return result;
	}
	
	public int minLoc()
	{
		return EASTMIST.ordinal();
	}
	
	public int maxLoc()
	{
		return DEAD0.ordinal();
	}
	
	public boolean outside(Location here)
	{
		boolean outside = false;
		if(here.ordinal() > INHAND.ordinal() && here.ordinal() < DEBRIS.ordinal())
		{
			outside = true;
		}
		return outside;
	}
	
	public boolean outsideCave(Location here)
	{
		boolean outside = false;
		if(here.ordinal() > INHAND.ordinal() && here.ordinal() < INSIDE.ordinal())
		{
			outside = true;
		}
		return outside;
	}
	
	public boolean upperCave(Location here)
	{
		boolean upper = false;
		if(here.ordinal() > OUTSIDE.ordinal() && here.ordinal() < OUTSIDE.ordinal())
		{
			upper = true;
		}
		return upper;
	}
	
	public int getOrdinal(Location here)
	{
		return here.ordinal();
	}
	
	public boolean dontNeedLamp(Location here)
	{
		boolean need = false;
		if(outside(here)||here == VIEW||here == NEEND||here == SWEND||here == PROOM)
		{
			need = true;
		}
		return need;
	}
	
	public Location moveTo(Movement destination, Location here, boolean grate,
			boolean gold, boolean crystalBridge, boolean snake, boolean emerald, boolean clam, 
			boolean oyster, int plant, boolean oilDoor, boolean dragon, int troll,
			boolean trollHere, int itemsInHand, boolean collapse, int bear)
	{
		double chance = AdventControl.generate();
		Location next = null;
		switch(here)
		{
			case ROAD:
				
				switch(destination)
				{
					case WEST: case UP: case ROAD: case HILL: next = HILL; break;
					case EAST: case BUILDING: case IN: case ENTER: next = BUILDING; break;
					case SOUTH: case DOWN: case GULLY: case STREAM: case DOWNSTREAM: next = VALLEY; break;
					case NORTH: case FOREST: next = FOREST; break;
					case DEPRESSION: next = OUTSIDE; break;
					default: next = THEVOID; break; 
				}
				break;
			
			case HILL:
				switch(destination)
				{
					case ROAD: case BUILDING: case FORWARD: case EAST: case DOWN: next = ROAD; break;
					case FOREST: case NORTH: case SOUTH: next = FOREST; break;
					default: next = THEVOID; break; 
				}
				break;	
			
			case BUILDING:
				switch(destination)
				{
					case ENTER: case OUT: case OUTDOORS: case WEST: next = ROAD; break;
					case XYZZY: next = DEBRIS; break;
					case PLUGH: next = Y2; break;
					case DOWNSTREAM: case STREAM: next = SEWER; break;
					default: next = THEVOID; break; 
				}
				break;
			
			case VALLEY:
				switch(destination)
				{
					case UPSTREAM: case BUILDING: case NORTH: next = ROAD; break; 
					case FOREST: case EAST: case WEST: case UP: next = FOREST; break; 
					case DOWNSTREAM: case SOUTH: case DOWN: next = SLIT; break; 
					case DEPRESSION: next = OUTSIDE; break; 
					default: next = THEVOID; break; 
				}
				break;
				
			case FOREST:
				switch(destination)
				{
					case VALLEY: case EAST: case DOWN: next = VALLEY; break; 
					case FOREST:
						if(chance>.49)
						{	next = FOREST;}
						else 
						{	next = WOODS;}
						break; 
					case FORWARD:
						if(chance<.51)
						{	next = FOREST;	}
						else 
						{	next = WOODS;	}
						break; 
					case NORTH:
						if(chance>.49)
						{	next = FOREST;	}
						else 
						{	next = WOODS;	}
						break; 
					case WEST: case SOUTH: next = FOREST; break; 
					default: next = THEVOID; break; 
				}
				break;
				
			case WOODS:
				switch(destination)
				{
					case ROAD: case NORTH: next = ROAD; break; 
					case VALLEY: case EAST: case WEST: case DOWN: next = VALLEY; break; 
					case FOREST: case SOUTH: next = FOREST; break; 
					default: next = THEVOID; break;
				}
				break;
				
			case SLIT:
				switch(destination)
				{
					case BUILDING: next = ROAD; break; 
					case UPSTREAM: case NORTH: next = VALLEY; break; 
					case FOREST: case WEST: case EAST: next = FOREST; break; 
					case DOWNSTREAM: case ROCK: case BED: case SOUTH: next = OUTSIDE; break; 
					case SLIT: next = REMARK; break; 
					default: next = THEVOID; break; 
				}
				break;
			
			case OUTSIDE:
				switch(destination)
				{
					case FOREST: case EAST: case WEST: case SOUTH: next = FOREST; break; 
					case BUILDING: next = ROAD; break;
					case UPSTREAM: case GULLY: case NORTH: next = SLIT; break;
					case ENTER: case IN: case DOWN: next = throughGrate(grate); break;
					default: next = THEVOID; break; 
				}
				break;
				
			case INSIDE:
				switch(destination)
				{
					case CRAWL: case COBBLE: case IN: case WEST: next = COBBLES; break;
					case PIT: next = SMALLPIT; break;
					case DEBRIS: next = DEBRIS; break;
					case OUT: case UP: next = backThroughGrate(grate); break;
					default: next = THEVOID; break;
				}
				break;
					
			case COBBLES:
				switch(destination)
				{
					case OUT: case SURFACE: case NOWHERE: case EAST: next = INSIDE; break;
					case DEBRIS: case DARK: case WEST: case IN: next = DEBRIS; break;
					case PIT: next = SMALLPIT; break;
					default: next = THEVOID; break;
				}
				break;
				
			case DEBRIS:
				switch(destination)
				{
					case DEPRESSION: next = backThroughGrate(grate); break;
					case ENTRANCE: next = INSIDE; break;
					case COBBLE: case CRAWL: case PASSAGE: case LOW: case EAST: next = COBBLES; break;
					case CANYON: case IN: case UP: case WEST: next = AWKWARD; break;
					case XYZZY: next = BUILDING; break;
					case PIT: next = SMALLPIT; break;
					default: next = THEVOID; break;
				}
				break;
			
			case AWKWARD:
				switch(destination)
				{
					case DEPRESSION: next = backThroughGrate(grate); break;
					case ENTRANCE: next = INSIDE; break;
					case DOWN: case EAST: case DEBRIS: next = DEBRIS; break;
					case IN: case UP: case WEST: next = BIRD; break;
					case PIT: next = SMALLPIT; break;
					default: next = THEVOID; break;
				}
				break;
			
			case BIRD:
				switch(destination)
				{
					case DEPRESSION: next = backThroughGrate(grate); break;
					case ENTRANCE: next = INSIDE; break;
					case DEBRIS: next = DEBRIS; break;
					case CANYON: case EAST: next = AWKWARD; break;
					case PASSAGE: case PIT: case WEST: next = SMALLPIT; break;
					default: next = THEVOID; break;
				}
				break;
				
			case SMALLPIT:
				switch(destination)
				{
					case DEPRESSION: next = backThroughGrate(grate); break;
					case ENTRANCE: next = INSIDE; break;
					case DEBRIS: next = DEBRIS; break;
					case PASSAGE: case EAST: next = BIRD; break;
					case DOWN: case PIT: case STEPS:
						if(gold)
						{	next = NECK;	}
						else
						{	next = EASTMIST;}
						break;
					case CRACK: case WEST: next = CRACK; break;
					default: next = THEVOID; break;
				}
				break;
				
			case EASTMIST:
				switch(destination)
				{
					case LEFT: case SOUTH: next = NUGGET; break;
					case FORWARD: case HALL: case WEST: next = EASTFISSURE; break;
					case STAIRS: case DOWN: case NORTH: next = HALLOFMOUNTAINKING; break;
					case UP: case PIT:
						if(gold)
						{	next = CANT;	}
						else
						{	next = SMALLPIT;}
						break;
					case STEPS: case DOME: case PASSAGE: case EAST:
						if(gold)
						{	next = CANT;	}
						else
						{	next = THEVOID;	}
						break;
					case Y2: next = JUMBLE; break;
					default: next = THEVOID; break;
				}
				break;
			
			case NUGGET:
				switch(destination)
				{
					case HALL: case OUT: case NORTH: next = EASTMIST; break;
					default: next = THEVOID; break;
				}
				break;
				
			case EASTFISSURE:
				switch(destination)
				{
					case HALL: case EAST: next = EASTMIST; break;
					case JUMP: case FORWARD:
						if(!crystalBridge)
						{	next = LOSE;	}
						else
						{	next = THEVOID;	}
						break;
					case OVER: case ACROSS: case WEST: case CROSS: next = westRemark(crystalBridge); break;
					default: next = THEVOID; break;
				}
				break;
				
			case WESTFISSURE:
				switch(destination)
				{
					case NORTH: next = THRU; break;
					case WEST: next = WESTMIST; break;
					case JUMP:
						if(crystalBridge)
						{	next = REMARK;	}
						else
						{	next = THEVOID;	}
						break;
					case FORWARD: case OVER: case ACROSS: case EAST: case CROSS: next = eastRemark(crystalBridge); break;
					default: next = THEVOID; break;
				}
				break;
				
			case WESTMIST:
				switch(destination)
				{
					case SOUTH: case UP: case PASSAGE: case CLIMB: next = ALIKE1; break;
					case EAST: next = WESTFISSURE; break;
					case NORTH: next = DUCK; break;
					case WEST: case CRAWL: next = EASTLONG; break;
					default: next = THEVOID; break;
				}
				break;
				
			case ALIKE1:
				switch(destination)
				{
					case UP: next = WESTMIST; break;
					case NORTH: next = ALIKE1; break;
					case EAST: next = ALIKE2; break;
					case SOUTH: next = ALIKE4; break;
					case WEST: next = ALIKE11; break;
					default: next = THEVOID; break;
				}
				break;
				
			case ALIKE2:
				switch(destination)
				{
					case EAST: next = ALIKE4; break;
					case SOUTH: next = ALIKE3; break;
					case WEST: next = ALIKE1; break;
					default: next = THEVOID; break;
				}
				break;
				
			case ALIKE3:
				switch(destination)
				{
					case DOWN: next = DEAD5; break;
					case NORTH: next = DEAD9; break;
					case EAST: next = ALIKE2; break;
					case SOUTH: next = ALIKE6; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case ALIKE4:
				switch(destination)
				{
					case UP: case DOWN: next = ALIKE14; break;
					case NORTH: next = ALIKE2; break;
					case EAST: next = DEAD3; break;
					case SOUTH: next = DEAD4; break;
					case WEST: next = ALIKE1; break;
					default: next = THEVOID; break;
				}
				break;
			
			case ALIKE5:
				switch(destination)
				{
					case EAST: next = ALIKE6; break;
					case WEST: next = ALIKE7; break;
					default: next = THEVOID; break;
				}
				break;
		
			case ALIKE6:
				switch(destination)
				{
					case DOWN: next = ALIKE7; break;
					case WEST: next = ALIKE5; break;
					case EAST: next = ALIKE3; break;
					case SOUTH: next = ALIKE8; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case ALIKE7:
				switch(destination)
				{
					case UP: next = ALIKE6; break;
					case WEST: next = ALIKE5; break;
					case EAST: next = ALIKE8; break;
					case SOUTH: next = ALIKE9; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case ALIKE8:
				switch(destination)
				{
					case UP: next = ALIKE9; break;
					case WEST: next = ALIKE6; break;
					case EAST: next = ALIKE7; break;
					case SOUTH: next = ALIKE8; break;
					case NORTH: next = ALIKE10; break;
					case DOWN: next = DEAD11; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case ALIKE9:
				switch(destination)
				{
					case WEST: next = ALIKE7; break;
					case NORTH: next = ALIKE8; break;
					case SOUTH: next = DEAD6; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case ALIKE10:
				switch(destination)
				{
					case NORTH: next = ALIKE10; break;
					case WEST: next = ALIKE8; break;
					case EAST: next = BRINK; break;
					case DOWN: next = DEAD7; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case ALIKE11:
				switch(destination)
				{
					case NORTH: next = ALIKE1; break;
					case EAST: next = DEAD1; break;
					case SOUTH: case WEST: next = ALIKE11; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case ALIKE12:
				switch(destination)
				{
					case WEST: next = DEAD10; break;
					case EAST: next = ALIKE13; break;
					case SOUTH: next = BRINK; break;
					default: next = THEVOID; break;
				}
				break;	
				
				
			case ALIKE13:
				switch(destination)
				{
					case NORTH: next = BRINK; break;
					case WEST: next = ALIKE12; break;
					case NORTHWEST: next = DEAD2; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case ALIKE14:
				switch(destination)
				{
					case UP: case DOWN: next = ALIKE4; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case BRINK:
				switch(destination)
				{
					case DOWN: case CLIMB: next = BIRD; break;
					case WEST: next = ALIKE10; break;
					case SOUTH: next = DEAD8; break;
					case NORTH: next = ALIKE12; break;
					case EAST: next = ALIKE13; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case EASTLONG:
				switch(destination)
				{
					case EAST: case UP: case CRAWL: next = WESTMIST; break;
					case WEST: next = WESTLONG; break;
					case NORTH: case DOWN: case HOLE: next = CROSS; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case WESTLONG:
				switch(destination)
				{
					case NORTH: next = CROSS; break;
					case EAST: next = EASTLONG; break;
					case SOUTH: next = DIFF0; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DIFF0:
				switch(destination)
				{
					case SOUTH: next = DIFF1; break;
					case SOUTHWEST: next = DIFF2; break;
					case NORTHEAST: next = DIFF3; break;
					case SOUTHEAST: next = DIFF4; break;
					case UP: next = DIFF5; break;
					case NORTHWEST: next = DIFF6; break;
					case EAST: next = DIFF7; break;
					case WEST: next = DIFF8; break;
					case NORTH: next = DIFF9; break;
					case DOWN: next = WESTLONG; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DIFF1:
				switch(destination)
				{
					case WEST: next = DIFF0; break;
					case SOUTHEAST: next = DIFF1; break;
					case NORTHWEST: next = DIFF3; break;
					case SOUTHWEST: next = DIFF4; break;
					case NORTHEAST: next = DIFF5; break;
					case UP: next = DIFF6; break;
					case DOWN: next = DIFF7; break;
					case NORTH: next = DIFF8; break;
					case SOUTH: next = DIFF9; break;
					case EAST: next = DIFF10; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DIFF2:
				switch(destination)
				{
					case NORTHWEST: next = DIFF0; break;
					case UP: next = DIFF1; break;
					case NORTH: next = DIFF3; break;
					case SOUTH: next = DIFF4; break;
					case WEST: next = DIFF5; break;
					case SOUTHWEST: next = DIFF6; break;
					case NORTHEAST: next = DIFF7; break;
					case EAST: next = DIFF8; break;
					case DOWN: next = DIFF9; break;
					case SOUTHEAST: next = DIFF10; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DIFF3:
				switch(destination)
				{
					case UP: next = DIFF0; break;
					case DOWN: next = DIFF1; break;
					case WEST: next = DIFF2; break;
					case NORTHEAST: next = DIFF4; break;
					case SOUTHWEST: next = DIFF5; break;
					case EAST: next = DIFF6; break;
					case NORTH: next = DIFF7; break;
					case NORTHWEST: next = DIFF8; break;
					case SOUTHEAST: next = DIFF9; break;
					case SOUTH: next = DIFF10; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DIFF4:
				switch(destination)
				{
					case NORTHEAST: next = DIFF0; break;
					case NORTH: next = DIFF1; break;
					case NORTHWEST: next = DIFF2; break;
					case SOUTHEAST: next = DIFF3; break;
					case EAST: next = DIFF5; break;
					case DOWN: next = DIFF6; break;
					case SOUTH: next = DIFF7; break;
					case UP: next = DIFF8; break;
					case WEST: next = DIFF9; break;
					case SOUTHWEST: next = DIFF10; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DIFF5:
				switch(destination)
				{
					case NORTH: next = DIFF0; break;
					case SOUTHEAST: next = DIFF1; break;
					case DOWN: next = DIFF2; break;
					case SOUTH: next = DIFF3; break;
					case EAST: next = DIFF4; break;
					case WEST: next = DIFF6; break;
					case SOUTHWEST: next = DIFF7; break;
					case NORTHEAST: next = DIFF8; break;
					case NORTHWEST: next = DIFF9; break;
					case UP: next = DIFF10; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DIFF6:
				switch(destination)
				{
					case EAST: next = DIFF0; break;
					case WEST: next = DIFF1; break;
					case UP: next = DIFF2; break;
					case SOUTHWEST: next = DIFF3; break;
					case DOWN: next = DIFF4; break;
					case SOUTH: next = DIFF5; break;
					case NORTHWEST: next = DIFF7; break;
					case SOUTHEAST: next = DIFF8; break;
					case NORTHEAST: next = DIFF9; break;
					case NORTH: next = DIFF10; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DIFF7:
				switch(destination)
				{
					case EAST: next = DIFF0; break;
					case WEST: next = DIFF1; break;
					case UP: next = DIFF2; break;
					case SOUTHWEST: next = DIFF3; break;
					case DOWN: next = DIFF4; break;
					case SOUTH: next = DIFF5; break;
					case NORTHWEST: next = DIFF6; break;
					case SOUTHEAST: next = DIFF8; break;
					case NORTHEAST: next = DIFF9; break;
					case NORTH: next = DIFF10; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DIFF8:
				switch(destination)
				{
					case DOWN: next = DIFF0; break;
					case EAST: next = DIFF1; break;
					case NORTHEAST: next = DIFF2; break;
					case UP: next = DIFF3; break;
					case WEST: next = DIFF4; break;
					case NORTH: next = DIFF5; break;
					case SOUTH: next = DIFF6; break;
					case SOUTHEAST: next = DIFF7; break;
					case SOUTHWEST: next = DIFF9; break;
					case NORTHWEST: next = DIFF10; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DIFF9:
				switch(destination)
				{
					case SOUTHWEST: next = DIFF0; break;
					case NORTHWEST: next = DIFF1; break;
					case EAST: next = DIFF2; break;
					case WEST: next = DIFF3; break;
					case NORTH: next = DIFF4; break;
					case DOWN: next = DIFF5; break;
					case SOUTHEAST: next = DIFF6; break;
					case UP: next = DIFF7; break;
					case SOUTH: next = DIFF8; break;
					case NORTHEAST: next = DIFF10; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DIFF10:
				switch(destination)
				{
					case SOUTHWEST: next = DIFF1; break;
					case NORTH: next = DIFF2; break;
					case EAST: next = DIFF3; break;
					case NORTHWEST: next = DIFF4; break;
					case SOUTHEAST: next = DIFF5; break;
					case NORTHEAST: next = DIFF6; break;
					case WEST: next = DIFF7; break;
					case DOWN: next = DIFF8; break;
					case UP: next = DIFF9; break;
					case SOUTH: next = PONY; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case PONY:
				switch(destination)
				{
					case NORTH: case OUT: next = DIFF10; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case CROSS:
				switch(destination)
				{
					case WEST: next = EASTLONG; break;
					case NORTH: next = DEAD0; break;
					case EAST: next = WEST; break;
					case SOUTH: next = WESTLONG; break;
					default: next = THEVOID; break;
				}
				break;
				
			case HALLOFMOUNTAINKING:
				switch(destination)
				{
					case STAIRS: case UP: case EAST: next = EASTMIST; break;
					case NORTH: case LEFT:
						if(snake)
						{	next = REMARK;	}
						else
						{	next = NS;	}
						break;
					case SOUTH: case RIGHT:
						if(snake)
						{	next = REMARK;	}
						else
						{	next = SOUTH;	}
						break;
					case WEST: case FORWARD:
						if(snake)
						{	next = REMARK;	}
						else
						{	next = WEST;	}
						break;
					case SOUTHWEST:
						if(snake)
						{
							if(Math.random() < .36)
							{	next = SECRET;	}
							else
							{	next = REMARK;	}
						}
						else
						{	next = SECRET;	}
						break;
					case SECRET: next = SECRET; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case WEST:
				switch(destination)
				{
					case HALL: case OUT: case EAST: next = HALLOFMOUNTAINKING; break;
					case WEST: case UP: next = CROSS; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case SOUTH:
				switch(destination)
				{
					case HALL: case OUT: case NORTH: next = HALLOFMOUNTAINKING; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case NS:
				switch(destination)
				{
					case HALL: case OUT: case SOUTH: next = HALLOFMOUNTAINKING; break;
					case Y2: next = Y2; break;
					case NORTH: next = Y2; break;
					case DOWN: case HOLE: next = DIRTY; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case Y2:
				switch(destination)
				{
					case PLUGH: next = BUILDING; break;
					case SOUTH: next = NS; break;
					case EAST: case WALL: case BROKEN: next = JUMBLE; break;
					case WEST: next = EASTWINDOW; break;
					case PLOVER: next = PROOM; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case JUMBLE:
				switch(destination)
				{
					case DOWN: case Y2: next = Y2; break;
					case UP: next = EASTMIST; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case EASTWINDOW:
				switch(destination)
				{
					case EAST: case Y2: next = Y2; break;
					case JUMP: next = NECK; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DIRTY:
				switch(destination)
				{
					case EAST: case CRAWL: next = CLEAN; break;
					case UP: case HOLE: next = NS; break;
					case WEST: next = DUSTY; break;
					case BEDQUILT: next = BEDQUILT; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case CLEAN:
				switch(destination)
				{
					case WEST: case CRAWL: next = DIRTY; break;
					case DOWN: case PIT: case CLIMB: next = WET; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case WET:
				switch(destination)
				{
					case CLIMB: case UP: case OUT: next = CLEAN; break;
					case SLIT: case STREAM: case DOWN: case UPSTREAM: case DOWNSTREAM: next = REMARK; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DUSTY:
				switch(destination)
				{
					case EAST: case PASSAGE: next = DIRTY; break;
					case DOWN: case HOLE: case FLOOR: next = COMPLEX; break;
					case BEDQUILT: next = BEDQUILT; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case COMPLEX:
				switch(destination)
				{
					case UP: case CLIMB: case ROOM: next = DUSTY; break;
					case WEST: next = BEDQUILT; break;
					case BEDQUILT: next = BEDQUILT; break;
					case NORTH: case SHELL: next = SHELL; break;
					case EAST: next = ANTE; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case SHELL:
				switch(destination)
				{
					case UP: case HALL: next = ARCH; break;
					case DOWN: next = RAGGED; break;
					case WEST: next = BEDQUILT; break;
					case SOUTH:
						if(clam||oyster)
						{	next = REMARK;	}
						else
						{	next = COMPLEX;	}
						break;
					default: next = THEVOID; break;
				}
				break;	
				
			case ARCH:
				switch(destination)
				{
					case DOWN: case SHELL: case OUT: next = SHELL; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case RAGGED:
				switch(destination)
				{
					case UP: case SHELL: next = SHELL; break;
					case DOWN: next = CULDESAC; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case CULDESAC:
				switch(destination)
				{
					case UP: case OUT: next = RAGGED; break;
					case SHELL: next = SHELL; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case ANTE:
				switch(destination)
				{
					case UP: next = COMPLEX; break;
					case WEST: next = BEDQUILT; break;
					case EAST: next = WITT; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case WITT:
				switch(destination)
				{
					case EAST: case NORTH: case SOUTH: case NORTHEAST: case SOUTHEAST: case SOUTHWEST: case NORTHWEST: case UP: case DOWN: next = atWittsEnd(); break;
					case WEST: next = REMARK; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case BEDQUILT:
				switch(destination)
				{
					case EAST: next = COMPLEX; break;
					case WEST: next = CHEESE; break;
					case SOUTH: 
						if(chance < .81)
						{	next = REMARK;	}
						else
						{	next = SLAB;	}
						break;
					case SLAB: next = SLAB; break;
					case UP: 
						if(chance < .81)
						{	next = REMARK;	}
						else if(chance > .49)
						{	next = ABOVEP;	}
						else
						{	next = DUSTY;	}
						break;
					case NORTH: 
						if(chance < .61)
						{	next = REMARK;	}
						else if(chance < .76)
						{	next = LOW;	}
						else
						{	next = SJUNC;	}
						break;
					case DOWN: 
						if(chance < .81)
						{	next = REMARK;	}
						else
						{	next = ANTE;	}
						break;
					default: next = THEVOID; break;
				}
				break;	
				
			case CHEESE:
				switch(destination)
				{
					case NORTHEAST: next = BEDQUILT; break;
					case WEST: next = EAST2PIT; break;
					case SOUTH: 
						if(chance < .81)
						{	next = REMARK;	}
						else
						{	next = TALL;	}
						break;
					case CANYON: next = TALL; break;
					case EAST: next = SOFT; break;
					case NORTHWEST: 
						if(chance < .51)
						{	next = REMARK;	}
						else
						{	next = ORIENTAL;	}
						break;
					case ORIENTAL: next = ORIENTAL; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case SOFT:
				switch(destination)
				{
					case WEST: case OUT: next = CHEESE; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case EAST2PIT:
				switch(destination)
				{
					case EAST: next = CHEESE; break;
					case WEST: case ACROSS: next = WEST2PIT; break;
					case DOWN: case PIT: next = EASTPIT; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case WEST2PIT:
				switch(destination)
				{
					case EAST: case ACROSS: next = EAST2PIT; break;
					case WEST: case SLAB: next = SLAB; break;
					case DOWN: case PIT: next = WESTPIT; break;
					default: next = THEVOID; break;
				}
				break;	

			case EASTPIT:
				switch(destination)
				{
					case UP: case OUT: next = EAST2PIT; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case WESTPIT:
				switch(destination)
				{
					case UP: case OUT: next = WEST2PIT; break;
					case CLIMB: 
						if(!(plant == 2))
						{	next = CHECK;	}
						else
						{	next = CLIMB;	}
						break;
					default: next = THEVOID; break;
				}
				break;	
				
			case NARROW:
				switch(destination)
				{
					case DOWN: case CLIMB: case EAST: next = WESTPIT; break;
					case JUMP: next = NECK; break;
					case WEST: case GIANT: next = GIANT; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case GIANT:
				switch(destination)
				{
					case SOUTH: next = NARROW; break;
					case EAST: next = BLOCK; break;
					case NORTH: next = IMMENSE; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case BLOCK:
				switch(destination)
				{
					case SOUTH: case GIANT: case OUT: next = GIANT; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case IMMENSE:
				switch(destination)
				{
					case SOUTH: case GIANT: case PASSAGE: next = GIANT; break;
					case NORTH: case ENTER: case CAVERN:
						if(oilDoor)
						{	next = FALLS;	}
						else
						{	next = REMARK;	} 
						break;
					default: next = THEVOID; break;
				}
				break;	
				
			case FALLS:
				switch(destination)
				{
					case SOUTH: case OUT: next = IMMENSE; break;
					case GIANT: next = GIANT; break;
					case WEST: next = STEEP; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case STEEP:
				switch(destination)
				{
					case NORTH: case CAVERN: case PASSAGE: next = FALLS; break;
					case DOWN: case CLIMB: next = LOW; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case ABOVEP:
				switch(destination)
				{
					case NORTH: next = SJUNC; break;
					case DOWN: case PASSAGE: next = BEDQUILT; break;
					case SOUTH: next = STALACTITE; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case SJUNC:
				switch(destination)
				{
					case SOUTHEAST: next = BEDQUILT; break;
					case SOUTH: next = ABOVEP; break;
					case NORTH: next = WESTWINDOW; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case STALACTITE:
				switch(destination)
				{
					case NORTH: next = ABOVEP; break;
					case DOWN: case JUMP: case CLIMB:
						if(chance < .41)
						{	next = ALIKE6;	}
						else if(chance > .49)
						{	next = ALIKE9;	}
						else
						{	next = ALIKE4;	} 
						break;
					default: next = THEVOID; break;
				}
				break;
				
			case LOW:
				switch(destination)
				{
					case BEDQUILT: next = BEDQUILT; break;
					case SOUTHWEST: next = SCORR; break;
					case NORTH: next = CRAWL; break;
					case SOUTHEAST: case ORIENTAL: next = ORIENTAL; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case CRAWL:
				switch(destination)
				{
					case SOUTH: case CRAWL: case OUT: next = LOW; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case WESTWINDOW:
				switch(destination)
				{
					case WEST: next = SJUNC; break;
					case JUMP: next = NECK; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case ORIENTAL:
				switch(destination)
				{
					case SOUTHEAST: next = CHEESE; break;
					case WEST: case CRAWL: next = LOW; break;
					case UP: case NORTH: case CAVERN: next = MISTY; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case MISTY:
				switch(destination)
				{
					case SOUTH: case ORIENTAL: next = ORIENTAL; break;
					case WEST: next = ALCOVE; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case ALCOVE:
				switch(destination)
				{
					case NORTHWEST: next = MISTY; break;
					case CAVERN: next = MISTY; break;
					case EAST: case PASSAGE: case PLOVER:
						if(itemsInHand > 1 || (itemsInHand > 0 && !emerald))
						{	next = REMARK;	}
						else
						{	next = PROOM;	}
						break;
					default: next = THEVOID; break;
				}
				break;	
				
			case PROOM:
				switch(destination)
				{
					case WEST: case PASSAGE: case OUT: 
						if(itemsInHand > 1 || (itemsInHand > 0 && !emerald))
						{	next = REMARK;	}
						else
						{	next = ALCOVE;	}
						break;
					case PLOVER: 
						if(emerald)
						{
							base.relocate();
						}
						next = Y2;
						break;
					case NORTHEAST: case DARK: next = DROOM; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DROOM:
				switch(destination)
				{
					case SOUTH: case PLOVER: case OUT: next = PROOM; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case SLAB:
				switch(destination)
				{
					case SOUTH: next = WEST2PIT; break;
					case UP: case CLIMB: next = ABOVER; break;
					case NORTH: next = BEDQUILT; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case ABOVER:
				switch(destination)
				{
					case DOWN: case SLAB: next = SLAB; break;
					case SOUTH: 
						if(dragon)
						{	next = SCAN1;	}
						else
						{	next = SCAN2;	}
						break;
					case NORTH: next = MIRROR; break;
					case RESERVOIR: next = RESER; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case MIRROR:
				switch(destination)
				{
					case SOUTH: next = ABOVER; break;
					case NORTH: case RESERVOIR: next = RESER; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case RESER:
				switch(destination)
				{
					case SOUTH: case OUT: next = MIRROR; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case SCAN1:
				switch(destination)
				{
					case NORTH: case OUT: next = ABOVER; break;
					case EAST: case FORWARD: next = REMARK; break;
					default: next = THEVOID; break;
				}
				break;	
				
				
			case SCAN2:
				switch(destination)
				{
					case NORTH: next = ABOVER; break;
					case EAST: next = SECRET; break;
					default: next = THEVOID; break;
				}
				break;	
				
				
			case SCAN3:
				switch(destination)
				{
					case EAST: case OUT: next = SECRET; break;
					case NORTH: case FORWARD: next = REMARK; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case SECRET:
				switch(destination)
				{
					case EAST: next = HALLOFMOUNTAINKING; break;
					case WEST:
						if(dragon)
						{	next = SCAN3;	}
						else
						{	next = SCAN2;	}
						break;
					case DOWN: next = WIDE; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case WIDE:
				switch(destination)
				{
					case SOUTH: next = TIGHT; break;
					case NORTH: next = TALL; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case TIGHT:
				switch(destination)
				{
					case NORTH: next = WIDE; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case TALL:
				switch(destination)
				{
					case EAST: next = WIDE; break;
					case WEST: next = BOULDERS; break;
					case NORTH: case CRAWL: next = CHEESE; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case BOULDERS:
				switch(destination)
				{
					case SOUTH: next = TALL; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case SCORR:
				switch(destination)
				{
					case DOWN: next = LOW; break;
					case UP: next = SWSIDE; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case SWSIDE:
				switch(destination)
				{
// TODO Fix This!!!
					case SOUTHWEST: next = SCORR; break;
					case OVER: case ACROSS: case CROSS: case NORTHEAST:
						if(troll < 2 || collapse)
						{
							next = REMARK;
						}
						else
						{	
							next = NESIDE;
							if(troll == 3)
							{
								AdventControl.setTroll();
							}
						}
						break;
					case JUMP:
						if(collapse)
						{	next = LOSE;	}
						else
						{	next = REMARK;	}
						break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DEAD0:
				switch(destination)
				{
					case SOUTH: case OUT: next = CROSS; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DEAD1:
				switch(destination)
				{
					case WEST: case OUT: next = ALIKE11; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DEAD2:
				switch(destination)
				{
					case SOUTHEAST: next = ALIKE13; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DEAD3:
				switch(destination)
				{
					case WEST: case OUT: next = ALIKE4; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DEAD4:
				switch(destination)
				{
					case EAST: case OUT: next = ALIKE4; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DEAD5:
				switch(destination)
				{
					case UP: case OUT: next = ALIKE3; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DEAD6:
				switch(destination)
				{
					case WEST: case OUT: next = ALIKE9; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DEAD7:
				switch(destination)
				{
					case UP: case OUT: next = ALIKE10; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DEAD8:
				switch(destination)
				{
					case EAST: case OUT: next = BRINK; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DEAD9:
				switch(destination)
				{
					case SOUTH: case OUT: next = ALIKE3; break;
					default: next = THEVOID; break;
				}
				break;	
				
				
			case DEAD10:
				switch(destination)
				{
					case EAST: case OUT: next = ALIKE12; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case DEAD11:
				switch(destination)
				{
					case UP: case OUT: next = ALIKE8; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case NESIDE:
// TODO Fix This!!!
				switch(destination)
				{
					case NORTHEAST: next = CORR; break;
					case OVER: case ACROSS: case CROSS: case SOUTHWEST:
						if(troll < 2)
						{
							next = REMARK;
						}
						else
						{	
							if(collapse)
							{
								next = REMARK;
							}
							else
							{
								if(troll == 3)
								{
									next = SWSIDE;
									AdventControl.setTroll();
								}
								else
								{
									next = SWSIDE;
									if(bear == 2)
									{
										AdventControl.collapse();
									}
								}
							}
						}
						break;
					case JUMP: next = REMARK; break;
					case FORK: next = FORK; break;
					case VIEW: next = VIEW; break;
					case BARREN: next = FBARR; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case CORR:
				switch(destination)
				{
					case WEST: next = NESIDE; break;
					case EAST: case FORK: next = FORK; break;
					case VIEW: next = VIEW; break;
					case BARREN: next = FBARR; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case FORK:
				switch(destination)
				{
					case WEST: next = CORR; break;
					case NORTHEAST: case LEFT: next = WARM; break;
					case SOUTHEAST: case RIGHT: case DOWN: next = LIME; break;
					case VIEW: next = VIEW; break;
					case BARREN: next = FBARR; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case WARM:
				switch(destination)
				{
					case SOUTH: case FORK: next = FORK; break;
					case NORTH: case VIEW: next = VIEW; break;
					case EAST: case CRAWL: next = CHAMBER; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case VIEW:
				switch(destination)
				{
					case SOUTH: case PASSAGE: case OUT: next = WARM; break;
					case FORK: case DOWN: next = REMARK; break;
					case JUMP: next = FORK; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case CHAMBER:
				switch(destination)
				{
					case WEST: case OUT: case CRAWL: next = WARM; break;
					case FORK: next = FORK; break;
					case VIEW: next = VIEW; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case LIME:
				switch(destination)
				{
					case NORTH: case UP: case FORK: next = FORK; break;
					case SOUTH: case DOWN: case BARREN: next = FBARR; break;
					case VIEW: next = VIEW; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case FBARR:
				switch(destination)
				{
					case WEST: case UP: next = LIME; break;
					case FORK: next = FORK; break;
					case EAST: case IN: case BARREN: case ENTER: next = BARR; break;
					case VIEW: next = VIEW; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case BARR:
				switch(destination)
				{
					case WEST: case OUT: next = FBARR; break;
					case FORK: next = FORK; break;
					case VIEW: next = VIEW; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case NEEND:
				switch(destination)
				{
					case SOUTHWEST: next = SWEND; break;
					default: next = THEVOID; break;
				}
				break;	
				
			case SWEND:
				switch(destination)
				{
					case NORTHEAST: next = NEEND; break;
//					case DOWN: next = GRATE_RMK; break;
					default: next = THEVOID; break;
				}
				break;	
				
				
				
				
			default: next = REMARK; break;
				
				
		}
		
		return next;
	}
	
	private Location atWittsEnd()
	{
		double chance = AdventControl.generate();
		Location result = REMARK;
		if(chance < .06)
		{	
			result = ANTE;	
		}
		return result;
	}
	
	private Location eastRemark(boolean bridge)
	{
		Location result = EASTFISSURE;
		if(!bridge)
		{	
			result = REMARK;	
		}
		return result;
	}
	
	private Location westRemark(boolean bridge)
	{
		Location result = WESTFISSURE;
		if(!bridge)
		{	
			result = REMARK;	
		}
		return result;
	}
	
	private Location throughGrate(boolean grate)
	{
		Location result = REMARK;
		if(grate)
		{	
			result = INSIDE;	
		}
		return result;
	}
	
	private Location backThroughGrate(boolean grate)
	{
		Location result = REMARK;
		if(grate)
		{	
			result = OUTSIDE;	
		}
		return result;
	}
	
	
}
