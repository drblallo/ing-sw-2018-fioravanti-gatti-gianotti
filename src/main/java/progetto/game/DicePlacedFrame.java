package progetto.game;

import progetto.utils.AbstractObservable;

/**
 * Player window with positioned dice
 */
public final class DicePlacedFrame extends AbstractObservable<DicePlacedFrameData> {

	private DicePlacedFrameData dicePlacedFrameData = new DicePlacedFrameData();

	/**
	 * Get dicePlacedFrameData
	 * @return dicePlacedFrameData
	 */
	public DicePlacedFrameData getDicePlacedFrameData()
	{
		return dicePlacedFrameData;
	}

	/**
	 * Place a dice in the dice placed frame in the selected position
	 * @param newDice dice to add
	 * @param x pos horizontal
	 * @param y pos vertical
	 */
	void addDice(Dice newDice, int y, int x)
	{
		dicePlacedFrameData = dicePlacedFrameData.addDice(newDice, y, x);
		change(dicePlacedFrameData);
	}

	/**
	 * Remove a dice from the dice placed frame, from the selected position
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @return the removed dice
	 */
	Dice removeDice(int y, int x)
	{
		Dice dice = dicePlacedFrameData.getDice(y, x);
		dicePlacedFrameData = dicePlacedFrameData.removeDice(y, x);
		change(dicePlacedFrameData);
		return dice;
	}

}