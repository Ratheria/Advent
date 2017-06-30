/**
 *	@author Ariana Fairbanks
 *	These words always have a static response, 
 *	or always return the same message, if you will.
 *	That is why they are called message words.
 */

package model;

public enum MessageWords 
{
	MAGIC, HELP, TREE, DIG, LOST, MIST, CUSS, INFO, SWIM, DENNIS;

	public String getText(MessageWords input)
	{
		String result = "I don't know how.";
		switch(input)
		{
			case MAGIC:
				result = new String("Good try, but that is an old worn-out magic word.");
				break;
			
			case HELP:
				result = new String("I know of places, actions, and things. "
						+ "Most of my vocabulary describes places and is used to move you there."
						+ "To move, try words like forest, building, downstream, enter, east, west, north, south, up, or down. "
						+ "I know about a few special objects, like a black rod hidden in the cave. "
						+ "These objects can be manipulated using some of the action words that I know. "
						+ "Usually you will need to give both the object and the action words (in either order), but sometimes I can infer the object from the verb alone. "
						+ "Some objects also imply verbs; in particular, \"inventory\" implies \"take inventory\", which causes me to give you a list of what you are carrying. "
						+ "The objects have side effects; for instance, the rod scares the bird. "
						+ "Usually people having trouble moving just need to try a few more words. "
						+ "Usually people trying unsuccessfully to manupulate an object are attempting beyond their (or my!) capabilities and should try a completely different tack. "
						+ "To speed the game you can sometimes move long distances with a single word. "
						+ "For example, \"building\" usually gets you to the building from anywhere above ground except when lost in the forest. "
						+ "Also, note that cave passages turn a lot, and that leaving a room to the north does not guarantee entering the next from the south. \nGood luck!");
				break;
				
			case TREE:
				result = new String("The trees of the forest are large hardwood oak and maple, with an occasional grove of pine or spruce. "
						+ "There is quite a bit of under-growth, largely birch and ash saplings plus nondescript bushes of various sorts. "
						+ "This time of year visibility is quite restricted by all the leaves, but travel is quite easy if you detour around the spruce and berry bushes.");
				break;
				
			case DIG:
				result = new String("Digging without a shovel is quite impractical. Even with a shovel progress is unlikely.");
				break;
				
			case LOST:
				result = new String("I'm as confused as you are.");
				break;
				
			case MIST:
				result = new String("Mist is a white vapor, usually water, seen from time to time in caverns. It can be found anywhere but is frequently a sign of a deep pit leading down to water.");
				break;
				
			case CUSS:
				result = new String("Watch it!");
				break;
				
			case INFO:
				result = new String("If you want to end your adventure early, say \"quit\". "
						+ "To get full credit for a treasure, you must have left it safely in the building, though you get partial credit just for locating it. "
						+ "You lose points for getting killed, or for quitting, though the former costs you more. "
						+ "There are also points based on how much (if any) of the cave you've managed to explore; in particular, there is a large bonus just for getting in (to distinguish the beginners from the rest of the pack), and there are other ways to determine whether you've been through some of the more harrowing sections. "
						+ "If you think you've found all the treasures, just keep exploring for a while. If nothing interesting happens, you haven't found them all yet. If something interesting DOES happen, it means you're getting a bonus and have an opportunity to garner many more points in the master's section. "
						+ "I may occasionally offer hints if you seem to be having trouble. If I do, I will warn you in advance how much it will affect your score to accept the hints. "
						+ "Finally, to save paper, you may specify \"brief\", which tells me never to repeat the full description of a place unless you explicitly ask me to.");
				break;
				
			case DENNIS:
				result = new String("Thou cannotst go there. Who do you think thou art? A magistrate?!");
				break;
				
			default:
				result = new String("I don't know how.");
				break;
		}
		return result;
	}
}
