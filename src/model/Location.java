/**
 *	@author Ariana Fairbanks
 */

package model;

import controller.AdventControl;

public enum Location
{
	THEVOID(),
	INHAND(), 
	ROAD
	(
			true,
			"End Of Road",
			"You're at end of road again.",
			"You are standing at the end of a road before a small brick building. Around you is a forest. A small stream flows out of the building and down a gully."
			), 
	HILL
	(
			"Hill",
			"You're at hill in road.",
			"You have walked up a hill, still in the forest. The road slopes back down the other side of the hill. There is a building in the distance."
			), 
	BUILDING
	(
			true,
			"Building",
			"You're inside building.",
			"You are inside a building, a well house for a large spring."
			), 
	VALLEY
	(
			true,
			"Valley",
			"You're in valley.",
			"You are in a valley in the forest beside a stream tumbling along a rocky bed."
			), 
	FOREST
	(
			"Forest",
			"You're in forest.",
			"You are in open forest, with a deep valley to one side."
			), 
	WOODS
	(
			"Forest",
			"You're in forest near a road.",
			"You are in open forest near both a valley and a road."
			), 
	SLIT
	(
			true,
			"Slit",
			"You're at slit in streambed.",
			"At your feet all the water of the stream splashes into a 2-inch slit in the rock. Downstream the streambed is bare rock."
			), 
	OUTSIDE
	(
			"Outside Grate",
			"You're outside grate.",
			"You are in a 20-foot depression floored with bare dirt. Set into the dirt is a strong steel grate mounted in concrete. A dry streambed leads into the depression."
			), 
	
	INSIDE
	(
			"Below Grate",
			"You're below the grate.",
			"You are in a small chamber beneath a 3x3 steel grate to the surface. A low crawl over cobbles leads inward to the west."
			), 
	COBBLES
	(
			"Cobble Crawl",
			"You're in cobble crawl.",
			"You are crawling over cobbles in a low passage. There is a dim light at the east end of the pasage."
			), 
	DEBRIS
	(
			"Debris Room",
			"You're in debris room.",
			"You are in a debris room filled with stuff washed in from the surface. A low wide passage with cobbles becomes plugged with mud and debris here, but an awkward canyon leads upward and west. \nA note on the wall says:\n\tMagic Word \"XYZZY\""
			), 
	AWKWARD
	(
			"Awkward Canyon",
			"You are in an awkward sloping east/west canyon."
			), 
	BIRD
	(
			"Bird Chamber",
			"You're in bird chamber.",
			"You are in a splendid chamber thirty feet high. The walls are frozen rivers of orange stone. An awkward canyon and a good passage exit from east and west sides of the chamber."
			), 
	SMALLPIT
	(
			"Small Pit",
			"You're at top of small pit.",
			"At your feet is a small pit breathing traces of white mist. An east passage ends here except for a small crack leading on."
			),
	
	EASTMIST
	(
			"Hall Of Mists",
			"You're in Hall of Mists.",
			"You are at one end of a vast hall stretching forward out of sight to the west. There are openings to either side. Nearby, a wide stone staircase leads downward. The hall is filled with wisps of white mist swaying to and fro almost as if alive. A cold wind blows up the staircase. There is a passage at the top of a dome behind you."
			), 
	NUGGET
	(
			"Nugget Of Gold Room",
			"You're in nugget of gold room.",
			"This is a low room with a crude note on the wall. The note says, \n\t\"You won't get it up the steps\"."
			), 
	EASTFISSURE
	(
			"East Bank",
			"You're on east bank of fissure.",
			"You are on the east bank of a fissure slicing clear across the hall. The mist is quite thick here, and the fissure is too wide to jump."
			), 
	WESTFISSURE
	(
			"West Bank",
			"You're on the west side of the fissure in the Hall of Mists."
			), 
	WESTMIST
	(	
			"Hall Of Mists",
			"You're at west end of Hall of Mists.",
			"You are at the west end of Hall of Mists. A low wide crawl continues west and another goes north. To the south is a little passage 6 feet off the floor."
			),
	
