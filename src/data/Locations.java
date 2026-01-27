package data;

import controller.*;

import static controller.AdventMain.*;
import static data.GameObjects.*;
import static data.Communication.*;

public enum Locations
{
    VOID(), INHAND(),
    ROAD	( true,	"End Of Road",              "You're at end of road again.",         "You are standing at the end of a road before a small brick building. Around you is a forest. A small stream flows out of the building and down a gully."	),
    HILL	(       "Hill",                     "You're at hill in road.",              "You have walked up a hill, still in the forest. The road slopes back down the other side of the hill. There is a building in the distance."	),
    BUILDING( true,	"Building",                 "You're inside building.",              "You are inside a building, a well house for a large spring."	),
    VALLEY	( true,	"Valley",                   "You're in valley.",                    "You are in a valley in the forest beside a stream tumbling along a rocky bed."	),
    FOREST	(	    "Forest",                   "You're in forest.",                    "You are in open forest, with a deep valley to one side."	),
    WOODS	(	    "Forest",                   "You're in forest near a road.",        "You are in open forest near both a valley and a road."	),
    SLIT	( true,	"Slit",                     "You're at slit in streambed.",         "At your feet all the water of the stream splashes into a 2-inch slit in the rock. Downstream the streambed is bare rock."	),
    OUTSIDE	(	    "Outside Grate",            "You're outside grate.",                "You are in a 20-foot depression floored with bare dirt. Set into the dirt is a strong steel grate mounted in concrete. A dry streambed leads into the depression."	),

    INSIDE	(	    "Below Grate",              "You're below the grate.",              "You are in a small chamber beneath a 3x3 steel grate to the surface. A low crawl over cobbles leads inward to the west."	),
    COBBLES	(	    "Cobble Crawl",             "You're in cobble crawl.",              "You are crawling over cobbles in a low passage. There is a dim light at the east end of the passage."	),
    DEBRIS	(	    "Debris Room",              "You're in debris room.",               "You are in a debris room filled with stuff washed in from the surface. A low wide passage with cobbles becomes plugged with mud and debris here, but an awkward canyon leads upward and west. \nA note on the wall says:\n\tMagic Word \"XYZZY\""	),
    AWKWARD	(	    "Awkward Canyon",           "You are in an awkward sloping east/west canyon."	),
    BIRD	(	    "Bird Chamber",             "You're in bird chamber.",              "You are in a splendid chamber thirty feet high. The walls are frozen rivers of orange stone. An awkward canyon and a good passage exit from east and west sides of the chamber."	),
    SMALLPIT(	    "Small Pit",                "You're at top of small pit.",          "At your feet is a small pit breathing traces of white mist. An east passage ends here except for a small crack leading on."	),

    EASTMIST(	    "Hall Of Mists",            "You're in Hall of Mists.",             "You are at one end of a vast hall stretching forward out of sight to the west. There are openings to either side. Nearby, a wide stone staircase leads downward. The hall is filled with wisps of white mist swaying to and fro almost as if alive. A cold wind blows up the staircase. There is a passage at the top of a dome behind you."	),
    NUGGET	(	    "Nugget Of Gold Room",      "You're in nugget of gold room.",       "This is a low room with a crude note on the wall. The note says, \n\t\"You won't get it up the steps\"."	),
    EASTFISSURE(    "East Bank",                "You're on east bank of fissure.",      "You are on the east bank of a fissure slicing clear across the hall. The mist is quite thick here, and the fissure is too wide to jump."	),
    WESTFISSURE(    "West Bank",                "You're on the west side of the fissure in the Hall of Mists."	),
    WESTMIST(	    "Hall Of Mists",            "You're at west end of Hall of Mists.", "You are at the west end of Hall of Mists. A low wide crawl continues west and another goes north. To the south is a little passage 6 feet off the floor."	),

    ALIKE1(ALIKE_T, ALIKE_PASSAGES),	ALIKE2(ALIKE_T, ALIKE_PASSAGES),
    ALIKE3(ALIKE_T, ALIKE_PASSAGES),	ALIKE4(ALIKE_T, ALIKE_PASSAGES),	ALIKE5(ALIKE_T, ALIKE_PASSAGES),
    ALIKE6(ALIKE_T, ALIKE_PASSAGES),	ALIKE7(ALIKE_T, ALIKE_PASSAGES),	ALIKE8(ALIKE_T, ALIKE_PASSAGES),    ALIKE9(ALIKE_T, ALIKE_PASSAGES),	ALIKE10(ALIKE_T, ALIKE_PASSAGES),
    ALIKE11(ALIKE_T, ALIKE_PASSAGES),	ALIKE12(ALIKE_T, ALIKE_PASSAGES),	ALIKE13(ALIKE_T, ALIKE_PASSAGES),   ALIKE14(ALIKE_T, ALIKE_PASSAGES),

    BRINK	(	    "Brink Of Pit",             "You're at brink of pit.",              "You are on the brink of a thirty-foot pit with a massive orange column down one wall. You could climb down here but you could not get back up. The maze continues at this level."	),
    EASTLONG(	    "Long Hall East",           "You are at east end of long hall.",    "You are at the east end of a very long hall apparently without side chambers. To the east a low wide crawl slants up. To the north a round two foot hole slants down."	),
    WESTLONG(	    "Long Hall West",           "You are at west end of long hall.",    "You are at the west end of a very long featureless hall. The hall joins up with a narrow north/south passage."	),

    DIFF0	(DIFF_T, "You are in a maze of twisty little passages, all different."),
    DIFF1	(DIFF_T, "You are in a maze of twisting little passages, all different."),
    DIFF2	(DIFF_T, "You are in a little maze of twisty passages, all different."),
    DIFF3	(DIFF_T, "You are in a twisting maze of little passages, all different."),
    DIFF4	(DIFF_T, "You are in a twisting little maze of passages, all different."),
    DIFF5	(DIFF_T, "You are in a twisty little maze of passages, all different."),
    DIFF6	(DIFF_T, "You are in a twisty maze of little passages, all different."),
    DIFF7	(DIFF_T, "You are in a little twisty maze of passages, all different."),
    DIFF8	(DIFF_T, "You are in a maze of little twisting passages, all different."),
    DIFF9	(DIFF_T, "You are in a maze of little twisty passages, all different."),
    DIFF10	(DIFF_T, "You are in a little maze of twisting passages, all different."),
    PONY	(DEAD_END_T, DEAD_END),

    CROSS	(	    "Crossover",                "You are at a crossover of a high N/S passage and a low E/W one."	),
    MTKHALL (       "Hall Of The Mt. King",     "You're in Hall of Mt King.",           "You are in the Hall of the Mountain King, with passages off in all directions."	),
    WEST	(	    "West Side Chamber",        "You're in west side chamber.",         "You are in the west side chamber of the Hall of the Mountain King. A passage continues west and up here."	),
    SOUTH	(	    "South Side Chamber",       "You are in the south side chamber."),
    NS		(	    "North/South Passage",      "You're in a N/S passage.",             "You are in a low N/S passage at a hole in the floor. The hole goes down to an E/W passage."	),
    Y2		(	    "\"Y2\"",                   "You're at \"Y2\".",                    "You are in a large room, with a passage to the south, a passage to the west, and a wall of broken rock to the east. There is a large \"Y2\" on a rock in the room's center."	),
    JUMBLE	(	    "Rock Jumble",              "You are in a jumble of rock, with cracks everywhere."	),
    EWINDOW (       "Window",                   "You're at window on pit.",             "You're at a low window overlooking a huge pit, which extends up out of sight.  A floor is indistinctly visible over 50 feet below. Traces of white mist cover the floor of the pit, becoming thicker to the right. Marks in the dust around the window would seem to indicate that someone has been here recently. Directly across the pit from you and 25 feet away there is a similar window looking into a lighted room. A shadowy figure can be seen there peering back at you."	),

    DIRTY	(	    "Dirty Passage",            "You're in dirty passage.",             "You are in a dirty broken passage. To the east is a crawl. To the west is a large passage. Above you is a hole to another passage."	),
    CLEAN	(	    "Clean Pit",                "You're by a clean pit.",               "You are on the brink of a small clean climbable pit. A crawl leads west."	),
    WET		( true,	"Wet Pit",                  "You're in pit by stream.",             "You are in the bottom of a small pit with a little stream, which enters and exits through tiny slits."	),
    DUSTY	(	    "Dusty Room",               "You're in dusty rock room.",           "You are in a large room full of dusty rocks. There is a big hole in the floor. There are cracks everywhere, and a passage leading east."	),
    COMPLEX	(	    "Complex Junction",         "You're at complex junction.",          "You are at a complex junction. A low hands and knees passage from the north joins a higher crawl from the east to make a walking passage going west. There is also a large room above. The air is damp here."	),

