package progetto.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Immutable class with data of ExtractedDices
 */
public final class ExtractedDicesData implements Serializable{

	private final List<Dice> extractedDices;

	private static final Logger LOGGER = Logger.getLogger(ExtractedDicesData.class.getName());

	/**
	 * Constructor
	 */
	public ExtractedDicesData() {
		ArrayList<Dice> temp = new ArrayList<>();
		this.extractedDices = Collections.unmodifiableList(temp);
	}

	/**
	 * Constructor to add a dice
	 * @param extractedDicesData previous extractedDicesData
	 * @param newDice to add
	 */
	private ExtractedDicesData(ExtractedDicesData extractedDicesData, Dice newDice) {
		ArrayList<Dice> temp = new ArrayList<>(extractedDicesData.extractedDices);
		temp.add(newDice);
		this.extractedDices = Collections.unmodifiableList(temp);
	}

	/**
	 * Constructor to change dice in position index
	 * @param extractedDicesData previous extractedDicesData
	 * @param newDice to add
	 * @param index position where change the dice
	 */
	private ExtractedDicesData(ExtractedDicesData extractedDicesData, Dice newDice, int index)
	{
		ArrayList<Dice> temp = new ArrayList<>(extractedDicesData.extractedDices);
		temp.remove(index);
		temp.add(index, newDice);
		this.extractedDices = Collections.unmodifiableList(temp);
	}

	/**
	 * Constructor to remove dice in position index
	 * @param extractedDicesData previous extractedDicesData
	 * @param index position where remove the dice
	 */
	private ExtractedDicesData(ExtractedDicesData extractedDicesData, int index) {
		ArrayList<Dice> temp = new ArrayList<>(extractedDicesData.extractedDices);
		temp.remove(index);
		this.extractedDices = Collections.unmodifiableList(temp);
	}

	/**
	 * Get dice in position index
	 * @param index position
	 * @return dice
	 */
	public Dice getDice(int index) {
		if(exists(index))
		{
			return extractedDices.get(index);
		}
		return null;
	}

	/**
	 * Add dice
	 * @param newDice to add
	 * @return new ExtractedDicesData with added dice
	 */
	ExtractedDicesData addDice(Dice newDice) {
		return new ExtractedDicesData(this, newDice);
	}

	/**
	 * Get number of dices
	 * @return number of dices
	 */
	public int getNumberOfDices() {
		return extractedDices.size();
	}

	/**
	 * Change dice in position index
	 * @param index position
	 * @param newDice to add
	 * @return new ExtractedDicesData without changed dice
	 */
	ExtractedDicesData changeDice(int index, Dice newDice) {
		if(exists(index))
		{
			return new ExtractedDicesData(this, newDice, index);
		}
		return this;
	}

	/**
	 * Remove dice in position index
	 * @param index position
	 * @return new ExtractedDicesData without removed dice
	 */
	ExtractedDicesData removeDice(int index) {
		if(exists(index))
		{
			return new ExtractedDicesData(this, index);
		}
		return this;
	}

	/**
	 * Method to verify if index is a valid position with a dice
	 * @param index position to verify
	 * @return boolean
	 */
	public boolean exists(int index) {
		try {
			extractedDices.get(index);
			return true;
		} catch (IndexOutOfBoundsException e) {
			LOGGER.log(Level.SEVERE,"Wrong index");
			return false;
		}
	}


}
