package progetto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  Immutable support class with dices in a position of the roundTrack
 *  @author Michele
 */
public final class NineDices implements Serializable{

	private final List<Dice> dicesList;

	/**
	 * Constructor
	 */
	NineDices()
	{
		ArrayList<Dice> temp = new ArrayList<>();
		this.dicesList = Collections.unmodifiableList(temp);
	}

	/**
	 * Costructor copy
	 * @param nineDices nine dices to copy
	 */
	NineDices(NineDices nineDices)
	{
		ArrayList<Dice> temp = new ArrayList<>(nineDices.dicesList);
		this.dicesList = Collections.unmodifiableList(temp);
	}

	/**
	 * Constructor to add a dice
	 * @param nineDices previous nineDices
	 * @param newDice dice to add
	 */
	NineDices(NineDices nineDices, Dice newDice)
	{
		ArrayList<Dice> temp = new ArrayList<>(nineDices.dicesList);
		temp.add(newDice);
		this.dicesList = Collections.unmodifiableList(temp);
	}

	/**
	 * Constructor to change a dice
	 * @param nineDices previous nineDices
	 * @param newDice dice to add in position index
	 * @param index position of the dice to remove and for the dice to add
	 */
	NineDices(NineDices nineDices, Dice newDice, int index)
	{
		ArrayList<Dice> temp = new ArrayList<>(nineDices.dicesList);
		temp.remove(index);
		temp.add(index, newDice);
		this.dicesList = Collections.unmodifiableList(temp);
	}

	/**
	 * Get a dice from the group, do not remove it
	 * @param index position of the dice
	 * @return the dice in position index
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
	 * @param index position to verify
	 * @return boolean
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

	/**
	 * Get number of dices
	 * @return number of dices
	 */
	int getNumberOfDices()
	{
		return dicesList.size();
	}

	/**
	 * Add a dice
	 * @param newDice dice to add
	 * @return new NineDices with the added dice
	 */
	NineDices addDice(Dice newDice)
	{
		return new NineDices(this, newDice);
	}

	/**
	 * Put the new dice in position index (in place of the previous one that is removed)
	 * @param index position
	 * @param newDice dice to add
	 * @return new NineDices with the changed dice
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