	ALIKE1
	(
			AdventControl.alikeT,
			AdventControl.alikePassages
			), 
	ALIKE2
	(
			AdventControl.alikeT,
			AdventControl.alikePassages
			), 
	ALIKE3
	(
			AdventControl.alikeT,
			AdventControl.alikePassages
		), 
	ALIKE4
	(
			AdventControl.alikeT,
			AdventControl.alikePassages
		), 
	ALIKE5
	(
			AdventControl.alikeT,
			AdventControl.alikePassages
		), 
	ALIKE6
	(
			AdventControl.alikeT,
			AdventControl.alikePassages
		), 
	ALIKE7
	(
			AdventControl.alikeT,
			AdventControl.alikePassages
		), 
	ALIKE8
	(
			AdventControl.alikeT,
			AdventControl.alikePassages
		), 
	ALIKE9
	(
			AdventControl.alikeT,
			AdventControl.alikePassages
		), 
	ALIKE10
	(
			AdventControl.alikeT,
			AdventControl.alikePassages
		), 
	ALIKE11
	(
			AdventControl.alikeT,
			AdventControl.alikePassages
		), 
	ALIKE12
	(
			AdventControl.alikeT,
			AdventControl.alikePassages
		), 
	ALIKE13
	(
			AdventControl.alikeT,
			AdventControl.alikePassages
		), 
	ALIKE14
	(
			AdventControl.alikeT,
			AdventControl.alikePassages
		), 
	
	BRINK
	(
			"Brink Of Pit",
			"You're at brink of pit.",
			"You are on the brink of a thirty-foot pit with a massive orange column down one wall. You could climb down here but you could not get back up. The maze continues at this level."
			),
	EASTLONG
	(
			"Long Hall East",
			"You are at east end of long hall.",
			"You are at the east end of a very long hall apparently without side chambers. To the east a low wide crawl slants up. To the north a round two foot hole slants down."
			),
	WESTLONG
	(
			"Long Hall West",
			"You are at west end of long hall.",
			"You are at the west end of a very long featureless hall. The hall joins up with a narrow north/south passage."
			),
	
	DIFF0
	(
			AdventControl.diffT,
			"You are in a maze of twisty little passages, all different."
			),
	DIFF1
	(
			AdventControl.diffT,
			"You are in a maze of twisting little passages, all different."
			), 
	DIFF2
	(
			AdventControl.diffT,
			"You are in a little maze of twisty passages, all different."
			),
	DIFF3
	(
			AdventControl.diffT,
			"You are in a twisting maze of little passages, all different."
			),
	DIFF4
	(
			AdventControl.diffT,
			"You are in a twisting little maze of passages, all different."
			),
	DIFF5
	(
			AdventControl.diffT,
			"You are in a twisty little maze of passages, all different."
			),
	DIFF6
	(
			AdventControl.diffT,
			"You are in a twisty maze of little passages, all different."
			),
	DIFF7
	(
			AdventControl.diffT,
			"You are in a little twisty maze of passages, all different."
			),
	DIFF8
	(
			AdventControl.diffT,
			"You are in a maze of little twisting passages, all different."
			),
	DIFF9
	(
			AdventControl.diffT,
			"You are in a maze of little twisty passages, all different."
			),
	DIFF10
	(
			AdventControl.diffT,
			"You are in a little maze of twisting passages, all different."
			),
	
	PONY
	(
			AdventControl.deadEndT,
			AdventControl.deadEnd
			),
	CROSS
	(
			"Crossover",
			"You are at a crossover of a high N/S passage and a low E/W one."
			),
	HALLOFMOUNTAINKING
	(
			"Hall Of The Mt. King",
			"You're in Hall of Mt King.",
			"You are in the Hall of the Mountain King, with passages off in all directions."
			),
	WEST
	(
			"West Side Chamber",
			"You're in west side chamber.",
			"You are in the west side chamber of the Hall of the Mountain King. A passage continues west and up here."
			),
	SOUTH
	(
			"South Side Chamber",
			"You are in the south side chamber."	
			),
	NS
	(
			"North/South Passage",
			"You're in a N/S passage.",
			"You are in a low N/S passage at a hole in the floor. The hole goes down to an E/W passage."
			),
	Y2
	(
			"\"Y2\"",
			"You're at \"Y2\".",
			"You are in a large room, with a passage to the south, a passage to the west, and a wall of broken rock to the east. There is a large \"Y2\" on a rock in the room's center."
			),
	JUMBLE
	(
			"Rock Jumble",
			"You are in a jumble of rock, with cracks everywhere."
			),
	EASTWINDOW
	(
			"Window",
			"You're at window on pit.",
			"You're at a low window overlooking a huge pit, which extends up out of sight.	A floor is indistinctly visible over 50 feet below. Traces of white mist cover the floor of the pit, becoming thicker to the right. Marks in the dust around the window would seem to indicate that someone has been here recently. Directly across the pit from you and 25 feet away there is a similar window looking into a lighted room. A shadowy figure can be seen there peering back at you."
			),
	
