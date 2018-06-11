package progetto.model;

/**
 * Dices extracted by the player on the main board
 */
public final class ExtractedDices extends Container<ExtractedDicesData> {

	/**
	 * Constructor
	 */
	public ExtractedDices()
	{
		super(new ExtractedDicesData());
	}

	/**
	 * Add dice to extracted dices
	 * @param newDice to add
	 */
	public void addDice(Dice newDice)
	{
		setData(getData().addDice(newDice));
	}

	/**
	 * Change the dice in position index
	 * @param index position of the dice to change
	 * @param newDice to add
	 * @return removed dice
	 */
	public Dice changeDice(int index, Dice newDice)
	{
		Dice dice = getData().getDice(index);
		setData(getData().changeDice(index, newDice));
		return dice;
	}

	/**
	 * Remove dice from extracted dices
	 * @param index position of the dice to remove
	 * @return removed dice
	 */
	public Dice removeDice(int index)
	{
		Dice dice = getData().getDice(index);
		setData(getData().removeDice(index));
		return dice;
	}
}
