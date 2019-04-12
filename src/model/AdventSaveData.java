/**
 * @author Ariana Fairbanks
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class AdventSaveData implements Serializable
{
	private static final long serialVersionUID = -4962116536507971292L;
	public String log;
	public ArrayList<GameObjects> mobileObjectsData;
	public int[] visits;
	public HashMap<GameObjects, Boolean> found;
	public Location[] objectLocation;
	public Location currentLocation;
	public Location previousLocation;

	public boolean dead;
	public boolean beginning;
	public boolean closing, closed;
	public boolean grateUnlocked;
	public boolean crystalBridge;
	public boolean light;
	public boolean snake;
	public boolean oilDoor;
	public boolean dragon;
	public boolean birdInCage;
	public boolean bearAxe;
	public boolean broken;
	public boolean haveGold;
	public boolean relocateData;
	public boolean collapseData;
	public boolean justCollapsedData;
	public boolean lampWarn;
	public boolean panic;
	public boolean over;
	public boolean shortcut;
	public boolean dwarvesOn;
	public boolean battleUpdate;
	public boolean wayIsBlocked;
	public boolean justBlocked;
	public boolean locationChange;
	public boolean seriousQuestion;
	public boolean increaseTurns;
	public boolean noMore;

	public boolean wellInCave;
	public boolean read;
	public boolean quit;
	public byte hint;
	public byte h1, h2, h3, h4, h5, h6;

	public byte clock1, clock2;
	public byte quest;
	public int brief;
	public int score;
	public int bonus;
	public byte turns;
	public int lamp;
	public byte itemsInHand;
	public byte deaths;
	public byte fatality;
	public int tallyData;
	public byte lostTreasures;
	public byte plant;
	public byte bottle;
	public byte usedBatteries;
	public byte pirate;
	public byte movesWOEncounter;
	public byte dwarves;
	public byte dwarvesLeft;
	public byte dwarfPresent;
	public byte trollData;
	public byte bearData;
	public byte chain;
	public byte west;
	public byte foo;
	public byte rod1, rod2;
	public byte bottles;
	public byte lamps;
	public byte pillows;
	public byte oysters;
	public byte grates;
	public byte cages;
	public byte birds;
	public byte snakes;
	public boolean textField;
}