	DIRTY
	(
			"Dirty Passage",
			"You're in dirty passage.",
			"You are in a dirty broken passage. To the east is a crawl. To the west is a large passage. Above you is a hole to another passage."
			),
	CLEAN
	(
			"Clean Pit",
			"You're by a clean pit.",
			"You are on the brink of a small clean climbable pit. A crawl leads west."
			),
	WET
	(
			true,
			"Wet Pit",
			"You're in pit by stream.",
			"You are in the bottom of a small pit with a little stream, which enters and exits through tiny slits."
			), 
	DUSTY
	(
			"Dusty Room",
			"You're in dusty rock room.",
			"You are in a large room full of dusty rocks. There is a big hole in the floor. There are cracks everywhere, and a passage leading east."
			),
	COMPLEX
	(
			"Complex Junction",
			"You're at complex junction.",
			"You are at a complex junction. A low hands and knees passage from the north joins a higher crawl from the east to make a walking passage going west. There is also a large room above. The air is damp here."
			),
	
	SHELL
	(
			"Shell Room",
			"You're in Shell Room.",
			"You are in a large room carved out of sedimentary rock. The floor and walls are littered with bits of shells embedded in the stone. A shallow passage proceeds downward, and a somewhat steeper one leads up. A low hands-and-knees passage enters from the south."
			),
	ARCH
	(
			"Arched Hall",
			"You're in arched hall.",
			"You are in an arched hall. A coral passage once continued up and east from here, but is now blocked by debris. The air smells of sea water."
			),
	RAGGED
	(
			"Ragged Corridor",
			"You are in a long sloping corridor with ragged sharp walls."
			),
	CULDESAC
	(
			"Cul-de-sac",
			"You are in a cul-de-sac about eight feet across."
			),
	ANTE
	(
			"Anteroom",
			"You're in anteroom.",
			"You are in an anteroom leading to a large passage to the east. Small passages go west and up. The remnants of recent digging are evident.\n\n\tA sign in midair here says \n\t\t\"CAVE UNDER CONSTRUCTION BEYOND THIS POINT.\n\t\tPROCEED AT OWN RISK\n\t\t[WITT CONSTRUCTION COMPANY]\""
			),
	WITT
	(
			"Witt's End",
			"You are at Witt's End.",
			"You are at Witt's End. Passages lead off in \"all\" directions."
			),

	BEDQUILT
	(
			"Bedquilt",
			"You're in Bedquilt.",
			"You are in Bedquilt, a long E/W passage with holes everywhere. \nTo explore at random select north, south, up or down."
			), 
	CHEESE
	(
			"Swiss Cheese Room",
			"You're in Swiss Cheese Room.",
			"You are in a room whose walls resemble swiss cheese. \nObvious passages go west, east, ne, and nw. Part of the room is occupied by a large bedrock block."
			),
	SOFT
	(
			"Soft Room",
			"You're in Soft Room.",
			"You are in the Soft Room. The walls are covered with heavy curtains, the floor with a thick pile carpet. Moss covers the ceiling."
			),
	
	EAST2PIT
	(
			"East Twopit Room",
			"You're at east end of Twopit Room.",
			"You are at the east end of the twopit room. The floor here is littered with thin rock slabs, which make it easy to descend the pits. There is a path here bypassing the pits to connect passages from east and west. There are holes all over, but the only big one is on the wall directly over the west pit where you can't get at it."
			),
	WEST2PIT
	(
			"West Twopit Room",
			"You're at west end of Twopit Room.",
			"You are at the west end of the twopit room. There is a large hole in the wall above the pit at this end of the room."
			),
	EASTPIT
	(
			"East Pit",
			"You're in east pit.",
			"You are that the bottom of the eastern pit in the twopit room. There is a small pool of oil in one corner of the pit."
			),
	WESTPIT
	(
			"West Pit",
			"You're in west pit.",
			"You are at the bottom of the western pit in the twopit room. There is a large hole in the wall about 25 feet above you."
			),
	
