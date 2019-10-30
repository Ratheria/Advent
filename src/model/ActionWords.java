/**
 *	@author Ariana Fairbanks
 */

package model;

public enum ActionWords implements KnownWord
{
	NOTHING, LOOK, ABSTAIN, TAKE, DROP, OPEN, CLOSE, ON, OFF, WAVE, CALM, 
	GO, RELAX, POUR, EAT, DRINK, RUB, TOSS, WAKE, FEED, 
	FILL, BREAK, BLAST, KILL, SAY, READ, FEEFIE, BRIEF, VERBOSE,
	FIND, INVENTORY, SCORE, QUIT, 
	PET, SAVE, LOAD;

	@Override
	public byte getType() 
	{
		return 2;
	}
}
