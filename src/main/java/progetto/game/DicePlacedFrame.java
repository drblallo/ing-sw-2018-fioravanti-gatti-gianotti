package progetto.game;

import progetto.utils.AbstractObservable;

/**
 * Player window with positioned dice
 */
public final class DicePlacedFrame extends AbstractObservable<DicePlacedFrameData> {

	private DicePlacedFrameData dicePlacedFrameData = new DicePlacedFrameData();

	public DicePlacedFrameData getDicePlacedFrameData()
	{
		return dicePlacedFrameData;
	}

	void addDice(Dice newDice, int x, int y)
	{
		dicePlacedFrameData = dicePlacedFrameData.addDice(newDice, x, y);
		change(dicePlacedFrameData);
	}

	Dice removeDice(int x, int y)
	{
		Dice dice = dicePlacedFrameData.getDice(x, y);
		dicePlacedFrameData = dicePlacedFrameData.removeDice(x, y);
		change(dicePlacedFrameData);
		return dice;
	}

}