	NARROW
	(
			"Narrow Corridor",
			"You're in narrow corridor.",
			"You are in a long, narrow corridor stretching out of sight to the west. At the eastern end is a hole through which you can see a profusion of leaves."
			),
	GIANT
	(
			"Giant Room",
			"You're in Giant Room.",
			"You are in the Giant Room. The ceiling here is too high up for your lamp to show it. Cavernous passages lead east, north, and south. \n\nOn the west wall is scrawled the inscription, \n\t\"FEE FIE FOE FOO\"[sic]."
			),
	BLOCK
	(
			"Blocked Passage",
			"The passage here is blocked by a recent cave-in."
			),
	IMMENSE
	(
			"Immense Passage",
			"You are at one end of an immense north/south passage."
			),

	FALLS
	(
			true,
			"Falls",
			"You're in cavern with waterfall.",
			"You are in a magnificet cavern with a rushing stream, which cascades over a sparkling waterfall into a roaring whirlpool that disappears through a hole in the floor.\nPassages exit to the south and west."
			), 
	STEEP
	(
			"Steep Incline",
			"You're at steep incline above large room.",
			"You are at the top of a steep incline above a large room. You could climb down here, but you would not be able to climb up. There is a passage leading back to the north."
			),
	ABOVEP
	(
			AdventControl.secretCanyon,
			"You are in a secret N/S canyon above a sizable passage."
			),
	SJUNC
	(
			"Secret Canyon Junction",
			"You're at junction of three secret canyons.",
			"You are in a secret canyon at a junction of three canyons, bearing north, south, and SE. The north one is as tall as the other two combined."
			),
	TIGHT
	(
			"Tight Canyon",
			"The canyon here becomes too tight to go further south."
			),
	LOW
	(
			"Low Room",
			"You are in a large low room. Crawls lead north, SE, and SW."
			),
	CRAWL
	(
			"Dead End Crawl",
			"Dead end crawl."
			),
	WESTWINDOW
	(
			"Window",
			"You're at window on pit.",
			"You're at a low window overlooking a huge pit, which extends up out of sight. A floor is indistinctly visible over 50 feet below. Traces of white mist cover the floor of the pit, becoming thicker to the left. Marks in the dust around the window would seem to indicate that someone has been here recently. Directly across the pit from you and 25 feet away there is a similar window looking into a lighted room. A shadowy figure can be seen there peering back at you."
			),
	
	ORIENTAL
	(
			"Oriental Room",
			"You're in Oriental Room.",
			"This is the Oriental Room. Ancient oriental cave drawings cover the walls. A gently sloping passage leads upward to the north, another passage leads se, and a hands and knees crawl leads west."
			),
	MISTY
	(
			"Misty Cavern",
			"You're in misty cavern.",
			"You are following a wide path around the outer edge of a large cavern. Far below, through a heavy white mist, strange splashing noises can be heard. The mist rises up through a fissure in the ceiling. The path exits to the south and west."
			),
	ALCOVE
	(
			"Alcove",
			"You're in alcove.",
			"You are in an alcove. A small NW path seems to widen after a short distance. An extremely tight tunnel leads east. It looks like a very tight squeeze. An eerie light can be seen at the other end."
			),
	PROOM
	(
			"Plover Room",
			"You are in Plover Room.",
			"You're in a small chamber lit by an eerie green light. An extremely narrow tunnel exits to the west. A dark corridor leads NE."
			),
	DROOM
	(
			"Dark Room",
			"You're in the Dark-Room.",
			"You're in the Dark-Room. A corridor leading south is the only exit."
			),

	SLAB
	(
			"Slab Room",
			"You're in Slab Room.",
			"You are in a large low circular chamber whose floor is an immense slab fallen from the ceiling. (Slab Room). There once were large passages to the east and west, but they are now filled with boulders. Low small passages go north and south, and the south one quickly bends west around the boulders."
			), 
	ABOVER
	(
			AdventControl.secretCanyon,
			"You are in a secret N/S canyon above a large room."
			),
	MIRROR
	(
			"Mirror Canyon",
			"You're in mirror canyon.",
			"You are in a north/south canyon about 25 feet across. The floor is covered by white mist seeping in from the north. The walls extend upward for well over 100 feet. Suspended from some unseen point far above you, an enormous two-sided mirror is hanging parallel to and midway between the canyon walls. (The mirror is obviously provided for the use of the dwarves, who as you know are extremely vain.) A small window can be seen in either wall, some fifty feet up."
			),
	RESER
	(
			true,
			"Reservoir",
			"You're at reservoir.",
			"You are at the edge of a large underground reservoir. An opaque cloud of white mist fills the room and rises rapidly upward. The lake is fed by a stream, which tumbles out of a hole in the wall about 10 feet overhead and splashes noisily into the water somewhere within the mist. The only passage goes back toward the south."
			), 
	
