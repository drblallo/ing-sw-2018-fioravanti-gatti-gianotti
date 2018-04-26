package progetto.game;

import progetto.utils.AbstractObservable;

/**
 * Dices extracted by the player on the main board
 */
public final class ExtractedDices extends AbstractObservable<ExtractedDices> {

	private static final int MAX_NUMBER_OF_DICES = 9;

	private Dice[] dicesExtracted = new Dice[MAX_NUMBER_OF_DICES];
	private int numberOfDices=0;

	public Value getValue(int index)
	{
		return dicesExtracted[index].getValue();
	}

	public Color getColor(int index)
	{
		return dicesExtracted[index].getColor();
	}

	void addDice(Dice newDice)
	{
		if(numberOfDices>=MAX_NUMBER_OF_DICES)
		{
			return;
		}
		change(this);
		dicesExtracted[numberOfDices]=newDice;
		numberOfDices++;
	}

	public int getNumberOfDices()
	{
		return numberOfDices;
	}

	public Dice getDice(int index)
	{
		return dicesExtracted[index];
	}

	void changeDice(int index, Dice newDice)
	{
		if (dicesExtracted[index] != null)
		{
			change(this);
			dicesExtracted[index]=newDice;
		}
	}
}
