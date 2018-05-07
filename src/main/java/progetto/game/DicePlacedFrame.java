package progetto.game;

import progetto.utils.AbstractObservable;

/**
 * Player window with positioned dice
 */
public final class DicePlacedFrame extends AbstractObservable<DicePlacedFrame> {

	private static final int MAX_NUMBER_OF_ROWS = 4;
	private static final int MAX_NUMBER_OF_COLUMNS = 5;

	private int nPlacedDices = 0;

	private Dice[][] dicesFrame = new Dice[MAX_NUMBER_OF_COLUMNS][MAX_NUMBER_OF_ROWS];

	void addDice(Dice newDice, int x, int y)
	{
		change(this);
		dicesFrame[x][y] = newDice;
		nPlacedDices++;
	}

	public Dice getDice(int x, int y)
	{
		return dicesFrame[x][y];
	}

	void removeDice(int x, int y)
	{
		change(this);
		dicesFrame[x][y] = null;
		nPlacedDices--;
	}

	public int getNDices()
	{
		return nPlacedDices;
	}

}