	SCAN1
	(
			AdventControl.secretCanyon,
			"You're in a secret canyon that exits to the north and east."
			), 
	SCAN2
	(
			AdventControl.secretCanyon,
			"You're in a secret canyon that exits to the north and east."
			),
	SCAN3
	(
			AdventControl.secretCanyon,
			"You're in a secret canyon that exits to the north and east."
			),
	SECRET
	(
			AdventControl.secretCanyon,
			"You're in secret E/W canyon above tight canyon.",
			"You are in a secret canyon, which here runs E/W. It crosses over an very tight canyon 15 feet below. If you go down you may not be able to get back up."
			),

	WIDE
	(
			"Wide Place",
			"You're at a wide place in a very tight N/S canyon."
			), 
	STALACTITE
	(
			"Stalactite",
			"You're on top of stalactite.",
			"A large stalactite extends from the roof and almost reaches the floor below. You could climb down, but you would be unable to reach it to climb back up."
			), 
	TALL
	(
			"Tall Canyon",
			"You're in tall E/W canyon.",
			"You are in a tall E/W canyon. A low tight crawl goes 3 feet north and seems to open up."
			),
	BOULDERS
	(
			AdventControl.deadEndT,
			"The canyon runs into a mass of boulders --- dead end."
			),
	
	SCORR
	(
			"Sloping Corridor",
			"You are in sloping corridor.",
			"You are in a long winding corridor sloping out of sight in both directions."
			),
	SWSIDE
	(
			"South West Side",
			"You're on SW side of chasm.",
			"You are on one side of a large, deep chasm. A heavy white mist rising up from below obscures all view of the far side. A SW path leads away from the chasm into a winding corridor."
			),
	
	DEAD0
	(
			AdventControl.deadEndT,
			AdventControl.deadEnd
			),
	DEAD1
	(
			AdventControl.deadEndT,
			AdventControl.deadEnd
			),
	DEAD2
	(
			AdventControl.deadEndT,
			AdventControl.deadEnd
			),
	DEAD3
	(
			AdventControl.deadEndT,
			AdventControl.deadEnd
			),
	DEAD4
	(
			AdventControl.deadEndT,
			AdventControl.deadEnd
			), 
	DEAD5
	(
			AdventControl.deadEndT,
			AdventControl.deadEnd
			),
	DEAD6
	(
			AdventControl.deadEndT,
			AdventControl.deadEnd
			),
	DEAD7
	(
			AdventControl.deadEndT,
			AdventControl.deadEnd
			),
	DEAD8
	(
			AdventControl.deadEndT,
			AdventControl.deadEnd
			),
	DEAD9
	(
			AdventControl.deadEndT,
			AdventControl.deadEnd
			),
	DEAD10
	(
			AdventControl.deadEndT,
			AdventControl.deadEnd
			),
	DEAD11
	(
			AdventControl.deadEndT,
			AdventControl.deadEnd
			),
	
