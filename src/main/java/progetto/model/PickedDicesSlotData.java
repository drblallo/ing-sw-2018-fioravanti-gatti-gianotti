package progetto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Slot for picked dices (immutable)
 */
public final class PickedDicesSlotData implements Serializable{

	private final List<DicePlacementCondition> pickedDices;

	/**
	 * Constructor
	 */
	public PickedDicesSlotData()
	{
		ArrayList<DicePlacementCondition> temp = new ArrayList<>();
		pickedDices = Collections.unmodifiableList(temp);
	}

	/**
	 * Constructor to add a dicePlacementCondition
	 * @param pickedDicesSlotData previous pickedDiceSlotData
	 * @param dicePlacementCondition to add
	 */
	private PickedDicesSlotData(PickedDicesSlotData pickedDicesSlotData, DicePlacementCondition dicePlacementCondition)
	{
		ArrayList<DicePlacementCondition> temp = new ArrayList<>(pickedDicesSlotData.pickedDices);
		temp.add(dicePlacementCondition);
		pickedDices = Collections.unmodifiableList(temp);
	}

	/**
	 * Constructor to remove a dicePlacementCondition
	 * @param pickedDicesSlotData previous pickedDiceSlotData
	 * @param index position of the dicePlacementCondition to remove
	 */
	private PickedDicesSlotData(PickedDicesSlotData pickedDicesSlotData, int index)
	{
		ArrayList<DicePlacementCondition> temp = new ArrayList<>(pickedDicesSlotData.pickedDices);
		temp.remove(index);
		pickedDices = Collections.unmodifiableList(temp);
	}

	/**
	 * Constructor to change a dicePlacementCondition
	 * @param pickedDicesSlotData previous pickedDicesSlotData
	 * @param index index where make the change
	 * @param dicePlacementCondition dicePlacementCondition to add
	 */
	private PickedDicesSlotData(PickedDicesSlotData pickedDicesSlotData, int index, DicePlacementCondition dicePlacementCondition)
	{
		ArrayList<DicePlacementCondition> temp = new ArrayList<>(pickedDicesSlotData.pickedDices);
		temp.remove(index);
		temp.add(index, dicePlacementCondition);
		pickedDices = Collections.unmodifiableList(temp);
	}

	/**
	 * get number of dices
	 * @return number of picked dices
	 */
	public int getNDices()
	{
		return pickedDices.size();
	}

	/**
	 * Get dicePlacementCondition in position index
	 * @param index position
	 * @return dicePlacementCondition in position index
	 */
	public DicePlacementCondition getDicePlacementCondition(int index)
	{
		if(exists(index))
		{
			return pickedDices.get(index);
		}
		return null;
	}

	/**
	 * Verify if position index is free
	 * @param index position
	 * @return result of the check
	 */
	private boolean exists(int index) {
		try {
			pickedDices.get(index);
			return true;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * Add a dice (dicePlacementCondition)
	 * @param dice dice to add
	 * @param ignoreColor ignore color
	 * @param ignoreValue ignore value
	 * @param ignoreAdjacent ignore adjacent
	 * @return new PickedDicesSlotData with added dice
	 */
	PickedDicesSlotData add(Dice dice, boolean ignoreColor, boolean ignoreValue, boolean ignoreAdjacent)
	{
		return new PickedDicesSlotData(this, new DicePlacementCondition(dice, ignoreColor, ignoreValue, ignoreAdjacent));
	}

	/**
	 * Remove dice in position index
	 * @param index position
	 * @return PickedDicesSlotData without removed dice
	 */
	PickedDicesSlotData remove(int index)
	{
		if(exists(index))
		{
			return new PickedDicesSlotData(this, index);
		}
		return this;
	}

	/**
	 * Set ignore color of dice in position index
	 * @param index position
	 * @param ignoreColor new value for ignore color
	 * @return PickedDicesSlotData with modified value
	 */
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

	/**
	 * Set ignore value of dice in position index
	 * @param index position
	 * @param ignoreValue new value for ignore value
	 * @return PickedDicesSlotData with modified value
	 */
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

	/**
	 * Set ignore adjacent of dice in position index
	 * @param index position
	 * @param ignoreAdjacent new value for ignore adjacent
	 * @return PickedDicesSlotData with modified value
	 */
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

	/**
	 * Change dice in position index
	 * @param index position
	 * @param dice dice to set
	 * @return PickedDicesSlotData with changed dice
	 */
	PickedDicesSlotData changeDice(int index, Dice dice)
	{
		DicePlacementCondition dpc = getDicePlacementCondition(index);
		if(dpc!=null)
		{
			DicePlacementCondition dicePlacementCondition = new DicePlacementCondition(dice, dpc.getIgnoreColor(), dpc.getIgnoreValue(), dpc.getIgnoreAdjacent());
			return new PickedDicesSlotData(this, index, dicePlacementCondition);
		}
		return this;
	}

}
