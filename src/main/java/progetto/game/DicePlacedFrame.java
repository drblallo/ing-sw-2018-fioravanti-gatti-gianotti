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
	void addDice(Dice newDice, int x, int y)
	{
		dicePlacedFrameData = dicePlacedFrameData.addDice(newDice, x, y);
		change(dicePlacedFrameData);
	}

	/**
	 * Remove a dice from the dice placed frame, from the selected position
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @return the removed dice
	 */
	Dice removeDice(int x, int y)
	{
		Dice dice = dicePlacedFrameData.getDice(x, y);
		dicePlacedFrameData = dicePlacedFrameData.removeDice(x, y);
		change(dicePlacedFrameData);
		return dice;
	}

}