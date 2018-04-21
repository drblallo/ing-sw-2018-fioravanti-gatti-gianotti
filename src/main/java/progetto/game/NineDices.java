package progetto.game;

/**
 * A group of up to nine dice
 */
public final class NineDices {

	private static final int MAX_NUMBER_OF_DICES = 9;

	private Dice[] dice = new Dice[MAX_NUMBER_OF_DICES];
	private int numberOfDices=0;

	Value getValue(int index)
	{
		return dice[index].getValue();
	}

	Color getColor(int index)
	{
		return dice[index].getColor();
	}

	/**
	 * Add a dice to the group
	 */
	void addDice(Dice newDice)
	{
		if(numberOfDices>=MAX_NUMBER_OF_DICES)
		{
			return;
		}
		dice[numberOfDices]=newDice;
		numberOfDices++;
	}

	int getNumberOfDices()
	{
		return numberOfDices;
	}

	/**
	 * Get a dice from the group, do not remove it
	 */
	Dice getDice(int index)
	{
		return dice[index];
	}

	/**
	 * Put the new dice in the position index (in place of the previous one)
	 */
	void changeDice(int index, Dice newDice)
	{
		if (dice[index] != null)
		{
			dice[index]=newDice;
		}
	}
}
