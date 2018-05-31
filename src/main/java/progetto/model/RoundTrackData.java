package progetto.model;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Immuutable class with data of RoundTrack
 */
public final class RoundTrackData implements Serializable
{

	public static final int NUMBER_OF_ROUNDS = 10;

	private final NineDices[] dices = new NineDices[NUMBER_OF_ROUNDS];

	private static final Logger LOGGER = Logger.getLogger(RoundTrackData.class.getName());

	/**
	 * Constructor
	 */
	public RoundTrackData() {}

	/**
	 * Constructor to add a dice in position index
	 * @param roundTrackData previous roundTrackData
	 * @param newDice dice to add
	 * @param index number of the round
	 */
	private RoundTrackData(RoundTrackData roundTrackData, Dice newDice, int index)
	{
		for (int i = 0; i < NUMBER_OF_ROUNDS; i++)
		{
			if (roundTrackData.dices[i] != null)
			{
				dices[i] = new NineDices(roundTrackData.dices[i]);
			}
		}
		if (dices[index] == null) {
			dices[index] = new NineDices();
		}
		dices[index] = dices[index].addDice(newDice);
	}

	/**
	 * Constructor to change a dice
	 * @param roundTrackData previous roundTrackData
	 * @param newDice dice to add
	 * @param index number of the round
	 * @param pos position of the dice to change
	 */
	private RoundTrackData(RoundTrackData roundTrackData, Dice newDice, int index, int pos)
	{
		for (int i = 0; i < NUMBER_OF_ROUNDS; i++)
		{
			if (roundTrackData.dices[i] != null)
			{
				dices[i] = new NineDices(roundTrackData.dices[i]);
			}
		}
		NineDices nineDices;
		nineDices = dices[index].changeDice(pos, newDice);
		dices[index] = nineDices;
	}

	/**
	 * Add a dice
	 * @param newDice dice to add
	 * @param index number of the round
	 * @return new RoundTrackData with the added dice
	 */
	RoundTrackData add(Dice newDice, int index) {
		if (index < 0 || index > NUMBER_OF_ROUNDS - 1) {
			return this;
		}
		return new RoundTrackData(this, newDice, index);
	}

	/**
	 * Verify if position index is free
	 * @param index number of the round
	 * @return boolean
	 */
	public boolean isFree(int index) {
		if (index < 0 || index > NUMBER_OF_ROUNDS - 1)
		{
			LOGGER.log(Level.SEVERE,"Wrong index");
			return true;
		}
		return (dices[index] == null);
	}

	/**
	 * Return first free position
	 * @return index of the first position
	 */
	public int firstFreePosition() {
		for (int i = 0; i < NUMBER_OF_ROUNDS; i++) {
			if (dices[i] == null) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Get a dice
	 * @param index number of the round
	 * @param pos position of the dice
	 * @return dice in round index, position pos
	 */
	public Dice getDice(int index, int pos)
	{
		if(!isFree(index))
		{
			return dices[index].getDice(pos);
		}
		return null;
	}

	/**
	 * Change a dice
	 * @param index number of the round
	 * @param pos position of the dice
	 * @param newDice dice to add
	 * @return new RoundTrackData with the changed dice
	 */
	RoundTrackData change(int index, int pos, Dice newDice)
	{
		if(!isFree(index))
		{
			return new RoundTrackData(this, newDice, index, pos);
		}
		LOGGER.log(Level.SEVERE,"Wrong position");
		return this;
	}

}
