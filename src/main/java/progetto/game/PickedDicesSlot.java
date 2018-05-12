package progetto.game;

import progetto.utils.AbstractObservable;

/**
 * Dices that still need to be placed that have been taken by the player
 */
public final class PickedDicesSlot extends AbstractObservable<PickedDicesSlotData> {

	private PickedDicesSlotData pickedDicesSlotData = new PickedDicesSlotData();

	public PickedDicesSlotData getPickedDicesSlotData()
	{
		return pickedDicesSlotData;
	}

	void add(Dice dice, boolean ignoreColor, boolean ignoreValue, boolean ignoreAdjacent)
	{
		pickedDicesSlotData = pickedDicesSlotData.add(dice, ignoreColor, ignoreValue, ignoreAdjacent);
		change(pickedDicesSlotData);
	}

	public int getNDices()
	{
		return pickedDicesSlotData.getNDices();
	}

	DicePlacementCondition remove(int index)
	{
		DicePlacementCondition dicePlacementCondition = pickedDicesSlotData.getDicePlacementCondition(index);
		pickedDicesSlotData = pickedDicesSlotData.remove(index);
		change(pickedDicesSlotData);
		return dicePlacementCondition;
	}

	void setIgnoreColor(int index, boolean ignoreColor)
	{
		pickedDicesSlotData = pickedDicesSlotData.setIgnoreColor(index, ignoreColor);
	}

	void setIgnoreValue(int index, boolean ignoreValue)
	{
		pickedDicesSlotData = pickedDicesSlotData.setIgnoreValue(index, ignoreValue);
	}

	void setIgnoreAdjacent(int index, boolean ignoreAdjacent)
	{
		pickedDicesSlotData = pickedDicesSlotData.setIgnoreAdjacent(index, ignoreAdjacent);
	}


}
