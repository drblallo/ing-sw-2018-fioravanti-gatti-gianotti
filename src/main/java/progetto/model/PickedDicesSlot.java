package progetto.model;


/**
 * Dices that still need to be placed that have been taken by the player
 */
public final class PickedDicesSlot extends Container<PickedDicesSlotData> {

	/**
	 * Constructor
	 */
	PickedDicesSlot()
	{
		super(new PickedDicesSlotData());
	}

	/**
	 * Add a dice and set ignore parameters
	 * @param dice dice to add
	 * @param ignoreColor
	 * @param ignoreValue
	 * @param ignoreAdjacent
	 */
	public void add(Dice dice, boolean ignoreColor, boolean ignoreValue, boolean ignoreAdjacent)
	{
		setData(getData().add(dice, ignoreColor, ignoreValue, ignoreAdjacent));
	}

	/**
	 * Add a dice
	 * @param dice dice to add
	 */
	public void add(Dice dice)
	{
		setData(getData().add(dice, false, false, false));
	}

	/**
	 * Get number of dices
	 * @return number of dices
	 */
	public int getNDices()
	{
		return getData().getNDices();
	}

	/**
	 * Remove a dicePlacementCondition (and a dice)
	 * @param index index of the item to remove
	 * @return removed dicePlacementCondition
	 */
	public DicePlacementCondition remove(int index)
	{
		DicePlacementCondition dicePlacementCondition = getData().getDicePlacementCondition(index);
		setData(getData().remove(index));
		return dicePlacementCondition;
	}

	/**
	 * Set ignore color
	 * @param index position
	 * @param ignoreColor ignore color
	 */
	public void setIgnoreColor(int index, boolean ignoreColor)
	{
		setData(getData().setIgnoreColor(index, ignoreColor));
	}

	/**
	 * Set ignore value
	 * @param index position
	 * @param ignoreValue ignore value
	 */
	void setIgnoreValue(int index, boolean ignoreValue)
	{
		setData(getData().setIgnoreValue(index, ignoreValue));
	}

	/**
	 * Set ignore adjacent
	 * @param index position
	 * @param ignoreAdjacent ignore adjacent
	 */
	public void setIgnoreAdjacent(int index, boolean ignoreAdjacent)
	{
		setData(getData().setIgnoreAdjacent(index, ignoreAdjacent));
	}

	/**
	 * Change the dice in a position
	 * @param index position
	 * @param dice dice to set
	 */
	public void changeDice(int index, Dice dice)
	{
		setData(getData().changeDice(index, dice));
	}

}
