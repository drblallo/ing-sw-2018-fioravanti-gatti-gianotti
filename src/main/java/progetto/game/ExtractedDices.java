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

	void addDice(Dice newDice)
	{
		extractedDicesData = extractedDicesData.addDice(newDice);
		change(extractedDicesData);
	}

	Dice changeDice(int index, Dice newDice)
	{
		Dice dice = extractedDicesData.getDice(index);
		extractedDicesData = extractedDicesData.changeDice(index, newDice);
		change(extractedDicesData);
		return dice;
	}

	Dice removeDice(int index)
	{
		Dice dice = extractedDicesData.getDice(index);
		extractedDicesData = extractedDicesData.removeDice(index);
		change(extractedDicesData);
		return dice;
	}
}