    SHELL	(	    "Shell Room",               "You're in Shell Room.",                "You are in a large room carved out of sedimentary rock. The floor and walls are littered with bits of shells embedded in the stone. A shallow passage proceeds downward, and a somewhat steeper one leads up. A low hands-and-knees passage enters from the south."	),
    ARCH	(	    "Arched Hall",              "You're in arched hall.",               "You are in an arched hall. A coral passage once continued up and east from here, but is now blocked by debris. The air smells of sea water."	),
    RAGGED	(	    "Ragged Corridor",          "You are in a long sloping corridor with ragged sharp walls."	),
    CULDESAC(	    "Cul-de-sac",               "You are in a cul-de-sac about eight feet across."	),
    ANTE	(	    "Anteroom",                 "You're in anteroom.",                  "You are in an anteroom leading to a large passage to the east. Small passages go west and up. The remnants of recent digging are evident.\n\n\tA sign in midair here says \n\t\t\"CAVE UNDER CONSTRUCTION BEYOND THIS POINT.\n\t\tPROCEED AT OWN RISK\n\t\t[WITT CONSTRUCTION COMPANY]\""	),
    WITT	(	    "Witt's End",               "You are at Witt's End.",               "You are at Witt's End. Passages lead off in \"all\" directions."	),

    BEDQUILT(	    "Bedquilt",                 "You're in Bedquilt.",                  "You are in Bedquilt, a long E/W passage with holes everywhere. \nTo explore at random select north, south, up or down."	),
    CHEESE	(	    "Swiss Cheese Room",        "You're in Swiss Cheese Room.",         "You are in a room whose walls resemble swiss cheese. \nObvious passages go west, east, ne, and nw. Part of the room is occupied by a large bedrock block."	),
    SOFT	(	    "Soft Room",                "You're in Soft Room.",                 "You are in the Soft Room. The walls are covered with heavy curtains, the floor with a thick pile carpet. Moss covers the ceiling."	),

    EAST2PIT(	    "East Twopit Room",         "You're at east end of Twopit Room.",   "You are at the east end of the twopit room. The floor here is littered with thin rock slabs, which make it easy to descend the pits. There is a path here bypassing the pits to connect passages from east and west. There are holes all over, but the only big one is on the wall directly over the west pit where you can't get at it."	),
    WEST2PIT(	    "West Twopit Room",         "You're at west end of Twopit Room.",   "You are at the west end of the twopit room. There is a large hole in the wall above the pit at this end of the room."	),
    EASTPIT	(	    "East Pit",                 "You're in east pit.",                  "You are that the bottom of the eastern pit in the twopit room. There is a small pool of oil in one corner of the pit."	),
    WESTPIT	(	    "West Pit",                 "You're in west pit.",                  "You are at the bottom of the western pit in the twopit room. There is a large hole in the wall about 25 feet above you."	),

    NARROW	(	    "Narrow Corridor",          "You're in narrow corridor.",           "You are in a long, narrow corridor stretching out of sight to the west. At the eastern end is a hole through which you can see a profusion of leaves."	),
    GIANT	(	    "Giant Room",               "You're in Giant Room.",                "You are in the Giant Room. The ceiling here is too high up for your lamp to show it. Cavernous passages lead east, north, and south. \n\nOn the west wall is scrawled the inscription, \n\t\"FEE FIE FOE FOO\" [sic]."	),
    BLOCK	(	    "Blocked Passage",          "The passage here is blocked by a recent cave-in."	),
    IMMENSE	(	    "Immense Passage",          "You are at one end of an immense north/south passage."	),

    FALLS	( true,	"Falls",                    "You're in cavern with waterfall.",     "You are in a magnificent cavern with a rushing stream, which cascades over a sparkling waterfall into a roaring whirlpool that disappears through a hole in the floor.\nPassages exit to the south and west."	),
    STEEP	(	    "Steep Incline",            "You're at steep incline above large room.","You are at the top of a steep incline above a large room. You could climb down here, but you would not be able to climb up. There is a passage leading back to the north."	),
    ABOVEP	(       SECRET_CANYON,              "You are in a secret N/S canyon above a sizable passage."	),
    SJUNC	(	    "Secret Canyon Junction",   "You're at junction of three secret canyons.","You are in a secret canyon at a junction of three canyons, bearing north, south, and SE. The north one is as tall as the other two combined."	),
    TIGHT	(	    "Tight Canyon",             "The canyon here becomes too tight to go further south."	),
    LOW		(	    "Low Room",                 "You are in a large low room. Crawls lead north, SE, and SW."	),
    CRAWL	(	    "Dead End Crawl",           "Dead end crawl."	),
    WWINDOW (       "Window",                   "You're at window on pit.",             "You're at a low window overlooking a huge pit, which extends up out of sight. A floor is indistinctly visible over 50 feet below. Traces of white mist cover the floor of the pit, becoming thicker to the left. Marks in the dust around the window would seem to indicate that someone has been here recently. Directly across the pit from you and 25 feet away there is a similar window looking into a lighted room. A shadowy figure can be seen there peering back at you."	),

    ORIENTAL(	    "Oriental Room",            "You're in Oriental Room.",             "This is the Oriental Room. Ancient oriental cave drawings cover the walls. A gently sloping passage leads upward to the north, another passage leads se, and a hands and knees crawl leads west."	),
    MISTY	(	    "Misty Cavern",             "You're in misty cavern.",              "You are following a wide path around the outer edge of a large cavern. Far below, through a heavy white mist, strange splashing noises can be heard. The mist rises up through a fissure in the ceiling. The path exits to the south and west."	),
    ALCOVE	(	    "Alcove",                   "You're in alcove.",                    "You are in an alcove. A small NW path seems to widen after a short distance. An extremely tight tunnel leads east. It looks like a very tight squeeze. An eerie light can be seen at the other end."	),
    PROOM	(	    "Plover Room",              "You are in Plover Room.",              "You're in a small chamber lit by an eerie green light. An extremely narrow tunnel exits to the west. A dark corridor leads NE."	),
    DROOM	(	    "Dark Room",                "You're in the Dark-Room.",             "You're in the Dark-Room. A corridor leading south is the only exit."	),

    SLAB	(	    "Slab Room",                "You're in Slab Room.",					"You are in a large low circular chamber whose floor is an immense slab fallen from the ceiling. (Slab Room). There once were large passages to the east and west, but they are now filled with boulders. Low small passages go north and south, and the south one quickly bends west around the boulders."	),
    ABOVER	(       SECRET_CANYON,              "You are in a secret N/S canyon above a large room."	),
    MIRROR	(	    "Mirror Canyon",            "You're in mirror canyon.",             "You are in a north/south canyon about 25 feet across. The floor is covered by white mist seeping in from the north. The walls extend upward for well over 100 feet. Suspended from some unseen point far above you, an enormous two-sided mirror is hanging parallel to and midway between the canyon walls. (The mirror is obviously provided for the use of the dwarves, who as you know are extremely vain.) A small window can be seen in either wall, some fifty feet up."	),
    RESER	( true,	"Reservoir",                "You're at reservoir.",                 "You are at the edge of a large underground reservoir. An opaque cloud of white mist fills the room and rises rapidly upward. The lake is fed by a stream, which tumbles out of a hole in the wall about 10 feet overhead and splashes noisily into the water somewhere within the mist. The only passage goes back toward the south."	),

    SCAN1	(   SECRET_CANYON, SECRET_CANYON_DESC	),
    SCAN2	(   SECRET_CANYON, SECRET_CANYON_DESC	),
    SCAN3	(   SECRET_CANYON, SECRET_CANYON_DESC	),
    SECRET	(   SECRET_CANYON, "You're in secret E/W canyon above tight canyon.",        "You are in a secret canyon, which here runs E/W. It crosses over an very tight canyon 15 feet below. If you go down you may not be able to get back up."	),

