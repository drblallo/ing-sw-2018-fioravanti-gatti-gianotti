package progetto.game;

import progetto.utils.AbstractObservable;

/**
 * Dices extracted by the player on the main board
 */
public final class ExtractedDices extends AbstractObservable<ExtractedDicesData> {

	private ExtractedDicesData extractedDicesData = new ExtractedDicesData();

	public ExtractedDicesData getExtractedDicesData()
	{
		return extractedDicesData;
	}

	/**
	 * Add dice to extracted dices
	 * @param newDice to add
	 */
	void addDice(Dice newDice)
	{
		extractedDicesData = extractedDicesData.addDice(newDice);
		change(extractedDicesData);
	}

	/**
	 * Change the dice in position index
	 * @param index position of the dice to change
	 * @param newDice to add
	 * @return removed dice
	 */
	Dice changeDice(int index, Dice newDice)
	{
		Dice dice = extractedDicesData.getDice(index);
		extractedDicesData = extractedDicesData.changeDice(index, newDice);
		change(extractedDicesData);
		return dice;
	}

	/**
	 * Remove dice from extracted dices
	 * @param index position of the dice to remove
	 * @return removed dice
	 */
	Dice removeDice(int index)
	{
		Dice dice = extractedDicesData.getDice(index);
		extractedDicesData = extractedDicesData.removeDice(index);
		change(extractedDicesData);
		return dice;
	}
}
