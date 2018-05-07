package progetto.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class NineDices {

	private static final int MAX_NUMBER_OF_DICES = 9;

	private final List<Dice> dicesList;

	NineDices()
	{
		ArrayList<Dice> temp = new ArrayList<>();
		this.dicesList = Collections.unmodifiableList(temp);
	}

	NineDices(NineDices nineDices)
	{
		ArrayList<Dice> temp = new ArrayList<>(nineDices.dicesList);
		this.dicesList = Collections.unmodifiableList(temp);
	}

	NineDices(NineDices nineDices, Dice newDice)
	{
		ArrayList<Dice> temp = new ArrayList<>(nineDices.dicesList);
		temp.add(newDice);
		this.dicesList = Collections.unmodifiableList(temp);
	}

	NineDices(NineDices nineDices, Dice newDice, int index)
	{
		ArrayList<Dice> temp = new ArrayList<>(nineDices.dicesList);
		temp.remove(index);
		temp.add(index, newDice);
		this.dicesList = Collections.unmodifiableList(temp);
	}

	/**
	 * Get a dice from the group, do not remove it
	 */
	Dice getDice(int index)
	{
		if(isFree(index))
		{
			return null;
		}
		return dicesList.get(index);
	}

	/**
	 * Verify if position index is free
	 */
	public boolean isFree(int index)
	{
		try {
			dicesList.get( index );
			return false;
		} catch ( IndexOutOfBoundsException e ) {
			return true;
		}
	}

	int getNumberOfDices()
	{
		return dicesList.size();
	}

	/**
	 * Add a dice to the group
	 */
	NineDices addDice(Dice newDice)
	{
		if(dicesList.size()>=MAX_NUMBER_OF_DICES)
		{
			return this;
		}
		return new NineDices(this, newDice);
	}

	/**
	 * Put the new dice in the position index (in place of the previous one that is removed)
	 */
	NineDices changeDice(int index, Dice newDice)
	{
		if(index<0 || index > dicesList.size()-1)
		{
			return this;
		}

		return new NineDices(this, newDice, index);
	}

}