	NESIDE
	(
			"North East Side",
			"You're on NE side of chasm.",
			"You are on the far side of the chasm. A NE path leads away from the chasm on this side."
			),
	CORR
	(
			"Corridor",
			"You're in corridor.",
			"You're in a long east/west corridor. A faint rumbling noise can be heard in the distance."
			),
	FORK
	(
			"Fork In Path",
			"You're at fork in path.",
			"The path forks here. The left fork leads northeast. A dull rumbling seems to get louder in that direction. The right fork leads southeast down a gentle slope. The main corridor enters from the west."
			),
	WARM
	(
			"Warm Junction",
			"You're at junction with warm walls.",
			"The walls are quite warm here. From the north can be heard a steady roar, so loud that the entire cave seems to be trembling. Another passage leads south, and a low crawl goes east."
			),
	VIEW
	(
			"Breath-Taking View",
			"You're at breath-taking view.",
			"You are on the edge of a breath-taking view. Far below you is an active volcano, from which great gouts of molten lava come surging out, cascading back down into the depths. The glowing rock fills the farthest reaches of the cavern with a blood-red glare, giving every-thing an eerie, macabre appearance. The air is filled with flickering sparks of ash and a heavy smell of brimstone. The walls are hot to the touch, and the thundering of the volcano drowns out all other sounds. Embedded in the jagged roof far overhead are myriad twisted formations, composed of pure white alabaster, which scatter the murky light into sinister apparitions upon the walls. To one side is a deep gorge, filled with a bizarre chaos of tortured rock that seems to have been crafted by the Devil himself. An immense river of fire crashes out from the depths of the volcano, burns its way through the gorge, and plummets into a bottomless pit far off to your left. To the right, am immense geyser of blistering steam erupts continuously from a barren island in the center of a sulfurous lake, which bubbles ominously. The far right wall is aflame with an incandescence of its own, which lends an additional infernal splendor to the already hellish scene. \nA dark, foreboding passage exits to the south."
			),
	CHAMBER
	(
			"Boulder Chamber",
			"You're in chamber of boulders.",
			"You are in a small chamber filled with large boulders. The walls are very warm, causing the air in the room to be almost stifling from the heat. The only exit is a crawl heading west, through which a low rumbling noise is coming."
			),
	LIME
	(
			"Limestone Passage",
			"You're in limestone passage.",
			"You are walking along a gently sloping north/south passage lined with oddly shapped limestone formations."
			),
	FBARR
	(
			"In Front Of Barren Room",
			"You're in front of barren room.",
			"You are standing at the entrance to a large, barren room. \nA sign posted above the entrance reads:\n\t\"CAUTION! BEAR IN ROOM!\""
			),
	BARR
	(
			"Barren Room",
			"You're in barren room.",
			"You are inside a barren room. The center of the room is completely empty except for some dust. Marks in the dust lead away toward the far end of the room. The only exit is the way you came in."
			),
	
	NEEND
	(
			"North East End",
			"You're at NE end.",
			"You are at the northeast end of an immense room, even larger than the Giant Room. It appears to be a repository for the \"Adventure\" program. Massive torches far overhead bathe the room with smoky yellow light. Scattered about you can be seen a pile of bottles (all of them empty), a nursery of young beanstalks murmering quietly, a bed of oysters, a bundle of black rods with rusty stars on their ends, and a collection of brass lanterns. Off to one side a great many dwarves are sleeping on the floor, snoring loudly. \nA sign nearby reads:\n\t\"DO NOT DISTURB THE DWARVES!\"\nAn immense mirror is hanging against one wall, and stretches to the other end of the room, where various other sundry objects can be glimpsed dimly in the distance."
			),
	SWEND
	(
			"South West End",
			"You're at SW end.",
			"You are at the southwest end of the repository. To one side is a pit full of fierce green snakes. On the other side is a row of small wicker cages, each of which contains a little sulking bird. In one corner is a bundle of black rods with rusty marks on their ends. A large number of velvet pillows are scattered about on the floor. A vast mirror stretches off to the northeast. At your feet is a large steel grate, next to which is a sign that reads, \n\t\"TREASURE VAULT. KEYS IN MAIN OFFICE.\""
			),
	
	CRACK(),
	NECK(),
	LOSE(),
	CANT(),
	CLIMB(),
	CHECK(),
	SNAKED(),
	THRU(),
	DUCK(),
	SEWER(),
	UPNOUT(),
	DIDIT(),
	
	REMARK();
	
	public static Location[] locate = Location.values();
	
	public final String title;
	public final String shortDescription;
	public final String longDescription;
	public final boolean hasWater;
	public int visits = 0;
	
	private Location()
	{
		this.title = AdventControl.empty;
		this.shortDescription = AdventControl.empty;
		this.longDescription = AdventControl.empty;
		this.hasWater = false;
	}
	
	private Location(String title, String shortDescription)
	{
		this.title = title;
		this.shortDescription = shortDescription;
		this.longDescription = AdventControl.empty;
		this.hasWater = false;
	}
	
	private Location(String title, String shortDescription, String longDescription)
	{
		this.title = title;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.hasWater = false;
	}
	