    WIDE	(	    "Wide Place",               "You're at a wide place in a very tight N/S canyon."	),
    STALACTITE(	    "Stalactite",               "You're on top of stalactite.",         "A large stalactite extends from the roof and almost reaches the floor below. You could climb down, but you would be unable to reach it to climb back up."	),
    TALL	(	    "Tall Canyon",              "You're in tall E/W canyon.",           "You are in a tall E/W canyon. A low tight crawl goes 3 feet north and seems to open up."	),
    BOULDERS(       DEAD_END_T,                 "The canyon runs into a mass of boulders --- dead end."	),

    SCORR	(	    "Sloping Corridor",         "You are in sloping corridor.",         "You are in a long winding corridor sloping out of sight in both directions."	),
    SWSIDE	(	    "South West Side",          "You're on SW side of chasm.",          "You are on one side of a large, deep chasm. A heavy white mist rising up from below obscures all view of the far side. A SW path leads away from the chasm into a winding corridor."	),

    DEAD0(DEAD_END_T, DEAD_END),	DEAD1(DEAD_END_T, DEAD_END),	DEAD2(DEAD_END_T, DEAD_END),	DEAD3(DEAD_END_T, DEAD_END),
    DEAD4(DEAD_END_T, DEAD_END),	DEAD5(DEAD_END_T, DEAD_END),	DEAD6(DEAD_END_T, DEAD_END),	DEAD7(DEAD_END_T, DEAD_END),
    DEAD8(DEAD_END_T, DEAD_END),	DEAD9(DEAD_END_T, DEAD_END),	DEAD10(DEAD_END_T, DEAD_END),	DEAD11(DEAD_END_T, DEAD_END),

    NESIDE	(	    "North East Side",          "You're on NE side of chasm.",          "You are on the far side of the chasm. A NE path leads away from the chasm on this side."	),
    CORR	(	    "Corridor",                 "You're in corridor.",                  "You're in a long east/west corridor. A faint rumbling noise can be heard in the distance."	),
    FORK	(	    "Fork In Path",             "You're at fork in path.",              "The path forks here. The left fork leads northeast. A dull rumbling seems to get louder in that direction. The right fork leads southeast down a gentle slope. The main corridor enters from the west."	),
    WARM	(	    "Warm Junction",            "You're at junction with warm walls.",  "The walls are quite warm here. From the north can be heard a steady roar, so loud that the entire cave seems to be trembling. Another passage leads south, and a low crawl goes east."	),
    VIEW	(	    "Breath-Taking View",       "You're at breath-taking view.",        "You are on the edge of a breath-taking view. Far below you is an active volcano, from which great gouts of molten lava come surging out, cascading back down into the depths. The glowing rock fills the farthest reaches of the cavern with a blood-red glare, giving every-thing an eerie, macabre appearance. "
        + "The air is filled with flickering sparks of ash and a heavy smell of brimstone. The walls are hot to the touch, and the thundering of the volcano drowns out all other sounds. "
        + "Embedded in the jagged roof far overhead are myriad twisted formations, composed of pure white alabaster, which scatter the murky light into sinister apparitions upon the walls. To one side is a deep gorge, filled with a bizarre chaos of tortured rock that seems to have been crafted by the Devil himself. "
        + "An immense river of fire crashes out from the depths of the volcano, burns its way through the gorge, and plummets into a bottomless pit far off to your left. "
        + "To the right, am immense geyser of blistering steam erupts continuously from a barren island in the center of a sulfurous lake, which bubbles ominously. The far right wall is aflame with an incandescence of its own, which lends an additional infernal splendor to the already hellish scene. "
        + "\nA dark, foreboding passage exits to the south."	),
    CHAMBER	(	    "Boulder Chamber",          "You're in chamber of boulders.",       "You are in a small chamber filled with large boulders. The walls are very warm, causing the air in the room to be almost stifling from the heat. The only exit is a crawl heading west, through which a low rumbling noise is coming."	),
    LIME	(	    "Limestone Passage",        "You're in limestone passage.",         "You are walking along a gently sloping north/south passage lined with oddly shaped limestone formations."	),
    FBARR	(	    "In Front Of Barren Room",  "You're in front of barren room.",      "You are standing at the entrance to a large, barren room. \nA sign posted above the entrance reads:\n\t\"CAUTION! BEAR IN ROOM!\""	),
    BARR	(	    "Barren Room",              "You're in barren room.",               "You are inside a barren room. The center of the room is completely empty except for some dust. Marks in the dust lead away toward the far end of the room. The only exit is the way you came in."	),

    NEEND	(	    "North East End",           "You're at NE end.",                    "You are at the northeast end of an immense room, even larger than the Giant Room. It appears to be a repository for the \"Adventure\" program. "
        + "Massive torches far overhead bathe the room with smoky yellow light. Scattered about you can be seen a pile of bottles (all of them empty), a nursery of young beanstalks murmuring quietly, a bed of oysters, a bundle of black rods with rusty stars on their ends, and a collection of brass lanterns. "
        + "Off to one side a great many dwarves are sleeping on the floor, snoring loudly. \nA sign nearby reads:\n\t\"DO NOT DISTURB THE DWARVES!"
        + "\"\nAn immense mirror is hanging against one wall, and stretches to the other end of the room, where various other sundry objects can be glimpsed dimly in the distance."	),
    SWEND	(	    "South West End",           "You're at SW end.",                    "You are at the southwest end of the repository. To one side is a pit full of fierce green snakes. On the other side is a row of small wicker cages, each of which contains a little sulking bird. In one corner is a bundle of black rods with rusty marks on their ends. "
        + "A large number of velvet pillows are scattered about on the floor. A vast mirror stretches off to the northeast. At your feet is a large steel grate, next to which is a sign that reads, \n\t\"TREASURE VAULT. KEYS IN MAIN OFFICE.\""	),

    REMARK();


