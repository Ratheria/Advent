package controller;

public class AdventMain13PreviewFeatures
{
    /*
    public Locations moveTo(Movement destination, Locations here)
    {
        AdventGame game = AdventMain.ADVENT;
        double chance = AdventMain.generate();
        Locations next = null;
        switch(here)
        {
            case ROAD: next = switch(destination)
                    {
                        case WEST, UP, ROAD, HILL					:	yield HILL;
                        case EAST, BUILDING, IN, ENTER				: 	yield BUILDING;
                        case SOUTH, DOWN, GULLY, STREAM, DOWNSTREAM	: 	yield VALLEY;
                        case NORTH, FOREST							:	yield FOREST;
                        case DEPRESSION								:	yield OUTSIDE;
                        default: yield THEVOID;
                    };break;

            case HILL: next = switch(destination)
                    {
                        case ROAD, BUILDING, FORWARD, EAST, DOWN	:	yield ROAD;
                        case FOREST, NORTH, SOUTH					:	yield FOREST;
                        default: yield THEVOID;
                    };break;

            case BUILDING: next = switch(destination)
                    {
                        case ENTER, OUT, OUTDOORS, WEST			:	yield ROAD;
                        case XYZZY								:	yield DEBRIS;
                        case PLUGH								:	yield Y2;
                        case DOWNSTREAM, STREAM					:	yield SEWER;
                        default: yield THEVOID;
                    };break;

            case VALLEY: next = switch(destination)
                    {
                        case UPSTREAM, BUILDING, NORTH			:	yield ROAD;
                        case FOREST, EAST, WEST, UP				:	yield FOREST;
                        case DOWNSTREAM, SOUTH, DOWN			:	yield SLIT;
                        case DEPRESSION							:	yield OUTSIDE;
                        default: yield THEVOID;
                    };break;

            case FOREST: next = switch(destination)
                    {
                        case VALLEY, EAST, DOWN					:	yield VALLEY;
                        case FOREST								:	yield (chance > .49 ? FOREST : WOODS);
                        case FORWARD							:	yield (chance < .51 ? FOREST : WOODS);
                        case NORTH								:	yield (chance > .49 ? FOREST : WOODS);
                        case WEST, SOUTH						:	yield FOREST;
                        default: yield THEVOID;
                    };break;

            case WOODS: next = switch(destination)
                    {
                        case ROAD, NORTH						:	yield ROAD;
                        case VALLEY, EAST, WEST, DOWN			:	yield VALLEY;
                        case FOREST, SOUTH						:	yield FOREST;
                        default: yield THEVOID;
                    };break;

            case SLIT: next = switch(destination)
                    {
                        case BUILDING							:	yield ROAD;
                        case UPSTREAM, UP, NORTH				:	yield VALLEY;
                        case FOREST, WEST, EAST					:	yield FOREST;
                        case DOWNSTREAM, DOWN, ROCK, BED, SOUTH	:	yield OUTSIDE;
                        case SLIT								:	yield REMARK;
                        default: yield THEVOID;
                    };break;

            case OUTSIDE: next = switch(destination)
                    {
                        case FOREST, EAST, WEST, SOUTH			:	yield FOREST;
                        case BUILDING, ROAD						:	yield ROAD;
                        case UPSTREAM, UP, GULLY, NORTH			:	yield SLIT;
                        case ENTER, IN, DOWN					: 	yield throughGrate(true);
                        default: yield THEVOID;
                    };break;

            case INSIDE: next = switch(destination)
                    {
                        case CRAWL, COBBLE, IN, WEST			:	yield COBBLES;
                        case PIT								:	yield SMALLPIT;
                        case DEBRIS								:	yield DEBRIS;
                        case OUT, UP							:	yield throughGrate(false);
                        default: yield THEVOID;
                    };break;

            case COBBLES: next = switch(destination)
                    {
                        case OUT, SURFACE, NOWHERE, EAST		:	yield INSIDE;
                        case DEBRIS, DARK, WEST, IN				:	yield DEBRIS;
                        case PIT								:	yield SMALLPIT;
                        default: yield THEVOID;
                    };break;

            case DEBRIS: next = switch(destination)
                    {
                        case DEPRESSION							:	yield throughGrate(false);
                        case ENTRANCE							:	yield INSIDE;
                        case COBBLE, CRAWL, PASSAGE, LOW, EAST	:	yield COBBLES;
                        case CANYON, IN, UP, WEST				:	yield AWKWARD;
                        case XYZZY								:	yield BUILDING;
                        case PIT								:	yield SMALLPIT;
                        default: yield THEVOID;
                    };break;

            case AWKWARD: next = switch(destination)
                    {
                        case DEPRESSION						:	yield throughGrate(false);
                        case ENTRANCE						:	yield INSIDE;
                        case DOWN, EAST, DEBRIS				:	yield DEBRIS;
                        case IN, UP, WEST					:	yield BIRD;
                        case PIT							:	yield SMALLPIT;
                        default: yield THEVOID;
                    };break;

            case BIRD: next = switch(destination)
                    {
                        case DEPRESSION						:	yield throughGrate(false);
                        case ENTRANCE						:	yield INSIDE;
                        case DEBRIS							:	yield DEBRIS;
                        case CANYON, EAST					:	yield AWKWARD;
                        case PASSAGE, PIT, WEST				:	yield SMALLPIT;
                        default: yield THEVOID;
                    };break;

            case SMALLPIT: next = switch(destination)
                    {
                        case DEPRESSION						:	yield throughGrate(false);
                        case ENTRANCE						:	yield INSIDE;
                        case DEBRIS							:	yield DEBRIS;
                        case PASSAGE, EAST					:	yield BIRD;
                        case DOWN, PIT, STEPS				:	yield (game.goldInInventory ? NECK : EASTMIST);
                        case CRACK, WEST					:	yield CRACK;
                        default: yield THEVOID;
                    };break;

            case EASTMIST: next = switch(destination)
                    {
                        case LEFT, SOUTH					:	yield NUGGET;
                        case FORWARD, HALL, WEST			:	yield EASTFISSURE;
                        case STAIRS, DOWN, NORTH			:	yield HALLOFMOUNTAINKING;
                        case UP, PIT						: 	yield (game.goldInInventory ? CANT : SMALLPIT);
                        case STEPS, DOME, PASSAGE, EAST		:	yield (game.goldInInventory ? CANT : THEVOID);
                        case Y2								:	yield JUMBLE;
                        default: yield THEVOID;
                    };break;

            case NUGGET: next = switch(destination)
                    {
                        case HALL, OUT, NORTH				:	yield EASTMIST;
                        default: yield THEVOID;
                    };break;

            case EASTFISSURE: next = switch(destination)
                    {
                        case HALL, EAST						:	yield EASTMIST;
                        case JUMP, FORWARD					:	yield ((!game.crystalBridgeIsThere) ? LOSE : THEVOID);
                        case OVER, ACROSS, WEST, CROSS		:	yield bridgeRemark(true);
                        default: yield THEVOID;
                    };break;

            case WESTFISSURE: next = switch(destination)
                    {
                        case NORTH								:	yield THRU;
                        case WEST								:	yield WESTMIST;
                        case JUMP								:	yield ((game.crystalBridgeIsThere) ? REMARK : THEVOID);
                        case FORWARD, OVER, ACROSS, EAST, CROSS	:	yield bridgeRemark(false);
                        default: yield THEVOID;
                    };break;

            case WESTMIST: next = switch(destination)
                    {
                        case SOUTH, UP, PASSAGE, CLIMB			:	yield ALIKE1;
                        case EAST								:	yield WESTFISSURE;
                        case NORTH								:	yield DUCK;
                        case WEST, CRAWL						:	yield EASTLONG;
                        default: yield THEVOID;
                    };break;

            case ALIKE1: next = switch(destination)
                    {
                        case UP			:	yield WESTMIST;
                        case NORTH		:	yield ALIKE1;
                        case EAST		:	yield ALIKE2;
                        case SOUTH		:	yield ALIKE4;
                        case WEST		:	yield ALIKE11;
                        default: yield THEVOID;
                    };break;

            case ALIKE2: next = switch(destination)
                    {
                        case EAST		:	yield ALIKE4;
                        case SOUTH		:	yield ALIKE3;
                        case WEST		:	yield ALIKE1;
                        default: yield THEVOID;
                    };break;

            case ALIKE3: next = switch(destination)
                    {
                        case DOWN		:	yield DEAD5;
                        case NORTH		:	yield DEAD9;
                        case EAST		:	yield ALIKE2;
                        case SOUTH		:	yield ALIKE6;
                        default: yield THEVOID;
                    };break;

            case ALIKE4: next = switch(destination)
                    {
                        case UP, DOWN	:	yield ALIKE14;
                        case NORTH		:	yield ALIKE2;
                        case EAST		:	yield DEAD3;
                        case SOUTH		:	yield DEAD4;
                        case WEST		:	yield ALIKE1;
                        default: yield THEVOID;
                    };break;

            case ALIKE5: next = switch(destination)
                    {
                        case EAST		:	yield ALIKE6;
                        case WEST		:	yield ALIKE7;
                        default: yield THEVOID;
                    };break;

            case ALIKE6: next = switch(destination)
                    {
                        case DOWN		:	yield ALIKE7;
                        case WEST		:	yield ALIKE5;
                        case EAST		:	yield ALIKE3;
                        case SOUTH		:	yield ALIKE8;
                        default: yield THEVOID;
                    };break;

            case ALIKE7: next = switch(destination)
                    {
                        case UP			:	yield ALIKE6;
                        case WEST		:	yield ALIKE5;
                        case EAST		:	yield ALIKE8;
                        case SOUTH		:	yield ALIKE9;
                        default: yield THEVOID;
                    };break;

            case ALIKE8: next = switch(destination)
                    {
                        case UP			:	yield ALIKE9;
                        case WEST		:	yield ALIKE6;
                        case EAST		:	yield ALIKE7;
                        case SOUTH		:	yield ALIKE8;
                        case NORTH		:	yield ALIKE10;
                        case DOWN		:	yield DEAD11;
                        default: yield THEVOID;
                    };break;

            case ALIKE9: next = switch(destination)
                    {
                        case WEST		:	yield ALIKE7;
                        case NORTH		:	yield ALIKE8;
                        case SOUTH		:	yield DEAD6;
                        default: yield THEVOID;
                    };break;

            case ALIKE10: next = switch(destination)
                    {
                        case NORTH		:	yield ALIKE10;
                        case WEST		:	yield ALIKE8;
                        case EAST		:	yield BRINK;
                        case DOWN		:	yield DEAD7;
                        default: yield THEVOID;
                    };break;

            case ALIKE11: next = switch(destination)
                    {
                        case NORTH		:	yield ALIKE1;
                        case EAST		:	yield DEAD1;
                        case SOUTH, WEST:	yield ALIKE11;
                        default: yield THEVOID;
                    };break;

            case ALIKE12: next = switch(destination)
                    {
                        case WEST		:	yield DEAD10;
                        case EAST		:	yield ALIKE13;
                        case SOUTH		:	yield BRINK;
                        default: yield THEVOID;
                    };break;

            case ALIKE13: next = switch(destination)
                    {
                        case NORTH		:	yield BRINK;
                        case WEST		:	yield ALIKE12;
                        case NORTHWEST	:	yield DEAD2;
                        default: yield THEVOID;
                    };break;

            case ALIKE14: next = switch(destination)
                    {
                        case UP, DOWN	:	yield ALIKE4;
                        default: yield THEVOID;
                    };break;

            case BRINK: next = switch(destination)
                    {
                        case DOWN, CLIMB:	yield BIRD;
                        case WEST		:	yield ALIKE10;
                        case SOUTH		:	yield DEAD8;
                        case NORTH		:	yield ALIKE12;
                        case EAST		:	yield ALIKE13;
                        default: yield THEVOID;
                    };break;

            case EASTLONG: next = switch(destination)
                    {
                        case EAST, UP, CRAWL	:	yield WESTMIST;
                        case WEST				:	yield WESTLONG;
                        case NORTH, DOWN, HOLE	:	yield CROSS;
                        default: yield THEVOID;
                    };break;

            case WESTLONG: next = switch(destination)
                    {
                        case NORTH		:	yield CROSS;
                        case EAST		:	yield EASTLONG;
                        case SOUTH		:	yield DIFF0;
                        default: yield THEVOID;
                    };break;

            case DIFF0: next = switch(destination)
                    {
                        case SOUTH		:	yield DIFF1;
                        case SOUTHWEST	:	yield DIFF2;
                        case NORTHEAST	:	yield DIFF3;
                        case SOUTHEAST	:	yield DIFF4;
                        case UP			:	yield DIFF5;
                        case NORTHWEST	:	yield DIFF6;
                        case EAST		:	yield DIFF7;
                        case WEST		:	yield DIFF8;
                        case NORTH		:	yield DIFF9;
                        case DOWN		:	yield WESTLONG;
                        default: yield THEVOID;
                    };break;

            case DIFF1: next = switch(destination)
                    {
                        case WEST		:	yield DIFF0;
                        case SOUTHEAST	:	yield DIFF1;
                        case NORTHWEST	:	yield DIFF3;
                        case SOUTHWEST	:	yield DIFF4;
                        case NORTHEAST	:	yield DIFF5;
                        case UP			:	yield DIFF6;
                        case DOWN		:	yield DIFF7;
                        case NORTH		:	yield DIFF8;
                        case SOUTH		:	yield DIFF9;
                        case EAST		:	yield DIFF10;
                        default: yield THEVOID;
                    };break;

            case DIFF2: next = switch(destination)
                    {
                        case NORTHWEST	:	yield DIFF0;
                        case UP			:	yield DIFF1;
                        case NORTH		:	yield DIFF3;
                        case SOUTH		:	yield DIFF4;
                        case WEST		:	yield DIFF5;
                        case SOUTHWEST	:	yield DIFF6;
                        case NORTHEAST	:	yield DIFF7;
                        case EAST		:	yield DIFF8;
                        case DOWN		:	yield DIFF9;
                        case SOUTHEAST	:	yield DIFF10;
                        default: yield THEVOID;
                    };break;

            case DIFF3: next = switch(destination)
                    {
                        case UP			:	yield DIFF0;
                        case DOWN		:	yield DIFF1;
                        case WEST		:	yield DIFF2;
                        case NORTHEAST	:	yield DIFF4;
                        case SOUTHWEST	:	yield DIFF5;
                        case EAST		:	yield DIFF6;
                        case NORTH		:	yield DIFF7;
                        case NORTHWEST	:	yield DIFF8;
                        case SOUTHEAST	:	yield DIFF9;
                        case SOUTH		:	yield DIFF10;
                        default: yield THEVOID;
                    };break;

            case DIFF4: next = switch(destination)
                    {
                        case NORTHEAST	:	yield DIFF0;
                        case NORTH		:	yield DIFF1;
                        case NORTHWEST	:	yield DIFF2;
                        case SOUTHEAST	:	yield DIFF3;
                        case EAST		:	yield DIFF5;
                        case DOWN		:	yield DIFF6;
                        case SOUTH		:	yield DIFF7;
                        case UP			:	yield DIFF8;
                        case WEST		:	yield DIFF9;
                        case SOUTHWEST	:	yield DIFF10;
                        default: yield THEVOID;
                    };break;

            case DIFF5: next = switch(destination)
                    {
                        case NORTH		:	yield DIFF0;
                        case SOUTHEAST	:	yield DIFF1;
                        case DOWN		:	yield DIFF2;
                        case SOUTH		:	yield DIFF3;
                        case EAST		:	yield DIFF4;
                        case WEST		:	yield DIFF6;
                        case SOUTHWEST	:	yield DIFF7;
                        case NORTHEAST	:	yield DIFF8;
                        case NORTHWEST	:	yield DIFF9;
                        case UP			:	yield DIFF10;
                        default: yield THEVOID;
                    };break;

            case DIFF6: next = switch(destination)
                    {
                        case EAST		:	yield DIFF0;
                        case WEST		:	yield DIFF1;
                        case UP			:	yield DIFF2;
                        case SOUTHWEST	:	yield DIFF3;
                        case DOWN		:	yield DIFF4;
                        case SOUTH		:	yield DIFF5;
                        case NORTHWEST	:	yield DIFF7;
                        case SOUTHEAST	:	yield DIFF8;
                        case NORTHEAST	:	yield DIFF9;
                        case NORTH		:	yield DIFF10;
                        default: yield THEVOID;
                    };break;

            case DIFF7: next = switch(destination)
                    {
                        case EAST		:	yield DIFF0;
                        case WEST		:	yield DIFF1;
                        case UP			:	yield DIFF2;
                        case SOUTHWEST	:	yield DIFF3;
                        case DOWN		:	yield DIFF4;
                        case SOUTH		:	yield DIFF5;
                        case NORTHWEST	:	yield DIFF6;
                        case SOUTHEAST	:	yield DIFF8;
                        case NORTHEAST	:	yield DIFF9;
                        case NORTH		:	yield DIFF10;
                        default: yield THEVOID;
                    };break;

            case DIFF8: next = switch(destination)
                    {
                        case DOWN		:	yield DIFF0;
                        case EAST		:	yield DIFF1;
                        case NORTHEAST	:	yield DIFF2;
                        case UP			:	yield DIFF3;
                        case WEST		:	yield DIFF4;
                        case NORTH		:	yield DIFF5;
                        case SOUTH		:	yield DIFF6;
                        case SOUTHEAST	:	yield DIFF7;
                        case SOUTHWEST	:	yield DIFF9;
                        case NORTHWEST	:	yield DIFF10;
                        default: yield THEVOID;
                    };break;

            case DIFF9: next = switch(destination)
                    {
                        case SOUTHWEST	:	yield DIFF0;
                        case NORTHWEST	:	yield DIFF1;
                        case EAST		:	yield DIFF2;
                        case WEST		:	yield DIFF3;
                        case NORTH		:	yield DIFF4;
                        case DOWN		:	yield DIFF5;
                        case SOUTHEAST	:	yield DIFF6;
                        case UP			:	yield DIFF7;
                        case SOUTH		:	yield DIFF8;
                        case NORTHEAST	:	yield DIFF10;
                        default: yield THEVOID;
                    };break;

            case DIFF10: next = switch(destination)
                    {
                        case SOUTHWEST	:	yield DIFF1;
                        case NORTH		:	yield DIFF2;
                        case EAST		:	yield DIFF3;
                        case NORTHWEST	:	yield DIFF4;
                        case SOUTHEAST	:	yield DIFF5;
                        case NORTHEAST	:	yield DIFF6;
                        case WEST		:	yield DIFF7;
                        case DOWN		:	yield DIFF8;
                        case UP			:	yield DIFF9;
                        case SOUTH		:	yield PONY;
                        default: yield THEVOID;
                    };break;

            case PONY: next = switch(destination)
                    {
                        case NORTH, OUT	:	yield DIFF10;
                        default: yield THEVOID;
                    };break;

            case CROSS: next = switch(destination)
                    {
                        case WEST				:	yield EASTLONG;
                        case NORTH				:	yield DEAD0;
                        case EAST				:	yield WEST;
                        case SOUTH				:	yield WESTLONG;
                        default: yield THEVOID;
                    };break;

            case HALLOFMOUNTAINKING: next = switch(destination)
                    {
                        case STAIRS, UP, EAST	:	yield EASTMIST;
                        case NORTH, LEFT		:	yield (game.snakeInHotMK ? REMARK : NS);
                        case SOUTH, RIGHT		:	yield (game.snakeInHotMK ? REMARK : SOUTH);
                        case WEST, FORWARD		:	yield (game.snakeInHotMK ? REMARK : WEST);
                        case SOUTHWEST			:	yield (game.snakeInHotMK ? ( Math.random() < .36 ? SECRET : REMARK ) : SECRET);
                        case SECRET				:	yield SECRET;
                        default: yield THEVOID;
                    };break;

            case WEST: next = switch(destination)
                    {
                        case HALL, OUT, EAST	:	yield HALLOFMOUNTAINKING;
                        case WEST, UP			:	yield CROSS;
                        default: yield THEVOID;
                    };break;

            case SOUTH: next = switch(destination)
                    {
                        case HALL, OUT, NORTH	:	yield HALLOFMOUNTAINKING;
                        default: yield THEVOID;
                    };break;

            case NS: next = switch(destination)
                    {
                        case HALL, OUT, SOUTH	:	yield HALLOFMOUNTAINKING;
                        case Y2, NORTH			:	yield Y2;
                        case DOWN, HOLE			:	yield DIRTY;
                        default: yield THEVOID;
                    };break;

            case Y2: next = switch(destination)
                    {
                        case PLUGH				:	yield BUILDING;
                        case SOUTH				:	yield NS;
                        case EAST, WALL, BROKEN	:	yield JUMBLE;
                        case WEST				:	yield EASTWINDOW;
                        case PLOVER				:	yield PROOM;
                        default: yield THEVOID;
                    };break;

            case JUMBLE: next = switch(destination)
                    {
                        case DOWN, Y2			:	yield Y2;
                        case UP					:	yield EASTMIST;
                        default: yield THEVOID;
                    };break;

            case EASTWINDOW: next = switch(destination)
                    {
                        case EAST, Y2			:	yield Y2;
                        case JUMP				:	yield NECK;
                        default: yield THEVOID;
                    };break;

            case DIRTY: next = switch(destination)
                    {
                        case EAST, CRAWL		:	yield CLEAN;
                        case UP, HOLE			:	yield NS;
                        case WEST				:	yield DUSTY;
                        case BEDQUILT			:	yield BEDQUILT;
                        default: yield THEVOID;
                    };break;

            case CLEAN: next = switch(destination)
                    {
                        case WEST, CRAWL		:	yield DIRTY;
                        case DOWN, PIT, CLIMB	:	yield WET;
                        default: yield THEVOID;
                    };break;

            case WET: next = switch(destination)
                    {
                        case CLIMB, UP, OUT		:	yield CLEAN;
                        case SLIT, STREAM, DOWN, UPSTREAM, DOWNSTREAM:	yield REMARK;
                        default: yield THEVOID;
                    };break;

            case DUSTY: next = switch(destination)
                    {
                        case EAST, PASSAGE		:	yield DIRTY;
                        case DOWN, HOLE, FLOOR	:	yield COMPLEX;
                        case BEDQUILT			:	yield BEDQUILT;
                        default: yield THEVOID;
                    };break;

            case COMPLEX: next = switch(destination)
                    {
                        case UP, CLIMB, ROOM	:	yield DUSTY;
                        case WEST, BEDQUILT		:	yield BEDQUILT;
                        case NORTH, SHELL		:	yield SHELL;
                        case EAST				:	yield ANTE;
                        default: yield THEVOID;
                    };break;

            case SHELL: next = switch(destination)
                    {
                        case UP, HALL			:	yield ARCH;
                        case DOWN				:	yield RAGGED;
                        case WEST				:	yield BEDQUILT;
                        case SOUTH				:	yield ((game.isInHand(GameObjects.CLAM)||game.isInHand(GameObjects.OYSTER)) ? REMARK : COMPLEX);
                        default: yield THEVOID;
                    };break;

            case ARCH: next = switch(destination)
                    {
                        case DOWN, SHELL, OUT	:	yield SHELL;
                        default: yield THEVOID;
                    };break;

            case RAGGED: next = switch(destination)
                    {
                        case UP, SHELL			:	yield SHELL;
                        case DOWN				:	yield CULDESAC;
                        default: yield THEVOID;
                    };break;

            case CULDESAC: next = switch(destination)
                    {
                        case UP, OUT			:	yield RAGGED;
                        case SHELL				:	yield SHELL;
                        default: yield THEVOID;
                    };break;

            case ANTE: next = switch(destination)
                    {
                        case UP					:	yield COMPLEX;
                        case WEST				:	yield BEDQUILT;
                        case EAST				:	yield WITT;
                        default: yield THEVOID;
                    };break;

            case WITT: next = switch(destination)
                    {
                        case EAST, NORTH, SOUTH, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST, UP, DOWN:	yield atWittsEnd();
                        case WEST			:	yield REMARK;
                        default: yield THEVOID;
                    };break;

            case BEDQUILT: next = switch(destination)
                    {
                        case EAST			:	yield COMPLEX;
                        case WEST			:	yield CHEESE;
                        case SOUTH			:	yield (chance < .81 ? REMARK : SLAB);
                        case SLAB			:	yield SLAB;
                        case UP				:	yield (chance < .81 ? REMARK : ( chance > .49 ? ABOVEP : DUSTY));
                        case NORTH			:	yield (chance < .61 ? REMARK : ( chance < .76 ? LOW : SJUNC));
                        case DOWN			: 	yield (chance < .81 ? REMARK : ANTE);
                        default: yield THEVOID;
                    };break;

            case CHEESE: next = switch(destination)
                    {
                        case NORTHEAST		:	yield BEDQUILT;
                        case WEST			:	yield EAST2PIT;
                        case SOUTH			:	yield (chance < .81 ? REMARK : TALL);
                        case CANYON			:	yield TALL;
                        case EAST			:	yield SOFT;
                        case NORTHWEST		:	yield (chance < .51 ? REMARK : ORIENTAL);
                        case ORIENTAL		:	yield ORIENTAL;
                        default: yield THEVOID;
                    };break;

            case SOFT: next = switch(destination)
                    {
                        case WEST, OUT		:	yield CHEESE;
                        default: yield THEVOID;
                    };break;

            case EAST2PIT: next = switch(destination)
                    {
                        case EAST			:	yield CHEESE;
                        case WEST, ACROSS	:	yield WEST2PIT;
                        case DOWN, PIT		:	yield EASTPIT;
                        default: yield THEVOID;
                    };break;

            case WEST2PIT: next = switch(destination)
                    {
                        case EAST, ACROSS	:	yield EAST2PIT;
                        case WEST, SLAB		:	yield SLAB;
                        case DOWN, PIT		:	yield WESTPIT;
                        default: yield THEVOID;
                    };break;

            case EASTPIT: next = switch(destination)
                    {
                        case UP, OUT			:	yield EAST2PIT;
                        default: yield THEVOID;
                    };break;

            case WESTPIT: next = switch(destination)
                    {
                        case UP, OUT			:	yield WEST2PIT;
                        case CLIMB				:	yield ((!(game.stateOfThePlant == 2)) ? CHECK : CLIMB);
                        default: yield THEVOID;
                    };break;

            case NARROW: next = switch(destination)
                    {
                        case DOWN, CLIMB, EAST	:	yield WESTPIT;
                        case JUMP				:	yield NECK;
                        case WEST, GIANT		:	yield GIANT;
                        default: yield THEVOID;
                    };break;

            case GIANT: next = switch(destination)
                    {
                        case SOUTH				:	yield NARROW;
                        case EAST				:	yield BLOCK;
                        case NORTH				:	yield IMMENSE;
                        default: yield THEVOID;
                    };break;

            case BLOCK: next = switch(destination)
                    {
                        case SOUTH, GIANT, OUT	:	yield GIANT;
                        default: yield THEVOID;
                    };break;

            case IMMENSE: next = switch(destination)
                    {
                        case SOUTH, GIANT, PASSAGE	:	yield GIANT;
                        case NORTH, ENTER, CAVERN	:	yield (game.doorHasBeenOiled ? FALLS : REMARK);
                        default: yield THEVOID;
                    };break;

            case FALLS: next = switch(destination)
                    {
                        case SOUTH, OUT				:	yield IMMENSE;
                        case GIANT					:	yield GIANT;
                        case WEST					:	yield STEEP;
                        default: yield THEVOID;
                    };break;

            case STEEP: next = switch(destination)
                    {
                        case NORTH, CAVERN, PASSAGE	:	yield FALLS;
                        case DOWN, CLIMB			:	yield LOW;
                        default: yield THEVOID;
                    };break;

            case ABOVEP: next = switch(destination)
                    {
                        case NORTH					:	yield SJUNC;
                        case DOWN, PASSAGE			:	yield BEDQUILT;
                        case SOUTH					:	yield STALACTITE;
                        default: yield THEVOID;
                    };break;

            case SJUNC: next = switch(destination)
                    {
                        case SOUTHEAST				:	yield BEDQUILT;
                        case SOUTH					:	yield ABOVEP;
                        case NORTH					:	yield WESTWINDOW;
                        default: yield THEVOID;
                    };break;

            case STALACTITE: next = switch(destination)
                    {
                        case NORTH					:	yield ABOVEP;
                        case DOWN, JUMP, CLIMB		:	yield (chance < .41 ? ALIKE6 : ( chance > .49 ? ALIKE9 : ALIKE4 ));
                        default: yield THEVOID;
                    };break;

            case LOW: next = switch(destination)
                    {
                        case BEDQUILT				:	yield BEDQUILT;
                        case SOUTHWEST				:	yield SCORR;
                        case NORTH					:	yield CRAWL;
                        case SOUTHEAST, ORIENTAL	:	yield ORIENTAL;
                        default: yield THEVOID;
                    };break;

            case CRAWL: next = switch(destination)
                    {
                        case SOUTH, CRAWL, OUT		:	yield LOW;
                        default: yield THEVOID;
                    };break;

            case WESTWINDOW: next = switch(destination)
                    {
                        case WEST					:	yield SJUNC;
                        case JUMP					:	yield NECK;
                        default: yield THEVOID;
                    };break;

            case ORIENTAL: next = switch(destination)
                    {
                        case SOUTHEAST				:	yield CHEESE;
                        case WEST, CRAWL			:	yield LOW;
                        case UP, NORTH, CAVERN		:	yield MISTY;
                        default: yield THEVOID;
                    };break;

            case MISTY: next = switch(destination)
                    {
                        case SOUTH, ORIENTAL		:	yield ORIENTAL;
                        case WEST					:	yield ALCOVE;
                        default: yield THEVOID;
                    };break;

            case ALCOVE: next = switch(destination)
                    {
                        case NORTHWEST, CAVERN		:	yield MISTY;
                        case EAST, PASSAGE, PLOVER	:	yield ((game.itemsInHand > 1 || (game.itemsInHand > 0 && !game.isInHand(GameObjects.EMERALD))) ? REMARK : PROOM);
                        default: yield THEVOID;
                    };break;

            case PROOM: next = switch(destination)
                    {
                        case WEST, PASSAGE, OUT	:	yield ((game.itemsInHand > 1 || (game.itemsInHand > 0 && !game.isInHand(GameObjects.EMERALD))) ? REMARK : ALCOVE);
                        case PLOVER:
                            if(game.isInHand(GameObjects.EMERALD)){ game.relocate = true; }
                            yield Y2;
                        case NORTHEAST, DARK	:	yield DROOM;
                        default: yield THEVOID;
                    };break;

            case DROOM: next = switch(destination)
                    {
                        case SOUTH, PLOVER, OUT	:	yield PROOM;
                        default: yield THEVOID;
                    };break;

            case SLAB: next = switch(destination)
                    {
                        case SOUTH				:	yield WEST2PIT;
                        case UP, CLIMB			:	yield ABOVER;
                        case NORTH				:	yield BEDQUILT;
                        default: yield THEVOID;
                    };break;

            case ABOVER: next = switch(destination)
                    {
                        case DOWN, SLAB			:	yield SLAB;
                        case SOUTH				:	yield (game.dragonIsAlive ? SCAN1 : SCAN2);
                        case NORTH				:	yield MIRROR;
                        case RESERVOIR			:	yield RESER;
                        default: yield THEVOID;
                    };break;

            case MIRROR: next = switch(destination)
                    {
                        case SOUTH				:	yield ABOVER;
                        case NORTH, RESERVOIR	:	yield RESER;
                        default: yield THEVOID;
                    };break;

            case RESER: next = switch(destination)
                    {
                        case SOUTH, OUT			:	yield MIRROR;
                        default: yield THEVOID;
                    };break;

            case SCAN1: next = switch(destination)
                    {
                        case NORTH, OUT			:	yield ABOVER;
                        case EAST, FORWARD		:	yield REMARK;
                        default: yield THEVOID;
                    };break;

            case SCAN2: next = switch(destination)
                    {
                        case NORTH				:	yield ABOVER;
                        case EAST				:	yield SECRET;
                        default: yield THEVOID;
                    };break;

            case SCAN3: next = switch(destination)
                    {
                        case EAST, OUT			:	yield SECRET;
                        case NORTH, FORWARD		:	yield REMARK;
                        default: yield THEVOID;
                    };break;

            case SECRET: next = switch(destination)
                    {
                        case EAST				:	yield HALLOFMOUNTAINKING;
                        case WEST				:	yield (game.dragonIsAlive ? SCAN3 : SCAN2);
                        case DOWN				:	yield WIDE;
                        default: yield THEVOID;
                    };break;

            case WIDE: next = switch(destination)
                    {
                        case SOUTH				:	yield TIGHT;
                        case NORTH				:	yield TALL;
                        default: yield THEVOID;
                    };break;

            case TIGHT: next = switch(destination)
                    {
                        case NORTH				:	yield WIDE;
                        default: yield THEVOID;
                    };break;

            case TALL: next = switch(destination)
                    {
                        case EAST				:	yield WIDE;
                        case WEST				:	yield BOULDERS;
                        case NORTH, CRAWL		:	yield CHEESE;
                        default: yield THEVOID;
                    };break;

            case BOULDERS: next = switch(destination)
                    {
                        case SOUTH				:	yield TALL;
                        default: yield THEVOID;
                    };break;

            case SCORR: next = switch(destination)
                    {
                        case DOWN				:	yield LOW;
                        case UP					:	yield SWSIDE;
                        default: yield THEVOID;
                    };break;

            case SWSIDE: next = switch(destination)
                    {
                        case SOUTHWEST			:	yield SCORR;
                        case OVER, ACROSS, CROSS, NORTHEAST:
                            if(game.stateOfTheTroll < 2 || game.collapse){	yield REMARK;	}
                            else
                            {
                                if(game.stateOfTheTroll == 3){ game.setTroll(); }
                                yield NESIDE;
                            }
                        case JUMP			:	yield (game.collapse ? LOSE : REMARK);
                        default: yield THEVOID;
                    };break;

            case DEAD0: next = switch(destination)
                    {
                        case SOUTH, OUT		:	yield CROSS;
                        default: yield THEVOID;
                    };break;

            case DEAD1: next = switch(destination)
                    {
                        case WEST, OUT		:	yield ALIKE11;
                        default: yield THEVOID;
                    };break;

            case DEAD2: next = switch(destination)
                    {
                        case SOUTHEAST		:	yield ALIKE13;
                        default: yield THEVOID;
                    };break;

            case DEAD3: next = switch(destination)
                    {
                        case WEST, OUT		:	yield ALIKE4;
                        default: yield THEVOID;
                    };break;

            case DEAD4: next = switch(destination)
                    {
                        case EAST, OUT		:	yield ALIKE4;
                        default: yield THEVOID;
                    };break;

            case DEAD5: next = switch(destination)
                    {
                        case UP, OUT		:	yield ALIKE3;
                        default: yield THEVOID;
                    };break;

            case DEAD6: next = switch(destination)
                    {
                        case WEST, OUT		:	yield ALIKE9;
                        default: yield THEVOID;
                    };break;

            case DEAD7: next = switch(destination)
                    {
                        case UP, OUT		: 	yield ALIKE10;
                        default: yield THEVOID;
                    };break;

            case DEAD8: next = switch(destination)
                    {
                        case EAST, OUT		:	yield BRINK;
                        default: yield THEVOID;
                    };break;

            case DEAD9: next = switch(destination)
                    {
                        case SOUTH, OUT		:	yield ALIKE3;
                        default: yield THEVOID;
                    };break;


            case DEAD10: next = switch(destination)
                    {
                        case EAST, OUT		:	yield ALIKE12;
                        default: yield THEVOID;
                    };break;

            case DEAD11: next = switch(destination)
                    {
                        case UP, OUT		:	yield ALIKE8;
                        default: yield THEVOID;
                    };break;

            case NESIDE: next = switch(destination)
                    {
                        case NORTHEAST	:	yield CORR;
                        case OVER, ACROSS, CROSS, SOUTHWEST:
                            //System.out.println("troll " + game.stateOfTheTroll);
                            if(game.stateOfTheTroll == 0 || game.stateOfTheTroll == 1){ yield REMARK; }
                            else
                            {
                                if(game.collapse){ yield REMARK; }
                                else
                                {
                                    if(game.stateOfTheTroll == 3) { game.stateOfTheTroll = 1; }
                                    else { if(game.stateOfTheBear == 2){ game.collapse(); } }
                                    yield SWSIDE;
                                }
                            }
                        case JUMP		:	yield REMARK;
                        case FORK		:	yield FORK;
                        case VIEW		:	yield VIEW;
                        case BARREN		:	yield FBARR;
                        default: yield THEVOID;
                    };break;

            case CORR: next = switch(destination)
                    {
                        case WEST						:	yield NESIDE;
                        case EAST, FORK					:	yield FORK;
                        case VIEW						:	yield VIEW;
                        case BARREN						:	yield FBARR;
                        default: yield THEVOID;
                    };break;

            case FORK: next = switch(destination)
                    {
                        case WEST						:	yield CORR;
                        case NORTHEAST, LEFT			:	yield WARM;
                        case SOUTHEAST, RIGHT, DOWN		:	yield LIME;
                        case VIEW						:	yield VIEW;
                        case BARREN						:	yield FBARR;
                        default: yield THEVOID;
                    };break;

            case WARM: next = switch(destination)
                    {
                        case SOUTH, FORK				:	yield FORK;
                        case NORTH, VIEW				:	yield VIEW;
                        case EAST, CRAWL				:	yield CHAMBER;
                        default: yield THEVOID;
                    };break;

            case VIEW: next = switch(destination)
                    {
                        case SOUTH, PASSAGE, OUT		:	yield WARM;
                        case FORK, DOWN					:	yield REMARK;
                        case JUMP						:	yield FORK;
                        default: yield THEVOID;
                    };break;

            case CHAMBER: next = switch(destination)
                    {
                        case WEST, OUT, CRAWL			:	yield WARM;
                        case FORK						:	yield FORK;
                        case VIEW						:	yield VIEW;
                        default: yield THEVOID;
                    };break;

            case LIME: next = switch(destination)
                    {
                        case NORTH, UP, FORK			:	yield FORK;
                        case SOUTH, DOWN, BARREN		:	yield FBARR;
                        case VIEW						:	yield VIEW;
                        default: yield THEVOID;
                    };break;

            case FBARR: next = switch(destination)
                    {
                        case WEST, UP					:	yield LIME;
                        case FORK						:	yield FORK;
                        case EAST, IN, BARREN, ENTER	:	yield BARR;
                        case VIEW						:	yield VIEW;
                        default: yield THEVOID;
                    };break;

            case BARR: next = switch(destination)
                    {
                        case WEST, OUT					:	yield FBARR;
                        case FORK						:	yield FORK;
                        case VIEW						:	yield VIEW;
                        default: yield THEVOID;
                    };break;

            case NEEND: next = switch(destination)
                    {
                        case SOUTHWEST					:	yield SWEND;
                        default: yield THEVOID;
                    };break;

            case SWEND: next = switch(destination)
                    {
                        case NORTHEAST					:	yield NEEND;
//						case DOWN						:	yield GRATE_RMK;
                        default: yield THEVOID;
                    };break;

            default: next = REMARK;	break;
        }
        return next;
    }
    */
}
