package progetto.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PickedDicesSlotData {

	private final List<DicePlacementCondition> pickedDices;

	PickedDicesSlotData()
	{
		ArrayList<DicePlacementCondition> temp = new ArrayList<>();
		pickedDices = Collections.unmodifiableList(temp);
	}

	PickedDicesSlotData(PickedDicesSlotData pickedDicesSlotData, DicePlacementCondition dicePlacementCondition)
	{
		ArrayList<DicePlacementCondition> temp = new ArrayList<>(pickedDicesSlotData.pickedDices);
		temp.add(dicePlacementCondition);
		pickedDices = Collections.unmodifiableList(temp);
	}

	PickedDicesSlotData(PickedDicesSlotData pickedDicesSlotData, int index)
	{
		ArrayList<DicePlacementCondition> temp = new ArrayList<>(pickedDicesSlotData.pickedDices);
		temp.remove(index);
		pickedDices = Collections.unmodifiableList(temp);
	}

	PickedDicesSlotData(PickedDicesSlotData pickedDicesSlotData, int index, DicePlacementCondition dicePlacementCondition)
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
		return pickedDices.get(index);
	}

	PickedDicesSlotData add(Dice dice, boolean ignoreColor, boolean ignoreValue, boolean ignoreAdjacent)
	{
		return new PickedDicesSlotData(this, new DicePlacementCondition(dice, ignoreColor, ignoreValue, ignoreAdjacent));
	}

	PickedDicesSlotData remove(int index)
	{
		return new PickedDicesSlotData(this, index);
	}

	PickedDicesSlotData setIgnoreColor(int index, boolean ignoreColor)
	{
		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(getDicePlacementCondition(index).getDice(), ignoreColor, getDicePlacementCondition(index).getIgnoreValue(), getDicePlacementCondition(index).getIgnoreAdjacent());
		return new PickedDicesSlotData(this, index, dicePlacementCondition);
	}

	PickedDicesSlotData setIgnoreValue(int index, boolean ignoreValue)
	{
		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(getDicePlacementCondition(index).getDice(), getDicePlacementCondition(index).getIgnoreColor(), ignoreValue, getDicePlacementCondition(index).getIgnoreAdjacent());
		return new PickedDicesSlotData(this, index, dicePlacementCondition);
	}

	PickedDicesSlotData setIgnoreAdjacent(int index, boolean ignoreAdjacent)
	{
		DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(getDicePlacementCondition(index).getDice(), getDicePlacementCondition(index).getIgnoreColor(), getDicePlacementCondition(index).getIgnoreValue(), ignoreAdjacent);
		return new PickedDicesSlotData(this, index, dicePlacementCondition);
	}

}
