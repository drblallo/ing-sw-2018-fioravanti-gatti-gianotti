package progetto.game;

/**
 * RoundTrack with up to nine dices for round
 */
public final class RoundTrack {

	private static final int NUMBER_OF_ROUNDS = 10;

	private NineDices[] dices = new NineDices[NUMBER_OF_ROUNDS];


	Value getValue(int index, int pos)
	{
		return dices[index].getValue(pos);
	}

	Color getColor(int index, int pos)
	{
		return dices[index].getColor(pos);
	}

	Dice getDice(int index, int pos)
	{
		return new Dice(dices[index].getValue(pos),dices[index].getColor(pos));
	}

	/**
	 * Add a dice in position index
	 */
	void add(Dice newDice, int index)
	{
		if(index<0 || index>NUMBER_OF_ROUNDS-1)
		{
			return;
		}
		if(dices[index]==null)
		{
			dices[index] = new NineDices();
		}
		dices[index].addDice(newDice);
	}

	/**
	 * Verify if position index is free
	 */
	boolean isFree(int index)
	{
		return (dices[index]==null);
	}

	/**
	 * Return first free position
	 */
	int firstFreePosition()
	{
		for(int i=0; i<NUMBER_OF_ROUNDS; i++)
		{
			if(dices[i]==null)
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * Change the dice in round index, position pos
	 * Return the dice previously present in this position
	 */
	Dice change(int index, int pos, Dice newDice)
	{
		Dice dice = dices[index].getDice(pos);
		dices[index].changeDice(pos, newDice);
		return dice;
	}

}