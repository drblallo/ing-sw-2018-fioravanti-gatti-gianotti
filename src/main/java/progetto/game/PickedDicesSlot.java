package progetto.game;


/**
 * Dices that still need to be placed that have been taken by the player
 */
public final class PickedDicesSlot extends DataContainer<PickedDicesSlotData> {

	PickedDicesSlot()
	{
		super(new PickedDicesSlotData());
	}

	void add(Dice dice, boolean ignoreColor, boolean ignoreValue, boolean ignoreAdjacent)
	{
		setData(getData().add(dice, ignoreColor, ignoreValue, ignoreAdjacent));
	}

	public int getNDices()
	{
		return getData().getNDices();
	}

	DicePlacementCondition remove(int index)
	{
		DicePlacementCondition dicePlacementCondition = getData().getDicePlacementCondition(index);
		setData(getData().remove(index));
		return dicePlacementCondition;
	}

	void setIgnoreColor(int index, boolean ignoreColor)
	{
		setData(getData().setIgnoreColor(index, ignoreColor));
	}

	void setIgnoreValue(int index, boolean ignoreValue)
	{
		setData(getData().setIgnoreValue(index, ignoreValue));
	}

	void setIgnoreAdjacent(int index, boolean ignoreAdjacent)
	{
		setData(getData().setIgnoreAdjacent(index, ignoreAdjacent));
	}


}
