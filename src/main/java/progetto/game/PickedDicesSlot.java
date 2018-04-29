package progetto.game;

import progetto.utils.AbstractObservable;

import java.util.ArrayList;

/**
 * Dices that still need to be placed that have been taken by the player
 */
public class PickedDicesSlot extends AbstractObservable<PickedDicesSlot> {

	private ArrayList<DicePlacementCondition> pickedDices = new ArrayList<>();

	void add(Dice dice, boolean ignoreColor, boolean ignoreValue, boolean ignoreAdjacent)
	{
		pickedDices.add(new DicePlacementCondition(dice, ignoreColor, ignoreValue, ignoreAdjacent));
		change(this);
	}

	public int getNDices()
	{
		return pickedDices.size();
	}

	DicePlacementCondition remove(int index)
	{
		DicePlacementCondition dicePlacementCondition;
		dicePlacementCondition = pickedDices.remove(index);
		change(this);
		return dicePlacementCondition;
	}


}