	private Location(boolean hasWater, String title, String shortDescription)
	{
		this.title = title;
		this.shortDescription = shortDescription;
		this.longDescription = AdventControl.empty;
		this.hasWater = hasWater;
	}
	
	private Location(boolean hasWater, String title, String shortDescription, String longDescription)
	{
		this.title = title;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.hasWater = hasWater;
	}
	
	public int minLoc()
	{	return EASTMIST.ordinal();	}
	
	public int maxLoc()
	{	return DEAD0.ordinal();	}
	
	public boolean critters(Location here)
	{
		boolean result = false;
		if(here.ordinal() > EASTMIST.ordinal() && here.ordinal() < DEAD0.ordinal() && !(here == PROOM) && !(here == DROOM))
		{	result = true;	}
		return result;
	}
	
	public boolean outside(Location here)
	{
		boolean outside = false;
		if(here.ordinal() > INHAND.ordinal() && here.ordinal() < DEBRIS.ordinal())
		{	outside = true;	}
		return outside;
	}
	
	public boolean outsideCave(Location here)
	{
		boolean outside = false;
		if(here.ordinal() > INHAND.ordinal() && here.ordinal() < INSIDE.ordinal())
		{	outside = true;	}
		return outside;
	}
	
	public boolean upperCave(Location here)
	{
		boolean upper = false;
		if(here.ordinal() > OUTSIDE.ordinal() && here.ordinal() < OUTSIDE.ordinal())
		{	upper = true;	}
		return upper;
	}
	
	public int getOrdinal(Location here)
	{	return here.ordinal();	}
	
	public boolean dontNeedLamp(Location here)
	{
		boolean need = false;
		if(outside(here)||here == VIEW||here == NEEND||here == SWEND||here == PROOM)
		{	need = true;	}
		return need;
	}
	
	public String getDescription(Location here, int breif)
	{
		String description = null;
		if(!(here.longDescription.equals(AdventControl.empty)) && ((breif == 0 && here.visits % 5 == 0) || (breif == 2) 
				|| (here.visits == 0)))
		{
			description = here.longDescription;
		}
		else
		{
			description = here.shortDescription;
		}
		//here.visits++;
		return description;
	}
	
	public String getLongDescription(Location here)
	{
		String description = "";
		if(!(here.longDescription.equals(AdventControl.empty)))
		{	description = here.longDescription;	}
		return description;
	}
	
	public boolean beenHere(Location here)
	{
		boolean result = false;
		if(here.visits == 0 || here.visits % 5 == 0)
		{	result = false;	}
		return result;
	}
	
	public void visit(Location here, boolean canSee)
	{
		if(canSee)
		{
			here.visits++;
		}
	}
	
	public static int[] getVisitsArray()
	{
		int[] visitsArray = new int[locate.length];
		for(int i = 0; i < locate.length; i++)
		{
			visitsArray[i] = locate[i].visits;
		}
		return visitsArray;
	}
	
	public static void loadVisits(int[] visits)
	{
		for(int i = 0; i < locate.length; i++)
		{
			locate[i].visits = visits[i];
		}
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
						{	next = FOREST;	}
						else 
						{	next = WOODS;	}
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
						{	AdventControl.relocate();	}
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
					case SOUTHWEST: next = SCORR; break;
					case OVER: case ACROSS: case CROSS: case NORTHEAST:
						if(troll < 2 || collapse)
						{	next = REMARK;	}
						else
						{	
							next = NESIDE;
							if(troll == 3)
							{	AdventControl.setTroll();	}
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
							{	next = REMARK;	}
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
									{	AdventControl.collapse();	}
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
		{	result = ANTE;	}
		return result;
	}
	
	private Location eastRemark(boolean bridge)
	{
		Location result = EASTFISSURE;
		if(!bridge)
		{	result = REMARK;	}
		return result;
	}
	
	private Location westRemark(boolean bridge)
	{
		Location result = WESTFISSURE;
		if(!bridge)
		{	result = REMARK;	}
		return result;
	}
	
	private Location throughGrate(boolean grate)
	{
		Location result = REMARK;
		if(grate)
		{	result = INSIDE;	}
		return result;
	}
	
	private Location backThroughGrate(boolean grate)
	{
		Location result = REMARK;
		if(grate)
		{	result = OUTSIDE;	}
		return result;
	}
}
