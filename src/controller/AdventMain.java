package controller;

import java.util.Random;

import model.ActionWords;
import model.GameObjects;
import model.WordTypeMappings;
import model.Locations;
import model.MessageWords;
import state.GameStateHandler;
import view.AdventureFrame;


//TODO beginning/normal/serious question/etc. enum instead of ints
//TODO maybe change AdventData to have n save slots for advent games 
//TODO questions as enums


public class AdventMain 
{
	public static AdventGame ADVENT;
	
	public static Random random = new Random();
	public static final Locations places = Locations.THEVOID;
	public static final ActionWords actions = ActionWords.NOTHING;
	public static final GameObjects things = GameObjects.NOTHING;
	public static final MessageWords messages = MessageWords.DENNIS;
	public static WordTypeMappings wordTypes;
	
	public static final String empty = "";
	public static final String alikePassages = "You are in a maze of twisty little passages, all alike.";
	public static final String alikeT = "Maze All Alike";
	public static final String diffT = "Maze All Different";
	public static final String secretCanyon = "Secret Canyon";
	public static final String deadEnd = "Dead end.";
	public static final String deadEndT = "Dead End";
	public static final String okay = "Okay.";
	public static final String dontHave = "You are not carrying it!";
	public static final String nothing = "Nothing happens.";
	
	public static final String[] feeFieFoe = new String[] {"fee", "fie", "foe", "foo", "fum"};
	public static final int[] scores = new int[] {35, 100, 130, 200, 250, 300, 330, 349, 350};
	public static final String[] sMessages = new String[] 
	{	
		"You are obviously a rank amateur. Better luck next time.",
		"Your score qualifies you as a novice class adventurer.",
		"You have now achieved the rating 'Experienced Adventurer'.",
		"You may now consider yourself a 'Seasoned Adventurer'.",
		"You have reached 'Junior Master' status.",
		"Your score puts you in Master Adventurer Class C.",
		"Your score puts you in Master Adventurer Class B.",
		"Your score puts you in Master Adventurer Class A.",
		"All of Adventuredom gives tribute to you, Adventure Grandmaster!"
	};
	
	public static AdventureFrame frame;
	public static GameStateHandler stateHandler = new GameStateHandler();
	
	public static void main(String[] args)
	{
		wordTypes = new WordTypeMappings();
		ADVENT = new AdventGame();
		frame = new AdventureFrame();
		frame.setUp();
	}
	
	public static double generate()
	{	return random.nextDouble();	}
	
	public static String truncate(String s)
	{
		return s.substring(0, Math.min(s.length(), 5));
	}
	
}
