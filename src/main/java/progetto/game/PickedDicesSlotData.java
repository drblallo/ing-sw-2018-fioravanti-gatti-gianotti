package progetto.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Slot for picked dices (immutable)
 */
public final class PickedDicesSlotData {

	private final List<DicePlacementCondition> pickedDices;

	PickedDicesSlotData()
	{
		ArrayList<DicePlacementCondition> temp = new ArrayList<>();
		pickedDices = Collections.unmodifiableList(temp);
	}

	private PickedDicesSlotData(PickedDicesSlotData pickedDicesSlotData, DicePlacementCondition dicePlacementCondition)
	{
		ArrayList<DicePlacementCondition> temp = new ArrayList<>(pickedDicesSlotData.pickedDices);
		temp.add(dicePlacementCondition);
		pickedDices = Collections.unmodifiableList(temp);
	}

	private PickedDicesSlotData(PickedDicesSlotData pickedDicesSlotData, int index)
	{
		ArrayList<DicePlacementCondition> temp = new ArrayList<>(pickedDicesSlotData.pickedDices);
		temp.remove(index);
		pickedDices = Collections.unmodifiableList(temp);
	}

	private PickedDicesSlotData(PickedDicesSlotData pickedDicesSlotData, int index, DicePlacementCondition dicePlacementCondition)
	{
		ArrayList<DicePlacementCondition> temp = new ArrayList<>(pickedDicesSlotData.pickedDices);
		temp.remove(index);
		temp.add(index, dicePlacementCondition);
		pickedDices = Collections.unmodifiableList(temp);
	}

	public int getNDices()
	{
		return pickedDices.size();
	}

	public DicePlacementCondition getDicePlacementCondition(int index)
	{
		if(exists(index))
		{
			return pickedDices.get(index);
		}
		return null;
	}

	private boolean exists(int index) {
		try {
			pickedDices.get(index);
			return true;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	PickedDicesSlotData add(Dice dice, boolean ignoreColor, boolean ignoreValue, boolean ignoreAdjacent)
	{
		return new PickedDicesSlotData(this, new DicePlacementCondition(dice, ignoreColor, ignoreValue, ignoreAdjacent));
	}

	PickedDicesSlotData remove(int index)
	{
		if(exists(index))
		{
			return new PickedDicesSlotData(this, index);
		}
		return this;
	}

	PickedDicesSlotData setIgnoreColor(int index, boolean ignoreColor)
	{
		DicePlacementCondition dpc = getDicePlacementCondition(index);
		if(dpc!=null)
		{
			DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dpc.getDice(), ignoreColor, dpc.getIgnoreValue(), dpc.getIgnoreAdjacent());
			return new PickedDicesSlotData(this, index, dicePlacementCondition);
		}
		return this;
	}

	PickedDicesSlotData setIgnoreValue(int index, boolean ignoreValue)
	{
		DicePlacementCondition dpc = getDicePlacementCondition(index);
		if(dpc!=null)
		{
			DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dpc.getDice(), dpc.getIgnoreColor(), ignoreValue, dpc.getIgnoreAdjacent());
			return new PickedDicesSlotData(this, index, dicePlacementCondition);
		}
		return this;

	}

	PickedDicesSlotData setIgnoreAdjacent(int index, boolean ignoreAdjacent)
	{
		DicePlacementCondition dpc = getDicePlacementCondition(index);
		if(dpc!=null)
		{
			DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dpc.getDice(), dpc.getIgnoreColor(), dpc.getIgnoreValue(), ignoreAdjacent);
			return new PickedDicesSlotData(this, index, dicePlacementCondition);
		}
		return this;
	}

}
