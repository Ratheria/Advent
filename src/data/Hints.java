package data;

public enum Hints
{
    NONE ("", 	0,  -1, null, null),
    INSTRUCTIONS ("Intro", 5,-1, null,
                  "Somewhere nearby is Colossal Cave, where others have found great fortunes in treasure and gold, though it is rumored that some who enter are never seen again. "
                      + "Magic is said to work in the cave. I will be your eyes and hands. Direct me with commands of 1 or 2 words. "
                      + "I should warn you that I only look at the first five letters of each word, so you'll have to enter \"northeast\" as \"ne\" to distinguish it from \"north\" "
                      + "(Should you get stuck, type \"help\" for some general hints. For information on how to end your adventure, etc., type \"info\".)\n\n"),
    BLAST ("Blast",  10,  -1, null,
           "It says, 'There is something strange about this place, such that one of the words I've always known now has a new effect.'"),
    GRATE ("Grate", 	2,   4, "\n\nAre you trying to get into the cave?",
           "The grate is very solid and has a hardened steel lock. You cannot enter without a key, and there are no keys in sight. "
               + "I would recommend looking elsewhere for the keys."),
    BIRD ("Bird"	, 	2,   5, "\n\nAre you trying to catch the bird?",
          "Something seems to be frightening the bird just now and you cannot catch it no matter what you try. Perhaps you might try later."),
    SNAKE ("Snake", 	2,   8, "\n\nAre you trying to deal somehow with the snake?",
           "You can't kill the snake, or drive it away, or avoid it, or anything like that. There is a way to get by, but you don't have the necessary resources right now."),
    MAZE ("Maze"	,  	4,  75, "\n\nDo you need help getting out of the maze?",
          "You can make the passages look less alike by dropping things."),
	DARK ("Dark"	,   5,  25, "\n\nAre you trying to explore beyond the Plover Room?",
          "There is a way to explore that region without having to worry about falling into a pit."),
	WITT ("Witt"	,   3,  20, "\n\nDo you need help getting out of here?",
          "Don't go west."),
    WEST ("West"	,   0,  10, null,
          "\n\nIf you prefer, simply type W rather than WEST.");

    public final String name;
    public final String question;
    public final String hint;

    public final int cost;
    public final int threshold;

    public int proc;
    public boolean given;

    static final Hints[] currentHintData = Hints.values();

    Hints(String name, int cost, int threshold, String question, String hint)
    {
        this.name = name;
        this.cost = cost;
        this.threshold = threshold;
        this.question = question;
        this.hint = hint;
        this.given = false;
        this.proc = 0;
    }

    public void proc()
    {
        if (this.underThreshold())
        {
            this.proc++;
        }
    }

    public boolean underThreshold()
    {
        return this.proc < this.threshold;
    }

    public static boolean[] getHintGivenStatus()
    {
        boolean[] given = new boolean[currentHintData.length];
        for (int i = 0; i < currentHintData.length; i++)
        {
            given[i] = currentHintData[i].given;
        }
        return given;
    }

    public static int[] getHintProcValues()
    {
        int[] proc = new int[currentHintData.length];
        for (int i = 0; i < currentHintData.length; i++)
        {
            proc[i] = currentHintData[i].proc;
        }
        return proc;
    }

    public static void loadHintStates(boolean[] given, int[] proc)
    {
        for (int i = 0; i < currentHintData.length; i++)
        {
            currentHintData[i].given = given[i];
            currentHintData[i].proc = proc[i];
        }
    }
}