	public static final Map<Locations, Locations[]> POSSIBLE_SIMPLE_EXITS = new EnumMap<>(Locations.class)
	{{
		put(ROAD, new Locations[]{HILL, BUILDING, VALLEY, FOREST, OUTSIDE});
		put(HILL, new Locations[]{ROAD, FOREST});
		put(BUILDING, new Locations[]{ROAD});
		put(VALLEY, new Locations[]{ROAD, FOREST, SLIT, OUTSIDE});
		put(FOREST, new Locations[]{WOODS, VALLEY});
		put(WOODS, new Locations[]{ROAD, VALLEY, FOREST});
		put(SLIT, new Locations[]{ROAD, VALLEY, FOREST, OUTSIDE});
		put(OUTSIDE, new Locations[]{FOREST, ROAD, SLIT});
		put(INSIDE, new Locations[]{COBBLES, SMALLPIT, DEBRIS});
		put(COBBLES, new Locations[]{INSIDE, DEBRIS, SMALLPIT});
		put(DEBRIS, new Locations[]{INSIDE, COBBLES, AWKWARD, SMALLPIT});
		put(AWKWARD, new Locations[]{INSIDE, DEBRIS, BIRD, SMALLPIT});
		put(BIRD, new Locations[]{INSIDE, DEBRIS, AWKWARD, SMALLPIT});
		put(SMALLPIT, new Locations[]{INSIDE, DEBRIS});
		put(EASTMIST, new Locations[]{NUGGET, EASTFISSURE, MTKHALL, JUMBLE});
		put(NUGGET, new Locations[]{EASTMIST});
		put(EASTFISSURE, new Locations[]{EASTMIST});
		put(WESTFISSURE, new Locations[]{WESTMIST});
		put(WESTMIST, new Locations[]{WESTFISSURE, EASTLONG});
		put(EASTLONG, new Locations[]{WESTMIST, WESTLONG, CROSS});
		put(WESTLONG, new Locations[]{CROSS, EASTLONG});
		put(PONY, new Locations[]{DIFF10});
		put(CROSS, new Locations[]{EASTLONG, DEAD0, WEST, WESTLONG});
		put(MTKHALL, new Locations[]{EASTMIST, SECRET});
		put(WEST, new Locations[]{MTKHALL, CROSS});
		put(SOUTH, new Locations[]{MTKHALL});
		put(NS, new Locations[]{MTKHALL, Y2, DIRTY});
		put(Y2, new Locations[]{NS, JUMBLE, EWINDOW});
		put(JUMBLE, new Locations[]{Y2, EASTMIST});
		put(EWINDOW, new Locations[]{Y2});
		put(DIRTY, new Locations[]{CLEAN, NS, DUSTY, BEDQUILT});
		put(CLEAN, new Locations[]{DIRTY, WET});
		put(DUSTY, new Locations[]{DIRTY, COMPLEX, BEDQUILT});
		put(COMPLEX, new Locations[]{DUSTY, BEDQUILT, SHELL, ANTE});
		put(SHELL, new Locations[]{ARCH, RAGGED, BEDQUILT});
		put(ARCH, new Locations[]{SHELL});
		put(RAGGED, new Locations[]{SHELL, CULDESAC});
		put(CULDESAC, new Locations[]{RAGGED, SHELL});
		put(ANTE, new Locations[]{COMPLEX, BEDQUILT});
		put(BEDQUILT, new Locations[]{COMPLEX, CHEESE, SLAB});
		put(CHEESE, new Locations[]{BEDQUILT, EAST2PIT, TALL, SOFT, ORIENTAL});
		put(SOFT, new Locations[]{CHEESE});
		put(EAST2PIT, new Locations[]{CHEESE, WEST2PIT, EASTPIT});
		put(WEST2PIT, new Locations[]{EAST2PIT, SLAB, WESTPIT});
		put(EASTPIT, new Locations[]{EAST2PIT});
		put(WESTPIT, new Locations[]{WEST2PIT});
		put(NARROW, new Locations[]{WESTPIT, GIANT});
		put(GIANT, new Locations[]{NARROW, BLOCK, IMMENSE});
		put(BLOCK, new Locations[]{GIANT});
		put(IMMENSE, new Locations[]{GIANT});
		put(FALLS, new Locations[]{IMMENSE, GIANT, STEEP});
		put(STEEP, new Locations[]{FALLS, LOW});
		put(ABOVEP, new Locations[]{SJUNC, BEDQUILT, STALACTITE});
		put(SJUNC, new Locations[]{BEDQUILT, ABOVEP, WWINDOW});
		put(STALACTITE, new Locations[]{ABOVEP});
		put(LOW, new Locations[]{BEDQUILT, SCORR, CRAWL, ORIENTAL});
		put(CRAWL, new Locations[]{LOW});
		put(WWINDOW, new Locations[]{SJUNC});
		put(ORIENTAL, new Locations[]{CHEESE, LOW, MISTY});
		put(MISTY, new Locations[]{ORIENTAL, ALCOVE});
		put(ALCOVE, new Locations[]{MISTY});
		put(PROOM, new Locations[]{DROOM});
		put(DROOM, new Locations[]{PROOM});
		put(SLAB, new Locations[]{WEST2PIT, ABOVER, BEDQUILT});
		put(ABOVER, new Locations[]{SLAB, MIRROR, RESER});
		put(MIRROR, new Locations[]{ABOVER, RESER});
		put(RESER, new Locations[]{MIRROR});
		put(SCAN1, new Locations[]{ABOVER});
		put(SCAN2, new Locations[]{ABOVER, SECRET});
		put(SCAN3, new Locations[]{SECRET});
		put(SECRET, new Locations[]{MTKHALL, WIDE});
		put(WIDE, new Locations[]{TIGHT, TALL});
		put(TIGHT, new Locations[]{WIDE});
		put(TALL, new Locations[]{WIDE, BOULDERS, CHEESE});
		put(BOULDERS, new Locations[]{TALL});
		put(SCORR, new Locations[]{LOW, SWSIDE});
		put(SWSIDE, new Locations[]{SCORR});
		put(DEAD0, new Locations[]{CROSS});
		put(DEAD1, new Locations[]{ALIKE11});
		put(DEAD2, new Locations[]{ALIKE13});
		put(DEAD3, new Locations[]{ALIKE4});
		put(DEAD4, new Locations[]{ALIKE4});
		put(DEAD5, new Locations[]{ALIKE3});
		put(DEAD6, new Locations[]{ALIKE9});
		put(DEAD7, new Locations[]{ALIKE10});
		put(DEAD8, new Locations[]{BRINK});
		put(DEAD9, new Locations[]{ALIKE3});
		put(DEAD10, new Locations[]{ALIKE12});
		put(DEAD11, new Locations[]{ALIKE8});
		put(NESIDE, new Locations[]{CORR, FORK, VIEW, FBARR});
		put(CORR, new Locations[]{NESIDE, FORK, VIEW, FBARR});
		put(FORK, new Locations[]{CORR, WARM, LIME, VIEW, FBARR});
		put(WARM, new Locations[]{FORK, VIEW, CHAMBER});
		put(VIEW, new Locations[]{WARM, FORK});
		put(CHAMBER, new Locations[]{WARM, FORK, VIEW});
		put(LIME, new Locations[]{FORK, FBARR, VIEW});
		put(FBARR, new Locations[]{LIME, FORK, BARR, VIEW});
		put(BARR, new Locations[]{FBARR, FOREST, VIEW});
		put(NEEND, new Locations[]{SWEND});
		put(SWEND, new Locations[]{NEEND});
	}};


    static final Locations[] locationArray = Locations.values(); // TODO
    int visits = 0;

    public final String title;
    public final String shortDescription;
    public final String longDescription;
    public final boolean hasWater;

    Locations()
    {
        this.title = EMPTY;
        this.shortDescription = EMPTY;
        this.longDescription = EMPTY;
        this.hasWater = false;
    }

    Locations(String title, String shortDescription)
    {
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = EMPTY;
        this.hasWater = false;
    }

    Locations(String title, String shortDescription, String longDescription)
    {
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.hasWater = false;
    }

    Locations(boolean hasWater, String title, String shortDescription, String longDescription)
    {
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.hasWater = hasWater;
    }

    public static int minLocDeepInCave()
    {
        return EASTMIST.ordinal();
    }

    public static int maxLoc()
    {
        return DEAD0.ordinal();
    }

    public static boolean crittersAllowed(Locations here)
    {
        return (here.ordinal() > SMALLPIT.ordinal() && here.ordinal() < DEAD0.ordinal() && here != PROOM && here != DROOM);
    }

    public boolean outside(Locations here)
    {
        return (here.ordinal() > INHAND.ordinal() && here.ordinal() < DEBRIS.ordinal());
    }

    public static boolean outsideCave(Locations here)
    {
        return (here.ordinal() > INHAND.ordinal() && here.ordinal() < INSIDE.ordinal());
    }

    public boolean dontNeedLamp(Locations here)
    {
        return outside(here) || here == VIEW || here == NEEND || here == SWEND || here == PROOM;
    }

    public static String getDescription(Locations here, int brief)
    {
        return (!(here.longDescription.equals(EMPTY)) && ((brief == 0 && here.visits % 5 == 0) || (brief == 2) || (here.visits == 0))) ? here.longDescription : here.shortDescription;
    }

    public static int[] getVisitsArray()
    {
        int[] visitsArray = new int[locationArray.length];

        for (int i = 0; i < locationArray.length; i++)
        {
            visitsArray[i] = locationArray[i].visits;
        }

        return visitsArray;
    }

    public static void loadVisits(int[] visits)
    {
        for (int i = 0; i < locationArray.length; i++)
        {
			locationArray[i].visits = visits[i];
        }
    }

    public static void placeObject(GameObjects thing, Locations here)
    {
        if (thing != GameObjects.BIRD)
        {
            // current and future locations could both be INHAND
            if (here == INHAND)
            {
                advent.setItemsInHand((byte) (advent.getItemsInHand() + 1));
            }

            if (thing.location == INHAND)
            {
                advent.setItemsInHand((byte) (advent.getItemsInHand() - 1));
            }
        }
        thing.location = here;
    }

    public static Locations moveTo(Movement destination, Locations here)
    {
        AdventGame game = advent;
        double chance = AdventMain.generate();
        Locations next;

        switch (here)
        {
            case ROAD: next = switch (destination)
            {
                case WEST, UP, ROAD, HILL -> HILL;
                case EAST, BUILDING, IN, ENTER -> BUILDING;
                case SOUTH, DOWN, GULLY, STREAM, DOWNSTREAM -> VALLEY;
                case NORTH, FOREST -> FOREST;
                case DEPRESSION -> OUTSIDE;
                default -> VOID;
            }; break;

            case HILL: next = switch (destination)
            {
                case ROAD, BUILDING, FORWARD, EAST, DOWN -> ROAD;
                case FOREST, NORTH, SOUTH -> FOREST;
                default -> VOID;
            }; break;

            case BUILDING: next = switch (destination)
            {
                case ENTER, OUT, OUTDOORS, WEST -> ROAD;
                case XYZZY -> DEBRIS;
                case PLUGH -> Y2;
                case DOWNSTREAM, STREAM -> REMARK;
                default -> VOID;
            }; break;

            case VALLEY: next = switch (destination)
            {
                case UPSTREAM, BUILDING, NORTH -> ROAD;
                case FOREST, EAST, WEST, UP -> FOREST;
                case DOWNSTREAM, SOUTH, DOWN -> SLIT;
                case DEPRESSION -> OUTSIDE;
                default -> VOID;
            }; break;

            case FOREST: next = switch (destination)
            {
                case VALLEY, EAST, DOWN -> VALLEY;
                case FOREST, NORTH -> chance > .49 ? FOREST : WOODS;
                case FORWARD -> chance < .51 ? FOREST : WOODS;
                case WEST, SOUTH -> FOREST;
                default -> VOID;
            }; break;

            case WOODS: next = switch (destination)
            {
                case ROAD, NORTH -> ROAD;
                case VALLEY, EAST, WEST, DOWN -> VALLEY;
                case FOREST, SOUTH -> FOREST;
                default -> VOID;
            }; break;

            case SLIT: next = switch (destination)
            {
                case BUILDING -> ROAD;
                case UPSTREAM, UP, NORTH -> VALLEY;
                case FOREST, WEST, EAST -> FOREST;
                case DOWNSTREAM, DOWN, ROCK, BED, SOUTH -> OUTSIDE;
                case SLIT -> REMARK;
                default -> VOID;
            }; break;

            case OUTSIDE: next = switch (destination)
            {
                case FOREST, EAST, WEST, SOUTH -> FOREST;
                case BUILDING, ROAD -> ROAD;
                case UPSTREAM, UP, GULLY, NORTH -> SLIT;
                case ENTER, IN, DOWN -> throughGrate(true);
                default -> VOID;
            }; break;

            case INSIDE: next = switch (destination)
            {
                case CRAWL, COBBLE, IN, WEST -> COBBLES;
                case PIT -> SMALLPIT;
                case DEBRIS -> DEBRIS;
                case OUT, UP -> throughGrate(false);
                default -> VOID;
            }; break;

            case COBBLES: next = switch (destination)
            {
                case OUT, SURFACE, NOWHERE, EAST -> INSIDE;
                case DEBRIS, DARK, WEST, IN -> DEBRIS;
                case PIT -> SMALLPIT;
                default -> VOID;
            }; break;

            case DEBRIS: next = switch (destination)
            {
                case DEPRESSION -> throughGrate(false);
                case ENTRANCE -> INSIDE;
                case COBBLE, CRAWL, PASSAGE, LOW, EAST -> COBBLES;
                case CANYON, IN, UP, WEST -> AWKWARD;
                case XYZZY -> BUILDING;
                case PIT -> SMALLPIT;
                default -> VOID;
            }; break;

            case AWKWARD: next = switch (destination)
            {
                case DEPRESSION -> throughGrate(false);
                case ENTRANCE -> INSIDE;
                case DOWN, EAST, DEBRIS -> DEBRIS;
                case IN, UP, WEST -> BIRD;
                case PIT -> SMALLPIT;
                default -> VOID;
            }; break;

            case BIRD: next = switch (destination)
            {
                case DEPRESSION -> throughGrate(false);
                case ENTRANCE -> INSIDE;
                case DEBRIS -> DEBRIS;
                case CANYON, EAST -> AWKWARD;
                case PASSAGE, PIT, WEST -> SMALLPIT;
                default -> VOID;
            }; break;

            case SMALLPIT: next = switch (destination)
            {
                case DEPRESSION -> throughGrate(false);
                case ENTRANCE -> INSIDE;
                case DEBRIS -> DEBRIS;
                case PASSAGE, EAST -> BIRD;
                case DOWN, PIT, STEPS -> game.isGoldInInventory() ? REMARK : EASTMIST;
                case CRACK, WEST -> REMARK;
                default -> VOID;
            }; break;

            case EASTMIST: next = switch (destination)
            {
                case LEFT, SOUTH -> NUGGET;
                case FORWARD, HALL, WEST -> EASTFISSURE;
                case STAIRS, DOWN, NORTH -> MTKHALL;
                case UP, PIT -> game.isGoldInInventory() ? REMARK : SMALLPIT;
                case STEPS, DOME, PASSAGE, EAST -> game.isGoldInInventory() ? REMARK : VOID;
                case Y2 -> JUMBLE;
                default -> VOID;
            }; break;

            case NUGGET: next = switch (destination)
            {
                case HALL, OUT, NORTH -> EASTMIST;
                default -> VOID;
            }; break;

            case EASTFISSURE: next = switch (destination)
            {
                case HALL, EAST -> EASTMIST;
                case JUMP -> REMARK;
                case FORWARD, OVER, ACROSS, WEST, CROSS -> bridgeRemark(true);
                default -> VOID;
            }; break;

            case WESTFISSURE: next = switch (destination)
            {
                case NORTH, JUMP -> REMARK;
                case WEST -> WESTMIST;
                case FORWARD, OVER, ACROSS, EAST, CROSS -> bridgeRemark(false);
                default -> VOID;
            }; break;

            case WESTMIST: next = switch (destination)
            {
                case SOUTH, UP, PASSAGE, CLIMB -> ALIKE1;
                case EAST -> WESTFISSURE;
                case NORTH -> REMARK;
                case WEST, CRAWL -> EASTLONG;
                default -> VOID;
            }; break;

            case ALIKE1: next = switch (destination)
            {
                case UP -> WESTMIST;
                case NORTH -> ALIKE1;
                case EAST -> ALIKE2;
                case SOUTH -> ALIKE4;
                case WEST -> ALIKE11;
                default -> VOID;
            }; break;

            case ALIKE2: next = switch (destination)
            {
                case EAST -> ALIKE4;
                case SOUTH -> ALIKE3;
                case WEST -> ALIKE1;
                default -> VOID;
            }; break;

            case ALIKE3: next = switch (destination)
            {
                case DOWN -> DEAD5;
                case NORTH -> DEAD9;
                case EAST -> ALIKE2;
                case SOUTH -> ALIKE6;
                default -> VOID;
            }; break;

            case ALIKE4: next = switch (destination)
            {
                case UP, DOWN -> ALIKE14;
                case NORTH -> ALIKE2;
                case EAST -> DEAD3;
                case SOUTH -> DEAD4;
                case WEST -> ALIKE1;
                default -> VOID;
            }; break;

            case ALIKE5: next = switch (destination)
            {
                case EAST -> ALIKE6;
                case WEST -> ALIKE7;
                default -> VOID;
            }; break;

            case ALIKE6: next = switch (destination)
            {
                case DOWN -> ALIKE7;
                case WEST -> ALIKE5;
                case EAST -> ALIKE3;
                case SOUTH -> ALIKE8;
                default -> VOID;
            }; break;

            case ALIKE7: next = switch (destination)
            {
                case UP -> ALIKE6;
                case WEST -> ALIKE5;
                case EAST -> ALIKE8;
                case SOUTH -> ALIKE9;
                default -> VOID;
            }; break;

            case ALIKE8: next = switch (destination)
            {
                case UP -> ALIKE9;
                case WEST -> ALIKE6;
                case EAST -> ALIKE7;
                case SOUTH -> ALIKE8;
                case NORTH -> ALIKE10;
                case DOWN -> DEAD11;
                default -> VOID;
            }; break;

            case ALIKE9: next = switch (destination)
            {
                case WEST -> ALIKE7;
                case NORTH -> ALIKE8;
                case SOUTH -> DEAD6;
                default -> VOID;
            }; break;

            case ALIKE10: next = switch (destination)
            {
                case NORTH -> ALIKE10;
                case WEST -> ALIKE8;
                case EAST -> BRINK;
                case DOWN -> DEAD7;
                default -> VOID;
            }; break;

            case ALIKE11: next = switch (destination)
            {
                case NORTH -> ALIKE1;
                case EAST -> DEAD1;
                case SOUTH, WEST -> ALIKE11;
                default -> VOID;
            }; break;

            case ALIKE12: next = switch (destination)
            {
                case WEST -> DEAD10;
                case EAST -> ALIKE13;
                case SOUTH -> BRINK;
                default -> VOID;
            }; break;

            case ALIKE13: next = switch (destination)
            {
                case NORTH -> BRINK;
                case WEST -> ALIKE12;
                case NORTHWEST -> DEAD2;
                default -> VOID;
            }; break;

            case ALIKE14: next = switch (destination)
            {
                case UP, DOWN -> ALIKE4;
                default -> VOID;
            }; break;

            case BRINK: next = switch (destination)
            {
                case DOWN, CLIMB -> BIRD;
                case WEST -> ALIKE10;
                case SOUTH -> DEAD8;
                case NORTH -> ALIKE12;
                case EAST -> ALIKE13;
                default -> VOID;
            }; break;

            case EASTLONG: next = switch (destination)
            {
                case EAST, UP, CRAWL -> WESTMIST;
                case WEST -> WESTLONG;
                case NORTH, DOWN, HOLE -> CROSS;
                default -> VOID;
            }; break;

            case WESTLONG: next = switch (destination)
            {
                case NORTH -> CROSS;
                case EAST -> EASTLONG;
                case SOUTH -> DIFF0;
                default -> VOID;
            }; break;

            case DIFF0: next = switch (destination)
            {
                case SOUTH -> DIFF1;
                case SOUTHWEST -> DIFF2;
                case NORTHEAST -> DIFF3;
                case SOUTHEAST -> DIFF4;
                case UP -> DIFF5;
                case NORTHWEST -> DIFF6;
                case EAST -> DIFF7;
                case WEST -> DIFF8;
                case NORTH -> DIFF9;
                case DOWN -> WESTLONG;
                default -> VOID;
            }; break;

            case DIFF1: next = switch (destination)
            {
                case WEST -> DIFF0;
                case SOUTHEAST -> DIFF1;
                case NORTHWEST -> DIFF3;
                case SOUTHWEST -> DIFF4;
                case NORTHEAST -> DIFF5;
                case UP -> DIFF6;
                case DOWN -> DIFF7;
                case NORTH -> DIFF8;
                case SOUTH -> DIFF9;
                case EAST -> DIFF10;
                default -> VOID;
            }; break;

            case DIFF2: next = switch (destination)
            {
                case NORTHWEST -> DIFF0;
                case UP -> DIFF1;
                case NORTH -> DIFF3;
                case SOUTH -> DIFF4;
                case WEST -> DIFF5;
                case SOUTHWEST -> DIFF6;
                case NORTHEAST -> DIFF7;
                case EAST -> DIFF8;
                case DOWN -> DIFF9;
                case SOUTHEAST -> DIFF10;
                default -> VOID;
            }; break;

            case DIFF3: next = switch (destination)
            {
                case UP -> DIFF0;
                case DOWN -> DIFF1;
                case WEST -> DIFF2;
                case NORTHEAST -> DIFF4;
                case SOUTHWEST -> DIFF5;
                case EAST -> DIFF6;
                case NORTH -> DIFF7;
                case NORTHWEST -> DIFF8;
                case SOUTHEAST -> DIFF9;
                case SOUTH -> DIFF10;
                default -> VOID;
            }; break;

            case DIFF4: next = switch (destination)
            {
                case NORTHEAST -> DIFF0;
                case NORTH -> DIFF1;
                case NORTHWEST -> DIFF2;
                case SOUTHEAST -> DIFF3;
                case EAST -> DIFF5;
                case DOWN -> DIFF6;
                case SOUTH -> DIFF7;
                case UP -> DIFF8;
                case WEST -> DIFF9;
                case SOUTHWEST -> DIFF10;
                default -> VOID;
            }; break;

            case DIFF5: next = switch (destination)
            {
                case NORTH -> DIFF0;
                case SOUTHEAST -> DIFF1;
                case DOWN -> DIFF2;
                case SOUTH -> DIFF3;
                case EAST -> DIFF4;
                case WEST -> DIFF6;
                case SOUTHWEST -> DIFF7;
                case NORTHEAST -> DIFF8;
                case NORTHWEST -> DIFF9;
                case UP -> DIFF10;
                default -> VOID;
            }; break;

            case DIFF6: next = switch (destination)
            {
                case EAST -> DIFF0;
                case WEST -> DIFF1;
                case UP -> DIFF2;
                case SOUTHWEST -> DIFF3;
                case DOWN -> DIFF4;
                case SOUTH -> DIFF5;
                case NORTHWEST -> DIFF7;
                case SOUTHEAST -> DIFF8;
                case NORTHEAST -> DIFF9;
                case NORTH -> DIFF10;
                default -> VOID;
            }; break;

            case DIFF7: next = switch (destination)
            {
                case EAST -> DIFF0;
                case WEST -> DIFF1;
                case UP -> DIFF2;
                case SOUTHWEST -> DIFF3;
                case DOWN -> DIFF4;
                case SOUTH -> DIFF5;
                case NORTHWEST -> DIFF6;
                case SOUTHEAST -> DIFF8;
                case NORTHEAST -> DIFF9;
                case NORTH -> DIFF10;
                default -> VOID;
            }; break;

            case DIFF8: next = switch (destination)
            {
                case DOWN -> DIFF0;
                case EAST -> DIFF1;
                case NORTHEAST -> DIFF2;
                case UP -> DIFF3;
                case WEST -> DIFF4;
                case NORTH -> DIFF5;
                case SOUTH -> DIFF6;
                case SOUTHEAST -> DIFF7;
                case SOUTHWEST -> DIFF9;
                case NORTHWEST -> DIFF10;
                default -> VOID;
            }; break;

            case DIFF9: next = switch (destination)
            {
                case SOUTHWEST -> DIFF0;
                case NORTHWEST -> DIFF1;
                case EAST -> DIFF2;
                case WEST -> DIFF3;
                case NORTH -> DIFF4;
                case DOWN -> DIFF5;
                case SOUTHEAST -> DIFF6;
                case UP -> DIFF7;
                case SOUTH -> DIFF8;
                case NORTHEAST -> DIFF10;
                default -> VOID;
            }; break;

            case DIFF10: next = switch (destination)
            {
                case SOUTHWEST -> DIFF1;
                case NORTH -> DIFF2;
                case EAST -> DIFF3;
                case NORTHWEST -> DIFF4;
                case SOUTHEAST -> DIFF5;
                case NORTHEAST -> DIFF6;
                case WEST -> DIFF7;
                case DOWN -> DIFF8;
                case UP -> DIFF9;
                case SOUTH -> PONY;
                default -> VOID;
            }; break;

            case PONY: next = switch (destination)
            {
                case NORTH, OUT -> DIFF10;
                default -> VOID;
            }; break;

            case CROSS: next = switch (destination)
            {
                case WEST -> EASTLONG;
                case NORTH -> DEAD0;
                case EAST -> WEST;
                case SOUTH -> WESTLONG;
                default -> VOID;
            }; break;

            case MTKHALL: next = switch (destination)
            {
                case STAIRS, UP, EAST -> EASTMIST;
                case NORTH, LEFT -> game.isSnakeInHotMK() ? REMARK : NS;
                case SOUTH, RIGHT -> game.isSnakeInHotMK() ? REMARK : SOUTH;
                case WEST, FORWARD -> game.isSnakeInHotMK() ? REMARK : WEST;
                case SOUTHWEST -> game.isSnakeInHotMK() ? (Math.random() < .36 ? SECRET : REMARK) : SECRET;
                case SECRET -> SECRET;
                default -> VOID;
            }; break;

            case WEST: next = switch (destination)
            {
                case HALL, OUT, EAST -> MTKHALL;
                case WEST, UP -> CROSS;
                default -> VOID;
            }; break;

            case SOUTH: next = switch (destination)
            {
                case HALL, OUT, NORTH -> MTKHALL;
                default -> VOID;
            }; break;

            case NS: next = switch (destination)
            {
                case HALL, OUT, SOUTH -> MTKHALL;
                case Y2, NORTH -> Y2;
                case DOWN, HOLE -> DIRTY;
                default -> VOID;
            }; break;

            case Y2: next = switch (destination)
            {
                case PLUGH -> BUILDING;
                case SOUTH -> NS;
                case EAST, WALL, BROKEN -> JUMBLE;
                case WEST -> EWINDOW;
                case PLOVER -> PROOM;
                default -> VOID;
            }; break;

            case JUMBLE: next = switch (destination)
            {
                case DOWN, Y2 -> Y2;
                case UP -> EASTMIST;
                default -> VOID;
            }; break;

            case EWINDOW: next = switch (destination)
            {
                case EAST, Y2 -> Y2;
                case JUMP -> REMARK;
                default -> VOID;
            }; break;

            case DIRTY: next = switch (destination)
            {
                case EAST, CRAWL -> CLEAN;
                case UP, HOLE -> NS;
                case WEST -> DUSTY;
                case BEDQUILT -> BEDQUILT;
                default -> VOID;
            }; break;

            case CLEAN: next = switch (destination)
            {
                case WEST, CRAWL -> DIRTY;
                case DOWN, PIT, CLIMB -> WET;
                default -> VOID;
            }; break;

            case WET: next = switch (destination)
            {
                case CLIMB, UP, OUT -> CLEAN;
                case SLIT, STREAM, DOWN, UPSTREAM, DOWNSTREAM -> REMARK;
                default -> VOID;
            }; break;

            case DUSTY: next = switch (destination)
            {
                case EAST, PASSAGE -> DIRTY;
                case DOWN, HOLE, FLOOR -> COMPLEX;
                case BEDQUILT -> BEDQUILT;
                default -> VOID;
            }; break;

            case COMPLEX: next = switch (destination)
            {
                case UP, CLIMB, ROOM -> DUSTY;
                case WEST, BEDQUILT -> BEDQUILT;
                case NORTH, SHELL -> SHELL;
                case EAST -> ANTE;
                default -> VOID;
            }; break;

            case SHELL: next = switch (destination)
            {
                case UP, HALL -> ARCH;
                case DOWN -> RAGGED;
                case WEST -> BEDQUILT;
                case SOUTH -> (game.isInHand(CLAM) || game.isInHand(OYSTER)) ? REMARK : COMPLEX;
                default -> VOID;
            }; break;

            case ARCH: next = switch (destination)
            {
                case DOWN, SHELL, OUT -> SHELL;
                default -> VOID;
            }; break;

            case RAGGED: next = switch (destination)
            {
                case UP, SHELL -> SHELL;
                case DOWN -> CULDESAC;
                default -> VOID;
            }; break;

            case CULDESAC: next = switch (destination)
            {
                case UP, OUT -> RAGGED;
                case SHELL -> SHELL;
                default -> VOID;
            }; break;

            case ANTE: next = switch (destination)
            {
                case UP -> COMPLEX;
                case WEST -> BEDQUILT;
                case EAST -> WITT;
                default -> VOID;
            }; break;

            case WITT: next = switch (destination)
            {
                case EAST, NORTH, SOUTH, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST, UP, DOWN -> atWittsEnd();
                case WEST -> REMARK;
                default -> VOID;
            }; break;

            case BEDQUILT: next = switch (destination)
            {
                case EAST -> COMPLEX;
                case WEST -> CHEESE;
                case SOUTH -> chance < .81 ? REMARK : SLAB;
                case SLAB -> SLAB;
                case UP -> chance < .81 ? REMARK : (chance > .49 ? ABOVEP : DUSTY);
                case NORTH -> chance < .61 ? REMARK : (chance < .76 ? LOW : SJUNC);
                case DOWN -> chance < .81 ? REMARK : ANTE;
                default -> VOID;
            }; break;

            case CHEESE: next = switch (destination)
            {
                case NORTHEAST -> BEDQUILT;
                case WEST -> EAST2PIT;
                case SOUTH -> chance < .81 ? REMARK : TALL;
                case CANYON -> TALL;
                case EAST -> SOFT;
                case NORTHWEST -> chance < .51 ? REMARK : ORIENTAL;
                case ORIENTAL -> ORIENTAL;
                default -> VOID;
            }; break;

            case SOFT: next = switch (destination)
            {
                case WEST, OUT -> CHEESE;
                default -> VOID;
            }; break;

            case EAST2PIT: next = switch (destination)
            {
                case EAST -> CHEESE;
                case WEST, ACROSS -> WEST2PIT;
                case DOWN, PIT -> EASTPIT;
                default -> VOID;
            }; break;

            case WEST2PIT: next = switch (destination)
            {
                case EAST, ACROSS -> EAST2PIT;
                case WEST, SLAB -> SLAB;
                case DOWN, PIT -> WESTPIT;
                default -> VOID;
            }; break;

            case EASTPIT: next = switch (destination)
            {
                case UP, OUT -> EAST2PIT;
                default -> VOID;
            }; break;

            case WESTPIT: next = switch (destination)
            {
                case UP, OUT -> WEST2PIT;
                case CLIMB -> REMARK;
                default -> VOID;
            }; break;

            case NARROW: next = switch (destination)
            {
                case DOWN, CLIMB, EAST -> WESTPIT;
                case JUMP -> REMARK;
                case WEST, GIANT -> GIANT;
                default -> VOID;
            }; break;

            case GIANT: next = switch (destination)
            {
                case SOUTH -> NARROW;
                case EAST -> BLOCK;
                case NORTH -> IMMENSE;
                default -> VOID;
            }; break;

            case BLOCK: next = switch (destination)
            {
                case SOUTH, GIANT, OUT -> GIANT;
                default -> VOID;
            }; break;

            case IMMENSE: next = switch (destination)
            {
                case SOUTH, GIANT, PASSAGE -> GIANT;
                case NORTH, ENTER, CAVERN -> game.doorHasBeenOiled() ? FALLS : REMARK;
                default -> VOID;
            }; break;

            case FALLS: next = switch (destination)
            {
                case SOUTH, OUT -> IMMENSE;
                case GIANT -> GIANT;
                case WEST -> STEEP;
                default -> VOID;
            }; break;

            case STEEP: next = switch (destination)
            {
                case NORTH, CAVERN, PASSAGE -> FALLS;
                case DOWN, CLIMB -> LOW;
                default -> VOID;
            }; break;

            case ABOVEP: next = switch (destination)
            {
                case NORTH -> SJUNC;
                case DOWN, PASSAGE -> BEDQUILT;
                case SOUTH -> STALACTITE;
                default -> VOID;
            }; break;

            case SJUNC: next = switch (destination)
            {
                case SOUTHEAST -> BEDQUILT;
                case SOUTH -> ABOVEP;
                case NORTH -> WWINDOW;
                default -> VOID;
            }; break;

            case STALACTITE: next = switch (destination)
            {
                case NORTH -> ABOVEP;
                case DOWN, JUMP, CLIMB -> chance < .41 ? ALIKE6 : (chance > .49 ? ALIKE9 : ALIKE4);
                default -> VOID;
            }; break;

            case LOW: next = switch (destination)
            {
                case BEDQUILT -> BEDQUILT;
                case SOUTHWEST -> SCORR;
                case NORTH -> CRAWL;
                case SOUTHEAST, ORIENTAL -> ORIENTAL;
                default -> VOID;
            }; break;

            case CRAWL: next = switch (destination)
            {
                case SOUTH, CRAWL, OUT -> LOW;
                default -> VOID;
            }; break;

            case WWINDOW: next = switch (destination)
            {
                case WEST -> SJUNC;
                case JUMP -> REMARK;
                default -> VOID;
            }; break;

            case ORIENTAL: next = switch (destination)
            {
                case SOUTHEAST -> CHEESE;
                case WEST, CRAWL -> LOW;
                case UP, NORTH, CAVERN -> MISTY;
                default -> VOID;
            }; break;

            case MISTY: next = switch (destination)
            {
                case SOUTH, ORIENTAL -> ORIENTAL;
                case WEST -> ALCOVE;
                default -> VOID;
            }; break;

            case ALCOVE: next = switch (destination)
            {
                case NORTHWEST, CAVERN -> MISTY;
                case EAST, PASSAGE, PLOVER -> game.getItemsInHand() > 1 || (game.getItemsInHand() > 0 && !game.isInHand(GameObjects.EMERALD)) ? REMARK : PROOM;
                default -> VOID;
            }; break;

            case PROOM: next = switch (destination)
            {
                case WEST, PASSAGE, OUT -> game.getItemsInHand() > 1 || (game.getItemsInHand() > 0 && !game.isInHand(GameObjects.EMERALD)) ? REMARK : ALCOVE;
                case PLOVER ->
                {
                    if (game.isInHand(GameObjects.EMERALD))
                    {
                        game.setRelocate(true);
                    }
                    yield Y2;
                }
                case NORTHEAST, DARK -> DROOM;
                default -> VOID;
            }; break;

            case DROOM: next = switch (destination)
            {
                case SOUTH, PLOVER, OUT -> PROOM;
                default -> VOID;
            }; break;

            case SLAB: next = switch (destination)
            {
                case SOUTH -> WEST2PIT;
                case UP, CLIMB -> ABOVER;
                case NORTH -> BEDQUILT;
                default -> VOID;
            }; break;

            case ABOVER: next = switch (destination)
            {
                case DOWN, SLAB -> SLAB;
                case SOUTH -> game.isDragonAlive() ? SCAN1 : SCAN2;
                case NORTH -> MIRROR;
                case RESERVOIR -> RESER;
                default -> VOID;
            }; break;

            case MIRROR: next = switch (destination)
            {
                case SOUTH -> ABOVER;
                case NORTH, RESERVOIR -> RESER;
                default -> VOID;
            }; break;

            case RESER: next = switch (destination)
            {
                case SOUTH, OUT -> MIRROR;
                default -> VOID;
            }; break;

            case SCAN1: next = switch (destination)
            {
                case NORTH, OUT -> ABOVER;
                case EAST, FORWARD -> REMARK;
                default -> VOID;
            }; break;

            case SCAN2: next = switch (destination)
            {
                case NORTH -> ABOVER;
                case EAST -> SECRET;
                default -> VOID;
            }; break;

            case SCAN3: next = switch (destination)
            {
                case EAST, OUT -> SECRET;
                case NORTH, FORWARD -> REMARK;
                default -> VOID;
            }; break;

            case SECRET: next = switch (destination)
            {
                case EAST -> MTKHALL;
                case WEST -> game.isDragonAlive() ? SCAN3 : SCAN2;
                case DOWN -> WIDE;
                default -> VOID;
            }; break;

            case WIDE: next = switch (destination)
            {
                case SOUTH -> TIGHT;
                case NORTH -> TALL;
                default -> VOID;
            }; break;

            case TIGHT: next = switch (destination)
            {
                case NORTH -> WIDE;
                default -> VOID;
            }; break;

            case TALL: next = switch (destination)
            {
                case EAST -> WIDE;
                case WEST -> BOULDERS;
                case NORTH, CRAWL -> CHEESE;
                default -> VOID;
            }; break;

            case BOULDERS: next = switch (destination)
            {
                case SOUTH -> TALL;
                default -> VOID;
            }; break;

            case SCORR: next = switch (destination)
            {
                case DOWN -> LOW;
                case UP -> SWSIDE;
                default -> VOID;
            }; break;

            case SWSIDE: switch(destination)
            {
                case SOUTHWEST:	next = SCORR; break;
                case OVER, ACROSS, CROSS, NORTHEAST:
                    if (game.getStateOfTroll() < 2 || game.isCollapse())
                    {
                        next = REMARK;
                    }
                    else
                    {
                        if (game.getStateOfTroll() == 3)
                        {
                            game.setTroll();
                        }
                        next = NESIDE;
                    }
                    break;
                case JUMP: next = REMARK; break;
                default: next = VOID; break;
            } break;

            case DEAD0: next = switch (destination)
            {
                case SOUTH, OUT -> CROSS;
                default -> VOID;
            }; break;

            case DEAD1: next = switch (destination)
            {
                case WEST, OUT -> ALIKE11;
                default -> VOID;
            }; break;

            case DEAD2: next = switch (destination)
            {
                case SOUTHEAST -> ALIKE13;
                default -> VOID;
            }; break;

            case DEAD3: next = switch (destination)
            {
                case WEST, OUT -> ALIKE4;
                default -> VOID;
            }; break;

            case DEAD4: next = switch (destination)
            {
                case EAST, OUT -> ALIKE4;
                default -> VOID;
            }; break;

            case DEAD5: next = switch (destination)
            {
                case UP, OUT -> ALIKE3;
                default -> VOID;
            }; break;

            case DEAD6: next = switch (destination)
            {
                case WEST, OUT -> ALIKE9;
                default -> VOID;
            }; break;

            case DEAD7: next = switch (destination)
            {
                case UP, OUT -> ALIKE10;
                default -> VOID;
            }; break;

            case DEAD8: next = switch (destination)
            {
                case EAST, OUT -> BRINK;
                default -> VOID;
            }; break;

            case DEAD9: next = switch (destination)
            {
                case SOUTH, OUT -> ALIKE3;
                default -> VOID;
            }; break;

            case DEAD10: next = switch (destination)
            {
                case EAST, OUT -> ALIKE12;
                default -> VOID;
            }; break;

            case DEAD11: next = switch (destination)
            {
                case UP, OUT -> ALIKE8;
                default -> VOID;
            }; break;

            case NESIDE: switch(destination)
            {
                case NORTHEAST:	next = CORR; break;
                case OVER, ACROSS, CROSS, SOUTHWEST:
                    int trollState = game.getStateOfTroll();
                    if (trollState == 0 || trollState == 1 || game.isCollapse())
                    {
                        next = REMARK;
                    }
                    else
                    {
                        if (trollState == 3)
                        {
                            game.setTroll();
                        }
                        else if (game.getStateOfBear() == 2)
                        {
                            game.collapseBridge();
                        }
                        next = SWSIDE;
                    }
                    break;
                case JUMP: next = REMARK; break;
                case FORK: next = FORK; break;
                case VIEW: next = VIEW; break;
                case BARREN: next = FBARR; break;
                default: next = VOID; break;
            } break;

            case CORR: next = switch (destination)
            {
                case WEST -> NESIDE;
                case EAST, FORK -> FORK;
                case VIEW -> VIEW;
                case BARREN -> FBARR;
                default -> VOID;
            }; break;

            case FORK: next = switch (destination)
            {
                case WEST -> CORR;
                case NORTHEAST, LEFT -> WARM;
                case SOUTHEAST, RIGHT, DOWN -> LIME;
                case VIEW -> VIEW;
                case BARREN -> FBARR;
                default -> VOID;
            }; break;

            case WARM: next = switch (destination)
            {
                case SOUTH, FORK -> FORK;
                case NORTH, VIEW -> VIEW;
                case EAST, CRAWL -> CHAMBER;
                default -> VOID;
            }; break;

            case VIEW: next = switch (destination)
            {
                case SOUTH, PASSAGE, OUT -> WARM;
                case FORK, DOWN -> REMARK;
                case JUMP -> FORK;
                default -> VOID;
            }; break;

            case CHAMBER: next = switch (destination)
            {
                case WEST, OUT, CRAWL -> WARM;
                case FORK -> FORK;
                case VIEW -> VIEW;
                default -> VOID;
            }; break;

            case LIME: next = switch (destination)
            {
                case NORTH, UP, FORK -> FORK;
                case SOUTH, DOWN, BARREN -> FBARR;
                case VIEW -> VIEW;
                default -> VOID;
            }; break;

            case FBARR: next = switch (destination)
            {
                case WEST, UP -> LIME;
                case FORK -> FORK;
                case EAST, IN, BARREN, ENTER -> BARR;
                case VIEW -> VIEW;
                default -> VOID;
            }; break;

            case BARR: next = switch (destination)
            {
                case WEST, OUT -> FBARR;
                case FORK -> FORK;
                case VIEW -> VIEW;
                default -> VOID;
            }; break;

            case NEEND: next = switch (destination)
            {
                case SOUTHWEST -> SWEND;
                default -> VOID;
            }; break;

            case SWEND: next = switch (destination)
            {
                // TODO: DOWN -> GRATE_RMK?
                case NORTHEAST -> NEEND;
                default -> VOID;
            }; break;

            default: next = REMARK;	break;
        }
        return next;
    }

    private static Locations atWittsEnd()
    {
        return AdventMain.generate() < .06 ? ANTE : REMARK;
    }

    private static Locations bridgeRemark(boolean onEastSide)
    {
        return advent.isCrystalBridge() ? (onEastSide ? WESTFISSURE : EASTFISSURE) : REMARK;
    }

    private static Locations throughGrate(boolean goingIn)
    {
        return advent.isGrateUnlocked() ? (goingIn ? INSIDE : OUTSIDE) : REMARK;
    }
}
